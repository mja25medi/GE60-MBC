package egovframework.edutrack.web.manage.course;

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
@RequestMapping(value="/mng/course/certification")
public class CourseCertificationManageController extends GenericController {

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
	@RequestMapping(value="/certificationMain")
	public String mainCourse( CourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		commonVOProcessing(vo, request);
		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");

		return "mng/course/certification/certification_main";
	}

	/**
	 * 과정 목록 조회
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/listCertification")
	public String listCourse( CourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		vo.setCrsOperType("E");

		ProcessResultListVO<CourseVO> resultList = courseService.listPageing(vo, vo.getCurPage(), vo.getListScale());

		request.setAttribute("courseList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("courseVO", vo);
		request.setAttribute("nowDate", DateTimeUtil.getCurrentString());
		return "mng/course/certification/certification_list_div";
	}

	/**
	 * 과정 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addFormCertCoursePop")
	public String addFormCourse( CourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		
		vo.setUseYn("Y");
		request.setAttribute("gubun", "A");
		request.setAttribute("vo", vo);

		return "mng/course/certification/certification_write";
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
		vo.setCrsOperType("E");
		
		ProcessResultVO<CourseVO> resultVO = null;
		try {
			vo.setRegNo(UserBroker.getUserNo(request));
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
	@RequestMapping(value="/editFormCertCoursePop")
	public String editFormCourse( CourseVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		vo.setCrsOperType("E");
		//-- 과정의 정보를 가져온다.
		ProcessResultVO<CourseVO> resultVO = courseService.view(vo);
		vo = resultVO.getReturnVO();
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		
		return "mng/course/certification/certification_write";
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


}
