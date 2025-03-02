public class Occ {
    
    public static int fOcc(int arr[],int key,int i){ //first compare and then look forward strategy
        if(arr.length == i){
            return -1;
        }else
        if(arr[i]==key){
            return i;
        }else{
           return fOcc(arr, key, i+1);
        } 
    }


    // public static int lOcc(int arr[],int key,int i){ //look form backward strategy

    //     if(0 == i){
    //         return -1;
    //     }else
    //     if(arr[i]==key){
    //         return i;
    //     }else{
    //        return lOcc(arr, key, i-1);
    //     } 
    // }
    public static int lOcc(int arr[],int key,int i){ //look forward and then compare strategy

        if(arr.length == i){
            return -1;
        }
        int isFound=lOcc(arr, key, i+1);
        if(isFound==-1 && arr[i]==key){
            return i;
        }else{
           return isFound;
        } 
    }
    public static void main(String[] args) {
        int arr[]={1,5,4,5,6};
       // System.out.println(fOcc(arr, 10, 0));
        System.out.println(lOcc(arr, 5, 0));
    }
}
