package egovframework.edutrack.modules.board.popup.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.board.popup.service.BrdPopupNoticeService;
import egovframework.edutrack.modules.board.popup.service.BrdPopupNoticeVO;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 */
@Service("brdPopupNoticeService")
public class BrdPopupNoticeServiceImpl 
	extends EgovAbstractServiceImpl implements BrdPopupNoticeService {

	/** Mapper */
    @Resource(name="brdPopupNoticeMapper")
    private BrdPopupNoticeMapper brdPopupNoticeMapper;
    
	@Resource(name="sysFileService")
	private SysFileService 		sysFileService;
	
	private final class NestedFileHandler
	implements FileHandler<BrdPopupNoticeVO> {

		@Override
		public String getRepoCd() {
			return "POPUP";
		}
		
		@Override
		public String getPK(BrdPopupNoticeVO vo) {
			return vo.getPopupSn().toString();
		}
		
		@Override
		public List<SysFileVO> getFiles(BrdPopupNoticeVO vo) {
			List<SysFileVO> fileList = vo.getAttachImages();
//			fileList.addAll(vo.getAttachFiles());
//			fileList.addAll(vo.getAttachPhotos());
			return fileList;
		}
	
		@Override
		public BrdPopupNoticeVO setFiles(BrdPopupNoticeVO vo, FileListVO fileListVO) {
			vo.setAttachImages(fileListVO.getFiles("image"));
//			vo.setAttachFiles(fileListVO.getFiles("file"));
//			vo.setAttachPhotos(fileListVO.getFiles("photo"));
			return vo;
		}
	}
    
	/**
	 * 팝업 공지사항 페이징 목록을 조회한다.
	 */
	@Override
	public ProcessResultListVO<BrdPopupNoticeVO> listPageing(
			BrdPopupNoticeVO vo, int pageIndex, int listScale, int pageScale) throws Exception {
		ProcessResultListVO<BrdPopupNoticeVO> resultList = new ProcessResultListVO<BrdPopupNoticeVO>(); 
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(pageIndex);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = brdPopupNoticeMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<BrdPopupNoticeVO> deptInfoList =  brdPopupNoticeMapper.listPageing(vo);
			resultList.setResult(1);
			resultList.setReturnList(deptInfoList);
			resultList.setPageInfo(paginationInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	/**
	 * 팝업 공지사항 페이징 목록을 조회한다.
	 */
	@Override
	public ProcessResultListVO<BrdPopupNoticeVO> listPageing(BrdPopupNoticeVO vo, 
			int pageIndex, int listScale) throws Exception {
		return this.listPageing(vo, pageIndex, listScale, Constants.LIST_PAGE_SCALE);
	}
	
	/**
	 * 팝업 공지사항 페이징 목록을 조회한다.
	 */
	@Override
	public ProcessResultListVO<BrdPopupNoticeVO> listPopup(BrdPopupNoticeVO vo) throws Exception {
		ProcessResultListVO<BrdPopupNoticeVO> resultList = new ProcessResultListVO<BrdPopupNoticeVO>(); 
		
		try {
			List<BrdPopupNoticeVO> deptInfoList =  brdPopupNoticeMapper.listPopup(vo);
			resultList.setReturnList(deptInfoList);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		
		return resultList;
	}
	
	/**
	 * 팝업 공지사항 페이징 목록을 조회한다.
	 */
	@Override
	public ProcessResultListVO<BrdPopupNoticeVO> listPageing(BrdPopupNoticeVO vo, 
			int pageIndex) throws Exception {
		return this.listPageing(vo, pageIndex, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}
	
	
	/**
	 * 팝업 공지사항 상세 정보를 조회한다.
	 */
	@Override
	public BrdPopupNoticeVO view(BrdPopupNoticeVO vo) throws Exception {
		BrdPopupNoticeVO retVo = brdPopupNoticeMapper.select(vo);
		retVo = sysFileService.getFile(retVo, new NestedFileHandler());
		return retVo;
	}
	
	/**
	 * 팝업 공지사항을 등록한다.
	 */
	@Override
	public String addPopup(BrdPopupNoticeVO vo) throws Exception {
		//-- 키 정보가 없을 경우 생성
		if(ValidationUtils.isEmpty(vo.getPopupSn())) {
			vo.setPopupSn(brdPopupNoticeMapper.selectKey());
		}
		
		if(vo.getStartHour().length() ==1){
			vo.setStartHour("0"+vo.getStartHour());
		}
		if(vo.getStartMin().length() ==1){
			vo.setStartMin("0"+vo.getStartMin());
		}
		if(vo.getEndHour().length() ==1){
			vo.setEndHour("0"+vo.getEndHour());
		}
		if(vo.getEndMin().length() ==1){
			vo.setEndMin("0"+vo.getEndMin());
		}
		
		String startDttm = StringUtil.ReplaceAll(StringUtil.ReplaceAll(vo.getStartDttm(), ".", ""),"/","");
		String endDttm = StringUtil.ReplaceAll(StringUtil.ReplaceAll(vo.getEndDttm(), ".", ""),"/","");

		if(!"".equals(startDttm)) startDttm = startDttm+vo.getStartHour()+vo.getStartMin()+"01";;
		if(!"".equals(endDttm)) endDttm = endDttm+vo.getEndHour()+vo.getEndMin()+"59";

		vo.setStartDttm(startDttm);
		vo.setEndDttm(endDttm);
		
		int insert = brdPopupNoticeMapper.insert(vo);
		String result = Integer.toString(insert); 
		
		
		sysFileService.bindFile(vo, new NestedFileHandler());
		
		return result;
	}	
	
	/**
	 * 팝업 공지사항을 수정한다.
	 */
	@Override
	public int editPopup(BrdPopupNoticeVO vo) throws Exception {
		
		if(vo.getStartHour().length() ==1){
			vo.setStartHour("0"+vo.getStartHour());
		}
		if(vo.getStartMin().length() ==1){
			vo.setStartMin("0"+vo.getStartMin());
		}
		if(vo.getEndHour().length() ==1){
			vo.setEndHour("0"+vo.getEndHour());
		}
		if(vo.getEndMin().length() ==1){
			vo.setEndMin("0"+vo.getEndMin());
		}

		String startDttm = StringUtil.ReplaceAll(StringUtil.ReplaceAll(vo.getStartDttm(), ".", ""),"/","");
		String endDttm = StringUtil.ReplaceAll(StringUtil.ReplaceAll(vo.getEndDttm(), ".", ""),"/","");

		if(!"".equals(startDttm)) startDttm = startDttm+vo.getStartHour()+vo.getStartMin()+"01";;
		if(!"".equals(endDttm)) endDttm = endDttm+vo.getEndHour()+vo.getEndMin()+"59";

		vo.setStartDttm(startDttm);
		vo.setEndDttm(endDttm);
		sysFileService.bindFileUpdate(vo, new NestedFileHandler());
		return brdPopupNoticeMapper.update(vo);
	}
	
	/**
	 * 기관 정보 정보를 삭제 한다.
	 * @param UsrDeptInfoVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int remove(BrdPopupNoticeVO vo) throws Exception {
		return brdPopupNoticeMapper.delete(vo);
	}

}
