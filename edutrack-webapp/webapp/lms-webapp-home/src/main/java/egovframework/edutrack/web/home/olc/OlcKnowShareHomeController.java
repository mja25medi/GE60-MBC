package egovframework.edutrack.web.home.olc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.comm.service.JsTreeVO;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.JsTreeUtil;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.olc.olccart.service.OlcCartrgService;
import egovframework.edutrack.modules.olc.olccnts.service.OlcCntsService;
import egovframework.edutrack.modules.olc.olcctgr.service.OlcCtgrService;
import egovframework.edutrack.modules.olc.olcctgr.service.OlcCtgrVO;
import egovframework.edutrack.modules.olc.sharectgr.service.OlcShareCtgrService;
import egovframework.edutrack.modules.olc.sharectgr.service.OlcShareCtgrVO;
import egovframework.edutrack.modules.olc.sharerel.service.OlcShareRelService;
import egovframework.edutrack.modules.olc.sharerel.service.OlcShareRelVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/home/olc/knowShare")
public class OlcKnowShareHomeController extends GenericController{

	@Autowired @Qualifier("olcCtgrService")
	private OlcCtgrService 	olcCtgrService;

	@Autowired @Qualifier("olcCartrgService")
	private OlcCartrgService 	olcCartrgService;

	@Autowired @Qualifier("olcCntsService")
	private OlcCntsService 	olcCntsService;

	@Autowired @Qualifier("olcShareCtgrService")
	private OlcShareCtgrService 	olcShareCtgrService;

	@Autowired @Qualifier("olcShareRelService")
	private OlcShareRelService 	olcShareRelService;

	/**
	 * OLC 지식공유 카트리지 목록 리스트
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/main")
	public String main(OlcShareRelVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		OlcShareCtgrVO oscVO = new OlcShareCtgrVO();
		oscVO.setOrgCd(orgCd);
		oscVO.setCtgrDivCd("K");

		ProcessResultListVO<OlcShareRelVO> resultList = olcShareRelService.listPageingByCtgrKnow(vo);

    	request.setAttribute("olcShareRelList", resultList.getReturnList());
    	request.setAttribute("pageInfo", resultList.getPageInfo());
    	request.setAttribute("vo", vo);

		return "/home/olc/know/know_main";
	}


	/**
	 * 분류 목록 (JsTree)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listCtgrJsTree")
	public String listCtgrJsTree(OlcShareCtgrVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		String id = StringUtil.nvl(request.getParameter("id"));

		//-- 최상외 접속시 리턴
		if("#".equals(id)) {
			//회원의 정보를 가져와 최상위 node에 입력
			JsTreeVO treeVO = JsTreeUtil.getJsTreeVO("root", "ROOT", "root", 1, new OlcCtgrVO());
			return JsonUtil.responseJson(response, treeVO);
		} else {
			if(id.equals("root")) id = "";
			OlcShareCtgrVO oscVO = new OlcShareCtgrVO();
			oscVO.setOrgCd(orgCd);
			oscVO.setParCtgrCd(id);
			oscVO.setCtgrDivCd(vo.getCtgrDivCd());
			List<OlcShareCtgrVO> listCtgr = olcShareCtgrService.listSub(oscVO).getReturnList();

			List<JsTreeVO> treeList = new ArrayList<JsTreeVO>();
			String firstCd = "";
			String lastCd = "";
			int i = 0;
			for(OlcShareCtgrVO VO : listCtgr) {
				if(i == 0) {
					firstCd = VO.getCtgrCd();
				}
				i++;
				lastCd = VO.getCtgrCd();
			}
			for(OlcShareCtgrVO VO : listCtgr) {
				String rel = "contents";
				if(VO.getSubCnt() > 0) rel = "category";
				VO.setFirstCd(firstCd);
				VO.setLastCd(lastCd);
				JsTreeVO treeVO = JsTreeUtil.getJsTreeVO(VO.getCtgrCd(), VO.getCtgrNm(), rel, VO.getSubCnt(), VO);
				treeList.add(treeVO);
			}
			return JsonUtil.responseJson(response, treeList);
		}
	}



}
