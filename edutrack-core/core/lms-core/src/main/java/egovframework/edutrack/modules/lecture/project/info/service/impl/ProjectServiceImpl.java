package egovframework.edutrack.modules.lecture.project.info.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.modules.lecture.project.assignment.service.PrjAssignmentVO;
import egovframework.edutrack.modules.lecture.project.assignment.service.impl.PrjAssignmentMapper;
import egovframework.edutrack.modules.lecture.project.board.atcl.service.PrjBbsAtclVO;
import egovframework.edutrack.modules.lecture.project.board.atcl.service.impl.PrjBbsAtclMapper;
import egovframework.edutrack.modules.lecture.project.board.cmnt.service.PrjBbsCmntVO;
import egovframework.edutrack.modules.lecture.project.board.cmnt.service.impl.PrjBbsCmntMapper;
import egovframework.edutrack.modules.lecture.project.board.info.service.PrjBbsVO;
import egovframework.edutrack.modules.lecture.project.board.info.service.impl.PrjBbsMapper;
import egovframework.edutrack.modules.lecture.project.info.service.ProjectService;
import egovframework.edutrack.modules.lecture.project.info.service.ProjectVO;
import egovframework.edutrack.modules.lecture.project.member.service.PrjMemberVO;
import egovframework.edutrack.modules.lecture.project.member.service.PrjTeamVO;
import egovframework.edutrack.modules.lecture.project.member.service.impl.PrjMemberMapper;
import egovframework.edutrack.modules.lecture.project.member.service.impl.PrjTeamMapper;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("projectService")
public class ProjectServiceImpl extends EgovAbstractServiceImpl implements ProjectService {
	
	private static final String	REPO_PRJT		= "PRJT";
	
	private static final String	FILE_TYPE		= "file";
	
	private final class PrjtFileHandler implements FileHandler<ProjectVO> {

		@Override
		public String getPK(ProjectVO vo) {
			return vo.getCrsCreCd() + Constants.SEPERATER_DB + vo.getPrjtSn();
		}
		
		@Override
		public String getRepoCd() {
			return REPO_PRJT;
		}
		
		@Override
		public List<SysFileVO> getFiles(ProjectVO vo) {
			return vo.getAttachFiles();
		}
		
		@Override
		public ProjectVO setFiles(ProjectVO vo, FileListVO fileListVO) {
			vo.setAttachFiles(fileListVO.getFiles(FILE_TYPE));
			return vo;
		}
	}

	
	@Resource(name="projectMapper")
	private ProjectMapper projectMapper;

	@Resource(name="prjTeamMapper")
	private PrjTeamMapper prjTeamMapper;
	
	@Resource(name="prjMemberMapper")
	private PrjMemberMapper prjMemberMapper;

	@Resource(name="prjBbsMapper")
	private PrjBbsMapper prjBbsMapper;

	@Resource(name="prjBbsAtclMapper")
	private PrjBbsAtclMapper prjBbsAtclMapper;

	@Resource(name="prjBbsCmntMapper")
	@Qualifier("prjBbsCmntMapper")
	private PrjBbsCmntMapper prjBbsCmntMapper;
	
	@Resource(name="prjAssignmentMapper")
	private PrjAssignmentMapper prjAssignmentMapper;
	
	 @Resource(name="sysFileService")
	 private SysFileService 			fileService;
	
