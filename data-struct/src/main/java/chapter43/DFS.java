package chapter43;

import java.util.LinkedList;

/**
 * 拓扑排序实现方法二:深度优先遍历
 * 时间复杂度 O(V+E)
 */
public class DFS {

    public static void topoDfs(Graph graph) {
        int v = graph.v;
        LinkedList<Integer>[] adj = graph.adj;
        LinkedList<Integer>[] reverseAdj = new LinkedList[v];
        boolean[] visited = new boolean[v];
        for (int i = 0; i < v; i++) {
            reverseAdj[i] = new LinkedList<>();
        }
        //生成逆邻接表
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < adj[i].size(); j++) {
                int w = adj[i].get(j);
                reverseAdj[w].add(i);
            }
        }
        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i, reverseAdj, visited);
            }
        }
    }

    //递归处理每个顶点。对于顶点 i 来说，先输出它可达的所有顶点，也就是说，先把它依赖的所有的顶点输出了，然后再输出自己。
    private static void dfs(int i, LinkedList<Integer>[] reverseAdj, boolean[] visited) {
        for (int j = 0; j < reverseAdj[i].size(); j++) {
            int w = reverseAdj[i].get(j);
            if (visited[w]) {
                continue;
            }
            visited[w] = true;
            dfs(w, reverseAdj, visited);
        }
        System.out.print("->"+i);//输出自己
    }

    public static void main(String[] args) {
        Graph graph = new Graph(4);//无环
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(1, 3);
        Graph recursiveGraph = new Graph(4);//有环
        recursiveGraph.addEdge(0, 1);
        recursiveGraph.addEdge(1, 2);
        recursiveGraph.addEdge(2, 3);
        recursiveGraph.addEdge(3, 1);
        topoDfs(graph);
        System.out.println();
        topoDfs(recursiveGraph);
    }

}
