package chapter43;

import java.util.LinkedList;

/**
 * 有向图
 */
public class Graph {

    public int v;

    public LinkedList<Integer>[] adj;

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t) {
        adj[s].add(t);
    }
}
