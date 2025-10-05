//173 Vedant Kapgate
#include<stdio.h>
int main()
{   int n;
    printf("Enter no.");
    scanf("%d",&n);
    if(n%3 == 0 && n%5==0 )
    {
        printf("FizzBuzz\n");
    }
    if(n%3 == 0){
        printf("Fizz\n");
    }
    if(n%5==0){
         printf("Buzz\n");
    }
    while(n>0){
        int d=n%10;
        if(d==3){
            printf("Fizz");
        }
        if(d==5){
            printf("Buzz");
        }
        n/=10;
    }
}
