public class QueueArray {

    // Queue using Array
    
    // static class Queue{
    //     static int arr[],size;
    //     static int rear;

    //     Queue(int n){
    //         arr=new int[n];
    //         size=n;
    //         rear=-1;
    //     }
    //     public static boolean isEmpty(){
    //         return rear==-1;
    //     }
    //     //Add
    //     public static void add(int data){
    //         if(rear==size-1){
    //             System.out.println("Queue is full");
    //             return;
    //         }
    //         rear=rear+1;
    //         arr[rear]=data;
    //     }
    //     //Remove
    //     public static int remove(){
    //         if(isEmpty()){
    //             System.out.println("Queue is empty");
    //             return -1;
    //         }
    //         int front=arr[0];
    //         for(int i=0;i<rear;i++){
    //             arr[i]=arr[i+1];
    //         }
    //         rear-=1;
    //         return front;
    //     }

    //     //peek
    //     public static int peek(){
    //         if(isEmpty()){
    //             System.out.println("Empty queue");
    //             return -1;
    //         }
    //         return arr[0];
    //     }

    // }

   // Circular Queue using Array
    
    static class Queue{
        static int arr[],size;
        static int rear,front;

        Queue(int n){
            arr=new int[n];
            size=n;
            rear=-1;
            front=-1;
        }
        public static boolean isEmpty(){
            return rear==-1 && front==-1;
        }

        public static boolean isFull(){
            return (rear+1)%size ==front;
        }
        //Add
        public static void add(int data){
            if(isFull()){
                System.out.println("Queue is full");
                return;
            }
            //add first element
            if(front ==-1){
                front=0;
            }
            rear=(rear+1)%size;
            arr[rear]=data;
        }
        //Remove
        public static int remove(){
            if(isEmpty()){
                System.out.println("Queue is empty");
                return -1;
            }

            int result=arr[front];
            //last ele del
            if(rear==front){
                rear=front=-1;
            }else{
                front=(front+1)%size;
            }
            return result;
            
            
        }

        //peek
        public static int peek(){
            if(isEmpty()){
                System.out.println("Empty queue");
                return -1;
            }
            return arr[front];
        }

    }

    public static void main(String[] args) {
        // Queue q=new Queue(5);
        // q.add(1);
        // q.add(4);
        // q.add(6);

        // while(!q.isEmpty())
        // {
        //     System.out.println(q.peek());
        //     q.remove();
        // }

        Queue q=new Queue(3);
        q.add(1);
        q.add(2);
        q.add(3);
        System.out.println(q.remove());
        q.add(4);

        while(!q.isEmpty())
        {
            System.out.println(q.peek());
            q.remove();
        }


    }
}
