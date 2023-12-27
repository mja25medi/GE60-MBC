package egovframework.edutrack.modules.lecture.exam.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.annotation.HrdApiScore;
import egovframework.edutrack.comm.annotation.HrdApiStdStd;
import egovframework.edutrack.comm.annotation.HrdApiStdStd.SyncType;
import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.ExcelUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.course.exambank.service.ExamQbankQstnVO;
import egovframework.edutrack.modules.course.exambank.service.impl.ExamQbankQstnMapper;
import egovframework.edutrack.modules.lecture.exam.service.ExamQuestionVO;
import egovframework.edutrack.modules.lecture.exam.service.ExamService;
import egovframework.edutrack.modules.lecture.exam.service.ExamStareVO;
import egovframework.edutrack.modules.lecture.exam.service.ExamVO;
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
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

@Service("examService")
public class ExamServiceImpl extends EgovAbstractServiceImpl implements ExamService {
	
	private final class ExamFileHandler
			implements FileHandler<ExamQuestionVO> {

		@Override
		public String getRepoCd() {
			return "EXAM_QSTN";
		}

		@Override
		public String getPK(ExamQuestionVO vo) {
			return vo.getExamQstnSn().toString();
		}

		@Override
		public List<SysFileVO> getFiles(ExamQuestionVO vo) {
			List<SysFileVO> fileList = vo.getAttachImages();
			return fileList;
		}

		@Override
		public ExamQuestionVO setFiles(ExamQuestionVO vo, FileListVO fileListVO) {
			vo.setAttachImages(fileListVO.getFiles("image"));
			return vo;
		}
	}

	@Resource(name="examMapper")
	private ExamMapper examMapper;

	@Resource(name="examQstnMapper")
	private ExamQstnMapper examQstnMapper;

	@Resource(name="examStareMapper")
	private ExamStareMapper examStareMapper;

	@Resource(name="examQbankQstnMapper")
	private ExamQbankQstnMapper examQbankQstnMapper;

	@Resource(name="sysFileService")
	private SysFileService sysFileService;

	@Resource(name="eduResultMapper")
	private EduResultMapper eduResultMapper;

	@Resource(name="examService")
	private ExamService examService;
	
	@Resource(name="eduResultService")
	private EduResultService eduResultService;

