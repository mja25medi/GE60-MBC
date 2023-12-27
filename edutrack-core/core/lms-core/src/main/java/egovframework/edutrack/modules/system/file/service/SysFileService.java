package egovframework.edutrack.modules.system.file.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;

public interface SysFileService {

	/**
	 * 멀티파트 파일 업로드 처리 서비스
	 * @param multipartFile 첨부 파일 정보
	 * @param repository 파일이 저장될 repository명
	 * @param fileType 파일 타입 정보
	 * @return
	 * @throws Throwable
	 */
	public abstract SysFileVO upload(MultipartFile multipartFile,
			String repository, String organization, String fileType,
			String regNo) throws Exception;

	/**
	 * 인자로 받은 SysFileVO를 복제해서 새로운 SysFileVO를 반환한다.<br>
	 * 물리적인 파일을 포함한다.
	 * @param vo 복사 대상 SysFileVO
	 * @return 복사된 결과 SysFileVO
	 * @throws IOException
	 */
	public abstract SysFileVO copyFile(SysFileVO vo) throws Exception;

	/**
	 * 파일 업로드 처리 서비스. type은 기본값 'file'로 설정.
	 * @param multipartFile 첨부파일 정보
	 * @param repository 저장 영역 정보
	 * @return
	 * @throws Throwable
	 */
	public abstract SysFileVO upload(MultipartFile multipartFile,
			String repository, String organization, String regNo)
			throws Throwable;

	/**
	 * 파일 업로드 처리 서비스. Repository 영역을 기본값인 'general'로 설정.
	 * @param multipartFile
	 * @return
	 * @throws Throwable
	 */
	public abstract SysFileVO upload(MultipartFile multipartFile, String regNo)
			throws Throwable;

	/**
	 * 파일 정보 조회. 조회에 대한 정보를 업데이트 할지 결정할 수 있다.
	 * @param vo
	 * @param updateEnabled
	 * @return
	 */
	public abstract SysFileVO getFile(SysFileVO vo, boolean updateEnabled)
			throws Exception;

	/**
	 * 파일정보 조회. 카운트 정보 업데이트 여부를 true로 설정해서 조회한다.
	 * @param vo
	 */
	public abstract SysFileVO getFile(SysFileVO vo) throws Exception;

	/**
	 * 파일 삭제.<p>
	 * 파일정보를 DB에서 먼저 삭제한 뒤. 실제 파일 삭제를 시도한다.
	 * 삭제중 오류가 발생하면 DB정보도 롤백된다.
	 */
	public abstract void removeFile(SysFileVO vo) throws Exception;

	/**
	 * 파일 삭제.<p>
	 * 파일정보를 DB에서 먼저 삭제한 뒤. 실제 파일 삭제를 시도한다.
	 * 삭제중 오류가 발생하면 DB정보도 롤백된다.
	 */
	public abstract void removeFile(Integer fileSn) throws Exception;

	/**
	 * 파일리스트의 각 파일과 연결되는 본래 데이터의 연결관계를 확정해서 저장한다. (UPDATE)
	 * @param vo 파일과 연결된 원 데이터
	 * @param selector 원 데이터에서 직렬화된 주키를 추출하는 구현체
	 * @param <D> 파일과 연결된 본래 데이터 타입
	 * @return
	 */
	public abstract <D> void bindFile(D vo, FileHandler<D> selector)
			throws Exception;

	/**
	 * 파일리스트의 각 파일과 연결되는 본래 데이터의 연결관계를 제거한다. (UPDATE)
	 * @param <D> 파일과 연결된 본래 데이터 타입
	 * @param vo 파일과 연결된 원 데이터
	 * @param selector 원 데이터에서 직렬화된 주키를 추출하는 구현체
	 */
	public abstract <D> void unbindFile(D vo, FileHandler<D> selector)
			throws Exception;

	/**
	 * 컨텐츠 수정시 사용. 기존 파일의 연결정보를 제거하고 연결관계를 다시 저장한다. (UPDATE)<br>
	 * 내부적으로 연결된 파일 정보 컬럼을 일괄 제거한 뒤 다시 저장한다.
	 * @param vo 파일과 연결된 원 데이터
	 * @param handler 원 데이터에서 직렬화된 주키를 추출하는 구현체
	 * @param <D> 파일과 연결된 본래 데이터 타입
	 * @return
	 */
	public abstract <D> void bindFileUpdate(D vo, FileHandler<D> handler)
			throws Exception;

	/**
	 * 파일저장소코드와 연결데이터의 직렬화된 주키정보를 모아서 해당 파일 목록을 반환한다.
	 * @param <D> 파일과 연결된 본래 데이터 타입
	 * @param vo 파일과 연결된 원 데이터
	 * @param handler 원 데이터에서 직렬화된 주키를 추출하는 구현체
	 * @return 파일타입으로 구분된 FileListVO
	 */
	public abstract <D> D getFile(D vo, FileHandler<D> handler)
			throws Exception;

	/**
	 * 파일저장소코드와 연결데이터의 직렬화된 주키정보의 목록에 해당 파일 정보를 바인딩 한다.
	 * 쿼리가 List의 갯수만큼 수행되므로 성능 저하를 감안하고 사용해야 한다.
	 * @param <D> 파일과 연결된 본래 데이터 타입
	 * @param voList 파일과 연결된 원 데이터의 목록
	 * @param handler 원 데이터에서 직렬화된 주키를 추출하는 구현체
	 * @return 파일타입으로 구분된 FileListVO
	 */
	public abstract <D> List<D> getFile(List<D> voList, FileHandler<D> handler)
			throws Exception;

	/**
	 * 파일과 연결된 본 데이터의 정보로 파일을 일괄삭제한다.
	 * @param <D>
	 * @param vo
	 * @param selector
	 */
	public abstract <D> void removeFile(D vo, FileHandler<D> selector)
			throws Exception;

	/**
	 * 파일리포지토리에서 조건에 일치하는 파일 목록을 일괄 조회한다.
	 * @param vo : repoCd, fileBindDataSn 항목으로 조회할 경우 해당 자료에 연결된 파일 목록을 반환한다.
	 * @return
	 */
	public abstract ProcessResultListVO<SysFileVO> list(SysFileVO vo)
			throws Exception;

	/**
	 * 파일리포지토리에서 조건에 일치하는 파일 목록을 페이징 조회한다.
	 * @param vo
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO<SysFileVO>
	 */
	public abstract ProcessResultListVO<SysFileVO> listPageing(SysFileVO vo,
			int pageIndex, int listScale, int pageScale) throws Exception;

	/**
	 * 파일리포지토리에서 조건에 일치하는 파일 목록을 페이징 조회한다.
	 * @param vo
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO<SysFileVO>
	 */
	public abstract ProcessResultListVO<SysFileVO> listPageing(SysFileVO vo,
			int pageIndex, int listScale) throws Exception;

	/**
	 * 파일리포지토리에서 조건에 일치하는 파일 목록을 페이징 조회한다.
	 * @param vo
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO<SysFileVO>
	 */
	public abstract ProcessResultListVO<SysFileVO> listPageing(SysFileVO vo,
			int pageIndex) throws Exception;

	/**
	 * 파일리스트를 받아서 해당 파일 타입만 추출해서 리스트로 반환한다.
	 * DB에서 일괄 조회한 리스트에서 원하는 타입만 추출해낼때 사용한다.
	 * @param listFile 파일의 전체 목록
	 * @param fileType 파일 타입 속성
	 * @return 파일 타입이 일치하는 파일 리스트
	 */
	public abstract List<SysFileVO> pullFileByType(List<SysFileVO> listFile,
			String fileType);

}