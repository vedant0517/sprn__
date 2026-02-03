

import java.util.*;;
public class heapImplementation {

    static class Heap{
        ArrayList<Integer> arr=new ArrayList<>();

        public void add(int data){
            arr.add(data);

            int x=arr.size()-1;  //child index
            int par=(x-1)/2;   //parent index

            while (arr.get(x) < arr.get(par)) {  //O(logn)  while (arr.get(x) > arr.get(par)) ---- max heap condition
                int temp=arr.get(x);
                arr.set(x,arr.get(par));
                arr.set(par,temp);

                x=par;
                par=(x-1)/2;
            }
        }

        public int peek(){
            return arr.get(0);
        }

        private void heapify(int i){
            int left=(2*i)+1;
            int right=(2*i)+2;
            int minIdx=i;   //maxIdx for max heap

            if(left < arr.size() && arr.get(minIdx) > arr.get(left)){   //f(left < arr.size() && arr.get(minIdx) < arr.get(left))  --- max heap condition
                minIdx=left;
            }

            if(right < arr.size() && arr.get(minIdx) > arr.get(right)){  //if(right < arr.size() && arr.get(minIdx) < arr.get(right)) --- max heap cond
                minIdx=right;
            }

            if(minIdx != i){
                //swap
                int temp=arr.get(i);
                arr.set(i,arr.get(minIdx));
                arr.set(minIdx,temp);

                heapify(minIdx);
            }

        }

        public int remove(){
            int data=arr.get(0);

            //step1 - swap first & last
            int temp=arr.get(0);
            arr.set(0,arr.get(arr.size()-1));
            arr.set(arr.size()-1,temp);

            //step2- delete last
            arr.remove(arr.size()-1);

            //step3- heapify
            heapify(0);
            return data;
        }

        public boolean isEmpty(){
            return arr.size()==0;
        }
    }
    public static void main(String[] args) {
        Heap h=new Heap();
        h.add(3);
        h.add(4);
        h.add(1);
        h.add(5);

        while(!h.isEmpty()){   //heapsort --O(nlogn)
            System.out.println(h.peek());
            h.remove();
        }
    }
}
