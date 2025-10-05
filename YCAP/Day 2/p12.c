// 173 Vedant
#include<stdio.h>
int main()
{
    int i;
    int r = 0; 
    int a = 12345;

    for(i = a; i != 0; i = i / 10) {
        int d = i % 10;  
        r = r * 10 + d; 
    }
    
    printf("Original number: %d\n", a);
    printf("Reversed number: %d\n", r);
    
    return 0;
}