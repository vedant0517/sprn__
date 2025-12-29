import java.util.*;
public class max_balanced_str_partit {
    public static int balanced(String str,int n){
        if(n==0){
            return 0;
        }
        int l=0,r=0,ans=0;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='R'){
                r++;
            }else if(str.charAt(i)=='L'){
                l++;
            }
            if(l==r){
                r=0;l=0;
            ans++;
        }
        
        }
        return ans;
    }
    
    public static void main(String[] args) {
        String str="LLRRLLRR";
        System.out.println("Output : "+balanced(str, str.length()));

    }
    
}
