package egovframework.edutrack.web.manage.course;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.edutrack.comm.exception.AjaxProcessResultFailedException;
import egovframework.edutrack.comm.service.JsTreeVO;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.web.GenericController;
import egovframework.edutrack.modules.course.subject.service.LecRoomVO;
import egovframework.edutrack.modules.course.subject.service.OfflineSubjectVO;
import egovframework.edutrack.modules.course.subject.service.OnlineSubjectVO;
import egovframework.edutrack.modules.course.subject.service.SubjectCategoryVO;
import egovframework.edutrack.modules.course.subject.service.SubjectService;


@Controller
@SuppressWarnings("rawtypes")
@RequestMapping(value="/mng/course/subject")
public class CourseSubjectManageController extends GenericController{

	private static final String OFFLINE_MAIN = "offline_main";
	private static final String OFFLINE_LIST = "offline_list";
	private static final String OFFLINE_WRITE = "offline_write";
	private static final String ONLINE_MAIN = "online_main";
	private static final String ONLINE_LIST = "online_list";
	private static final String ONLINE_WRITE = "online_write";

	@Autowired @Qualifier("subjectService")
	private SubjectService	subjectService;


	/**
	 * 과목 분류 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/ctgrMain")
	public String mainCtgr( SubjectCategoryVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);
		
		request.setAttribute("vo", vo);
		return "mng/course/subject/ctgr_main";
	}

	/**
	 * 과목 분류 목록 조회
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/listCtgr")
	public String listCtgr( SubjectCategoryVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setUseYn("");
		vo.setOrgCd(orgCd);

		ProcessResultListVO<SubjectCategoryVO> result = subjectService.listCategory(vo);
		request.setAttribute("subjectCategoryList", result.getReturnList());
		return "mng/course/subject/ctgr_list_div";
	}

	/**
	 * 과목 분류 목록 조회
	 * @param request
	 * @return List<JsTreeVO>
	 */
	@RequestMapping(value="/listCtgrJsTree")
	public String listCtgrJsTree( Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sbjCtgrTypeCd = StringUtil.nvl(request.getParameter("sbjCtgrTypeCd"));
		String orgCd = UserBroker.getUserOrgCd(request);
		String id = StringUtil.nvl(request.getParameter("id"));
		SubjectCategoryVO iVO = new SubjectCategoryVO();
		iVO.setSbjCtgrTypeCd(sbjCtgrTypeCd);
		iVO.setParSbjCtgrCd(id);
		iVO.setOrgCd(orgCd);
		iVO.setUseYn("Y");
		List<SubjectCategoryVO> list = subjectService.listCategorySub(iVO).getReturnList();
		List<JsTreeVO> treeList = new ArrayList<JsTreeVO>();
		for(SubjectCategoryVO VO : list) {
			String rel = "contents";
			if(VO.getSubCnt() > 0) rel = "category";
			JsTreeVO treeVO = new JsTreeVO();
			treeVO.setData(VO.getSbjCtgrNm());
			treeVO.setState(VO.getSubCnt());
			treeVO.addAttr("id", VO.getSbjCtgrCd());
			treeVO.addAttr("title", VO.getSbjCtgrNm());
			treeVO.addAttr("rel", rel);
			treeVO.addAttr(VO);
			treeList.add(treeVO);
		}
		return JsonUtil.responseJson(response, treeList);
	}

