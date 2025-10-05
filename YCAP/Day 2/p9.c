#include <stdio.h>
#include <conio.h>
#include <stdlib.h>

struct node {
    int data;
    struct node *addr;
};
struct node *start = NULL, *temp = NULL, *new1 = NULL;

int Create() {
    char ch;
    int n;

    printf("Enter an element : ");
    scanf("%d", &n);

    start = (struct node *)(malloc(sizeof(struct node))); // Allocate memory

    start->data = n;
    start->addr = NULL;

    temp = start;

    printf("Want to continue ? (Y/N): ");
    ch = getche();

    while (ch == 'Y' || ch == 'y') {
        printf("\nEnter next element : ");
        scanf("%d", &n);
        new1 = (struct node *)(malloc(sizeof(struct node)));

        new1->data = n;
        new1->addr = NULL;

        temp->addr = new1;
        temp = temp->addr;

        printf("Want to continue ? (Y/N): ");
        ch = getche();
    }

    return 0;
}

int Display() {
    if (start == NULL) {
        printf("\nList is empty!\n");
    } else {
        temp = start;
        printf("\nLinked list is : \n");
        while (temp != NULL) {
            printf("\t%d ->", temp->data);
            temp = temp->addr;
        }
        printf("\tNULL\n");
    }
    return 0;
}

int InsertOnFirst() {
    int n;
	
    printf("Enter element to insert at first : ");
    scanf("%d", &n);

    new1 = (struct node *)malloc(sizeof(struct node));
    new1->data = n;
    new1->addr = start;
    start = new1;

    printf("Inserted at first successfully!\n");
    return 0;
}

int InsertOnLast() {
    int n;
    printf("Enter element to insert at last : ");
    scanf("%d", &n);

    new1 = (struct node *)malloc(sizeof(struct node));
    new1->data = n;
    new1->addr = NULL;

    if (start == NULL) {
        start = new1;
    } else {
        temp = start;
        while (temp->addr != NULL)
            temp = temp->addr;
        temp->addr = new1;
    }

    printf("Inserted at last successfully!\n");
    return 0;
}

int InsertOnMiddle() {
    int n, pos, i;
    printf("Enter element to insert : ");
    scanf("%d", &n);
    printf("Enter position to insert (starting from 1): ");
    scanf("%d", &pos);

    new1 = (struct node *)malloc(sizeof(struct node));
    new1->data = n;

    if (pos == 1) {
        new1->addr = start;
        start = new1;
    } else {
        temp = start;
        for (i = 1; i < pos - 1 && temp != NULL; i++) {
            temp = temp->addr;
        }
        if (temp == NULL) {
            printf("Position out of range!\n");
            free(new1);
            return 0;
        }
        new1->addr = temp->addr;
        temp->addr = new1;
    }

    printf("Inserted at position %d successfully!\n", pos);
    return 0;
}

int DeleteFromFirst() {
    if (start == NULL) {
        printf("List is empty, nothing to delete!\n");
    } else {
        temp = start;
        start = start->addr;
        printf("Deleted element: %d\n", temp->data);
        free(temp);
    }
    return 0;
}

int DeleteFromLast() {
    struct node *prev;
    if (start == NULL) {
        printf("List is empty, nothing to delete!\n");
    } else if (start->addr == NULL) {
        printf("Deleted element: %d\n", start->data);
        free(start);
        start = NULL;
    } else {
        temp = start;
        while (temp->addr != NULL) {
            prev = temp;
            temp = temp->addr;
        }
        printf("Deleted element: %d\n", temp->data);
        free(temp);
        prev->addr = NULL;
    }
    return 0;
}

int DeleteFromMiddle() {
    int pos, i;
    struct node *prev;
    if (start == NULL) {
        printf("List is empty, nothing to delete!\n");
        return 0;
    }
    printf("Enter position to delete (starting from 1): ");
    scanf("%d", &pos);

    temp = start;
    if (pos == 1) {
        start = start->addr;
        printf("Deleted element: %d\n", temp->data);
        free(temp);
    } else {
        for (i = 1; i < pos && temp != NULL; i++) {
            prev = temp;
            temp = temp->addr;
        }
        if (temp == NULL) {
            printf("Position out of range!\n");
            return 0;
        }
        prev->addr = temp->addr;
        printf("Deleted element: %d\n", temp->data);
        free(temp);
    }
    return 0;
}

int main() {
    int choice;

    do {
        printf("\n-------------Linked List-------------");
        printf("\n1.Create \n2.Insert on first \n3.Insert on last \n4.Insert on middle \n5.Delete from first \n6.Delete from last \n7.Delete from middle \n8.Display \n9.Exit");
        printf("\n-------------------------------------");

        printf("\nEnter the choice : ");
        scanf("%d", &choice);

        switch (choice) {
            case 1: Create();
                break;

            case 2: InsertOnFirst();
                break;

            case 3: InsertOnLast();
                break;

            case 4: InsertOnMiddle();
                break;

            case 5: DeleteFromFirst();
                break;

            case 6: DeleteFromLast();
                break;

            case 7: DeleteFromMiddle();
                break;

            case 8: Display();
                break;

            case 9: printf("Exiting program...\n");
                break;

            default: printf("Invalid choice! Try again.\n");
        }
    } while (choice != 9);

    return 0;
}