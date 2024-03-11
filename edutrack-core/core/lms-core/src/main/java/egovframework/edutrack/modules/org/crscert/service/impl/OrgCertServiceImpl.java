package egovframework.edutrack.modules.org.crscert.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.modules.org.crscert.service.OrgCertService;
import egovframework.edutrack.modules.org.crscert.service.OrgCertVO;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 *  <b>기관 - 기관 과정 수료증 관리</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("orgCertService")
public class OrgCertServiceImpl 
	extends EgovAbstractServiceImpl implements OrgCertService {

	/** 수료증 배경 이미지 */
	private final class CertBackFileHandler
		implements FileHandler<OrgCertVO> {
	
		@Override
		public String getPK(OrgCertVO vo) {
			return vo.getOrgCd().toString();
		}
	
		@Override
		public String getRepoCd() {
			return "CERTIMG";
		}
	
		@Override
		public List<SysFileVO> getFiles(OrgCertVO vo) {
			List<SysFileVO> fileList = new ArrayList<SysFileVO>();
			fileList.addAll(vo.getAttachFiles());
			return fileList;
		}
	
		@Override
		public OrgCertVO setFiles(OrgCertVO vo, FileListVO fileListVO) {
			vo.setAttachFiles(fileListVO.getFiles("image"));
			return vo;
		}
	}
	
	/** 수료증 직인 이미지 */
	private final class CertStampFileHandler
		implements FileHandler<OrgCertVO> {

		@Override
		public String getPK(OrgCertVO vo) {
			return vo.getOrgCd().toString();
		}
	
		@Override
		public String getRepoCd() {
			return "CERTSTAMP";
		}
	
		@Override
		public List<SysFileVO> getFiles(OrgCertVO vo) {
			List<SysFileVO> fileList = new ArrayList<SysFileVO>();
			fileList.addAll(vo.getAttachFiles2());
			return fileList;
		}
	
		@Override
		public OrgCertVO setFiles(OrgCertVO vo, FileListVO fileListVO) {
			vo.setAttachFiles2(fileListVO.getFiles("image"));
			return vo;
		}
	}
	
	@Resource(name="sysFileService")
	private SysFileService 	sysFileService;
	
	
	/** Mapper */
	@Resource(name="orgCertMapper")
	private OrgCertMapper 		orgCertMapper;
    
	/**
	 * 수료증 상세 정보를 조회한다.
	 * @param OrgCertVO
	 * @return OrgCertVO
	 * @throws Exception
	 */
	@Override
	public OrgCertVO view(OrgCertVO vo) throws Exception {
		try {
			vo = orgCertMapper.select(vo);
			if(vo != null) {	
				vo = sysFileService.getFile(vo, new CertBackFileHandler());
				vo = sysFileService.getFile(vo, new CertStampFileHandler());
			}
		} catch(Exception e) {
            e.printStackTrace();
        }
		return vo;
	}
	
	/**
	 * 수료증 정보를 등록한다.
	 * @param OrgCertVO
	 * @return String
	 * @throws Exception
	 */
	@Override
	public int add(OrgCertVO vo) throws Exception {
		sysFileService.bindFile(vo, new CertBackFileHandler());
		sysFileService.bindFile(vo, new CertStampFileHandler());
		return orgCertMapper.insert(vo);
	}	
	
	/**
	 * 수료증 정보를 수정한다.
	 * @param OrgCertVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int edit(OrgCertVO vo) throws Exception {
		sysFileService.bindFile(vo, new CertBackFileHandler());
		sysFileService.bindFile(vo, new CertStampFileHandler());
		return orgCertMapper.update(vo);
	}
	
	/**
	 * 수료증 정보를 삭제 한다.
	 * @param OrgCertVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int remove(OrgCertVO vo) throws Exception {
		sysFileService.removeFile(vo, new CertBackFileHandler());
		sysFileService.removeFile(vo, new CertStampFileHandler());
		return orgCertMapper.delete(vo);
	}

}
