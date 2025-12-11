#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define ALPHABET_SIZE 26
#define MAX_WORD_LEN 100

// Trie Node Structure
typedef struct TrieNode {
    struct TrieNode *children[ALPHABET_SIZE];
    int isEndOfWord;
} TrieNode;

// Create a new node
TrieNode* createNode() {
    TrieNode *node = (TrieNode*)malloc(sizeof(TrieNode));
    node->isEndOfWord = 0;

    for (int i = 0; i < ALPHABET_SIZE; i++)
        node->children[i] = NULL;

    return node;
}

// Convert a character to index (NO <ctype.h> USED)
// Works only for lowercase a–z
int getIndex(char ch) {
    if (ch >= 'a' && ch <= 'z')
        return ch - 'a';
    else
        return -1;   // invalid character
}

// Insert a word into Trie
void insert(TrieNode *root, const char *word) {
    TrieNode *curr = root;

    for (int i = 0; word[i] != '\0'; i++) {
        int index = getIndex(word[i]);
        if (index == -1)
            continue;   // skip invalid characters

        if (curr->children[index] == NULL)
            curr->children[index] = createNode();

        curr = curr->children[index];
    }

    curr->isEndOfWord = 1;
}

// Search a word in Trie
int search(TrieNode *root, const char *word) {
    TrieNode *curr = root;

    for (int i = 0; word[i] != '\0'; i++) {
        int index = getIndex(word[i]);
        if (index == -1)
            continue;

        if (curr->children[index] == NULL)
            return 0;

        curr = curr->children[index];
    }

    return curr->isEndOfWord;
}

// Insert words from file
void readFromFileAndInsert(TrieNode *root, const char *filename) {
    FILE *fp = fopen(filename, "r");

    if (fp == NULL) {
        printf("File not found.\n");
        return;
    }

    char buffer[1000];
    fgets(buffer, sizeof(buffer), fp);
    fclose(fp);

    char *token = strtok(buffer, "\t");
    while (token != NULL) {
        insert(root, token);
        token = strtok(NULL, "\t");
    }

    printf("Words inserted from file successfully!\n");
}

// Insert from array
void readFromArrayAndInsert(TrieNode *root, char keys[][MAX_WORD_LEN], int n) {
    for (int i = 0; i < n; i++)
        insert(root, keys[i]);

    printf("Words inserted from array successfully!\n");
}

// Direct search in file without Trie
void searchArrayKeysInFile(char keys[][MAX_WORD_LEN], int n, const char *filename) {
    FILE *fp = fopen(filename, "r");

    if (fp == NULL) {
        printf("File not found.\n");
        return;
    }

    char buffer[1000];
    fgets(buffer, sizeof(buffer), fp);
    fclose(fp);

    for (int i = 0; i < n; i++) {
        if (strstr(buffer, keys[i]) != NULL)
            printf("'%s' found in file.\n", keys[i]);
        else
            printf("'%s' not found in file.\n", keys[i]);
    }
}

int main() {
    TrieNode *root = createNode();
    int choice;
    char input[MAX_WORD_LEN];

    // Sample words
    char keys[][MAX_WORD_LEN] = {
        "and","bat","ball","book","cot","cotton",
        "internet","interview","joy","job","king",
        "lion","man","mango","manage"
    };
    int keyCount = sizeof(keys) / sizeof(keys[0]);

    // Write array words into a file
    FILE *fp = fopen("keys.txt", "w");
    for (int i = 0; i < keyCount; i++)
        fprintf(fp, "%s\t", keys[i]);
    fclose(fp);

    printf("\n==== TRIE WORD SEARCH PROGRAM (Beginner-Friendly) ====\n");

    while (1) {
        printf("\nMenu:\n");
        printf("1. Read from file and search word\n");
        printf("2. Read from array and search word\n");
        printf("3. Search all array words in file (no trie)\n");
        printf("4. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                readFromFileAndInsert(root, "keys.txt");
                printf("Enter word to search (lowercase only): ");
                scanf("%s", input);

                if (search(root, input))
                    printf("'%s' FOUND in Trie.\n", input);
                else
                    printf("'%s' NOT found in Trie.\n", input);
                break;

            case 2:
                readFromArrayAndInsert(root, keys, keyCount);
                printf("Enter word to search (lowercase only): ");
                scanf("%s", input);

                if (search(root, input))
                    printf("'%s' FOUND in Trie.\n", input);
                else
                    printf("'%s' NOT found in Trie.\n", input);
                break;

            case 3:
                searchArrayKeysInFile(keys, keyCount, "keys.txt");
                break;

            case 4:
                printf("Exiting... Goodbye!\n");
                exit(0);

            default:
                printf("Invalid choice. Try again.\n");
        }
    }

    return 0;
}
