class Node{
        int data;
        Node next;
        public Node(int data){
            this.data=data;
            this.next=null;
        }
    }

public class swaptovalue {
    Node head;
    public void swapNode(int x,int y){
        if(x==y)return;

        Node prevX=null,currX=head;
        while(currX!=null && currX.data!=x){
            prevX=currX;
            currX=currX.next;
        }

        Node prevY=null,currY=head;
        while(currY!=null && currY.data!=y){
            prevY=currY;
            currY=currY.next;
        }

        if (currX == null || currY == null) return;

        if(prevX!=null){
            prevX.next=currY;
        }else{
            head=currY;
    }
        if(prevY!=null){
            prevY.next=currX;
        }else{
            head=currX;
        }

        Node temp=currX.next;
        currX.next=currY.next;
        currY.next=temp;

    }
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
        swaptovalue ll=new swaptovalue();
        ll.addFirst(4);
        ll.addFirst(3);
        ll.addFirst(2);
        ll.addFirst(1);

        ll.print();
        ll.swapNode(2,4);
        ll.print();
        
    }
    
}
