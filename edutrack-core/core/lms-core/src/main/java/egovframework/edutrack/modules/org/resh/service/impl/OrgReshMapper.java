package egovframework.edutrack.modules.org.resh.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import egovframework.edutrack.modules.org.resh.service.OrgReshResultVO;
import egovframework.edutrack.modules.org.resh.service.OrgReshAnsrVO;
import egovframework.edutrack.modules.org.resh.service.OrgReshVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("orgReshMapper")
public interface OrgReshMapper {

	/**
	 * Select Key 조회
	 * @param VO
	 * @return
	 */
	public int selectKey()  throws DataAccessException ;
	
	/**
	 * 설문 목록 조회
	 * @param OrgReshVO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OrgReshVO> list(OrgReshVO VO)  throws DataAccessException ;
	/**
	 * 나의 일반 설문 목록 조회
	 * @param OrgReshVO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OrgReshVO> myList(OrgReshVO VO)  throws DataAccessException ;
	/**
	 * 나의 일반 설문 목록 조회 - 페이징
	 * @param OrgReshVO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OrgReshVO> myListPageing(OrgReshVO vo) throws DataAccessException ;
	
	/**
	 * 나의 일반 설문 목록 조회 - 카운트
	 * @param OrgReshVO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int myListCount(OrgReshVO vo) throws DataAccessException ;

	/**
	 * 설문 페이징 목록 조회
	 * @param OrgReshVO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OrgReshVO> listPageing(OrgReshVO VO)  throws DataAccessException ;

	/**
	 * 설문 페이징 목록수 반환 
	 * @param OrgReshVO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int count(OrgReshVO VO)  throws DataAccessException ;
	
	/**
	 * 설문 목록 조회(설문명 검색)
	 * @param VO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OrgReshVO> searchList(OrgReshVO VO)  throws DataAccessException ;
	
	/**
	 * 설문 정보 조회
	 * @param VO
	 * @return
	 */
	public OrgReshVO select(OrgReshVO VO  )   throws DataAccessException ;


	/**
	 * 설문 등록
	 * @param VO
	 * @return
	 */
	public int insert(OrgReshVO VO)  throws DataAccessException ;

	/**
	 * 설문 수정
	 * @param VO
	 * @return
	 */
	public int update(OrgReshVO VO)   throws DataAccessException ;

	/**
	 * 설문 삭제
	 * @param VO
	 * @return
	 */
	public int delete(OrgReshVO VO )  throws DataAccessException ;
	
	/**
	 * 설문 목록 조회시 기등록된 설문은 제외한다.
	 * @param VO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OrgReshVO> listResearchPreclusive(OrgReshVO VO)  throws DataAccessException ;
	
	/**
	 * 퇴교 설문작성자 페이징 목록수 반환
	 */
	@SuppressWarnings("unchecked")
	public int countPaging(OrgReshAnsrVO vo) throws Exception;
	
	/**
	 * 퇴교 설문작성자 반환
	 * @param OrgReshVO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OrgReshAnsrVO> listExpulsionStdPageing(OrgReshAnsrVO vo)  throws DataAccessException ;
	
	/**
	 * 기수 퇴교 설문 조회
	 * @param VO
	 * @return
	 */
	public OrgReshVO expulsionResh(OrgReshVO VO)   throws DataAccessException ;

	/**
	 * 설문 목록 조회(복사)
	 * @param OrgReshVO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OrgReshVO> listReshForCopy(OrgReshVO vo)  throws DataAccessException ;
}
