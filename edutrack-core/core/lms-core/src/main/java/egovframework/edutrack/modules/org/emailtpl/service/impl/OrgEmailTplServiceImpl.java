package egovframework.edutrack.modules.org.emailtpl.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.modules.log.message.service.LogMsgLogVO;
import egovframework.edutrack.modules.org.emailtpl.service.OrgEmailTplService;
import egovframework.edutrack.modules.org.emailtpl.service.OrgEmailTplVO;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


/**
 *  <b>기관 - 기관 이메일 템플릿</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("orgEmailTplService")
public class OrgEmailTplServiceImpl 
	extends EgovAbstractServiceImpl implements OrgEmailTplService {
	
	private final class NestedFilehandler
		implements FileHandler<OrgEmailTplVO> {
	
		@Override
		public String getRepoCd() {
			return "EMAIL_TPL";
		}
	
		@Override
		public String getPK(OrgEmailTplVO dto) {
			return dto.getOrgCd() + Constants.SEPERATER_DB + dto.getTplCd();
		}
	
		@Override
		public List<SysFileVO> getFiles(OrgEmailTplVO dto) {
			return dto.getAttachImages();
		}
	
		@Override
		public OrgEmailTplVO setFiles(OrgEmailTplVO dto, FileListVO fileListVO) {
			dto.setAttachImages(fileListVO.getFiles("image"));
			return dto;
		}
	}	
	
	/** dao */
