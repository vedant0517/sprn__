public class towerofHoanoi {
    public static void hanoi(String src,String helper,String Dest,int n){
        if (n==0){
           // System.out.println("disk "+n+" move from "+src+" to "+Dest);
            return;
        }
        hanoi(src, Dest, helper,n-1);
        System.out.println("disk " + n + " from " + src + " to " + Dest);// desination disk jaha phchi vaha print krna hai
        
        hanoi(helper, src,Dest, n-1); //jo helper be bachi huw thi wo destination pe bhejne k liye
        
        //System.out.println("disk "+n+" from"+helper+"to "+Dest);

    }
    
    public static void main(String[] args) {
        int n=3;
        hanoi("A","B","C",n);
    }
}
