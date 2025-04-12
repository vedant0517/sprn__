public class sort {
    public static String[] mergeSort(String arr[],int left,int right){
        if(left ==right){
            String A[]={arr[left]};
            return A ;
        }

        int mid=left+(right-left)/2;
       String arr1[]= mergeSort(arr, left, mid);
       String arr2[]= mergeSort(arr, mid+1, right);
        
       String arr3[]=merge(arr1, arr2);
       return arr3;
    } 
    static String[] merge(String[] arr1,String[] arr2){
        int n=arr1.length;
        int m=arr2.length;

        String arr3[]=new String[m+n];

        int idx=0,i=0,j=0;

        while(i<n && j<m){
            if(alphabet(arr1[i],arr2[j])){
                arr3[idx]=arr1[i];
                i++;
                idx++;
            }else{
                arr3[idx]=arr2[j];
                j++;
                idx++;
            }
        }
            while (i < n) { 
                arr3[idx] = arr1[i]; 
                i++; 
                idx++; 
                } 
                while (j < m) { 
                arr3[idx] = arr2[j]; 
                j++; 
                idx++; 
                } 
                return arr3;
              }
    static boolean alphabet(String str1,String str2){
        if(str1.compareTo(str2) < 0){
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args) {
        String [] arr1={"sun","earth","mars","mercury"};
        String[] a=mergeSort(arr1, 0, arr1.length-1);

        for(int i=0;i<a.length;i++){
            System.out.print(a[i]+" ");
        }

    }
}
