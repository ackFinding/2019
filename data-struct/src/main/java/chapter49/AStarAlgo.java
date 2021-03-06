package chapter49;


/**
 * A* 搜索算法
 */
public class AStarAlgo {


    public void asStar(Graph graph, int s, int t) {
        int[] predecessor = new int[graph.v];
        graph.vertices[s].dist = 0;
        graph.vertices[s].f = 0;

        PriorityQueue queue = new PriorityQueue(graph.v);
        boolean[] inQueue = new boolean[graph.v];//为了避免将一个顶点重复添加到优先级队列
        inQueue[s] = true;
        queue.add(graph.vertices[s]);

        while (!queue.isEmpty()) {
            Vertex minVertex = queue.poll();//取出dist最小的顶点

            for (int i = 0; i < graph.adj[minVertex.id].size(); i++) {//考察最小顶点可达的所有顶点
                Graph.Edge edge = graph.adj[minVertex.id].get(i);
                Vertex nextVertex = graph.vertices[edge.eid];
                int minDist = minVertex.dist + edge.w;
                if (nextVertex.dist > minDist) {
                    nextVertex.dist = minDist;
                    nextVertex.f = nextVertex.dist + hManhattan(nextVertex, graph.vertices[t]);//起点到这个顶点的路径长度 + 顶点跟终点的曼哈顿距离
                    predecessor[nextVertex.id] = minVertex.id;
                    if (inQueue[nextVertex.id]) {
                        queue.update(nextVertex);
                    } else {
                        queue.add(nextVertex);
                        inQueue[nextVertex.id] = true;
                    }
                }
                if (nextVertex.id == t) {// 只要到达t就可以结束while了
                    queue.clear();// 清空queue，才能推出while循环
                    break;
                }
            }
        }
        System.out.print(s);
        print(s, t, predecessor);
    }

    public void print(int s, int t, int[] predecessor) {
        if (s == t) return;
        print(s, predecessor[t], predecessor);
        System.out.print("->" + t);
    }

    public static void main(String[] args) {
        AStarAlgo aStarAlgo = new AStarAlgo();
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 2);
        graph.addEdge(2, 3, 3);
        graph.addEdge(1, 3, 2);
        aStarAlgo.asStar(graph, 0, 3);
    }


    /**
     * 曼哈顿距离(两点之间横纵坐标的距离之和)
     *
     * @param v1
     * @param v2
     * @return
     */
    int hManhattan(Vertex v1, Vertex v2) { // Vertex表示顶点
        return Math.abs(v1.x - v2.x) + Math.abs(v1.y - v2.y);
    }

    /**
     * ===============================================================
     */


    // 因为Java提供的优先级队列，没有暴露更新数据的接口，所以我们需要重新实现一个
    private class PriorityQueue { // 按照vertex的f值构建的小顶堆，而不是按照dist
        private Vertex[] nodes;
        private int count;
        private int capacity;

        public PriorityQueue(int v) {
            this.nodes = new Vertex[v + 1];//0位置不用
            this.capacity = v + 1;
            this.count = 1;
        }

        public Vertex poll() {
            Vertex res = nodes[1];
            //数据搬移
            for (int i = 2; i < count; i++) {
                nodes[i - 1] = nodes[i];
            }
            count--;
            return res;
        }

        public void add(Vertex vertex) {
            if (count == capacity) {
                throw new IllegalStateException("Queue has full!");
            }
            nodes[count] = vertex;
            //由下往上堆化
            for (int i = count / 2; i >= 1; i--) {
                heapify(nodes, i, count);
            }
            count++;
        }


        public void update(Vertex vertex) {
            for (int i = 1; i < count; i++) {//更新结点的值
                if (nodes[i].id == vertex.id) {
                    nodes[i].f = vertex.f;
                    nodes[i].dist = vertex.dist;
                }
            }
            for (int i = count / 2; i >= 1; i--) { //从下往上堆化，重新符合堆的定义。时间复杂度O(logn)。
                heapify(nodes, i, count);
            }
        }

        public boolean isEmpty() {
            return count < 2;
        }

        /**
         * 调整成小根堆
         *
         * @param vertices
         * @param i
         * @param n
         */
        private void heapify(Vertex[] vertices, int i, int n) {
            int left = 2 * i;
            int right = 2 * i + 1;

            while (true) {
                int min;
                min = left <= n && vertices[left].f < vertices[i].f ? left : i;
                min = right <= n && vertices[right].f < vertices[min].f ? right : min;
                if (min == i) {
                    break;
                }
                swap(vertices, i, min);
                i = min;
                left = 2 * i;
                right = 2 * i + 1;
            }

        }


        private void swap(Vertex[] vertices, int i, int j) {
            Vertex temp = vertices[i];
            vertices[i] = vertices[j];
            vertices[j] = temp;
        }

        public void clear() {
            for (int i = 1; i < count; i++) {
                nodes[i] = null;
            }
            count = 1;
        }
    }
}
