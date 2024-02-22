package egovframework.edutrack.modules.course.createcourse.service.impl;

import java.io.File;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.annotation.HrdApiCrsCreCrs;
import egovframework.edutrack.comm.annotation.HrdApiCrsCreCrs.CreSyncType;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.FileUtil;
import egovframework.edutrack.comm.util.web.QrUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.course.attendance.service.AttendanceVO;
import egovframework.edutrack.modules.course.attendance.service.impl.AttendanceMapper;
import egovframework.edutrack.modules.course.contents.service.ContentsVO;
import egovframework.edutrack.modules.course.contents.service.impl.ContentsMapper;
import egovframework.edutrack.modules.course.course.service.CourseVO;
import egovframework.edutrack.modules.course.course.service.impl.CourseMapper;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseService;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;
import egovframework.edutrack.modules.course.createcourse.service.ResearchCourseVO;
import egovframework.edutrack.modules.course.createcourse.service.UserCourseVO;
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateOnlineSubjectVO;
import egovframework.edutrack.modules.course.createcoursesubject.service.impl.CreateCourseOfflineSubjectMapper;
import egovframework.edutrack.modules.course.createcoursesubject.service.impl.CreateCourseOnlineSubjectMapper;
import egovframework.edutrack.modules.course.createcourseteacher.service.TeacherVO;
import egovframework.edutrack.modules.course.createcourseteacher.service.impl.CreateCourseTeacherMapper;
import egovframework.edutrack.modules.course.decls.service.CreCrsDeclsVO;
import egovframework.edutrack.modules.course.decls.service.impl.CreCrsDeclsMapper;
import egovframework.edutrack.modules.course.subject.service.impl.SubjectOnlineMapper;
import egovframework.edutrack.modules.lecture.bbs.service.LecBbsVO;
import egovframework.edutrack.modules.lecture.bbs.service.impl.LecBbsMapper;
import egovframework.edutrack.modules.log.classconn.service.LogClassConnVO;
import egovframework.edutrack.modules.log.classconn.service.impl.LogClassConnMapper;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoVO;
import egovframework.edutrack.modules.org.info.service.impl.OrgOrgInfoMapper;
import egovframework.edutrack.modules.student.student.service.StudentVO;
import egovframework.edutrack.modules.student.student.service.impl.StudentMapper;
import egovframework.edutrack.modules.system.config.service.SysCfgVO;
import egovframework.edutrack.modules.system.config.service.impl.SysCfgMapper;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Service("createCourseService")
public class CreateCourseServiceImpl extends EgovAbstractServiceImpl implements  CreateCourseService {

	private final class NestedThumbFileHandler
		implements FileHandler<CreateCourseVO> {
	
		@Override
		public String getPK(CreateCourseVO vo) {
			return vo.getCrsCd().toString();
		}
	
		@Override
		public String getRepoCd() {
			return "CRS_THUMB";
		}
	
		@Override
		public List<SysFileVO> getFiles(CreateCourseVO vo) {
			List<SysFileVO> fileList = new ArrayList<SysFileVO>();
			if(ValidationUtils.isNotZeroNull(vo.getThumbFileSn()))
				fileList.add(vo.getThumbFile());
			return fileList;
		}
	
		@Override
		public CreateCourseVO setFiles(CreateCourseVO vo, FileListVO fileListVO) {
			vo.setThumbFile(fileListVO.getFile("thumb"));
			return vo;
		}
	}
	
	private final class NestedPlanFileHandler
	implements FileHandler<CreateCourseVO> {
		
		@Override
		public String getPK(CreateCourseVO vo) {
			return vo.getCrsCd().toString();
		}
		
		@Override
		public String getRepoCd() {
			return "CRS_PLAN";
		}
		
		@Override
		public List<SysFileVO> getFiles(CreateCourseVO vo) {
			List<SysFileVO> fileList = new ArrayList<SysFileVO>();
			fileList.addAll(vo.getAttachFiles());
			return fileList;
		}
		
		@Override
		public CreateCourseVO setFiles(CreateCourseVO vo, FileListVO fileListVO) {
			vo.setAttachFiles(fileListVO.getFiles("file"));
			return vo;
		}
	}
	
	private final class NestedQrFileHandler
	implements FileHandler<CreateCourseVO> {

	@Override
	public String getPK(CreateCourseVO vo) {
		return vo.getCrsCreCd().toString();
	}

	@Override
	public String getRepoCd() {
		return "CRS_CRE_CRS_QR";
	}

	@Override
	public List<SysFileVO> getFiles(CreateCourseVO vo) {
		List<SysFileVO> fileList = new ArrayList<SysFileVO>();
		if(ValidationUtils.isNotZeroNull(vo.getQrFileSn()))
			fileList.add(vo.getQrFile());
		return fileList;
	}

	@Override
	public CreateCourseVO setFiles(CreateCourseVO vo, FileListVO fileListVO) {
		vo.setQrFile(fileListVO.getFile("QR"));
		return vo;
	}
}

	/** Mapper */
	@Resource(name="createCourseMapper")
	private CreateCourseMapper 		createCourseMapper;

	@Resource(name="courseMapper")
	private CourseMapper 		courseMapper;

	@Resource(name="lecBbsMapper")
	private LecBbsMapper 		lecBbsMapper;

	@Resource(name="studentMapper")
	private StudentMapper 		studentMapper;

	@Resource(name="createCourseTeacherMapper")
	private CreateCourseTeacherMapper 		createCourseTeacherMapper;

	@Resource(name="creCrsDeclsMapper")
	private CreCrsDeclsMapper 		creCrsDeclsMapper;

	@Resource(name="createCourseOfflineSubjectMapper")
	private CreateCourseOfflineSubjectMapper 		createCourseOfflineSubjectMapper;

	@Resource(name="createCourseOnlineSubjectMapper")
	private CreateCourseOnlineSubjectMapper 		createCourseOnlineSubjectMapper;

	@Resource(name="orgOrgInfoMapper")
	private OrgOrgInfoMapper 		orgOrgInfoMapper;

	@Resource(name="sysCfgMapper")
	private SysCfgMapper 		sysCfgMapper;
	
	@Resource(name="subjectOnlineMapper")
	private SubjectOnlineMapper 		subjectOnlineMapper;

	@Resource(name="sysFileService")
	private SysFileService sysFileService;
	
