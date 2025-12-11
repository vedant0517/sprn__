#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define SIZE 100

// Structure to represent a Song (Node)
typedef struct Song {
    char name[SIZE];
    struct Song* next;
} Song;

Song* head = NULL;

// Function to create a new song node
Song* createSong(char* name) {
    Song* newSong = (Song*)malloc(sizeof(Song));
    strcpy(newSong->name, name);
    newSong->next = NULL;
    return newSong;
}

// 1. Insert a song
void insertSong(char* name) {
    Song* newSong = createSong(name);
    if (head == NULL) {
        head = newSong;
        newSong->next = head;
    } else {
        Song* temp = head;
        while (temp->next != head)
            temp = temp->next;
        temp->next = newSong;
        newSong->next = head;
    }
    printf("Song '%s' inserted.\n", name);
}

// 2. Delete a song
void deleteSong(char* name) {
    if (head == NULL) {
        printf("Playlist is empty.\n");
        return;
    }

    Song *current = head, *prev = NULL;

    // If head node itself holds the song to be deleted
    if (strcmp(head->name, name) == 0) {
        if (head->next == head) {
            free(head);
            head = NULL;
            printf("Song '%s' deleted.\n", name);
            return;
        } else {
            Song* temp = head;
            while (temp->next != head)
               {
                   temp = temp->next;
               }
            temp->next = head->next;
            Song* toDelete = head;
            head = head->next;
            free(toDelete);
            printf("Song '%s' deleted.\n", name);
            return;
        }
    }

    do {
        prev = current;
        current = current->next;
        if (strcmp(current->name, name) == 0) {
            prev->next = current->next;
            free(current);
            printf("Song '%s' deleted.\n", name);
            return;
        }
    } while (current != head);

    printf("Song '%s' not found in the playlist.\n", name);
}

// 3. Play songs (traversal)
void playSongs(int count) {
    if (head == NULL) {
        printf("Playlist is empty.\n");
        return;
    }
    Song* temp = head;
    printf("Playing %d songs (looping):\n", count);
    for (int i = 0; i < count; i++) {
        printf("Now Playing: %s\n", temp->name);
        temp = temp->next;
    }
}

// 4. Search for a song
void searchSong(char* name) {
    if (head == NULL) {
        printf("Playlist is empty.\n");
        return;
    }

    Song* temp = head;
    int pos = 1;
    do {
        if (strcmp(temp->name, name) == 0) {
            printf("Song '%s' found at position %d.\n", name, pos);
            return;
        }
        temp = temp->next;
        pos++;
    } while (temp != head);

    printf("Song '%s' not found in the playlist.\n", name);
}

// 5. Display playlist
void displayPlaylist() {
    if (head == NULL) {
        printf("Playlist is empty.\n");
        return;
    }

    Song* temp = head;
    printf("Current Playlist:\n");
    int index = 1;
    do {
        printf("%d. %s\n", index++, temp->name);
        temp = temp->next;
    } while (temp != head);
}

// 6. Count songs
int countSongs() {
    if (head == NULL)
        return 0;
    int count = 0;
    Song* temp = head;
    do {
        count++;
        temp = temp->next;
    } while (temp != head);
    return count;
}

// Main function with switch case
int main() {
    int choice, playCount;
    char name[SIZE];

    while (1) {
        printf("\n--- Music Playlist Menu ---\n");
        printf("1. Insert Song\n");
        printf("2. Delete Song\n");
        printf("3. Play Songs (Looped)\n");
        printf("4. Search Song\n");
        printf("5. Display Playlist\n");
        printf("6. Count Songs\n");
        printf("0. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);
        getchar(); // Consume newline

        switch (choice) {
            case 1:
                printf("Enter song name to insert: ");
                fgets(name, SIZE, stdin);
                name[strcspn(name, "\n")] = '\0'; // remove newline
                insertSong(name);
                break;

            case 2:
                printf("Enter song name to delete: ");
                fgets(name, SIZE, stdin);
                name[strcspn(name, "\n")] = '\0';
                deleteSong(name);
                break;

            case 3:
                printf("Enter how many songs you want to play: ");
                scanf("%d", &playCount);
                playSongs(playCount);
                break;

            case 4:
                printf("Enter song name to search: ");
                fgets(name, SIZE, stdin);
                name[strcspn(name, "\n")] = '\0';
                searchSong(name);
                break;

            case 5:
                displayPlaylist();
                break;

            case 6:
                printf("Total number of songs: %d\n", countSongs());
                break;

            case 0:
                printf("Exiting playlist program. Goodbye!\n");
                exit(0);

            default:
                printf("Invalid choice. Try again.\n");
        }
    }

    return 0;
}
