#include <stdio.h>
int main()
{
    int f1 = 1, f2 = 2, f3;
    printf("%d \t%d", f1, f2);
    printf("\t");
    for (int i = 1; i <= 30; i++)
    {
        f3 = f1 + f2;

        printf("%d\t", f3);
        f1 = f2;
        f2 = f3;
    }
    return 0;
}
