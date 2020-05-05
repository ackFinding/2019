package chapter43;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 拓扑排序实现方法一:Kahn 算法
 * 时间复杂度 O(V+E)（V 表示顶点个数，E 表示边的个数）
 */
public class KahnAlgo {

    public static void topoKahn(Graph graph) {
        int v = graph.v;
        LinkedList<Integer>[] adj = graph.adj;
        int[] inDegree = new int[v];//每个节点入度
        //统计初始邻接表入度
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < adj[i].size(); j++) {
                int w = adj[i].get(j);
                ++inDegree[w];
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        //找到入度为0的结点
        for (int i = 0; i < v; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int p = queue.poll();
            System.out.print(p + " ");
            for (int j = 0; j < adj[p].size(); j++) {
                int w = adj[p].get(j);
                --inDegree[w];
                if (inDegree[w] == 0) {
                    queue.add(w);
                }
            }
        }
    }


}
