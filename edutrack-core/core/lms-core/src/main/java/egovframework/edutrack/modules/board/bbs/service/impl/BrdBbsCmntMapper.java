package egovframework.edutrack.modules.board.bbs.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.board.bbs.service.BrdBbsCmntVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>게시판 - 게시판 댓글 관리</b> 영역  Mapper 클래스
 * @author Jamfam
 *
 */
@Mapper("brdBbsCmntMapper")
public interface BrdBbsCmntMapper {

   /**
	 * 게시판 댓글의 전체 목록을 조회한다. 
	 * @param  BrdBbsCmntVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<BrdBbsCmntVO> list(BrdBbsCmntVO vo) throws Exception;
	
    /**
	 * 게시판 댓글의 검색된 수를 카운트 한다. 
	 * @param  BrdBbsCmntVO 
	 * @return int
	 * @throws Exception
	 */
	public int count(BrdBbsCmntVO vo) throws Exception;
	
    /**
	 * 게시판 댓글의 전체 목록을 조회한다. 
	 * @param  BrdBbsCmntVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<BrdBbsCmntVO> listPageing(BrdBbsCmntVO vo) throws Exception ;
	
    /**
	 * 게시판 댓글의 상세 정보를 조회한다. 
	 * @param  BrdBbsCmntVO 
	 * @return BrdBbsCmntVO
	 * @throws Exception
	 */
	public BrdBbsCmntVO select(BrdBbsCmntVO vo) throws Exception;
	
    /**
	 * 게시판 댓글의 키를 생성한다. 
	 * @return int
	 * @throws Exception
	 */
	public int selectKey() throws Exception;

    /**
     * 게시판 댓글의 상세 정보를 등록한다.  
     * @param  BrdBbsCmntVO
     * @return String
     * @throws Exception
     */
    public int insert(BrdBbsCmntVO vo) throws Exception;
    
    /**
     * 게시판 댓글의 상세 정보를 수정한다. 
     * @param  BrdBbsCmntVO
     * @return int
     * @throws Exception
     */
    public int update(BrdBbsCmntVO vo) throws Exception ;
    
    /**
     * 게시판 댓글의 상세 정보를 삭제한다.  
     * @param  BrdBbsCmntVO
     * @return int
     * @throws Exception
     */
    public int delete(BrdBbsCmntVO vo) throws Exception ;
    
    /**
     * 게시판 게시물의 댓글 전체 삭제한다.  
     * @param  BrdBbsCmntVO
     * @return int
     * @throws Exception
     */
    public int deleteAll(BrdBbsCmntVO vo) throws Exception ;
}
