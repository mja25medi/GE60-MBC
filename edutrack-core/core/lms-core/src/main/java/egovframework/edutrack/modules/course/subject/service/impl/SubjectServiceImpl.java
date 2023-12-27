package egovframework.edutrack.modules.course.subject.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.annotation.HrdApiCrsOnlnSbj;
import egovframework.edutrack.comm.annotation.HrdApiCrsOnlnSbj.SyncType;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.FileUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.course.subject.service.LecRoomVO;
import egovframework.edutrack.modules.course.subject.service.OfflineSubjectVO;
import egovframework.edutrack.modules.course.subject.service.OnlineSubjectVO;
import egovframework.edutrack.modules.course.subject.service.SubjectCategoryVO;
import egovframework.edutrack.modules.course.subject.service.SubjectService;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Service("subjectService")
public class SubjectServiceImpl extends EgovAbstractServiceImpl implements SubjectService {

	private final class NestedThumbFileHandler
		implements FileHandler<OnlineSubjectVO> {

		@Override
		public String getPK(OnlineSubjectVO vo) {
			return vo.getSbjCd().toString();
		}
	
		@Override
		public String getRepoCd() {
			return "SBJ_THUMB";
		}
	
		@Override
		public List<SysFileVO> getFiles(OnlineSubjectVO vo) {
			List<SysFileVO> fileList = new ArrayList<SysFileVO>();
			if(ValidationUtils.isNotZeroNull(vo.getThumbFileSn()))
				fileList.add(vo.getThumbFile());
			return fileList;
		}
	
		@Override
		public OnlineSubjectVO setFiles(OnlineSubjectVO vo, FileListVO fileListVO) {
			vo.setThumbFile(fileListVO.getFile("thumb"));
			return vo;
		}
	}
	
	private final class NestedPlanFileHandler
	implements FileHandler<OnlineSubjectVO> {
		
		@Override
		public String getPK(OnlineSubjectVO vo) {
			return vo.getSbjCd().toString();
		}
		
		@Override
		public String getRepoCd() {
			return "SBJ_PLAN";
		}
		
		@Override
		public List<SysFileVO> getFiles(OnlineSubjectVO vo) {
			List<SysFileVO> fileList = new ArrayList<SysFileVO>();
			fileList.addAll(vo.getAttachFiles());
			return fileList;
		}
		
		@Override
		public OnlineSubjectVO setFiles(OnlineSubjectVO vo, FileListVO fileListVO) {
			vo.setAttachFiles(fileListVO.getFiles("file"));
			return vo;
		}
	}
	
	/** Mapper */
	@Resource(name="subjectCategoryMapper")
	private SubjectCategoryMapper subjectCategoryMapper;
	@Resource(name="subjectOnlineMapper")
	private SubjectOnlineMapper subjectOnlineMapper;
	@Resource(name="subjectOfflineMapper")
	private SubjectOfflineMapper subjectOfflineMapper;
	@Resource(name="sysFileService")
	private SysFileService sysFileService;

