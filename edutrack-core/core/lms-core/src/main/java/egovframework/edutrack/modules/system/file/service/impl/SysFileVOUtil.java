package egovframework.edutrack.modules.system.file.service.impl;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ImageInfoVO;
import egovframework.edutrack.comm.util.web.ImageUtil;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.modules.system.file.service.SysFileVO;

/**
 * SysFileVO에서 필요한 매서드만 JSON으로 전송하기 위한 어댑터 클래스
 * @author SungKook
 *
 */
@SuppressWarnings("serial")
public class SysFileVOUtil {

	//@Autowired
	//private IOrgInfoService orgInfoService; //인터페이스 선언부

	private SysFileVOUtil() {}

	/**
	 * AttachmentFiles 를 위한 JsonString을 반환
	 * @param attachFiles
	 * @param editor 반환 Json의 형태가 에디터일 경우 true
	 * @return
	 */
	public static String getJson(SysFileVO SysFileVO, boolean editor) {
		List<SysFileVO> listSysFileVO = new ArrayList<SysFileVO>();
		listSysFileVO.add(SysFileVO);
		return SysFileVOUtil.getJson(listSysFileVO, editor);
	}

	/**
	 * AttachmentFiles 를 위한 JsonString을 반환
	 * @param attachFiles
	 * @param editor 반환 Json의 형태가 에디터일 경우 true
	 * @return
	 */
	public static String getJson(List<? extends SysFileVO> attachFiles, boolean editor) {
		if(editor) {
			return JsonUtil.getJsonString(SysFileVOUtil.toJsonAttachImagesForEditorWrapper(attachFiles));
		} else {
			return JsonUtil.getJsonString(SysFileVOUtil.toJsonAttachFiles(attachFiles));
		}
	}

