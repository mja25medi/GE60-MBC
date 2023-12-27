
package egovframework.edutrack.modules.org.fac.service;

import java.io.OutputStream;
import java.util.List;

import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface OrgFacService {
	
	/**
	 * 시설 예약 정보업데이트.
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public abstract int updateRes(OrgFacResVO vo) throws Exception;
	
	/**
	 * 시설 정보 업데이트
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public abstract int updateFac(OrgFacInfoVO vo) throws Exception;
	
	/**
	 * 시설 정보 업데이트
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public abstract int updateFacUse(OrgFacInfoVO vo) throws Exception;
	
	public abstract ProcessResultListVO<OrgFacInfoVO> listInfoPageing(OrgFacInfoVO vo, boolean fileIn) throws Exception;
	
	/**
	 * 시설 대관 조회
	 * 
	 * @param vo
	 * @param pageIndex
	 * @param listScale
	 * @return
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<OrgFacResVO> listPageingRes(OrgFacResVO vo) throws Exception;
	
	public abstract ProcessResultListVO<OrgFacInfoVO> listPageingFac(OrgFacInfoVO vo) throws Exception;
	
	/**
	 * 시설 대관 상세 조회
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public abstract OrgFacResVO viewRes(OrgFacResVO vo) throws Exception;
	
	/**
	 * 시설 상세 조회
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public abstract OrgFacInfoVO viewFac(OrgFacInfoVO vo) throws Exception;
	
	/**
	 * 시설 예약 정보를 사용안함으로 변경.
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public abstract int cancelRes(OrgFacResVO vo) throws Exception;
	
	/**
	 * 시설 예약 리스트 조회
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public abstract List<OrgFacResVO> listRes(OrgFacResVO vo) throws Exception;
	
	/**
	 * 시설 리스트
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<OrgFacInfoVO> listFac(OrgFacInfoVO vo) throws Exception;

	
	/**
	 * 시설 신청정보 저장
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public abstract int addRes(OrgFacResVO vo) throws Exception;

	/**
	 * 시설 등록
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public abstract String addFac(OrgFacInfoVO vo) throws Exception;

	/**
	 * 시설 삭제
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public abstract int deleteFac(OrgFacInfoVO vo) throws Exception;
	
	public String addFacCtgr(OrgFacCtgrVO vo) throws Exception;
	
	public OrgFacCtgrVO viewFacCtgr(OrgFacCtgrVO vo) throws Exception;
	
	public List<OrgFacCtgrVO> listFacCtgr(OrgFacCtgrVO vo) throws Exception;
	
	public int updateFacCtgr(OrgFacCtgrVO vo) throws Exception;
	
	public int deleteFacCtgr(OrgFacCtgrVO vo) throws Exception;
	
	public void validateReserveTime(OrgFacResVO vo) throws Exception;
	
	public int updateFacInfoOdr(OrgFacInfoVO vo) throws Exception;
	
	public void resListExcelDownload(OrgFacResVO vo, OutputStream os) throws Exception;
}