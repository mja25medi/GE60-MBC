package egovframework.edutrack.modules.log.message.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.log.message.service.LogMsgLogVO;
import egovframework.edutrack.modules.log.message.service.LogMsgTransLogVO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>로그 - 메세지 전송 로그 관리</b> 영역  Mapper 클래스
 * @author Jamfam
 *
 */
@Mapper("logMsgTransLogMapper")
public interface LogMsgTransLogMapper {

    /**
	 * 메시지 전송의 전체 목록을 조회한다. 
	 * @param  LogMsgTransLogVO 
	 * @return List
	 * @
	 */
	
	public List<LogMsgTransLogVO> list(LogMsgTransLogVO vo) ;
	
    /**
	 * 메시지 전송의 검색된 수를 카운트 한다. 
	 * @param  LogMsgTransLogVO 
	 * @return int
	 * @
	 */
	public int count(LogMsgTransLogVO vo) ;
	
    /**
	 * 메시지 전송의 전체 목록을 조회한다. 
	 * @param  LogMsgTransLogVO 
	 * @return List
	 * @
	 */
	
	public List<LogMsgTransLogVO> listPageing(LogMsgTransLogVO vo) ;
	
    /**
	 * 메시지 전송의 상세 정보를 조회한다. 
	 * @param  LogMsgTransLogVO 
	 * @return LogMsgTransLogVO
	 * @
	 */
	public LogMsgTransLogVO select(LogMsgTransLogVO vo) ;

    /**
     * 메시지 전송의 상세 정보를 등록한다.  
     * @param  LogMsgTransLogVO
     * @return String
     * @
     */
    public int insert(LogMsgTransLogVO vo) ;
    
    /**
     * 메시지 전송의 상세 정보를 재등록한다.  
     * @param  LogMsgTransLogVO
     * @return String
     * @
     */
    public int reinsert(LogMsgTransLogVO vo) ;
    
    /**
     * 메시지 전송의 상세 정보를 수정한다. 
     * @param  LogMsgTransLogVO
     * @return int
     * @
     */
    public int update(LogMsgTransLogVO vo) ;
    
    /**
     * 메시지 전송의 상세 정보를 삭제한다.  
     * @param  LogMsgTransLogVO
     * @return int
     * @
     */
    public int delete(LogMsgTransLogVO vo) ;
    
    /**
     * 메시지 전송의 상세 정보를 삭제한다.  
     * @param  LogMsgTransLogVO
     * @return int
     * @
     */
    public int deleteByRecvAddr(LogMsgTransLogVO vo) ;
    
    /**
	 * 여러 테이블에서 사용자 연락처를 조회해온다. 
	 * @param  LogMsgTransLogVO 
	 * @return List
	 * @
	 */
	
	public List<LogMsgTransLogVO> listReceiver(LogMsgTransLogVO vo) ;
	
	
	public List<LogMsgTransLogVO> listReceiverStd(LogMsgTransLogVO vo) ;
	
    /**
     * 메시지 읽음 표시 
     * @param  LogMsgTransLogVO
     * @return int
     * @
     */
    public int updateRead(LogMsgTransLogVO vo) ;
    
    /**
 	 * 메시지Trans의 키를 생성한다. 
 	 * @param   
 	 * @return 
 	 * @
 	 */
 	public int selectKey() ;
}
