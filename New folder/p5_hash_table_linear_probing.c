#include <stdio.h>
#define SIZE 10

int hash(int key) {
    return key % SIZE;
}

void insert(int hashTable[], int key) {
    int idx = hash(key);

    int first = idx;
    while (hashTable[idx] != -1) {
        idx = (idx + 1) % SIZE;
        if (idx == first) {
            printf("Hash Table is full, cannot insert %d\n", key);
            return;
        }
    }
    hashTable[idx] = key;
}

void display(int hashTable[]) {
    printf("\nHash Table:\n");
    for (int i = 0; i < SIZE; i++) {
        printf("Index %d : %d\n", i, hashTable[i]);
    }
}

int main() {
    int hashTable[SIZE];
    int choice, key;


    for (int i = 0; i < SIZE; i++) {
        hashTable[i] = -1;
    }

    while (1) {
        printf("\n--- Hash Table Menu ---\n");
        printf("1. Insert Key\n");
        printf("2. Display Hash Table\n");
        printf("3. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                printf("Enter key to insert: ");
                scanf("%d", &key);
                insert(hashTable, key);
                break;

            case 2:
                display(hashTable);
                break;

            case 3:
                printf("Exiting program..\n");
                return 0;

            default:
                printf("Invalid choice!\n");
        }
    }
}
