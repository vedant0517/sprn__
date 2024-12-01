// package arrays;
import java.util.*;
public class gratest {
    // public static int great(int num[]){
    //     int max=num[0];
    //     for(int i=0;i<num.length;i++){
    //         if(max<num[i]){
    //             max=num[i];
    //         }
    //     }
    //     System.out.println("greatest is "+max);
    //         return 0;
    //     }

        public static int getLargest(int num[]){
            int largest=Integer.MIN_VALUE;  //-infinity
            int smallest=Integer.MAX_VALUE;
            for(int i=0;i<num.length;i++){
                if(largest<num[i]){
                    largest=num[i];
                }
                if(smallest>num[i]){
                    smallest=num[i];
                }
            }System.out.println("Largest num is "+largest);
            System.out.println("Largest num is "+smallest);
     return 0;
        }
           public static void main(String args[]){
        int num[]={1,2,6,3,5};
        //great(num); 
        getLargest(num);  
}
}
