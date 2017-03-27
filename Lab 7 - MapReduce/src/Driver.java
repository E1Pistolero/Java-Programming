
import java.util.ArrayList;

public class Driver {

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

        System.out.println(mapJobs);

        
        // processJobs (populate wordCount.mapHashTable())
        wordCount.processMapJobs(mapJobs);

        // the table with <cpu #ID, <word,list(word counts)>>. Should be of size nReducers
        HashTable<Integer, Pair<String, ArrayList<Integer>>> reduceJobs = wordCount
                .assignReduceJobs();

        System.out.println(reduceJobs);

        // list of <word,count>
        ArrayList<Pair<String, Integer>> counts = wordCount
                .processReduceJobs(reduceJobs);
        System.out.println("Counts:");
        System.out.println(counts);
    }

}