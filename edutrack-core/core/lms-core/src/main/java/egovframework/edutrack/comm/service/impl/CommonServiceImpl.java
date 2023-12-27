package egovframework.edutrack.comm.service.impl;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.service.CommonService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 *  <b>공통 - 공통 서비스</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("commonService")
public class CommonServiceImpl 
	extends EgovAbstractServiceImpl implements CommonService {

	@Resource(name="commonMapper")
	private CommonMapper 		commonMapper;
	
	/**
	 * 데이터베이스의 시간을 {@code Date}로 반환한다.
	 * @return
	 */
	@Override
	public Date getCurrentDBTimeToDate() throws Exception {
		return (Date)commonMapper.selectCurrentDbTime();
	}
	
	/**
	 * 데이터베이스의 시간을 {@code Calendar}로 반환한다.
	 * @return
	 */
	@Override
	public Calendar getCurrentDBTimeToCalendar() throws Exception{
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone("GMT+09:00"));
		calendar.setTime(getCurrentDBTimeToDate());
		return calendar;
	}
	
	/**
	 * 데이터베이스의 시간을 {@code String}으로 "yyyyMMddHHmmss"로 반환한다.
	 * @return
	 */
	@Override
	public String getCurrentDBTime() throws Exception {
		return getCurrentDBTime("yyyyMMddHHmmss");
	}
	
	/**
	 * 데이터베이스의 시간을 {@code String}으로 패턴에 맞게 반환한다.
	 * @param datePattern {@link SimpleDateFormat}의 날짜 패턴
	 * @return
	 */
	@Override
	public String getCurrentDBTime(String datePattern) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat(datePattern);
		return formatter.format(getCurrentDBTimeToDate());
	}
}
