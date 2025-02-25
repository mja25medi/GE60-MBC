package egovframework.edutrack.modules.lecture.bbs.service.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.modules.lecture.bbs.service.LecBbsAtclVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper("lecBbsAtclMapper")
public interface LecBbsAtclMapper {


	/**
	 * 게시글 레코드 목록을 조회한다.(트리 페이징 포함)
	 * @param articleParam
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 */
	
	public List<LecBbsAtclVO> listPageing(LecBbsAtclVO vo) ;
	
	/**
	 * 게시글 레코드 목록을 조회한다.(트리 페이징 포함)
	 * @param articleParam
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 */
	
	public List<LecBbsAtclVO> listPageingVer5(LecBbsAtclVO vo) ;

	/**
	 * 게시글 레코드 목록수 반환
	 * @param articleParam
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 */
	
	public int count(LecBbsAtclVO vo) ;
	
	/**
	 * 단일 게시물 조회.
	 * @param crsCreCd
	 * @param bbsSn
	 * @param ATCLSn
	 * @return BbsArticleVO
	 */
	public LecBbsAtclVO select(LecBbsAtclVO vo) ;
	/**
	 * Select Key 조회
	 * @param int
	 * @return
	 */
	public int selectKey()  ;

	/**
	 * 게시물 등록
	 * @param  QnaArticleVO
	 */
	public int insert(LecBbsAtclVO vo) ;

	/**
	 * 게시글 단일 레코드를 수정한다.
	 * @param articleVO
	 * @return
	 */
	public int update(LecBbsAtclVO vo) ;

	/**
	 * 게시글 단일 레코드를 삭제한다.
	 * @param articleVO
	 * @return
	 */
	public int delete(LecBbsAtclVO vo) ;
	
	/**
	 * 게시판의 게시글 전체 레코드를 삭제한다.
	 * @param articleVO
	 * @return
	 */
	public int deleteAll(LecBbsAtclVO vo) ;

	/**
	 * 해당글의 조회수를 1 증가시킨다.
	 *
	 * @param articleVO
	 */
	public void hitup(LecBbsAtclVO vo) ;

	
}
