package egovframework.edutrack.modules.system.file.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.FileUtil;
import egovframework.edutrack.comm.util.web.ImageUtil;
import egovframework.edutrack.comm.util.web.RandomStringUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.comm.util.web.ZipUtil;
import egovframework.edutrack.modules.system.file.service.SysFileRepoVO;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *  <b>시스템 - 시스템 파일 정보 관리</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("sysFileService")
public class SysFileServiceImpl 
	extends EgovAbstractServiceImpl implements SysFileService {
	
	protected final Log log = LogFactory.getLog(getClass());
	
    @Resource(name="sysFileMapper")
    private SysFileMapper 			sysFileMapper;
    
    @Resource(name="sysFileRepoMapper")
    private SysFileRepoMapper 		sysFileRepoMapper;
    
	/**
	 * 멀티파트 파일 업로드 처리 서비스
	 * @param multipartFile 첨부 파일 정보
	 * @param repository 파일이 저장될 repository명
	 * @param fileType 파일 타입 정보
	 * @return
	 * @throws Throwable
	 */
	@Override
	public SysFileVO upload(MultipartFile multipartFile, String repository, String organization, 
			String fileType, String regNo) throws Exception {

		String fileName = multipartFile.getOriginalFilename();
		Long fileSize = multipartFile.getSize();

		SysFileVO sfvo = this.addFile(fileName, fileSize, repository, organization, fileType, regNo);

		// ex ) /FILE_STORAGE/repogitory/yyyymm/dd
		//2016-12-16 arothy
		String saveFullPath = Constants.FILE_STORAGE_PATH;
		if("contents".equals(fileType)){
			saveFullPath = Constants.CONTENTS_STORAGE_PATH;
		}
		
		String name =sfvo.getFileSaveNm();
		if(name != null && !"".equals(name)) {
			name = name.replaceAll("/", "");
			name = name.replaceAll("\\\\", ""); 
			name = name.replaceAll("[.]{2}", "");    
			name = name.replaceAll("&", "");    
			name = name.replaceAll("%", "");     
		} 
		
		if(ValidationUtils.isNotEmpty(organization)) saveFullPath += File.separator + organization;
		saveFullPath += sfvo.getSaveDirectoryPath();//getRepoPath() + SysFileVO.getFilePath();

		// DB에 정상적으로 저장이 됐으면 파일을 실제 저장소로 이전.
		if(sfvo.getFileSn() != null) {
			preparePath(saveFullPath);	// 경로가 없으면 만든다.
			File saveFile = new File(saveFullPath + File.separator + name);
			multipartFile.transferTo(saveFile); // 파일저장소 경로로 복사
			SysFileVOUtil.thumbNameGenerate(sfvo);
			
			/* 2016-12-16 arothy upzip처리*/
			if("contents".equals(fileType) && fileName.lastIndexOf(".zip") >= fileName.length()-4 ){
				
				FileUtil.unZipFile(saveFullPath + File.separator + name, saveFullPath + File.separator + name.replaceAll(".zip", "") + "/ROOT", "EUC-KR");
//				FileUtil.unZipFile(saveFullPath + File.separator + sfvo.getFileSaveNm(), saveFullPath + File.separator + sfvo.getFileSaveNm().replaceAll(".zip", "") + "/ROOT", "EUC-KR");
			}
			
		}

		return sfvo;
	}


	/**
	 * 인자로 받은 SysFileVO를 복제해서 새로운 SysFileVO를 반환한다.<br>
	 * 물리적인 파일을 포함한다.
	 * @param vo 복사 대상 SysFileVO
	 * @return 복사된 결과 SysFileVO
	 * @throws IOException
	 */
	@Override
	public SysFileVO copyFile(SysFileVO vo) throws Exception {

		if(vo == null || vo.getFileSn() == 0) return vo;

		// 파일 정보를 복사하고 그 결과를 저장
		SysFileVO tSysFileVO = this.addFile(vo.getFileNm(), vo.getFileSize(), vo.getRepoCd(), vo.getOrgCd(), vo.getFileType(), vo.getRegNo());

		//2016-12-16 arothy
		String storagePath;
		storagePath = Constants.FILE_STORAGE_PATH;
		if("contents".equals(vo.getFileType())){
			storagePath = Constants.FILE_STORAGE_PATH;
		}
		
		// ex ) /FILE_STORAGE/repogitory/yyyymm/dd
		String sourceDirectory = storagePath + vo.getSaveDirectoryPath();
		String targetDirectory = storagePath + tSysFileVO.getSaveDirectoryPath();

		// DB에 정상적으로 저장이 됐으면 파일을 실제로 복사
		if(tSysFileVO.getFileSn() != null) {
			preparePath(targetDirectory);	// 경로가 없으면 만든다.

			File sFile = new File(sourceDirectory + File.separator + vo.getFileSaveNm());
			File tFile = new File(targetDirectory + File.separator + tSysFileVO.getFileSaveNm());

			log.info(sFile + " copy to -> " +tFile);
			// 파일 복사
			FileUtils.copyFile(sFile, tFile);

			// 대상 파일이 이미지일 경우 썸네일을 만든다.
			if(tSysFileVO.getMimeType().indexOf("image/") > -1) {
				ImageUtil.resize(tFile, SysFileVOUtil.getThumbnailName(tFile), 100, ImageUtil.RATIO);
			}
		}

		return tSysFileVO;
	}
	
	/**
	 * 파일 업로드 처리 서비스. type은 기본값 'file'로 설정.
	 * @param multipartFile 첨부파일 정보
	 * @param repository 저장 영역 정보
	 * @return
	 * @throws Throwable
	 */
	@Override
	public SysFileVO upload(MultipartFile multipartFile, String repository, 
			String organization, String regNo) throws Throwable {
		return upload(multipartFile, repository, organization, "file", regNo);
	}

	/**
	 * 파일 업로드 처리 서비스. Repository 영역을 기본값인 'general'로 설정.
	 * @param multipartFile
	 * @return
	 * @throws Throwable
	 */
	@Override
	public SysFileVO upload(MultipartFile multipartFile, String regNo) throws Throwable {
		return upload(multipartFile, "GENERAL", "", regNo);
	}

	/**
	 * 파일 정보 조회. 조회에 대한 정보를 업데이트 할지 결정할 수 있다.
	 * @param vo
	 * @param updateEnabled
	 * @return
	 */
	@Override
	public SysFileVO getFile(SysFileVO vo, boolean updateEnabled) throws Exception{
		Assert.notNull(vo, "파일 정보가 없습니다.");
		Assert.notNull(vo.getFileSn(), "파일의 주키 정보는 필수값입니다.");
		SysFileVO resultFile = sysFileMapper.select(vo);
		if(resultFile != null && updateEnabled)
			sysFileMapper.updateFileLastInqDttm(vo);	// 파일 접근 정보 갱신
		return resultFile;
	}

	/**
	 * 파일정보 조회. 카운트 정보 업데이트 여부를 true로 설정해서 조회한다.
	 * @param vo
	 */
	@Override
	public SysFileVO getFile(SysFileVO vo) throws Exception {
		return getFile(vo, true);
	}


	/**
	 * 파일 삭제.<p>
	 * 파일정보를 DB에서 먼저 삭제한 뒤. 실제 파일 삭제를 시도한다.
	 * 삭제중 오류가 발생하면 DB정보도 롤백된다.
	 */
	@Override
	public void removeFile(SysFileVO vo) throws Exception {
		SysFileVO fileEntity = sysFileMapper.select(vo);

		SysFileRepoVO sfrvo = new SysFileRepoVO();  
		sfrvo.setRepoCd(fileEntity.getRepoCd());
		sysFileMapper.delete(vo);
		
		//2016-12-16 arothy
		String filePath = Constants.FILE_STORAGE_PATH;
		if("contents".equals(vo.getFileType())){
			filePath = Constants.CONTENTS_STORAGE_PATH;
		}
		
		if(ValidationUtils.isNotEmpty(fileEntity.getOrgCd())) filePath += File.separator + fileEntity.getOrgCd();
		filePath += fileEntity.getSaveFilePath();

		File file = new File(filePath);
		File thumbFile = SysFileVOUtil.getThumbnailName(file);

		deleteAbsoluteFileExecute(file);
		deleteAbsoluteFileExecute(thumbFile);
		
	}

	/**
	 * 실제 물리 파일의 삭제가 가능할 경우 삭제한다.
	 * @param file
	 */
	private void deleteAbsoluteFileExecute(File file) {
		if(file.exists() && file.canWrite()) {
			log.debug("파일 삭제 > " + file.getAbsolutePath());
			file.delete();	// 파일의 삭제가 가능하다면 삭제.
		}
	}

	/**
	 * 파일 삭제.<p>
	 * 파일정보를 DB에서 먼저 삭제한 뒤. 실제 파일 삭제를 시도한다.
	 * 삭제중 오류가 발생하면 DB정보도 롤백된다.
	 */
	@Override
	public void removeFile(Integer fileSn) throws Exception {		
		this.removeFile(new SysFileVO(fileSn));
	}

	/**
	 * 대상 경로가 없을 경우 생성.
	 * @param path
	 */
	private void preparePath(String path) {
		File savePath = new File(path);
		if (!savePath.exists()) {
			if(!savePath.mkdirs()) {
				log.debug("It did not create the directory.! "+savePath.toString());
			}		
		}
	}

	/**
	 * 파일에 대한 정보를 받아서 저장을 위한 랜덤 파일이름을 지정하고 DB에 저장한다.
	 * @param fileName 원본파일 이름
	 * @param fileSize 원본파일 크기
	 * @param repository 파일이 저장될 저장소코드
	 * @param fileType 파일의 유형
	 * @param regNo 파일 업로드 사용번호
	 * @return
	 */
	private SysFileVO addFile(String fileName, Long fileSize, String repository, String organization, String fileType, String regNo) throws Exception{
		String currentTime = DateTimeUtil.getCurrentString();

		// 저장경로 패턴 : /yyyyMM/dd
		String saveFilePath = File.separator + currentTime.substring(0, 6) + File.separator + currentTime.substring(6, 8);

		String repositoryPath = "";
		try {
			SysFileRepoVO sfrvo = new SysFileRepoVO(repository);
			sfrvo = sysFileRepoMapper.select(sfrvo);
			if(ValidationUtils.isEmpty(sfrvo)) {
				sfrvo = new SysFileRepoVO(repository);
			}
			repositoryPath = sfrvo.getRepoDfltPath();
		} catch (Exception ex) {
			// 유효하지 않은 저장소 코드로 인한 오류시 GENERAL로 지정해서 다시 조회
			SysFileRepoVO sfrvo = new SysFileRepoVO("GENERAL");
			sfrvo = sysFileRepoMapper.select(sfrvo);
			if(ValidationUtils.isEmpty(sfrvo)) {
				sfrvo = new SysFileRepoVO();
			}		
			repositoryPath = sfrvo.getRepoDfltPath();
		}
		
		if(fileName != null && !"".equals(fileName)) {
			fileName = fileName.replaceAll("/", "");
			fileName = fileName.replaceAll("\\\\", ""); 
			fileName = fileName.replaceAll("[.]{2}", "");    
			fileName = fileName.replaceAll("&", "");    
			fileName = fileName.replaceAll("%", "");     
		} 

		String fileExt  = StringUtil.getExtNoneDot(fileName).toLowerCase();
		String saveFileName = RandomStringUtil.getRandomMD5() + "." + fileExt;

		int fileSn = sysFileMapper.selectKey(); //-- 파일키를 생성한다.
		
		SysFileVO sfvo = new SysFileVO();
		sfvo.setFileSn(fileSn);
		sfvo.setFileNm(fileName);
		sfvo.setFileSaveNm(saveFileName);
		sfvo.setFilePath(saveFilePath);
		sfvo.setFileExt(fileExt);
		sfvo.setFileSize(fileSize);
		sfvo.setFileType(fileType);
		sfvo.setRepoCd(repository);
		sfvo.setOrgCd(organization);
		sfvo.setRepoPath(repositoryPath);
		sfvo.setRegNo(regNo);
		
		// 파일이름으로 파일의 mime을 가져온다. (파일을 뽑은 뒤에는 파일에서 추출할 수 있는건지 확인.)
		// 2016.05.20 jdk1.7에서의 오류 확인 모든 리튼을 application/octet-stream을 반환하고 있다.
		sfvo.setMimeType(new MimetypesFileTypeMap().getContentType(sfvo.getFileNm()));
		
		// 파일의 MIME타입을 강제로 설정.
		if(sfvo.getFileExt().toLowerCase().equals("png")) {
			sfvo.setMimeType("image/png");
		} else if(sfvo.getFileExt().toLowerCase().equals("jpg") || sfvo.getFileExt().toLowerCase().equals("jpeg") || sfvo.getFileExt().toLowerCase().equals("jpe")) {
			sfvo.setMimeType("image/jpeg");
		} else if(sfvo.getFileExt().toLowerCase().equals("gif")) {
			sfvo.setMimeType("image/gif");
		} else if(sfvo.getFileExt().toLowerCase().equals("tiff") || sfvo.getFileExt().toLowerCase().equals("tif")) {
			sfvo.setMimeType("image/tiff");
		} else if(sfvo.getFileExt().toLowerCase().equals("bmp")) {
			sfvo.setMimeType("image/bmp");
		}else if(sfvo.getFileExt().toLowerCase().equals("pdf")) {
			sfvo.setMimeType("application/pdf");
		}
		sfvo.setFileSn(sysFileMapper.selectKey());
		sysFileMapper.insert(sfvo);	// 파일을 DB에 저장
		return sfvo;
	}

	/**
	 * 파일리스트의 각 파일과 연결되는 본래 데이터의 연결관계를 확정해서 저장한다. (UPDATE)
	 * @param vo 파일과 연결된 원 데이터
	 * @param selector 원 데이터에서 직렬화된 주키를 추출하는 구현체
	 * @param <D> 파일과 연결된 본래 데이터 타입
	 * @return
	 */
	@Override
	public <D> void bindFile(D vo, FileHandler<D> selector) throws Exception {
		List<SysFileVO> fileList = selector.getFiles(vo);
		for (SysFileVO sfvo : fileList) {
			sfvo = getFile(sfvo);
			sfvo.setFileBindDataSn(selector.getPK(vo));
			sysFileMapper.updateFileBindData(sfvo);
		}
	}

	/**
	 * 파일리스트의 각 파일과 연결되는 본래 데이터의 연결관계를 제거한다. (UPDATE)
	 * @param <D> 파일과 연결된 본래 데이터 타입
	 * @param vo 파일과 연결된 원 데이터
	 * @param selector 원 데이터에서 직렬화된 주키를 추출하는 구현체
	 */
	@Override
	public <D> void unbindFile(D vo, FileHandler<D> selector) throws Exception {
		// 연결 제거용 파라매터 객체 만들기
		SysFileVO sfvo = new SysFileVO();
		sfvo.setRepoCd(selector.getRepoCd());
		sfvo.setFileBindDataSn(selector.getPK(vo));
		sysFileMapper.deleteFileBindData(sfvo);
	}

	/**
	 * 컨텐츠 수정시 사용. 기존 파일의 연결정보를 제거하고 연결관계를 다시 저장한다. (UPDATE)<br>
	 * 내부적으로 연결된 파일 정보 컬럼을 일괄 제거한 뒤 다시 저장한다.
	 * @param vo 파일과 연결된 원 데이터
	 * @param handler 원 데이터에서 직렬화된 주키를 추출하는 구현체
	 * @param <D> 파일과 연결된 본래 데이터 타입
	 * @return
	 */
	@Override
	public <D> void bindFileUpdate(D vo, FileHandler<D> handler) throws Exception {
		this.unbindFile(vo, handler);
		// 파라매터로 넘어온 값을 다시 저장한다.
		this.bindFile(vo, handler);
	}

	/**
	 * 파일저장소코드와 연결데이터의 직렬화된 주키정보를 모아서 해당 파일 목록을 반환한다.
	 * @param <D> 파일과 연결된 본래 데이터 타입
	 * @param vo 파일과 연결된 원 데이터
	 * @param handler 원 데이터에서 직렬화된 주키를 추출하는 구현체
	 * @return 파일타입으로 구분된 FileListVO
	 */
	@Override
	public <D> D getFile(D vo, FileHandler<D> handler) throws Exception {
		SysFileVO sfvo = new SysFileVO();
		sfvo.setRepoCd(handler.getRepoCd());
		sfvo.setFileBindDataSn(handler.getPK(vo));

		List<SysFileVO> listFile = this.list(sfvo).getReturnList();
		FileListVO fileListVO = new FileListVO(listFile);

		vo = handler.setFiles(vo, fileListVO);
		return vo;
	}

	/**
	 * 파일저장소코드와 연결데이터의 직렬화된 주키정보의 목록에 해당 파일 정보를 바인딩 한다.
	 * 쿼리가 List의 갯수만큼 수행되므로 성능 저하를 감안하고 사용해야 한다.
	 * @param <D> 파일과 연결된 본래 데이터 타입
	 * @param voList 파일과 연결된 원 데이터의 목록
	 * @param handler 원 데이터에서 직렬화된 주키를 추출하는 구현체
	 * @return 파일타입으로 구분된 FileListVO
	 */
	@Override
	public <D> List<D> getFile(List<D> voList, FileHandler<D> handler) throws Exception{
		for (D vo : voList) {
			vo = this.getFile(vo, handler);
		}
		return voList;
	}

	/**
	 * 파일과 연결된 본 데이터의 정보로 파일을 일괄삭제한다.
	 * @param <D>
	 * @param vo
	 * @param selector
	 */
	@Override
	public <D> void removeFile(D vo, FileHandler<D> selector) throws Exception {
		vo = this.getFile(vo, selector);					// 파일목록 다시 연결.
		List<SysFileVO> fileList = selector.getFiles(vo);	// 파일 목록 추출

		for (SysFileVO SysFileVO : fileList) {
			try {
				this.removeFile(SysFileVO);
			} catch (Exception ignore) { }	// 삭제중 오류가 발생해도 계속 삭제..
		}
	}

	/**
	 * 파일리포지토리에서 조건에 일치하는 파일 목록을 일괄 조회한다.
	 * @param vo : repoCd, fileBindDataSn 항목으로 조회할 경우 해당 자료에 연결된 파일 목록을 반환한다.
	 * @return
	 */
	@Override
	public ProcessResultListVO<SysFileVO> list(SysFileVO vo) throws Exception {
		//-- 파일 리파지토리의 정보를 검색
		SysFileRepoVO sfrvo = new SysFileRepoVO();
		sfrvo.setRepoCd(vo.getRepoCd());
		SysFileRepoVO selSfrvo = sysFileRepoMapper.select(sfrvo);
		if(ValidationUtils.isNotEmpty(selSfrvo)) {
			sfrvo = selSfrvo;
		}
		vo.setParTableNm(sfrvo.getParTableNm());
		vo.setParFieldNm(sfrvo.getParFieldNm());
		
		ProcessResultListVO<SysFileVO> resultList = new ProcessResultListVO<SysFileVO>();
		List<SysFileVO> fileList = sysFileMapper.list(vo);
		resultList.setResult(1);
		resultList.setReturnList(fileList);
		return resultList;
	}

	/**
	 * 파일리포지토리에서 조건에 일치하는 파일 목록을 페이징 조회한다.
	 * @param vo
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO<SysFileVO>
	 */
	@Override
	public ProcessResultListVO<SysFileVO> listPageing(SysFileVO vo, int pageIndex, int listScale, int pageScale) throws Exception {
		//-- 파일 리파지토리의 정보를 검색
		SysFileRepoVO sfrvo = new SysFileRepoVO();
		sfrvo.setRepoCd(vo.getRepoCd());
		
		SysFileRepoVO selSfrvo = new SysFileRepoVO();
		selSfrvo = sysFileRepoMapper.select(sfrvo);
		if(ValidationUtils.isNotEmpty(sfrvo)) {
			sfrvo = selSfrvo;
		}		
		vo.setParTableNm(sfrvo.getParTableNm());
		vo.setParFieldNm(sfrvo.getParFieldNm());
		
		ProcessResultListVO<SysFileVO> resultList = new ProcessResultListVO<SysFileVO>();
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pageIndex);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		
		// 전체 목록 수
		int totalCount = sysFileMapper.count(vo);
		paginationInfo.setTotalRecordCount(totalCount);
		
		List<SysFileVO> fileList =  sysFileMapper.listPageing(vo);
		resultList.setResult(1);
		resultList.setReturnList(fileList);
		resultList.setPageInfo(paginationInfo);		
		return resultList;
	}

	/**
	 * 파일리포지토리에서 조건에 일치하는 파일 목록을 페이징 조회한다.
	 * @param vo
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO<SysFileVO>
	 */
	@Override
	public ProcessResultListVO<SysFileVO> listPageing(SysFileVO vo, int pageIndex, int listScale) throws Exception {
		return this.listPageing(vo, pageIndex, listScale, Constants.LIST_PAGE_SCALE);
	}
	
	/**
	 * 파일리포지토리에서 조건에 일치하는 파일 목록을 페이징 조회한다.
	 * @param vo
	 * @param pageIndex
	 * @param listScale
	 * @return ProcessResultListVO<SysFileVO>
	 */
	@Override
	public ProcessResultListVO<SysFileVO> listPageing(SysFileVO vo, int pageIndex) throws Exception {
		return this.listPageing(vo, pageIndex, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}	

	/**
	 * 파일리스트를 받아서 해당 파일 타입만 추출해서 리스트로 반환한다.
	 * DB에서 일괄 조회한 리스트에서 원하는 타입만 추출해낼때 사용한다.
	 * @param listFile 파일의 전체 목록
	 * @param fileType 파일 타입 속성
	 * @return 파일 타입이 일치하는 파일 리스트
	 */
	@Override
	public List<SysFileVO> pullFileByType(List<SysFileVO> listFile, String fileType) {
		List<SysFileVO> listFiles  = new ArrayList<SysFileVO>();
		for (SysFileVO sfvo : listFiles) {
			if(sfvo.getFileType().equals(fileType)) {
				listFiles.add(sfvo);
			}
		}
		return listFiles;
	}

}
