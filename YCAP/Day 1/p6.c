//173 Vedant Kapgate
#include <stdio.h>
int main() {
    int choice;  float amt;
    printf("1: USD to INR\n");
    printf("2: INR to USD\n");
    printf("3: EUR to INR\n");
    printf("4: INR to EUR\n");
    printf("Enter choice: ");
    scanf("%d", &choice);
    printf("Enter amount: ");
    scanf("%f", &amt);
    switch(choice) {
        case 1:
            printf("%.2f USD = %.2f INR\n", amt, amt*85);
            break;
        case 2:
            printf("%.2f INR = %.2f USD\n", amt, amt*0.014);
            break;
        case 3:
             printf("%.2f EUR = %.2f INR\n", amt, amt*95);
             break;
        case 4: printf("%.2f INR = %.2f EUR\n", amt, amt*0.017);
             break;
        default:
            printf("Invalid choice!\n");
    }
    return 0;
}


