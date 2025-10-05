//173 Vedant
#include<stdio.h>
#include<string.h>
#include<stdlib.h>

int main()
{
    int choice;
    char str[100];
    int i, len, vowels;
    
    printf("Menu:\n");
    printf("1. Reverse string\n");
    printf("2. Count vowels\n");
    printf("3. Convert to uppercase\n");
    printf("4. Convert to lowercase\n");
    printf("Enter your choice: ");
    scanf("%d", &choice);
    
    printf("Enter a string (no spaces): ");
    scanf("%s", str);
    
    switch(choice)
    {
        case 1: // Reverse string
            len = strlen(str);
            printf("Original: %s\n", str);
            printf("Reversed: ");
            for(i = len-1; i >= 0; i--)
                printf("%c", str[i]);
            printf("\n");
            break;
            
        case 2: // Count vowels
            vowels = 0;
            for(i = 0; str[i] != '\0'; i++)
            {
                if(str[i] == 'a' || str[i] == 'e' || str[i] == 'i' || 
                   str[i] == 'o' || str[i] == 'u' || str[i] == 'A' || 
                   str[i] == 'E' || str[i] == 'I' || str[i] == 'O' || str[i] == 'U')
                    vowels++;
            }
            printf("Number of vowels: %d\n", vowels);
            break;
            
        case 3: // Convert to uppercase
             printf("Uppercase: ");
            for(i = 0; str[i] != '\0'; i++)
            {
                if(str[i] >= 'a' && str[i] <= 'z')
                    {
                        printf("%c", str[i] - 32);
                    }
                else{
                    printf("%c", str[i]);
                }
            }
            printf("\n");
            break;
            
        case 4: // Convert to lowercase
            printf("Lowercase: ");
            for(i = 0; str[i] != '\0'; i++)
            {
                if(str[i] >= 'A' && str[i] <= 'Z')
                    {
                        printf("%c", str[i] + 32);
                    }
                else{
                    printf("%c", str[i]);
                }
                    
            }
            printf("\n");
            break;
        
        default:
            printf("Invalid choice!\n");
    }
    
    return 0;
}