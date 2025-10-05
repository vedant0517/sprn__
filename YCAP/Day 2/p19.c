// 173 Vedant
#include <stdio.h>
#include <string.h>

int main() {
    int choice;
    printf("=== Simple Menu ===\n");
    do {
        printf("\n--- MENU ---\n");
        printf("1. Hello\n");
        printf("2. Add a+b\n");
        printf("3. Square n\n");
        printf("4. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);
        
        switch(choice) {
            case 1:
                printf("Hello!\n");
                break;
            case 2: {
                int a, b;
                printf("Enter two numbers: ");
                scanf("%d %d", &a, &b);
                printf("Sum = %d\n", a + b);
                break;
            }
            case 3: {
                int n;
                printf("Enter a number: ");
                scanf("%d", &n);
                printf("Square = %d\n", n * n);
                break;
            }
            case 4:
                printf("Exiting...\n");
                break;
            default:
                printf("Invalid choice!\n");
        }
    } while(choice != 4);
   
    return 0;
}