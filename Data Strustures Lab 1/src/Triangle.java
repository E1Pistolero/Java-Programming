// Triangle.java
public class Triangle {
	double[] a; // three points which make up a triangle. Each points is an array of size 2
	double[] b;
	double[] c;
	public String toString(){
		// return string-like representation of the triangle. Example will be
		//given in the input-output section
		String area = "Point A: " + a[0] +" "+a[1]
				+"\nPoint B: " + b[0] +" "+b[1]
				+ "\nPoint C: " + c[0] +" "+c[1]
				+ "\nArea: " + calculateArea();
		return area;
		}
	public double calculateArea(){
	// calculate the area of the triangle and return it
		double areaT=Math.abs(a[0]*(b[1]-c[1])+b[0]*(c[1]-a[1])+c[0]*(a[1]-b[1]))/2;
		return areaT;
	}
	}