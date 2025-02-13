#include<stdio.h>
int main()
{
    int a[3][3];
    int b[3][3];
    int result[3][3];
    int i,j,k,sum;
    printf("Enter Matrix 1\n");
    for(i=0;i<3;i++){
        for(j=0;j<3;j++){
            printf("Enter Array %d %d :",i,j);
            scanf("%d",&a[i][j]);
        }
    }
    printf("Enter Matrix 2\n");
    for(i=0;i<3;i++){
        for(j=0;j<3;j++){
            printf("Enter Array %d %d :",i,j);
            scanf("%d",&b[i][j]);
        }
    }

    for(i=0;i<3;i++){
        for(j=0;j<3;j++){
                sum=0;
                for(k=0;k<3;k++){
                    sum=sum+(a[i][k]*b[k][j]);
                }
        result[i][j]=sum;
        }
    }
    printf("Sum:\n");
    for(i=0;i<3;i++){
        for(j=0;j<3;j++){
    printf("%d ",result[i][j]);
        }
        printf("\n");
    }

}

