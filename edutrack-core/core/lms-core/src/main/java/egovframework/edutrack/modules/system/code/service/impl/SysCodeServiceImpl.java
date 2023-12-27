package egovframework.edutrack.modules.system.code.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.system.code.service.SysCodeCtgrVO;
import egovframework.edutrack.modules.system.code.service.SysCodeLangVO;
import egovframework.edutrack.modules.system.code.service.SysCodeService;
import egovframework.edutrack.modules.system.code.service.SysCodeVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *  <b>시스템 - 시스템 코드 관리</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("sysCodeService")
public class SysCodeServiceImpl 
	extends EgovAbstractServiceImpl implements SysCodeService {

	/** dao */
    @Resource(name="sysCodeCtgrMapper")
    private SysCodeCtgrMapper sysCodeCtgrMapper;
    
    @Resource(name="sysCodeMapper")
    private SysCodeMapper sysCodeMapper;
    
    @Resource(name="sysCodeLangMapper")
    private SysCodeLangMapper sysCodeLangMapper;
    
    /**
	 *  코드 분류 전체 목록을 조회한다.
	 * @param SysCodeCtgrVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<SysCodeCtgrVO> listCtgr(SysCodeCtgrVO vo) throws Exception {
		ProcessResultListVO<SysCodeCtgrVO> resultList = new ProcessResultListVO<SysCodeCtgrVO>(); 
		try {
			List<SysCodeCtgrVO> codeCtgrList =  sysCodeCtgrMapper.list(vo);
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
	 * @param SysCodeCtgrVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<SysCodeCtgrVO> listCtgrPageing(SysCodeCtgrVO vo, 
			int pageIndex, int listScale, int pageScale) throws Exception {
		ProcessResultListVO<SysCodeCtgrVO> resultList = new ProcessResultListVO<SysCodeCtgrVO>(); 
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(pageIndex);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = sysCodeCtgrMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<SysCodeCtgrVO> codeCtgrList =  sysCodeCtgrMapper.listPageing(vo);
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
	 * @param SysCodeCtgrVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<SysCodeCtgrVO> listCtgrPageing(SysCodeCtgrVO vo, 
			int pageIndex, int listScale) throws Exception {
		return this.listCtgrPageing(vo, pageIndex, listScale, Constants.LIST_PAGE_SCALE);
	}
	
    /**
	 * 코드 분류 페이징 목록을 조회한다.
	 * @param SysCodeCtgrVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<SysCodeCtgrVO> listCtgrPageing(SysCodeCtgrVO vo, 
			int pageIndex) throws Exception {
		return this.listCtgrPageing(vo, pageIndex, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}	
	
	/**
	 * 코드 분류 상세 정보를 조회한다.
	 * @param SysCodeCtgrVO
	 * @return SysCodeCtgrVO
	 * @throws Exception
	 */
	@Override
	public SysCodeCtgrVO viewCtgr(SysCodeCtgrVO vo) throws Exception {
		SysCodeCtgrVO sccvo = new SysCodeCtgrVO();
		sccvo= sysCodeCtgrMapper.select(vo);
		if(ValidationUtils.isNotEmpty(sccvo)) {
			vo = sccvo;
		} 
		return vo;
	}
	
	/**
	 * 코드 분류 정보를 등록한다.
	 * @param SysCodeCtgrVO
	 * @return String
	 * @throws Exception
	 */
	@Override
	public int addCtgr(SysCodeCtgrVO vo) throws Exception {
		return sysCodeCtgrMapper.insert(vo);
	}	
	
	/**
	 * 코드 분류 정보를 수정한다.
	 * @param SysCodeCtgrVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int editCtgr(SysCodeCtgrVO vo) throws Exception {
		return sysCodeCtgrMapper.update(vo);
	}
	
	/**
	 * 코드 분류 정보를 삭제 한다.
	 * @param SysCodeCtgrVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int removeCtgr(String codeCtgrCd) throws Exception {
		//-- 분류 하위의 모든 언어 정보를 삭제함.
		SysCodeLangVO sysCodeLangVO = new SysCodeLangVO();
		sysCodeLangVO.setCodeCtgrCd(codeCtgrCd);
		sysCodeLangMapper.deleteAllByCtgr(sysCodeLangVO);
		
		//-- 분류 하위의 모든 코드 정보를 삭제함.
		SysCodeVO sysCodeVO = new SysCodeVO();
		sysCodeVO.setCodeCtgrCd(codeCtgrCd);
		sysCodeMapper.deleteAll(sysCodeVO);
		
		SysCodeCtgrVO sysCodeCtgrVO = new SysCodeCtgrVO();
		sysCodeCtgrVO.setCodeCtgrCd(codeCtgrCd);
		
		return sysCodeCtgrMapper.delete(sysCodeCtgrVO);
	}

	/**
	 * 코드 정보의 목록를 반환한다.
	 * @param SysCodeCtgrVO
	 * @return ProcessResultListDTO<SysCodeVO>
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<SysCodeVO> listCode(String codeCtgrCd) throws Exception {
		return this.listCode(codeCtgrCd, true);
	}
	
	/**
	 * 코드 정보의 목록를 반환한다.
	 * @param codeCtgrCd
	 * @return ProcessResultListDTO<SysCodeVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultListVO<SysCodeVO> listCode(String codeCtgrCd, boolean use) throws Exception {
		List<SysCodeVO> codeList = listCodeByDB(codeCtgrCd).getReturnList();
		ProcessResultListVO<SysCodeVO> result = new ProcessResultListVO<SysCodeVO>();
		List<SysCodeVO> returnList = new ArrayList<SysCodeVO>();
		for(SysCodeVO scvo : codeList) {
			if(use) {
				if("Y".equals(scvo.getUseYn())) {
					returnList.add(scvo);
				}
			} else {
				returnList.add(scvo);
			}
		}
		result.setResult(1);
		result.setReturnList(returnList);
		return result;
	}
	
	/**
	 * 메뉴가 변경되었음을 DB에 저장한다.
	 */
	@Override
	public void setCodeChanged() throws Exception {
		sysCodeMapper.updateVersion();
	}
	
	/**
	 * 메뉴의 버젼값을 DB와 비교한다.
	 * @return true:변경됨, false:변경되지 않음
	 * @throws Exception  
	 */
	@Override
	public int selectCodeVersion() throws Exception {
		return sysCodeMapper.selectVersion();
	}	

	/**
	 * DB에서 코드를 읽어와 리턴한다.
	 * @param codeCtgrCd
	 * @return ProcessResultListVO<SysCodeVO>
	 */
	@Override
	public ProcessResultListVO<SysCodeVO> listCodeByDB(String codeCtgrCd) throws Exception {
		SysCodeVO scvo = new SysCodeVO();
		scvo.setCodeCtgrCd(codeCtgrCd);

		List<SysCodeVO> codeList = sysCodeMapper.list(scvo);
		ProcessResultListVO<SysCodeVO> result = new ProcessResultListVO<SysCodeVO>();
		List<SysCodeVO> returnList = new ArrayList<SysCodeVO>();
		for(SysCodeVO svo : codeList) {
			SysCodeLangVO sclvo = new SysCodeLangVO();
			sclvo.setCodeCtgrCd(svo.getCodeCtgrCd());
			sclvo.setCodeCd(svo.getCodeCd());
			List<SysCodeLangVO> codeLangList = sysCodeLangMapper.list(sclvo);
			svo.setCodeLangList(codeLangList);
			returnList.add(svo);
		}
		result.setResult(1);
		result.setReturnList(returnList);
		return result;
	}
	
	/**
	 * 코드의 정보를 조회한다.
	 * @param cdCtgrCd
	 * @param cdCd
	 * @return SysCodeVO
	 */
	@Override
	public SysCodeVO viewCode(String codeCtgrCd, String codeCd) throws Exception {
		SysCodeVO vo = new SysCodeVO();
		vo.setCodeCtgrCd(codeCtgrCd);
		vo.setCodeCd(codeCd);

		SysCodeVO scvo = new SysCodeVO();
		scvo = sysCodeMapper.select(vo);
		if(ValidationUtils.isNotEmpty(scvo)) {
			vo = scvo;
		}
		SysCodeLangVO sclvo = new SysCodeLangVO();
		sclvo.setCodeCtgrCd(codeCtgrCd);
		sclvo.setCodeCd(codeCd);
		List<SysCodeLangVO> codeLangList = sysCodeLangMapper.list(sclvo);
		vo.setCodeLangList(codeLangList);

		return vo;
	}

	/**
	 * 코드 정보를 등록한다.
	 * @param SysCodeVO
	 * @return String
	 * @throws Exception
	 */
	@Override
	public int addCode(SysCodeVO vo) throws Exception {
		int result = sysCodeMapper.insert(vo);
		List<SysCodeLangVO> codeLangList = vo.getCodeLangList();
		for(SysCodeLangVO sclvo : codeLangList) {
			try {
				sysCodeLangMapper.insert(sclvo);
			} catch(Exception e) {
				sysCodeLangMapper.update(sclvo);
			}
		}
		this.setCodeChanged();
		return result;
	}
	
	/**
	 * 코드 정보를 수정한다.
	 * @param SysCodeVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int editCode(SysCodeVO vo) throws Exception {
		int result = sysCodeMapper.update(vo);
		List<SysCodeLangVO> codeLangList = vo.getCodeLangList();
		for(SysCodeLangVO sclvo : codeLangList) {
			try {
				sysCodeLangMapper.insert(sclvo);
			} catch(Exception e) {
				sysCodeLangMapper.update(sclvo);
			}
		}
		this.setCodeChanged();
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
	public int removeCode(String codeCtgrCd, String codeCd) throws Exception {
		SysCodeLangVO sclvo = new SysCodeLangVO();
		sclvo.setCodeCtgrCd(codeCtgrCd);
		sclvo.setCodeCd(codeCd);
		sysCodeLangMapper.deleteAll(sclvo);
		
		SysCodeVO scvo = new SysCodeVO();
		scvo.setCodeCtgrCd(codeCtgrCd);
		scvo.setCodeCd(codeCd);
		int result = sysCodeMapper.delete(scvo);

		this.setCodeChanged();
		return result;
	}	

	/**
	 * 코드의 순서를 변경한다.
	 * @param SysCodeVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int sortCode(SysCodeVO vo) throws Exception {
		int result = 0;
		String[] codeList = StringUtil.split(vo.getCodeCd(),"|");

		// 하위 코드 목록을 한꺼번에 조회
		List<SysCodeVO> codeArray = sysCodeMapper.list(vo);

		// 이중 포문으로 codeArray에 변경된 order를 다시 저장
		// 만약 파라매터로 코드키가 누락되는 경우가 있다면... 로직 수정 필요.
		for (SysCodeVO scvo : codeArray) {
			for (int order = 0; order < codeList.length; order++) {
				if(scvo.getCodeCd().equals(codeList[order])) {
					scvo.setCodeOdr(order+1);	// 1부터 차례로 순서값을 지정
					sysCodeMapper.update(scvo);
				}
			}
		}

		// 변경된 시스템코드 어래이를 일괄 저장.
		this.setCodeChanged();
		return result;
	}	
	/**
	 * 시스템 코드 목록 (직업구분에 따른 JOB_CTGR )
	 * @param codeCtgrCd
	 * @param codeCd
	 * @return
	 */
	@Override
	public List<SysCodeVO> listSelectCd(String codeCtgrCd, String codeCd) throws Exception {
		SysCodeVO vo = new SysCodeVO();
		vo.setCodeCtgrCd(codeCtgrCd);
		vo.setCodeCd(codeCd);
		return sysCodeMapper.listSelectCd(vo);
	}

	/**
	 * 시스템 코드 목록 (직업구분에 따른 JOB_CTGR의 CODE_OPTN코드 조회 )
	 * @param codeCtgrCd
	 * @param codeOptn
	 * @return
	 */
	@Override
	public List<SysCodeVO> listSelectOptn(String codeCtgrCd, String codeOptn) throws Exception {
		SysCodeVO vo = new SysCodeVO();
		vo.setCodeCtgrCd(codeCtgrCd);
		vo.setCodeOptn(codeOptn);
		return sysCodeMapper.listSelectOptn(vo);
	}
}
