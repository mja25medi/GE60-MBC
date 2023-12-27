package egovframework.edutrack.modules.org.connip.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.modules.org.connip.service.OrgConnIpService;
import egovframework.edutrack.modules.org.connip.service.OrgConnIpVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *  <b>기관 - 접속 IP 관리</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("orgConnIpService")
public class OrgConnIpServiceImpl 
	extends EgovAbstractServiceImpl implements OrgConnIpService {

 /*   @Resource(name="orgConnIpDAO")
    private OrgConnIpMapper 	orgConnIpMapper;*/
	
	/** Mapper */
	@Resource(name="orgConnIpMapper")
	private OrgConnIpMapper 		orgConnIpMapper;
    
    /* (non-Javadoc)
	 * @see egovframework.edutrack.modules.org.connip.service.impl.OrgConnIpService#list(egovframework.edutrack.modules.org.connip.service.OrgConnIpVO)
	 */
	@Override
	public ProcessResultListVO<OrgConnIpVO> list(OrgConnIpVO vo) throws Exception {
		ProcessResultListVO<OrgConnIpVO> resultList = new ProcessResultListVO<OrgConnIpVO>(); 
		try {
			List<OrgConnIpVO> connIpList =  orgConnIpMapper.list(vo);
			resultList.setResult(1);
			resultList.setReturnList(connIpList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
    /* (non-Javadoc)
	 * @see egovframework.edutrack.modules.org.connip.service.impl.OrgConnIpService#listPageing(egovframework.edutrack.modules.org.connip.service.OrgConnIpVO, int, int, int)
	 */
	@Override
	public ProcessResultListVO<OrgConnIpVO> listPageing(OrgConnIpVO vo, 
			int pageIndex, int listScale, int pageScale) throws Exception {
		ProcessResultListVO<OrgConnIpVO> resultList = new ProcessResultListVO<OrgConnIpVO>(); 
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(pageIndex);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = orgConnIpMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<OrgConnIpVO> connIpList =  orgConnIpMapper.listPageing(vo);
			resultList.setResult(1);
			resultList.setReturnList(connIpList);
			resultList.setPageInfo(paginationInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
    /* (non-Javadoc)
	 * @see egovframework.edutrack.modules.org.connip.service.impl.OrgConnIpService#listPageing(egovframework.edutrack.modules.org.connip.service.OrgConnIpVO, int, int)
	 */
	@Override
	public ProcessResultListVO<OrgConnIpVO> listPageing(OrgConnIpVO vo, 
			int pageIndex, int listScale) throws Exception {
		return this.listPageing(vo, pageIndex, listScale, Constants.LIST_PAGE_SCALE);
	}
	
    /* (non-Javadoc)
	 * @see egovframework.edutrack.modules.org.connip.service.impl.OrgConnIpService#listPageing(egovframework.edutrack.modules.org.connip.service.OrgConnIpVO, int)
	 */
	@Override
	public ProcessResultListVO<OrgConnIpVO> listPageing(OrgConnIpVO vo, 
			int pageIndex) throws Exception {
		return this.listPageing(vo, pageIndex, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}	
	
	/* (non-Javadoc)
	 * @see egovframework.edutrack.modules.org.connip.service.impl.OrgConnIpService#view(egovframework.edutrack.modules.org.connip.service.OrgConnIpVO)
	 */
	@Override
	public OrgConnIpVO view(OrgConnIpVO vo) throws Exception {
		return orgConnIpMapper.select(vo);
	}
	
	/* (non-Javadoc)
	 * @see egovframework.edutrack.modules.org.connip.service.impl.OrgConnIpService#add(egovframework.edutrack.modules.org.connip.service.OrgConnIpVO)
	 */
	@Override
	public int add(OrgConnIpVO vo) throws Exception {
		return orgConnIpMapper.insert(vo);
	}	
	
	/* (non-Javadoc)
	 * @see egovframework.edutrack.modules.org.connip.service.impl.OrgConnIpService#remove(egovframework.edutrack.modules.org.connip.service.OrgConnIpVO)
	 */
	@Override
	public int remove(OrgConnIpVO vo) throws Exception {
		return orgConnIpMapper.delete(vo);
	}
}
