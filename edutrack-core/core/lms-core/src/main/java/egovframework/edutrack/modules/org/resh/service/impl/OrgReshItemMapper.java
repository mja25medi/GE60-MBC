package egovframework.edutrack.modules.org.resh.service.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import egovframework.edutrack.modules.org.resh.service.OrgReshItemVO;
import egovframework.edutrack.modules.org.resh.service.OrgReshVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("orgReshItemMapper")
public interface OrgReshItemMapper {

	/**
	 *  select Key 정보 조회
	 */
	public int selectKey()  throws DataAccessException ;

	/**
	 * 설문 문제 목록 조회
	 */
	@SuppressWarnings("unchecked")
	public List<OrgReshItemVO> list(OrgReshVO vo )   throws DataAccessException ;
	
	/**
	 * 설문 문제 목록 조회
	 */
	@SuppressWarnings("unchecked")
	public List<OrgReshItemVO> list(OrgReshItemVO vo )   throws DataAccessException ;

	/**
	 *  설문 문제 정보 조회
	 */
	public OrgReshItemVO select(OrgReshItemVO  vo )  throws DataAccessException ;

	/**
	 *  설문 문제 정보 등록
	 */
	public int insert(OrgReshItemVO vo)  throws DataAccessException ;

	/**
	 *  설문 문제 정보 수정
	 */
	public int update(OrgReshItemVO vo)  throws DataAccessException ;

	/**
	 *  설문 문제 정보 삭제
	 */
	public int delete(OrgReshItemVO vo )  throws DataAccessException ;

	/**
	 * 설문 문제 레코드를 일괄 Update하고 결과를 반환한다.
	 * @param ResearchBankVO
	 * @return ProcessResultVO<ResearchBankVO>
	 */
	public int updateBatch(List<OrgReshItemVO> itemArray)   throws DataAccessException ;
	
	/**
	 * 일반 설문 통계 리스트
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OrgReshItemVO> listStatResearch(OrgReshItemVO vo ) throws DataAccessException;
	
	/**
	 * 과목 설문 통계 리스트
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OrgReshItemVO> listStatCrsResearch(OrgReshItemVO vo ) throws DataAccessException;
	
	/**
	 * 일반 설문 통계 리스트 - 엑셀
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OrgReshItemVO> listStatResearchStdNmExcel(OrgReshItemVO vo ) throws DataAccessException;
	
	/**
	 * 과목 설문 통계 리스트 - 엑셀
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OrgReshItemVO> listStatCrsResearchStdNmExcel(OrgReshItemVO vo ) throws DataAccessException;
	
	/**
	 * 개설 과정 리스트
	 */
	@SuppressWarnings("unchecked")
	public List<OrgReshItemVO> listStatCrsResearchCrsCre(OrgReshItemVO VO)  throws DataAccessException ;
	
	/**
	 * 과목 설문 항목 리스트
	 */
	@SuppressWarnings("unchecked")
	public List<OrgReshItemVO> listCrsReshItem(OrgReshItemVO VO)  throws DataAccessException ;
	
	/**
	 * 일반 설문 항목 리스트
	 */
	@SuppressWarnings("unchecked")
	public List<OrgReshItemVO> listReshItem(OrgReshItemVO VO)  throws DataAccessException ;
	
	/**
	 * 설문결과 점수결과보기
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OrgReshItemVO> listRsltScore(OrgReshItemVO vo ) throws DataAccessException;
	
	/**
	 * 설문결과 결과보기
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OrgReshItemVO> listRslt(OrgReshItemVO vo ) throws DataAccessException;
	
	
	/**
	 * 설문결과 엑셀다운로드
	 */
	@SuppressWarnings("unchecked")
	public List<OrgReshItemVO> listReshScoreExcel(OrgReshItemVO VO)  throws DataAccessException ;
	
	/**
	 * 퇴교 설문 통계 리스트 - 엑셀
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OrgReshItemVO> listStatOutResearchStdNmExcel(OrgReshItemVO vo ) throws DataAccessException;
	
	/**
	 * 개설 과정 설문 사용자 기타 응답 목록 조회
	 * @param OrgReshItemVO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OrgReshItemVO> listOpinion(OrgReshItemVO VO)  throws DataAccessException ;
}
