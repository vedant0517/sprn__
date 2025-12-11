#include <stdio.h>
#include <stdlib.h>

#define MAX 100

// Stack structure
struct Stack {
    int arr[MAX];
    int top;
};

// Initialize stack
void init(struct Stack* s) {
    s->top = -1;
}

// Check if stack is empty
int isEmpty(struct Stack* s) {
    return s->top == -1;
}

// Check if stack is full
int isFull(struct Stack* s) {
    return s->top == MAX - 1;
}

// Push element
void push(struct Stack* s, int x) {
    if (isFull(s)) {
        printf("Stack Overflow\n");
        return;
    }
    s->arr[++s->top] = x;
}

// Pop element
int pop(struct Stack* s) {
    if (isEmpty(s)) {
        printf("Stack Underflow\n");
        return -1;
    }
    return s->arr[s->top--];
}

// Queue using two stacks
struct Queue {
    struct Stack s1, s2;
};

// Initialize queue
void initQueue(struct Queue* q) {
    init(&q->s1);
    init(&q->s2);
}

// Enqueue operation (O(1))
void enqueue(struct Queue* q, int x) {
    push(&q->s1, x);
}

// Dequeue operation (Amortized O(1))
int dequeue(struct Queue* q) {
    if (isEmpty(&q->s1) && isEmpty(&q->s2)) {
        printf("Queue is empty\n");
        return -1;
    }

    if (isEmpty(&q->s2)) {
        while (!isEmpty(&q->s1)) {
            push(&q->s2, pop(&q->s1));
        }
    }
    return pop(&q->s2);
}

// Peek front element
int peek(struct Queue* q) {
    if (isEmpty(&q->s1) && isEmpty(&q->s2)) {
        printf("Queue is empty\n");
        return -1;
    }

    if (isEmpty(&q->s2)) {
        while (!isEmpty(&q->s1)) {
            push(&q->s2, pop(&q->s1));
        }
    }
    return q->s2.arr[q->s2.top];
}

// Display queue elements
void display(struct Queue* q) {
    int i;
    // Transfer all to s2 to display in queue order
    if (isEmpty(&q->s1) && isEmpty(&q->s2)) {
        printf("Queue is empty\n");
        return;
    }
    // Copy stacks to avoid modifying original
    struct Stack temp1 = q->s1, temp2 = q->s2;
    // Move all from temp1 to temp2
    while (!isEmpty(&temp1)) {
        push(&temp2, pop(&temp1));
    }
    printf("Queue: ");
    for (i = temp2.top; i >= 0; i--) {
        printf("%d ", temp2.arr[i]);
    }
    printf("\n");
}

// Menu-driven main function
int main() {
    struct Queue q;
    int choice, val;
    initQueue(&q);

    while (1) {
        printf("\nQueue Using Two Stacks (Menu)\n");
        printf("1. Enqueue\n");
        printf("2. Dequeue\n");
        printf("3. Peek\n");
        printf("4. Display\n");
        printf("5. Exit\n");
        printf("Enter your choice: ");
        if (scanf("%d", &choice) != 1) {
            printf("Invalid input. Exiting.\n");
            break;
        }
        switch (choice) {
            case 1:
                printf("Enter value to enqueue: ");
                if (scanf("%d", &val) != 1) {
                    printf("Invalid input.\n");
                    // Clear input buffer
                    while (getchar() != '\n');
                    break;
                }
                enqueue(&q, val);
                printf("%d enqueued.\n", val);
                break;
            case 2:
                val = dequeue(&q);
                if (val != -1)
                    printf("Dequeued: %d\n", val);
                break;
            case 3:
                val = peek(&q);
                if (val != -1)
                    printf("Front element: %d\n", val);
                break;
            case 4:
                display(&q);
                break;
            case 5:
                printf("Exiting...\n");
                exit(0);
            default:
                printf("Invalid choice. Try again.\n");
        }
    }
    return 0;
}