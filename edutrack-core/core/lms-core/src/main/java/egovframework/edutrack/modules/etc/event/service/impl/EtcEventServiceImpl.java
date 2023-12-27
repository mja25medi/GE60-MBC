package egovframework.edutrack.modules.etc.event.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.etc.event.service.EtcEventService;
import egovframework.edutrack.modules.etc.event.service.EtcEventVO;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *  <b>기타 - 기타 이벤트 관리</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("etcEventService")
public class EtcEventServiceImpl 
	extends EgovAbstractServiceImpl implements EtcEventService {

	private final class NestedFileHandler
		implements FileHandler<EtcEventVO> {
	
		@Override
		public String getPK(EtcEventVO vo) {
			return vo.getEventSn().toString();
		}
		
		@Override
		public String getRepoCd() {
			return "EVENT";
		}
		
		@Override
		public List<SysFileVO> getFiles(EtcEventVO vo) {
			List<SysFileVO> fileList = new ArrayList<SysFileVO>();
		
			// 단일 항목도 저장.
			if(ValidationUtils.isNotZeroNull(vo.geteventFileVO().getFileSn()))
				fileList.add(vo.geteventFileVO());
		
			return fileList;
		}
		
		@Override
		public EtcEventVO setFiles(EtcEventVO vo, FileListVO fileListVO) {
			vo.seteventFileVO(fileListVO.getFile("image"));
			return vo;
		}
	}	
	
    @Resource(name="etcEventMapper")
    private EtcEventMapper 			etcEventMapper;
    
	@Resource(name="sysFileService")
	private SysFileService 		sysFileService;
    
	/**
	 * 이벤트 전체 목록을 조회한다.
	 * @param EtcEventVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<EtcEventVO> list(EtcEventVO vo) throws Exception {
		ProcessResultListVO<EtcEventVO> resultList = new ProcessResultListVO<EtcEventVO>(); 
		try {
			List<EtcEventVO> eventList =  etcEventMapper.list(vo);
			resultList.setResult(1);
			resultList.setReturnList(eventList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
	/**
	 * 이벤트 페이징 목록을 조회한다.
	 * @param EtcEventVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<EtcEventVO> listPageing(EtcEventVO vo, 
			int pageIndex, int listScale, int pageScale) throws Exception {
		ProcessResultListVO<EtcEventVO> resultList = new ProcessResultListVO<EtcEventVO>(); 
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(pageIndex);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = etcEventMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<EtcEventVO> eventList =  etcEventMapper.listPageing(vo);
			resultList.setResult(1);
			resultList.setReturnList(eventList);
			resultList.setPageInfo(paginationInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
	/**
	 * 이벤트 페이징 목록을 조회한다.
	 * @param EtcEventVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<EtcEventVO> listPageing(EtcEventVO vo, 
			int pageIndex, int listScale) throws Exception {
		return this.listPageing(vo, pageIndex, listScale, Constants.LIST_PAGE_SCALE);
	}
	
	/**
	 * 이벤트 페이징 목록을 조회한다.
	 * @param EtcEventVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<EtcEventVO> listPageing(EtcEventVO vo, 
			int pageIndex) throws Exception {
		return this.listPageing(vo, pageIndex, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}	
	
	/**
	 * 이벤트 상세 정보를 조회한다.
	 * @param EtcEventVO
	 * @return EtcEventVO
	 * @throws Exception
	 */
	@Override
	public EtcEventVO view(EtcEventVO vo) throws Exception {
		vo = etcEventMapper.select(vo);
		vo = sysFileService.getFile(vo, new NestedFileHandler());
		return vo;
	}
	
	/**
	 * 이벤트 정보를 등록한다.
	 * @param EtcEventVO
	 * @return String
	 * @throws Exception
	 */
	@Override
	public ProcessResultVO add(EtcEventVO vo) throws Exception {
		// 글저장 후 주키 값 받아오기.
		vo.setEventImgSn(vo.getEventFileSn());
		if(ValidationUtils.isEmpty(vo.getEventSn())) {
			vo.setEventSn(etcEventMapper.selectKey());
		}
		int result = etcEventMapper.insert(vo);
		sysFileService.bindFile(vo, new NestedFileHandler());
		
		return new ProcessResultVO<EtcEventVO>().setResultSuccess();
	}	
	
	/**
	 * 이벤트 정보를 수정한다.
	 * @param EtcEventVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public ProcessResultVO edit(EtcEventVO vo) throws Exception {
		// 글저장 후 주키 값 받아오기.
		vo.setEventImgSn(vo.getEventFileSn());
		etcEventMapper.update(vo);
		sysFileService.bindFile(vo, new NestedFileHandler());
		return new ProcessResultVO<EtcEventVO>().setResultSuccess();

	}
	
	/**
	 * 이벤트 정렬 순서를 변경한다.
	 * @param EtcEventVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int sort(EtcEventVO vo) throws Exception {
		String[] eventList = StringUtil.split(vo.getEventSns(),"|");
		// 목록을 한꺼번에 조회
		List<EtcEventVO> eventArray = etcEventMapper.list(vo);
		// 이중 포문으로 eventArray에 변경된 order를 다시 저장
		// 만약 파라매터로 코드키가 누락되는 경우가 있다면... 로직 수정 필요.
		for (EtcEventVO ebvo : eventArray) {
			for (int order = 0; order < eventList.length; order++) {
				if(ebvo.getEventSn().toString().equals(eventList[order]) ) {
					ebvo.setEventOdr(order+1);	// 1부터 차례로 순서값을 지정
					etcEventMapper.update(ebvo);
				}
			}
		}
		return 1;
	}	
	
	/**
	 * 이벤트 정보를 삭제 한다.
	 * @param EtcEventVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int remove(EtcEventVO vo) throws Exception {
		sysFileService.removeFile(vo, new NestedFileHandler()); // 파일정보 삭제..		
		return etcEventMapper.delete(vo);
	}
}
