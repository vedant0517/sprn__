// 173 Vedant
#include <stdio.h>
#include <string.h>

int main() {
    char password[20];
    int attempt = 0;
    int correct = 0;

    do {
        printf("Enter password: ");
        scanf("%s", password);

        if(strcmp(password, "code123") == 0) {
            printf("Access Granted!\n");
            correct = 1;
            break;
        } else {
            printf("Wrong password! Try again.\n");
        }
        attempt++;
    } while(attempt < 3);

    if(!correct) {
        printf("Access Denied! Attempts over.\n");
    }

    return 0;
}