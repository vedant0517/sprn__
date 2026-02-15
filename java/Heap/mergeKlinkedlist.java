import java.util.*;

// Node class to represent each element in the linked list
class Node { 
   int data;  // Value stored in the node
   Node next; // Reference to the next node
   
   // Constructor to create a new node
   Node(int key){ 
    data = key; 
    next = null; // Next is null by default
   } 
} 

// Custom comparator for nodes to create a min-heap based on node data
// This ensures that the node with the smallest value stays at the top of the priority queue
class NodeComparator implements Comparator<Node>{
    public int compare(Node k1,Node k2){
        if(k1.data < k2.data){
            return -1;  // k1 has higher priority (smaller value)
        }
        else if(k1.data > k2.data){
            return 1;   // k2 has higher priority
        }else{
            return 0;   // Both have equal priority
        }
    }
}
public class mergeKlinkedlist {
    // Function to merge k sorted linked lists using a min-heap (priority queue)
    // Time Complexity: O(N*log(k)) where N is total nodes and k is number of lists
    // Space Complexity: O(k) for the priority queue
    static Node mergeKlist(Node []arr,int k){
        // Create a min-heap using custom comparator
        PriorityQueue<Node> queue =new PriorityQueue<>(new NodeComparator());
        
        // Dummy node to simplify result list construction
        Node head=new Node(0);
        Node last = head;  // Pointer to track the last node in merged list

        // Add the first node of each k lists to the priority queue
        for(int i=0 ;i<k;i++){
            if(arr[i] != null){
                queue.add(arr[i]);
            }
        }
        
        // If all lists are empty
        if(queue.isEmpty()){
            return null;
        }
        
        // Process nodes until priority queue is empty
        while(!queue.isEmpty()){
            // Get the node with minimum value from heap
            Node curr=queue.poll();
            
            // Add it to the merged list
            last.next=curr;
            last=last.next;
            
            // If the extracted node has a next node, add it to the heap
            if(curr.next != null){
                queue.add(curr.next);
            }
        }
        
        // Return the merged list (skip dummy head)
        return head.next;
    }

    // Utility function to print a linked list
    public static void printList(Node node){
        while(node != null){
            System.out.print(node.data+" ");
            node=node.next;
        }
    }

    public static void main(String[] args) {
        // Number of linked lists to merge
        int n=3;
        Node[] a =new Node[n];  // Array to hold head nodes of k lists
        
        // Create first sorted linked list: 1 -> 3 -> 5 -> 7
        Node head1=new Node(1);
        a[0]=head1;
        head1.next=new Node(3);
        head1.next.next=new Node(5);
        head1.next.next.next=new Node(7);
        
        // Create second sorted linked list: 2 -> 4 -> 6 -> 8
        Node head2=new Node(2);
        a[1]=head2;
        head2.next = new Node(4); 
        head2.next.next = new Node(6); 
        head2.next.next.next = new Node(8); 
 
        // Create third sorted linked list: 0 -> 9 -> 10 -> 11
        Node head3 = new Node(0); 
        a[2] = head3; 
        head3.next = new Node(9); 
        head3.next.next = new Node(10); 
        head3.next.next.next = new Node(11);
        
        // Merge all k sorted lists
        Node res = mergeKlist(a, n); 
 
        // Print the merged sorted list
        // Expected output: 0 1 2 3 4 5 6 7 8 9 10 11
        if (res != null) 
            printList(res); 
        System.out.println(); 
    }
}
