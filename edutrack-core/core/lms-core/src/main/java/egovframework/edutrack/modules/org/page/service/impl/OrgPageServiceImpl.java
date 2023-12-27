package egovframework.edutrack.modules.org.page.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.modules.org.page.service.OrgPageService;
import egovframework.edutrack.modules.org.page.service.OrgPageVO;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *  <b>기관 - 기관 페이지 관리</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("orgPageService")
public class OrgPageServiceImpl 
	extends EgovAbstractServiceImpl implements OrgPageService {

	private final class PageFilehandler
		implements FileHandler<OrgPageVO> {
	
		@Override
		public String getRepoCd() {
			return "PAGE";
		}
		
		@Override
		public String getPK(OrgPageVO vo) {
			return vo.getOrgCd() + Constants.SEPERATER_DB + vo.getPageCd();
		}
		
		@Override
		public List<SysFileVO> getFiles(OrgPageVO vo) {
			List<SysFileVO> fileList = vo.getAttachImages();
			fileList.addAll(vo.getAttachImages());
			return fileList;
		}
		
		@Override
		public OrgPageVO setFiles(OrgPageVO vo, FileListVO fileListVO) {
			vo.setAttachImages(fileListVO.getFiles("image"));
			return vo;
		}
	}
	
/*    @Resource(name="orgPageDAO")
    private OrgPageMapper 			orgPageMapper;*/
	
	/** Mapper */
	@Resource(name="orgPageMapper")
	private OrgPageMapper 		orgPageMapper;
    
	@Resource(name="sysFileService")
	private SysFileService		sysFileService;
	
    /**
	 *  기관 페이지 전체 목록을 조회한다.
	 * @param OrgPageVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<OrgPageVO> list(OrgPageVO vo) throws Exception {
		ProcessResultListVO<OrgPageVO> resultList = new ProcessResultListVO<OrgPageVO>(); 
		try {
			List<OrgPageVO> pageList =  orgPageMapper.list(vo);
			resultList.setResult(1);
			resultList.setReturnList(pageList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
    /**
	 *  메뉴에 연결 되지 않은 기관 페이지 전체 목록을 조회한다.
	 * @param OrgPageVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<OrgPageVO> listForMenu(OrgPageVO vo) throws Exception {
		ProcessResultListVO<OrgPageVO> resultList = new ProcessResultListVO<OrgPageVO>(); 
		try {
			List<OrgPageVO> pageList =  orgPageMapper.listForMenu(vo);
			resultList.setResult(1);
			resultList.setReturnList(pageList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}	
	
    /**
	 * 기관 페이지 페이징 목록을 조회한다.
	 * @param OrgPageVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<OrgPageVO> listPageing(OrgPageVO vo, 
			int pageIndex, int listScale, int pageScale) throws Exception {
		ProcessResultListVO<OrgPageVO> resultList = new ProcessResultListVO<OrgPageVO>(); 
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(pageIndex);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = orgPageMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<OrgPageVO> pageList =  orgPageMapper.listPageing(vo);
			resultList.setResult(1);
			resultList.setReturnList(pageList);
			resultList.setPageInfo(paginationInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
    /**
	 * 기관 페이지 페이징 목록을 조회한다.
	 * @param OrgPageVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<OrgPageVO> listPageing(OrgPageVO vo, 
			int pageIndex, int listScale) throws Exception {
		return this.listPageing(vo, pageIndex, listScale, Constants.LIST_PAGE_SCALE);
	}
	
    /**
	 * 기관 페이지 페이징 목록을 조회한다.
	 * @param OrgPageVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<OrgPageVO> listPageing(OrgPageVO vo, 
			int pageIndex) throws Exception {
		return this.listPageing(vo, pageIndex, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}	
	
	/**
	 * 기관 페이지 상세 정보를 조회한다.
	 * @param OrgPageVO
	 * @return OrgPageVO
	 * @throws Exception
	 */
	@Override
	public OrgPageVO view(OrgPageVO vo) throws Exception {
		vo = orgPageMapper.select(vo);
		vo = sysFileService.getFile(vo, new PageFilehandler());
		this.pageUrlToView(vo);
		return vo;
	}
	
	/**
	 * 기관 페이지 정보를 등록한다.
	 * @param OrgPageVO
	 * @return String
	 * @throws Exception
	 */
	@Override
	public int add(OrgPageVO vo) throws Exception {
		int result = orgPageMapper.insert(vo);
		sysFileService.bindFile(vo, new PageFilehandler());		
		return result;
	}	
	
	/**
	 * 기관 페이지 정보를 수정한다.
	 * @param OrgPageVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int edit(OrgPageVO vo) throws Exception {
		sysFileService.bindFile(vo, new PageFilehandler());
		return orgPageMapper.update(vo);
	}
	
	/**
	 * 기관 페이지 정보를 삭제 한다.
	 * @param OrgPageVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int remove(OrgPageVO vo) throws Exception {
		//-- 분류 하위의 모든 언어 정보를 삭제함.
		sysFileService.bindFileUpdate(vo, new PageFilehandler());
		return orgPageMapper.delete(vo);
	}
	
	/**
	 * 기관의 페이지 정보 전체를 삭제 한다.
	 * @param OrgPageVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int removeAll(OrgPageVO vo) throws Exception {
		//-- 기관 페이지와 연결된 모든 파일을 삭제함.
		List<OrgPageVO> pageList = orgPageMapper.list(vo);
		for(OrgPageVO opvo : pageList) {
			sysFileService.bindFileUpdate(opvo, new PageFilehandler());
		}
		return orgPageMapper.deleteAll(vo);
	}
	
	/**
	 * 기관 페이지와 메뉴를 연결한다.
	 * @param OrgPageVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int editMenuCd(OrgPageVO vo) throws Exception {
		return orgPageMapper.updateMenuCd(vo);
	}
	
	/**
	 * 기관 페이지와 메뉴의 연결을 해제 한다.
	 * @param OrgPageVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int editMenuCdToNull(OrgPageVO vo) throws Exception {
		return orgPageMapper.updateMenuCdToNull(vo);
	}
	
	/**
	 * Default가 아닌 모든 페이지와 메뉴의 연결을 해제 한다.
	 * @param OrgPageVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int editAllMenuCdToNull(OrgPageVO vo) throws Exception {
		return orgPageMapper.updateAllMenuCdToNull(vo);
	}	
	
	private void pageUrlToPersist(OrgPageVO vo) {
		vo.setPageCts(StringUtil.replaceUrlToPersist(vo.getPageCts()));
	}

	private void pageUrlToView(OrgPageVO vo) {
		vo.setPageCts(StringUtil.replaceUrlToBrowser(vo.getPageCts()));
	}	
}
