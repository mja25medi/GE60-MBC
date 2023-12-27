package egovframework.edutrack.modules.etc.relatedsite.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.etc.relatedsite.service.EtcRelatedSiteCtgrVO;
import egovframework.edutrack.modules.etc.relatedsite.service.EtcRelatedSiteService;
import egovframework.edutrack.modules.etc.relatedsite.service.EtcRelatedSiteVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *  <b>기타 - 기타 관련사이트 관리</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("etcRelatedSiteService")
public class EtcRelatedSiteServiceImpl 
	extends EgovAbstractServiceImpl implements EtcRelatedSiteService {
	
    @Resource(name="etcRelatedSiteCtgrMapper")
    private EtcRelatedSiteCtgrMapper	etcRelatedSiteCtgrMapper;
    
    @Resource(name="etcRelatedSiteMapper")
    private EtcRelatedSiteMapper		etcRelatedSiteMapper;

	/**
	 * 관련사이트 분류 전체 목록을 조회한다.
	 * @param EtcRelatedSiteCtgrVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<EtcRelatedSiteCtgrVO> listCtgr(EtcRelatedSiteCtgrVO vo) throws Exception {
		ProcessResultListVO<EtcRelatedSiteCtgrVO> resultList = new ProcessResultListVO<EtcRelatedSiteCtgrVO>(); 
		try {
			List<EtcRelatedSiteCtgrVO> siteCtgrList =  etcRelatedSiteCtgrMapper.list(vo);
			resultList.setResult(1);
			resultList.setReturnList(siteCtgrList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
	/**
	 * 관련사이트 분류 페이징 목록을 조회한다.
	 * @param EtcRelatedSiteCtgrVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<EtcRelatedSiteCtgrVO> listCtgrPageing(EtcRelatedSiteCtgrVO vo, 
			int pageIndex, int listScale, int pageScale) throws Exception {
		ProcessResultListVO<EtcRelatedSiteCtgrVO> resultList = new ProcessResultListVO<EtcRelatedSiteCtgrVO>(); 
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(pageIndex);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = etcRelatedSiteCtgrMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<EtcRelatedSiteCtgrVO> siteCtgrList =  etcRelatedSiteCtgrMapper.listPageing(vo);
			resultList.setResult(1);
			resultList.setReturnList(siteCtgrList);
			resultList.setPageInfo(paginationInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
	/**
	 * 관련사이트 분류 페이징 목록을 조회한다.
	 * @param EtcRelatedSiteCtgrVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<EtcRelatedSiteCtgrVO> listCtgrPageing(EtcRelatedSiteCtgrVO vo, 
			int pageIndex, int listScale) throws Exception {
		return this.listCtgrPageing(vo, pageIndex, listScale, Constants.LIST_PAGE_SCALE);
	}
	
	/**
	 * 관련사이트 분류 페이징 목록을 조회한다.
	 * @param EtcRelatedSiteCtgrVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<EtcRelatedSiteCtgrVO> listCtgrPageing(EtcRelatedSiteCtgrVO vo, 
			int pageIndex) throws Exception {
		return this.listCtgrPageing(vo, pageIndex, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}	
	
	/**
	 * 관련사이트 분류 상세 정보를 조회한다.
	 * @param EtcRelatedSiteCtgrVO
	 * @return EtcRelatedSiteCtgrVO
	 * @throws Exception
	 */
	@Override
	public EtcRelatedSiteCtgrVO viewCtgr(EtcRelatedSiteCtgrVO vo) throws Exception {
		return etcRelatedSiteCtgrMapper.select(vo);
	}
	
	/**
	 * 관련사이트 분류 정보를 등록한다.
	 * @param EtcRelatedSiteCtgrVO
	 * @return String
	 * @throws Exception
	 */
	@Override
	public int addCtgr(EtcRelatedSiteCtgrVO vo) throws Exception {
		if("Y".equals(vo.getAutoMakeYn())) {
			vo.setCtgrCd(etcRelatedSiteCtgrMapper.selectKey());
		}
		return etcRelatedSiteCtgrMapper.insert(vo);
	}	
	
	/**
	 * 관련사이트 분류 정보를 수정한다.
	 * @param EtcRelatedSiteCtgrVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int editCtgr(EtcRelatedSiteCtgrVO vo) throws Exception {
		return etcRelatedSiteCtgrMapper.update(vo);
	}
	
	/**
	 * 관련사이트 분류 정렬 순서를 변경한다.
	 * @param EtcRelatedSiteCtgrVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int sortCrgr(EtcRelatedSiteCtgrVO vo) throws Exception {
		String[] itemList = StringUtil.split(vo.getCtgrCd(),"|");
		// 목록을 한꺼번에 조회
		List<EtcRelatedSiteCtgrVO> itemArray = etcRelatedSiteCtgrMapper.list(vo);
		// 이중 포문으로 bannerArray에 변경된 order를 다시 저장
		// 만약 파라매터로 코드키가 누락되는 경우가 있다면... 로직 수정 필요.
		for (EtcRelatedSiteCtgrVO erscvo : itemArray) {
			for (int order = 0; order < itemList.length; order++) {
				if(erscvo.getCtgrCd().toString().equals(itemList[order]) ) {
					erscvo.setCtgrOdr((order+1)+"");	// 1부터 차례로 순서값을 지정
					etcRelatedSiteCtgrMapper.update(erscvo);
				}
			}
		}
		return 1;
	}	
	
	/**
	 * 관련사이트 분류 정보를 삭제 한다.
	 * @param EtcRelatedSiteCtgrVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int removeCtgr(EtcRelatedSiteCtgrVO vo) throws Exception {
		//하위 연관 사이트 삭제
		EtcRelatedSiteVO ersvo = new EtcRelatedSiteVO();
		ersvo.setOrgCd(vo.getOrgCd());
		ersvo.setCtgrCd(vo.getCtgrCd());
		etcRelatedSiteMapper.deleteAll(ersvo);
		
		return etcRelatedSiteCtgrMapper.delete(vo);
	}
	
	/**
	 * 관련사이트 전체 목록을 조회한다.
	 * @param EtcRelatedSiteVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<EtcRelatedSiteVO> listSite(EtcRelatedSiteVO vo) throws Exception {
		ProcessResultListVO<EtcRelatedSiteVO> resultList = new ProcessResultListVO<EtcRelatedSiteVO>(); 
		try {
			List<EtcRelatedSiteVO> siteList =  etcRelatedSiteMapper.list(vo);
			resultList.setResult(1);
			resultList.setReturnList(siteList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
	/**
	 * 관련사이트 페이징 목록을 조회한다.
	 * @param EtcRelatedSiteVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<EtcRelatedSiteVO> listSitePageing(EtcRelatedSiteVO vo, 
			int pageIndex, int listScale, int pageScale) throws Exception {
		ProcessResultListVO<EtcRelatedSiteVO> resultList = new ProcessResultListVO<EtcRelatedSiteVO>(); 
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(pageIndex);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = etcRelatedSiteMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<EtcRelatedSiteVO> siteList =  etcRelatedSiteMapper.listPageing(vo);
			resultList.setResult(1);
			resultList.setReturnList(siteList);
			resultList.setPageInfo(paginationInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
	/**
	 * 관련사이트 페이징 목록을 조회한다.
	 * @param EtcRelatedSiteVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<EtcRelatedSiteVO> listSitePageing(EtcRelatedSiteVO vo, 
			int pageIndex, int listScale) throws Exception {
		return this.listSitePageing(vo, pageIndex, listScale, Constants.LIST_PAGE_SCALE);
	}
	
	/**
	 * 관련사이트 페이징 목록을 조회한다.
	 * @param EtcRelatedSiteVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<EtcRelatedSiteVO> listSitePageing(EtcRelatedSiteVO vo, 
			int pageIndex) throws Exception {
		return this.listSitePageing(vo, pageIndex, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}	
	
	/**
	 * 관련사이트 상세 정보를 조회한다.
	 * @param EtcRelatedSiteVO
	 * @return EtcRelatedSiteVO
	 * @throws Exception
	 */
	@Override
	public EtcRelatedSiteVO viewSite(EtcRelatedSiteVO vo) throws Exception {
		return etcRelatedSiteMapper.select(vo);
	}
	
	/**
	 * 관련사이트 정보를 등록한다.
	 * @param EtcRelatedSiteVO
	 * @return String
	 * @throws Exception
	 */
	@Override
	public int addSite(EtcRelatedSiteVO vo) throws Exception {
		if(ValidationUtils.isEmpty(vo.getSiteSn())) {
			vo.setSiteSn(etcRelatedSiteMapper.selectKey()+"");
		}
		return etcRelatedSiteMapper.insert(vo);
	}	
	
	/**
	 * 관련사이트 정보를 수정한다.
	 * @param EtcRelatedSiteVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int editSite(EtcRelatedSiteVO vo) throws Exception {
		return etcRelatedSiteMapper.update(vo);
	}
	
	/**
	 * 관련사이트 정렬 순서를 변경한다.
	 * @param EtcRelatedSiteVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int sortSite(EtcRelatedSiteVO vo) throws Exception {
		String[] itemList = StringUtil.split(vo.getSiteSn(),"|");
		// 목록을 한꺼번에 조회
		List<EtcRelatedSiteVO> itemArray = etcRelatedSiteMapper.list(vo);
		// 이중 포문으로 bannerArray에 변경된 order를 다시 저장
		// 만약 파라매터로 코드키가 누락되는 경우가 있다면... 로직 수정 필요.
		for (EtcRelatedSiteVO erscvo : itemArray) {
			for (int order = 0; order < itemList.length; order++) {
				if(erscvo.getSiteSn().toString().equals(itemList[order]) ) {
					erscvo.setSiteOdr(order+1);	// 1부터 차례로 순서값을 지정
					etcRelatedSiteMapper.update(erscvo);
				}
			}
		}
		return 1;
	}	
	
	/**
	 * 관련사이트 정보를 삭제 한다.
	 * @param EtcRelatedSiteVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int removeSite(EtcRelatedSiteVO vo) throws Exception {
		return etcRelatedSiteMapper.delete(vo);
	}
	
	/**
	 * 관련사이트 페이징 목록을 조회한다.
	 * @param EtcRelatedSiteCtgrVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<EtcRelatedSiteCtgrVO> listSiteAll(EtcRelatedSiteCtgrVO vo) throws Exception {
		ProcessResultListVO<EtcRelatedSiteCtgrVO> resultList = new ProcessResultListVO<EtcRelatedSiteCtgrVO>(); 
		try {
			List<EtcRelatedSiteCtgrVO> ctgrList = etcRelatedSiteCtgrMapper.list(vo);
			List<EtcRelatedSiteCtgrVO> returnList = new ArrayList<EtcRelatedSiteCtgrVO>();
			for(EtcRelatedSiteCtgrVO erscvo : ctgrList) {
				EtcRelatedSiteVO ersvo = new EtcRelatedSiteVO();
				ersvo.setOrgCd(erscvo.getOrgCd());
				ersvo.setCtgrCd(erscvo.getCtgrCd());
				List<EtcRelatedSiteVO> siteList = this.listSite(ersvo).getReturnList();
				if(!siteList.isEmpty()) erscvo.setSubList(siteList);
				returnList.add(erscvo);
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
}
