package egovframework.edutrack.modules.log.sitestat.service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface SiteStatService {

	/**
	 * 사이트 운영 현황 목록
	 * @param SiteStatVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<SiteStatVO> list(SiteStatVO vo) throws Exception;

	/**
	 * 사이트 운영 현황 페이징 목록
	 * @param SiteStatVO
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract ProcessResultListVO<SiteStatVO> listPageing(SiteStatVO vo, int pageIndex, int listScale, int pageScale) throws Exception;
	public abstract ProcessResultListVO<SiteStatVO> listPageing(SiteStatVO vo, int pageIndex, int listScale) throws Exception;
	public abstract ProcessResultListVO<SiteStatVO> listPageing(SiteStatVO vo, int pageIndex) throws Exception;

	/**
	 * 사이트 운영 현황 목록 엑셀 다운로드
	 * @param SiteStatVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	public abstract void listSiteStatExcelDownload(SiteStatVO vo, ServletOutputStream outputStream, HttpServletRequest request) throws Exception;

}