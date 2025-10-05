//173 Vedant
#include<stdio.h>
int main()
{
    char s1[20]="ssvvgg";
    char result[20];
    int i, j, found, pos = 0;
    
    for(i = 0; s1[i] != '\0'; i++)
    {
        found = 0;
        for(j = 0; j < pos; j++)
        {
            if(result[j] == s1[i])
            {
                found = 1;
                break;
            }
        }
        
        if(found == 0)
        {
            result[pos] = s1[i];
            pos++;
        }
    }
    result[pos] = '\0';
    printf("Original: %s\n", s1);
    printf("Result: %s\n", result);
    
    return 0;
}