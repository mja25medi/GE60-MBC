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
import egovframework.edutrack.modules.teacher.writings.service.TchWritingsService;
import egovframework.edutrack.modules.teacher.writings.service.TchWritingsVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/lec/tch/writing")
public class TchWritingLectureController extends GenericController {
	@Autowired @Qualifier("tchWritingsService")
	private TchWritingsService tchWritingsService;

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
	@RequestMapping(value="/mainCtgr")
	public String listWrite( TchWritingsVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		//ProcessResultListVO<TchWritingsVO> resultList = tchWritingsService.listPageing(tchWritingsVO, tchWritingsForm.getCurPage(), tchWritingsForm.getListScale());
		ProcessResultListVO<TchWritingsVO> resultList = tchWritingsService.list(vo);

		request.setAttribute("tchWritingsVO", vo);
		request.setAttribute("tchWritingsList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		return "home/lecture/teacher/write_list_div";
	}
}
