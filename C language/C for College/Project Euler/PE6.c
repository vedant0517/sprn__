#include <stdio.h>
int main()
{
    int sqofsum = 0;
    int     sumofsq =0;
    for (int i = 1; i <= 100; i++)
    {
        sumofsq = ( sumofsq  + i*i) ;
    }
    for (int i = 1; i <= 100; i++)
    {
       sqofsum = ( sqofsum + i) ;
    }
    printf(" The sum of the squares of first 100 natural number is %d\n", sumofsq ); // this was for sum of squares
    printf("The square of the sum of the first 100 natural numbers is %d\n",sqofsum*sqofsum ); // this was for  square of sum
    printf("%d",(sqofsum*sqofsum)-sumofsq);
    return 0;
}
