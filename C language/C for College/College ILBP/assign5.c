#include<stdio.h>
int main()
{
    int arr[3][3],upper[3][3]={0},lower[3][3]={0},i,j;
    printf("Enter 3x3 matrix:\n");
    for(i=0;i<3;i++){
        for(j=0;j<3;j++){
        printf("Enter[%d][%d]:",i,j);
        scanf("%d",&arr[i][j]);}
    }
    //upper triangular and lower
     for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            if (i <= j) {
                upper[i][j] = arr[i][j];}
            if (i >= j) {
                lower[i][j] = arr[i][j];
            }
        }
    }
        // Output:upper triangular matrix
    printf("\nUpper Triangular Matrix:\n");
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
        printf("%d ", upper[i][j]);
        }
        printf("\n");
    }
    // Output:lower triangular matrix
    printf("\nLower Triangular Matrix:\n");
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
        printf("%d ", lower[i][j]);
        }
        printf("\n");
    }
    return 0;
}
