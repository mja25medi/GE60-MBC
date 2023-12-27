package egovframework.edutrack.modules.lecture.bbs.service;

import java.util.List;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface LecBbsService {

	/**
	 * 강의실 게시판의 정보를 가져온다.
	 * @param lecBbsVO.bbsCd
	 * @return LecBbsVO
	 */
	public abstract ProcessResultVO<LecBbsVO> viewInfo(LecBbsVO vo) throws Exception;

	/**
	 * 게시판 글 목록 조회 (페이지당 표시갯수와 컨트롤 갯수는 {@link Constants} 를 참조한다.
	 *
	 * @param lecBbsAtclVO 검색을 위한 파라매터용 VO
	 * @param curPage 현재 페이지
	 * @return 조회결과 목록 VO
	 */
	public abstract ProcessResultListVO<LecBbsAtclVO> listAtclPageing(
			LecBbsAtclVO vo,boolean filein) throws Exception;

	public abstract ProcessResultListVO<LecBbsAtclVO> listAtclPageing(LecBbsAtclVO vo) throws Exception;


	/**
	 * 게시판 글 등록
	 *
	 * @param vo
	 * @return 등록 결과 VO (생성된 주키를 포함)
	 */
	public abstract ProcessResultVO<LecBbsAtclVO> addAtcl(LecBbsAtclVO vo) throws Exception;

	/**
	 * 게시판 글 조회
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public abstract ProcessResultVO<LecBbsAtclVO> viewAtcl(LecBbsAtclVO vo) throws Exception;

	/**
	 * 게시판 글 조회
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public abstract ProcessResultVO<LecBbsAtclVO> viewAtcl(LecBbsAtclVO vo, boolean hitup) throws Exception;

	/**
	 * 게시글에 소속된 댓글 목록 조회
	 *
	 * @param commentVO
	 * @return
	 */
	public abstract ProcessResultListVO<LecBbsCmntVO> listCmntPageing(
			LecBbsCmntVO vo) throws Exception;
	
	public abstract List<LecBbsCmntVO> listCmnt(LecBbsCmntVO vo)  throws Exception;

	/**
	 * 댓글 추가
	 *
	 * @param comment
	 * @return
	 */
	public abstract ProcessResultVO<LecBbsCmntVO> addCmnt(LecBbsCmntVO vo) throws Exception;

	/**
	 * 게시판 글 정보 조회 (조회수 미증가)
	 *
	 * @param articleVO 글 고유 번호
	 * @return 조회 결과 VO
	 */
	public abstract ProcessResultVO<LecBbsAtclVO> getAtcl(LecBbsAtclVO vo) throws Exception;

	/**
	 * 게시판 글 수정
	 *
	 * @param vo 수정될 정보를 가지고 있는 VO
	 * @return 수정 결과 VO
	 */
	public abstract ProcessResultVO<?> editAtcl(LecBbsAtclVO vo) throws Exception;

	/**
	 * 게시판 글 삭제
	 *
	 * @param articleVO 삭제 대상 고유 번호
	 * @return 삭제 처리 결과 VO
	 */
	public abstract ProcessResultVO<?> removeAtcl(LecBbsAtclVO vo) throws Exception;

	/**
	 * 댓글 삭제
	 *
	 * @param comment
	 * @return
	 */
	public abstract ProcessResultVO<?> removeCmnt(LecBbsCmntVO vo) throws Exception;

	/**
	 * 댓글 수정
	 *
	 * @param comment
	 * @return
	 */
	public abstract ProcessResultVO<LecBbsCmntVO> editCmnt(LecBbsCmntVO vo) throws Exception;

	/**
	 * 댓글 단일 항목 조회(수정전 자료 조회용)
	 *
	 * @param comment
	 * @return
	 */
	public abstract ProcessResultVO<LecBbsCmntVO> getCmnt(LecBbsCmntVO vo) throws Exception;

}