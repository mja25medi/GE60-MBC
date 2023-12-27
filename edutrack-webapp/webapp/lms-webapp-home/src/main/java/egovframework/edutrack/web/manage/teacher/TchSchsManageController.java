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
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.board.bbs.service.BrdBbsAtclVO;
import egovframework.edutrack.modules.system.config.service.SysCfgService;
import egovframework.edutrack.modules.system.menu.service.SysMenuService;
import egovframework.edutrack.modules.teacher.schs.service.TchSchsForm;
import egovframework.edutrack.modules.teacher.schs.service.TchSchsService;
import egovframework.edutrack.modules.teacher.schs.service.TchSchsVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/tch/schs")
public class TchSchsManageController extends GenericController {
	@Autowired @Qualifier("tchSchsService")
	private TchSchsService tchSchsService;

	@Autowired @Qualifier("sysMenuService")
	private SysMenuService 		menuService;

	@Autowired @Qualifier("sysCodeMemService")
	private SysCodeMemService 	sysCodeMemService;

	@Autowired @Qualifier("sysCfgService")
	private SysCfgService 		sysCfgService;

	/**
	 * 강사 학력정보 목록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/listSchool")
	public String listSchool( TchSchsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		ProcessResultListVO<TchSchsVO> resultList = tchSchsService.list(vo);
	
		request.setAttribute("tchSchsVO", vo);
		request.setAttribute("tchSchsList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());

		return "mng/teacher/school/school_list_div";
	}

	/**
	 * 강사 학력정보 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addFormSchoolPop")
	public String addFormSchool ( TchSchsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		return "mng/teacher/school/school_write";
	}

	/**
	 * 강사 학력정보 등록(AJAX)
	 *
	 * @return ProcessResultVO
	 */
	@RequestMapping(value="/addSchool")
	public String addSchool(TchSchsVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);
		ProcessResultVO<TchSchsVO> resultVO = tchSchsService.add(vo);
		
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "teacher.message.teacherinfo.school.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "teacher.message.teacherinfo.school.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 강사 학력정보 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/editFormSchoolPop")
	public String editFormSchool ( TchSchsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		ProcessResultVO<TchSchsVO> resultVO = tchSchsService.view(vo);
		vo = resultVO.getReturnVO();
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");

		return "mng/teacher/school/school_write";
	}

	/**
	 * 강사 학력정보 수정(AJAX)
	 *
	 * @return ProcessResultVO
	 */
	@RequestMapping(value="/editSchool")
	public String editSchool(TchSchsVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);
		ProcessResultVO<TchSchsVO> resultVO = tchSchsService.edit(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "teacher.message.teacherinfo.school.edit.success"));
		} else {
			resultVO.setMessage(getMessage(request, "teacher.message.teacherinfo.school.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 강사 학력정보 삭제(AJAX)
	 *
	 * @return ProcessResultVO
	 */
	@RequestMapping(value="/delSchool")
	public String delSchool(TchSchsVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		commonVOProcessing(vo, request);
		ProcessResultVO<TchSchsVO> resultVO = tchSchsService.remove(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "teacher.message.teacherinfo.school.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "teacher.message.teacherinfo.school.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

}
