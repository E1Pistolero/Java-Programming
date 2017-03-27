import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;


public class Tester {

	public static boolean isSorted(Comparable[] list) {
		for(int i = 1; i < list.length; i++) {
			if(list[i].compareTo(list[i-1]) < 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String args[]) {
		Random rand = new Random(1);

		//Integer[] a = {33, 2, 6, 1, 54, 4, 0, 4, 4754, 43, 20 ,9, 10, 54, 38, 57, 43};

		int n = 8;
		Integer[] m = new Integer[n];
		Integer[] s = new Integer[n];
		Integer[] q = new Integer[n];
		Date[] dates = new Date[n];
		Driver.Grade[] grades = new Driver.Grade[n];

		// SEED
		int numberOfGrades = 3;

		for(int i = 0; i < n; i++) {
			/*m[i] = (int)(Math.random() * 99);
			s[i] = (int)(Math.random() * 99);
			q[i] = (int)(Math.random() * 99);*/

			m[i] = rand.nextInt(100);
			s[i] = rand.nextInt(100);
			q[i] = rand.nextInt(100);

			dates[i]=new Date(rand.nextInt(115), rand.nextInt(12), rand.nextInt(31),rand.nextInt(24),rand.nextInt(60));

			// SEED GRADES
			ArrayList<Integer> arrayListWithGrade=new ArrayList<Integer>(numberOfGrades);
			for (int j = 0; j < numberOfGrades; j++) {

				arrayListWithGrade.add(rand.nextInt(100));
			}
			Driver.Grade grade = new Driver.Grade(arrayListWithGrade);
			grades[i]=grade;
		}

		MergeSort ms = new MergeSort();
		SelectionSort ss = new SelectionSort();
		QuickSort qs = new QuickSort();

		ss.sort(s);
		ms.sort(m);
		qs.sort(q);

		qs.sort(dates);
		qs.sort(grades);

		System.out.println("Selection: " + Arrays.toString(s) + "\n" + isSorted(s));
		System.out.println("Merge: " + Arrays.toString(m) + "\n" + isSorted(m));
		System.out.println("Quick: " + Arrays.toString(q) + "\n" + isSorted(q));
		System.out.println("dates: " + Arrays.toString(dates) + "\n" + isSorted(dates));
		System.out.println("Grades: " + Arrays.toString(grades) + "\n" + isSorted(grades));
	}
}

