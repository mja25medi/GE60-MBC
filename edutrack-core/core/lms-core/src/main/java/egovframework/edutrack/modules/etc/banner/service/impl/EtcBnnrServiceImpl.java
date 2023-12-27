package egovframework.edutrack.modules.etc.banner.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.etc.banner.service.EtcBnnrService;
import egovframework.edutrack.modules.etc.banner.service.EtcBnnrVO;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *  <b>기타 - 기타 베너 관리</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("etcBnnrService")
public class EtcBnnrServiceImpl 
	extends EgovAbstractServiceImpl implements EtcBnnrService {

	private final class NestedFileHandler
		implements FileHandler<EtcBnnrVO> {
	
		@Override
		public String getPK(EtcBnnrVO vo) {
			return vo.getBnnrSn().toString();
		}
		
		@Override
		public String getRepoCd() {
			return "BANNER";
		}
		
		@Override
		public List<SysFileVO> getFiles(EtcBnnrVO vo) {
			List<SysFileVO> fileList = new ArrayList<SysFileVO>();
		
			// 단일 항목도 저장.
			if(ValidationUtils.isNotZeroNull(vo.getbnnrFileVO().getFileSn()))
				fileList.add(vo.getbnnrFileVO());
		
			return fileList;
		}
		
		@Override
		public EtcBnnrVO setFiles(EtcBnnrVO vo, FileListVO fileListVO) {
			vo.setbnnrFileVO(fileListVO.getFile("image"));
			return vo;
		}
	}	
	
    @Resource(name="etcBnnrMapper")
    private EtcBnnrMapper 			etcBnnrMapper;
    
	@Resource(name="sysFileService")
	private SysFileService 		sysFileService;
    
	/**
	 * 베너 전체 목록을 조회한다.
	 * @param EtcBnnrVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<EtcBnnrVO> list(EtcBnnrVO vo) throws Exception {
		ProcessResultListVO<EtcBnnrVO> resultList = new ProcessResultListVO<EtcBnnrVO>(); 
		try {
			List<EtcBnnrVO> bnnrList =  etcBnnrMapper.list(vo);
			resultList.setResult(1);
			resultList.setReturnList(bnnrList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
	/**
	 * 베너 페이징 목록을 조회한다.
	 * @param EtcBnnrVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<EtcBnnrVO> listPageing(EtcBnnrVO vo, 
			int pageIndex, int listScale, int pageScale) throws Exception {
		ProcessResultListVO<EtcBnnrVO> resultList = new ProcessResultListVO<EtcBnnrVO>(); 
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(pageIndex);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = etcBnnrMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<EtcBnnrVO> bnnrList =  etcBnnrMapper.listPageing(vo);
			resultList.setResult(1);
			resultList.setReturnList(bnnrList);
			resultList.setPageInfo(paginationInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
	/**
	 * 베너 페이징 목록을 조회한다.
	 * @param EtcBnnrVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<EtcBnnrVO> listPageing(EtcBnnrVO vo, 
			int pageIndex, int listScale) throws Exception {
		return this.listPageing(vo, pageIndex, listScale, Constants.LIST_PAGE_SCALE);
	}
	
	/**
	 * 베너 페이징 목록을 조회한다.
	 * @param EtcBnnrVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<EtcBnnrVO> listPageing(EtcBnnrVO vo, 
			int pageIndex) throws Exception {
		return this.listPageing(vo, pageIndex, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}	
	
	/**
	 * 베너 상세 정보를 조회한다.
	 * @param EtcBnnrVO
	 * @return EtcBnnrVO
	 * @throws Exception
	 */
	@Override
	public EtcBnnrVO view(EtcBnnrVO vo) throws Exception {
		vo = etcBnnrMapper.select(vo);
		vo = sysFileService.getFile(vo, new NestedFileHandler());
		return vo;
	}
	
	/**
	 * 베너 정보를 등록한다.
	 * @param EtcBnnrVO
	 * @return String
	 * @throws Exception
	 */
	@Override
	public ProcessResultVO add(EtcBnnrVO vo) throws Exception {
		// 글저장 후 주키 값 받아오기.
		vo.setBnnrImgSn(vo.getBnnrFileSn());
		if(ValidationUtils.isEmpty(vo.getBnnrSn())) {
			vo.setBnnrSn(etcBnnrMapper.selectKey());
		}
		int result = etcBnnrMapper.insert(vo);
		sysFileService.bindFile(vo, new NestedFileHandler());
		
		return new ProcessResultVO<EtcBnnrVO>().setResultSuccess();
	}	
	
	/**
	 * 베너 정보를 수정한다.
	 * @param EtcBnnrVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public ProcessResultVO edit(EtcBnnrVO vo) throws Exception {
		// 글저장 후 주키 값 받아오기.
		vo.setBnnrImgSn(vo.getBnnrFileSn());
		etcBnnrMapper.update(vo);
		sysFileService.bindFile(vo, new NestedFileHandler());
		return new ProcessResultVO<EtcBnnrVO>().setResultSuccess();

	}
	
	/**
	 * 베너 정렬 순서를 변경한다.
	 * @param EtcBnnrVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int sort(EtcBnnrVO vo) throws Exception {
		String[] bannerList = StringUtil.split(vo.getBnnrSns(),"|");
		// 목록을 한꺼번에 조회
		List<EtcBnnrVO> bannerArray = etcBnnrMapper.list(vo);
		// 이중 포문으로 bannerArray에 변경된 order를 다시 저장
		// 만약 파라매터로 코드키가 누락되는 경우가 있다면... 로직 수정 필요.
		for (EtcBnnrVO ebvo : bannerArray) {
			for (int order = 0; order < bannerList.length; order++) {
				if(ebvo.getBnnrSn().toString().equals(bannerList[order]) ) {
					ebvo.setBnnrOdr(order+1);	// 1부터 차례로 순서값을 지정
					etcBnnrMapper.update(ebvo);
				}
			}
		}
		return 1;
	}	
	
	/**
	 * 베너 정보를 삭제 한다.
	 * @param EtcBnnrVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int remove(EtcBnnrVO vo) throws Exception {
		sysFileService.removeFile(vo, new NestedFileHandler()); // 파일정보 삭제..		
		return etcBnnrMapper.delete(vo);
	}
}
