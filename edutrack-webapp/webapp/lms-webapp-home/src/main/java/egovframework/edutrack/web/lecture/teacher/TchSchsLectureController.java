package egovframework.edutrack.web.lecture.teacher;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.SysCodeMemService;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.system.config.service.SysCfgService;
import egovframework.edutrack.modules.teacher.schs.service.TchSchsForm;
import egovframework.edutrack.modules.teacher.schs.service.TchSchsService;
import egovframework.edutrack.modules.teacher.schs.service.TchSchsVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/lec/tch/schs")
public class TchSchsLectureController extends GenericController {
	@Autowired @Qualifier("tchSchsService")
	private TchSchsService tchSchsService;

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

		//ProcessResultListVO<TchSchsVO> resultList = tchSchsService.listPageing(tchSchsVO, tchSchsForm.getCurPage(), tchSchsForm.getListScale());
		ProcessResultListVO<TchSchsVO> resultList = tchSchsService.list(vo);

		request.setAttribute("tchSchsVO", vo);
		request.setAttribute("tchSchsList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		return "home/lecture/teacher/school_list_div";
	}

}
