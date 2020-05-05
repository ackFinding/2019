package chapter49;

// 下面这个类是为了A*算法实现用的
public class Vertex {
    public int id; // 顶点编号ID
    public int dist; // 从起始顶点到这个顶点的距离
    public int x;//x坐标
    public int y;//y坐标
    public int f; // 新增：f(i)=g(i)[从起点走到这个顶点的路径长度]+h(i)[曼哈顿距离]

    public Vertex(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.f = Integer.MAX_VALUE;
        this.dist = Integer.MAX_VALUE;
    }
}
