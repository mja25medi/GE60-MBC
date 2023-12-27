package egovframework.edutrack.modules.kollus.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import egovframework.edutrack.comm.util.web.BeanUtils;


public class KollusResultVO implements Serializable {

	private static final long serialVersionUID = -5046472814960100337L;
	private Integer error;
	private String  message;
	private Map<String, Object> result;

	public KollusResultVO() {
		result = new HashMap<String, Object>();
	}

	public Integer getError() {
		return error;
	}

	public void setError(Integer error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public void addResult(String key, Object value) {
		this.result.put(key, value);
	}

	public void addResult(Object obj) {
		BeanUtils.mergeBean(this.result, obj);
	}
}
