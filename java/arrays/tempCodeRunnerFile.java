// package arrays;

public class linearsearch {
    public static int linsearch(int num[],int key){
        for(int i=0;i<num.length;i++){
            if(num[i] == key){
                return i;
            }

        }
         return -1;
    }
    public static void main(String args[]){
        int num[]={2,4,6,8,10,12,14,16};
        int key=10;
        int index=linsearch(num, key);
        if(index==-1){
            System.out.println("Not Found");
        }else{
        System.out.println("key is at index "+index);}
    }
    
}