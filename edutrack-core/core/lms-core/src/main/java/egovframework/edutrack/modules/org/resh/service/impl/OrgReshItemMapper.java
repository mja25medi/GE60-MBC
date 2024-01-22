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
	public int selectKey()   ;

	/**
	 * 설문 문제 목록 조회
	 */
	
	public List<OrgReshItemVO> list(OrgReshVO vo )    ;
	
	/**
	 * 설문 문제 목록 조회
	 */
	
	public List<OrgReshItemVO> list(OrgReshItemVO vo )    ;

	/**
	 *  설문 문제 정보 조회
	 */
	public OrgReshItemVO select(OrgReshItemVO  vo )   ;

	/**
	 *  설문 문제 정보 등록
	 */
	public int insert(OrgReshItemVO vo)   ;

	/**
	 *  설문 문제 정보 수정
	 */
	public int update(OrgReshItemVO vo)   ;

	/**
	 *  설문 문제 정보 삭제
	 */
	public int delete(OrgReshItemVO vo )   ;

	/**
	 * 설문 문제 레코드를 일괄 Update하고 결과를 반환한다.
	 * @param ResearchBankVO
	 * @return ProcessResultVO<ResearchBankVO>
	 */
	public int updateBatch(List<OrgReshItemVO> itemArray)    ;
	
	/**
	 * 일반 설문 통계 리스트
	 * @return
	 */
	
	public List<OrgReshItemVO> listStatResearch(OrgReshItemVO vo ) ;
	
	/**
	 * 과목 설문 통계 리스트
	 * @return
	 */
	
	public List<OrgReshItemVO> listStatCrsResearch(OrgReshItemVO vo ) ;
	
	/**
	 * 일반 설문 통계 리스트 - 엑셀
	 * @return
	 */
	
	public List<OrgReshItemVO> listStatResearchStdNmExcel(OrgReshItemVO vo ) ;
	
	/**
	 * 과목 설문 통계 리스트 - 엑셀
	 * @return
	 */
	
	public List<OrgReshItemVO> listStatCrsResearchStdNmExcel(OrgReshItemVO vo ) ;
	
	/**
	 * 개설 과정 리스트
	 */
	
	public List<OrgReshItemVO> listStatCrsResearchCrsCre(OrgReshItemVO VO)   ;
	
	/**
	 * 과목 설문 항목 리스트
	 */
	
	public List<OrgReshItemVO> listCrsReshItem(OrgReshItemVO VO)   ;
	
	/**
	 * 일반 설문 항목 리스트
	 */
	
	public List<OrgReshItemVO> listReshItem(OrgReshItemVO VO)   ;
	
	/**
	 * 설문결과 점수결과보기
	 * @return
	 */
	
	public List<OrgReshItemVO> listRsltScore(OrgReshItemVO vo ) ;
	
	/**
	 * 설문결과 결과보기
	 * @return
	 */
	
	public List<OrgReshItemVO> listRslt(OrgReshItemVO vo ) ;
	
	
	/**
	 * 설문결과 엑셀다운로드
	 */
	
	public List<OrgReshItemVO> listReshScoreExcel(OrgReshItemVO VO)   ;
	
	/**
	 * 퇴교 설문 통계 리스트 - 엑셀
	 * @return
	 */
	
	public List<OrgReshItemVO> listStatOutResearchStdNmExcel(OrgReshItemVO vo ) ;
	
	/**
	 * 개설 과정 설문 사용자 기타 응답 목록 조회
	 * @param OrgReshItemVO
	 * @return
	 */
	
	public List<OrgReshItemVO> listOpinion(OrgReshItemVO VO)   ;
}
