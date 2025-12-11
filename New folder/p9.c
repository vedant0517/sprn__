#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

#define MAX 100

int adj[MAX][MAX];
bool visited[MAX];
int V;

// Function for DFS to detect cycle
bool dfsCycle(int v, int parent) {
    visited[v] = true;

    for (int u = 0; u < V; u++) {
        if (adj[v][u]) { // if there is an edge
            if (!visited[u]) {
                if (dfsCycle(u, v))
                    return true;
            } 
            else if (u != parent) {
                return true; // cycle found
            }
        }
    }
    return false;
}

// Function to check if graph has a cycle
bool isCyclic() {
    for (int i = 0; i < V; i++)
        visited[i] = false;

    for (int i = 0; i < V; i++) {
        if (!visited[i]) {
            if (dfsCycle(i, -1))
                return true;
        }
    }
    return false;
}#include <stdio.h>
#include <stdlib.h>

#define MAX 100

int adj[MAX][MAX];   // Adjacency matrix
int visited[MAX];    // Visited array
int n;               // Number of nodes (network size)

// Depth-First Search ignoring the failed node
void dfs(int v, int skip) {
    visited[v] = 1;

    for (int i = 0; i < n; i++) {
        // Do not visit the failed node
        if (i == skip)
            continue;

        // If there is a connection and not visited, continue DFS
        if (adj[v][i] == 1 && !visited[i])
            dfs(i, skip);
    }
}

// Check if the network is connected when node 'skip' fails
int isConnected(int skip) {
    // Reset visited array
    for (int i = 0; i < n; i++)
        visited[i] = 0;

    // Choose a starting node (not the failed one)
    int start = -1;
    for (int i = 0; i < n; i++) {
        if (i != skip) {
            start = i;
            break;
        }
    }

    // If no starting node exists (only one node), network is trivially connected
    if (start == -1)
        return 1;

    // Run DFS
    dfs(start, skip);

    // Check if all nodes except the failed one were reached
    for (int i = 0; i < n; i++) {
        if (i != skip && visited[i] == 0)
            return 0;  // Network became disconnected
    }
    return 1; // Still connected
}

int main() {
    printf("Enter number of nodes in the network: ");
    scanf("%d", &n);

    printf("Enter adjacency matrix (%d x %d):\n", n, n);
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            scanf("%d", &adj[i][j]);

    printf("\n--- Single Node Failure Detection ---\n");

    for (int i = 0; i < n; i++) {
        if (isConnected(i))
            printf("Node %d is NOT critical (network stays connected).\n", i);
        else
            printf("Node %d is CRITICAL! (network becomes disconnected if this node fails)\n", i);
    }

    return 0;
}


int main() {
    int choice;
    printf("----- CYCLE DETECTION IN GRAPH -----\n");
    printf("1. Read Graph from File\n");
    printf("2. Read Graph as Adjacency List\n");
    printf("3. Read Graph as Adjacency Matrix\n");
    printf("Enter your choice: ");
    scanf("%d", &choice);

    // Initialize adjacency matrix
    memset(adj, 0, sizeof(adj));

    switch (choice) {
        case 1: { // Read graph from file
            FILE *file = fopen("graph.txt", "r");
            if (file == NULL) {
                printf("File not found!\n");
                return 0;
            }
            int E, u, v;
            fscanf(file, "%d %d", &V, &E);
            for (int i = 0; i < E; i++) {
                fscanf(file, "%d %d", &u, &v);
                adj[u][v] = 1;
                adj[v][u] = 1;
            }
            fclose(file);
            break;
        }

        case 2: { // Read from adjacency list
            printf("Enter number of vertices: ");
            scanf("%d", &V);
            for (int i = 0; i < V; i++) {
                printf("Enter adjacent vertices of %d (-1 to stop): ", i);
                int node;
                while (1) {
                    scanf("%d", &node);
                    if (node == -1)
                        break;
                    adj[i][node] = 1;
                    adj[node][i] = 1;
                }
            }
            break;
        }

        case 3: { // Read from adjacency matrix
            printf("Enter number of vertices: ");
            scanf("%d", &V);
            printf("Enter adjacency matrix:\n");
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    scanf("%d", &adj[i][j]);
                }
            }
            break;
        }

        default:
            printf("Invalid choice!\n");
            return 0;
    }

    if (isCyclic())
        printf("\nCycle Detected in Graph.\n");
    else
        printf("\nNo Cycle Found in Graph.\n");

    return 0;
}