	/**
	 * 과목 분류 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<SubjectCategoryVO> listCategory(SubjectCategoryVO iSubjectCategoryVO) throws Exception {
		ProcessResultListVO<SubjectCategoryVO> resultList = new ProcessResultListVO<SubjectCategoryVO>();
		try {
			List<SubjectCategoryVO> returnList = new ArrayList<SubjectCategoryVO>();
			
			if("mysql".equals(Constants.DATABASE_DB_TYPE) && "5".equals(Constants.DATABASE_DB_VERSION)) {
				returnList = subjectCategoryMapper.listVer5(iSubjectCategoryVO);
			}else {
				returnList = subjectCategoryMapper.list(iSubjectCategoryVO);
			}
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}


	/**
	 * 과목 분류 목록 조회 (하위 과목 분류 있는 목록만)
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<SubjectCategoryVO> listCategorySort(SubjectCategoryVO iSubjectCategoryVO) throws Exception {
		ProcessResultListVO<SubjectCategoryVO> resultList = new ProcessResultListVO<SubjectCategoryVO>();
		try {
			List<SubjectCategoryVO> returnList = subjectCategoryMapper.listSort(iSubjectCategoryVO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	/**
	 * 하위 과목 분류 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<SubjectCategoryVO> listCategorySub(SubjectCategoryVO iSubjectCategoryVO) throws Exception {
		ProcessResultListVO<SubjectCategoryVO> resultList = new ProcessResultListVO<SubjectCategoryVO>();
		try {
			List<SubjectCategoryVO> returnList = subjectCategoryMapper.listSub(iSubjectCategoryVO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	/**
	 * 과목 분류 정보 조회
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<SubjectCategoryVO> viewCategory(SubjectCategoryVO iSubjectCategoryVO)  throws Exception {
		ProcessResultVO<SubjectCategoryVO> resultVO = new ProcessResultVO<SubjectCategoryVO>();
		try {
			SubjectCategoryVO returnVO = subjectCategoryMapper.select(iSubjectCategoryVO);
			resultVO.setReturnVO(returnVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}

	/**
	 * 과목 분류 등록
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<SubjectCategoryVO> addCategory(SubjectCategoryVO iSubjectCategoryVO) throws Exception {
		ProcessResultVO<SubjectCategoryVO> resultVO = new ProcessResultVO<SubjectCategoryVO>();
		try {
			//---- 상위 분류 정보 조회
			SubjectCategoryVO VO = new SubjectCategoryVO();
			try {
				if(StringUtil.nvl(iSubjectCategoryVO.getParSbjCtgrCd()).equals("")==false) {
					VO.setSbjCtgrCd(iSubjectCategoryVO.getParSbjCtgrCd());
					VO = subjectCategoryMapper.select(VO);
				} else {
					VO.setSbjCtgrPath("");
					VO.setSbjCtgrPathNm("");
				}
			} catch (Exception e) {
				VO.setSbjCtgrPath("");
				VO.setSbjCtgrPathNm("");
			}
			//---- 분류 레벨 : 상위 분류 레벨 + 1
			iSubjectCategoryVO.setCtgrLvl(StringUtil.nvl(iSubjectCategoryVO.getParCtgrLvl(), 0)+1);
			//---- 상위 분류 경로 및 경로명 셋팅
			iSubjectCategoryVO.setSbjCtgrPath(VO.getSbjCtgrPath()+"/"+iSubjectCategoryVO.getSbjCtgrCd());
			iSubjectCategoryVO.setSbjCtgrPathNm(VO.getSbjCtgrPathNm()+"/"+iSubjectCategoryVO.getSbjCtgrNm());
			
			iSubjectCategoryVO.setSbjCtgrCd(subjectCategoryMapper.selectKey());
			subjectCategoryMapper.insert(iSubjectCategoryVO);
			resultVO.setReturnVO(iSubjectCategoryVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		

		return resultVO;
	}

	/**
	 * 과목 분류 수정
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<SubjectCategoryVO> editCategory(SubjectCategoryVO iSubjectCategoryVO) throws Exception {
		ProcessResultVO<SubjectCategoryVO> resultVO = new ProcessResultVO<SubjectCategoryVO>();
		try {
			subjectCategoryMapper.update(iSubjectCategoryVO);
			resultVO.setReturnVO(iSubjectCategoryVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}

	/**
	 * 과목 분류 삭제
	 * @param crsCtgrCd 삭제 대상 코드값
	 * @return
	 */
	@Override
	public ProcessResultVO<?> deleteCategory(SubjectCategoryVO iSubjectCategoryVO) throws Exception {
		subjectCategoryMapper.delete(iSubjectCategoryVO);

		// 성공처리를 표현하는 ProcessResultVO<Object>를 반환.
		return ProcessResultVO.success();
	}

