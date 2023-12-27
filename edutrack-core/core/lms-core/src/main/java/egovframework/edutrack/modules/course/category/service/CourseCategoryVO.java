/**
 *
 */
package egovframework.edutrack.modules.course.category.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.modules.course.course.service.CourseVO;



/**
 * 과정 분류 VO
 */
public class CourseCategoryVO extends DefaultVO {
	
	private static final long	serialVersionUID	= 7361286758818622883L;
	private String  orgCd;
	private String 	crsCtgrCd;
	private String	crsCtgrNm;
	private String	parCrsCtgrCd;
	private int		crsCtgrLvl=0;
	private int		crsCtgrOdr=0;
	private String	crsCtgrDesc;
	private String	crsCtgrPath;
	private String	crsCtgrPathNm;
	private String	crsCtgrImg;
	private String	useYn = "Y";
	
	private int		subCnt=0;
	private int		crsCnt=0;
	private int		parCrsCtgrLvl;
	private String	parCrsCtgrPath;
	private String	parCrsCtgrNm = "최상위 분류";
	
	private List<CourseVO> courseList = null;
	
	/**
	 * 생성자
	 */
	public CourseCategoryVO() {
		//courseList = new ArrayList<CourseVO>();
		//
	}
	
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getCrsCtgrCd() {
		return crsCtgrCd;
	}
	public void setCrsCtgrCd(String crsCtgrCd) {
		this.crsCtgrCd = crsCtgrCd;
	}
	public String getCrsCtgrNm() {
		return crsCtgrNm;
	}
	public void setCrsCtgrNm(String crsCtgrNm) {
		this.crsCtgrNm = crsCtgrNm;
	}
	public String getParCrsCtgrCd() {
		return parCrsCtgrCd;
	}
	public void setParCrsCtgrCd(String parCrsCtgrCd) {
		this.parCrsCtgrCd = parCrsCtgrCd;
	}
	public int getCrsCtgrLvl() {
		return crsCtgrLvl;
	}
	public void setCrsCtgrLvl(int crsCtgrLvl) {
		this.crsCtgrLvl = crsCtgrLvl;
	}
	public int getCrsCtgrOdr() {
		return crsCtgrOdr;
	}
	public void setCrsCtgrOdr(int crsCtgrOdr) {
		this.crsCtgrOdr = crsCtgrOdr;
	}
	public String getCrsCtgrDesc() {
		return crsCtgrDesc;
	}
	public void setCrsCtgrDesc(String crsCtgrDesc) {
		this.crsCtgrDesc = crsCtgrDesc;
	}
	public String getCrsCtgrPath() {
		return crsCtgrPath;
	}
	public void setCrsCtgrPath(String crsCtgrPath) {
		this.crsCtgrPath = crsCtgrPath;
	}
	public String getCrsCtgrPathNm() {
		return crsCtgrPathNm;
	}
	public void setCrsCtgrPathNm(String crsCtgrPathNm) {
		this.crsCtgrPathNm = crsCtgrPathNm;
	}
	public String getCrsCtgrImg() {
		return crsCtgrImg;
	}
	public void setCrsCtgrImg(String crsCtgrImg) {
		this.crsCtgrImg = crsCtgrImg;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public int getSubCnt() {
		return subCnt;
	}
	public void setSubCnt(int subCnt) {
		this.subCnt = subCnt;
	}
	public int getCrsCnt() {
		return crsCnt;
	}
	public void setCrsCnt(int crsCnt) {
		this.crsCnt = crsCnt;
	}
	public int getParCrsCtgrLvl() {
		return parCrsCtgrLvl;
	}
	public void setParCrsCtgrLvl(int parCrsCtgrLvl) {
		this.parCrsCtgrLvl = parCrsCtgrLvl;
	}
	public String getParCrsCtgrPath() {
		return parCrsCtgrPath;
	}
	public void setParCrsCtgrPath(String parCrsCtgrPath) {
		this.parCrsCtgrPath = parCrsCtgrPath;
	}
	public String getParCrsCtgrNm() {
		return parCrsCtgrNm;
	}
	public void setParCrsCtgrNm(String parCrsCtgrNm) {
		this.parCrsCtgrNm = parCrsCtgrNm;
	}
	public List<CourseVO> getCourseList() {
		return courseList;
	}
	public void setCourseList(List<CourseVO> courseList) {
		this.courseList = courseList;
	}
}