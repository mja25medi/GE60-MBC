package egovframework.edutrack.modules.lecture.mybbs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.lecture.bbs.service.impl.LecBbsCmntMapper;
import egovframework.edutrack.modules.lecture.mybbs.service.MyLecBbsAtclService;
import egovframework.edutrack.modules.lecture.mybbs.service.MyLecBbsAtclVO;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Service("myLecbbsAtclService")
public class MyLecBbsAtclServiceImpl 
	extends EgovAbstractServiceImpl implements MyLecBbsAtclService {

	@Resource(name="myLecBbsAtclMapper")
	private MyLecBbsAtclMapper	myLecBbsAtclMapper;
	
	@Resource(name="lecBbsCmntMapper")
	private LecBbsCmntMapper	lecBbsCmntMapper;

	@Resource(name="sysFileService")
	private SysFileService	sysFileService;
	
	private class ArticleFileHandler
		implements FileHandler<MyLecBbsAtclVO> {
		
		@Override
		public String getRepoCd() {
			return "LEC_BBS_ATCL";
		}
		
		@Override
		public String getPK(MyLecBbsAtclVO vo) {
			return vo.getAtclSn().toString();
		}
		
		@Override
		public List<SysFileVO> getFiles(MyLecBbsAtclVO vo) {
			List<SysFileVO> fileList = vo.getAttachImages();
			fileList.addAll(vo.getAttachFiles());
			return fileList;			
		}
		
		@Override
		public MyLecBbsAtclVO setFiles(MyLecBbsAtclVO vo, FileListVO fileListVO) {
			vo.setAttachImages(fileListVO.getFiles("image"));
			vo.setAttachFiles(fileListVO.getFiles("file"));
			return vo;
		}
	}

	/**
	 * 나의 학습Q&A 페이징 목록을 반환한다.
	 * 
	 * @param MyLecBbsAtclVO
	 * @param curPage
	 * @param listScale
	 * @return 조회결과 목록 VO
	 */
	@Override
	public ProcessResultListVO<MyLecBbsAtclVO> listPageing(MyLecBbsAtclVO vo, 
			int curPage, int listScale, int pageScale)  throws Exception{

		ProcessResultListVO<MyLecBbsAtclVO> resultList = new ProcessResultListVO<MyLecBbsAtclVO>();
		
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());		

		try {
			// 전체 목록 수
			int totalCount = myLecBbsAtclMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<MyLecBbsAtclVO> returnList =  myLecBbsAtclMapper.listPageing(vo);
			resultList.setResult(1);
			resultList.setReturnList(returnList);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		
		
		
		return resultList;
	}
	
	/**
	 * 나의 학습Q&A 페이징 목록을 반환한다.
	 * 
	 * @param MyLecBbsAtclVO
	 * @param curPage
	 * @param listScale
	 * @return 조회결과 목록 VO
	 */
	@Override
		public ProcessResultListVO<MyLecBbsAtclVO> listPageing(MyLecBbsAtclVO vo, 
			int curPage, int listScale) throws Exception {

		ProcessResultListVO<MyLecBbsAtclVO> resultList = new ProcessResultListVO<MyLecBbsAtclVO>();
		
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		
	
		try {
			// 전체 목록 수
			int totalCount = myLecBbsAtclMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<MyLecBbsAtclVO> returnList =  myLecBbsAtclMapper.listPageing(vo);
			resultList.setResult(1);
			resultList.setReturnList(returnList);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		
		return resultList;
	}
	
	/**
	 * 나의 학습Q&A 페이징 목록을 반환한다.
	 * 
	 * @param MyLecBbsAtclVO
	 * @param curPage
	 * @return 조회결과 목록 VO
	 */
	@Override
		public ProcessResultListVO<MyLecBbsAtclVO> listPageing(MyLecBbsAtclVO vo, int curPage)  throws Exception{

		
		ProcessResultListVO<MyLecBbsAtclVO> resultList = new ProcessResultListVO<MyLecBbsAtclVO>();
		
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(Constants.LIST_SCALE);
		paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		
	
		try {
			// 전체 목록 수
			int totalCount = myLecBbsAtclMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<MyLecBbsAtclVO> returnList =  myLecBbsAtclMapper.listPageing(vo);
			resultList.setResult(1);
			resultList.setReturnList(returnList);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}

		
		
		return resultList;
	}
	
	/**
	 * 게시판 글 조회
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@Override
	public ProcessResultVO<MyLecBbsAtclVO> view(MyLecBbsAtclVO vo)  throws Exception{
		// 조회수 증가
		vo = myLecBbsAtclMapper.select(vo);
		vo = sysFileService.getFile(vo, new ArticleFileHandler());
		return new ProcessResultVO<MyLecBbsAtclVO>().setResultSuccess().setReturnVO(vo);
	}
}
