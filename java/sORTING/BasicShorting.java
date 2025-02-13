import java.util.*;




public class BasicShorting {
  //Bubble Sorting
    public static void bubbleSort(int arr[]){
      int swap=1;
      for(int turn=0;turn<arr.length-1;turn++){
        swap=0;
        for(int j=0;j<arr.length-1-turn;j++){
          if(arr[j] > arr[j+1]){
            //swap
            
            int temp=arr[j];
            arr[j]=arr[j+1];
            arr[j+1]=temp;
            swap=1;
          }
        }
           if(swap==0){
           break;
        }

    }
}
// Selection Sort
// public static void selectionSort(int arr[]){
//   for(int turn=0;turn<arr.length-1;turn++){ //calculate turns
//     int minPos=turn;
//     for(int j=turn+1;j<arr.length;j++){
//       if(arr[minPos] > arr[j]){
//         minPos=j; 
//       }
//     }
//     //swap
//     int temp=arr[minPos];
//     arr[minPos]=arr[turn];
//     arr[turn]=temp;
//   }
// }
// // Insertion Sort 
// public static void insertionSort(int arr[]){
//     for(int turn=1; turn<arr.length;turn++){
//       int curr=arr[turn],prev=turn-1;
//       //finding out corr position
//       while(prev>=0 && arr[prev] > curr){
//         arr[prev+1]=arr[prev];
//         prev--;
//       }

//       //insertion
//       arr[prev+1]= curr;
//       }
//     }

// public static void printArr(Integer arr[]){
//   for(int turn=0;turn<arr.length;turn++){
//     System.out.print(arr[turn] +" ");
//   }
// }
public static void printArr(int arr[]){
  for(int turn=0;turn<arr.length;turn++){
    System.out.print(arr[turn] +" ");
  }
}
    public static void main(String args[]){
    int arr[]={5,4,1,3,2};
    bubbleSort(arr);
    printArr(arr);
   // Integer arr1[]={5,4,1,3,2};
    //insertionSort(arr);
   // Arrays.sort(arr1,Collections.reverseOrder());
   // printArr(arr1);
    } 
  } 
  
