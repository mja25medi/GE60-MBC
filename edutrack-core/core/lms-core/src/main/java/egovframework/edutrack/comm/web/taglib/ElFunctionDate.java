package egovframework.edutrack.comm.web.taglib;

import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.StringUtil;

public class ElFunctionDate {
	
	public static String dateFormat(String date, int type) {
		return dateFormat(date, type, ".");
	}
	
	public static String dateFormat(String date, int type, String delimeter) {
		String retVal = "";
		if(StringUtil.isNotNull(date)) {
			retVal = DateTimeUtil.getDateType(type, date, delimeter);		
		} 
		return retVal;
	}
}