	/**
	 * 과목 분류 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addFormCtgrPop")
	public String addFormCtgrPop( SubjectCategoryVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		commonVOProcessing(vo, request);
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		return "mng/course/subject/ctgr_write_pop";
	}

	/**
	 * 하위 과목 분류 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addFormCtgrSubPop")
	public String addFormCtgrSubPop( SubjectCategoryVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		SubjectCategoryVO subjectCategoryVO = new SubjectCategoryVO();
		subjectCategoryVO.setSbjCtgrTypeCd(vo.getSbjCtgrTypeCd());
		subjectCategoryVO.setSbjCtgrCd(vo.getParSbjCtgrCd());
		//---- 상위 메뉴의 정보를 가져온다.
		ProcessResultVO<SubjectCategoryVO> resultVO = subjectService.viewCategory(subjectCategoryVO);
		subjectCategoryVO = resultVO.getReturnVO();
		vo.setParSbjCtgrCd(subjectCategoryVO.getSbjCtgrCd());
		vo.setParCtgrLvl(subjectCategoryVO.getCtgrLvl());
		vo.setParSbjCtgrNm(subjectCategoryVO.getSbjCtgrNm());
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		return "mng/course/subject/ctgr_write_pop";
	}

	/**
	 * 과목 분류 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addCtgr")
	public String addCtgr( SubjectCategoryVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<SubjectCategoryVO> resultVO = subjectService.addCategory(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.subject.category.add.success"));
		}else{
			resultVO.setMessage(getMessage(request, "course.message.subject.category.add.failed"));
		}

		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 과목 분류 수정 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editFormCtgrPop")
	public String editFormCtgrPop( SubjectCategoryVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		
		ProcessResultVO<SubjectCategoryVO> resultVO = subjectService.viewCategory(vo);
		vo = resultVO.getReturnVO();
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		return "mng/course/subject/ctgr_write_pop";
	}

	/**
	 * 과목 분류 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editCtgr")
	public String editCtgr( SubjectCategoryVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<SubjectCategoryVO> resultVO = subjectService.editCategory(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.subject.category.edit.success"));
		}else{
			resultVO.setMessage(getMessage(request, "course.message.subject.category.edit.failed"));
		}

		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 과목 분류 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/deleteCtgr")
	public String deleteCtgr( SubjectCategoryVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<?> resultVO = subjectService.deleteCategory(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.subject.category.delete.success"));
		}else{
			resultVO.setMessage(getMessage(request, "course.message.subject.category.delete.failed"));
		}

		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 과목 분류 순서 변경 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sortFormCtgrPop")
	public String sortFormCtgrPop( SubjectCategoryVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		//---- 과목 분류 목록 조회
		ProcessResultListVO<SubjectCategoryVO> resultListVO = subjectService.listCategorySort(vo);
		request.setAttribute("subjectCategoryList", resultListVO.getReturnList());
		request.setAttribute("gubun", "E");
		request.setAttribute("vo", vo);
		return "mng/course/subject/ctgr_sort_pop";
	}

	/**
	 * 하위 과목 분류 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listCtgrSub")
	public String listCtgrSub( SubjectCategoryVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultListVO<SubjectCategoryVO> resultVO = subjectService.listCategorySub(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.subject.sort.success"));
		} else {
			resultVO.setMessage(getMessage(request, "course.message.subject.sort.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 과목 분류 순서변경
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/sortCtgr")
	public String sortCtgr( SubjectCategoryVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<?> resultVO = subjectService.sortCategory(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.subject.category.sort.success"));
		}else{
			resultVO.setMessage(getMessage(request, "course.message.subject.category.sort.failed"));
		}

		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 오프라인 과목 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/offlineMain")
	public String mainOffline( Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) {
		//-- 페이지 권한 체크 및 등록자 번호 셋팅
		SubjectCategoryVO subjectCategoryVO = new SubjectCategoryVO();
		subjectCategoryVO.setSbjCtgrTypeCd("OF");
		subjectCategoryVO.setUseYn("Y");
		//List<SubjectCategoryVO> categoryList = subjectService.listCategory(subjectCategoryVO).getReturnList();
		//request.setAttribute("categoryList", categoryList);
		request.setAttribute("paging", "Y");
		request.setAttribute("jstree", "Y");		
		return  "mng/course/subject/offline_main";
	}

	/**
	 * 오프라인 과목 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listOffline")
	public String listOffline( OfflineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultListVO<OfflineSubjectVO> resultList = subjectService.listOfflinePageing(vo, vo.getCurPage(), vo.getListScale());

		request.setAttribute("offlineSubjectVO", vo);
		request.setAttribute("offlineSubjectList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		return "mng/course/subject/offline_list_div";
	}

	/**
	 * 오프라인 과목 목록 조회(과목 연결 서치용)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listOfflineSearch")
	public String listOfflineSearch( OfflineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		return JsonUtil
			.responseJson(response, subjectService.listOfflineSearch(vo));
	}

	/**
	 * 오프라인 과목 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addFormOfflinePop")
	public String addFormOfflinePop( OfflineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		vo.setUseYn("Y");

		SubjectCategoryVO subjectCategoryVO = new SubjectCategoryVO();
		subjectCategoryVO.setOrgCd(orgCd);
		subjectCategoryVO.setSbjCtgrTypeCd("OF");
		subjectCategoryVO.setUseYn("Y");
		List<SubjectCategoryVO> categoryList = subjectService.listCategory(subjectCategoryVO).getReturnList();
		request.setAttribute("categoryList", categoryList);
		
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		
		return "mng/course/subject/offline_write_pop";
	}

	/**
	 * 오프라인 과목 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addOffline")
	public String addOffline( OfflineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<OfflineSubjectVO> resultVO = null;
		try {
			resultVO = subjectService.addOffline(vo);
			if(resultVO.getResult() > 0) {
				resultVO.setMessage(getMessage(request, "course.message.subject.add.success"));
			}else{
				resultVO.setMessage(getMessage(request, "course.message.subject.add.failed"));
			}
		} catch (DuplicateKeyException e) {
			throw new AjaxProcessResultFailedException(getMessage(request, "course.message.subject.alert.dupcode"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 오프라인 과목 수정 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editFormOfflinePop")
	public String editFormOfflinePop( OfflineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);

		ProcessResultVO<OfflineSubjectVO> resultVO = subjectService.viewOffline(vo);
		vo = resultVO.getReturnVO();
		SubjectCategoryVO subjectCategoryVO = new SubjectCategoryVO();
		subjectCategoryVO.setOrgCd(orgCd);
		subjectCategoryVO.setSbjCtgrTypeCd("OF");
		subjectCategoryVO.setUseYn("Y");
		List<SubjectCategoryVO> categoryList = subjectService.listCategory(subjectCategoryVO).getReturnList();
		request.setAttribute("categoryList", categoryList);
		
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");		
		return "mng/course/subject/offline_write_pop";
	}

	/**
	 * 오프라인 과목 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editOffline")
	public String editOffline( OfflineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<OfflineSubjectVO> resultVO = subjectService.editOffline(vo);
		if(resultVO.getResult() > 0) {
			resultVO.setMessage(getMessage(request, "course.message.subject.edit.success"));
		}else{
			resultVO.setMessage(getMessage(request, "course.message.subject.edit.failed"));
		}

		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 오프라인 과목 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/deleteOffline")
	public String deleteOffline( OfflineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		ProcessResultVO<?> resultVO = null;
		boolean isException = false;
		try{
			resultVO = subjectService.deleteOffline(vo);
		}catch(Exception e){
			isException = true;
			resultVO = ProcessResultVO.failed();
			resultVO.setMessage(getMessage(request, "course.message.already.course.registered"));
		}
		if(!isException){
			if(resultVO.getResult() > 0) {
				resultVO.setMessage(getMessage(request, "course.message.subject.delete.success"));
			}else{
				resultVO.setMessage(getMessage(request, "course.message.subject.delete.failed"));
			}
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 과목 관리 메인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/onlineMain")
	public String mainOnline( Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		SubjectCategoryVO subjectCategoryVO = new SubjectCategoryVO();
		//subjectCategoryVO.setSbjCtgrTypeCd("ON");
		subjectCategoryVO.setUseYn("Y");
		//List<SubjectCategoryVO> categoryList = subjectService.listCategory(subjectCategoryVO).getReturnList();
		//request.setAttribute("categoryList", categoryList);
		request.setAttribute("paging", "Y");
		request.setAttribute("jstree", "Y");
		return "mng/course/subject/online_main";
	}
	
	/**
	 * 테스트 메인 - 강의실 관리
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/lecRoomMain")
	public String lecRoomMain( Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		
		LecRoomVO lecRoomVO = new LecRoomVO();
		
		lecRoomVO.setUseYn("Y");
		request.setAttribute("paging", "Y");
		
		return "mng/course/subject/lec_room_main";
	}
	
	/**
	 * 강의실 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listLecRoom")
	public String listLecRoom( LecRoomVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultListVO<LecRoomVO> resultList = subjectService.listLecRoomPageing(vo, vo.getCurPage(), vo.getListScale());

		request.setAttribute("lecRoomList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("vo", vo);
		return "mng/course/subject/lec_room_list_div";
	}
	
	/**
	 * 강의실 관리 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addFormLecRoomPop")
	public String addFormLecRoomPop(LecRoomVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {

		request.setAttribute("gubun", "A");
		request.setAttribute("vo", vo);
		 
		return "mng/course/subject/lec_room_write_pop";
	}
	
	/**
	 * 강의실 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addLecRoom")
	public String addLecRoom( LecRoomVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<LecRoomVO> resultVO = new ProcessResultVO<>();
		
		try {
			resultVO = subjectService.addLecRoom(vo);
			resultVO.setMessage(getMessage(request, "course.message.lecroom.add.success"));
		} catch (Exception e) {
			resultVO.setResultFailed();
			resultVO.setMessage(getMessage(request, "course.message.lecroom.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 강의실 관리 수정 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editFormLecRoomPop")
	public String editFormLecRoomPop(LecRoomVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {

		ProcessResultVO<LecRoomVO> resultVO = subjectService.viewLecRoom(vo);
		vo = resultVO.getReturnVO();
		request.setAttribute("gubun", "E");
		request.setAttribute("vo", vo);
		 
		return "mng/course/subject/lec_room_write_pop";
	}
	
	/**
	 * 강의실 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editLecRoom")
	public String editLecRoom( LecRoomVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<LecRoomVO> resultVO = new ProcessResultVO<>();
		
		try {
			resultVO = subjectService.editLecRoom(vo);
			resultVO.setMessage(getMessage(request, "course.message.lecroom.edit.success"));
		} catch (Exception e) {
			resultVO.setResultFailed();
			resultVO.setMessage(getMessage(request, "course.message.lecroom.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}
	
	/**
	 * 강의실 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/deleteLecRoom")
	public String deleteLecRoom( LecRoomVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		ProcessResultVO<?> resultVO = new ProcessResultVO<>();
		try {
			resultVO = subjectService.deleteLecRoom(vo);
			resultVO.setMessage(getMessage(request, "course.message.lecroom.delete.success"));
		} catch (Exception e) {
			resultVO.setResultFailed();
			resultVO.setMessage(getMessage(request, "course.message.lecroom.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}
	

	/**
	 * 과목 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listOnline")
	public String listOnline( OnlineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultListVO<OnlineSubjectVO> resultList = subjectService.listOnlinePageing(vo, vo.getCurPage(), vo.getListScale());

		request.setAttribute("onlineSubjectList", resultList.getReturnList());
		request.setAttribute("pageInfo", resultList.getPageInfo());
		request.setAttribute("vo", vo);
		return "mng/course/subject/online_list_div";
	}

	/**
	 * 온라인 과목 목록 조회(과목 연결 서치용)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listOnlineSearch")
	public String listOnlineSearch( OnlineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		return JsonUtil
			.responseJson(response, subjectService.listOnlineSearch(vo));
	}
	
	/**
	 * 온라인 과목 목록 조회(과목 연결 서치용)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listOnlineJson")
	public String listOnlineJson( OnlineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);
		
		return JsonUtil
				.responseJson(response, subjectService.listOnline(vo));
	}

	/**
	 * 온라인 과목 목록 조회(공개과정 과목 검색 용)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listOnlineSearchOpen")
	public String listOnlineSearchOpen( OnlineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		return JsonUtil
			.responseJson(response, subjectService.listOnlineSearchOpen(vo));
	}

	/**
	 * 온라인 과목 등록 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addFormOnlinePop")
	public String addFormOnlinePop( OnlineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {

		String orgCd = UserBroker.getUserOrgCd(request);

		SubjectCategoryVO subjectCategoryVO = new SubjectCategoryVO();
		subjectCategoryVO.setOrgCd(orgCd);
		subjectCategoryVO.setSbjCtgrTypeCd("ON");
		subjectCategoryVO.setUseYn("Y");

		List<SubjectCategoryVO> categoryList = subjectService.listCategory(subjectCategoryVO).getReturnList();
		request.setAttribute("categoryList", categoryList);

		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "A");
		request.setAttribute("fileupload", "Y");
		return "mng/course/subject/online_write_pop";
	}

	/**
	 * 온라인 과목 등록
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/addOnline")
	public String addOnline( OnlineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<OnlineSubjectVO> resultVO = new ProcessResultVO<>();
		try {
			resultVO = subjectService.addOnline(vo);
			resultVO.setMessage(getMessage(request, "course.message.subject.add.success"));
		} catch (DuplicateKeyException e) {
			throw new AjaxProcessResultFailedException(getMessage(request, "course.message.subject.alert.dupcode"));
		} catch (Exception e) {
			resultVO.setResultFailed();
			resultVO.setMessage(getMessage(request, "course.message.subject.add.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 온라인 과목 수정 폼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editFormOnlinePop")
	public String editFormOnlinePop( OnlineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);

		SubjectCategoryVO subjectCategoryVO = new SubjectCategoryVO();
		subjectCategoryVO.setOrgCd(orgCd);
		subjectCategoryVO.setSbjCtgrTypeCd("ON");
		subjectCategoryVO.setUseYn("Y");
		List<SubjectCategoryVO> categoryList = subjectService.listCategory(subjectCategoryVO).getReturnList();
		request.setAttribute("categoryList", categoryList);

		ProcessResultVO<OnlineSubjectVO> resultVO = subjectService.viewOnline(vo);
		vo = resultVO.getReturnVO();
		request.setAttribute("vo", vo);
		request.setAttribute("gubun", "E");
		return "mng/course/subject/online_write_pop";
	}

	/**
	 * 온라인 과목 수정
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/editOnline")
	public String editOnline( OnlineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<OnlineSubjectVO> resultVO = new ProcessResultVO<>();
		try {
			resultVO = subjectService.editOnline(vo);
			resultVO.setMessage(getMessage(request, "course.message.subject.edit.success"));
		} catch (Exception e) {
			resultVO.setResultFailed();
			resultVO.setMessage(getMessage(request, "course.message.subject.edit.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 온라인 과목 삭제
	 *
	 * @return  ProcessResultVO
	 */
	@RequestMapping(value="/deleteOnline")
	public String deleteOnline( OnlineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);

		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultVO<?> resultVO = new ProcessResultVO<>();
		try {
			resultVO = subjectService.deleteOnline(vo);
			resultVO.setMessage(getMessage(request, "course.message.subject.delete.success"));
		} catch (Exception e) {
			resultVO.setResultFailed();
			resultVO.setMessage(getMessage(request, "course.message.subject.delete.failed"));
		}
		return JsonUtil.responseJson(response, resultVO);
	}

	/**
	 * 과목분류 조회(온라인 오프라인 과목 분류시 사용)
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listOnOffCategory")
	public String listOnOffCategory( SubjectCategoryVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		String parCrsCtgrCd = request.getParameter("parCtgrCd");
		vo.setParSbjCtgrCd(parCrsCtgrCd);
		vo.setOrgCd(orgCd);
		return JsonUtil
				.responseJson(response, subjectService.listOnOffCategory(vo));
	}


	/**
	 * 사용과정정보 - 교육과정-온라인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewSubInfoPop")
	public String viewSubInfoPop( OnlineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) {
		commonVOProcessing(vo, request);
		request.setAttribute("vo", vo);
		return "mng/course/subject/online_use_sub_Info";
	}

	/**
	 * 사용과정정보 - 교육과정-오프라인
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/viewSubInfoOfflinePop")
	public String viewSubInfoOffline( OfflineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request,	HttpServletResponse response) {
		commonVOProcessing(vo, request);
		request.setAttribute("vo", vo);

		return "mng/course/subject/offline_use_sub_Info";
	}


	/**
	 * 온라인 과목 목록 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/listOnlineForCoursePop")
	public String listOnlineForCoursePop( OnlineSubjectVO vo, Map commandMap, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		commonVOProcessing(vo, request);
		
		String orgCd = UserBroker.getUserOrgCd(request);
		vo.setOrgCd(orgCd);

		ProcessResultListVO<OnlineSubjectVO> resultList = subjectService.listOnline(vo);

		request.setAttribute("onlineSubjectList", resultList.getReturnList());
		request.setAttribute("vo", vo);
		return "mng/course/subject/online_list_for_course_list";
	}
}
