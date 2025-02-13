#include <stdio.h>
int main() {
    int matrix1[3][3], matrix2[3][3], result[3][3];
    // Input elements for the first matrix
    printf("Enter the elements of the first 3x3 matrix:\n");
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            printf("Enter element [%d][%d]: ",i,j);
            scanf("%d", &matrix1[i][j]);
        }
    }
    // Input elements for the second matrix
    printf("Enter the elements of the second 3x3 matrix:\n");
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            printf("Enter element [%d][%d]: ",i,j);
            scanf("%d", &matrix2[i][j]);
        }
    }
    // Addition
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            result[i][j] = matrix1[i][j] + matrix2[i][j];
        }
    }
    // Display
    printf("\nThe resultant matrix after addition is:\n");
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            printf("%d ", result[i][j]);
        }printf("\n");}
    return 0;
}
