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
	public int selectKey()   ;
	
	/**
	 * 설문 목록 조회
	 * @param OrgReshVO
	 * @return
	 */
	
	public List<OrgReshVO> list(OrgReshVO VO)   ;
	/**
	 * 나의 일반 설문 목록 조회
	 * @param OrgReshVO
	 * @return
	 */
	
	public List<OrgReshVO> myList(OrgReshVO VO)   ;
	/**
	 * 나의 일반 설문 목록 조회 - 페이징
	 * @param OrgReshVO
	 * @return
	 */
	
	public List<OrgReshVO> myListPageing(OrgReshVO vo)  ;
	
	/**
	 * 나의 일반 설문 목록 조회 - 카운트
	 * @param OrgReshVO
	 * @return
	 */
	
	public int myListCount(OrgReshVO vo)  ;

	/**
	 * 설문 페이징 목록 조회
	 * @param OrgReshVO
	 * @return
	 */
	
	public List<OrgReshVO> listPageing(OrgReshVO VO)   ;

	/**
	 * 설문 페이징 목록수 반환 
	 * @param OrgReshVO
	 * @return
	 */
	
	public int count(OrgReshVO VO)   ;
	
	/**
	 * 설문 목록 조회(설문명 검색)
	 * @param VO
	 * @return
	 */
	
	public List<OrgReshVO> searchList(OrgReshVO VO)   ;
	
	/**
	 * 설문 정보 조회
	 * @param VO
	 * @return
	 */
	public OrgReshVO select(OrgReshVO VO  )    ;


	/**
	 * 설문 등록
	 * @param VO
	 * @return
	 */
	public int insert(OrgReshVO VO)   ;

	/**
	 * 설문 수정
	 * @param VO
	 * @return
	 */
	public int update(OrgReshVO VO)    ;

	/**
	 * 설문 삭제
	 * @param VO
	 * @return
	 */
	public int delete(OrgReshVO VO )   ;
	
	/**
	 * 설문 목록 조회시 기등록된 설문은 제외한다.
	 * @param VO
	 * @return
	 */
	
	public List<OrgReshVO> listResearchPreclusive(OrgReshVO VO)   ;
	
	/**
	 * 퇴교 설문작성자 페이징 목록수 반환
	 */
	
	public int countPaging(OrgReshAnsrVO vo) ;
	
	/**
	 * 퇴교 설문작성자 반환
	 * @param OrgReshVO
	 * @return
	 */
	
	public List<OrgReshAnsrVO> listExpulsionStdPageing(OrgReshAnsrVO vo)   ;
	
	/**
	 * 기수 퇴교 설문 조회
	 * @param VO
	 * @return
	 */
	public OrgReshVO expulsionResh(OrgReshVO VO)    ;

	/**
	 * 설문 목록 조회(복사)
	 * @param OrgReshVO
	 * @return
	 */
	
	public List<OrgReshVO> listReshForCopy(OrgReshVO vo)   ;
}