	/**
	 * 시험 목록을 반환한다.(교수자)
	 * @param vo
	 * @return
	 */
	@Override
	public	ProcessResultListVO<ExamVO> listExam(ExamVO vo) throws Exception{
		ProcessResultListVO<ExamVO> resultList = new ProcessResultListVO<ExamVO>();
		try {
			List<ExamVO> returnList =  examMapper.list(vo);
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
	 * 시험 목록을 반환한다.(학습자)
	 * @param vo
	 * @return
	 */
	@Override
	public	ProcessResultListVO<ExamVO> listExamStd(ExamVO vo) throws Exception{
		ProcessResultListVO<ExamVO> resultList = new ProcessResultListVO<ExamVO>();
		try {
			List<ExamVO> returnList =  examMapper.listExamStd(vo);
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
	 * 시험 정보를 반환한다.
	 * @param vo
	 * @return
	 */
	@Override
	public	ProcessResultVO<ExamVO> viewExam(ExamVO vo) throws Exception{
		ProcessResultVO<ExamVO> resultVO = new ProcessResultVO<ExamVO>();
		try {
			ExamVO returnVO = examMapper.selectExam(vo);
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
	 * 시험 정보를 등록하고 결과를 반환한다.
	 * @param vo
	 * @return
	 */
	@Override
	public	ProcessResultVO<ExamVO> addExam(ExamVO vo) throws Exception{

		ProcessResultVO<ExamVO> resultVO = new ProcessResultVO<ExamVO>();
		try {
			//---- 분류 코드 생성
			ExamVO examVO = examMapper.selectExamSn();
			if("R".equals(vo.getExamStareTypeCd()) || "OFF".equals(vo.getExamTypeCd())) {

				if(vo.getExamStartHour().length() ==1){
					vo.setExamStartHour("0"+vo.getExamStartHour());
				}
				if(vo.getExamStartMin().length() ==1){
					vo.setExamStartMin("0"+vo.getExamStartMin());
				}

				if(vo.getExamEndHour().length() ==1){
					vo.setExamEndHour("0"+vo.getExamEndHour());
				}
				if(vo.getExamEndMin().length() ==1){
					vo.setExamEndMin("0"+vo.getExamEndMin());
				}

				if(vo.getRsltCfrmHour().length() ==1){
					vo.setRsltCfrmHour("0"+vo.getRsltCfrmHour());
				}
				if(vo.getRsltCfrmMin().length() ==1){
					vo.setRsltCfrmMin("0"+vo.getRsltCfrmMin());
				}

				//-- 시간 관련 처리
				String examStartDttm = StringUtil.dateNumber(vo.getExamStartDttm())+vo.getExamStartHour()+vo.getExamStartMin()+"01";
				String examEndDttm = StringUtil.dateNumber(vo.getExamEndDttm())+vo.getExamEndHour()+vo.getExamEndMin()+"59";
				String rsltCfrmDttm = "";
				if(StringUtil.isNotNull(vo.getRsltCfrmDttm())) rsltCfrmDttm = StringUtil.dateNumber(vo.getRsltCfrmDttm())+vo.getRsltCfrmHour()+vo.getRsltCfrmMin()+"59";
				
				
				vo.setExamStartDttm(examStartDttm);
				vo.setExamEndDttm(examEndDttm);
				vo.setRsltCfrmDttm(rsltCfrmDttm);
			}
			if("OFF".equals(vo.getExamTypeCd())) {
				//-- 오프라인의 경우 정규 시험으로 셋팀함.
				vo.setExamStareTypeCd("R");
			}
			//---- 신규 코드 세팅
			vo.setExamSn(examVO.getExamSn());			
			
			examMapper.insertExam(vo);
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
	 * 시험 등록 완료를 처리하고 결과를 반환한다.
	 * @param vo
	 * @return
	 */
	@Override
	public	ProcessResultVO<ExamVO> addRegistExam(ExamVO vo) {
		ExamQuestionVO eqvo = new ExamQuestionVO();
		eqvo.setCrsCreCd(vo.getCrsCreCd());
		eqvo.setExamSn(vo.getExamSn());
		List<ExamQuestionVO> qstnList;
		try {
			qstnList = examQstnMapper.list(eqvo);
		} catch (Exception e) {
			throw new ServiceProcessException("문제 조회 오류");
		}

		String[] scoreList = StringUtil.split(vo.getQstnScores(),"|");
		String[] qstnNoList = StringUtil.split(vo.getQstnNos(), "|");
		String[] examQstnSnList = StringUtil.split(vo.getExamQstnSn(), "|");
		
		if("ON".equals(vo.getExamTypeCd())){
			int sumSKCnt = 0;
			int sumDCnt = 0;
			int sumJCnt = 0;
			//시험 관리의 총 갯수와 문제관리의 총 갯수가 일치하지 않음.
			if(qstnList.size() < vo.getSumSelShortDesCnt()) {
				throw new ServiceProcessException("시험 관리의 총 문항 수보다 문제 관리의 총 문항 수가 많아야 합니다."
													+ "\n[시험관리] 총 문항 수(선택형+단답형+서술형) : " + vo.getSumSelShortDesCnt() 
													+ "\n[문제관리] 총 문항 수 : " + qstnList.size()
													);
			}
			
			for(ExamQuestionVO seqvo : qstnList) {
				//[HRD] 랜덤 출제 및 시험에서 유형별 문항수, 배점 세팅하는 경우 시험 유형 갯수 체크
				if("S".equals(seqvo.getQstnType()) || "K".equals(seqvo.getQstnType()) ) {
					sumSKCnt += 1;
				}else if("D".equals(seqvo.getQstnType())) {
					sumDCnt += 1;
				}else if("J".equals(seqvo.getQstnType())) {
					sumJCnt += 1;
				}
			}
			
			int selCnt = vo.getSelCnt();
			int shortCnt = vo.getShortCnt();
			int descCnt = vo.getDesCnt();
			
			if((selCnt != 0 && selCnt > sumSKCnt) || (shortCnt != 0 && shortCnt > sumDCnt) || (descCnt != 0 && descCnt > sumJCnt)) {
				throw new ServiceProcessException("시험 관리의 유형별 문항 수와 문제 관리 유형별 문항 수가 일치하지 않습니다."
						+ "\n[시험관리 문항수]\n선택형/단답형/서술형 : " + vo.getSelCnt() + "/" + vo.getShortCnt() + "/" + vo.getDesCnt() 
						+ "\n[문제관리 문항수]\n선택형/단답형/서술형 : " + sumSKCnt + "/" + sumDCnt + "/" + sumJCnt);
			}
		}
		
		/*////성적 점수 update => 랜덤 출제이기 때문에 문항별 점수 세팅 기능은 [HRD] 에서는 사용하지 않음. 재사용 시, 주석 해제
		for(ExamQuestionVO seqvo : qstnList) {
			for(int i = 0; i < qstnNoList.length; i++) {
				if(seqvo.getQstnNo() == Integer.parseInt(qstnNoList[i])) {
					seqvo.setQstnScore(Double.parseDouble(scoreList[i]));
				}
			}
			try {
				examQstnMapper.updateScore(seqvo);
			} catch (Exception e) {
				throw new ServiceProcessException("문제 점수 등록 오류");
			}
		}
		*/

		if("R".equals(vo.getExamStareTypeCd()) || "OFF".equals(vo.getExamTypeCd())) {
			if(vo.getExamStartHour().length() ==1){
				vo.setExamStartHour("0"+vo.getExamStartHour());
			}
			if(vo.getExamStartMin().length() ==1){
				vo.setExamStartMin("0"+vo.getExamStartMin());
			}
			if(vo.getExamEndHour().length() ==1){
				vo.setExamEndHour("0"+vo.getExamEndHour());
			}
			if(vo.getExamEndMin().length() ==1){
				vo.setExamEndMin("0"+vo.getExamEndMin());
			}
			if(vo.getRsltCfrmHour().length() ==1){
				vo.setRsltCfrmHour("0"+vo.getRsltCfrmHour());
			}
			if(vo.getRsltCfrmMin().length() ==1){
				vo.setRsltCfrmMin("0"+vo.getRsltCfrmMin());
			}
			//2015.11.05 김현욱 수정 시험일자 및 결과확인일을 1,8로 되어 있어 일자가 잘 못 등록되고 있었음.
			/*String examStartDttm = StringUtil.substring(StringUtil.ReplaceAll(vo.getExamStartDttm(),".",""),0,8)+vo.getExamStartHour()+vo.getExamStartMin()+"01";
			String examEndDttm = StringUtil.substring(StringUtil.ReplaceAll(vo.getExamEndDttm(),".",""),0,8)+vo.getExamEndHour()+vo.getExamEndMin()+"59";
			String rsltCfrmDttm = StringUtil.substring(StringUtil.ReplaceAll(vo.getRsltCfrmDttm(),".",""),0,8)+vo.getRsltCfrmHour()+vo.getRsltCfrmMin()+"59";*/
			//-- 시간 관련 처리
			String examStartDttm = StringUtil.substring(StringUtil.ReplaceAll(vo.getExamStartDttm(),".",""),0,8)+vo.getExamStartHour()+vo.getExamStartMin()+"01";
			String examEndDttm = StringUtil.substring(StringUtil.ReplaceAll(vo.getExamEndDttm(),".",""),0,8)+vo.getExamEndHour()+vo.getExamEndMin()+"59";
			String rsltCfrmDttm = StringUtil.substring(StringUtil.ReplaceAll(vo.getRsltCfrmDttm(),".",""),0,8)+vo.getRsltCfrmHour()+vo.getRsltCfrmMin()+"59";
			vo.setExamStartDttm(examStartDttm);
			vo.setExamEndDttm(examEndDttm);
			vo.setRsltCfrmDttm(rsltCfrmDttm);
		}
		vo.setRegYn("Y");
		try {
			examMapper.updateExam(vo);
		} catch (Exception e) {
			throw new ServiceProcessException("등록 수정 오류");
		}
		return ProcessResultVO.success();
	}

	/**
	 * 학습자를 재응시 할 수 있도록 처리하고 결과를 반환한다.
	 * @param examStareVO
	 * @return
	 */
	
	@Override
	public ProcessResultVO<ExamStareVO> removeStare(ExamStareVO vo) throws Exception{
		/*if(!StringUtil.nvl(vo.getUserNoObj()).equals("")) {
			vo.setUserNoObjArr(vo.getUserNoObj().split(","));
		}*/
		examStareMapper.deleteMulti(vo);
		return ProcessResultVO.success();
	}
	
	@Override
	public ProcessResultVO<ExamStareVO> removeStare(ExamStareVO examStareVO, List<ExamStareVO> creExamStareList) {
		for(int i = 0; i< creExamStareList.size(); i++) {
			ExamStareVO paramVO = new ExamStareVO();
			paramVO.setCrsCreCd(examStareVO.getCrsCreCd());
			paramVO.setExamSn(examStareVO.getExamSn());
			paramVO.setStdNo(creExamStareList.get(i).getStdNo());
			paramVO.setRegNo(examStareVO.getRegNo());
			paramVO.setSemiExamYn(examStareVO.getSemiExamYn());
			//examService.removeStareOne(paramVO);
			if(examStareMapper.delete(paramVO) < 1) {
				throw new ServiceProcessException("삭제 오류");//성적 api 못 쌓게 오류 발생
			}
			paramVO.setScoreCategory("EXAM");
			paramVO.setScoreSaveType("RESET");
			
			EduResultVO eduResultVO = new EduResultVO();
			eduResultVO.setCrsCreCd(paramVO.getCrsCreCd());
			eduResultVO.setStdNo(paramVO.getStdNo());
			eduResultVO.setSbjCd("");
			eduResultService.addEduResultSp(eduResultVO, paramVO);
		}
		return ProcessResultVO.success();
	}
	
	/**
	 * 시험 정보를 수정하고 결과를 반환한다.
	 * @param vo
	 * @return
	 */
	@Override
	public	ProcessResultVO<ExamVO> editExam(ExamVO vo) throws Exception{
		
		ProcessResultVO<ExamVO> resultVO = new ProcessResultVO<ExamVO>();
		try {
			if("R".equals(vo.getExamStareTypeCd()) || "OFF".equals(vo.getExamTypeCd())) {
				if(vo.getExamStartHour().length() ==1){
					vo.setExamStartHour("0"+vo.getExamStartHour());
				}
				if(vo.getExamStartMin().length() ==1){
					vo.setExamStartMin("0"+vo.getExamStartMin());
				}
				if(vo.getExamEndHour().length() ==1){
					vo.setExamEndHour("0"+vo.getExamEndHour());
				}
				if(vo.getExamEndMin().length() ==1){
					vo.setExamEndMin("0"+vo.getExamEndMin());
				}
				if(vo.getRsltCfrmHour().length() ==1){
					vo.setRsltCfrmHour("0"+vo.getRsltCfrmHour());
				}
				if(vo.getRsltCfrmMin().length() ==1){
					vo.setRsltCfrmMin("0"+vo.getRsltCfrmMin());
				}
				//-- 시간 관련 처리
				String examStartDttm = StringUtil.ReplaceAll(vo.getExamStartDttm(),"-","");
				if(examStartDttm.length() <= 8) examStartDttm = examStartDttm+vo.getExamStartHour()+vo.getExamStartMin()+"01";
				String examEndDttm = StringUtil.ReplaceAll(vo.getExamEndDttm(),"-","");
				if(examEndDttm.length() <=8) examEndDttm = examEndDttm+vo.getExamEndHour()+vo.getExamEndMin()+"59";
				String rsltCfrmDttm = "";
				if(StringUtil.isNotNull(vo.getRsltCfrmDttm())) {
					rsltCfrmDttm = StringUtil.ReplaceAll(vo.getRsltCfrmDttm(),"-","");
					if(rsltCfrmDttm.length() <=8 ) rsltCfrmDttm = rsltCfrmDttm+vo.getRsltCfrmHour()+vo.getRsltCfrmMin()+"59";
				}
				vo.setExamStartDttm(examStartDttm);
				vo.setExamEndDttm(examEndDttm);
				vo.setRsltCfrmDttm(rsltCfrmDttm);
			}
			examMapper.updateExam(vo);
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
	 * 시험 정보를 삭제하고 결과를 반환한다.
	 * @param vo
	 * @return
	 */
	@Override
	public	ProcessResultVO<ExamVO> deleteExam(ExamVO vo) throws Exception{
		//응시내용 삭제
		examStareMapper.deleteAll(vo);

		//시험문제 파일 삭제
		ExamQuestionVO iExamQuestionVO = new ExamQuestionVO();
		iExamQuestionVO.setCrsCreCd(vo.getCrsCreCd());
		iExamQuestionVO.setExamSn(vo.getExamSn());

		ProcessResultListVO<ExamQuestionVO> resultListVO = new ProcessResultListVO<ExamQuestionVO>();
		List<ExamQuestionVO> returnList =  examQstnMapper.list(iExamQuestionVO);
		resultListVO.setReturnList(returnList);

		for(int i=0; i < resultListVO.getReturnList().size(); i++) {
			iExamQuestionVO = resultListVO.getReturnList().get(i);
			sysFileService.removeFile(iExamQuestionVO, new ExamFileHandler());

		}
		//시험문제 삭제
		examQstnMapper.deleteAll(vo);
		//시험 정보 삭제
		examMapper.deleteExam(vo);
		return ProcessResultVO.success();
	}

	/**
	 * 시험문제의 목록을 반환한다.
	 * @param vo
	 * @return
	 */
	@Override
	public	ProcessResultListVO<ExamQuestionVO> listQstn(ExamQuestionVO vo) throws Exception{
		ProcessResultListVO<ExamQuestionVO> resultList = new ProcessResultListVO<ExamQuestionVO>();
		try {
			if("RANDOM".equals(StringUtil.nvl(vo.getGubun(),""))) {
				vo.setSqlForeach(StringUtil.split(vo.getStrExamQstnSn(), "@#"));
			}
			List<ExamQuestionVO> returnList =  examQstnMapper.list(vo);
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
	 * 시험문제의 목록을 반환한다.
	 * @param vo
	 * @return
	 */
	@Override
	public	ProcessResultListVO<ExamQuestionVO> randListQstn(ExamQuestionVO vo) throws Exception {
		ProcessResultListVO<ExamQuestionVO> resultList = new ProcessResultListVO<ExamQuestionVO>();
		try {
			List<ExamQuestionVO> returnList =  examQstnMapper.randList(vo);
			
			for(int i=0; i < returnList.size(); i++) {
				if(i != returnList.size()-1) {
					returnList.get(i).setQstnScore(Math.floor(100.0/vo.getExamSetCnt()*10)/10.0);
				} else {
					returnList.get(i).setQstnScore(Math.round((100 - Math.round((Math.floor(100.0/vo.getExamSetCnt()*10)/10.0 )*(vo.getExamSetCnt()-1)*10)/10.0)*10)/10.0);
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
	
	/**
	 * 유형별 목록을 랜덤으로 조회한다.
	 * @param vo
	 * @return
	 */
	@Override
	public	ProcessResultListVO<ExamQuestionVO> randListQstnType(ExamQuestionVO vo, ExamVO examVO) {
		ProcessResultListVO<ExamQuestionVO> resultList = new ProcessResultListVO<ExamQuestionVO>();
		
		//시험관리의 문항, 배점에 따라 문제 랜덤 세팅 (선택형, 단답형, 서술형 순)
		List<ExamQuestionVO> selectExamList = new ArrayList<>();
		List<ExamQuestionVO> shortExamList = new ArrayList<>();
		List<ExamQuestionVO> desExamList = new ArrayList<>();
		List<ExamQuestionVO> mergeExamList = new ArrayList<>();
		
		int examSelectCnt 				= examVO.getSelCnt();
		int examShortAnswerCnt 			= examVO.getShortCnt();
		int examDescriptionAnswerCnt 	= examVO.getDesCnt();
		
		int examSelectPnt 				= examVO.getSelPnt();
		int examShortAnswerPnt 			= examVO.getShortPnt();
		int examDescriptionAnswerPnt 	= examVO.getDesPnt();
		
		if(examSelectCnt > 0) {
			vo.setSearchKey("QSTN");
			vo.setSearchValue("SK");
			vo.setExamSetCnt(examSelectCnt);
			selectExamList = examQstnMapper.randQstnTypeList(vo);
			if(selectExamList != null && selectExamList.size() > 0) {
				mergeExamList.addAll(selectExamList);
			}
		}
		
		if(examShortAnswerCnt > 0) {
			vo.setSearchKey("QSTN");
			vo.setSearchValue("D");
			vo.setExamSetCnt(examShortAnswerCnt);
			shortExamList = examQstnMapper.randQstnTypeList(vo);
			if(shortExamList != null && shortExamList.size() > 0) {
				mergeExamList.addAll(shortExamList);
			}
		}
		
		if(examDescriptionAnswerCnt > 0) {
			vo.setSearchKey("QSTN");
			vo.setSearchValue("J");
			vo.setExamSetCnt(examDescriptionAnswerCnt);
			desExamList = examQstnMapper.randQstnTypeList(vo);
			if(desExamList != null && desExamList.size() > 0) {
				mergeExamList.addAll(desExamList);
			}
		}
		
		for(int i = 0; i < mergeExamList.size(); i++) {
			ExamQuestionVO qstnVO =  mergeExamList.get(i);
			if(qstnVO.getQstnType().equals("D")){//단답
				mergeExamList.get(i).setQstnScore(examShortAnswerPnt);
			}else if(qstnVO.getQstnType().equals("J")){//서술
				mergeExamList.get(i).setQstnScore(examDescriptionAnswerPnt);
			}else if(qstnVO.getQstnType().equals("S") || qstnVO.getQstnType().equals("K")) {//선택형,진위형
				mergeExamList.get(i).setQstnScore(examSelectPnt);
			}
		}
		
		
		resultList.setResult(1);
		resultList.setReturnList(mergeExamList);
		
		return resultList;
	}

	/**
	 * 시험문제의 단일항목 정보를 반환한다.
	 * @param vo
	 * @return
	 */
	@Override
	public	ProcessResultVO<ExamQuestionVO> viewQstn(ExamQuestionVO vo) throws Exception{
		vo = examQstnMapper.select(vo);
		vo = sysFileService.getFile(vo, new ExamFileHandler());
		this.atclUrlToView(vo);
		return new ProcessResultVO<ExamQuestionVO>().setResultSuccess()
				.setReturnVO(vo);
	}

	/**
	 * 시험문제 정보를 등록하고 결과를 반환한다.
	 * @param vo
	 * @return
	 */
	@Override
	public	ProcessResultVO<ExamQuestionVO> addQstn(ExamQuestionVO vo)  throws Exception{
		//-- 주키를 생성후 파일 저장
		this.atclUrlToPersist(vo);
		if(vo.getExamQstnSn() == null || vo.getExamQstnSn() == 0) {
			//---- 신규 코드 세팅
			vo.setExamQstnSn(examQstnMapper.selectKey());
		}
		examQstnMapper.insert(vo);
		examQstnMapper.forUpdate(vo);
		sysFileService.bindFile(vo, new ExamFileHandler());

		return ProcessResultVO.success();
	}

	/**
	 * 시험문제은행에서 가져온 문제를 등록한다.
	 * @param vo
	 * @return
	 */
	@Override
	public	ProcessResultVO<ExamQuestionVO> addQbank(ExamQuestionVO vo) throws Exception{
		String[] qstnList = StringUtil.split(vo.getStrQstnSn(),"|");
		String[] qstnCtgrCdList = StringUtil.split(vo.getStrCtgrCd(),"|");
		ExamQuestionVO eqVO = examQstnMapper.selectMaxQstnNo(vo);
		int	maxQstnNo = eqVO.getQstnNo();
		for (int i = 0; i < qstnList.length; i++) {
			//-- 문제 은행에서 정보 가져오기

			ExamQbankQstnVO examQbankQstnVO = new ExamQbankQstnVO();
			examQbankQstnVO.setSbjCd(vo.getSbjCd());
			examQbankQstnVO.setCtgrCd(qstnCtgrCdList[i]);
			examQbankQstnVO.setQstnSn(Integer.parseInt(qstnList[i]));
			examQbankQstnVO = examQbankQstnMapper.select(examQbankQstnVO);

			ExamQuestionVO examQuestionVO = new ExamQuestionVO();
			examQuestionVO.setCrsCreCd(vo.getCrsCreCd());
			examQuestionVO.setExamSn(vo.getExamSn());
			//examQuestionVO.setExamQstnSn(examQstnMapper.selectExamQstnSn().getReturnVO().getExamQstnSn());

			//-- 시험 번호 안들어왔을 경우 순번으로 저장
			if(ValidationUtils.isZeroNull(vo.getQstnNo())) {
				examQuestionVO.setQstnNo(maxQstnNo+(i+1));
			} else {
				examQuestionVO.setQstnNo(vo.getQstnNo());
			}

			examQuestionVO.setTitle(examQbankQstnVO.getTitle());
			examQuestionVO.setQstnType(examQbankQstnVO.getQstnType());
			examQuestionVO.setQstnCts(examQbankQstnVO.getQstnCts());
			examQuestionVO.setEmpl1(examQbankQstnVO.getView1());
			examQuestionVO.setEmpl2(examQbankQstnVO.getView2());
			examQuestionVO.setEmpl3(examQbankQstnVO.getView3());
			examQuestionVO.setEmpl4(examQbankQstnVO.getView4());
			examQuestionVO.setEmpl5(examQbankQstnVO.getView5());
//			examQuestionVO.setEmpl6(examQbankQstnVO.getView6());
//			examQuestionVO.setEmpl7(examQbankQstnVO.getView7());
//			examQuestionVO.setEmpl8(examQbankQstnVO.getView8());
//			examQuestionVO.setEmpl9(examQbankQstnVO.getView9());
//			examQuestionVO.setEmpl10(examQbankQstnVO.getView10());
			examQuestionVO.setRgtAnsr(examQbankQstnVO.getRgtAnsr());
			examQuestionVO.setQstnExpl(examQbankQstnVO.getQstnExpl());
			//---- 신규 코드 세팅
			examQuestionVO.setExamQstnSn(examQstnMapper.selectKey());

			examQstnMapper.insert(examQuestionVO);
			examQstnMapper.forUpdate(examQuestionVO);
		}
		return ProcessResultVO.success();
	}

	/**
	 * 시험문제 정보를 수정한다.
	 * @param vo
	 * @return
	 */
	@Override
	public  ProcessResultVO<ExamQuestionVO> editQstn(ExamQuestionVO vo) throws Exception{
		this.atclUrlToPersist(vo);
		examQstnMapper.update(vo);
		sysFileService.bindFileUpdate(vo, new ExamFileHandler());
		return ProcessResultVO.success();
	}

	/**
	 * 시험문제 정보를 삭제한다.
	 * @param vo
	 * @return
	 */
	@Override
	public ProcessResultVO<ExamQuestionVO> deleteQstn(ExamQuestionVO vo) throws Exception{
		vo = this.viewQstn(vo).getReturnVO();

		int delQstnNo = vo.getQstnNo();

		sysFileService.removeFile(vo, new ExamFileHandler());
		examQstnMapper.delete(vo);

		List<ExamQuestionVO> qstnArray = examQstnMapper.list(vo);
		// 문제번호 중간 삭제시 삭제된 문제번호 다음 문제번호 소팅을 새로함
		for(ExamQuestionVO questionVO : qstnArray){
			if(questionVO.getQstnNo() > delQstnNo){
				questionVO.setQstnNo(questionVO.getQstnNo() -1);
			}
		}
		if(qstnArray != null && qstnArray.size() > 0) {
			// 변경된 시스템코드 어래이를 일괄 저장.
			examQstnMapper.updateBatch(qstnArray);
		}
		return ProcessResultVO.success();
	}

	/**
	 * 시험 응시자의 전체 목록을 반환한다.
	 * @param vo
	 * @return
	 */
	@Override
	public	ProcessResultListVO<ExamStareVO> listExamStare(ExamVO vo) throws Exception{
		ProcessResultListVO<ExamStareVO> resultList = new ProcessResultListVO<ExamStareVO>();
		try {
			List<ExamStareVO> returnList =  examStareMapper.list(vo);
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
	 * 시험 응시자의 페이징 목록을 반환한다.
	 * @param vo
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 */
	@Override
	public	ProcessResultListVO<ExamStareVO> listExamStarePaging(ExamVO vo) throws Exception{
		
		ProcessResultListVO<ExamStareVO> resultList = new ProcessResultListVO<ExamStareVO>();
		
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(vo.getCurPage());
		paginationInfo.setRecordCountPerPage(vo.getListScale());
		paginationInfo.setPageSize(vo.getPageScale());
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		
		try {
			// 전체 목록 수
			int totalCount = examStareMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<ExamStareVO> returnList =  examStareMapper.listPageing(vo);
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
	 * 시험 응시 단인행 정보를 반환한다.
	 * @param vo
	 * @return
	 */
	@Override
	public	ProcessResultVO<ExamStareVO> viewStare(ExamStareVO vo) throws Exception{
		ProcessResultVO<ExamStareVO> resultVO = new ProcessResultVO<ExamStareVO>();
		try {
			ExamStareVO returnVO = examStareMapper.select(vo);
			if(returnVO == null) {
					returnVO = new ExamStareVO();
			}
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
	 * 서술형 시험의 모사율을 조회한다.
	 * @param vo
	 * @return
	 */
	@Override
	public	ProcessResultVO<ExamStareVO> viewStareCopyRatio(ExamStareVO vo) throws Exception{
		ProcessResultVO<ExamStareVO> resultVO = new ProcessResultVO<ExamStareVO>();
		try {
			ExamStareVO returnVO = examStareMapper.selectCopyRatio(vo);
			if(returnVO == null) {
					returnVO = new ExamStareVO();
			}
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
	 * 시험 응시 단인행 정보를 반환한다. - 수정 : 기존 로직은 조회 안되면 초기화한다. 없으면 null로 반환
	 */
	@Override
	public	ProcessResultVO<ExamStareVO> viewStareNoCont(ExamStareVO vo) throws Exception{
		ExamStareVO returnVO = null;
		try {
			returnVO = examStareMapper.select(vo);
		} catch (Exception e) {
			throw new ServiceProcessException("수강생 성적 조회 오류");
		}
		return new ProcessResultVO<ExamStareVO>(returnVO, 1);
	}


	/**
	 * 시험 평가를 저장하고 결과를 반환한다. (단건)
	 * @param iExamStareVO
	 * @return
	 */
	@Override
	public	ProcessResultVO<ExamStareVO> addStareRate(ExamStareVO vo) throws Exception{

		//-- 기존 값 가져오기
		ExamStareVO examStareVO = examStareMapper.select(vo);
		examStareVO.setAtchCts(vo.getAtchCts());
		examStareVO.setGetScores(vo.getGetScores());
		examStareVO.setRateYn("Y");
		examStareVO.setTotGetScore(vo.getTotGetScore());

		examStareMapper.update(examStareVO);

		ExamVO examVO = new ExamVO();
		examVO.setExamSn(vo.getExamSn());
		examVO.setCrsCreCd(vo.getCrsCreCd());
		examVO = examMapper.selectExam(examVO);

		
		//-- 자동 성적 처리 :
		EduResultVO eduResultVO = new EduResultVO();
		eduResultVO.setCrsCreCd(vo.getCrsCreCd());
		eduResultVO.setStdNo(vo.getStdNo());
		eduResultVO.setSbjCd(examVO.getSbjCd());

		vo.setScoreCategory("EXAM");
		vo.setScoreSaveType("RATE");
		eduResultService.addEduResultSp(eduResultVO, vo);

		return ProcessResultVO.success();
	}
	
	@Override
	public	ProcessResultVO<ExamStareVO> addStareScore(ExamStareVO vo) throws Exception{
		
		examStareMapper.insert(vo);
		
		EduResultVO eduResultVO = new EduResultVO();
		eduResultVO.setCrsCreCd(vo.getCrsCreCd());
		eduResultVO.setStdNo(vo.getStdNo());
		eduResultVO.setSbjCd("");
		
		//오프라인 점수 미정
		vo.setScoreCategory("");
		vo.setScoreSaveType("");
		eduResultService.addEduResultSp(eduResultVO, vo);
		return ProcessResultVO.success();
	}
	
	@Override
	public	ProcessResultVO<ExamStareVO> editStareScore(ExamStareVO vo) throws Exception{
		examStareMapper.update(vo);
		
		EduResultVO eduResultVO = new EduResultVO();
		eduResultVO.setCrsCreCd(vo.getCrsCreCd());
		eduResultVO.setStdNo(vo.getStdNo());
		eduResultVO.setSbjCd("");
		
		//오프라인 점수 미정
		vo.setScoreCategory("");
		vo.setScoreSaveType("");
		eduResultService.addEduResultSp(eduResultVO, vo);
		return ProcessResultVO.success();
	}
	
	/**
	 * 시험 평가 점수를 등록하고 결과를 반환한다. (여러건)
	 * 구분자("|")로 구분된 학습자 번호, 학습자 스코어
	 * @param iExamStareVO
	 * @param strStdNo
	 * @param strScore
	 * @return
	 */
	@Override
	public	ProcessResultVO<ExamStareVO> addStareScoreAll(ExamStareVO vo, String[] strStdNoArray, String[] strScoreArray) throws Exception{
		for(int i = 0; i < strStdNoArray.length; i++) {
			vo.setStdNo(strStdNoArray[i]);
			vo.setTotGetScore(Double.parseDouble(strScoreArray[i]));
			if(examStareMapper.select(vo) == null){
				addStareScore(vo);
			}else {
				editStareScore(vo);
			}
		}
		return ProcessResultVO.success();
	}

	/**
	 * 시험 문제(시험지)를 조회한다. 객관식, 단답식, 서술형 문항 별로 랜덤 조회 후 insert
	 * @param iExamStareVO
	 * @return
	 */
	@Override
	@HrdApiScore
	//@HrdApiScore(category = Category.EXAM, saveType = SaveType.START)
	public	ProcessResultVO<ExamStareVO> addPaper(ExamStareVO vo, ExamVO examVO, List<ExamQuestionVO> questionList) throws Exception{

		int examSelectPnt 				= examVO.getSelPnt();
		int examShortAnswerPnt 			= examVO.getShortPnt();
		int examDescriptionAnswerPnt 	= examVO.getDesPnt();
		
		String qstnNos = "";
		String qstnRgtAnsrs = "";
		String qstnScores = "";
		String stareAnss ="";
		
		for(int i = 0; i < questionList.size(); i++) {
			ExamQuestionVO examQuestionVO = questionList.get(i);
			qstnNos += "@#"+ examQuestionVO.getExamQstnSn();
			if(!examQuestionVO.getQstnType().equals("J")){
				qstnRgtAnsrs += "@#"+examQuestionVO.getRgtAnsr();
			}else{
				qstnRgtAnsrs += "@#$%";
			}
			stareAnss +="@#";
			if(examQuestionVO.getQstnType().equals("D")){//단답형
				qstnScores += "@#"+examShortAnswerPnt;
			}else if(examQuestionVO.getQstnType().equals("J")){//서술형
				qstnScores += "@#"+examDescriptionAnswerPnt;
			}else if(examQuestionVO.getQstnType().equals("S") || examQuestionVO.getQstnType().equals("K")) {//선택형,진위형
				qstnScores += "@#"+examSelectPnt;
			}else {
				qstnScores += "@#";
			}
		}
		
		qstnNos += "@#";
		qstnRgtAnsrs += "@#";
		qstnScores += "@#";
		stareAnss += "@#";

		vo.setQstnNos(qstnNos);
		vo.setQstnRgtAnsrs(qstnRgtAnsrs);
		vo.setQstnScores(qstnScores);
		vo.setStareAnss(stareAnss);
		
		ProcessResultVO<ExamStareVO> resultVO = new ProcessResultVO<ExamStareVO>();
		try {
			//학습자의 시험응시를 저장
			examStareMapper.addPaper(vo);
			ExamStareVO returnVO = examStareMapper.select(vo);
			
			vo.setScoreCategory("EXAM");
			vo.setScoreSaveType("START");
			
			resultVO.setReturnVO(returnVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			throw new ServiceProcessException("시험 제출 도중 오류가 발생하였습니다. 반복되는 경우 관리자에게 문의바랍니다.");
		}
		
		//저장 후 학습자 시험 조회
		return resultVO;
	}
	
	/**
	 * 학습자의 시험 응시를 저장하고 결과를 반환한다. (시작)
	 * @param vo
	 * @return
	 */
	@Override
	//@HrdApiScore(category = Category.EXAM, saveType = SaveType.START)
	public	ProcessResultVO<ExamStareVO> addPaperSubmitStart(ExamStareVO vo, ExamVO examVO) throws Exception{
		vo.setScoreSaveType("START");
		return addSumitLogic(vo, examVO, "START");
	}
	
	/**
	 * 학습자의 시험 응시를 저장하고 결과를 반환한다. (종료)
	 * @param vo
	 * @return
	 */
	@Override
	//@HrdApiScore(category = Category.EXAM, saveType = SaveType.END)
	public	ProcessResultVO<ExamStareVO> addPaperSubmitEnd(ExamStareVO vo, ExamVO examVO) throws Exception{
		vo.setScoreSaveType("END");
		return addSumitLogic(vo, examVO, "END");
	}
	
	private ProcessResultVO<ExamStareVO> addSumitLogic(ExamStareVO vo, ExamVO examVO, String saveType) throws Exception{
		//-- 문제 답 가져오기
		ExamStareVO resultVO = examStareMapper.selectExamQstnNo(vo);

		vo.setQstnNos(resultVO.getQstnNos());
		if("".equals(StringUtil.nvl(vo.getStareCnt())))
			vo.setStareCnt(resultVO.getStareCnt());
		if("".equals(StringUtil.nvl(vo.getStareTm())))
			vo.setStareTm(resultVO.getStareTm()*60);

		ExamQuestionVO examQuestionVO = new ExamQuestionVO();
		examQuestionVO.setStrExamQstnSn(resultVO.getQstnNos());
		examQuestionVO.setExamSn(vo.getExamSn());
		examQuestionVO.setCrsCreCd(vo.getCrsCreCd());
		examQuestionVO.setGubun("RANDOM");
		List<ExamQuestionVO> resultQuestionList =  this.listQstn(examQuestionVO).getReturnList();
		
		String qstnScore = "";
		double totScore = 0;
		for(int i=0; i < resultQuestionList.size(); i++) {
			ExamQuestionVO iExamQuestionVO = resultQuestionList.get(i);

			String strExamQstnNo = vo.getQstnNos().substring(2, vo.getQstnNos().length()-2);
			String strStareAnss = "";
			String examQstnScores = resultVO.getQstnScores();
			if(StringUtil.isNotNull(vo.getStareAnss())) strStareAnss = vo.getStareAnss().substring(2, vo.getStareAnss().length()-2);

			String[] examQstnNo = strExamQstnNo.split("@#");
			String[] stareAnss = strStareAnss.split("@#");
			String[] getQstnScoreArr = StringUtil.split(StringUtil.substring(examQstnScores, 2, examQstnScores.length() - 2),"@#");
			
			for(int j=0; j < examQstnNo.length; j++) {
				String ans = "";
			
				//정답 rtrim, ltrim 처리 2015.11.04 김현욱 수정
				String qstnNo = examQstnNo[j];
				if(stareAnss.length > j){
					ans = stareAnss[j].replaceAll("\\s+$","");//rtrim
					ans = ans.replaceAll("^\\s+","");//ltrim
				}
				
				if(Integer.parseInt(qstnNo) == iExamQuestionVO.getExamQstnSn()){
					//문제의 점수가 아닌 수강생이 응시하는 문항의 score로 세팅 
					double score = 0;
					if("".equals(StringUtil.nvl(getQstnScoreArr[j]))) {
						score = 0;
					}else {
						try {
							score = Double.parseDouble(getQstnScoreArr[j]);
						} catch (NumberFormatException e) {
							score = 0;
						}
					}
					
					if(!iExamQuestionVO.getQstnType().equals("D")){
						if(ans.equals(iExamQuestionVO.getRgtAnsr())){
							totScore += score;
							qstnScore += "@#"+score;
						}else{
							totScore += 0;
							qstnScore += "@#"+0;
						}
					}else{
						String[] arrAnsr = StringUtil.split(iExamQuestionVO.getRgtAnsr(), "|");
						String confirm = "";

						for(int k=0; k < arrAnsr.length; k++) {
							if(ans.equals(arrAnsr[k])){
								confirm = "true";
							}
						}
						if(confirm.equals("true")){
							totScore += score;
							qstnScore += "@#"+score;
						}else{
							totScore += 0;
							qstnScore += "@#"+0;
						}

					}
				}
			}
		}
		qstnScore += "@#";
		vo.setGetScores(qstnScore);
		vo.setTotGetScore(totScore);
		examStareMapper.addPaperSubmit(vo);


		//-- 자동 성적 처리 :
		EduResultVO eduResultVO = new EduResultVO();
		eduResultVO.setCrsCreCd(vo.getCrsCreCd());
		eduResultVO.setStdNo(vo.getStdNo());
		eduResultVO.setSbjCd(examVO.getSbjCd());

		vo.setScoreCategory("EXAM");
		eduResultService.addEduResultSp(eduResultVO, vo);

		return ProcessResultVO.success();
	}

	/**
	 * 시험 평가를 완료하고 결과를 반환한다.
	 * @param iExamStareVO
	 * @return
	 */
	@Override
	public ProcessResultVO<ExamStareVO> editExamComplete(ExamStareVO vo) throws Exception{
		examStareMapper.updateExamComplete(vo);
		return ProcessResultVO.success();
	}

	/**
	 * 단답식, 주관식 평가
	 */
	@Override
	public	ProcessResultVO<ExamStareVO> rateStareDan(ExamStareVO vo) throws Exception{

		//-- 문제 답 가져오기
		ExamQuestionVO examQuestionVO = new ExamQuestionVO();
		examQuestionVO.setCrsCreCd(vo.getCrsCreCd());
		examQuestionVO.setExamSn(vo.getExamSn());
		examQuestionVO.setExamQstnSn(vo.getExamQstnSn());
		examQuestionVO = examQstnMapper.select(examQuestionVO);

		//-- 문제의 목록
		ExamQuestionVO eqvo = new ExamQuestionVO();
		eqvo.setCrsCreCd(vo.getCrsCreCd());
		eqvo.setExamSn(vo.getExamSn());
		List<ExamQuestionVO> examQuestionList = examQstnMapper.list(eqvo);

		String[] stdNoArr = StringUtil.split(vo.getStdNo(),"|");
		String[] getScoreArr = StringUtil.split(vo.getGetScores(),"|");

		for(int i=0; i < stdNoArr.length; i++) {
			String stdNo = stdNoArr[i];
			//-- 학습자의 응답 정보를 가져온다.
			ExamStareVO esvo = new ExamStareVO();
			esvo.setCrsCreCd(vo.getCrsCreCd());
			esvo.setExamSn(vo.getExamSn());
			esvo.setStdNo(stdNo);
			esvo = examStareMapper.select(esvo);

			//-- 제출한 답이 없는 경우 문제 목록을 돌려 값을 세팅힌다.
			String stareAnss = "";
			if("".equals(esvo.getStareAnss())) {
				for(ExamQuestionVO ieqvo : examQuestionList) {
					stareAnss += "@#";
				}
				stareAnss += "@#";
			} else {
				stareAnss = esvo.getStareAnss();
			}
			String getScores = "";
			if("".equals(esvo.getGetScores())) {
				for(ExamQuestionVO ieqvo : examQuestionList) {
					getScores += "@#0";
				}
				getScores += "@#";
			} else {
				getScores += esvo.getGetScores();
			}
			String examNos = esvo.getQstnNos();

			String[] examNoAr = StringUtil.split(StringUtil.substring(examNos, 2, examNos.length() - 2),"@#");
			String[] getScoreAr = StringUtil.split(StringUtil.substring(getScores, 2, getScores.length() - 2),"@#");

			String stdGetScore = "";
			int getTotalScore = 0;
			for(int j=0; j < examNoAr.length; j++ ) {
				if(vo.getExamQstnSn() == Integer.parseInt(examNoAr[j])) {
					stdGetScore += "@#"+getScoreArr[i];
					getTotalScore += Double.parseDouble(StringUtil.nvl(getScoreArr[i],"0"));
				} else {
					stdGetScore += "@#"+getScoreAr[j];
					getTotalScore += Double.parseDouble(StringUtil.nvl(getScoreAr[j],"0"));
				}
			}
			if(!"".equals(stdGetScore)) stdGetScore += "@#";

			esvo.setRgtAnsr(stareAnss);
			esvo.setGetScores(stdGetScore);
			esvo.setTotGetScore(getTotalScore);

			//-- 결과 저장.
			examStareMapper.update(esvo);

			//-- 자동 성적 처리 :
			EduResultVO eduResultVO = new EduResultVO();
			eduResultVO.setCrsCreCd(vo.getCrsCreCd());
			eduResultVO.setStdNo(stdNo);
			eduResultVO.setSbjCd("");

			esvo.setScoreCategory("EXAM");
			esvo.setScoreSaveType("RATE");
			eduResultService.addEduResultSp(eduResultVO, esvo);
		}
		return ProcessResultVO.success();
	}
	
	private void atclUrlToPersist(ExamQuestionVO iExamQuestionVO) {
		iExamQuestionVO.setQstnCts(StringUtil.replaceUrlToPersist(iExamQuestionVO.getQstnCts()));
	}

	private void atclUrlToView(ExamQuestionVO iExamQuestionVO) {
		iExamQuestionVO.setQstnCts(StringUtil.replaceUrlToBrowser(iExamQuestionVO.getQstnCts()));
	}

	/**
	 * 시험 문제 미리보기
	 */
	@Override
	public	ProcessResultListVO<ExamQuestionVO> listPreview(ExamQuestionVO vo) throws Exception{
		ProcessResultListVO<ExamQuestionVO> resultList = new ProcessResultListVO<ExamQuestionVO>();
		try {
			List<ExamQuestionVO> returnList =  examQstnMapper.listPreview(vo);
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
	public ProcessResultVO<ExamQuestionVO> editQstnSort(ExamQuestionVO vo)  throws Exception{
		String[] qstnSnArr = StringUtil.split(vo.getExamQstnSnSort(), "|");
		String[] qstnNoArr = StringUtil.split(vo.getQstnNoSort(), "|");

		for(int i=0; i<qstnSnArr.length; i++){
			vo.setExamQstnSn(Integer.parseInt(qstnSnArr[i]));
			vo.setQstnNo(Integer.parseInt(qstnNoArr[i]));
			examQstnMapper.updateSort(vo);
		}
		return ProcessResultVO.success();
	}

	/**
	 * 샘플 엑셀파일 다운로드
	 * @param (HashMap<String, String> titles
	 * @param OutputStream os
	 * @throws ServiceProcessException
	 */
	@Override
	public void sampleExcelDownload(HashMap<String, String> titles, OutputStream os, String orgCd) throws ServiceProcessException {
		try{
			int rowNum = 0;

			WritableWorkbook workbook = Workbook.createWorkbook(os);

			WritableSheet sheet = workbook.createSheet("QuestionBank", 0); //-- 시트 만들기

			//첫번째열 문서 제목줄 만들기
			sheet.addCell(ExcelUtil.createComment(0,rowNum,"left",titles.get("info1")));
			sheet.setRowView(rowNum, 400);
			sheet.mergeCells(0, rowNum, 10, rowNum); //-- 병합
			rowNum++;
			sheet.addCell(ExcelUtil.createComment(0,rowNum,"left",titles.get("info2")));
			sheet.setRowView(rowNum, 400);
			sheet.mergeCells(0, rowNum, 10, rowNum); //-- 병합
			rowNum++;
			sheet.addCell(ExcelUtil.createComment(0,rowNum,"left",titles.get("info3")));
			sheet.setRowView(rowNum, 400);
			sheet.mergeCells(0, rowNum, 10, rowNum); //-- 병합
			rowNum++;
			sheet.addCell(ExcelUtil.createComment(0,rowNum,"left",titles.get("info4")));
			sheet.setRowView(rowNum, 400);
			sheet.mergeCells(0, rowNum, 10, rowNum); //-- 병합
			rowNum++;
			sheet.addCell(ExcelUtil.createComment(0,rowNum,"left",titles.get("info5")));
			sheet.setRowView(rowNum, 400);
			sheet.mergeCells(0, rowNum, 10, rowNum); //-- 병합
			rowNum++;
			sheet.addCell(ExcelUtil.createComment(0,rowNum,"left",titles.get("info6")));
			sheet.setRowView(rowNum, 400);
			sheet.mergeCells(0, rowNum, 10, rowNum); //-- 병합
			rowNum++;
			sheet.addCell(ExcelUtil.createComment(0,rowNum,"left",titles.get("info7")));
			sheet.setRowView(rowNum, 400);
			sheet.mergeCells(0, rowNum, 10, rowNum); //-- 병합
			rowNum++;
			sheet.addCell(ExcelUtil.createComment(0,rowNum,"left",titles.get("info8")));
			sheet.setRowView(rowNum, 400);
			sheet.mergeCells(0, rowNum, 10, rowNum); //-- 병합
			rowNum++;
			sheet.addCell(ExcelUtil.createComment(0,rowNum,"left",titles.get("info9")));
			sheet.setRowView(rowNum, 400);
			sheet.mergeCells(0, rowNum, 10, rowNum); //-- 병합
			rowNum++;
			sheet.addCell(ExcelUtil.createComment(0,rowNum,"left",titles.get("info10")));
			sheet.setRowView(rowNum, 400);
			sheet.mergeCells(0, rowNum, 10, rowNum); //-- 병합
			rowNum++;
			sheet.addCell(ExcelUtil.createComment(0,rowNum,"left",titles.get("info11")));
			sheet.setRowView(rowNum, 400);
			sheet.mergeCells(0, rowNum, 10, rowNum); //-- 병합
			rowNum++;
			sheet.addCell(ExcelUtil.createComment(0,rowNum,"left",titles.get("info12")));
			sheet.setRowView(rowNum, 400);
			sheet.mergeCells(0, rowNum, 10, rowNum); //-- 병합

			rowNum++;

			//-- 타이틀 만들기 user.title.userinfo.userid
			rowNum++;
			sheet.addCell(ExcelUtil.createHeader(0,rowNum,titles.get("type")));
			sheet.addCell(ExcelUtil.createHeader(1,rowNum,titles.get("no")));
			sheet.addCell(ExcelUtil.createHeader(2,rowNum,titles.get("title")));
			sheet.addCell(ExcelUtil.createHeader(3,rowNum,titles.get("qstncts")));
			sheet.addCell(ExcelUtil.createHeader(4,rowNum,titles.get("item1")));
			sheet.addCell(ExcelUtil.createHeader(5,rowNum,titles.get("item2")));
			sheet.addCell(ExcelUtil.createHeader(6,rowNum,titles.get("item3")));
			sheet.addCell(ExcelUtil.createHeader(7,rowNum,titles.get("item4")));
			sheet.addCell(ExcelUtil.createHeader(8,rowNum,titles.get("item5")));
			sheet.addCell(ExcelUtil.createHeader(9,rowNum,titles.get("rightansr")));
			sheet.addCell(ExcelUtil.createHeader(10,rowNum,titles.get("comment")));

			//-- 셀의 넓이 조절
			sheet.setColumnView(0, 11);
			sheet.setColumnView(1, 15);
			sheet.setColumnView(2, 30);
			sheet.setColumnView(3, 40);
			sheet.setColumnView(4, 10);
			sheet.setColumnView(5, 10);
			sheet.setColumnView(6, 10);
			sheet.setColumnView(7, 10);
			sheet.setColumnView(8, 10);
			sheet.setColumnView(9, 30);
			sheet.setColumnView(10, 30);

			//-- 셀의 높이 조절
			sheet.setRowView(rowNum, 1500);
			workbook.write();
			workbook.close();

		}catch (Exception e) {
			throw new ServiceProcessException("Failed! Make excel file.", e);
		}
	}

	@Override
	public ProcessResultListVO<ExamQuestionVO> excelUploadValidationCheck(String fileName,
			String filePath) throws ServiceProcessException {

		ProcessResultListVO<ExamQuestionVO> resultVO = new ProcessResultListVO<ExamQuestionVO>();

		Workbook workbook	= null;
		Sheet sheet			= null;

		String errMsg = "";
		try {
			workbook = Workbook.getWorkbook(new File(filePath + "/" + fileName));
		} catch (BiffException ex1) {
			ex1.printStackTrace();
		} catch (IOException ex2) {
			ex2.printStackTrace();
		}

		sheet = workbook.getSheet(0);
		try {

		} catch (Exception e) {
			throw new ServiceProcessException("Failed read excel : " + e.getMessage(), e);
		}

		List<ExamQuestionVO> questionBankList = new ArrayList<ExamQuestionVO>();

		String regex = "^[a-zA-Z가-힣0-9,|]*$";

		for (int j = 14; j < sheet.getRows(); j++) {
			Cell cellQstnType = sheet.getCell(0, j); 			// 문제유형
			Cell cellQstnNo = sheet.getCell(1, j); 				// 번호
			Cell cellQstnTitle = sheet.getCell(2, j); 				// 제목
			Cell cellQstnCts = sheet.getCell(3, j); 				// 문제내용
			Cell cellQstnEmpl1 = sheet.getCell(4, j); 		// 보기1
			Cell cellQstnEmpl2 = sheet.getCell(5, j); 		// 보기2
			Cell cellQstnEmpl3 = sheet.getCell(6, j); 		// 보기3
			Cell cellQstnEmpl4 = sheet.getCell(7, j); 		// 보기4
			Cell cellQstnEmpl5 = sheet.getCell(8, j); 		// 보기5
			Cell cellRgtAnsr = sheet.getCell(9, j); 				// 정답
			Cell cellQstnExpl = sheet.getCell(10, j); 			// 해설

			String errorCode = "";

			ExamQuestionVO qstnvo = new ExamQuestionVO();

			qstnvo.setLineNo((j-13)+"");
			qstnvo.setQstnType(cellQstnType.getContents().trim().toUpperCase());
			if(StringUtil.isNumber(cellQstnNo.getContents().trim())){
				qstnvo.setQstnNo(Integer.parseInt(cellQstnNo.getContents().trim()));
			}
			qstnvo.setTitle(cellQstnTitle.getContents().trim());
			qstnvo.setQstnCts(cellQstnCts.getContents().trim());
			qstnvo.setEmpl1(cellQstnEmpl1.getContents().trim());
			qstnvo.setEmpl2(cellQstnEmpl2.getContents().trim());
			qstnvo.setEmpl3(cellQstnEmpl3.getContents().trim());
			qstnvo.setEmpl4(cellQstnEmpl4.getContents().trim());
			qstnvo.setEmpl5(cellQstnEmpl5.getContents().trim());

			if(ValidationUtils.isEmpty(qstnvo.getQstnNo())){
				errorCode += "|"+"EMPTYQSTNNO";
			}
			if(qstnvo.getQstnNo() < 1){
				errorCode += "|"+"TYPEQSTNNO";
			}
			if("S".equals(qstnvo.getQstnType())){
				qstnvo.setRgtAnsr(StringUtil.toHalfChar(cellRgtAnsr.getContents().trim().toUpperCase()));
			} else{
				qstnvo.setRgtAnsr(StringUtil.toHalfChar(cellRgtAnsr.getContents().trim()));
			}
			qstnvo.setQstnExpl(cellQstnExpl.getContents().trim());

			if(!"J".equals(qstnvo.getQstnType()) && !"K".equals(qstnvo.getQstnType())
				&& !"S".equals(qstnvo.getQstnType()) && !"D".equals(qstnvo.getQstnType())	){
				errorCode += "|"+"TYPEQSTNTYPE";
			}
			if(ValidationUtils.isEmpty(qstnvo.getTitle())){
				errorCode += "|"+"EMPTYTITLE";
			}
			if(ValidationUtils.isEmpty(qstnvo.getQstnCts())){
				errorCode += "|"+"EMPTYQSTNCTS";
			}
			if(ValidationUtils.isEmpty(qstnvo.getRgtAnsr())){
				errorCode += "|"+"EMPTYRGTANSR";
			}
			if(ValidationUtils.isEmpty(qstnvo.getQstnExpl())){
				//errorCode += "|"+"EMPTYQSTNEXPL";
			}

			if("K".equals(qstnvo.getQstnType()) ){
				if(ValidationUtils.isEmpty(qstnvo.getEmpl1())){
					errorCode += "|"+"EMPTYVIEW1";
				}
				if(ValidationUtils.isEmpty(qstnvo.getEmpl2())){
					errorCode += "|"+"EMPTYVIEW2";
				}
				if(ValidationUtils.isEmpty(qstnvo.getEmpl3())){
					errorCode += "|"+"EMPTYVIEW3";
				}
				if(ValidationUtils.isEmpty(qstnvo.getEmpl4())){
					errorCode += "|"+"EMPTYVIEW4";
				}
				if(!StringUtil.isNumber(qstnvo.getRgtAnsr())){
					errorCode += "|"+"TYPERGTANSR";
				} else {
					if(Integer.parseInt(qstnvo.getRgtAnsr()) > 5){
						errorCode += "|"+"TYPERGTANSR";
					}
					if(ValidationUtils.isEmpty(qstnvo.getEmpl5()) && Integer.parseInt(qstnvo.getRgtAnsr()) > 4 ){
						errorCode += "|"+"TYPERGTANSR";
					}
				}

			}else if("S".equals(qstnvo.getQstnType()) ){
				if(!"O".equals(qstnvo.getRgtAnsr()) && !"X".equals(qstnvo.getRgtAnsr())){
					errorCode += "|"+"TYPERGTANSR";
				}
			} else if("D".equals(qstnvo.getQstnType()) ){
				/*
				if(!qstnvo.getRgtAnsr().matches(regex)){
					errorCode += "|"+"TYPERGTANSR";
				}
				*/
			}

			qstnvo.setErrorCode(errorCode);
			questionBankList.add(qstnvo);
		}


		resultVO.setReturnList(questionBankList);
		resultVO.setResult(1);
		return resultVO;

	}

	/**
	 * 시험 문제은행 문제 일괄 등록
	 * @param List<UserInfoVO> userList
	 * @return
	 */
	@Override
	public ProcessResultVO<ExamQuestionVO> addQstnBatch (List<ExamQuestionVO> questionBankList) throws Exception{
		for(ExamQuestionVO vo : questionBankList) {
			examQstnMapper.insert(vo);
			examQstnMapper.forUpdate(vo);
		}
		return new ProcessResultVO<ExamQuestionVO>().setResultSuccess().setReturnVO(new ExamQuestionVO());
	}

	@Override
	public List<ExamVO> listExamScore(ExamVO vo) throws Exception {
		return examMapper.listByStdNo(vo);
	}

}