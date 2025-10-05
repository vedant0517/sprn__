//173 Vedant  kapgate
#include<stdio.h>

int main()
{
    int arr[100], n, i;
    printf("Enter size: ");
    scanf("%d", &n);
    
    printf("Enter numbers: ");
    for(i = 0; i < n; i++)
        scanf("%d", &arr[i]);
    
    // Check palindrome
    for(i = 0; i < n/2; i++)
    {
        if(arr[i] != arr[n-1-i])
        {
            printf("Not palindrome\n");
            return 0;
        }
    }
    printf("Palindrome\n");
    return 0;
}