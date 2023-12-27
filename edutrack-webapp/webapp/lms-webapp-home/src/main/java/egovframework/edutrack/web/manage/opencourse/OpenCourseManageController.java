package egovframework.edutrack.web.manage.opencourse;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.course.service.CourseService;
import egovframework.edutrack.modules.opencourse.course.service.OpenCrsService;
import egovframework.edutrack.modules.opencourse.course.service.OpenCrsVO;
import egovframework.edutrack.modules.opencourse.ctgr.service.OpenCrsCtgrService;
import egovframework.edutrack.modules.opencourse.ctgr.service.OpenCrsCtgrVO;
import egovframework.edutrack.modules.opencourse.subject.service.OpenCrsSbjService;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/openCourse")
public class OpenCourseManageController extends GenericController {
	
	@Autowired @Qualifier("courseService")
	private CourseService			courseService;

	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService			sysCodeMemService;

	@Autowired @Qualifier("openCrsCtgrService")
	private OpenCrsCtgrService		openCrsCtgrService;

	@Autowired @Qualifier("openCrsSbjService")
	private OpenCrsSbjService		openCrsSbjService;

	@Autowired @Qualifier("openCrsService")
	private OpenCrsService			 openCrsService;

	/**
	 * 공개 과정 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/main")
	public String main( OpenCrsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		
		commonVOProcessing(vo, request);
		request.setAttribute("vo", vo);
		
		return "mng/opencourse/open_course_main";
	}

	/**
	 * 공개 분류 목록 조회
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/listCtgr")
	public String listCtgr( OpenCrsCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//기관코드 삽입
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		//공개 분류 목록
		ProcessResultListVO<OpenCrsCtgrVO> resultList = openCrsCtgrService.list(vo);
		request.setAttribute("openCrsCtgrList", resultList.getReturnList());
		request.setAttribute("vo", vo);
		return "mng/opencourse/ctgr/ctgr_list_div";
	}

	/**
	 * 공개과정 분류 등록 폼
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addCtgrPop")
	public String addCtgrForm(OpenCrsCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String gubun = vo.getGubun();
		request.setAttribute("gubun", gubun);
		if(gubun.equals("E")){
			//기관코드 삽입
			String orgCd = UserBroker.getUserOrgCd(request);
			vo.setOrgCd(orgCd);

			//공개과정 정보
			vo = openCrsCtgrService.view(vo).getReturnVO();
		} else {
			vo.setUseYn("Y");
		}
		request.setAttribute("vo", vo);
		return "mng/opencourse/ctgr/ctgr_write_pop";
	}

	/**
	 * 공개과정 분류 등록
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addCtgr")
	public String addCtgr(OpenCrsCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		//기관코드 삽입
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		//공개과정 정보
		ProcessResultVO<OpenCrsCtgrVO> resultVO = openCrsCtgrService.add(vo);
		request.setAttribute("resultVO", resultVO.getReturnVO());
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.open.message.category.add.success"));
			resultVO.setResult(1);
		} else {
			resultVO.setMessage(getMessage(request, "course.open.message.category.add.failed"));
			resultVO.setResult(-1);
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 공개과정 분류 수정
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editCtgr")
	public String editCtgr(OpenCrsCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//기관코드 삽입
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		//공개과정 정보
		ProcessResultVO<OpenCrsCtgrVO> resultVO = openCrsCtgrService.edit(vo);
		request.setAttribute("resultVO", resultVO.getReturnVO());
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.open.message.category.edit.success"));
			resultVO.setResult(1);
		} else {
			resultVO.setMessage(getMessage(request, "course.open.message.category.edit.failed"));
			resultVO.setResult(-1);
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 공개과정 순서 변경 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sortCtgrForm")
	public String sortCtgrForm( OpenCrsCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//기관코드 삽입
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		//공개 분류 목록
		ProcessResultListVO<OpenCrsCtgrVO> resultList = openCrsCtgrService.list(vo);
		
		request.setAttribute("vo", vo);
		request.setAttribute("openCrsCtgrList", resultList.getReturnList());
		return "mng/opencourse/ctgr/ctgr_sort";
	}

	/**
	 * 공개과정 순서 변경 - 분류 목록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listCtgrJSON")
	public String listCtgrJSON( OpenCrsCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		//기관코드 삽입
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		//공개 분류 목록
		ProcessResultListVO<OpenCrsCtgrVO> resultList = openCrsCtgrService.list(vo);
		return JsonUtil
				.responseJson(response, resultList);
	}

	/**
	 * 공객과정 분류 순서변경
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/sortCtgr")
	public String sortCtgr( OpenCrsCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		//기관코드 삽입
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<?> resultVO = openCrsCtgrService.sort(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.open.message.category.sort.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.open.message.category.sort.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 공개과정 분류 수정
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/removeCtgr")
	public String removeCtgr(OpenCrsCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);
		//기관코드 삽입
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<?> resultVO;
		//공개과정 정보
		try {
			resultVO = openCrsCtgrService.remove(vo);
			if(resultVO.getResult() > 0) {
				resultVO.setMessage(getMessage(request, "course.open.message.category.delete.success"));
				resultVO.setResult(1);
			} else {
				resultVO.setMessage(getMessage(request, "course.open.message.category.delete.failed"));
				resultVO.setResult(-1);
			}
		} catch (Exception e) {
			resultVO = new ProcessResultVO<OpenCrsCtgrVO>();
			resultVO.setMessage(getMessage(request, "course.open.message.category.delete.failed"));
			resultVO.setResult(-1);
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 공개 과정 과목 목록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listCrs")
	public String listCrs( OpenCrsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		//공개 분류>과정 목록
		ProcessResultListVO<OpenCrsVO> resultList = openCrsService.list(vo);
		request.setAttribute("openCrsList", resultList.getReturnList());
		return "mng/opencourse/course/course_list_div";
	}

	/**
	 * 공개과정 과정 등록 폼
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addCrsPop")
	public String addCrsPop(OpenCrsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String gubun = vo.getGubun();
		request.setAttribute("gubun", gubun);
		
		vo.setOrgCd(orgCd); 			//기관코드 삽입

		OpenCrsCtgrVO openCrsCtgrVO = new OpenCrsCtgrVO();
		openCrsCtgrVO.setOrgCd(orgCd);
		openCrsCtgrVO.setCtgrCd(vo.getCtgrCd());
		openCrsCtgrVO = openCrsCtgrService.view(openCrsCtgrVO).getReturnVO();
		
		if(gubun.equals("E")){
			//공개과정 정보
			vo = openCrsService.view(vo).getReturnVO();
		} else {
			vo.setUseYn("Y");
		}

		request.setAttribute("openCrsCtgrVO", openCrsCtgrVO);
		request.setAttribute("vo", vo);

		return "mng/opencourse/course/course_write_pop";
	}

	/**
	 * 공개과정 과정 등록
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addCrs")
	public String addCrs(OpenCrsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		//기관코드 삽입
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		//공개과정 정보
		ProcessResultVO<OpenCrsVO> resultVO = openCrsService.add(vo);
		request.setAttribute("resultVO", resultVO.getReturnVO());
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.open.message.course.add.success"));
			resultVO.setResult(1);
		} else {
			resultVO.setMessage(getMessage(request, "course.open.message.course.add.failed"));
			resultVO.setResult(-1);
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 공개과정 과정 수정
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editCrs")
	public String editCrs(OpenCrsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//기관코드 삽입
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		//공개과정 정보
		ProcessResultVO<OpenCrsVO> resultVO = openCrsService.edit(vo);
		request.setAttribute("resultVO", resultVO.getReturnVO());
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.open.message.course.edit.success"));
			resultVO.setResult(1);
		} else {
			resultVO.setMessage(getMessage(request, "course.open.message.course.edit.failed"));
			resultVO.setResult(-1);
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 공개과정 순서변경 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sortCrsForm")
	public String sortCrsForm( OpenCrsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);
		//기관코드 삽입
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		request.setAttribute("vo", vo);
		return "mng/opencourse/course/course_sort";
	}

	/**
	 * 공개과정  과정 목록 - JSON
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listCrsJSON")
	public String listCrsJSON( OpenCrsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		//기관코드 삽입
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		//공개 분류 목록
		ProcessResultListVO<OpenCrsVO> resultList = openCrsService.list(vo);

		return JsonUtil
				.responseJson(response, resultList);
	}

	/**
	 * 공객과정 과정 순서변경
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/sortCrs")
	public String sortCrs(OpenCrsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		//기관코드 삽입
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<?> resultVO = openCrsService.sort(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.open.message.course.sort.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.open.message.course.sort.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 공개과정 과정 삭제
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/removeCrs")
	public String removeCrs(OpenCrsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//기관코드 삽입
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		//공개과정 정보
		ProcessResultVO<?> resultVO = openCrsService.remove(vo);
		request.setAttribute("resultVO", resultVO.getReturnVO());
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.open.message.course.delete.success"));
			resultVO.setResult(1);
		} else {
			resultVO.setMessage(getMessage(request, "course.open.message.course.delete.failed"));
			resultVO.setResult(-1);
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 공개과정 과정 수정
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/editExposure")
	public String editExposure(OpenCrsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		//기관코드 삽입
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		String exposureYn = vo.getExposureYn();
		vo = openCrsService.view(vo).getReturnVO();
		vo.setExposureYn(exposureYn);

		//공개과정 정보
		ProcessResultVO<OpenCrsVO> resultVO = openCrsService.edit(vo);
		request.setAttribute("resultVO", resultVO.getReturnVO());
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.open.message.course.edit.success"));
			resultVO.setResult(1);
		} else {
			resultVO.setMessage(getMessage(request, "course.open.message.course.edit.failed"));
			resultVO.setResult(-1);
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 과목 사용중인 공개 과정 목록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/listSubInfo")
	public String listSubInfo( OpenCrsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		//공개 분류>과정 목록
		ProcessResultListVO<OpenCrsVO> resultList = openCrsService.listSubInfo(vo);
		request.setAttribute("openCrsList", resultList.getReturnList());
		return "mng/opencourse/course/course_sub_list_div";
	}

}
