package egovframework.edutrack.modules.log.message.service;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessException;

import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.notification.MessageNotificationException;

public interface LogMsgMngService {

	/**
	 * 과정 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CourseVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	public abstract  ProcessResultListVO<LogMsgMngVO> listMessageMainPageing(
			LogMsgMngVO VO, int curPage, int listScale) throws Exception;
	
	/**
	 * 개설 과정 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CourseVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<CourseVO>
	 */
	public abstract  ProcessResultListVO<LogMsgMngVO> listMessageMainPageing(
			LogMsgMngVO VO, int curPage, int listScale, int pageScale) throws Exception;
	
	/**
	 * 메시지 정보 조회
	 * @param LogMsgMngVO
	 * @return ProcessResultVO<LogMsgMngVO>
	 */
	public abstract ProcessResultVO<LogMsgMngVO> view(LogMsgMngVO VO) throws Exception;
	
	/**
	 * 메시지 전송 정보 리스트
	 * @param LogMsgMngVO
	 * @return ProcessResultListVO<LogMsgMngVO>
	 */
	public abstract ProcessResultListVO<LogMsgMngVO> listMsgTrans(LogMsgMngVO vo);
}