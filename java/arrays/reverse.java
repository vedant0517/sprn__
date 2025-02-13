// package arrays;

public class reverse {
    public static void reverseArray(int num[]){
       int first=0,last=num.length-1; 

       while(first < last)
       {
        //swap
        int temp=num[last];
        num[last]=num[first];
        num[first]=temp;

        first++;
        last--;
       }
       for(int i=0;i<num.length;i++){
        System.out.print(num[i]+" ");
    }
    }
    // public static void reverseArray1(int num[],int n[]){
    //     int j=0;
    //     for(int i=num.length-1;i>=0;i--){
    //         n[j]=num[i];
    //         j++;
    //     }
    //     for(j=0;j<n.length;j++){
    //         System.out.print(n[j]+" ");

    //     }
    // }
    public static void main(String[] args) {
        int num[]={2,4,6,8,10};
        // int n[]=new int[5];
        for(int i=0;i<=num.length-1;i++){
            System.out.print(num[i]+" ");
        }
        System.out.println();
       
         reverseArray(num);
        
    }
    
}
