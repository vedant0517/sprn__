//173 Vedant  kapgate
#include<stdio.h>
#include<string.h>
int main()
{
    char str[100];
    int n, i;
    
    printf("Enter string: ");
    scanf("%s", str);
    
    n = strlen(str);
    
    for(i = 0; i < n/2; i++)
    {
        if(str[i] != str[n-1-i])
        {
            printf("Not palindrome\n");
            return 0;
        }
    }
    printf("Palindrome\n");
    return 0;
}