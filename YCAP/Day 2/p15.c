// 173 Vedant
#include <stdio.h>

int main() {
    int n;
    printf("Enter the size of matrix (n x n): ");
    scanf("%d", &n);
    
    int matrix[n][n];
    int num = 1;
    
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (num % 3 == 0) {
                num++;
            }
            printf("%d ",num);
             num++;
        }
        printf("\n");
    }
    return 0;
}