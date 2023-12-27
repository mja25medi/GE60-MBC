package egovframework.edutrack.modules.course.research.service;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface ResearchBankService {

	/**
	 * 설문 목록 조회
	 */
	public abstract ProcessResultListVO<ResearchBankVO> listResearch(
			ResearchBankVO iResearchBankVO) throws Exception;

	/**
	 * 설문 목록 조회(설문명 검색)
	 */
	public abstract	ProcessResultListVO<ResearchBankVO> searchListResearch(ResearchBankVO iResearchBankVO) throws Exception;

	/**
	 * 설문 목록 조회
	 * (페이징 정보 포함)
	 * @param ResearchBankVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<CourseVO>
	 */
	public abstract ProcessResultListVO<ResearchBankVO> listPageing(ResearchBankVO iResearchBankVO,
			int curPage, int listScale, int pageScale) throws Exception;

	/**
	 * 설문 목록 조회
	 * (페이징 정보 포함)
	 * @param ResearchBankVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<CourseVO>
	 */
	public abstract ProcessResultListVO<ResearchBankVO> listPageing(ResearchBankVO iResearchBankVO,
			int curPage, int listScale) throws Exception;

	/**
	 * 설문 목록 조회
	 * (페이징 정보 포함)
	 * @param ResearchBankVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<CourseVO>
	 */
	public abstract ProcessResultListVO<ResearchBankVO> listPageing(ResearchBankVO iResearchBankVO,
			int curPage) throws Exception;

	/**
	 * 설문 정보 조회
	 */
	public abstract ProcessResultVO<ResearchBankVO> viewResearch(ResearchBankVO iResearchBankVO) throws Exception;

	/**
	 * 설문 정보 등록
	 */
	public abstract ProcessResultVO<ResearchBankVO> addResearch(ResearchBankVO iResearchBankVO) throws Exception;

	/**
	 * 서룸 정보 수정
	 */
	public abstract ProcessResultVO<ResearchBankVO> editResearch(ResearchBankVO iResearchBankVO) throws Exception;

	/**
	 * 서룸 정보 삭제
	 */
	public abstract ProcessResultVO<ResearchBankVO> deleteResearch(
			ResearchBankVO iResearchBankVO) throws Exception;

	/**
	 * 설문 문제 목록 조회
	 */
	public abstract ProcessResultListVO<ResearchBankItemVO> listItem(
			ResearchBankVO iResearchBankVO) throws Exception;

	/**
	 * 설문 문제 정보 조회
	 */
	public abstract ProcessResultVO<ResearchBankItemVO> viewItem(
			ResearchBankItemVO iResearchBankItemVO) throws Exception;

	/**
	 * 설문 문제 정보 등록
	 */
	public abstract ProcessResultVO<ResearchBankItemVO> addItem(
			ResearchBankItemVO iResearchBankItemVO) throws Exception;

	/**
	 * 설문 문제 정보 수정
	 */
	public abstract ProcessResultVO<ResearchBankItemVO> editItem(
			ResearchBankItemVO iResearchBankItemVO) throws Exception;

	/**
	 * 설문 문제 정보 삭제
	 */
	public abstract ProcessResultVO<ResearchBankItemVO> deleteItem(
			ResearchBankItemVO iResearchBankItemVO) throws Exception;

	/**
	 * 강의실 설문등록 폼에서 설문은행 목록 조회시 기등록된 설문은 제외하다.
	 */
	public abstract ProcessResultListVO<ResearchBankVO> listResearchPreclusive(
			ResearchBankVO iResearchBankVO) throws Exception;

	/**
	 * 설문 문제 순서를 변경 하고 결과를 반환한다.
	 * @param ResearchBankItemVO
	 * @return ProcessResultVO<ResearchBankItemVO>
	 */
	public abstract ProcessResultVO<?> sortReserchItem(ResearchBankItemVO VO) throws Exception;

	/**
	 * 설문 문제 목록 조회
	 */
	public abstract ProcessResultListVO<ResearchBankItemVO> listItem(
			ResearchBankItemVO iResearchBankVO) throws Exception;

	/**
	 * 샘플 엑셀파일 다운로드
	 * @param (HashMap<String, String> titles
	 * @param OutputStream os
	 * @throws ServiceProcessException
	 */
	public abstract void sampleExcelDownload(HashMap<String, String> titles, OutputStream os
			) throws ServiceProcessException;

	/**
	 * Upload 사용자 정보 체크 하여 돌려 준다.
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public abstract ProcessResultListVO<ResearchBankItemVO> excelUploadValidationCheck(
			String fileName, String filePath) throws ServiceProcessException;

	/**
	 * 설문 문제 일괄 등록
	 * @param List<UserInfoVO> userList
	 * @return
	 */
	public abstract ProcessResultVO<ResearchBankItemVO> addResearchBankItemBatch(
			List<ResearchBankItemVO> researchBankList) throws Exception;
	
	/**
	 * 설문 문제 목록 조회
	 */
	public	abstract ProcessResultVO<ResearchBankItemVO> addResearchBankItemBatch(ResearchBankItemVO VO) throws Exception;

}