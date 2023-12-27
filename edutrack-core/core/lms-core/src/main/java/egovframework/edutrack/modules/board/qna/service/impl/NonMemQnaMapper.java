package egovframework.edutrack.modules.board.qna.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import egovframework.edutrack.modules.board.qna.service.NonMemQnaVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("nonMemQnaMapper")
public interface NonMemQnaMapper {
	
	/**
	 * 비회원 상담 신청 - Select Key 조회
	 *
	 * @return 
	 */
	public int selectKey();
	
	/**
	 * 비회원 상담 신청 - 상담 등록
	 *
	 * @return 
	 */
	public int insertNonMemQna(NonMemQnaVO vo) throws DataAccessException;
	
	/**
	 * 비회원 상담 신청 - 답변 등록
	 *
	 * @return 
	 */
	public int addNonMemAns(NonMemQnaVO vo) throws DataAccessException;
	
	/**
	 * 비회원 상담 신청 - 답변 수정
	 *
	 * @return 
	 */
	public int updateNonMemAns(NonMemQnaVO vo) throws DataAccessException;
	
	/**
	 * 비회원 상담 신청 - 상담 삭제
	 *
	 * @return 
	 */
	public int deleteNonMemQna(NonMemQnaVO vo) throws DataAccessException;

	/**
	 * 비회원 상담 신청 - 상담 개수 카운트
	 *
	 * @return 
	 */
	public int countNonMemQna(NonMemQnaVO vo);
	
	/**
	 * 비회원 상담 신청 - 상담 목록 조회
	 *
	 * @return 
	 */
	public List<NonMemQnaVO> listPageing(NonMemQnaVO vo);
	
	/**
	 * 비회원 상담 신청 - 개별 상담 내역 조회
	 *
	 * @return 
	 */
	public NonMemQnaVO selectNonMemQna(NonMemQnaVO vo) throws DataAccessException;
}
