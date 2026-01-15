import java.util.ArrayList;

public class mergeBst {

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

    public static Node merge(Node root1,Node root2){
        //step 1
        ArrayList<Integer> arr1=new ArrayList<>();
        getInorder(root1, arr1);

        //step 2
        ArrayList<Integer> arr2=new ArrayList<>();
        getInorder(root2, arr2);
        int i=0,j=0;

        ArrayList<Integer> merged=new ArrayList<>();

        while(i<arr1.size() && j<arr2.size()){
            if(arr1.get(i)<=arr2.get(j)){
                merged.add(arr1.get(i));
                i++;
            }else{
                merged.add(arr2.get(j));
                j++;
            }
        }

        while (i<arr1.size()) {
            merged.add(arr1.get(i));
            i++;
            
        }

        while (j<arr2.size()) {
            merged.add(arr2.get(j));
            j++;
            
        }

        //sorted arraylist to balaced bst
        return createBST(merged, 0, merged.size()-1);

    }

    public static void printInorder(Node root){
        if(root == null){
            return;
        }
        printInorder(root.left);
        System.out.print(root.data + " ");
        printInorder(root.right);
    }


    public static void main(String[] args) {
        
        Node root1=new Node(2);
        root1.left=new Node(1);
        root1.right=new Node(4);

        Node root2=new Node(9);
        root2.left=new Node(3);
        root2.right=new Node(12);

        Node root=merge(root1, root2);
        printInorder(root);
        System.out.println();
    }
    
}
