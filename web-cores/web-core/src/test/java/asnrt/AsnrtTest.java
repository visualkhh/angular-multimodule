package asnrt;

import java.io.*;

public class AsnrtTest {
    public static void main(String[] args) throws IOException {
//        byte[] bytes = new BigInteger("0101", 16).toByteArray();
//
//        byte[] b = new byte[this.tac.length - 1];
//
//        System.arraycopy(this.tac, 1, b, 0, b.length);
//        this.tac = b;


//        byte[] ffs = new BigInteger("4525846CCDE54525846CCDE54525846CCDE54525846CCDE5", 16).toByteArray();
//        byte[] ffs = new BigInteger("4525846CCDE5", 32).toByteArray();
//        System.out.println(ffs.length);
        //
//        for (int i = 0; i < ffs.length; i++) {
//            System.out.println(String.format("%x",ffs[i]));
//            System.out.println("--"+Integer.toBinaryString(ffs[i]));
//        }
//            System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));

        OutputStream output = new OutputStream()
        {
            private StringBuilder string = new StringBuilder();
            @Override
            public void write(int b) throws IOException {
                this.string.append((char) b );
            }

            //Netbeans IDE automatically overrides this toString()
            public String toString(){
                return this.string.toString();
            }
        };


//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//
//        WriteReplaceRequest message = new WriteReplaceRequest(1, "asd", 3, 3, 444);
////        message.makeSapWriteReplace(SendDiv.T.toString(), "e014e018", "AEEE");
//        message.makeSapWriteReplace(SendDiv.T.toString(), "00e01400e01800e019",  null);
//        message.sapWriteReplace.print(System.out);
////        message.sapWriteReplace.print(baos);
//        message.sapWriteReplace.per_encode(true, output);
////        Criticality.per_decode()
//
//        System.out.println("---------------");
//        System.out.println(output.toString().length());


//        InputStream resourceAsStream = AsnrtTest.class.getResourceAsStream("SBC_AP_PDU_Descriptions.meta");
//        System.out.println(resourceAsStream);
//        this.getClass().getClassLoader();
//        InputStream resourceAsStream = ClassLoader.getSystemClassLoader().getResourceAsStream("asnrt/ttt.txt");
//        InputStream resourceAsStream = AsnrtTest.class.getResourceAsStream("ttt.txt");
//        String a = readFromInputStream(resourceAsStream);
//        System.out.println(a);
//        System.out.println(resourceAsStream);
    }

    public static String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }
}
