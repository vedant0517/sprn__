#include <stdio.h>
#include <stdlib.h>

#define MAX 100

int adj[MAX][MAX];  // Adjacency matrix
int visited[MAX];   // Visited array
int n;              // Number of nodes

// Standard DFS ignoring the skipped node
void dfs(int v, int skip) {
    visited[v] = 1;
    for (int i = 0; i < n; i++) {
        if (i != skip && adj[v][i] && !visited[i])
            dfs(i, skip);
    }
}

// Check if the graph is connected when node 'skip' is removed
int isConnected(int skip) {
    for (int i = 0; i < n; i++)
        visited[i] = 0;

    // Find a starting node that is not the skipped node
    int start = -1;
    for (int i = 0; i < n; i++) {
        if (i != skip) {
            start = i;
            break;
        }
    }

    if (start == -1)
        return 1; // Only one node? It's trivially connected

    dfs(start, skip);

    // Check if all nodes except skipped are visited
    for (int i = 0; i < n; i++) {
        if (i != skip && !visited[i])
            return 0; // Not connected
    }
    return 1; // Connected
}

int main() {
    printf("Enter number of nodes: ");
    scanf("%d", &n);

    printf("Enter adjacency matrix:\n");
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            scanf("%d", &adj[i][j]);

    printf("\nCritical node analysis:\n");
    for (int i = 0; i < n; i++) {
        if (!isConnected(i))
            printf("Node %d is critical. Its failure disconnects the network.\n", i);
        else
            printf("Node %d is not critical.\n", i);
    }

    return 0;
}

