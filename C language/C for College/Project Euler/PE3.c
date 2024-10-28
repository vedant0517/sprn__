#include <stdio.h>
int main()
{
    long long num = 600851475143;
    long long factor=2;
    while (factor*factor <= num)
    {
       if(num%factor==0){
        num/=factor;
       }
       else{
        factor++;
       }
    }
    printf("the largest prime factor is %lld\n",num);
    return 0;
}
