public class LinkedList {
    public static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public Node head;
    public Node tail;
    public int size;

    public void addFirst(int data) {
        // step 1
        Node newNode = new Node(data);
        size++;
        if (head == null) {
            head = tail = newNode;
            return;
        }

        // step 2
        newNode.next = head; // link

        // step 3
        head = newNode;
    }

    public void addLast(int data) {
        Node newNode = new Node(data);
        size++;
        if (head == null) {
            head = tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
    }

    public void addMiddle(int idx, int data) {
        if (idx < 0 || idx > size) {
            throw new IndexOutOfBoundsException("Invalid index: " + idx);
        }
        if (idx == 0) {
            addFirst(data);
            return;
        }
        if (idx == size) {
            addLast(data);
            return;
        }
        size++;
        Node newNode = new Node(data);
        Node temp = head;
        int i = 0;
        while (i < idx - 1) {
            temp = temp.next;
            i++;
        }
        // i=idx-1; temp->prev
        newNode.next = temp.next;
        temp.next = newNode;
    }

    public int removeFirst() {
        if (size == 0) {
            System.out.println("LL is Empty");
            return Integer.MIN_VALUE;
        } else if (size == 1) {
            int val = head.data;
            head = tail = null;
            size = 0;
            return val;
        }
        int val = head.data;
        head = head.next;
        size--;
        return val;
    }

    public int removeLast() {
        if (size == 0) {
            System.out.println("LL is Empty");
            return Integer.MIN_VALUE;
        } else if (size == 1) {
            int val = head.data;
            head = tail = null;
            size = 0;
            return val;
        }

        // previous node i=size-2
        Node prev = head;
        for (int i = 0; i < size - 2; i++) {
            prev = prev.next;
        }
        int val = prev.next.data; // tail.data
        prev.next = null;
        tail = prev;
        size--;
        return val;
    }

    public int itrSearch(int key) { // O(n)
        Node temp = head;
        int i = 0;
        while (temp != null) {
            if (temp.data == key) {
                return i;
            }
            temp = temp.next;
            i++;
        }
        // key not found
        return -1;
    }

    private int helper(Node head, int key) {
        if (head == null) {
            return -1;
        }
        if (head.data == key) {
            return 0;
        }
        int idx = helper(head.next, key);
        if (idx == -1) {
            return -1;
        }
        return idx + 1;
    }

    public int recSearch(int key) {
        return helper(head, key);
    }

    private Node reverseList(Node node) {
        Node prev = null;
        Node curr = node;

        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public void reverse() {
        if (head == null || head.next == null) {
            return;
        }
        tail = head;
        head = reverseList(head);
    }

    public void deleteNthEnd(int n) {
        if (n <= 0 || n > size) {
            throw new IllegalArgumentException("n must be in range 1 to " + size);
        }
        if (n == size) {
            removeFirst();
            return;
        }

        int i = 1;
        int itoFind = size - n;
        Node prev = head;
        while (i < itoFind) {
            prev = prev.next;
            i++;
        }

        if (prev.next == tail) {
            tail = prev;
        }
        prev.next = prev.next.next;
        size--;
    }

    // Slow Fast Approach
    private Node findMid(Node head) {
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next; // +1
            fast = fast.next.next; // +2
        }
        return slow; // slow is middle
    }

    public boolean checkPalindrome() {
        if (head == null || head.next == null) {
            return true;
        }
        // step1:- find mid
        Node midNode = findMid(head);

        // step2:- reverse 2nd half
        Node rightHead = reverseList(midNode);
        Node right = rightHead;
        Node left = head;

        // step3:- check left half & right half
        while (right != null) {
            if (left.data != right.data) {
                reverseList(rightHead); // restore original list structure
                return false;
            }
            left = left.next;
            right = right.next;
        }

        reverseList(rightHead); // restore original list structure
        return true;
    }

    public void print() {
        if (head == null) {
            System.out.println("LL is Empty");
            return;
        }
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + "->");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        // ll.addFirst(2);
        // ll.addFirst(1);
        // ll.addLast(4);
        // ll.addLast(5);
        // ll.addMiddle(2,3);
        ll.addLast(1);
        ll.addLast(2);
        ll.addLast(2);
        ll.addLast(1);

        System.out.println(ll.checkPalindrome());
        // ll.print(); // 1-2-3-4-5
        // System.out.println(ll.recSearch(3));
        // System.out.println(ll.recSearch(10));
        // ll.reverse();
        // ll.deleteNthEnd(3);

        // ll.print();
    }
}
