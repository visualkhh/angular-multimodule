package ans;

import NGAP_PDU_Descriptions.*;
import ans.test.MyHTTP.AcceptTypes;
import ans.test.MyHTTP.GetRequest;
import ans.test.MyHTTP.Url;
import com.lgu.cbcf.ecc.ans.WriteReplaceRequest;
import org.asnlab.asndt.runtime.conv.*;
import org.asnlab.asndt.runtime.type.Buffer;

import java.io.IOException;

public class TestAnsExecutor_0 {
    public static void main(String[] args) throws IOException {

        //DB SEND CELL
        // split 한뒤 0 뒤에 다붙혀줌
        WriteReplaceRequest message = new WriteReplaceRequest(1, "test-msg", 3, 1, 4372, 1);
        message.makeSapWriteReplace("C", "000001", "0000004D20");
//        message.makeSapWriteReplace("T", "000001", "");
        byte[] messageByte = encode(message.sapWriteReplace);

        WriteReplaceRequest message2 = new WriteReplaceRequest(1, "test-msg", 3, 1, 4372, 1);
        message2.makeSapWriteReplace("C", "000001", "0000004D20");
//        message2.makeSapWriteReplace("T", "000001", "");
        byte[] message2Byte = encode(message2.sapWriteReplace);



        NGAP_PDU msg = decode(messageByte);
        NGAP_PDU msg2 = decode(message2Byte);



        System.out.println("======== equality ========");
        System.out.println(msg.equals(msg2));
//        System.out.println(message.sapWriteReplace);
        System.out.println(NGAP_PDU.TYPE.equals(msg, msg2, NGAP_PDU.CONV));
//        System.out.println("======== equality ========");
//        System.out.println(message2.sapWriteReplace.equals(message.sapWriteReplace));
////        System.out.println(message.sapWriteReplace);
//        System.out.println(NGAP_PDU.TYPE.equals(message.sapWriteReplace, message2.sapWriteReplace, NGAP_PDU.CONV));


    }


    public static byte[] encode(NGAP_PDU ngap_pdu) {


//        AsnConverter converter = CONVERTER;
//        ChoiceConverter WarningAreaListCONV = new AnnotationChoiceConverter(WarningAreaList.class);
//        AsnConverter eUTRA_CGIListForWarningConverter = EUTRA_CGIListForWarning.CONV;
//        AsnConverter nR_CGIListForWarningConverter = NR_CGIListForWarning.CONV;
//        AsnConverter tAIListForWarningConverter = TAIListForWarning.CONV;
//        AsnConverter emergencyAreaIDListConverter = EmergencyAreaIDList.CONV;
//        AsnConverter choice_ExtensionsConverter = WarningAreaList_Choice_Extensions.CONV;
////        WarningAreaListCONV.setAlternativeConverters(new AsnConverter[]{tAIListForWarningConverter});
//        WarningAreaListCONV.setAlternativeConverters(new AsnConverter[]{eUTRA_CGIListForWarningConverter, nR_CGIListForWarningConverter, tAIListForWarningConverter, emergencyAreaIDListConverter, choice_ExtensionsConverter});
//
//        CompositeConverter ProtocolCONV = new AnnotationCompositeConverter(WriteReplaceWarningRequest_ProtocolIEs.class);
//        AsnConverter idConverter = ProtocolIE_ID.CONV;
//        AsnConverter criticalityConverter = Criticality.CONV;
//        AsnConverter valueConverter = new OpenValueConverter(NGAP_PROTOCOL_IES.WriteReplaceWarningRequestIEs, NGAP_PROTOCOL_IES.CONV, new Object[]{MessageIdentifier.CONV, SerialNumber.CONV, WarningAreaListCONV, RepetitionPeriod.CONV, NumberOfBroadcastsRequested.CONV, WarningType.CONV, WarningSecurityInfo.CONV, DataCodingScheme.CONV, WarningMessageContents.CONV, ConcurrentWarningMessageInd.CONV, WarningAreaCoordinates.CONV});
//        ProtocolCONV.setComponentConverters(new AsnConverter[]{idConverter, criticalityConverter, valueConverter});
//
//        CompositeConverter CONV = new AnnotationCompositeConverter(WriteReplaceWarningRequest.class);
//        AsnConverter protocolIEsConverter = new VectorConverter(ProtocolCONV);
//        CONV.setComponentConverters(new AsnConverter[]{protocolIEsConverter});
//        AsnConverter converter = CONV;
//        AsnConverter converter = ngap_pdu.CONV;
        AsnConverter converter = NGAP_PDU.CONV;

        System.out.println("======== encode ========");
        Buffer buffer = Buffer.allocate(1024, EncodingRules.BASIC_ENCODING_RULES);
        NGAP_PDU.TYPE.print(ngap_pdu, converter, System.out);
        NGAP_PDU.TYPE.encode(ngap_pdu, buffer, converter);
//        NGAP_PDU.TYPE.print(ngap_pdu, NGAP_PDU.CONV, System.out);
//        NGAP_PDU.TYPE.encode(ngap_pdu, buffer, NGAP_PDU.CONV);
        byte[] bytes = buffer.array();


        return bytes;
    }


