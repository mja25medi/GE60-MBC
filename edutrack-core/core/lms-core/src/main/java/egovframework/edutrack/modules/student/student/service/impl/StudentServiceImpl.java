package egovframework.edutrack.modules.student.student.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.annotation.HrdApiStdStd;
import egovframework.edutrack.comm.annotation.HrdApiStdStd.SyncType;
import egovframework.edutrack.comm.annotation.RefundHistory;
import egovframework.edutrack.comm.annotation.RefundHistory.RefundType;
import egovframework.edutrack.comm.exception.MediopiaDefineException;
import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.BeanUtils;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.course.course.service.CourseVO;
import egovframework.edutrack.modules.course.course.service.impl.CourseMapper;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;
import egovframework.edutrack.modules.course.createcourse.service.impl.CreateCourseMapper;
import egovframework.edutrack.modules.lecture.bookmark.service.BookmarkVO;
import egovframework.edutrack.modules.lecture.bookmark.service.impl.BookmarkMapper;
import egovframework.edutrack.modules.student.payment.service.impl.PaymentMapper;
import egovframework.edutrack.modules.student.result.service.EduResultVO;
import egovframework.edutrack.modules.student.result.service.impl.EduResultMapper;
import egovframework.edutrack.modules.student.student.service.StudentService;
import egovframework.edutrack.modules.student.student.service.StudentVO;
import egovframework.edutrack.modules.student.student.service.ValidateStudentException;
import egovframework.edutrack.modules.user.info.service.UsrLoginVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;
import egovframework.edutrack.modules.user.info.service.impl.UsrLoginMapper;
import egovframework.edutrack.modules.user.info.service.impl.UsrUserInfoMapper;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Service("studentService")
public class StudentServiceImpl extends EgovAbstractServiceImpl implements StudentService {

	/** Mapper */
	@Resource(name="studentMapper")
	private StudentMapper 		studentMapper;
	
	@Resource(name="paymentMapper")
	private PaymentMapper paymentMapper;
	
	@Resource(name="usrLoginMapper")
	private UsrLoginMapper 		usrLoginMapper;

	@Resource(name="eduResultMapper")
	private EduResultMapper 		eduResultMapper;

	@Resource(name="createCourseMapper")
	private CreateCourseMapper 		createCourseMapper;
	
	@Resource(name="courseMapper")
	private CourseMapper 		courseMapper;

	@Resource(name="bookmarkMapper")
	private BookmarkMapper 		bookmarkMapper;
	
	@Resource(name="studentService")
	private StudentService 	 studentService;
	
	@Resource
	private UsrUserInfoMapper usrUserInfoMapper;
	
	/**
	 * 수강 신청 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	public ProcessResultListVO<StudentVO> listStudent(StudentVO iStudentVO) throws Exception{
		ProcessResultListVO<StudentVO> resultList = new ProcessResultListVO<StudentVO>();
		try {
			List<StudentVO> returnList = studentMapper.listStudent(iStudentVO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}		
		
		return resultList;
	}

	/**
	 * 수강 신청 목록 조회(페이징)
	 *
	 * @return ProcessResultListVO
	 */
	public ProcessResultListVO<StudentVO> listStudentPageing(StudentVO iStudentVO) throws Exception{
		
		ProcessResultListVO<StudentVO> resultList = new ProcessResultListVO<StudentVO>();
		
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(iStudentVO.getCurPage());
			paginationInfo.setRecordCountPerPage(iStudentVO.getListScale());
			paginationInfo.setPageSize(iStudentVO.getPageScale());
			
			iStudentVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			iStudentVO.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = studentMapper.count(iStudentVO);
			paginationInfo.setTotalRecordCount(totalCount);			
			
			List<StudentVO> returnList = studentMapper.listStudentPageing(iStudentVO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}		
		return resultList;
	}



	/**
	 * 수강 신청 정보 조회
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<StudentVO> viewStudent(StudentVO iStudentVO) throws Exception{
		ProcessResultVO<StudentVO> resultVO = new ProcessResultVO<StudentVO>();
		try {
			StudentVO returnVO = studentMapper.selectStudent(iStudentVO);
			resultVO.setReturnVO(returnVO);
			resultVO.setResult(1);
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResult(-1);
			resultVO.setMessage(e.getMessage());
		}		
		return resultVO;
	}

	/**
	 * 수강 신청 등록 (학습자, 회원정보와 수강신청 정보를 같이 바꾼다.)
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<StudentVO> addEnrollStudent(StudentVO iStudentVO) throws Exception{
		ProcessResultVO<StudentVO> resultVO = new ProcessResultVO<StudentVO>();
		try {
			if("".equals(StringUtil.nvl(iStudentVO.getStdNo()))) {
				iStudentVO.setStdNo(studentMapper.selectKey());
			}
			studentMapper.insertStudent(iStudentVO);
			resultVO.setReturnVO(iStudentVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}		
		return resultVO;
	}


	/**
	 * 수강 신청 등록
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<StudentVO> addStudent(StudentVO studentVO)throws Exception{
		
		/*
		 *  23.12.19 메디
		 *  관리자 > 수강생등록 시 유/무료 상관없이 전부 무료수강처리
		 */
		//studentMapper.insertStudentSp(iStudentVO);
		
		//개설과정 조회
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(studentVO.getCrsCreCd());
		CreateCourseVO viewCreateCourse = createCourseMapper.selectCreateCourseForEnroll(createCourseVO);
		
		if(ValidationUtils.isNull(viewCreateCourse)) {
			throw new ServiceProcessException("개설과정 조회 오류");
		}

		UsrUserInfoVO viewUserInfo = usrUserInfoMapper.select(new UsrUserInfoVO(studentVO.getUserNo()));
		if(viewUserInfo == null) {
			throw new ServiceProcessException("회원 조회 오류");
		}
		
		StudentVO isEnroll = studentMapper.isEnroll(studentVO);
		if("Y".equals(isEnroll.getStdYn())) {
			throw new ServiceProcessException("중복 신청 불가능합니다.");
		}
		
		studentVO.setDeclsNo(1);
		studentVO.setEnrlSts("E"); //승인대기
		studentVO.setEnrlStartDttm(viewCreateCourse.getEnrlStartDttm());
		studentVO.setEnrlEndDttm(viewCreateCourse.getEnrlEndDttm());
		studentVO.setAuditEndDttm(viewCreateCourse.getAuditEndDttm());
		studentVO.setStdNo(studentMapper.selectKey());
		studentVO.setOrgCd(viewUserInfo.getOrgCd());
		studentVO.setDeptNm(viewUserInfo.getDeptNm());
		return new ProcessResultVO<>(studentService.addStudentForHrdApi(studentVO));
		
