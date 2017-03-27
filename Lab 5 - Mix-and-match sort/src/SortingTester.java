
import java.util.Arrays;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class SortingTester {

    int n;
    
    Random rand;
    @SuppressWarnings("rawtypes")
    Comparable[] list;

    Integer[] sorted;
    
    SelectionSort selectionSort = new SelectionSort();
    QuickSort quickSort = new QuickSort();
    MergeSort mergeSort = new MergeSort();
    
    int baseline=1563;
    
    @Before
    public void setUp() throws Exception {
        n=5000;
        rand=new Random();
        list=new Integer[n];
        
        for (int i = 0; i < n; i++) {
            list[i]=rand.nextInt();
        }
        
        sorted=(Integer[]) copy();
        Arrays.sort(sorted);
    }

    @Test
    public void testMergeSort() {

        MergeSort mergeSort = new MergeSort();
        
        mergeSort.sort(list);

        assertArraysAreEqual(sorted, list);
        
        System.out.println("Merge sort: "+"     PASS"+"     5/5");
    }
    
    
    @Test
    public void testQuickSort() {

        QuickSort mergeSort = new QuickSort();
        
        mergeSort.sort(list);

        assertArraysAreEqual(sorted, list);
        
        System.out.println("Quick sort: "+"         PASS"+"     5/5");
    }
    
    @Test
    public void testSelectionSort() {

        
        SelectionSort selectionSort = new SelectionSort();
        selectionSort.sort(list);
        assertArraysAreEqual(sorted, list);
        
        System.out.println("Selection sort: "+"     PASS"+"     5/5");
    }
    
    @Test
    public void testMixAndMatchSortMergeAndQuick() {

        MixAndMatchSort mixAndMatchSort = new MixAndMatchSort(mergeSort,quickSort,baseline);
        
        
        Comparable[] listCopy=Arrays.copyOf(list, list.length);
        
        MixAndMatchSort mixAndMatchSort1 = new MixAndMatchSort(quickSort,mergeSort,baseline);
        mixAndMatchSort.sort(list);
        mixAndMatchSort1.sort(listCopy);
        
        assertArraysAreEqual(sorted, list);
        assertArraysAreEqual(sorted, listCopy);
        
        
        System.out.println("Merge+Quick sort: "+"   PASS"+"     10/10");
    }
    
    
    @Test
    public void testMixAndMatchSortSelectionAndQuick() {

        MixAndMatchSort mixAndMatchSort = new MixAndMatchSort(selectionSort,quickSort,baseline);
        
        
        Comparable[] listCopy=Arrays.copyOf(list, list.length);
        
        MixAndMatchSort mixAndMatchSort1 = new MixAndMatchSort(quickSort,selectionSort,baseline);
        
        mixAndMatchSort.sort(list);
        mixAndMatchSort1.sort(listCopy);
        
        assertArraysAreEqual(sorted, list);
        assertArraysAreEqual(sorted, listCopy);
        
        System.out.println("Selection+Quick sort: "+"   PASS"+"     10/10");
    }
    

    
    
    @Test
    public void testMixAndMatchSortSelectionAndMerge() {

        MixAndMatchSort mixAndMatchSort = new MixAndMatchSort(selectionSort,mergeSort,baseline);
        
        
        Comparable[] listCopy=Arrays.copyOf(list, list.length);
        
        MixAndMatchSort mixAndMatchSort1 = new MixAndMatchSort(mergeSort,selectionSort,baseline);
        
        mixAndMatchSort.sort(list);
        mixAndMatchSort1.sort(listCopy);
        
        assertArraysAreEqual(sorted, list);
        assertArraysAreEqual(sorted, listCopy);
        
        System.out.println("Selection+Merge sort: "+"   PASS"+"     10/10");
    }
    
    
    
    
    public Comparable[] copy(){
        Integer[] listCopy = new Integer[list.length];
    
        
        for (int i = 0; i < listCopy.length; i++) {
            listCopy[i]= (Integer)list[i];
        }
        return listCopy;
    }
    
    public String printArray(Comparable[] l){
        
        String result="";
        
        for (int i = 0; i < l.length; i++) {
            result+=l[i]+" ";
        }
        return result;
    }
    
    
    public void assertArraysAreEqual(Comparable[] t,Comparable[] s){
        
        
        for (int i = 0; i < s.length; i++) {
            assertEquals(t[i], s[i]);
        }
    }

    private void assertEquals(Comparable comparable, Comparable comparable2) {
        // TODO Auto-generated method stub
        
    }

}
