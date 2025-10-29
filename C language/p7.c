#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct node {
    char name[50];
    int salary;
    struct node *left, *right;
};

struct node* createNode(char name[],int salary)
{
    struct node* newNode= (struct node*)malloc(sizeof(struct node));
    strcpy(name,newNode->salary);
    newNode->salary=salary;
    newNode->left = newNode->right ==NULL;
    return newNode;
}

struct node* insert(struct node* root,char name[],int salary)
{
    if(root == NULL) return createNode(name,salary);

    else if(salary<root->salary){
            root->left=insert(root->left,name,salary);
        } else{
            root->right=insert(root->right,name,salary);
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
