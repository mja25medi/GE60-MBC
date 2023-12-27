package egovframework.edutrack.modules.course.contents.service;

import java.util.ArrayList;
import java.util.List;


public class ContentsFileVO  {

	protected String orgCd = "";
	protected String fileId = "";
    protected String fileName = "";
    protected String fileDir = "";
    protected String linkDir = "";
    protected String fileType = "";
    protected boolean thisDir = false;
    protected boolean thisFile = false;
    protected String fileTime = "";
    protected long fileSize = 0;
    protected String gubun="";

	private List<ContentsFileVO> subList;

	public ContentsFileVO() {
		this.subList = new ArrayList<ContentsFileVO>();
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileDir() {
		return fileDir;
	}

	public void setFileDir(String fileDir) {
		this.fileDir = fileDir;
	}

	public String getLinkDir() {
		return linkDir;
	}

	public void setLinkDir(String linkDir) {
		this.linkDir = linkDir;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public boolean isThisDir() {
		return thisDir;
	}

	public void setThisDir(boolean thisDir) {
		this.thisDir = thisDir;
	}

	public boolean isThisFile() {
		return thisFile;
	}

	public void setThisFile(boolean thisFile) {
		this.thisFile = thisFile;
	}

	public String getFileTime() {
		return fileTime;
	}

	public void setFileTime(String fileTime) {
		this.fileTime = fileTime;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public List<ContentsFileVO> getSubList() {
		return subList;
	}

	public void setSubList(List<ContentsFileVO> subList) {
		this.subList = subList;
	}

	public String getGubun() {
		return gubun;
	}

	public void setGubun(String gubun) {
		this.gubun = gubun;
	}

	public String getOrgCd() {
		return orgCd;
	}

	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}

}
