package chapter7;

import org.junit.Test;

/**
 * 1) 单链表反转
 * 2) 链表中环的检测
 * 3) 两个有序的链表合并
 * 4) 删除链表倒数第n个结点
 * 5) 求链表的中间结点
 */
public class LinkedListAlgo {

    // 1)单链表反转
    public static Node reverse(Node node) {
        Node curr = node, prev = null, next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }


    // 2)检测环
    public static boolean checkCircle(Node node) {
        if (node == null) {
            return false;
        }
        Node slow = node, fast = node;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void testCircle() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        n1.next = n2;
        n2.next = n3;
//        n3.next = n2;
        System.out.println(checkCircle(n1));
    }

    // 3)有序链表合并
    public static Node mergeSortedLists(Node node1, Node node2) {
        if (node1 == null) {
            return node2;
        }
        if (node2 == null) {
            return node1;
        }
        Node curr, prev, res, other;
        res = node1.val < node2.val ? node1 : node2;
        prev = res;
        curr = prev.next;
        other = curr == node1 ? node2 : node1;
        while (curr != null) {
            if (curr.val <= other.val) {
                prev = curr;
                curr = curr.next;
            } else {
                prev.next = other;
                prev = prev.next;
                other = curr;
                curr = prev.next;
            }
        }
        prev.next = other;
        return res;
    }

    public static Node mergeSortedLists2(Node node1, Node node2) {
        if (node1 == null) {
            return node2;
        }
        if (node2 == null) {
            return node1;
        }
        Node head, p = node1, q = node2;
        if (p.val < q.val) {
            head = p;
            p = p.next;
        } else {
            head = q;
            q = q.next;
        }
        Node now = head;
        while (q != null && p != null) {
            if (q.val < p.val) {
                now.next = q;
                q = q.next;
            } else {
                now.next = p;
                p = p.next;
            }
            now = now.next;
        }
        now.next = p != null ? p : q;
        return head;
    }

    @Test
    public void testMergeList() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(4);
        n1.next = n2;
        n2.next = n3;
        //============
        Node n4 = new Node(1);
        Node n5 = new Node(3);
        Node n6 = new Node(4);
        n4.next = n5;
        n5.next = n6;

        printAll(mergeSortedLists(n1, n4));
        System.out.println("===========");
//        printAll(mergeSortedLists2(n1,n4));
    }

    // 4)删除倒数第K个结点
    public static Node deleteLastKth(Node node, int k) {
        if (node == null || k < 1) return node;
        int len = 0;
        Node head = node;
        //1.获取链表长度
        while (head != null) {
            head = head.next;
            len++;
        }
        //2.取得正序数
        int count = len - k;
        //3.为零表示要删除的是头结点
        if (count == 0) {
            node = node.next;
        }
        if (count > 0) {
            head = node;
            //4.定位到要删除结点的前一个结点
            while (--count > 0) {
                head = head.next;
            }
            //5.删除
            head.next = head.next.next;
        }
        return node;
    }

    public static Node deleteLastKth2(Node node, int k) {
        if (node == null || k < 1) return node;
        Node p = node;
        while (p != null) {
            p = p.next;
            k--;
        }
        //删除的是头部
        if (k == 0) {
            node = node.next;
        }
        if (k < 0) {
            p = node;
            //定位到要删除结点的前一个结点
            while (++k < 0) {
                p = p.next;
            }
            p.next = p.next.next;
        }
        return node;
    }

    public static Node deleteLastKth3(Node node, int k) {
        if (node == null || k < 1) return node;
        Node fast = node;
        while (fast != null && --k > 0) {
            fast = fast.next;
        }
        //k值超出范围,找不到
        if (k > 0) {
            return node;
        }
        Node prev = null, slow = node;
        while (fast.next != null) {
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }
        if (prev == null) {//删除的是头结点
            node = node.next;
        } else {
            prev.next = prev.next.next;
        }
        return node;
    }

    @Test
    public void testDeleteLastKth() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(4);
        n1.next = n2;
        n2.next = n3;
        //============
        Node n4 = new Node(1);
        Node n5 = new Node(3);
        Node n6 = new Node(4);
        n4.next = n5;
        n5.next = n6;

//        printAll(deleteLastKth(n1, 2));
//        printAll(deleteLastKth2(n1, 2));
        printAll(deleteLastKth3(n1, 3));
        System.out.println("===========");
//        printAll(deleteLastKth(new Node(), 1));
//        printAll(deleteLastKth2(new Node(), 1));
//        printAll(deleteLastKth3(n1, 1));
//        System.out.println("===========");
//        printAll(deleteLastKth(n1, 3));
//        printAll(deleteLastKth2(n1, 3));
        printAll(deleteLastKth3(new Node(), -1));
    }

    // 5)求中间结点
    public static Node findMiddleNode(Node node) {
        if (node == null) {
            return null;
        }
        Node slow = node, fast = node;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    @Test
    public void testMiddleNode() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(4);
        n1.next = n2;
        n2.next = n3;
        //============
        Node n4 = new Node(1);
        Node n5 = new Node(3);
        Node n6 = new Node(4);
        Node n7 = new Node(5);
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        printAll(findMiddleNode(n1));
        printAll(findMiddleNode(n4));
        System.out.println("===========");
//        printAll(mergeSortedLists2(n1,n4));
    }

    private static class Node {
        int val;
        Node next;

        /**
         * for dummy node
         */
        public Node() {
            next = null;
        }

        public Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    public static void printAll(Node list) {
        Node p = list;
        while (p != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }
        System.out.println();
    }
}
