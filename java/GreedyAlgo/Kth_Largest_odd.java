import java.util.*;
public class Kth_Largest_odd {
    public static int kthOdd(int L,int R,int K){
        int totalNumber=(R-L+1);
        int numberOfOdd;

        if(L%2!=0 && R%2!=0){
            numberOfOdd=(totalNumber/2)+1;
        }else{
            numberOfOdd=totalNumber/2;
        }
        if(K>numberOfOdd){
            return 0;
        }
        int largest_val;
        if(R%2!=0){
            largest_val=R;
        }else{
            largest_val=R-1;
        }
        return largest_val-((K-1)*2);
    }
    public static void main(String[] args) {
        System.out.println(kthOdd(-10000, 10000, 25





















            
        ));
        
    }
}


// import java.util.*;
// public class Kth_Largest_odd {
//     public static void main(String[] args) {
//         int l=-3,r=3,k=2;
//         if(k<=0){
//             return;
//         }
//         ArrayList<Integer> oddList=new ArrayList<>();

//         for(int i=r;i>=l;i--){
//             if(i%2!=0){
//                 oddList.add(i);
//             }
//         }
//         System.out.println(oddList.get(k-1));
        
//     }
// }
