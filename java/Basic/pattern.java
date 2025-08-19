// //import java.util.*;

// public class pattern
//  {
//     public static void main(String args[]){
// // nested loop for() solid rectangle 
// int n=4; int m=5;
// //outerloop for rows  
// for(int i=1;i<=n;i++){
//     //inner loop for columns
//     for(int j=1;j<=m;j++)
//     {
//       System.out.print("*");
//     }System.out.println();
// }
//   }
// }
//import java.util.*;

// public class pattern
//  {
//     public static void main(String args[]){
//       int n=5;
//       for(int row=1;row<=n;row++){
//         for(int col=1;col<=n;col++)
//         {
//           System.out.print("* ");
//         }
//       System.out.println();
//      }
//   }
// }
//import java.util.*;

// public class pattern
//  {
//     public static void main(String args[]){
//       int n=5;
//       for(int row=1;row<=n;row++){
//         for(int col=1;col<=row;col++){
//           System.out.print("* ");
//         }System.out.println();

//       }

//   }
// }
//public class pattern
//  {
//     public static void main(String args[]){
//       int n=5;
//       for(int row=n;row>=1;row--){      //for(int row=1;row<=n;row++)
//         for(int col=1;col<=row;col++){  // for(int col=1;col<=row;col++)
//                                         //if(row+col<=n+1)
//           System.out.print("* ");
//         }System.out.println();

//       }

//   }
// }
// public class pattern
//  {
//     public static void main(String args[]){
//       int n=5;
//       for(int i=1;i<=n;i++){
//         for(int j=1;j<=n;j++){
//           if(i+j >= n+1){
//           System.out.print("*");}
//           else {
//           System.out.print(" ");}
//         }System.out.println();
//       }
//   }
// }

// public class pattern
//  {
//     public static void main(String args[]){
//       int n=5;
//       for(int i=1;i<=n;i++){
//         for(int j=1;j<=n;j++){
//           if(j-i >= 0){
//           System.out.print("*");}
//           else {
//           System.out.print(" ");}
//         }System.out.println();
//       }
//     }
//   }

// public class pattern
//  {
//     public static void main(String args[]){
//       int n=5;
//       for(int i=1;i<=n;i++){
//         for(int j=1;j<=n;j++){
//           if((j-i)==0 || (i+j)==n+1){
//           System.out.print("*");}
//           else {
//           System.out.print(" ");}
//         }System.out.println();
//       }
//     }
//   }

// public class pattern
//  {
//     public static void main(String args[]){
//       int n=5;
//       for(int row=1;row<=n;row++)
//       {
//         for(int col=1;col<=n;col++)
//         {if(row>=col)
//           {System.out.print(col);}
//           else {System.out.print(" ");}
//         }System.out.println();
//       }
//     }
//   }

//   public class pattern
//  {
//     public static void main(String args[]){
//       int  c=5;int num=1;int r=4;
//       for(int row=1;row<=r;row++)
//       {
//         for(int col=1;col<=c;col++)
//         {if(row==1 || row==4 || col==1 || col==5)
//           {System.out.print("*");
//             }
//           else {System.out.print(" ");}
       
//         }System.out.println();
//       }
//     }
//   }
       
        
// public class pattern
// {
//    public static void main(String args[]){
//      int n=5;
//      for(int i=1;i<=n;i++)
//      {
//        for(int j=1;j<=n-i+1;j++){
        
//          System.out.print(j);
//        }
//        System.out.println();
//      }
//  }
// }

// public class pattern
//  {
//     public static void main(String args[]){
//       int n=4;
//       //outer loop 1st half
//       for(int row=1;row<=n;row++){
//         //stars-i
//         for(int col=1;col<=row;col++){
//           System.out.print("*");
//         }
//         //spaces- 2*(n-row)
//         for(int col=1;col<=2*(n-row);col++){
//           System.out.print(" ");
//         }
//         //stars-i
//         for(int col=1;col<=row;col++){
//           System.out.print("*");
//         }
//         System.out.println();
//       }
//       //2nd half
//         for(int row=n;row>=1;row--){
//           for(int col=1;col<=row;col++){
//             System.out.print("*");
//           }
//           for(int col=1;col<=2*(n-row);col++){
//             System.out.print(" ");
//           }
//           for(int col=1;col<=row;col++){
//             System.out.print("*");
//           }System.out.println();
//       }
      
//   }
// }


//  public class pattern
// {
//    public static void main(String args[]){
//      int n=5;
//      for(int i=1;i<=n;i++){
//       for(int j=1;j<=n-i;j++){
//         System.out.print(" ");
//       }
//       for(int j=1;j<=n;j++){
//         System.out.print("*");
//       }System.out.println();
//      }
    
//  }
// }

//  public class pattern
// {
//    public static void main(String args[]){
//      int n=5;
//      for(int i=1;i<=n;i++){
//       for(int j=1;j<=n-i;j++){
//         System.out.print(" ");
//       }
//       for(int j=1;j<=n;j++){
//         if(i==1 || i==n || j==1 || j==n){
//         System.out.print("*");}
//         else{
//           System.out.print(" ");
//         }
//       }System.out.println();
//      }
    
//  }
// }




public class pattern
{
    public static void diamond(int n){
      int total_row=2*n-1;
      int spaces=n-1;
      int star=1;
      int row=1;
      while(row<=total_row)
      {
        //print spaces
        for(int i=1;i<=spaces;i++){
          System.out.print(" ");
        }
        //print stars
        for(int i=1;i<=star;i++){
          System.out.print("*");}

        //print to next line
        System.out.println();
        if(row<n){
          spaces--;
          star=star+2;

        }
        else{
          spaces++;
          star=star-2;
        }
        row++;
      }

    }

    public static void palindrome(int n){
      int total_row=2*n-1;
      int spaces=n-1;
      int star=1;
      int row=1;
      while(row<=total_row)
      {
        //print spaces
        for(int i=1;i<=spaces;i++){
          System.out.print("  ");
        }
        //print stars
        int num=1;
        for(int i=1;i<=star;i++){
          System.out.print(num + " ");
          if(i<=star/2){
          num++;}
          else{
            num--;
          }
          
        }

        //print to next line
        System.out.println();
        if(row<n){
          spaces--;
          star=star+2;

        }
        else{
          spaces++;
          star=star-2;
        }
        row++;
      }

    }
 
    public static void main(String args[]){
  //     int n=4;
  //     //outer loop 1st half
  //     for(int row=1;row<=n;row++){
  //       //spaces n-row
  //       for(int col=1;col<=n-row;col++){
  //         System.out.print(" ");
  //       }
  //       //star row
  //       for(int col=1;col<=2*row-1;col++){
  //         System.out.print("*");
  //       }
  //       System.out.println();
  //     }
  //     //2nd half
  //     for(int row=n;row>=1;row--){
  //       //spaces n-row
  //       for(int col=1;col<=n-row;col++){
  //         System.out.print(" ");
  //       }
  //       //star row
  //       for(int col=1;col<=2*row-1;col++){
  //         System.out.print("*");
  //       }
  //       System.out.println();
  // }
  palindrome(5);
}
 }


