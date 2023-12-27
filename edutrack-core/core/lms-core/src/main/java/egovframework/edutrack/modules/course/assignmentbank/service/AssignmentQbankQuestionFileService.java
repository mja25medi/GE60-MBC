package egovframework.edutrack.modules.course.assignmentbank.service;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;



public interface AssignmentQbankQuestionFileService {


	/**
	 * 과제문제은행 파일등록
	 * 
	 * @return ProcessResultVO
	 */
	public abstract ProcessResultVO<?> insert(
			final AssignmentQbankQuestionVO VO) throws DataAccessException;

	/**
	 * 해당괴제에 포함된 파일조회
	 * 
	 */
	public abstract ProcessResultListVO<AttachFileVO> list(
			final AssignmentQbankQuestionVO iAssignmentQbankQuestionVO)
			throws DataAccessException;

	/**
	 * 문제등록 첨부파일 테이블에 첨부된 파일들을 삭제한다.
	 * 삭제한 뒤에 SystemFile 영역에서 실제 파일도 삭제해야 한다. 
	 * @param articleVO	삭제하고자 하는 게시물 고유번호
	 * @return
	 */
	public abstract ProcessResultVO<?> delete(
			final AssignmentQbankQuestionVO assignmentQbankQuestionVO)
			throws DataAccessException;


}