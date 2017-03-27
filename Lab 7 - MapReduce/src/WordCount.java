/*
* Author : Milica Knezevic, mknezevic2013@my.fit.edu
* Course : CSE 2010, Section 06, Fall 2014
* Project: lab 7, WordCount
*/
import java.util.ArrayList;
import java.util.HashSet;
/**
 * 
 * @author Knezevic Milica
 *
 */
public class WordCount {

    MapReduce<String, Integer> mapReduce; // needed for map(), reduce()
                                            // functions

    int numMappers; // number of computers for map function
    int numReducers; // number of computers for reduce function

    HashTable<String, Integer> mapHashTable; // HashTable with <word,cpu>
    HashTable<Integer, Pair<String, ArrayList<Integer>>> reduceHashTable; // HashTable
                                                                            // with
                                                                            // <cpu,<word,list(word
                                                                            // counts)>>
    /**
     * This method is constructor 
     * 
     * @param numMappers_ number of computers for map function
     * @param numReducers_ number of computers for reduce function
     * @param mapReduce_ generic list MapReduce<String, Integer>
     */
    public WordCount(int numMappers_, int numReducers_,
            MapReduce<String, Integer> mapReduce_) {
        // constructor
        numMappers = numMappers_;
        numReducers = numReducers_;
        mapReduce = mapReduce_;
        mapHashTable = new HashTable<String, Integer>(numMappers);
        reduceHashTable = new HashTable<Integer, Pair<String, ArrayList<Integer>>>(
                numReducers);
    }
    /**
     * This method divides string into list of chunk 
     * 
     * @param key generic type that has a pair
     * @return it returns stringList
     */
    public ArrayList<String> divide(String key) {
        // divide string into list of chunks( lines in this case ). Nothing to
        // implement here.
        String[] tokens = key.split("\n");
        ArrayList<String> stringList = new ArrayList<String>(tokens.length);

        for (int i = 0; i < tokens.length; i++) {
            stringList.add(tokens[i]);
        }

        return stringList;
    }
    /**
     * This method divides key into chunks and then it assigns each chunk to one
     * of map computers
     * 
     * @param key generic type that has a pair
     * @return mapJob 
     */
    public HashTable<Integer, ArrayList<String>> assignMapJobs(String key) {

        // 1. divide key into chunks using divide() function
        // 2. assign each chunk to one of map computers enumerated from 0 to
        // numMappers-1
        // starting from cpu=0;
        // for each line
        // split it into words using
        // String[] words = toProcess.replaceAll("[^a-zA-Z ]",
        // "").toLowerCase().split("\\s+");
        // add <cpu,words> into resulting HashMap
        //
        // increament cpu number using
        // cpu=(cpu+1)% numMappers;

        // return mapJobs;

        ArrayList<String> jobs = divide(key);
        HashTable<Integer, ArrayList<String>> mapJobs = new HashTable<Integer, ArrayList<String>>(
                this.numMappers);
        
        for(int i = 0, cpu = 0; i < jobs.size(); i++) {
            String[] words = jobs.get(i).replaceAll("[^a-zA-Z ]", "")
                    .toLowerCase().split("\\s+");  // split into words 
            ArrayList<String> list = new ArrayList<String>();
            for(int j = 0; j < words.length; j++) {
                list.add(words[j]); // adding to lists 
            }
         // increament cpu number
            mapJobs.insert(cpu,list);
            cpu = (cpu + 1) % numMappers;
        }
        
        return mapJobs;

    }
    /**
     * This method is processing mapJob
     * 
     * @param cpuVsWords array list with strings 
     */
    public void processMapJobs(HashTable<Integer, ArrayList<String>> cpuVsWords) {

        // process map jobs

        // for every unique cpu
        //
        // Note: mapReduce.map() should be used here
        //
        // for every word assigned to that cpu
        // add the pair <cpu,word> into mapHashTable
        //
        
        HashSet<Integer> cpus = cpuVsWords.getKeys();
        
        // for unique numbers
        for(Integer id : cpus) {
            while(cpuVsWords.contains(id)) {
                ArrayList<String> list = cpuVsWords.get(id);                            
                ArrayList<Pair<String, Integer>> mapJ = this.mapReduce
                        .map(list);
                for (Pair<String, Integer> pair : mapJ) {
                    mapHashTable.insert(pair.getFirst(), pair.getSecond());
                 // add the pair <cpu,word> into mapHashTable
                }
            }
        }
    }
    /**
     * This method is used for getting list of unique words in mapHashTables
     * 
     * @return cpuToReduceJob
     */
    public HashTable<Integer, Pair<String, ArrayList<Integer>>> assignReduceJobs() {
        

        // perform reduce step
        // starting from cpu=0;
        // for each word
        // for as many times as word appears in the mapHashTable
        // add <word,list(ints)> into result
        // add <cpu,pair(word,list(ints))> to the result
        // cpu=(cpu+1)% numReducers;
        //
        // example:
        // assume mapHashTable has the following key-value pairs
        // <"cat",1>
        // <"cat",5>
        // <"cat",2>
        // then the following should be added: <cpu ID, pair("cat",list(1,5,2))>
        
        HashSet<String> keys = this.mapHashTable.getKeys();     
        
        HashTable<Integer, Pair<String, ArrayList<Integer>>> cpuToReduceJob = new HashTable<Integer,
        Pair<String, ArrayList<Integer>>>(this.numReducers);
        System.out.println(mapHashTable);
        int cpu = 0;
        for (String word : keys) {
            ArrayList<Integer> counts=new ArrayList<Integer>();             
            while(mapHashTable.contains(word)) {                
                counts.add(mapHashTable.get(word)); // adding strings to the list     
            }           
            Pair<String,ArrayList<Integer>> pair = new Pair<String,ArrayList<Integer>>(word,counts);
            cpuToReduceJob.insert(cpu, pair);
            cpu = (cpu + 1) % numReducers;
        }       
        return cpuToReduceJob;
    }
    /**
     * This method pocess reduce jobs for every unique cpu
     * 
     * @param reduceJobs array list with integers
     * @return result ArrayList<Pair<String, Integer>> 
     */
    public ArrayList<Pair<String, Integer>> processReduceJobs(
            HashTable<Integer, Pair<String, ArrayList<Integer>>> reduceJobs) {

        // for every unique cpu
        //
        // Note: mapReduce.reduce() should be used here
        //
        // for every <word,list(ints)>
        // add mapReduce.reduce(<word,list(ints)>) to the result
        //
        ArrayList<Pair<String, Integer>> result = new ArrayList<Pair<String, Integer>>();
        HashSet<Integer> cpus = reduceJobs.getKeys();

        for (Integer id : cpus) {
            while (reduceJobs.contains(id)) {
                Pair<String, ArrayList<Integer>> list = reduceJobs.get(id);
                Pair<String, Integer> reduceJ = this.mapReduce
                        .reduce(list);              
                
                result.add(reduceJ); //mapReduce.reduce(<word,list(ints)>) to the result                
            }
        }
        return result; //ArrayList 
    }
    /**
     *  This is main method for testing 
     * @param args
     */
    public static void main(String[] args) {
        WordCountMapReduce wordCountMapReduce = new WordCountMapReduce();

        int nMappers = 3;
        int nReducers = 2;
        WordCount wordCount = new WordCount(nMappers, nReducers,
                wordCountMapReduce);

        String strToProcess = "one two three \n" + "three three one \n"
                + "two three one \n" + "one three one \n";

        // get the table with <cpu #ID, words to process>. Should be of size nMappers.
        HashTable<Integer, ArrayList<String>> mapJobs = wordCount
                .assignMapJobs(strToProcess);

        //System.out.println(mapJobs);
        
        wordCount.processMapJobs(mapJobs);

        // the table with <cpu #ID, <word,list(word counts)>>. Should be of size nReducers
        HashTable<Integer, Pair<String, ArrayList<Integer>>> reduceJobs = wordCount
                .assignReduceJobs();

        System.out.println(reduceJobs);
    }
}