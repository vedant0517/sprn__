#include<stdio.h>
int main()
{
    int num;
    printf("Enter Registration number to identify vehicle\n");
    scanf("%d",&num);

    if(num == 31)
    {
        printf("Nagpur");
    }
    else if(num == 49)
    {
        printf("Nagpur East");
    }
    else if(num == 40)
    {
        printf("Nagpur Rural");
    }
    else
    {
        printf("Out of Nagpur");
    }
    return 0;
}
