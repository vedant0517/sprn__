//vedant 173
#include <stdio.h>
#define MAX 10

int stack[MAX], top = -1;

// Push operation
void push(int x) {
    if (top == MAX - 1) {
        printf("Stack Overflow\n");
        return;
    }
    stack[++top] = x;
    printf("Element %d pushed successfully\n", x);
}

// Pop operation
int pop() {
    if (top == -1) {
        printf("Stack Underflow\n");
        return -1;
    }
    return stack[top--];
}

// Display stack
void display() {
    if (top == -1) {
        printf("Stack is empty\n");
        return;
    }
    printf("Stack:\n");
    for (int i = top; i >= 0; i--)
    {
         printf("%d ", stack[i]);
    }
    printf("\n");
}

int main() {
    int choice, value;
    
    while (1) {
        printf("\n=== STACK MENU ===\n");
        printf("1. Push\n");
        printf("2. Pop\n");
        printf("3. Display\n");
        printf("4. Exit\n");
        printf("Enter your choice: ");
        
        scanf("%d", &choice);
        
        switch (choice) {
            case 1:
                printf("Enter element to push: ");
                scanf("%d", &value);
                push(value);
                break;
                
            case 2:
                value = pop();
                if (value != -1) {
                    printf("Popped element: %d\n", value);
                }
                break;
                
            case 3:
                display();
                break;
                
            case 4:
                printf("Exiting program...\n");
                return 0;
                
            default:
                printf("Invalid choice! Please enter 1-4.\n");
        }
    }
    
    return 0;
}