import java.util.Stack;

public class DecodeString {
     public static String decode(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<StringBuilder> st = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int num = 0;

        for (char ch : s.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                int digit = ch - '0';
                num = num * 10 + digit;
            } 
            else if (ch == '[') {
                st.push(sb);
                sb = new StringBuilder();
                numStack.push(num);
                num = 0;
            } 
            else if (ch == ']') {
                StringBuilder temp = sb;
                sb = st.pop();
                int count = numStack.pop();

                while (count > 0) {
                    sb.append(temp);
                    count--;
                }
            } 
            else {
                sb.append(ch);
            }
        }

        return sb.toString();
    }

    // Main method to test
    public static void main(String[] args) {
        

        String s1 = "3[a]2[bc]";
        String s2 = "3[a2[c]]";
        String s3 = "2[abc]3[cd]ef";
        String s4 = "10[a]";

        System.out.println("Input: " + s1 + " -> Output: " + decode(s1));
        System.out.println("Input: " + s2 + " -> Output: " + decode(s2));
        System.out.println("Input: " + s3 + " -> Output: " + decode(s3));
        System.out.println("Input: " + s4 + " -> Output: " + decode(s4));
    }
}
