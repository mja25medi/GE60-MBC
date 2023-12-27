package egovframework.edutrack.modules.course.crsbbs.atcl.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.board.bbs.service.BrdBbsAtclService;
import egovframework.edutrack.modules.board.bbs.service.impl.BrdBbsInfoMapper;
import egovframework.edutrack.modules.course.crsbbs.atcl.service.CrsBbsAtclService;
import egovframework.edutrack.modules.course.crsbbs.atcl.service.CrsBbsAtclVO;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Service("crsBbsAtclService")
public class CrsBbsAtclServiceImpl 
extends EgovAbstractServiceImpl implements CrsBbsAtclService{
	
	private final class NestedFileHandler
		implements FileHandler<CrsBbsAtclVO> {
	
		@Override
		public String getPK(CrsBbsAtclVO VO) {
			return VO.getAtclSn().toString();
		}
	
		@Override
		public String getRepoCd() {
			return "CRS_BBS_ATCL";
		}
	
		@Override
		public List<SysFileVO> getFiles(CrsBbsAtclVO VO) {
			List<SysFileVO> fileList = VO.getAttachImages();
			fileList.addAll(VO.getAttachFiles());
			return fileList;			
		}
	
		@Override
		public CrsBbsAtclVO setFiles(CrsBbsAtclVO VO, FileListVO fileListVO) {
			VO.setAttachImages(fileListVO.getFiles("image"));
			VO.setAttachFiles(fileListVO.getFiles("file"));
			return VO;
		}
	}
	
	/** Mapper */
	@Resource(name="crsBbsAtclMapper")
	private CrsBbsAtclMapper 		crsBbsAtclMapper;

	
	@Resource(name="sysFileService")
	private SysFileService 		sysFileService;

	/**
	 * 과정 게시물의 목록을 반환한다.
	 * @param VO
	 * @return
	 */
	@Override
	public ProcessResultListVO<CrsBbsAtclVO> list(CrsBbsAtclVO VO)  throws Exception {
		ProcessResultListVO<CrsBbsAtclVO> resultList = new ProcessResultListVO<CrsBbsAtclVO>();
		try {
			List<CrsBbsAtclVO> returnList = crsBbsAtclMapper.list(VO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
	/**
	 * 과정 게시물의 페이징 목록을 반환한다.
	 * @param VO
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 */
	@Override
	public ProcessResultListVO<CrsBbsAtclVO> listPageing(CrsBbsAtclVO VO)  throws Exception {

		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(VO.getCurPage());
		paginationInfo.setRecordCountPerPage(VO.getListScale());
		paginationInfo.setPageSize(VO.getPageScale());
		
		VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		VO.setLastIndex(paginationInfo.getLastRecordIndex());
		ProcessResultListVO<CrsBbsAtclVO> resultList = new ProcessResultListVO<CrsBbsAtclVO>();
		try {
			// 전체 목록 수
			int totalCount = crsBbsAtclMapper.count(VO);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<CrsBbsAtclVO> returnList = crsBbsAtclMapper.listPageing(VO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		List<CrsBbsAtclVO> crsBbsAtclList = new ArrayList<CrsBbsAtclVO>();
		for(CrsBbsAtclVO sVO : resultList.getReturnList()) {
			sVO = sysFileService.getFile(sVO, new NestedFileHandler());
			this.atclUrlToView(sVO);
			crsBbsAtclList.add(sVO);
		}
		resultList.setReturnList(crsBbsAtclList);
		return resultList;
	}
	
	
	/**
	 * 게시판의 게시글글 단일 레코드를 조회하여 반환한다.
	 * @param CrsBbsAtclVO.atclSn
	 * @param 조회수 증가 여부(boolean) : default -> false
	 * @return ProcessResultVO<BbsAtclVO>
	 */
	@Override
	public ProcessResultVO<CrsBbsAtclVO> view(CrsBbsAtclVO VO)  throws Exception {
		return this.view(VO, false);
	}
	
	/**
	 * 게시판의 게시글글 단일 레코드를 조회하여 반환한다.
	 * @param CrsBbsAtclVO.atclSn
	 * @param 조회수 증가 여부(boolean) : default -> false
	 * @return ProcessResultVO<BbsAtclVO>
	 */
	@Override
	public ProcessResultVO<CrsBbsAtclVO> view(CrsBbsAtclVO VO, boolean hitsup)  throws Exception {
		// 조회수 증가
		if(hitsup) crsBbsAtclMapper.hitsup(VO);
		VO = crsBbsAtclMapper.select(VO);
		VO = sysFileService.getFile(VO, new NestedFileHandler());
		this.atclUrlToView(VO);
		return new ProcessResultVO<CrsBbsAtclVO>().setResultSuccess()
				.setReturnVO(VO);
	}
	
	/**
	 * 게시판 게시글을 등록하고 결과를 반환한다.
	 * @param CrsBbsAtclVO
	 * @return ProcessResultVO<BbsAtclVO>
	 */
	@Override
	public ProcessResultVO<CrsBbsAtclVO> add(CrsBbsAtclVO VO)  throws Exception {
		if(ValidationUtils.isNotNull(VO.getParAtclSn()) && VO.getParAtclSn() > 0) {
			// 상위글 정보를 조회해서 설정
			CrsBbsAtclVO parent = new CrsBbsAtclVO();
			parent.setAtclSn(VO.getParAtclSn());
			parent = crsBbsAtclMapper.select(parent);
			VO.setAtclLvl(parent.getAtclLvl() + 1);
		} else {
			VO.setParAtclSn(null);
			VO.setAtclLvl(0);
		}		
		// 첨부 이미지 URL 변경
		this.atclUrlToPersist(VO);
		
		VO.setAtclSn(crsBbsAtclMapper.selectKey());
		// 글저장 후 주키 값 받아오기.
		crsBbsAtclMapper.insert(VO);
		// 주키가 생성된 이후 첨부파일번호들을 바인딩해서 저장
		sysFileService.bindFile(VO, new NestedFileHandler());
		return new ProcessResultVO<CrsBbsAtclVO>().setReturnVO(VO).setResultSuccess(); 
	}
	
	/**
	 * 게시판 게시글을 수정하고 결과를 반환한다.
	 * @param CrsBbsAtclVO
	 * @return ProcessResultVO<BbsAtclVO>
	 */
	@Override
	public ProcessResultVO<CrsBbsAtclVO> edit(CrsBbsAtclVO VO) throws Exception {
		this.atclUrlToPersist(VO);	// URL 변환
		crsBbsAtclMapper.update(VO);
		sysFileService.bindFileUpdate(VO, new NestedFileHandler());
		return new ProcessResultVO<CrsBbsAtclVO>().setReturnVO(VO).setResultSuccess();
	}
	
	/**
	 * 게시판 게시글을 삭제하고 결과를 반환한다.
	 * @param articleVO 삭제 대상 고유 번호
	 * @return 삭제 처리 결과 VO
	 */
	@Override
	public ProcessResultVO<?> remove(CrsBbsAtclVO VO)  throws Exception {
		sysFileService.removeFile(VO, new NestedFileHandler()); // 파일정보 삭제..
		crsBbsAtclMapper.delete(VO);	// 게시글을 삭제
		return ProcessResultVO.success();
	}	
	
	private void atclUrlToPersist(CrsBbsAtclVO VO) {
		VO.setCts(StringUtil.replaceUrlToPersist(VO.getCts()));
	}

	private void atclUrlToView(CrsBbsAtclVO VO) {
		VO.setCts(StringUtil.replaceUrlToBrowser(VO.getCts()));
	}
}
