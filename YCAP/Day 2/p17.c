// 173 Vedant
#include <stdio.h>
int main() {
    int n=4538, last, first;
    last = n % 10;  

    while(n > 10) {
        n = n / 10;
    }
    first = n;

    printf("First digit = %d, Last digit = %d\n", first, last);
    return 0;
}