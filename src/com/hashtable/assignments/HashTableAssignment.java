/*
1.) Use Case 1 --> Ability to find frequency of words in a sentence i.e “To be or not to be”.
2.) Use Case 2 --> Ability to find frequency of words in a large paragraph phrase i.e
    “Paranoids are not paranoid because they are paranoid
     but because they keep putting themselves deliberately into paranoid avoidable situations”.
3.) Use Case 3 --> Ability to remove avoidable word from the phrase i.e
    “Paranoids are not paranoid because they are paranoid
     but because they keep putting themselves deliberately into paranoid avoidable situations”.
*/


package com.hashtable.assignments;

public class HashTableAssignment {

    //Nested class
    static class myMapNode {
        //Define key value pair for hashTable
        String key;
        int value;
        myMapNode next;

        //Declaring parameterized constructor
        myMapNode(String key, int value)
        {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    myMapNode head = null;
    myMapNode tail = null;

    //Adding to HashTable. (by means of LinkedList)
    public void addToHashTable(String data, int occurrence)
    {
        myMapNode newNode = new myMapNode(data, occurrence);
        if (head == null)
        {

            head = newNode;
        }
        else
        {
            tail.next = newNode;
            newNode.next = null;
        }
        tail = newNode;
    }

    //Printing the HashTable.
    public void printHashTable()
    {
        myMapNode current = head;
        if (head == null)
        {
            System.out.println("HashTable is empty");
        }
        else
        {
            System.out.println("Occurrence of word in Paragraph");
            System.out.println("Key && Occurrence");
            System.out.println("-----------------");
            while (current != null)
            {
                System.out.println(current.key + "    " + current.value);
                current = current.next;
            }
        }
    }

    //Delete Hash Table index containing a given key.
    public void removeFromHashTable(String keyValue)
    {
        myMapNode current = head;
        if (head == null)
        {
            System.out.println("HashTable is empty");
        }
        else
        {
            myMapNode previous = null;
            while (current != null && !current.key.equalsIgnoreCase(keyValue))
            {
                //store the value of current in previous
                previous = current;
                current = current.next;
            }
            if (current == null)
            {
                throw new RuntimeException("The key with the given value is not found!!");
            }
            previous.next = current.next;
        }
    }

    public static void main(String[] args) {

        HashTableAssignment hashTable = new HashTableAssignment();

        //Declaring the sentence
        String paragraph = "Paranoids are not paranoid because they are paranoid \n" +
                "but because they keep putting themselves deliberately into paranoid avoidable situations";

        //Create HashTable
        createHashTable(paragraph,hashTable);
        System.out.println("\nPrinting the original Hash Table below.");

        //Printing the HashTable
        hashTable.printHashTable();
        System.out.println();

        //Deleting from hashTable
        hashTable.removeFromHashTable("avoidable");
        System.out.println("Printing the Hash Table after removing the avoidable word from phrase");
        System.out.println();
        hashTable.printHashTable();
    }

    public static void createHashTable(String sentence, HashTableAssignment hashTable)
    {
        //Creating the string array
        String[] words = sentence.toLowerCase().split(" ");

        int[] temp = new int[words.length];
        int visited = -1;
        for (int i = 0; i < words.length; i++)
        {
            int occurrence = 1;
            for (int j = i + 1; j < words.length; j++)
            {
                if (words[i].equals(words[j]))
                {
                    occurrence++;
                    temp[j] = visited;
                }
            }
            if (temp[i] != visited)
                temp[i] = occurrence;
        }
        for (int i = 0; i < temp.length; i++)
        {
            if (temp[i] != visited)
            {
                hashTable.addToHashTable(words[i], temp[i]);
            }
        }
    }

}