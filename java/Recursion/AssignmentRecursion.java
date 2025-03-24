public class AssignmentRecursion { 
    public static void allOccurences(int arr[], int key, int start) { 
    if(start == arr.length) { 
    return; 
    } 
     
    if(arr[start] == key) { 
    System.out.print(start+" "); 
    } 
    allOccurences(arr, key, start+1); 
    } 



    //If digits[] were declared inside printDigits, it would be re-created on every recursive call, which is inefficient.
    static String digits[]={"zero","one","two","three","four","five","six","seven","eight","nine"};

    public static void printDigits(int number){
        //inefficient declaration
     String digits[]={"zero","one","two","three","four","five","six","seven","eight","nine"};
       
        if(number == 0){
            return;
        }
        int lastDigit=number%10;
        printDigits(number/10);
        System.out.print(digits[lastDigit]+" ");

    }

    public static int printLength(String str){
        if(str.length() == 0){
            return 0;
        }
        
        else{
            return printLength(str.substring(1))+1;
        }
    }

    public static int countSubstrs(String str, int start, int end, int n) { 
        if (n == 1) { 
        return 1; 
        } 
        if (n <= 0) { 
        return 0; 
        } 
         
         
        int res = countSubstrs(str, start + 1, end, n - 1) + 
        countSubstrs(str, start, end- 1, n - 1) - 
        countSubstrs(str, start + 1, end - 1, n - 2); 
         
        if (str.charAt(start) == str.charAt(end)) { 
        res++; 
        } 
        return res;
   
        } 

    public static void main(String[] args) { 
    // int arr[] = {3, 2, 4, 5, 6, 2, 7, 2, 2}; 
    // int key = 2; 
    // allOccurences(arr, key, 0); 

    // System.out.println(); 

    // printDigits(12340);

   // System.out.println(printLength("abcd"));


        String str = "abcab"; 
        int n = str.length(); 
        System.out.print(countSubstrs(str, 0, n-1, n));
    
    } 
    } 