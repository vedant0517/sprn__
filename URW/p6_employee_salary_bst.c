#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct node {
    char name[50];
    int salary;
    struct node *left, *right;
};

// create a new node
struct node* createNode(char name[], int salary) {
    struct node* newNode = (struct node*)malloc(sizeof(struct node));
    strcpy(newNode->name, name);
    newNode->salary = salary;
    newNode->left = newNode->right = NULL;
    return newNode;
}

struct node* insert(struct node* root, char name[], int salary) {
    if (root == NULL){
        return createNode(name, salary);
    }
    else if (salary < root->salary){
        root->left = insert(root->left, name, salary);
    }
    else{
         root->right = insert(root->right, name, salary);
    }
    return root;
}

// display employees 
void displayInorder(struct node* root) {
    if (root != NULL) {
        displayInorder(root->left);
        printf("%s\t%d\n", root->name, root->salary);
        displayInorder(root->right);
    }
}

// find min salary
struct node* minSalary(struct node* root) {
    if (root == NULL || root->left == NULL)
        return root;
    return minSalary(root->left);
}

// find max salary
struct node* maxSalary(struct node* root) {
    if (root == NULL || root->right == NULL)
        return root;
    return maxSalary(root->right);
}

// total salary expenses
int totalSalary(struct node* root) {
    if (root == NULL)
        return 0;
    return root->salary + totalSalary(root->left) + totalSalary(root->right);
}

int main() {
    struct node* root = NULL;
    int choice, salary;
    char name[50];

    while (1) {
        printf("\n\n----- Employee Database Menu -----\n");
        printf("1. Add Employee\n");
        printf("2. Display Employees (Sorted by Salary)\n");
        printf("3. Show Employee with Minimum Salary\n");
        printf("4. Show Employee with Maximum Salary\n");
        printf("5. Show Total Salary Expenses\n");
        printf("6. Exit\n");
        printf("----------------------------------\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                printf("Enter Employee Name: ");
                scanf("%s", name);
                printf("Enter Employee Salary: ");
                scanf("%d", &salary);
                root = insert(root, name, salary);
                printf("Employee Added!\n");
                break;

            case 2:
                if (root == NULL)
                    printf("No employees in database.\n");
                else {
                    printf("\nEmployees (Sorted by Salary):\n");
                    displayInorder(root);
                }
                break;

            case 3:
                if (root == NULL)
                    printf("No employees in database.\n");
                else {
                    struct node* min = minSalary(root);
                    printf("\nMinimum Salary: %s (%d)\n", min->name, min->salary);
                }
                break;

            case 4:
                if (root == NULL)
                    printf("No employees in database.\n");
                else {
                    struct node* max = maxSalary(root);
                    printf("\nMaximum Salary: %s (%d)\n", max->name, max->salary);
                }
                break;

            case 5:
                if (root == NULL)
                    printf("No employees in database.\n");
                else
                    printf("\nTotal Salary Expenses: %d\n", totalSalary(root));
                break;

            case 6:
                printf("Exiting Program...\n");
                exit(0);

            default:
                printf("Invalid Choice! Try again.\n");
        }
    }
    return 0;
}
