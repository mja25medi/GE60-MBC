package egovframework.edutrack.modules.org.ide.service;

import java.io.OutputStream;

import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface OrgIdeInfoService {

	public abstract ProcessResultListVO<OrgIdeInfoVO> listPageing(OrgIdeInfoVO vo, int pageIndex, int listScale, int pageScale) throws Exception;

	public abstract ProcessResultListVO<OrgIdeInfoVO> listPageing(OrgIdeInfoVO vo, int pageIndex, int listScale) throws Exception;

	public abstract ProcessResultListVO<OrgIdeInfoVO> listPageing(OrgIdeInfoVO vo, int pageIndex) throws Exception;

	public abstract int add(OrgIdeInfoVO vo);
	
	public abstract int editUseYn(OrgIdeInfoVO vo) throws Exception;

	public abstract int remove(OrgIdeInfoVO vo) throws Exception;

	public abstract void sampleExcelDownloadForIdeAdd(OutputStream outputStream, String orgCd) throws Exception;

	public abstract ProcessResultListVO<OrgIdeInfoVO> excelUploadIdeAdd(OrgIdeInfoVO vo, String fileName, String filePath, String orgCd) throws Exception;
}
