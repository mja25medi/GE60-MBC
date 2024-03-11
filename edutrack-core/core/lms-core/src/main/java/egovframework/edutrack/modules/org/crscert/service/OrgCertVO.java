package egovframework.edutrack.modules.org.crscert.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;

public class OrgCertVO extends DefaultVO{

	private static final long serialVersionUID = 5746304581030788478L;

	private String orgCd;
	private String useYn;
	private String printDirec;
	private String certContentJson;
	private String regNo;
	private String regDttm;
	private String modNo;
	private String modDttm;
	
	private String encFileSn;
	private Integer fileSn;

	// 파일 업로드
	private List<SysFileVO>	attachFiles;    		/*관련서류첨부 파일목록*/
		
	// 파일 업로드
	private List<SysFileVO>	attachFiles2;    		/*관련서류첨부 파일목록*/

	
	public String getOrgCd() {
		return orgCd;
	}


	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}


	public String getUseYn() {
		return useYn;
	}


	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}


	public String getPrintDirec() {
		return printDirec;
	}


	public void setPrintDirec(String printDirec) {
		this.printDirec = printDirec;
	}


	public String getCertContentJson() {
		return certContentJson;
	}


	public void setCertContentJson(String certContentJson) {
		this.certContentJson = certContentJson;
	}


	public String getRegNo() {
		return regNo;
	}


	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}


	public String getRegDttm() {
		return regDttm;
	}


	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}


	public String getModNo() {
		return modNo;
	}


	public void setModNo(String modNo) {
		this.modNo = modNo;
	}


	public String getModDttm() {
		return modDttm;
	}


	public void setModDttm(String modDttm) {
		this.modDttm = modDttm;
	}


	public String getEncFileSn() {
		return encFileSn;
	}


	public void setEncFileSn(String encFileSn) {
		this.encFileSn = encFileSn;
	}


	public Integer getFileSn() {
		return fileSn;
	}


	public void setFileSn(Integer fileSn) {
		this.fileSn = fileSn;
	}


	// 관련서류첨부 파일목록
		public String getAttachFileSns() {
			return SysFileVOUtil.convertSysFileVOListToString(this.getAttachFiles());
		}
		public void setAttachFileSns(String attachFileSns) {
			this.setAttachFiles(SysFileVOUtil.convertStringToSysFileVOList(attachFileSns));
		}

		public List<SysFileVO> getAttachFiles() {
			if (this.attachFiles == null)
				this.attachFiles = new ArrayList<SysFileVO>();
			return this.attachFiles;
		}
		public void setAttachFiles(List<SysFileVO> attachFiles) {
			this.attachFiles = attachFiles;
		}

		public String getAttachFilesJson() {
			return SysFileVOUtil.getJson(this.getAttachFiles(), false);
		}
		
		// 관련서류첨부 파일목록
		public String getAttachFileSns2() {
			return SysFileVOUtil.convertSysFileVOListToString(this.getAttachFiles2());
		}
		public void setAttachFileSns2(String attachFileSns2) {
			this.setAttachFiles2(SysFileVOUtil.convertStringToSysFileVOList(attachFileSns2));
		}

		public List<SysFileVO> getAttachFiles2() {
			if (this.attachFiles2 == null)
				this.attachFiles2 = new ArrayList<SysFileVO>();
			return this.attachFiles2;
		}
		public void setAttachFiles2(List<SysFileVO> list) {
			this.attachFiles2 = list;
		}

		public String getAttachFilesJson2() {
			return SysFileVOUtil.getJson(this.getAttachFiles2(), false);
		}


	
}
