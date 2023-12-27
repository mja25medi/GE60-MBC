package egovframework.edutrack.modules.system.config.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.system.config.service.SysCfgCtgrVO;
import egovframework.edutrack.modules.system.config.service.SysCfgService;
import egovframework.edutrack.modules.system.config.service.SysCfgVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *  <b>시스템 - 시스템 설정 정보 관리</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("sysCfgService")
public class SysCfgServiceImpl 
	extends EgovAbstractServiceImpl implements SysCfgService {
	
    @Resource(name="sysCfgCtgrMapper")
    private SysCfgCtgrMapper sysCfgCtgrMapper;
    
    @Resource(name="sysCfgMapper")
    private SysCfgMapper sysCfgMapper;
    
    /**
 	 *  설정 분류 전체 목록을 조회한다.
 	 * @param SysCfgCtgrVO
 	 * @return ProcessResultListVO
 	 * @throws Exception
 	 */
 	@Override
	public ProcessResultListVO<SysCfgCtgrVO> listCtgr(SysCfgCtgrVO vo) throws Exception {
 		ProcessResultListVO<SysCfgCtgrVO> resultList = new ProcessResultListVO<SysCfgCtgrVO>(); 
 		List<SysCfgCtgrVO> cfgCtgrList =  sysCfgCtgrMapper.list(vo);
 		resultList.setResult(1);
 		resultList.setReturnList(cfgCtgrList);
 		return resultList;
 	}
 	
     /**
 	 * 설정 분류 페이징 목록을 조회한다.
 	 * @param SysCfgCtgrVO
 	 * @param pageIndex
 	 * @param listScale
 	 * @param pageScale
 	 * @return ProcessResultListVO
 	 * @throws Exception
 	 */
 	@Override
	public ProcessResultListVO<SysCfgCtgrVO> listCtgrPageing(SysCfgCtgrVO vo, 
 			int pageIndex, int listScale, int pageScale) throws Exception {
 		ProcessResultListVO<SysCfgCtgrVO> resultList = new ProcessResultListVO<SysCfgCtgrVO>(); 
 		
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pageIndex);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		
		// 전체 목록 수
		int totalCount = sysCfgCtgrMapper.count(vo);
		paginationInfo.setTotalRecordCount(totalCount);
		
		List<SysCfgCtgrVO> cfgCtgrList =  sysCfgCtgrMapper.listPageing(vo);
		resultList.setResult(1);
		resultList.setReturnList(cfgCtgrList);
		resultList.setPageInfo(paginationInfo);
 			
 		return resultList;
 	}
 	
 	/**
 	 * 설정 분류 페이징 목록을 조회한다.
 	 * @param SysCfgCtgrVO
 	 * @param pageIndex
 	 * @param listScale
 	 * @param pageScale
 	 * @return ProcessResultListVO
 	 * @throws Exception
 	 */
 	@Override
 	public ProcessResultListVO<SysCfgVO> listConfig(SysCfgCtgrVO vo) throws Exception {
 		ProcessResultListVO<SysCfgVO> resultList = new ProcessResultListVO<SysCfgVO>(); 
 		
 		List<SysCfgVO> cfgList =  sysCfgMapper.listConfig(vo);
 		resultList.setReturnList(cfgList);
 		
 		return resultList;
 	}
 	
     /**
 	 * 설정 분류 페이징 목록을 조회한다.
 	 * @param SysCfgCtgrVO
 	 * @param pageIndex
 	 * @param listScale
 	 * @return ProcessResultListVO
 	 * @throws Exception
 	 */
 	@Override
	public ProcessResultListVO<SysCfgCtgrVO> listCtgrPageing(SysCfgCtgrVO vo, 
 			int pageIndex, int listScale) throws Exception {
 		return this.listCtgrPageing(vo, pageIndex, listScale, Constants.LIST_PAGE_SCALE);
 	}
 	
     /**
 	 * 설정 분류 페이징 목록을 조회한다.
 	 * @param SysCfgCtgrVO
 	 * @param pageIndex
 	 * @return ProcessResultListVO
 	 * @throws Exception
 	 */
 	@Override
	public ProcessResultListVO<SysCfgCtgrVO> listCtgrPageing(SysCfgCtgrVO vo, 
 			int pageIndex) throws Exception {
 		return this.listCtgrPageing(vo, pageIndex, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
 	}	
 	
 	/**
 	 * 설정 분류 상세 정보를 조회한다.
 	 * @param SysCfgCtgrVO
 	 * @return SysCfgCtgrVO
 	 * @throws Exception
 	 */
 	@Override
	public SysCfgCtgrVO viewCtgr(SysCfgCtgrVO vo) throws Exception {
		SysCfgCtgrVO sccvo = new SysCfgCtgrVO();
		sccvo = sysCfgCtgrMapper.select(vo);
		if(ValidationUtils.isNotEmpty(sccvo)) {
			vo = sccvo;
		} 	
 		return vo;
 	}
 	
 	/**
 	 * 설정 분류 정보를 등록한다.
 	 * @param SysCfgCtgrVO
 	 * @return String
 	 * @throws Exception
 	 */
 	@Override
	public int addCtgr(SysCfgCtgrVO vo) throws Exception {
 		return sysCfgCtgrMapper.insert(vo);
 	}	
 	
 	/**
 	 * 설정 분류 정보를 수정한다.
 	 * @param SysCfgCtgrVO
 	 * @return int
 	 * @throws Exception
 	 */
 	@Override
	public int editCtgr(SysCfgCtgrVO vo) throws Exception {
 		return sysCfgCtgrMapper.update(vo);
 	}
 	
 	/**
 	 * 설정 분류 정보를 삭제 한다.
 	 * @param SysCfgCtgrVO
 	 * @return int
 	 * @throws Exception
 	 */
 	@Override
	public int removeCtgr(String cfgCtgrCd) throws Exception {
 		//-- 분류 하위의 모든 설정 정보를 삭제함.
 		SysCfgVO sysCfgVO = new SysCfgVO();
 		sysCfgVO.setCfgCtgrCd(cfgCtgrCd);
 		sysCfgMapper.deleteAll(sysCfgVO);
 		
 		SysCfgCtgrVO sysCfgCtgrVO = new SysCfgCtgrVO();
 		sysCfgCtgrVO.setCfgCtgrCd(cfgCtgrCd);
 		
 		return sysCfgCtgrMapper.delete(sysCfgCtgrVO);
 	}
 	
    /**
 	 *  설정 정보 전체 목록을 조회한다.
 	 * @param SysCfgCtgrVO
 	 * @return ProcessResultListVO
 	 * @throws Exception
 	 */
 	@Override
	public ProcessResultListVO<SysCfgVO> listCfg(SysCfgVO vo) throws Exception {
 		ProcessResultListVO<SysCfgVO> resultList = new ProcessResultListVO<SysCfgVO>(); 
 		List<SysCfgVO> cfgList =  sysCfgMapper.list(vo);
 		resultList.setResult(1);
 		resultList.setReturnList(cfgList);
 		return resultList;
 	}
 	
     /**
 	 * 설정 정보 페이징 목록을 조회한다.
 	 * @param SysCfgCtgrVO
 	 * @param pageIndex
 	 * @param listScale
 	 * @param pageScale
 	 * @return ProcessResultListVO
 	 * @throws Exception
 	 */
 	@Override
	public ProcessResultListVO<SysCfgVO> listCfgPageing(SysCfgVO vo, 
 			int pageIndex, int listScale, int pageScale) throws Exception {
 		ProcessResultListVO<SysCfgVO> resultList = new ProcessResultListVO<SysCfgVO>(); 
 		
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pageIndex);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		
		// 전체 목록 수
		int totalCount = sysCfgMapper.count(vo);
		paginationInfo.setTotalRecordCount(totalCount);
		
		List<SysCfgVO> codeCtgrList =  sysCfgMapper.listPageing(vo);
		resultList.setResult(1);
		resultList.setReturnList(codeCtgrList);
		resultList.setPageInfo(paginationInfo);
 			
 		return resultList;
 	}
 	
     /**
 	 * 설정 정보 페이징 목록을 조회한다.
 	 * @param SysCfgCtgrVO
 	 * @param pageIndex
 	 * @param listScale
 	 * @return ProcessResultListVO
 	 * @throws Exception
 	 */
 	@Override
	public ProcessResultListVO<SysCfgVO> listCfgPageing(SysCfgVO vo, 
 			int pageIndex, int listScale) throws Exception {
 		return this.listCfgPageing(vo, pageIndex, listScale, Constants.LIST_PAGE_SCALE);
 	}
 	
     /**
 	 * 설정 정보 페이징 목록을 조회한다.
 	 * @param SysCfgCtgrVO
 	 * @param pageIndex
 	 * @return ProcessResultListVO
 	 * @throws Exception
 	 */
 	@Override
	public ProcessResultListVO<SysCfgVO> listCfgPageing(SysCfgVO vo, 
 			int pageIndex) throws Exception {
 		return this.listCfgPageing(vo, pageIndex, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
 	}	
 	
 	/**
 	 * 설정 정보 상세 정보를 조회한다.
 	 * @param SysCfgCtgrVO
 	 * @return SysCfgCtgrVO
 	 * @throws Exception
 	 */
 	@Override
	public SysCfgVO viewCfg(SysCfgVO vo) throws Exception {
		SysCfgVO scvo = new SysCfgVO();
		scvo =  sysCfgMapper.select(vo);
		if(ValidationUtils.isNotEmpty(scvo)) {
			vo = scvo;
		}
 		
 		return vo;
 	}
 	
 	/**
 	 * 설정 정보 상제 정보를 등록한다.
 	 * @param SysCfgCtgrVO
 	 * @return String
 	 * @throws Exception
 	 */
 	@Override
	public int addCfg(SysCfgVO vo) throws Exception {
 		return sysCfgMapper.insert(vo);
 	}	
 	
 	/**
 	 * 설정 정보 상세 정보를 수정한다.
 	 * @param SysCfgCtgrVO
 	 * @return int
 	 * @throws Exception
 	 */
 	@Override
	public int editCfg(SysCfgVO vo) throws Exception {
 		return sysCfgMapper.update(vo);
 	}
 	
 	/**
 	 * 설정 정보 상세 정보를 삭제 한다.
 	 * @param SysCfgCtgrVO
 	 * @return int
 	 * @throws Exception
 	 */
 	@Override
	public int removeCfg(SysCfgVO vo) throws Exception {
 		return sysCfgMapper.delete(vo);
 	} 
 	
 	/**
 	 * 설정 정보 상세 정보를 조회한다.
 	 * @param cfgCtgrCd
 	 * @param cfgCd
 	 * @return SysCfgCtgrVO
 	 * @throws Exception
 	 */
 	@Override
	public String getValue(String cfgCtgrCd, String cfgCd) throws Exception {
 		SysCfgVO scvo = new SysCfgVO();
 		scvo.setCfgCtgrCd(cfgCtgrCd);
 		scvo.setCfgCd(cfgCd);
 		scvo = sysCfgMapper.select(scvo);
 		String result = scvo.getCfgVal();
 		return result;
 	} 	

}
