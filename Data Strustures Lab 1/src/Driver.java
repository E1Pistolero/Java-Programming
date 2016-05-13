import java.util.Random;
public class Driver {
	public static void main(String[] args) {
		// In order to generate SAME numbers each time use fixed seed value
		int seed=1;
		Random random = new Random(seed);
		// number of points to generate
		int n=200;
		double[][] points=new double[n][2];
		// generate input
		for (int i = 0; i < n; i++) {
			points[i][0]=random.nextDouble()*100;
			points[i][1]=random.nextDouble()*100;
		}
		TriangleProblem triangleProblem = new TriangleProblem(points);
		double start=0,end=0;
		Triangle solution;
		System.out.println("Smallest area triangle:");
		// time the function
		start=System.currentTimeMillis();
		solution=triangleProblem.calculateLowestAreaTriangle();
		end=System.currentTimeMillis();
		System.out.println(solution.toString());
		System.out.println((end-start)/1000+" seconds");
		System.out.println("Largest area triangle:");
		start=System.currentTimeMillis();
		solution=triangleProblem.calculateHighestAreaTriangle();
		end=System.currentTimeMillis();
		System.out.println(solution.toString());
		System.out.println((end-start)/1000+" seconds");
	}
}

