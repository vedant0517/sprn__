import java.util.*;
public class fract_knapsack {
    public static void main(String[] args) {
        int val[]={60,100,120};
        int wt[]={10,20,30};
        int W=50;
        double ratio[][]=new double[val.length][2];  //0th col => idx  ,1st col=>ratio

        for(int i=0;i<val.length;i++){
            ratio[i][0]=i;
            ratio[i][1]=val[i]/(double)wt[i];
        }
        //ASCENDING ORDER SORT
        Arrays.sort(ratio,Comparator.comparingDouble(O -> O[1]));

        int capacity=W;
        int finalValue=0;
        for(int i=ratio.length-1;i>=0;i--){
            int idx=(int)ratio[i][0];
            if(capacity >=wt[idx]){  //include full item
                finalValue+=val[idx];
                capacity-=wt[idx];
            }
            else{
                //include fractonal item;
                finalValue+=(ratio[i][1]*capacity);
                capacity=0;
                break;
            }

        }
        System.out.println("Final Value : "+finalValue);
    }
    
}
