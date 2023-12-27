package egovframework.edutrack.modules.opencourse.course.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.opencourse.course.service.OpenCrsService;
import egovframework.edutrack.modules.opencourse.course.service.OpenCrsVO;
import egovframework.edutrack.modules.opencourse.subject.service.OpenCrsSbjVO;
import egovframework.edutrack.modules.opencourse.subject.service.impl.OpenCrsSbjMapper;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Service("openCrsService")
public class OpenCrsServiceImpl
	extends EgovAbstractServiceImpl implements OpenCrsService {

	@Resource(name="openCrsMapper")
	private OpenCrsMapper 		openCrsMapper;
	
	@Resource(name="openCrsSbjMapper")
	private OpenCrsSbjMapper 		openCrsSbjMapper;

	/**
	 * 공개강좌 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<OpenCrsVO> list(OpenCrsVO vo) throws Exception {
		ProcessResultListVO<OpenCrsVO> resultList = new ProcessResultListVO<OpenCrsVO>();
		try {
			List<OpenCrsVO> returnList = openCrsMapper.list(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}		
		return resultList;
	}

	/**
	 * 공개강좌 페이징 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<OpenCrsVO> listPageing(OpenCrsVO vo, int curPage, int listScale, int pageScale) throws Exception {
		
		ProcessResultListVO<OpenCrsVO> resultList = new ProcessResultListVO<OpenCrsVO>();

		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(curPage);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = openCrsMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<OpenCrsVO> returnList = openCrsMapper.listPageing(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}		
		return resultList;
	}

	/**
	 * 공개강좌 페이징 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<OpenCrsVO> listPageing(OpenCrsVO vo, int curPage, int listScale) throws Exception {
		
		ProcessResultListVO<OpenCrsVO> resultList = new ProcessResultListVO<OpenCrsVO>();
		
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(curPage);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = openCrsMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<OpenCrsVO> returnList = openCrsMapper.listPageing(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		
		return resultList;
	}

	/**
	 * 공개강좌 페이징 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<OpenCrsVO> listPageing(OpenCrsVO vo, int curPage) throws Exception {
		
		ProcessResultListVO<OpenCrsVO> resultList = new ProcessResultListVO<OpenCrsVO>();
		
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(curPage);
			paginationInfo.setRecordCountPerPage(Constants.LIST_SCALE);
			paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = openCrsMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<OpenCrsVO> returnList = openCrsMapper.listPageing(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}		
		return resultList;
	}

	/**
	 * 공개강좌 정보 조회
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<OpenCrsVO> view(OpenCrsVO vo) throws Exception {
		ProcessResultVO<OpenCrsVO> resultVO = new ProcessResultVO<OpenCrsVO>();
		try {
			OpenCrsVO returnVO = openCrsMapper.select(vo);
			resultVO.setReturnVO(returnVO);
			resultVO.setResult(1);
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResult(-1);
			resultVO.setMessage(e.getMessage());
		}		
		return resultVO;
	}

	/**
	 * 공개강좌 정보 등록
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<OpenCrsVO> add(OpenCrsVO vo) throws Exception {
		ProcessResultVO<OpenCrsVO> resultVO = new ProcessResultVO<OpenCrsVO>();
		try {
			if(ValidationUtils.isEmpty(vo.getCrsCd())) {
				vo.setCrsCd(openCrsMapper.selectKey());
			}
			openCrsMapper.insert(vo);
			resultVO.setReturnVO(vo);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}			
		return resultVO;
	}

	/**
	 * 공개강좌 정보 수정
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<OpenCrsVO> edit(OpenCrsVO vo) throws Exception {
		ProcessResultVO<OpenCrsVO> resultVO = new ProcessResultVO<OpenCrsVO>();
		try {
			openCrsMapper.update(vo);
			resultVO.setReturnVO(vo);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}			
		return resultVO;
	}

	/**
	 * 공개강좌 정보 삭제
	 *
	 * @return
	 */
	@Override
	public ProcessResultVO<?> remove(OpenCrsVO vo) throws Exception {
		ProcessResultVO<OpenCrsVO> resultVO = new ProcessResultVO<OpenCrsVO>();
		//-- 연결된 과목 정보를 삭제한다.
		OpenCrsVO ocsvo = new OpenCrsVO();
		ocsvo.setCrsCd(vo.getCrsCd());
		try {	
			openCrsMapper.delete(ocsvo);
			resultVO.setReturnVO(vo);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		return resultVO;
	}

	/**
	 * 공개강좌 정렬 순서 변경
	 * @param iCodeVO
	 * @return
	 */
	@Override
	public ProcessResultVO<?> sort(OpenCrsVO vo) throws Exception {
		String[] itemList = StringUtil.split(vo.getCrsCd(),"|");
		// 과목의 목록을 조회한다.
		List<OpenCrsVO> itemArray = openCrsMapper.list(vo);
		// 이중 포문으로 itemArray에 변경된 order를 다시 저장
		for (OpenCrsVO ocvo : itemArray) {
			for (int order = 0; order < itemList.length; order++) {
				if(ocvo.getCrsCd().equals(itemList[order])) {
					ocvo.setCrsOdr(order+1);	// 1부터 차례로 순서값을 지정
				}
			}
		}
		openCrsMapper.updateBatch(itemArray);
		return ProcessResultVO.success();
	}

	/**
	 * 공개강좌 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<OpenCrsVO> listCourse(OpenCrsVO vo) throws Exception {
		ProcessResultListVO<OpenCrsVO> resultList = new ProcessResultListVO<OpenCrsVO>();
		try {
			List<OpenCrsVO> returnList = openCrsMapper.listCourse(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}		
		return resultList;
	}

	/**
	 * 홈페이지 공개강좌 페이징 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<OpenCrsVO> listCoursePageing(OpenCrsVO vo, int curPage, int listScale, int pageScale) throws Exception {
		
		ProcessResultListVO<OpenCrsVO> resultList = new ProcessResultListVO<OpenCrsVO>();

		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(curPage);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = openCrsMapper.listCoursePageingCount(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<OpenCrsVO> returnList = openCrsMapper.listCoursePageing(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		
		return resultList;
	}

	/**
	 * 홈페이지 공개강좌 페이징 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<OpenCrsVO> listCoursePageing(OpenCrsVO vo, int curPage, int listScale) throws Exception {
		
		ProcessResultListVO<OpenCrsVO> resultList = new ProcessResultListVO<OpenCrsVO>();
		
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(curPage);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = openCrsMapper.listCoursePageingCount(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<OpenCrsVO> returnList = openCrsMapper.listCoursePageing(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}		
		return resultList;
	}

	/**
	 * 홈페이지 공개강좌 페이징 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<OpenCrsVO> listCoursePageing(OpenCrsVO vo, int curPage) throws Exception {
	
		ProcessResultListVO<OpenCrsVO> resultList = new ProcessResultListVO<OpenCrsVO>();

		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(curPage);
			paginationInfo.setRecordCountPerPage(Constants.LIST_SCALE);
			paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = openCrsMapper.listCoursePageingCount(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<OpenCrsVO> returnList = openCrsMapper.listCoursePageing(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}		
		return resultList;
	}


	/**
	 * 과목 사용중인 공개강좌 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<OpenCrsVO> listSubInfo(OpenCrsVO vo) throws Exception {
		ProcessResultListVO<OpenCrsVO> resultList = new ProcessResultListVO<OpenCrsVO>();
		try {
			List<OpenCrsVO> returnList = openCrsMapper.listSubInfo(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}		
		return resultList;
	}
}