/*    @Resource(name="orgEmailTplDAO")
    private OrgEmailTplMapper 		orgEmailTplMapper;*/
	
	/** Mapper */
	@Resource(name="orgEmailTplMapper")
	private OrgEmailTplMapper 		orgEmailTplMapper;
    
	@Resource(name="sysFileService")
	private SysFileService 		sysFileService;    
    
    /* (non-Javadoc)
	 * @see egovframework.edutrack.modules.org.emailtpl.service.impl.OrgEmailTplService#list(egovframework.edutrack.modules.org.emailtpl.service.OrgEmailTplVO)
	 */
	@Override
	public ProcessResultListVO<OrgEmailTplVO> list(OrgEmailTplVO vo) throws Exception {
		ProcessResultListVO<OrgEmailTplVO> resultList = new ProcessResultListVO<OrgEmailTplVO>(); 
		try {
			List<OrgEmailTplVO> codeList =  orgEmailTplMapper.list(vo);
			resultList.setResult(1);
			resultList.setReturnList(codeList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
    /* (non-Javadoc)
	 * @see egovframework.edutrack.modules.org.emailtpl.service.impl.OrgEmailTplService#listPageing(egovframework.edutrack.modules.org.emailtpl.service.OrgEmailTplVO, int, int, int)
	 */
	@Override
	public ProcessResultListVO<OrgEmailTplVO> listPageing(OrgEmailTplVO vo, 
			int pageIndex, int listScale, int pageScale) throws Exception {
		ProcessResultListVO<OrgEmailTplVO> resultList = new ProcessResultListVO<OrgEmailTplVO>(); 
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(pageIndex);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = orgEmailTplMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<OrgEmailTplVO> codeList =  orgEmailTplMapper.listPageing(vo);
			resultList.setResult(1);
			resultList.setReturnList(codeList);
			resultList.setPageInfo(paginationInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
    /* (non-Javadoc)
	 * @see egovframework.edutrack.modules.org.emailtpl.service.impl.OrgEmailTplService#listPageing(egovframework.edutrack.modules.org.emailtpl.service.OrgEmailTplVO, int, int)
	 */
	@Override
	public ProcessResultListVO<OrgEmailTplVO> listPageing(OrgEmailTplVO vo, 
			int pageIndex, int listScale) throws Exception {
		return this.listPageing(vo, pageIndex, listScale, Constants.LIST_PAGE_SCALE);
	}
	
    /* (non-Javadoc)
	 * @see egovframework.edutrack.modules.org.emailtpl.service.impl.OrgEmailTplService#listPageing(egovframework.edutrack.modules.org.emailtpl.service.OrgEmailTplVO, int)
	 */
	@Override
	public ProcessResultListVO<OrgEmailTplVO> listPageing(OrgEmailTplVO vo, 
			int pageIndex) throws Exception {
		return this.listPageing(vo, pageIndex, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}	
	
	/* (non-Javadoc)
	 * @see egovframework.edutrack.modules.org.emailtpl.service.impl.OrgEmailTplService#view(egovframework.edutrack.modules.org.emailtpl.service.OrgEmailTplVO)
	 */
	@Override
	public OrgEmailTplVO view(OrgEmailTplVO vo) throws Exception {
		vo = orgEmailTplMapper.select(vo);
		vo = sysFileService.getFile(vo, new NestedFilehandler());
		this.pageUrlToView(vo);
		return vo;
	}
	
	/* (non-Javadoc)
	 * @see egovframework.edutrack.modules.org.emailtpl.service.impl.OrgEmailTplService#add(egovframework.edutrack.modules.org.emailtpl.service.OrgEmailTplVO)
	 */
	@Override
	public int add(OrgEmailTplVO vo) throws Exception {
		if("Y".equals(vo.getAutoMakeYn())) {
			vo.setTplCd(orgEmailTplMapper.selectKey());
		}		
		int result = orgEmailTplMapper.insert(vo); 
		sysFileService.bindFile(vo, new NestedFilehandler());
		return result;
	}	
	
	/* (non-Javadoc)
	 * @see egovframework.edutrack.modules.org.emailtpl.service.impl.OrgEmailTplService#edit(egovframework.edutrack.modules.org.emailtpl.service.OrgEmailTplVO)
	 */
	@Override
	public int edit(OrgEmailTplVO vo) throws Exception {
		sysFileService.bindFileUpdate(vo, new NestedFilehandler());
		return orgEmailTplMapper.update(vo);
	}
	
	/* (non-Javadoc)
	 * @see egovframework.edutrack.modules.org.emailtpl.service.impl.OrgEmailTplService#remove(egovframework.edutrack.modules.org.emailtpl.service.OrgEmailTplVO)
	 */
	@Override
	public int remove(OrgEmailTplVO vo) throws Exception {
		sysFileService.removeFile(vo, new NestedFilehandler());
		return orgEmailTplMapper.delete(vo);
	}
	
	/* (non-Javadoc)
	 * @see egovframework.edutrack.modules.org.emailtpl.service.impl.OrgEmailTplService#removeAll(egovframework.edutrack.modules.org.emailtpl.service.OrgEmailTplVO)
	 */
	@Override
	public int removeAll(OrgEmailTplVO vo) throws Exception {
		List<OrgEmailTplVO> tplList = orgEmailTplMapper.list(vo);
		for(OrgEmailTplVO oetvo : tplList) {
			sysFileService.removeFile(oetvo, new NestedFilehandler());
		}
		return orgEmailTplMapper.deleteAll(vo);
	}
	
	private void pageUrlToPersist(OrgEmailTplVO vo) {
		vo.setTplCts(StringUtil.replaceUrlToPersist(vo.getTplCts()));
	}

	private void pageUrlToView(OrgEmailTplVO vo) {
		vo.setTplCts(StringUtil.replaceUrlToBrowser(vo.getTplCts()));
	}

	/* (non-Javadoc)
	 * @see egovframework.edutrack.modules.org.emailtpl.service.impl.OrgEmailTplService#decorator(java.lang.String, java.lang.String, java.util.Map, egovframework.edutrack.modules.log.message.service.LogMsgLogVO)
	 */
	@Override
	public void decorator(String orgCd, String tplCd, Map<String, Object> argu, LogMsgLogVO message) {
		OrgEmailTplVO oetvo = new OrgEmailTplVO();
		oetvo.setOrgCd(orgCd);
		oetvo.setTplCd(tplCd);
		try {
			oetvo = orgEmailTplMapper.select(oetvo);
			message.setTitle(this.replaceContens(oetvo.getTplDesc(), argu)); //-- 타이틀을 치환한다.
			message.setCts(this.replaceContens(oetvo.getTplCts(), argu)); //-- 내용을 치환한다.
		} catch (Exception e) {
			//-- 템플릿이 검색이 안된 경우는 제목과 내용만 변환하도록 한다.
			//-- 슈퍼 관리자가 대량 메일 보낼때 사용.
			message.setTitle((String)argu.get("Title"));
			message.setCts((String)argu.get("Contents"));
		}
	}

	private String replaceContens(String source, Map<String, Object> argu) {
	    for(Map.Entry<String, Object> elem : argu.entrySet()) {
	    	source = StringUtil.ReplaceAll(source, "[$"+elem.getKey()+"]", (String)elem.getValue());
        }
		return source;
	}	

}
