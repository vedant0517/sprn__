//Time and aspace Complexuity Big(n)

public class sorting {
    public static boolean isSorted(int arr[],int i){
        if(i==arr.length-1){
            return true;

        }else
        if(arr[i]>arr[i+1]){
            return false;
        } else{
            return isSorted(arr, i+1);}
    }
    public static void main(String[] args) {
        int arr[]={1,2,3,4,5};
        System.out.println(isSorted(arr, 0));
    }
}
