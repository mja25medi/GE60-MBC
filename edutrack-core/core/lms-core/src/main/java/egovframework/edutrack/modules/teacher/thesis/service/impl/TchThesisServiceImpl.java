package egovframework.edutrack.modules.teacher.thesis.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.teacher.thesis.service.TchThesisService;
import egovframework.edutrack.modules.teacher.thesis.service.TchThesisVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


@Service("tchThesisService")
public class TchThesisServiceImpl extends EgovAbstractServiceImpl implements TchThesisService {

	/** Mapper */
	@Resource(name="tchThesisMapper")
	private TchThesisMapper 		tchThesisMapper;

	/**
	 * 강사 학력 목록 조회
	 *
	 * @return ProcessResultListVO
	 * @throws Exception 
	 */
	public ProcessResultListVO<TchThesisVO> listThesis(TchThesisVO teacherThesisVO, int curPage) throws Exception {
		teacherThesisVO.setCurPage(curPage);
		
		ProcessResultListVO<TchThesisVO> resultList = new ProcessResultListVO<TchThesisVO>(); 
		try {
			List<TchThesisVO> returnList =  tchThesisMapper.listThesis(teacherThesisVO);
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
	 * 강사학력 상세 조회
	 *
	 * @return  ProcessResultVO
	 * @throws Exception 
	 */
	public ProcessResultVO<TchThesisVO> viewThesis(TchThesisVO teacherThesisVO) throws Exception {
		
		ProcessResultVO<TchThesisVO> resultVO = new ProcessResultVO<TchThesisVO>(); 
		try {
			TchThesisVO returnVO = tchThesisMapper.selectThesis(teacherThesisVO);
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
	 * 강사 발표논문 입력
	 *
	 * @return  ProcessResultVO
	 * @throws Exception 
	 */
	public ProcessResultVO<TchThesisVO> addThesis(TchThesisVO teacherThesisVO) throws Exception{
		teacherThesisVO.setThesisSn(tchThesisMapper.selectKey());
		tchThesisMapper.insertThesis(teacherThesisVO);
		return ProcessResultVO.success();
	}


	/**
	 * 강사 발표논문 수정
	 *
	 * @return  ProcessResultVO
	 * @throws Exception 
	 */
	public ProcessResultVO<TchThesisVO> editThesis(TchThesisVO teacherThesisVO) throws Exception{
		tchThesisMapper.updateThesis(teacherThesisVO);
		return ProcessResultVO.success();
	}


	/**
	 * 강사 발표논문 삭제
	 * @param  삭제 대상 코드값
	 * @return
	 * @throws Exception 
	 */
	public ProcessResultVO<TchThesisVO> deleteThesis(TchThesisVO teacherThesisVO) throws Exception{
		String userNo =teacherThesisVO.getUserNo();
		int thesisSn = teacherThesisVO.getThesisSn();
		tchThesisMapper.deleteThesis(userNo,thesisSn);
		return ProcessResultVO.success();
	}
}
