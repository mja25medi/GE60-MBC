package egovframework.edutrack.comm.code;

public interface CodeType {
	public String getCodeTypeCd();
	public String getCodeTypeNm();
	
	public default boolean hasValue() {
		if(this.getCodeTypeNm().equals("")) return false;
		return true;
	}
}
