package com.example.mypackage;


import org.w3c.dom.Node;

public class myLinkedList {
   public Node head;

    private class Node {
        public int num;
        public Node next;

        public Node(int num,Node next){
            this.num=num;
            this.next=next;
        }
    }

    public myLinkedList() {
        this.head = null;
    }

    public void add(int num) {
        if (head == null) {
            head = new Node(num, null);
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new Node(num, null);
        }

    }

    public void insert(int num) {
        Node current;
        Node newNode = new Node(num, null);

        if (head == null || head.num >= newNode.num ) {
            newNode.next = head;
            head = newNode;
        } else {
            current = head;
            while (current.next != null && current.next.num < num) {
                current = current.next;
            }

            newNode.next = current.next;
            current.next = newNode;
        }
    }


    @Override
    public String  toString() {
        Node temp = head;
        int start = 0;
        String result = "";
        while (temp != null) {
            if (start != 0) {
                result += ",";
            }
            result += temp.num;
            temp = temp.next;
            start++;
        }
        return result;

    }
}
