package chapter31;

public class DFS {

    private static boolean found = false;

    public static void dfs(Graph graph, int s, int t) {
        boolean[] visited = new boolean[graph.v];
        int[] prev = new int[graph.v];
        for (int i = 0; i < graph.v; ++i) {
            prev[i] = -1;
        }
        recurDfs(graph, visited, prev, s, t);
        print(prev, s, t);
    }

    private static void recurDfs(Graph graph, boolean[] visited, int[] prev, int s, int t) {
        if (found) return;
        visited[s] = true;
        if (s == t) {
            found = true;
            return;
        }
        for (int i = 0; i < graph.adj[s].size(); i++) {
            int q = graph.adj[s].get(i);
            if (!visited[q]) {
                prev[q] = s;
                recurDfs(graph, visited, prev, s, t);
            }
        }
    }


    private static void print(int[] prev, int s, int t) {
        if (prev[t] != -1 && t != s) {
            print(prev, s, prev[t]);
        }
        System.out.print(prev[t] + " ");
    }

    public static void main(String[] args) {

    }
}
