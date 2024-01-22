package egovframework.edutrack.modules.board.bbs.service.impl;

import java.util.List;

import egovframework.edutrack.modules.board.bbs.service.BrdBbsInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>게시판 - 게시판 정보 관리</b> 영역  Mapper 클래스
 * @author Jamfam
 *
 */
@Mapper("brdBbsInfoMapper")
public interface BrdBbsInfoMapper {

    /**
	 * 게시판 정보의 전체 목록을 조회한다. 
	 * @param  BrdBbsInfoVO 
	 * @return List
	 * @
	 */
	public List<BrdBbsInfoVO> list(BrdBbsInfoVO vo) ;
	
    /**
	 * 게시판 정보의 검색된 수를 카운트 한다. 
	 * @param  BrdBbsInfoVO 
	 * @return int
	 * @
	 */
	public int count(BrdBbsInfoVO vo) ;
	
    /**
	 * 게시판 정보의 전체 목록을 조회한다. 
	 * @param  BrdBbsInfoVO 
	 * @return List
	 * @
	 */
	public List<BrdBbsInfoVO> listPageing(BrdBbsInfoVO vo) ;
	
    /**
	 * 게시판 정보의 상세 정보를 조회한다. 
	 * @param  BrdBbsInfoVO 
	 * @return BrdBbsInfoVO
	 * @
	 */
	public BrdBbsInfoVO select(BrdBbsInfoVO vo) ;
	
    /**
	 * 게시판 정보의 키를 생성한다. 
	 * @param  BrdBbsInfoVO 
	 * @return BrdBbsInfoVO
	 * @
	 */
	public String selectKey() ;

    /**
     * 게시판 정보의 상세 정보를 등록한다.  
     * @param  BrdBbsInfoVO
     * @return String
     * @
     */
    public int insert(BrdBbsInfoVO vo) ;
    
    /**
     * 게시판 정보의 상세 정보를 수정한다. 
     * @param  BrdBbsInfoVO
     * @return int
     * @
     */
    public int update(BrdBbsInfoVO vo)  ;
    
    /**
     * 게시판 정보의 상세 정보를 삭제한다.  
     * @param  BrdBbsInfoVO
     * @return int
     * @
     */
    public int delete(BrdBbsInfoVO vo)  ;
    
    /**
	 * 메뉴에 연결되지 않은 게시판 정보의 검색된 수를 카운트 한다. 
	 * @param  BrdBbsInfoVO 
	 * @return int
	 * @
	 */
	public int countForMenu(BrdBbsInfoVO vo) ;
	
    /**
	 * 메뉴에 연결되지 않은 게시판 정보의 전체 목록을 조회한다. 
	 * @param  BrdBbsInfoVO 
	 * @return List
	 * @
	 */
	public List<BrdBbsInfoVO> listPageingForMenu(BrdBbsInfoVO vo)  ;
    
    /**
     * 게시판과 메뉴를 연결한다. 
     * @param  BrdBbsInfoVO
     * @return int
     * @
     */
    public int updateMenuCd(BrdBbsInfoVO vo)  ;
    
    /**
     * 게시판과 메뉴 연결을 헤제한다. 
     * @param  BrdBbsInfoVO
     * @return int
     * @
     */
    public int updateMenuCdToNull(BrdBbsInfoVO vo)  ;
    
    /**
     * Default가 아닌 게시판과 메뉴 연결을 헤제한다. 
     * @param  BrdBbsInfoVO
     * @return int
     * @
     */
    public int updateAllMenuCdToNull(BrdBbsInfoVO vo)  ;
}
