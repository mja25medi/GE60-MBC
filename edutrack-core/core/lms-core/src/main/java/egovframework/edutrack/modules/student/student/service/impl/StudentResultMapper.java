package egovframework.edutrack.modules.student.student.service.impl;

import java.util.List;

import egovframework.edutrack.modules.student.student.service.StudentResultVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("studentResultMapper")
public interface StudentResultMapper {

	/**
	 * 학습자 목록 조회 (전체 - 페이징 없이 검색)
	 * @param studentResultVO.crsCreCd
	 * @param studentResultVO.enrlSts |E|S|C|F|N| 형태
	 * @param studentResultVO.userNm 검색용
	 * @param studentResultVO.startDate yyyymmddhh24miss 형태 수강신청일 기준 검색
	 * @param studentResultVO.endDate yyyymmddhh24miss 형태 수강신청일 기준 검색
	 * @return ProcessReslutListVO(StudentResultVO)
	 */
	@SuppressWarnings("unchecked")
	public abstract List<StudentResultVO> list(
			StudentResultVO iStudentResultVO) throws Exception;

	/**
	 * 학습자 목록 조회 (페이징)
	 * @param studentResultVO.crsCreCd
	 * @param studentResultVO.enrlSts |E|S|C|F|N| 형태
	 * @param studentResultVO.userNm 검색용
	 * @param studentResultVO.startDate yyyymmddhh24miss 형태 수강신청일 기준 검색
	 * @param studentResultVO.endDate yyyymmddhh24miss 형태 수강신청일 기준 검색
	 * @param curPage 현재 페이지
	 * @param listScale 리스트 갯수
	 * @param pageScale 페이지 갯수
	 * @return ProcessReslutListVO(StudentResultVO)
	 */
	@SuppressWarnings("unchecked")
	public abstract List<StudentResultVO> listPageing(StudentResultVO iStudentResultVO) throws Exception;
	
	/**
	 * 학습자 목록수 반환
	 * @return int
	 */
	@SuppressWarnings("unchecked")
	public int count(StudentResultVO iStudentResultVO) throws Exception;


}