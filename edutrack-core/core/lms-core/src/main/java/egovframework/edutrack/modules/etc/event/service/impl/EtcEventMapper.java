package egovframework.edutrack.modules.etc.event.service.impl;

import java.util.List;

import egovframework.edutrack.modules.etc.event.service.EtcEventVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>기타 - 기타 이벤트 관리</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("etcEventMapper")
public interface EtcEventMapper{

    /**
	 * 이벤트의 전체 목록을 조회한다. 
	 * @param  EtcEventVO 
	 * @return List
	 * @
	 */
	
	public List<EtcEventVO> list(EtcEventVO vo) ;
	
    /**
	 * 이벤트의 검색된 수를 카운트 한다. 
	 * @param  EtcEventVO 
	 * @return int
	 * @
	 */
	public int count(EtcEventVO vo)  ;
	
    /**
	 * 이벤트의 전체 목록을 조회한다. 
	 * @param  EtcEventVO 
	 * @return List
	 * @
	 */
	
	public List<EtcEventVO> listPageing(EtcEventVO vo) ;
	
    /**
	 * 이벤트의 상세 정보를 조회한다. 
	 * @param  EtcEventVO 
	 * @return EtcEventVO
	 * @
	 */
	public EtcEventVO select(EtcEventVO vo) ;
	
    /**
	 * 이벤트의 키를 생성 한다. 
	 * @return Integer
	 * @
	 */
	public int selectKey() ;

    /**
     * 이벤트의 상세 정보를 등록한다.  
     * @param  EtcEventVO
     * @return String
     * @
     */
    public int insert(EtcEventVO vo) ;
    
    /**
     * 이벤트의 상세 정보를 수정한다. 
     * @param  EtcEventVO
     * @return int
     * @
     */
    public int update(EtcEventVO vo) ;
    
    /**
     * 이벤트의 상세 정보를 삭제한다.  
     * @param  EtcEventVO
     * @return int
     * @
     */
    public int delete(EtcEventVO vo) ; 
}
