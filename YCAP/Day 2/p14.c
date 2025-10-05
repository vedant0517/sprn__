// 173 Vedant
#include<stdio.h>
int main()
{
    int num = 572891;
    int evenSum = 0, oddSum = 0;
    int digit,i;
    
    printf("Original number: %d\n", num);
    
    for(i=num;i != 0; i= i / 10) {
        digit = i % 10;  
        
        if(digit % 2 == 0) {
            evenSum += digit;  
        } else {
            oddSum += digit;   
        }
    }
    printf("Sum of even digits: %d\n", evenSum);
    printf("Sum of odd digits: %d\n", oddSum);
    
    return 0;
}