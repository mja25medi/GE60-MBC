package egovframework.edutrack.modules.board.faq.service.impl;

import java.util.List;

import egovframework.edutrack.modules.board.faq.service.BrdFaqAtclVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>게시판 - 게시판 FAQ 내용 관리</b> 영역  Mapper 클래스
 * @author Jamfam
 *
 */
@Mapper("brdFaqAtclMapper")
public interface BrdFaqAtclMapper {

    /**
	 * FAQ 게시물의 전체 목록을 조회한다. 
	 * @param  BrdFaqAtclVO 
	 * @return List
	 * @
	 */
	public List<BrdFaqAtclVO> list(BrdFaqAtclVO vo) ;
	
    /**
	 * FAQ 게시물의 검색된 수를 카운트 한다. 
	 * @param  BrdFaqAtclVO 
	 * @return int
	 * @
	 */
	public int count(BrdFaqAtclVO vo) ;
	
    /**
	 * FAQ 게시물의 전체 목록을 조회한다. 
	 * @param  BrdFaqAtclVO 
	 * @return List
	 * @
	 */
	
	public List<BrdFaqAtclVO> listPageing(BrdFaqAtclVO vo) ;
	
    /**
	 * FAQ 게시물의 상세 정보를 조회한다. 
	 * @param  BrdFaqAtclVO 
	 * @return BrdFaqAtclVO
	 * @
	 */
	public BrdFaqAtclVO select(BrdFaqAtclVO vo) ;
	
    /**
	 * FAQ 게시물의 키를 조회한다. 
	 * @param  BrdFaqAtclVO 
	 * @return BrdFaqAtclVO
	 * @
	 */
	public int selectKey() ;

    /**
     * FAQ 게시물의 상세 정보를 등록한다.  
     * @param  BrdFaqAtclVO
     * @return String
     * @
     */
    public int insert(BrdFaqAtclVO vo) ;
    
    /**
     * FAQ 게시물의 상세 정보를 수정한다. 
     * @param  BrdFaqAtclVO
     * @return int
     * @
     */
    public int update(BrdFaqAtclVO vo)  ;
    
    /**
     * FAQ 게시물의 상세 정보를 삭제한다.  
     * @param  BrdFaqAtclVO
     * @return int
     * @
     */
    public int delete(BrdFaqAtclVO vo)  ; 
}
