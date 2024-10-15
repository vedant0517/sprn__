package Practice;
import java.util.*;

public class P3 {
    public static double getCircum(double radius){
        return 2 * 3.14 * radius;
    } 
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        double radius=sc.nextDouble();
        getCircum(radius);
        System.out.println("Circumference is:"+ getCircum(radius));
        sc.close();

    }   
}
