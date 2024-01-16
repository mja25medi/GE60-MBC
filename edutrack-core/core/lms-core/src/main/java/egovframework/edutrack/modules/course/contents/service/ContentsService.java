package egovframework.edutrack.modules.course.contents.service;


import java.util.List;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;


public interface ContentsService {

	/**
	 * 교재 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	public ProcessResultListVO<ContentsVO> listContentsList(String sbjCd,
			String parUnitCd)  throws Exception ;

	public ProcessResultListVO<ContentsVO> listContents(ContentsVO vo)  throws Exception ;
	
	/**
	 * 교재 목록 조회 (트리형태의 VO 반환)
	 * @param sbjCda
	 * @param parUnitCd
	 * @return
	 */
	public ContentsVO listContentsTree(String sbjCd, String parUnitCd)  throws Exception ;

	
	public void listContentsTreeMake2(String sbjCd, String parUnitCd, ContentsVO iContentsVO, List<ContentsVO> subList)  throws Exception ; 
	/**
	 * 교재 목록 만들기 (트리형태의 목록 만들기)
	 * @param sbjCd
	 * @param parUnitCd
	 * @param iContentsVO
	 */
	public void listContentsTreeMake(String sbjCd, String parUnitCd,
			ContentsVO iContentsVO)  throws Exception ;

	/**
	 * 교재 정보 조회
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<ContentsVO> viewContents(String sbjCd,
			String unitCd)  throws Exception ;
	
	/**
	 * 교재 정보 조회 (회차)
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<ContentsVO> viewCreContents(String sbjCd,String crsCreCd,
			String unitCd)  throws Exception ;
	

	/**
	 * 교재 등록
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<ContentsVO> addContents(ContentsVO iContentsVO)  throws Exception ;

	/**
	 * 교재 수정
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<ContentsVO> editContents(ContentsVO iContentsVO)  throws Exception ;

	/**
	 * 교재 삭제
	 * @param
	 * @return
	 */
	public ProcessResultVO<ContentsVO> deleteContents(ContentsVO iContentsVO)  throws Exception ;

	/**
	 * 교제 순서 변경
	 * @param
	 * @return
	 */
	public ProcessResultVO<?> moveContents(ContentsVO iContentsVO)  throws Exception ;

	/**
	 * 교제 순서 변경
	 * @param
	 * @return
	 */
	public ProcessResultVO<?> sortContents(ContentsVO iContentsVO)  throws Exception ;

	/**
	 * 다른 과목 교제 카피
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<ContentsVO> copyOtherContents(ContentsVO sourceContentsVO, ContentsVO targetContentsVO)  throws Exception ;

	/**
	 * 트랜잭션 테스트용 매소드(테스트에서만 사용된다.)
	 */
	public ProcessResultVO<ContentsVO> transactionRollbackMethod(
			ContentsVO iContentsVO) throws Exception;

	/**
	 * 폴더 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	public ContentsFileVO listDir(String sbjCd, String orgCd) throws Exception;

	/**
	 * 입력된 폴더 하위의 폴더 및 파일 목록 조회
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public ProcessResultListVO<ContentsFileVO> listFile(String filePath, String parentId, String orgCd) throws Exception;

	/**
	 * 파일 및 폴더 삭제
	 */
	public ProcessResultVO<?> deleteFile(String sbjCd, String fileDir, String orgCd)
			throws Exception;

	/**
	 * 파일 압축 풀기
	 */
	public ProcessResultVO<?> unzipFile(String sbjCd, String fileDir,String fileName, String orgCd) throws Exception;

	/**
	 * 붙여넣기
	 */
	public ProcessResultVO<?> pasteFile(String sbjCd, String tarFileDir, String cutFileDir, String cutFileName, String cutType, String orgCd)
			throws Exception;

	/**
	 * 파일, 폴더명 변경
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<ContentsFileVO> renameFile(
			ContentsFileVO iContentsFileVO)  throws Exception ;

	/**
	 * 폴더 생성
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<ContentsFileVO> addDir(
			ContentsFileVO iContentsFileVO)  throws Exception ;
	
	/**
	 * 컨텐츠 목록 조회 (하위 컨텐츠가 있는 목록만)
	 * 
	 * @return ProcessResultListVO
	 */
	public ProcessResultListVO<ContentsVO> listContentsSort(ContentsVO vo) throws Exception;

	/**
	 * 하위 컨텐츠  목록 조회
	 * 
	 * @return ProcessResultListVO
	 */
	public ProcessResultListVO<ContentsVO> listContentsSub(ContentsVO vo) throws Exception;
	
	/**
	 * 컨텐츠 정렬 순서 변경
	 * @param iSubjectCategoryVO
	 * @return
	 */
	public ProcessResultVO<?> contentsSort(ContentsVO vo) throws Exception;
	
	public List<ContentsVO> listAttendContents(ContentsVO vo) throws Exception;

	/**
	 * 회차별 교재 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	public ProcessResultListVO<ContentsVO> listCreateContents(ContentsVO vo) throws Exception;
	
	/**
	 * 회차별 교재 정보 수정
	 *
	 * @return ProcessResultListVO
	 * @throws Exception 
	 */
	public ProcessResultVO<ContentsVO> editCreateContents(ContentsVO vo) throws Exception;

	/**
	 * 교재 정보 조회
	 *
	 */
	public ProcessResultVO<ContentsVO> viewCreateContents(String crsCreCd, String sbjCd, String unitCd) throws Exception;

	
	/**
	 * 회차 교재 일괄 삭제
	 * @param
	 * @return
	 * @throws Exception 
	 */
	public void deleteAllCreateContents(ContentsVO vo) throws Exception;

	/**
	 * 회차 교재 삭제
	 * @param
	 * @return
	 */
	public ProcessResultVO<ContentsVO> deleteCreateContents(ContentsVO iContentsVO)  throws Exception ;
	
	/**
	 * 회차 단일 목차 등록
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<ContentsVO> addCreateContents(ContentsVO iContentsVO)  throws Exception ;
	
	/**
	 * 회차 컨텐츠 목록 조회 (하위 컨텐츠가 있는 목록만)
	 * 
	 * @return ProcessResultListVO
	 */
	public ProcessResultListVO<ContentsVO> listCreateContentsSort(ContentsVO vo) throws Exception;

	
	/**
	 * 회차 컨텐츠  목록 조회
	 * 
	 * @return ProcessResultListVO
	 */
	public ProcessResultListVO<ContentsVO> listCreateContentsSub(ContentsVO vo) throws Exception;
	
	/**
	 * 회차 컨텐츠 정렬 순서 변경
	 * @return
	 */
	public ProcessResultVO<?> CreateContentsSort(ContentsVO vo) throws Exception;

	/**
	 * 교재 정보 조회 (회차)
	 *
	 * @return  ProcessResultVO
	 */
	ProcessResultVO<ContentsVO> viewCreContents(String sbjCd, String crsCreCd, String unitCd, String stdNo)
			throws Exception;
	
}