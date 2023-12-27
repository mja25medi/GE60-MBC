package egovframework.edutrack.modules.lecture.main.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentVO;
import egovframework.edutrack.modules.lecture.assignment.service.impl.AssignmentMapper;
import egovframework.edutrack.modules.lecture.exam.service.ExamVO;
import egovframework.edutrack.modules.lecture.exam.service.impl.ExamMapper;
import egovframework.edutrack.modules.lecture.forum.service.ForumVO;
import egovframework.edutrack.modules.lecture.forum.service.impl.ForumMapper;
import egovframework.edutrack.modules.lecture.main.service.MainLectureService;
import egovframework.edutrack.modules.lecture.main.service.MainLectureVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("mainLectureService")
public class MainLectureServiceImpl  extends EgovAbstractServiceImpl implements MainLectureService {

	@Resource(name="mainLectureMapper")
	private MainLectureMapper 	mainLectureMapper;
	
	@Resource(name="examMapper")
	private ExamMapper examMapper;
	
	@Resource(name="assignmentMapper")
	private AssignmentMapper assignmentMapper;
	
	@Resource(name="forumMapper")
	private ForumMapper forumMapper;

	/**
	 * 강의실의 정보를 가져온다.
	 * @param vo
	 * @return
	 */
	@Override
		public	ProcessResultVO<MainLectureVO> view(MainLectureVO vo) throws Exception{
		ProcessResultVO<MainLectureVO> resultVO = new ProcessResultVO<MainLectureVO>();
		try {
			MainLectureVO returnVO = mainLectureMapper.select(vo);
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
	 * 강의실의 정보를 가져온다. (과정)
	 * @param vo
	 * @return
	 */
	@Override
	public	ProcessResultVO<MainLectureVO> viewCourseSchedule(MainLectureVO vo) throws Exception{
		ProcessResultVO<MainLectureVO> resultVO = new ProcessResultVO<MainLectureVO>();
		try {
			MainLectureVO returnVO = mainLectureMapper.selectCourseSchedule(vo);
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
	 * 강의실의 정보를 가져온다. (회차)
	 * @param vo
	 * @return
	 */
	@Override
	public	ProcessResultVO<MainLectureVO> viewCreateCourseSchedule(MainLectureVO vo) throws Exception{
		ProcessResultVO<MainLectureVO> resultVO = new ProcessResultVO<MainLectureVO>();
		try {
			MainLectureVO returnVO = mainLectureMapper.selectCreateCourseSchedule(vo);
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
	 * 미채점 현황 리스트 과정,시험,과제 성적 리스트별 정보를 가져온다
	 * @param vo
	 * @return
	 * @throws Exception 
	 */
	@Override
		public	ProcessResultListVO<EgovMap> listUnScoreStatus(MainLectureVO vo) throws Exception{
			ProcessResultListVO<EgovMap> resultList = new ProcessResultListVO<EgovMap>();
			try {
				List<EgovMap> returnList =  mainLectureMapper.listUnScoreStatus(vo);
				
				if(returnList != null && returnList.size() > 0) {
					String forumListYn = "N";
					for (int i = 0; i < returnList.size(); i++) {
						EgovMap map = returnList.get(i);
						String crsCreCd = StringUtil.nvl(map.get("crsCreCd"),"");
						String examSnStr = StringUtil.nvl(map.get("examSn"),"");
						String[] examSnArray = {};
						List<EgovMap> examList = new ArrayList<EgovMap>();
						if(examSnStr != null && !"".equals(examSnStr)) {
							examSnArray = examSnStr.split("\\|");
							for (int j = 0; j < examSnArray.length; j++) {
								ExamVO examVo = new ExamVO();
								examVo.setExamSn(Integer.parseInt(examSnArray[j]));
								examVo.setCrsCreCd(crsCreCd);
								examVo.setSemiExamYn("N");
								EgovMap examMap = examMapper.selectExamStatus(examVo);
								examList.add(examMap);
							}
						}
						map.put("examList", examList);
						
						String asmtSnStr = StringUtil.nvl(map.get("asmtSn"),"");
						String[] asmtSnArray = {};
						List<EgovMap> asmtList = new ArrayList<EgovMap>();
						if(asmtSnStr != null  && !"".equals(asmtSnStr)) {
							asmtSnArray = asmtSnStr.split("\\|");
							for (int j = 0; j < asmtSnArray.length; j++) {
								AssignmentVO asmtVo = new AssignmentVO();
								asmtVo.setAsmtSn(Integer.parseInt(asmtSnArray[j]));
								asmtVo.setCrsCreCd(crsCreCd);
								EgovMap asmtMap = assignmentMapper.selectAsmtStatus(asmtVo);
								asmtList.add(asmtMap);
							}
						}
						map.put("asmtList", asmtList);
						
						
						String forumSnStr = StringUtil.nvl(map.get("forumSn"),"");
						String[] forumSnArray = {};
						List<EgovMap> forumList = new ArrayList<EgovMap>();
						if(forumSnStr != null && !"".equals(forumSnStr)) {
							forumSnArray = forumSnStr.split("\\|");
							forumListYn = "Y";
							for (int j = 0; j < forumSnArray.length; j++) {
								ForumVO forumVo = new ForumVO();
								forumVo.setForumSn(Integer.parseInt(forumSnArray[j]));
								forumVo.setCrsCreCd(crsCreCd);
								EgovMap forumMap = forumMapper.selectForumStatus(forumVo);
								forumList.add(forumMap);
							}
						}
						map.put("forumList", forumList);
						map.put("forumListYn", forumListYn);
						
						String semiExamSnStr = StringUtil.nvl(map.get("semiExamSn"),"");
						String[] semiExamSnArray = {};
						List<EgovMap> semiExamList = new ArrayList<EgovMap>();
						if(semiExamSnStr != null && !"".equals(semiExamSnStr)) {
							semiExamSnArray = semiExamSnStr.split("\\|");
							for (int j = 0; j < semiExamSnArray.length; j++) {
								ExamVO semiExamVo = new ExamVO();
								semiExamVo.setExamSn(Integer.parseInt(semiExamSnArray[j]));
								semiExamVo.setCrsCreCd(crsCreCd);
								semiExamVo.setSemiExamYn("Y");
								EgovMap semiExamMap = examMapper.selectExamStatus(semiExamVo);
								semiExamList.add(semiExamMap);
							}
						}
						map.put("semiExamList", semiExamList);
					}
				}
				resultList.setResult(1);
				resultList.setReturnList(returnList);
			} catch (Exception e){
				e.printStackTrace();
				resultList.setResult(-1);
				resultList.setMessage(e.getMessage());
			}

		return resultList;
		
	}

	@Override
	public	ProcessResultVO<MainLectureVO> createCourseSchedule(MainLectureVO vo) throws Exception{
		ProcessResultVO<MainLectureVO> resultVO = new ProcessResultVO<MainLectureVO>();
		try {
			MainLectureVO returnVO = mainLectureMapper.selectCreateCourseSchedule(vo);
			resultVO.setReturnVO(returnVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		return resultVO;
	}
}
