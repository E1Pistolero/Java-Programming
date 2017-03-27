import java.util.Date;

public class Pair<Key, Value> {

    private final Key element0;
    private final Value element1;
    

    /**
     * Creates an instance of the pair given two objects
     * @param element0 first object in a pair
     * @param element1 second object in a pair
     */
    public Pair(Key element0, Value element1) {
        this.element0 = element0;
        this.element1 = element1;
    }

    public Key getFirst() {
        return element0;
    }

    public Value getSecond() {
        return element1;
    }
    
    @Override
    public String toString(){
        String result="[";
        
        result+=element0.toString()+" - ";
        result+=element1.toString();
        
        result+="]";
        
        return result;
    } 
    
    public static void main(String[] args) {
        Pair<String, Integer> pair1 = new Pair<String, Integer>("string",5);
        Pair<Date, Double> pair2 = new Pair<Date, Double>(new Date(),3.45);
        System.out.println(pair1);
        System.out.println(pair2);
    }

}