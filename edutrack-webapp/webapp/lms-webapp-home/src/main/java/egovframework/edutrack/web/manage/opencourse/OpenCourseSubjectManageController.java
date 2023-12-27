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
import egovframework.edutrack.modules.opencourse.course.service.OpenCrsService;
import egovframework.edutrack.modules.opencourse.course.service.OpenCrsVO;
import egovframework.edutrack.modules.opencourse.ctgr.service.OpenCrsCtgrService;
import egovframework.edutrack.modules.opencourse.subject.service.OpenCrsSbjService;
import egovframework.edutrack.modules.opencourse.subject.service.OpenCrsSbjVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/openCourse/subject")
public class OpenCourseSubjectManageController extends GenericController {
	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService			sysCodeMemService;

	@Autowired @Qualifier("openCrsCtgrService")
	private OpenCrsCtgrService		openCrsCtgrService;

	@Autowired @Qualifier("openCrsSbjService")
	private OpenCrsSbjService		openCrsSbjService;

	@Autowired @Qualifier("openCrsService")
	private OpenCrsService 		openCrsService;

	/**
	 * 공개과정 과정 과목관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/main")
	public String main( OpenCrsSbjVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		OpenCrsVO openCrsVO		= new OpenCrsVO();
		openCrsVO.setOrgCd(orgCd);
		openCrsVO.setCrsCd(vo.getCrsCd());

		openCrsVO = openCrsService.view(openCrsVO).getReturnVO();
		request.setAttribute("openCrsVO", openCrsVO);
		request.setAttribute("vo", vo);
		return "mng/opencourse/subject/subject_main";
	}

	/**
	 * 공개과정 과정 과목 목록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list( OpenCrsSbjVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		//공개과정 정보
		ProcessResultListVO<OpenCrsSbjVO> resultVO = openCrsSbjService.list(vo);

		request.setAttribute("openCrsSbjList", resultVO.getReturnList());
		return "mng/opencourse/subject/subject_list_div";
	}

	/**
	 * 공개과정 과목 삭제
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/remove")
	public String remove(OpenCrsSbjVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		//공개과정 정보
		ProcessResultVO<?> resultVO = openCrsSbjService.remove(vo);
		request.setAttribute("resultVO", resultVO.getReturnVO());
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.open.message.course.edit.success"));
			resultVO.setResult(1);
		} else {
			resultVO.setMessage(getMessage(request, "course.open.message.course.edit.success"));
			resultVO.setResult(-1);
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 공개과정 순서변경 과목 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sortForm")
	public String sortForm( OpenCrsSbjVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);


		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		request.setAttribute("vo", vo);
		return "mng/opencourse/subject/subject_sort";
	}

	/**
	 * 공개과정 순서변경 과목 목록 조회
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/listJSON")
	public String listJSON( OpenCrsSbjVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		//공개 분류 목록
		ProcessResultListVO<OpenCrsSbjVO> resultList = openCrsSbjService.list(vo);

		return JsonUtil.responseJson(response, resultList);
	}

	/**
	 * 공객과정 과목 순서변경
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/sort")
	public String sort(OpenCrsSbjVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<?> resultVO = openCrsSbjService.sort(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.open.message.course.sort.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.open.message.course.sort.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}
}
