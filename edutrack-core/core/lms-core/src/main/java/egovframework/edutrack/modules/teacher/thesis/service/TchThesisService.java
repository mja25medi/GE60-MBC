package egovframework.edutrack.modules.teacher.thesis.service;



import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;



/**
 * <strong>강사 - 강사정보</strong> 영역 서비스 인터페이스
 * @author DongKwang
 */
public interface TchThesisService {

	/**
	 * 강사  발표논문 조회
	 * 
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<TchThesisVO> listThesis(TchThesisVO teacherThesisVO, int curPage) throws Exception;
	
	
	
	
	/**
	 * 강사 발표논문 입력
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<TchThesisVO> addThesis(TchThesisVO teacherThesisVO) throws Exception;
	
	
	/**
	 * 강사 발표논문 상세 조회
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<TchThesisVO> viewThesis(TchThesisVO teacherThesisVO) throws Exception;
	
	
	/**
	 * 강사 발표논문 수정
	 *
	 * @return  ProcessResultVO
	 */
	public abstract ProcessResultVO<TchThesisVO> editThesis(TchThesisVO teacherThesisVO) throws Exception;
	

	/**
	 *  강사 발표논문 삭제
	 * @param userSn 삭제 대상 코드값
	 * @return
	 */
	public abstract ProcessResultVO<TchThesisVO> deleteThesis(TchThesisVO teacherThesisVO) throws Exception;
	
	
	

}