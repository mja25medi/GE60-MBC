package egovframework.edutrack.modules.log.privateinfo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.log.privateinfo.service.LogPrivateInfoInqLogVO;
import egovframework.edutrack.modules.log.privateinfo.service.LogPrivateInfoService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Service("logPrivateInfoService")
public class LogPrivateInfoServiceImpl 
	extends EgovAbstractServiceImpl implements LogPrivateInfoService {

	@Resource(name="logPrivateInfoInqLogMapper")
    private LogPrivateInfoInqLogMapper logPrivateInfoInqLogMapper;
	
	@Override
	public ProcessResultListVO<LogPrivateInfoInqLogVO> list(LogPrivateInfoInqLogVO vo) throws Exception {
		
		ProcessResultListVO<LogPrivateInfoInqLogVO> resultList = new ProcessResultListVO<LogPrivateInfoInqLogVO>();
		try {
			List<LogPrivateInfoInqLogVO> returnList = logPrivateInfoInqLogMapper.list(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		
		return resultList;
	}

	@Override
	public ProcessResultListVO<LogPrivateInfoInqLogVO> listPageing(LogPrivateInfoInqLogVO vo, int curPage, int listScale,int pageScale) throws Exception {
		ProcessResultListVO<LogPrivateInfoInqLogVO> resultList = new ProcessResultListVO<LogPrivateInfoInqLogVO>();
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(curPage);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = logPrivateInfoInqLogMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<LogPrivateInfoInqLogVO> returnList = logPrivateInfoInqLogMapper.listPageing(vo);
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

	@Override
	public ProcessResultVO<LogPrivateInfoInqLogVO> view(LogPrivateInfoInqLogVO vo) throws Exception {
		
		ProcessResultVO<LogPrivateInfoInqLogVO> resultVO = new ProcessResultVO<LogPrivateInfoInqLogVO>();
		try {
			LogPrivateInfoInqLogVO returnVO = logPrivateInfoInqLogMapper.select(vo);
			resultVO.setReturnVO(returnVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		
		return resultVO;
	}

	@Override
	public int add(LogPrivateInfoInqLogVO vo) throws Exception {
		return logPrivateInfoInqLogMapper.insert(vo);
	}
	
    
}
