package egovframework.edutrack.modules.course.course.service.impl;


import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.annotation.HrdApiCrsCreCrs;
import egovframework.edutrack.comm.annotation.HrdApiCrsCreCrs.CreSyncType;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.FileUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.course.course.service.CourseService;
import egovframework.edutrack.modules.course.course.service.CourseVO;
import egovframework.edutrack.modules.course.courseplan.service.CrsPlanVO;
import egovframework.edutrack.modules.course.courseplan.service.impl.CrsPlanMapper;
import egovframework.edutrack.modules.course.coursesubject.service.CrsOflnSbjVO;
import egovframework.edutrack.modules.course.coursesubject.service.CrsOnlnSbjVO;
import egovframework.edutrack.modules.course.coursesubject.service.impl.CrsOflnSbjMapper;
import egovframework.edutrack.modules.course.coursesubject.service.impl.CrsOnlnSbjMapper;
import egovframework.edutrack.modules.course.crsbbs.info.service.impl.CrsBbsInfoMapper;
import egovframework.edutrack.modules.course.crstch.service.CrsTchVO;
import egovframework.edutrack.modules.course.crstch.service.impl.CrsTchMapper;
import egovframework.edutrack.modules.course.subject.service.OnlineSubjectVO;
import egovframework.edutrack.modules.org.fac.service.OrgFacInfoVO;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 과정 정보의 입.출력 처리를 하는 서비스 클래스
 *
 * <pre>
 * <b>업  무 :</b> 과정 - 과정 정보
 * </pre>
 *
 * @author MediopiaTech
 */
