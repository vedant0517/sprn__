//173 Vedant Kapgate
#include <stdio.h>

int main() {
    int n;
    int frequency[101] = {0}; 

    printf("Enter number of integers: ");
    scanf("%d", &n);
    
    printf("Enter %d integers (0-100): ", n);
    for (int i = 0; i < n; i++) {
        int num;
        scanf("%d", &num);
        
        if (num >= 0 && num <= 100) {
            frequency[num]++;
        } else {
            printf("Warning: %d is out of range\n", num);
        }
    }
    printf("\nFrequency of each number:\n");
    for (int i = 0; i <= 100; i++) {
        if (frequency[i] > 0) {
            printf("%d appears %d times\n", i, frequency[i]);
        }
    }
    return 0;
}