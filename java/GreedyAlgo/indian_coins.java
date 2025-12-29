import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import java.util.*;
public class indian_coins {
    public static void main(String[] args) {
        Integer coins[]={1,2,5,10,20,50,100,500,2000};
        Arrays.sort(coins,Comparator.reverseOrder());

        int count=0;

    int amt=111;
    ArrayList<Integer>ans=new ArrayList<>();

    for(int i=0;i<coins.length;i++){
        if(coins[i]<=amt){
            while(amt>=coins[i]){
                count++;
                ans.add(coins[i]);
                amt-=coins[i];
            }
        }
    }
    System.out.println("Coins Min Required: "+count);
    for(int i=0;i<ans.size();i++){
        System.out.print(ans.get(i)+" ");
    }
  }
    
}
