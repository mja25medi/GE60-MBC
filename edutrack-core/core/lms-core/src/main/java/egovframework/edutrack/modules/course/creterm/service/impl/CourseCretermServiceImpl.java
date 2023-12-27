package egovframework.edutrack.modules.course.creterm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.course.creterm.service.CourseCretermService;
import egovframework.edutrack.modules.course.creterm.service.CourseCretermVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Service("courseCretermService")
public class CourseCretermServiceImpl
extends EgovAbstractServiceImpl implements CourseCretermService  {

	/** Mapper */
	@Resource(name="courseCretermMapper")
	private CourseCretermMapper 		courseCretermMapper;

	@Override
	public ProcessResultListVO<CourseCretermVO> list(CourseCretermVO VO) throws Exception {
		ProcessResultListVO<CourseCretermVO> resultList = new ProcessResultListVO<CourseCretermVO>();
		try {
			List<CourseCretermVO> returnList = courseCretermMapper.list(VO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	@Override
	public ProcessResultListVO<CourseCretermVO> listPageing(CourseCretermVO VO) throws Exception {
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(VO.getCurPage());
		paginationInfo.setRecordCountPerPage(VO.getListScale());
		paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
		
		VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		VO.setLastIndex(paginationInfo.getLastRecordIndex());
		
		ProcessResultListVO<CourseCretermVO> resultList = new ProcessResultListVO<CourseCretermVO>();
		try {
			// 전체 목록 수
			int totalCount = courseCretermMapper.count(VO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<CourseCretermVO> returnList = courseCretermMapper.listPageing(VO);
			resultList.setReturnList(returnList);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

	@Override
	public ProcessResultVO<CourseCretermVO> view(CourseCretermVO VO) throws Exception {
		ProcessResultVO<CourseCretermVO> resultVO = new ProcessResultVO<CourseCretermVO>();
		try {
			resultVO.setReturnVO(courseCretermMapper.select(VO));
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		return resultVO;
	}

	@Override
	public ProcessResultVO<CourseCretermVO> add(CourseCretermVO VO) throws Exception {
		VO.setCrsTermCd(courseCretermMapper.selectTermCd());
		
		ProcessResultVO<CourseCretermVO> resultVO = new ProcessResultVO<CourseCretermVO>();
		try {
			courseCretermMapper.insert(VO);
			resultVO.setReturnVO(VO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}

	@Override
	public ProcessResultVO<CourseCretermVO> edit(CourseCretermVO VO) throws Exception {
		ProcessResultVO<CourseCretermVO> resultVO = new ProcessResultVO<CourseCretermVO>();
		try {
			courseCretermMapper.update(VO);
			resultVO.setReturnVO(VO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		return resultVO;
	}

	@Override
	public ProcessResultVO<?> remove(CourseCretermVO VO) throws Exception {
		courseCretermMapper.delete(VO); // 분류 삭제
		return ProcessResultVO.success();
	}

}
