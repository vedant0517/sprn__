public class MergeSort {
    public static class Node{
        int data;
        Node next;
        public Node(int data){
            this.data=data;
            this.next=null;
        }
    }
    public static Node head;
    // public static Node lHead;
    // public static Node RHead;

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

    private Node mergeAll(Node head1,Node head2){
        Node mergedLL=new Node(-1);
        Node temp=mergedLL;

        while(head1 != null && head2 != null){
            if(head1.data <=head2.data){
                temp.next=head1;
                head1=head1.next;
                temp=temp.next;
            }
            else{
                temp.next=head2;
                head2=head2.next;
                temp=temp.next;
            }
        }
        while(head1 != null){
            temp.next=head1;
            head1=head1.next;
            temp=temp.next;
        }
           while(head2 != null){
            temp.next=head2;
            head2=head2.next;
            temp=temp.next;
           }
           return mergedLL.next;
    }

    public Node merge(Node head){
        if(head==null || head.next==null){
            return head;
        }
        //find mid
        Node mid=getMid(head);

        //left and right
        Node rightHead=mid.next;
        mid.next=null;
        Node newLeft=merge(head);
        Node newRight=merge(rightHead);
        //mergeALL
        return mergeAll(newLeft,newRight);

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
        MergeSort ll=new MergeSort();
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(3);
        ll.addFirst(4);
        ll.addFirst(5);

        ll.print();
        ll.head=ll.merge(ll.head);
        ll.print();

        //O(nlogn)
        
    }
    
}
 