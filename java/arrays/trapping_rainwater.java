public class trapping_rainwater {
    public static int trapRainwater(int height[]){
        int n=height.length;
        int trappedWater=0;
        //calculate left max boundary-array
        int left_max_boundary[]=new int[n];
        left_max_boundary[0]=height[0];
        for(int i=1;i < n;i++){
            left_max_boundary[i]=Math.max(height[i],left_max_boundary[i-1]);
        }
        //calculate right max boundary-array
        int right_max_boundary[]=new int[n];
        right_max_boundary[n-1]=height[n-1];
        for(int i=n-2;i>=0 ;i--){
            right_max_boundary[i]=Math.max(height[i],right_max_boundary[i+1]);
        }

        //loop
       
        for(int i=0;i<n;i++){
             //waterlevel=min(leftmax,rightmax)
            int waterlevel=Math.min(left_max_boundary[i],right_max_boundary[i]);
             //trapped water=(waterlevel-ht[i])
            trappedWater+=waterlevel-height[i];
        }
       return trappedWater;

    }
    public static void main(String[] args) {
        int height[]={4,2,0,6,3,2,5};
       System.out.println("Rainwater Trapped : " +trapRainwater(height));
    }    
}                                                                                                                                                                                                                                                                                                
