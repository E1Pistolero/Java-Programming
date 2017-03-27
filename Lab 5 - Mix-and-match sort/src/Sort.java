@SuppressWarnings("rawtypes")
public interface Sort {
    // find an index to split the array
    int split(Comparable[] list,int first, int last);
    // sort the array from the index ’from’ to the index ’to’
    void sort(Comparable[] list, int from, int to);
    // combine elements after sorting algorithm (merge procedure from the merge sort
    // can be used in all algorithms)
    void combine(Comparable[] list, int first, int middle, int last);
    // convenience function which would make the first call to sort the array
    void sort(Comparable[] list);
}
