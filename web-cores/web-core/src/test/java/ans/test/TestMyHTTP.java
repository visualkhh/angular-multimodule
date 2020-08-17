package ans.test;
import ans.test.MyHTTP.GetRequest;
import org.asnlab.asndt.runtime.conv.AsnConverter;
import org.asnlab.asndt.runtime.conv.EncodingRules;
import org.asnlab.asndt.runtime.type.AsnType;
import org.asnlab.asndt.runtime.type.Buffer;



public class TestMyHTTP {

	/**
	 *  Sample
	 */
	public static void main(String[] args) {
		System.out.println("MyRequest");
		System.out.println("test of myRequest:");
		test(GetRequest.myRequest, GetRequest.TYPE, GetRequest.CONVERTER);
	}
	
	private static <V> void test(V value, AsnType type, AsnConverter converter) {
		System.out.println("======== print ========");
		type.print(value, converter, System.out);
		System.out.println();
		System.out.println();
		
		System.out.println("======== encode ========");
		Buffer buffer = Buffer.allocate(1024, EncodingRules.BASIC_ENCODING_RULES);
		type.encode(value, buffer, converter);
		byte[] bytes = buffer.array();
		for(byte b : bytes) {
			System.out.printf("%02X ", b & 0xFF);
		}
		System.out.println();
		System.out.println();

		System.out.println("======== decode ========");
		Buffer buffer2 = Buffer.wrap(bytes, EncodingRules.BASIC_ENCODING_RULES);
		V newValue = (V) type.decode(buffer2, converter);
		type.print(newValue, converter, System.out);
		System.out.println();
		System.out.println();
		
		System.out.println("======== equality ========");
		System.out.println(type.equals(value, newValue, converter));
	}

}
