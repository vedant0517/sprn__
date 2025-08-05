public class CircularLL {

    public class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node last;

    // Add to empty list
    public void addToEmpty(int data) {
        if (last != null) return;

        Node newNode = new Node(data);
        last = newNode;
        last.next = last;
    }

    // Add at front
    public void addFirst(int data) {
        if (last == null) {
            addToEmpty(data);
            return;
        }

        Node newNode = new Node(data);
        newNode.next = last.next;
        last.next = newNode;
    }

    // Add at end
    public void addLast(int data) {
        if (last == null) {
            addToEmpty(data);
            return;
        }

        Node newNode = new Node(data);
        newNode.next = last.next;
        last.next = newNode;
        last = newNode;
    }

    // Add after a specific node
    public void addAfter(int data, int item) {
        if (last == null) return;

        Node curr = last.next;
        do {
            if (curr.data == item) {
                Node newNode = new Node(data);
                newNode.next = curr.next;
                curr.next = newNode;

                if (curr == last) {
                    last = newNode;
                }
                return;
            }
            curr = curr.next;
        } while (curr != last.next);

        System.out.println(item + " not found in the list.");
    }

    // Delete a node with given key
    public void delete(int key) {
        if (last == null) return;

        // Only one node
        if (last.data == key && last.next == last) {
            last = null;
            return;
        }

        Node curr = last.next;
        Node prev = last;

        // If head needs to be deleted
        if (curr.data == key) {
            while (prev.next != last.next) {
                prev = prev.next;
            }
            prev.next = curr.next;
            last.next = curr.next;
            return;
        }

        // Delete any middle node
        while (curr != last) {
            if (curr.data == key) {
                prev.next = curr.next;
                if (curr == last) {
                    last = prev;
                }
                return;
            }
            prev = curr;
            curr = curr.next;
        }
    }

    // Print circular linked list
    public void print() {
        if (last == null) {
            System.out.println("List is empty.");
            return;
        }

        Node temp = last.next;
        do {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        } while (temp != last.next);

        System.out.println("(head)");
    }

    public static void main(String[] args) {
        CircularLL cll = new CircularLL();

        cll.addToEmpty(6);
        cll.addLast(8);
        cll.addFirst(2);
        cll.addAfter(10, 2);

        cll.print();

        cll.delete(8);

        cll.print();
    }
}
