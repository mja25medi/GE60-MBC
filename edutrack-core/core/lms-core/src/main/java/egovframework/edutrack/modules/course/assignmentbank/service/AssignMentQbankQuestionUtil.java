package egovframework.edutrack.modules.course.assignmentbank.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import egovframework.edutrack.modules.system.file.service.impl.SysFileVOUtil;



/**
 * 게시판 - 일반 게시글 관련 JSON 어뎁터
 * <pre>
 * 업  무 : 게시판 - 일반 게시판
 * </pre>
 * @author SungKook
 */
public class AssignMentQbankQuestionUtil {
	
	private AssignMentQbankQuestionUtil() {}
	
	/**
	 * 게시글에 첨부되어 있는 모든 파일을 다음에디터 attachment 구조로 변환. 현재는 image만을 추출.
	 * <pre>
	 * attachment --- ['file'] --- {'attacher' : 'file'}, {'data' : {'fileNm' : 'fileNm', ....}}} 
	 *             -- ['image'] --- {'attacher' : 'image'}, {'data' : {'fileNm' : 'fileNm', ....}} 
	 *                          --- {'attacher' : 'image'}, {'data' : {'fileNm' : 'fileNm', ....}}
	 *                          }
	 * </pre>
	 * @param VO
	 * @return
	 */
	
	/**
	 * 동일 유형의 구조화한 FileVO Map을 List에 넣는다.
	 * @param files
	 * @return
	 */
	public static Map<String, Object> toJsonAttachImagesForEditorWrapper(AssignmentQbankQuestionVO VO) {
		Map<String, Object> data = new Hashtable<String, Object>();
		data.put("image", SysFileVOUtil.toJsonAttachImagesForEditor(VO.getAttachImages()));
		// 첨부파일은 별도로 관리하므로 이곳에서 제외시킴.
		//data.put("file",  bbsAttachFiles(VO.getAttachFiles()));
		return data;
	}

	/**
	 * {@code ArticleVO#getAttachFiles()}를 JSON 구조로 변형한 List를 반환.
	 * @param VO
	 * @return
	 */
	public static List<Object> toJsonAttachFiles(AssignmentQbankQuestionVO VO) {
		List<Object> fileList = new ArrayList<Object>();
		for (AttachFileVO file : VO.getAttachFiles()) {
			fileList.add(SysFileVOUtil.getJsonData(file));
		}
		return fileList;
	}
}
