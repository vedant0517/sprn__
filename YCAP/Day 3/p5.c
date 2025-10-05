//173 Vedant
#include<stdio.h>
#include<string.h>

int main()
{
    char line[100];
    int i, words = 0;
    
    printf("Enter a line: ");
    gets(line);
    
    for(i = 0; line[i] != '\0'; i++)
    {
        if(line[i] == ' ')
            words++;
    }
    printf("Number of words: %d\n", words+1);
     return 0;
}