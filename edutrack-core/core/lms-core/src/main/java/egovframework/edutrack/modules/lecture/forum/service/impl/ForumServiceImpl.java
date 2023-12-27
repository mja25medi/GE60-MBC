package egovframework.edutrack.modules.lecture.forum.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.lecture.forum.service.ForumAtclVO;
import egovframework.edutrack.modules.lecture.forum.service.ForumCmntVO;
import egovframework.edutrack.modules.lecture.forum.service.ForumJoinUserVO;
import egovframework.edutrack.modules.lecture.forum.service.ForumService;
import egovframework.edutrack.modules.lecture.forum.service.ForumVO;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Service("forumService")
public class ForumServiceImpl extends EgovAbstractServiceImpl implements ForumService {


	private final class AtclFileHandler
		implements FileHandler<ForumAtclVO> {

		@Override
		public String getPK(ForumAtclVO vo) {
			return vo.getAtclSn().toString();
		}

		@Override
		public String getRepoCd() {
			return "FORUM_ATCL";
		}

		@Override
		public List<SysFileVO> getFiles(ForumAtclVO vo) {
			List<SysFileVO> fileList = vo.getAttachImages();
			fileList.addAll(vo.getAttachFiles());
			return fileList;
		}

		@Override
		public ForumAtclVO setFiles(ForumAtclVO vo, FileListVO fileListVO) {
			vo.setAttachImages(fileListVO.getFiles("image"));
			vo.setAttachFiles(fileListVO.getFiles("file"));
			return vo;
		}
	}

	@Resource(name="forumMapper")
	private ForumMapper forumMapper;

	@Resource(name="forumAtclMapper")
	private ForumAtclMapper forumAtclMapper;

	@Resource(name="forumCmntMapper")
	private ForumCmntMapper forumCmntMapper;

	@Resource(name="forumJoinUserMapper")
	private ForumJoinUserMapper forumJoinUserMapper;

	@Resource(name="sysFileService")
	private SysFileService sysFileService;


