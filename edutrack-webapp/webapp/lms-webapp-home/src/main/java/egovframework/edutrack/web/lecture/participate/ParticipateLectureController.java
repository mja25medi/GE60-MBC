package egovframework.edutrack.web.lecture.participate;



import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.URLBuilder;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.decls.service.CreCrsDeclsService;
import egovframework.edutrack.modules.course.decls.service.CreCrsDeclsVO;
import egovframework.edutrack.modules.lecture.participate.service.ParticipateService;
import egovframework.edutrack.modules.lecture.participate.service.ParticipateVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/lec/participate")
public class ParticipateLectureController extends GenericController{

	@Autowired @Qualifier("participateService")
	private ParticipateService participateService;

	@Autowired @Qualifier("creCrsDeclsService")
	private CreCrsDeclsService 		creCrsDeclsService;

	/**
	 * 학습자참여현황  목록 화면
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value="/listMain")
	public String listMain(ParticipateVO vo, Map commandMap, ModelMap model,
				HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String crsCreCd = UserBroker.getCreCrsCd(request);
		vo.setCrsCreCd(crsCreCd);

		//--- 분반 목록
		CreCrsDeclsVO creCrsDeclsVO = new CreCrsDeclsVO();
		creCrsDeclsVO.setCrsCreCd(crsCreCd);
		List<CreCrsDeclsVO> creCrsDeclsList = creCrsDeclsService.list(creCrsDeclsVO).getReturnList();
		request.setAttribute("creCrsDeclsList", creCrsDeclsList);

		vo.setPageScale(10);
		ProcessResultListVO<ParticipateVO> resultList = participateService.listPageing(vo);
		request.setAttribute("participateListVO", resultList.getReturnList());
	   	request.setAttribute("pageInfo", resultList.getPageInfo());
	   	request.setAttribute("paging", "Y");
	   	request.setAttribute("vo", vo);

		return "home/lecture/participate/participate_list";
	}

	//점수 저장 및 수정
	@RequestMapping(value="/addScore")
	public String addScore(ParticipateVO vo, Map commandMap, ModelMap model,
			  HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);

		String crsCreCd = UserBroker.getCreCrsCd(request);
		vo.setCrsCreCd(crsCreCd);


		try {
			participateService.addScore(vo);
		} catch (Exception e) {
			e.printStackTrace();
			setAlertMessage(request, getMessage(request, "lecture.message.join.score.add.failed"));
			//목록 화면으로
			return "redirect:"+ new URLBuilder("/lec/participate/listMain")
					.addParameter("participateVO.crsCreCd", crsCreCd)
					.addParameter("participateVO.searchKey", vo.getSearchKey())
					.addParameter("participateVO.searchValue", vo.getSearchValue())
					.addParameter("curPage", vo.getCurPage())
					.toString();
		}

		setAlertMessage(request, getMessage(request, "lecture.message.join.score.add.success"));

		//목록 화면으로
		return "redirect:"+ new URLBuilder("/lec/participate/listMain")
				.addParameter("participateVO.crsCreCd", crsCreCd)
				.addParameter("participateVO.searchKey", vo.getSearchKey())
				.addParameter("participateVO.searchValue", vo.getSearchValue())
				.addParameter("curPage", vo.getCurPage())
				.toString();
	}

	//점수 저장 및 수정
	@RequestMapping(value="/addScoreAll")
	public String addScoreAll(ParticipateVO vo, Map commandMap, ModelMap model,
			  HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String crsCreCd = UserBroker.getCreCrsCd(request);

		vo.setCrsCreCd(crsCreCd);

		String strStdNo = request.getParameter("strStdNo");
		String strJoinScore = request.getParameter("strJoinScore");

		try {
			participateService.addScoreAll(vo, strStdNo, strJoinScore);
		} catch (Exception e) {
			e.printStackTrace();
			setAlertMessage(request, getMessage(request, "lecture.message.join.score.add.failed"));
			//목록 화면으로
			return "redirect:"+ new URLBuilder("/lec/participate/listMain")
					.addParameter("participateVO.crsCreCd", crsCreCd)
					.addParameter("participateVO.searchKey", vo.getSearchKey())
					.addParameter("participateVO.searchValue", vo.getSearchValue())
					.addParameter("curPage", vo.getCurPage())
					.toString();
		}

		setAlertMessage(request, getMessage(request, "lecture.message.join.score.add.success"));

		//목록 화면으로
		return "redirect:"+ new URLBuilder("/lec/participate/listMain")
				.addParameter("participateVO.crsCreCd", crsCreCd)
				.addParameter("participateVO.searchKey", vo.getSearchKey())
				.addParameter("participateVO.searchValue", vo.getSearchValue())
				.addParameter("curPage", vo.getCurPage())
				.toString();
	}

}