	/**
	 * 과목 분류 정렬 순서 변경
	 * @param iSubjectCategoryVO
	 * @return
	 */
	@Override
	public ProcessResultVO<?> sortCategory(SubjectCategoryVO iSubjectCategoryVO) throws Exception {

		String[] categoryList = StringUtil.split(iSubjectCategoryVO.getSbjCtgrCd(),"|");

		iSubjectCategoryVO.setUseYn("");
		// 하위 코드 목록을 한꺼번에 조회
		List<SubjectCategoryVO> categoryArray = new ArrayList<SubjectCategoryVO>();
		
		if("mysql".equals(Constants.DATABASE_DB_TYPE) && "5".equals(Constants.DATABASE_DB_VERSION)) {
			categoryArray = subjectCategoryMapper.listVer5(iSubjectCategoryVO);
		}else {
			categoryArray = subjectCategoryMapper.list(iSubjectCategoryVO);
		}

		// 이중 포문으로 categoryArray에 변경된 order를 다시 저장
		// 만약 파라매터로 코드키가 누락되는 경우가 있다면... 로직 수정 필요.
		for (SubjectCategoryVO subjectCategoryVO : categoryArray) {
			for (int order = 0; order < categoryList.length; order++) {
				if(subjectCategoryVO.getSbjCtgrCd().equals(categoryList[order])) {
					subjectCategoryVO.setCtgrOdr(order+1);	// 1부터 차례로 순서값을 지정
				}
			}
		}

		// 변경된 시스템코드 어래이를 일괄 저장.
		subjectCategoryMapper.updateBatch(categoryArray);

		return ProcessResultVO.success();
	}



