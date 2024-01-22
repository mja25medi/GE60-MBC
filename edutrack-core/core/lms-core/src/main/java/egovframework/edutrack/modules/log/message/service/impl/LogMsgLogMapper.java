package egovframework.edutrack.modules.log.message.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.log.message.service.LogMsgLogVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>로그 - 메세지 로그 관리</b> 영역  Mapper 클래스
 * @author Jamfam
 *
 */
@Mapper("logMsgLogMapper")
public interface LogMsgLogMapper {

    /**
	 * 메시지의 전체 목록을 조회한다. 
	 * @param  LogMsgLogVO 
	 * @return List
	 * @
	 */
	
	public List<LogMsgLogVO> list(LogMsgLogVO vo) ;
	
    /**
	 * 메시지의 검색된 수를 카운트 한다. 
	 * @param  LogMsgLogVO 
	 * @return int
	 * @
	 */
	public int count(LogMsgLogVO vo) ;
	
    /**
	 * 메시지의 전체 목록을 조회한다. 
	 * @param  LogMsgLogVO 
	 * @return List
	 * @
	 */
	
	public List<LogMsgLogVO> listPageing(LogMsgLogVO vo) ;
	
    /**
	 * 메시지의 상세 정보를 조회한다. 
	 * @param  LogMsgLogVO 
	 * @return LogMsgLogVO
	 * @
	 */
	public LogMsgLogVO select(LogMsgLogVO vo) ;
	
   /**
	 * 메시지의 키를 생성한다. 
	 * @param  LogMsgLogVO 
	 * @return LogMsgLogVO
	 * @
	 */
	public int selectKey() ;

    /**
     * 메시지의 상세 정보를 등록한다.  
     * @param  LogMsgLogVO
     * @return String
     * @
     */
    public int insert(LogMsgLogVO vo) ;
    
    /**
     * 메시지의 상세 정보를 재등록한다.  
     * @param  LogMsgLogVO
     * @return String
     * @
     */
    public int reinsert(LogMsgLogVO vo) ;
    
    /**
     * 메시지의 상세 정보를 수정한다. 
     * @param  LogMsgLogVO
     * @return int
     * @
     */
    public int update(LogMsgLogVO vo) ;
    
    /**
     * 메시지의 상세 정보를 삭제한다.  
     * @param  LogMsgLogVO
     * @return int
     * @
     */
    public int delete(LogMsgLogVO vo) ;
    
    /**
	 * 보낸쪽지함 검색된 수를 카운트 한다. 
	 * @param  LogMsgLogVO 
	 * @return int
	 * @
	 */
	public int countSendMsg(LogMsgLogVO vo) ;
	
    /**
	 * 보낸쪽지함 전체 목록을 조회한다. 
	 * @param  LogMsgLogVO 
	 * @return List
	 * @
	 */
	
	public List<LogMsgLogVO> listSendMsgPageing(LogMsgLogVO vo) ;
	
    /**
	 * 받은쪽지함 검색된 수를 카운트 한다. 
	 * @param  LogMsgLogVO 
	 * @return int
	 * @
	 */
	public int countRecvMsg(LogMsgLogVO vo) ;
	
    /**
	 * 받은쪽지함 전체 목록을 조회한다. 
	 * @param  LogMsgLogVO 
	 * @return List
	 * @
	 */
	
	public List<LogMsgLogVO> listRecvMsgPageing(LogMsgLogVO vo) ;
	
	/**
	 * 받은쪽지함 전체 목록을 조회한다. 
	 * @param  LogMsgLogVO 
	 * @return List
	 * @
	 */
	
	public List<LogMsgLogVO> listTopRecvMsg(LogMsgLogVO vo) ;
	
    /**
	 * 읽지 않은 쪽지수를 카운트 한다.
	 * @param  LogMsgLogVO 
	 * @return int
	 * @
	 */
	public int countMsg(LogMsgLogVO vo) ;
	
    /**
	 * [CRM] 메시지 종류 별 카운트
	 * @param  LogMsgLogVO 
	 * @return int
	 */
	public int countForCRM(LogMsgLogVO vo);

    /**
	 * [CRM] 메시지 종류 별 받은 내역
	 * @param  LogMsgLogVO 
	 * @return List
	 */
	public List<LogMsgLogVO> listPageingForCRM(LogMsgLogVO vo); 

   /**
	 * 메시지 그룹의 키를 생성한다. 
	 * @
	 */
	public int selectKeyGrpSn();
}
