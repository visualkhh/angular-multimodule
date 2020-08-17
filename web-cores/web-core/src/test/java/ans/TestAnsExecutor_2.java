package ans;

import NGAP_PDU_Descriptions.NGAP_PDU;
import com.lgu.cbcf.ecc.ans.WriteReplaceRequest;
import org.asnlab.asndt.runtime.conv.EncodingRules;
import org.asnlab.asndt.runtime.type.Buffer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class TestAnsExecutor_2 {
    public static void main(String[] args) throws IOException {

        //DB SEND CELL
        // split 한뒤 0 뒤에 다붙혀줌
        WriteReplaceRequest message = new WriteReplaceRequest(1, "test-msg", 3, 1, 4372, 1);
//        message.makeSapWriteReplace("T", "000000111111222222", "123123123");
//        message.makeSapWriteReplace("C", "000064111111222222", "0000000000");
//        message.makeSapWriteReplace("T", "000064", "00000004D20");
//        message.makeSapWriteReplace("C", "000064", "00000004D20");
//        message.makeSapWriteReplace("C", "000064", "0000004D20");
//        message.makeSapWriteReplace("T", "000001", "");
        message.makeSapWriteReplace("T", "000001", null);
//        message.makeSapWriteReplace("", "", "");‭2DFDC1C3E‬
//        message.makeSapWriteReplace("C", "000001", "2DFDC1C3E0");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        message.sapWriteReplace.



        System.out.println("======== encode ========");
        Buffer buffer = Buffer.allocate(1024, EncodingRules.BASIC_ENCODING_RULES);
        NGAP_PDU.TYPE.print(message.sapWriteReplace, NGAP_PDU.CONV, System.out);
        NGAP_PDU.TYPE.encode(message.sapWriteReplace, buffer, NGAP_PDU.CONV);
        byte[] bytes = buffer.array();
        StringBuffer str = new StringBuffer();
        for(byte b : bytes) {
//            System.out.printf("%02X ", b & 0xFF);
            str.append( String.format("%02X", b & 0xFF));
        }

        String bs = str.toString();
        System.out.println();
        System.out.println(bs);



        bs = "01" + bs;
        byte[] bytes1 = new java.math.BigInteger(bs, 16).toByteArray();
        byte[] b = new byte[bytes1.length - 1];
        System.arraycopy(bytes1, 1, b, 0, b.length);
        bytes1 = b;


        System.out.println();
        System.out.println("======== decode ========");
        Buffer buffer2 = Buffer.wrap(bytes1, EncodingRules.BASIC_ENCODING_RULES);
        System.out.println(buffer2.toString());

        ////		ProtocolIEs
//        NGAP_PDU newValue = (NGAP_PDU) NGAP_PDU.TYPE.decode(buffer2, NGAP_PDU.CONV);
//        NGAP_PDU.TYPE.print(newValue, NGAP_PDU.CONV, System.out);
//        System.out.println();
        NGAP_PDU newValue = (NGAP_PDU) NGAP_PDU.TYPE.decode(buffer2, NGAP_PDU.CONV);
        NGAP_PDU.TYPE.print(newValue, NGAP_PDU.CONV, System.out);
        System.out.println();
//        System.out.println();
//
        System.out.println("======== equality ========");
        System.out.println(newValue.equals(message.sapWriteReplace));
        System.out.println(message.sapWriteReplace);
        System.out.println(NGAP_PDU.TYPE.equals(message.sapWriteReplace, newValue, NGAP_PDU.CONV));








//        NGAP_PDU.
//        WriteReplaceRequest message = Main.getMessageCache(sendInfo.msgSeq);
//        SBC_AP_PDU sapWriteReplace = null;
//        ByteArrayInputStream bais = null;
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        byte[] data = null;
//
//        message.makeSapWriteReplace(sendInfo.sendDiv, sendInfo.sendTac, sendInfo.sendCell);
//        message.sapWriteReplace.print(this.logPs);
//        logger.debug(idx + ":" + this.getLogPs());
//        message.sapWriteReplace.per_encode(true, baos);
//
//        synchronized (this.lock[idx]) {
//            this.checker[idx].sendCgppPacket(baos.toByteArray(), false);
//            data = this.checker[idx].receiveCgppPacket();
//        }
//
//        if (data == null) {
//            throw new MmeTimeoutException();
//        }
//
//        bais = new ByteArrayInputStream(data);
//        sapWriteReplace = SBC_AP_PDU.per_decode(true, bais);
//
//        logger.debug(idx + ":" + WriteReplaceRequest.getHexValue(data));
//
//        if (sapWriteReplace != null) {
//            sapWriteReplace.print(this.logPs);
//            logger.debug(idx + ":" + this.getLogPs());
//        }
    }
}
