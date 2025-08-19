import java.util.Stack;

public class TrappingRainWater {
    public static void WaterStored(int height[]) {
        int n = height.length;
        int water = 0;
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            // While current bar is higher than the bar at stack top
            while (!st.isEmpty() && height[i] > height[st.peek()]) {
                int bottom = st.pop(); // the "valley" or bottom

                if (st.isEmpty()) break; // no left boundary

                int left = st.peek();   // left boundary index
                int distance = i - left - 1; // width between boundaries
                int bounded_height = Math.min(height[left], height[i]) - height[bottom];

                water += distance * bounded_height;
            }
            st.push(i);
        }

        System.out.println("Water Stored: " + water);
    }

    public static void main(String[] args) {
        int height[] = {4, 2 };
        WaterStored(height); // Output: 9
    }
}
