package egovframework.edutrack.modules.org.domain.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.modules.org.domain.service.OrgDomainService;
import egovframework.edutrack.modules.org.domain.service.OrgDomainVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *  <b>기관 - 기관 도메인 관리</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("orgDomainService")
public class OrgDomainServiceImpl 
	extends EgovAbstractServiceImpl implements OrgDomainService{

	/** dao */
/*    @Resource(name="orgDomainDAO")
    private OrgDomainMapper 	orgDomainMapper;*/
	
	/** Mapper */
	@Resource(name="orgDomainMapper")
	private OrgDomainMapper 		orgDomainMapper;
    
    /**
	 *  기관 도메인 전체 목록을 조회한다.
	 * @param OrgDomainVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<OrgDomainVO> list(OrgDomainVO vo) throws Exception {
		ProcessResultListVO<OrgDomainVO> resultList = new ProcessResultListVO<OrgDomainVO>(); 
		try {
			List<OrgDomainVO> domainList =  orgDomainMapper.list(vo);
			resultList.setResult(1);
			resultList.setReturnList(domainList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
    /**
	 * 기관 도메인 페이징 목록을 조회한다.
	 * @param OrgDomainVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<OrgDomainVO> listPageing(OrgDomainVO vo, 
			int pageIndex, int listScale, int pageScale) throws Exception {
		ProcessResultListVO<OrgDomainVO> resultList = new ProcessResultListVO<OrgDomainVO>(); 
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(pageIndex);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = orgDomainMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<OrgDomainVO> domainList =  orgDomainMapper.listPageing(vo);
			resultList.setResult(1);
			resultList.setReturnList(domainList);
			resultList.setPageInfo(paginationInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
    /**
	 * 기관 도메인 페이징 목록을 조회한다.
	 * @param OrgDomainVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<OrgDomainVO> listPageing(OrgDomainVO vo, 
			int pageIndex, int listScale) throws Exception {
		return this.listPageing(vo, pageIndex, listScale, Constants.LIST_PAGE_SCALE);
	}
	
    /**
	 * 기관 도메인 페이징 목록을 조회한다.
	 * @param OrgDomainVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<OrgDomainVO> listPageing(OrgDomainVO vo, 
			int pageIndex) throws Exception {
		return this.listPageing(vo, pageIndex, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}	
	
	/**
	 * 기관 도메인 상세 정보를 조회한다.
	 * @param OrgDomainVO
	 * @return OrgDomainVO
	 * @throws Exception
	 */
	@Override
	public OrgDomainVO view(OrgDomainVO vo) throws Exception {
		return orgDomainMapper.select(vo);
	}
	
	/**
	 * 기관 도메인 상세 정보를 조회한다.
	 * @param OrgDomainVO
	 * @return OrgDomainVO
	 * @throws Exception
	 */
	@Override
	public OrgDomainVO viewByTypeCd(OrgDomainVO vo) throws Exception {
		return orgDomainMapper.selectByTypeCd(vo);
	}
	
	
	/**
	 * 기관 도메인 상세 정보를 조회한다.
	 * @param OrgDomainVO
	 * @return OrgDomainVO
	 * @throws Exception
	 */
	@Override
	public OrgDomainVO viewByServiceTypeCd(OrgDomainVO vo) throws Exception {
		return orgDomainMapper.selectByServiceTypeCd(vo);
	}
	
	/**
	 * 기관 도메인 정보를 등록한다.
	 * @param OrgDomainVO
	 * @return String
	 * @throws Exception
	 */
	@Override
	public int add(OrgDomainVO vo) throws Exception {
		return orgDomainMapper.insert(vo);
	}	
	
	/**
	 * 기관 도메인 정보를 수정한다.
	 * @param OrgDomainVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int edit(OrgDomainVO vo) throws Exception {
		return orgDomainMapper.update(vo);
	}
	
	/**
	 * 기관 도메인 정보를 삭제 한다.
	 * @param OrgDomainVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int remove(OrgDomainVO vo) throws Exception {
		return orgDomainMapper.delete(vo);
	}
	
	/**
	 * 기관의 대표 도메인을 변경 한다.
	 * @param OrgDomainVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int change(OrgDomainVO vo) throws Exception {
		orgDomainMapper.updateRprstN(vo);
		return orgDomainMapper.updateRprstY(vo);
	}
}
