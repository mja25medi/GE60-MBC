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
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<LogMsgLogVO> list(LogMsgLogVO vo) throws Exception;
	
    /**
	 * 메시지의 검색된 수를 카운트 한다. 
	 * @param  LogMsgLogVO 
	 * @return int
	 * @throws Exception
	 */
	public int count(LogMsgLogVO vo) throws Exception;
	
    /**
	 * 메시지의 전체 목록을 조회한다. 
	 * @param  LogMsgLogVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<LogMsgLogVO> listPageing(LogMsgLogVO vo) throws Exception;
	
    /**
	 * 메시지의 상세 정보를 조회한다. 
	 * @param  LogMsgLogVO 
	 * @return LogMsgLogVO
	 * @throws Exception
	 */
	public LogMsgLogVO select(LogMsgLogVO vo) throws Exception;
	
   /**
	 * 메시지의 키를 생성한다. 
	 * @param  LogMsgLogVO 
	 * @return LogMsgLogVO
	 * @throws Exception
	 */
	public int selectKey() throws Exception;

    /**
     * 메시지의 상세 정보를 등록한다.  
     * @param  LogMsgLogVO
     * @return String
     * @throws Exception
     */
    public int insert(LogMsgLogVO vo) throws Exception;
    
    /**
     * 메시지의 상세 정보를 재등록한다.  
     * @param  LogMsgLogVO
     * @return String
     * @throws Exception
     */
    public int reinsert(LogMsgLogVO vo) throws Exception;
    
    /**
     * 메시지의 상세 정보를 수정한다. 
     * @param  LogMsgLogVO
     * @return int
     * @throws Exception
     */
    public int update(LogMsgLogVO vo) throws Exception;
    
    /**
     * 메시지의 상세 정보를 삭제한다.  
     * @param  LogMsgLogVO
     * @return int
     * @throws Exception
     */
    public int delete(LogMsgLogVO vo) throws Exception;
    
    /**
	 * 보낸쪽지함 검색된 수를 카운트 한다. 
	 * @param  LogMsgLogVO 
	 * @return int
	 * @throws Exception
	 */
	public int countSendMsg(LogMsgLogVO vo) throws Exception;
	
    /**
	 * 보낸쪽지함 전체 목록을 조회한다. 
	 * @param  LogMsgLogVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<LogMsgLogVO> listSendMsgPageing(LogMsgLogVO vo) throws Exception;
	
    /**
	 * 받은쪽지함 검색된 수를 카운트 한다. 
	 * @param  LogMsgLogVO 
	 * @return int
	 * @throws Exception
	 */
	public int countRecvMsg(LogMsgLogVO vo) throws Exception;
	
    /**
	 * 받은쪽지함 전체 목록을 조회한다. 
	 * @param  LogMsgLogVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<LogMsgLogVO> listRecvMsgPageing(LogMsgLogVO vo) throws Exception;
	
	/**
	 * 받은쪽지함 전체 목록을 조회한다. 
	 * @param  LogMsgLogVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<LogMsgLogVO> listTopRecvMsg(LogMsgLogVO vo) throws Exception;
	
    /**
	 * 읽지 않은 쪽지수를 카운트 한다.
	 * @param  LogMsgLogVO 
	 * @return int
	 * @throws Exception
	 */
	public int countMsg(LogMsgLogVO vo) throws Exception;
	
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
	 * @throws Exception
	 */
	public int selectKeyGrpSn();
}
