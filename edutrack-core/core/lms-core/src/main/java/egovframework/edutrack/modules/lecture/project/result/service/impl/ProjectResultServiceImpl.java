package egovframework.edutrack.modules.lecture.project.result.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.modules.lecture.project.assignment.service.PrjAssignmentSendVO;
import egovframework.edutrack.modules.lecture.project.result.service.ProjectResultService;
import egovframework.edutrack.modules.lecture.project.result.service.ProjectResultVO;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("projectResultService")
public class ProjectResultServiceImpl extends EgovAbstractServiceImpl implements ProjectResultService {

	private static final String	REPO_ASMT_SEND	= "ASMT_SEND";
	private static final String	FILE_TYPE		= "file";
	
	private final class AsmtSendFileHandler implements FileHandler<PrjAssignmentSendVO> {

		@Override
		public String getPK(PrjAssignmentSendVO vo) {
			return vo.getCrsCreCd()
					+ Constants.SEPERATER_DB + vo.getAsmtSn();
		}
		
		@Override
		public String getRepoCd() {
			return REPO_ASMT_SEND;
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
	
	@Resource(name="projectResultMapper")
	private ProjectResultMapper projectResultMapper;
	
	 @Resource(name="sysFileService")
	 private SysFileService 			fileService;
	
	/**
	 * 평가 관리 목록 조회
	 * @throws Exception 
	 */
	@Override
	public ProcessResultListVO<ProjectResultVO> list(ProjectResultVO vo) throws Exception {
		ProcessResultListVO<ProjectResultVO> resultList = new ProcessResultListVO<ProjectResultVO>();
		try {
			List<ProjectResultVO> returnList =  projectResultMapper.list(vo);
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
	 * 팀 점수저장
	 * @throws Exception 
	 */
	public ProcessResultVO<ProjectResultVO> editScoreAll(ProjectResultVO vo, String strTeamScore, String strPrjtTeamSn) throws Exception{
		
		String[] strTeamScoreArray = StringUtil.split(strTeamScore,"|");
		String[] strPrjtTeamSnArray = StringUtil.split(strPrjtTeamSn,"|");
		
		for(int i = 0; i < strPrjtTeamSnArray.length; i++) {
			vo.setPrjtTeamSn(Integer.parseInt(strPrjtTeamSnArray[i]));
			vo.setTeamScore(Float.parseFloat(strTeamScoreArray[i]));
			
			projectResultMapper.updateScoreAll(vo);
			projectResultMapper.updateMbrScoreAll(vo);
		}
		
		return ProcessResultVO.success();
	}

	/**
	 *  팀원 보기
	 * @throws Exception 
	 */
	public ProcessResultVO<ProjectResultVO> view(ProjectResultVO vo) throws Exception{
		ProcessResultVO<ProjectResultVO> resultVO = new ProcessResultVO<ProjectResultVO>();
		try {
			ProjectResultVO returnVO = projectResultMapper.select(vo);
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
	 * 팀원 목록
	 * @throws Exception 
	 */
	public ProcessResultListVO<ProjectResultVO> teamList(ProjectResultVO vo) throws Exception{
		ProcessResultListVO<ProjectResultVO> resultList = new ProcessResultListVO<ProjectResultVO>();
		try {
			List<ProjectResultVO> returnList =  projectResultMapper.teamList(vo);
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
	 * 팀 회원 점수저장
	 * @throws Exception 
	 */
	public ProcessResultVO<ProjectResultVO> editMbScoreAll(ProjectResultVO vo, String strMbrScore, String strPrjtMbrSn) throws Exception{
		
		String[] strMbrScoreArray = StringUtil.split(strMbrScore,"|");
		String[] strPrjtMbrSnArray = StringUtil.split(strPrjtMbrSn,"|");
		
		for(int i = 0; i < strPrjtMbrSnArray.length; i++) {
			vo.setPrjtMbrSn(Integer.parseInt(strPrjtMbrSnArray[i]));
			vo.setMbrScore(Float.parseFloat(strMbrScoreArray[i]));
			
			projectResultMapper.editMbScoreAll(vo);
		}
		
		return ProcessResultVO.success();
	}
	
	/**
	 *  과제 정보
	 * @throws Exception 
	 */
	public ProcessResultVO<ProjectResultVO> siInfoView(ProjectResultVO vo) throws Exception{
		ProcessResultVO<ProjectResultVO> resultVO = new ProcessResultVO<ProjectResultVO>();
		try {
			ProjectResultVO returnVO = projectResultMapper.siInfoSelect(vo);
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
	 *  제출 정보
	 * @throws Exception 
	 */
	public ProcessResultVO<PrjAssignmentSendVO> submitInfoView(PrjAssignmentSendVO vo) throws Exception{
		
		vo = projectResultMapper.submitInfoSelect(vo);

		vo = fileService.getFile(vo, new AsmtSendFileHandler());
		
		return new ProcessResultVO<PrjAssignmentSendVO>().setReturnVO(vo).setResultSuccess();

	}
	
	
}




