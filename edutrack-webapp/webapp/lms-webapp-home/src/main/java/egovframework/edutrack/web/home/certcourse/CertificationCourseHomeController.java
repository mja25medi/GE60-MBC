package egovframework.edutrack.web.home.certcourse;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.exception.AjaxProcessResultFailedException;
import egovframework.edutrack.comm.exception.MediopiaDefineException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.service.SysMenuMemService;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.URLBuilder;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.board.bbs.service.BrdBbsInfoVO;
import egovframework.edutrack.modules.course.category.service.CourseCategoryVO;
import egovframework.edutrack.modules.course.course.service.CourseService;
import egovframework.edutrack.modules.course.course.service.CourseVO;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseService;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;
import egovframework.edutrack.modules.course.subject.service.OfflineSubjectVO;
import egovframework.edutrack.modules.lecture.bookmark.service.BookmarkVO;
import egovframework.edutrack.modules.org.crscert.service.OrgCrsCertService;
import egovframework.edutrack.modules.org.crscert.service.OrgCrsCertVO;
import egovframework.edutrack.modules.org.menu.service.OrgMenuVO;
import egovframework.edutrack.modules.student.student.service.StudentService;
import egovframework.edutrack.modules.student.student.service.StudentVO;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;



/**
 * 자격증 과정 엑션 컨트롤
 * @author JNOTE
 *
 */
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/home/certcourse")
public class CertificationCourseHomeController extends GenericController {

	@Autowired @Qualifier("courseService")
	private CourseService 					courseService;

	@Autowired @Qualifier("createCourseService")
	private CreateCourseService 			createCourseService;

	@Autowired @Qualifier("studentService")
	private StudentService studentService;
	
	@Autowired @Qualifier("orgCrsCertService")
	private OrgCrsCertService	orgCrsCertService;

