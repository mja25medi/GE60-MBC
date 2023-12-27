package egovframework.edutrack.web.manage.course;

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
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.category.service.CourseCategoryService;
import egovframework.edutrack.modules.course.category.service.CourseCategoryVO;


@Controller
@RequestMapping(value="/mng/course/category")
public class CourseCategoryManageController extends GenericController{

	@Autowired @Qualifier("courseCategoryService")
	private CourseCategoryService	courseCategoryService;


	/**
	 * 과정 분류 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/main")
	public String main( CourseCategoryVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);
		request.setAttribute("vo", vo);
		return "mng/course/category/ctgr_main";
	}

	/**
	 * 과정 분류 관리 메인_팝업
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/mainPop")
	public String mainPop( CourseCategoryVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);

		request.setAttribute("vo", vo);
		return "mng/course/category/ctgr_main_pop";
	}

	/**
	 * 과정 분류 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list( CourseCategoryVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);

		ProcessResultListVO<CourseCategoryVO> result = courseCategoryService.listCategory(orgCd, vo.getParCrsCtgrCd());
		request.setAttribute("courseCategoryList", result.getReturnList());
		return "mng/course/category/ctgr_list_div";
	}

	/**
	 * 과정 분류 목록 조회 (Json 반환)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listCategory")
	public String listCategory( Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String orgCd = UserBroker.getUserOrgCd(request);
		String parCrsCtgrCd = request.getParameter("parCrsCtgrCd");

		return JsonUtil
			.responseJson(response, courseCategoryService.listCategory(orgCd, parCrsCtgrCd));
	}

	/**
	 * 과정 분류 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addFormCategoryPop")
	public String addFormCategoryPop( CourseCategoryVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);
		vo.setParCrsCtgrNm(getMessage(request,"course.title.category.root"));

		request.setAttribute("isPop", StringUtil.nvl(request.getParameter("isPop"), "N"));
		request.setAttribute("gubun", "A");
		request.setAttribute("vo", vo);
		return "mng/course/category/ctgr_write_pop";
	}

	/**
	 * 하위 과정 분류 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addFormCategorySubPop")
	public String addFormCategorySubPop( CourseCategoryVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response ) throws Exception {
		commonVOProcessing(vo, request);
		
		CourseCategoryVO courseCategoryVO = new CourseCategoryVO();

		//---- 상위 메뉴의 정보를 가져온다.
		ProcessResultVO<CourseCategoryVO> resultVO = courseCategoryService.viewCategory(vo.getParCrsCtgrCd());
		courseCategoryVO = resultVO.getReturnVO();
		vo.setParCrsCtgrCd(courseCategoryVO.getCrsCtgrCd());
		vo.setParCrsCtgrLvl(courseCategoryVO.getCrsCtgrLvl());
		vo.setParCrsCtgrNm(courseCategoryVO.getCrsCtgrNm());
		vo.setParCrsCtgrPath(courseCategoryVO.getCrsCtgrPath());
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");

		request.setAttribute("isPop", StringUtil.nvl(request.getParameter("isPop"), "N"));

		return "mng/course/category/ctgr_write_pop";
	}

	/**
	 * 과정 분류 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addCategory")
	public String addCategory( CourseCategoryVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<CourseCategoryVO> resultVO = courseCategoryService.addCategory(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.category.add.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.category.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 과정 분류 수정 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editFormCategoryPop")
	public String editFormCategoryPop(CourseCategoryVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		
		System.out.println(vo.getCrsCtgrCd() + "===============");
		ProcessResultVO<CourseCategoryVO> resultVO = courseCategoryService.viewCategory(vo.getCrsCtgrCd());
		vo = resultVO.getReturnVO();
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		request.setAttribute("isPop", StringUtil.nvl(request.getParameter("isPop"), "N"));

		return "mng/course/category/ctgr_write_pop";
	}

	/**
	 * 과정 분류 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editCategory")
	public String editCategory(CourseCategoryVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<CourseCategoryVO> resultVO = courseCategoryService.editCategory(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.category.edit.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.category.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 과정 분류 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/deleteCategory")
	public String deleteCategory(CourseCategoryVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<?> resultVO = courseCategoryService.deleteCategory(vo.getCrsCtgrCd());
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.category.delete.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.category.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 과정 분류 순서 변경 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sortFormCategoryPop")
	public String sortFormCategoryPop(CourseCategoryVO vo,  Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String orgCd = UserBroker.getUserOrgCd(request);
		//---- 과정 분류 목록 조회
		ProcessResultListVO<CourseCategoryVO> resultListVO = courseCategoryService.listCategorySort(orgCd, "");
		request.setAttribute("courseCategoryList", resultListVO.getReturnList());
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		request.setAttribute("isPop", StringUtil.nvl(request.getParameter("isPop"), "N"));

		return "mng/course/category/ctgr_sort_pop";
	}

	/**
	 * 하위 과정 분류 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listCategorySub")
	public String listCategorySub( Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String parCrsCtgrCd = request.getParameter("parCrsCtgrCd");
		String orgCd = UserBroker.getUserOrgCd(request);
		return JsonUtil
			.responseJson(response, courseCategoryService.listCategorySub(orgCd, parCrsCtgrCd));
	}

	/**
	 * 과정 분류 목록 (JsTree)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/listCategoryJsTree")
	public String listCategoryJsTree( Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String orgCd = UserBroker.getUserOrgCd(request);
		String id = StringUtil.nvl(request.getParameter("id"));
		List<CourseCategoryVO> list = courseCategoryService.listCategorySub(orgCd, id).getReturnList();
		List<JsTreeVO> treeList = new ArrayList<JsTreeVO>();
		for(CourseCategoryVO VO : list) {
			String rel = "contents";
			if(VO.getSubCnt() > 0) rel = "category";
			JsTreeVO treeVO = new JsTreeVO();
			treeVO.setData(VO.getCrsCtgrNm());
			treeVO.setState(VO.getSubCnt());
			treeVO.addAttr("id", VO.getCrsCtgrCd());
			treeVO.addAttr("title", VO.getCrsCtgrNm());
			treeVO.addAttr("rel", rel);
			treeVO.addAttr(VO);
			treeList.add(treeVO);
		}
		return JsonUtil.responseJson(response, treeList);
	}

	/**
	 * 과정 분류 순서변경
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/sortCategory")
	public String sortCategory( CourseCategoryVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response ) throws Exception {
		commonVOProcessing(vo, request);
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<?> resultVO = courseCategoryService.sortCategory(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.category.sort.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.category.sort.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}
}
