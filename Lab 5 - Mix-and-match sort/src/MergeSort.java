/*
* Author : Milica Knezevic, mknezevic2013@my.fit.edu
* Course : CSE 2010, Section 06, Fall 2014
* Project: lab#5, MergeSort
*/
/**
 * 
 * @author Knezevic Milica
 *
 */

// implement MergeSort using method definitions from the Sort interface
public class MergeSort implements Sort{
    /**
     * This method returns index of array and index is a half length of array
     * 
     * @param list is a array list with objects that we are comparing 
     * @param first is first index in array and it is 0 in beginning
     * @param to index of last element
     * @return index of variable in this case it is half of length of array
     */
    @Override
    public int split(Comparable[] list, int first, int last) {
        // TODO Auto-generated method stub
        int index= (last+first)/2; // index is half or array
        return index;
    }
    /**
     * This method returns element in case that we have just one element
     * or in case that we have more, it calls combine method that sorts elements
     * and also it recursively calls itself to sort to new arrays
     * one from first element to the middle and one from middle+1 to the end
     * 
     * @param list is a array list with objects that we are comparing 
     * @param from is first index in array and it is 0 in beginning
     * @param to is last  index in array 
     * @return nothing in case that we have just one element, because,
     * we then do not have to sort anything
     */
    @Override
    public void sort(Comparable[] list, int from, int to) {
        // TODO Auto-generated method stub
        // if there is only 1 or 0 elements
        if(from==to){
            return;
            
        }
        // else we call sort 
        int index= split(list, from, to);
        sort(list, from, index);
        sort(list, index+1, to);
        combine(list,from,index, to);
        
    }
    /**
     * This method sorts elements of arrays, one from first element to do middle
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
        //temporary array
        Comparable[] temp = new Comparable[list.length];
        for(int i=first; i<=last ;i++){
            temp[i] = list[i];
            
        }
        
        int i= first;
        int j=middle+1;
        int k=first;
        
        while (i <= middle && j <= last) {
             
            int coomparason = temp[i].compareTo(temp[j]);
         // comparing two leftmost elements
            if (coomparason == -1 || coomparason == 0) { //if less or equal     
                list[k] = temp[i];     // and copying lower one
                i++;
            }else { // if bigger 
                list[k] = temp[j];
                j++;
            }
            k++;
            }
        //going from i to the middle
        while (i <= middle) {
            list[k] = temp[i]; // copying elements from temp beck to list
            k++;
            i++;
            }

        
        
    }
    /** 
     * this method just send the start value of variables
     * from and to 
     * 
     * @param list is list of objects that we are sorting
     */
    @Override
    public void sort(Comparable[] list) {
        // TODO Auto-generated method stub
        int from = 0, to=list.length-1; // index of first and last element
        sort(list, from, to);
        
        
    }
    
}