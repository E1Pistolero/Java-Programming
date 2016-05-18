public class Matrix {

	private double[][] elements; 			// elements of the matrix
	private int n;	// size
	
	
	
	public Matrix(double[][] elements_) {
		// constructor
		this.elements = elements_;
		n = elements.length;
		
	}
	
	public Matrix multiplyStrassen(Matrix b) {
	// implement Strassen method for matrix multiplication. This function should be recursive.
		double[][] maxArray= new double [n][n];
		Matrix max = new Matrix(maxArray);
		
		if(b.elements.length ==1){
			max.elements[0][0] = this.elements[0][0]*b.elements[0][0];
		} else {
			double[][] a11 = new double[n/2][n/2];
			double[][] a12 = new double[n/2][n/2];
			double[][] a21 = new double[n/2][n/2];
			double[][] a22 = new double[n/2][n/2];
			double[][] b11 = new double[n/2][n/2];
			double[][] b12 = new double[n/2][n/2];
			double[][] b21 = new double[n/2][n/2];
			double[][] b22 = new double[n/2][n/2];
			
			a11 = splitMatrix(this,0,0);
			a12 = splitMatrix(this,0,n/2);
			a21 = splitMatrix(this,n/2,0);
			a22 = splitMatrix(this,n/2,n/2);
			Matrix A11 = new Matrix(a11);
            Matrix A12 = new Matrix(a12);
            Matrix A21 = new Matrix(a21);
            Matrix A22 = new Matrix(a22);
			
			b11 = splitMatrix(b,0,0);
			b12 = splitMatrix(b,0,n/2);
			b21 = splitMatrix(b,n/2,0);
			b22 = splitMatrix(b,n/2,n/2);
			Matrix B11 = new Matrix(b11);
            Matrix B12 = new Matrix(b12);
            Matrix B21 = new Matrix(b21);
            Matrix B22 = new Matrix(b22);
            
            
             Matrix P1 = A11.multiplyStrassen(B12.subtract(B22));
             Matrix P2 = (A11.add(A12)).multiplyStrassen(B22);
			 Matrix P3 = (A21.add(A22)).multiplyStrassen(B11);
			 Matrix P4 = A22.multiplyStrassen(B21.subtract(B11)); 
			 Matrix P5 = (A11.add(A22)).multiplyStrassen(B11.add(B22));
			 Matrix P6 = (A12.subtract(A22)).multiplyStrassen(B21.add(B22));
			 Matrix P7 = (A11.subtract(A21)).multiplyStrassen(B11.add(B12));
			 
			 /*
			C11 = P5 + P4 − P2 + P6,
			C12 = P1 + P2,
			C21 = P3 + P4,
			C22 = P5 + P1 − P3 − P7.
			  */
			Matrix C11 = ((P5.add(P4)).subtract(P2)).add(P6);
			Matrix C12 = P1.add(P2);
			Matrix C21 = P3.add(P4);
			Matrix C22 = (((P5.add(P1)).subtract(P3))).subtract(P7);
			
			for(int i=0; i<n/2; i++){
	            for(int j=0; j<n/2; j++){
	                max.elements[i][j]=C11.elements[i][j];
	                max.elements[i][j+(n/2)]=C12.elements[i][j];
	                max.elements[i+(n/2)][j]=C21.elements[i][j];
	                max.elements[i+(n/2)][j+(n/2)]=C22.elements[i][j];
	            }
	        }
			
			
		}
		
		
		

		return max; 
	}
	
	 public double[][] splitMatrix(Matrix mat, int s1, int s2){
		 double [][] splited = new double [n/2][n/2];
		 for(int i=s1, k=0; k<n/2; i++, k++){
			 for(int j=s2, l=0; l<n/2; j++, l++){
				 splited[k][l]= mat.elements[i][j];
			 }
		 }
		 
		 return splited;
	 }
	
	
	public Matrix multiply(Matrix b) {
	// 	implement regular matrix multiplication method (hint: you might want to use it for testing)
		double[][] max = new double [n][n];
		Matrix regular = new Matrix(max);
		
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				for(int k=0; k<n; k++){
					
					regular.elements[i][j] += (elements[i][k]*b.elements[j][k]); 
					
				}
			}
			
		}
		
		return regular;
	}
	
	public boolean equals(Matrix b) {
	// check if matrices are equal. Compare elements up to certain precision, say 1e-6, e.g.
	// abs(this.elements[i][j]-b.elements[i][j])<1e-6
		double precision = 1E-6; // precision for equality
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                // checks if elements are equal
                if(Math.abs(this.elements[i][j]-b.elements[i][j])<precision){
                    return false;
                }
            }
        }
        
        return true; 
	}
	
	public String toString() {
	// return string representation of the matrix	
		String matrixStr="Matrix of the size [" +n+","+n+"]\n"; // size of matrix [x, x]
        for(int i=0; i<n;i ++){
            for(int j=0; j<n; j++){
                
                matrixStr+=elements[i][j] + " "; // elements
            }
            matrixStr+="\n";
        }
        return matrixStr;
	}
	
	public Matrix add(Matrix b) {
	// addition
		n=elements.length;
		double[][] max=new double[n][n];
		Matrix sum = new Matrix(max);
		for(int i=0; i<n;i++){
			for(int j =0; j<n; j++){
				sum.elements[i][j] = elements[i][j]+b.elements[i][j];
			}
		}
		return sum; 
	}
	
	public Matrix subtract(Matrix b) {
	// multiplication
		n=elements.length;
		double[][] max=new double[n][n];
		Matrix sub = new Matrix(max);
		for(int i=0; i<n;i++){
			for(int j =0; j<n; j++){
				sub.elements[i][j] = elements[i][j]-b.elements[i][j];
			}
		}
		return sub; 
	}
	
}