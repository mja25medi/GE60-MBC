package egovframework.edutrack.modules.board.bbs.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.board.bbs.service.BrdBbsHeadVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
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
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<BrdBbsHeadVO> list(BrdBbsHeadVO vo) throws Exception;
	
    /**
	 * 게시판 머릿말의 검색된 수를 카운트 한다. 
	 * @param  BrdBbsHeadVO 
	 * @return int
	 * @throws Exception
	 */
	public int count(BrdBbsHeadVO vo) throws Exception;
	
    /**
	 * 게시판 머릿말의 전체 목록을 조회한다. 
	 * @param  BrdBbsHeadVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<BrdBbsHeadVO> listPageing(BrdBbsHeadVO vo) throws Exception ;
	
    /**
	 * 게시판 머릿말의 상세 정보를 조회한다. 
	 * @param  BrdBbsHeadVO 
	 * @return BrdBbsHeadVO
	 * @throws Exception
	 */
	public BrdBbsHeadVO select(BrdBbsHeadVO vo) throws Exception;
	
    /**
	 * 게시판 머릿말의 키를 생성한다. 
	 * @return String
	 * @throws Exception
	 */
	public String selectKey() throws Exception;

    /**
     * 게시판 머릿말의 상세 정보를 등록한다.  
     * @param  BrdBbsHeadVO
     * @return String
     * @throws Exception
     */
    public int insert(BrdBbsHeadVO vo) throws Exception;
    
    /**
     * 게시판 머릿말의 상세 정보를 수정한다. 
     * @param  BrdBbsHeadVO
     * @return int
     * @throws Exception
     */
    public int update(BrdBbsHeadVO vo) throws Exception ;
    
    /**
     * 게시판 머릿말의 상세 정보를 삭제한다.  
     * @param  BrdBbsHeadVO
     * @return int
     * @throws Exception
     */
    public int delete(BrdBbsHeadVO vo) throws Exception ;
    
    /**
     * 게시판의 전체 머릿말 정보를 삭제한다.  
     * @param  BrdBbsHeadVO
     * @return int
     * @throws Exception
     */
    public int deleteAll(BrdBbsHeadVO vo) throws Exception ;
}
