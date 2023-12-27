package egovframework.edutrack.modules.org.code.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.modules.org.code.service.OrgCodeCtgrVO;
import egovframework.edutrack.modules.org.code.service.OrgCodeLangVO;
import egovframework.edutrack.modules.org.code.service.OrgCodeService;
import egovframework.edutrack.modules.org.code.service.OrgCodeVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *  <b>기관 - 기관 코드 관리</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("orgCodeService")
public class OrgCodeServiceImpl 
	extends EgovAbstractServiceImpl implements OrgCodeService{

	/** dao */
/*    @Resource(name="orgCodeCtgrDAO")
    private OrgCodeCtgrMapper orgCodeCtgrMapper;
    
    @Resource(name="orgCodeDAO")
    private OrgCodeMapper orgCodeMapper;
    
    @Resource(name="orgCodeLangDAO")
    private OrgCodeLangMapper orgCodeLangMapper;*/
	
	/** Mapper */
	@Resource(name="orgCodeCtgrMapper")
	private OrgCodeCtgrMapper 		orgCodeCtgrMapper;
	
	@Resource(name="orgCodeMapper")
	private OrgCodeMapper 		orgCodeMapper;
	
	@Resource(name="orgCodeLangMapper")
	private OrgCodeLangMapper 		orgCodeLangMapper;
    
    /**
	 *  코드 분류 전체 목록을 조회한다.
	 * @param OrgCodeCtgrVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<OrgCodeCtgrVO> listCtgr(OrgCodeCtgrVO vo) throws Exception {
		ProcessResultListVO<OrgCodeCtgrVO> resultList = new ProcessResultListVO<OrgCodeCtgrVO>(); 
		try {
			List<OrgCodeCtgrVO> codeCtgrList =  orgCodeCtgrMapper.list(vo);
			resultList.setResult(1);
			resultList.setReturnList(codeCtgrList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
    /**
	 * 코드 분류 페이징 목록을 조회한다.
	 * @param OrgCodeCtgrVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<OrgCodeCtgrVO> listCtgrPageing(OrgCodeCtgrVO vo, 
			int pageIndex, int listScale, int pageScale) throws Exception {
		ProcessResultListVO<OrgCodeCtgrVO> resultList = new ProcessResultListVO<OrgCodeCtgrVO>(); 
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(pageIndex);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = orgCodeCtgrMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<OrgCodeCtgrVO> codeCtgrList =  orgCodeCtgrMapper.listPageing(vo);
			resultList.setResult(1);
			resultList.setReturnList(codeCtgrList);
			resultList.setPageInfo(paginationInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
    /**
	 * 코드 분류 페이징 목록을 조회한다.
	 * @param OrgCodeCtgrVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<OrgCodeCtgrVO> listCtgrPageing(OrgCodeCtgrVO vo, 
			int pageIndex, int listScale) throws Exception {
		return this.listCtgrPageing(vo, pageIndex, listScale, Constants.LIST_PAGE_SCALE);
	}
	
    /**
	 * 코드 분류 페이징 목록을 조회한다.
	 * @param OrgCodeCtgrVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<OrgCodeCtgrVO> listCtgrPageing(OrgCodeCtgrVO vo, 
			int pageIndex) throws Exception {
		return this.listCtgrPageing(vo, pageIndex, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}	
	
	/**
	 * 코드 분류 상세 정보를 조회한다.
	 * @param OrgCodeCtgrVO
	 * @return OrgCodeCtgrVO
	 * @throws Exception
	 */
	@Override
	public OrgCodeCtgrVO viewCtgr(OrgCodeCtgrVO vo) throws Exception {
		return orgCodeCtgrMapper.select(vo);
	}
	
	/**
	 * 코드 분류 정보를 등록한다.
	 * @param OrgCodeCtgrVO
	 * @return String
	 * @throws Exception
	 */
	@Override
	public int addCtgr(OrgCodeCtgrVO vo) throws Exception {
		return orgCodeCtgrMapper.insert(vo);
	}	
	
	/**
	 * 코드 분류 정보를 수정한다.
	 * @param OrgCodeCtgrVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int editCtgr(OrgCodeCtgrVO vo) throws Exception {
		return orgCodeCtgrMapper.update(vo);
	}
	
	/**
	 * 코드 분류 정보를 삭제 한다.
	 * @param OrgCodeCtgrVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int removeCtgr(String orgCd, String codeCtgrCd) throws Exception {
		//-- 분류 하위의 모든 언어 정보를 삭제함.
		OrgCodeLangVO orgCodeLangVO = new OrgCodeLangVO();
		orgCodeLangVO.setOrgCd(orgCd);
		orgCodeLangVO.setCodeCtgrCd(codeCtgrCd);
		orgCodeLangMapper.deleteAllByCtgr(orgCodeLangVO);
		
		//-- 분류 하위의 모든 코드 정보를 삭제함.
		OrgCodeVO orgCodeVO = new OrgCodeVO();
		orgCodeVO.setOrgCd(orgCd);
		orgCodeVO.setCodeCtgrCd(codeCtgrCd);
		orgCodeMapper.deleteAll(orgCodeVO);
		
		OrgCodeCtgrVO sysCodeCtgrVO = new OrgCodeCtgrVO();
		sysCodeCtgrVO.setOrgCd(orgCd);
		sysCodeCtgrVO.setCodeCtgrCd(codeCtgrCd);
		
		return orgCodeCtgrMapper.delete(sysCodeCtgrVO);
	}
	

	/**
	 * 코드 정보의 목록를 반환한다.
	 * @param OrgCodeCtgrVO
	 * @return ProcessResultListDTO<OrgCodeVO>
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<OrgCodeVO> listCode(String orgCd, String codeCtgrCd) throws Exception {
		return this.listCode(orgCd, codeCtgrCd, true);
	}

	/**
	 * 코드 정보의 목록를 반환한다.
	 * @param codeCtgrCd
	 * @return ProcessResultListDTO<OrgCodeVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultListVO<OrgCodeVO> listCode(String orgCd, String codeCtgrCd, boolean use) throws Exception {
		//List<OrgCodeVO> codeList = listCodeWithCache(codeCtgrCd);
		OrgCodeVO orgCodeVO = new OrgCodeVO();
		orgCodeVO.setOrgCd(orgCd);
		orgCodeVO.setCodeCtgrCd(codeCtgrCd);
		List<OrgCodeVO> codeList = orgCodeMapper.list(orgCodeVO);
		ProcessResultListVO<OrgCodeVO> result = new ProcessResultListVO<OrgCodeVO>();
		List<OrgCodeVO> returnList = new ArrayList<OrgCodeVO>();
		for(OrgCodeVO ocvo : codeList) {
			if(use) {
				if("Y".equals(ocvo.getUseYn())) returnList.add(ocvo);
			} else {
				returnList.add(ocvo);
			}
		}
		result.setResult(1);
		result.setReturnList(returnList);
		return result;
	}
	
	/**
	 * 코드의 정보를 조회한다.
	 * @param cdCtgrCd
	 * @param cdCd
	 * @return OrgCodeVO
	 */
	@Override
	public OrgCodeVO viewCode(String orgCd, String codeCtgrCd, String codeCd) throws Exception {
		OrgCodeVO orgCodeVO = new OrgCodeVO();
		orgCodeVO.setOrgCd(orgCd);
		orgCodeVO.setCodeCtgrCd(codeCtgrCd);
		orgCodeVO.setCodeCd(codeCd);

		orgCodeVO = orgCodeMapper.select(orgCodeVO);

		OrgCodeLangVO orgCodeLangVO = new OrgCodeLangVO();
		orgCodeLangVO.setCodeCtgrCd(codeCtgrCd);
		orgCodeLangVO.setCodeCd(codeCd);
		List<OrgCodeLangVO> codeLangList = orgCodeLangMapper.list(orgCodeLangVO);
		orgCodeVO.setCodeLangList(codeLangList);

		return orgCodeVO;
	}
	/**
	 * 코드의 정보를 조회한다.
	 * @param cdCtgrCd
	 * @param cdCd
	 * @return OrgCodeVO
	 */
	@Override
	public OrgCodeVO viewCode(OrgCodeVO orgCodeVO) throws Exception {

		orgCodeVO = orgCodeMapper.select(orgCodeVO);

		OrgCodeLangVO orgCodeLangVO = new OrgCodeLangVO();
		orgCodeLangVO.setCodeCtgrCd(orgCodeVO.getCodeCtgrCd());
		orgCodeLangVO.setCodeCd(orgCodeVO.getCodeCd());
		List<OrgCodeLangVO> codeLangList = orgCodeLangMapper.list(orgCodeLangVO);
		orgCodeVO.setCodeLangList(codeLangList);

		return orgCodeVO;
	}

	/**
	 * 코드 정보를 등록한다.
	 * @param OrgCodeVO
	 * @return String
	 * @throws Exception
	 */
	@Override
	public int addCode(OrgCodeVO vo) throws Exception {
		String codeCtgr = "";
		if("Y".equals(vo.getAutoMakeYn())) {
			
			if(vo.getCodeCtgrCd().equals("USER_DIV_CD")){
				codeCtgr = "UDIV";
			} else if(vo.getCodeCtgrCd().equals("AREA_CD")){
				codeCtgr = "AREA";
			} else if(vo.getCodeCtgrCd().equals("DEPT_CD")){
				codeCtgr = "DEPT";
			} else if(vo.getCodeCtgrCd().equals("JOB_CD")){
				codeCtgr = "JOB";
			} else {
				codeCtgr = "CODE";
			}
			OrgCodeVO vo2 = new OrgCodeVO();
			vo2.setCodeCtgrCd(codeCtgr);
			vo.setCodeCd(orgCodeMapper.selectKey(vo2));
		}
		int result = orgCodeMapper.insert(vo);
		return result;
	}
	
	/**
	 * 코드 정보를 수정한다.
	 * @param OrgCodeVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int editCode(OrgCodeVO vo) throws Exception {
		int result = orgCodeMapper.update(vo);
		List<OrgCodeLangVO> codeLangList = vo.getCodeLangList();
		for(OrgCodeLangVO oclvo : codeLangList) {
			try {
				orgCodeLangMapper.insert(oclvo);
			} catch(Exception e) {
				orgCodeLangMapper.update(oclvo);
			}
		}
		return result;
	}
	
	/**
	 * 코드 정보를 삭제한다.
	 * @param codeCtgrCd
	 * @param codeCd
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int removeCode(String orgCd, String codeCtgrCd, String codeCd) throws Exception {
		OrgCodeLangVO orgCodeLangVO = new OrgCodeLangVO();
		orgCodeLangVO.setOrgCd(orgCd);
		orgCodeLangVO.setCodeCtgrCd(codeCtgrCd);
		orgCodeLangVO.setCodeCd(codeCd);
		orgCodeLangMapper.deleteAll(orgCodeLangVO);
		
		OrgCodeVO orgCodeVO = new OrgCodeVO();
		orgCodeVO.setOrgCd(orgCd);
		orgCodeVO.setCodeCtgrCd(codeCtgrCd);
		orgCodeVO.setCodeCd(codeCd);
		int result = orgCodeMapper.delete(orgCodeVO);

		return result;
	}	

	/**
	 * 코드 정보를 수정한다.
	 * @param OrgCodeVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int sortCode(OrgCodeVO vo) throws Exception {
		int result = 0;
		String[] codeList = StringUtil.split(vo.getCodeCd(),"|");

		// 하위 코드 목록을 한꺼번에 조회
		List<OrgCodeVO> codeArray = orgCodeMapper.list(vo);

		// 이중 포문으로 codeArray에 변경된 order를 다시 저장
		// 만약 파라매터로 코드키가 누락되는 경우가 있다면... 로직 수정 필요.
		for (OrgCodeVO scvo : codeArray) {
			for (int order = 0; order < codeList.length; order++) {
				if(scvo.getCodeCd().equals(codeList[order])) {
					scvo.setCodeOdr(order+1);	// 1부터 차례로 순서값을 지정
				}
			}
		}

		// 변경된 시스템코드 어래이를 일괄 저장.
		result = orgCodeMapper.updateBatch(codeArray);
		return result;
	}
	
	/**
	 * 코드 페이징 목록을 가져온다.
	 */
	@Override
	public ProcessResultListVO<OrgCodeVO> listCodePageing(OrgCodeVO vo) throws Exception {
		
		ProcessResultListVO<OrgCodeVO> resultList = new ProcessResultListVO<OrgCodeVO>(); 
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(vo.getCurPage());
			paginationInfo.setRecordCountPerPage(vo.getListScale());
			paginationInfo.setPageSize(vo.getPageScale());
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = orgCodeMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<OrgCodeVO> returnList =  orgCodeMapper.listPageing(vo);
			resultList.setReturnList(returnList);				
			resultList.setResult(1);
			resultList.setPageInfo(paginationInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}	
	/**
	 * 시스템 코드 언어 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public List<OrgCodeLangVO> langList(OrgCodeLangVO dto) throws Exception {
		return orgCodeLangMapper.list(dto);
	}

}
