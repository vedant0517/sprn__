#include <stdio.h>
#include <stdlib.h>

struct node {
    int coeff;
    int expo;
    struct node* link;
};

struct node* insert(struct node* head, int co, int ex)
{
    struct node* temp;
    struct node* newP = malloc(sizeof(struct node));
    newP->coeff = co;
    newP->expo = ex;
    newP->link = NULL;

    //If there is no node in the list OR given exponent is greater
//than the first node exponent
    if(head == NULL || ex > head->expo)
    {
        newP->link = head;
        head = newP;
    }
    else
    {
        temp = head;
        while(temp->link != NULL && temp->link->expo >= ex)
           { temp = temp->link;}
        newP->link = temp->link;
        temp->link = newP;
    }
    return head;
}

struct node* create(struct node* head)
{
    int n, i;
    int coeff;
    int expo;

    printf("Enter the number of terms: ");
    scanf("%d", &n);

    for(i=0; i<n; i++)
    {
        printf("Enter the coefficient for term %d: ", i+1);
        scanf("%d", &coeff);

        printf("Enter the exponent for term %d: ", i+1);
        scanf("%d", &expo);

        head = insert(head, coeff, expo);
    }
    return head;
}

void print(struct node* head)
{
    if(head == NULL)
        printf("No Polynomial.");
    else {
        struct node* temp = head;
        while(temp != NULL)
        {
            printf("(%.1fx^%d)", temp->coeff, temp->expo);
            temp = temp->link;
            if(temp!=NULL)
                printf(" + ");
            else printf("\n");
        }
    }
}

void polyMult(struct node* head1, struct node* head2)
{
    struct node* ptr1 = head1;
    struct node* ptr2 = head2;
    struct node* head3 = NULL;

    //Check if first or second polynomial is NULL
    if(head1 == NULL || head2 == NULL)
    {
        printf("Zero polynomial\n");
        return;
    }

    //Multiplication of two polynomials
    while(ptr1 != NULL)
    {
        while(ptr2 != NULL)
        {
            head3 = insert(head3, ptr1->coeff *
            ptr2->coeff, ptr1->expo + ptr2->expo);
            ptr2 = ptr2->link;
        }
        ptr1 = ptr1->link;
        ptr2 = head2;
    }
    print(head3);
}
void polyAdd(struct node* head1, struct node* head2)
{
    struct node* ptr1 = head1;
    struct node* ptr2 = head2;
    struct node* head3 = NULL;
    while(ptr1!=NULL && ptr2!=NULL)
    {
        if(ptr1->expo == ptr2->expo)
        {
            head3 = insert(head3, ptr1->coeff+ptr2->coeff, ptr1->expo);
            ptr1 = ptr1->link;
            ptr2 = ptr2->link;
        }
        else if(ptr1->expo > ptr2->expo)
        {
            head3 = insert(head3, ptr1->coeff, ptr1->expo);
            ptr1 = ptr1->link;
        }
        else if(ptr1->expo < ptr2->expo)
        {
         head3 = insert(head3, ptr2->coeff, ptr2->expo);
         ptr2 = ptr2->link;
        }
    }

    while(ptr1!=NULL)
    {
        head3 = insert(head3, ptr1->coeff, ptr1->expo);
        ptr1 = ptr1->link;
    }
    while(ptr2!=NULL)
    {
        head3 = insert(head3, ptr2->coeff, ptr2->expo);
        ptr2 = ptr2->link;
    }
    printf("Added polynomial is: ");
    print(head3);
}

int main()
{
    struct node* head1 = NULL;
    struct node* head2 = NULL;
    printf("Enter the first polynomial\n ");
    head1 = create(head1);
    printf("Enter the second polynomial\n ");
    head2 = create(head2);

    polyMult(head1, head2);
    polyAdd(head1,head2);
    return 0;
}

