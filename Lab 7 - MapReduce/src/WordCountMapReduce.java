import java.util.ArrayList;
import java.util.Iterator;

public class WordCountMapReduce implements MapReduce<String, Integer> {

    @Override
    public ArrayList<Pair<String, Integer>> map(ArrayList<String> keys) {
        // map function for word count. For every word in the list it will add the pair <word,1> to the
        // result.
        
        ArrayList<Pair<String, Integer>> pairs = new ArrayList<Pair<String, Integer>>(keys.size());
        
        for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
            String value =iterator.next();
            pairs.add(new Pair<String, Integer>(value, 1));  // <word,1>
        }
        
        return pairs;
    }

    @Override
    public Pair<String, Integer> reduce(Pair<String, ArrayList<Integer>> list) {
        // reduce function for word count. For every <word,list of integers> it will output <word,sum(list of integers)>
        ArrayList<Integer> intList=list.getSecond();
        
        Integer sum=0;
        
        for (Iterator<Integer> iterator = intList.iterator(); iterator.hasNext();) {
            sum +=iterator.next();
        }
        
        Pair<String, Integer> pair = new Pair<String, Integer>(list.getFirst(),sum);
        
        return pair;
    }

}