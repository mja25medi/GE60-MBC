package egovframework.edutrack.modules.org.resh.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import egovframework.edutrack.modules.org.resh.service.OrgReshAnsrVO;
import egovframework.edutrack.modules.org.resh.service.OrgReshResultVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("orgReshAnsrMapper")
public interface OrgReshAnsrMapper {

	/**
	 * 개설 과정 설문 사용자 응답 목록 조회
	 * @param OrgReshAnsrVO
	 * @return
	 */
	
	public List<OrgReshAnsrVO> list(OrgReshAnsrVO VO)   ;

	/**
	 * 개설 과정 설문 사용자 응답 페이징 목록 조회
	 * @param OrgReshAnsrVO
	 * @return
	 */
	
	public List<OrgReshAnsrVO> listPageing(OrgReshAnsrVO VO)   ;

	/**
	 * 개설 과정 설문 사용자 응답 페이징 목록수 반환
	 * @param OrgReshAnsrVO
	 * @return
	 */
	
	public int count(OrgReshAnsrVO VO)   ;
	
	/**
	 * 개설 과정 설문 사용자 응답 정보 조회
	 * @param VO
	 * @return
	 */
	public OrgReshAnsrVO select(OrgReshAnsrVO VO  )   ;


	/**
	 * 개설 과정 설문 사용자 응답 등록
	 * @param VO
	 * @return
	 */
	public int insert(OrgReshAnsrVO VO)   ;

	/**
	 * 개설 과정 설문 사용자 응답 수정
	 * @param VO
	 * @return
	 */
	public int update(OrgReshAnsrVO VO)   ;

	/**
	 * 개설 과정 설문 사용자 응답 삭제
	 * @param VO
	 * @return
	 */
	public int delete(OrgReshAnsrVO VO )   ;

	/**
	 * 개설 과정 설문 사용자 기타 응답 목록 조회
	 * @param OrgReshAnsrVO
	 * @return
	 */
	
	public List<OrgReshAnsrVO> listOpinion(OrgReshAnsrVO VO)   ;

	/**
	 * 개설 과정 설문 사용자 기타 응답 목록 조회-엑셀다운로드용
	 * @param OrgReshAnsrVO
	 * @return
	 */
	
	public List<OrgReshAnsrVO> listExcel(OrgReshAnsrVO VO)   ;
	

	/**
	 * 개설 과정 설문 사용자 응답 목록 조회 (설문번호 없이)
	 * @param OrgReshAnsrVO
	 * @return
	 */
	
	public List<OrgReshAnsrVO> listNoReshSn(OrgReshAnsrVO VO)   ;

	/**
	 * 개설 과정 설문 사용자 응답건수
	 * @param OrgReshAnsrVO
	 * @return
	 */
	public OrgReshAnsrVO listCount(OrgReshAnsrVO VO)   ;

	/**
	 * 개설 과정 설문 사용자 기타 점수 응답 목록 조회-엑셀다운로드용
	 * @param OrgReshAnsrVO
	 * @return
	 */
	
	public List<OrgReshAnsrVO> listReshScoreExcel(OrgReshAnsrVO VO)   ;
	

	
	public List<OrgReshAnsrVO> selectAsrList(OrgReshAnsrVO VO)   ;
	/**
	 * 개설 과정 설문 결과, 교육생 설문 응시 답 목록 조회
	 * @param CrsReshAnsrVO
	 * @return
	 */
	
	public List<OrgReshResultVO> listReshAnsr(OrgReshResultVO VO)   ;
	
	/**
	 * 개설 과정 설문 사용자 응답 목록 조회
	 * @param OrgReshAnsrVO
	 * @return
	 */
	
	public List<OrgReshAnsrVO> listAnsrList(OrgReshAnsrVO VO)   ;
	

}
