package egovframework.edutrack.modules.teacher.writings.service.impl;

import java.util.List;

import egovframework.edutrack.modules.teacher.writings.service.TchWritingsVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("tchWritingsMapper")
public interface TchWritingsMapper {
	/**
	 * Select Key 조회
	 * @param int
	 * @return
	 */
	public int selectKey()  throws Exception;

	/**
	 * 강사 저서의 페이징 목록을 반환한다.
	 * @param vo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TchWritingsVO> listPageing(
			TchWritingsVO vo) throws Exception;
	
	/**
	 * 강사 저서의 조회수를 반환한다.
	 * @param vo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int count(
			TchWritingsVO vo) throws Exception;
	

	/**
	 * 강사 저서의 목록을 반환한다.
	 * @param vo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TchWritingsVO> list(TchWritingsVO vo) throws Exception;

	/**
	 * 강사 저서의 정모의 단일 항목을 반환한다.
	 * @param vo
	 * @return
	 */
	public TchWritingsVO select(TchWritingsVO vo) throws Exception;

	/**
	 * 강사의 강의 저서 정보를 Insert 하고 결과를 반환한다.
	 * @param vo
	 * @return
	 */
	public int insert(TchWritingsVO vo) throws Exception;

	/**
	 * 강사의 강의 저서 정보를 Update 하고 결과를 반환한다.
	 * @param vo
	 * @return
	 */
	public int update(TchWritingsVO vo) throws Exception;

	/**
	 * 강사의 강의 저서 정보를 Delete 하고 결과를 반환한다.
	 * @param userNo
	 * @param lecWritingsSn
	 * @return
	 */
	public int delete(TchWritingsVO vo) throws Exception;

}