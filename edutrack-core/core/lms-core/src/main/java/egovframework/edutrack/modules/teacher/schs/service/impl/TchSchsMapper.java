package egovframework.edutrack.modules.teacher.schs.service.impl;

import java.util.List;

import egovframework.edutrack.modules.teacher.schs.service.TchSchsVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("tchSchsMapper")
public interface TchSchsMapper {
	/**
	 * Select Key 조회
	 * @param int
	 * @return
	 */
	public int selectKey()  ;

	/**
	 * 강사의 학력 페이징 목록을 반환한다.
	 * @param tchSchsVO
	 * @return
	 */
	
	public List<TchSchsVO> listPageing(
			TchSchsVO vo) ;
	
	/**
	 * 강사의 학력 페이징 목록수를 반환한다.
	 * @param tchSchsVO
	 * @return
	 */
	
	public int count(
			TchSchsVO vo) ;
	

	/**
	 * 강사의 학력 목록을 반환한다.
	 * @param vo
	 * @return
	 */
	
	public List<TchSchsVO> list(TchSchsVO vo) ;

	/**
	 * 강사의 학력 단일 항목을 반환한다.
	 * @param vo
	 * @return
	 */
	public TchSchsVO select(TchSchsVO vo) ;

	/**
	 * 강사 학력 정보를 Insert 하고 결과를 반환한다.
	 * @param vo
	 * @return
	 */
	public int insert(TchSchsVO vo) ;

	/**
	 * 강사 학력의 정보를 Update하고 결과를 반환한다.
	 * @param vo
	 * @return
	 */
	public int update(TchSchsVO vo) ;

	/**
	 * 강사의 학력 정보를 Delete하고 결과를 반환한다.
	 * @param vo
	 * @return
	 */
	public int delete(TchSchsVO vo) ;


}