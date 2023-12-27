package egovframework.edutrack.modules.system.tpl.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.edutrack.modules.system.tpl.service.SysTplService;
import egovframework.edutrack.modules.system.tpl.service.SysTplVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *  <b>시스템 - 시스템 템플릿 관리</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("sysTplService")
public class SysTplServiceImpl 
	extends EgovAbstractServiceImpl implements SysTplService {

	private final class NestedFileHandler
		implements FileHandler<SysTplVO> {
	
		@Override
		public String getPK(SysTplVO vo) {
			return vo.getTplCd().toString();
		}
	
		@Override
		public String getRepoCd() {
			return "SYSTPL";
		}
	
		@Override
		public List<SysFileVO> getFiles(SysTplVO vo) {
			List<SysFileVO> fileList = new ArrayList<SysFileVO>();
			if(ValidationUtils.isNotZeroNull(vo.getMainImgFileSn()))
				fileList.add(vo.getMainImgFile());
			if(ValidationUtils.isNotZeroNull(vo.getSubImgFileSn()))
				fileList.add(vo.getSubImgFile());
			return fileList;
		}
	
		@Override
		public SysTplVO setFiles(SysTplVO vo, FileListVO fileListVO) {
			vo.setMainImgFile(fileListVO.getFile("main"));
			vo.setSubImgFile(fileListVO.getFile("sub"));
			return vo;
		}
	}
	
	/** Mapper */
    @Resource(name="sysTplMapper")
    private SysTplMapper 		sysTplMapper;
    
	@Resource(name="sysFileService")
	private SysFileService 	sysFileService;
    
    /**
	 *  시스템 템플릿 전체 목록을 조회한다.
	 * @param SysTplVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<SysTplVO> list(SysTplVO vo) throws Exception {
		ProcessResultListVO<SysTplVO> resultList = new ProcessResultListVO<SysTplVO>(); 
		try {
			List<SysTplVO> tplList =  sysTplMapper.list(vo);
			resultList.setResult(1);
			resultList.setReturnList(tplList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
    /**
	 * 시스템 템플릿 페이징 목록을 조회한다.
	 * @param SysTplVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<SysTplVO> listPageing(SysTplVO vo, 
			int pageIndex, int listScale, int pageScale) throws Exception {
		ProcessResultListVO<SysTplVO> resultList = new ProcessResultListVO<SysTplVO>(); 
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(pageIndex);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = sysTplMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<SysTplVO> tplList =  sysTplMapper.listPageing(vo);
			resultList.setResult(1);
			resultList.setReturnList(tplList);
			resultList.setPageInfo(paginationInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
    /**
	 * 시스템 템플릿 페이징 목록을 조회한다.
	 * @param SysTplVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<SysTplVO> listPageing(SysTplVO vo, 
			int pageIndex, int listScale) throws Exception {
		return this.listPageing(vo, pageIndex, listScale, Constants.LIST_PAGE_SCALE);
	}
	
    /**
	 * 시스템 템플릿 페이징 목록을 조회한다.
	 * @param SysTplVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<SysTplVO> listPageing(SysTplVO vo, 
			int pageIndex) throws Exception {
		return this.listPageing(vo, pageIndex, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}	
	
	/**
	 * 시스템 템플릿 상세 정보를 조회한다.
	 * @param SysTplVO
	 * @return SysTplVO
	 * @throws Exception
	 */
	@Override
	public SysTplVO view(SysTplVO vo) throws Exception {
		SysTplVO stvo = sysTplMapper.select(vo);
 		if(ValidationUtils.isNotEmpty(stvo)) {
			vo = stvo;
		}
		vo = sysFileService.getFile(vo, new NestedFileHandler());
		return vo;
	}
	
	/**
	 * 시스템 템플릿 정보를 등록한다.
	 * @param SysTplVO
	 * @return String
	 * @throws Exception
	 */
	@Override
	public int add(SysTplVO vo) throws Exception {
		int result = sysTplMapper.insert(vo);
		sysFileService.bindFile(vo, new NestedFileHandler());
		return result;
	}	
	
	/**
	 * 시스템 템플릿 정보를 수정한다.
	 * @param SysTplVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int edit(SysTplVO vo) throws Exception {
		sysFileService.bindFile(vo, new NestedFileHandler());
		return sysTplMapper.update(vo);
	}
	
	/**
	 * 시스템 템플릿의 순서를 변경한다.
	 * @param SysTplVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int sort(SysTplVO vo) throws Exception {
		int result = 0;
		String[] itemList = StringUtil.split(vo.getTplCd(),"|");
		// 하위 코드 목록을 한꺼번에 조회
		List<SysTplVO> itemArray = sysTplMapper.list(vo);
		// 이중 포문으로 codeArray에 변경된 order를 다시 저장
		// 만약 파라매터로 코드키가 누락되는 경우가 있다면... 로직 수정 필요.
		for (SysTplVO svo : itemArray) {
			for (int order = 0; order < itemList.length; order++) {
				if(svo.getTplCd().equals(itemList[order])) {
					svo.setTplOdr(order+1);	// 1부터 차례로 순서값을 지정
				}
				result = sysTplMapper.update(svo);
			}
		}
		return result;
	}	
	
	/**
	 * 시스템 템플릿 정보를 삭제 한다.
	 * @param SysTplVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int remove(SysTplVO vo) throws Exception {
		sysFileService.removeFile(vo, new NestedFileHandler()); // 파일정보 삭제..		
		return sysTplMapper.delete(vo);
	}    
}
