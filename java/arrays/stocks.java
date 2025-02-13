public class stocks {
    public static int BuyandSell(int price[]){
        int buyPrice=Integer.MAX_VALUE;
        int maxProfit=0;
        for(int i=0;i<price.length;i++){
            if(price[i]>buyPrice){ //profit
                int profit=price[i]-buyPrice; //todays profit
                maxProfit=Math.max(maxProfit,profit);}
                else{
                    buyPrice=price[i];
                }
            }
       return maxProfit;

    }
    public static void main(String[] args) {
        int price[]={7,1,5,3,6,4};
      System.out.println(BuyandSell(price));
    }    
}                                                                                                                                                                                                                                                                                                
