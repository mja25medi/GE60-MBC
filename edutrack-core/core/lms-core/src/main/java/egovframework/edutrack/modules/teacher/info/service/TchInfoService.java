package egovframework.edutrack.modules.teacher.info.service;

import egovframework.edutrack.comm.service.ProcessResultVO;

public interface TchInfoService {

	/**
	 * 강사의 단일행 정보를 검색하여 반환한다.
	 * @param teacherInfoVO
	 * @return
	 */
	public abstract ProcessResultVO<TchInfoVO> view(
			TchInfoVO teacherInfoVO)throws Exception;	

	/**
	 * 강사의 정보를 등록하고 결과를 반환한다.
	 * @param teacherInfoVO
	 * @return
	 */
	public abstract ProcessResultVO<TchInfoVO> add(
			TchInfoVO teacherInfoVO)throws Exception;	

	/**
	 * 강사의 정보를 수정하고 결과를 반환한다.
	 * @param teacherInfoVO
	 * @return
	 */
	public abstract ProcessResultVO<TchInfoVO> edit(
			TchInfoVO teacherInfoVO)throws Exception;	

	/**
	 * 강사의 정보를 삭제하고 결과를 반환한다.
	 * @param teacherInfoVO
	 * @return
	 */
	public abstract ProcessResultVO<?> remove(TchInfoVO teacherInfoVO)throws Exception;	

}