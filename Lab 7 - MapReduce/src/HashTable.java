
/*
* Author : Skyler Knezevic, mknezevic2013@my.fit.edu
* Course : CSE 2010, Section 06, Fall 2014
* Project: lab 7, HashTable
*/
import java.util.HashSet;
/**
 * 
 * This class is for implementing Hash Tables
 * 
 * @author Knezevic Milica
 *
 * @param <Key> generic type 
 * @param <Value> generic type for value of key
 */
public class HashTable<Key, Value> {

    private int N;                             // size of the hashtable
    private final int M;                             // number of linked lists
    private final LinkedList<Key, Value>[] lists;    // Hashtable with collision resolution by chaining
    
    private final HashSet<Key> uniqueKeys;
    
    /**
     * This method implements number of linked lists and it stores 
     * linked list elements
     * 
     * @param M is number of linked lists 
     */
    @SuppressWarnings("unchecked")
    public HashTable(int M) {
        // M is the number of linked lists the table should be initialized with
        this.M = M;
        lists = new LinkedList[M];
        for(int i = 0; i < M; i++) {
            lists[i] = new LinkedList<Key, Value>();
        }
        uniqueKeys = new HashSet<Key>();
    }
    /**
     * This method is used for hushing
     * 
     * @param key generic type that has a pair
     * @return hashValue
     */
    private int hash(Key key) {
        // multiplication method for hashing. Nothing to implement here.
        String strKey = key.toString();
        int intKey = 0;
        int strLength = strKey.length();

        final int RADIX=128; // See CLSR book for details
        
        for (int i =0; i <strLength ; i++) {
            intKey = (int)(Math.pow(RADIX, (strLength-1)-i)) * strKey.charAt(i) + intKey;
        }
        double A = (Math.sqrt(5) - 1) / 2;
       
        double res = intKey * A;
        res = res - Math.floor(res);
        int hashValue = (int) Math.floor(M * res);

        return hashValue;
    }
    /**
     * 
     * @param key generic type that has a pair
     * @return value of given key
     */
    public Value get(Key key) {     
        // return Value, given key
        return lists[hash(key)].get(key);        
    }
    /**
     * This method returns true if value exists and false if it does not exist
     * 
     * @param key
     * @return boolean value true or false
     */
    public boolean contains(Key key){  
        // true if key exists in the table, false otherwise
        return lists[hash(key)].contains(key);        
    }
    /**
     * This method inserts value in hashtables
     * 
     * @param key generic type that has a pair
     * @param val
     */
    public void insert(Key key, Value val) {
        // insert Key-Value pair into hashtable
        //System.out.print(hash(key));
        lists[hash(key)].add(key, val);
        if(!uniqueKeys.contains(key)) {
            uniqueKeys.add(key);
        }
    }
    /**
     * This method deletes key and in case that there are more then one key
     * then it deletes just the first occurrence of key
     * 
     * @param key generic type that has a pair
     */
    public void delete(Key key) {
        // delete key from the hashtable. Should only delete the first occurence of the key if there is more than one.
        int hash;
        if(lists[hash = hash(key)].contains(key)) {
            lists[hash].delete(key);
        }
    }

    /**
     * This method returns set of unique elements in HashTable
     * 
     * @return uniqueKeys - unique keys in HashTable
     */
    public HashSet<Key> getKeys(){
        // return HashSet of unique keys in the HashTable
        return uniqueKeys;
    }
    /**
     * This method returns string that represents Hash Table
     * 
     * @return
     */
    @Override
    public String toString() {
        String result = "";
        for(int i = 0; i < M; i++) {
            result +="\nList #" + (i + 1) + "\n-----------------\n" + lists[i].toString();          
        }
        return result;
        // return string representation of the HashTable
    }
    /**
     * This is main method for testing 
     * 
     * @param args
     */
    public static void main(String[] args) {
        HashTable<String, Integer> ht = new HashTable<String, Integer>(3);
        
        ht.insert("asd", 4);
        ht.insert("bff", 6);
        ht.insert("asodoos", 10);
        ht.insert("asd", 4);
        ht.insert("asdwef", 42433);
        ht.insert("bff", 424246);
        ht.insert("bff", 6);
        ht.insert("aoos", 10);
        
        System.out.println(ht.toString()); 
        
    }

}
