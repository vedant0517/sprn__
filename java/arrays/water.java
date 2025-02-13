public class water {
    // public static int maxArea(int height[]){
    //     int n=height.length;
    //     int max_area=0;
    //     for(int i=0;i<n;i++){
    //         for(int j=i+1;j<n;j++){
    //             int breadth=Math.abs(j-i);
    //             int length=Math.min(height[i],height[j]);
    //             int area=breadth * length;
    //             max_area=Math.max(area,max_area);
    //         }
    //     }
    //         return max_area;
    //     }

    public static int maxAreaApproch2(int height[]){
        int left=0,right=height.length-1,max_area=0;
            while(left<=right){
                int area=0;
                int breadth=Math.abs(left-right);
                int length=Math.min(height[left],height[right]);
                area=(breadth * length);
                max_area=Math.max(area,max_area);
                if(height[left] <= height[right]){
                    left++; 
                }else{
                    right--;
                }
            }
        
            return max_area;
        }
    public static void main(String[] args) {
        int height[]={1,8,6,2,5,4,8,3,7};
       System.out.println("Most water in container area : " +maxAreaApproch2(height));
    }    
}                                                                                                                                                                                                                                                                                                
