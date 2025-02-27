import java.util.*;
class Complex{
    int real,imag;
    public Complex(int r,int i){
        real=r;
        imag=i;
    }

    public static Complex add(Complex a,Complex b){
        return new Complex ((a.real+b.real),(a.imag+b.imag));
    }
    public static Complex diff(Complex a,Complex b){
        return new Complex ((a.real-b.real),(a.imag-b.imag));
    } 
    public static Complex product(Complex a,Complex b){
        return new Complex (((a.real*b.real)-(a.imag*b.imag)),((a.imag*b.real)+(a.real*b.imag)));
    } 


    public void printComplex(){
        if(real == 0 && imag!=0){
            System.out.println(imag+"i");
        }
        else if(imag==0 && real !=0){
            System.out.println(real);
        }else if(real ==0 && imag==0){
            System.out.println("0");
        }
        else {
            System.out.println(real + "+"+ imag+"i");
        }
    }
}



    
public class complex {
    public static void main(String[] args) {
        Complex num1=new Complex(5,4 );
        Complex num2=new Complex(7, 6);

        Complex a=Complex.add(num1, num2);
        Complex b=Complex.diff(num1,num2);
        Complex c=Complex.product(num1,num2);

        System.out.print("Sum: ");
        a.printComplex();
        
        System.out.print("Difference: ");
        b.printComplex();
        
        System.out.print("Product: ");
        c.printComplex();
       
    }
    
}
