package com.appstudio.android.sample.ikeeper_android.entity;


public class User { 
	  final static public String NAME 			= "NAME";
	  final static public String TEL_NO 		= "TEL_NO";
	  final static public String RELATION_MODE 	= "RELATION_MODE";
	  final static public String PAPA_TEL_NO 	= "PAPA_TEL_NO";
	  final static public String MAMA_TEL_NO 	= "MAMA_TEL_NO";
	  final static public String C2DM_APPID 	= "C2DM_APPID";
	  
	  private String name;
	  private String telNo;
	  private String relationMode;
	  private String papaTelNo;
	  private String mamaTelNo;
	  private String c2dmAppid;
	  
	  
	  public String getRelationMode() {
		return relationMode;
	  }
	  public void setRelationMode(String relationMode) {
		this.relationMode = relationMode;
	  }
	  public String getPapaTelNo() {
		return papaTelNo;
	  }
	  public void setPapaTelNo(String papaTelNo) {
		this.papaTelNo = papaTelNo;
	  }
	  public String getMamaTelNo() {
		return mamaTelNo;
	  }
	  public void setMamaTelNo(String mamaTelNo) {
		this.mamaTelNo = mamaTelNo;
	  }
	  public String getC2dmAppid() {
		return c2dmAppid;
	  }
	  public void setC2dmAppid(String c2dmAppid) {
		this.c2dmAppid = c2dmAppid;
	  }
	  public String getTelNo() {
	    return telNo;
	  }
	  public void setTelNo(String telNo) {
	    this.telNo = telNo;
	  }
	  public String getName() {
	    return name;
	  }
	  public void setName(String name) {
	    this.name = name;
	  }

}