	/**
	 * 오프라인 과목 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<OfflineSubjectVO> listOffline(OfflineSubjectVO iOfflineSubjectVO) throws Exception {
		iOfflineSubjectVO.setSbjCtgrArr(StringUtil.split(iOfflineSubjectVO.getSbjCtgrCd(), "/"));
		ProcessResultListVO<OfflineSubjectVO> resultList = new ProcessResultListVO<OfflineSubjectVO>();
		try {
			List<OfflineSubjectVO> returnList = subjectOfflineMapper.list(iOfflineSubjectVO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	/**
	 * 오프라인 과목 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param OfflineSubjectVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<CourseVO>
	 */
	@Override
	public ProcessResultListVO<OfflineSubjectVO> listOfflinePageing(OfflineSubjectVO VO, int curPage, int listScale, int pageScale) throws Exception {
		ProcessResultListVO<OfflineSubjectVO> resultList = new ProcessResultListVO<OfflineSubjectVO>();
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		
		VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		VO.setLastIndex(paginationInfo.getLastRecordIndex());
	
		if( !"".equals(VO.getSbjCtgrCd()) && VO.getSbjCtgrCd() != null) {
			String [] sbjCtgrArr = VO.getSbjCtgrCd().split("/");
			List<String> list = new ArrayList<String>();
			Collections.addAll(list, sbjCtgrArr);
			list.remove("");
			sbjCtgrArr = list.toArray(new String[0]);
			VO.setSbjCtgrArr(sbjCtgrArr);
		}		
		
		try {
			// 전체 목록 수
			int totalCount = subjectOfflineMapper.count(VO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<OfflineSubjectVO> returnList = subjectOfflineMapper.listPageing(VO);
			resultList.setReturnList(returnList);
			resultList.setPageInfo(paginationInfo);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	/**
	 * 오프라인 과목 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CourseVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	@Override
	public ProcessResultListVO<OfflineSubjectVO> listOfflinePageing(OfflineSubjectVO VO, int curPage, int listScale) throws Exception {
		return this.listOfflinePageing(VO, curPage, listScale, Constants.LIST_PAGE_SCALE);
	}

	/**
	 * 오프라인 과목 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CourseVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	@Override
	public ProcessResultListVO<OfflineSubjectVO> listOfflinePageing(OfflineSubjectVO VO, int curPage) throws Exception {
		return this.listOfflinePageing(VO, curPage, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}

	/**
	 * 오프라인 과목 정보 조회
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<OfflineSubjectVO> viewOffline(OfflineSubjectVO iOfflineSubjectVO) throws Exception {
		ProcessResultVO<OfflineSubjectVO> resultVO = new ProcessResultVO<OfflineSubjectVO>();
		try {
			OfflineSubjectVO returnVO = subjectOfflineMapper.select(iOfflineSubjectVO);
			resultVO.setReturnVO(returnVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}

	/**
	 * 오프라인 과목 등록
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<OfflineSubjectVO> addOffline(OfflineSubjectVO iOfflineSubjectVO) throws Exception {
		ProcessResultVO<OfflineSubjectVO> resultVO = new ProcessResultVO<OfflineSubjectVO>();
		try {
			if("Y".equals(iOfflineSubjectVO.getAutoMakeYn())) {
				iOfflineSubjectVO.setSbjCd(subjectOfflineMapper.selectKey());
			}
			subjectOfflineMapper.insert(iOfflineSubjectVO);
			resultVO.setReturnVO(iOfflineSubjectVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}

	/**
	 * 오프라인 과목 수정
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<OfflineSubjectVO> editOffline(OfflineSubjectVO iOfflineSubjectVO) throws Exception {
		ProcessResultVO<OfflineSubjectVO> resultVO = new ProcessResultVO<OfflineSubjectVO>();
		try {
			subjectOfflineMapper.update(iOfflineSubjectVO);
			resultVO.setReturnVO(iOfflineSubjectVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}


	/**
	 * 오프라인 과목 삭제
	 *
	 * @return ProcessResultVO
	 */
	@Override
	public ProcessResultVO<?> deleteOffline(OfflineSubjectVO iOfflineSubjectVO) throws Exception {
		subjectOfflineMapper.delete(iOfflineSubjectVO);

		// 성공처리를 표현하는 ProcessResultVO<Object>를 반환.
		return ProcessResultVO.success();
	}

	/**
	 * 온라인 과목 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<OnlineSubjectVO> listOnline(OnlineSubjectVO iOnlineSubjectVO) throws Exception {
		ProcessResultListVO<OnlineSubjectVO> resultList = new ProcessResultListVO<OnlineSubjectVO>();
		try {
			List<OnlineSubjectVO> returnList = subjectOnlineMapper.list(iOnlineSubjectVO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	/**
	 * 온라인 과목 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param OnlineSubjectVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<CourseVO>
	 */
	@Override
	public ProcessResultListVO<OnlineSubjectVO> listOnlinePageing(OnlineSubjectVO VO, int curPage, int listScale, int pageScale, boolean filein) throws Exception {
		ProcessResultListVO<OnlineSubjectVO> resultList = new ProcessResultListVO<OnlineSubjectVO>();
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		
		VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		VO.setLastIndex(paginationInfo.getLastRecordIndex());
		
		if( !"".equals(VO.getSbjCtgrCd()) && VO.getSbjCtgrCd() != null) {
			String [] sbjCtgrArr = VO.getSbjCtgrCd().split("/");
			List<String> list = new ArrayList<String>();
			Collections.addAll(list, sbjCtgrArr);
			list.remove("");
			sbjCtgrArr = list.toArray(new String[0]);
			VO.setSbjCtgrArr(sbjCtgrArr);
		}
		
		try {
			// 전체 목록 수
			int totalCount = subjectOnlineMapper.count(VO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<OnlineSubjectVO> returnList = subjectOnlineMapper.listPageing(VO);
			if(filein) {
				List<OnlineSubjectVO> onlineSubjectList = new ArrayList<OnlineSubjectVO>();
				for(OnlineSubjectVO sosvo : returnList) {
					sosvo = sysFileService.getFile(sosvo, new NestedThumbFileHandler());
					sosvo = sysFileService.getFile(sosvo, new NestedPlanFileHandler());
					onlineSubjectList.add(sosvo);
				}
				resultList.setReturnList(onlineSubjectList);
			} else {
				resultList.setReturnList(returnList);	
			}			
			resultList.setReturnList(returnList);
			resultList.setPageInfo(paginationInfo);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	/**
	 * 온라인 과목 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param OnlineSubjectVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	@Override
	public ProcessResultListVO<OnlineSubjectVO> listOnlinePageing(OnlineSubjectVO VO, int curPage, int listScale) throws Exception {
		return this.listOnlinePageing(VO, curPage, listScale, Constants.LIST_PAGE_SCALE, false);
	}

	/**
	 * 온라인 과목 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param OnlineSubjectVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	@Override
	public ProcessResultListVO<OnlineSubjectVO> listOnlinePageing(OnlineSubjectVO VO, int curPage) throws Exception {
		return this.listOnlinePageing(VO, curPage, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE, false);
	}
	
	/**
	 * 온라인 과목 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param OnlineSubjectVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	@Override
	public ProcessResultListVO<OnlineSubjectVO> listOnlinePageing(OnlineSubjectVO VO, int curPage, int listScale, boolean filein) throws Exception {
		return this.listOnlinePageing(VO, curPage, listScale, Constants.LIST_PAGE_SCALE, filein);
	}
	
	/**
	 * 온라인 과목 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param OnlineSubjectVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	@Override
	public ProcessResultListVO<OnlineSubjectVO> listOnlinePageing(OnlineSubjectVO VO, int curPage, boolean filein) throws Exception {
		return this.listOnlinePageing(VO, curPage, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE, filein);
	}

	/**
	 * 온라인 과목 정보 조회
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<OnlineSubjectVO> viewOnline(OnlineSubjectVO iOnineSubjectVO) throws Exception {
		ProcessResultVO<OnlineSubjectVO> resultVO = new ProcessResultVO<OnlineSubjectVO>();
		try {
			OnlineSubjectVO returnVO = subjectOnlineMapper.select(iOnineSubjectVO);
			returnVO = sysFileService.getFile(returnVO, new NestedThumbFileHandler());
			returnVO = sysFileService.getFile(returnVO, new NestedPlanFileHandler());
			resultVO.setReturnVO(returnVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}

	/**
	 * 온라인 과목 등록
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	@HrdApiCrsOnlnSbj(SyncType.CREATE)
	public ProcessResultVO<OnlineSubjectVO> addOnline(OnlineSubjectVO iOnlineSubjectVO) throws Exception {
		
		ProcessResultVO<OnlineSubjectVO> resultVO = new ProcessResultVO<OnlineSubjectVO>();

		//---- 과목 폴더 생성
		String contentsDir =  Constants.CONTENTS_STORAGE_PATH + File.separator  + iOnlineSubjectVO.getOrgCd() + File.separator + iOnlineSubjectVO.getSbjCd();

		FileUtil.createDirectory(contentsDir);
		if("Y".equals(iOnlineSubjectVO.getAutoMakeYn())) {
			iOnlineSubjectVO.setSbjCd(subjectOnlineMapper.selectKey());
		}
		subjectOnlineMapper.insert(iOnlineSubjectVO);
		sysFileService.bindFile(iOnlineSubjectVO, new NestedThumbFileHandler());
		sysFileService.bindFile(iOnlineSubjectVO, new NestedPlanFileHandler());
		resultVO.setReturnVO(iOnlineSubjectVO);
		resultVO.setResultSuccess();

		return resultVO;
	}

	/**
	 * 온라인 과목 수정
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	@HrdApiCrsOnlnSbj(SyncType.UPDATE)
	public ProcessResultVO<OnlineSubjectVO> editOnline(OnlineSubjectVO iOnlineSubjectVO) throws Exception {
		ProcessResultVO<OnlineSubjectVO> resultVO = new ProcessResultVO<OnlineSubjectVO>();
		subjectOnlineMapper.update(iOnlineSubjectVO);
		sysFileService.bindFileUpdate(iOnlineSubjectVO, new NestedThumbFileHandler());
		sysFileService.bindFileUpdate(iOnlineSubjectVO, new NestedPlanFileHandler());
		resultVO.setReturnVO(iOnlineSubjectVO);
		resultVO.setResultSuccess();

		return resultVO;
	}


	/**
	 * 온라인 과목 삭제
	 *
	 * @return ProcessResultVO
	 */
	@Override
	@HrdApiCrsOnlnSbj(SyncType.DELETE)
	public ProcessResultVO<?> deleteOnline(OnlineSubjectVO iOnlineSubjectVO) throws Exception {
		sysFileService.removeFile(iOnlineSubjectVO, new NestedThumbFileHandler()); // 파일정보 삭제..
		sysFileService.removeFile(iOnlineSubjectVO, new NestedPlanFileHandler()); // 파일정보 삭제..
		subjectOnlineMapper.delete(iOnlineSubjectVO);
		// 성공처리를 표현하는 ProcessResultVO<Object>를 반환.
		return ProcessResultVO.success();
	}


	/**
	 * 온라인 과목 목록 조회 (개설 과정 서치용)
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<OnlineSubjectVO> listOnlineSearch(OnlineSubjectVO iOnlineSubjectVO) throws Exception {
		ProcessResultListVO<OnlineSubjectVO> resultList = new ProcessResultListVO<OnlineSubjectVO>();
		try {
			List<OnlineSubjectVO> returnList = subjectOnlineMapper.listSearch(iOnlineSubjectVO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	/**
	 * 오프라인 과목 목록 조회 (개설 과정 서치용)
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<OfflineSubjectVO> listOfflineSearch(OfflineSubjectVO iOfflineSubjectVO) throws Exception {
		ProcessResultListVO<OfflineSubjectVO> resultList = new ProcessResultListVO<OfflineSubjectVO>();
		try {
			List<OfflineSubjectVO> returnList = subjectOfflineMapper.listSearch(iOfflineSubjectVO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	/**
	 * 온라인 과목 목록 조회 (공개 과정 용)
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<OnlineSubjectVO> listOnlineSearchOpen(OnlineSubjectVO iOnlineSubjectVO) throws Exception {
		ProcessResultListVO<OnlineSubjectVO> resultList = new ProcessResultListVO<OnlineSubjectVO>();
		try {
			List<OnlineSubjectVO> returnList = subjectOnlineMapper.listSearchOpen(iOnlineSubjectVO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	/**
	 *
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<SubjectCategoryVO> listOnOffCategory(SubjectCategoryVO iSubjectCategoryVO) throws Exception {
		ProcessResultListVO<SubjectCategoryVO> resultList = new ProcessResultListVO<SubjectCategoryVO>();
		try {
			List<SubjectCategoryVO> returnList = new ArrayList<SubjectCategoryVO>();
			
			if("mysql".equals(Constants.DATABASE_DB_TYPE) && "5".equals(Constants.DATABASE_DB_VERSION)) {
				returnList = subjectCategoryMapper.listVer5(iSubjectCategoryVO);
			}else {
				returnList = subjectCategoryMapper.list(iSubjectCategoryVO);
			}
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}


	/**
	 * 트랜잭션 테스트용 매소드(테스트에서만 사용된다.)
	 */
	@Override
	@Deprecated
	public ProcessResultVO<OfflineSubjectVO> transactionRollbackMethod(OfflineSubjectVO iOfflineSubjectVO) throws Exception {
		this.addOffline(iOfflineSubjectVO);
		throw new Exception("트랜잭션 테스트를 위한 강제 Exception");
	}
	
	/**
	 * 과목 분류 목록 조회 - 수강신청
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<SubjectCategoryVO> listCategoryForEnroll(SubjectCategoryVO iSubjectCategoryVO) {
		ProcessResultListVO<SubjectCategoryVO> resultList = new ProcessResultListVO<SubjectCategoryVO>();
		try {
			List<SubjectCategoryVO> returnList = subjectCategoryMapper.listForEnroll(iSubjectCategoryVO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
	/**
	 * 강의실 등록
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	@HrdApiCrsOnlnSbj(SyncType.CREATE)
	public ProcessResultVO<LecRoomVO> addLecRoom(LecRoomVO lecRoomVO) throws Exception {
		
		ProcessResultVO<LecRoomVO> resultVO = new ProcessResultVO<LecRoomVO>();
		
		if("Y".equals(lecRoomVO.getAutoMakeYn())) {
			lecRoomVO.setLecRoomSn(subjectOnlineMapper.selectLecRoomKey());
		}
		subjectOnlineMapper.lecRoomInsert(lecRoomVO);

		resultVO.setReturnVO(lecRoomVO);
		resultVO.setResultSuccess();

		return resultVO;
	}
	
	/**
	 * 강의실 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param LecRoomVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<CourseVO>
	 */
	@Override
	public ProcessResultListVO<LecRoomVO> listLecRoomPageing(LecRoomVO VO, int curPage, int listScale, int pageScale, boolean filein) throws Exception {
		ProcessResultListVO<LecRoomVO> resultList = new ProcessResultListVO<LecRoomVO>();
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		
		VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		VO.setLastIndex(paginationInfo.getLastRecordIndex());
		
		try {
			// 전체 목록 수
			int totalCount = subjectOnlineMapper.lecRoomCount(VO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<LecRoomVO> returnList = subjectOnlineMapper.lecListPageing(VO);
			resultList.setReturnList(returnList);	
			resultList.setPageInfo(paginationInfo);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	@Override
	public ProcessResultListVO<LecRoomVO> listLecRoomPageing(LecRoomVO VO, int curPage, int listScale) throws Exception {
		return this.listLecRoomPageing(VO, curPage, listScale, Constants.LIST_PAGE_SCALE, false);
	}

	@Override
	public ProcessResultListVO<LecRoomVO> listLecRoomPageing(LecRoomVO VO, int curPage) throws Exception {
		return this.listLecRoomPageing(VO, curPage, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE, false);
	}

	@Override
	public ProcessResultListVO<LecRoomVO> listLecRoomPageing(LecRoomVO VO, int curPage, int listScale, boolean filein) throws Exception {
		return this.listLecRoomPageing(VO, curPage, listScale, Constants.LIST_PAGE_SCALE, filein);
	}
	
	@Override
	public ProcessResultListVO<LecRoomVO> listLecRoomPageing(LecRoomVO VO, int curPage, boolean filein) throws Exception {
		return this.listLecRoomPageing(VO, curPage, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE, filein);
	}
	
	/**
	 * 강의실 정보 조회
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<LecRoomVO> viewLecRoom(LecRoomVO lecRoomVO) throws Exception {
		ProcessResultVO<LecRoomVO> resultVO = new ProcessResultVO<LecRoomVO>();
		try {
			LecRoomVO returnVO = subjectOnlineMapper.getLecRoom(lecRoomVO);
			
			resultVO.setReturnVO(returnVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}
	
	/**
	 * 강의실 수정
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	@HrdApiCrsOnlnSbj(SyncType.UPDATE)
	public ProcessResultVO<LecRoomVO> editLecRoom(LecRoomVO lecRoomVO) throws Exception {
		ProcessResultVO<LecRoomVO> resultVO = new ProcessResultVO<LecRoomVO>();
		
		subjectOnlineMapper.updateLecRoom(lecRoomVO);
		
		resultVO.setReturnVO(lecRoomVO);
		resultVO.setResultSuccess();

		return resultVO;
	}
	
	/**
	 * 강의실 삭제
	 *
	 * @return ProcessResultVO
	 */
	@Override
	@HrdApiCrsOnlnSbj(SyncType.DELETE)
	public ProcessResultVO<?> deleteLecRoom(LecRoomVO lecRoomVO) throws Exception {
		subjectOnlineMapper.deleteLecRoom(lecRoomVO);
		// 성공처리를 표현하는 ProcessResultVO<Object>를 반환.
		return ProcessResultVO.success();
	}
}
