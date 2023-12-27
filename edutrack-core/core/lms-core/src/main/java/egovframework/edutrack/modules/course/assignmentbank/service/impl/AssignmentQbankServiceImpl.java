package egovframework.edutrack.modules.course.assignmentbank.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.course.assignmentbank.service.AssignmentQbankCategoryVO;
import egovframework.edutrack.modules.course.assignmentbank.service.AssignmentQbankService;
import egovframework.edutrack.modules.course.assignmentbank.service.AssignmentQbankQuestionVO;
import egovframework.edutrack.modules.course.assignmentbank.service.AttachFileVO;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


@Service("assignmentQbankService")
public class AssignmentQbankServiceImpl 
	extends EgovAbstractServiceImpl implements AssignmentQbankService {

	/** Mapper */
	@Resource(name="assignmentQbankQuestionMapper")
	private AssignmentQbankQuestionMapper 		assignmentQbankQuestionMapper;

	@Resource(name="assignmentQbankQuestionFileMapper")
	private AssignmentQbankQuestionFileMapper 		assignmentQbankQuestionFileMapper;

	@Resource(name="sysFileService")
	private SysFileService 		sysFileService;

	/**
	 * 과제 문제은행 문제 목록 조회
	 * 
	 * @return ProcessResultListVO
	 */
	@Override	
	public ProcessResultListVO<AssignmentQbankQuestionVO> listQuestion(String sbjCd, String ctgrCd)  throws Exception{
		AssignmentQbankQuestionVO questionVO = new AssignmentQbankQuestionVO();
		questionVO.setSbjCd(sbjCd);
		questionVO.setCtgrCd(ctgrCd);
		ProcessResultListVO<AssignmentQbankQuestionVO> resultList = new ProcessResultListVO<AssignmentQbankQuestionVO>();
		try {
			List<AssignmentQbankQuestionVO> returnList = assignmentQbankQuestionMapper.list(questionVO);
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
	 * 과제 문제은행 문제 정보 조회
	 *
	 * @return  ProcessResultVO
	 */
	@Override	
	public ProcessResultVO<AssignmentQbankQuestionVO> viewQuestion( AssignmentQbankQuestionVO assignmentQbankQuestionVO)  throws Exception{
		
		ProcessResultVO<AssignmentQbankQuestionVO> resultVO = new ProcessResultVO<AssignmentQbankQuestionVO>();
		try {
			AssignmentQbankQuestionVO returnVO = assignmentQbankQuestionMapper.select(assignmentQbankQuestionVO);
			returnVO = this.listAttachFiles(returnVO).getReturnVO();
			resultVO.setReturnVO(returnVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}		

		return resultVO;

	}
	
	/**
	 * 과제 문제은행 문제 정보 등록
	 *
	 * @return  ProcessResultVO
	 */
	@Override	
	public ProcessResultVO<AssignmentQbankQuestionVO> addQuestion(
			AssignmentQbankQuestionVO iAssignmentQbankQuestionVO,List<Integer> txAttachFiles,List<Integer> txAttachImages
		) throws Exception{

		
		ProcessResultVO<AssignmentQbankQuestionVO> resultVO = new ProcessResultVO<AssignmentQbankQuestionVO>();
		try {
			iAssignmentQbankQuestionVO.setQstnSn(assignmentQbankQuestionMapper.selectSn().getQstnSn());
			assignmentQbankQuestionMapper.insert(iAssignmentQbankQuestionVO);
			iAssignmentQbankQuestionVO = assignmentQbankQuestionMapper.select(iAssignmentQbankQuestionVO);
			
	    	// 주키가 생성된 이후 첨부파일번호들을 바인딩해서 저장
			iAssignmentQbankQuestionVO.addAttachFiles(txAttachFiles);
			iAssignmentQbankQuestionVO.addAttachImages(txAttachImages);

		   	

			List<AttachFileVO> fileList = new ArrayList();
			if(iAssignmentQbankQuestionVO.getAttachFiles() != null) {
				for(int i=0; i<iAssignmentQbankQuestionVO.getAttachFiles().size(); i++) {
					fileList.add(iAssignmentQbankQuestionVO.getAttachFiles().get(i));
				}
			}
			if(iAssignmentQbankQuestionVO.getAttachImages() != null) {
				for(int i=0; i<iAssignmentQbankQuestionVO.getAttachImages().size(); i++) {
					fileList.add(iAssignmentQbankQuestionVO.getAttachImages().get(i));
				}
			}
			assignmentQbankQuestionFileMapper.insert(fileList);
			
			resultVO.setReturnVO(iAssignmentQbankQuestionVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}		
	    
		return resultVO; 

	}

	/**
	 * 과제 문제은행 문제 정보 수정
	 * 파일정보와 등록된 이미지 정보를 가져온다.
	 * @return  ProcessResultVO
	 */
	@Override	
	public ProcessResultVO<AssignmentQbankQuestionVO> editQuestion(
			AssignmentQbankQuestionVO iAssignmentQbankQuestionVO,List<Integer> txAttachFiles,List<Integer> txAttachImages) throws Exception{
		
		ProcessResultVO<AssignmentQbankQuestionVO> resultVO = new ProcessResultVO<AssignmentQbankQuestionVO>();
		try {
			assignmentQbankQuestionFileMapper.delete(iAssignmentQbankQuestionVO);
			
			iAssignmentQbankQuestionVO.addAttachFiles(txAttachFiles);
			iAssignmentQbankQuestionVO.addAttachImages(txAttachImages);

			List<AttachFileVO> fileList = new ArrayList();
			if(iAssignmentQbankQuestionVO.getAttachFiles() != null) {
				for(int i=0; i<iAssignmentQbankQuestionVO.getAttachFiles().size(); i++) {
					fileList.add(iAssignmentQbankQuestionVO.getAttachFiles().get(i));
				}
			}
			if(iAssignmentQbankQuestionVO.getAttachImages() != null) {
				for(int i=0; i<iAssignmentQbankQuestionVO.getAttachImages().size(); i++) {
					fileList.add(iAssignmentQbankQuestionVO.getAttachImages().get(i));
				}
			}

			assignmentQbankQuestionFileMapper.insert(fileList);
			assignmentQbankQuestionMapper.update(iAssignmentQbankQuestionVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}		
		
		
		return resultVO;
	}
	

	/**
	 * 과제 문제은행 문제 정보 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@Override	
	public ProcessResultVO<AssignmentQbankQuestionVO> deleteQuestion(AssignmentQbankQuestionVO assignmentQbankQuestionVO) throws Exception{

		
		ProcessResultVO<AssignmentQbankQuestionVO> resultVO = new ProcessResultVO<AssignmentQbankQuestionVO>();
		try {
			//실제 등록된 파일을 삭제한다.
			AssignmentQbankQuestionVO qustion = this.getArticle(assignmentQbankQuestionVO).getReturnVO();
			
			for (Integer fileSn : qustion.getAttachSerials()) {
				sysFileService.removeFile(fileSn);	// 실제 파일을 포함해서 삭제
			}
			assignmentQbankQuestionMapper.delete(assignmentQbankQuestionVO);
			resultVO.setReturnVO(qustion);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}		
		
		
		
		return resultVO;
	}
	/**
	 * 게시판 글 정보 조회 (조회수 미증가)
	 * @param articleVO 글 고유 번호
	 * @return 조회 결과 VO
	 */
	@Override	
	public ProcessResultVO<AssignmentQbankQuestionVO> getArticle(AssignmentQbankQuestionVO assignmentQbankQuestionVO) throws Exception{

		ProcessResultVO<AssignmentQbankQuestionVO> resultVO = new ProcessResultVO<AssignmentQbankQuestionVO>();
		try {
			AssignmentQbankQuestionVO returnVO = assignmentQbankQuestionMapper.select(assignmentQbankQuestionVO);
			// 첨부파일 정보 조회
			returnVO = this.listAttachFiles(assignmentQbankQuestionVO).getReturnVO();

			resultVO.setReturnVO(returnVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}		

		
		return resultVO;
	}
	
	/**
	 * 과제 문제은행 첨부파일 목ㄹ
	 *
	 * @return  ProcessResultVO
	 */
	
	@Override	
	public ProcessResultVO<AssignmentQbankQuestionVO> listAttachFiles(AssignmentQbankQuestionVO assignmentQbankQuestionVO) throws Exception{

		ProcessResultVO<AssignmentQbankQuestionVO> resultVO = new ProcessResultVO<AssignmentQbankQuestionVO>();
		try {
			List<AttachFileVO> attachFiles = assignmentQbankQuestionFileMapper.list(assignmentQbankQuestionVO);
			
			for (AttachFileVO attachFile : attachFiles) {
				if(attachFile.getFileType().equals("image")) {
					assignmentQbankQuestionVO.addAttachImages(attachFile);
				} else if(attachFile.getFileType().equals("file")){
					assignmentQbankQuestionVO.addAttachFiles(attachFile);
				}
			}
			resultVO.setReturnVO(assignmentQbankQuestionVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}		

		
		return resultVO;
	}

	
}