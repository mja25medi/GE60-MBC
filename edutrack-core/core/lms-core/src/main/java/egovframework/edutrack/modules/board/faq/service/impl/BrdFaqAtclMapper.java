package egovframework.edutrack.modules.board.faq.service.impl;

import java.util.List;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.board.faq.service.BrdFaqAtclVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
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
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<BrdFaqAtclVO> list(BrdFaqAtclVO vo) throws Exception;
	
    /**
	 * FAQ 게시물의 검색된 수를 카운트 한다. 
	 * @param  BrdFaqAtclVO 
	 * @return int
	 * @throws Exception
	 */
	public int count(BrdFaqAtclVO vo) throws Exception;
	
    /**
	 * FAQ 게시물의 전체 목록을 조회한다. 
	 * @param  BrdFaqAtclVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<BrdFaqAtclVO> listPageing(BrdFaqAtclVO vo) throws Exception;
	
    /**
	 * FAQ 게시물의 상세 정보를 조회한다. 
	 * @param  BrdFaqAtclVO 
	 * @return BrdFaqAtclVO
	 * @throws Exception
	 */
	public BrdFaqAtclVO select(BrdFaqAtclVO vo) throws Exception;
	
    /**
	 * FAQ 게시물의 키를 조회한다. 
	 * @param  BrdFaqAtclVO 
	 * @return BrdFaqAtclVO
	 * @throws Exception
	 */
	public int selectKey() throws Exception;

    /**
     * FAQ 게시물의 상세 정보를 등록한다.  
     * @param  BrdFaqAtclVO
     * @return String
     * @throws Exception
     */
    public int insert(BrdFaqAtclVO vo) throws Exception;
    
    /**
     * FAQ 게시물의 상세 정보를 수정한다. 
     * @param  BrdFaqAtclVO
     * @return int
     * @throws Exception
     */
    public int update(BrdFaqAtclVO vo) throws Exception ;
    
    /**
     * FAQ 게시물의 상세 정보를 삭제한다.  
     * @param  BrdFaqAtclVO
     * @return int
     * @throws Exception
     */
    public int delete(BrdFaqAtclVO vo) throws Exception ; 
}
