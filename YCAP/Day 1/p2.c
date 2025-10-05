//173 Vedant Kapgate
#include<stdio.h>
int main(){
    int x,y;
    printf("Enter withdrawl amt and acc bal\n");
    scanf("%d %d",&x,&y);
    if(x%5 !=0){
        printf("Transation failed\n");
    }
    else if(x +0.5 > y)
        {
        printf("Insufficient balace\n");
    } else{
        printf("New Balace : %d",y-x);
    }

}