		/*ProcessResultVO<StudentVO> resultVO = new ProcessResultVO<>();
		
		//유료과정
		if(iStudentVO.getPaymPrice() != 0) {
			
			//결제 정보 저장
			PaymentVO paymentVO = new PaymentVO();
			String paymNo = paymentMapper.selectKey();			
			
			paymentVO.setPaymNo(paymNo);
			paymentVO.setPaymMthdCd(iStudentVO.getPaymMthdCd());
			paymentVO.setUserNo(iStudentVO.getUserNo());
			paymentVO.setPaymPrice(iStudentVO.getPaymPrice());
			
			if(iStudentVO.getPaymDttm().length() != 0) {
				String paymDttm = StringUtil.ReplaceAll(iStudentVO.getPaymDttm(),".","")+"235959";
				paymentVO.setPaymDttm(paymDttm);			
			}
			
			if("PAYM003".equals(iStudentVO.getPaymStsCd())) {//가상계좌(무통장입금)인 경우 결제 상태 = 결제대기(DI) 
				paymentVO.setPaymStsCd("DI");
			}else {//그외(카드결제, 실시간결제) 결제 완료 DF
				paymentVO.setPaymStsCd("DF");
			}
			
			if(paymentMapper.insertPayInfo(paymentVO) > 0) {
				resultVO.setMessage("결제 정보 등록 성공");
				resultVO.setResultSuccess();
			}else {
				resultVO.setMessage("결제 정보 등록 오류");
				resultVO.setResultFailed();
			}
			
			if("PAYM003".equals(paymentVO.getPaymMthdCd())) {//가상계좌(무통장입금)인 경우 수강상태 = 수강대기(E)
				iStudentVO.setEnrlSts("E");
			}else {//그외(카드결제, 실시간결제) 수강신청 완료 S
				iStudentVO.setEnrlSts("S");
			}
			
			// 수강생 등록
			iStudentVO.setPaymNo(paymNo);		
			studentMapper.insertStudentSp(iStudentVO);
			
		}else {
			//무료과정
			studentMapper.insertStudentSp(iStudentVO);
		}

		resultVO.setResultSuccess();
		return resultVO;*/
	}

	/**
	 * 수강 신청 등록 배치
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<Object> addStudentBatch(List<StudentVO> iStudentArray) throws Exception {
		ProcessResultVO<Object> resultVO = new ProcessResultVO<Object>();
		
		try {
			studentMapper.insertStudentBatch(iStudentArray);
			resultVO.setReturnVO(iStudentArray);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		return  resultVO;
	}



	/**
	 * 수강 신청 수정
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<StudentVO> editStudent(StudentVO iStudentVO) throws Exception {
		ProcessResultVO<StudentVO> resultVO = new ProcessResultVO<StudentVO>();
		try {
			studentMapper.updateStudent(iStudentVO);
			resultVO.setReturnVO(iStudentVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		return resultVO;
	}
	
	/**
	 * 수강생 ide 수정
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<StudentVO> editStudentUrl(StudentVO iStudentVO) throws Exception {
		ProcessResultVO<StudentVO> resultVO = new ProcessResultVO<StudentVO>();
		int iResult = 0;
		
		try {
			
			if(iStudentVO.getStdNos() != null) {
				for(int i=0; i < iStudentVO.getStdNos().length; i++) {
					StudentVO pStudentVO = new StudentVO();
					
					pStudentVO.setStdNo(iStudentVO.getStdNos()[i]);
					pStudentVO.setIdeUrl(iStudentVO.getIdeUrls()[i]);
					iResult += studentMapper.updateStudentIde(pStudentVO);
					
				}
			}
			
			if(iResult > 0) {
				resultVO.setReturnVO(iStudentVO);
				resultVO.setResultSuccess();
			}
			
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		return resultVO;
	}

	/**
	 * 수강 신청 인증 처리
	 *
	 * @return  ProcessResultVO
	 */
	/*public ProcessResultVO<StudentVO> confirmStudent(StudentVO iStudentVO, HttpServletRequest request)  throws Exception {
		//-- 개설과정 정보 검색
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(iStudentVO.getCrsCreCd());
		createCourseVO = createCourseMapper.selectCreateCourse(createCourseVO);

		//-- 과정 정보 검색
		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(createCourseVO.getCrsCd());
		courseVO = courseMapper.select(courseVO);

		String[] studentList = StringUtil.split(iStudentVO.getStdNo(),"|");
		//List<StudentVO> studentArray = new ArrayList<StudentVO>();

		int[] retrunCnt = new int[studentList.length];
		String returnMsg = "";
		for (int i = 0; i < studentList.length; i++) {
			StudentVO studentVO = new StudentVO();
			studentVO.setCrsCreCd(iStudentVO.getCrsCreCd());
			studentVO.setStdNo(studentList[i]);
			studentVO.setEnrlSts("S");

			String enrlStartDttm = "";
			String enrlEndDttm = "";
			String auditEndDttm = "";

			if("R".equals(courseVO.getCrsOperType())) {
				//---- 정규 과정의 경우 과정의 날짜로 셋팅
				enrlStartDttm = StringUtil.ReplaceAll(createCourseVO.getEnrlStartDttm(),".","")+"000001";
				enrlEndDttm = StringUtil.ReplaceAll(createCourseVO.getEnrlEndDttm(),".","")+"235959";
				auditEndDttm = StringUtil.ReplaceAll(createCourseVO.getAuditEndDttm(),".","")+"235959";
			} else {
				enrlStartDttm = DateTimeUtil.getDate()+"000001";
				enrlEndDttm = DateTimeUtil.afterDate(createCourseVO.getEnrlDaycnt())+"235959";
				auditEndDttm = enrlEndDttm;
			}

			studentVO.setEnrlStartDttm(enrlStartDttm);
			studentVO.setEnrlEndDttm(enrlEndDttm);
			studentVO.setAuditEndDttm(auditEndDttm);
			studentVO.setEnrlDaycnt(createCourseVO.getEnrlDaycnt());
			studentVO.setModNo(UserBroker.getUserNo(request));
			studentVO.setCrsOperType(courseVO.getCrsOperType());
			try {
				int result = studentMapper.confirmStudent(studentVO);
				if(result > 0) {
					retrunCnt[i] = 1;
					//-- 학습자 정보를 가져온다.
					studentVO = studentMapper.selectStudentInfo(studentVO);
					returnMsg +="/"+studentVO.getUserNo();
				} else {
					retrunCnt[i] = 0;
				}
			} catch (Exception ex) {
				retrunCnt[i] = 0;
			}
		}

		ProcessResultVO<StudentVO> processResultVO = new ProcessResultVO<StudentVO>();
		processResultVO.setResultSuccess();
		processResultVO.setRetrunCnt(retrunCnt);
		processResultVO.setMessage(returnMsg);
		return processResultVO;
	}*/

	/**
	 * 수강 신청 취소 처리
	 *
	 * @return  ProcessResultVO
	 */
	/*public ProcessResultVO<StudentVO> cancelStudent(StudentVO iStudentVO, HttpServletRequest request)  throws Exception {
		String[] studentList = StringUtil.split(iStudentVO.getStdNo(),"|");
		List<StudentVO> studentArray = new ArrayList<StudentVO>();

		String returnMsg = "";
		for (int i = 0; i < studentList.length; i++) {
			StudentVO studentVO = new StudentVO();
			studentVO.setCrsCreCd(iStudentVO.getCrsCreCd());
			studentVO.setStdNo(studentList[i]);

			StudentVO sdto = studentMapper.selectStudent(studentVO);
			returnMsg += "/"+sdto.getUserNo();
			//-- 결제완료된 경우
			if("Y".equals(sdto.getPaymStsCd())) {
				//-- 환불관련 상태가 없는 경우 환불요청으로 상태 변경
				if(ValidationUtils.isEmpty(sdto.getRepayStsCd())) {
					studentVO.setRepayStsCd("REFUND001");
				}
			}

			studentVO.setEnrlSts("N");
			studentVO.setModNo(UserBroker.getUserNo(request));
			studentArray.add(studentVO);
		}

		// 변경된 어래이를 일괄 저장.
		studentMapper.cancelStudent(studentArray);

		ProcessResultVO<StudentVO> processResultVO = new ProcessResultVO<StudentVO>();
		processResultVO.setMessage(returnMsg);
		processResultVO.setResultSuccess();
		return processResultVO;

	}*/

	/**
	 * 수강 신청 삭제 처리
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<StudentVO> deleteStudent(StudentVO iStudentVO)  throws Exception {
		String[] studentList = StringUtil.split(iStudentVO.getStdNo(),"|");

		String returnMsg = "";
		for (int i = 0; i < studentList.length; i++) {
			StudentVO studentVO = new StudentVO();
			studentVO.setCrsCreCd(iStudentVO.getCrsCreCd());
			studentVO.setStdNo(studentList[i]);
			studentVO.setEnrlSts("D");

			studentVO = studentMapper.selectStudentInfo(studentVO);
			returnMsg += "/"+studentVO.getUserNo();
			
			studentService.deleteStudentForHrdApi(studentVO);
		}

		//진도율 삭제
		for (int i = 0; i < studentList.length; i++) {
			BookmarkVO bookmarkVO = new BookmarkVO();
			bookmarkVO.setStdNo(studentList[i]);
			bookmarkMapper.deleteBookmarkStdNo(bookmarkVO);
		}

		ProcessResultVO<StudentVO> processResultVO = new ProcessResultVO<StudentVO>();
		processResultVO.setResultSuccess();
		processResultVO.setMessage(returnMsg);
		processResultVO.setResult(1);
		return processResultVO;
	}
	
	/**
	 * 수강 신청 삭제 처리 (개별)
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<StudentVO> deleteCourseStudent(StudentVO iStudentVO)  throws Exception {


		String returnMsg = "";
		StudentVO studentVO = new StudentVO();
		studentVO.setCrsCreCd(iStudentVO.getCrsCreCd());
		studentVO.setStdNo(iStudentVO.getStdNo());
		studentVO.setEnrlSts("D");

		studentService.deleteStudentForHrdApi(studentVO);

		studentVO = studentMapper.selectStudentInfo(studentVO);
		returnMsg += "/"+studentVO.getUserNo();
	

		//진도율 삭제
		BookmarkVO bookmarkVO = new BookmarkVO();
		bookmarkVO.setStdNo(iStudentVO.getStdNo());
		bookmarkMapper.deleteBookmarkStdNo(bookmarkVO);


		ProcessResultVO<StudentVO> processResultVO = new ProcessResultVO<StudentVO>();
		processResultVO.setResultSuccess();
		processResultVO.setMessage(returnMsg);
		processResultVO.setResult(1);
		return processResultVO;
	}

	/**
	 * 수료(미수료) 취소 처리
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<StudentVO> cancelComplete(StudentVO iStudentVO)  throws Exception {
		String[] studentList = StringUtil.split(iStudentVO.getStdNo(),"|");
		List<StudentVO> studentArray = new ArrayList<StudentVO>();

		for (int i = 0; i < studentList.length; i++) {
			StudentVO studentVO = new StudentVO();
			studentVO.setCrsCreCd(iStudentVO.getCrsCreCd());
			studentVO.setStdNo(studentList[i]);
			studentVO.setEnrlSts("S");
			studentArray.add(studentVO);
		}

		// 변경된 어래이를 일괄 저장.
		studentMapper.cancelComplete(studentArray);
		studentMapper.cancelRsltComplete(studentArray);

		return ProcessResultVO.success();
	}

	/**
	 * 사용자 목록 조회 - 수강 신청용
	 *
	 * @return ProcessResultListVO
	 */
	public ProcessResultListVO<StudentVO> listUser(StudentVO iStudentVO)  throws Exception {
		ProcessResultListVO<StudentVO> resultList = new ProcessResultListVO<StudentVO>();
		try {
			List<StudentVO> returnList = studentMapper.listUser(iStudentVO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}		
		return resultList;
	}

	/**
	 * 수강생인지 검색
	 *
	 * @return ProcessResultListVO
	 */
	public ProcessResultVO<StudentVO> isStudent(StudentVO iStudentVO)  throws Exception {
		ProcessResultVO<StudentVO> resultVO = new ProcessResultVO<StudentVO>();
		try {
			StudentVO returnVO = studentMapper.isStudent(iStudentVO);
			resultVO.setReturnVO(returnVO);
			resultVO.setResult(1);
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResult(-1);
			resultVO.setMessage(e.getMessage());
		}		
		return resultVO;
	}

	/**
	 * 수강신청이 있는지 검색
	 *
	 * @return ProcessResultListVO
	 */
	public ProcessResultVO<StudentVO> isEnroll(StudentVO iStudentVO)  throws Exception {
		ProcessResultVO<StudentVO> resultVO = new ProcessResultVO<StudentVO>();
		try {
			StudentVO returnVO = studentMapper.isEnroll(iStudentVO);
			resultVO.setReturnVO(returnVO);
			resultVO.setResult(1);
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResult(-1);
			resultVO.setMessage(e.getMessage());
		}		
		return resultVO;
	}

	/**
	 * 수강신청이 있는지 검색
	 *
	 * @return ProcessResultListVO
	 */
	public ProcessResultVO<StudentVO> enrollCnt(StudentVO iStudentVO)  throws Exception {
		ProcessResultVO<StudentVO> resultVO = new ProcessResultVO<StudentVO>();
		try {
			StudentVO returnVO = studentMapper.enrollCnt(iStudentVO);
			resultVO.setReturnVO(returnVO);
			resultVO.setResult(1);
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResult(-1);
			resultVO.setMessage(e.getMessage());
		}		
		return resultVO;
	}
	
	/**
	 * 수강신청이 있는지 검색
	 *
	 * @return ProcessResultListVO
	 */
	public ProcessResultVO<StudentVO> selectMyStdNo(StudentVO iStudentVO)  throws Exception {
		ProcessResultVO<StudentVO> resultVO = new ProcessResultVO<StudentVO>();
		try {
			StudentVO returnVO = studentMapper.selectMyStdNo(iStudentVO);
			resultVO.setReturnVO(returnVO);
			resultVO.setResult(1);
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResult(-1);
			resultVO.setMessage(e.getMessage());
		}		
		return resultVO;
	}

	/**
	 * 수강생의 학습 정보를 조회한다.
	 *
	 * @return ProcessResultListVO
	 */
	public ProcessResultVO<StudentVO> viewStudentInfo(StudentVO iStudentVO)  throws Exception {
		ProcessResultVO<StudentVO> resultVO = new ProcessResultVO<StudentVO>();
		try {
			StudentVO returnVO = studentMapper.selectStudentInfo(iStudentVO);
			resultVO.setReturnVO(returnVO);
			resultVO.setResult(1);
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResult(-1);
			resultVO.setMessage(e.getMessage());
		}		
		return resultVO;
	}

	/**
	 * 자동 수료 처리
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<StudentVO> autoComplete(StudentVO iStudentVO) throws Exception {
		List<StudentVO> studentList = studentMapper.listStudent(iStudentVO);
		String returnMsg = "";
		for(StudentVO sdto : studentList) {
			String userNo = completeProcess(iStudentVO.getCrsCreCd(), sdto.getStdNo(), iStudentVO.getRegNo());
			if(!"".equals(userNo)) {
				returnMsg += "/"+userNo;
			}
		}
		ProcessResultVO<StudentVO> processResultVO = new ProcessResultVO<StudentVO>();
		processResultVO.setResult(1);
		processResultVO.setMessage(returnMsg);
		return processResultVO;
	}

	/**
	 * 선택 수료 처리
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<StudentVO> checkComplete(StudentVO iStudentVO) throws Exception {
		String[] studentList = StringUtil.split(iStudentVO.getStdNo(),"|");
		//List<StudentVO> studentArray = new ArrayList<StudentVO>();
		String returnMsg = "";
		for (int i = 0; i < studentList.length; i++) {
			String userNo = completeProcess(iStudentVO.getCrsCreCd(), studentList[i], iStudentVO.getRegNo());
			if(!"".equals(userNo)) {
				returnMsg += "/"+userNo;
			}
		}
		ProcessResultVO<StudentVO> processResultVO = new ProcessResultVO<StudentVO>();
		processResultVO.setResult(1);
		processResultVO.setMessage(returnMsg);
		return processResultVO;
	}

	private String completeProcess(String crsCreCd, String stdNo, String regNo) throws Exception {
		String retVal = "";
		//-- 과정 정보를 가져온다.
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(crsCreCd);
		createCourseVO = createCourseMapper.selectCreateCourse(createCourseVO);

		Integer cpltScore = createCourseVO.getCpltScore();
		Integer  creYear = createCourseVO.getCreYear();
		Integer  creTerm = createCourseVO.getCreTerm();
		String  crsCd = createCourseVO.getCrsCd();
		String  crsOperType = createCourseVO.getCrsOperType();

		//-- 학습자의 정보를 가져온다.
		StudentVO studentVO = new StudentVO();
		studentVO.setStdNo(stdNo);
		studentVO = studentMapper.selectStudentInfo(studentVO);
		String  enrlSts = studentVO.getEnrlSts();

		//-- 학습자의 결과 정보를 가져온다.
		EduResultVO eduResultVO = new EduResultVO();
		eduResultVO.setStdNo(stdNo);
		Double totalScore = 0.0;
		try {
			eduResultVO = eduResultMapper.selectResult(eduResultVO);
			totalScore = eduResultVO.getTotScore();
		} catch(Exception e) {
			totalScore = 0.0;
		}

		//-- 취득 점수가 수료점수보다 높을 경우 처리
		if(totalScore >= cpltScore) {
			//-- 수료처리가 되지 않은 사용자만.
			if(!"C".equals(enrlSts)) {
				String maxCompleteNo = studentMapper.getCompleteNo(studentVO).getCpltNo();
				String completeNo = crsCd+"-"+creYear+"-"+creTerm+"-"+maxCompleteNo;
				studentVO.setCpltNo(completeNo);
				studentMapper.completeSuccess(studentVO);
				retVal = studentVO.getUserNo();
			}
		} else {
			String crsEndYn = "N";
			String startDate = "";
			String endDate = "";
			String currentDate = DateTimeUtil.getCurrentString("yyyyMMdd");
			if("R".equals(crsOperType)) {
				startDate = StringUtil.ReplaceAll(createCourseVO.getEnrlStartDttm(),".","");
				endDate = StringUtil.ReplaceAll(createCourseVO.getEnrlEndDttm(),".","");
			} else {
				startDate = StringUtil.substring(studentVO.getEnrlStartDttm(),0,8);
				endDate = StringUtil.substring(studentVO.getEnrlEndDttm(),0,8);
			}
			//-- 수강일이 종료된 경우 미수료 처리
			if(Integer.parseInt(currentDate) > Integer.parseInt(endDate)) {
				studentMapper.completeFailed(studentVO);
			}
		}

		return retVal;
	}

	/**
	 * 수강 결과 등록
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<StudentVO> addEduNoAll(StudentVO iStudentVO, String strStdNo, String strEduNo) throws Exception {

		String[] stdNo = StringUtil.split(strStdNo, "|");
		String[] eduNo = StringUtil.split(strEduNo, "|");

		List<StudentVO> studentArray = new ArrayList<StudentVO>();

		for(int i=0; i < stdNo.length; i++) {
			StudentVO studentVO = new StudentVO();
			studentVO.setCrsCreCd(iStudentVO.getCrsCreCd());
			studentVO.setStdNo(stdNo[i]);
			studentVO.setEduNo(Integer.parseInt(eduNo[i]));
			studentArray.add(studentVO);
		}
		studentMapper.updateEduNo(studentArray);
		return ProcessResultVO.success();
	}

	/**
	 * 회원정보를 검사한 뒤 있을 경우 update 없을 경우 insert를 수행한다.<br>
	 * 학생정보를 검사한 뒤 수강신청 상태가 아닌 경우 Insert를 수행한다.
	 * @param studentVO
	 * @param newUserDto
	 * @throws Exception 
	 * @throws DataAccessException 
	 */
	@Override
	public void saveUserStudent(StudentVO studentVO, UsrUserInfoVO userInfoVO) throws DataAccessException, Exception {
		//Assert.notNull(userVO, "조회대상 사용자 정보가 없습니다.");
		//Assert.hasLength(userVO.getGpinDupCfrmInfo(), "조회대상 사용자 정보의 G-PIN 중복가입 확인 정보가 없습니다.");

		// 회원존재여부 검사
		// 이미 가입했던 적이 있는 사용자라면...
//G_PIN 부분 주석 처리 dongwoo
//		if (userDao.isExistUserByGpinDup(userVO)) {
//		// G-PIN정보로 사용자 조회
//			UserVO destUserVO = new UserVO();
//			destUserVO.setGpinDupCfrmInfo(userVO.getGpinDupCfrmInfo());
//		destUserVO = userDao.selectUserByGpinDupCfrmInfo(destUserVO, false).getReturnDto();
//
//			// 기존 사용자와 이름이 동일한지 확인.(주민번호가 같고 이름이 다를 경우 확인이 필요함)
//			//if(!destUserVO.getUserNm().equals(userVO.getUserNm())) {
//			//	throw new ValidateStudentException(
//			//			"이미 등록되어있는 사용자 이름["+destUserVO.getUserNm()+"]과 동일하지 않습니다.");
//			//}
//
//			BeanUtils.mergeBean(destUserVO, userVO); // 기존 정보에 사용자 입력정보를 갱신
//			destUserVO.setSecedeDttm(null); // 탈퇴일자 정보 삭제
//			//userVO.setUserNo(destUserVO.getUserNo()); // 사용자 번호를 받아 userVO 에 셋팅함.
//
//			// 저장하고 처리 결과를 userVO에 바인딩
//			//TO-DO : 회원관리 완성시 다시 활성화
//			//userVO = userDao.updateUser(destUserVO).getReturnDto();
//		} else {
//			//TO-DO : 회원관리 완성시 다시 활성화
//			//userVO = userDao.insertUser(userVO).getReturnDto();
//		}

		StudentVO newStudentVO = new StudentVO();
		newStudentVO.setCrsCreCd(studentVO.getCrsCreCd());
		newStudentVO.setUserNo(userInfoVO.getUserNo());


		// -- 학습자 (수강신청 상태가 아닌경우 Insert)
		if ("N".equals(studentMapper.isEnroll(newStudentVO).getStdYn())) {

			try {
				BeanUtils.copyProperties(newStudentVO, userInfoVO);
			} catch (Exception ex) {
				throw new ValidateStudentException("학생정보 추출과정에서 오류 발생..");
			}

			// -- 개설과정의 정보를 가져온다.
			CreateCourseVO createCourseVO = new CreateCourseVO();
			createCourseVO.setCrsCreCd(studentVO.getCrsCreCd());
			createCourseVO = createCourseMapper.selectCreateCourse(createCourseVO);

			newStudentVO.setCrsCreCd(studentVO.getCrsCreCd());
			//newStudentVO.setSchlTypeCd(studentVO.getSchlTypeCd());
			//newStudentVO.setTrainingNomiNo(studentVO.getTrainingNomiNo());
			newStudentVO.setEnrlStartDttm(StringUtil.ReplaceAll(createCourseVO.getEnrlStartDttm(),".","")+"000001");
			newStudentVO.setEnrlEndDttm(StringUtil.ReplaceAll(createCourseVO.getEnrlEndDttm(),".","")+"235959");
			newStudentVO.setAuditEndDttm(StringUtil.ReplaceAll(StringUtil.nvl(createCourseVO.getAuditEndDttm(),createCourseVO.getEnrlEndDttm()),".","")+"235959");
			newStudentVO.setEduNo((studentMapper.enrollCnt(newStudentVO).getEnrlCnt())+1);
			/*
			if(createCourseVO.getStayYn().equals("Y")){
				newStudentVO.setStayAplcYn(studentVO.getStayAplcYn());
				if(studentVO.getStayAplcYn().equals("Y")){
					newStudentVO.setStayAplcYn2(studentVO.getStayAplcYn2());
				}
			}
			*/
			newStudentVO.setEnrlSts("S");
			newStudentVO.setRegNo(studentVO.getRegNo());
			if("".equals(StringUtil.nvl(newStudentVO.getStdNo()))) {
				newStudentVO.setStdNo(studentMapper.selectKey());
			}

			studentMapper.addMergeStudent(newStudentVO);
		} else {
			throw new ValidateStudentException("이미 등록되어 있는 교육생입니다.");
		}
	}


	/**
	 * 수강생의 분반 정보 변경
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<StudentVO> editDecls(String userList, Integer declsNo)  throws Exception {
		String[] userArray = StringUtil.split(userList, ",");
		for(int i=0; i < userArray.length; i++) {
			StudentVO dto = new StudentVO();
			dto.setStdNo(userArray[i]);
			dto.setDeclsNo(declsNo);
			studentMapper.updateDecls(dto);
		}
		return ProcessResultVO.success();
	}

	/**
	 * 트랜잭션 테스트용 매소드(테스트에서만 사용된다.)
	 */
	@Deprecated
	public ProcessResultVO<StudentVO> transactionRollbackMethod(StudentVO iStudentVO) throws Exception {
		this.addStudent(iStudentVO);
		throw new Exception("트랜잭션 테스트를 위한 강제 Exception");
	}

	/**
	 * 성적우수자 선정/해제
	 * @author twkim
	 * @date 2013. 10. 30.
	 * @param iStudentVO
	 * @return ProcessResultVO<StudentVO>
	 */
	@Override
	public ProcessResultVO<StudentVO> editScoreEclt(StudentVO iStudentVO)  throws Exception {
		ProcessResultVO<StudentVO> resultVO = new ProcessResultVO<StudentVO>();
		try {
			studentMapper.updateScoreEclt(iStudentVO);
			resultVO.setReturnVO(iStudentVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}		
		return resultVO;
	}

	/**
	 * 성적우수자 선정/해제 Batch
	 * @param iStudentVO
	 * @return ProcessResultVO<StudentVO>
	 */
	@Override
	public ProcessResultVO<StudentVO> editScoreEcltBatch(String crsCreCd, String targetList, String studentList)  throws Exception {
		//-- 입력된 사용자의 성적우수자 선정을 해지 한다.
		String[] studentArray = StringUtil.split(studentList, ",");
		for(int i=0; i < studentArray.length; i++) {
			StudentVO studentVO = new StudentVO();
			studentVO.setCrsCreCd(crsCreCd);
			studentVO.setStdNo(studentArray[i]);
			studentVO.setScoreEcltYn("N");
			studentMapper.updateScoreEclt(studentVO);
		}

		//-- 성적우수자 선정
		String[] targetArray = StringUtil.split(targetList, ",");
		for(int i=0; i < targetArray.length; i++) {
			StudentVO studentVO = new StudentVO();
			studentVO.setCrsCreCd(crsCreCd);
			studentVO.setStdNo(targetArray[i]);
			studentVO.setScoreEcltYn("Y");
			studentMapper.updateScoreEclt(studentVO);
		}
		return new ProcessResultVO<StudentVO>().setResultSuccess();
	}

	/**
	 * 수강생의 비밀번호 ID로 변경
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<StudentVO> resetPassStudent(StudentVO iStudentVO, HttpServletRequest request)  throws Exception {
		String[] studentList = StringUtil.split(iStudentVO.getStdNo(),"|");
		List<StudentVO> studentArray = new ArrayList<StudentVO>();

		for (int i = 0; i < studentList.length; i++) {
			StudentVO studentVO = new StudentVO();
			studentVO.setStdNo(studentList[i]);
			studentVO = studentMapper.selectStudent(studentVO);

			UsrLoginVO loginVO = new UsrLoginVO();
			loginVO.setUserNo(studentVO.getUserNo());
			loginVO.setUserPass(studentVO.getUserId());
			loginVO.setModNo(iStudentVO.getModNo());

			usrLoginMapper.updatePswd(loginVO);
		}
		return ProcessResultVO.success();
	}



	/**
	 * 수강생 입금 확인 일괄처리
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<StudentVO> confirmDepositStudent(StudentVO iStudentVO, HttpServletRequest request)  throws Exception {
		//-- 개설과정 정보 검색
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(iStudentVO.getCrsCreCd());
		createCourseVO = createCourseMapper.selectCreateCourse(createCourseVO);

		//-- 과정 정보 검색
		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(createCourseVO.getCrsCd());
		courseVO = courseMapper.select(courseVO);

		String[] studentList = StringUtil.split(iStudentVO.getStdNo(),"|");
		List<StudentVO> studentArray = new ArrayList<StudentVO>();


		for (int i = 0; i < studentList.length; i++) {

			StudentVO studentVO = new StudentVO();
			studentVO.setCrsCreCd(iStudentVO.getCrsCreCd());
			studentVO.setStdNo(studentList[i]);
			studentVO.setPaymStsCd("Y");

			String paymDttm = StringUtil.ReplaceAll(createCourseVO.getEnrlEndDttm(),".","")+"235959";

			Integer paymPrice = createCourseVO.getEduPrice();

			studentVO.setPaymDttm(paymDttm);
			studentVO.setPaymPrice(paymPrice);
			studentArray.add(studentVO);

		}

		// 수강생 입금확인을 일괄 저장.
		studentMapper.confirmDepositStudent(studentArray);

		return ProcessResultVO.success();
	}


	/**
	 * 과정 전체 진도율 정보 조회
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<StudentVO> viewCrsRatioInfo(StudentVO iStudentVO)  throws Exception {
		ProcessResultVO<StudentVO> resultVO = new ProcessResultVO<StudentVO>();
		try {
			StudentVO returnVO = studentMapper.selectCrsRatio(iStudentVO);
			resultVO.setReturnVO(returnVO);
			resultVO.setResult(1);
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResult(-1);
			resultVO.setMessage(e.getMessage());
		}		
		return resultVO;
	}

	@Override
	public ProcessResultListVO<StudentVO> listStudentPageingForMng(StudentVO vo) throws Exception {
		ProcessResultListVO<StudentVO> resultList = new ProcessResultListVO<>();
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(vo.getCurPage());
		paginationInfo.setRecordCountPerPage(vo.getListScale());
		paginationInfo.setPageSize(vo.getPageScale());
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		
		// 전체 목록 수
		int totalCount = studentMapper.countForStdManage(vo);
		paginationInfo.setTotalRecordCount(totalCount);			
		
		List<StudentVO> stdList = studentMapper.listStudentPageingForStdManage(vo);
		resultList.setReturnList(stdList);
		resultList.setPageInfo(paginationInfo);
		return resultList;
	}
	
	@Override
	public List<StudentVO> listStudentForMng(StudentVO vo) throws Exception {
		return studentMapper.listStudentForStdManage(vo);
	}

	/**
	 * [HRD] 회원번호로 수강생의 결제내역 리스트 조회(수강생 기준)
	 */
	@Override
	public List<StudentVO> listStudentPaymentInfoByUserNo(StudentVO iStudentVO) {
		return studentMapper.listStudentPaymentInfoByUserNo(iStudentVO);
	}
	
	/**
	 * [HRD] 수강생 번호로 수강생의 결제내역 단건 조회(수강생 기준)
	 */
	@Override
	public StudentVO viewStudentPaymentInfoByStdNo(StudentVO iStudentVO) {
		return studentMapper.selectStudentPaymentInfoByStdNo(iStudentVO);
	}

	/**
	 * [HRD] 회원번호, 개설과정코드로 대기중인 수강생 조회(수강생 기준)
	 * @param iStudentVO
	 * @return
	 */
	@Override
	public ProcessResultVO<StudentVO> selectStudentEnrollBefore(StudentVO iStudentVO){
		return new ProcessResultVO<StudentVO>().setReturnVO(studentMapper.selectStudentEnrollBefore(iStudentVO));
	}

	/**
	 * [HRD] 관리자>수강신청관리 - 리스트 조회
	 */
	@Override
	public ProcessResultListVO<StudentVO> listStudentPaymentInfoManage(StudentVO iStudentVO) {
		return new ProcessResultListVO<StudentVO>(studentMapper.listStudentPaymentInfoManage(iStudentVO));
	}
	
	/**
	 * [HRD] 관리자>수강신청관리 - 리스트 조회
	 */
	@Override
	public ProcessResultListVO<StudentVO> listIdeManage(StudentVO iStudentVO) {
		return new ProcessResultListVO<StudentVO>(studentMapper.listIdeManage(iStudentVO));
	}
	
	/**
	 * [HRD] 관리자>수강신청관리 - 리스트 페이징 조회
	 */
	@Override
	public ProcessResultListVO<StudentVO> listStudentPaymentInfoManagePageing(StudentVO iStudentVO) {
		ProcessResultListVO<StudentVO> resultList = new ProcessResultListVO<StudentVO>();
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(iStudentVO.getCurPage());
		paginationInfo.setRecordCountPerPage(iStudentVO.getListScale());
		paginationInfo.setPageSize(iStudentVO.getPageScale());
		
		iStudentVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		iStudentVO.setLastIndex(paginationInfo.getLastRecordIndex());
		
		//현황용 카운트 조회
		StudentVO statusCntVO = studentMapper.listStudentPaymentInfoManagePageingCount(iStudentVO);
		resultList.setReturnVO(statusCntVO);
		
		// 전체 목록 수
		//int totalCount = studentMapper.count(iStudentVO);
		paginationInfo.setTotalRecordCount(statusCntVO.getStuCnt());//전체 카운트는 이전에서 조회해온다.
		
		resultList.setReturnList(studentMapper.listStudentPaymentInfoManagePageing(iStudentVO));
		resultList.setPageInfo(paginationInfo);
		
		return resultList;
	}
	
	/**
	 * [HRD] 결제내역 - 수강취소
	 * @param iStudentVO
	 * @return
	 */
	@Override
	public ProcessResultVO<StudentVO> cancelStudentEnrlStsE(StudentVO iStudentVO) {

		if(ValidationUtils.isEmpty(iStudentVO.getUserNo()) || ValidationUtils.isEmpty(iStudentVO.getCrsCreCd()) || ValidationUtils.isEmpty(iStudentVO.getUserNo())) {
			throw new MediopiaDefineException("해당 과정은 수강 취소할 수 없습니다. 새로고침 후 다시 이용바랍니다.");
		}
		
		StudentVO sdto = studentMapper.selectStudentEnrollBefore(iStudentVO);
		
		if(sdto == null) {
			throw new MediopiaDefineException("해당 과정은 수강 취소할 수 없습니다. 새로고침 후 다시 이용바랍니다.");
		}else if(ValidationUtils.isEmpty(sdto.getStdNo())) {
			throw new MediopiaDefineException("해당 과정은 수강 취소할 수 없습니다. 새로고침 후 다시 이용바랍니다.");
		}
		
		sdto.setEnrlSts("N");
		sdto.setModNo(iStudentVO.getModNo());
		
		// 변경된 어래이를 일괄 저장.
		try {
			studentService.updateStudentEnrlStsForHrdApi(sdto);
		} catch (Exception e) {
			throw new MediopiaDefineException("수강 취소 중 오류가 발생하였습니다. 새로고침 후 다시 이용바랍니다.");
		}

		ProcessResultVO<StudentVO> processResultVO = new ProcessResultVO<StudentVO>();
		processResultVO.setResultSuccess();
		return processResultVO;

	}

	/**
	 * [HRD] 관리자>수강신청관리 - 수강생 상세보기
	 */
	@Override
	public ProcessResultVO<StudentVO> viewStudentPaymentInfoManage(StudentVO vo) {
		return new ProcessResultVO<StudentVO>(studentMapper.selectStudentPaymentInfoManage(vo));
	}
	
	/**
	 * 수강 신청 인증 처리
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<StudentVO> confirmStudent(StudentVO iStudentVO, HttpServletRequest request)  throws Exception {
		//-- 개설과정 정보 검색
		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(iStudentVO.getCrsCreCd());
		createCourseVO = createCourseMapper.selectCreateCourse(createCourseVO);

		//-- 과정 정보 검색
		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(createCourseVO.getCrsCd());
		courseVO = courseMapper.select(courseVO);

		String[] studentList = StringUtil.split(iStudentVO.getStdNo(),"|");
		//List<StudentVO> studentArray = new ArrayList<StudentVO>();

		int[] retrunCnt = new int[studentList.length];
		String returnMsg = "";
		for (int i = 0; i < studentList.length; i++) {
			StudentVO studentVO = new StudentVO();
			studentVO.setCrsCreCd(iStudentVO.getCrsCreCd());
			studentVO.setStdNo(studentList[i]);
			studentVO.setEnrlSts("S");

			String enrlStartDttm = "";
			String enrlEndDttm = "";
			String auditEndDttm = "";

			if("R".equals(courseVO.getCrsOperType())) {
				//---- 정규 과정의 경우 과정의 날짜로 셋팅
				enrlStartDttm = StringUtil.ReplaceAll(createCourseVO.getEnrlStartDttm(),".","")+"000001";
				enrlEndDttm = StringUtil.ReplaceAll(createCourseVO.getEnrlEndDttm(),".","")+"235959";
				auditEndDttm = StringUtil.ReplaceAll(createCourseVO.getAuditEndDttm(),".","")+"235959";
			} else {
				enrlStartDttm = DateTimeUtil.getDate()+"000001";
				enrlEndDttm = DateTimeUtil.afterDate(createCourseVO.getEnrlDaycnt())+"235959";
				auditEndDttm = enrlEndDttm;
			}

			studentVO.setEnrlStartDttm(enrlStartDttm);
			studentVO.setEnrlEndDttm(enrlEndDttm);
			studentVO.setAuditEndDttm(auditEndDttm);
			studentVO.setEnrlDaycnt(createCourseVO.getEnrlDaycnt());
			studentVO.setModNo(UserBroker.getUserNo(request));
			studentVO.setCrsOperType(courseVO.getCrsOperType());
			try {
				int result = studentMapper.confirmStudent(studentVO);
				if(result > 0) {
					retrunCnt[i] = 1;
					//-- 학습자 정보를 가져온다.
					studentVO = studentMapper.selectStudentInfo(studentVO);
					returnMsg +="/"+studentVO.getUserNo();
				} else {
					retrunCnt[i] = 0;
				}
			} catch (Exception ex) {
				retrunCnt[i] = 0;
			}
		}

		ProcessResultVO<StudentVO> processResultVO = new ProcessResultVO<StudentVO>();
		processResultVO.setResultSuccess();
		processResultVO.setRetrunCnt(retrunCnt);
		processResultVO.setMessage(returnMsg);
		return processResultVO;
	}

	/**
	 * 수강 신청 취소 처리
	 *
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<StudentVO> cancelStudent(StudentVO iStudentVO, HttpServletRequest request)  throws Exception {
		String[] studentList = StringUtil.split(iStudentVO.getStdNo(),"|");

		String returnMsg = "";
		for (int i = 0; i < studentList.length; i++) {
			StudentVO studentVO = new StudentVO();
			studentVO.setCrsCreCd(iStudentVO.getCrsCreCd());
			studentVO.setStdNo(studentList[i]);

			StudentVO sdto = studentMapper.selectStudent(studentVO);
			returnMsg += "/"+sdto.getUserNo();
			//-- 결제완료된 경우
			if("Y".equals(sdto.getPaymStsCd())) {
				//-- 환불관련 상태가 없는 경우 환불요청으로 상태 변경
				if(ValidationUtils.isEmpty(sdto.getRepayStsCd())) {
					studentVO.setRepayStsCd("REFUND001");
				}
			}
			studentVO.setEnrlSts("N");
			studentVO.setModNo(UserBroker.getUserNo(request));
			studentService.updateStudentEnrlStsForHrdApi(studentVO);
		}

		// 변경된 어래이를 일괄 저장.

		ProcessResultVO<StudentVO> processResultVO = new ProcessResultVO<StudentVO>();
		processResultVO.setMessage(returnMsg);
		processResultVO.setResultSuccess();
		return processResultVO;

	}

	/**
	 * [HRD] 관리자>수강신청관리>승인
	 */
	@Override
	@Transactional
	public ProcessResultVO<StudentVO> confirmStudentPayment(StudentVO vo) {
		String[] studentList = StringUtil.split(vo.getStdNo(),"|");
		
		String resultMessage = "";
		
		for (int i = 0; i < studentList.length; i++) {
			StudentVO studentVO = new StudentVO();
			studentVO.setStdNo(studentList[i]);
			StudentVO sdto = studentMapper.selectStudent(studentVO);
			
			if(sdto == null) {
				throw new ServiceProcessException("수강생번호 : " + studentList[i] + "\n 조회되지 않습니다. 회원, 수강생 정보를 확인바랍니다.");
			}
			
			//기수 종료일이 지난 후에는 승인 불가
			
			//승인 가능 : 수강대기(E)
			/*if(!"E".equals(sdto.getEnrlSts()) && !"N".equals(sdto.getEnrlSts())) {
				throw new ServiceProcessException("수강생번호 : " + studentList[i] + ", 회원명 : " + sdto.getUserNm() + "\n승인 가능한 상태가 아닙니다.\n승인가능상태:결제대기,결제취소\n승인불가상태:결제완료(수강중,수료취소,수료)");
			}*/
			if(!"E".equals(sdto.getEnrlSts())) {
				throw new ServiceProcessException("수강생번호 : " + studentList[i] + ", 회원명 : " + sdto.getUserNm() + "\n승인 가능한 상태가 아닙니다.\n승인가능상태:결제대기\n승인불가상태:결제완료(수강중,수료취소,수료),결제취소(환불)");
			}
			
			//수강중인 강의가 있는 경우 승인 불가
			//if("Y".equals(studentMapper.isStudent(sdto).getStdYn())) {
			if("Y".equals(studentMapper.isStudentByUserNo(sdto).getStdYn())) {
				throw new ServiceProcessException("수강생번호 : " + studentList[i] + ", 회원명 : " + sdto.getUserNm() + "\n 수강생은 해당 강의 수강중입니다.\n이상이 없는 경우 회원의 같은 개설 과정을 여러 개 체크한 건 아닌지 확인바랍니다.");
			}
			
			StudentVO paramVO = new StudentVO();
			paramVO.setModNo(vo.getModNo());
			paramVO.setEnrlSts("S");
			paramVO.setStdNo(sdto.getStdNo());
			
			studentService.updateStudentEnrlStsForHrdApi(paramVO);
			
			resultMessage += "수강생번호 : " + sdto.getStdNo() + ", 회원명 : " + sdto.getUserNm() + "\n";
			
		}
		if(!"".equals(resultMessage)) {
			egovLogger.info("[관리자 수강승인]\n" + resultMessage);
		}
		return new ProcessResultVO<StudentVO>(ProcessResultVO.RESULT_SUCC, resultMessage + "승인하였습니다.");
	}

	/**
	 * [HRD] 관리자>수강신청관리>취소
	 */
	@Override
	@Transactional
	public ProcessResultVO<StudentVO> cancelStudentPayment(StudentVO vo) {
		String[] studentList = StringUtil.split(vo.getStdNo(),"|");
		
		String resultMessage = "";
		
		for (int i = 0; i < studentList.length; i++) {
			StudentVO studentVO = new StudentVO();
			studentVO.setStdNo(studentList[i]);
			StudentVO sdto = studentMapper.selectStudent(studentVO);
			
			if(sdto == null) {
				throw new ServiceProcessException("수강생번호 : " + studentList[i] + "\n 조회되지 않습니다. 회원, 수강생 정보를 확인바랍니다.");
			}
			
			//취소가능 : 수강중(S)
			//수료(C), 수료취소(F)는 이미 수강이 진행되었다고 판단하여 불가
			if(!"S".equals(sdto.getEnrlSts()) && !"E".equals(sdto.getEnrlSts())) {//취소가능:수강중,결제대기   //수료, 수료취소는 불가
				throw new ServiceProcessException("수강생번호 : " + studentList[i] + ", 회원명 : " + sdto.getUserNm() + "\n취소 가능한 상태가 아닙니다.\n취소가능상태:결제완료(수강중),결제대기\n취소불가상태:결제완료(수료취소,수료),결제취소");
			}
			
			StudentVO paramVO = new StudentVO();
			paramVO.setModNo(vo.getModNo());
			paramVO.setEnrlSts("N");
			paramVO.setStdNo(sdto.getStdNo());
			paramVO.setRepayPrice(vo.getRepayPrice());//취소 처리 시 환불 금액 = 수강생 결제 금액
			paramVO.setRepayMemo(vo.getRepayMemo());
			
			studentService.updateStudentEnrlStsForHrdApi(paramVO);
			resultMessage += "수강생번호 : " + sdto.getStdNo() + ", 회원명 : " + sdto.getUserNm() + "\n";
		}
		if(!"".equals(resultMessage)) {
			egovLogger.info("[관리자 수강취소]\n" + resultMessage);
		}
		return new ProcessResultVO<StudentVO>(ProcessResultVO.RESULT_SUCC, resultMessage + "취소하였습니다.");
	}

	/**
	 * [HRD] 관리자>수강신청관리>삭제
	 */
	@Override
	public ProcessResultVO<StudentVO> deleteStudentPayment(StudentVO vo) {
		String[] studentList = StringUtil.split(vo.getStdNo(),"|");
		
		ProcessResultVO<StudentVO> processResultVO = new ProcessResultVO<>(new StudentVO());
		
		List<String> stdNoList = new ArrayList<>();
		String resultMessage = "";
		
		for (int i = 0; i < studentList.length; i++) {
			StudentVO studentVO = new StudentVO();
			studentVO.setStdNo(studentList[i]);
			
			StudentVO sdto = studentVO = studentMapper.selectStudent(studentVO);
			if(sdto == null) {
				throw new ServiceProcessException("수강생번호 : " + studentList[i] + "\n 조회되지 않습니다. 회원, 수강생 정보를 확인바랍니다.");
			}
			
			if(!"N".equals(sdto.getEnrlSts()) && !"E".equals(sdto.getEnrlSts())) {
				throw new ServiceProcessException("수강생번호 : " + studentList[i] + ", 회원명 : " + sdto.getUserNm() + "\n삭제 가능한 상태가 아닙니다.\n삭제가능상태:결제취소,결제대기");
			}
			
			StudentVO paramVO = new StudentVO();
			paramVO.setModNo(vo.getModNo());
			paramVO.setStdNo(sdto.getStdNo());
			paramVO.setCrsCreCd(sdto.getCrsCreCd());
			paramVO.setEnrlSts("D");
			stdNoList.add(sdto.getStdNo());
			resultMessage += "수강생번호 : " + sdto.getStdNo() + ", 회원명 : " + sdto.getUserNm() + "\n";
			
			try {
				studentService.deleteStudentForHrdApi(paramVO);
			} catch (Exception e) {
				throw new ServiceProcessException("수강생 삭제 중 오류가 발생하였습니다.");
			}
		}
		
		processResultVO.getReturnVO().setStdNoList(stdNoList);
		processResultVO.setResultSuccess();
		processResultVO.setMessage(resultMessage + "수강생 삭제 성공하였습니다.");
		
		return processResultVO;
	}
	
	@Override
	public List<StudentVO> listStudentForAttend(StudentVO vo) throws Exception {
		List<StudentVO> stdList = studentMapper.listStudentForStdManage(vo);
		stdList.parallelStream()
				.peek(std->std.setBookmarkList(studentMapper.stdBookmarkListForAttend(std)))
				.forEach(StudentVO::calcAttendCnt);
		
		return stdList;
	}
	
	@Override
	public StudentVO viewStudentForAttend(StudentVO vo) throws Exception {
		StudentVO student = studentMapper.selectStdForAttend(vo);
		if(student == null) throw new Exception("해당하는 수강생이 없습니다.");
		student.setBookmarkList(studentMapper.stdBookmarkListForAttend(student));
		student.calcAttendCnt();
		return student;
	}
	
	@Override
	@HrdApiStdStd(SyncType.CREATE)
	public int addStudentForHrdApi(StudentVO vo) {
		return studentMapper.insertStudent(vo);
	}

	
	@Override
	@HrdApiStdStd(SyncType.UPDATE)
	public int addStudentIdeUrl(StudentVO vo) {
		int result = 0;
		result = studentMapper.updateStudentIdeUrl(vo);

		return result;
	}
	
	@Override
	@HrdApiStdStd(SyncType.DELETE)
	public int deleteStudentForHrdApi(StudentVO vo) {
		return studentMapper.deleteStudentOne(vo);
	}
	
	@Override
	@HrdApiStdStd(SyncType.UPDATE)
	public int updateStudentEnrlStsForHrdApi(StudentVO vo) {
		String enrlSts = StringUtil.nvl(vo.getEnrlSts());
		int result = 0;
		if("S".equals(enrlSts)) {//수강승인
			result = studentMapper.updateStudentEnrlStsConfirm(vo);
		}else if("N".equals(enrlSts)) {//수강취소
			result = studentMapper.updateStudentEnrlStsCancel(vo);
		}else {//승인, 취소 외 오류
			//result = studentMapper.updateStudentEnrlSts(vo);
		}
		return result;
	}

	/**
	 * 환불 신청(수강생)
	 */
	@Override
	@HrdApiStdStd(SyncType.UPDATE)
	@RefundHistory(value = RefundType.REQUEST)
	public int updateAddRefund(StudentVO vo) {
		return studentMapper.updateAddRefund(vo);
	}

	/**
	 * 환불 완료(관리자 처리)
	 */
	@Override
	@HrdApiStdStd(SyncType.UPDATE)
	@RefundHistory(value = RefundType.ADMIN_COMPLETE)
	public int updateRefundComplete(StudentVO vo) {
		return studentMapper.updateRefundComplete(vo);
	}
	
	/**
	 * 환불 취소(관리자 처리) 
	 */
	@Override
	@HrdApiStdStd(SyncType.UPDATE)
	@RefundHistory(value = RefundType.CANCEL)
	public int updateRefundCancel(StudentVO vo) {
		return studentMapper.updateRefundCancel(vo);
	}

	/**
	 * 환불 메모 수정(관리자)
	 */
	@Override
	@RefundHistory(value = RefundType.MEMO)
	public int updateRefundMemo(StudentVO vo) {
		return studentMapper.updateRefundMemo(vo);
	}
	
	/**
	 * [HRD] 수강생 취소 - 이니시스 환불 처리 후 하기 때문에 조건 체크는 이전에 해야 한다. update만 진행
	 * 
	 */
	@Override
	@Transactional
	@RefundHistory(value = RefundType.USER_COMPLETE)
	public int cancelStudentPaymentByStudent(StudentVO vo) {
		return studentService.updateStudentEnrlStsForHrdApi(vo);
	}

	/**
	 * 환불 내역 리스트 조회
	 */
	@Override
	public List<StudentVO> listStdPayRefundHsty(StudentVO vo) {
		return studentService.listStdPayRefundHsty(vo);
	}

	/**
	 * 환불 내역 리스트 페이징 조회
	 */
	@Override
	public ProcessResultListVO<StudentVO> listStdPayRefundHstyPaging(StudentVO vo) {
		ProcessResultListVO<StudentVO> resultList = new ProcessResultListVO<>();
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(vo.getCurPage());
		paginationInfo.setRecordCountPerPage(vo.getListScale());
		paginationInfo.setPageSize(vo.getPageScale());
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		paginationInfo.setTotalRecordCount(studentMapper.listStdPayRefundHstyCount(vo));
		
		resultList.setReturnList(studentMapper.listStdPayRefundHstyPaging(vo));
		resultList.setPageInfo(paginationInfo);
		
		return resultList;
	}
	
	/**
	 * 자격증 과정 신청자 목록
	 * */
	@Override
	public List<StudentVO> listStdCertCourse(CourseVO vo) throws Exception {
		List<StudentVO> stdList = studentMapper.listStdCertCourse(vo);
		return stdList;
	}
	

	/**
	 * 자격증 과정 신청 정보(개인)
	 * */
	@Override
	public StudentVO stdCertCourse(StudentVO vo) throws Exception {
		StudentVO stdVO = studentMapper.stdCertCourse(vo);
		return stdVO;
	}
	
	
	
	/**
	 * 자격증 신청(수강생)
	 * */
	public ProcessResultVO<StudentVO> certAplcStudent(StudentVO iStudentVO)  throws Exception {
		ProcessResultVO<StudentVO> resultVO = new ProcessResultVO<StudentVO>();
		try {
			studentMapper.updateCertStudent(iStudentVO);
			resultVO.setReturnVO(iStudentVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}		
		return resultVO;
	}
	
	/**
	 * 자격증 승인(관리자)
	 * */
	public ProcessResultVO<StudentVO> certAplcStudentByAdmin(StudentVO iStudentVO)  throws Exception {
		ProcessResultVO<StudentVO> resultVO = new ProcessResultVO<StudentVO>();
		try {
			studentMapper.updateCertStudentByadmin(iStudentVO);
			resultVO.setReturnVO(iStudentVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}		
		return resultVO;
	}

	/**
	 * 자격증과정 수강생 선택 합격처리
	 */
	@Override
	@Transactional
	public ProcessResultVO<StudentVO> confirmStudentCertPass(StudentVO vo) {
		String[] studentList = StringUtil.split(vo.getStdNo(),"|");
		
		String resultMessage = "";
		
		for (int i = 0; i < studentList.length; i++) {
			StudentVO studentVO = new StudentVO();
			studentVO.setStdNo(studentList[i]);
			StudentVO sdto = studentMapper.selectStudent(studentVO);
			
			if(sdto == null) {
				throw new ServiceProcessException("수강생번호 : " + studentList[i] + "\n 조회되지 않습니다. 수강생 정보를 확인바랍니다.");
			}
			
			//이미 합격처리가 된 경우
			if("Y".equals(sdto.getCertPassYn())) {
				throw new ServiceProcessException("수강생번호 : " + studentList[i] + ", 회원명 : " + sdto.getUserNm() + "\n 이미 합격 처리 된 수강생 입니다.");
			}
			
			StudentVO paramVO = new StudentVO();
			paramVO.setModNo(vo.getModNo());
			paramVO.setCertPassYn("Y");
			paramVO.setStdNo(sdto.getStdNo());
			
			try {
				studentMapper.updateStudentCertPass(paramVO);
			} catch (Exception e) {
				e.printStackTrace();
				resultMessage += "합격 처리 과정 중 오류가 발생하였습니다.";
			}
		}
		return new ProcessResultVO<StudentVO>(ProcessResultVO.RESULT_SUCC, resultMessage);
	}
	
	/**
	 * 자격증과정 수강생 선택 불합격처리
	 */
	@Override
	@Transactional
	public ProcessResultVO<StudentVO> cancelStudentCertPass(StudentVO vo) {
		String[] studentList = StringUtil.split(vo.getStdNo(),"|");
		
		String resultMessage = "";
		
		for (int i = 0; i < studentList.length; i++) {
			StudentVO studentVO = new StudentVO();
			studentVO.setStdNo(studentList[i]);
			StudentVO sdto = studentMapper.selectStudent(studentVO);
			
			if(sdto == null) {
				throw new ServiceProcessException("수강생번호 : " + studentList[i] + "\n 조회되지 않습니다. 수강생 정보를 확인바랍니다.");
			}
			
			//이미 불합격처리가 된 경우
			if("N".equals(sdto.getCertPassYn())) {
				throw new ServiceProcessException("회원명 : " + sdto.getUserNm() + "\n 이미 불합격 처리 된 수강생 입니다.");
			}
			//이미 자격증 신청을 진행한 경우
			if("I".equals(sdto.getCertSts())) {
				throw new ServiceProcessException("회원명 : " + sdto.getUserNm() + "\n 이미 자격증 신청을 진행한 수강생 입니다.");
			}
			//이미 자격증 승인 완료 된 경우
			if("S".equals(sdto.getCertSts())) {
				throw new ServiceProcessException("회원명 : " + sdto.getUserNm() + "\n 이미 자격증 승인이 완료된 수강생 입니다.");
			}
			
			StudentVO paramVO = new StudentVO();
			paramVO.setModNo(vo.getModNo());
			paramVO.setCertPassYn("N");
			paramVO.setStdNo(sdto.getStdNo());
			
			try {
				studentMapper.updateStudentCertPass(paramVO);
			} catch (Exception e) {
				e.printStackTrace();
				resultMessage += "불합격 처리 과정 중 오류가 발생하였습니다.";
			}
		}
		return new ProcessResultVO<StudentVO>(ProcessResultVO.RESULT_SUCC, resultMessage);
	}
	
	/***************************************************** 
	 * 학습자 수료증을 정보를 조회한다.
	 * @param vo
	 * @throws Exception
	 ******************************************************/ 
	@Override
	public HashMap<String, Object> selectStdCertInfo(StudentVO vo) throws Exception {
		
		HashMap<String, Object> repositories = new HashMap<String, Object>();
		if(vo.getCpltNo() != null) {
			repositories = studentMapper.selectStdCpltInfo(vo);
		}else {
			repositories = studentMapper.selectStdCpltInfoSample(vo);
		}
		
		return repositories;
	}
}