	/**
	 * 개설 과정 리스트
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listCourseMain")
	public String listCourseMain( CourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		if(!UserBroker.isLogin(request) || UserBroker.getUserType(request).equals("MEM_PROVIS")) {
			setAlertMessage(request, "로그인 후 이용바랍니다.");
			return "redirect:"+ new URLBuilder("/home/main/indexMain").toString();
		}
		
		String returnUrl = "";
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		vo.setCrsOperType("E");

		ProcessResultListVO<CourseVO> courseList = courseService.list(vo);
		request.setAttribute("courseList", courseList.getReturnList());
	   	request.setAttribute("vo", vo);
	   	
	   	returnUrl = "home/certcourse/list_student_certification_main";
		return returnUrl;
	}
	
	/**
	 * 개설 과정 리스트
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/stdCourselist")
	public String stdCourselist( CourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String returnUrl = "";

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		vo.setCrsOperType("E");
		
	   	String userNo = UserBroker.getUserNo(request);
	   	vo.setUserNo(userNo);
	   	request.setAttribute("stuList", studentService.listStdCertCourse(vo));
	   	request.setAttribute("vo", vo);
	   	
	   	returnUrl = "home/certcourse/list_student_certification_list";

		return returnUrl;
	}
	
	
	/**
	 * 자격증 신청 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/certAplcStudentPop2")
	public String certAplcStudent( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
	   	String userNo = UserBroker.getUserNo(request);
	   	vo.setUserNo(userNo);
	   	vo = studentService.stdCertCourse(vo);
	   	request.setAttribute("vo", vo);
		
		String returnUrl = "home/certcourse/student_certification_aplc_pop";
		return returnUrl;
	}
	
	/**
	 * 자격증 신청
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addCert")
	public String addCert( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<StudentVO> result = new ProcessResultVO<StudentVO>();
		try {
			studentService.certAplcStudent(vo);
			result.setResult(1);
			result.setMessage("자격증 신청을 완료하였습니다.");
		} catch (Exception e) {
			result.setResult(-1);
			setAlertMessage(request, "자격증 신청 중 오류가 발생하였습니다. 새로고침 후 다시 이용바랍니다.");
		} 
		return JsonUtil.responseJson(response, result);
	}
	
	/**
	 * 자격증 신청 취소
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/cancelCert")
	public String cancelCert( StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<StudentVO> result = new ProcessResultVO<StudentVO>();
		try {
			studentService.certAplcStudent(vo);
			result.setResult(1);
			result.setMessage("자격증 신청을 취소하였습니다.");
		} catch (Exception e) {
			result.setResult(-1);
			setAlertMessage(request, "자격증 취소 중 오류가 발생하였습니다. 새로고침 후 다시 이용바랍니다.");
		} 
		return JsonUtil.responseJson(response, result);
	}
	
	/**
	 * 자격증 출력
	 * 수정 2015.11.27 jamfam
	 * - PDF 출력으로 변경
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/printCertificate")
	public String printCertificate(StudentVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);

		CreateCourseVO createCourseVO = new CreateCourseVO();
		createCourseVO.setCrsCreCd(vo.getCrsCreCd());
		createCourseVO.setCreOperTypeCd("E");
		createCourseVO = createCourseService.viewCreateCourse(createCourseVO).getReturnVO();

		CourseVO courseVO = new CourseVO();
		courseVO.setCrsCd(createCourseVO.getCrsCd());
		courseVO = courseService.view(courseVO).getReturnVO();

		//-- 수강생 정보를 가져온다.
		vo = studentService.viewStudent(vo).getReturnVO();
		String enrlCpltDttm = vo.getEnrlCpltDttm();
		String year = StringUtil.substring(enrlCpltDttm, 0, 4);
		String month = StringUtil.substring(enrlCpltDttm, 4, 6);
		String day = StringUtil.substring(enrlCpltDttm, 6, 8);

		try {
			//-- 수료증 정보를 가져온다.
			OrgCrsCertVO orgCrsCertVO = new OrgCrsCertVO();
			orgCrsCertVO.setOrgCd(orgCd);
			orgCrsCertVO = orgCrsCertService.view(orgCrsCertVO);

		/*	String studyPeriod = "";
			//-- 정규과정 / 상시과정에 따른 수강기간 가져온기
			if("R".equals(courseVO.getCrsOperType())) {
				studyPeriod = createCourseVO.getEnrlStartDttm()+"~"+createCourseVO.getEnrlEndDttm();
			} else {
				studyPeriod = DateTimeUtil.getDateType(1,vo.getEnrlStartDttm())+"~"+DateTimeUtil.getDateType(1,vo.getEnrlEndDttm());
			}*/

			// 파일 다운로드 설정
			String fileName = URLEncoder.encode(getMessage(request, "org.title.crscert"), "UTF-8"); // 파일명이 한글일 땐 인코딩 필요
			response.setHeader("Content-Transper-Encoding", "binary");
			//response.setContentType("application/pdf");
			response.setContentType("application/octet-stream"); // 다운로드 형태로 변경
			response.setHeader("Content-Disposition", "inline; filename=" + fileName + ".pdf");

	    	// step 1 : Document 생성
			Document document;
			if("HOR".equals(orgCrsCertVO.getPrnDirec())) {
				document = new Document(PageSize.A4.rotate(), 50, 50, 50, 50); // 용지 및 여백 설정
			} else {
				document = new Document(PageSize.A4, 50, 50, 50, 50); // 용지 및 여백 설정
			}

			// step 2 : 백그라운드 이미지 설정
			String imgFilePath = Constants.FILE_STORAGE_PATH  + "/" + orgCd + "/" + orgCrsCertVO.getImgFileVO().getSaveFilePath();
			Image background = Image.getInstance(imgFilePath);
			float width = document.getPageSize().getWidth();
	        float height = document.getPageSize().getHeight();

