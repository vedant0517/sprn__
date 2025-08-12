class Polynomial {
    static class Node {
        int coeff;
        int pow;
        Node next;

        Node(int coeff, int pow) {
            this.coeff = coeff;
            this.pow = pow;
            this.next = null;
        }
    }

    Node head;

    // Insert term in descending order of power
    void insertTerm(int coeff, int pow) {
        if (coeff == 0) return; // Skip zero coefficient
        Node newNode = new Node(coeff, pow);

        if (head == null || head.pow < pow) {
            newNode.next = head;
            head = newNode;
            return;
        }

        Node temp = head;
        while (temp.next != null && temp.next.pow > pow) {
            temp = temp.next;
        }

        if (temp.next != null && temp.next.pow == pow) {
            temp.next.coeff += coeff;
        } else {
            newNode.next = temp.next;
            temp.next = newNode;
        }
    }

    // Display polynomial
    void display() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.coeff);
            if (temp.pow != 0) System.out.print("x^" + temp.pow);
            if (temp.next != null && temp.next.coeff >= 0)
                System.out.print(" + ");
            else if (temp.next != null)
                System.out.print(" ");
            temp = temp.next;
        }
        System.out.println();
    }

    // Add two polynomials
    static Polynomial addPoly(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial();
        Node poly1 = p1.head;
        Node poly2 = p2.head;

        while (poly1 != null && poly2 != null) {
            if (poly1.pow > poly2.pow) {
                result.insertTerm(poly1.coeff, poly1.pow);
                poly1 = poly1.next;
            } else if (poly1.pow < poly2.pow) {
                result.insertTerm(poly2.coeff, poly2.pow);
                poly2 = poly2.next;
            } else {
                result.insertTerm(poly1.coeff + poly2.coeff, poly1.pow);
                poly1 = poly1.next;
                poly2 = poly2.next;
            }
        }

        while (poly1 != null) {
            result.insertTerm(poly1.coeff, poly1.pow);
            poly1 = poly1.next;
        }
        while (poly2 != null) {
            result.insertTerm(poly2.coeff, poly2.pow);
            poly2 = poly2.next;
        }

        return result;
    }

    // Multiply two polynomials
    static Polynomial multiplyPoly(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial();

        for (Node poly1 = p1.head; poly1 != null; poly1 = poly1.next) {
            for (Node poly2 = p2.head; poly2 != null; poly2 = poly2.next) {
                result.insertTerm(poly1.coeff * poly2.coeff, poly1.pow + poly2.pow);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Polynomial poly1 = new Polynomial();
        Polynomial poly2 = new Polynomial();

        // Example: poly1 = 3x^3 + 2x^2 + 5
        poly1.insertTerm(3, 3);
        poly1.insertTerm(2, 2);
        poly1.insertTerm(5, 0);

        // Example: poly2 = 4x^2 + 1x + 2
        poly2.insertTerm(4, 2);
        poly2.insertTerm(1, 1);
        poly2.insertTerm(2, 0);

        System.out.print("Polynomial 1: ");
        poly1.display();

        System.out.print("Polynomial 2: ");
        poly2.display();

        Polynomial sum = addPoly(poly1, poly2);
        System.out.print("\nSum: ");
        sum.display();

        Polynomial product = multiplyPoly(poly1, poly2);
        System.out.print("Product: ");
        product.display();
    }
}
