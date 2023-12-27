package egovframework.edutrack.modules.teacher.hsty.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.teacher.hsty.service.TchHstyService;
import egovframework.edutrack.modules.teacher.hsty.service.TchHstyVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


@Service("tchHstyService")
public class TchHstyServiceImpl extends EgovAbstractServiceImpl implements TchHstyService {

	/** Mapper */
	@Resource(name="tchHstyMapper")
	private TchHstyMapper 		tchHstyMapper;

	/**
	 * 강사 활동이력 목록 조회
	 *
	 * @return ProcessResultListVO
	 * @throws Exception 
	 */
	public ProcessResultListVO<TchHstyVO> listHsty(TchHstyVO teacherHstyVO, int curPage) throws Exception {
		teacherHstyVO.setCurPage(curPage);
		
		ProcessResultListVO<TchHstyVO> resultList = new ProcessResultListVO<TchHstyVO>(); 
		try {
			List<TchHstyVO> returnList =  tchHstyMapper.listHsty(teacherHstyVO);
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
	 * 강사활동이력 상세 조회
	 *
	 * @return  ProcessResultVO
	 * @throws Exception 
	 */
	public ProcessResultVO<TchHstyVO> viewHsty(String userNo, int hstySn) throws Exception {
		
		ProcessResultVO<TchHstyVO> resultVO = new ProcessResultVO<TchHstyVO>(); 
		try {
			TchHstyVO returnVO =  tchHstyMapper.selectHsty(userNo,hstySn);
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
	 * 강사활동이력 입력
	 *
	 * @return  ProcessResultVO
	 * @throws Exception 
	 */
	public ProcessResultVO<TchHstyVO> addHsty(TchHstyVO teacherHstyVO) throws Exception{
		teacherHstyVO.setActnHstySn(tchHstyMapper.selectKey());
		tchHstyMapper.insertHsty(teacherHstyVO);
		return ProcessResultVO.success();
	}


	/**
	 * 강사활동이력 수정
	 *
	 * @return  ProcessResultVO
	 * @throws Exception 
	 */
	public ProcessResultVO<TchHstyVO> editHsty(TchHstyVO teacherHstyVO) throws Exception{
		tchHstyMapper.updateHsty(teacherHstyVO);
		return ProcessResultVO.success();
	}


	/**
	 * 강사활동이력 삭제
	 * @param  삭제 대상 코드값
	 * @return
	 * @throws Exception 
	 */
	public ProcessResultVO<TchHstyVO> deleteHsty(TchHstyVO teacherHstyVO) throws Exception{
		String userNo =teacherHstyVO.getUserNo();
		int hstySn = teacherHstyVO.getActnHstySn();
		tchHstyMapper.deleteHsty(userNo,hstySn);
		return ProcessResultVO.success();
	}
}
