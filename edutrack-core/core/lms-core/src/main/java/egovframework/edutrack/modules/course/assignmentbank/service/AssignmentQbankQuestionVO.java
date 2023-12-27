package egovframework.edutrack.modules.course.assignmentbank.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.ValidationUtils;

/**
 * 과제 문제은행 문제 VO
 */
public class AssignmentQbankQuestionVO extends DefaultVO  {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -8655176953435079330L;
	private String	sbjCd;
	private int		qstnSn;
	private String	ctgrCd;
	private String	qstnTitle;
	private String	qstnCts;
	
    private List<AttachFileVO> attachImages;	// 첨부사진 목록
    private List<AttachFileVO> attachFiles;		// 첨부파일 목록

	/**
	 * @return the sbjCd
	 */
	public String getSbjCd() {
		return sbjCd;
	}
	/**
	 * @param sbjCd the sbjCd to set
	 */
	public void setSbjCd(String sbjCd) {
		this.sbjCd = sbjCd;
	}
	/**
	 * @return the qstnSn
	 */
	public int getQstnSn() {
		return qstnSn;
	}
	/**
	 * @param qstnSn the qstnSn to set
	 */
	public void setQstnSn(int qstnSn) {
		this.qstnSn = qstnSn;
	}
	/**
	 * @return the ctgrCd
	 */
	public String getCtgrCd() {
		return ctgrCd;
	}
	/**
	 * @param ctgrCd the ctgrCd to set
	 */
	public void setCtgrCd(String ctgrCd) {
		this.ctgrCd = ctgrCd;
	}
	/**
	 * @return the qstnTitle
	 */
	public String getQstnTitle() {
		return qstnTitle;
	}
	/**
	 * @param qstnTitle the qstnTitle to set
	 */
	public void setQstnTitle(String qstnTitle) {
		this.qstnTitle = qstnTitle;
	}
	/**
	 * @return the qstnCts
	 */
	public String getQstnCts() {
		return qstnCts;
	}
	/**
	 * @param qstnCts the qstnCts to set
	 */
	public void setQstnCts(String qstnCts) {
		this.qstnCts = qstnCts;
	}
	
	public List<AttachFileVO> getAttachFiles() {
		if(this.attachFiles == null)
			this.attachFiles = new ArrayList<AttachFileVO>();
		return this.attachFiles;
	}
	
	public void setAttachFiles(List<AttachFileVO> attachFiles) {
		this.attachFiles = attachFiles;
	}

	public List<AttachFileVO> getAttachImages() {
		if(this.attachImages == null)
			this.attachImages = new ArrayList<AttachFileVO>();
		return this.attachImages;
	}

	public void setAttachImages(List<AttachFileVO> attachImages) {
		this.attachImages = attachImages;
	}

	/**
	 * 파일을 추가
	 * @param fileSn
	 */
	public void addAttachFiles(AttachFileVO file) {
		this.getAttachFiles().add(file);
	}

	/**
	 * 파일의 번호만 파일목록에 추가
	 * @param fileSn
	 */
	public void addAttachFiles(Integer fileSn) {
		this.addAttachFiles(new AttachFileVO(this.ctgrCd,this.sbjCd,this.qstnSn, fileSn, "file"));
	}
	
	/**
	 * 파일의 번호 배열을 이용해서 파일을 추가
	 * @param fileSns
	 */
	public void addAttachFiles(List<Integer> fileSns) {
		for (Integer fileSn : fileSns) {
			if(ValidationUtils.isNotNull(fileSn))
				this.addAttachFiles(fileSn);
		}
	}

	/**
	 * 이미지 파일을 추가
	 * @param fileSn
	 */
	public void addAttachImages(AttachFileVO image) {
		this.getAttachImages().add(image);
	}
	
	/**
	 * 이미지 파일의 번호를 이용해서 파일을 추가
	 * @param fileSn
	 */
	public void addAttachImages(int fileSn) {
		this.addAttachImages(new AttachFileVO(this.ctgrCd,this.sbjCd,this.qstnSn, fileSn, "image"));
	}

	/**
	 * 이미지 파일의 번호 배열을 이용해서 파일을 추가
	 * @param imageSns
	 */
	public void addAttachImages(List<Integer> imageSns) {
		for (Integer imageSn : imageSns) {
			if(ValidationUtils.isNotNull(imageSns))
				this.addAttachFiles(imageSn);
		}
	}
	
	public String getJsonAttachFiles() {
		Map<String, Object> files = new Hashtable<String, Object>();
		files.put("file",  getAttachFiles());
		files.put("image", getAttachImages());
		return JsonUtil.getJsonString(files);
	}

	/**
	 * 이미지파일, 첨부파일의 번호만을 일괄적으로 ArrayList로 구한다.
	 * 
	 * @return
	 */
	public List<Integer> getAttachSerials() {
		List<Integer> fileSerials = new ArrayList<Integer>();
		for (AttachFileVO file : this.getAttachFiles()) {
			fileSerials.add(file.getFileSn());
		}
		for (AttachFileVO image : this.getAttachImages()) {
			fileSerials.add(image.getFileSn());
		}
		return fileSerials;
	}

}
