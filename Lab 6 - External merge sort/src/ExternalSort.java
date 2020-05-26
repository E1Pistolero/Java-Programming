/*
* Author : Skyler Knezevic, mknezevic2013@my.fit.edu
* Course : CSE 2010, Section 06, Fall 2014
* Project: lab 6, ExternalSort
*/
/**
 * 
 * @author Knezevic Milica
 *
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;


public class ExternalSort {

    private final int chunkSize ; // size of the chunks
    private final String directory; // directory where temporary files will be saved to
    /**
     * This is constructor, it is used for directory where files are on computer
     * and for size of temp files (number of integers)
     * 
     * @param directory_ is place where we are saving files
     * @param chunkSize_ numer of ints in new files that we are splitting on original file
     */
    public ExternalSort(String directory_, int chunkSize_) {
    // implement constructor
        this.directory=directory_;
        this.chunkSize=chunkSize_;
    }
    /**
     * This method calls mergeSort method that sorts numbers
     * 
     * @param a array list with integers for first round of sorting
     * @return a int array
     */
    public int[] mergeSort(int[] a){
    // regular merge sort
        int from =0, to=a.length-1;
        mergeSort(a, from, to);
        return null;
    }
    /**
     * This method is for first sorting uses general merge sort
     * by using array and it makes temp files with sorted elements
     * with chunkSize number of elements
     * 
     * @param inputFile file that from which we are reading
     * @param outputFile file in which we are saving
     * @throws IOException in case that something is wrong throws exception
     */
    public void completeSort(String inputFile, String outputFile) throws IOException, FileNotFoundException {
     // FileReader used for reading streams of characters.
        FileReader   myFile = new FileReader(inputFile);  
      //BufferedReader class reads text from a character-input stream
        BufferedReader  textLine = new BufferedReader(myFile); 
        int  NumberOfLines = 0;
        //int NumberOfChunks=0;
        String line;
        int counter=0;
        int tempFileIndex=0; //index for file names
        String tempFileName;
        int[] a = new int[this.chunkSize];
        ArrayList<String> FileName = new ArrayList<String>(); //list of temp file names 
        line = textLine.readLine(); // read first line of file
     // reading lines until there is no new line
        while(line!= null){
            NumberOfLines = 0;
            counter=0;
         // for temp files size of chunkSize ints                
            while(line != null && NumberOfLines<this.chunkSize){       
             a[counter] = Integer.parseInt(line);   // parsing string to integer value
             NumberOfLines++; // counting number of lines
             counter++;
             line = textLine.readLine(); // reading next line
            
        }
        
         // in case that file is smaller then chunkSize we need to delete 0 from array
        if (counter < chunkSize){
            int[] tempArray = new int[counter];
            System.arraycopy(a, 0, tempArray, 0, counter); // save in temporary array
            a = new int[counter];
            System.arraycopy(tempArray, 0, a, 0, counter); // move back to original array   
        }
 
        mergeSort(a); // sorted chunk
        tempFileIndex++; // for new temp file name adding index
        tempFileName=this.directory+"tempfile"+Integer.toString(tempFileIndex); // name of temp file
        FileWriter tempfw = new FileWriter(tempFileName);
        BufferedWriter tempbw = new BufferedWriter(tempfw);
        FileName.add(tempFileName); // saving temp file names to the ArrayList
        
        for(int i=0; i<a.length; i++){
            tempbw.append(a[i] + "\n");
        }

        tempbw.close();
        }
        textLine.close();
        myFile.close();
        
        mergeFiles(FileName,outputFile);    // getting name of the last file
      
    }
    /**
     * This method returns an integer that is index of middle element
     * 
     * @param list - array list with integers
     * @param first - index of first element
     * @param last - index of last element
     * @return index, that is on half between first and last  
     */
    public static int split(int[] list, int first, int last) {
        int index= (last+first)/2; //index in the middle 
        return index;
     
    }
    /**
     * This method combines sorted elements from first to middle
     * and from middle+1 to last
     * 
     * @param a array list with integers
     * @param from  index of first element
     * @param to index of last element
     */
    public void mergeSort(int[] a,int from, int to ){
         if(from==to){return;   }
         int index= split(a, from, to);
            
            mergeSort(a, from, index);  // sort for lower part of array
            mergeSort(a, index+1, to);  // sort for upper part of array
            a=combine(a,from,index, to);
            
    }
    
    /**
     * This method compares integers 2 by 2 and sorts them
     * each from different chunk
     * 
     * @param list array with integer
     * @param first index of first element in array
     * @param middle index of middle element in array
     * @param last index of last element in array
     * @return sorted array with integers
     */
    public static int[] combine(int[] list, int first, int middle, int last) {
        // comparing 2 ints one from one chunk and 1 from another
        int[] temp = new int[list.length];
        for(int i=first; i<=last ;i++){
            temp[i] = list[i];
            
        }
        
        int i= first;
        int j=middle+1;
        int k=first;
        while (i <= middle && j <= last) {
     
            if (temp[i] <= temp[j]) {      // comparing two leftmost elements
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

    
       
        return list;
    }
    /**
     * This method sorts elements from 2 files by comparing them 1 from first file and 1 from 2 file
     * and then sorting them to new file
     * 
     * @param FilesArr list with names of the temp files that we are sorting
     * @param outPath  name of output file
     * @throws FileNotFoundException in case that file is not found it throws exception
     * @throws IOException other possible exceptions
     */
    public void mergeFiles(ArrayList<String> FilesArr, String outPath)throws FileNotFoundException,IOException {
        ArrayList<String> nameList = new ArrayList<String>();
        //int chunkLength = this.chunkSize;
        int listSize = FilesArr.size();
        if (listSize==1){   // if one file left then copy to output file
            File source = new File(FilesArr.get(0));
            File dest = new File(outPath);
            Files.copy(source.toPath(), dest.toPath()); //copying whole array in one file
            source.delete();
            return;
        }
        int tempFileIndex=1;
        String tempFileName;
        int i;
        
        for (i=0; i < listSize - 1; i=i+2){
            System.out.println(FilesArr.get(i)+"  "+FilesArr.get(i+1));
            
            FileReader File1 = new FileReader(FilesArr.get(i)); // open pair of Files
            FileReader File2 = new FileReader(FilesArr.get(i+1));   
            BufferedReader Line1 = new BufferedReader(File1); 
            BufferedReader Line2 = new BufferedReader(File2); 
            String l1 = Line1.readLine(); //reading first lines of files
            String l2 = Line2.readLine();
            int num1;
            int num2;
            
            //tempFileName=this.directory+"tempfile_"+Integer.toString(tempFileIndex);
            tempFileName=FilesArr.get(i)+"_"+Integer.toString(tempFileIndex);
            
            nameList.add(tempFileName);
            
            while(l1 != null){
                FileWriter tempfw = new FileWriter(tempFileName);
                BufferedWriter tempbw = new BufferedWriter(tempfw);
                
                
                
                
                while (l1 != null ){   // comparing numbers from two files and sort them in new one
                    num1 = Integer.parseInt(l1);
                    while ( l2 != null){
                    num2 = Integer.parseInt(l2);
                        
                        if (num2 < num1){
                            
                            tempbw.append(num2 + "\n");
                            l2 = Line2.readLine();
                        } else{
                            break;
                        }
                        
                    }
                    tempbw.append(num1 + "\n");
                   
                    l1 = Line1.readLine();
                    
                }
                while ( l2 != null){           // finish with rest of second file
                    num2 = Integer.parseInt(l2);
                    tempbw.append(num2 + "\n");   // writting in the file
                    l2 = Line2.readLine();
                }
                
                
                tempbw.close();
                tempFileIndex++;
            }
            
            
            Line1.close();
            Line2.close();
            File1.close();
            File2.close();
            // Deleting temp files 
            String path1=FilesArr.get(i);
            String path2=FilesArr.get(i+1);
            File F1 = new File(path1);
            File F2 = new File(path2);
            F1.delete();
            F2.delete();

           
            
        }
     // if we have odd number of files, just copy last one to new file - can not be merged - no pair
        if (i < listSize){    
            tempFileName=FilesArr.get(i)+"_"+Integer.toString(tempFileIndex+1);
            String sourcepath=FilesArr.get(listSize-1);
            
            File source = new File(sourcepath);
            File dest = new File(tempFileName);
            nameList.add(tempFileName); // adding to listArray
            source.delete();
            Files.copy(source.toPath(), 
            dest.toPath());
            
        }
        
        
        mergeFiles(nameList,outPath); // recursive call
       
        return ;
        
    }
    
    
    
}
