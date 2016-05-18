public class Driver {

	public static void main(String[] args) {

		// create some double 
		double[][] el_1={{1,3,4,5},{2,4,3,5},{1,3,4,5},{2,4,3,5}};
		double[][] el_2={{1,3,4,5},{2,4,1,5},{1,5,4,5},{2,4,-3,3}};
		
		Matrix m1 = new Matrix(el_1);
		Matrix m2 = new Matrix(el_2);
		
		System.out.println(m1);
		Matrix productStrassen=m1.multiplyStrassen(m2);
		Matrix productRegular=m1.multiply(m2);
		
		System.out.println("Are matrices the same? "+productStrassen.equals(productRegular));
		System.out.println(productStrassen);

	}

}