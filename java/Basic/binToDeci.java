public class binToDeci {
    public static void binToDec(int binNum) {
        int myNum=binNum;
        int pow=0;
        int deciNum=0;

        while(binNum>0){
        int lastDigit;
        lastDigit=(binNum%10);
        deciNum=deciNum+(lastDigit * (int)Math.pow(2,pow));

        binNum=binNum/10;
        pow++;
    }
    System.out.println("decimal of "+myNum+" = "+deciNum);
}

    public static void decToBin(int n){
    int myNum=n; 
    int pow=0;
    int binNum=0;
    while(n>0)
    {
        int rem=n%2;
        binNum=binNum+(rem*(int) Math.pow(10,pow));
        pow++;
        n/=2;
    } System.out.println("Binary of "+myNum+" = "+binNum);
    }
    public static void main(String[] args) {
        binToDec(101101);
        decToBin(54);
        
    }
    


}