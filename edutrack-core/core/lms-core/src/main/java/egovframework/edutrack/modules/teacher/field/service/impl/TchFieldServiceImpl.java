package egovframework.edutrack.modules.teacher.field.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.teacher.aplc.service.TchAplcVO;
import egovframework.edutrack.modules.teacher.field.service.TchFieldService;
import egovframework.edutrack.modules.teacher.field.service.TchFieldVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Service("tchFieldService")
public class TchFieldServiceImpl extends EgovAbstractServiceImpl implements TchFieldService {

	/** Mapper */
	@Resource(name="tchFieldMapper")
	private TchFieldMapper 		tchFieldMapper;

	/**
	 * 강사 강의분야 목록 조회
	 *
	 * @return ProcessResultListVO
	 * @throws Exception 
	 */
	public ProcessResultListVO<TchFieldVO> listField(TchFieldVO TeacherFieldVO, int curPage) throws Exception {
		ProcessResultListVO<TchFieldVO> resultList = new ProcessResultListVO<TchFieldVO>();
	
		try {
			List<TchFieldVO> returnList = tchFieldMapper.listField(TeacherFieldVO);
			resultList.setResult(1);
			resultList.setReturnList(returnList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		
		return resultList;

	}

	/**
	 * 강사강의분야 상세 조회
	 *
	 * @return  ProcessResultVO
	 * @throws Exception 
	 */
	public ProcessResultVO<TchFieldVO> viewField(String userNo,String lecFieldCd) throws Exception {
		
		ProcessResultVO<TchFieldVO> resultVO = new ProcessResultVO<TchFieldVO>(); 
		try {
			TchFieldVO returnVO =  tchFieldMapper.selectField(userNo,lecFieldCd);
			resultVO.setResult(1);
			resultVO.setReturnVO(returnVO);
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResult(-1);
			resultVO.setMessage(e.getMessage());
		}
		return resultVO;
		
	}



	/**
	 * 강사강의분야 입력
	 *
	 * @return  ProcessResultVO
	 * @throws Exception 
	 */
	public ProcessResultVO<TchFieldVO> addField(TchFieldVO TeacherFieldVO) throws Exception{
		tchFieldMapper.insertField(TeacherFieldVO);
		return ProcessResultVO.success();
	}


	/**
	 * 강사강의분야 삭제
	 * @param  삭제 대상 코드값
	 * @return
	 * @throws Exception 
	 */
	public ProcessResultVO<TchFieldVO> deleteField(TchFieldVO TeacherFieldVO) throws Exception{
		String userNo =TeacherFieldVO.getUserNo();
		String lecFieldCd =TeacherFieldVO.getLecFieldCd();
		tchFieldMapper.deleteField(userNo,lecFieldCd);
		return ProcessResultVO.success();
	}
}
