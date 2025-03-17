import java.util.*;
public class xtoxn{
    public static int power(int x,int n){
        if(n==0){
            return 1;
        }else {
            return x * power(x, n-1);
        }
    }

    public static int optimizedPower(int x,int n){
        if(n==0){
            return 1;
        }
        int halfpower=optimizedPower(x, n/2);
        int halfPowerSq=halfpower*halfpower;
        
        if(n%2!=0){
             halfPowerSq*=x;
        }
        return halfPowerSq;
    }
    public static void main(String[] args) {
        System.out.println(power(2,10));
        System.out.println(optimizedPower(2, 10));    
    }
}