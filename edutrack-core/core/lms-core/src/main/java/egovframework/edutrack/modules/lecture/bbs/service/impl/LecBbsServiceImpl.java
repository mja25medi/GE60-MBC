package egovframework.edutrack.modules.lecture.bbs.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.lecture.bbs.service.LecBbsAtclVO;
import egovframework.edutrack.modules.lecture.bbs.service.LecBbsCmntVO;
import egovframework.edutrack.modules.lecture.bbs.service.LecBbsService;
import egovframework.edutrack.modules.lecture.bbs.service.LecBbsVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Service("lecBbsService")
public class LecBbsServiceImpl
	extends EgovAbstractServiceImpl implements LecBbsService {

	private class NestedFileHandler
			implements FileHandler<LecBbsAtclVO> {

		@Override
		public String getRepoCd() {
			return "LEC_BBS_ATCL";
		}

		@Override
		public String getPK(LecBbsAtclVO vo) {
			return vo.getAtclSn().toString();
		}

		@Override
		public List<SysFileVO> getFiles(LecBbsAtclVO vo) {
			List<SysFileVO> fileList = vo.getAttachImages();
			fileList.addAll(vo.getAttachFiles());
			return fileList;
		}

		@Override
		public LecBbsAtclVO setFiles(LecBbsAtclVO vo, FileListVO fileListVO) {
			vo.setAttachImages(fileListVO.getFiles("image"));
			vo.setAttachFiles(fileListVO.getFiles("file"));
			return vo;
		}
	}

	@Resource(name="lecBbsMapper")
	private LecBbsMapper			lecBbsMapper;

	@Resource(name="lecBbsAtclMapper")
	private LecBbsAtclMapper		lecBbsAtclMapper;

	@Resource(name="lecBbsCmntMapper")
	private LecBbsCmntMapper		lecBbsCmntMapper;

	@Resource(name="sysFileService")
	private SysFileService		sysFileService;

	/**
	 * 강의실 게시판의 정보를 가져온다.
	 * @param lecBbsVO.bbsCd
	 * @return LecBbsVO
	 */
	@Override
	public ProcessResultVO<LecBbsVO> viewInfo(LecBbsVO vo)  throws Exception{
		ProcessResultVO<LecBbsVO> resultVO = new ProcessResultVO<LecBbsVO>();
		try {
			LecBbsVO returnVO = lecBbsMapper.select(vo);
			resultVO.setReturnVO(returnVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}

	/**
	 * 게시판 글 목록 조회 (페이지당 표시갯수와 컨트롤 갯수는 {@link Constants} 를 참조한다.
	 *
	 * @param lecBbsAtclVO 검색을 위한 파라매터용 VO
	 * @param curPage 현재 페이지
	 * @return 조회결과 목록 VO
	 */
	@Override
	public ProcessResultListVO<LecBbsAtclVO> listAtclPageing(LecBbsAtclVO vo, boolean filein)  throws Exception{

		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(vo.getCurPage());
		paginationInfo.setRecordCountPerPage(vo.getListScale());
		paginationInfo.setPageSize(vo.getPageScale());
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());		
		ProcessResultListVO<LecBbsAtclVO> resultList = new ProcessResultListVO<LecBbsAtclVO>();
		
		// 전체 목록 수
		int totalCount = lecBbsAtclMapper.count(vo);
		paginationInfo.setTotalRecordCount(totalCount);
		
		List<LecBbsAtclVO> returnList = new ArrayList<LecBbsAtclVO>();
		if("mysql".equals(Constants.DATABASE_DB_TYPE) && "5".equals(Constants.DATABASE_DB_VERSION)) {
			returnList = lecBbsAtclMapper.listPageingVer5(vo);
		}else {
			returnList = lecBbsAtclMapper.listPageing(vo);
		}
		
		resultList.setReturnList(returnList);
		resultList.setPageInfo(paginationInfo);
		List<LecBbsAtclVO> bbsAtclList = new ArrayList<LecBbsAtclVO>();
		if(filein) {
			for(LecBbsAtclVO svo : resultList.getReturnList()) {
				svo = sysFileService.getFile(svo, new NestedFileHandler());
				this.atclUrlToView(svo);
				bbsAtclList.add(svo);
			}
			resultList.setReturnList(bbsAtclList);
		}
		return resultList;
	}

	@Override
	public ProcessResultListVO<LecBbsAtclVO> listAtclPageing(LecBbsAtclVO vo)  throws Exception{
		return this.listAtclPageing(vo, true);
	}



	/**
	 * 게시판 글 등록
	 *
	 * @param vo
	 * @return 등록 결과 VO (생성된 주키를 포함)
	 */
	@Override
	public ProcessResultVO<LecBbsAtclVO> addAtcl(LecBbsAtclVO vo)  throws Exception{ 
		if (ValidationUtils.isNotZeroNull(vo.getParAtclSn())) {
			// 상위글 정보를 조회해서 설정
			LecBbsAtclVO parent = new LecBbsAtclVO();
			parent.setCrsCreCd(vo.getCrsCreCd());
			parent.setBbsCd(vo.getBbsCd());
			parent.setAtclSn(vo.getParAtclSn());
			parent = lecBbsAtclMapper.select(parent);
			vo.setAtclLvl(parent.getAtclLvl() + 1);
		} else {
			vo.setParAtclSn(null);
		}
		if(ValidationUtils.isEmpty(vo.getAtclSn())) {
			vo.setAtclSn(lecBbsAtclMapper.selectKey());
		}
		// 글저장 후 주키 값 받아오기.
		lecBbsAtclMapper.insert(vo); // 게시글 정보 테이블로 저장
		sysFileService.bindFile(vo, new NestedFileHandler());
		return ProcessResultVO.success();
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
	public ProcessResultVO<LecBbsAtclVO> viewAtcl(LecBbsAtclVO vo)  throws Exception{
		return this.viewAtcl(vo, true);
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
	public ProcessResultVO<LecBbsAtclVO> viewAtcl(LecBbsAtclVO vo, boolean hitup)  throws Exception{
		// 조회수 증가
		if(hitup) lecBbsAtclMapper.hitup(vo);
		vo = lecBbsAtclMapper.select(vo);
		vo = sysFileService.getFile(vo, new NestedFileHandler());
		this.atclUrlToView(vo);
		return new ProcessResultVO<LecBbsAtclVO>().setResultSuccess()
				.setReturnVO(vo);
	}

	/**
	 * 게시글에 소속된 댓글 목록 조회
	 *
	 * @param commentVO
	 * @return
	 */
	@Override
	public ProcessResultListVO<LecBbsCmntVO> listCmntPageing(LecBbsCmntVO vo)  throws Exception{

		ProcessResultListVO<LecBbsCmntVO> resultList = new ProcessResultListVO<LecBbsCmntVO>();
		
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(vo.getCurPage());
		paginationInfo.setRecordCountPerPage(Constants.LIST_SCALE);
		paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());	
		
		// 전체 목록 수
		int totalCount = lecBbsCmntMapper.count(vo);
		paginationInfo.setTotalRecordCount(totalCount);
		
		resultList.setReturnList(lecBbsCmntMapper.listPageing(vo)); 
		resultList.setPageInfo(paginationInfo);
		
		return resultList;
	}
	
	/**
	 * 게시글에 소속된 댓글 목록 조회
	 *
	 * @param commentVO
	 * @return
	 */
	@Override
	public List<LecBbsCmntVO> listCmnt(LecBbsCmntVO vo)  throws Exception{
		return lecBbsCmntMapper.list(vo); 
	}

	/**
	 * 댓글 추가
	 *
	 * @param comment
	 * @return
	 */
	@Override
	public ProcessResultVO<LecBbsCmntVO> addCmnt(LecBbsCmntVO vo) throws Exception {
		ProcessResultVO<LecBbsCmntVO> resultVO = new ProcessResultVO<LecBbsCmntVO>();
		try {
			vo.setCmntSn(lecBbsCmntMapper.selectKey());
			lecBbsCmntMapper.insert(vo);
			resultVO.setReturnVO(vo);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}

	/**
	 * 게시판 글 정보 조회 (조회수 미증가)
	 *
	 * @param articleVO 글 고유 번호
	 * @return 조회 결과 VO
	 */
	@Override
	public ProcessResultVO<LecBbsAtclVO> getAtcl(LecBbsAtclVO vo) throws Exception {
		vo = lecBbsAtclMapper.select(vo);
		vo = sysFileService.getFile(vo, new NestedFileHandler());
		return new ProcessResultVO<LecBbsAtclVO>().setResultSuccess().setReturnVO(vo);
	}

	/**
	 * 게시판 글 수정
	 *
	 * @param vo 수정될 정보를 가지고 있는 VO
	 * @return 수정 결과 VO
	 */
	@Override
	public ProcessResultVO<?> editAtcl(LecBbsAtclVO vo) throws Exception {
		// 첨부파일 정보를 DELETE후 INSERT한다. (실제 파일과는 무관함)
		sysFileService.bindFileUpdate(vo, new NestedFileHandler());
		lecBbsAtclMapper.update(vo);
		return ProcessResultVO.success();
	}

	/**
	 * 게시판 글 삭제
	 *
	 * @param articleVO 삭제 대상 고유 번호
	 * @return 삭제 처리 결과 VO
	 */
	@Override
	public ProcessResultVO<?> removeAtcl(LecBbsAtclVO vo)  throws Exception{
		// 실제 파일들을 지운다.
		vo = this.getAtcl(vo).getReturnVO();
		sysFileService.removeFile(vo, new NestedFileHandler());

		LecBbsCmntVO cmntVO = new LecBbsCmntVO();
		cmntVO.setArclSn(cmntVO.getArclSn());
		lecBbsCmntMapper.deleteAll(cmntVO);
		lecBbsAtclMapper.delete(vo); // 게시글을 삭제
		return ProcessResultVO.success();
	}

	/**
	 * 댓글 삭제
	 *
	 * @param comment
	 * @return
	 */
	@Override
	public ProcessResultVO<?> removeCmnt(LecBbsCmntVO vo)  throws Exception{
		ProcessResultVO<?> resultVO = new ProcessResultVO();
		try {
			lecBbsCmntMapper.delete(vo);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}

	/**
	 * 댓글 수정
	 *
	 * @param comment
	 * @return
	 */
	@Override
	public ProcessResultVO<LecBbsCmntVO> editCmnt(LecBbsCmntVO vo)  throws Exception{
		ProcessResultVO<LecBbsCmntVO> resultVO = new ProcessResultVO<LecBbsCmntVO>();
		try {
			lecBbsCmntMapper.update(vo);
			resultVO.setReturnVO(vo);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}

	/**
	 * 댓글 단일 항목 조회(수정전 자료 조회용)
	 *
	 * @param comment
	 * @return
	 */
	@Override
	public ProcessResultVO<LecBbsCmntVO> getCmnt(LecBbsCmntVO vo) throws Exception {
		ProcessResultVO<LecBbsCmntVO> resultVO = new ProcessResultVO<LecBbsCmntVO>();
		try {
			LecBbsCmntVO returnVO = lecBbsCmntMapper.select(vo);
			resultVO.setReturnVO(returnVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}

	private void atclUrlToPersist(LecBbsAtclVO vo) {
		vo.setCts(StringUtil.replaceUrlToPersist(vo.getCts()));
	}

	private void atclUrlToView(LecBbsAtclVO vo) {
		vo.setCts(StringUtil.replaceUrlToBrowser(vo.getCts()));
	}
}