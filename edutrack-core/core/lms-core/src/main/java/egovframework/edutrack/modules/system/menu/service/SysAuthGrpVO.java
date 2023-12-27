package egovframework.edutrack.modules.system.menu.service;

import java.util.ArrayList;
import java.util.List;

import egovframework.edutrack.comm.service.DefaultVO;
import egovframework.edutrack.comm.util.web.ValidationUtils;

public class SysAuthGrpVO extends DefaultVO {

	private static final long serialVersionUID = -4005909597684571757L;

	private String	userNo;
	private String 	menuType;
	private String 	authGrpCd;
	private String	authGrpNm;
	private String	useYn = "Y";
	private String	authGrpDesc;
	private int		authGrpOdr;

	private List<SysAuthGrpLangVO> authGrpLangList;

	/**
	 * 생성자
	 */
	public SysAuthGrpVO() {
		//
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	/**
	 * @return the authGrpOdr
	 */
	public int getAuthGrpOdr() {
		return authGrpOdr;
	}

	/**
	 * @param authGrpOdr the authGrpOdr to set
	 */
	public void setAuthGrpOdr(int authGrpOdr) {
		this.authGrpOdr = authGrpOdr;
	}

	/**
	 * @return the authGrpCd
	 */
	public String getAuthGrpCd() {
		return authGrpCd;
	}

	/**
	 * @param authGrpCd the authGrpCd to set
	 */
	public void setAuthGrpCd(String authGrpCd) {
		this.authGrpCd = authGrpCd;
	}

	/**
	 * @return the authGrpDesc
	 */
	public String getAuthGrpDesc() {
		return authGrpDesc;
	}

	/**
	 * @param authGrpDesc the authGrpDesc to set
	 */
	public void setAuthGrpDesc(String authGrpDesc) {
		this.authGrpDesc = authGrpDesc;
	}

	/**
	 * @return the authGrpNm
	 */
	public String getAuthGrpNm() {
		return authGrpNm;
	}

	/**
	 * @param authGrpNm the authGrpNm to set
	 */
	public void setAuthGrpNm(String authGrpNm) {
		this.authGrpNm = authGrpNm;
	}

	/**
	 * @return the useYn
	 */
	public String getUseYn() {
		return useYn;
	}

	/**
	 * @param useYn the useYn to set
	 */
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	/**
	 * @return the menuType
	 */
	public String getMenuType() {
		return menuType;
	}

	/**
	 * @param menuType the menuType to set
	 */
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public List<SysAuthGrpLangVO> getAuthGrpLangList() {
		if(ValidationUtils.isEmpty(authGrpLangList)) authGrpLangList = new ArrayList<SysAuthGrpLangVO>();
		return authGrpLangList;
	}

	public void setAuthGrpLangList(List<SysAuthGrpLangVO> authGrpLangList) {
		this.authGrpLangList = authGrpLangList;
	}
}
