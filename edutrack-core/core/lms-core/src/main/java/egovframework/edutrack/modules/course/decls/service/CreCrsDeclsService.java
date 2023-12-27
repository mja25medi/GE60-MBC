package egovframework.edutrack.modules.course.decls.service;

import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface CreCrsDeclsService {

	/**
	 * 개설 과정 분반 정보의 목록을 조회하여 반환한다.
	 * @param CreCrsDeclsVO
	 * @return ProcessResultListVO<CreCrsDeclsVO>
	 */
	public abstract ProcessResultListVO<CreCrsDeclsVO> list(CreCrsDeclsVO VO) throws Exception;

	/**
	 * 개설 과정 분반 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CreCrsDeclsVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return ProcessResultListVO<CreCrsDeclsVO>
	 */
	public abstract ProcessResultListVO<CreCrsDeclsVO> listPageing(
			CreCrsDeclsVO VO, int curPage, int listScale, int pageScale) throws Exception;

	/**
	 * 개설 과정 분반 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CreCrsDeclsVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	public abstract ProcessResultListVO<CreCrsDeclsVO> listPageing(
			CreCrsDeclsVO VO, int curPage, int listScale) throws Exception;

	/**
	 * 개설 과정 분반 정보의 목록을 조회하여 반환한다.
	 * (페이징 정보 포함)
	 * @param CreCrsDeclsVO
	 * @param curPage 현재 페이지
	 * @param listScale 페이지당 표시갯수 : 입력이 없을 경우 시스템 초기값 사용
	 * @param pageScale 표시할 페이지 이동컨트롤 갯수 : 입력이 없을 경우 시스템 초기갑 사용
	 * @return 조회결과 목록 VO
	 */
	public abstract ProcessResultListVO<CreCrsDeclsVO> listPageing(
			CreCrsDeclsVO VO, int curPage) throws Exception;

	/**
	 * 개설 과정 분반 정보의 단일 레코드를 조회하여 반환한다.
	 * @param CreCrsDeclsVO.atclSn
	 * @return ProcessResultVO<CreCrsDeclsVO>
	 */
	public abstract ProcessResultVO<CreCrsDeclsVO> view(CreCrsDeclsVO VO) throws Exception;

	/**
	 * 개설 과정 분반 정보를 등록하고 결과를 반환한다.
	 * @param CreCrsDeclsVO
	 * @return ProcessResultVO<CreCrsDeclsVO>
	 */
	public abstract ProcessResultVO<CreCrsDeclsVO> add(CreCrsDeclsVO VO) throws Exception;

	/**
	 * 개설 과정 분반 정보 삭제하고 결과를 반환한다.
	 * @param articleVO 삭제 대상 고유 번호
	 * @return 삭제 처리 결과 VO
	 */
	public abstract ProcessResultVO<?> remove(CreCrsDeclsVO VO) throws Exception;

	/**
	 * 개설 과정 분반 정보 삭제하고 결과를 반환한다.
	 * @param articleVO 삭제 대상 고유 번호
	 * @return 삭제 처리 결과 VO
	 */
	public abstract ProcessResultVO<?> removeAll(CreCrsDeclsVO VO) throws Exception;

	/**
	 * 개설 과정의 분반수를 반환한다.
	 * @param CreCrsDeclsVO VO
	 * @return
	 */
	public abstract int getCount(CreCrsDeclsVO VO) throws Exception;

	/**
	 * 트랜잭션 테스트용 매소드(테스트에서만 사용된다.)
	 */
	@Deprecated
	public abstract ProcessResultVO<CreCrsDeclsVO> transactionRollbackMethod(
			CreCrsDeclsVO VO) throws Exception;

}