package egovframework.edutrack.comm.web.taglib;

public class ElFunctionString {

	public static String cutString(String value, int len) {
		try {
			String retVal = "";
			if(value == null || "".equals(value)) return "";
			if(len > value.length()) retVal = value;
			else retVal = value.substring(0, len)+"...";			
			return retVal;
		} catch (Exception ex) {
			return "";
		}
	}
	
}
