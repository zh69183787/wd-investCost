package com.wonders.webservice.datacenter;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.wonders.stpt.dataExchange package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _ConfirmGetDataInfo_QNAME = new QName(
			"http://dataExchange.webservice.stpt.wondersgroup.com/",
			"confirmGetDataInfo");
	private final static QName _SetDataInfoResponse_QNAME = new QName(
			"http://dataExchange.webservice.stpt.wondersgroup.com/",
			"setDataInfoResponse");
	private final static QName _GetDataInfoResponse_QNAME = new QName(
			"http://dataExchange.webservice.stpt.wondersgroup.com/",
			"getDataInfoResponse");
	private final static QName _ConfirmGetDataInfoResponse_QNAME = new QName(
			"http://dataExchange.webservice.stpt.wondersgroup.com/",
			"confirmGetDataInfoResponse");
	private final static QName _GetDataInfo_QNAME = new QName(
			"http://dataExchange.webservice.stpt.wondersgroup.com/",
			"getDataInfo");
	private final static QName _ConfirmgetDataInfoResponse_QNAME = new QName(
			"http://dataExchange.webservice.stpt.wondersgroup.com/",
			"confirmgetDataInfoResponse");
	private final static QName _SetDataInfo_QNAME = new QName(
			"http://dataExchange.webservice.stpt.wondersgroup.com/",
			"setDataInfo");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: com.wonders.stpt.dataExchange
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link ConfirmGetDataInfo }
	 * 
	 */
	public ConfirmGetDataInfo createConfirmGetDataInfo() {
		return new ConfirmGetDataInfo();
	}

	/**
	 * Create an instance of {@link SetDataInfo }
	 * 
	 */
	public SetDataInfo createSetDataInfo() {
		return new SetDataInfo();
	}

	/**
	 * Create an instance of {@link GetDataInfoResponse }
	 * 
	 */
	public GetDataInfoResponse createGetDataInfoResponse() {
		return new GetDataInfoResponse();
	}

	/**
	 * Create an instance of {@link GetDataInfo }
	 * 
	 */
	public GetDataInfo createGetDataInfo() {
		return new GetDataInfo();
	}

	/**
	 * Create an instance of {@link SetDataInfoResponse }
	 * 
	 */
	public SetDataInfoResponse createSetDataInfoResponse() {
		return new SetDataInfoResponse();
	}

	/**
	 * Create an instance of {@link ConfirmgetDataInfoResponse }
	 * 
	 */
	public ConfirmgetDataInfoResponse createConfirmgetDataInfoResponse() {
		return new ConfirmgetDataInfoResponse();
	}

	/**
	 * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}
	 * {@link ConfirmGetDataInfo }{@code >}
	 *
	 */
	@XmlElementDecl(namespace = "http://dataExchange.webservice.stpt.wondersgroup.com/", name = "confirmGetDataInfo")
	public JAXBElement<ConfirmGetDataInfo> createConfirmGetDataInfo(
			ConfirmGetDataInfo value) {
		return new JAXBElement<ConfirmGetDataInfo>(_ConfirmGetDataInfo_QNAME,
				ConfirmGetDataInfo.class, null, value);
	}

	/**
	 * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}
	 * {@link SetDataInfoResponse }{@code >}
	 *
	 */
	@XmlElementDecl(namespace = "http://dataExchange.webservice.stpt.wondersgroup.com/", name = "setDataInfoResponse")
	public JAXBElement<SetDataInfoResponse> createSetDataInfoResponse(
			SetDataInfoResponse value) {
		return new JAXBElement<SetDataInfoResponse>(_SetDataInfoResponse_QNAME,
				SetDataInfoResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}
	 * {@link GetDataInfoResponse }{@code >}
	 *
	 */
	@XmlElementDecl(namespace = "http://dataExchange.webservice.stpt.wondersgroup.com/", name = "getDataInfoResponse")
	public JAXBElement<GetDataInfoResponse> createGetDataInfoResponse(
			GetDataInfoResponse value) {
		return new JAXBElement<GetDataInfoResponse>(_GetDataInfoResponse_QNAME,
				GetDataInfoResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}
	 * {@link ConfirmgetDataInfoResponse }{@code >}
	 *
	 */
	@XmlElementDecl(namespace = "http://dataExchange.webservice.stpt.wondersgroup.com/", name = "confirmGetDataInfoResponse")
	public JAXBElement<ConfirmgetDataInfoResponse> createConfirmGetDataInfoResponse(
			ConfirmgetDataInfoResponse value) {
		return new JAXBElement<ConfirmgetDataInfoResponse>(
				_ConfirmGetDataInfoResponse_QNAME,
				ConfirmgetDataInfoResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link GetDataInfo }
	 * {@code >}
	 *
	 */
	@XmlElementDecl(namespace = "http://dataExchange.webservice.stpt.wondersgroup.com/", name = "getDataInfo")
	public JAXBElement<GetDataInfo> createGetDataInfo(GetDataInfo value) {
		return new JAXBElement<GetDataInfo>(_GetDataInfo_QNAME,
				GetDataInfo.class, null, value);
	}

	/**
	 * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}
	 * {@link ConfirmgetDataInfoResponse }{@code >}
	 *
	 */
	@XmlElementDecl(namespace = "http://dataExchange.webservice.stpt.wondersgroup.com/", name = "confirmgetDataInfoResponse")
	public JAXBElement<ConfirmgetDataInfoResponse> createConfirmgetDataInfoResponse(
			ConfirmgetDataInfoResponse value) {
		return new JAXBElement<ConfirmgetDataInfoResponse>(
				_ConfirmgetDataInfoResponse_QNAME,
				ConfirmgetDataInfoResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link SetDataInfo }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://dataExchange.webservice.stpt.wondersgroup.com/", name = "setDataInfo")
	public JAXBElement<SetDataInfo> createSetDataInfo(SetDataInfo value) {
		return new JAXBElement<SetDataInfo>(_SetDataInfo_QNAME,
				SetDataInfo.class, null, value);
	}

}
