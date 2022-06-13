/**
 * class Node is uses the "next" Node representing a pointer to the next Node in sequence and String word
 * is the element which is an anagram or not anagram word.
 */
/*
    The Node class was created with the guidance of code used from:
    class Node from LinkedList.zip in lecture notes - week 3 on D2L. The differences are the "next" datamember is set
    to null by default and without the constructor and the setter methods are not called from the constructor. Conclusively,
    a generic Node class for a linked list.
 */
public class Node
{
    private Node next = null; // reference for traversing forward and default set to null
    private String word;

    public Node(String word, Node next)
    {
        this.word = word;
        this.next = next; // default reference is null so call this method to reference another node
    }
    public Node(String word){this.word = word;}

    public void setWord(String word){this.word = word;}
    public void setNext(Node next){this.next = next;}

    public String getWord(){return word;}
    public Node getNext(){return next;}

}
