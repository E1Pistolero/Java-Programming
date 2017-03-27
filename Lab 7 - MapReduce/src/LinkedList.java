/*
* Author : Milica Knezevic, mknezevic2013@my.fit.edu
* Course : CSE 2010, Section 06, Fall 2014
* Project: lab 7, LinkedList
*/
import java.util.HashSet;
/**
 * 
 * @author Knezevic Milica
 *
 *This class implements Linked List
 *
 * @param <Key> generic type that has a pair
 * @param <Value> generic type 
 */
public class LinkedList<Key, Value> {

    private int N; // number of key-value pairs
    private Node first; // the linked list of key-value pairs
   
    // a helper linked list data type
    /**
     * 
     * 
     *
     */
    private class Node {

        private final Key key;
        private Value val;
        private Node next; // pointer to the next Node
        
        
        
        /**
         * This method is constructor
         * 
         * @param key generic type that has a pair
         * @param val value of key
         * @param next next node generic type for next key
         */
        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }
    /**
     * This method adds new elements to the linked list
     * 
     * @param key generic type that has a pair
     * @param val value of key
     */
    public void add(Key key, Value val) {
        // add new Key-Value pair to the linked list
        if (first == null) {
            first = new Node(key, val, null);
        } else {
            first = new Node(key, val, first);
        }
        N++;
        //System.out.print(key.toString());
    }
    /**
     * This method just returns size of list (number of pairs)
     * 
     * @return number of key-value pairs
     */
    // return number of key-value pairs
    public int size() {
        return N;
    }
    /**
     * This method checks is table empty and returns boolean value
     * 
     * @return boolean true or false if table is empty 
     */
    // is the symbol table empty?
    public boolean isEmpty() {
        return size() == 0;
    }
    /**
     * This is boolean method that returns true if key object is in the list
     * and false if the key object is not in the list 
     * 
     * @param key generic type that has a pair
     * @return true or false depending if key is in list or not
     */
    public boolean contains(Key key) {
        Node curr = first; // pointing first node
        // checking if key is in the list
        while (curr != null) {
            if (curr.key.toString().equals(key.toString())) {
                return true; //if element is in list
            } else {
                curr = curr.next;
            }
        }
        return false; // if key is not there
    }
    /**
     * This method returns the value in place associated with key if it exist
     * or if it does not exist, then returns null
     * 
     * @param key
     * @return the value of key or null
     */
    public Value get(Key key) {
        // getting value of key or if not then returns null
        Node curr = first;
        while (curr != null) {
            if (curr.key.toString().equals(key.toString())) {
                Value v = curr.val;
                delete(key); // deleting key
                return v; // value assoc. with key
            } else {
                curr = curr.next;
            }
        }
        return null; // there is no vale
    }
    /**
     * This method adds a key value pairs and replaces old key value pairs if key
     * already exists in list
     * 
     * @param key
     * @param val
     */
    public void put(Key key, Value val) {
        // adding  a key-value pair
        Node curr = first;
        while (curr != null) {
            if (curr.key.toString().equals(key.toString())) {
                curr.val = val;
                break;
            //replacing old key-value pair if key is already exists
            } else {
                curr = curr.next;
            }
        }
    }
    /**
     *  Returning set of unique keys
     *  
     * @return HashSet list
     */
    public HashSet<Key> getKeys() {
     
        Node firstNode = first;

        HashSet<Key> s = new HashSet<Key>();

        while (firstNode != null) {
            s.add(firstNode.key); // adding to HashSet list
            firstNode = firstNode.next; // going to next node
        }
        return s; // returning HashSet
    }
    /**
     * This method deletes elements 
     * 
     * @param key generic type that has a pair
     */
    public void delete(Key key) {
        // remove key-value pair with given key
        Node curr = first, last = null;
        while(curr != null) {
            if(curr.key.toString().equals(key.toString())) {
                if(last != null) {
                    last.next = curr.next;
                    N--;
                    break;
                } else {
                    first = curr.next;
                    N--;
                    break;
                }
            }
            last = curr;
            curr = curr.next;
        }
    }
    /**
     * This is method for returning string that represents the list
     * 
     * @return list representation as string 
     */
    @Override
    public String toString() {
        // return string representation of the list. Nothing to implement here.

        Node var = this.first;
        String result = "";
        while (var != null) {
            result += var.key.toString() + "\t \t " + var.val + "\n";
            var = var.next;
        }

        return result;
    }
    /**
     * This is main method for testing
     * 
     * @param args
     */
    public static void main(String args[]) {
        LinkedList<String, Integer> list = new LinkedList<String, Integer>();
        list.add("asd", 6);
        list.add("asd", 5);
        list.add("test", 7);
        list.add("omg", 9);  
        list.add("omg", 4); 
        list.add("omg", 3); 
        
        System.out.println(list); 
        list.delete("asd");
        System.out.println(list); 
             
    }
   
}