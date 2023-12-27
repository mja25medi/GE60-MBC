package egovframework.edutrack.modules.org.resh.service;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface OrgReshService {

	/**
	 * 일반 설문 목록 조회
	 */
	public abstract ProcessResultListVO<OrgReshVO> listResh(OrgReshVO VO) throws Exception;

	/**
	 * 나의 일반 설문 목록 조회
	 */
	public abstract ProcessResultListVO<OrgReshVO> listMyResh(OrgReshVO VO) throws Exception;
	
	/**
	 * 나의 일반 설문 목록 조회 - 페이징
	 */
	public abstract ProcessResultListVO<OrgReshVO> listMyReshPageing(OrgReshVO VO) throws Exception;
	
	/**
	 * 일반 설문 목록 조회
	 * (페이징 정보 포함)
	 */
	public abstract ProcessResultListVO<OrgReshVO> listPageing(OrgReshVO VO, int curPage, int listScale, int pageScale)
			throws Exception;
	
	/**
	 * 일반 설문 목록 조회
	 * (페이징 정보 포함)
	 */
	public abstract ProcessResultListVO<OrgReshVO> listPageing(OrgReshVO VO, int curPage, int listScale) throws Exception;

	/**
	 * 일반 설문 목록 조회
	 * (페이징 정보 포함)
	 */
	public abstract ProcessResultListVO<OrgReshVO> listPageing(OrgReshVO VO, int curPage) throws Exception;
	
	/**
	 * 일반 설문 목록 조회(설문명 검색)
	 */
	public abstract ProcessResultListVO<OrgReshVO> searchListResearch(OrgReshVO VO) throws Exception;

	/**
	 * 일반 설문 정보 조회
	 */
	public abstract ProcessResultVO<OrgReshVO> viewResearch(OrgReshVO VO) throws Exception;
	
	/**
	 * 일반 설문 정보 등록
	 */
	public abstract ProcessResultVO<OrgReshVO> addResearch(OrgReshVO VO) throws Exception;
	
	/**
	 * 일반 설문 정보 수정
	 */
	public abstract ProcessResultVO<OrgReshVO> editResearch(OrgReshVO VO) throws Exception;
	
	/**
	 * 일반 설문 정보 삭제
	 */
	public abstract ProcessResultVO<OrgReshVO> deleteResearch(OrgReshVO VO) throws Exception;

	/**
	 * 일반 설문 문제 목록 조회
	 */
	public abstract ProcessResultListVO<OrgReshItemVO> listItem(OrgReshVO VO) throws Exception;
	
	/**
	 * 일반 설문 문제 목록 조회
	 */
	public abstract ProcessResultListVO<OrgReshItemVO> listItem(OrgReshItemVO VO) throws Exception;

	
	/**
	 * 설문 문제 정보 조회
	 */
	public abstract ProcessResultVO<OrgReshItemVO> viewItem(OrgReshItemVO VO) throws Exception;
	
	/**
	 * 설문 문제 정보 등록
	 */
	public abstract ProcessResultVO<OrgReshItemVO> addItem(OrgReshItemVO VO) throws Exception;
	
	/**
	 * 설문 문제 정보 수정
	 */
	public abstract ProcessResultVO<OrgReshItemVO> editItem(OrgReshItemVO VO) throws Exception;
	
	/**
	 * 설문 문제 정보 삭제
	 */
	public abstract ProcessResultVO<OrgReshItemVO> deleteItem(OrgReshItemVO VO) throws Exception;
	
	/**
	 * 강의실 설문등록 폼에서 설문은행 목록 조회시 기등록된 설문은 제외하다.
	 */
	ProcessResultListVO<OrgReshVO> listResearchPreclusive(OrgReshVO VO) throws Exception;
	
	ProcessResultVO<?> sortReserchItem(OrgReshItemVO VO);
	
	/**
	 * 일반 설문 답변 저장
	 */
	public abstract int addAnsr(OrgReshAnsrVO VO) throws Exception;

	/**
	 * 샘플 엑셀파일 다운로드
	 * @param (HashMap<String, String> titles
	 * @param OutputStream os
	 * @throws ServiceProcessException
	 */
	void sampleExcelDownload(HashMap<String, String> titles, OutputStream os) throws ServiceProcessException;

	/**
	 * Upload 된 Excel의 정보를 확인하여 돌려 준다.
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	ProcessResultListVO<OrgReshItemVO> excelUploadValidationCheck(String fileName, String filePath)
			throws ServiceProcessException;

	/**
	 * 설문 문제 목록 조회
	 */
	ProcessResultVO<OrgReshItemVO> addOrgReshItemBatch(OrgReshItemVO VO) throws Exception;

	ProcessResultVO<OrgReshItemVO> addOrgReshItemBatch(List<OrgReshItemVO> orgReshList) throws Exception;

	/**
	 * 일반 설문 통계 리스트 조회
	 */
	public	ProcessResultListVO<OrgReshItemVO> listStatResearch(OrgReshItemVO vo) throws Exception ;
	
	/**
	 * 일반 설문현황 다운로드
	 */
	public void listExcelDownload(OrgReshItemVO vo, String orgNm, ArrayList<String> titleList, OutputStream os) throws Exception;
	
	/**
	 * 일반 설문현황 다운로드
	 */
	public void listExcelScoreDownload(OrgReshItemVO vo, String orgNm, OutputStream os) throws Exception;
	
	/**
	 * 과목 설문 통계 리스트 조회
	 */
	public	ProcessResultListVO<OrgReshItemVO> listStatCrsResearch(OrgReshItemVO vo) throws Exception ;
	
	/**
	 * 과목 설문현황 다운로드
	 */
	public void listCrsExcelDownload(OrgReshItemVO vo, String orgNm, ArrayList<String> titleList, OutputStream os) throws Exception;
	
	/**
	 * 개설 과정 리스트 조회
	 */
	public	ProcessResultListVO<OrgReshItemVO> listStatCrsResearchCrsCre(OrgReshItemVO vo) throws Exception ;

	public abstract ProcessResultListVO<OrgReshAnsrVO> getAnsrList(OrgReshAnsrVO orgReshAnsrVO) throws Exception;

	/**
	 * 개설 과정 설문 정보 등록
	 */
	public abstract ProcessResultVO<OrgReshAnsrVO> addReshAnsr(OrgReshAnsrVO VO) throws Exception;

	/**
	 * 일반 설문 결과 목록, 교육생 설문 응시 답 조회
	 */
	public abstract ProcessResultListVO<OrgReshResultVO> listReshAnsr(
			OrgReshResultVO VO) throws Exception;


	/**
	 * 퇴교 설문 작성 교육생 리스트
	 * (페이징 정보 포함)
	 */
	public abstract ProcessResultListVO<OrgReshAnsrVO> listExpulsionPageing(OrgReshAnsrVO vo) throws Exception;

	/**
	 * 퇴교 설문 답안 리스트
	 * (페이징 정보 포함)
	 */
	public abstract ProcessResultListVO<OrgReshAnsrVO> reshAnsrList(OrgReshAnsrVO vo) throws Exception;

	/**
	 * 퇴교 설문 조회
	 */
	public abstract ProcessResultVO<OrgReshVO> expulsionResh(OrgReshVO VO) throws Exception;
	
	/**
	 * 설문결과 점수결과보기
	 */
	public ProcessResultListVO<OrgReshItemVO> listRsltScore(OrgReshItemVO vo) throws Exception ;
	
	/**
	 * 설문결과 결과보기
	 */
	public ProcessResultListVO<OrgReshItemVO> listRslt(OrgReshItemVO vo) throws Exception ;

	/**
	 * 퇴교 설문현황 다운로드
	 */
	public void listOutExcelDownload(OrgReshItemVO vo, String orgNm, ArrayList<String> titleList, OutputStream os) throws Exception;
	
	/**
	 * 개설 일반 설문 의견 응답 목록 조회
	 */
	public abstract	ProcessResultListVO<OrgReshItemVO> listOpinion(OrgReshItemVO vo) throws Exception;
	
}