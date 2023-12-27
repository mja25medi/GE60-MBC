package egovframework.edutrack.modules.course.assignmentbank.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.course.assignmentbank.service.AssignmentQbankCategoryVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.edutrack.modules.course.assignmentbank.service.AssignmentQbankCategoryService;

@Service("assignmentQbankCategoryService")
public class AssignmentQbankCategoryServiceImpl 
	extends EgovAbstractServiceImpl implements AssignmentQbankCategoryService {

	/** Mapper */
	@Resource(name="assignmentQbankCategoryMapper")
	private AssignmentQbankCategoryMapper 		assignmentQbankCategoryMapper;
	
	
	/**
	 * 과제 문제은행 분류 목록 조회
	 * 
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<AssignmentQbankCategoryVO> listCategory(String sbjCd)  throws Exception{
		ProcessResultListVO<AssignmentQbankCategoryVO> resultList = new ProcessResultListVO<AssignmentQbankCategoryVO>();
		try {
			List<AssignmentQbankCategoryVO> returnList = assignmentQbankCategoryMapper.list(sbjCd);
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
	 * 과제 문제은행 분류 정보 조회
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<AssignmentQbankCategoryVO> viewCategory(String sbjCd, String ctgrCd) throws Exception {
		AssignmentQbankCategoryVO ctgrVO = new AssignmentQbankCategoryVO();
		ctgrVO.setSbjCd(sbjCd);
		ctgrVO.setCtgrCd(ctgrCd);
		ProcessResultVO<AssignmentQbankCategoryVO> resultVO = new ProcessResultVO<AssignmentQbankCategoryVO>();
		try {
			AssignmentQbankCategoryVO returnVO = assignmentQbankCategoryMapper.select(ctgrVO);
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
	 * 과제 문제은행 분류 정보 등록
	 *
	 * @return  ProcessResultVO
	 */
	@Override	
	public ProcessResultVO<AssignmentQbankCategoryVO> addCategory(AssignmentQbankCategoryVO iAssignmentQbankCategoryVO) throws Exception{

		ProcessResultVO<AssignmentQbankCategoryVO> resultVO = new ProcessResultVO<AssignmentQbankCategoryVO>();
		try {
			AssignmentQbankCategoryVO examQbankCategoryVO = assignmentQbankCategoryMapper.selectCd();
			
			//---- 신규 코드 세팅
			iAssignmentQbankCategoryVO.setCtgrCd(examQbankCategoryVO.getCtgrCd());

			assignmentQbankCategoryMapper.insert(iAssignmentQbankCategoryVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}		
		
		return resultVO;
	}

	/**
	 * 과제 문제은행 분류 정보 수정
	 *
	 * @return  ProcessResultVO
	 */
	@Override	
	public ProcessResultVO<AssignmentQbankCategoryVO> editCategory(AssignmentQbankCategoryVO iAssignmentQbankCategoryVO) throws Exception{

		ProcessResultVO<AssignmentQbankCategoryVO> resultVO = new ProcessResultVO<AssignmentQbankCategoryVO>();
		try {
			assignmentQbankCategoryMapper.update(iAssignmentQbankCategoryVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}		
		
		
		return resultVO;
	}
	

	/**
	 * 과제 문제은행 분류 정보 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@Override	
	public ProcessResultVO<AssignmentQbankCategoryVO> deleteCategory(String sbjCd, String ctgrCd) throws Exception{
		AssignmentQbankCategoryVO ctgrVO = new AssignmentQbankCategoryVO();
		ctgrVO.setSbjCd(sbjCd);
		ctgrVO.setCtgrCd(ctgrCd);
		ProcessResultVO<AssignmentQbankCategoryVO> resultVO = new ProcessResultVO<AssignmentQbankCategoryVO>();
		try {
			assignmentQbankCategoryMapper.delete(ctgrVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}		
		
		return resultVO;
	}
	

	
}