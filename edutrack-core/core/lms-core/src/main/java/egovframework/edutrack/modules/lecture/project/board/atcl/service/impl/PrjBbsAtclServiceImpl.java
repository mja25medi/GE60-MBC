package egovframework.edutrack.modules.lecture.project.board.atcl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.lecture.project.board.atcl.service.PrjBbsAtclService;
import egovframework.edutrack.modules.lecture.project.board.atcl.service.PrjBbsAtclVO;
import egovframework.edutrack.modules.lecture.project.board.cmnt.service.PrjBbsCmntVO;
import egovframework.edutrack.modules.lecture.project.board.cmnt.service.impl.PrjBbsCmntMapper;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Service("prjBbsAtclService")
public class PrjBbsAtclServiceImpl extends EgovAbstractServiceImpl implements PrjBbsAtclService {

	private final class PrjtAtclFileHandler implements FileHandler<PrjBbsAtclVO> {

	@Override
	public String getPK(PrjBbsAtclVO vo) {
		return vo.getCrsCreCd() + Constants.SEPERATER_DB + vo.getAtclSn();
	}
	
	@Override
	public String getRepoCd() {
		return "PRJT_ATCL";
	}
	
	@Override
	public List<SysFileVO> getFiles(PrjBbsAtclVO vo) {
		return vo.getAttachFiles();
	}
	
	@Override
	public PrjBbsAtclVO setFiles(PrjBbsAtclVO vo, FileListVO fileListVO) {
		vo.setAttachFiles(fileListVO.getFiles("file"));
		return vo;
	}
	}
	
	@Resource(name="prjBbsAtclMapper")
	private PrjBbsAtclMapper prjBbsAtclMapper;

	@Resource(name="prjBbsCmntMapper")
	private PrjBbsCmntMapper prjBbsCmntMapper;
	
	 @Resource(name="sysFileService")
	 private SysFileService 			fileService;

	
	/**
	 * 팀프로젝트 게시글 목록 조회
	 * @author mhShin 
	 * @date 2013. 10. 31.
	 * @param vo
	 * @return ProcessResultListVO<PrjBbsAtclVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultListVO<PrjBbsAtclVO> listPageing(PrjBbsAtclVO vo, int curPage, int listScale, int pageScale) throws Exception {
		ProcessResultListVO<PrjBbsAtclVO> resultList = new ProcessResultListVO<PrjBbsAtclVO>();
		
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(curPage);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());	
		

		try {
			// 전체 목록 수
			int totalCount = prjBbsAtclMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<PrjBbsAtclVO> returnList =  prjBbsAtclMapper.listPageing(vo);
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
	 * 팀프로젝트 게시글 정보 조회
	 * @author mhShin 
	 * @date 2013. 10. 31.
	 * @param vo
	 * @return ProcessResultListVO<PrjBbsAtclVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<PrjBbsAtclVO> view(PrjBbsAtclVO vo) throws Exception {
		return this.view(vo, false);
	}
	
	@Override
	public ProcessResultVO<PrjBbsAtclVO> view(PrjBbsAtclVO vo, boolean hitsup) throws Exception {
		// 조회수 증가
		if(hitsup) prjBbsAtclMapper.hitsup(vo);
		
		vo = prjBbsAtclMapper.select(vo);
		
		// 파일 목록 조회
		vo = fileService.getFile(vo, new PrjtAtclFileHandler());
		
		return new ProcessResultVO<PrjBbsAtclVO>().setReturnVO(vo).setResultSuccess();
	}

	/**
	 * 팀프로젝트 게시글 정보 등록
	 * @author mhShin 
	 * @date 2013. 10. 31.
	 * @param vo
	 * @return ProcessResultListVO<PrjBbsAtclVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<PrjBbsAtclVO> add(PrjBbsAtclVO vo) throws Exception {
		if(ValidationUtils.isNotNull(vo.getParAtclSn()) && vo.getParAtclSn() > 0) {
			// 상위글 정보를 조회해서 설정
			PrjBbsAtclVO parent = new PrjBbsAtclVO();
			parent.setParAtclSn(vo.getParAtclSn());
			parent = prjBbsAtclMapper.select(parent);
			vo.setAtclLvl(parent.getAtclLvl() + 1);
		} else {
			vo.setParAtclSn(null);
			vo.setAtclLvl(0);
		}	
		if(vo.getAtclSn() <= 0 || vo.getAtclSn() == null) {
			vo.setAtclSn(prjBbsAtclMapper.selectKey());
		}
		prjBbsAtclMapper.insert(vo);
		
		// 파일 저장
		fileService.bindFile(vo, new PrjtAtclFileHandler());
		
		return new ProcessResultVO<PrjBbsAtclVO>().setReturnVO(vo).setResultSuccess(); 
		
	}

	/**
	 * 팀프로젝트 게시글 정보 수정
	 * @author mhShin 
	 * @date 2013. 10. 31.
	 * @param vo
	 * @return ProcessResultListVO<PrjBbsAtclVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<PrjBbsAtclVO> edit(PrjBbsAtclVO vo) throws Exception {


		ProcessResultVO<PrjBbsAtclVO> resultVO = new ProcessResultVO<PrjBbsAtclVO>();
		try {
			// 파일 연결 업데이트
			fileService.bindFileUpdate(vo, new PrjtAtclFileHandler());
			
			prjBbsAtclMapper.update(vo);
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
	 * 팀프로젝트 게시글 정보 삭제
	 * @author mhShin 
	 * @date 2013. 10. 31.
	 * @param vo
	 * @return ProcessResultListVO<PrjBbsAtclVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<?> remove(PrjBbsAtclVO vo) throws Exception {
		
		
		ProcessResultVO<?> resultVO = new ProcessResultVO();
		try {
			//게시글 댓글 삭제
			PrjBbsCmntVO prjBbsCmntVO = new PrjBbsCmntVO();
			prjBbsCmntVO.setAtclSn(vo.getAtclSn());
			prjBbsCmntMapper.deleteAtclAll(prjBbsCmntVO);
			
			//파일 삭제
			fileService.removeFile(vo, new PrjtAtclFileHandler());
			//게시글 정보 삭제
			prjBbsAtclMapper.delete(vo);
			
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}
	
}

