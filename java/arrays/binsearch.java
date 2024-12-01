// package arrays;

public class binsearch {
    public static int binSearch(int num[],int key){
        int start=0,end=num.length-1;
        //compare
        while(start<=end){
            int mid=(start+end)/2;
        if(num[mid]==key){
          return mid;
        }
        if(num[mid]<key){
            start=mid+1;
        }else{
            end=mid-1;
        }
    }
    return -1;
    }
    public static void main(String[] args) {
        int num[]={2,4,6,8,10,12,14};
        int key=11;
        System.out.println("index for key is : "  +binSearch(num, key));
        
    }
    
}
