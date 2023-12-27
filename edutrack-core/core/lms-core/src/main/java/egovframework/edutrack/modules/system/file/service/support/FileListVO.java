package egovframework.edutrack.modules.system.file.service.support;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import egovframework.edutrack.modules.system.file.service.SysFileVO;


public class FileListVO {

	private final Map<String, List<SysFileVO>> fileMap = new Hashtable<String, List<SysFileVO>>();

	/**
	 * 파일 리스트를 파라매터로 받아서 파일타입에 맞도록 파일 컨테이너에 추가한다.
	 * @param fileList
	 */
	public FileListVO(List<SysFileVO> fileList) {
		super();
		this.addFile(fileList);
	}

	/**
	 * 단일 파일을 파일타입의 파일 컨테이너에 추가한다.
	 * @param file
	 */
	public void addFile(SysFileVO file) {
		if(!fileMap.containsKey(file.getFileType())) {	// 키가 없으면 리스트 생성
			fileMap.put(file.getFileType(), new ArrayList<SysFileVO>());
		}
		fileMap.get(file.getFileType()).add(file);
	}
	
	/**
	 * 파일 리스트를 각각의 파일타입에 맞도록 파일 컨테이너에 추가한다.
	 * @param fileList
	 */
	public void addFile(List<SysFileVO> fileList) {
		for (SysFileVO SysFileVO : fileList) {
			this.addFile(SysFileVO);
		}
	}
	
	/**
	 * 파일 타입에 해당하는 파일목록을 반환한다. 해당 파일 목록이 없으면 비어있는 리스트를 반환한다.
	 * @param key
	 * @return
	 */
	public List<SysFileVO> getFiles(String fileType) {
		if(fileMap.containsKey(fileType))
			return fileMap.get(fileType);
		else
			return new ArrayList<SysFileVO>();
	}
	
	/**
	 * 파일 타입에 해당하는 단일 파일을 반환한다.
	 * 해당 파일의 목록이 없을 경우 null을 반환하며, 목록의 크기가 2개이상일 경우 첫번째 항목을 반환한다.
	 * @param fileType
	 * @return
	 */
	public SysFileVO getFile(String fileType) {
		if(fileMap.containsKey(fileType)) {
			List<SysFileVO> list = fileMap.get(fileType);
			if(list.isEmpty())
				return null;
			else
				return list.get(0);
		}
		return null;
	}
	
	/**
	 * 모든 파일 목록을 병합해서 하나의 리스트로 반환한다.
	 * @return
	 */
	public List<SysFileVO> getFilesAll() {
		List<SysFileVO> fileList = new ArrayList<SysFileVO>();
		for (String fileType : fileMap.keySet()) {
			fileList.addAll(fileMap.get(fileType));
		}
		return fileList;
	}
}
