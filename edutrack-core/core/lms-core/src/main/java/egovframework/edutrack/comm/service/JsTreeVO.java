package egovframework.edutrack.comm.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import egovframework.edutrack.comm.util.web.BeanUtils;

public class JsTreeVO implements Serializable {

	private static final long serialVersionUID = 653400653375642440L;
	private String 	data = "";
	private String	state = "";
	private Map<String, Object>	attr;

	public JsTreeVO() {
		attr = new HashMap<String, Object>();
	}

	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getState() {
		return state;
	}
	public void setState(int cnt) {
		if(cnt > 0) this.state = "closed";
		else this.state = "";
	}
	public Map<String, Object> getAttr() {
		return attr;
	}
	public void setAttr(HashMap<String, Object> attr) {
		this.attr = attr;
	}
	public void addAttr(String key, Object value) {
		this.attr.put(key, value);
	}
	public void addAttr(Object obj) {
		BeanUtils.mergeBean(this.attr, obj);
	}
}
