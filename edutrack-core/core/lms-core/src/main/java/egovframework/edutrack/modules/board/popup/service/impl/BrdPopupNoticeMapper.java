package egovframework.edutrack.modules.board.popup.service.impl;

import java.util.List;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.board.popup.service.BrdPopupNoticeVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 *  <b>사용자 - 사용자  관리</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("brdPopupNoticeMapper")
public interface BrdPopupNoticeMapper {

//   /**
//	 * 부서 정보의 전체 목록을 조회한다. 
//	 * @param  UsrDeptInfoVO 
//	 * @return List
//	 * @throws Exception
//	 */
//	@SuppressWarnings("unchecked")
//	public List<UsrDeptInfoVO> list(UsrDeptInfoVO vo) throws Exception{
//		return (List<UsrDeptInfoVO>)list("TbUsrDeptInfoDAO.list", vo);
//	}
//	
	/**
	 * 팝업 공지사항 페이징 목록을 조회한다.
	 */
	public int count(BrdPopupNoticeVO vo) throws Exception;
	
	/**
	 * 팝업 공지사항 페이징 목록을 조회한다.
	 */
	@SuppressWarnings("unchecked")
	public List<BrdPopupNoticeVO> listPageing(BrdPopupNoticeVO vo) throws Exception;
	
	/**
	 * 팝업 공지사항 전체 목록을 조회한다.
	 */
	@SuppressWarnings("unchecked")
	public List<BrdPopupNoticeVO> listPopup(BrdPopupNoticeVO vo) throws Exception ;
	
    /**
	 * 팝업 공지사항 상세 정보를 조회한다. 
	 */
	public BrdPopupNoticeVO select(BrdPopupNoticeVO vo) throws Exception;

	/**
	 * 팝업 공지사항 키를 조회한다.
	 */
	public Integer selectKey() throws Exception;
	
	/**
	 * 팝업 공지사항을 등록한다.
	 */
    public int insert(BrdPopupNoticeVO vo) throws Exception;
    
    /**
     * 팝업 공지사항을 수정한다. 
     */
    public int update(BrdPopupNoticeVO vo) throws Exception ;
    
    /**
     * 팝업 공지사항을 삭제한다.  
     */
    public int delete(BrdPopupNoticeVO vo) throws Exception ;  
}
