import java.util.ArrayList;

public class bst2balced { //O(n)
static class Node{
        int data;
        Node left,right;

        Node(int data){
            this.data=data;
            this.left=this.right=null;
        }
    }

    public static void getInorder(Node root,ArrayList<Integer> inorder){
        if(root == null){
            return;
        }
        getInorder(root.left, inorder);
        inorder.add(root.data);
        getInorder(root.right, inorder);
    }

    public static Node createBST(ArrayList<Integer> inorder,int st,int end){
        if(st>end) return null;

        int mid=st+(end-st)/2;
        Node root=new Node(inorder.get(mid));
        root.left=createBST(inorder, st,mid-1);
        root.right=createBST(inorder, mid+1, end);
        return root;
    } 

    public static void preOrder(Node root){
        if(root == null){
            return;
        }
        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void printInorder(Node root){
        if(root == null){
            return;
        }
        printInorder(root.left);
        System.out.print(root.data + " ");
        printInorder(root.right);
    }

    public static Node balancedBST(Node root){
        //inorder sequence
        ArrayList<Integer> inorder=new ArrayList<>();
        getInorder(root, inorder);

        //sorted inorder to balced bst
        root=createBST(inorder, 0, inorder.size()-1);
        return root;
    }
    public static void main(String[] args) {
        /*
                     8
                   /   \
                  6     10
                 /        \
                5          11
               /            \
              3              12 
        given bst

                Result (balanced BST after calling balancedBST):

                                 8
                             /   \
                            5     11
                         / \   /  \
                        3   6 10  12
        
        */

        // Create the exact tree shown above
        Node root = new Node(8);
        root.left = new Node(6);
        root.right = new Node(10);
        root.left.left = new Node(5);
        root.left.left.left = new Node(3);
        root.right.right = new Node(11);
        root.right.right.right = new Node(12);

        System.out.print("Original inorder: ");
        printInorder(root);
        System.out.println();

        Node balanced = balancedBST(root);

        System.out.print("Balanced inorder: ");
        printInorder(balanced);
        System.out.println();

        System.out.print("Balanced preorder: ");
        preOrder(balanced);
        System.out.println();
    }
    
}
