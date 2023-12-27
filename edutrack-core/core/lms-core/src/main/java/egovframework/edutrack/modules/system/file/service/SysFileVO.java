package egovframework.edutrack.modules.system.file.service;

import java.io.File;
import java.util.List;
import org.apache.commons.io.FileUtils;
import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;

public class SysFileVO extends DefaultVO {

	private static final long serialVersionUID = 295996715405468455L;
	
	protected Integer	fileSn;								// FILE_SN,
	private String	repoCd;								// FILE_SIZE,
	private String	orgCd;
	private String	domainUrl;
	private String	repoPath;							// REPO_PATH,
	private String	fileNm;								// FILE_NM,
	private String	fileSaveNm;							// FILE_SAVE_NM,
	private String	filePath;							// FILE_PATH,
	private String	fileExt;							// FILE_EXT,
	private Long	fileSize;							// FILE_SIZE,
	private String	fileSizeStr;						// FILE_SIZE STRING,
	protected String	fileType;							// TYPE,
	private String	mimeType;							// MIME_TYPE,
	private int		hits;								// HITS,
	private String	lastInqDttm;						// LAST_INQ_DTTM,
	private String	fileBindDataSn;						// FILE_USE_DATA_SN,
	private String	etcInfo1;							// ETC_INFO_1,
	private String	etcInfo2;							// ETC_INFO_2,
	private String	etcInfo3;							// ETC_INFO_3,
	private String	repoNm;
	private String	parTableNm;
	private String	parFieldNm;
	private Integer	usingCnt;
	
	public String getFileSizeStr() {
		return fileSizeStr;
	}

	public void setFileSizeStr(String fileSizeStr) {
		this.fileSizeStr = fileSizeStr;
	}

	private String isUsingTable;

	// 검색용 파라매터..
	private List<String> fileSnArr;						// 주키의 배열	

	public SysFileVO() {
		super();
	}

	public SysFileVO(Integer fileSn) {
		super();
		this.fileSn = fileSn;
	}

	public Integer getFileSn() {
		return fileSn;
	}

	public void setFileSn(Integer fileSn) {
		this.fileSn = fileSn;
	}

	public String getRepoCd() {
		return repoCd;
	}

	public void setRepoCd(String repoCd) {
		this.repoCd = repoCd;
	}

	public String getOrgCd() {
		return orgCd;
	}

	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}

	public String getDomainUrl() {
		return domainUrl;
	}

	public void setDomainUrl(String domainUrl) {
		this.domainUrl = domainUrl;
	}

	public String getRepoPath() {
		return repoPath;
	}

	public void setRepoPath(String repoPath) {
		this.repoPath = repoPath;
	}

	public String getFileNm() {
		return fileNm;
	}

	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}

	public String getFileSaveNm() {
		return fileSaveNm;
	}

	public void setFileSaveNm(String fileSaveNm) {
		this.fileSaveNm = fileSaveNm;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
		this.setFileSizeStr(FileUtils.byteCountToDisplaySize(this.fileSize));
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public String getLastInqDttm() {
		return lastInqDttm;
	}

	public void setLastInqDttm(String lastInqDttm) {
		this.lastInqDttm = lastInqDttm;
	}

	public String getFileBindDataSn() {
		return fileBindDataSn;
	}

	public void setFileBindDataSn(String fileBindDataSn) {
		this.fileBindDataSn = fileBindDataSn;
	}

	public String getEtcInfo1() {
		return etcInfo1;
	}

	public void setEtcInfo1(String etcInfo1) {
		this.etcInfo1 = etcInfo1;
	}

	public String getEtcInfo2() {
		return etcInfo2;
	}

	public void setEtcInfo2(String etcInfo2) {
		this.etcInfo2 = etcInfo2;
	}

	public String getEtcInfo3() {
		return etcInfo3;
	}

	public void setEtcInfo3(String etcInfo3) {
		this.etcInfo3 = etcInfo3;
	}

	public String getRepoNm() {
		return repoNm;
	}

	public void setRepoNm(String repoNm) {
		this.repoNm = repoNm;
	}

	public String getParTableNm() {
		return parTableNm;
	}

	public void setParTableNm(String parTableNm) {
		this.parTableNm = parTableNm;
	}

	public String getParFieldNm() {
		return parFieldNm;
	}

	public void setParFieldNm(String parFieldNm) {
		this.parFieldNm = parFieldNm;
	}

	public Integer getUsingCnt() {
		return usingCnt;
	}

	public void setUsingCnt(Integer usingCnt) {
		this.usingCnt = usingCnt;
	}

	public List<String> getFileSnArr() {
		return fileSnArr;
	}

	public void setFileSnArr(List<String> fileSnArr) {
		this.fileSnArr = fileSnArr;
	}
	
	
	/**
	 * 파일 이름을 포함한 실제 저장된 경로 전체를 반환.<br>
	 * {@code /repo/filepath/filesavename}
	 * @return
	 */
	public String getSaveFilePath() {
		return this.getSaveDirectoryPath()
				+ File.separator + this.getFileSaveNm();
	}

	/**
	 * 파일의 저장 경로 반환
	 * @return
	 */
	public String getSaveDirectoryPath() {
		return File.separator + this.getRepoPath()
				+ StringUtil.ReplaceAll(this.getFilePath(), "\\", File.separator);
	}

	/**
	 * 파일 번호화 파일 확장자를 조합해서 가상의 파일명을 반환
	 * @return
	 */
	public String getFileSnName() {
		//return this.getFileSn() + "." + StringUtil.nvl(this.getFileExt(), "image");
		return ""+this.getFileSn();
	}

	/**
	 * SysFileVO에 대한 download Tag를 반환.
	 * @return
	 */
	public String getDownloadTag() {
		return SysFileVOUtil.getDownloadTag(this);
	}
	/**
	 * SysFileVO에 대한 lecture download Tag를 반환.
	 * @return
	 */
	public String getLecDownloadTag() {
		return SysFileVOUtil.getLecDownloadTag(this);
	}

	/**
	 * SysFileVO에 대한 image tag를 반환.
	 * @return
	 */
	public String getImageTag() {
		return SysFileVOUtil.getImageTag(this);
	}


	public String getThumbUrl() {
		return SysFileVOUtil.getThumbUrl(this);
	}


	public String getViewUrl() {
		return SysFileVOUtil.getViewUrl(this);
	}

	public boolean isMedia() {
		if(this.fileExt == null) return false;
		String mediaExt = "avi|wmv|wma|mpg|asx";
		return mediaExt.indexOf(this.fileExt.toLowerCase()) > -1;
	}

	public boolean isImage() {
		if(this.fileExt == null) return false;
		String imageExt = "jpg|gif|png";
		return imageExt.indexOf(this.fileExt.toLowerCase()) > -1;
	}

	public boolean isFlash() {
		if(this.fileExt == null) return false;
		String imageExt = "swf";
		return imageExt.indexOf(this.fileExt.toLowerCase()) > -1;
	}
	
	/**
	 * 테이블 명칭이 "TB_"로 시작하는 명명규칙을 따르지 않는다면 실제로 존재하지 않는 테이블이라고 판단한다.
	 * 
	 * @return
	 */
	public String getIsUsingTable() {
		if(ValidationUtils.isEmpty(this.isUsingTable)) {
			String result = "false";
			if (StringUtil.nvl(this.getParTableNm()).indexOf("TB_") == 0)
				result = "true";
			return result;
		} else {
			return this.isUsingTable;
		}
	}

	public void setIsUsingTable(String isUsingTable) {
		this.isUsingTable = isUsingTable;
	}	
}
