package egovframework.edutrack.modules.teacher.info.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.teacher.info.service.TchInfoService;
import egovframework.edutrack.modules.teacher.info.service.TchInfoVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * <strong>강사 - 강사 코드</strong> 영역 서비스 구현체.<br>
 * 트랜잭션의 경계
 *
 * @author SungKook
 */
@Service("tchInfoService")
public class TchInfoServiceImpl
		extends EgovAbstractServiceImpl implements TchInfoService {

	/** Mapper */
	@Resource(name="tchInfoMapper")
	private TchInfoMapper 		tchInfoMapper;
	

	/**
	 * 강사의 단일행 정보를 검색하여 반환한다.
	 * @param teacherInfoVO
	 * @return
	 * @throws Exception 
	 */
	public ProcessResultVO<TchInfoVO> view(TchInfoVO teacherInfoVO) throws Exception {
		ProcessResultVO<TchInfoVO> resultVO = new ProcessResultVO<TchInfoVO>(); 
		try {
			TchInfoVO returnVO =  tchInfoMapper.select(teacherInfoVO);
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
	 * 강사의 정보를 등록하고 결과를 반환한다.
	 * @param teacherInfoVO
	 * @return
	 * @throws Exception 
	 */
	public ProcessResultVO<TchInfoVO> add(TchInfoVO teacherInfoVO) throws Exception {
		ProcessResultVO<TchInfoVO> resultVO = new ProcessResultVO<TchInfoVO>(); 
		try {
			tchInfoMapper.insert(teacherInfoVO);
			resultVO.setResult(1);
			resultVO.setReturnVO(teacherInfoVO);
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResult(-1);
			resultVO.setMessage(e.getMessage());
		}
		return resultVO;
	}

	/**
	 * 강사의 정보를 수정하고 결과를 반환한다.
	 * @param teacherInfoVO
	 * @return
	 * @throws Exception 
	 */
	public ProcessResultVO<TchInfoVO> edit(TchInfoVO teacherInfoVO) throws Exception {
		ProcessResultVO<TchInfoVO> resultVO = new ProcessResultVO<TchInfoVO>(); 
		try {
			tchInfoMapper.update(teacherInfoVO);
			resultVO.setResult(1);
			resultVO.setReturnVO(teacherInfoVO);
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResult(-1);
			resultVO.setMessage(e.getMessage());
		}
		return resultVO;
	}

	/**
	 * 강사의 정보를 삭제하고 결과를 반환한다.
	 * @param teacherInfoVO
	 * @return
	 * @throws Exception 
	 */
	public ProcessResultVO<?> remove(TchInfoVO teacherInfoVO) throws Exception {
		ProcessResultVO<TchInfoVO> resultVO = new ProcessResultVO<TchInfoVO>(); 
		try {
			tchInfoMapper.delete(teacherInfoVO);
			resultVO.setResult(1);
			resultVO.setReturnVO(teacherInfoVO);
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResult(-1);
			resultVO.setMessage(e.getMessage());
		}
		return resultVO;
	}
}
