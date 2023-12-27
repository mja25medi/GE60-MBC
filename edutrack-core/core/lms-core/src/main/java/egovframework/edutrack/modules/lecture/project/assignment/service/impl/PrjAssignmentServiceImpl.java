package egovframework.edutrack.modules.lecture.project.assignment.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.modules.lecture.project.assignment.service.PrjAssignmentSendVO;
import egovframework.edutrack.modules.lecture.project.assignment.service.PrjAssignmentService;
import egovframework.edutrack.modules.lecture.project.assignment.service.PrjAssignmentVO;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("prjAssignmentService")
public class PrjAssignmentServiceImpl extends EgovAbstractServiceImpl implements PrjAssignmentService {

	private static final String	REPO_PRJT_ASMT		= "PRJT_ASMT";
	private static final String	REPO_PRJT_ASMT_SEND	= "PRJT_ASMT_SEND";

	private static final String	FILE_TYPE		= "file";
	
	private final class AsmtFileHandler implements FileHandler<PrjAssignmentVO> {

		@Override
		public String getPK(PrjAssignmentVO vo) {
			return vo.getCrsCreCd() + Constants.SEPERATER_DB + vo.getAsmtSn();
		}
		
		@Override
		public String getRepoCd() {
			return REPO_PRJT_ASMT;
		}
		
		@Override
		public List<SysFileVO> getFiles(PrjAssignmentVO vo) {
			return vo.getAttachFiles();
		}
		
		@Override
		public PrjAssignmentVO setFiles(PrjAssignmentVO vo, FileListVO fileListVO) {
			vo.setAttachFiles(fileListVO.getFiles(FILE_TYPE));
			return vo;
		}
	}
	
	private final class AsmtSendFileHandler implements FileHandler<PrjAssignmentSendVO> {

		@Override
		public String getPK(PrjAssignmentSendVO vo) {
			return vo.getCrsCreCd() + Constants.SEPERATER_DB + vo.getPrjtTeamSn();
		}
		
		@Override
		public String getRepoCd() {
			return REPO_PRJT_ASMT_SEND;
		}
		
		@Override
		public List<SysFileVO> getFiles(PrjAssignmentSendVO vo) {
			return vo.getAttachFiles();
		}
		
		@Override
		public PrjAssignmentSendVO setFiles(PrjAssignmentSendVO vo, FileListVO fileListVO) {
			vo.setAttachFiles(fileListVO.getFiles(FILE_TYPE));
			return vo;
		}
	}
	
	
	/** Mapper */
	@Resource(name="prjAssignmentMapper")
	private PrjAssignmentMapper prjAssignmentMapper;

	@Resource(name="prjAssignmentSendMapper")
	private PrjAssignmentSendMapper prjAssignmentSendMapper;
	
    @Resource(name="sysFileService")
    private SysFileService 			fileService;

