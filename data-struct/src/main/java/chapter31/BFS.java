package chapter31;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {


        public static void bfs(Graph graph, int s, int t) {
        if (s == t) return;
        boolean[] visited = new boolean[graph.v];
        Queue<Integer> queue = new LinkedList<>();
        int[] prev = new int[graph.v];
        for (int i = 0; i < graph.v; ++i) {
            prev[i] = -1;
        }
        visited[s] = true;
        queue.add(s);
        while (!queue.isEmpty()) {
            int w = queue.poll();
            for (int i = 0; i < graph.adj[w].size(); i++) {
                int q = graph.adj[w].get(i);
                if (!visited[q]) {
                    prev[q] = w;
                    if (q == t) {
                        print(prev, s, t);
                        return;
                    }
                    queue.add(q);
                    visited[q] = true;
                }
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
