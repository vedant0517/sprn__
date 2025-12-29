import java.util.*;
public class chocola {
    public static void main(String[] args) {
        int n=4,m=6;
        Integer costVer[]={2,1,3,1,4};
        Integer costHor[]={4,1,2};
        Arrays.sort(costVer,Collections.reverseOrder());
         Arrays.sort(costHor,Collections.reverseOrder());

         int hcut=0,vcut=0;
         int hpiece=1,vpiece=1;
        int cost=0;
         while(hcut<costHor.length && vcut<costVer.length){
            //vertical cost<hor cost
            if(costVer[vcut]<=costHor[hcut]){
                cost+=(costHor[hcut]*vpiece);
                hpiece++;
                hcut++;
            }else{
                cost+=(costVer[vcut]*hpiece);
                vpiece++;
                vcut++;
            }
         }
         while(hcut<costHor.length){
             cost+=(costHor[hcut]*vpiece);
                hpiece++;
                hcut++;
         }
         while(vcut<costVer.length){
            cost+=(costVer[vcut]*hpiece);
                vpiece++;
                vcut++;
         }

         System.out.println("min cost of cut : " +cost);
        
    }
    
}
