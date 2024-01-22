package egovframework.edutrack.modules.etc.banner.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.etc.banner.service.EtcBnnrVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>기타 - 기타 베너 관리</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("etcBnnrMapper")
public interface EtcBnnrMapper{

    /**
	 * 베너의 전체 목록을 조회한다. 
	 * @param  EtcBnnrVO 
	 * @return List
	 * @
	 */
	
	public List<EtcBnnrVO> list(EtcBnnrVO vo) ;
	
    /**
	 * 베너의 검색된 수를 카운트 한다. 
	 * @param  EtcBnnrVO 
	 * @return int
	 * @
	 */
	public int count(EtcBnnrVO vo)  ;
	
    /**
	 * 베너의 전체 목록을 조회한다. 
	 * @param  EtcBnnrVO 
	 * @return List
	 * @
	 */
	
	public List<EtcBnnrVO> listPageing(EtcBnnrVO vo) ;
	
    /**
	 * 베너의 상세 정보를 조회한다. 
	 * @param  EtcBnnrVO 
	 * @return EtcBnnrVO
	 * @
	 */
	public EtcBnnrVO select(EtcBnnrVO vo) ;
	
    /**
	 * 베너의 키를 생성 한다. 
	 * @return Integer
	 * @
	 */
	public int selectKey() ;

    /**
     * 베너의 상세 정보를 등록한다.  
     * @param  EtcBnnrVO
     * @return String
     * @
     */
    public int insert(EtcBnnrVO vo) ;
    
    /**
     * 베너의 상세 정보를 수정한다. 
     * @param  EtcBnnrVO
     * @return int
     * @
     */
    public int update(EtcBnnrVO vo) ;
    
    /**
     * 베너의 상세 정보를 삭제한다.  
     * @param  EtcBnnrVO
     * @return int
     * @
     */
    public int delete(EtcBnnrVO vo) ; 
}
