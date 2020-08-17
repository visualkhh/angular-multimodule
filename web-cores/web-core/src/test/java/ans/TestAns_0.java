package ans;

import NGAP_PDU_Descriptions.*;
import org.asnlab.asndt.runtime.conv.EncodingRules;
import org.asnlab.asndt.runtime.type.Buffer;
import org.asnlab.asndt.runtime.value.BitString;

import java.io.IOException;
import java.util.Vector;
public class TestAns_0 {
    public static void main(String[] args) {


//		NGAP_PDU_Contents.instance.addObject(arg0, arg1, arg2, arg3);

        WriteReplaceWarningRequest writeReplaceWarningReguest = new WriteReplaceWarningRequest();
        writeReplaceWarningReguest.protocolIEs = new Vector<>();

        WriteReplaceWarningRequest_ProtocolIEs writeReplace = new WriteReplaceWarningRequest_ProtocolIEs();
        MessageIdentifier messageIdentifier = new MessageIdentifier(new byte[]{0x01,0x02,0x03,0x04,0x01,0x02,0x03,0x04,0x01,0x02,0x03,0x04,0x01,0x02,0x03,0x04},(byte) 0x10);
        writeReplace.id = ProtocolIE_ID.id_MessageIdentifier;
        writeReplace.criticality = Criticality.reject;
        writeReplace.value = messageIdentifier;
        writeReplaceWarningReguest.protocolIEs.add(writeReplace);

        //nR-CGIList
        writeReplace = new WriteReplaceWarningRequest_ProtocolIEs();
        writeReplace.id = ProtocolIE_ID.id_WarningAreaList;
        writeReplace.criticality = Criticality.ignore;
        Vector<NR_CGI> nrCGIs = new Vector<>();
        for (int i = 0; i < 2; i++) {
            NR_CGI nrCgi = new NR_CGI();
            nrCgi.pLMNIdentity = new byte[]{0x01,0x02,0x04};
            nrCgi.nRCellIdentity = new BitString(new byte[]{(byte)i,0x02,0x03,0x01,0x02,0x03,0x01,0x02,0x03,0x01,0x02,0x03,0x01,0x02,0x03,0x01,0x02,0x03,0x01,0x02,0x03,0x01,0x02,0x03,0x01,0x02,0x03,0x04}, (byte)0x1c);
            nrCGIs.add(nrCgi);
        }
        WarningAreaList nRCGIW = WarningAreaList.nR_CGIListForWarning(nrCGIs);
        writeReplace.value = nRCGIW;
        writeReplaceWarningReguest.protocolIEs.add(writeReplace);



//
        //eUTRA
//		writeReplace = new WriteReplaceWarningRequest_ProtocolIEs();
//		writeReplace.id = ProtocolIE_ID.id_WarningAreaList;
//		writeReplace.criticality = Criticality.ignore;
//		Vector<EUTRA_CGI> eutras = new Vector<>();
//
//		for (int i = 0; i < 10; i++) {
//			EUTRA_CGI eutra_cgi = new EUTRA_CGI();
//			eutra_cgi.pLMNIdentity = new byte[]{0x01,0x02,0x03};
//			eutra_cgi.eUTRACellIdentity = new BitString(new byte[]{(byte)i,0x02,0x03,0x01,0x02,0x03,0x01,0x02,0x03,0x01,0x02,0x03,0x01,0x02,0x03,0x01,0x02,0x03,0x01,0x02,0x03,0x01,0x02,0x03,0x01,0x02,0x03,0x04}, (byte)0x1c);
//			eutras.add(eutra_cgi);
//		}
//
//		WarningAreaList eutraWarnings = WarningAreaList.eUTRA_CGIListForWarning(eutras);
//		writeReplace.value = eutraWarnings;
//		writeReplaceWarningReguest.protocolIEs.add(writeReplace);




//		writeReplace = new WriteReplaceWarningRequest_ProtocolIEs();
//		writeReplace.id = ProtocolIE_ID.id_WarningAreaList;
//		writeReplace.criticality = Criticality.ignore;
//		Vector<byte[]> emergencyAreaIDList = new Vector<>();
//		emergencyAreaIDList.add(new byte[]{0x01,0x02,0x03});
//		WarningAreaList warningAreaList = WarningAreaList.emergencyAreaIDList(emergencyAreaIDList);
//		writeReplace.value = warningAreaList;
//		writeReplaceWarningReguest.protocolIEs.add(writeReplace);




//		WriteReplaceWarningRequest_ProtocolIEs writeReplaceWarningRequest_protocolIEs = new WriteReplaceWarningRequest_ProtocolIEs();
//		writeReplaceWarningRequest_protocolIEs.id = ProtocolIE_ID.id_WarningAreaList;
//		writeReplaceWarningRequest_protocolIEs.criticality = Criticality.ignore;
//		Vector<byte[]> emergencyAreaIDList = new Vector<>();
//		emergencyAreaIDList.add(new byte[]{0x01,0x02,0x03});
//		WarningAreaList warningAreaList = WarningAreaList.emergencyAreaIDList(emergencyAreaIDList);
//		System.out.println(warningAreaList.choiceID);
//		writeReplaceWarningRequest_protocolIEs.value = warningAreaList;
//		writeReplaceWarningReguest.protocolIEs.add(writeReplaceWarningRequest_protocolIEs);

//		WriteReplaceWarningRequest_ProtocolIEs writeReplaceWarningRequest_protocolIEs_Serial = new WriteReplaceWarningRequest_ProtocolIEs();
//		writeReplaceWarningRequest_protocolIEs_Serial.id = ProtocolIE_ID.id_SerialNumber;
//		writeReplaceWarningRequest_protocolIEs_Serial.criticality = Criticality.reject;
//		writeReplaceWarningRequest_protocolIEs_Serial.value = new SerialNumber(new byte[]{0x01,0x02,0x03, 0x04, 0x01,0x02,0x03, 0x04, 0x01,0x02,0x03, 0x04, 0x01,0x02,0x03, 0x04}, (byte)16);
//		writeReplaceWarningReguest.protocolIEs.add(writeReplaceWarningRequest_protocolIEs_Serial);
//
//		WriteReplaceWarningRequest_ProtocolIEs writeReplaceWarningRequest_protocolIEs_Identi = new WriteReplaceWarningRequest_ProtocolIEs();
//		writeReplaceWarningRequest_protocolIEs_Identi.id = ProtocolIE_ID.id_MessageIdentifier;
//		writeReplaceWarningRequest_protocolIEs_Identi.criticality = Criticality.reject;
////		MessageIdentifier messageIdentifier = new MessageIdentifier(new byte[]{0x01,0x02,0x03}, (byte)1);
//		MessageIdentifier messageIdentifier = new MessageIdentifier(3);
//		writeReplaceWarningRequest_protocolIEs_Identi.value = messageIdentifier;
//		writeReplaceWarningReguest.protocolIEs.add(writeReplaceWarningRequest_protocolIEs_Identi);
//
        WriteReplaceWarningRequest_ProtocolIEs writeReplaceWarningRequest_protocolIEs_MSG = new WriteReplaceWarningRequest_ProtocolIEs();
        writeReplaceWarningRequest_protocolIEs_MSG.id = ProtocolIE_ID.id_WarningMessageContents;
        writeReplaceWarningRequest_protocolIEs_MSG.criticality = Criticality.ignore;
//		MessageIdentifier messageIdentifier = new MessageIdentifier(new byte[]{0x01,0x02,0x03}, (byte)1);
//		writeReplaceWarningRequest_protocolIEs_MSG.value = new byte[]{0x01,0x02,0x03};
        writeReplaceWarningRequest_protocolIEs_MSG.value = "show me the money  show me the money  show me the money  show me the money  show me the money".getBytes();
        writeReplaceWarningReguest.protocolIEs.add(writeReplaceWarningRequest_protocolIEs_MSG);
//
//		WriteReplaceWarningRequest_ProtocolIEs writeReplaceWarningRequest_protocolIEs = new WriteReplaceWarningRequest_ProtocolIEs();
//		writeReplaceWarningRequest_protocolIEs.id = ProtocolIE_ID.id_WarningAreaList;
//		writeReplaceWarningRequest_protocolIEs.criticality = Criticality.ignore;
//		Vector<TAI> tAIListForWarning = new Vector<TAI>();
//		TAI tai = new TAI();
//		tai.pLMNIdentity = new byte[]{0x01,0x02,0x03};
//		tai.tAC = new byte[]{0x01,0x02,0x03};
//		tAIListForWarning.add(tai);
//		WarningAreaList wl = WarningAreaList.tAIListForWarning(tAIListForWarning);
//		writeReplaceWarningRequest_protocolIEs.value = wl;
//		writeReplaceWarningReguest.protocolIEs.add(writeReplaceWarningRequest_protocolIEs);
//
//
//		WriteReplaceWarningRequest_ProtocolIEs writeReplaceWarningRequest_protocolIEs_wars = new WriteReplaceWarningRequest_ProtocolIEs();
//		writeReplaceWarningRequest_protocolIEs_wars.id = ProtocolIE_ID.id_WarningAreaList;
//		writeReplaceWarningRequest_protocolIEs_wars.criticality = Criticality.ignore;
//		Vector<TAI> tAIListForWarning = new Vector<TAI>();
//		TAI tai = new TAI();
//		tai.pLMNIdentity = new byte[]{0x01,0x02,0x03};
//		tai.tAC = new byte[]{0x01,0x02,0x03};
//		tAIListForWarning.add(tai);
//		WarningAreaList wl = WarningAreaList.tAIListForWarning(tAIListForWarning);
//		writeReplaceWarningRequest_protocolIEs_wars.value = wl;
//		writeReplaceWarningReguest.protocolIEs.add(writeReplaceWarningRequest_protocolIEs_wars);








        ////////////////////////
        InitiatingMessage initiatingMessage = new InitiatingMessage();
        initiatingMessage.procedureCode = ProcedureCode.id_WriteReplaceWarning;
        initiatingMessage.criticality = Criticality.reject;
        initiatingMessage.value = writeReplaceWarningReguest;
        NGAP_PDU ngap_pdu = NGAP_PDU.initiatingMessage(initiatingMessage);
        /////////////


        NGAP_PDU.TYPE.print(ngap_pdu, NGAP_PDU.CONV, System.out);





//		writeReplaceWarningReguest.TYPE.print(writeReplaceWarningReguest, writeReplaceWarningReguest.CONV, System.out);
//		writeReplaceWarningReguest.ber_encode(System.out);

        System.out.println("======== encode ========");
        Buffer buffer = Buffer.allocate(1024, EncodingRules.BASIC_ENCODING_RULES);
        NGAP_PDU.TYPE.encode(ngap_pdu, buffer, NGAP_PDU.CONV);
        byte[] bytes = buffer.array();
        for(byte b : bytes) {
            System.out.printf("%02X ", b & 0xFF);
        }
//		System.out.println();
//		System.out.println();
////
        System.out.println("======== decode ========");
        Buffer buffer2 = Buffer.wrap(bytes, EncodingRules.BASIC_ENCODING_RULES);
        System.out.println(buffer2.toString());
//
////		ProtocolIEs
//        NGAP_PDU newValue = (NGAP_PDU) NGAP_PDU.TYPE.decode(buffer2, NGAP_PDU.CONV);
//        NGAP_PDU.TYPE.print(newValue, NGAP_PDU.CONV, System.out);
//        System.out.println();
//        System.out.println();
//
//        System.out.println("======== equality ========");
//        System.out.println(newValue.equals(ngap_pdu));
//        System.out.println(ngap_pdu);
//        System.out.println(NGAP_PDU.TYPE.equals(ngap_pdu, newValue, NGAP_PDU.CONV));

    }
}
