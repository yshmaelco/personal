package co.yshmael.xml.jmeter;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

public class Sample {
	
	@XStreamAsAttribute
	private long t; 
	@XStreamAsAttribute
	private long lt;
	@XStreamAsAttribute
	private long ts;
	@XStreamAsAttribute
	private String s;
	@XStreamAsAttribute
	private String lb; 
	@XStreamAsAttribute
	private int rc; 
	@XStreamAsAttribute
	private String rm; 
	@XStreamAsAttribute
	private String tn; 
	@XStreamAsAttribute
	private String dt; 
	@XStreamAsAttribute
	private String by;
	
	private String responseData;
	
	public long getT() {
		return t;
	}
	public void setT(long t) {
		this.t = t;
	}
	public long getLt() {
		return lt;
	}
	public void setLt(long lt) {
		this.lt = lt;
	}
	public long getTs() {
		return ts;
	}
	public void setTs(long ts) {
		this.ts = ts;
	}
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}
	public String getLb() {
		return lb;
	}
	public void setLb(String lb) {
		this.lb = lb;
	}
	public int getRc() {
		return rc;
	}
	public void setRc(int rc) {
		this.rc = rc;
	}
	public String getRm() {
		return rm;
	}
	public void setRm(String rm) {
		this.rm = rm;
	}
	public String getTn() {
		return tn;
	}
	public void setTn(String tn) {
		this.tn = tn;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public String getBy() {
		return by;
	}
	public void setBy(String by) {
		this.by = by;
	}
	public String getResponseData() {
		return responseData;
	}
	public void setResponseData(String responseData) {
		this.responseData = responseData;
	}
	
	

}
