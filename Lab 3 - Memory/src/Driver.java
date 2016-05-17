public class Driver {

	public static void main(String[] args) {

		int n=32;
		Memory ram = new Memory(n);
		
		// things we will store in the memory
		Integer[] a={234,301};
		Double[] b={13.5};
		
		System.out.println("Initially memory is free - all bytes are avaliable");
		System.out.println(ram);
		
		// allocate segment in the memory for a.
		int a_p=ram.allocate(a);
		
		// copy array a into the memory starting at pointer
		boolean status=ram.copy(a_p, a);
		
		// allocate segment for b and copy it inot
		int b_p=ram.allocate(b);
		ram.copy(b_p, b);
		
		
		Integer[] a_back=new Integer[a.length];
		Double[] b_back=new Double[b.length];
				
		if (status) {
			
			// read from the memory
			byte[][] bytes=ram.read(a_p, Integer.class, a.length);

			for (int i = 0; i < a.length; i++) {
				a_back[i]=TypeConverter.byteArrayToInt(bytes[i]);
				System.out.println(a_back[i]);
			}
			
			bytes=ram.read(b_p, b[0].getClass(),b.length);
			for (int i = 0; i < b_back.length; i++) {
				b_back[i]=TypeConverter.byteArrayToDouble(bytes[i]);
				System.out.println(b_back[i]);
			}
			
			// print state of the memory
			System.out.println(ram);
		}
		
		// print location of bytes occupied by b
		System.out.println("Location of the array b in the memory");
		System.out.println(ram.printLocationInMemory(b_p, Double.class, b.length));
		
		
		Integer[] c={-100,-200};
		
		int c_p=ram.allocate(c);
		ram.copy(c_p, c);
		
		int sum_p=ram.addIntArrays(a_p, c_p, c.length);
		
		
		System.out.println(ram);
		byte[][] bytes=ram.read(sum_p, Integer.class, a.length);
		
		Integer[] sum=new Integer[2];
		
		System.out.println("Array sum:");
		for (int i = 0; i < sum.length; i++) {
			sum[i]=TypeConverter.byteArrayToInt(bytes[i]);
			System.out.println(sum[i]);
		}
		
		// print location of bytes occupied by the sum array
		System.out.println("Location of the array sum in the memory");
		System.out.println(ram.printLocationInMemory(sum_p, Integer.class, sum.length));
		
	}

}
