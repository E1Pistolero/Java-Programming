import java.nio.ByteBuffer;

public class TypeConverter {

	
	public static byte[] toByteArray(Object value){
		
		Class<?> cls=value.getClass();
		byte[] bytes = new byte[numberOfBytesPerClass(cls)];
		
		if(cls==Double.class){
			ByteBuffer.wrap(bytes).putDouble((Double)value);
		}
		
		else if(cls==Short.class){
			ByteBuffer.wrap(bytes).putShort((Short)value);
		}
		
		else if(cls==Integer.class){
			ByteBuffer.wrap(bytes).putInt((Integer)value);
		}
		
		else if(cls==Long.class){
			ByteBuffer.wrap(bytes).putLong((Long)value);
		}
		
		else if(cls==Character.class){
			ByteBuffer.wrap(bytes).putChar((Character)value);
		}
		
		else if(cls==Float.class){
			ByteBuffer.wrap(bytes).putFloat((Float)value);
		}
		
		return bytes;
	}

	public static byte[] toByteArray(double value) {
		byte[] bytes = new byte[8];
		ByteBuffer.wrap(bytes).putDouble(value);
		return bytes;
	}


	public static byte[] toByteArray(short value) {
		byte[] bytes = new byte[2];
		ByteBuffer.wrap(bytes).putShort(value);
		return bytes;
	}


	public static byte[] toByteArray(char value) {
		byte[] bytes = new byte[2];
		ByteBuffer.wrap(bytes).putChar(value);
		return bytes;
	}


	public static byte[] toByteArray(int value) {
		byte[] bytes = new byte[4];
		ByteBuffer.wrap(bytes).putInt(value);
		return bytes;
	}

	public static byte[] toByteArray(float value) {
		byte[] bytes = new byte[4];
		ByteBuffer.wrap(bytes).putFloat(value);
		return bytes;
	}


	public static byte[] toByteArray(long value) {
		byte[] bytes = new byte[8];
		ByteBuffer.wrap(bytes).putLong(value);
		return bytes;
	}

	public static Double byteArrayToDouble(byte[] bytes){
		return ByteBuffer.wrap(bytes).getDouble();
	}
	
	public static Short byteArrayToShort(byte[] bytes){
		return ByteBuffer.wrap(bytes).getShort();
	}
	
	public static Integer byteArrayToInt(byte[] bytes){
		return ByteBuffer.wrap(bytes).getInt();
	}
	
	public static Long byteArrayToLong(byte[] bytes){
		return ByteBuffer.wrap(bytes).getLong();
	}
	
	public static Character byteArrayToChar(byte[] bytes){
		return ByteBuffer.wrap(bytes).getChar();
	}
	
	public static Float byteArrayToFloat(byte[] bytes){
		return ByteBuffer.wrap(bytes).getFloat();
	}

	public static int numberOfBytesPerClass(Class<?> cls){

		int numberOfBytes=0;

		if (cls==Short.class	) {
			numberOfBytes=2;
		}
		else if (cls==Integer.class) {
			numberOfBytes=4;
		}
		else if (cls==Long.class) {
			numberOfBytes=8;
		}
		else if (cls==Double.class) {
			numberOfBytes=8;
		}
		else if (cls==Character.class) {
			numberOfBytes=2;
		}
		else if (cls==Float.class) {
			numberOfBytes=4;
		}
		else {
			System.out.println("Unknown type");
		}

		return numberOfBytes;
	}

}

