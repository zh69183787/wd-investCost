package com.wonders.ic.quantitiesSubject.entity.bo;

public class WbsEstimateSubject {

	private String subjectId;	//科目主键
	private String subjectName;	//科目名称
	private String sum;			//总价
	private String wbsId;		//wbsId编号，不是主键
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getSum() {
		return sum;
	}
	public void setSum(String sum) {
		this.sum = sum;
	}
	public String getWbsId() {
		return wbsId;
	}
	public void setWbsId(String wbsId) {
		this.wbsId = wbsId;
	}
	
	
}
