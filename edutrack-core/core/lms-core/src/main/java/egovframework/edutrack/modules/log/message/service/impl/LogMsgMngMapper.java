package egovframework.edutrack.modules.log.message.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.log.message.service.LogMsgMngVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 *  <b>로그 - 메세지 로그 관리</b> 영역  Mapper 클래스
 * @author Jamfam
 *
 */
@Mapper("logMsgMngMapper")
public interface LogMsgMngMapper {
	
	/**
	 * 메시지 관리 Pageing 목록 조회
	 *
	 * @return List<LogMsgMngVO>
	 */
	
	public List<LogMsgMngVO> listMessageMainPageing(LogMsgMngVO ilogMsgMngVO)   ;
	
	/**
	 * 메세지 관리 Pageing 목록 조회
	 *
	 * @return int
	 */
	
	public int listMessageMainPageingCount (LogMsgMngVO ilogMsgMngVO)   ;
	
	public LogMsgMngVO select(LogMsgMngVO VO)  ;
	
	/**
	 * 메시지 관리 Pageing 목록 조회
	 *
	 * @return List<LogMsgMngVO>
	 */
	
	public List<EgovMap> listRecvStudent(LogMsgMngVO ilogMsgMngVO)   ;
	
	public List<LogMsgMngVO> listMsgTrans(LogMsgMngVO vo);
	
	
}
