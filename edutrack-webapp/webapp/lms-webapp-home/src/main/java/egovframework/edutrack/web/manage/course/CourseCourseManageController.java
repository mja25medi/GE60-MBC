package egovframework.edutrack.web.manage.course;

import java.io.File;
import java.util.ArrayList;
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

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.exception.AjaxProcessResultFailedException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.category.service.CourseCategoryService;
import egovframework.edutrack.modules.course.category.service.CourseCategoryVO;
import egovframework.edutrack.modules.course.course.service.CourseService;
import egovframework.edutrack.modules.course.course.service.CourseVO;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;
import egovframework.edutrack.modules.user.dept.service.UsrDeptInfoVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/course/course")
public class CourseCourseManageController extends GenericController {

	@Autowired @Qualifier("courseService")
	private CourseService			courseService;

	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService			sysCodeMemService;

	@Autowired @Qualifier("courseCategoryService")
	private CourseCategoryService	courseCategoryService;

	/**
	 * 과정 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/courseMain")
	public String mainCourse( CourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		commonVOProcessing(vo, request);
		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");
		//request.setAttribute("summernote", "Y");

		return "mng/course/course/course_main";
	}

	/**
	 * 과정 목록 조회
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/listCourse")
	public String listCourse( CourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultListVO<CourseVO> resultList = courseService.listPageing(vo, vo.getCurPage(), vo.getListScale());

		request.setAttribute("courseList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("courseVO", vo);
		request.setAttribute("nowDate", DateTimeUtil.getCurrentString());
		return "mng/course/course/course_list_div";
	}

	/**
	 * 과정 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addFormCourse")
	public String addFormCourse( CourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		
		vo.setUseYn("Y");
		request.setAttribute("gubun", "A");
		request.setAttribute("vo", vo);
		request.setAttribute("summernote", "Y");

		return "mng/course/course/course_write";
	}

	/**
	 * 과정 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addCourse")
	public String addCourse( CourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<CourseVO> resultVO = null;
		try {
			vo.setRegNo(UserBroker.getUserNo(request));
			if (vo.getMetaTag() != null && !vo.getMetaTag().equals("")){
			vo.setMetaTag(vo.getMetaTag().replace(",", "|"));
			}
			resultVO = courseService.add(vo);
		}
		catch (DuplicateKeyException e) {
			throw new AjaxProcessResultFailedException(getMessage(request, "course.message.course.dupcode"));
		}
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.course.add.success"));
			resultVO.setReturnVO(vo);
		} else {
			resultVO.setMessage(getMessage(request, "course.message.course.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 과정 수정 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editFormCoursePop")
	public String editFormCourse( CourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		//-- 과정 카테고리를 리스트로 가져온다.
		ProcessResultListVO<CourseCategoryVO> resultListVO = courseCategoryService.listCategory(orgCd, "","Y");
		request.setAttribute("courseCategoryList", resultListVO.getReturnList());
		//-- 과정의 정보를 가져온다.
		ProcessResultVO<CourseVO> resultVO = courseService.view(vo);
		vo = resultVO.getReturnVO();
		String[] preMetaTag = null;
		if (vo.getMetaTag() != null && !vo.getMetaTag().equals("")){
		preMetaTag = vo.getMetaTag().split("\\|");
		}

		List<SysCodeVO> nopLimitList = sysCodeMemService.getSysCodeList("NOP_LIMIT_YN");
        List<SysCodeVO> crsOperMthdList = sysCodeMemService.getSysCodeList("CRS_OPER_MTHD", UserBroker.getLocaleKey(request));
        List<SysCodeVO> enrlCertMthdList = sysCodeMemService.getSysCodeList("ENRL_CERT_MTHD", UserBroker.getLocaleKey(request));
        List<SysCodeVO> cpltHandlTypeList = sysCodeMemService.getSysCodeList("CPLT_HANDL_TYPE", UserBroker.getLocaleKey(request));
        List<SysCodeVO> crsSvcEditList = sysCodeMemService.getSysCodeList("CRS_SVC_TYPE", UserBroker.getLocaleKey(request));
        
		request.setAttribute("nopLimitList", nopLimitList);
        request.setAttribute("crsOperMthdList", crsOperMthdList);
		request.setAttribute("enrlCertMthdList", enrlCertMthdList);
		request.setAttribute("cpltHandlTypeList", cpltHandlTypeList);
		request.setAttribute("crsSvcEditList", crsSvcEditList);
		request.setAttribute("vo", vo);
		request.setAttribute("preMetaTag", preMetaTag);
		request.setAttribute("gubun", "E");
		request.setAttribute("summernote", "Y");
		

		return "mng/course/course/course_write_pop";
	}

	/**
	 * 과정 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editCourse")
	public String editCourse( CourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		if (vo.getMetaTag() != null && !vo.getMetaTag().equals("")){
		vo.setMetaTag(vo.getMetaTag().replace(",", "|"));
		}
		ProcessResultVO<CourseVO> resultVO = new ProcessResultVO<CourseVO>();
		try {
			resultVO = courseService.edit(vo);
			resultVO.setMessage(getMessage(request, "course.message.course.edit.success"));
		}catch(Exception e) {
			resultVO.setMessage(getMessage(request, "course.message.course.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 과정 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/deleteCourse")
	public String deleteCourse( CourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<?> resultVO = courseService.remove(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.course.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.course.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 과정 관리
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/mngCourse")
	public String mngCourse( CourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		//-- 과정의 정보를 가져온다.
		ProcessResultVO<CourseVO> resultVO = courseService.view(vo);
		if(ValidationUtils.isNotEmpty(resultVO.getReturnVO().getDeptCds())) {
			List<UsrDeptInfoVO> deptList = new ArrayList<UsrDeptInfoVO>();
			for(int i=0; i< StringUtil.splitToList(resultVO.getReturnVO().getDeptCds(), ",").size(); i++) {
				UsrDeptInfoVO udiVO = new UsrDeptInfoVO();
				udiVO.setDeptCd(StringUtil.splitToList(resultVO.getReturnVO().getDeptCds(), ",").get(i).toString());
				udiVO.setDeptNm(StringUtil.splitToList(resultVO.getReturnVO().getDeptNms(), ",").get(i).toString());
				deptList.add(udiVO);
			}
			request.setAttribute("deptList", deptList);
		}
		vo = resultVO.getReturnVO();
		request.setAttribute("vo", vo);
		return "mng/course/course/course_manage";
	}

	/**
	 * 과정명 검색
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/checkCrsNm")
	public String checkCrsNm( CourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);

		return JsonUtil.responseJson(response,
			courseService.isDupulicationByCrsNm(orgCd, vo.getCrsNm()).getReturnVO());
	}

	/**
	 * 과목 사용중인 과정 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listSubInfo")
	public String listSubInfo( CourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultListVO<CourseVO> resultList = courseService.listSubInfo(vo);

		request.setAttribute("courseList", resultList.getReturnList());
		request.setAttribute("vo", vo);
		return "mng/course/course/course_sub_list_div";
	}

	/**
	 * 과목 사용중인 과정 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listSubInfoOffline")
	public String listSubInfoOffline( CourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultListVO<CourseVO> resultList = courseService.listSubInfoOffline(vo);

		request.setAttribute("courseList", resultList.getReturnList());
		request.setAttribute("vo", vo);
		return "mng/course/course/course_sub_list_div";
	}
	
	/**
	 * 과정 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addCoursePop")
	public String addCoursePop( CourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		//-- 과정 카테고리를 리스트로 가져온다.
		ProcessResultListVO<CourseCategoryVO> resultListVO = courseCategoryService.listCategory(orgCd, "","Y");
		
		request.setAttribute("courseCategoryList", resultListVO.getReturnList());
		
		vo.setUeinsYn("N");
		vo.setStayYn("Y");
		vo.setCertIssueYn("Y");
		vo.setUseYn("Y");
		request.setAttribute("courseVO", vo);
		request.setAttribute("gubun", "A");
		
		List<SysCodeVO> nopLimitList = sysCodeMemService.getSysCodeList("NOP_LIMIT_YN");
        List<SysCodeVO> crsOperMthdList = sysCodeMemService.getSysCodeList("CRS_OPER_MTHD", UserBroker.getLocaleKey(request));
        List<SysCodeVO> enrlCertMthdList = sysCodeMemService.getSysCodeList("ENRL_CERT_MTHD", UserBroker.getLocaleKey(request));
        List<SysCodeVO> cpltHandlTypeList = sysCodeMemService.getSysCodeList("CPLT_HANDL_TYPE", UserBroker.getLocaleKey(request));
        List<SysCodeVO> crsSvcEditList = sysCodeMemService.getSysCodeList("CRS_SVC_TYPE", UserBroker.getLocaleKey(request));
            
        if(!Constants.HRD_API_USE_YN.equals("Y")) {
        	//HRD 사용하지 않을 경우에는 국비지원 과정이 뜨지 않도록 작업
        	for(int i=0; i<crsSvcEditList.size();i++) {
        		if(crsSvcEditList.get(i).getCodeCd().equals("R")) {
        			crsSvcEditList.remove(i);
        		}
        	}
        }
		request.setAttribute("nopLimitList", nopLimitList);
		request.setAttribute("crsOperMthdList", crsOperMthdList);
		request.setAttribute("enrlCertMthdList", enrlCertMthdList);
		request.setAttribute("cpltHandlTypeList", cpltHandlTypeList);
		request.setAttribute("crsSvcEditList", crsSvcEditList);
		request.setAttribute("vo", vo);
		request.setAttribute("fileupload", "Y");
		request.setAttribute("summernote", "Y");

		return "mng/course/course/course_write_pop";
	}

}
