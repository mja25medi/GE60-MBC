package egovframework.edutrack.modules.course.assignmentbank.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.course.assignmentbank.service.AssignmentQbankQuestionFileService;
import egovframework.edutrack.modules.course.assignmentbank.service.AssignmentQbankQuestionVO;
import egovframework.edutrack.modules.course.assignmentbank.service.AttachFileVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


@Service("assignmentQbankQuestionFileService")
public class AssignmentQbankQuestionFileServiceImpl 
	extends EgovAbstractServiceImpl implements AssignmentQbankQuestionFileService {

	/** Mapper */
	@Resource(name="assignmentQbankQuestionFileMapper")
	private AssignmentQbankQuestionFileMapper 		assignmentQbankQuestionFileMapper;


	/**
	 * 과제문제은행 파일등록
	 * 
	 * @return ProcessResultVO
	 */
	public ProcessResultVO<?> insert(
			final AssignmentQbankQuestionVO VO) throws DataAccessException {
		List<AttachFileVO> fileList = new ArrayList();
		ProcessResultVO<?> resultVO = new ProcessResultVO<>();
		try {
			if(VO.getAttachFiles() != null) {
				for(int i=0; i<VO.getAttachFiles().size(); i++) {
					fileList.add(VO.getAttachFiles().get(i));
				}
			}
			if(VO.getAttachImages() != null) {
				for(int i=0; i<VO.getAttachImages().size(); i++) {
					fileList.add(VO.getAttachImages().get(i));
				}
			}
			assignmentQbankQuestionFileMapper.insert(fileList);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		return resultVO;
	}

	/**
	 * 해당괴제에 포함된 파일조회
	 * 
	 */
	public ProcessResultListVO<AttachFileVO> list(
			final AssignmentQbankQuestionVO iAssignmentQbankQuestionVO)
			throws DataAccessException {
		
		ProcessResultListVO<AttachFileVO> resultList = new ProcessResultListVO<AttachFileVO>();
		try {
			List<AttachFileVO> returnList = assignmentQbankQuestionFileMapper.list(iAssignmentQbankQuestionVO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}		
		return resultList;
	}

	/**
	 * 문제등록 첨부파일 테이블에 첨부된 파일들을 삭제한다.
	 * 삭제한 뒤에 SystemFile 영역에서 실제 파일도 삭제해야 한다. 
	 * @param articleVO	삭제하고자 하는 게시물 고유번호
	 * @return
	 */
	public ProcessResultVO<?> delete(
			final AssignmentQbankQuestionVO assignmentQbankQuestionVO)
			throws DataAccessException {
		
		ProcessResultVO<?> resultVO = new ProcessResultVO<>();
		try {
			assignmentQbankQuestionFileMapper.delete(assignmentQbankQuestionVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}		
		
		return resultVO;
	}

	
}