	@Resource(name="contentsMapper")
	private ContentsMapper 		contentsMapper;
	
	@Resource(name="attendanceMapper")
	private AttendanceMapper 		attendanceMapper;
	
	@Resource(name="logClassConnMapper")
	private LogClassConnMapper 		logClassConnMapper;
	

	/**
	 * 개설 과정 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<CreateCourseVO> listCreateCourse(CreateCourseVO iCreateCourseVO)  throws Exception {
		iCreateCourseVO.setCrsCtgrArr(StringUtil.split(StringUtil.nvl(iCreateCourseVO.getCrsCtgrCd()), "/"));
		ProcessResultListVO<CreateCourseVO> resultList = new ProcessResultListVO<CreateCourseVO>();
		try {
			List<CreateCourseVO> returnList = createCourseMapper.listCreateCourse(iCreateCourseVO);
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
	 * 개설 과정 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CourseCretermVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<CourseVO>
	 */
	@Override
	public ProcessResultListVO<CreateCourseVO> listCreateCoursePageing(CreateCourseVO VO, int pageIndex, int listScale, int pageScale, boolean filein)  throws Exception {

		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pageIndex);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		
		VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		VO.setLastIndex(paginationInfo.getLastRecordIndex());
		
		//VO.setCrsCtgrArr(StringUtil.split(VO.getCrsCtgrCd(), "/"));
		ProcessResultListVO<CreateCourseVO> resultList = new ProcessResultListVO<CreateCourseVO>();
		try {
			// 전체 목록 수
			String mngType = VO.getMngType();
			List<CreateCourseVO> returnList;
			
			if (mngType.contains("TCHMGR")) {
				int totalCount = createCourseMapper.listCreateCourseForTchMgrPageingCount(VO);
				paginationInfo.setTotalRecordCount(totalCount);
				returnList = createCourseMapper.listCreateCourseForTchMgrPageing(VO);
			}else {
				int totalCount = createCourseMapper.listCreateCoursePageingCount(VO);
				paginationInfo.setTotalRecordCount(totalCount);
				returnList = createCourseMapper.listCreateCoursePageing(VO);
			}
			
			if (filein) {
				List<CreateCourseVO> courseCreateList = new ArrayList<CreateCourseVO>();
				for(CreateCourseVO ccvo : returnList) {
					ccvo = sysFileService.getFile(ccvo, new NestedThumbFileHandler());
					ccvo = sysFileService.getFile(ccvo, new NestedPlanFileHandler());
					courseCreateList.add(ccvo);
				}
				resultList.setReturnList(courseCreateList);
			} else {
				resultList.setReturnList(returnList);
			}
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
	 * 과정 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CourseCretermVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	@Override
	public ProcessResultListVO<CreateCourseVO> listCreateCoursePageing(CreateCourseVO VO, int curPage, int listScale, boolean filein) throws Exception {
		return this.listCreateCoursePageing(VO, curPage, listScale, Constants.LIST_PAGE_SCALE, filein);
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
	public ProcessResultListVO<CreateCourseVO> listCreateCoursePageing(CreateCourseVO VO, int curPage, boolean filein) throws Exception {
		return this.listCreateCoursePageing(VO, curPage, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE, filein);
	}

	/**
	 * 수강신청용 회차 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<CreateCourseVO> listCreateCourseForEnroll(CreateCourseVO VO) throws Exception {
//		List<CourseVO> courseList = courseMapper.listForEnroll(iCourseVO).getReturnList();
//		List<CourseVO> returnList = new ArrayList<CourseVO>();
//		for(int i=0; i < courseList.size();i++) {
//			CourseVO courseVO = courseList.get(i);
//			//-- 하위 개설 과정 검색
//			courseVO.setCreYear(iCourseVO.getCreYear());
//			courseVO.setSearchMode(iCourseVO.getSearchMode());
//			List<CreateCourseVO> createCourseList = createCourseMapper.listCreateCourseForEnroll(courseVO).getReturnList();
//			if(createCourseList.size() > 0) {
//				courseVO.setCreateCourseList(createCourseList);
//				returnList.add(courseVO);
//			}
//		}
//		ProcessResultListVO<CourseVO> resultListVO = new ProcessResultListVO<CourseVO>(returnList);
		
		ProcessResultListVO<CreateCourseVO> resultList = new ProcessResultListVO<CreateCourseVO>();
		try {
			List<CreateCourseVO> returnList = createCourseMapper.listCreateCourseForEnroll(VO);
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
	 * 수강신청용 회차 조회
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<CreateCourseVO> createCourseForEnroll(CreateCourseVO VO) throws Exception {
//		List<CourseVO> courseList = courseMapper.listForEnroll(iCourseVO).getReturnList();
//		List<CourseVO> returnList = new ArrayList<CourseVO>();
//		for(int i=0; i < courseList.size();i++) {
//			CourseVO courseVO = courseList.get(i);
//			//-- 하위 개설 과정 검색
//			courseVO.setCreYear(iCourseVO.getCreYear());
//			courseVO.setSearchMode(iCourseVO.getSearchMode());
//			List<CreateCourseVO> createCourseList = createCourseMapper.listCreateCourseForEnroll(courseVO).getReturnList();
//			if(createCourseList.size() > 0) {
//				courseVO.setCreateCourseList(createCourseList);
//				returnList.add(courseVO);
//			}
//		}
//		ProcessResultListVO<CourseVO> resultListVO = new ProcessResultListVO<CourseVO>(returnList);
		
		ProcessResultListVO<CreateCourseVO> resultList = new ProcessResultListVO<CreateCourseVO>();
		try {
			List<CreateCourseVO> returnList = createCourseMapper.createCourseForEnroll(VO);
			List<CreateCourseVO> courseCreateList = new ArrayList<CreateCourseVO>();
			for(CreateCourseVO ccvo : returnList) {
				ccvo = sysFileService.getFile(ccvo, new NestedThumbFileHandler());
				ccvo = sysFileService.getFile(ccvo, new NestedPlanFileHandler());
				courseCreateList.add(ccvo);
			}
			resultList.setReturnList(courseCreateList);
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
	 * 날짜별 개설 과정 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<CreateCourseVO> listCreateCourseForEnrollDate(CreateCourseVO iCreateCourseVO) throws Exception {
		ProcessResultListVO<CreateCourseVO> resultList = new ProcessResultListVO<CreateCourseVO>();
		try {
			List<CreateCourseVO> returnList = createCourseMapper.listCreateCourseForEnrollDate(iCreateCourseVO);
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
	 * 날짜별 개설 과정 목록 조회 - 기숙사 배정용 과정 목록
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<CreateCourseVO> listCreateCourseForEnrollDateOffline(CreateCourseVO iCreateCourseVO) throws Exception {
		ProcessResultListVO<CreateCourseVO> resultList = new ProcessResultListVO<CreateCourseVO>();
		try {
			List<CreateCourseVO> returnList = createCourseMapper.listCreateCourseForEnrollDateOffline(iCreateCourseVO);
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
	 * 과정명 검색 개설 과정 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<CreateCourseVO> listCreateCourseForEnrollSearch(CreateCourseVO iCreateCourseVO) throws Exception {
		ProcessResultListVO<CreateCourseVO> resultList = new ProcessResultListVO<CreateCourseVO>();
		try {
			List<CreateCourseVO> returnList = createCourseMapper.listCreateCourseForEnrollSearch(iCreateCourseVO);
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
	 * 개설 과정 정보 조회
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<CreateCourseVO> viewCreateCourse(CreateCourseVO iCreateCourseVO) throws Exception {
		ProcessResultVO<CreateCourseVO> resultVO = new ProcessResultVO<CreateCourseVO>();
		try {
			CreateCourseVO returnVO = createCourseMapper.selectCreateCourse(iCreateCourseVO);
			returnVO = sysFileService.getFile(returnVO, new NestedQrFileHandler());
			//returnVO = sysFileService.getFile(returnVO, new NestedPlanFileHandler());
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
	 * 회차 등록
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	@HrdApiCrsCreCrs(CreSyncType.CREATE)
	public ProcessResultVO<CreateCourseVO> addCreateCourse(CreateCourseVO iCreateCourseVO) throws Exception {

		ProcessResultVO<CreateCourseVO> resultVO = new ProcessResultVO<CreateCourseVO>();
		try {
			//-- 개설 과정 코드 생성
			String createCourseCd = createCourseMapper.selectCreateCourseCd();
			iCreateCourseVO.setCrsCreCd(createCourseCd) ;

			int declsCnt = StringUtil.nvl(iCreateCourseVO.getDeclsCnt(),0);
			//-- 시간값 변경
			setDateConvert(iCreateCourseVO);
			//-- 개설 과정 생성
			createCourseMapper.insertCreateCourse(iCreateCourseVO);
			
			//---- 회차 폴더 생성
			String contentsDir =  Constants.CONTENTS_STORAGE_PATH + File.separator  + iCreateCourseVO.getOrgCd() + File.separator + iCreateCourseVO.getCrsCreCd();
			FileUtil.createDirectory(contentsDir);
			sysFileService.bindFile(iCreateCourseVO, new NestedQrFileHandler());

			//-- 사이트 정보 검색
			OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
			orgInfoVO.setOrgCd(iCreateCourseVO.getOrgCd());
			orgInfoVO = orgOrgInfoMapper.select(orgInfoVO);

			String langCd = orgInfoVO.getDfltLangCd();
			if(ValidationUtils.isEmpty(langCd)) {
				langCd = Constants.LANG_DEFAULT;
			}

			LecBbsVO bbsVO = new LecBbsVO();
			bbsVO.setRegNo(iCreateCourseVO.getRegNo());
			bbsVO.setModNo(iCreateCourseVO.getModNo());
			bbsVO.setCrsCreCd(createCourseCd);

			SysCfgVO configVO = new SysCfgVO();
			configVO.setCfgCtgrCd("LEC_BOARD");
			configVO.setCfgCd(langCd+"_NOTICE");
			SysCfgVO scvo = sysCfgMapper.select(configVO);
	 		if(ValidationUtils.isNotEmpty(scvo)) {
	 			configVO = scvo;
			}

			bbsVO.setBbsCd("NOTICE");
			bbsVO.setBbsNm(configVO.getCfgVal());
			bbsVO.setBbsDesc(configVO.getCfgVal());
			bbsVO.setAnsrUseYn("N");
			bbsVO.setCmntUseYn("N");

			configVO.setCfgCd(langCd+"_PDS");
			scvo = sysCfgMapper.select(configVO);
	 		if(ValidationUtils.isNotEmpty(scvo)) {
	 			configVO = scvo;
			}

			lecBbsMapper.insert(bbsVO);
			bbsVO.setBbsCd("PDS");
			bbsVO.setBbsNm(configVO.getCfgVal());
			bbsVO.setBbsDesc(configVO.getCfgVal());
			bbsVO.setAnsrUseYn("N");
			bbsVO.setCmntUseYn("N");

			lecBbsMapper.insert(bbsVO);

			configVO.setCfgCd(langCd+"_QNA");
			scvo = sysCfgMapper.select(configVO);
	 		if(ValidationUtils.isNotEmpty(scvo)) {
	 			configVO = scvo;
			}

			bbsVO.setBbsCd("QNA");
			bbsVO.setBbsNm(configVO.getCfgVal());
			bbsVO.setBbsDesc(configVO.getCfgVal());
			bbsVO.setAnsrUseYn("Y");
			bbsVO.setCmntUseYn("Y");

			lecBbsMapper.insert(bbsVO);

			//-- 분반 생성
			for(int i = 0; i < declsCnt; i++) {
				CreCrsDeclsVO ccdVO = new CreCrsDeclsVO();
				ccdVO.setCrsCreCd(createCourseCd);
				ccdVO.setRegNo(iCreateCourseVO.getRegNo());
				ccdVO.setModNo(iCreateCourseVO.getModNo());
				creCrsDeclsMapper.insert(ccdVO);
			}

			
			/*
			 * //-- 오프라인 과목 가져오기 CreateOfflineSubjectVO ofsVO = new
			 * CreateOfflineSubjectVO(); ofsVO.setCrsCd(iCreateCourseVO.getCrsCd());
			 * ofsVO.setCrsCreCd(createCourseCd);
			 * ofsVO.setRegNo(iCreateCourseVO.getRegNo());
			 * ofsVO.setModNo(iCreateCourseVO.getModNo());
			 * 
			 * createCourseOfflineSubjectMapper.insertCopy(ofsVO);
			 */
			  
			  //-- 온라인 과목 가져오기 
			  CreateOnlineSubjectVO onsVO = new CreateOnlineSubjectVO();
			  onsVO.setCrsCd(iCreateCourseVO.getCrsCd());
			  onsVO.setCrsCreCd(createCourseCd);
			  onsVO.setRegNo(iCreateCourseVO.getRegNo());
			  onsVO.setModNo(iCreateCourseVO.getModNo());
			  
			  createCourseOnlineSubjectMapper.insertCopy(onsVO);
			  
			  //-- 과정 강사 가져오기 
			  TeacherVO tchVO = new TeacherVO();
			  tchVO.setCrsCd(iCreateCourseVO.getCrsCd());
			  tchVO.setCrsCreCd(createCourseCd);
			  tchVO.setRegNo(iCreateCourseVO.getRegNo());
			  tchVO.setModNo(iCreateCourseVO.getModNo());
			  
			  createCourseTeacherMapper.insertCopy(tchVO);
			  
			  //과목 목차 가져오기
			  List<CreateOnlineSubjectVO> returnList = createCourseOnlineSubjectMapper.list(onsVO);
			  for(int i=0; i<returnList.size(); i++) {
				  ContentsVO cntVO = new ContentsVO();
				  cntVO.setSbjCd(returnList.get(i).getSbjCd());
				  cntVO.setCrsCreCd(createCourseCd);
				  cntVO.setRegNo(iCreateCourseVO.getRegNo());
				  cntVO.setModNo(iCreateCourseVO.getModNo());
				  
				  contentsMapper.insertCopy(cntVO);
			  }
			  
			  // 오프라인 or 혼합과정 QR 코드 생성
			  if(iCreateCourseVO.getCreTypeCd().equals("OF") || iCreateCourseVO.getCreTypeCd().equals("BL")){
				  iCreateCourseVO.setCrsCreQrInFilePath(QrUtil.getCreateQrImage(createCourseCd, "in", iCreateCourseVO.getOrgCd()));
				  iCreateCourseVO.setCrsCreQrInNo(QrUtil.getCreateQrNo(4, 9));
				  iCreateCourseVO.setCrsCreQrOutFilePath(QrUtil.getCreateQrImage(createCourseCd, "out", iCreateCourseVO.getOrgCd()));
				  iCreateCourseVO.setCrsCreQrOutNo(QrUtil.getCreateQrNo(4, 9));
				  
				  createCourseMapper.insertCreateCourseQr(iCreateCourseVO);
			  }
			  
			  
			  //주말을 제외한 오프라인 교육일 등록
			  if(iCreateCourseVO.getCreTypeCd().equals("OF") || iCreateCourseVO.getCreTypeCd().equals("BL")) {
				  AttendanceVO avo = new AttendanceVO();
				  avo.setCrsCreCd(iCreateCourseVO.getCrsCreCd());
				  avo.setRegNo(iCreateCourseVO.getRegNo());
				  avo.setModNo(iCreateCourseVO.getModNo());
				  
				  LocalDateTime startDay = DateTimeUtil.parseDttmToLocalDateTime(iCreateCourseVO.getEnrlStartDttm());
				  LocalDateTime endDay = DateTimeUtil.parseDttmToLocalDateTime(iCreateCourseVO.getEnrlEndDttm());
				  while(!startDay.isAfter(endDay)) {
					  if(!isWeekend(startDay)) {
						  //yyyyMMddHHmm01 형태로 저장
						  avo.setAttendDttm(startDay.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
						  System.out.println(isWeekend(startDay));
						  System.out.println(startDay);
						  attendanceMapper.insertAttendDttm(avo);
					  }
					  startDay = startDay.plusDays(1);
				  }
			  }
		
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		return resultVO;
	}
	
	//주말 체크
	  private static boolean isWeekend(LocalDateTime date) {
		  DayOfWeek dayOfWeek = date.getDayOfWeek();
		  return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
	  }
	
	/**
	 * 개설 과정 등록
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<CreateCourseVO> addCertificateCreateCourse(CreateCourseVO iCreateCourseVO) throws Exception {

		ProcessResultVO<CreateCourseVO> resultVO = new ProcessResultVO<CreateCourseVO>();
		
		CourseVO cVO = new CourseVO();
		cVO.setCrsCd(iCreateCourseVO.getCrsCd());
		cVO = courseMapper.select(cVO);
		
		//-- 개설 과정 코드 생성
		String createCourseCd = createCourseMapper.selectCreateCourseCd();
		iCreateCourseVO.setCrsCreCd(createCourseCd);
		iCreateCourseVO.setCrsCd(cVO.getCrsCd());
		//-- 개설 과정 생성
		createCourseMapper.insertCreateCourse(iCreateCourseVO);
				
		resultVO.setResultSuccess();
		
		return resultVO;
	}

	/**
	 * 개설 과정 수정
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	@HrdApiCrsCreCrs(CreSyncType.CREUPDATE)
	public ProcessResultVO<CreateCourseVO> editCreateCourse(CreateCourseVO iCreateCourseVO) throws Exception {
		
		ProcessResultVO<CreateCourseVO> resultVO = new ProcessResultVO<CreateCourseVO>();
		setDateConvert(iCreateCourseVO);
		//--- 과정 정보 변경
		createCourseMapper.updateCreateCourse(iCreateCourseVO);
		
		iCreateCourseVO = createCourseMapper.selectCreateCourse(iCreateCourseVO);
		// 오프라인 교육일 삭제 후 재등록
		  if(iCreateCourseVO.getCreTypeCd().equals("OF") || iCreateCourseVO.getCreTypeCd().equals("BL")) {
			  AttendanceVO avo = new AttendanceVO();
			  avo.setCrsCreCd(iCreateCourseVO.getCrsCreCd());
			  avo.setRegNo(iCreateCourseVO.getRegNo());
			  avo.setModNo(iCreateCourseVO.getModNo());
			  attendanceMapper.deleteAttendDttm(avo);
			  
			  LocalDateTime startDay = DateTimeUtil.parseDttmToLocalDateTime(iCreateCourseVO.getEnrlStartDttm());
			  LocalDateTime endDay = DateTimeUtil.parseDttmToLocalDateTime(iCreateCourseVO.getEnrlEndDttm());
			  while(!startDay.isAfter(endDay)) {
				  if(!isWeekend(startDay)) {
					  //yyyyMMddHHmm01 형태로 저장
					  avo.setAttendDttm(startDay.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
					  System.out.println(isWeekend(startDay));
					  System.out.println(startDay);
					  attendanceMapper.insertAttendDttm(avo);
				  }
				  startDay = startDay.plusDays(1);
			  }
		  }
		sysFileService.bindFileUpdate(iCreateCourseVO, new NestedQrFileHandler());
		resultVO.setReturnVO(iCreateCourseVO);
		resultVO.setResultSuccess();

		return resultVO;
	}
	
	/**
	 * 개설 과정 수정
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<CreateCourseVO> editCertCreateCourse(CreateCourseVO iCreateCourseVO) throws Exception {
		
		ProcessResultVO<CreateCourseVO> resultVO = new ProcessResultVO<CreateCourseVO>();
		//--- 과정 정보 변경
		createCourseMapper.updateCreateCourse(iCreateCourseVO);
		resultVO.setReturnVO(iCreateCourseVO);
		resultVO.setResultSuccess();

		return resultVO;
	}

	private void setDateConvert(CreateCourseVO iCreateCourseVO) throws Exception {

		//-- 시간값 변경
		String enrlAplcStartDttm = "";
		String enrlAplcEndDttm = "";
		String enrlStartDttm = "";
		String enrlEndDttm = "";
		String auditEndDttm = "";
		String oflnStartDttm = "";
		String oflnEndDttm = "";
		String scoreOpenDttm = "";
		if(!"".equals(StringUtil.nvl(iCreateCourseVO.getEnrlAplcStartDttm())))
			enrlAplcStartDttm = StringUtil.ReplaceAll(StringUtil.ReplaceAll(StringUtil.ReplaceAll(iCreateCourseVO.getEnrlAplcStartDttm(), ".", ""),"-",""),"/","")+"000001";
		if(!"".equals(StringUtil.nvl(iCreateCourseVO.getEnrlAplcEndDttm())))
			enrlAplcEndDttm = StringUtil.ReplaceAll(StringUtil.ReplaceAll(StringUtil.ReplaceAll(iCreateCourseVO.getEnrlAplcEndDttm(), ".", ""),"-",""),"/","")+"235959";
		if(!"".equals(StringUtil.nvl(iCreateCourseVO.getEnrlStartDttm())))
			enrlStartDttm = StringUtil.ReplaceAll(StringUtil.ReplaceAll(StringUtil.ReplaceAll(iCreateCourseVO.getEnrlStartDttm(), ".", ""),"-",""),"/","")+"000001";
		if(!"".equals(StringUtil.nvl(iCreateCourseVO.getEnrlEndDttm())))
			enrlEndDttm = StringUtil.ReplaceAll(StringUtil.ReplaceAll(StringUtil.ReplaceAll(iCreateCourseVO.getEnrlEndDttm(), ".", ""),"-",""),"/","")+"235959";
		if(!"".equals(StringUtil.nvl(iCreateCourseVO.getAuditEndDttm())))
			auditEndDttm = StringUtil.ReplaceAll(StringUtil.ReplaceAll(StringUtil.ReplaceAll(iCreateCourseVO.getAuditEndDttm(), ".", ""),"-",""),"/","")+"235959";
		if(!"".equals(StringUtil.nvl(iCreateCourseVO.getOflnStartDttm())))
			oflnStartDttm = StringUtil.ReplaceAll(StringUtil.ReplaceAll(StringUtil.ReplaceAll(iCreateCourseVO.getOflnStartDttm(), ".", ""),"-",""),"/","")+"000001";
		if(!"".equals(StringUtil.nvl(iCreateCourseVO.getOflnEndDttm())))
			oflnEndDttm = StringUtil.ReplaceAll(StringUtil.ReplaceAll(StringUtil.ReplaceAll(iCreateCourseVO.getOflnEndDttm(), ".", ""),"-",""),"/","")+"235959";
		if(!"".equals(StringUtil.nvl(iCreateCourseVO.getScoreOpenDttm())))
			scoreOpenDttm = StringUtil.ReplaceAll(StringUtil.ReplaceAll(StringUtil.ReplaceAll(iCreateCourseVO.getScoreOpenDttm(), ".", ""),"-",""),"/","")+"000001";
		iCreateCourseVO.setEnrlAplcStartDttm(enrlAplcStartDttm);
		iCreateCourseVO.setEnrlAplcEndDttm(enrlAplcEndDttm);
		iCreateCourseVO.setEnrlStartDttm(enrlStartDttm);
		iCreateCourseVO.setEnrlEndDttm(enrlEndDttm);
		iCreateCourseVO.setAuditEndDttm(auditEndDttm);
		iCreateCourseVO.setOflnStartDttm(oflnStartDttm);
		iCreateCourseVO.setOflnEndDttm(oflnEndDttm);
		iCreateCourseVO.setScoreOpenDttm(scoreOpenDttm);
	}

	/**
	 * 개설 과정 삭제
	 * @param crsCd 삭제 대상 코드값
	 * @return
	 */
	@Override
	@HrdApiCrsCreCrs(CreSyncType.DELETE)
	public ProcessResultVO<?> deleteCreateCourse(CreateCourseVO iCreateCourseVO) throws Exception {

		ProcessResultVO<?> resultVO = new ProcessResultVO();
			//-- 과정 개설시 과정 개시판 삭제.
		LecBbsVO bbsVO = new LecBbsVO();
		bbsVO.setCrsCreCd(iCreateCourseVO.getCrsCreCd());
		lecBbsMapper.deleteAll(bbsVO);

		//수강신청 삭제한 학생 정보 삭제
		StudentVO svo = new StudentVO();
		svo.setEnrlSts("D");
		svo.setCrsCreCd(iCreateCourseVO.getCrsCreCd());
		List<StudentVO> sList = studentMapper.listStudent(svo);
		for(int i=0; i<sList.size(); i++) {
			studentMapper.deleteStudentEduRslt(sList.get(i));
		}
		
		studentMapper.deleteCreateCourseStudent(svo);

		//-- 담임,튜터 삭제
		TeacherVO tchVO = new TeacherVO();
		tchVO.setCrsCreCd(iCreateCourseVO.getCrsCreCd());
		createCourseTeacherMapper.deleteTeacherAll(tchVO);
		
		//-- 개설된 과정의 분반을 삭제한다.
		CreCrsDeclsVO creCrsDeclsVO = new CreCrsDeclsVO();
		creCrsDeclsVO.setCrsCreCd(iCreateCourseVO.getCrsCreCd());
		creCrsDeclsMapper.deleteAll(creCrsDeclsVO);
		
		//-- 강의실 접속 로그 삭제
		LogClassConnVO lccvo = new LogClassConnVO();
		lccvo.setCrsCreCd(iCreateCourseVO.getCrsCreCd());
		logClassConnMapper.delClassConLog(lccvo);
		//-- 개설과정 QR 정보 삭제
		createCourseMapper.deleteCreateCourseQr(iCreateCourseVO);
		
		createCourseMapper.deleteCreateCourse(iCreateCourseVO);
		
		resultVO.setResultSuccess();
		
		return resultVO;
		
		
	}
	
	/**
	 * 개설 과정 삭제
	 * @param crsCreCd 삭제 대상 코드값
	 * @return
	 */
	@Override
	public ProcessResultVO<?> deleteCertCreateCourse(CreateCourseVO iCreateCourseVO) throws Exception {

		ProcessResultVO<?> resultVO = new ProcessResultVO();
		
		//-- 개설된 과정의 수강생을 삭제한다.
		StudentVO studentVO = new StudentVO();
		studentVO.setCrsCreCd(iCreateCourseVO.getCrsCreCd());
		studentMapper.deleteCreateCourseStd(studentVO);
		
		//-- 개설된 과정을 삭제한다.
		createCourseMapper.deleteCreateCourse(iCreateCourseVO);
		resultVO.setResultSuccess();
		
		return resultVO;
	}

	/**
	 * 설문지 은행 사용중인 과정 리스트
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<ResearchCourseVO> listCreateCourseForResearch(Map<String, Object> researchInfo)  throws Exception {
		ProcessResultListVO<ResearchCourseVO> resultList = new ProcessResultListVO<ResearchCourseVO>();
		try {
			List<ResearchCourseVO> returnList = createCourseMapper.listCreateCourseForResearch(researchInfo);
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
	 * 학습자 학습중인 과정 리스트
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<UserCourseVO> listCreateCourseForStudent(Map<String, Object> userInfo) throws Exception {
		ProcessResultListVO<UserCourseVO> resultList = new ProcessResultListVO<UserCourseVO>();
		try {
			List<UserCourseVO> returnList = createCourseMapper.listCreateCourseForStudent(userInfo);
			for(UserCourseVO vo : returnList) {
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

	/**
	 * 학습자 학습중인 과정 리스트
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<UserCourseVO> listCreateCourseForStudent(Map<String, Object> userInfo, int curPage, int listScale, int pageScale) throws Exception {

		ProcessResultListVO<UserCourseVO> resultList = new ProcessResultListVO<UserCourseVO>();
		
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		

		userInfo.put("firstRecordIndex", paginationInfo.getFirstRecordIndex());
		userInfo.put("lastRecordIndex", paginationInfo.getLastRecordIndex());

		try {
			// 전체 목록 수
			int totalCount = createCourseMapper.listCreateCourseForStudentPageingCount(userInfo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<UserCourseVO> returnList = createCourseMapper.listCreateCourseForStudentPageing(userInfo);
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
	 * 학습자 학습중인 과정 리스트
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<UserCourseVO> listCreateCourseForStudent(Map<String, Object> userInfo, int curPage, int listScale) throws Exception {

		ProcessResultListVO<UserCourseVO> resultList = new ProcessResultListVO<UserCourseVO>();
		
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
		

		userInfo.put("firstRecordIndex", paginationInfo.getFirstRecordIndex());
		userInfo.put("lastRecordIndex", paginationInfo.getLastRecordIndex());
		try {
			// 전체 목록 수
			int totalCount = createCourseMapper.listCreateCourseForStudentPageingCount(userInfo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<UserCourseVO> returnList = createCourseMapper.listCreateCourseForStudentPageing(userInfo);
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
	 * 학습자 학습중인 과정 리스트
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<UserCourseVO> listCreateCourseForStudent(Map<String, Object> userInfo, int curPage) throws Exception {

		ProcessResultListVO<UserCourseVO> resultList = new ProcessResultListVO<UserCourseVO>();
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(Constants.LIST_SCALE);
		paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
		

		userInfo.put("firstIndex", paginationInfo.getFirstRecordIndex());
		userInfo.put("lastIndex", paginationInfo.getLastRecordIndex());
		try {
			// 전체 목록 수
			int totalCount = createCourseMapper.listCreateCourseForStudentPageingCount(userInfo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<UserCourseVO> returnList = createCourseMapper.listCreateCourseForStudentPageing(userInfo);
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
	 * 교수자 담당 과정 목록
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<UserCourseVO> listCreateCourseForTeacher(Map<String, Object> userInfo, int curPage, int listScale, int pageScale) throws Exception {

		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		userInfo.put("firstIndex", paginationInfo.getFirstRecordIndex());
		userInfo.put("lastIndex", paginationInfo.getLastRecordIndex());
		ProcessResultListVO<UserCourseVO> resultList = new ProcessResultListVO<UserCourseVO>();
		try {
			// 전체 목록 수
			int totalCount = createCourseMapper.listCreateCourseForTeacherCount(userInfo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<UserCourseVO> returnList = createCourseMapper.listCreateCourseForTeacher(userInfo);
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
	 * 교수자 담당 과정 목록
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<UserCourseVO> listCreateCourseForTeacher(Map<String, Object> userInfo, int curPage, int listScale) throws Exception {

		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
		userInfo.put("firstIndex", paginationInfo.getFirstRecordIndex());
		userInfo.put("lastIndex", paginationInfo.getLastRecordIndex());
		ProcessResultListVO<UserCourseVO> resultList = new ProcessResultListVO<UserCourseVO>();
		try {
			// 전체 목록 수
			int totalCount = createCourseMapper.listCreateCourseForTeacherCount(userInfo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<UserCourseVO> returnList = createCourseMapper.listCreateCourseForTeacher(userInfo);
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
	 * 교수자 담당 과정 목록
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<UserCourseVO> listCreateCourseForTeacher(Map<String, Object> userInfo, int curPage) throws Exception {

		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(Constants.LIST_SCALE);
		paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
		userInfo.put("firstIndex", paginationInfo.getFirstRecordIndex());
		userInfo.put("lastIndex", paginationInfo.getLastRecordIndex());
		ProcessResultListVO<UserCourseVO> resultList = new ProcessResultListVO<UserCourseVO>();
		try {
			// 전체 목록 수
			int totalCount = createCourseMapper.listCreateCourseForTeacherCount(userInfo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<UserCourseVO> returnList = createCourseMapper.listCreateCourseForTeacher(userInfo);
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
	 * 교수자 담당 과정 목록
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<UserCourseVO> listCreateCourseForTeacher(Map<String, Object> userInfo) throws Exception {
		ProcessResultListVO<UserCourseVO> resultList = new ProcessResultListVO<UserCourseVO>();
		try {
			List<UserCourseVO> returnList = createCourseMapper.listCreateCourseForTeacher(userInfo);
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
	 * 월별 개설 과정 목록 조회 (리스트 형태)
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<CreateCourseVO> listCreateCourseForMonth(CreateCourseVO iCreateCourseVO) throws Exception {
		ProcessResultListVO<CreateCourseVO> resultList = new ProcessResultListVO<CreateCourseVO>();
		try {
			List<CreateCourseVO> returnList = createCourseMapper.listCreateCourseForMonth(iCreateCourseVO);
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
	 * 월별 개설 과정 목록 조회 (칼렌더 형태)
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<CreateCourseVO> calendarCreateCourseForMonth(CreateCourseVO iCreateCourseVO)  throws Exception {
		ProcessResultListVO<CreateCourseVO> resultList = new ProcessResultListVO<CreateCourseVO>();
		try {
			List<CreateCourseVO> returnList = createCourseMapper.calendarCreateCourseForMonth(iCreateCourseVO);
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
	 * 트랜잭션 테스트용 매소드(테스트에서만 사용된다.)
	 */
	@Override
	@Deprecated
	public ProcessResultVO<CreateCourseVO> transactionRollbackMethod(CreateCourseVO iCreateCourseVO) throws Exception {
		this.addCreateCourse(iCreateCourseVO);
		throw new Exception("트랜잭션 테스트를 위한 강제 Exception");
	}

	/**
	 * 과정 코드로 기수 목록 조회
	 */
	@Override
	public ProcessResultListVO<CreateCourseVO> listCourseTerm(CreateCourseVO iCreateCourseVO) throws Exception {
		ProcessResultListVO<CreateCourseVO> resultList = new ProcessResultListVO<CreateCourseVO>();
		try {
			List<CreateCourseVO> returnList = createCourseMapper.listCourseTerm(iCreateCourseVO);
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
	 * 월별 개설 과정 목록 조회 (칼렌더 형태)
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<CreateCourseVO> calendarCreateCourse(CreateCourseVO iCreateCourseVO) throws Exception {
		ProcessResultListVO<CreateCourseVO> resultList = new ProcessResultListVO<CreateCourseVO>();
		try {
			List<CreateCourseVO> returnList = createCourseMapper.calendarCreateCourse(iCreateCourseVO);
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
	 * 개설 기수 최대값 가져오기
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultVO<CreateCourseVO> selectMaxTerm(CreateCourseVO vo) throws Exception {
		ProcessResultVO<CreateCourseVO> resultVO = new ProcessResultVO<CreateCourseVO>();
		try {
			CreateCourseVO returnVO = createCourseMapper.selectMaxTerm(vo);
			if(returnVO == null) {
				returnVO = new CreateCourseVO();
				returnVO.setCreTerm(0);
			}
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
	 * 과정명 검색 개설 과정 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<CreateCourseVO> listCreateCourseForEnrollSearchPaging(CreateCourseVO iCreateCourseVO) throws Exception {

		ProcessResultListVO<CreateCourseVO> resultList = new ProcessResultListVO<CreateCourseVO>();
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(iCreateCourseVO.getCurPage());
		paginationInfo.setRecordCountPerPage(iCreateCourseVO.getListScale());
		paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
		
		iCreateCourseVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		iCreateCourseVO.setLastIndex(paginationInfo.getLastRecordIndex());
		try {
			// 전체 목록 수
			int totalCount = createCourseMapper.listCreateCourseForEnrollSearchPagingCount(iCreateCourseVO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<CreateCourseVO> returnList = createCourseMapper.listCreateCourseForEnrollSearchPaging(iCreateCourseVO);
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
	 * [HRD] [교육과정/신청>교육과정 및 신청] - 개설과정 리스트 조회
	 */
	@Override
	public ProcessResultListVO<CreateCourseVO> listCreateCourseForEnrollSearchBySubjectPaging(CreateCourseVO iCreateCourseVO, boolean filein) {

		ProcessResultListVO<CreateCourseVO> resultList = new ProcessResultListVO<CreateCourseVO>();
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(iCreateCourseVO.getCurPage());
		paginationInfo.setRecordCountPerPage(iCreateCourseVO.getListScale());
		paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
		
		iCreateCourseVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		iCreateCourseVO.setLastIndex(paginationInfo.getLastRecordIndex());
		try {
			// 전체 목록 수
			int totalCount = createCourseMapper.listCreateCourseForEnrollSearchBySubjectPagingCount(iCreateCourseVO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<CreateCourseVO> returnList = createCourseMapper.listCreateCourseForEnrollSearchBySubjectPaging(iCreateCourseVO);
			if(filein) {
				List<CreateCourseVO> courseList = new ArrayList<CreateCourseVO>();
				for(CreateCourseVO cvo : returnList) {
					cvo = sysFileService.getFile(cvo, new NestedThumbFileHandler());
					cvo = sysFileService.getFile(cvo, new NestedPlanFileHandler());
					courseList.add(cvo);
				}
				resultList.setReturnList(courseList);
			} else {
				resultList.setReturnList(returnList);	
			}			
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
	 * 과목 사용중인 개설 과정 목록 조회 - 온라인
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<CreateCourseVO> listSubInfo(CreateCourseVO iCreateCourseVO) throws Exception {
		ProcessResultListVO<CreateCourseVO> resultList = new ProcessResultListVO<CreateCourseVO>();
		try {
			List<CreateCourseVO> returnList = createCourseMapper.listSubInfo(iCreateCourseVO);
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
	 * 과목 사용중인 개설 과정 목록 조회 - 오프라인
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<CreateCourseVO> listSubInfoOffline(CreateCourseVO iCreateCourseVO) throws Exception {
		ProcessResultListVO<CreateCourseVO> resultList = new ProcessResultListVO<CreateCourseVO>();
		try {
			List<CreateCourseVO> returnList = createCourseMapper.listSubInfoOffline(iCreateCourseVO);
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
	 * 진행중인과정 - Todo리스트 목록조회
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<CreateCourseVO> listCreateCourseTodoList(CreateCourseVO iCreateCourseVO) throws Exception {
		ProcessResultListVO<CreateCourseVO> resultList = new ProcessResultListVO<CreateCourseVO>();
		try {
			List<CreateCourseVO> returnList = createCourseMapper.listCreateCourseTodoList(iCreateCourseVO);
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
	 * 개설 과정 전체 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<CreateCourseVO> listCreateCourseForAll(CreateCourseVO iCreateCourseVO) throws Exception {
		ProcessResultListVO<CreateCourseVO> resultList = new ProcessResultListVO<CreateCourseVO>();
		try {
			List<CreateCourseVO> returnList = createCourseMapper.listCreateCourseForAll(iCreateCourseVO);
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
	 * 과정 코드로 기수 목록 조회
	 */
	@Override
	public ProcessResultListVO<CreateCourseVO> listDeptByCrsCd(CreateCourseVO iCreateCourseVO) throws Exception {
		ProcessResultListVO<CreateCourseVO> resultList = new ProcessResultListVO<CreateCourseVO>();
		try {
			List<CreateCourseVO> returnList = createCourseMapper.listDeptByCrsCd(iCreateCourseVO);
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
	 * 과정 코드로 기수 목록 조회
	 */
	@Override
	public ProcessResultListVO<CreateCourseVO> listSbjByDeptCd(CreateCourseVO iCreateCourseVO) throws Exception {
		ProcessResultListVO<CreateCourseVO> resultList = new ProcessResultListVO<CreateCourseVO>();
		try {
			List<CreateCourseVO> returnList = createCourseMapper.listSbjByDeptCd(iCreateCourseVO);
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
	 * 과정 코드로 기수 목록 조회
	 */
	@Override
	public ProcessResultListVO<UserCourseVO> myCreListForStudent(Map<String, Object> userInfo) throws Exception {
		ProcessResultListVO<UserCourseVO> resultList = new ProcessResultListVO<UserCourseVO>();
		try {
			List<UserCourseVO> returnList = createCourseMapper.myCreListForStudent(userInfo);
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
	 * 과정 코드로 기수 목록 조회
	 */
	@Override
	public ProcessResultListVO<UserCourseVO> myCrsListForStudent(Map<String, Object> userInfo) throws Exception {
		ProcessResultListVO<UserCourseVO> resultList = new ProcessResultListVO<UserCourseVO>();
		try {
			List<UserCourseVO> returnList = createCourseMapper.myCrsListForStudent(userInfo);
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
	 * 과정 코드로 기수 목록 조회
	 */
	@Override
	public ProcessResultListVO<UserCourseVO> myCreListForTeacher(Map<String, Object> userInfo) throws Exception {
		ProcessResultListVO<UserCourseVO> resultList = new ProcessResultListVO<UserCourseVO>();
		try {
			List<UserCourseVO> returnList = createCourseMapper.myCreListForTeacher(userInfo);
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
	 * 과정 코드로 기수 목록 조회
	 */
	@Override
	public ProcessResultListVO<UserCourseVO> myCrsListForTeacher(Map<String, Object> userInfo) throws Exception {
		ProcessResultListVO<UserCourseVO> resultList = new ProcessResultListVO<UserCourseVO>();
		try {
			List<UserCourseVO> returnList = createCourseMapper.myCrsListForTeacher(userInfo);
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
	 * [HRD] [수강신청결제>강의계획서확인/동의] - 강의정보 조회
	 */
	@Override
	public ProcessResultVO<CreateCourseVO> viewCreateCourseForEnroll(CreateCourseVO vo) {
		ProcessResultVO<CreateCourseVO> resultVO = new ProcessResultVO<>();
		try {
			CreateCourseVO returnVO = createCourseMapper.selectCreateCourseForEnroll(vo);
			returnVO = sysFileService.getFile(returnVO, new NestedThumbFileHandler());
			returnVO = sysFileService.getFile(returnVO, new NestedPlanFileHandler());
			resultVO.setReturnVO(returnVO);
			resultVO.setResultSuccess();
		} catch (Exception e) {
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		return resultVO; 
	}
	
	@Override
	public CreateCourseVO viewCreateCourseForAttend(CreateCourseVO vo) throws Exception {

		CreateCourseVO createCourse = createCourseMapper.selectCreateCourseForAttend(vo);
		if(createCourse == null) throw new Exception("해당하는 개설과정이 존재하지 않습니다.");
		createCourse = sysFileService.getFile(createCourse, new NestedThumbFileHandler());
		createCourse = sysFileService.getFile(createCourse, new NestedPlanFileHandler());
		
		return createCourse; 
	}


	/**
	 * [HRD] 관리자>수강신청관리>엑셀업로드 - 샘플다운로드 개설과정 조회 : 학기의 종료일이 지난 개설과정은 조회하지 않음
	 */
	@Override
	public ProcessResultListVO<CreateCourseVO> listCreateCourseForManageEnroll(CreateCourseVO vo)
			throws Exception {
		return new ProcessResultListVO<CreateCourseVO>(createCourseMapper.listCreateCourseForManageEnroll(vo));
	}


	@Override
	public CreateCourseVO cntCourse(CreateCourseVO cntCourseVO) {
		CreateCourseVO cntCourse = createCourseMapper.cntCourse(cntCourseVO);
		return cntCourse;
	}
	
	/**
	 * 수강신청 목록을 조회하여 반환한다.
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<CourseVO>
	 */
	@Override
	public ProcessResultListVO<CreateCourseVO> listStdCoursePaging(CreateCourseVO VO)  throws Exception {

		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(VO.getCurPage());
		paginationInfo.setRecordCountPerPage(VO.getListScale());
		paginationInfo.setPageSize(VO.getPageScale());
		
		VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		VO.setLastIndex(paginationInfo.getLastRecordIndex());
		
		ProcessResultListVO<CreateCourseVO> resultList = new ProcessResultListVO<CreateCourseVO>();
		try {
			// 전체 목록 수
			int totalCount = createCourseMapper.countCourse(VO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<CreateCourseVO> returnList = createCourseMapper.listUserStdCourse(VO);
			resultList.setReturnList(returnList);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}


}
