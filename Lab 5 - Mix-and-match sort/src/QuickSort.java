/*
* Author : Milica Knezevic, mknezevic2013@my.fit.edu
* Course : CSE 2010, Section 06, Fall 2014
* Project: lab#5, QuickSort
*/
/**
 * 
 * @author Knezevic Milica
 *
 */
public class QuickSort implements Sort{
    // implement QuickSort using method definitions from the Sort interface
    /**
     * This method sorts objects in array by taking one element until we place
     * all other element in front of it are smaller 
     * 
     * @param list is a array list with objects that we are comparing 
     * @param first is first index in array and it is 0 in beginning
     * @return index of variable in this case it is half of length of array
     */
    @Override
    public int split(Comparable[] list, int first, int last) {
        // TODO Auto-generated method stub
        
        int lastS = first;
        for(int i = first + 1; i <= last; i++) {
            // if less or equal
            if(list[i].compareTo(list[first]) == -1 || list[i].compareTo(list[first]) == 0){
                lastS++;
                // swaping elements
                Comparable temp = list[lastS];
                list[lastS] = list[i];
                list[i] = temp;
            }
        }
        
        Comparable temp = list[first];
        list[first] = list[lastS];
        list[lastS] = temp;
        return lastS;
        
     
    }
    /**
     * This method checks if there is only one element or none and in that
     * case it returns, because we do not have anything to sort, but in case
     * there are more elements it recursively calls sort that is divided in to parts
     * form beginning to the index where element that was placed and from there to end
     * 
     * @param list is a array list with objects that we are comparing 
     * @param from is first index in array and it is 0 in beginning
     * @return to of variable in this case it is half of length of array
     */
    @Override
    public void sort(Comparable[] list, int from, int to) {
     // if there is only 1 or 0 elements
        if (from >= to){
            return;
        // else we call sort     
        }else{
            int index = split(list, from, to);
            sort(list, from, index);
            sort(list, index+1, to); 

        }

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
        Comparable[] temp = new Comparable[list.length];
        for(int i=first; i<=last ;i++){
            temp[i] = list[i];
            
        }
        int i= first;
        int j=middle+1;
        int k=first;
        
        while (i <= middle && j <= last) {
             
            int coomparason = temp[i].compareTo(temp[j]);
            if (coomparason == -1 || coomparason == 0) {  // comparing two leftmost elements
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
        int from = 0, to=list.length-1; // index of first and last element
        sort(list,  from ,  to);  

    }

}