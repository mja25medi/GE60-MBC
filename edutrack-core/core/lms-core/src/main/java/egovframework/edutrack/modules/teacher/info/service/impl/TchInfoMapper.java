package egovframework.edutrack.modules.teacher.info.service.impl;

import egovframework.edutrack.modules.teacher.info.service.TchInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("tchInfoMapper")
public interface TchInfoMapper {

	/**
	 * 강사 정보의 단일 항목을 검색하여 리턴한다.
	 * @param vo
	 * @return
	 */
	public TchInfoVO select(TchInfoVO vo) ;

	/**
	 * 강사 정보를 Insert 하고 결과를 리턴한다.
	 * @param vo
	 * @return
	 */
	public int insert(TchInfoVO vo) ;

	/**
	 * 강사 정보를 Update하고 결과를 리턴한다.
	 * @param vo
	 * @return
	 */
	public int update(TchInfoVO vo) ;

	/**
	 * 강사 정보를 Delete하고 결과를 리턴한다.
	 * @param vo
	 * @return
	 */
	public int delete(TchInfoVO vo) ;

}