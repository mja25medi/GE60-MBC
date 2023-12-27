package egovframework.edutrack.modules.course.creCrsResh.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface CreCrsReshService {

	/**
	 * 개설 과정 설문 목록 조회
	 */
	public abstract ProcessResultListVO<CreCrsReshVO> list(CreCrsReshVO VO) throws Exception;

	/**
	 * 개설 과정 설문 페이징 목록 조회
	 */
	public abstract ProcessResultListVO<CreCrsReshVO> listPageing(
			CreCrsReshVO VO, int curPage, int listScale, int pageScale) throws Exception;

	/**
	 * 개설 과정 설문 페이징 목록 조회
	 */
	public abstract ProcessResultListVO<CreCrsReshVO> listPageing(
			CreCrsReshVO VO, int curPage, int listScale) throws Exception;

	/**
	 * 개설 과정 설문 페이징 목록 조회
	 */
	public abstract ProcessResultListVO<CreCrsReshVO> listPageing(
			CreCrsReshVO VO, int curPage) throws Exception;

	/**
	 * 개설 과정 설문 정보 조회			
	 */
	public abstract ProcessResultVO<CreCrsReshVO> view(CreCrsReshVO VO) throws Exception;

	/**
	 * 개설 과정 설문 정보 등록 
	 */
	public abstract ProcessResultVO<CreCrsReshVO> add(CreCrsReshVO VO) throws Exception;

	/**
	 * 개설 과정 설문 정보 수정
	 */
	public abstract ProcessResultVO<CreCrsReshVO> edit(CreCrsReshVO VO) throws Exception;

	/**
	 * 개설 과정 설문 정보 삭제
	 */
	public abstract ProcessResultVO<CreCrsReshVO> remove(CreCrsReshVO VO) throws Exception;
	
	/**
	 * 개설 과정 설문 답변 갯수 반환
	 */
	public abstract ProcessResultVO<CreCrsReshVO> ansrCount(CreCrsReshVO VO) throws Exception;
	
	/**
	 * 개설 과정 설문 갯수 반환
	 */
	public abstract ProcessResultVO<CreCrsReshVO> itemCount(CreCrsReshVO VO) throws Exception;

}