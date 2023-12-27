package egovframework.edutrack.web.lecture.teacher;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.teacher.info.service.TchInfoService;
import egovframework.edutrack.modules.teacher.info.service.TchInfoVO;
import egovframework.edutrack.modules.user.info.service.UsrUserAuthGrpService;
import egovframework.edutrack.modules.user.info.service.UsrUserAuthGrpVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoService;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/lec/tch")
public class TchLectureController extends GenericController {
	@Autowired @Qualifier("tchInfoService")
	private TchInfoService 	tchInfoService;

	@Autowired @Qualifier("usrUserInfoService")
	private UsrUserInfoService	usrUserInfoService;

	@Autowired @Qualifier("usrUserAuthGrpService")
	private UsrUserAuthGrpService usrUserAuthGrpService;

	/**
	 * 강사정보 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/viewTchPop")
	public String viewTch( UsrUserInfoVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception{
		commonVOProcessing(vo, request);
		TchInfoVO tchInfoVO = vo.getTchInfoVO();
		String userNo = UserBroker.getUserNo(request);
		vo.setUserNo(userNo);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		try {
			tchInfoVO = tchInfoService.view(tchInfoVO).getReturnVO();
			request.setAttribute("gubun", "E");
		} catch (Exception e) {
			request.setAttribute("gubun", "A");
		}

		String teacherYn = "N";
		UsrUserAuthGrpVO uagVO = new UsrUserAuthGrpVO();
		uagVO.setUserNo(vo.getUserNo());
		List<UsrUserAuthGrpVO> authList = usrUserAuthGrpService.list(uagVO).getReturnList();
		for(int i=0; i < authList.size(); i++) {
			UsrUserAuthGrpVO iagVO = authList.get(i);
			if("HOME".equals(iagVO.getMenuType())) {
				if("TEACHER".equals(iagVO.getAuthGrpCd())) teacherYn = "Y";
			}
		}
		request.setAttribute("teacherYn", teacherYn);

		request.setAttribute("vo", vo);
		request.setAttribute("tchInfoVO", tchInfoVO);
		return "home/lecture/teacher/teacher_info_view_pop";

	}
}
