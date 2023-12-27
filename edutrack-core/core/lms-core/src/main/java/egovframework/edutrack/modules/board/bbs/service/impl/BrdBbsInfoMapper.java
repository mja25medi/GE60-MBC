package egovframework.edutrack.modules.board.bbs.service.impl;

import java.util.List;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.board.bbs.service.BrdBbsInfoVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
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
	 * @throws Exception
	 */
	public List<BrdBbsInfoVO> list(BrdBbsInfoVO vo) throws Exception;
	
    /**
	 * 게시판 정보의 검색된 수를 카운트 한다. 
	 * @param  BrdBbsInfoVO 
	 * @return int
	 * @throws Exception
	 */
	public int count(BrdBbsInfoVO vo) throws Exception;
	
    /**
	 * 게시판 정보의 전체 목록을 조회한다. 
	 * @param  BrdBbsInfoVO 
	 * @return List
	 * @throws Exception
	 */
	public List<BrdBbsInfoVO> listPageing(BrdBbsInfoVO vo) throws Exception;
	
    /**
	 * 게시판 정보의 상세 정보를 조회한다. 
	 * @param  BrdBbsInfoVO 
	 * @return BrdBbsInfoVO
	 * @throws Exception
	 */
	public BrdBbsInfoVO select(BrdBbsInfoVO vo) throws Exception;
	
    /**
	 * 게시판 정보의 키를 생성한다. 
	 * @param  BrdBbsInfoVO 
	 * @return BrdBbsInfoVO
	 * @throws Exception
	 */
	public String selectKey() throws Exception;

    /**
     * 게시판 정보의 상세 정보를 등록한다.  
     * @param  BrdBbsInfoVO
     * @return String
     * @throws Exception
     */
    public int insert(BrdBbsInfoVO vo) throws Exception;
    
    /**
     * 게시판 정보의 상세 정보를 수정한다. 
     * @param  BrdBbsInfoVO
     * @return int
     * @throws Exception
     */
    public int update(BrdBbsInfoVO vo) throws Exception ;
    
    /**
     * 게시판 정보의 상세 정보를 삭제한다.  
     * @param  BrdBbsInfoVO
     * @return int
     * @throws Exception
     */
    public int delete(BrdBbsInfoVO vo) throws Exception ;
    
    /**
	 * 메뉴에 연결되지 않은 게시판 정보의 검색된 수를 카운트 한다. 
	 * @param  BrdBbsInfoVO 
	 * @return int
	 * @throws Exception
	 */
	public int countForMenu(BrdBbsInfoVO vo) throws Exception;
	
    /**
	 * 메뉴에 연결되지 않은 게시판 정보의 전체 목록을 조회한다. 
	 * @param  BrdBbsInfoVO 
	 * @return List
	 * @throws Exception
	 */
	public List<BrdBbsInfoVO> listPageingForMenu(BrdBbsInfoVO vo) throws Exception ;
    
    /**
     * 게시판과 메뉴를 연결한다. 
     * @param  BrdBbsInfoVO
     * @return int
     * @throws Exception
     */
    public int updateMenuCd(BrdBbsInfoVO vo) throws Exception ;
    
    /**
     * 게시판과 메뉴 연결을 헤제한다. 
     * @param  BrdBbsInfoVO
     * @return int
     * @throws Exception
     */
    public int updateMenuCdToNull(BrdBbsInfoVO vo) throws Exception ;
    
    /**
     * Default가 아닌 게시판과 메뉴 연결을 헤제한다. 
     * @param  BrdBbsInfoVO
     * @return int
     * @throws Exception
     */
    public int updateAllMenuCdToNull(BrdBbsInfoVO vo) throws Exception ;
}