	        // step 3 : PdfWriter 생성
	        PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());

	        // step 4 : Document Open
	        document.open();

	        // step 5 : Background Image 등록
			writer.getDirectContentUnder().addImage(background, width, 0, 0, height, 0, 0);


	        // step 6
			String args[] = new String[1];
			args[0]  = vo.getCpltNo();
	        String cpltNo 		= getMessage(request, "org.title.certificate.cpltno", args);
	        String userNm 		= getMessage(request, "org.title.certificate.username")+" : "+vo.getUserNm();
	        String birthDay		= getMessage(request, "org.title.certificate.birthday")+" : "+vo.getBirth();
	        String crsNm 		= getMessage(request, "org.title.certificate.course")+" : "+createCourseVO.getCrsCreNm();
	    //    String crsPeriod 	= getMessage(request, "org.title.certificate.period")+" : "+studyPeriod;
	        String prnDay 		= year+"."+month+"."+day;


	        // Measuring a String in Helvetica
	        String fontPath = "";
	        String locale = UserBroker.getLocaleKey(request);
	        BaseFont bf_font;
	        if("ko".equals(locale)) {
	        	fontPath = request.getSession().getServletContext().getRealPath("/font/Batang.ttf");
	        	bf_font = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED); // IDENTITY_V, IDENTITY_H, WINANSI, UniKS-UCS2-H, UniGB-UCS2-H
	        } else if("jp".equals(locale)) {
	        	fontPath = request.getSession().getServletContext().getRealPath("/font/MSMINCHO.TTF");
	        	bf_font = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	        } else {
	            Font helvetica = new Font(FontFamily.HELVETICA, 12);
	            bf_font = helvetica.getCalculatedBaseFont(false);
	        }

	        // Drawing lines to see where the text is added
	        PdfContentByte canvas = writer.getDirectContent();
	        canvas.beginText();
	        canvas.setFontAndSize(bf_font, 13);
	        canvas.showTextAligned(Element.ALIGN_LEFT, cpltNo, orgCrsCertVO.getCpltnoX(), orgCrsCertVO.getCpltnoY(), 0);
	        canvas.setFontAndSize(bf_font, 19);
	        int margin = 0;
	        if("Y".equals(orgCrsCertVO.getUserNmUseYn())) {
	        	canvas.showTextAligned(Element.ALIGN_LEFT, userNm, orgCrsCertVO.getCrsNmX(), orgCrsCertVO.getCrsNmY() - margin, 0);
	        	margin += 30;
	        }
	        if("Y".equals(orgCrsCertVO.getBirthUseYn())) {
	        	canvas.showTextAligned(Element.ALIGN_LEFT, birthDay, orgCrsCertVO.getCrsNmX(), orgCrsCertVO.getCrsNmY() - margin, 0);
	        	margin += 30;
	        }
	        if("Y".equals(orgCrsCertVO.getCrsNmUseYn())) {
	        	canvas.showTextAligned(Element.ALIGN_LEFT, crsNm, orgCrsCertVO.getCrsNmX(), orgCrsCertVO.getCrsNmY() - margin, 0);
	        	margin += 30;
	        }
	      /*  if("Y".equals(orgCrsCertVO.getEduPeriodUseYn())) {
	        	canvas.showTextAligned(Element.ALIGN_LEFT, crsPeriod, orgCrsCertVO.getCrsNmX(), orgCrsCertVO.getCrsNmY() - margin, 0);
	        	margin += 30;
	        }*/
	        canvas.setFontAndSize(bf_font, 19);
	        canvas.showTextAligned(Element.ALIGN_LEFT, prnDay, orgCrsCertVO.getPrndayX(), orgCrsCertVO.getPrndayY(), 0);
	        canvas.endText();

	        document.close();
			writer.close();
			return null;
			
		} catch (Exception e) {
			//-- 수료증 정보가 없을 경우는 기본 html 출력
			request.setAttribute("createCourseVO", createCourseVO);
			request.setAttribute("courseVO", courseVO);
			request.setAttribute("vo", vo);
			request.setAttribute("year", year);
			request.setAttribute("month", month);
			request.setAttribute("day", day);

			String msie6 = "N";
			String userBr = request.getHeader("User-Agent");
			if(userBr.indexOf("MSIE 6") > 0 || userBr.indexOf("MSIE 7") > 0) msie6 = "Y";
			request.setAttribute("msie6", msie6);

			return "home/certcourse/print_certification_default_pop";
		}
	}
}