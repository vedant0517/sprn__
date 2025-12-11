#include <stdio.h>
#include <stdlib.h>

#define N 10

char text[N];
char redoStack[N];
int topText = -1;
int topRedo = -1;

void pushText(char ch)
{
    if (topText < N - 1)
    {
        text[++topText] = ch;
    }
    else
    {
        printf("Text buffer full\n");
    }
}

char popText()
{
    if (topText >= 0)
    {
        return text[topText--];
    }
    return '\0';
}

void pushRedo(char ch)
{
    if (topRedo < N - 1)
    {
        redoStack[++topRedo] = ch;
    }
    else
    {
        printf("Redo stack overflow\n");
    }
}

char popRedo()
{
    if (topRedo >= 0)
    {
        return redoStack[topRedo--];
    }
    return '\0';
}

void displayText()
{
    printf("Current Text: ");
    for (int i = 0; i <= topText; i++)
    {
        printf("%c", text[i]);
    }
    printf("\n");
}

int main()
{
    char ch;
    int choice;
    printf("---- Menu Choice ---- ");

    while (1)
    {
        printf("\n1. Push\n2. Undo\n3. Redo\n4. Display\n5. Exit\nEnter choice: ");
        scanf("%d", &choice);

        switch (choice)
        {
            case 1:
                printf("Enter character to push: ");
                scanf(" %c", &ch);
                pushText(ch);
                topRedo = -1;  // Clear redo stack
                break;

            case 2:
                if (topText >= 0)
                {
                    char undoChar = popText();
                    pushRedo(undoChar);
                    printf("Undo: Removed %c\n", undoChar);
                }
                else
                {
                    printf("Nothing to undo.\n");
                }
                break;

            case 3:
                if (topRedo >= 0)
                {
                    char redoChar = popRedo();
                    pushText(redoChar);
                    printf("Redo: Restored %c\n", redoChar);
                }
                else
                {
                    printf("Nothing to redo.\n");
                }
                break;

            case 4:
                displayText();
                break;

            case 5:
                printf("Exited");
                exit(0);

            default:
                printf("Invalid choice.\n");
        }
    }
}
