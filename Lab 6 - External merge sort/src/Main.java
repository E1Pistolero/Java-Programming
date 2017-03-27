/*
* Author : Milica Knezevic, mknezevic2013@my.fit.edu
* Course : CSE 2010, Section 06, Fall 2014
* Project: lab 6, ExternalSort
*/

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

 // main method
    public static void main(String[] args) throws FileNotFoundException {
    ExternalSort externalsort=new
    ExternalSort("C:\\Users\\TEMP\\Downloads\\temp\\",10000);
    try {
        externalsort.completeSort("C:\\Users\\TEMP\\Downloads\\input\\testcase2.txt",
        "C:\\Users\\TEMP\\Downloads\\output\\output2.txt");
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    }
    
}