package egovframework.edutrack.modules.board.bbs.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.board.bbs.service.BrdBbsAtclService;
import egovframework.edutrack.modules.board.bbs.service.BrdBbsAtclVO;
import egovframework.edutrack.modules.board.bbs.service.BrdBbsCmntVO;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *  <b>게시판 - 게시판 게시물 관리</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("brdBbsAtclService")
public class BrdBbsAtclServiceImpl 
	extends EgovAbstractServiceImpl implements BrdBbsAtclService {

	
	
	private final class NestedFileHandler
		implements FileHandler<BrdBbsAtclVO> {
	
		@Override
		public String getPK(BrdBbsAtclVO vo) {
			return vo.getAtclSn().toString();
		}
	
		@Override
		public String getRepoCd() {
			return "BBS_ATCL";
		}
	
		@Override
		public List<SysFileVO> getFiles(BrdBbsAtclVO vo) {
			List<SysFileVO> fileList = vo.getAttachImages();
			fileList.addAll(vo.getAttachFiles());
			fileList.addAll(vo.getAttachPhotos());
			if(ValidationUtils.isNotZeroNull(vo.getThumbFileSn()))
				fileList.add(vo.getThumbFile());
	
			return fileList;
		}
	
		@Override
		public BrdBbsAtclVO setFiles(BrdBbsAtclVO vo, FileListVO fileListVO) {
			vo.setAttachImages(fileListVO.getFiles("image"));
			vo.setAttachFiles(fileListVO.getFiles("file"));
			vo.setAttachPhotos(fileListVO.getFiles("photo"));
			vo.setThumbFile(fileListVO.getFile("thumb"));
			return vo;
		}
	}
	
	/** Mapper */
	@Resource(name="brdBbsInfoMapper")
	private BrdBbsInfoMapper 		brdBbsInfoMapper;
	
	@Resource(name="brdBbsHeadMapper")
	private BrdBbsHeadMapper 		brdBbsHeadMapper;

	@Resource(name="brdBbsAtclMapper")
	private BrdBbsAtclMapper 		brdBbsAtclMapper;

	@Resource(name="brdBbsCmntMapper")
	private BrdBbsCmntMapper 		brdBbsCmntMapper;
	
	@Resource(name="sysFileService")
	private SysFileService 		sysFileService;
	
	/**
	 * 게시판 게시물 전체 목록을 조회한다.
	 * @param BrdBbsAtclVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<BrdBbsAtclVO> listAtcl(BrdBbsAtclVO vo) throws Exception {
		return this.listAtcl(vo, false);
	}
	
	/**
	 * 게시판 게시물 전체 목록을 조회한다.
	 * @param BrdBbsAtclVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<BrdBbsAtclVO> listAtcl(BrdBbsAtclVO vo, boolean filein) throws Exception {
		ProcessResultListVO<BrdBbsAtclVO> resultList = new ProcessResultListVO<BrdBbsAtclVO>(); 
		try {
			List<BrdBbsAtclVO> returnList =  brdBbsAtclMapper.list(vo);
			if(filein) {
				List<BrdBbsAtclVO> bbsAtclList = new ArrayList<BrdBbsAtclVO>();
				for(BrdBbsAtclVO sbbavo : returnList) {
					sbbavo = sysFileService.getFile(sbbavo, new NestedFileHandler());
					this.atclUrlToView(sbbavo);
					bbsAtclList.add(sbbavo);
				}
				resultList.setReturnList(bbsAtclList);
			} else {
				resultList.setReturnList(returnList);	
			}
			resultList.setResult(1);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	/**
	 * 게시판 게시물 상위 목록을 조회한다.
	 * @param BrdBbsAtclVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<BrdBbsAtclVO> listTopAtcl(BrdBbsAtclVO vo) throws Exception {
		return this.listTopAtcl(vo, false);
	}
	
	/**
	 * 게시판 게시물 상위 목록을 조회한다.
	 * @param BrdBbsAtclVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<BrdBbsAtclVO> listTopAtcl(BrdBbsAtclVO vo, boolean filein) throws Exception {
		ProcessResultListVO<BrdBbsAtclVO> resultList = new ProcessResultListVO<BrdBbsAtclVO>(); 
		try {
			List<BrdBbsAtclVO> returnList =  brdBbsAtclMapper.listTop(vo);
			if(filein) {
				List<BrdBbsAtclVO> bbsAtclList = new ArrayList<BrdBbsAtclVO>();
				for(BrdBbsAtclVO sbbavo : returnList) {
					sbbavo = sysFileService.getFile(sbbavo, new NestedFileHandler());
					this.atclUrlToView(sbbavo);
					bbsAtclList.add(sbbavo);
				}
				resultList.setReturnList(bbsAtclList);
			} else {
				resultList.setReturnList(returnList);	
			}
			resultList.setResult(1);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}	

	/**
	 * 게시판 게시물 페이징 목록을 조회한다.
	 * @param BrdBbsAtclVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<BrdBbsAtclVO> listAtclPageing(BrdBbsAtclVO vo, 
			int pageIndex, int listScale, int pageScale, boolean filein) throws Exception {
		ProcessResultListVO<BrdBbsAtclVO> resultList = new ProcessResultListVO<BrdBbsAtclVO>(); 
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(pageIndex);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setListScale(listScale);
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = brdBbsAtclMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<BrdBbsAtclVO> returnList =  brdBbsAtclMapper.listPageing(vo);
			if(filein) {
				List<BrdBbsAtclVO> bbsAtclList = new ArrayList<BrdBbsAtclVO>();
				for(BrdBbsAtclVO sbbavo : returnList) {
					sbbavo = sysFileService.getFile(sbbavo, new NestedFileHandler());
					this.atclUrlToView(sbbavo);
					bbsAtclList.add(sbbavo);
				}
				resultList.setReturnList(bbsAtclList);
			} else {
				resultList.setReturnList(returnList);	
			}				
			resultList.setResult(1);
			resultList.setPageInfo(paginationInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}	
	
	/**
	 * 게시판 게시물 페이징 목록을 조회한다.
	 * @param BrdBbsAtclVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<BrdBbsAtclVO> listAtclPageing(BrdBbsAtclVO vo, 
			int pageIndex, int listScale, int pageScale) throws Exception {
		return this.listAtclPageing(vo, pageIndex, listScale, pageScale, false);
	}	
	
	/**
	 * 게시판 게시물 페이징 목록을 조회한다.
	 * @param BrdBbsAtclVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<BrdBbsAtclVO> listAtclPageing(BrdBbsAtclVO vo, 
			int pageIndex, int listScale) throws Exception {
		return this.listAtclPageing(vo, pageIndex, listScale, Constants.LIST_PAGE_SCALE, false);
	}
	
	/**
	 * 게시판 게시물 페이징 목록을 조회한다.
	 * @param BrdBbsAtclVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<BrdBbsAtclVO> listAtclPageing(BrdBbsAtclVO vo, 
			int pageIndex) throws Exception {
		return this.listAtclPageing(vo, pageIndex, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE, false);
	}
	
	/**
	 * 게시판 게시물 페이징 목록을 조회한다.
	 * @param BrdBbsAtclVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<BrdBbsAtclVO> listAtclPageing(BrdBbsAtclVO vo, 
			int pageIndex, int listScale, boolean filein) throws Exception {
		return this.listAtclPageing(vo, pageIndex, listScale, Constants.LIST_PAGE_SCALE, filein);
	}
	
	/**
	 * 게시판 게시물 페이징 목록을 조회한다.
	 * @param BrdBbsAtclVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<BrdBbsAtclVO> listAtclPageing(BrdBbsAtclVO vo, 
			int pageIndex, boolean filein) throws Exception {
		return this.listAtclPageing(vo, pageIndex, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE, filein);
	}	
	
	/**
	 * 게시판 게시물 상세 게시물를 조회한다.
	 * @param BrdBbsAtclVO
	 * @return BrdBbsAtclVO
	 * @throws Exception
	 */
	@Override
	public BrdBbsAtclVO viewAtcl(BrdBbsAtclVO vo) throws Exception {
		return this.viewAtcl(vo, false);
	}
	
	/**
	 * 게시판 게시물 상세 게시물를 조회한다.
	 * @param BrdBbsAtclVO
	 * @return BrdBbsAtclVO
	 * @throws Exception
	 */
	@Override
	public BrdBbsAtclVO viewAtcl(BrdBbsAtclVO vo, boolean hitsup) throws Exception {
		if(hitsup) brdBbsAtclMapper.hitsup(vo); //-- 조회수를 증가시킴
		vo = brdBbsAtclMapper.select(vo);
		vo = sysFileService.getFile(vo, new NestedFileHandler());
		return vo;
	}	
	
	@Override
	public BrdBbsAtclVO viewAtclService(BrdBbsAtclVO vo, boolean hitsup) throws Exception {
		if(hitsup) brdBbsAtclMapper.hitsup(vo); //-- 조회수를 증가시킴
		vo = brdBbsAtclMapper.selectService(vo);
		return vo;
	}	
	
	/**
	 * 게시판 게시물 상세 게시물를 조회한다.(이전글, 다음글 포함)
	 * @param BrdBbsAtclVO
	 * @return BrdBbsAtclVO
	 * @throws Exception
	 */
	@Override
	public BrdBbsAtclVO viewAtclWithPreNext(BrdBbsAtclVO vo, boolean hitsup) throws Exception {
		if(hitsup) brdBbsAtclMapper.hitsup(vo); //-- 조회수를 증가시킴
		if("mysql".equals(Constants.DATABASE_DB_TYPE) && "5".equals(Constants.DATABASE_DB_VERSION)) {
			vo = brdBbsAtclMapper.selectAtclWithPreNextVer5(vo);
		}else {
			vo = brdBbsAtclMapper.selectAtclWithPreNext(vo);
		}
		vo = sysFileService.getFile(vo, new NestedFileHandler());
		return vo;
	}
	
	/**
	 * 게시판 게시물 게시물를 등록한다.
	 * @param BrdBbsAtclVO
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String addAtcl(BrdBbsAtclVO vo) throws Exception {
		if(ValidationUtils.isEmpty(vo.getAtclSn())) {
			vo.setAtclSn(brdBbsAtclMapper.selectKey());
		}
		int insert = brdBbsAtclMapper.insert(vo);
		String result = Integer.toString(insert);
		sysFileService.bindFile(vo, new NestedFileHandler());
		return result;
	}	
	
	@Override
	public String addAtclService(BrdBbsAtclVO vo) throws Exception {
		if(ValidationUtils.isEmpty(vo.getAtclSn())) {
			vo.setAtclSn(brdBbsAtclMapper.selectKey());
		}
		int insert = brdBbsAtclMapper.insert(vo);
		insert = brdBbsAtclMapper.insertService(vo);
		
		String result = Integer.toString(insert);
		return result;
	}	
	
	/**
	 * 게시판 게시물 게시물를 수정한다.
	 * @param BrdBbsAtclVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int editAtcl(BrdBbsAtclVO vo) throws Exception {
		sysFileService.bindFileUpdate(vo, new NestedFileHandler());
		return brdBbsAtclMapper.update(vo);
	}
	
	/**
	 * 게시판 게시물 게시물를 삭제 한다.
	 * @param BrdBbsAtclVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int removeAtcl(BrdBbsAtclVO vo) throws Exception {
		//-- 게시판 댓글 목록 삭제
		BrdBbsCmntVO bbcvo = new BrdBbsCmntVO();
		bbcvo.setAtclSn(vo.getAtclSn());
		brdBbsCmntMapper.deleteAll(bbcvo);
		
		sysFileService.removeFile(vo, new NestedFileHandler());
		return brdBbsAtclMapper.delete(vo);
	}
	
	/**
	 * 게시판 글댓 전체 목록을 조회한다.
	 * @param BrdBbsCmntVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<BrdBbsCmntVO> listCmnt(BrdBbsCmntVO vo) throws Exception {
		ProcessResultListVO<BrdBbsCmntVO> resultList = new ProcessResultListVO<BrdBbsCmntVO>(); 
		try {
			List<BrdBbsCmntVO> bbsCmntList =  brdBbsCmntMapper.list(vo);
			resultList.setResult(1);
			resultList.setReturnList(bbsCmntList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
	/**
	 * 게시판 글댓 페이징 목록을 조회한다.
	 * @param BrdBbsCmntVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<BrdBbsCmntVO> listCmntPageing(BrdBbsCmntVO vo, 
			int pageIndex, int listScale, int pageScale) throws Exception {
		ProcessResultListVO<BrdBbsCmntVO> resultList = new ProcessResultListVO<BrdBbsCmntVO>(); 
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(pageIndex);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = brdBbsCmntMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<BrdBbsCmntVO> bbsCmntList =  brdBbsCmntMapper.listPageing(vo);
			resultList.setResult(1);
			resultList.setReturnList(bbsCmntList);
			resultList.setPageInfo(paginationInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
	/**
	 * 게시판 글댓 페이징 목록을 조회한다.
	 * @param BrdBbsCmntVO
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<BrdBbsCmntVO> listCmntPageing(BrdBbsCmntVO vo, 
			int pageIndex, int listScale) throws Exception {
		return this.listCmntPageing(vo, pageIndex, listScale, Constants.LIST_PAGE_SCALE);
	}
	
	/**
	 * 게시판 글댓 페이징 목록을 조회한다.
	 * @param BrdBbsCmntVO
	 * @param pageIndex
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<BrdBbsCmntVO> listCmntPageing(BrdBbsCmntVO vo, 
			int pageIndex) throws Exception {
		return this.listCmntPageing(vo, pageIndex, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}
	
	/**
	 * 게시판 글댓 상세 글댓를 조회한다.
	 * @param BrdBbsCmntVO
	 * @return BrdBbsCmntVO
	 * @throws Exception
	 */
	@Override
	public BrdBbsCmntVO viewCmnt(BrdBbsCmntVO vo) throws Exception {
		return brdBbsCmntMapper.select(vo);
	}
	
	/**
	 * 게시판 글댓 글댓를 등록한다.
	 * @param BrdBbsCmntVO
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String addCmnt(BrdBbsCmntVO vo) throws Exception {
		if(ValidationUtils.isEmpty(vo.getCmntSn())) {
			vo.setCmntSn(brdBbsCmntMapper.selectKey());
		}
		int insert = brdBbsCmntMapper.insert(vo);
		String result = Integer.toString(insert);
		return result;
	}	
	
	/**
	 * 게시판 글댓 글댓를 수정한다.
	 * @param BrdBbsCmntVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int editCmnt(BrdBbsCmntVO vo) throws Exception {
		return brdBbsCmntMapper.update(vo);
	}
	
	/**
	 * 게시판 글댓 글댓를 삭제 한다.
	 * @param BrdBbsCmntVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int removeCmnt(BrdBbsCmntVO vo) throws Exception {
		return brdBbsCmntMapper.delete(vo);
	}

	private void atclUrlToPersist(BrdBbsAtclVO vo) {
		vo.setAtclCts(StringUtil.replaceUrlToPersist(vo.getAtclCts()));
	}

	private void atclUrlToView(BrdBbsAtclVO vo) {
		vo.setAtclCts(StringUtil.replaceUrlToBrowser(vo.getAtclCts()));
	}

	@Override
	public BrdBbsAtclVO checkPassword(BrdBbsAtclVO vo) throws Exception {
		return brdBbsAtclMapper.checkPassword(vo);
	}	
}
