package egovframework.edutrack.comm.util.web;

import egovframework.edutrack.comm.service.JsTreeVO;


public class JsTreeUtil {

	/**
	 * JsTreeVO 객체를 셋팅하여 반환한다.
	 * @param id
	 * @param title
	 * @param rel
	 * @param state
	 * @param obj<VO 객체>
	 * @return
	 */
	public static JsTreeVO getJsTreeVO(String id, String title, String rel, int state, Object obj) {
		JsTreeVO treeVO = new JsTreeVO();
		treeVO.setData(title);
		treeVO.setState(state);
		treeVO.addAttr("id", id);
		treeVO.addAttr("title", title);
		treeVO.addAttr("rel", rel);
		treeVO.addAttr(obj);
		return treeVO;
	}

}
