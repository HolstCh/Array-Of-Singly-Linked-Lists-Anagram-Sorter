/**
 * TheLinkedList is an implementation of a linked list and represents a category which is referenced as a sorted letter word
 * or a not anagram word that correspond to the ArrayLists in the FindAnagrams class. They also correspond to every element in
 * the "categoriesList" ArrayList. The implementation uses "head" and "tail" Nodes to mark the beginning and end of the linked list.
 * It then uses "before" and "after" pointers for iterating through Nodes and inserting a Node to their respective category.
 * The category is represented by a single linked list object in the array of TheLinkedList objects, "listB".
 */
/*
    TheLinkedList class was created with the guidance of code used from:
    class SinglyLinkedList from LinkedList.zip in lecture notes - week 3 on D2L. The ideas are similar but a different
    implementation is created by only using "head" and "tail" pointers for marking and using "before" and "after"
    pointers for iterating and inserting.
 */
public class TheLinkedList
{
    private Node before = null; // reference to assist in traversing
    private Node after = null; // reference to assist in traversing
    private Node head = null; // reference to first node
    private Node tail = null; // reference to last node
    private int length = 0; // length of list

    public TheLinkedList(){} // default ctor

    public Node getHead(){return head;} // getter methods
    public Node getTail(){return tail;}
    public int getLength(){return length;}

    public void setHead(Node head){this.head = head;} // setter methods
    public void setTail(Node tail){this.tail = tail;}
    public void setLength(int length){this.length = length;}

    /**
     * @param word is a String that represents the element of a Node object. The method insertLast() first checks if the
     * head pointer is null and then inserts the first node if it is. If not null, the method takes advantage of the "before"
     * Node and points to the "tail" node. Then, sets the new node to be last and the "tail" node to point at the end node again.
     * Lastly, sets last Node's "next" datamember to null, resets "before" Node to null for further use, and then increases length.
     */
    /*
        insertLast() was created with the guidance of code used from:
        methods addFirst() and addLast() from SinglyLinkedList.java within LinkedList.zip from lecture - week 3 on D2L
        This code is based on ideas within those two methods except using a different implementation
     */
    public void insertLast(String word)
    {
        Node newNode = new Node(word); // next Node data member default reference is null
        if(head == null) // insert in first position if no other nodes
        {
            head = newNode; // head points to new node
            tail = newNode; // tail points to new node
            length++;
        }
        else if(length > 0) // if there exists at least one node
        {
            before = tail; // reference last node that is marked by tail pointer
            before.setNext(newNode); // set next Node to the new last Node
            tail = newNode; // assign newNode as last node
            tail.setNext(null); // set tail Node's next Node to null
            before = null; // reset to null for further use
            length++;
        }
    }

    /**
     * @param word is a String that represents the element of a Node object. The method insertFirst() first checks if the
     * head node is null and then inserts the first node if it is. If not null, the method takes advantage of the "after"
     * Node which points to the first node. Then, sets the new Node's "next" Node to be the second Node (originally the first)
     * and the head node to point at the first node again. Lastly, it sets the "next" datamember of "head" Node to be the
     * newly second node, resets "after" to null for further use, and increases length.
     */
     /*
        insertFirst() was created with the guidance of code used from:
        methods addFirst() and addLast() from SinglyLinkedList.java within LinkedList.zip from lecture - week 3 on D2L
        The code is based on the ideas within those two methods except using a different implementation.
     */
    public void insertFirst(String word)
    {
        if(head == null) // insert in first position if no other nodes
        {
            Node newNode = new Node(word); // next data member default reference is null
            head = newNode; // head points to new node
            tail = newNode; // tail points to new node
            length++;
        }
        else if(length > 0) // if there exists at least one node
        {
            after = head; // reference first node that is marked by head pointer
            Node newNode = new Node(word, after); // set next Node of newNode to be the second node to replace first position
            head = newNode; // set first node to be newNode
            head.setNext(newNode.getNext()); // set next Node of head Node to be the same as next Node of after Node
            after = null; // reset to null for further use
            length++;
        }
    }

    /**
     * @return line is a String that corresponds to one line representing all elements within TheLinkedList object. The "before"
     * Node is used as a pointer that iterates through each Node object until the tail node. After, the last word of the
     * tail node is added to the String
     */
    /*
        The for loop was created with the guidance of code used from:
        method show() from SinglyLinkedList.java within LinkedList.zip from lecture - week 3 on D2L.
        The technique of traversing using the getNext() method inside the while loop inside show() is the same as how the
        for loop iterates.
     */
    public String printAnagrams()
    {
        String line = "";
        if(length != 0)
        {
            for(before = head; before != tail; before = before.getNext()) // traverse with "before" pointer until tail pointer
            {
                line += (before.getWord() + " "); // add each word with one space between to "line"
            }
            line += before.getWord(); // last element must be added to "line" after loop
        }
    before = null; // reset "before" for further use
    return line;
    }
}
