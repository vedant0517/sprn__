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



// public class sort {
//     private static int CountInRange(int nums[],int num,int low,int high){
//         int count=0;
//         for(int i=low;i<high;i++){
//             if(nums[i]==num){
//                 count++;
//             }

//         }
//         return count;
//     }
//     private static int MajorityElementRec(int nums[],int low,int high){
//         if(low==high){
//             return nums[low];
//         }
//         int mid=low+(high-low)/2;
//         int left=MajorityElementRec(nums, low,mid);
//         int right=MajorityElementRec(nums, mid+1, high);

//         if(left == right){
//             return left;
//         }

//         int leftCount=CountInRange(nums,left,low, high);
//         int rightCount=CountInRange(nums,right,low, high);

//         return leftCount>rightCount ? left:right;

//     }
//     public static int MajorityElements(int nums[]){
//         return MajorityElementRec(nums,0,nums.length-1);
//     }

//     public static void main(String[] args) {
//         int nums[]={2,2,2,4,5,6,4,6,7,5,5,5};
//         System.out.println(MajorityElementRec(nums, 0,nums.length-1));
        

//     }
// }


// public class sort {

//     public static int mergeSort(int nums[],int left,int right){
//         int invcount=0;
//         if(right>left){
//             int mid=left+(right-left)/2;

//             invcount=mergeSort(nums,left,mid);
//             invcount+=mergeSort(nums,mid+1,right);
//             invcount+=merge(nums,left, mid+1, right);

//         }
//         return invcount;
//     }
//     public static int merge(int nums[],int left,int mid,int right){
//         int i=left,j=mid,k=0;
//         int invcount=0;
//         int temp[]=new int[right-left+1];

//         while((i<mid) && (j<=right)){
//             if(nums[i]<=nums[j]){
//                 temp[k]=nums[i];
//                 i++;
//             }
//             else{
//                 temp[k]=nums[j];
//                 j++;
//                 invcount+=(mid-i);
//             }
//             k++;
//         }
//             while(i<mid){
//                 temp[k]=nums[i];
//                 i++;
//             }
//             while (j <= right) { 
//                 temp[k++] = nums[j++]; 
//                 } 
//                 for (i = left, k = 0; i <= right; i++, k++) { 
//                     nums[i] = temp[k]; 
//                     }
//                 return invcount;

//         }
//         public static void main(String[] args) {
//         int nums[]={1,20,6,4,5};
//         System.out.println();
//         System.out.println(mergeSort(nums, 0, nums.length-1));
    
//     }
// }


import java.util.Arrays;

public class sort {

    public static int mergeSort(int nums[], int left, int right) {
        int invcount = 0;

        if (right > left) {
            int mid = left + (right - left) / 2;

            System.out.println("Calling mergeSort for left half: " + left + " to " + mid);
            invcount = mergeSort(nums, left, mid);

            System.out.println("Calling mergeSort for right half: " + (mid + 1) + " to " + right);
            invcount += mergeSort(nums, mid + 1, right);

            System.out.println("Merging from " + left + " to " + right + " (mid=" + mid + ")");
            invcount += merge(nums, left, mid + 1, right);

            System.out.println("Array after merge from " + left + " to " + right + ": " +
                    Arrays.toString(Arrays.copyOfRange(nums, left, right + 1)));
            System.out.println("Inversions so far: " + invcount);
            System.out.println("----------------------------------------------------");
        }

        return invcount;
    }

    public static int merge(int nums[], int left, int mid, int right) {
        int i = left, j = mid, k = 0;
        int invcount = 0;
        int temp[] = new int[right - left + 1];

        System.out.println("Merging arrays: " +
                Arrays.toString(Arrays.copyOfRange(nums, left, mid)) + " and " +
                Arrays.toString(Arrays.copyOfRange(nums, mid, right + 1)));

        while ((i < mid) && (j <= right)) {
            if (nums[i] <= nums[j]) {
                temp[k] = nums[i];
                i++;
            } else {
                temp[k] = nums[j];
                j++;
                invcount += (mid - i);
                System.out.println("Inversion found: " + (mid - i) + " (because " + nums[i] + " > " + temp[k] + ")");
            }
            k++;
        }

        while (i < mid) {
            temp[k++] = nums[i++];
        }

        while (j <= right) {
            temp[k++] = nums[j++];
        }

        // Copy back to original array
        for (i = left, k = 0; i <= right; i++, k++) {
            nums[i] = temp[k];
        }

        return invcount;
    }

    public static void main(String[] args) {
        int nums[] = {1, 20, 6, 4, 5};
        System.out.println("Original array: " + Arrays.toString(nums));
        int totalInversions = mergeSort(nums, 0, nums.length - 1);
        System.out.println("\nSorted array: " + Arrays.toString(nums));
        System.out.println("Total Inversion Count: " + totalInversions);
    }
}



