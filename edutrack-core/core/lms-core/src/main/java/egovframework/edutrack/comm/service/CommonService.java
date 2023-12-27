package egovframework.edutrack.comm.service;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public interface CommonService {

	/**
	 * 데이터베이스의 시간을 {@code Date}로 반환한다.
	 * @return
	 */
	public abstract Date getCurrentDBTimeToDate() throws Exception;

	/**
	 * 데이터베이스의 시간을 {@code Calendar}로 반환한다.
	 * @return
	 */
	public abstract Calendar getCurrentDBTimeToCalendar() throws Exception;

	/**
	 * 데이터베이스의 시간을 {@code String}으로 "yyyyMMddHHmmss"로 반환한다.
	 * @return
	 */
	public abstract String getCurrentDBTime() throws Exception;

	/**
	 * 데이터베이스의 시간을 {@code String}으로 패턴에 맞게 반환한다.
	 * @param datePattern {@link SimpleDateFormat}의 날짜 패턴
	 * @return
	 */
	public abstract String getCurrentDBTime(String datePattern)
			throws Exception;

}