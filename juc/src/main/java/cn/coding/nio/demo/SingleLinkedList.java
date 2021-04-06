package cn.coding.nio.demo;

public class SingleLinkedList {
    private int length;
    private Node head;
    public SingleLinkedList() {
        length = 0;
        head = null;
    }
    private class Node {
        private Object data;
        private Node next;
        public Node(Object data) {
            this.data = data;
        }
    }
    public Object addHead(Object obj) {
        Node newHead = new Node(obj);
        if (length == 0) {
            head = newHead;
        } else {
            newHead.next = head;
            head = newHead;

        }
        length++;
        return obj;
    }

    @Override
    public String toString() {
        if (length == 0) {
            return "空";
        }
        StringBuilder sb = new StringBuilder();
        Node node = head;
        while (node != null) {
            sb.append(node.data).append(",");
            node = node.next;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SingleLinkedList list = new SingleLinkedList();
        list.addHead(1);

        //System.out.println(list);
        list.addHead(2);
        System.out.println(list);
    }
}
