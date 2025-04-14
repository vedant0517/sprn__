// public class sort {
//     public static String[] mergeSort(String arr[],int left,int right){
//         if(left ==right){
//             String A[]={arr[left]};
//             return A ;
//         }

//         int mid=left+(right-left)/2;
//        String arr1[]= mergeSort(arr, left, mid);
//        String arr2[]= mergeSort(arr, mid+1, right);
        
//        String arr3[]=merge(arr1, arr2);
//        return arr3;
//     } 
//     static String[] merge(String[] arr1,String[] arr2){
//         int n=arr1.length;
//         int m=arr2.length;

//         String arr3[]=new String[m+n];

//         int idx=0,i=0,j=0;

//         while(i<n && j<m){
//             if(alphabet(arr1[i],arr2[j])){
//                 arr3[idx]=arr1[i];
//                 i++;
//                 idx++;
//             }else{
//                 arr3[idx]=arr2[j];
//                 j++;
//                 idx++;
//             }
//         }
//             while (i < n) { 
//                 arr3[idx] = arr1[i]; 
//                 i++; 
//                 idx++; 
//                 } 
//                 while (j < m) { 
//                 arr3[idx] = arr2[j]; 
//                 j++; 
//                 idx++; 
//                 } 
//                 return arr3;
//               }
//     static boolean alphabet(String str1,String str2){
//         if(str1.compareTo(str2) < 0){
//             return true;
//         }else{
//             return false;
//         }
//     }

//     public static void main(String[] args) {
//         String [] arr1={"sun","earth","mars","mercury"};
//         String[] a=mergeSort(arr1, 0, arr1.length-1);

//         for(int i=0;i<a.length;i++){
//             System.out.print(a[i]+" ");
//         }

//     }
// }



public class sort {
    private static int CountInRange(int nums[],int num,int low,int high){
        int count=0;
        for(int i=low;i<high;i++){
            if(nums[i]==num){
                count++;
            }

        }
        return count;
    }
    private static int MajorityElementRec(int nums[],int low,int high){
        if(low==high){
            return nums[low];
        }
        int mid=low+(high-low)/2;
        int left=MajorityElementRec(nums, low,mid);
        int right=MajorityElementRec(nums, mid+1, high);

        if(left == right){
            return left;
        }

        int leftCount=CountInRange(nums,left,low, high);
        int rightCount=CountInRange(nums,right,low, high);

        return leftCount>rightCount ? left:right;

    }
    public static int MajorityElements(int nums[]){
        return MajorityElementRec(nums,0,nums.length-1);
    }

    public static void main(String[] args) {
        int nums[]={2,2,2,4,5,6,4,6,7,5,5,5};
        System.out.println(MajorityElementRec(nums, 0,nums.length-1));
        

    }
}

