package chapter44;

import java.util.LinkedList;

/**
 * 有向带权图
 */
public class Graph {

    public int v;

    public LinkedList<Edge>[] adj;

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t, int w) {
        adj[s].add(new Edge(s, t, w));
    }


    class Edge {
        public int sid;//起点
        public int eid;//终点
        public int w;//权值

        public Edge(int sid, int eid, int w) {
            this.sid = sid;
            this.eid = eid;
            this.w = w;
        }
    }


}
