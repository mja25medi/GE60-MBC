/**
 *
 */
package egovframework.edutrack.modules.teacher.recd.service;

import java.util.List;

@SuppressWarnings("serial")
public class TchRecdForm{

	private TchRecdVO			tchRecdVO;
	private List<TchRecdVO>	tchRecdList;

	public TchRecdForm() {
		this.tchRecdVO = new TchRecdVO();
	}

	public TchRecdVO getTchRecdVO() {
		return tchRecdVO;
	}

	public void setTchRecdVO(TchRecdVO tchRecdVO) {
		this.tchRecdVO = tchRecdVO;
	}

	public List<TchRecdVO> getTchRecdList() {
		return tchRecdList;
	}

	public void setTchRecdList(List<TchRecdVO> tchRecdList) {
		this.tchRecdList = tchRecdList;
	}
}