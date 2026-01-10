import java.util.*;
import java.util.LinkedList;

import org.w3c.dom.Node;
public class Kth_level {
    static class Node{
        int data;
        Node left,right;

        Node(int data){
            this.data=data;
            this.left=null;
            this.right=null;
        }
    }

    public static void printKthLevelIteration(Node root, int k) {
        if (root == null || k < 1)
            return;

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int level = 1;

        while (!q.isEmpty()) {
            int size=q.size();

            if (level == k) {
                for (int i = 0; i < size; i++) {
                    Node curr = q.remove();
                    System.out.print(curr.data + " ");
                }
                return; 
            }

            for (int i = 0; i < size; i++) {
                Node curr = q.remove();
                if (curr.left != null)
                    q.add(curr.left);
                if (curr.right != null)
                    q.add(curr.right);
            }
            level++;
        }
    }


        public static void printKthLevelRecursive(Node root,int level, int k) {  //O(n)
            if((root==null || k<1)){
                return;
            }
            if(level == k){
                System.out.print(root.data+" ");
                return;
            }

            printKthLevelRecursive(root.left, level+1, k);
            printKthLevelRecursive(root.right, level+1, k);

    }

    public static void main(String[] args) {

         /*
                 1
               /   \
              2     3
             / \   / \
            4   5 6   7
        */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        int k = 3;
        System.out.print("Using iteration: "); printKthLevelIteration(root, k);
        System.out.println(); printKthLevelRecursive(root, 1, 2);
        
    }




    
    
}
