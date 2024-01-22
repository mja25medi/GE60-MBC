package egovframework.edutrack.modules.student.worklog.service.impl;

import java.util.List;

import egovframework.edutrack.modules.student.worklog.service.StdEduRsltWorkLogVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("stdEduRsltWorkLogMapper")
public interface StdEduRsltWorkLogMapper {

	/**
	 * 작업 로그 전체 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	
	public List<StdEduRsltWorkLogVO> list(
			StdEduRsltWorkLogVO vo) ;

	/**
	 * 직압 로그 페이징 목록 조회
	 *
	 * @return ProcessReslutListVO
	 */
	
	public List<StdEduRsltWorkLogVO> listPageing(
			StdEduRsltWorkLogVO vo) ;
	
	/**
	 * 직압 로그 페이징 목록 조회수를 반환
	 *
	 * @return ProcessReslutListVO
	 */
	
	public int count(
			StdEduRsltWorkLogVO vo) ;
	

	/**
	 * 학습결과 정보 조회
	 *
	 * @return ProcessResultVO
	 */
	public StdEduRsltWorkLogVO select(
			StdEduRsltWorkLogVO vo) ;

	/**
	 * 학습결과 등록
	 *
	 * @reurn ProcessResultVO
	 */
	public int insert(
			StdEduRsltWorkLogVO vo) ;

}