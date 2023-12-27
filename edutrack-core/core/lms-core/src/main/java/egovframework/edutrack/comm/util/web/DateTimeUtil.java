package egovframework.edutrack.comm.util.web;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


/**
 * 	현재 시각 또는 'YYYYMMDD24HHMISS' 형태의 문자열을 이용하여
 * 	'YYYY/MM/DD 24HH:MI:SS' 형태의 문자열 변환
 *
 */

/**
 * @author 장철웅
 *
 */
public class DateTimeUtil {

	private static final String DATE_GUBUN = "-";
	private static final String TIME_GUBUN = ":";
	
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
	/**
	 * 현재 시각을 Date()로 반환
	 * @return
	 */
	public static Date getCurrentDate() {
		//ICommonService service = SpringStaticContext.getInstance().getContext()
		//	.getBean(ICommonService.class);
		//return service.getCurrentDBTimeToDate();
		return new Date();
	}

	/**
	 * 현재 시각을 Calendar()로 반환
	 * @return
	 */
	public static Calendar getCurrentCalendar() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone("GMT+09:00"));
		calendar.setTime(getCurrentDate());
		return calendar;
	}

	/**
	 * 현재 시각을 String으로 반환
	 * @return
	 */
	public static String getCurrentString() {
		return getCurrentString("yyyyMMddHHmmss");
		//ICommonService service = SpringStaticContext.getInstance().getContext()
		//.getBean(ICommonService.class);
		//return service.getCurrentDBTime();
	}

	public static String getCurrentString(String datePattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(datePattern);
		return formatter.format(getCurrentDate());
	}

	/**
	 * 현재 시간을 돌려준다. - YYYYMMDDHHMMSS
	 */
	public static String getDateTime(){
		return getCurrentString();
	}

	/**
	 * 현재 시간 반환 - HHMMSS
	 * @param request
	 * @return
	 */
	public static String getTime() {
		return getCurrentString().substring(8,14);
	}

	/**
	 * 현재 날짜 반환 - YYYYMMDD
	 * @param request
	 * @return
	 */
	public static String getDate() {
		return getCurrentString().substring(0,8);
	}

	/**
	 * 년도 반환
	 * @return
	 */
	public static String getYear() {
		return getCurrentString().substring(0,4);
	}

	/**
	 * 월 반환
	 * @return
	 */
	public static String getMonth() {
		return getCurrentString().substring(4,6);
	}

	/**
	 * 일 반환
	 * @return
	 */
	public static String getDay() {
		return getCurrentString().substring(6,8);
	}


	/**
	 * 현재 년월일을 돌려준다. - YYYY.MM.DD
	 * TYPE 1 : YYYY.MM.DD
	 * TYPE 2 : YY.MM.DD
	 * TYPE 3 : MM.DD
	 * TYPE 4 : YYYY.MM
	 * TYPE 5 : YYYY
	 */
	public static String getDateText(int type, String szdate){
	    return getDateText(type, szdate,DATE_GUBUN);
	}

	public static String getDateText(int type, String szdate,String delimeter) {

		if(szdate != null && szdate.length() != 8) {
			return "";
		}
		if(szdate != null && szdate.length() == 8){
			String year = szdate.substring(0, 4);
			String month = szdate.substring(4, 6);
			String day = szdate.substring(6, 8);

			switch(type) {
		        case 1:
		            return  year + delimeter + month + delimeter + day;
		        case 2:
		            return  year.substring(2, 4) + delimeter + month + delimeter + day;
		        case 3:
		            return month + delimeter + day;
		        case 4:
		            return  year + delimeter + month;
		        case 5:
		            return year;
	       }
		}

		return "";
	}

	/**
	 * 특정형태의 날자 타입을 돌려준다.
	 * TYPE 0 : YYYY.MM.DD HH:MI:SS
	 * TYPE 1 : YYYY.MM.DD
	 * TYPE 2 : YY.MM.DD
	 * TYPE 3 : MM.DD
	 * TYPE 4 : YYYY.MM
	 * TYPE 5 : YYYY
	 * TYPE 6 : MM.DD HH:MI
	 * TYPE 7 : HH:MI
     * @param type
	 * @param dateTime
	 * @return
	 */
	public static String getDateType(int type, String date) {
		return getDateType(type, date, DATE_GUBUN);
	}

	public static String getDateType(int type, String date, String delimeter) {

		if (date == null) {
			return "";
		}

		if(date.length() == 12) date += "01";
	    else if(date.length() == 10) date += "0101";
        else if(date.length() == 8) date += "010101";
        else if(date.length() == 6) date += "01010101";
        else if(date.length() == 4) date += "0101010101";

		//2015.11.04 김현욱 수정 type 9 추가(시험관리에서 일시까지 보여줌)
        switch(type) {
	        case 0:
	            return getDateText(1,StringUtil.substring(date, 0, 8), delimeter) + " " + getTimeText(1,StringUtil.substring(date, 8, 14));
	        case 1:
	            return getDateText(1,StringUtil.substring(date, 0, 8), delimeter);
	        case 2:
	            return getDateText(2,StringUtil.substring(date, 0, 8), delimeter);
	        case 3:
	            return getDateText(3,StringUtil.substring(date, 0, 8), delimeter);
	        case 4:
	            return getDateText(4,StringUtil.substring(date, 0, 8), delimeter);
	        case 5:
	            return getDateText(5,StringUtil.substring(date, 0, 8), delimeter);
	        case 6:
	            return getDateText(3,StringUtil.substring(date, 0, 8), delimeter) + " " + getTimeText(2,StringUtil.substring(date, 8, 14));
	        case 7:
	            return getTimeText(2,StringUtil.substring(date, 8, 14));
	        case 8:
	        	return getDateText(1,StringUtil.substring(date, 0, 8), delimeter) + " " + getTimeText(2,StringUtil.substring(date, 8, 14));
	        case 9:
	        	return getDateText(1,StringUtil.substring(date, 0, 8), delimeter) + " " + getTimeText(3,StringUtil.substring(date, 8, 14));
        }

        return "";
	}

	/**
	 * 현재 시간을 돌려준다. - HH:MI:SS
	 */
	public static String getTimeText(int type, String szTime) {
		if(szTime != null && szTime.length() != 6) {
			return "";
		}
		if(szTime != null && szTime.length() == 6){
			String hour = StringUtil.substring(szTime,0, 2);
			String minute = StringUtil.substring(szTime, 2, 4);
			String second = StringUtil.substring(szTime,4, 6);

			switch(type) {
				case 1:
					return hour + TIME_GUBUN + minute + TIME_GUBUN+ second;
				case 2:
					return  hour + TIME_GUBUN + minute;
				case 3:
					return  hour;
			}
		}
		return "";
	}


	/**
	 * YYYYMMDD 형태의 텍스트를 Date로 변환
	 * @param strDate
	 * @return
	 */
	public static Date convStrToDate(String strDate) {
		return convStrToCalendar(strDate).getTime();
	}

	/**
	 * YYYYMMDD 형태의 텍스트를 Calendar로 변환
	 * @param strDate
	 * @return
	 */
	public static Calendar convStrToCalendar(String strDate) {
		Calendar cal = Calendar.getInstance();

		int year = Integer.parseInt(strDate.substring(0,4));
		int month = Integer.parseInt(strDate.substring(4,6));
		int date = Integer.parseInt(strDate.substring(6,8));
		cal.setTimeZone(TimeZone.getTimeZone("GMT+09:00"));
		cal.set(year, month - 1, date);
		return cal;
	}

	/**
	 * YYYYMMDD 형태의 텟스트를 받아서 현재시간의 3일 이내 시간인지 비교 결과를 반환.
	 * @param regDttm 생성일 문자열
	 * @param beforeDay 몇일 이내인지 비교하고자 하는 숫자
	 * @return
	 */
	public static boolean isNewer(String regDttm, int beforeDay) {

		try {
			Calendar now = Calendar.getInstance();
				//getCurrentCalendar();
			Calendar cal = DateTimeUtil.convStrToCalendar(regDttm);

			// 현재 시간에서 3일을 감소시킨다.
			now.add(Calendar.DATE, -beforeDay);

			// 비교시간이 3일전 시간보다 크면 true
			if(now.getTimeInMillis() < cal.getTimeInMillis()) return true;

			return false;
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * 현재 시간부터 3일 이내의 시간인지 비교 결과를 반환
	 * @param regDttm
	 * @return
	 */
	public static boolean isNewer(String regDttm) {
		return isNewer(regDttm, 3);
	}

	/**
	 * 몇일 후의 날짜를 가져온다.
	 * @param afterDay 몇일 이후인지 ...
	 * @return
	 */
	public static String afterDate(int afterDay) {
		if(afterDay <= 0 ) {
			afterDay = 30;
		}
		try {
			Calendar now = getCurrentCalendar();

			// 현재 시간에서 날짜를 증가 시킨다.
			now.add(Calendar.DATE, afterDay);

			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			String retVal = formatter.format(now.getTime());
			return retVal;

		} catch (Exception ex) {
			return null;
		}
	}
	
	/**
	 * YYYYMMDDHHmmss 형태의 텟스트를 받아서 현재시간과의 간격을 초로 반환한다.
	 * @param compareDttm 생성일 문자열
	 * @return long
	 */
	public static long getIntervalSecond(String compareDttm) {
		try {
		    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmiss");
		    Date beginDate = formatter.parse(compareDttm);
		    Date endDate = formatter.parse(getCurrentString());
		 
		    long diff = endDate.getTime() - beginDate.getTime();
		    long diffSec = diff / 1000;			

		    return diffSec;
		} catch (Exception ex) {
			return 0;
		}
	}
	
	public static boolean chkTimeNowBetween(String startTime, String endTime) {
		try {
			LocalDateTime start = LocalDateTime.parse(startTime, DATE_TIME_FORMATTER);
			LocalDateTime end = LocalDateTime.parse(endTime, DATE_TIME_FORMATTER);
			LocalDateTime now = LocalDateTime.now();
			return now.isAfter(start) && now.isBefore(end);
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean chkDateTimeNowAfter(String startTime) {
		try {
			LocalDateTime start = LocalDateTime.parse(startTime, DATE_TIME_FORMATTER);
			LocalDateTime now = LocalDateTime.now();
			return now.isAfter(start);
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean chkDateNowAfter(String startDate) {
		try {
			LocalDate start = LocalDate.parse(startDate, DATE_FORMATTER);
			LocalDate now = LocalDate.now();
			return now.isAfter(start);
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static LocalDateTime parseDttmToLocalDateTime(String dttm) {
		try {
			return LocalDateTime.parse(dttm, DATE_TIME_FORMATTER);
		} catch(Exception e) {
			return LocalDateTime.now();
		}
	}
}