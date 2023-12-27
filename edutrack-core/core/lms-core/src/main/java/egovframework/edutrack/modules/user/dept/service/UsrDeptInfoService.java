package egovframework.edutrack.modules.user.dept.service;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.course.createcourseteacher.service.TeacherVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;


public interface UsrDeptInfoService {

	/**
	 *  기관 정보 전체 목록을 조회한다.
	 * @param UsrDeptInfoVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<UsrDeptInfoVO> list(UsrDeptInfoVO vo)
			throws Exception;

	/**
	 * 기관 정보 페이징 목록을 조회한다.
	 * @param UsrDeptInfoVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<UsrDeptInfoVO> listPageing(UsrDeptInfoVO vo)
			throws Exception;


	/**
	 * 기관 정보 상세 정보를 조회한다.
	 * @param UsrDeptInfoVO
	 * @return UsrDeptInfoVO
	 * @throws Exception
	 */
	public abstract UsrDeptInfoVO view(UsrDeptInfoVO vo) throws Exception;
	
	public UsrDeptInfoVO search(UsrDeptInfoVO vo) throws Exception;

	/**
	 * 기관 정보 정보를 등록한다.
	 * @param UsrDeptInfoVO
	 * @return String
	 * @throws Exception
	 */
	public abstract int add(UsrDeptInfoVO vo) throws Exception;

	/**
	 * 기관 정보 정보를 수정한다.
	 * @param UsrDeptInfoVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int edit(UsrDeptInfoVO vo) throws Exception;

	/**
	 * 기관 정보 정보를 삭제 한다.
	 * @param UsrDeptInfoVO
	 * @return int
	 * @throws Exception
	 */
	public abstract int remove(UsrDeptInfoVO vo) throws Exception;

	/**
	 * 기업코드 중복 체크
	 * @param UsrDeptInfoVO
	 * @return  String
	 */
	public abstract String deptCdCheck(UsrDeptInfoVO vo) throws Exception;
	
	/**
	 * 샘플 엑셀파일 다운로드
	 * @param (HashMap<String, String> titles
	 * @param OutputStream os
	 * @throws ServiceProcessException
	 */
	public abstract void sampleExcelDownload(OutputStream os) throws Exception;
	
	/**
	 * Upload 사용자 정보 체크 하여 돌려 준다.
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public ProcessResultListVO<UsrDeptInfoVO> excelUploadValidationCheck(
			String fileName, String filePath, String orgCd) throws Exception;
	
	/**
	 * 일반 사용자 일괄 등록
	 * @param List<OrgCodeVO> codeList
	 * @return
	 */
	public ProcessResultVO<UsrDeptInfoVO> addDeptBatch(	List<UsrDeptInfoVO> deptList) throws Exception ;

	/**
	 * 기업관리자 권한 리스트 
	 * 사용자의 목록을 반환한다.
	 * @param CrsTchVO
	 * @return ProcessResultListVO<CrsTchVO>
	 */
	public ProcessResultListVO<UsrUserInfoVO> listSearch(UsrDeptInfoVO vo) throws Exception;
	
	/**
	 * 엑셀 파일을 통한 기업 등록
	 * @param UsrDeptInfoVO
	 * @param String
	 * @param String
	 * @param String
	 * @return ProcessResultListVO<UsrDeptInfoVO>
	 */
	public ProcessResultListVO<UsrDeptInfoVO> excelUploadDeptAdd(UsrDeptInfoVO vo, String fileName, String filePath, String orgCd) throws Exception;
	
	/**
	 * 기업 목록을 반환한다.
	 * @param UsrDeptInfoVO
	 * @return ProcessResultListVO<UsrDeptInfoVO>
	 */
	public ProcessResultListVO<UsrDeptInfoVO> listForSearch(UsrDeptInfoVO vo) throws Exception;
}