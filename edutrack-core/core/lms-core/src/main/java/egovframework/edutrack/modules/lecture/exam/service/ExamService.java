package egovframework.edutrack.modules.lecture.exam.service;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.annotation.HrdApiScore;
import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface ExamService {

	/**
	 * 시험 목록을 반환한다.(교수자)
	 * @param vo
	 * @return
	 */
	public abstract ProcessResultListVO<ExamVO> listExam(ExamVO vo) throws Exception;

	/**
	 * 시험 목록을 반환한다.(학습자)
	 * @param vo
	 * @return
	 */
	public abstract ProcessResultListVO<ExamVO> listExamStd(ExamVO vo) throws Exception;

	/**
	 * 시험 정보를 반환한다.
	 * @param vo
	 * @return
	 */
	public abstract ProcessResultVO<ExamVO> viewExam(ExamVO vo) throws Exception;

	/**
	 * 시험 정보를 등록하고 결과를 반환한다.
	 * @param vo
	 * @return
	 */
	public abstract ProcessResultVO<ExamVO> addExam(ExamVO vo) throws Exception;

	/**
	 * 시험 등록 완료를 처리하고 결과를 반환한다.
	 * @param vo
	 * @return
	 */
	public abstract ProcessResultVO<ExamVO> addRegistExam(ExamVO vo);

	/**
	 * 학습자를 재응시 할 수 있도록 처리하고 결과를 반환한다.
	 * @param examStareVO
	 * @return
	 */
	//@HrdApiScore(category = Category.EXAM, saveType = SaveType.RESET)
	@HrdApiScore
	public abstract ProcessResultVO<ExamStareVO> removeStare(ExamStareVO vo) throws Exception;
	
	/**
	 * 시험 정보를 수정하고 결과를 반환한다.
	 * @param vo
	 * @return
	 */
	public abstract ProcessResultVO<ExamVO> editExam(ExamVO vo) throws Exception;

	/**
	 * 시험 정보를 삭제하고 결과를 반환한다.
	 * @param vo
	 * @return
	 */
	public abstract ProcessResultVO<ExamVO> deleteExam(ExamVO vo) throws Exception;

	/**
	 * 시험문제의 목록을 반환한다.
	 * @param vo
	 * @return
	 */
	public abstract ProcessResultListVO<ExamQuestionVO> listQstn(
			ExamQuestionVO vo) throws Exception;
	
	/**
	 * 시험문제의 목록을 랜덤하게 반환한다.
	 * @param vo
	 * @return
	 */
	public	ProcessResultListVO<ExamQuestionVO> randListQstn(ExamQuestionVO vo) throws Exception;

	/**
	 * 시험문제의 단일항목 정보를 반환한다.
	 * @param vo
	 * @return
	 */
	public abstract ProcessResultVO<ExamQuestionVO> viewQstn(
			ExamQuestionVO vo) throws Exception;

	/**
	 * 시험문제 정보를 등록하고 결과를 반환한다.
	 * @param vo
	 * @return
	 */
	public abstract ProcessResultVO<ExamQuestionVO> addQstn(
			ExamQuestionVO vo) throws Exception;

	/**
	 * 시험문제은행에서 가져온 문제를 등록한다.
	 * @param vo
	 * @return
	 */
	public abstract ProcessResultVO<ExamQuestionVO> addQbank(
			ExamQuestionVO vo) throws Exception;

	/**
	 * 시험문제 정보를 수정한다.
	 * @param vo
	 * @return
	 */
	public abstract ProcessResultVO<ExamQuestionVO> editQstn(
			ExamQuestionVO vo) throws Exception;

	/**
	 * 시험문제 정보를 삭제한다.
	 * @param vo
	 * @return
	 */
	public abstract ProcessResultVO<ExamQuestionVO> deleteQstn(
			ExamQuestionVO vo) throws Exception;

	/**
	 * 시험 응시자의 전체 목록을 반환한다.
	 * @param vo
	 * @return
	 */
	public abstract ProcessResultListVO<ExamStareVO> listExamStare(ExamVO vo) throws Exception;

	/**
	 * 시험 응시자의 페이징 목록을 반환한다.
	 * @param vo
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 */
	public abstract ProcessResultListVO<ExamStareVO> listExamStarePaging(
			ExamVO vo) throws Exception;

	/**
	 * 시험 응시 단인행 정보를 반환한다.
	 * @param vo
	 * @return
	 */
	public abstract ProcessResultVO<ExamStareVO> viewStare(ExamStareVO vo) throws Exception;
	
	/**
	 * 시험 모사 정보를 반환한다.
	 * @param vo
	 * @return
	 */
	public abstract ProcessResultVO<ExamStareVO> viewStareCopyRatio(ExamStareVO vo) throws Exception;
	
	public abstract ProcessResultVO<ExamStareVO> viewStareNoCont(ExamStareVO vo) throws Exception;

	/**
	 * 시험 평가를 저장하고 결과를 반환한다.
	 * @param iExamStareVO
	 * @return
	 */
	//@HrdApiScore(category = Category.EXAM, saveType = SaveType.RATE)
	@HrdApiScore
	public abstract ProcessResultVO<ExamStareVO> addStareRate(ExamStareVO vo) throws Exception;

	/**
	 * 시험 평가 점수를 등록하고 결과를 반환한다. (단건)
	 * @param vo
	 * @return
	 */
	public abstract ProcessResultVO<ExamStareVO> addStareScore(ExamStareVO vo) throws Exception;
	
	public abstract ProcessResultVO<ExamStareVO> editStareScore(ExamStareVO vo) throws Exception;

	/**
	 * 시험 평가 점수를 등록하고 결과를 반환한다. (여러건)
	 * 구분자("|")로 구분된 학습자 번호, 학습자 스코어
	 * @param iExamStareVO
	 * @param strStdNo
	 * @param strScore
	 * @return
	 */
	public abstract ProcessResultVO<ExamStareVO> addStareScoreAll(
			ExamStareVO vo, String[] strStdNoArray, String[] strScoreArray) throws Exception;

	/**
	 * 시험 문제(시험지)를 조회한다.
	 * @param examVO 
	 * @param questionList 
	 * @param iExamStareVO
	 * @return
	 */
	//@HrdApiScore(category = Category.EXAM, saveType = SaveType.START)
	@HrdApiScore
	public abstract ProcessResultVO<ExamStareVO> addPaper(ExamStareVO vo, ExamVO examVO, List<ExamQuestionVO> questionList) throws Exception;

	/**
	 * 학습자의 시험 응시를 저장하고 결과를 반환한다.(시작)
	 * @param vo
	 * @return
	 */
	//@HrdApiScore(category = Category.EXAM, saveType = SaveType.START)
	@HrdApiScore
	public abstract ProcessResultVO<ExamStareVO> addPaperSubmitStart(ExamStareVO vo, ExamVO examVO) throws Exception;
	
	/**
	 * 학습자의 시험 응시를 저장하고 결과를 반환한다.(종료)
	 * @param vo
	 * @return
	 */
	//@HrdApiScore(category = Category.EXAM, saveType = SaveType.END)
	@HrdApiScore
	public abstract ProcessResultVO<ExamStareVO> addPaperSubmitEnd(ExamStareVO vo, ExamVO examVO) throws Exception;

	/**
	 * 시험 평가를 완료하고 결과를 반환한다.
	 * @param iExamStareVO
	 * @return
	 */
	public abstract ProcessResultVO<ExamStareVO> editExamComplete(
			ExamStareVO vo) throws Exception;

	/**
	 * 단답식, 주관식 평가
	 */
	//@HrdApiScore(category = Category.EXAM, saveType = SaveType.RATE)
	@HrdApiScore
	public abstract ProcessResultVO<ExamStareVO> rateStareDan(ExamStareVO vo) throws Exception;

	/**
	 * 시험 문제 미리보기
	 */
	public abstract ProcessResultListVO<ExamQuestionVO> listPreview(
			ExamQuestionVO vo) throws Exception;

	/**
	 * 시험문제 순서를 수정한다.
	 * @param vo
	 * @return
	 */
	public abstract ProcessResultVO<ExamQuestionVO> editQstnSort(
			ExamQuestionVO vo) throws Exception;


	/**
	 * 샘플 엑셀파일 다운로드
	 * @param (HashMap<String, String> titles
	 * @param OutputStream os
	 * @throws ServiceProcessException
	 */
	public abstract void sampleExcelDownload(HashMap<String, String> titles, OutputStream os, String orgCd) throws ServiceProcessException;

	/**
	 * Upload 문제 정보 체크 하여 돌려 준다.
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public abstract ProcessResultListVO<ExamQuestionVO> excelUploadValidationCheck(
			String fileName, String filePath) throws ServiceProcessException;

	/**
	 * 시험 문제은행 문제 일괄 등록
	 * @param List<ExamQuestionVO> examList
	 * @return
	 */
	public abstract ProcessResultVO<ExamQuestionVO> addQstnBatch(
			List<ExamQuestionVO> questionBankList) throws Exception;

	/**
	 * 시험 유형별 문항 랜덤 조회
	 * @param examQuestionVO
	 * @param resultExamVO
	 * @return
	 */
	public abstract ProcessResultListVO<ExamQuestionVO> randListQstnType(ExamQuestionVO examQuestionVO, ExamVO resultExamVO);

	public abstract ProcessResultVO<ExamStareVO> removeStare(ExamStareVO examStareVO, List<ExamStareVO> creExamStareList);
	
	public abstract List<ExamVO> listExamScore(ExamVO vo) throws Exception;
	
}