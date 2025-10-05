//173 Vedant kapgate
#include <stdio.h>

int main() {
    int m;
    printf("Enter marks : ");  //0 to 100
    scanf("%d", &m);
    if (m == 99) {
        printf("Almost Perfect!\n");
    } else if (m >= 90) {
        printf("Grade: A\n");
    } else if (m >= 75) {
        printf("Grade: B\n");
    } else if (m >= 60) {
        printf("Grade: C\n");
    } else if (m >= 40) {
        printf("Grade: D\n");
    } else {
        printf("Grade: F\n");
    }
    return 0;
}

