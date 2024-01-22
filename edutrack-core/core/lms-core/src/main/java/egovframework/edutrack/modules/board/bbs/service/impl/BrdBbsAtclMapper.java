package egovframework.edutrack.modules.board.bbs.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.board.bbs.service.BrdBbsAtclVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>게시판 - 게시판 게시물 관리</b> 영역  Mapper 클래스
 * @author Jamfam
 *
 */
@Mapper("brdBbsAtclMapper")
public interface BrdBbsAtclMapper {
	
    /**
	 * 게시판 게시물의 전체 목록을 조회한다. 
	 * @param  BrdBbsAtclVO 
	 * @return List
	 * @
	 */
	public List<BrdBbsAtclVO> list(BrdBbsAtclVO vo) ;
	
    /**
	 * 게시판 게시물의 검색된 수를 카운트 한다. 
	 * @param  BrdBbsAtclVO 
	 * @return int
	 * @
	 */
	public int count(BrdBbsAtclVO vo) ;
	
    /**
	 * 게시판 게시물의 전체 목록을 조회한다. 
	 * @param  BrdBbsAtclVO 
	 * @return List
	 * @
	 */
	public List<BrdBbsAtclVO> listPageing(BrdBbsAtclVO vo)  ;
	
    /**
	 * 게시판 게시물의 전체 목록을 조회한다. 
	 * @param  BrdBbsAtclVO 
	 * @return List
	 * @
	 */
	public List<BrdBbsAtclVO> listTop(BrdBbsAtclVO vo) ;
	
    /**
	 * 게시판 게시물의 상세 정보를 조회한다. 
	 * @param  BrdBbsAtclVO 
	 * @return BrdBbsAtclVO
	 * @
	 */
	public BrdBbsAtclVO select(BrdBbsAtclVO vo) ;
	
	public BrdBbsAtclVO selectService(BrdBbsAtclVO vo) ;
	
	/**
	 * 게시판 게시물의 상세 정보를 조회한다. 
	 * @param  BrdBbsAtclVO 
	 * @return BrdBbsAtclVO
	 * @
	 */
	public BrdBbsAtclVO selectAtclWithPreNext(BrdBbsAtclVO vo) ;
	
	/**
	 * 게시판 게시물의 상세 정보를 조회한다. 
	 * @param  BrdBbsAtclVO 
	 * @return BrdBbsAtclVO
	 * @
	 */
	public BrdBbsAtclVO selectAtclWithPreNextVer5(BrdBbsAtclVO vo) ;

    /**
	 * 게시판 게시물의 키를 생성한다. 
	 * @param  BrdBbsAtclVO 
	 * @return BrdBbsAtclVO
	 * @
	 */
	public int selectKey() ;
	
    /**
     * 게시판 게시물의 상세 정보를 등록한다.  
     * @param  BrdBbsAtclVO
     * @return String
     * @
     */
    public int insert(BrdBbsAtclVO vo) ;
    
    public int insertService(BrdBbsAtclVO vo) ;
    /**
     * 게시판 게시물의 상세 정보를 수정한다. 
     * @param  BrdBbsAtclVO
     * @return int
     * @
     */
    public int update(BrdBbsAtclVO vo) ;
    
    /**
     * 게시판 게시물의 상세 정보를 삭제한다.  
     * @param  BrdBbsAtclVO
     * @return int
     * @
     */
    public int delete(BrdBbsAtclVO vo)  ;
    
    /**
     * 게시판 게시물의 조회수를 증가 시킨다. 
     * @param  BrdBbsAtclVO
     * @return int
     * @
     */
    public int hitsup(BrdBbsAtclVO vo)  ;
    
    public BrdBbsAtclVO checkPassword(BrdBbsAtclVO vo) ;
}
