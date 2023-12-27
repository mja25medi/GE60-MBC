/**
 *
 */
package egovframework.edutrack.modules.teacher.recd.service.impl;

import java.util.List;

import egovframework.edutrack.modules.teacher.recd.service.TchRecdVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * <b>강사 - 강사관리</b> 영역 인터페이스
 * 
 * @author pinkpanda
 */
@Mapper("tchRecdMapper")
public interface TchRecdMapper {

	/**
	 * Select Key 조회
	 * @param int
	 * @return
	 */
	public int selectKey()  throws Exception;
	/**
	 * 강사 학력 목록
	 * 
	 * @return ProcessReslutListVO
	 */
	@SuppressWarnings("unchecked")
	public List<TchRecdVO> listRecd(TchRecdVO teacherRecdVO) throws Exception;

	/**
	 * 강사 강의 기록 상세 정보
	 * 
	 * @param teacherRecdVO
	 * @return ProcessReslutListVO
	 */
	public TchRecdVO selectRecd(TchRecdVO teacherRecdVO) throws Exception;

	/**
	 * 강사 강의 기록 입력
	 * 
	 * @reurn 
	 */
	public int insertRecd(TchRecdVO teacherRecdVO) throws Exception;

	/**
	 * 강사 강의 기록 수정
	 * 
	 * @reurn 
	 */
	public int updateRecd(TchRecdVO teacherRecdVO) throws Exception;

	/**
	 * 강사 삭제
	 * 
	 * @reurn 
	 * @param teacherRecdVO
	 */
	public int deleteRecd(TchRecdVO teacherRecdVO) throws Exception;

}