public class SearchinArray {
    public static int search(int arr[],int tar,int start,int end) {
        if(start>end){
            return -1;
        }
        //kam
        int mid=start+(end-start)/2;

        //case found
        if(arr[mid]==tar){
            return mid;
        }
        //mid on line 1
        if(arr[start] <=arr[mid]){
            // case 1:left
            if(arr[start]<=tar && tar <=arr[mid]){
                return search(arr, tar, start, mid);
            } else{
                //case b: right
                return search(arr, tar, mid+1, end);
            }
        }
        // mid on L2
        else{
            //case c : right
            if(arr[mid]<=tar && tar<=arr[end]){
                return search(arr, tar, mid+1, end);
            }
            else{
                //case d : left
                return search(arr, tar, start,mid-1);
            }

        }

    }
    public static void main(String[] args) {
        int arr[]={4,5,6,7,0,1,2};
        int target=0;
        int targetIdx = search(arr, target, 0,arr.length-1);
        System.out.println(targetIdx);
    }
    
}
