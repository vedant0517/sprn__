#include<stdio.h>
int main(argc, char *argv[])
{
    if(argc!=1){
        printf("Enter only 1 argument\n")
        return 0;
    }
    int fact=1
    int num1=atoi(argv[1]);
    for(int i=1;i<=num1;i++){
        fact=fact*i;
    }
    printf("Factorial of %d is %d \n",num1,fact);
    return 0;
}
