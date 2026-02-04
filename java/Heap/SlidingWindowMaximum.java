import java.util.PriorityQueue;

public class SlidingWindowMaximum {

    static class Pair implements Comparable<Pair>{
        int val;
        int idx;

        public Pair(int val,int idx){
            this.val=val;
            this.idx=idx;
        }
        @Override
        public int compareTo(Pair p2){
            //ascending
            //return this.val-p2.val;

            //descending
            return p2.val-this.val;
        }


    }

    public static void main(String[] args) {
        int arr[]={1,3,-1,-3,5,3,6,7};
        int k=3;
        int res[]=new int[arr.length-k+1];

        PriorityQueue<Pair> pq=new PriorityQueue<>();
        
        // Phase 1: Build initial window [1, 3, -1] (indices 0-2)
        // i=0: Add (1,0) -> PQ: [(1,0)]
        // i=1: Add (3,1) -> PQ: [(3,1), (1,0)]
        // i=2: Add (-1,2) -> PQ: [(3,1), (1,0), (-1,2)]
        for(int i=0;i<k;i++){
            pq.add(new Pair(arr[i], i));
        }

        res[0]=pq.peek().val;  // res[0] = 3 (max of first window)
        
        // Phase 2: Slide the window from index 3 to 7
        for(int i=k;i<arr.length;i++){
            // i=3: Window [3,-1,-3] (idx 1-3)
            //      Remove elements with idx <= 0, Add (-3,3), res[1]=3
            // i=4: Window [-1,-3,5] (idx 2-4)
            //      Remove elements with idx <= 1, Add (5,4), res[2]=5
            // i=5: Window [-3,5,3] (idx 3-5)
            //      Remove elements with idx <= 2, Add (3,5), res[3]=5
            // i=6: Window [5,3,6] (idx 4-6)
            //      Remove elements with idx <= 3, Add (6,6), res[4]=6
            // i=7: Window [3,6,7] (idx 5-7)
            //      Remove elements with idx <= 4, Add (7,7), res[5]=7
            
            while(pq.size() > 0 && pq.peek().idx <=(i-k)){
                pq.remove();  // Remove elements outside current window
            } 
            pq.add(new Pair(arr[i], i));  // Add new element to window
            res[i-k+1]=pq.peek().val;  // Store max of current window
        }

        // Print result: [3, 3, 5, 5, 6, 7]
        for(int i=0;i<res.length;i++){
            System.out.print(res[i]+" ");
        }
        System.out.println();
    }
    
}
