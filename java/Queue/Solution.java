import java.util.LinkedList;
import java.util.Queue;

/**
 * Solution
 *
 * Generates and prints binary representations for numbers 1..N.
 * Algorithm: BFS-style generation using a queue of strings.
 * Start with "1" and repeatedly:
 *  - take the front string s,
 *  - print s (this corresponds to next binary number),
 *  - enqueue s+"0" and s+"1" which become later binary numbers.
 *
 * Example (queue contents shown after each enqueue):
 *   start: ["1"]
 *   step1: print "1", enqueue "10","11" -> ["10","11"]
 *   step2: print "10", enqueue "100","101" -> ["11","100","101"]
 *   ...
 *
 * Complexity: O(N) time (each step does constant work + two enqueues)
 *             O(N) space for the queue and stored strings.
 */
public class Solution {

    /**
     * Print binary numbers from 1 to n.
     *
     * This method intentionally prints extra debug information (step and
     * queue contents) to make the algorithm behavior clear. For clean
     * output (space-separated binaries), use the simplified version.
     */
    static void generatePrintBinary(int n) {
        // Defensive: nothing to do for non-positive n
        if (n <= 0) return;

        // Queue holds binary strings in the order they should be printed.
        Queue<String> q = new LinkedList<>();

        // Seed the process with the first binary number "1".
        q.add("1");

        int step = 1; // iteration counter used only for debugging output
        while (n-- > 0) {
            // Peek the front string (next binary number to print).
            // We use peek() followed by remove() to make the sequence
            // of actions explicit for learners; q.remove() alone would
            // also return and remove the element.
            String s1 = q.peek(); // get front
            q.remove();           // remove front

            // Print current binary number along with the step number.
            System.out.println("Step " + step + ": Print " + s1);

            // Generate the next two binary numbers by appending 0 and 1.
            // Appending keeps the BFS order: all numbers with current
            // prefix are generated before deeper prefixes.
            q.add(s1 + "0");
            q.add(s1 + "1");

            // Print queue contents for clarity — shows pending binaries.
            System.out.println("   Queue after step " + step + ": " + q);

            step++;
        }
    }

    public static void main(String[] args) {
        // Demo: prints first 10 binary numbers with debug traces.
        int n = 10;
        generatePrintBinary(n);
    }
}