	/**
	 * SysFileVO에 해당하는 JsonData String을 생성.
	 * @param SysFileVO
	 * @return
	 * @deprecated getJson(..)을 사용하세요..
	 */
	@Deprecated
	public static String getJsonString(SysFileVO SysFileVO) {
		// 비어있는 객체면 javascript 명령줄을 반환.
		if(SysFileVO.getFileSn() == null || SysFileVO.getFileSn() == 0)
			return "[]";

		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object obj, String name, Object value) {
				if(obj instanceof SysFileVO && name.equals("json")) {
					return true;
				}
				return false;
			}
		});
		return JsonUtil.getJsonString(getJsonData(SysFileVO));
	}

	/**
	 * {@code List<? extends SysFileVO>} 를 다음에디터에서 필요로 attachment 구조로 변환.
	 * image만 포함한다. 실제 자료 구조는 아래와 같다.
	 * <pre>
	 * attachment --- ['file'] --- {'attacher' : 'file'}, {'data' : {'fileNm' : 'fileNm', ....}}}
	 *             -- ['image'] --- {'attacher' : 'image'}, {'data' : {'fileNm' : 'fileNm', ....}}
	 *                          --- {'attacher' : 'image'}, {'data' : {'fileNm' : 'fileNm', ....}}
	 *                          }
	 * </pre>
	 * @param attachFiles SysFileVO를 상속받은 개체의 List
	 * @return
	 */
	public static Map<String, Object> toJsonAttachImagesForEditorWrapper(List<? extends SysFileVO> attachFiles) {
		Map<String, Object> outerMap = new Hashtable<String, Object>();
		Map<String, Object> innerMap = new Hashtable<String, Object>();
		List<Object> fileList = new ArrayList<Object>();

		// SysFileVO를 직렬화 한 Map을 List에 넣는다.
		for (SysFileVO file : attachFiles) {
			innerMap = new Hashtable<String, Object>();
			innerMap.put("attacher", 	file.getFileType());
			innerMap.put("data", 		SysFileVOUtil.getJsonData(file));
			fileList.add(innerMap);
		}

		outerMap.put("image", fileList);
		return outerMap;
	}

	/**
	 * {@code List<? extends SysFileVO>} 를 $M.AttachFiles에서 사용하는 간략한 JSON 구조로 변형한 List를 반환.
	 * @param attachFiles SysFileVO를 상속받은 개체의 List
	 * @return
	 */
	public static List<Object> toJsonAttachFiles(List<? extends SysFileVO> attachFiles) {
		List<Object> fileList = new ArrayList<Object>();
		for (SysFileVO file : attachFiles) {
			fileList.add(SysFileVOUtil.getJsonData(file));
		}
		return fileList;
	}


	/**
	 * 동일 유형의 구조화한 SysFileVO Map을 List에 넣는다.
	 * @param files
	 * @return
	 */
	public static List<Object> toJsonAttachImagesForEditor(List<? extends SysFileVO> files) {
		List<Object> fileList = new ArrayList<Object>();
		Map<String, Object> innerMap = new Hashtable<String, Object>();
		// SysFileVO를 직렬화 한 Map을 List에 넣는다.
		for (SysFileVO file : files) {
			innerMap.clear();
			innerMap.put("attacher", 	file.getFileType());
			innerMap.put("data", 		SysFileVOUtil.getJsonData(file));
			fileList.add(innerMap);
		}
		return fileList;
	}

	// 다음에디터 attachment.data 전송을 위한 getter..
	public static Map<String, Object> getJsonData(SysFileVO SysFileVO) {
		Map<String, Object> data = new Hashtable<String, Object>();

		if(SysFileVO == null || SysFileVO.getFileNm() == null || SysFileVO.getFileSn() == 0 )
			return data;

		
		String fileName = SysFileVO.getFileNm();
		
		if(fileName != null && !"".equals(fileName)) {
			fileName = fileName.replaceAll("/", "");
			fileName = fileName.replaceAll("\\\\", ""); 
			fileName = fileName.replaceAll("[.]{2}", "");    
			fileName = fileName.replaceAll("&", "");    
			fileName = fileName.replaceAll("%", "");     
		} 
		SysFileVO.setFileNm(fileName);
		
		// both
		data.put("fileSn",		SysFileVO.getFileSn());
		data.put("filename",	SysFileVO.getFileNm());
		data.put("filesize",	SysFileVO.getFileSize());

		// image only
		data.put("imagealign",	Constants.EDITOR_IMAGE_ALIGN);
		data.put("imageurl",	getViewUrl(SysFileVO));
		data.put("thumburl",	getThumbUrl(SysFileVO));
		data.put("originalurl",	getDownloadUrl(SysFileVO));
		// 이미지 정보를 얻을 수 있다면 해당 정보도 같이 리턴
		ImageInfoVO imageInfo = null;
		//ImageInfoVO imageInfo = ImageUtil.getSize(new File(Constants.FILE_STORAGE_PATH + SysFileVO.getSaveFilePath()));
		if(imageInfo != null) {
			data.put("width",	imageInfo.getWidth());
			data.put("height",	imageInfo.getHeight());
		}

		// file only
		data.put("filemime",	SysFileVO.getMimeType());
		data.put("attachurl",	getDownloadUrl(SysFileVO));
		return data;
	}

	/**
	 * 파일 DTO를 이용해서 다운로드 태그 문자열을 생성.
	 * @param SysFileVO
	 * @return
	 */
	public static String getDownloadTag(SysFileVO SysFileVO) {
		StringBuilder tag = new StringBuilder();

		try {
			String icon = iconMap.get(SysFileVO.getFileExt());
			if(icon == null)
				icon = iconMap.get("_default");

			tag.append("<a href=\"javascript:fileDown('" + SysFileVO.getFileSn() + "');\"");	
			tag.append("class='undelegate' ");
			tag.append("style='cursor:pointer;' ");
			//-- 웹접근성 : 키보드 이벤트 때문에 삭제
			//tag.append("onmouseover=\"this.style.textDecoration='underline'\" ");
			//tag.append("onmouseout=\"this.style.textDecoration='none'\" ");
			tag.append("title='Download: " + SysFileVO.getFileNm() + "' ");
			tag.append(">");
			tag.append("<img src='");
			tag.append(Constants.IMAGE_PATH_ICON + "filetype/" + icon);
			tag.append("' alt='"+icon+" icon'/> ");
			tag.append(SysFileVO.getFileNm() + " (" + byteConvertor(SysFileVO.getFileSize()) + ") ");
			tag.append("</a>");
		} catch (Exception ignored) {
			return "";
		}
		return tag.toString();
	}
	/**
	 * 파일 DTO를 이용해서 다운로드 태그 문자열을 생성.
	 * @param SysFileVO
	 * @return
	 */
	public static String getLecDownloadTag(SysFileVO SysFileVO) {
		StringBuilder tag = new StringBuilder();

		try {
			String icon = iconMap.get(SysFileVO.getFileExt());
			if(icon == null)
				icon = iconMap.get("_default");
			tag.append("<a href=\"javascript:fileDown('" + SysFileVO.getFileSn() + "');\" class=\"file_down\">");	
			tag.append("<img src=\"/img/filetype/file_"+icon+".png\" alt=\"\">");	
			tag.append("<span class=\"text\">"+SysFileVO.getFileNm()+"</span>");
			tag.append("<span class=\"fileSize\">("+byteConvertor(SysFileVO.getFileSize())+")</span>");
			tag.append("</a>   ");                 
			tag.append("<span class=\"link\">");
			tag.append("<a class=\"btn-line btn-down\" href=\"javascript:fileDown('" + SysFileVO.getFileSn() + "');\" title=\""+SysFileVO.getFileNm()+" 다운로드\">다운로드<i></i></a>");
			tag.append("</span>");


		} catch (Exception ignored) {
			return "";
		}
		return tag.toString();
	}
	/**
	 * 파일 DTO를 이용해서 이미지 미리보기 태그 문자열을 생성.
	 * @param SysFileVO
	 * @return
	 */
	public static String getImageTag(SysFileVO SysFileVO) {
		StringBuilder tag = new StringBuilder();
		try {
			if(SysFileVO.getMimeType().indexOf("image") < 0) return "";
			tag.append("<img src='" + getViewUrl(SysFileVO) + "' alt = '" + SysFileVO.getFileNm() + "'>");
		} catch (Exception ignored) {
			return "";
		}
		return tag.toString();
	}

	public static String getViewUrl(SysFileVO SysFileVO) {
		return StringUtil.nvl(Constants.FILESERVER_URL) + Constants.CONTEXT_FILE_VIEW + SysFileVO.getFileSnName();
	}

	public static String getThumbUrl(SysFileVO SysFileVO) {
		return StringUtil.nvl(Constants.FILESERVER_URL) + Constants.CONTEXT_FILE_THUMB + SysFileVO.getFileSnName();
	}

	public static String getDownloadUrl(SysFileVO SysFileVO) {
		return StringUtil.nvl(Constants.FILESERVER_URL) + Constants.CONTEXT_FILE_DOWNLOAD + SysFileVO.getFileSnName();
	}

	/**
	 * byte를 용량에 따라 b, kb, mb, gb, tb로 계산하여 리턴함 (JavaScript)
	 * @param long bytes
	 * @return String
	 */
	public static String byteConvertor(long bytes) {
		try {
			String[] s = {"bytes", "KB", "MB", "GB", "TB", "PB"};
			double e = Math.floor(Math.log(bytes)/Math.log(1024));
			double value = (bytes / Math.pow(1024, Math.floor(e)));

			DecimalFormat floor = new DecimalFormat("#");
			int idx = Integer.parseInt(floor.format(e));

			DecimalFormat format = new DecimalFormat("#####.##");
			return format.format(value) + " " + s[idx];
		} catch (NumberFormatException ex) {
			return "";
		}
	}

	/**
	 * 문자열 스트링으로 넘어온 값을 {@code List<SysFileVO>} 형태로 변환해서 반환한다.
	 * @param source 구분자로 구분된 문자열
	 * @param delimiter 구분자 문자열
	 * @return
	 */
	// 구분자를 임의로 바꿔서 사용할 필요가 있을 때 public으로 변경해서 사용할 것.
	private static List<SysFileVO> convertStringToSysFileVOList(String source, String delimiter) {
		String[] strArray = source.split(delimiter);
		List<SysFileVO> listSysFileVO = new ArrayList<SysFileVO>();
		for (String str : strArray) {
			try {
				Integer intValue = Integer.parseInt(str);
				listSysFileVO.add(new SysFileVO(intValue)); // Integer 파싱에 실패한 값은 저장되지 않는다.
			} catch (NumberFormatException ignore) {}
		}
		return listSysFileVO;
	}

	/**
	 * 문자열 스트링으로 넘어온 값을 {@code List<SysFileVO>} 형태로 변환해서 반환한다.
	 * 구분자로 {@code Constants.SEPERATER_PARAM}를 사용한다.
	 * @param source 구분자로 구분된 문자열
	 * @return
	 */
	public static List<SysFileVO> convertStringToSysFileVOList(String source) {
		return convertStringToSysFileVOList(source, Constants.SEPERATER_PARAM);
	}


	/**
	 * {@code List<SysFileVO>} 를 구분자를 포함한 문자열로 변환한다.
	 * @param listSysFileVO 파일 목록
	 * @param delimiter 사용할 구분자 문자열
	 * @return
	 */
	// 구분자를 임의로 바꿔서 사용할 필요가 있을 때 public으로 변경해서 사용할 것.
	private static String convertSysFileVOListToString(List<SysFileVO> listSysFileVO, String delimiter) {
		String value = "";
		for (SysFileVO SysFileVO : listSysFileVO) {
			value += (value.equals("")) ? SysFileVO.getFileSn() : delimiter + SysFileVO.getFileSn();
		}
		return value;
	}

	/**
	 * {@code List<SysFileVO>} 를 구분자를 포함한 문자열로 변환한다.
	 * @param listSysFileVO 파일 목록
	 * @param delimiter 사용할 구분자 문자열
	 * @return
	 */
	public static String convertSysFileVOListToString(List<SysFileVO> listSysFileVO) {
		return convertSysFileVOListToString(listSysFileVO, Constants.SEPERATER_PARAM);
	}

	/**
	 * 파일 형식 아이콘 맵 (필요할 경우 확장)
	 */
	private final static Map<String, String> iconMap = new Hashtable<String, String>() {{
		// 압축파일
		put("alz", 		"alz.gif");
		put("zip", 		"zip.gif");
		put("rar", 		"rar.gif");
		put("7z", 		"zip.gif");

		// 오피스
		put("doc", 		"doc.gif");
		put("docx", 	"doc.gif");
		put("ppt", 		"ppt.gif");
		put("pptx", 	"ppt.gif");
		put("xls", 		"xls.gif");
		put("xlsx", 	"xls.gif");

		// HTML
		put("html", 	"html.gif");
		put("htm", 		"html.gif");

		// 문서
		put("hwp", 		"hwp.gif");
		put("pdf", 		"pdf.gif");
		put("xml", 		"xml.gif");
		put("txt", 		"text.gif");

		// 오디오
		put("mp3", 		"mp3.gif");
		put("wma", 		"mp3.gif");
		put("wav", 		"mp3.gif");

		// 비디오
		put("avi", 		"movie.gif");
		put("wmv", 		"movie.gif");
		put("ra", 		"ra.gif");

		// 사진
		put("gif", 		"gif.gif");
		put("jpg", 		"jpg.gif");
		put("bmp", 		"bmp.gif");
		put("png", 		"image.gif");

		// 기타
		put("_default",	"default.gif");
	}};

	/**
	 * 파일이 이미지일 경우 Thumbnail을 생성한다.
	 * @param SysFileVO
	 */
	public static void thumbNameGenerate(SysFileVO SysFileVO) throws Exception {
		String saveFullPath = Constants.FILE_STORAGE_PATH + SysFileVO.getSaveDirectoryPath();
		
		String fileName = SysFileVO.getFileSaveNm();
		if(fileName != null && !"".equals(fileName)) {
			fileName = fileName.replaceAll("/", "");
			fileName = fileName.replaceAll("\\\\", ""); 
			fileName = fileName.replaceAll("[.]{2}", "");    
			fileName = fileName.replaceAll("&", "");    
			fileName = fileName.replaceAll("%", "");     
		} 
		SysFileVO.setFileSaveNm(fileName);
		File saveFile = new File(saveFullPath + File.separator + SysFileVO.getFileSaveNm());

		// 파일에 접근이 가능하고 이미지일 경우 썸네일을 만든다.
		if( saveFile.canRead() && SysFileVO.getMimeType().indexOf("image/") > -1) {
			ImageUtil.resize(saveFile, getThumbnailName(saveFile), Constants.THUMB_FIX_WIDTH, ImageUtil.RATIO);
		}
	}

	public static File getThumbnailName(File file) {
		String src = file.getAbsolutePath();
		String ext = StringUtil.getExt(src);
		String dest = src.substring(0, src.lastIndexOf(ext)) + "_thumb.jpg";
		return new File(dest);
	}
}
