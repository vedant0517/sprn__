public class LinkedList {
    public static class Node{
        int data;
        Node next;
        public Node(int data){
            this.data=data;
            this.next=null;
        }
    }
    public static Node head;
    public static Node tail;
    public static int size;

    public void addFirst(int data){
        //step 1
        Node newNode=new Node(data);
        size++;
        if(head==null){
            head=tail=newNode;
            return;
        }

        //step 2
        newNode.next=head; //link

        //step 3
        head=newNode;
    }

    public void addLast(int data){
        Node newNode=new Node(data);
        size++;
        if(head==null){
            head=tail=newNode;
            return;
        }
        tail.next=newNode;
        tail=newNode;

    }

    public void addMiddle(int idx,int data){
        if(idx==0){
            addFirst(data);
            return;
        }
        size++;
        Node newNode=new Node(data);  
        Node temp=head;
        int i=0;
        while(i<idx-1){
            temp=temp.next;
            i++;
        }
        //i=idx-1;  temp->prev
        newNode.next=temp.next;
        temp.next=newNode;
    }

    public int removeFirst(){
        if(size==0){
            System.out.println("LL is Empty");
            return Integer.MIN_VALUE;
        } else if(size==1){
            int val=head.data;
            head=tail=null;
            size=0;
            return val;
        }
        int val=head.data;
        head=head.next;
        size--;
        return val;
    }

    public int removeLast(){
         if(size==0){
            System.out.println("LL is Empty");
            return Integer.MIN_VALUE;
        } else if(size==1){
            int val=head.data;
            head=tail=null;
            size=0;
            return val;
        }

        //previous node i=size-2
        Node prev=head;
        for(int i=0;i<size-2;i++){
            prev=prev.next;
        }
        int val=prev.next.data; //tail.data
        prev.next=null;
        tail=prev;
        size--;
        return val;
    }

    public int itrSearch(int key){//O(n)
        Node temp=head;int i=0;
        while(temp!=null){
            if(temp.data == key){
                return i;
            }
            temp=temp.next;
            i++;
        }
        //key not found
        return -1;
    }

    public int helper(Node head,int key){
        if(head == null){
            return -1;
        }
        if(head.data == key){
            return 0;
        }
        int idx =helper(head.next,key);
        if(idx == -1){ 
            return -1;
        }
        return idx+1;


    }
    public int recSearch(int key){
          return helper(head, key);

    }

    public void reverse(){
        Node prev=null,curr=tail=head,next;

        while(curr != null){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        head=prev;
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
        LinkedList ll=new LinkedList();
        ll.addFirst(2);
        ll.addFirst(1);
        ll.addLast(4);
        ll.addLast(5);
        ll.addMiddle(2,3);

        ll.print(); //1-2-3-4-5
        // System.out.println(ll.recSearch(3));
        // System.out.println(ll.recSearch(10));
        ll.reverse();
        ll.print();
        
       

        
        
        
        
    }
    
}
