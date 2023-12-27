package egovframework.edutrack.modules.student.sync.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.student.sync.service.EaiNkoreducompsendVO;
import egovframework.edutrack.modules.student.sync.service.EaiSyncService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Service("eaiSyncService")
public class EaiSyncServiceImpl extends EgovAbstractServiceImpl implements EaiSyncService {
	

	/** Mapper */
	@Resource(name="eaiSyncMapper")
	private EaiSyncMapper 		eaiSyncMapper;
	
	/**
	 * Eai 연동 목록 조회
	 * 
	 * @return ProcessResultListVO
	 * @throws Exception 
	 */
	public ProcessResultListVO<EaiNkoreducompsendVO> list(EaiNkoreducompsendVO iEaiNkoreducompsendVO) throws Exception {
		ProcessResultListVO<EaiNkoreducompsendVO> resultList = new ProcessResultListVO<EaiNkoreducompsendVO>();
		try {
			List<EaiNkoreducompsendVO> returnList = eaiSyncMapper.list(iEaiNkoreducompsendVO);
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
	 * Eai 연동 목록 조회 (페이징)
	 * 
	 * @return ProcessResultListVO
	 * @throws Exception 
	 */
	public ProcessResultListVO<EaiNkoreducompsendVO> list(EaiNkoreducompsendVO iEaiNkoreducompsendVO, int curPage, int listScale, int pageScale ) throws Exception {
		
		ProcessResultListVO<EaiNkoreducompsendVO> resultList = new ProcessResultListVO<EaiNkoreducompsendVO>();

		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(curPage);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			iEaiNkoreducompsendVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			iEaiNkoreducompsendVO.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = eaiSyncMapper.count(iEaiNkoreducompsendVO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<EaiNkoreducompsendVO> returnList = eaiSyncMapper.listPageing(iEaiNkoreducompsendVO, curPage, listScale, pageScale);
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
	 * Eai 연동 초기화
	 *
	 * @return  ProcessResultVO
	 * @throws Exception 
	 */
	public ProcessResultVO<EaiNkoreducompsendVO> editReset(EaiNkoreducompsendVO iEaiNkoreducompsendVO) throws Exception{
		
		eaiSyncMapper.updateEaisendrecvstsmt(iEaiNkoreducompsendVO);
		
		eaiSyncMapper.updateNkoreducompsend(iEaiNkoreducompsendVO);
		
		return ProcessResultVO.success();
	}
}