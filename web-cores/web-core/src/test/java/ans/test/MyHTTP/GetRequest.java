package ans.test.MyHTTP;

import org.asnlab.asndt.runtime.conv.*;
import org.asnlab.asndt.runtime.conv.annotation.Component;
import org.asnlab.asndt.runtime.type.AsnType;

import java.io.PrintStream;
import java.util.Date;

public class GetRequest {

	@Component(0)
	public Boolean header_only;

	@Component(1)
	public Boolean lock;

	@Component(2)
	public AcceptTypes accept_types;

	@Component(3)
	public String url;

	@Component(4)
	public Date timestamp;


	public boolean equals(Object obj) {
		if(!(obj instanceof GetRequest)){
			return false;
		}
		return TYPE.equals(this, obj, CONVERTER);
	}

	public void print(PrintStream out) {
		TYPE.print(this, CONVERTER, out);
	}


	public final static AsnType TYPE = MyHTTP.type(65540);

	public final static CompositeConverter CONVERTER;

	static {
		CONVERTER = new AnnotationCompositeConverter(GetRequest.class);
		AsnConverter header_onlyConverter = BooleanConverter.INSTANCE;
		AsnConverter lockConverter = BooleanConverter.INSTANCE;
		AsnConverter accept_typesConverter = AcceptTypes.CONVERTER;
		AsnConverter urlConverter = Url.CONVERTER;
		AsnConverter timestampConverter = DateConverter.INSTANCE;
		CONVERTER.setComponentConverters(new AsnConverter[] { header_onlyConverter, lockConverter, accept_typesConverter, urlConverter, timestampConverter });
	}

	public final static GetRequest myRequest = (GetRequest)MyHTTP.value(65541,CONVERTER);


}
