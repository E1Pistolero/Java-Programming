
import java.util.ArrayList;

public interface MapReduce<Key,Value> {

    public ArrayList<Pair<Key, Value>> map(ArrayList<Key> keys);
    
    public Pair<Key, Value> reduce(Pair<Key, ArrayList<Value>> list);
}