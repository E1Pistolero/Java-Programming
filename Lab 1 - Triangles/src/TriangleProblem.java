// TriangleProblem.java
public class TriangleProblem {
	double[][] points;
	// constructor. Nothing to implement here
	public TriangleProblem(double[][] points_) {
		this.points=points_;
		}
	

	public Triangle calculateLowestAreaTriangle() {
		// implement the function which would find triangle of the smallest area. Triangle is made from 3 distinct points.
		Triangle triangleL = new Triangle();
		
		triangleL.a = points[0];
		triangleL.b = points[1];
		triangleL.c = points[2];
		
		double minA = triangleL.calculateArea();
		
		for(int i=0; i<points.length-2; i++){
			for(int j=i+1; j<points.length-1; j++){
				for(int k=j+1; k<points.length; k++){
					Triangle newArea = new Triangle();
					
					newArea.a = points[i];
					newArea.b = points[j];
					newArea.c = points[k];
					
					double newA = newArea.calculateArea();
					if(newA < minA){
						triangleL = newArea;
						minA = newA;
					}
				}
			}
		}
		
		
		
		return triangleL;
		}
	public Triangle calculateHighestAreaTriangle() {
		// implement the function which would find triangle of the largest area.
		Triangle triangleH = new Triangle();
		
		triangleH.a = points[0];
		triangleH.b = points[1];
		triangleH.c = points[2];
		
		double maxA = triangleH.calculateArea();
		
		for(int i=0; i<points.length-2; i++){
			for(int j=i+1; j<points.length-1; j++){
				for(int k=j+1; k<points.length; k++){
					Triangle maxArea = new Triangle();
					
					maxArea.a = points[i];
					maxArea.b = points[j];
					maxArea.c = points[k];
					
					double newA = maxArea.calculateArea();
					if(maxA < newA){
						triangleH = maxArea;
						maxA = newA;
					}
				}
			}
		}
		
		
		return triangleH;
		}
}