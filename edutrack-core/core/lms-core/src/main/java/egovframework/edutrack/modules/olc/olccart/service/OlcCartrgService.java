package egovframework.edutrack.modules.olc.olccart.service;


import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface OlcCartrgService {

	/**
	 * OLC의 모든 목록을 조회하여 반환한다.
	 * @param OlcCartrgVO.orgCd
	 * @param OlcCartrgVO.userNo
	 * @param OlcCartrgVO.ctgrCd
	 * @return ProcessResultListVO<OlcCartrgVO>
	 */
	public ProcessResultListVO<OlcCartrgVO> list(OlcCartrgVO vo) throws Exception;

	/**
	 * OLC의 페이징된 목록을 조회하여 반환한다.
	 * 페이징 정보 포함.
	 * @param OlcCartrgVO.orgCd
	 * @param OlcCartrgVO.userNo
	 * @param OlcCartrgVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO<OlcCartrgVO>
	 */
	public ProcessResultListVO<OlcCartrgVO> listPageing(
			OlcCartrgVO vo, int curPage, int listScale, int pageScale) throws Exception;

	/**
	 * OLC의 페이징된 목록을 조회하여 반환한다.
	 * 페이징 정보 포함.
	 * @param OlcCartrgVO.orgCd
	 * @param OlcCartrgVO.userNo
	 * @param OlcCartrgVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @return ProcessResultListVO<OlcCartrgVO>
	 */
	public ProcessResultListVO<OlcCartrgVO> listPageing(
			OlcCartrgVO vo, int curPage, int listScale) throws Exception;

	/**
	 * OLC의 페이징된 목록을 조회하여 반환한다.
	 * 페이징 정보 포함.
	 * @param OlcCartrgVO.orgCd
	 * @param OlcCartrgVO.userNo
	 * @param OlcCartrgVO.ctgrCd
	 * @param curPage
	 * @return ProcessResultListVO<OlcCartrgVO>
	 */
	public ProcessResultListVO<OlcCartrgVO> listPageing(
			OlcCartrgVO vo, int curPage) throws Exception;

	/**
	 * OLC 정보 단일 레코드를 조회하여 반환한다.
	 * @param OlcCartrgVO.orgCd
	 * @param OlcCartrgVO.userNo
	 * @param OlcCartrgVO.cartrgCd
	 * @return
	 */
	public ProcessResultVO<OlcCartrgVO> view(OlcCartrgVO vo) throws Exception;

	/**
	 * OLC 정보 단일 레코드를 조회하여 반환한다.
	 * @param OlcCartrgVO.orgCd
	 * @param OlcCartrgVO.userNo
	 * @param OlcCartrgVO.cartrgCd
	 * @param 조회수 증가 여부(boolean) : default -> false
	 * @return
	 */
	public ProcessResultVO<OlcCartrgVO> view(OlcCartrgVO vo, boolean hitsup) throws Exception;

	/**
	 * OLC 정보 단일 레코드를 DB에 Insert하고 결과를 반환한다.
	 * @param OlcCartrgVO
	 * @return ProcessResultVO<OlcCartrgVO>
	 */
	public ProcessResultVO<OlcCartrgVO> add(OlcCartrgVO vo) throws Exception;

	/**
	 * OLC 정보 단일 레코드를 Update하고 결과를 반환한다.
	 * @param OlcCartrgVO
	 * @return ProcessResultVO<OlcCartrgVO>
	 */
	public ProcessResultVO<OlcCartrgVO> edit(OlcCartrgVO vo) throws Exception;

	/**
	 * OLC 정보 단일 레코드를 Delete하고 결과를 반환한다.
	 * @param OlcCartrgVO
	 * @return ProcessResultVO<OlcCartrgVO>
	 */
	public ProcessResultVO<?> remove(OlcCartrgVO vo) throws Exception;

	/**
	 * OLC 정보의 순서를 변경하고 결과를 반환한다.
	 * @param OlcCartrgVO
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<?> move(OlcCartrgVO vo, String moveType) throws Exception;

	/**
	 * 공유설정을 변경하고 결과를 반환한다.
	 * @param OlcCntsVO
	 * @return  ProcessResultVO
	 */
	public ProcessResultVO<?> ShareCdChange(OlcCartrgVO vo, String cdType) throws Exception;

	/**
	 * OLC 공유정보 단일 레코드를 Update하고 결과를 반환한다.
	 * @param OlcCartrgVO
	 * @return ProcessResultVO<OlcCartrgVO>
	 */
	public ProcessResultVO<OlcCartrgVO> editShare(OlcCartrgVO vo) throws Exception;

	/**
	 * OLC 정보 단일 레코드를 Update하고 결과를 반환한다. - 디자인 수정
	 * @param OlcCartrgVO
	 * @return ProcessResultVO<OlcCartrgVO>
	 */
	public ProcessResultVO<OlcCartrgVO> editDesign(OlcCartrgVO vo) throws Exception;
}