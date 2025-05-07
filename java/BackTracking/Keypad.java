
//TC O(4^n)

public class Keypad {
  // Mapping of digits to corresponding letters on a mobile keypad.
  // Index 0 and 1 are left empty as they don't have corresponding letters.
  final static char[][] L = {
      {},                    // 0
      {},                    // 1
      {'a','b','c'},         // 2
      {'d','e','f'},         // 3
      {'g','h','i'},         // 4
      {'j','k','l'},         // 5
      {'m','n','o'},         // 6
      {'p','q','r','s'},     // 7
      {'t','u','v'},         // 8
      {'w','x','y','z'}      // 9
  };

  // Main function to start generating letter combinations
  public static void letterCombinations(String DialPad) {
      int len = DialPad.length();
      
      // If the input is empty, just print an empty line
      if (len == 0) {
          System.out.println("");
          return;
      }

      // Start the recursive function to generate combinations
      recursion(0, len, new StringBuilder(), DialPad);
  }

  // Recursive function to generate all possible combinations
  // i        : current index in the input string
  // len      : total length of input string
  // sb       : current combination built so far
  // DialPad  : input string consisting of digits
  public static void recursion(int i, int len, StringBuilder sb, String DialPad) {
      // If we have processed all digits, print the current combination
      if (i == len) {
          System.out.println(sb.toString());
      } else {
          // Get the digit at index i and find its corresponding letters
          char[] letters = L[Character.getNumericValue(DialPad.charAt(i))];

          // For each letter, add it to the current combination and recurse
          for (int j = 0; j < letters.length; j++) {
              // Create a new StringBuilder to avoid affecting other recursive paths
              recursion(i + 1, len, new StringBuilder(sb).append(letters[j]), DialPad);
          }
      }
  }

  // Main method to test the letterCombinations function
  public static void main(String args[]) {
      letterCombinations("24"); // Example input; will print all combinations for digits 2 and 4
  }
}
