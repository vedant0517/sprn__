// 173 Vedant
#include<stdio.h>
int main()
{
    int num[10];
    int i;
    
    printf("Enter 10 integers: ");
    for(i = 0; i < 10; i++) {
        scanf("%d", &num[i]);
    }
    printf("Odd numbers: ");
    for(i = 0; i < 10; i++) {
        if(num[i] % 2 != 0) { 
            printf("%d ", num[i]);
        }
    }
    printf("\n");
    
    return 0;
} 