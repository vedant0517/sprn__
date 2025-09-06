import java.util.*;
public class BinaryTreeB{
    // O(n)
    static class Node{
        int data;
        Node left,right;

        Node(int data){
            this.data=data;
            this.left=null;
            this.right=null;
        }
    }

    static class BinaryTree{
        static int idx =-1;
        public static Node buildTree(int nodes[]){
            idx++;
            if(nodes[idx]==-1 || idx>=nodes.length)  return null;

            Node newNode=new Node(nodes[idx]);
            newNode.left=buildTree(nodes);
            newNode.right=buildTree(nodes);
            return newNode;
        }

        public static void preorder(Node root){
            //O(n)
            if(root ==null){
               // System.out.print("-1 ");
                return;
            }
            System.out.print(root.data+" ");
            preorder(root.left);
            preorder(root.right);
        }

          public static void inorder(Node root){
            //O(n)
            if(root ==null){
          //      System.out.print(" ");
                return;
            }
            
            inorder(root.left);
            System.out.print(root.data+" ");
            inorder(root.right);
        }

         public static void postorder(Node root){
            //O(n)
            if(root ==null){
          //      System.out.print(" ");
                return;
            }
            
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data+" ");
        }

        public static void levelOrder(Node root){
            //O(n)
            if(root == null) return;
            
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            queue.add(null); // Level separator
            
            while(!queue.isEmpty()){
                Node currNode = queue.remove();
                if(currNode == null){
                    System.out.println(); // New line for new level
                    if(queue.isEmpty()){
                        break;
                    } else {
                        queue.add(null);
                    }
                } else {
                    System.out.print(currNode.data + " ");
                    if(currNode.left != null){
                        queue.add(currNode.left);
                    }
                    if(currNode.right != null){
                        queue.add(currNode.right);
                    }
                }
            }
        }

        public static int height(Node root){
            //O(n) - Time Complexity
            //O(h) - Space Complexity where h is height
            if(root == null){
                return 0;  // Height of empty tree is 0
            }
            
            int leftHeight = height(root.left);
            int rightHeight = height(root.right);
            
            return Math.max(leftHeight, rightHeight) + 1;
        }

        public static int countNodes(Node root){
            //O(n) - Count total number of nodes
            if(root == null){
                return 0;
            }
            
            int leftCount = countNodes(root.left);
            int rightCount = countNodes(root.right);
            
            return leftCount + rightCount + 1;
        }

        public static int sumOfNodes(Node root){
            //O(n) - Sum of all nodes
            if(root == null){
                return 0;
            }
            
            int leftSum = sumOfNodes(root.left);
            int rightSum = sumOfNodes(root.right);
            
            return leftSum + rightSum + root.data;
        }

        public static int diameter(Node root){
            //O(n^2) - Diameter is longest path between any two nodes
            if(root == null){
                return 0;
            }
            
            int leftDiameter = diameter(root.left);
            int rightDiameter = diameter(root.right);
            int leftHeight = height(root.left);
            int rightHeight = height(root.right);
            
            int selfDiameter = leftHeight + rightHeight + 1;
            
            return Math.max(selfDiameter, Math.max(leftDiameter, rightDiameter));
        }

        
    }
    public static void main(String[] args) {
        // Method 1: Building tree from array
        int nodes[]={1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
        BinaryTree.idx = -1; // Reset idx before building tree
        Node root = BinaryTree.buildTree(nodes);
        
        System.out.println("=== Binary Tree Operations ===");
        System.out.println("\nTree structure:");
        /*
                  1
                 / \
                2   3
               / \   \   
              4   5   6
        */
        
        System.out.print("Preorder Traversal: ");
        BinaryTree.preorder(root);
        System.out.println();
        
        System.out.print("Inorder Traversal: ");
        BinaryTree.inorder(root);
        System.out.println();
        
        System.out.print("Postorder Traversal: ");
        BinaryTree.postorder(root);
        System.out.println();
        
        System.out.println("\nLevel Order Traversal:");
        BinaryTree.levelOrder(root);
        
        System.out.println("\n=== Tree Properties ===");
        System.out.println("Height of tree: " + BinaryTree.height(root));
        System.out.println("Total nodes: " + BinaryTree.countNodes(root));
        System.out.println("Sum of all nodes: " + BinaryTree.sumOfNodes(root));
        System.out.println("Diameter of tree: " + BinaryTree.diameter(root));
        
        System.out.println("\n=== Method 2: Manual tree construction ===");
        // Method 2: Manual construction
        Node root2 = new Node(1);
        root2.left = new Node(2);
        root2.right = new Node(3);
        root2.left.left = new Node(4);
        root2.left.right = new Node(5);
        root2.right.left = new Node(6);
        root2.right.right = new Node(7);
        
        /*
                  1
                 / \
                2   3
               / \ / \   
              4  5 6  7
        */
        
        System.out.println("Height of manually built tree: " + BinaryTree.height(root2));
        System.out.println("Total nodes in manual tree: " + BinaryTree.countNodes(root2));
        System.out.println("Sum of manual tree: " + BinaryTree.sumOfNodes(root2));
        System.out.println("Diameter of manual tree: " + BinaryTree.diameter(root2));
    }
}