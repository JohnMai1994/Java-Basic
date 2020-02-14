package com.example.mypackage;

public class myBinaryTree {
    public Node root;

    // Set Node
    private class Node {
        public int num;
        public Node left, right;

        public Node(int num){
            this.num = num;
            this.left = null;
            this.right = null;
        }

    }

    public myBinaryTree() {
        this.root = null;
    }

    public void insert(int num) {
        this.root = insertNode(this.root, num);
    }

    private Node insertNode(Node temp, int num) {
        if (temp ==null) {
            return new Node(num);
        }

        if (num < temp.num) {
            temp.left = insertNode(temp.left, num);
        } else if (num > temp.num) {
            temp.right = insertNode(temp.right, num);
        }
        return temp;
    }


    public String inOrder(Node node){
        String result = "";
        if(node!= null) {
            result += inOrder(node.left);
            result += node.num;
                result += ",";
            result +=inOrder(node.right);
        }
        return result;

    }


    @Override
    public String toString() {
        String result = inOrder(root);
        result = result.substring(0, result.length()-1);

        return result;
    }
}
