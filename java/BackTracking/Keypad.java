
//TC O(4^n)

public class Keypad { 
    final static char[][] L = {{},{},{'a','b','c'},{'d','e','f'},{'g','h','i'}, 
    {'j','k','l'},{'m','n','o'},{'p','q','r','s'}, 
    {'t','u','v'},{'w','x','y','z'}}; 
     
     
    public static void letterCombinations(String DialPad) { 
        int len = DialPad.length(); 
        if (len == 0) { 
        System.out.println(""); 
        return; 
      } 
        recursion(0, len, new StringBuilder(), DialPad); 
    } 
        
     
    public static void recursion(int i, int len, StringBuilder sb, String DialPad) { 
        if (i == len){ 
        System.out.println(sb.toString()); 
    } 
    else { 
        char[] letters = L[Character.getNumericValue(DialPad.charAt(i))]; 
        for (int j = 0; j < letters.length; j++) 
        recursion(i+1, len, new StringBuilder(sb).append(letters[j]), DialPad); 
      } 
    } 
    public static void main(String args[]){ 
        letterCombinations("2"); 
      } 
    }