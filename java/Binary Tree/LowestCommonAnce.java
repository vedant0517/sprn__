import java.util.ArrayList;

public class LowestCommonAnce {

    static class Node{
        int data;
        Node left,right;

        Node(int data){
            this.data=data;
            this.left=null;
            this.right=null;
        }
    }

    public static boolean getPath(Node root,int n,ArrayList <Node> p){

        if(root==null){
            return false;
        }
        p.add(root);
        if(root.data == n){
            return true;
        }
        boolean foundLeft=getPath(root.left, n, p);
        boolean foundRight=getPath(root.right, n, p);

        if(foundLeft || foundRight){
            return true;
        }
        
        p.remove(p.size()-1);
        return false;

    }


    public static Node lca(Node root,int n1,int n2){
        ArrayList<Node> p1 =new ArrayList<>();
        ArrayList<Node> p2 =new ArrayList<>();

        getPath(root,n1,p1);
        getPath(root,n2,p2);

        //last common ancestor
        int i=0;
        for(;i<p1.size() && i<p2.size();i++){
            if(p1.get(i)!=p2.get(i)){
                break;
            }
        }
        Node lca=p1.get(i-1);

        return lca;
    }

    public static Node lca2(Node root,int n1,int n2){

        if(root==null||root.data ==n1 || root.data==n2){
            return root;
        }
        Node leftLca=lca2(root.left, n1, n2);
        Node rightLca=lca2(root.right, n1, n2);

        //leftLca=val   rightLca=null
        if(rightLca==null){
            return leftLca;
        }

        if(leftLca==null){
            return rightLca;
        }

        return root;


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

        int n1=4,n2=6;

        System.out.println("Approach 1: "+lca(root, n1, n2).data);
        System.out.println("Approcah 2: "+lca2(root, n1, n2).data);

        
    }
    
}