	/**
	 * 팀프로젝트 과제 등록
	 * @author mhShin 
	 * @date 2013. 10. 30.
	 * @param vo
	 * @return ProcessResultListVO<PrjAssignmentVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<PrjAssignmentVO> add(PrjAssignmentVO vo) throws Exception {
		
		String asmtStartDttm = StringUtil.ReplaceAll(vo.getAsmtStartDttm(),".","")+vo.getAsmtStartHour()+vo.getAsmtStartMin()+"01";
		String asmtEndDttm = StringUtil.ReplaceAll(vo.getAsmtEndDttm(),".","")+vo.getAsmtEndHour()+vo.getAsmtEndMin()+"59";

		vo.setAsmtStartDttm(asmtStartDttm);
		vo.setAsmtEndDttm(asmtEndDttm);
		if(StringUtil.nvl(vo.getAsmtSn(),0) <= 0) {
			vo.setAsmtSn(prjAssignmentMapper.selectKey());
		}
		
		//주키값 받아오기
		prjAssignmentMapper.insert(vo); 
		
	
		// 파일 저장
		fileService.bindFile(vo, new AsmtFileHandler());
		
		return ProcessResultVO.success();
	}

	/**
	 * 팀프로젝트 과제 정보 조회
	 * @author mhShin 
	 * @date 2013. 10. 30.
	 * @param vo
	 * @return ProcessResultVO<PrjAssignmentVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<PrjAssignmentVO> view(PrjAssignmentVO vo) throws Exception {
		
		vo = prjAssignmentMapper.select(vo);
		// 파일 목록 조회
		vo = fileService.getFile(vo, new AsmtFileHandler());
		
		return new ProcessResultVO<PrjAssignmentVO>().setReturnVO(vo).setResultSuccess();
	}

	/**
	 * 팀프로젝트 과제 정보 수정
	 * @author mhShin 
	 * @date 2013. 10. 30.
	 * @param vo
	 * @return ProcessResultVO<PrjAssignmentVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<PrjAssignmentVO> edit(PrjAssignmentVO vo) throws Exception {
		
		
		ProcessResultVO<PrjAssignmentVO> resultVO = new ProcessResultVO<PrjAssignmentVO>();
		try {
			String asmtStartDttm = StringUtil.ReplaceAll(vo.getAsmtStartDttm(),".","")+vo.getAsmtStartHour()+vo.getAsmtStartMin()+"01";
			String asmtEndDttm = StringUtil.ReplaceAll(vo.getAsmtEndDttm(),".","")+vo.getAsmtEndHour()+vo.getAsmtEndMin()+"59";

			vo.setAsmtStartDttm(asmtStartDttm);
			vo.setAsmtEndDttm(asmtEndDttm);
			
			// 파일 저장
			fileService.bindFileUpdate(vo, new AsmtFileHandler());

			prjAssignmentMapper.update(vo);
			
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
	 * 팀프로젝트 과제 정보 삭제
	 * @author mhShin 
	 * @date 2013. 10. 30.
	 * @param vo
	 * @return ProcessResultVO<PrjAssignmentVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<PrjAssignmentVO> remove(PrjAssignmentVO vo) throws Exception {
		
		ProcessResultVO<PrjAssignmentVO> resultVO = new ProcessResultVO<PrjAssignmentVO>();
		try {
			//파일 삭제
			fileService.removeFile(vo, new AsmtFileHandler());
			prjAssignmentMapper.delete(vo);
			
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
	 * 팀프로젝트 과제 제출 정보 조회 (학습자용)
	 * @author mhShin 
	 * @date 2013. 12. 10.
	 * @param vo
	 * @return ProcessResultVO<PrjAssignmentSendVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<PrjAssignmentSendVO> viewSend(PrjAssignmentSendVO vo) throws Exception {
		vo = prjAssignmentSendMapper.select(vo);
		// 파일 목록 조회
		vo = fileService.getFile(vo, new AsmtSendFileHandler());
		
		return new ProcessResultVO<PrjAssignmentSendVO>().setReturnVO(vo).setResultSuccess();
	}
	
	/**
	 * 팀프로젝트 과제 제출 정보 등록 (학습자용)
	 * @author mhShin 
	 * @date 2013. 12. 10.
	 * @param vo
	 * @return ProcessResultVO<PrjAssignmentSendVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<PrjAssignmentSendVO> addSend(PrjAssignmentSendVO vo) throws Exception {
		
		ProcessResultVO<PrjAssignmentSendVO> resultVO = new ProcessResultVO<PrjAssignmentSendVO>();
		try {
			prjAssignmentSendMapper.insert(vo);
			//-- 첨부파일 저장
			fileService.bindFile(vo, new AsmtSendFileHandler());
			
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
	 * 팀프로젝트 과제 제츨 정보 수정 (학습자용)
	 * @author mhShin 
	 * @date 2013. 12. 10.
	 * @param vo
	 * @return ProcessResultVO<PrjAssignmentSendVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<PrjAssignmentSendVO> editSend(PrjAssignmentSendVO vo) throws Exception {
		
		ProcessResultVO<PrjAssignmentSendVO> resultVO = new ProcessResultVO<PrjAssignmentSendVO>();
		try {
			// 파일 저장
			fileService.bindFileUpdate(vo, new AsmtSendFileHandler());
			prjAssignmentSendMapper.update(vo);
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
	 * 팀프로젝트 과제 제출 정보 삭제(학습자용)
	 * @author mhShin 
	 * @date 2013. 12. 10.
	 * @param vo
	 * @return ProcessResultVO<PrjAssignmentSendVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<PrjAssignmentSendVO> removeSend(PrjAssignmentSendVO vo) throws Exception {
		//파일 삭제
		fileService.removeFile(vo, new AsmtSendFileHandler());
		prjAssignmentSendMapper.delete(vo);

		return ProcessResultVO.success();
	}


}

