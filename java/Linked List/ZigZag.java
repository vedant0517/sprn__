public class ZigZag {
    public static class Node{
        int data;
        Node next;
        public Node(int data){
            this.data=data;
            this.next=null;
        }
    }
    public static Node head;

      public void addFirst(int data){
        //step 1
        Node newNode=new Node(data);
        if(head==null){
            head=newNode;
            return;
        }
        //step 2
        newNode.next=head; //link
        //step 3
        head=newNode;
    }

    private Node getMid(Node head){
        Node slow=head,fast=head.next;
        while(fast !=null && fast.next!=null){
            slow=slow.next;//+1
            fast=fast.next.next;//+2
        }
        return slow;//slow is middle
    }

    public void zig(){
        //find mid
        Node mid=getMid(head);
        //reverse 2nd half
        Node curr=mid.next;
        mid.next=null;
        Node prev=null;Node next;

        while(curr!=null){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        Node leftH=head;
        Node rightH=prev;
        Node nextL,nextR;

        //alt merge - sig sag merge
        while(leftH != null && rightH != null){
    nextL = leftH.next;           // Save next node of left
    leftH.next = rightH;          // Link left node to current right node

    nextR = rightH.next;          // Save next node of right
    rightH.next = nextL;          // Link right node to saved next left

    leftH = nextL;                // Move leftH ahead
    rightH = nextR;               // Move rightH ahead
}

    }
      public void print(){
        if(head==null){
            System.out.println("LL is Empty");
            return;
        }
        Node temp=head;
        while(temp!=null){
            System.out.print(temp.data+"->");
            temp=temp.next;
        }
        System.out.println("null");
    }
    
    public static void main(String[] args) {
        ZigZag ll=new ZigZag();
         ll.addFirst(5);
        ll.addFirst(4);
        ll.addFirst(3);
        ll.addFirst(2);
        ll.addFirst(1);
        // 1 2 3 4 5 
        ll.print();
        ll.zig();
        ll.print();
        

    }
    
}
