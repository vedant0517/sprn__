//173 Vedant kapgate
#include <stdio.h>
int main() {
    int units;
    float bill = 0;

    printf("Enter number of units consumed: ");
    scanf("%d", &units);

    if (units <= 100) {
        bill = units * 5;
    } else if (units <= 200) {
        bill = (100 * 5) + (units - 100) * 7;
    } else {
        bill = (100 * 5) + (100 * 7) + (units - 200) * 10;
    }

    if (bill > 2000) {
        bill += bill * 0.10;
    }

    printf("Total Electricity Bill = Rs%.2f\n", bill);
    return 0;
}
