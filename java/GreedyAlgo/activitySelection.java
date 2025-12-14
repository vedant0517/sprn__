//O(n)
//O(n)
import java.util.ArrayList;
import java.util.*;

public class activitySelection {
    public static void main(String[] args) {
        int start[]={1,3,0,5,8,5};
        int end[]= {2,4,6,7,9,9};

        // //if not given sorted array then sorting
        // int activites[][]=new int[start.length][3];
        // for(int i=0;i<start.length;i++){
        //     activites[i][0]=i;
        //     activites[i][1]=start[i];
        //     activites[i][2]=end[i];
        // }
        // //lambda functions     //comparator is an interface shows how objects are sorted
        // Arrays.sort(activites,Comparator.comparingDouble(o -> o[2]));
       
        //end time already sorted
         int maxAct=0;
        ArrayList<Integer>ans=new ArrayList<>();

        maxAct=1;
        ans.add(0);
        int lastEnd=end[0];
        for(int i=1;i<end.length;i++)
        {
            if(start[i]>=lastEnd){
                //activity selection
                maxAct++;
                ans.add(i);
                lastEnd=end[i];
            }
        }
        System.out.println("Max Activities : "+maxAct);
        for(int i=0;i<ans.size();i++){
            System.out.print("A"+ans.get(i)+" ");
        }

    }
    
}











//nlog(n)
// //if end aray is not sorted then use this 

// import java.util.ArrayList;
// import java.util.*;

// public class activitySelection {
//     public static void main(String[] args) {
//         int start[]={1,3,0,5,8,5};
//         int end[]= {2,4,6,7,9,9};

//         //if not given sorted array then sorting
//         int activites[][]=new int[start.length][3];
//         for(int i=0;i<start.length;i++){
//             activites[i][0]=i;
//             activites[i][1]=start[i];
//             activites[i][2]=end[i];
//         }
//         //lambda functions     //comparator is an interface shows how objects are sorted
//         Arrays.sort(activites,Comparator.comparingDouble(o -> o[2]));
       
//         //end time already sorted
//          int maxAct=0;
//         ArrayList<Integer>ans=new ArrayList<>();

//         maxAct=1;
//         ans.add(activites[0][0]);
//         int lastEnd=activites[0][2];
//         for(int i=1;i<activites.length;i++)
//         {
//             if(activites[i][1]>=lastEnd){
//                 //activity selection
//                 maxAct++;
//                 ans.add(activites[i][0]);
//                 lastEnd=activites[i][2];
//             }
//         }
//         System.out.println("Max Activities : "+maxAct);
//         for(int i=0;i<ans.size();i++){
//             System.out.print("A"+ans.get(i)+" ");
//         }

//     }
    
// }
