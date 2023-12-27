package egovframework.edutrack.modules.course.crstch.service;

import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;


public interface CrsTchService {

	/**
	 * 과정 강사의 목록을 조회하여 반환한다.
	 * @param CrsTchVO
	 * @return ProcessResultListVO<CrsTchVO>
	 */
	public abstract ProcessResultListVO<CrsTchVO> list(CrsTchVO VO) throws Exception;

	/**
	 * 과정 강사의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsTchVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<CrsTchVO>
	 */
	public abstract ProcessResultListVO<CrsTchVO> listPageing(CrsTchVO VO,
			int curPage, int listScale, int pageScale) throws Exception;

	/**
	 * 과정 강사의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsTchVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	public abstract ProcessResultListVO<CrsTchVO> listPageing(CrsTchVO VO,
			int curPage, int listScale) throws Exception;

	/**
	 * 과정 강사의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CrsTchVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	public abstract ProcessResultListVO<CrsTchVO> listPageing(CrsTchVO VO,
			int curPage) throws Exception;

	/**
	 * 과정 강사의 단일 레코드를 조회하여 반환한다.
	 * @param CrsTchVO.atclSn
	 * @return ProcessResultVO<CrsTchVO>
	 */
	public abstract ProcessResultVO<CrsTchVO> view(CrsTchVO VO) throws Exception;

	/**
	 * 과정 강사를 등록하고 결과를 반환한다.
	 * @param CrsTchVO
	 * @return ProcessResultVO<CrsTchVO>
	 */
	public abstract ProcessResultVO<CrsTchVO> add(CrsTchVO VO) throws Exception;

	/**
	 * 과정 강사를 수정하고 결과를 반환한다.
	 * @param CrsTchVO
	 * @return ProcessResultVO<CrsTchVO>
	 */
	public abstract ProcessResultVO<CrsTchVO> edit(CrsTchVO VO) throws Exception;

	/**
	 * 과정 강사의 순서를 변경하고 결과를 반환한다.
	 * @param CrsTchVO
	 * @return
	 */
	public abstract ProcessResultVO<?> sort(CrsTchVO VO) throws Exception;

	/**
	 * 과정 강사를 삭제하고 결과를 반환한다.
	 * @param articleVO 삭제 대상 고유 번호
	 * @return 삭제 처리 결과 VO
	 */
	public abstract ProcessResultVO<?> remove(CrsTchVO VO) throws Exception;
	
	/**
	 * 강사/튜터의 권한이 있는 사용자중 과정 튜터/강사로 등록이 되어 있지 않은 
	 * 사용자의 목록을 반환한다.
	 * @param CrsTchVO
	 * @return ProcessResultListVO<CrsTchVO>
	 */
	public ProcessResultListVO<UsrUserInfoVO> listSearch(CrsTchVO VO) throws Exception;

}