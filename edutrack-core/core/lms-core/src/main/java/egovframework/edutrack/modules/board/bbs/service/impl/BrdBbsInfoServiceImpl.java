package egovframework.edutrack.modules.board.bbs.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.board.bbs.service.BrdBbsHeadVO;
import egovframework.edutrack.modules.board.bbs.service.BrdBbsInfoService;
import egovframework.edutrack.modules.board.bbs.service.BrdBbsInfoVO;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *  <b>게시판 - 게시판 정보 관리</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("brdBbsInfoService")
public class BrdBbsInfoServiceImpl 
	extends EgovAbstractServiceImpl implements BrdBbsInfoService {

	private final class NestedFileHandler
		implements FileHandler<BrdBbsInfoVO> {
	
		@Override
		public String getPK(BrdBbsInfoVO vo) {
			return vo.getBbsCd().toString();
		}
	
		@Override
		public String getRepoCd() {
			return "BBS_INFO";
		}
	
		@Override
		public List<SysFileVO> getFiles(BrdBbsInfoVO vo) {
			List<SysFileVO> fileList = new ArrayList<SysFileVO>();
			if(ValidationUtils.isNotZeroNull(vo.getMainImgFileSn()))
				fileList.add(vo.getMainImgFile());
			return fileList;
		}
	
		@Override
		public BrdBbsInfoVO setFiles(BrdBbsInfoVO vo, FileListVO fileListVO) {
			vo.setMainImgFile(fileListVO.getFile("thumb"));
			return vo;
		}
	}
	
	/** Mapper */
    @Resource(name="brdBbsInfoMapper")
    private BrdBbsInfoMapper 		brdBbsInfoMapper;

    @Resource(name="brdBbsHeadMapper")
    private BrdBbsHeadMapper 		brdBbsHeadMapper;
    
	@Resource(name="sysFileService")
	private SysFileService 		sysFileService;
    
    /**
	 *  게시판 정보 전체 목록을 조회한다.
	 * @param BrdBbsInfoVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<BrdBbsInfoVO> list(BrdBbsInfoVO vo) throws Exception {
		ProcessResultListVO<BrdBbsInfoVO> resultList = new ProcessResultListVO<BrdBbsInfoVO>(); 
		try {
			List<BrdBbsInfoVO> bbsInfoList =  brdBbsInfoMapper.list(vo);
			resultList.setResult(1);
			resultList.setReturnList(bbsInfoList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
    /**
	 * 게시판 정보 페이징 목록을 조회한다.
	 * @param BrdBbsInfoVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<BrdBbsInfoVO> listPageing(BrdBbsInfoVO vo, 
			int pageIndex, int listScale, int pageScale) throws Exception {
		ProcessResultListVO<BrdBbsInfoVO> resultList = new ProcessResultListVO<BrdBbsInfoVO>(); 
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(pageIndex);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = brdBbsInfoMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<BrdBbsInfoVO> bbsInfoList =  brdBbsInfoMapper.listPageing(vo);
			resultList.setResult(1);
			resultList.setReturnList(bbsInfoList);
			resultList.setPageInfo(paginationInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
    /**
	 * 게시판 정보 페이징 목록을 조회한다.
	 * @param BrdBbsInfoVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<BrdBbsInfoVO> listPageing(BrdBbsInfoVO vo, 
			int pageIndex, int listScale) throws Exception {
		return this.listPageing(vo, pageIndex, listScale, Constants.LIST_PAGE_SCALE);
	}
	
    /**
	 * 게시판 정보 페이징 목록을 조회한다.
	 * @param BrdBbsInfoVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<BrdBbsInfoVO> listPageing(BrdBbsInfoVO vo, 
			int pageIndex) throws Exception {
		return this.listPageing(vo, pageIndex, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}
	
	/**
	 * 게시판 정보 상세 정보를 조회한다.
	 * @param BrdBbsInfoVO
	 * @return BrdBbsInfoVO
	 * @throws Exception
	 */
	@Override
	public BrdBbsInfoVO view(BrdBbsInfoVO vo) throws Exception {
		vo = brdBbsInfoMapper.select(vo);
		vo = sysFileService.getFile(vo, new NestedFileHandler());
		return vo;
	}
	
	/**
	 * 게시판 정보 정보를 등록한다.
	 * @param BrdBbsInfoVO
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String add(BrdBbsInfoVO vo) throws Exception {
		if("Y".equals(vo.getAutoMakeYn())) {
			vo.setBbsCd(brdBbsInfoMapper.selectKey());
		}
		int insert= brdBbsInfoMapper.insert(vo);
		String result = Integer.toString(insert);
		sysFileService.bindFile(vo, new NestedFileHandler());
		return result;
	}	
	
	/**
	 * 게시판 정보 정보를 수정한다.
	 * @param BrdBbsInfoVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int edit(BrdBbsInfoVO vo) throws Exception {
		sysFileService.bindFileUpdate(vo, new NestedFileHandler());
		return brdBbsInfoMapper.update(vo);
	}
	
	/**
	 * 게시판 정보 정보를 삭제 한다.
	 * @param BrdBbsInfoVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int remove(BrdBbsInfoVO vo) throws Exception {
		//-- 게시판 머릿말 정보 삭제
		BrdBbsHeadVO bbhvo = new BrdBbsHeadVO();
		bbhvo.setOrgCd(vo.getOrgCd());
		bbhvo.setBbsCd(vo.getBbsCd());
		brdBbsHeadMapper.deleteAll(bbhvo);
		
		sysFileService.removeFile(vo, new NestedFileHandler());
		return brdBbsInfoMapper.delete(vo);
	}
	
    /**
	 * 메뉴 관리용 메뉴에 등록되지 않은 게시판 정보 페이징 목록을 조회한다.
	 * @param BrdBbsInfoVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<BrdBbsInfoVO> listPageingForMenu(BrdBbsInfoVO vo, 
			int pageIndex, int listScale, int pageScale) throws Exception {
		ProcessResultListVO<BrdBbsInfoVO> resultList = new ProcessResultListVO<BrdBbsInfoVO>(); 
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(pageIndex);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = brdBbsInfoMapper.countForMenu(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<BrdBbsInfoVO> bbsInfoList =  brdBbsInfoMapper.listPageingForMenu(vo);
			resultList.setResult(1);
			resultList.setReturnList(bbsInfoList);
			resultList.setPageInfo(paginationInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}	
	
	/**
	 * 게시판과 메뉴를 연결 한다.
	 * @param BrdBbsInfoVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int editMenuCd(BrdBbsInfoVO vo) throws Exception {
		return brdBbsInfoMapper.updateMenuCd(vo);
	}
	
	/**
	 * 게시판과 메뉴를 연결을 헤제 한다.
	 * @param BrdBbsInfoVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int editMenuCdToNull(BrdBbsInfoVO vo) throws Exception {
		return brdBbsInfoMapper.updateMenuCdToNull(vo);
	}
	
	/**
	 * Default가 아닌 게시판과 메뉴를 연결을 헤제 한다.
	 * @param BrdBbsInfoVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int editAllMenuCdToNull(BrdBbsInfoVO vo) throws Exception {
		return brdBbsInfoMapper.updateAllMenuCdToNull(vo);
	}	
}
