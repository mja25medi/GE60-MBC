package egovframework.edutrack.comm.service;


public class CommStatusVO extends DefaultVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5220117959745968977L;
	private String keyCode;
	private String keyValue;

	public String getKeyCode() {
		return keyCode;
	}
	public void setKeyCode(String keyCode) {
		this.keyCode = keyCode;
	}
	public String getKeyValue() {
		return keyValue;
	}
	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}
}
