package egovframework.edutrack.modules.board.qna.service;

import java.util.List;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;


public interface BrdQnaService {

	/**
	 * 1대1 문의 목록 페이징 조회(첨부파일 제외)
	 * @param BrdQnaQstnVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<BrdQnaQstnVO> listPageing(BrdQnaQstnVO vo) throws Exception;
	
	/**
	 * 1대1 문의 목록 조회(페이징,첨부파일 제외)
	 * @param BrdQnaQstnVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<BrdQnaQstnVO> qnaList(BrdQnaQstnVO vo) throws Exception;
	
	/**
	 * 1대1 문의 작성
	 * @param BrdQnaQstnVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int writeQstn(BrdQnaQstnVO vo) throws Exception;
	
	/**
	 * 1대1 문의 수정
	 * @param BrdQnaQstnVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int editQstn(BrdQnaQstnVO vo) throws Exception;
	
	/**
	 * 1대1 문의 삭제
	 * @param BrdQnaQstnVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int deleteQstn(BrdQnaQstnVO vo) throws Exception;
	
	public abstract int deleteAnsr(BrdQnaAnsrVO vo) throws Exception;
	
	/**
	 * 1대1 문의 및 답변 삭제
	 * @param BrdQnaAnsrVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int deleteQstnAndAnsr(BrdQnaQstnVO vo) throws Exception;
	
	/**
	 * 1대1 문의 상세조회
	 * @param BrdQnaQstnVO
	 * @return BrdQnaQstnVO
	 * @throws Exception
	 */
	public abstract BrdQnaQstnVO viewQstn(BrdQnaQstnVO vo) throws Exception;
	
	/**
	 * 1대1 문의 목록 페이징 조회
	 * @param BrdQnaQstnVO
	 * @param boolean
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<BrdQnaQstnVO> listPageing(BrdQnaQstnVO vo, boolean fileIn) throws Exception;

	/**
	 * 1대1 문의 답변 작성
	 * @param BrdQnaAnsrVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int writeAnsr(BrdQnaAnsrVO vo) throws Exception;
	
	/**
	 * 1대1 문의 답변 상세 조회
	 * @param BrdQnaAnsrVO
	 * @return ProcessResultVO
	 * @throws Exception
	 */
	public abstract ProcessResultVO<BrdQnaAnsrVO> viewAnsr(BrdQnaAnsrVO vo) throws Exception;
	
	/**
	 * 1대1 문의 답변 수정
	 * @param BrdQnaAnsrVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int editAnsr(BrdQnaAnsrVO vo) throws Exception;
	
	public abstract List<BrdQnaQstnVO> getParCntsList(BrdQnaQstnVO vo) throws Exception;
	
	public abstract BrdQnaQstnVO viewLecQstn(BrdQnaQstnVO vo) throws Exception;
	
}