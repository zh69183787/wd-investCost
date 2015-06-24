package com.wonders.webservice.contract;

@javax.jws.WebService(targetNamespace = "http://contract.webservice.wonders.com/", serviceName = "ContractConnectorService", portName = "ContractConnectorPort", wsdlLocation = "WEB-INF/wsdl/ContractConnectorService.wsdl")
public class ContractConnectorDelegate {

	com.wonders.webservice.contract.ContractConnector contractConnector = new com.wonders.webservice.contract.ContractConnector();

	public String saveContract(String contractXML, String secret) {
		return contractConnector.saveContract(contractXML, secret);
	}

}