//173 Vedant kapgate
#include <stdio.h>

int main()
{
    int arr[]={2,0,3,0,1,5};
    int ans[6]; 
    int indx=0;
    for(int i=0;i<6;i++){  
        if ( arr[i]!=0){
            ans[indx]=arr[i];
            indx++;
        }
    }
    while(indx<6){ 
        ans[indx]=0;
        indx++;
    }
    for( int i=0;i<6;i++){
        printf("%d ",ans[i]);  
    }

    return 0;
}