	/**
	 * 팀프로젝트 목록 조회
	 * @author mhShin 
	 * @date 2013. 10. 21.
	 * @param vo
	 * @return ProcessResultListVO<ProjectVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultListVO<ProjectVO> list(ProjectVO vo) throws Exception {
		ProcessResultListVO<ProjectVO> resultList = new ProcessResultListVO<ProjectVO>();
		try {
			List<ProjectVO> returnList =  projectMapper.list(vo);
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
	 * 팀프로젝트 목록 조회 (학습자용)
	 * @author mhShin 
	 * @date 2013. 12. 05.
	 * @param vo
	 * @return ProcessResultListVO<ProjectVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultListVO<ProjectVO> listPrjStudent(ProjectVO vo) throws Exception {
		ProcessResultListVO<ProjectVO> resultList = new ProcessResultListVO<ProjectVO>();
		try {
			List<ProjectVO> returnList =  projectMapper.listPrjStudent(vo);
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
	 * 팀프로젝트 정보 등록
	 * @author mhShin 
	 * @date 2013. 10. 21.
	 * @param vo
	 * @return ProcessResultListVO<ProjectVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<ProjectVO> add(ProjectVO vo) throws Exception {
		//날짜 포맷 변경
		String prjtStartDttm = StringUtil.ReplaceAll(vo.getPrjtStartDttm(),".","")+"000001";
		String prjtEndDttm = StringUtil.ReplaceAll(vo.getPrjtEndDttm(),".","")+"235959";
		
		vo.setPrjtStartDttm(prjtStartDttm);
		vo.setPrjtEndDttm(prjtEndDttm);
		//---- 신규 코드 세팅
		vo.setPrjtSn(projectMapper.selectKey());
		
		projectMapper.insert(vo);

		fileService.bindFile(vo, new PrjtFileHandler());
		
		return ProcessResultVO.success();
	}

	/**
	 * 팀프로젝트 정보 수정
	 * @author mhShin 
	 * @date 2013. 10. 21.
	 * @param vo
	 * @return ProcessResultListVO<ProjectVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<ProjectVO> edit(ProjectVO vo) throws Exception {
		
		ProcessResultVO<ProjectVO> resultVO = new ProcessResultVO<ProjectVO>();
		try {
			//날짜 포맷 변경
			String prjtStartDttm = StringUtil.ReplaceAll(vo.getPrjtStartDttm(),".","")+"000001";
			String prjtEndDttm = StringUtil.ReplaceAll(vo.getPrjtEndDttm(),".","")+"235959";
			
			vo.setPrjtStartDttm(prjtStartDttm);
			vo.setPrjtEndDttm(prjtEndDttm);
			
			// 파일 연결 업데이트
			fileService.bindFileUpdate(vo, new PrjtFileHandler());
			
			projectMapper.update(vo);
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
	 * 팀프로젝트 정보 조회
	 * @author mhShin 
	 * @date 2013. 10. 21.
	 * @param vo
	 * @return ProcessResultVO<ProjectVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<ProjectVO> view(ProjectVO vo) throws Exception {

		vo = projectMapper.select(vo);
		// 파일 목록 조회
		vo = fileService.getFile(vo, new PrjtFileHandler());
				
		return new ProcessResultVO<ProjectVO>().setReturnVO(vo).setResultSuccess();

	}
	
	/**
	 * 팀프로젝트 정보 조회 (학습자용)
	 * @author mhShin 
	 * @date 2013. 12. 05.
	 * @param vo
	 * @return ProcessResultVO<ProjectVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<ProjectVO> viewPrjStudent(ProjectVO vo) throws Exception {

		vo = projectMapper.selectPrjStudent(vo);
		// 파일 목록 조회
		vo = fileService.getFile(vo, new PrjtFileHandler());
				
		return new ProcessResultVO<ProjectVO>().setReturnVO(vo).setResultSuccess();

	}
	
	/**
	 * 팀프로젝트 정보 삭제
	 * @author mhShin 
	 * @date 2013. 10. 21.
	 * @param vo
	 * @return ProcessResultVO<ProjectVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<?> remove(ProjectVO vo) throws Exception {
		
		ProcessResultVO<?> resultVO = new ProcessResultVO();
		try {
			//프로젝트 팀 회원 전체 삭제 
			PrjMemberVO prjMemberVO = new PrjMemberVO();
			prjMemberVO.setPrjtSn(vo.getPrjtSn());
			prjMemberMapper.deletePrjAll(prjMemberVO);
			//프로젝트 팀 전체 삭제
			PrjTeamVO prjTeamVO = new PrjTeamVO();
			prjTeamVO.setPrjtSn(vo.getPrjtSn());
			prjTeamMapper.deleteAll(prjTeamVO);
			//프로젝트 게시판 댓글 전체 삭제
			PrjBbsCmntVO prjBbsCmntVO = new PrjBbsCmntVO();
			prjBbsCmntVO.setPrjtSn(vo.getPrjtSn());
			prjBbsCmntMapper.deletePrjAll(prjBbsCmntVO);
			//프로젝트 게시판 게시글 전체 삭제
			PrjBbsAtclVO prjBbsAtclVO = new PrjBbsAtclVO();
			prjBbsAtclVO.setPrjtSn(vo.getPrjtSn());
			prjBbsAtclMapper.deleteAll(prjBbsAtclVO);
			//프로젝트 게시판 전체삭제
			PrjBbsVO prjBbsVO = new PrjBbsVO();
			prjBbsVO.setPrjtSn(vo.getPrjtSn());
			prjBbsMapper.deleteAll(prjBbsVO);
			//프로젝트 과제 전체 삭제
			PrjAssignmentVO prjAssignmentVO = new PrjAssignmentVO();
			prjAssignmentVO.setPrjtSn(vo.getPrjtSn());
			prjAssignmentMapper.deletePrjAll(prjAssignmentVO);
			
			//프로젝트 첨부파일 삭제 
			fileService.removeFile(vo, new PrjtFileHandler());
			
			projectMapper.delete(vo);

			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		
		return resultVO;
	}
	
}

