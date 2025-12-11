
#include<stdio.h>
#include<stdlib.h>
struct node
{
    int data ;
    struct node *next;
};
struct node *head,*temp,*newnode;
//for newnode
struct node *getnode()
{
    newnode=(struct node*)malloc(sizeof(struct node));
    printf("Enter bill amount : ");
    scanf("%d",&newnode->data);
    newnode->next=0;
    return (newnode);
};

//Display Data
void display()
{
    if(head==0)
    {
        printf("No bills recorded yet \n");
    }
    temp=head;
    printf("Bills of the day :");
    while(temp!=0)
    {
        printf("%d rs ",temp->data);
        temp=temp->next;
    }
}

//insertion
void insert_at_start()
{
    newnode=getnode();
    newnode->next=head;
    head=newnode;
}

void total_sales() {
    if (head == NULL) {
        printf("No bills recorded yet.\n");
        return;
    }
    int total = 0;
    temp = head;
    while (temp != NULL) {
        total=total+temp->data;
        temp = temp->next;
    }
    printf("Total Sales of the day is %d rs\n", total);
}

void max_min_bill() {
    if (head == NULL) {
        printf("No bills recorded yet.\n");
        return;
    }
    temp = head;
    int max = head->data;
    int min = head->data;

    while (temp != NULL) {
        if (temp->data > max) max = temp->data;
        if (temp->data < min) min = temp->data;
        temp = temp->next;
    }

    printf("Maximum Bill is %d rs\n", max);
    printf("Minimum Bill is %d rs\n", min);
}
int main()
{
    int choice;
    while (choice != 5)
     {
        printf("\nCoffee Shop choices\n ");
        printf("1. Insert Bill of Customer\n");
        printf("2. Display all bills\n");
        printf("3. Display total sales\n");
        printf("4. Display maximum and minimum bill\n");
        printf("5. Exit\n");
        printf("Enter choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                insert_at_start();
                break;
            case 2:
                display();
                break;
            case 3:
                total_sales();
                break;
            case 4:
                max_min_bill();
                break;
            case 5:
                printf("Exit\n");
                break;
            default: printf("Invalid choice\n");
        }
    }
}
