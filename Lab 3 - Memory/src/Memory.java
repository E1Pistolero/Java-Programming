// Memory.java
import java.util.Arrays;
/**
 * 
 * @author Knezevic Milica
 * @lab Lab3
 * @name Memory
 *
 */
public class Memory {
 public byte[] memoryArray;
 public boolean[] occupiedMemory; // boolean array representing which bytes are occupied
 public int indexOfFreeMemory=0; // index where next object will be stored in the memory
 public int pointerend=0;  // pointer of last byte in segment
 
// constructor
/**
 * 
 * @param size represents number of element in array 
 */
 public Memory(int size) {
     memoryArray = new  byte [size];
     occupiedMemory = new boolean[size];
              
     }
/**
 * 
 * @param num number of elements
 * @param cls class type
 * @return next free place in the memory
 */
 int allocate(int num,Class<?> cls){   
   //number of bytes per each array element 
    int NumberOfBytes= TypeConverter.numberOfBytesPerClass(cls); 
    //next place where is pointed
    pointerend=indexOfFreeMemory+num*NumberOfBytes;
    return indexOfFreeMemory; //next place with free memory
     }
 /**
  * 
  * @param a object array
  * @return length of object array and class type
  */
 // convenience function - nothing to implement here
 int allocate(Object[] a){
     return allocate(a.length, a[0].getClass());
     }
 /**
  * 
  * @param pointer pointing on place in memory
  * @param objs for getting elements
  * @return is there enough space in memory (T or F)
  */
 boolean copy(int pointer,Object[] objs){
    Class<?> cls=objs[0].getClass(); // getting Class type
    boolean fit = false;        //   available space set to false
    int NumberOfBytes= TypeConverter.numberOfBytesPerClass(cls); //number of bytes per each array element 
    pointerend=indexOfFreeMemory+objs.length*NumberOfBytes;
        // if there is no enough space
    if (memoryArray.length < pointerend) {
        fit = false; //return false
       
      // if there is enough space          
    } else {
        for(int i=0; i<objs.length; i++){
            byte[] tempbytes = TypeConverter.toByteArray(objs[i]);   // getting bytes
            for (int j=0; j< tempbytes.length; j++) {
                memoryArray[indexOfFreeMemory]=tempbytes[j]; //saving new object in memory array
                occupiedMemory[indexOfFreeMemory]=true; // mark part of memory that is occupied
                indexOfFreeMemory++;    // moving pointer of free memory after each copied byte
             }
                
            
           }
         
        fit = true;                    
        
        }
            
        
    return fit;
     }
 /**
  * 
  * @param pointer points on memory location
  * @param cls class type
  * @param num size of array
  * @return 2D array with elements from memory array 
  */
 public byte[][] read(int pointer,Class<?> cls, int num){
    int NumberOfBytes= TypeConverter.numberOfBytesPerClass(cls); //number of bytes per each array element
    byte[][] retBytes= new byte[num][NumberOfBytes]; // temporary array
    for(int j=0; j<num; j++){
        for (int k=0; k<NumberOfBytes; k++){
            retBytes[j][k]=memoryArray[pointer]; //reading array starting from pointer
            pointer++; //move on the next pointer
            }
     }
    return retBytes;
    }
 /**
  * freeing everything from the memory
  */
 public void free(){
     for(int i=0; i<occupiedMemory.length;i++){
         occupiedMemory[i]=false; //occupied memory will be now free
         
         }
     memoryArray= new byte[memoryArray.length]; // freeing everything from memory
     
     }
 /**
  * 
  * @param pointer point on location in array
  * @param cls class type
  * @param num size of array
  */
 public void free(int pointer,Class<?> cls,int num){
     byte []newMemory=new byte[num];
     //set everything to 0
     for(int i=pointer; i<num;i++){
         newMemory[i]=0;
         }
     
     }
 /**
  * print out which elements of the memory array are occupied and which are not
  * @return String with X- for occupied and o - for free memory
  */

 @Override
 public String toString() {
     String printingOccupFreeMemory="";
     //checking memory
     for(int j=0; j<occupiedMemory.length; j++){
         if(occupiedMemory[j]==false){
             printingOccupFreeMemory+="o"; // free memory
             
         }else{
             printingOccupFreeMemory+="X"; //occupied memory  
         }
     }
         
     return printingOccupFreeMemory; //string with X and o
     
     }
/**
 *  
 * @param pointer points on place in memory
 * @param cls type of element
 * @param num size of element
 * @return location in the memory where are elements
 */
 
 String printLocationInMemory(int pointer,Class<?> cls, int num){
     
    int NumberOfBytes= TypeConverter.numberOfBytesPerClass(cls);
    int segment = num * NumberOfBytes; 
    String forPrintingLocations = "";
    // checking if memory parts store new elements
     for(int j=0; j<memoryArray.length; j++){
         if(j>=pointer && j<pointer+segment){
             forPrintingLocations+="X"; // if yes
             
         }else{
             forPrintingLocations+="o"; // if not
             
         }
         
     }
     
     return forPrintingLocations; // location in the memory where are elements
     
     }
 /**
  * 
  * @param pointer1 first pointer
  * @param pointer2 second pointer
  * @param num size of array
  * @return sum of value of two pointers 
  */
 public int addIntArrays(int pointer1, int pointer2, int num) {
 
 Integer[] firstArray = new Integer[num];
 Integer[] secondArray= new Integer[num];
 Integer[] resultArray = new Integer[num];
 
 
 int sumpointer = 0;

 for (int i = 0; i < num; ++i) {
     byte[] temp = new byte[4];
     // value of the first pointer
     temp = Arrays.copyOfRange(memoryArray, pointer1 + i*4, pointer1 + i*4 + 4);         
     firstArray[i] = TypeConverter.byteArrayToInt(temp);
     //value of the second pointer
     temp = Arrays.copyOfRange(memoryArray, pointer2 + i*4, pointer2 + i*4 + 4); 
     secondArray[i] = TypeConverter.byteArrayToInt(temp);
     //sum of the two pointers
     resultArray[i] = firstArray[i] + secondArray[i];
 }
 sumpointer = allocate(resultArray);
 copy(sumpointer, resultArray);     //  copying resulting Array in new segment of memoryArray
 
 return sumpointer; //returning sum
}
}
