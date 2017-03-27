

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestExternalSort {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testCompleteSort() throws Exception{
        
        String tempFolder="D:\temp/";
        String outputFile="D:\temp\output/output.txt";
        ExternalSort externalsort=new ExternalSort(tempFolder,3000000);
        
        externalsort.completeSort("D:\temp\output/grading_testcase.txt",
                "D:\temp\output/output.txt");
        
        File tempFolderFile = new File(tempFolder);
        File[] fileList=tempFolderFile.listFiles();
        System.out.println("Were files deleted? "+(fileList.length==0));
        
        assertEquals(true, isNonDecreasing(outputFile));
    }
    
    public boolean isNonDecreasing(String filename) throws FileNotFoundException{
        
        File output = new File(filename);
        Scanner scanner = new Scanner(output);
        
        boolean isNonDecreasing=true;
        
        int first=scanner.nextInt();
        int second;
        int line=1;
        
        int n=10;
        int[] range=new int[n];
        
        
        while (scanner.hasNext()) {
            
            second=scanner.nextInt();
            range[getIndexFromRange(second, n)]++;
            if (first>second) {
                System.out.println(first);
                System.out.println(second);
                System.out.println("Problem on lines: "+(line)+" "+(line+1));
                isNonDecreasing=false;
                break;
            }
            first=second;
            line++;
            
        }
        
        
        for (int i = 0; i < range.length; i++) {
            System.out.println(range[i]);
        }
        return isNonDecreasing;
    }
    
    
    public int getIndexFromRange(int i, int n){
        
        int maxValue=Integer.MAX_VALUE;
        
        int idx=0;
        
        int step=maxValue/n;
        int range=step;
        while(i>range){
            range+=step;
            idx++;
        }
        
        return idx;
    }

}
