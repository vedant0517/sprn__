public class RecursionAdvanced {
    public static int tilingProblem(int n){ //2 x n(floor size)
        if(n==0 || n==1){
            return 1;
        } 
        //kaam 
        //vertical choice
        int fnm1=tilingProblem(n-1);

        //horizontal choice
        int fnm2=tilingProblem(n-2);

        int totalWays=fnm1+fnm2;
        return totalWays;

    
    }
        public static int friendPairing(int n){
            if(n==1 || n==2){
                return n;
            }
            else{
                //choices 
                //single
                int fnm1=friendPairing(n-1);

                //pair
                int fnm2=friendPairing(n-2);
                int pairWays=(n-1)*fnm2;

                int totalWays=fnm1+pairWays;
                return totalWays;

            }



        }
    public static void removeDuplicates(String str,int index,StringBuilder newStr,boolean map[]) {
        if(index==str.length()){
            System.out.println(newStr);
            return; 
        }

        char currChar=str.charAt(index);
        if(map[currChar-'a'] == true){
            //duplicate
            removeDuplicates(str, index+1, newStr, map);
        }else{
            map[currChar-'a'] = true;      
                removeDuplicates(str, index+1, newStr.append(currChar), map);
        }
    }



    public static void printBinary(int n,int lastPlace,String str){
        if(n==0){
            System.out.println(str);
            return;

        }
        //kaam
        printBinary(n-1, 0, str+"0");
        if(lastPlace==0){
            printBinary(n-1,1, str+"1");

        }




    }

    public static void main(String[] args) {
     //   System.out.println("Total number of Ways : "+tilingProblem(4));
     //String str="appnnacollege";
     //removeDuplicates(str, 0, new StringBuilder(),new boolean[26]);
    // System.out.println("Total number of Ways: "+friendPairing(10));
    printBinary(3, 0,"");

    
        
    }
    
}
