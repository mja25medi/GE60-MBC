package egovframework.edutrack.web.manage.etc;

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
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.etc.bkgimg.service.BkgImgService;
import egovframework.edutrack.modules.etc.bkgimg.service.BkgImgVO;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/etc/bkgImg")
public class BkgImgManageController extends GenericController {

	@Autowired @Qualifier("bkgImgService")
	private BkgImgService bkgImgService;

	/**
	 * 배경이미지 관리 메인
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/main")
	public String main(BkgImgVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);

		request.setAttribute("vo", vo);
		request.setAttribute("paging", "Y");

		return "mng/etc/bkgimg/bkgimg_main";
	}

	/**
	 * 배경이미지 목록
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(BkgImgVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);


		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultListVO<BkgImgVO> resultList = bkgImgService.listPageing(vo);

		request.setAttribute("bkgImgList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());

		return "mng/etc/bkgimg/bkgimg_list_div";
	}



	/**
	 * 배경이미지 등록 폼
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addPop")
	public String addPop(BkgImgVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);


		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		request.setAttribute("fileupload", "Y");

		return "mng/etc/bkgimg/bkgimg_write_pop";
	}

	/**
	 * 배경이미지 수정 폼
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editPop")
	public String editPop(BkgImgVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);


		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		vo = bkgImgService.view(vo).getReturnVO();

		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		request.setAttribute("fileupload", "Y");

		return "mng/etc/bkgimg/bkgimg_write_pop";
	}

	/**
	 * 배경이미지 등록
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response`	`
	 * @return json
	 */
	@RequestMapping(value="/add")
	public String add(BkgImgVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);


		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<BkgImgVO> resultVO =bkgImgService.add(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "etc.message.bkgimg.add.success"));
			resultVO.setResult(1);
		} else {
			resultVO.setMessage(getMessage(request, "etc.message.bkgimg.add.failed"));
			resultVO.setResult(-1);
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 배경이미지 수정
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/edit")
	public String edit(BkgImgVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);


		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<BkgImgVO> resultVO = bkgImgService.edit(vo);

		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "etc.message.bkgimg.edit.success"));
		} else {
			resultVO.setMessage(getMessage(request, "etc.message.bkgimg.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 배경이미지 순서 변경폼
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sortPop")
	public String sortPop(BkgImgVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);


		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultListVO<BkgImgVO> resultList = bkgImgService.list(vo);

		
		request.setAttribute("vo", vo);
		request.setAttribute("bkgImgList", resultList.getReturnList());
		request.setAttribute("gubun", "E");

		return "mng/etc/bkgimg/bkgimg_sort_pop";
	}

	/**
	 * 배경이미지 순서변경
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sort")
	public String sort(BkgImgVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);


		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<?> resultVO = bkgImgService.sort(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "etc.message.bkgimg.sort.success"));
		} else {
			resultVO.setMessage(getMessage(request, "etc.message.bkgimg.sort.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 배경이미지 삭제
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/delete")
	public String delete(BkgImgVO vo, Map commandMap, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<?> resultVO = bkgImgService.remove(vo);

		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "etc.message.bkgimg.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "etc.message.bkgimg.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}


}
