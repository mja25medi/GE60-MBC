package egovframework.edutrack.modules.lecture.exam.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import java.util.List;
import egovframework.edutrack.modules.lecture.exam.service.ExamQuestionVO;
import egovframework.edutrack.modules.lecture.exam.service.ExamVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("examQstnMapper")
public interface ExamQstnMapper{

	/**
	 * 시험문제의 목록을 조회하여 반환한다.
	 *
	 * @param vo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ExamQuestionVO> list(ExamQuestionVO vo ) throws Exception;
	
	/**
	 * 시험문제의 목록을 랜덤하게 조회하여 반환한다.
	 *
	 * @param vo
	 * @return
	 */
	public List<ExamQuestionVO> randList(ExamQuestionVO vo) throws Exception;

	/**
	 * 시험문제의 단일 항목 정보를 조회하여 반환한다.
	 * @param vo
	 * @return
	 */
	public ExamQuestionVO select(ExamQuestionVO vo ) throws Exception;
	/**
	 * Select Key 조회
	 * @param int
	 * @return
	 */
	public int selectKey()  throws Exception;
	/**
	 * 시험문제 정보를 등록하고 결과를 반환한다.
	 * @param vo
	 * @return
	 */
	public int insert(ExamQuestionVO vo) throws Exception;

	/**
	 * 시험문제 정보를 수정하고 결과를 반환한다.
	 * @param vo
	 * @return
	 */
	public int update(ExamQuestionVO vo) throws Exception;

	/**
	 * 시험문제의 배점을 수정한다.
	 * @param vo
	 * @return
	 */
	public int updateScore(ExamQuestionVO vo) throws Exception;

	/**
	 * 해당 시험의 모든 시험문제를 삭제한다.
	 * @param iExamVO
	 * @return
	 */
	public int deleteAll(ExamVO iExamVO) throws Exception;

	/**
	 * 시험문제 정보를 삭제하고 결과를 반환한다.
	 * @param vo
	 * @return
	 */
	public int delete(ExamQuestionVO vo) throws Exception;

	/**
	 * 시험문제의 MAX_QSTN_NO 값을 검색하여 결과로 반환한다.
	 * @param vo
	 * @return
	 */
	public ExamQuestionVO selectMaxQstnNo(ExamQuestionVO vo) throws Exception;

	/**
	 * 시험문제의 고유번호 EXAM_QSTN_SN을 검색하여 결과롤 반환한다.
	 * @return
	 */
	public ExamQuestionVO selectExamQstnSn()  throws Exception;

	/**
	 * 시험 문제 목록 조회(미리보기용)
	 * @param vo
	 * @return
	 */
	public List<ExamQuestionVO> listPreview(ExamQuestionVO vo)  throws Exception;

	/**
	 * 시험문제 내용을 forUpdate한다.
	 * @param vo
	 * @return
	 */
	public int forUpdate(ExamQuestionVO vo)  throws Exception;

	/**
	 * 시험문제 순서를 수정하고 결과를 반환한다.
	 * @param vo
	 * @return
	 */
	public int updateSort(ExamQuestionVO vo)  throws Exception;

	/**
	 * 시험문제의 문제번호 EXAM_QSTN_NO을 검색하여 결과롤 반환한다.
	 * @return
	 */
	public ExamQuestionVO selectExamQstnNo(ExamQuestionVO vo)  throws Exception;

	/**
	 * 시험문제의 다중항목 정보를 변경하고 결과를 반환한다.
	 * @param List<BannerVO> bannerArray
	 * @return
	 */
	public int updateBatch(List<ExamQuestionVO> bannerArray) throws Exception;

	/**
	 * 시험 문항별 랜덤 조회한다.
	 * @param vo
	 * @return
	 */
	public List<ExamQuestionVO> randQstnTypeList(ExamQuestionVO vo);

}
