import java.util.*;
// public class palindrome{
//     public static boolean check(String s1){
//         for(int i=0;i<s1.length()/2;i++){
//             if(s1.charAt(i)!=s1.charAt(s1.length()-i-1))
//             {
//             System.out.println(s1 + " not Palindrome");
//             return false;}
            
            
//         }
//         System.out.println(s1 + " is Palindrome");
//         return true;
//     }
    public static float getShortPath(String path){
        int x=0,y=0;
    for(int i=0;i<path.length();i++){
        char dir=path.charAt(i);
        
        //north
        if(dir=='N'){
            y++;
        }
        //south
        else if(dir=='S'){
            y--;
        }
        //east
        else if(dir=='E'){
            x++;
        }
        //west
        else{
            x--;
        }
        
    }  int X2=x*x,Y2=y*y;
    return (float)Math.sqrt(X2+Y2);
 }
    public static void main(String argz[]){
        // Scanner sc=new Scanner(System.in);
        // System.out.println("Enter string to check palindrome or not");
        // String s1;
        // s1=sc.next();
        // check(s1);
        String path="WNEENESENNN";
        
        System.out.println(getShortPath(path));

    }











