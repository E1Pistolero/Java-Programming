/*
* Author : Milica Knezevic, mknezevic2013@my.fit.edu
* Course : CSE 2010, Section 06, Fall 2014
* Project: lab#5, Driver
*/
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Driver {
    
    public static class Grade implements Comparable<Grade>{
        ArrayList<Integer> grades;
        public Grade(ArrayList<Integer> grades_) {
            this.grades=grades_;
            }
        
        @Override
        public int compareTo(Grade other) {
            double currentGPA=0;
            double otherGPA=0;
            for (int i = 0; i < this.grades.size(); i++) {
                currentGPA+=this.grades.get(i);
                }
            currentGPA/=(this.grades.size());
            for (int i = 0; i < other.grades.size(); i++) {
                otherGPA+=other.grades.get(i);
                }
            otherGPA/=(other.grades.size());
            if (currentGPA>=otherGPA) {
                return 1;
                } else {
                    return -1;
                    }
            }
        
        @Override
        public String toString(){
            String result="";
            double average=0;
            for (int i = 0; i < this.grades.size(); i++) {
                result+=this.grades.get(i)+" ";
                average+=this.grades.get(i);
                }
            result+=" average: "+average/this.grades.size();
            return result;
            }
        }
    
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        SelectionSort selectionSort = new SelectionSort();
        QuickSort quickSort = new QuickSort();
        int baseline=5;
        MixAndMatchSort mixAndMatchSort1 = new
        MixAndMatchSort(mergeSort,selectionSort,baseline);
        MixAndMatchSort mixAndMatchSort2 = new
        MixAndMatchSort(mergeSort,quickSort,baseline);
        MixAndMatchSort mixAndMatchSort3 = new
        MixAndMatchSort(selectionSort,quickSort,baseline);
        int n=8;
        
        // arrays to be sorted
        Integer[] list = new Integer[n];
        Date[] dates = new Date[n];
        Grade[] grades=new Grade[n];
        Random random = new Random(1);
        int numberOfGrades=3;
        //populates arrays with random numbers, dates, grades
        for (int i = 0; i < n; i++) {
            list[i]=random.nextInt(100);
            dates[i]=new Date(random.nextInt(115),
                    random.nextInt(12),
                    random.nextInt(31),random.nextInt(24),random.nextInt(60));
            ArrayList<Integer> arrayListWithGrade=new
            ArrayList<Integer>(numberOfGrades);
            for (int j = 0; j < numberOfGrades; j++) {
                arrayListWithGrade.add(random.nextInt(100));
                }
            Grade grade = new Grade(arrayListWithGrade);
            grades[i]=grade;
            }
        //sort in place
        mixAndMatchSort1.sort(list);
        mixAndMatchSort2.sort(dates);
        mixAndMatchSort3.sort(grades);
        //print to be sure everything is correct
        Driver.printArray(list,false);
        System.out.println("\n\n");
        Driver.printArray(dates,true);
        System.out.println("\n\n");
        Driver.printArray(grades, true);
        }
    @SuppressWarnings("rawtypes")
    public static void printArray(Comparable[] array,boolean onEveryLine){
        //print array
        for (int i = 0; i < array.length; i++) {
            if (onEveryLine) {
                System.out.println(array[i]+" ");
                }else{
                    System.out.print(array[i]+" ");
                    }
            }
        System.out.println();
        }
    }