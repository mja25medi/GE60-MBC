package egovframework.edutrack.modules.lecture.assignment.service.impl;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.annotation.HrdApiScore;
import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.FileUtil;
import egovframework.edutrack.comm.util.web.POIExcelUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.course.assignmentbank.service.AssignmentQbankQuestionVO;
import egovframework.edutrack.modules.course.assignmentbank.service.impl.AssignmentQbankQuestionMapper;
import egovframework.edutrack.modules.course.createcoursesubject.service.CreateOnlineSubjectVO;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentSendVO;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentService;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentSubConstVO;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentSubVO;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentVO;
import egovframework.edutrack.modules.student.result.service.EduResultService;
import egovframework.edutrack.modules.student.result.service.EduResultVO;
import egovframework.edutrack.modules.student.result.service.impl.EduResultMapper;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;


@Service("assignmentService")
public class AssignmentServiceImpl extends EgovAbstractServiceImpl implements AssignmentService  {

	private static final String	REPO_ASMT		= "ASMT";
	private static final String	REPO_ASMT_SEND	= "ASMT_SEND";
	private static final String	REPO_ASMT_SUB	= "ASMT_SUB";

	private static final String	IMAGE_TYPE		= "image";
	private static final String	FILE_TYPE		= "file";

	private final class AsmtSubFileHandler
			implements FileHandler<AssignmentSubVO> {

		@Override
		public String getPK(AssignmentSubVO vo) {
			//return vo.getCrsCreCd() + Constants.SEPERATER_DB + vo.getAsmtSn()+ Constants.SEPERATER_DB + vo.getAsmtSubSn();
			return Integer.toString(vo.getAsmtSubSn());
		}

		@Override
		public String getRepoCd() {
			return REPO_ASMT_SUB;
		}

		@Override
		public List<SysFileVO> getFiles(AssignmentSubVO vo) {
			List<SysFileVO> listFile = vo.getAttachFiles();
			listFile.addAll(vo.getAttachImages());
			return listFile;
		}

		@Override
		public AssignmentSubVO setFiles(AssignmentSubVO vo, FileListVO fileListVO) {
			vo.setAttachFiles(fileListVO.getFiles(FILE_TYPE));
			vo.setAttachImages(fileListVO.getFiles(IMAGE_TYPE));
			return vo;
		}
	}

	private final class AsmtSendFileHandler
			implements FileHandler<AssignmentSendVO> {

		@Override
		public String getPK(AssignmentSendVO vo) {
			return vo.getStdNo() + Constants.SEPERATER_DB + vo.getCrsCreCd()
					+ Constants.SEPERATER_DB + vo.getAsmtSn();
		}

		@Override
		public String getRepoCd() {
			return REPO_ASMT_SEND;
		}

		@Override
		public List<SysFileVO> getFiles(AssignmentSendVO vo) {
			return vo.getAttachFiles();
		}

		@Override
		public AssignmentSendVO setFiles(AssignmentSendVO vo, FileListVO fileListVO) {
			vo.setAttachFiles(fileListVO.getFiles(FILE_TYPE));
			return vo;
		}
	}

	private final class AsmtFileHandler
			implements FileHandler<AssignmentVO> {

		@Override
		public String getPK(AssignmentVO vo) {
			//return vo.getCrsCreCd() + Constants.SEPERATER_DB + vo.getAsmtSn();
			return vo.getAsmtSn()+"";
		}

		@Override
		public String getRepoCd() {
			return REPO_ASMT;
		}

		@Override
		public List<SysFileVO> getFiles(AssignmentVO vo) {
			return vo.getAttachFiles();
		}

		@Override
		public AssignmentVO setFiles(AssignmentVO vo, FileListVO fileListVO) {
			vo.setAttachFiles(fileListVO.getFiles(FILE_TYPE));
			return vo;
		}
	}


	/** Mapper */
	@Resource(name="assignmentMapper")
	private AssignmentMapper assignmentMapper;

	@Resource(name="assignmentSubMapper")
	private AssignmentSubMapper assignmentSubMapper;

	@Resource(name="assignmentSendMapper")
	private AssignmentSendMapper assignmentSendMapper;

	@Resource(name="assignmentQbankQuestionMapper")
	private AssignmentQbankQuestionMapper assignmentQbankQuestionMapper;

	@Resource(name="eduResultMapper")
	private EduResultMapper eduResultMapper;
	
	@Resource(name="sysFileService")
	private SysFileService sysFileService;
	
	@Resource(name="assignmentService")
	private AssignmentService assignmentService;
	
	@Resource(name="eduResultService")
	private EduResultService eduResultService;
	
	@Resource(name="assignmentSubConstMapper")
	private AssignmentSubConstMapper assignmentSubConstMapper;


