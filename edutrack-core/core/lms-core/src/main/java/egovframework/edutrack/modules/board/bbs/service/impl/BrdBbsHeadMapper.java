package egovframework.edutrack.modules.board.bbs.service.impl;

import java.util.List;

import egovframework.edutrack.modules.board.bbs.service.BrdBbsHeadVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>게시판 - 게시판 머릿말 관리</b> 영역  Mapper 클래스
 * @author Jamfam
 *
 */
@Mapper("brdBbsHeadMapper")
public interface BrdBbsHeadMapper {

    /**
	 * 게시판 머릿말의 전체 목록을 조회한다. 
	 * @param  BrdBbsHeadVO 
	 * @return List
	 * @
	 */
	
	public List<BrdBbsHeadVO> list(BrdBbsHeadVO vo) ;
	
    /**
	 * 게시판 머릿말의 검색된 수를 카운트 한다. 
	 * @param  BrdBbsHeadVO 
	 * @return int
	 * @
	 */
	public int count(BrdBbsHeadVO vo) ;
	
    /**
	 * 게시판 머릿말의 전체 목록을 조회한다. 
	 * @param  BrdBbsHeadVO 
	 * @return List
	 * @
	 */
	
	public List<BrdBbsHeadVO> listPageing(BrdBbsHeadVO vo)  ;
	
    /**
	 * 게시판 머릿말의 상세 정보를 조회한다. 
	 * @param  BrdBbsHeadVO 
	 * @return BrdBbsHeadVO
	 * @
	 */
	public BrdBbsHeadVO select(BrdBbsHeadVO vo) ;
	
    /**
	 * 게시판 머릿말의 키를 생성한다. 
	 * @return String
	 * @
	 */
	public String selectKey() ;

    /**
     * 게시판 머릿말의 상세 정보를 등록한다.  
     * @param  BrdBbsHeadVO
     * @return String
     * @
     */
    public int insert(BrdBbsHeadVO vo) ;
    
    /**
     * 게시판 머릿말의 상세 정보를 수정한다. 
     * @param  BrdBbsHeadVO
     * @return int
     * @
     */
    public int update(BrdBbsHeadVO vo)  ;
    
    /**
     * 게시판 머릿말의 상세 정보를 삭제한다.  
     * @param  BrdBbsHeadVO
     * @return int
     * @
     */
    public int delete(BrdBbsHeadVO vo)  ;
    
    /**
     * 게시판의 전체 머릿말 정보를 삭제한다.  
     * @param  BrdBbsHeadVO
     * @return int
     * @
     */
    public int deleteAll(BrdBbsHeadVO vo)  ;
}
