package egovframework.edutrack.modules.etc.bkgimg.service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface BkgImgService {

	/**
	 * 배경이미지 목록을 반환한다.
	 * @param BkgImgVO.useYn
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<BkgImgVO> list (BkgImgVO VO)  throws Exception;

	/**
	 * 배경이미지 페이징 목록을 반환한다.
	 * @param BkgImgVO.useYn
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 */
	public abstract ProcessResultListVO<BkgImgVO> listPageing(BkgImgVO VO)  throws Exception;


	/**
	 * 시스템 펨플릿 단일 항목 정보를 반환한다.
	 * @param BkgImgVO.tplCd
	 * @return
	 */
	public abstract ProcessResultVO<BkgImgVO> view(BkgImgVO VO)  throws Exception;

	/**
	 * 시스템 템플릿 정보를 저장하고 결과를 반환한다.
	 * @param BkgImgVO
	 * @return
	 */
	public abstract ProcessResultVO<BkgImgVO> add(BkgImgVO VO)  throws Exception;

	/**
	 * 시스템 템플릿 정보를 수정하고 결과를 반환한다.
	 * @param VO
	 * @return
	 */
	public abstract ProcessResultVO<BkgImgVO> edit(BkgImgVO VO)  throws Exception;

	/**
	 * 시스템 템플릿 순서를 변경하고 결과를 반환한다.
	 * @param VO
	 * @return
	 */
	public abstract ProcessResultVO<?> sort(BkgImgVO VO)  throws Exception;

	/**
	 * 시스템 템플릿 정보를 삭제하고 결과를 반환한다.
	 * @param VO
	 * @return
	 */
	public abstract ProcessResultVO<?> remove(BkgImgVO VO)  throws Exception;

}
