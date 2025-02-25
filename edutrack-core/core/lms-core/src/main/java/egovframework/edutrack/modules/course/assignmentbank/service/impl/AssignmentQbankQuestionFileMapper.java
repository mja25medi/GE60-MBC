package egovframework.edutrack.modules.course.assignmentbank.service.impl;

import java.util.List;

import egovframework.edutrack.modules.course.assignmentbank.service.AssignmentQbankQuestionVO;
import egovframework.edutrack.modules.course.assignmentbank.service.AttachFileVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("assignmentQbankQuestionFileMapper")
public interface AssignmentQbankQuestionFileMapper {
	
	/**
	 * 과제문제은행 파일등록
	 * 
	 * @return int
	 */
	public int insert(List<AttachFileVO> fileList)  ;
	
	/**
	 * 해당괴제에 포함된 파일조회
	 * 
	 */
	
	public List<AttachFileVO> list(AssignmentQbankQuestionVO iAssignmentQbankQuestionVO)  ;
	
	/**
	 * 문제등록 첨부파일 테이블에 첨부된 파일들을 삭제한다.
	 * 삭제한 뒤에 SystemFile 영역에서 실제 파일도 삭제해야 한다. 
	 * @param articleVO	삭제하고자 하는 게시물 고유번호
	 * @return
	 */
	public int delete(AssignmentQbankQuestionVO assignmentQbankQuestionVO)  ;
}
