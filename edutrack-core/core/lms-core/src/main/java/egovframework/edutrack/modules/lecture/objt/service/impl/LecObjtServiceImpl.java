package egovframework.edutrack.modules.lecture.objt.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.modules.lecture.objt.service.LecObjtCmntVO;
import egovframework.edutrack.modules.lecture.objt.service.LecObjtService;
import egovframework.edutrack.modules.lecture.objt.service.LecObjtVO;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Service("lecObjtService")
public class LecObjtServiceImpl implements LecObjtService {
	
	private static final String	REPO_PRJT		= "LEC_OBJT";
	
	private static final String	FILE_TYPE		= "file";
	
	private final class lecObjtFileHandler implements FileHandler<LecObjtVO> {

		@Override
		public String getPK(LecObjtVO vo) {
			return vo.getCrsCreCd() + Constants.SEPERATER_DB + vo.getObjtSn();
		}
		
		@Override
		public String getRepoCd() {
			return REPO_PRJT;
		}
		
		@Override
		public List<SysFileVO> getFiles(LecObjtVO vo) {
			return vo.getAttachFiles();
		}
		
		@Override
		public LecObjtVO setFiles(LecObjtVO vo, FileListVO fileListVO) {
			vo.setAttachFiles(fileListVO.getFiles(FILE_TYPE));
			return vo;
		}
	}
	
	@Resource(name="lecObjtMapper")
	private LecObjtMapper lecObjtMapper;
	
	@Resource(name="lecObjtCmntMapper")
	private LecObjtCmntMapper lecObjtCmntMapper;
	
	 @Resource(name="sysFileService")
	 private SysFileService 			fileService;
	

	@Override
	public int makeObjection(LecObjtVO vo) throws Exception {
		int sn = lecObjtMapper.selectKey();
		vo.setObjtSn(sn);
		
		fileService.bindFile(vo, new lecObjtFileHandler());
		
		return lecObjtMapper.insert(vo);
	}
	
	@Override
	public ProcessResultListVO<LecObjtVO> getObjectionList(LecObjtVO vo) throws Exception {
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(vo.getCurPage());
		paginationInfo.setRecordCountPerPage(vo.getListScale());
		paginationInfo.setPageSize(vo.getPageScale());
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());		
		ProcessResultListVO<LecObjtVO> resultList = new ProcessResultListVO<LecObjtVO>();
		
		int totalCount = lecObjtMapper.count(vo);
		paginationInfo.setTotalRecordCount(totalCount);
		
		resultList.setReturnList(lecObjtMapper.listPageingByCreCd(vo));
		resultList.setPageInfo(paginationInfo);
		
		List<LecObjtVO> lecObjtList = new ArrayList<LecObjtVO>();
		for(LecObjtVO lVO : resultList.getReturnList()) {
			lVO = fileService.getFile(lVO, new lecObjtFileHandler());
			this.atclUrlToView(lVO);
			lecObjtList.add(lVO);
		}
		resultList.setReturnList(lecObjtList);
		
		return resultList;
	}

	@Override
	public LecObjtVO getObjection(LecObjtVO vo) throws Exception {
		lecObjtMapper.hitsup(vo);
		LecObjtVO objection = lecObjtMapper.selectBySn(vo);
		objection = fileService.getFile(objection, new lecObjtFileHandler());
		this.atclUrlToView(vo);
		if(objection == null) {
			throw new Exception("해당하는 이의제기가 존재하지 않습니다.");
		}
		return objection;
	}

	@Override
	public LecObjtVO viewObjection(LecObjtVO vo) throws Exception {
		LecObjtVO objection = lecObjtMapper.select(vo);
		if(objection != null) {
			objection = fileService.getFile(objection, new lecObjtFileHandler());
		} 
		return objection;
	}
	
	@Override
	public int modifyObjection(LecObjtVO vo) throws Exception {
		fileService.bindFileUpdate(vo, new lecObjtFileHandler());
		return lecObjtMapper.update(vo);
	}

	@Override
	public int deleteObjection(LecObjtVO vo) throws Exception {
		fileService.removeFile(vo, new lecObjtFileHandler());
		return lecObjtMapper.delete(vo);
	}
	
	@Override
	public List<LecObjtCmntVO> getCmntList(LecObjtVO objt) {
		LecObjtCmntVO vo = new LecObjtCmntVO();
		vo.setObjtSn(objt.getObjtSn());
		return lecObjtCmntMapper.listByObjtSnVer5(vo);
	}
	
	@Override
	public int makeObjectionCmnt(LecObjtCmntVO vo) {
		int cmntSn = lecObjtCmntMapper.selectKey();
		vo.setObjtCmntSn(cmntSn);
		int result = lecObjtCmntMapper.insert(vo);
		
		if("TCH".equals(vo.getUserType())) {
			LecObjtVO objt = new LecObjtVO();
			objt.setObjtSn(vo.getObjtSn());
			lecObjtMapper.updateSts(objt);		
		}
			
		return result;
	}
	
	@Override
	public int deleteCmnt(LecObjtCmntVO vo) {
		return lecObjtCmntMapper.delete(vo);
	}
	
	private void atclUrlToPersist(LecObjtVO vo) {
		vo.setCts(StringUtil.replaceUrlToPersist(vo.getCts()));
	}

	private void atclUrlToView(LecObjtVO vo) {
		vo.setCts(StringUtil.replaceUrlToBrowser(vo.getCts()));
	}
}
