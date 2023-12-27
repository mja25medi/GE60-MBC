package egovframework.edutrack.modules.org.info.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.etc.relatedsite.service.EtcRelatedSiteCtgrVO;
import egovframework.edutrack.modules.etc.relatedsite.service.impl.EtcRelatedSiteCtgrMapper;
import egovframework.edutrack.modules.org.creaplc.service.OrgCreAplcInfoVO;
import egovframework.edutrack.modules.org.creaplc.service.impl.OrgCreAplcInfoMapper;
import egovframework.edutrack.modules.org.domain.service.OrgDomainVO;
import egovframework.edutrack.modules.org.domain.service.impl.OrgDomainMapper;
import egovframework.edutrack.modules.org.image.service.OrgImgInfoVO;
import egovframework.edutrack.modules.org.image.service.impl.OrgImgInfoMapper;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoService;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoVO;
import egovframework.edutrack.modules.org.menu.service.OrgAuthGrpMenuVO;
import egovframework.edutrack.modules.org.menu.service.OrgMenuLangVO;
import egovframework.edutrack.modules.org.menu.service.OrgMenuVO;
import egovframework.edutrack.modules.org.menu.service.impl.OrgAuthGrpMenuMapper;
import egovframework.edutrack.modules.org.menu.service.impl.OrgMenuLangMapper;
import egovframework.edutrack.modules.org.menu.service.impl.OrgMenuMapper;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.edutrack.modules.system.menu.service.SysMenuService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *  <b>기관 - 기관 정보 관리</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("orgOrgInfoService")
public class OrgOrgInfoServiceImpl 
	extends EgovAbstractServiceImpl implements OrgOrgInfoService{

	private final class NestedFileHandler
		implements FileHandler<OrgOrgInfoVO> {
	
		@Override
		public String getPK(OrgOrgInfoVO vo) {
			return vo.getOrgCd().toString();
		}
	
		@Override
		public String getRepoCd() {
			return "ORGINFO";
		}
	
		@Override
		public List<SysFileVO> getFiles(OrgOrgInfoVO vo) {
			List<SysFileVO> fileList = new ArrayList<SysFileVO>();
	
			// 단일 항목도 저장.
			if(ValidationUtils.isNotZeroNull(vo.getTopLogoFileVO().getFileSn()))
				fileList.add(vo.getTopLogoFileVO());
			if(ValidationUtils.isNotZeroNull(vo.getSubLogo1FileVO().getFileSn()))
				fileList.add(vo.getSubLogo1FileVO());
			if(ValidationUtils.isNotZeroNull(vo.getSubLogo2FileVO().getFileSn()))
				fileList.add(vo.getSubLogo2FileVO());
			if(ValidationUtils.isNotZeroNull(vo.getAdmLogoFileVO().getFileSn()))
				fileList.add(vo.getAdmLogoFileVO());
			if(ValidationUtils.isNotZeroNull(vo.getContractFileVO().getFileSn()))
				fileList.add(vo.getContractFileVO());
			return fileList;
		}
	
		@Override
		public OrgOrgInfoVO setFiles(OrgOrgInfoVO vo, FileListVO fileListVO) {
			vo.setTopLogoFileVO(fileListVO.getFile("toplogo"));
			vo.setSubLogo1FileVO(fileListVO.getFile("sublogo1"));
			vo.setSubLogo2FileVO(fileListVO.getFile("sublogo2"));
			vo.setAdmLogoFileVO(fileListVO.getFile("admlogo"));
			vo.setContractFileVO(fileListVO.getFile("contract"));
			return vo;
		}
	}
	
	private final class ImageNestedFileHandler
		implements FileHandler<OrgImgInfoVO> {
	
		@Override
		public String getPK(OrgImgInfoVO dto) {
			return dto.getImgSn().toString();
		}
	
		@Override
		public String getRepoCd() {
			return "ORGIMG";
		}
	
		@Override
		public List<SysFileVO> getFiles(OrgImgInfoVO dto) {
			List<SysFileVO> fileList = new ArrayList<SysFileVO>();
	
			// 단일 항목도 저장.
			if(ValidationUtils.isNotZeroNull(dto.getBkgrFileVO().getFileSn()))
				fileList.add(dto.getBkgrFileVO());
			if(ValidationUtils.isNotZeroNull(dto.getDescFileVO().getFileSn()))
				fileList.add(dto.getDescFileVO());
			if(ValidationUtils.isNotZeroNull(dto.getConnFileVO().getFileSn()))
				fileList.add(dto.getConnFileVO());
			return fileList;
		}
	
		@Override
		public OrgImgInfoVO setFiles(OrgImgInfoVO dto, FileListVO fileListVO) {
			dto.setBkgrFileVO(fileListVO.getFile("bkgr"));
			dto.setDescFileVO(fileListVO.getFile("desc"));
			dto.setConnFileVO(fileListVO.getFile("link"));
			return dto;
		}
	}	
	
	/** Mapper */
   @Resource(name="sysFileService")
    private SysFileService 		sysFileService;
    
    @Resource(name="etcRelatedSiteCtgrMapper")
    private EtcRelatedSiteCtgrMapper	etcRelatedSiteCtgrMapper;
    
	@Resource(name="orgOrgInfoMapper")
	private OrgOrgInfoMapper 		orgOrgInfoMapper;
	
	@Resource(name="orgDomainMapper")
	private OrgDomainMapper 		orgDomainMapper;

	@Resource(name="orgImgInfoMapper")
	private OrgImgInfoMapper 		orgImgInfoMapper;
    
	@Resource(name="orgCreAplcInfoMapper")
	private OrgCreAplcInfoMapper 		orgCreAplcInfoMapper;
	
	@Resource(name="orgMenuMapper")
	private OrgMenuMapper 		orgMenuMapper;
	
	@Resource(name="orgMenuLangMapper")
	private OrgMenuLangMapper 		orgMenuLangMapper;
	
	@Resource(name="orgAuthGrpMenuMapper")
	private OrgAuthGrpMenuMapper 		orgAuthGrpMenuMapper;
	
	@Resource(name="sysMenuService")
	private SysMenuService		SysMenuService;
	
    /**
	 *  기관 정보 전체 목록을 조회한다.
	 * @param OrgOrgInfoVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<OrgOrgInfoVO> list(OrgOrgInfoVO vo) throws Exception {
		ProcessResultListVO<OrgOrgInfoVO> resultList = new ProcessResultListVO<OrgOrgInfoVO>(); 
		try {
			List<OrgOrgInfoVO> orgList =  orgOrgInfoMapper.list(vo);
			resultList.setResult(1);
			resultList.setReturnList(orgList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
    /**
	 * 기관 정보 페이징 목록을 조회한다.
	 * @param OrgOrgInfoVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<OrgOrgInfoVO> listPageing(OrgOrgInfoVO vo, 
			int pageIndex, int listScale, int pageScale) throws Exception {
		ProcessResultListVO<OrgOrgInfoVO> resultList = new ProcessResultListVO<OrgOrgInfoVO>(); 
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(pageIndex);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = orgOrgInfoMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<OrgOrgInfoVO> orgList =  orgOrgInfoMapper.listPageing(vo);
			resultList.setResult(1);
			resultList.setReturnList(orgList);
			resultList.setPageInfo(paginationInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
    /**
	 * 기관 정보 페이징 목록을 조회한다.
	 * @param OrgOrgInfoVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<OrgOrgInfoVO> listPageing(OrgOrgInfoVO vo, 
			int pageIndex, int listScale) throws Exception {
		return this.listPageing(vo, pageIndex, listScale, Constants.LIST_PAGE_SCALE);
	}
	
    /**
	 * 기관 정보 페이징 목록을 조회한다.
	 * @param OrgOrgInfoVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<OrgOrgInfoVO> listPageing(OrgOrgInfoVO vo, 
			int pageIndex) throws Exception {
		return this.listPageing(vo, pageIndex, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}	
	
	/**
	 * 기관 정보 상세 정보를 조회한다.
	 * @param OrgOrgInfoVO
	 * @return OrgOrgInfoVO
	 * @throws Exception
	 */
	@Override
	public OrgOrgInfoVO view(OrgOrgInfoVO vo) throws Exception {
		vo = orgOrgInfoMapper.select(vo);
		vo = sysFileService.getFile(vo, new NestedFileHandler());
		return vo;
	}
	
	/**
	 * 도메인으로 기관의 정보를 검색한다.
	 * @param OrgOrgInfoVO
	 * @return OrgOrgInfoVO
	 * @throws Exception
	 */
	@Override
	public OrgOrgInfoVO viewByDomain(OrgOrgInfoVO vo) throws Exception {
		vo = orgOrgInfoMapper.selectByDomain(vo); 
		vo = sysFileService.getFile(vo, new NestedFileHandler());
		return vo;
	}	
	
	/**
	 * 기관 정보 정보를 등록한다.
	 * @param OrgOrgInfoVO
	 * @return String
	 * @throws Exception
	 */
	@Override
	public int add(OrgOrgInfoVO vo) throws Exception {
		// 키를 생성한다.
		String orgCd = orgOrgInfoMapper.selectKey();
		// 기관 정보 등록
		vo.setOrgCd(orgCd);
		int result = orgOrgInfoMapper.insert(vo);
		sysFileService.bindFile(vo, new NestedFileHandler());
		
		//-- 기본 도메인 등록
		OrgDomainVO odvo = new OrgDomainVO();
		odvo.setOrgCd(vo.getOrgCd());
		odvo.setOrgDomain(vo.getDomainNm());
//		odvo.setOrgDomain(vo.getDomainNm()+"."+Constants.PRODUCT_DOMAIN);
		odvo.setDfltYn("Y");
		odvo.setRprstYn("Y");
		odvo.setDomainTypeCd("HOME");
		odvo.setSiteServiceTypeCd("PRODUCTION");
		odvo.setRegNo(vo.getRegNo());
		odvo.setModNo(vo.getModNo());

		orgDomainMapper.insert(odvo);

		//-- 최초 생성시 기본 게시판을 생성한다.
		orgOrgInfoMapper.insertBbs(vo);

		//-- 최초 생성시 기본 페이지를 생성한다.
		orgOrgInfoMapper.insertPage(vo);

		//-- 최초 생성시 이메일 템플릿을 생성한다.
		orgOrgInfoMapper.insertEmailTpl(vo);

		//--최초 생성시 코드카테고리 등록
		orgOrgInfoMapper.insertCodeCtgr(vo);

		//--최초 생성시 코드 등록
		orgOrgInfoMapper.insertCode(vo);

		//--최초 생성시 사용자정보관리 마스터 테이블에서 복사
		orgOrgInfoMapper.insertUserInfoCfg(vo);
		
		//--최초 생성시 지식분류 마스터 테이블에서 복사
		//orgOrgInfoMapper.insertKnowCtgr(vo);
		
		//--최초 생성시 관련사이트 Default 카테고리 추가 (footer 윗부분 영역)
		EtcRelatedSiteCtgrVO etcVo = new EtcRelatedSiteCtgrVO();
		etcVo.setOrgCd(vo.getOrgCd());
		etcVo.setCtgrCd("RSC0000000");
		etcVo.setTitle("REALATED SITE");
		etcVo.setCtgrDesc("REALATED SITE");
		etcVo.setRegNo(vo.getRegNo());
		etcVo.setModNo(vo.getModNo());
		etcRelatedSiteCtgrMapper.insert(etcVo);
		
		//footer 우측영역 selectbox
		etcVo.setCtgrCd("RSC0000001");
		etcVo.setTitle("FAMILY SITE");
		etcVo.setCtgrDesc("FAMILY SITE");
		etcRelatedSiteCtgrMapper.insert(etcVo);
		
		//개설신청으로 들어온 경우 신청받은 정보에 orgCd를 넣어준다.
		if(vo.getCreAplcSn() != null && vo.getCreAplcSn() > 0){
			OrgCreAplcInfoVO ocaivo = new OrgCreAplcInfoVO();
			ocaivo.setCreAplcSn(vo.getCreAplcSn()); 
			ocaivo.setCreOrgCd(orgCd); 
			ocaivo.setModNo(vo.getModNo());
			orgCreAplcInfoMapper.updateOrgCd(ocaivo);
		}
		
		// ORG 메뉴 생성
		OrgMenuVO orgMenuVO = new OrgMenuVO();
		orgMenuVO.setOrgCd(orgCd);
		orgMenuVO.setMenuType("HOME");
		orgMenuVO.setRegNo(vo.getRegNo());
		orgMenuVO.setModNo(vo.getModNo());
		orgMenuMapper.insertInit(orgMenuVO);
		
		OrgMenuLangVO orgMenuLangVO = new OrgMenuLangVO();
		orgMenuLangVO.setOrgCd(orgCd);
		orgMenuLangVO.setMenuType("HOME");
		orgMenuLangMapper.insertInit(orgMenuLangVO);
		
		OrgAuthGrpMenuVO orgAuthMenuVO = new OrgAuthGrpMenuVO();	
		orgAuthMenuVO.setOrgCd(orgCd);        
		orgAuthMenuVO.setMenuType("HOME");    
		orgAuthMenuVO.setRegNo(vo.getRegNo());
		orgAuthMenuVO.setModNo(vo.getModNo());		
		orgAuthGrpMenuMapper.insertInit(orgAuthMenuVO);
		
		return result;
	}	
	
	/**
	 * 기관 정보 정보를 수정한다.
	 * @param OrgOrgInfoVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int edit(OrgOrgInfoVO vo) throws Exception {
		return orgOrgInfoMapper.update(vo);
	}
	
	/**
	 * 기관 기본 정보 정보를 수정한다.
	 * @param OrgOrgInfoVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int editInfo(OrgOrgInfoVO vo) throws Exception {
		int result = orgOrgInfoMapper.updateInfo(vo);
		sysFileService.bindFile(vo, new NestedFileHandler());
		return result;
	}
	
	/**
	 * 기관 템플릿 정보 정보를 수정한다.
	 * @param OrgOrgInfoVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int editTemplate(OrgOrgInfoVO vo) throws Exception {
		return orgOrgInfoMapper.updateTemplate(vo);
	}
	
	/**
	 * 기관 템플릿 정보 정보를 수정한다.
	 * @param OrgOrgInfoVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int editDesign(OrgOrgInfoVO vo) throws Exception {
		int result = orgOrgInfoMapper.updateDesign(vo);
		sysFileService.bindFile(vo, new NestedFileHandler());
		return result;
	}	
	
	/**
	 * 기관 메타 정보 정보를 수정한다.
	 * @param OrgOrgInfoVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int editMeta(OrgOrgInfoVO vo) throws Exception {
		int result = orgOrgInfoMapper.updateMeta(vo);
		return result;
	}	
	
	/**
	 * 기관 정보 정보를 삭제 한다.
	 * @param OrgOrgInfoVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int remove(OrgOrgInfoVO vo) throws Exception {
		//-- 사이트 이미지 삭제
		//-- 이미지 파일 삭제 처리를 위해 이미지 목록 호출 후 파일 삭제 처리함.
		OrgImgInfoVO oiivo = new OrgImgInfoVO();
		oiivo.setOrgCd(vo.getOrgCd());
		List<OrgImgInfoVO> orgImgInfoList = orgImgInfoMapper.list(oiivo);
		for(OrgImgInfoVO soiivo : orgImgInfoList) {
			sysFileService.removeFile(soiivo, new ImageNestedFileHandler()); // 파일정보 삭제..
		}
		orgImgInfoMapper.delete(oiivo);

		//-- 게시판 삭제
		orgOrgInfoMapper.deleteBbs(vo);

		//-- 페이지 삭제
		orgOrgInfoMapper.deletePage(vo);

		//-- 도메인 정보 삭제
		orgOrgInfoMapper.deleteDomain(vo);

		//-- 이메일 템플릿 삭제
		orgOrgInfoMapper.deleteEmailTpl(vo);

		//-- 코드 삭제
		orgOrgInfoMapper.deleteCode(vo);
		
		//-- 코드카테고리 삭제
		orgOrgInfoMapper.deleteCodeCtgr(vo);

		//-- 사용자정보관리 삭제
		orgOrgInfoMapper.deleteUserInfoCfg(vo);
		
		//-- 사용자의 지식분류(관심분류) 코드값과 기관의 지식분류를 전부를 삭제
		//orgOrgInfoMapper.deleteUserKnowCtgr(vo);
		//orgOrgInfoMapper.deleteKnowCtgr(vo);
		
		//--최초 생성시 관련사이트,카테고리 전체 삭제
		orgOrgInfoMapper.deleteRelatedSite(vo);
		orgOrgInfoMapper.deleteRelatedSiteCtgr(vo);
		
		//개설신청으로 생성된 경우 신청받은 정보에 orgCd를 삭제해준다.
		OrgCreAplcInfoVO ocaivo = new OrgCreAplcInfoVO();
		ocaivo.setCreOrgCd(vo.getOrgCd()); 
		ocaivo.setModNo(vo.getModNo()); 
		orgCreAplcInfoMapper.deleteOrgCd(ocaivo);

		//-- 사이트 정보 삭제
		sysFileService.removeFile(vo, new NestedFileHandler()); // 파일정보 삭제..
		int result = orgOrgInfoMapper.delete(vo);
		return result;
	}  
	
	
	@Override
	public String getHrdApiUseYn(String orgCd) {
		String result = "N";
		try {
			result = orgOrgInfoMapper.selectHrdApiUseYn(orgCd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
