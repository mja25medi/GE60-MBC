package egovframework.edutrack.modules.board.popup.service.impl;

import java.util.List;

import egovframework.edutrack.modules.board.popup.service.BrdPopupNoticeVO;
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
//	 * @
//	 */
//	
//	public List<UsrDeptInfoVO> list(UsrDeptInfoVO vo) {
//		return (List<UsrDeptInfoVO>)list("TbUsrDeptInfoDAO.list", vo);
//	}
//	
	/**
	 * 팝업 공지사항 페이징 목록을 조회한다.
	 */
	public int count(BrdPopupNoticeVO vo) ;
	
	/**
	 * 팝업 공지사항 페이징 목록을 조회한다.
	 */
	public List<BrdPopupNoticeVO> listPageing(BrdPopupNoticeVO vo) ;
	
	/**
	 * 팝업 공지사항 전체 목록을 조회한다.
	 */
	public List<BrdPopupNoticeVO> listPopup(BrdPopupNoticeVO vo)  ;
	
    /**
	 * 팝업 공지사항 상세 정보를 조회한다. 
	 */
	public BrdPopupNoticeVO select(BrdPopupNoticeVO vo) ;

	/**
	 * 팝업 공지사항 키를 조회한다.
	 */
	public Integer selectKey() ;
	
	/**
	 * 팝업 공지사항을 등록한다.
	 */
    public int insert(BrdPopupNoticeVO vo) ;
    
    /**
     * 팝업 공지사항을 수정한다. 
     */
    public int update(BrdPopupNoticeVO vo)  ;
    
    /**
     * 팝업 공지사항을 삭제한다.  
     */
    public int delete(BrdPopupNoticeVO vo)  ;

	public int listPopupCnt(BrdPopupNoticeVO vo)  ;
}
