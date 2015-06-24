package com.wonders.webservice.contract.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="saveContractResponse", namespace="http://contract.webservice.wonders.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="saveContractResponse", namespace="http://contract.webservice.wonders.com/")
public class SaveContractResponse
{

  @XmlElement(name="return", namespace="")
  private String _return;

  public String getReturn()
  {
    return this._return;
  }

  public void setReturn(String _return)
  {
    this._return = _return;
  }
}