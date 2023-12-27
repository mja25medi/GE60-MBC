package egovframework.edutrack.modules.board.qna.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface NonMemQnaService {
	
	/**
	 * 비회원 상담 신청
	 * 
	 * @return ProcessResultListVO
	 */
	public ProcessResultVO<NonMemQnaVO> addQna(NonMemQnaVO iNonMemQnaVO) throws Exception;
	
	/**
	 * 비회원 상담 목록 페이징 조회
	 * @param NonMemQnaVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<NonMemQnaVO> nonMemListPageing(NonMemQnaVO vo) throws Exception;

	/**
	 * 비회원 상담 상세조회
	 * @param NonMemQnaVO
	 * @return NonMemQnaVO
	 * @throws Exception
	 */
	public abstract NonMemQnaVO viewNonMemQstn(NonMemQnaVO vo) throws Exception;

	/**
	 * 비회원 상담 답변 작성
	 * @param NonMemQnaVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int writeNonMemAnsr (NonMemQnaVO vo) throws Exception;
	
	/**
	 * 비회원 상담 답변 수정
	 * @param NonMemQnaVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int editNonMemAnsr (NonMemQnaVO vo) throws Exception;
	
	/**
	 * 비회원 상담 삭제
	 * @param NonMemQnaVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int deleteNonMem(NonMemQnaVO vo) throws Exception;

	/**
	 * 비회원 상담 답변 상세 조회
	 * @param NonMemQnaVO
	 * @return ProcessResultVO
	 * @throws Exception
	 */
	public abstract ProcessResultVO<NonMemQnaVO> viewNonMemAnsr(NonMemQnaVO vo) throws Exception;

}
