
public class QuickSort{
    public static void quickSort(int arr[],int start,int end){
        if(start>=end){
            return;
        }

        //last Element
        int pivotIndex = partition(arr,start,end);
        quickSort(arr, start, pivotIndex-1); //left
        quickSort(arr, pivotIndex+1, end); //right
        

    }
    public static int partition(int arr[],int start,int end){
        int pivot=arr[end];
        int i=start-1; //to make place for elemnt smaller than pivot
        for(int j=start;j<end;j++){
            if(arr[j]<=pivot){
                i++;
                //swap
                int temp=arr[j];
                arr[j]=arr[i];
                arr[i]=temp;
            }
        }
        i++;
         //swap
         int temp=pivot;
         arr[end]=arr[i]; 
         arr[i]=temp;

         return i;

      

    }

    public static void printArr(int arr[]){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }
    public static void main(String[] args) {
        int arr[]={6,3,9,5,2,8};
        quickSort(arr, 0, arr.length-1);
        printArr(arr);
    }
}