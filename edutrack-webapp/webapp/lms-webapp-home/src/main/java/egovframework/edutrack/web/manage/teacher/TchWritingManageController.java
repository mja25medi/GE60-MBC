package egovframework.edutrack.web.manage.teacher;

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
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.system.config.service.SysCfgService;
import egovframework.edutrack.modules.system.menu.service.SysMenuService;
import egovframework.edutrack.modules.teacher.schs.service.TchSchsVO;
import egovframework.edutrack.modules.teacher.writings.service.TchWritingsForm;
import egovframework.edutrack.modules.teacher.writings.service.TchWritingsService;
import egovframework.edutrack.modules.teacher.writings.service.TchWritingsVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/tch/writing")
public class TchWritingManageController extends GenericController {

	@Autowired @Qualifier("tchWritingsService")
	private TchWritingsService tchWritingsService;

	@Autowired @Qualifier("sysMenuService")
	private SysMenuService 		menuService;

	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService 	sysCodeMemService;

	@Autowired @Qualifier("sysCfgService")
	private SysCfgService 		sysCfgService;

	/**
	 * 강사 저서정보 목록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/listWrite")
	public String listWrite( TchWritingsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		ProcessResultListVO<TchWritingsVO> resultList = tchWritingsService.list(vo);
		
		request.setAttribute("tchWritingsVO", vo);
		request.setAttribute("writeList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());

		return "mng/teacher/writing/write_list_div";
	}

	/**
	 * 강사 저서정보 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addFormWritePop")
	public String addFormWrite ( TchWritingsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		return "mng/teacher/writing/write_write";
	}

	/**
	 * 강사 저서정보 등록(AJAX)
	 *
	 * @return ProcessResultVO
	 */
	@RequestMapping(value="/addWrite")
	public String addWrite(TchWritingsVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<TchWritingsVO> resultVO = tchWritingsService.add(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "teacher.message.teacherinfo.writing.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "teacher.message.teacherinfo.writing.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 강사 저서정보 수정 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/editFormWritePop")
	public String editFormWrite ( TchWritingsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		ProcessResultVO<TchWritingsVO> resultVO = tchWritingsService.view(vo);
		vo = resultVO.getReturnVO();
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");

		return "mng/teacher/writing/write_write";
	}

	/**
	 * 강사 저서정보 수정(AJAX)
	 *
	 * @return ProcessResultVO
	 */
	@RequestMapping(value="/editWrite")
	public String editWrite(TchWritingsVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		ProcessResultVO<TchWritingsVO> resultVO = tchWritingsService.edit(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "teacher.message.teacherinfo.writing.edit.success"));
		} else {
			resultVO.setMessage(getMessage(request, "teacher.message.teacherinfo.writing.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 강사 저서정보 삭제(AJAX)
	 *
	 * @return ProcessResultVO
	 */
	@RequestMapping(value="/delWrite")
	public String delWrite(TchWritingsVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		ProcessResultVO<TchWritingsVO> resultVO = tchWritingsService.remove(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "teacher.message.teacherinfo.writing.edit.success"));
		} else {
			resultVO.setMessage(getMessage(request, "teacher.message.teacherinfo.writing.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

}
