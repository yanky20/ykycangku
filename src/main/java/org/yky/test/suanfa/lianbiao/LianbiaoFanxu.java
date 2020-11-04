package org.yky.test.suanfa.lianbiao;

public class LianbiaoFanxu {
    public static void main(String[] args){
        Node a = new Node(null, 3);
        Node b = new Node(a, 2);
        Node b1 = new Node(b, 12);
        Node b2 = new Node(b1, 22);
        Node b3 = new Node(b2, 32);
        Node c = new Node(b3, 1);

        Node shaobing = new Node(c, 0);

        Node nd = shaobing;
        printNode(nd);
        System.out.println("1111");
        nd = resetNode(nd);

        printNode(nd);

    }

    private static void printNode(Node nd) {
        if (nd.next != null)
            do {
                nd = nd.next;
                System.out.println(nd.value);
            } while (nd.next != null);
    }

    private static Node resetNode(Node nd) {
        Node dddd = null;
        if (nd.next != null) {
            while (nd.next != null) {
                Node temp = dddd;
                dddd = nd.next;
                Node next = dddd.next;
                dddd.next = temp;
                nd.next = next;
            }
        }
        Node shaobing = new Node();
        shaobing.next = dddd;
        return shaobing;
    }

    public static class Node {
        public Node next;
        public int value;

        Node(Node next, int value) {
            this.next = next;
            this.value = value;
        }

        Node() {
        }
    }
}
