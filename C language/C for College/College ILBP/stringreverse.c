#include <stdio.h>
int main()
{
    char str[100], reversed[100];
    int length = 0, i;

    // Input string
    printf("Enter a string: ");
    fgets(str,100, stdin);

    // Calculate the length
    for(i=0;str[i]!='\0';i++){
            length++;
    }
    length-=1;
    printf("Length of string : %d\n",length);

    for (i = 0; i < length; i++) {
        reversed[i] = str[length - i - 1];
    }
    reversed[length] = '\0';  // Add the null terminator at end

    // Output the reversed string
     printf("Input string: %s", str);
    printf("Reversed string: %s\n", reversed);

    return 0;
}