    public static byte[] encodeString(NGAP_PDU ngap_pdu) {
        byte[] bytes = encode(ngap_pdu);
        StringBuffer str = new StringBuffer();
        for(byte b : bytes) {
            str.append( String.format("%02X", b & 0xFF));
        }
        return bytes;
    };



    public static NGAP_PDU decode(byte[] bytes){
        System.out.println("======== decode ========");
        Buffer buffer2 = Buffer.wrap(bytes, EncodingRules.BASIC_ENCODING_RULES);
//        Buffer buffer2 = Buffer.wrap(bytes, EncodingRules.DISTINGUISHED_ENCODING_RULES);
        System.out.println(buffer2.toString());



        CompositeConverter CONVERTER = new AnnotationCompositeConverter(WriteReplaceWarningRequest.class);
//        AsnConverter header_onlyConverter = ;
//        AsnConverter lockConverter = BooleanConverter.INSTANCE;
//        AsnConverter accept_typesConverter = AcceptTypes.CONVERTER;
//        AsnConverter urlConverter = Url.CONVERTER;
//        AsnConverter timestampConverter = DateConverter.INSTANCE;
//        CONVERTER.setComponentConverters(new AsnConverter[] {
//                MessageIdentifier.CONV,
//                SerialNumber.CONV,
//                TAIListForWarning.CONV,
//                RepetitionPeriod.CONV,
//        });




//        AsnConverter converter = CONVERTER;
//        CompositeConverter WarningAreaListCONV = new AnnotationCompositeConverter(WarningAreaList.class);
//        AsnConverter tAIListForWarningConverter = TAIListForWarning.CONV;
//        WarningAreaListCONV.setComponentConverters(new AsnConverter[]{tAIListForWarningConverter});
        ChoiceConverter WarningAreaListCONV = new AnnotationChoiceConverter(WarningAreaList.class);
        AsnConverter eUTRA_CGIListForWarningConverter = EUTRA_CGIListForWarning.CONV;
        AsnConverter nR_CGIListForWarningConverter = NR_CGIListForWarning.CONV;
        AsnConverter tAIListForWarningConverter = TAIListForWarning.CONV;
        AsnConverter emergencyAreaIDListConverter = EmergencyAreaIDList.CONV;
        AsnConverter choice_ExtensionsConverter = WarningAreaList_Choice_Extensions.CONV;
        WarningAreaListCONV.setAlternativeConverters(new AsnConverter[]{tAIListForWarningConverter});


        CompositeConverter ProtocolCONV = new AnnotationCompositeConverter(WriteReplaceWarningRequest_ProtocolIEs.class);
        AsnConverter idConverter = ProtocolIE_ID.CONV;
        AsnConverter criticalityConverter = Criticality.CONV;
        AsnConverter valueConverter = new OpenValueConverter(NGAP_PROTOCOL_IES.WriteReplaceWarningRequestIEs, NGAP_PROTOCOL_IES.CONV, new Object[]{MessageIdentifier.CONV, SerialNumber.CONV, WarningAreaListCONV, RepetitionPeriod.CONV, NumberOfBroadcastsRequested.CONV, WarningType.CONV, WarningSecurityInfo.CONV, DataCodingScheme.CONV, WarningMessageContents.CONV, ConcurrentWarningMessageInd.CONV, WarningAreaCoordinates.CONV});
        ProtocolCONV.setComponentConverters(new AsnConverter[]{idConverter, criticalityConverter, valueConverter});


        CompositeConverter WriteReplaceWarningCONV = new AnnotationCompositeConverter(WriteReplaceWarningRequest.class);
        AsnConverter protocolIEsConverter = new VectorConverter(ProtocolCONV);
        WriteReplaceWarningCONV.setComponentConverters(new AsnConverter[]{protocolIEsConverter});


        CompositeConverter InitiatingCONV = new AnnotationCompositeConverter(InitiatingMessage.class);
        AsnConverter procedureCodeConverter = ProcedureCode.CONV;
        AsnConverter criticalityConverter2 = Criticality.CONV;
//        AsnConverter valueConverter2 = new OpenValueConverter(NGAP_ELEMENTARY_PROCEDURE.NGAP_ELEMENTARY_PROCEDURES, NGAP_ELEMENTARY_PROCEDURE.CONV, new Object[]{AMFConfigurationUpdate.CONV, HandoverCancel.CONV, HandoverRequired.CONV, HandoverRequest.CONV, InitialContextSetupRequest.CONV, NGReset.CONV, NGSetupRequest.CONV, PathSwitchRequest.CONV, PDUSessionResourceModifyRequest.CONV, PDUSessionResourceModifyIndication.CONV, PDUSessionResourceReleaseCommand.CONV, PDUSessionResourceSetupRequest.CONV, PWSCancelRequest.CONV, RANConfigurationUpdate.CONV, UEContextModificationRequest.CONV, UEContextReleaseCommand.CONV, UERadioCapabilityCheckRequest.CONV, WriteReplaceWarningRequest.CONV, AMFStatusIndication.CONV, CellTrafficTrace.CONV, DeactivateTrace.CONV, DownlinkNASTransport.CONV, DownlinkNonUEAssociatedNRPPaTransport.CONV, DownlinkRANConfigurationTransfer.CONV, DownlinkRANStatusTransfer.CONV, DownlinkUEAssociatedNRPPaTransport.CONV, ErrorIndication.CONV, HandoverNotify.CONV, InitialUEMessage.CONV, LocationReport.CONV, LocationReportingControl.CONV, LocationReportingFailureIndication.CONV, NASNonDeliveryIndication.CONV, OverloadStart.CONV, OverloadStop.CONV, Paging.CONV, PDUSessionResourceNotify.CONV, PrivateMessage.CONV, PWSFailureIndication.CONV, PWSRestartIndication.CONV, RerouteNASRequest.CONV, RRCInactiveTransitionReport.CONV, SecondaryRATDataUsageReport.CONV, TraceFailureIndication.CONV, TraceStart.CONV, UEContextReleaseRequest.CONV, UERadioCapabilityInfoIndication.CONV, UETNLABindingReleaseRequest.CONV, UplinkNASTransport.CONV, UplinkNonUEAssociatedNRPPaTransport.CONV, UplinkRANConfigurationTransfer.CONV, UplinkRANStatusTransfer.CONV, UplinkUEAssociatedNRPPaTransport.CONV});
        AsnConverter valueConverter2 = new OpenValueConverter(NGAP_ELEMENTARY_PROCEDURE.NGAP_ELEMENTARY_PROCEDURES, NGAP_ELEMENTARY_PROCEDURE.CONV, new Object[]{AMFConfigurationUpdate.CONV, HandoverCancel.CONV, HandoverRequired.CONV, HandoverRequest.CONV, InitialContextSetupRequest.CONV, NGReset.CONV, NGSetupRequest.CONV, PathSwitchRequest.CONV, PDUSessionResourceModifyRequest.CONV, PDUSessionResourceModifyIndication.CONV, PDUSessionResourceReleaseCommand.CONV, PDUSessionResourceSetupRequest.CONV, PWSCancelRequest.CONV, RANConfigurationUpdate.CONV, UEContextModificationRequest.CONV, UEContextReleaseCommand.CONV, UERadioCapabilityCheckRequest.CONV, WriteReplaceWarningCONV, AMFStatusIndication.CONV, CellTrafficTrace.CONV, DeactivateTrace.CONV, DownlinkNASTransport.CONV, DownlinkNonUEAssociatedNRPPaTransport.CONV, DownlinkRANConfigurationTransfer.CONV, DownlinkRANStatusTransfer.CONV, DownlinkUEAssociatedNRPPaTransport.CONV, ErrorIndication.CONV, HandoverNotify.CONV, InitialUEMessage.CONV, LocationReport.CONV, LocationReportingControl.CONV, LocationReportingFailureIndication.CONV, NASNonDeliveryIndication.CONV, OverloadStart.CONV, OverloadStop.CONV, Paging.CONV, PDUSessionResourceNotify.CONV, PrivateMessage.CONV, PWSFailureIndication.CONV, PWSRestartIndication.CONV, RerouteNASRequest.CONV, RRCInactiveTransitionReport.CONV, SecondaryRATDataUsageReport.CONV, TraceFailureIndication.CONV, TraceStart.CONV, UEContextReleaseRequest.CONV, UERadioCapabilityInfoIndication.CONV, UETNLABindingReleaseRequest.CONV, UplinkNASTransport.CONV, UplinkNonUEAssociatedNRPPaTransport.CONV, UplinkRANConfigurationTransfer.CONV, UplinkRANStatusTransfer.CONV, UplinkUEAssociatedNRPPaTransport.CONV});
//        AsnConverter valueConverter2 = new OpenValueConverter(NGAP_ELEMENTARY_PROCEDURE.NGAP_ELEMENTARY_PROCEDURES, NGAP_ELEMENTARY_PROCEDURE.CONV, new Object[]{WriteReplaceWarningCONV});
//        InitiatingCONV.setComponentConverters(new AsnConverter[]{procedureCodeConverter, criticalityConverter2, valueConverter2});
        InitiatingCONV.setComponentConverters(new AsnConverter[]{IntegerConverter.INSTANCE, criticalityConverter2, valueConverter2});



        ChoiceConverter CONV = new AnnotationChoiceConverter(NGAP_PDU.class);
        AsnConverter initiatingMessageConverter = InitiatingMessage.CONV;
        AsnConverter successfulOutcomeConverter = SuccessfulOutcome.CONV;
        AsnConverter unsuccessfulOutcomeConverter = UnsuccessfulOutcome.CONV;
//        CONV.setAlternativeConverters(new AsnConverter[]{InitiatingCONV, successfulOutcomeConverter, unsuccessfulOutcomeConverter});
//        CONV.setAlternativeConverters(new AsnConverter[]{initiatingMessageConverter, successfulOutcomeConverter, unsuccessfulOutcomeConverter});
        CONV.setAlternativeConverters(new AsnConverter[]{initiatingMessageConverter});
        CONV.setAlternativeConverters(new AsnConverter[]{InitiatingCONV});

//        AsnConverter componentConverter = WriteReplaceWarningRequest.CONV.getComponentConverter(0);
//        AsnConverter converter = InitiatingCONV;

        //		ProtocolIEs
        ChoiceConverter converter = NGAP_PDU.CONV;
//        ChoiceConverter converter = CONV;
//        AsnConverter converter = TAIListForWarning.CONV;
//        CompositeConverter converter = WriteReplaceWarningTaiRequest.CONV;
//        NGAP_PDU newValue = (NGAP_PDU) NGAP_PDU.TYPE.decode(buffer2, WriteReplaceWarningTaiRequest.CONV);
//        NGAP_PDU newValue = (NGAP_PDU) NGAP_PDU.TYPE.decode(buffer2, converter);
//        NGAP_PDU.TYPE.decode(buffer2, converter);
//        NGAP_PDU newValue = (NGAP_PDU) WriteReplaceWarningRequest.TYPE.decode(buffer2, converter);
        NGAP_PDU newValue = (NGAP_PDU) NGAP_PDU.TYPE.decode(buffer2, NGAP_PDU.CONV);
//        NGAP_PDU newValue = (NGAP_PDU) NGAP_PDU.TYPE.decode(buffer2, WriteReplaceWarningRequest.CONV);
//        NGAP_PDU newValue = (NGAP_PDU) NGAP_PDU.TYPE.decode(buffer2, WriteReplaceWarningRequest.CONV);
        NGAP_PDU.TYPE.print(newValue, converter, System.out);
//        System.out.println();
//        NGAP_PDU newValue = (NGAP_PDU) NGAP_PDU.TYPE.decode(buffer2, NGAP_PDU.CONV);
//        NGAP_PDU.TYPE.print(newValue, NGAP_PDU.CONV, System.out);
//        System.out.println();
        return newValue;
//        return null;
    }
}
