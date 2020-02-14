package com.example.mypackage;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
	// write your code here
        myLinkedList head = new myLinkedList();
        head.insert(5);
        head.insert(7);
        head.insert(11);
        head.insert(20);
        head.insert(27);
        head.insert(36);
        head.insert(36);
        head.insert(54);
        head.insert(35);
        head.insert(33);
        head.insert(0);
        System.out.println(head.toString());



        myBinaryTree bst = new myBinaryTree();
        bst.insert(5);
        bst.insert(7);
        bst.insert(11);
        bst.insert(20);
        bst.insert(27);
        bst.insert(36);
        bst.insert(36);
        bst.insert(54);
        bst.insert(35);
        bst.insert(33);
        bst.insert(0);
//        System.out.println(bst.inOrder(bst.root));
        System.out.println(bst.toString());

    }
}
