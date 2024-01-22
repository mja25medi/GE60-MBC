package egovframework.edutrack.modules.board.faq.service.impl;

import java.util.List;

import egovframework.edutrack.modules.board.faq.service.BrdFaqCtgrVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>게시판 - 게시판 FAQ 분류 관리</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("brdFaqCtgrMapper")
public interface BrdFaqCtgrMapper {

    /**
	 * FAQ 분류의 전체 목록을 조회한다. 
	 * @param  BrdFaqCtgrVO 
	 * @return List
	 * @
	 */
	public List<BrdFaqCtgrVO> list(BrdFaqCtgrVO vo) ;
	
    /**
	 * FAQ 분류의 검색된 수를 카운트 한다. 
	 * @param  BrdFaqCtgrVO 
	 * @return int
	 * @
	 */
	public int count(BrdFaqCtgrVO vo) ;
	
    /**
	 * FAQ 분류의 전체 목록을 조회한다. 
	 * @param  BrdFaqCtgrVO 
	 * @return List
	 * @
	 */
	public List<BrdFaqCtgrVO> listPageing(BrdFaqCtgrVO vo)  ;
	
    /**
	 * FAQ 분류의 상세 정보를 조회한다. 
	 * @param  BrdFaqCtgrVO 
	 * @return BrdFaqCtgrVO
	 * @
	 */
	public BrdFaqCtgrVO select(BrdFaqCtgrVO vo) ;

    /**
     * FAQ 분류의 상세 정보를 등록한다.  
     * @param  BrdFaqCtgrVO
     * @return String
     * @
     */
    public int insert(BrdFaqCtgrVO vo) ;
    
    /**
     * FAQ 분류의 상세 정보를 수정한다. 
     * @param  BrdFaqCtgrVO
     * @return int
     * @
     */
    public int update(BrdFaqCtgrVO vo)  ;
    
    /**
     * FAQ 분류의 상세 정보를 삭제한다.  
     * @param  BrdFaqCtgrVO
     * @return int
     * @
     */
    public int delete(BrdFaqCtgrVO vo)  ;
    
    /**
     * FAQ 분류의 SELECT KEY.  
     * @param  BrdFaqCtgrVO
     * @return String
     * @
     */
    public String selectKey(BrdFaqCtgrVO vo)  ;
}
