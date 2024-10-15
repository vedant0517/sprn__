//import java.util.*;
public class Strings {
    public static void main(String[] args) {
    //    Scanner sc=new Scanner(System.in);
    //    String name=sc.nextLine();
    //    System.out.println("Your name is : "+ name );
    // sc.close();

    //concatenation
    // String firstName="Vedant";
    // String lastName="Kapgate";
    // String fullName= firstName + " " + lastName;
    // System.out.println(fullName.length());

    //charAt
    // for(int i=0;i<fullName.length();i++){
    //     System.out.println(fullName.charAt(i));
    // }

    //compare
    String name1="vedant";
    String name2="vedant";

    //s1>s2 : +ve value
    //s1==s2 : 0
    //s1<s2 : -ve value

    if(name1.compareTo(name2) == 0){
        System.out.println("Strings are Equal");
    } else{
        System.out.println("Strings are not equal");
    }
    


}
}