	/**
	 * 토론 목록 조회
	 * @author twkim
	 * @date 2013. 10. 22.
	 * @param vo
	 * @return ProcessResultListVO<ForumVO>
	 */
	@Override
	public ProcessResultListVO<ForumVO> listForum(ForumVO vo) throws Exception{
		ProcessResultListVO<ForumVO> resultList = new ProcessResultListVO<ForumVO>();
		try {
			List<ForumVO> returnList =  forumMapper.list(vo);
			resultList.setResult(1);
			resultList.setReturnList(returnList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	/**
	 * 토론 정보 조회
	 * @author twkim
	 * @date 2013. 10. 22.
	 * @param vo
	 * @return ProcessResultVO<ForumVO>
	 */
	@Override
	public ProcessResultVO<ForumVO> viewForum(ForumVO vo) throws Exception{
		ProcessResultVO<ForumVO> resultVO = new ProcessResultVO<ForumVO>();
		try {
			ForumVO returnVO = forumMapper.select(vo);
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
	 * 토론 정보 등록
	 * @author twkim
	 * @date 2013. 10. 22.
	 * @param vo
	 * @return ProcessResultVO<ForumVO>
	 */
	@Override
	public ProcessResultVO<ForumVO> addForum(ForumVO vo) throws Exception{

		ProcessResultVO<ForumVO> resultVO = new ProcessResultVO<ForumVO>();
		try {
			if(vo.getForumStartHour().length() ==1){
				vo.setForumStartHour("0"+vo.getForumStartHour());
			}
			if(vo.getForumStartMin().length() ==1){
				vo.setForumStartMin("0"+vo.getForumStartMin());
			}

			if(vo.getForumEndHour().length() ==1){
				vo.setForumEndHour("0"+vo.getForumEndHour());
			}
			if(vo.getForumEndMin().length() ==1){
				vo.setForumEndMin("0"+vo.getForumEndMin());
			}
			//-- 시간 관련 처리
			String forumStartDttm = StringUtil.ReplaceAll(vo.getForumStartDttm(),".","")+vo.getForumStartHour()+vo.getForumStartMin()+"01";
			String forumEndDttm = StringUtil.ReplaceAll(vo.getForumEndDttm(),".","")+vo.getForumEndHour()+vo.getForumEndMin()+"59";

			vo.setForumStartDttm(forumStartDttm);
			vo.setForumEndDttm(forumEndDttm);
			//---- 신규 코드 세팅
			vo.setForumSn(forumMapper.selectKey());
		
			forumMapper.insert(vo);
			
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
	 * 토론 정보 수정
	 * @author twkim
	 * @date 2013. 10. 22.
	 * @param vo
	 * @return ProcessResultVO<ForumVO>
	 */
	@Override
	public ProcessResultVO<ForumVO> editForum(ForumVO vo) throws Exception{

		ProcessResultVO<ForumVO> resultVO = new ProcessResultVO<ForumVO>();
		try {
			
			if(vo.getForumStartHour().length() ==1){
				vo.setForumStartHour("0"+vo.getForumStartHour());
			}
			if(vo.getForumStartMin().length() ==1){
				vo.setForumStartMin("0"+vo.getForumStartMin());
			}

			if(vo.getForumEndHour().length() ==1){
				vo.setForumEndHour("0"+vo.getForumEndHour());
			}
			if(vo.getForumEndMin().length() ==1){
				vo.setForumEndMin("0"+vo.getForumEndMin());
			}
			//-- 시간 관련 처리
			String forumStartDttm = StringUtil.ReplaceAll(vo.getForumStartDttm(),".","")+vo.getForumStartHour()+vo.getForumStartMin()+"01";
			String forumEndDttm = StringUtil.ReplaceAll(vo.getForumEndDttm(),".","")+vo.getForumEndHour()+vo.getForumEndMin()+"59";

			vo.setForumStartDttm(forumStartDttm);
			vo.setForumEndDttm(forumEndDttm);

			forumMapper.update(vo);
			
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
	 * 토론 정보 삭제
	 * @author twkim
	 * @date 2013. 10. 22.
	 * @param vo
	 * @return ProcessResultVO<ForumVO>
	 */
	@Override
	public ProcessResultVO<ForumVO> deleteForum(ForumVO vo) throws Exception{

		
		ProcessResultVO<ForumVO> resultVO = new ProcessResultVO<ForumVO>();
		try {
			// 토론 점수 정보 삭제
			ForumJoinUserVO forumJoinUserVO = new ForumJoinUserVO();
			forumJoinUserVO.setCrsCreCd(vo.getCrsCreCd());
			forumJoinUserVO.setForumSn(vo.getForumSn());
			forumJoinUserMapper.delete(forumJoinUserVO);

			// 댓글 정보 삭제
			ForumCmntVO forumCmntVO = new ForumCmntVO();
			forumCmntVO.setCrsCreCd(vo.getCrsCreCd());
			forumCmntVO.setForumSn(vo.getForumSn());
			forumCmntMapper.delete(forumCmntVO);

			// 게시글
			ForumAtclVO forumAtclVO = new ForumAtclVO();
			forumAtclVO.setCrsCreCd(vo.getCrsCreCd());
			forumAtclVO.setForumSn(vo.getForumSn());

			// 실제 파일들을 지운다.
			List<ForumAtclVO> atclList = forumAtclMapper.list(forumAtclVO);
			for(ForumAtclVO faVO: atclList){
				sysFileService.removeFile(faVO, new AtclFileHandler());
			}
			// 게시글 정보 삭제
			forumAtclMapper.delete(forumAtclVO);

			// 토론 정보 삭제
			forumMapper.delete(vo);
			
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
	 * 토론 게시글 목록 조회 paging
	 * @author twkim
	 * @date 2013. 10. 23.
	 * @param vo
	 * @param curPage
	 * @return ProcessResultListVO<ForumArticleVO>
	 */
	@Override
	public ProcessResultListVO<ForumAtclVO> listAtclPageing(ForumAtclVO vo)  throws Exception{

		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(vo.getCurPage());
		paginationInfo.setRecordCountPerPage(Constants.LIST_SCALE);
		paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		
		int totalCount = forumAtclMapper.count(vo);
		paginationInfo.setTotalRecordCount(totalCount);
		
		ProcessResultListVO<ForumAtclVO> resultList = new ProcessResultListVO<ForumAtclVO>();
		List<ForumAtclVO> returnList =  new ArrayList<ForumAtclVO>();
		if("mysql".equals(Constants.DATABASE_DB_TYPE) && "5".equals(Constants.DATABASE_DB_VERSION)) {
			returnList =  forumAtclMapper.listPageingVer5(vo);
		}else {
			returnList =  forumAtclMapper.listPageing(vo);
		}
		resultList.setReturnList(returnList);
		resultList.setPageInfo(paginationInfo);
		
		
		List<ForumAtclVO> forumAtclList = new ArrayList<ForumAtclVO>();
		for(ForumAtclVO svo: resultList.getReturnList()) {
			svo = sysFileService.getFile(svo, new AtclFileHandler());
			this.atclUrlToView(svo);
			forumAtclList.add(svo);
		}
		resultList.setReturnList(forumAtclList);
		return resultList;
	}

	/**
	 * 토론 게시글 상세 조회
	 * @author twkim
	 * @date 2013. 10. 23.
	 * @param vo
	 * @param hitsup
	 * @return ProcessResultVO<ForumArticleVO>
	 */
	@Override
	public ProcessResultVO<ForumAtclVO> viewAtcl(ForumAtclVO vo, boolean hitsup) throws Exception{
		// 조회수 증가
		if(hitsup) forumAtclMapper.hitsup(vo);
		vo = forumAtclMapper.select(vo);
		// 첨부파일 정보 조회
		vo = sysFileService.getFile(vo, new AtclFileHandler());
		this.atclUrlToView(vo);
		return new ProcessResultVO<ForumAtclVO>().setResultSuccess()
				.setReturnVO(vo);

	}

	/**
	 * 토론 게시글 등록
	 * @author twkim
	 * @date 2013. 10. 22.
	 * @param vo
	 * @return ProcessResultVO<ForumArticleVO>
	 */
	@Override
	public ProcessResultVO<ForumAtclVO> addAtcl(ForumAtclVO vo)  throws Exception{

		ProcessResultVO<ForumAtclVO> resultVO = new ProcessResultVO<ForumAtclVO>();
		try {
			if(hasParentArticle(vo.getParAtclSn())) {
				// 상위글 정보를 조회해서 설정
				ForumAtclVO parent = new ForumAtclVO();
				parent.setAtclSn(vo.getParAtclSn());
				parent = forumAtclMapper.select(vo);
				vo.setAtclLvl(parent.getAtclLvl() + 1);
			} else {
				vo.setParAtclSn(null);
				vo.setAtclLvl(0);
			}
			this.atclUrlToPersist(vo);
			
			// 키값 조회
			vo.setAtclSn(forumAtclMapper.selectKey()); 
			
			// 글저장 후 주키 값 받아오기.
			forumAtclMapper.insert(vo); //게시글 정보 테이블로 저장
			
			// 첨부파일 저장
			sysFileService.bindFile(vo, new AtclFileHandler());

			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}

	/**
	 * 상의글정보 정합성
	 * @author twkim
	 * @date 2013. 10. 24.
	 * @param parentSn
	 * @return boolean
	 */
	private boolean hasParentArticle(Integer parentSn) {
		return ValidationUtils.isNotNull(parentSn) && parentSn > 0;
	}

	/**
	 * 토론 게시글 수정
	 * @author twkim
	 * @date 2013. 10. 24.
	 * @param vo
	 * @return ProcessResultVO<ForumArticleVO>
	 */
	@Override
	public ProcessResultVO<ForumAtclVO> editAtcl(ForumAtclVO vo)  throws Exception{
		sysFileService.bindFileUpdate(vo, new AtclFileHandler());
		this.atclUrlToPersist(vo);
		forumAtclMapper.update(vo);
		return ProcessResultVO.success();
	}

	/**
	 * 토론 게시글 삭제
	 * @author twkim
	 * @date 2013. 10. 24.
	 * @param vo
	 * @return ProcessResultVO<?>
	 */
	@Override
	public ProcessResultVO<?> removeAtcl(ForumAtclVO vo, String classUserType)  throws Exception{
		// 실제 파일들을 지운다.
		ForumAtclVO forumArticleVO = this.viewAtcl(vo,false).getReturnVO();
		ForumCmntVO  rorumCmntVO = new  ForumCmntVO();

		sysFileService.removeFile(forumArticleVO, new AtclFileHandler());

		rorumCmntVO.setCrsCreCd(vo.getCrsCreCd());
		rorumCmntVO.setForumSn(vo.getForumSn());
		rorumCmntVO.setAtclSn(vo.getAtclSn());
		if(classUserType == "TCH"){
			forumCmntMapper.delete(rorumCmntVO);
			forumAtclMapper.delete(vo);	// 게시글을 삭제
		}else if(classUserType == "STU"){
			forumAtclMapper.delete(vo);	// 게시글을 삭제
		}

		return ProcessResultVO.success();
	}

	/**
	 * 토론 게시글 삭제
	 * @author twkim
	 * @date 2013. 10. 24.
	 * @param vo
	 * @return ProcessResultVO<?>
	 */
	@Override
	public ProcessResultVO<?> removeAtcl(ForumAtclVO vo)  throws Exception{
		// 실제 파일들을 지운다.
		ForumAtclVO forumArticleVO = this.viewAtcl(vo,false).getReturnVO();
		ForumCmntVO  forumCmntVO = new  ForumCmntVO();

		sysFileService.removeFile(forumArticleVO, new AtclFileHandler());

		forumCmntVO.setCrsCreCd(vo.getCrsCreCd());
		forumCmntVO.setForumSn(vo.getForumSn());
		forumCmntVO.setAtclSn(vo.getAtclSn());
		forumCmntMapper.delete(forumCmntVO);
		forumAtclMapper.delete(vo);	// 게시글을 삭제

		return ProcessResultVO.success();
	}

	/**
	 * 댓글 목록 조회
	 * @author twkim
	 * @date 2013. 10. 24.
	 * @param vo
	 * @param curPage
	 * @return ProcessResultListVO<ForumCommentVO>
	 */
	@Override
	public ProcessResultListVO<ForumCmntVO> listCmntPageing(ForumCmntVO vo, int curPage)  throws Exception{

		ProcessResultListVO<ForumCmntVO> resultList = new ProcessResultListVO<ForumCmntVO>();
		
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(10);
		paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());		
		
		try {
			paginationInfo.setTotalRecordCount(forumCmntMapper.count(vo));
			List<ForumCmntVO> returnList =  forumCmntMapper.listPageing(vo);
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
	 * 댓글 정보 저장
	 * @author twkim
	 * @date 2013. 10. 24.
	 * @param vo
	 * @return ProcessResultVO<ForumCommentVO>
	 */
	@Override
	public ProcessResultVO<ForumCmntVO> addCmnt(ForumCmntVO vo)  throws Exception{
		
		ProcessResultVO<ForumCmntVO> resultVO = new ProcessResultVO<ForumCmntVO>();
		try {
			//---- 신규 코드 세팅
			vo.setCmntSn(forumCmntMapper.selectKey());

			forumCmntMapper.insert(vo);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}

	/**
	 * 댓글 정보 삭제
	 * @author twkim
	 * @date 2013. 10. 24.
	 * @param vo
	 * @return ProcessResultVO<?>
	 */
	@Override
	public ProcessResultVO<?> removeCmnt(ForumCmntVO vo)  throws Exception{
		ProcessResultVO<?> resultVO = new ProcessResultVO();
		try {
			forumCmntMapper.delete(vo);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}

	/**
	 * 학습자 목록
	 * @author twkim
	 * @date 2013. 10. 28.
	 * @param vo
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 */
	@Override
	public ProcessResultListVO<ForumJoinUserVO> listJoinUserPageing(
			ForumJoinUserVO vo)  throws Exception{
		
		ProcessResultListVO<ForumJoinUserVO> resultList = new ProcessResultListVO<ForumJoinUserVO>();
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(vo.getCurPage());
		paginationInfo.setRecordCountPerPage(vo.getListScale());
		paginationInfo.setPageSize(vo.getPageScale());
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		try {
			// 전체 목록 수
			int totalCount = forumJoinUserMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<ForumJoinUserVO> returnList =  forumJoinUserMapper.listPageing(vo);
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
	 * 학습자 목록
	 * @author twkim
	 * @date 2013. 10. 28.
	 * @param vo
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 */
	@Override
	public ProcessResultListVO<ForumJoinUserVO> listJoinUser(
			ForumJoinUserVO vo)  throws Exception{
		ProcessResultListVO<ForumJoinUserVO> resultList = new ProcessResultListVO<ForumJoinUserVO>();
		try {
			List<ForumJoinUserVO> returnList =  forumJoinUserMapper.list(vo);
			resultList.setResult(1);
			resultList.setReturnList(returnList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		
		return resultList;
	}

	/**
	 * 토론 점수 개별 등록
	 * @author twkim
	 * @date 2013. 10. 28.
	 * @param vo
	 * @return ProcessResultVO<ForumJoinUserVO>
	 */
	@Override
	public ProcessResultVO<ForumJoinUserVO> addJoinUser(ForumJoinUserVO vo)  throws Exception{

		try{
			forumJoinUserMapper.insert(vo);
		}
		catch (DuplicateKeyException e) {
			forumJoinUserMapper.update(vo);
		}

		return ProcessResultVO.success();
	}

	/**
	 * 토론 점수 일괄 등록
	 * @author twkim
	 * @date 2013. 10. 29.
	 * @param vo
	 * @param strStdNo
	 * @param strScore
	 * @return ProcessResultVO<ForumJoinUserVO>
	 */
	@Override
	public ProcessResultVO<ForumJoinUserVO> addJoinUserAll(
			ForumJoinUserVO vo,	String strStdNo, String strScore)  throws Exception{

		String[] strStdNoArray = StringUtil.split(strStdNo,"|");
		String[] strScoreArray = StringUtil.split(strScore,"|");

		for(int i = 0; i < strStdNoArray.length; i++) {
			vo.setStdNo(strStdNoArray[i]);
			vo.setScore(Integer.parseInt(strScoreArray[i]));
			this.addJoinUser(vo);
		}
		return ProcessResultVO.success();
	}

	private void atclUrlToPersist(ForumAtclVO vo) {
		vo.setCts(StringUtil.replaceUrlToPersist(vo.getCts()));
	}

	private void atclUrlToView(ForumAtclVO vo) {
		vo.setCts(StringUtil.replaceUrlToBrowser(vo.getCts()));
	}

	/**
	 * 토론 정보 목록 조회	(학습자용)
	 * @author twkim
	 * @date 2013. 10. 22.
	 * @param vo
	 * @return ProcessResultListVO<ForumVO>
	 */
	@Override
	public ProcessResultListVO<ForumVO> listForumStd(ForumVO vo) throws Exception{
		ProcessResultListVO<ForumVO> resultList = new ProcessResultListVO<ForumVO>();
		try {
			List<ForumVO> returnList =  forumMapper.listStd(vo);
			resultList.setResult(1);
			resultList.setReturnList(returnList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		
		return resultList;
	}

}