// #include<stdio.h>
// int main()
// {
//     int i,num=2,count=1,n;
//     printf("Enter number to get first nth prime numbers\n");
//     scanf("%d",&n);

//     while(count<=n)
//     { int isPrime=1;
//         for(i=2;i<num;i++)
//         {
//             if(num%i==0)
//         {
//             isPrime=0;
//             break;
//         }

//         }
//         if(isPrime==1)
//         {
//             printf("%d ",num);
//             count++;
//         }
//         num++;
//     }
// return 0;
// }
#include<stdio.h>
int main()
{
    int i,num=2,count=1,n;
    printf("Enter number to get first nth prime numbers\n");
    scanf("%d",&n);

    while(count<=n)
    { int isPrime=1;
        for(i=2;i<num;i++)
        {
            if(num%i==0)
        {
            isPrime=0;
            break;
        }

        }
        if(isPrime==1)
        {
            printf("%d ",num);
            count++;
        }
        num++;
    }
return 0;
}
