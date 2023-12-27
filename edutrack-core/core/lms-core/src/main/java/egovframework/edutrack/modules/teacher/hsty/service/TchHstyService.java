package egovframework.edutrack.modules.teacher.hsty.service;


import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;



/**
 * <strong>강사 - 강사정보</strong> 영역 서비스 인터페이스
 * @author DongKwang
 */
public interface TchHstyService {

	/**
	 * 강사 학력 조회
	 * 
	 * @return ProcessResultListVO
	 * @throws Exception 
	 */
	public abstract ProcessResultListVO<TchHstyVO> listHsty(TchHstyVO teacherHstyVO, int curPage) throws Exception;
	
	
	
	
	/**
	 * 강사학력 입력
	 *
	 * @return  ProcessResultVO
	 * @throws Exception 
	 */
	public abstract ProcessResultVO<TchHstyVO> addHsty(TchHstyVO teacherHstyVO) throws Exception;
	
	
	/**
	 * 강사학력 상세 조회
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<TchHstyVO> viewHsty(String userNo, int hstySn) throws Exception;
	
	
	/**
	 * 강사학력 수정
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<TchHstyVO> editHsty(TchHstyVO teacherHstyVO) throws Exception;
	

	/**
	 *  강사학력 삭제
	 * @param userSn 삭제 대상 코드값
	 * @return
	 */
	public abstract ProcessResultVO<TchHstyVO> deleteHsty(TchHstyVO teacherHstyVO) throws Exception;
	
	
	

}