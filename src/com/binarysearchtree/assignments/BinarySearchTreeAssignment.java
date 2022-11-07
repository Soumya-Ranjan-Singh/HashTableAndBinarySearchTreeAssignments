//Use Case 1 --> Ability to create a BST by adding 56 and then adding 30 & 70.
//Use Case 2 --> Ability to create a BST, check if all are added and calculate size and height of the BST.
//Use Case 3 --> Ability to search 63 in the previously created BST by means of recursive method.

package com.binarysearchtree.assignments;

import java.util.Arrays;

public class BinarySearchTreeAssignment <T extends Comparable<T>>{

    //Nested class
    static class MyBinaryNode<T> {

        //Define variables for Binary Search Tree
        T data;
        MyBinaryNode left;
        MyBinaryNode right;

        //Declaring parameterized constructor
        MyBinaryNode(T value)
        {
            this.data = value;
            this.left = null;
            this.right = null;
        }
    }

    Integer[] passValue = {56,30,70,22,40,11,3,16,60,95,65,63,67};

    MyBinaryNode root = null;

    //Adding to Binary Search Tree. (by means of LinkedList)
    public void addToBST(T data)
    {
        this.root = this.insertRec(this.root, data);
    }

    //Utility function to insert a new node with given data in BST.
    public MyBinaryNode insertRec(MyBinaryNode node, T data)
    {
        if (node == null)
        {
            this.root = new MyBinaryNode(data);
            return this.root;
        }

        int comparator = data.compareTo((T) node.data);
        /* Otherwise, recursively adding down the tree */
        if (comparator <= 0)
        {
            node.left = this.insertRec(node.left, data);
        }
        else
        {
            node.right = this.insertRec(node.right, data);
        }
        return node;
    }

    //Printing the Binary Search Tree.
    public void traversal()
    {
        inorderUtil(this.root);
    }
    // Utility function for inorder traversal of tree.
    public void inorderUtil(MyBinaryNode node)
    {
        if (node == null)
        {
            return;
        }

        inorderUtil(node.left);
        System.out.print(node.data + "   ");
        inorderUtil(node.right);
    }

    //Calculating Height of BST
    public int calculateHeight(MyBinaryNode node)
    {
        if (node == null)
        {
            return 0;
        }
        else
        {
            //Computing depth of each subtree
            int leftDepth = calculateHeight(node.left);
            int rightDepth = calculateHeight(node.right);

            if (leftDepth > rightDepth)
            {
                return (leftDepth + 1);
            }
            else
            {
                return (rightDepth + 1);
            }
        }
    }

    //Calculating the size of BST
    public void calculateSize()
    {
        System.out.println("The size of the Binary Search Tree is : "+size(root));
    }
    //Compute the size recursively
    public int size(MyBinaryNode node)
    {
        if (node == null)
            return 0;
        else
            return (size(node.left) + 1 + size(node.right));
    }

    //Check all the elements from BST
    public boolean ifNodeExists( MyBinaryNode node, T key)
    {
        if (node == null)
            return false;

        if (node.data == key)
            return true;

        // then recur on left subtree /
        boolean res1 = ifNodeExists(node.left, key);

        // node found, no need to look further
        if(res1)
            return true;

        // node is not found in left,
        // so recur on right subtree /
        boolean res2 = ifNodeExists(node.right, key);

        return res2;
    }

    public static void main(String[] args) {
        BinarySearchTreeAssignment tree = new BinarySearchTreeAssignment();
        System.out.println();

        //Adding values to BST
        for (int i = 0; i < tree.passValue.length; i++)
        {
            tree.addToBST(tree.passValue[i]);
        }

        //Printing all the values of BST
        tree.traversal();
        System.out.println();

        //Calculating the size of BST
        tree.calculateSize();

        //Calculating the height of BST
        System.out.println("The height of the BST is : "+tree.calculateHeight(tree.root));

        //Checking if 63 is present in BST
        boolean flag = tree.ifNodeExists(tree.root,63);
        System.out.println("The value 63 is present in the BST : "+flag);
    }
}
