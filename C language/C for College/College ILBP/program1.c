#include <stdio.h>

int main()
{
    int per;


    printf("Enter the percentage of the student: ");
    scanf("%d", &per);


    if (per > 100) {
        printf("Invalid percentage\n");
    }
    else if (per >= 75) {
        printf("Distinction\n");
    }
    else if (per >= 60) {
        printf("First Class\n");
    }
    else if (per >= 50) {
        printf("Second Class\n");
    }
    else {
        printf("Pass without class\n");
    }

    return 0;
}
