/*
* Author : Milica Knezevic, mknezevic2013@my.fit.edu
* Course : CSE 2010, Section 06, Fall 2014
* Project: lab#5, SelectionSort
*/
/**
 * 
 * @author Knezevic Milica
 *
 */
public class SelectionSort implements Sort{
    // implement SelectionSort using method definitions from the Sort interface
    @Override
    /**
     *This method returns index of the next element with which we are 
     *comparing first element (in begining)
     * 
     * @param list is a array list with objects that we are comparing 
     * @param first is first index in array and it is 0 in beginning
     * @param last is index of last element in array
     * @return index of variable in this index of first element + 1
     */
    public int split(Comparable[] list, int first, int last) {
        // TODO Auto-generated method stub
        int index= first+1;
        return index;
    }
    /**
     * This method searches for smallest element going through whole array
     * and then switches places between elements, then it searches for next 
     * element until whole array is sorted
     * 
     * @param list is a array list with objects that we are comparing 
     * @param from is first index in array and it is 0 in beginning
     * @param to last index of element
     * @return if there is only one element or 0 it returns
     */
    @Override
    public void sort(Comparable[] list, int from, int to) {
        // TODO Auto-generated method stub
        int index= split(list, from, to);
     // if there is only 1 or 0 elements
        if(from >= to){
            return;
         // else we call sort 
        }else{
        
        Comparable temp;
        for (int i = 0; i <=to; i++){
            // Find the element with the minimum value and its index
            int minIndex=i;
             for (int j = i + 1; j <= to; j++){
                 
                 int comparison = list[j].compareTo(list[minIndex]);
                 
                 if (comparison == -1){     // if less then do swap
                        temp = list[i];
                        list[i] = list[j];
                        list[j] = temp;
                        
                        
                 }
             }
        
        
        }
        
        
        }
        sort(list, index, to);
    }
    /**
     * 
     * This method is from MergeSort
     * and it sorts elements of arrays, one from first element to do middle
     * and second from middle+1 to end, and then combines them in one array
     * 
     * @param list is a array list with objects that we are comparing 
     * @param first is first index in array and it is 0 in beginning
     * @param middle is middle index of array index  
     * @param last is last  index in array 
     *
     */
    @Override
    public void combine(Comparable[] list, int first, int middle, int last) {
        // TODO Auto-generated method stub
        // TODO Auto-generated method stub
        Comparable[] temp = new Comparable[list.length];
        for(int i=first; i<=last ;i++){
            temp[i] = list[i];
            
        }
        int i= first;
        int j=middle+1;
        int k=first;
        
        while (i <= middle && j <= last) {
             
            int coomparason = temp[i].compareTo(temp[j]);
            if (coomparason == -1 || coomparason == 0) {      // comparing two leftmost elements
                list[k] = temp[i];     // and copying lower one
                i++;
            }else {
                list[k] = temp[j];
                j++;
            }
            k++;
       }
       while (i <= middle) {
            list[k] = temp[i];
            k++;
            i++;
            }
 
         
    }
    /**
     * this method just send the start value of variables
     * from and to 
     * 
     * @param list is list of objects
     */
    @Override
    public void sort(Comparable[] list) {
        // TODO Auto-generated method stub
        int from =0, to=list.length-1; // index of first and last element
        sort(list, from, to);
        
    }

}