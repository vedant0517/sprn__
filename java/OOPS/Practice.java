public class Practice{
    public static void main(String[] args) {
        // Pen p1=new Pen (); //created object of class Pen
        // p1.colorChange("Red");
        // p1.setTip(10);
        // System.out.println(p1.color );
        // System.out.println(p1.tip);
        // System.out.println(p1.getColor());

        // BankAccount myAcc=new BankAccount();
        // myAcc.userName= "Vedant";
        // myAcc.setPass("Ved");

        //Inheritance
        // Fish shark=new Fish();
        // shark.eat();
        

        
    }
    

    }
    class Pen{
        String color="Blue";
        int tip=5;

        void colorChange(String newColor){
            color=newColor;
        }
        void setTip(int newTip){
            tip=newTip;
        }

        String getColor(){
            return this.color;

        }
        int getTip(){
            return this.tip;
        }
    }

        class BankAccount{
            public String userName;
            private String password;
            public void setPass(String pwd){
                password=pwd;
            }
        }


        //Inheritace
        //Base class
        class Animals{
            String color;
            void eat(){
                System.out.println("eats");
            }
            void breathe(){
                System.out.println("breathes");
            }
        };
        //derived class 
        class Fish extends Animal{
            int fins;

        }




        //abstraction
        abstract class Animal{}
