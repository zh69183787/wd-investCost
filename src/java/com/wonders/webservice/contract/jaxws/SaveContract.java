package com.wonders.webservice.contract.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="saveContract", namespace="http://contract.webservice.wonders.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="saveContract", namespace="http://contract.webservice.wonders.com/", propOrder={"arg0", "arg1"})
public class SaveContract
{

  @XmlElement(name="arg0", namespace="")
  private String arg0;

  @XmlElement(name="arg1", namespace="")
  private String arg1;

  public String getArg0()
  {
    return this.arg0;
  }

  public void setArg0(String arg0)
  {
    this.arg0 = arg0;
  }

  public String getArg1()
  {
    return this.arg1;
  }

  public void setArg1(String arg1)
  {
    this.arg1 = arg1;
  }
}