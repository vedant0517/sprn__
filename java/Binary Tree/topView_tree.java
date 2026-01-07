import java.util.*;
import java.util.LinkedList;

public class topView_tree {
   static class Node{
        int data;
        Node left,right;

        Node(int data){
            this.data=data;
            this.left=null;
            this.right=null;
        }
    }

    static class Info {
        Node node;
        int horDis;

        public Info(Node n,int h){
            this.node=n;
            this.horDis=h;
        } 
    }

    public static void topView(Node root){
        //Level Order
        Queue<Info> q=new LinkedList<>();
        HashMap<Integer,Node> map=new HashMap<>();

        int min=0,max=0;

        q.add(new Info(root,0));
        q.add(null);

        while(!q.isEmpty()){
            Info curr=q.remove();
            if(curr==null){
                if(q.isEmpty()){
                    break;
                }else{
                    q.add(null);
                }
            } else {
                if(!map.containsKey(curr.horDis)){//first time my hd is occuring
                    map.put(curr.horDis,curr.node);
                }
                
                if(curr.node.left !=null){
                    q.add(new Info(curr.node.left, curr.horDis-1));
                    min=Math.min(min,curr.horDis-1);
                }
                if(curr.node.right !=null){
                    q.add(new Info(curr.node.right,curr.horDis+1));
                    max=Math.max(max,curr.horDis+1);
                }
            }
        }

        for(int i=min;i<=max;i++){
            System.out.print(map.get(i).data + " ");
        }
    }

    public static void main(String[] args) {
        Node root=new Node(1);
        root.left=new Node(2);
        root.right=new Node(3);
        root.left.left=new Node(4);
        root.left.right=new Node(5);
        root.right.left=new Node(6);
        root.right.right=new Node(7);

        topView(root);
    }
}