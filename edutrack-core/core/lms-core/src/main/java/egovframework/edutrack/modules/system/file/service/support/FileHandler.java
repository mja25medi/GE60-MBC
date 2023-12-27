package egovframework.edutrack.modules.system.file.service.support;

import java.util.List;

import egovframework.edutrack.modules.system.file.service.SysFileVO;


/**
 * SysFileVO, List<SysFileVO>를 맴버로 가지고 있는 VO가 SysFileService에 파일 정보 CRUD를 요청할때
 * 본 인터페이스를 구현해서 같이 전달하여야 한다.
 * @author SungKook
 *
 * @param <T>
 */
public interface FileHandler<T> {

	/**
	 * 구현체에서 명시하는 파일 저장소 코드를 반환한다.<br>
	 * <b>파일저장소를 결정하는 요소</b> 
	 * @return
	 */
	public abstract String getRepoCd();
	
	/**
	 * 주키항목값을 취합해서 문자열로 반환하는 인터페이스<br>
	 * <b>cascade화된 주키는 '|'를 구분자로 해서 반환하도록 구현하길 권고.</b> 
	 * @param dto
	 * @return
	 */
	public abstract String getPK(T dto);
	
	/**
	 * 해당 DTO의 모든 SysFileVO 개체를 병합해서 하나의 리스트로 반환한다.<br>
	 * <b>파일 테이블에 저장하기 위해 병합하는 단계에서 사용됨.</b>
	 * @param dto
	 * @return
	 */
	public abstract List<SysFileVO> getFiles(T dto);
	
	/**
	 * FileList상의 파일을 dto객체의 각 맴버에 바인딩 시킨다.<br>
	 * <b>엔티티단위로 조회된 리스트에서 fileType값에 따라 각 맴버에 바인딩 시킬때 사용됨.</b>
	 * @param dto
	 * @param fileListVO
	 * @return
	 */
	public abstract T setFiles(T dto, FileListVO fileListVO);
}
