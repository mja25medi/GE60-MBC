package egovframework.edutrack.web.lecture.system;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.system.code.service.SysCodeCtgrVO;
import egovframework.edutrack.modules.system.code.service.SysCodeService;


/**
 * 시스템 코드 액션 컨트롤러
 */
@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/lec/code")
public class CodeLectureController
		extends GenericController {

	@Autowired @Qualifier("sysCodeService")
	private SysCodeService sysCodeService;

	/**
	 * 시스템코드 카테고리 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listCategory")
	public String listCategory(Map commandMap, ModelMap model,
									  HttpServletRequest request, HttpServletResponse response) throws Exception {

		SysCodeCtgrVO vo = new SysCodeCtgrVO();
		vo.setCodeCtgrNm(StringUtil.nvl(request.getParameter("codeCtgrNm")));

		return JsonUtil.responseJson(response, sysCodeService.listCtgr(vo));
	}

	/**
	 * 시스템코드 코드 목록 조회
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listCode")
	public String listCode(Map commandMap, ModelMap model,
								  HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cdCtgrCd = request.getParameter("codeCtgrCd");
		return JsonUtil.responseJson(response, sysCodeService.listCode(cdCtgrCd));
	}

	/**
	 * 시스템 코드의 앞글자를 이용한 조회
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listSelectCd")
	public String listSelectCd(Map commandMap, ModelMap model,
									  HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cdCtgrCd = request.getParameter("codeCtgrCd");
		String cdCd = request.getParameter("cdCd");
		return JsonUtil.responseJson(response, sysCodeService.listSelectCd(cdCtgrCd, cdCd));
	}


	@RequestMapping(value="/listSelectOptn")
	public String listSelectOptn(Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cdCtgrCd = request.getParameter("codeCtgrCd");
		String optnCd = request.getParameter("optnCd");
		return JsonUtil.responseJson(response, sysCodeService.listSelectOptn(cdCtgrCd,optnCd));
	}



}