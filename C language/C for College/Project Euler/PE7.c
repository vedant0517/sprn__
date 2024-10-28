#include<stdio.h>
#include<math.h>
int main()
{
    int i,num=2,count=1,sum=0;


    while(count<2000000)
    { int isPrime=1;
        for(i=2;i<sqrt(num);i++)
        {
            if(num%i==0)
        {
            isPrime=0;
            break;
        }

        }
        if(isPrime==1)
        {
            sum=sum+num;
printf("%d ",num);
            count++;
        }
        num++;
        if(num==2000000){break;}
    }
    printf("Sum is %d ",sum);
return 0;
}
