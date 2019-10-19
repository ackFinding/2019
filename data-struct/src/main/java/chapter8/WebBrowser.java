package chapter8;

public class WebBrowser {

    private LinkedListBaseStack<String> back = new LinkedListBaseStack();

    private LinkedListBaseStack<String> forward = new LinkedListBaseStack();

    private String currentPage;

    public void goForward() {
        if (canGoForward()) {
            back.push(this.currentPage);
            String forwardUrl = forward.pop();
            showUrl(forwardUrl,"Forward");
            return;
        }
        System.out.println("** Cannot go forward, no pages ahead.");
    }

    private boolean canGoForward() {
        return forward.size() > 0;
    }

    public void goBack() {
        if (canGoBack()) {
            forward.push(this.currentPage);
            String backUrl = back.pop();
            showUrl(backUrl,"Back");
            return;
        }
        System.out.println("* Cannot go back, no pages behind.");
    }

    private boolean canGoBack() {
        return back.size() > 0;
    }

    public void showUrl(String url, String prefix) {
        this.currentPage = url;
        System.out.println(prefix + " page == " + url);
    }

    private void checkCurrentPage() {
        System.out.println("current page:" + currentPage);
    }

    private void open(String s) {
        if (currentPage != null) {
            forward.clear();
            back.push(this.currentPage);
        }
        showUrl(s, "Open");
    }


    class Node<T> {
        T val;
        Node next;

        /**
         * for dummy node
         */
        public Node() {
            next = null;
        }

        public Node(T val) {
            this.val = val;
            this.next = null;
        }
    }

    public void printAll(Node list) {
        Node p = list;
        while (p != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        WebBrowser browser = new WebBrowser();
        browser.open("http://www.baidu.com/a");
        browser.open("http://news.baidu.com/b");
        browser.open("http://news.baidu.com/c");
        System.out.println("==========");
        browser.goBack();
        browser.goBack();
        browser.goForward();
        System.out.println("==========");
        browser.open("http://www.qq.com");
        browser.goForward();
        browser.goBack();
        browser.goForward();
        browser.goBack();
        browser.goBack();
        browser.goBack();
        browser.goBack();
        browser.checkCurrentPage();
    }

}
