import java.util.*;
import java.util.LinkedList;
public class nonRepeatingLetter {

    public static void printNonRepeating(String str)
    {
        int freq[]=new int[26]; //'a' --'z'
        Queue <Character> q=new LinkedList<>();

        for(int i=0;i<str.length();i++)
        {
            char ch=str.charAt(i);
            q.add(ch);
            freq[ch-'a']++;     //char to index

            while(!q.isEmpty() && freq[q.peek()-'a']>1){
                q.remove();
            }
            if(q.isEmpty()){
                System.out.print(-1+" ");
            }
            else{
                System.out.print(q.peek()+" ");
            }
        }
    }
    public static void main(String[] args) {
        String str="aabccxb";
        printNonRepeating(str);
    }
    
}
