package chapter43;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 在查找最终推荐人的时候，可能会因为脏数据，造成存在循环推荐，
 * 比如，用户 A 推荐了用户 B，用户 B 推荐了用户 C，用户 C 又推荐了用户 A。
 * 如何避免这种脏数据导致的无限递归？
 */
public class CheckCircle {


    Set<Long> cache = new HashSet<>();

    /**
     * 每次都只是查找一个用户的最终推荐人，所以，我们并不需要动用复杂的拓扑排序算法，
     * 而只需要记录已经访问过的用户 ID，当用户 ID 第二次被访问的时候，就说明存在环，也就说明存在脏数据。
     *
     * @param actorId
     * @return
     */
    public long findRootReferer(long actorId) {
        if (cache.contains(actorId)) {
            return -1;//存在环
        }
        cache.add(actorId);
        String refferer = "select referer_id from table where actor_id=" + actorId;//假设查询数据库
        if (refferer == null) return actorId;
        return findRootReferer(Long.parseLong(refferer));
    }


    /**
     * 拓扑排序检测环
     *
     * @param graph
     * @return
     */
    public static boolean topoKahnCheckCircle(Graph graph) {
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
        int count = 0;
        while (!queue.isEmpty()) {
            int p = queue.poll();
            System.out.print(p + " ");
            count += 1;
            for (int j = 0; j < adj[p].size(); j++) {
                int w = adj[p].get(j);
                --inDegree[w];
                if (inDegree[w] == 0) {
                    queue.add(w);
                }
            }
        }
        System.out.println();
        if (count == v) {
            return false;
        }
        return true;
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
        System.out.println(topoKahnCheckCircle(graph));
        System.out.println(topoKahnCheckCircle(recursiveGraph));
        System.out.println("---------------");
    }

}
