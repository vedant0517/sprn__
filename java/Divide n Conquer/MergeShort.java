
public class MergeShort{
    public static void mergeSort(int arr[],int start,int end){
        if(start>=end){
            return;
        }
        int mid=start+(end-start)/2;  //start+end/2
        mergeSort(arr, start, mid);
        mergeSort(arr, mid+1, end);
        mergeAl(arr, start, mid, end);

    }
    public static void mergeAl(int arr[],int start,int mid,int end){
        int temp[]=new int[end-start+1];
        int i=start; //iterator for left part
        int j=mid+1; //iterator for right part
        int k=0; //iterator for temp arr
        while(i<=mid && j<=end){
            if(arr[i]<arr[j]){
                temp[k]=arr[i++];
            }else{
                temp[k]=arr[j++];
            }
            k++;
        }

        //for leftover elements of 1st sorted part
        while(i<=mid){
            temp[k++]=arr[i++];
        }

        //for leftover elements of 2nd sorted part
        while(j<=end){
            temp[k++]=arr[j++];
        }

        //copy temp to orginal arr
        for(k=0,i=start;k<temp.length;k++,i++){
            arr[i]=temp[k];
        }

    }

    public static void printArr(int arr[]){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }
    public static void main(String[] args) {
        int arr[]={6,3,9,5,2,8};
        mergeSort(arr, 0,arr.length-1);
        printArr(arr);
    }





}