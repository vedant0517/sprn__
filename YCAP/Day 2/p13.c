// 173 Vedant
#include<stdio.h>
int main()
{
    int n, i, j;
    
    printf("Enter the size of triangle: ");
    scanf("%d", &n);
    
    printf("Normal Triangle:\n");
    for(i = 1; i <= n; i++) {
        for(j = 1; j <= i; j++) {
            printf("* ");
        }
        printf("\n");
    }
    
    printf("\n");
    

    printf("Inverted Triangle:\n");
    for(i = n; i >= 1; i--) {
        for(j = 1; j <= i; j++) {
            printf("* ");
        }
        printf("\n");
    }

    return 0;
}