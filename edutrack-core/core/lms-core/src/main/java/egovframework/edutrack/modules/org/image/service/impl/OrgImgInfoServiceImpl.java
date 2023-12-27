package egovframework.edutrack.modules.org.image.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.org.image.service.OrgImgInfoService;
import egovframework.edutrack.modules.org.image.service.OrgImgInfoVO;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *  <b>기관 - 기관 이미지 관리</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("orgImgInfoService")
public class OrgImgInfoServiceImpl
	extends EgovAbstractServiceImpl implements OrgImgInfoService{

	private final class NestedFileHandler
		implements FileHandler<OrgImgInfoVO> {
	
		@Override
		public String getPK(OrgImgInfoVO vo) {
			return vo.getImgSn().toString();
		}
	
		@Override
		public String getRepoCd() {
			return "ORGIMG";
		}
	
		@Override
		public List<SysFileVO> getFiles(OrgImgInfoVO vo) {
			List<SysFileVO> fileList = new ArrayList<SysFileVO>();
	
			// 단일 항목도 저장.
			if(ValidationUtils.isNotZeroNull(vo.getBkgrFileVO().getFileSn()))
				fileList.add(vo.getBkgrFileVO());
			if(ValidationUtils.isNotZeroNull(vo.getDescFileVO().getFileSn()))
				fileList.add(vo.getDescFileVO());
			if(ValidationUtils.isNotZeroNull(vo.getConnFileVO().getFileSn()))
				fileList.add(vo.getConnFileVO());
			return fileList;
		}
	
		@Override
		public OrgImgInfoVO setFiles(OrgImgInfoVO vo, FileListVO fileListVO) {
			vo.setBkgrFileVO(fileListVO.getFile("bkgr"));
			vo.setDescFileVO(fileListVO.getFile("desc"));
			vo.setConnFileVO(fileListVO.getFile("link"));
			return vo;
		}
	}
	
	/** dao */
/*    @Resource(name="orgImgInfoDAO")
    private OrgImgInfoMapper 		orgImgInfoMapper;*/
	
	/** Mapper */
	@Resource(name="orgImgInfoMapper")
	private OrgImgInfoMapper 		orgImgInfoMapper;
    
    @Resource(name="sysFileService")
    private SysFileService 		sysFileService;    
    
    /**
	 *  기관 이미지 전체 목록을 조회한다.
	 * @param OrgImgInfoVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<OrgImgInfoVO> list(OrgImgInfoVO vo) throws Exception {
		ProcessResultListVO<OrgImgInfoVO> resultList = new ProcessResultListVO<OrgImgInfoVO>(); 
		try {
			List<OrgImgInfoVO> imageList =  orgImgInfoMapper.list(vo);
			for(OrgImgInfoVO oiivo : imageList) {
				oiivo = sysFileService.getFile(oiivo, new NestedFileHandler());
			}
			resultList.setResult(1);
			resultList.setReturnList(imageList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
    /**
	 * 기관 이미지 페이징 목록을 조회한다.
	 * @param OrgImgInfoVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<OrgImgInfoVO> listPageing(OrgImgInfoVO vo, 
			int pageIndex, int listScale, int pageScale) throws Exception {
		ProcessResultListVO<OrgImgInfoVO> resultList = new ProcessResultListVO<OrgImgInfoVO>(); 
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(pageIndex);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = orgImgInfoMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<OrgImgInfoVO> imageList =  orgImgInfoMapper.listPageing(vo);
			for(OrgImgInfoVO oiivo : imageList) {
				oiivo = sysFileService.getFile(oiivo, new NestedFileHandler());
			}
			resultList.setResult(1);
			resultList.setReturnList(imageList);
			resultList.setPageInfo(paginationInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
    /**
	 * 기관 이미지 페이징 목록을 조회한다.
	 * @param OrgImgInfoVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<OrgImgInfoVO> listPageing(OrgImgInfoVO vo, 
			int pageIndex, int listScale) throws Exception {
		return this.listPageing(vo, pageIndex, listScale, Constants.LIST_PAGE_SCALE);
	}
	
    /**
	 * 기관 이미지 페이징 목록을 조회한다.
	 * @param OrgImgInfoVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<OrgImgInfoVO> listPageing(OrgImgInfoVO vo, 
			int pageIndex) throws Exception {
		return this.listPageing(vo, pageIndex, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}	
	
	/**
	 * 기관 이미지 상세 정보를 조회한다.
	 * @param OrgImgInfoVO
	 * @return OrgImgInfoVO
	 * @throws Exception
	 */
	@Override
	public OrgImgInfoVO view(OrgImgInfoVO vo) throws Exception {
		vo = orgImgInfoMapper.select(vo);
		vo = sysFileService.getFile(vo, new NestedFileHandler());
		return vo;
	}
	
	/**
	 * 기관 이미지 정보를 등록한다.
	 * @param OrgImgInfoVO
	 * @return String
	 * @throws Exception
	 */
	@Override
	public int add(OrgImgInfoVO vo) throws Exception {
		int imgSn = orgImgInfoMapper.selectKey();
		vo.setImgSn(imgSn);
		int result = orgImgInfoMapper.insert(vo);
		// 주키가 생성된 이후 첨부파일번호들을 바인딩해서 저장
		sysFileService.bindFile(vo, new NestedFileHandler());
		return result;
	}	
	
	/**
	 * 기관 이미지 정보를 수정한다.
	 * @param OrgImgInfoVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int edit(OrgImgInfoVO vo) throws Exception {
		int result = orgImgInfoMapper.update(vo); 
		sysFileService.bindFile(vo, new NestedFileHandler());
		return result;
	}
	
	/**
	 * 기관 이미지 순서를 변경한다.
	 * @param OrgImgInfoVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int sort(OrgImgInfoVO vo) throws Exception {
		String[] itemList = StringUtil.split(vo.getImgSns(),"|");
		// 목록을 한꺼번에 조회
		List<OrgImgInfoVO> itemArray = orgImgInfoMapper.list(vo);
		// 이중 포문으로 bannerArray에 변경된 order를 다시 저장
		// 만약 파라매터로 코드키가 누락되는 경우가 있다면... 로직 수정 필요.
		for (OrgImgInfoVO svo : itemArray) {
			for (int order = 0; order < itemList.length; order++) {
				if(svo.getImgSn().toString().equals(itemList[order]) ) {
					svo.setImgOdr(order+1);	// 1부터 차례로 순서값을 지정
					orgImgInfoMapper.update(svo);
				}
			}
		}
		return 1;
	}	
	
	/**
	 * 기관 이미지 정보를 삭제 한다.
	 * @param OrgImgInfoVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int remove(OrgImgInfoVO vo) throws Exception {
		sysFileService.removeFile(vo, new NestedFileHandler()); // 파일정보 삭제..
		return orgImgInfoMapper.delete(vo);
	}	
}