	/**
	 * 과제 정보 목록 조회
	 */
	public	ProcessResultListVO<AssignmentVO> listAssignment(AssignmentVO iAssignmentVO) throws Exception {
		ProcessResultListVO<AssignmentVO> resultList = new ProcessResultListVO<AssignmentVO>();
		try {
			List<AssignmentVO> returnList = assignmentMapper.list(iAssignmentVO);
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
	 * 과제 정보 목록 조회	(학습자용)
	 */
	public	ProcessResultListVO<AssignmentVO> listAssignmentStd(AssignmentVO iAssignmentVO) throws Exception {
		ProcessResultListVO<AssignmentVO> resultList = new ProcessResultListVO<AssignmentVO>();
		try {
			List<AssignmentVO> returnList = assignmentMapper.listStd(iAssignmentVO);
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
	 * 과제 정보 조회
	 */
	public	ProcessResultVO<AssignmentVO> viewAssignment(AssignmentVO assignmentVO) throws Exception {
		assignmentVO = assignmentMapper.select(assignmentVO);

		// 파일 목록 조회
		assignmentVO = sysFileService.getFile(assignmentVO, new AsmtFileHandler());

		return new ProcessResultVO<AssignmentVO>().setReturnVO(assignmentVO).setResultSuccess();
	}

	/**
	 * 과제 정보 등록
	 */
	public	ProcessResultVO<AssignmentVO> addAssignment(AssignmentVO iAssignmentVO) throws Exception {

		ProcessResultVO<AssignmentVO> resultVO = new ProcessResultVO<AssignmentVO>();
		try {
			String asmtSvcCd = iAssignmentVO.getAsmtSvcCd();
			if(!asmtSvcCd.equals("CODE")) {
				if(iAssignmentVO.getAsmtStartHour().length() ==1){
					iAssignmentVO.setAsmtStartHour("0"+iAssignmentVO.getAsmtStartHour());
				}
				if(iAssignmentVO.getAsmtStartMin().length() ==1){
					iAssignmentVO.setAsmtStartMin("0"+iAssignmentVO.getAsmtStartMin());
				}
	
				if(iAssignmentVO.getAsmtEndHour().length() ==1){
					iAssignmentVO.setAsmtEndHour("0"+iAssignmentVO.getAsmtEndHour());
				}
				if(iAssignmentVO.getAsmtEndMin().length() ==1){
					iAssignmentVO.setAsmtEndMin("0"+iAssignmentVO.getAsmtEndMin());
				}
	
				if(iAssignmentVO.getExtSendHour().length() ==1){
					iAssignmentVO.setExtSendHour("0"+iAssignmentVO.getExtSendHour());
				}
				if(iAssignmentVO.getExtSendMin().length() ==1){
					iAssignmentVO.setExtSendMin("0"+iAssignmentVO.getExtSendMin());
				}
	
	
				//-- 시간 관련 처리
				String asmtStartDttm = StringUtil.dateNumber(iAssignmentVO.getAsmtStartDttm())+iAssignmentVO.getAsmtStartHour()+iAssignmentVO.getAsmtStartMin()+"01";
				String asmtEndDttm = StringUtil.dateNumber(iAssignmentVO.getAsmtEndDttm())+iAssignmentVO.getAsmtEndHour()+iAssignmentVO.getAsmtEndMin()+"59";
				String extSendDttm = StringUtil.dateNumber(iAssignmentVO.getExtSendDttm())+iAssignmentVO.getExtSendHour()+iAssignmentVO.getExtSendMin()+"59";
	
				iAssignmentVO.setAsmtStartDttm(asmtStartDttm);
				iAssignmentVO.setAsmtEndDttm(asmtEndDttm);
				iAssignmentVO.setExtSendDttm(extSendDttm);
			}
			if(StringUtil.nvl(iAssignmentVO.getAsmtSn(),0) <= 0) {
				iAssignmentVO.setAsmtSn(assignmentMapper.selectKey().getAsmtSn());
			}
			assignmentMapper.insert(iAssignmentVO);
			
			// 파일 저장
			sysFileService.bindFile(iAssignmentVO, new AsmtFileHandler());
			resultVO.setReturnVO(iAssignmentVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}

	/**
	 * 과제 등록 완료
	 */
	public	ProcessResultVO<AssignmentVO> addRegistAssignment(AssignmentVO iAssignmentVO) throws Exception {
		
		String asmtSvcCd = iAssignmentVO.getAsmtSvcCd();
		if(!asmtSvcCd.equals("CODE")) {
			if(iAssignmentVO.getAsmtStartHour().length() ==1){
				iAssignmentVO.setAsmtStartHour("0"+iAssignmentVO.getAsmtStartHour());
			}
			if(iAssignmentVO.getAsmtStartMin().length() ==1){
				iAssignmentVO.setAsmtStartMin("0"+iAssignmentVO.getAsmtStartMin());
			}
	
			if(iAssignmentVO.getAsmtEndHour().length() ==1){
				iAssignmentVO.setAsmtEndHour("0"+iAssignmentVO.getAsmtEndHour());
			}
			if(iAssignmentVO.getAsmtEndMin().length() ==1){
				iAssignmentVO.setAsmtEndMin("0"+iAssignmentVO.getAsmtEndMin());
			}
	
			if(iAssignmentVO.getExtSendHour().length() ==1){
				iAssignmentVO.setExtSendHour("0"+iAssignmentVO.getExtSendHour());
			}
			if(iAssignmentVO.getExtSendMin().length() ==1){
				iAssignmentVO.setExtSendMin("0"+iAssignmentVO.getExtSendMin());
			}
	
	
			//-- 시간 관련 처리
			String asmtStartDttm = StringUtil.dateNumber(iAssignmentVO.getAsmtStartDttm())+iAssignmentVO.getAsmtStartHour()+iAssignmentVO.getAsmtStartMin()+"01";
			String asmtEndDttm = StringUtil.dateNumber(iAssignmentVO.getAsmtEndDttm())+iAssignmentVO.getAsmtEndHour()+iAssignmentVO.getAsmtEndMin()+"59";
			String extSendDttm = StringUtil.dateNumber(iAssignmentVO.getExtSendDttm())+iAssignmentVO.getExtSendHour()+iAssignmentVO.getExtSendMin()+"59";
	
			iAssignmentVO.setAsmtStartDttm(asmtStartDttm);
			iAssignmentVO.setAsmtEndDttm(asmtEndDttm);
			iAssignmentVO.setExtSendDttm(extSendDttm);

		//iAssignmentVO.setRegYn("Y");
		}
		assignmentMapper.update(iAssignmentVO);

		return ProcessResultVO.success();
	}

	/**
	 * 과제 정보 수정
	 */
	public	ProcessResultVO<AssignmentVO> editAssignment(AssignmentVO iAssignmentVO) throws Exception {

		
		ProcessResultVO<AssignmentVO> resultVO = new ProcessResultVO<AssignmentVO>();
		try {
			String asmtSvcCd = iAssignmentVO.getAsmtSvcCd();
			if(!asmtSvcCd.equals("CODE")) {
			if(iAssignmentVO.getAsmtStartHour().length() ==1){
				iAssignmentVO.setAsmtStartHour("0"+iAssignmentVO.getAsmtStartHour());
			}
			if(iAssignmentVO.getAsmtStartMin().length() ==1){
				iAssignmentVO.setAsmtStartMin("0"+iAssignmentVO.getAsmtStartMin());
			}

			if(iAssignmentVO.getAsmtEndHour().length() ==1){
				iAssignmentVO.setAsmtEndHour("0"+iAssignmentVO.getAsmtEndHour());
			}
			if(iAssignmentVO.getAsmtEndMin().length() ==1){
				iAssignmentVO.setAsmtEndMin("0"+iAssignmentVO.getAsmtEndMin());
			}

			if(iAssignmentVO.getExtSendHour().length() ==1){
				iAssignmentVO.setExtSendHour("0"+iAssignmentVO.getExtSendHour());
			}
			if(iAssignmentVO.getExtSendMin().length() ==1){
				iAssignmentVO.setExtSendMin("0"+iAssignmentVO.getExtSendMin());
			}


			//-- 시간 관련 처리
			String asmtStartDttm = StringUtil.dateNumber(iAssignmentVO.getAsmtStartDttm())+iAssignmentVO.getAsmtStartHour()+iAssignmentVO.getAsmtStartMin()+"01";
			String asmtEndDttm = StringUtil.dateNumber(iAssignmentVO.getAsmtEndDttm())+iAssignmentVO.getAsmtEndHour()+iAssignmentVO.getAsmtEndMin()+"59";
			String extSendDttm = StringUtil.dateNumber(iAssignmentVO.getExtSendDttm())+iAssignmentVO.getExtSendHour()+iAssignmentVO.getExtSendMin()+"59";

			iAssignmentVO.setAsmtStartDttm(asmtStartDttm);
			iAssignmentVO.setAsmtEndDttm(asmtEndDttm);
			iAssignmentVO.setExtSendDttm(extSendDttm);
			}

			// 파일 연결 업데이트
			sysFileService.bindFileUpdate(iAssignmentVO, new AsmtFileHandler());
			
			assignmentMapper.update(iAssignmentVO);
			
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		
		
		
		return resultVO;
	}

	/**
	 * 과제 정보 삭제
	 */
	public	ProcessResultVO<AssignmentVO> deleteAssignment(AssignmentVO iAssignmentVO) throws Exception {

		AssignmentSendVO assignmentSendVO = new AssignmentSendVO();
		List<AssignmentSendVO> stuArray = assignmentSendMapper.list(iAssignmentVO);

		//제출 정보 삭제
		for(int i=0; i < stuArray.size(); i++) {
			assignmentSendVO = (AssignmentSendVO)stuArray.get(i);
			assignmentSendVO.setAsmtSn(iAssignmentVO.getAsmtSn());
			sysFileService.removeFile(assignmentSendVO, new AsmtSendFileHandler());
			assignmentSendMapper.delete(assignmentSendVO);
			assignmentSendMapper.deleteDtl(assignmentSendVO);
		}

		//2015.11.17 김현욱 수정 과제정보 삭제시 sub가 삭제되지 않아 삭제가 안되어 수정함.(redmine : KNOTZ_NG_208)
		//과제 문제 정보 삭제
		//ProcessResultListVO<AssignmentSubVO> resultList = assignmentSubMapper.list(iAssignmentVO);
		
		ProcessResultListVO<AssignmentSubVO> resultListVO = new ProcessResultListVO<AssignmentSubVO>();
		try {
			List<AssignmentSubVO> returnList = assignmentSubMapper.list(iAssignmentVO);
			resultListVO.setReturnList(returnList);
			resultListVO.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultListVO.setResult(-1);
			resultListVO.setMessage(e.getMessage());
		}
		
		for(int i=0; i<resultListVO.getReturnList().size(); i++){
			AssignmentSubVO iAssignmentSubVO = resultListVO.getReturnList().get(i);
			sysFileService.removeFile(iAssignmentSubVO, new AsmtSubFileHandler());
		}
		assignmentSubMapper.deleteAll(iAssignmentVO);

		//과제 정보 삭제
		sysFileService.removeFile(iAssignmentVO, new AsmtFileHandler());
		assignmentMapper.delete(iAssignmentVO);

		return ProcessResultVO.success();
	}

	/**
	 * 과제 응시 결과 정보 조회
	 */
	public	ProcessResultVO<AssignmentSendVO> viewSend(AssignmentSendVO assignmentSendVO) throws Exception {
		assignmentSendVO = assignmentSendMapper.select(assignmentSendVO);
		if(assignmentSendVO != null) {
			assignmentSendVO = sysFileService.getFile(assignmentSendVO, new AsmtSendFileHandler());
		}
		return new ProcessResultVO<AssignmentSendVO>().setReturnVO(assignmentSendVO).setResultSuccess();
	}
	
	/**
	 * 과제 제출하기 위해 화면 들어갈 때  (학습자)
	 * @param iAssignmentSendVO
	 * @return
	 */
	//@HrdApiScore(category = Category.ASMT, saveType = SaveType.START)
	@HrdApiScore
	public	ProcessResultVO<AssignmentSendVO> addEnterSend(AssignmentSendVO iAssignmentSendVO, String addYn)  throws Exception {
		
		ProcessResultVO<AssignmentSendVO> resultVO = new ProcessResultVO<AssignmentSendVO>();
		iAssignmentSendVO.setScoreSaveType("START");
		iAssignmentSendVO.setScoreCategory("ASMT");
		//화면 들어갈 때마다 성적이력로그(과제) 쌓아야 함, insert X
		if("Y".equals(StringUtil.nvl(addYn))) {
			try {
				//-- 과제 후보 정보 저장후 주키를 받아 온다.
				assignmentSendMapper.insert(iAssignmentSendVO);
				assignmentSendMapper.insertDetail(iAssignmentSendVO);
				resultVO.setReturnVO(iAssignmentSendVO);
				resultVO.setResultSuccess();
			} catch (Exception e){
				e.printStackTrace();
				resultVO.setResultFailed();
				resultVO.setMessage(e.getMessage());
			}
		}
		return resultVO;
	}
	
	/**
	 * 과제 제출
	 * @param iAssignmentSendVO
	 * @return
	 */
	public	ProcessResultVO<AssignmentSendVO> addSend(AssignmentSendVO iAssignmentSendVO)  throws Exception {
		
		ProcessResultVO<AssignmentSendVO> resultVO = new ProcessResultVO<AssignmentSendVO>();
		try {
			//-- 과제 후보 정보 저장후 주키를 받아 온다.
			iAssignmentSendVO.setSendCnt(iAssignmentSendVO.getSendCnt() + 1);
			assignmentSendMapper.insert(iAssignmentSendVO);
			assignmentSendMapper.insertDetail(iAssignmentSendVO);

			//-- 첨부파일 저장
			sysFileService.bindFile(iAssignmentSendVO, new AsmtSendFileHandler());

			resultVO.setReturnVO(iAssignmentSendVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}

	/**
	 * 과제 수정 등록
	 */
	//@HrdApiScore(category = Category.ASMT, saveType = SaveType.END)
	@HrdApiScore
	public	ProcessResultVO<AssignmentSendVO> editSend(AssignmentSendVO iAssignmentSendVO) throws Exception {

		
		ProcessResultVO<AssignmentSendVO> resultVO = new ProcessResultVO<AssignmentSendVO>();
		try {
			//-- 기존 값 가져오기
			AssignmentSendVO assignmentSendVO = assignmentSendMapper.select(iAssignmentSendVO);
			iAssignmentSendVO.setSendCnt(assignmentSendVO.getSendCnt() + 1);
			
			//-- 첨부파일 처리
			sysFileService.bindFileUpdate(iAssignmentSendVO, new AsmtSendFileHandler());

			iAssignmentSendVO.setScoreCategory("ASMT");
			iAssignmentSendVO.setScoreSaveType("END");
			assignmentSendMapper.send(iAssignmentSendVO);
			assignmentSendMapper.sendDetail(iAssignmentSendVO);
			resultVO.setReturnVO(iAssignmentSendVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		
		

		return resultVO;
	}


	/**
	 * 과제 후보 목록 조회
	 */
	public	ProcessResultListVO<AssignmentSubVO> listSub(AssignmentVO iAssignmentVO) throws Exception {
		ProcessResultListVO<AssignmentSubVO> resultList = new ProcessResultListVO<AssignmentSubVO>();
		try {
			List<AssignmentSubVO> returnList = assignmentSubMapper.list(iAssignmentVO);
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
	 * 과제 후보 목록 조회(과제 선택 유형 코드R) - 수강생 조회, 문제 조회, insert 한번에(HRD 미사용)
	 */
	public ProcessResultVO<AssignmentSubVO> randomReadSub(AssignmentVO iAssignmentVO) throws Exception {

	      AssignmentSubVO SubVO = null;	//과제 문제 임시로 저장할 VO
	      AssignmentSendVO assignmentSendVO = new AssignmentSendVO();	//과제 제출 임시로 저장할 VO

	      try {
	    	  assignmentSendVO.setCrsCreCd(iAssignmentVO.getCrsCreCd());
		      assignmentSendVO.setStdNo(iAssignmentVO.getStdNo());
		      assignmentSendVO.setAsmtSn(iAssignmentVO.getAsmtSn());
		      AssignmentSendVO resultVO = assignmentSendMapper.select(assignmentSendVO);
		      if(resultVO != null) {	//과제를 제출한 적이 있으면 해당 과제 문제를 가져옴 
		    	  assignmentSendVO = resultVO;
		    	  SubVO = new AssignmentSubVO();	//과제 문제 임시로 저장할 VO
		    	  SubVO.setCrsCreCd(iAssignmentVO.getCrsCreCd());
			      SubVO.setAsmtSn(iAssignmentVO.getAsmtSn());
		    	  SubVO.setAsmtSubSn(assignmentSendVO.getAsmtSubSn());
		    	  SubVO = assignmentSubMapper.select(SubVO);
		      }

		  } catch (Exception e) { }
	      
	     if(SubVO == null) //과제를 제출한 적이 없으면 
	      {
	    	  SubVO = new AssignmentSubVO();	//과제 문제 임시로 저장할 VO 
			  int numbersNeeded = 1; // 1개를 뽑습니다.
		      Random rng = new Random(); // 하나의 전역 인스턴스 생성
		      
		      ArrayList<AssignmentSubVO> SubArrayList = (ArrayList<AssignmentSubVO>)assignmentSubMapper.list(iAssignmentVO);
		      for (int i = 0; i < numbersNeeded; i++)
		      {
		    	  while(true)
		            {
	                   Integer next = rng.nextInt(SubArrayList.size());
	                   if (!SubArrayList.contains(next))
	                   {
	                          // 이 반복에 대해 수행
	                	     SubVO = SubArrayList.get(next);
	                	     assignmentSendVO.setCrsCreCd(SubVO.getCrsCreCd());
	               	      	 assignmentSendVO.setStdNo(iAssignmentVO.getStdNo());
	               	         assignmentSendVO.setAsmtSn(SubVO.getAsmtSn());
	               	         assignmentSendVO.setAsmtSubSn(SubVO.getAsmtSubSn());
	               	         assignmentSendVO.setRegNo(iAssignmentVO.getRegNo());
	            	         assignmentSendVO.setModNo(iAssignmentVO.getModNo());
	               	         assignmentSendVO.setSendCnt(0);

	                	     assignmentSendMapper.insert(assignmentSendVO);
	                	     assignmentSendMapper.insertDetail(assignmentSendVO);
	                         break;
	                   }
		            }
		      }
	      }
	      // 파일 목록 조회
	      if(SubVO != null) {
	    	  SubVO = sysFileService.getFile(SubVO, new AsmtSubFileHandler());
	      }
		return new ProcessResultVO<AssignmentSubVO>().setResultSuccess().setReturnVO(SubVO);
	}
	
	/**
	 * 과제 후보 목록 조회(과제 선택 유형 코드R) - 문제 조회만
	 */
	public ProcessResultVO<AssignmentSubVO> viewOnlySubRandomRead(AssignmentVO iAssignmentVO) throws Exception {
		AssignmentSubVO SubVO = null;		//과제 문제 임시로 저장할 VO 
		int numbersNeeded = 1; // 1개를 뽑습니다.
		Random rng = new Random(); // 하나의 전역 인스턴스 생성
		
		ArrayList<AssignmentSubVO> SubArrayList = (ArrayList<AssignmentSubVO>)assignmentSubMapper.list(iAssignmentVO);
		for (int i = 0; i < numbersNeeded; i++)	{
			while(true)	{
				Integer next = rng.nextInt(SubArrayList.size());
				if (!SubArrayList.contains(next))
				{
				       // 이 반복에 대해 수행
				   SubVO = SubArrayList.get(next);
				  break;
				}
			}
	    }
	    // 파일 목록 조회
		if(SubVO != null) {
		  SubVO = sysFileService.getFile(SubVO, new AsmtSubFileHandler());
		}
		return new ProcessResultVO<AssignmentSubVO>().setResultSuccess().setReturnVO(SubVO);
	}


	/**
	 * 과제 후보 정보 조회
	 */
	public	ProcessResultVO<AssignmentSubVO> viewSub(AssignmentSubVO assignmentSubVO) throws Exception {
		assignmentSubVO = assignmentSubMapper.select(assignmentSubVO);
		assignmentSubVO = sysFileService.getFile(assignmentSubVO, new AsmtSubFileHandler());

		return new ProcessResultVO<AssignmentSubVO>().setResultSuccess().setReturnVO(assignmentSubVO);
	}

	/**
	 * 과제 후보 정보 등록
	 */
	public	ProcessResultVO<AssignmentSubVO> addSub(AssignmentSubVO iAssignmentSubVO)  throws Exception {
		
		iAssignmentSubVO.setAsmtSubSn(assignmentSubMapper.selectKey());
		//-- 과제 후보 정보 저장후 주키를 받아 온다.
		assignmentSubMapper.insert(iAssignmentSubVO);
		if(iAssignmentSubVO.getConstCtsList() != null) {
			AssignmentSubConstVO cVO = new AssignmentSubConstVO();
			cVO.setCrsCreCd(iAssignmentSubVO.getCrsCreCd());
			cVO.setAsmtSn(iAssignmentSubVO.getAsmtSn());
			cVO.setAsmtSubSn(iAssignmentSubVO.getAsmtSubSn());
			  for (String e : iAssignmentSubVO.getConstCtsList()) {
		            cVO.setConstCts(e);
		            cVO.setConstSn(assignmentSubConstMapper.selectKey());
		            assignmentSubConstMapper.insert(cVO);
		        }
		}

		//-- 주키를 생성후 파일 저장
		sysFileService.bindFile(iAssignmentSubVO, new AsmtSubFileHandler());

		return ProcessResultVO.success();
	}

	/**
	 * 과제 문제 정보 등록 (문제 은행에서 가져오기)
	 */
	public	ProcessResultVO<AssignmentSubVO> addQbankSub(AssignmentVO iAssignmentVO) throws Exception {
		String[] qstnList = StringUtil.split(iAssignmentVO.getStrQstnSn(),"|");
		for (int i = 0; i < qstnList.length; i++) {
			//-- 문제 은행에서 정보 가져오기
			AssignmentQbankQuestionVO assignmentQbankQuestionVO = new AssignmentQbankQuestionVO();
			assignmentQbankQuestionVO.setCtgrCd(iAssignmentVO.getCtgrCd());
			assignmentQbankQuestionVO.setSbjCd(iAssignmentVO.getSbjCd());
			assignmentQbankQuestionVO.setQstnSn(Integer.parseInt(qstnList[i]));
			AssignmentQbankQuestionVO selAssignmentQbankQuestionVO = assignmentQbankQuestionMapper.select(assignmentQbankQuestionVO);
	 		if(ValidationUtils.isNotEmpty(selAssignmentQbankQuestionVO)) {
	 			assignmentQbankQuestionVO = selAssignmentQbankQuestionVO;
			}
			AssignmentSubVO asmtSubVO = new AssignmentSubVO();
			asmtSubVO.setCrsCreCd(iAssignmentVO.getCrsCreCd());
			asmtSubVO.setAsmtSn(iAssignmentVO.getAsmtSn());
			asmtSubVO.setAsmtTitle(assignmentQbankQuestionVO.getQstnTitle());
			asmtSubVO.setAsmtCts(assignmentQbankQuestionVO.getQstnCts());

			assignmentSubMapper.insert(asmtSubVO);
		}
		return ProcessResultVO.success();
	}

	/**
	 * 과제 후보 정보 수정
	 */
	public  ProcessResultVO<AssignmentSubVO> editSub(AssignmentSubVO iAssignmentSubVO) throws Exception {
		// 첨부파일 정보를 DELETE후 INSERT한다. (실제 파일과는 무관함)
		sysFileService.bindFileUpdate(iAssignmentSubVO, new AsmtSubFileHandler());

		assignmentSubMapper.update(iAssignmentSubVO);

		return ProcessResultVO.success();
	}

	/**
	 * 선텍 제출 과제 후보 정보 수정
	 */
	public  ProcessResultVO<AssignmentSubVO> selectEditSub(AssignmentSubVO iAssignmentSubVO) throws Exception {

		assignmentSubMapper.update(iAssignmentSubVO);

		return ProcessResultVO.success();
	}

	/**
	 * 과제 후보 정보 삭제
	 */
	public ProcessResultVO<AssignmentSubVO> deleteSub(AssignmentSubVO iAssignmentSubVO) throws Exception {
		iAssignmentSubVO = this.viewSub(iAssignmentSubVO).getReturnVO();

		// 파일 삭제
		sysFileService.removeFile(iAssignmentSubVO, new AsmtSubFileHandler());
		assignmentSubMapper.delete(iAssignmentSubVO);
		
		AssignmentSubConstVO cVO = new AssignmentSubConstVO();
		cVO.setCrsCreCd(iAssignmentSubVO.getCrsCreCd());
		cVO.setAsmtSn(iAssignmentSubVO.getAsmtSn());
		cVO.setAsmtSubSn(iAssignmentSubVO.getAsmtSubSn());
		assignmentSubConstMapper.deleteAll(cVO);
		return ProcessResultVO.success();
	}

	/**
	 * 과제 후보 파일 목록 조회
	 */
	public	ProcessResultVO<AssignmentSubVO> listSubFile(AssignmentSubVO iAssignmentSubVO) throws Exception {
		return this.viewSub(iAssignmentSubVO);
	}

	/**
	 * 과제 응시자 목록 조회
	 */
	public	ProcessResultListVO<AssignmentSendVO> listSend(AssignmentVO iAssignmentVO) throws Exception {
		return new ProcessResultListVO<>(assignmentSendMapper.list(iAssignmentVO));

	}

	/**
	 * 과제 응시자 목록 조회 페이징
	 */
	public	ProcessResultListVO<AssignmentSendVO> listSendPageing(AssignmentVO iAssignmentVO ) throws Exception {
		
		ProcessResultListVO<AssignmentSendVO> resultList = new ProcessResultListVO<AssignmentSendVO>();
		
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(iAssignmentVO.getCurPage());
		paginationInfo.setRecordCountPerPage(iAssignmentVO.getListScale());
		paginationInfo.setPageSize(iAssignmentVO.getPageScale());
		
		iAssignmentVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		iAssignmentVO.setLastIndex(paginationInfo.getLastRecordIndex());
		
		// 전체 목록 수
		int totalCount = assignmentSendMapper.count(iAssignmentVO);
		paginationInfo.setTotalRecordCount(totalCount);
		
		resultList.setReturnList(assignmentSendMapper.listPageing(iAssignmentVO));
		resultList.setPageInfo(paginationInfo);
		
		return resultList;

	}

	/**
	 * 과제 평가 등록
	 */
	//@HrdApiScore(category = Category.ASMT, saveType = SaveType.RATE)
	public	ProcessResultVO<AssignmentSendVO> addSendRate(AssignmentSendVO iAssignmentSendVO) throws Exception {

		//-- 기존 값 가져오기
		AssignmentSendVO assignmentSendVO = assignmentSendMapper.select(iAssignmentSendVO);
		assignmentSendVO.setAtchCts(iAssignmentSendVO.getAtchCts());
		assignmentSendVO.setScore(iAssignmentSendVO.getScore());
		assignmentSendVO.setRateYn(iAssignmentSendVO.getRateYn());
		assignmentSendVO.setRateNo(iAssignmentSendVO.getRegNo());

		assignmentSendMapper.rate(assignmentSendVO);

		AssignmentVO assignmentVO = new AssignmentVO();
		assignmentVO.setCrsCreCd(iAssignmentSendVO.getCrsCreCd());
		assignmentVO.setAsmtSn(iAssignmentSendVO.getAsmtSn());
		assignmentVO = assignmentMapper.select(assignmentVO);
		

		//-- 자동 성적 처리 :
		iAssignmentSendVO.setScoreSaveType("RATE");
		iAssignmentSendVO.setScoreCategory("ASMT");
		
		EduResultVO eduResultVO = new EduResultVO();
		eduResultVO.setCrsCreCd(assignmentSendVO.getCrsCreCd());
		eduResultVO.setStdNo(assignmentSendVO.getStdNo());
		eduResultVO.setSbjCd(assignmentVO.getSbjCd());

		eduResultService.addEduResultSp(eduResultVO, iAssignmentSendVO);

		return ProcessResultVO.success();
	}

	/**
	 * 과제 점수 등록
	 */
	//@HrdApiScore(category = Category.ASMT, saveType = SaveType.END)
	public	ProcessResultVO<AssignmentSendVO> addSendScore(AssignmentSendVO iAssignmentSendVO) throws Exception {

		//-- 기존 값 검색 후 있으면 update, 없으면 insert
		AssignmentSendVO assignmentSendVO = new AssignmentSendVO();
		assignmentSendVO = assignmentSendMapper.select(iAssignmentSendVO);
		
		if(assignmentSendVO == null) {
			iAssignmentSendVO.setScoreSaveType("START");
			assignmentSendMapper.insert(iAssignmentSendVO);
			assignmentSendMapper.insertDetail(iAssignmentSendVO);
		}else {
			iAssignmentSendVO.setScoreSaveType("END");
			assignmentSendMapper.update(iAssignmentSendVO);
		}

		AssignmentVO assignmentVO = new AssignmentVO();
		assignmentVO.setCrsCreCd(iAssignmentSendVO.getCrsCreCd());
		assignmentVO.setAsmtSn(iAssignmentSendVO.getAsmtSn());
		assignmentVO = assignmentMapper.select(assignmentVO);

		//-- 자동 성적 처리 :
		iAssignmentSendVO.setScoreCategory("ASMT");
		
		EduResultVO eduResultVO = new EduResultVO();
		eduResultVO.setCrsCreCd(iAssignmentSendVO.getCrsCreCd());
		eduResultVO.setStdNo(iAssignmentSendVO.getStdNo());
		eduResultVO.setSbjCd(assignmentVO.getSbjCd());

		eduResultService.addEduResultSp(eduResultVO, iAssignmentSendVO);
		return ProcessResultVO.success();
	}

	/**
	 * 과제 점수 등록
	 */
	public	ProcessResultVO<AssignmentSendVO> addSendScoreAll(AssignmentSendVO iAssignmentSendVO, String strStdNo, String strScore) throws Exception {

		String[] strStdNoArray = StringUtil.split(strStdNo,"|");
		String[] strScoreArray = StringUtil.split(strScore,"|");

		for(int i = 0; i < strStdNoArray.length; i++) {
			iAssignmentSendVO.setStdNo(strStdNoArray[i]);
			iAssignmentSendVO.setScore(Integer.parseInt(strScoreArray[i]));
			addSendScore(iAssignmentSendVO);
		}
		return ProcessResultVO.success();
	}


	/**
	 * 제시험 처리
	 */
	public	ProcessResultVO<AssignmentSendVO> removeSend(AssignmentSendVO assignmentSendVO) throws Exception {

		//String[] stdNoArray = StringUtil.split(assignmentSendVO.getUserNoObj(), ",");
		String[] stdNoArray = assignmentSendVO.getUserNoObjArr();
		for(int i=0; i < stdNoArray.length; i++) {
			if("".equals(StringUtil.nvl(stdNoArray[i]))) {
				continue;
			}
			assignmentSendVO.setStdNo(stdNoArray[i]);
			sysFileService.removeFile(assignmentSendVO, new AsmtSendFileHandler());
			
			assignmentSendMapper.delete(assignmentSendVO);
			assignmentSendMapper.deleteDtl(assignmentSendVO);
			
			assignmentSendVO.setScoreCategory("ASMT");
			assignmentSendVO.setScoreSaveType("RESET");
			
			//삭제 후 점수 초기화
			EduResultVO eduResultVO = new EduResultVO();
			eduResultVO.setCrsCreCd(assignmentSendVO.getCrsCreCd());
			eduResultVO.setStdNo(assignmentSendVO.getStdNo());
			eduResultVO.setSbjCd("");
			eduResultService.addEduResultSp(eduResultVO, assignmentSendVO);
		}
		return new ProcessResultVO<AssignmentSendVO>(1);
	}
	
	@Override
	public ProcessResultVO<AssignmentSendVO> removeSend(AssignmentSendVO assignmentSendVO, List<AssignmentSendVO> asmtSendCreList) throws Exception {
		for(int i = 0; i < asmtSendCreList.size(); i++) {
			assignmentSendVO.setStdNo(asmtSendCreList.get(i).getStdNo());
			sysFileService.removeFile(assignmentSendVO, new AsmtSendFileHandler());
			//assignmentService.removeSendOne(assignmentSendVO);
			assignmentSendMapper.delete(assignmentSendVO);
			assignmentSendMapper.deleteDtl(assignmentSendVO);
			
			assignmentSendVO.setScoreCategory("ASMT");
			assignmentSendVO.setScoreSaveType("RESET");
			
			//삭제 후 점수 초기화
			EduResultVO eduResultVO = new EduResultVO();
			eduResultVO.setCrsCreCd(assignmentSendVO.getCrsCreCd());
			eduResultVO.setStdNo(assignmentSendVO.getStdNo());
			eduResultVO.setSbjCd("");
			//eduResultMapper.insertEduResultSp(eduResultVO);
			eduResultService.addEduResultSp(eduResultVO, assignmentSendVO);
		}
		return new ProcessResultVO<AssignmentSendVO>(1);
	}
	
	/**
	 * 성적 다운로드
	 * @author twkim
	 * @date 2013. 11. 25.
	 * @param iAssignmentVO
	 * @param os
	 * @throws ServiceProcessException void
	 */
	@Override
	public void listReshCourseExcelDownload(AssignmentVO iAssignmentVO, OutputStream os) throws ServiceProcessException {

		try {
			List<AssignmentSendVO> resultList = assignmentSendMapper.list(iAssignmentVO);
			
			int rowNum = 0;
			int colNum = 6;

			XSSFWorkbook wbook = new XSSFWorkbook();
			XSSFSheet sheet = wbook.createSheet();
			
			wbook.setSheetName(0, "과제 성적");
			
			// 페이지 제목줄 .. 작업코멘트 5줄.
			XSSFRow pageRow1 = sheet.createRow((short)rowNum);
			POIExcelUtil.createPageTitleCell("과제 성적", pageRow1, 0, colNum);

			//-- 제목 줄 만들기
			rowNum++;
			XSSFRow titleRow = sheet.createRow((short)rowNum);
			POIExcelUtil.createTitleCell("번호", titleRow, 0);
			POIExcelUtil.createTitleCell("이름", titleRow, 1);
			POIExcelUtil.createTitleCell("아이디", titleRow, 2);
			POIExcelUtil.createTitleCell("수강번호", titleRow, 3);
			POIExcelUtil.createTitleCell("응시여부", titleRow, 4);
			POIExcelUtil.createTitleCell("점수", titleRow, 5);
			POIExcelUtil.createTitleCell("채점여부", titleRow, 6);
			
			//-- 셀의 넓이 조절
			sheet.setColumnWidth(0, sheet.getDefaultColumnWidth() * 300);
			sheet.setColumnWidth(1, sheet.getDefaultColumnWidth() * 500);
			sheet.setColumnWidth(2, sheet.getDefaultColumnWidth() * 500);
			sheet.setColumnWidth(3, sheet.getDefaultColumnWidth() * 800);
			sheet.setColumnWidth(4, sheet.getDefaultColumnWidth() * 500);
			sheet.setColumnWidth(5, sheet.getDefaultColumnWidth() * 500);
			
			for(int i=0; i<resultList.size(); i++){
				rowNum++;
				XSSFRow row = sheet.createRow((short)rowNum);
				AssignmentSendVO vo = resultList.get(i);

				int sendCnt = vo.getSendCnt();
				String stareYnNm = "미제출";
				String rateYnNm = "미완료";

				if(sendCnt > 0){
					stareYnNm = "제출";
				}

				if(vo.getScore() > 0){
					rateYnNm = "완료";
				}
				POIExcelUtil.createContentCell(i+1, row, 0, "center");
				POIExcelUtil.createContentCell(vo.getUserNm(), row, 1, "center");
				POIExcelUtil.createContentCell(vo.getUserId(), row, 2, "center");
				POIExcelUtil.createContentCell(vo.getStdNo(), row, 3, "center");
				POIExcelUtil.createContentCell(stareYnNm, row, 4, "center");
				POIExcelUtil.createContentCell(vo.getScore(), row, 5, "center");
				POIExcelUtil.createContentCell(rateYnNm, row, 6, "center");
			}
			rowNum++;
			XSSFRow totalRow = sheet.createRow((short)rowNum);
			
			if(resultList.size() == 0){
				rowNum++;
				XSSFRow pageRow3 = sheet.createRow((short)rowNum);
				POIExcelUtil.createMergeCell("수강생이 존재하지 않습니다.", pageRow3, 0, colNum, "right");
			}

			try {
				wbook.write(os);
			} catch (Exception ex) {
				String name = ex.getClass().getName();
				if (!name.equals("org.apache.catalina.connector.ClientAbortException")) {
					throw ex;
				}
			}
		}catch (Exception e) {
			throw new ServiceProcessException("Excel 생성 실패", e);
		}
	}

	/**
	 * 성적 업로드
	 * @author twkim
	 * @date 2013. 11. 27.
	 * @param assignmentSendVO
	 * @param fileName
	 * @param filePath
	 * @return
	 * @throws ServiceProcessException ProcessResultVO<AssignmentSendVO>
	 */
	@Override
	public ProcessResultVO<AssignmentSendVO> addExcelUpload(AssignmentSendVO assignmentSendVO, String fileName,String filePath) throws ServiceProcessException {
		try {
			Workbook workbook = Workbook.getWorkbook(new File(filePath+"/"+fileName));
			Sheet sheet = workbook.getSheet(0);

			for(int j=2; j<sheet.getRows(); j++){
				Cell cellUserId	= sheet.getCell(2 ,j);	//아이디
				Cell cellStdNo	= sheet.getCell(3 ,j);	//수강번호
				Cell cellScore	= sheet.getCell(5 ,j);	//점수

				assignmentSendVO.setUserId(cellUserId.getContents());
				assignmentSendVO.setStdNo(cellStdNo.getContents());
				assignmentSendVO.setScore(Integer.parseInt(cellScore.getContents().trim()));
				assignmentSendVO.setSendCnt(1);

				this.addSendScore(assignmentSendVO);
			}

			return ProcessResultVO.success();
		} catch (Exception e) {
			throw new ServiceProcessException("excel 파일 처리 실패", e);
		} finally {
			//-- 사용한 파일 지움.
			FileUtil.delFile(filePath, fileName);
		}
	}
	
	@Override
	public List<AssignmentVO> listAsmtScore(AssignmentVO vo) throws Exception {
		return assignmentMapper.listByStdNo(vo);
	}

	@Override
	public ProcessResultListVO<AssignmentSubConstVO> listSubConst(AssignmentSubConstVO cVO) throws Exception {
	ProcessResultListVO<AssignmentSubConstVO> resultList = new ProcessResultListVO<AssignmentSubConstVO>();
	try {
		List<AssignmentSubConstVO> returnList = assignmentSubConstMapper.list(cVO);
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
	 * 과제 정보 목록 조회 (카운트)
	 */
	public	ProcessResultListVO<AssignmentVO> listAssignmentCount(AssignmentVO iAssignmentVO) throws Exception {
		ProcessResultListVO<AssignmentVO> resultList = new ProcessResultListVO<AssignmentVO>();
		try {
			iAssignmentVO.setAsmtSvcCd("CODE");
			List<AssignmentVO> returnList = assignmentMapper.list(iAssignmentVO);
			for (AssignmentVO aVO : returnList) {
				AssignmentVO cVO = new AssignmentVO();
				cVO = assignmentMapper.select(aVO);
				aVO.setSubCnt(cVO.getSubCnt());	
			}
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;

	}

	@Override
	public ProcessResultListVO<AssignmentSubVO> listCodeSub(AssignmentVO avo) throws Exception {
		ProcessResultListVO<AssignmentSubVO> resultList = new ProcessResultListVO<AssignmentSubVO>();
		try {
			List<AssignmentSubVO> returnList = assignmentSubMapper.listCodeSub(avo);
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
	 * 코딩 과제 제출
	 * @param iAssignmentSendVO
	 * @return
	 */
	public	ProcessResultVO<AssignmentSendVO> addSendCodeAsmt(AssignmentSendVO iAssignmentSendVO)  throws Exception {
		
		ProcessResultVO<AssignmentSendVO> resultVO = new ProcessResultVO<AssignmentSendVO>();
		try {
			//-- 과제 후보 정보 저장후 주키를 받아 온다.
			iAssignmentSendVO.setSendCnt(iAssignmentSendVO.getSendCnt() + 1);
			if(assignmentSendMapper.selectTemp(iAssignmentSendVO) == null) {
				assignmentSendMapper.insert(iAssignmentSendVO);
			} else {
				assignmentSendMapper.send(iAssignmentSendVO);
			}
			if(assignmentSendMapper.selectTestResult(iAssignmentSendVO) != null) {
				resultVO.setResultFailed();
				resultVO.setMessage("제출할 수 없는 상태입니다.");
				return resultVO;
			}
			assignmentSendMapper.insertDetail(iAssignmentSendVO);

			//-- 첨부파일 저장
			sysFileService.bindFile(iAssignmentSendVO, new AsmtSendFileHandler());

			resultVO.setReturnVO(iAssignmentSendVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}
	
	/**
	 * 코딩 과제 채점 결과 조회
	 * @param iAssignmentSendVO
	 * @return
	 */
	@Override
	public ProcessResultVO<AssignmentSendVO> selectTestResult(AssignmentSendVO vo) throws Exception {
		ProcessResultVO<AssignmentSendVO> resultVO = new ProcessResultVO<AssignmentSendVO>();
		try {
			vo = assignmentSendMapper.selectTestResult(vo);
			resultVO.setReturnVO(vo);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		return resultVO;
	}

	@Override
	public ProcessResultVO<AssignmentSendVO> saveAsmtSubScore(AssignmentSendVO vo) throws Exception {
		ProcessResultVO<AssignmentSendVO> resultVO = new ProcessResultVO<AssignmentSendVO>();
		try {
			assignmentSubMapper.saveAsmtSubScore(vo);
			resultVO.setReturnVO(vo);
			resultVO.setResult(1);
			resultVO.setMessage("저장되었습니다.\n 평가 완료 버튼을 눌러야 최종 점수가 저장됩니다.");
		} catch (Exception e) {
			e.printStackTrace();
			resultVO.setResult(-1);
			resultVO.setMessage(e.getMessage());
		}
		return resultVO;
	}
	
	/**
	 * 오프라인 과제 점수 등록
	 */
	//@HrdApiScore(category = Category.ASMT, saveType = SaveType.END)
	public	ProcessResultVO<AssignmentSendVO> addOffSendScore(AssignmentSendVO iAssignmentSendVO) throws Exception {

		//-- 기존 값 검색 후 있으면 update, 없으면 insert
		AssignmentSendVO assignmentSendVO = new AssignmentSendVO();
		assignmentSendVO = assignmentSendMapper.select(iAssignmentSendVO);
		
		if(assignmentSendVO == null) {
			assignmentSendMapper.insert(iAssignmentSendVO);
		}else {
			assignmentSendMapper.update(iAssignmentSendVO);
		}

		AssignmentVO assignmentVO = new AssignmentVO();
		assignmentVO.setCrsCreCd(iAssignmentSendVO.getCrsCreCd());
		assignmentVO.setAsmtSn(iAssignmentSendVO.getAsmtSn());
		assignmentVO = assignmentMapper.select(assignmentVO);

		//-- 자동 성적 처리 :
		iAssignmentSendVO.setScoreCategory("ASMT");
		
		EduResultVO eduResultVO = new EduResultVO();
		eduResultVO.setCrsCreCd(iAssignmentSendVO.getCrsCreCd());
		eduResultVO.setStdNo(iAssignmentSendVO.getStdNo());
		eduResultVO.setSbjCd(assignmentVO.getSbjCd());

		eduResultService.addEduResultSp(eduResultVO, iAssignmentSendVO);
		return ProcessResultVO.success();
	}
	
	/**
	 * 오프라인 과제 점수 등록
	 */
	public	ProcessResultVO<AssignmentSendVO> addOffSendScoreAll(AssignmentSendVO iAssignmentSendVO, String strStdNo, String strScore) throws Exception {

		String[] strStdNoArray = StringUtil.split(strStdNo,"|");
		String[] strScoreArray = StringUtil.split(strScore,"|");

		for(int i = 0; i < strStdNoArray.length; i++) {
			iAssignmentSendVO.setStdNo(strStdNoArray[i]);
			iAssignmentSendVO.setScore(Integer.parseInt(strScoreArray[i]));
			addOffSendScore(iAssignmentSendVO);
		}
		return ProcessResultVO.success();
	}
}