@Service("courseService")
public class CourseServiceImpl
extends EgovAbstractServiceImpl implements CourseService  {

	/** Mapper */
	@Resource(name="courseMapper")
	private CourseMapper 		courseMapper;

	@Resource(name="crsBbsInfoMapper")
	private CrsBbsInfoMapper 		crsBbsInfoMapper;

	@Resource(name="crsPlanMapper")
	private CrsPlanMapper 		crsPlanMapper;

	@Resource(name="crsOnlnSbjMapper")
	private CrsOnlnSbjMapper 		crsOnlnSbjMapper;

	@Resource(name="crsOflnSbjMapper")
	private CrsOflnSbjMapper 		crsOflnSbjMapper;

	@Resource(name="crsTchMapper")
	private CrsTchMapper 		crsTchMapper;
	
	@Resource(name="sysFileService")
	private SysFileService sysFileService;
	
	//파일 처리
	private final class NestedThumbFileHandler
	implements FileHandler<CourseVO> {

	@Override
	public String getPK(CourseVO vo) {
		return vo.getCrsCd().toString();
	}

	@Override
	public String getRepoCd() {
		return "CRS_THUMB";
	}

	@Override
	public List<SysFileVO> getFiles(CourseVO vo) {
		List<SysFileVO> fileList = new ArrayList<SysFileVO>();
		if(ValidationUtils.isNotZeroNull(vo.getThumbFileSn()))
			fileList.add(vo.getThumbFile());
		return fileList;
	}

	@Override
	public CourseVO setFiles(CourseVO vo, FileListVO fileListVO) {
		vo.setThumbFile(fileListVO.getFile("thumb"));
		return vo;
	}
}
	
	private final class NestedPlanFileHandler
	implements FileHandler<CourseVO> {
	
	@Override
	public String getPK(CourseVO vo) {
		return vo.getCrsCd().toString();
	}
	
	@Override
	public String getRepoCd() {
		return "CRS_PLAN";
	}
	
	@Override
	public List<SysFileVO> getFiles(CourseVO vo) {
		List<SysFileVO> fileList = new ArrayList<SysFileVO>();
		fileList.addAll(vo.getAttachFiles());
		return fileList;
	}
	
	@Override
	public CourseVO setFiles(CourseVO vo, FileListVO fileListVO) {
		vo.setAttachFiles(fileListVO.getFiles("file"));
		return vo;
	}
}
	
	private final class NestedImagesFileHandler
	implements FileHandler<CourseVO> {
		
		@Override
		public String getPK(CourseVO vo) {
			return vo.getCrsCd().toString();
		}
		
		@Override
		public String getRepoCd() {
			return "CRS_CRS";
		}
		
		@Override
		public List<SysFileVO> getFiles(CourseVO vo) {
			List<SysFileVO> fileList = new ArrayList<SysFileVO>();
			fileList.addAll(vo.getAttachImages());
			return fileList;
		}
		
		@Override
		public CourseVO setFiles(CourseVO vo, FileListVO fileListVO) {
			vo.setAttachImages(fileListVO.getFiles("image"));
			return vo;
		}
}
	
	
	/**
	 * 과정 정보의 목록을 조회하여 반환한다.
	 * @param CourseCretermVO
	 * @return ProcessResultListVO<CourseVO>
	 */
	@Override
	public ProcessResultListVO<CourseVO> list(CourseVO VO)  throws Exception {
		if(StringUtil.nvl(VO.getCrsCtgrCd()).equals("")==false) {
			VO.setCrsCtgrArr(StringUtil.split(VO.getCrsCtgrCd(), "/"));
		}
		ProcessResultListVO<CourseVO> resultList = new ProcessResultListVO<CourseVO>();
		try {
			List<CourseVO> returnList = courseMapper.list(VO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	/**
	 * 과정 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CourseCretermVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<CourseVO>
	 */
	@Override
	public ProcessResultListVO<CourseVO> listPageing(CourseVO VO, int pageIndex, int listScale, int pageScale)  throws Exception {

		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pageIndex);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		
		VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		VO.setLastIndex(paginationInfo.getLastRecordIndex());
		
		if( !"".equals(VO.getCrsCtgrCd()) && VO.getCrsCtgrCd() != null) {
			String [] crsCtgrArr = VO.getCrsCtgrCd().split("/");
			List<String> list = new ArrayList<String>();
			Collections.addAll(list, crsCtgrArr);
			list.remove("");
			crsCtgrArr = list.toArray(new String[0]);
			VO.setCrsCtgrArr(crsCtgrArr);
		}
		
		ProcessResultListVO<CourseVO> resultList = new ProcessResultListVO<CourseVO>();
		try {
			// 전체 목록 수
			int totalCount = courseMapper.count(VO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<CourseVO> returnList = courseMapper.listPageing(VO);
			resultList.setReturnList(returnList);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

	
	/**
	 * 수강신청 목록을 조회하여 반환한다.
	 * @param CourseCretermVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<CourseVO>
	 */
	@Override
	public ProcessResultListVO<CourseVO> listStdCoursePaging(CourseVO VO)  throws Exception {

		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(VO.getCurPage());
		paginationInfo.setRecordCountPerPage(VO.getListScale());
		paginationInfo.setPageSize(VO.getPageScale());
		
		VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		VO.setLastIndex(paginationInfo.getLastRecordIndex());
		
		ProcessResultListVO<CourseVO> resultList = new ProcessResultListVO<CourseVO>();
		try {
			// 전체 목록 수
			int totalCount = courseMapper.countCourse(VO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<CourseVO> returnList = courseMapper.listUserStdCourse(VO);
			resultList.setReturnList(returnList);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

	
	/**
	 * 과정 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CourseCretermVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	@Override
	public ProcessResultListVO<CourseVO> listPageing(CourseVO VO, int curPage, int listScale) throws Exception {
		return this.listPageing(VO, curPage, listScale, Constants.LIST_PAGE_SCALE);
	}

	/**
	 * 과정 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CourseCretermVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	@Override
	public ProcessResultListVO<CourseVO> listPageing(CourseVO VO, int curPage) throws Exception {
		return this.listPageing(VO, curPage, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}

	/**
	 * 과정 정보의 단일 레코드를 조회하여 반환한다.
	 * @param CourseVO.atclSn
	 * @return ProcessResultVO<CourseVO>
	 */
	@Override
	public ProcessResultVO<CourseVO> view(CourseVO VO) throws Exception {
		ProcessResultVO<CourseVO> resultVO = new ProcessResultVO<CourseVO>();
		try {
			CourseVO returnVO = courseMapper.select(VO);
			returnVO = sysFileService.getFile(returnVO, new NestedThumbFileHandler());
			returnVO = sysFileService.getFile(returnVO, new NestedPlanFileHandler());
			returnVO = sysFileService.getFile(returnVO, new NestedImagesFileHandler());
			resultVO.setReturnVO(returnVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		return resultVO;
	}

	/**
	 * 과정 정보의 목록을 조회하여 반환한다.
	 * @param CourseCretermVO
	 * @return ProcessResultListVO<CourseVO>
	 */
	@Override
	public ProcessResultListVO<CourseVO> listForEnroll(CourseVO VO) throws Exception {
		ProcessResultListVO<CourseVO> resultList = new ProcessResultListVO<CourseVO>();
		try {
			List<CourseVO> returnList = courseMapper.listForEnroll(VO);
			resultList.setReturnList(returnList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

	/**
	 * 과정 정보를 등록하고 결과를 반환한다.
	 * @param CourseCretermVO
	 * @return ProcessResultVO<CourseVO>
	 */
	@Override
	public ProcessResultVO<CourseVO> add(CourseVO vo) throws Exception {
		//-- 자동생성인 경우 과정 코드 받아오기
		if("Y".equals(vo.getAutoMakeCd())) {
			vo.setCrsCd(courseMapper.selectCrsCd());
		}
		
		ProcessResultVO<CourseVO> resultVO = new ProcessResultVO<CourseVO>();
		
		
		try {
			setDateConvert(vo);
			courseMapper.insert(vo);
			//---- 과정 폴더 생성
			String contentsDir =  Constants.CONTENTS_STORAGE_PATH + File.separator  + vo.getOrgCd() + File.separator + vo.getCrsCd();
			FileUtil.createDirectory(contentsDir);
			sysFileService.bindFile(vo, new NestedThumbFileHandler());
			sysFileService.bindFile(vo, new NestedPlanFileHandler());
			sysFileService.bindFile(vo, new NestedImagesFileHandler());
			resultVO.setReturnVO(vo);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}

	/**
	 * 과정 정보를 수정하고 결과를 반환한다.
	 * @param CourseCretermVO
	 * @return ProcessResultVO<CourseVO>
	 */
	@Override
	@HrdApiCrsCreCrs(CreSyncType.CRSUPDATE)
	public ProcessResultVO<CourseVO> edit(CourseVO vo) throws Exception {
		ProcessResultVO<CourseVO> resultVO = new ProcessResultVO<CourseVO>();
		setDateConvert(vo);
		courseMapper.update(vo);
		resultVO.setReturnVO(vo);
		resultVO.setResultSuccess();
		return resultVO;
	}

	/**
	 * 과정 정보 삭제하고 결과를 반환한다.
	 * 과정 정보 삭제시 년간 과정 계획,  과정 강사/튜터,
	 * 과정 온라인/오프라인 과목, 과정 게시판을 같이 삭제 한다.
	 * @param articleVO 삭제 대상 고유 번호
	 * @return 삭제 처리 결과 VO
	 */
	@Override
	public ProcessResultVO<?> remove(CourseVO VO) throws Exception {
		ProcessResultVO<CourseVO> resultVO = new ProcessResultVO<CourseVO>();
		try {
			//-- 년간 과정 계획을 삭제한다.
			CrsPlanVO cpVO = new CrsPlanVO();
			cpVO.setCrsCd(VO.getCrsCd());
			crsPlanMapper.deleteAll(cpVO);

			//-- 과정강사 삭제
			CrsTchVO ctVO = new CrsTchVO();
			ctVO.setCrsCd(VO.getCrsCd());
			crsTchMapper.deleteAll(ctVO);

			//-- 과정 과목 삭제
			CrsOnlnSbjVO cosVO = new CrsOnlnSbjVO();
			cosVO.setCrsCd(VO.getCrsCd());
			crsOnlnSbjMapper.deleteAll(cosVO);

			CrsOflnSbjVO cfsVO = new CrsOflnSbjVO();
			cfsVO.setCrsCd(VO.getCrsCd());
			crsOflnSbjMapper.deleteAll(cfsVO);
			
			courseMapper.delete(VO); // 분류 삭제
			resultVO.setResultSuccess();
		} catch (Exception e) {
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		return resultVO;
	}

	/**
	 * 과정명으로 중복이 있는지 확인한다.
	 * @param CourseCretermVO
	 * @return ProcessResultVO<CourseVO>
	 */
	@Override
	public ProcessResultVO<CourseVO> isDupulicationByCrsNm(String orgCd, String crsNm) throws Exception {
		CourseVO VO = new CourseVO();
		VO.setOrgCd(orgCd);
		VO.setCrsNm(crsNm);
		
		ProcessResultVO<CourseVO> resultVO = new ProcessResultVO<CourseVO>();
		try {
			CourseVO returnVO = courseMapper.isDupulicateByCrsNm(VO);
			resultVO.setReturnVO(returnVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}	
		
		
		return resultVO;
	}

	/**
	 * 트랜잭션 테스트용 매소드(테스트에서만 사용된다.)
	 */
	@Override
	@Deprecated
	public ProcessResultVO<CourseVO> transactionRollbackMethod(CourseVO VO) throws Exception {
		this.add(VO);
		throw new Exception("트랜잭션 테스트를 위한 강제 Exception");
	}

	/**
	 * 과정 정보의 목록을 paging조회하여 반환한다.
	 * @param CourseCretermVO
	 * @return ProcessResultListVO<CourseVO>
	 */
	@Override
	public ProcessResultListVO<CourseVO> listForEnrollPaging(CourseVO VO) throws Exception {

		ProcessResultListVO<CourseVO> resultList = new ProcessResultListVO<CourseVO>();
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(VO.getCurPage());
		paginationInfo.setRecordCountPerPage(VO.getListScale());
		paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
		
		VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		VO.setLastIndex(paginationInfo.getLastRecordIndex());		
		try {
			// 전체 목록 수
			int totalCount = courseMapper.listForEnrollPagingCount(VO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<CourseVO> returnList = courseMapper.listForEnrollPaging(VO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	/**
	 * 과목 사용중인 과정 정보의 목록을 조회하여 반환한다. - 온라인
	 * (페이징 정보 포함)
	 * @param CourseCretermVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	@Override
	public ProcessResultListVO<CourseVO> listSubInfo(CourseVO VO) throws Exception {
		ProcessResultListVO<CourseVO> resultList = new ProcessResultListVO<CourseVO>();
		try {
			List<CourseVO> returnList = courseMapper.listSubInfo(VO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	/**
	 * 과목 사용중인 과정 정보의 목록을 조회하여 반환한다. - 오프라인
	 * (페이징 정보 포함)
	 * @param CourseCretermVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	@Override
	public ProcessResultListVO<CourseVO> listSubInfoOffline(CourseVO VO) throws Exception {
		ProcessResultListVO<CourseVO> resultList = new ProcessResultListVO<CourseVO>();
		try {
			List<CourseVO> returnList = courseMapper.listSubInfoOffline(VO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
	@Override
	public ProcessResultListVO<CourseVO> listUserCourse(String userNo) throws Exception {
		ProcessResultListVO<CourseVO> resultList = new ProcessResultListVO<CourseVO>();
		try {
			List<CourseVO> returnList = courseMapper.listUserCourse(userNo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
	private void setDateConvert(CourseVO iCourseVO) throws Exception {

		//-- 시간값 변경
		String enrlAplcStartDttm = "";
		String enrlAplcEndDttm = "";
		String enrlStartDttm = "";
		String enrlEndDttm = "";
		String termEndDttm = "";
		String scoreOpenStartDttm = "";
		String scoreOpenEndDttm = "";
		if(!"".equals(StringUtil.nvl(iCourseVO.getEnrlAplcStartDttm())))
			enrlAplcStartDttm = StringUtil.ReplaceAll(StringUtil.ReplaceAll(StringUtil.ReplaceAll(iCourseVO.getEnrlAplcStartDttm(), ".", ""),"-",""),"/","")+"000001";
		if(!"".equals(StringUtil.nvl(iCourseVO.getEnrlAplcEndDttm())))
			enrlAplcEndDttm = StringUtil.ReplaceAll(StringUtil.ReplaceAll(StringUtil.ReplaceAll(iCourseVO.getEnrlAplcEndDttm(), ".", ""),"-",""),"/","")+"235959";
		if(!"".equals(StringUtil.nvl(iCourseVO.getEnrlStartDttm())))
			enrlStartDttm = StringUtil.ReplaceAll(StringUtil.ReplaceAll(StringUtil.ReplaceAll(iCourseVO.getEnrlStartDttm(), ".", ""),"-",""),"/","")+"000001";
		if(!"".equals(StringUtil.nvl(iCourseVO.getEnrlEndDttm())))
			enrlEndDttm = StringUtil.ReplaceAll(StringUtil.ReplaceAll(StringUtil.ReplaceAll(iCourseVO.getEnrlEndDttm(), ".", ""),"-",""),"/","")+"235959";
		if(!"".equals(StringUtil.nvl(iCourseVO.getTermEndDttm())))
			termEndDttm = StringUtil.ReplaceAll(StringUtil.ReplaceAll(StringUtil.ReplaceAll(iCourseVO.getTermEndDttm(), ".", ""),"-",""),"/","")+"235959";
		if(!"".equals(StringUtil.nvl(iCourseVO.getScoreOpenStartDttm())))
			scoreOpenStartDttm = StringUtil.ReplaceAll(StringUtil.ReplaceAll(StringUtil.ReplaceAll(iCourseVO.getScoreOpenStartDttm(), ".", ""),"-",""),"/","")+"000001";
		if(!"".equals(StringUtil.nvl(iCourseVO.getScoreOpenEndDttm())))
			scoreOpenEndDttm = StringUtil.ReplaceAll(StringUtil.ReplaceAll(StringUtil.ReplaceAll(iCourseVO.getScoreOpenEndDttm(), ".", ""),"-",""),"/","")+"235959";

		iCourseVO.setEnrlAplcStartDttm(enrlAplcStartDttm);
		iCourseVO.setEnrlAplcEndDttm(enrlAplcEndDttm);
		iCourseVO.setEnrlStartDttm(enrlStartDttm);
		iCourseVO.setEnrlEndDttm(enrlEndDttm);
		iCourseVO.setTermEndDttm(termEndDttm);
		iCourseVO.setScoreOpenStartDttm(scoreOpenStartDttm);
		iCourseVO.setScoreOpenEndDttm(scoreOpenEndDttm);
	}

	/**
	 *  [HRD] 수강생이 신청 및 결제한 기수 리스트 조회
	 */
	@Override
	public List<CourseVO> listStudentPaymentCourseByUserNo(CourseVO iCourseVO) {
		return courseMapper.listStudentPaymentCourseByUserNo(iCourseVO);
	}
	
	@Override
	public ProcessResultListVO<CourseVO> listCourse(CourseVO VO) throws Exception {
		ProcessResultListVO<CourseVO> resultList = new ProcessResultListVO<CourseVO>();
		try {
			
			List<CourseVO> returnList = courseMapper.listCourse(VO);
			for(CourseVO vo : returnList) {
				//vo.setAttachFiles(courseMapper.fileListCourse(vo));
				if (vo.getMetaTag() != null && !vo.getMetaTag().equals("")){
				vo.setMetaTagArr((vo.getMetaTag().split("\\|")));
				}
			}
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
}
