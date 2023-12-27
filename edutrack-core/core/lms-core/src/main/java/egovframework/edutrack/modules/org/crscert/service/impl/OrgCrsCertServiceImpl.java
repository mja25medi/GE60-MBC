package egovframework.edutrack.modules.org.crscert.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.org.crscert.service.OrgCrsCertService;
import egovframework.edutrack.modules.org.crscert.service.OrgCrsCertVO;
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
@Service("orgCrsCertService")
public class OrgCrsCertServiceImpl 
	extends EgovAbstractServiceImpl implements OrgCrsCertService {

	private final class NestedFileHandler
		implements FileHandler<OrgCrsCertVO> {
	
		@Override
		public String getPK(OrgCrsCertVO dto) {
			return dto.getOrgCd().toString();
		}
	
		@Override
		public String getRepoCd() {
			return "CERTIMG";
		}
	
		@Override
		public List<SysFileVO> getFiles(OrgCrsCertVO dto) {
			List<SysFileVO> fileList = new ArrayList<SysFileVO>();
	
			// 단일 항목도 저장.
			if(ValidationUtils.isNotZeroNull(dto.getImgFileVO().getFileSn()))
				fileList.add(dto.getImgFileVO());
			return fileList;
		}
	
		@Override
		public OrgCrsCertVO setFiles(OrgCrsCertVO dto, FileListVO fileListVO) {
			dto.setImgFileVO(fileListVO.getFile("image"));
			return dto;
		}
	}
	
	@Resource(name="sysFileService")
	private SysFileService 	sysFileService;
	
	/** dao */
/*    @Resource(name="orgCrsCertDAO")
    private OrgCrsCertMapper 	orgCrsCertMapper;*/
	
	/** Mapper */
	@Resource(name="orgCrsCertMapper")
	private OrgCrsCertMapper 		orgCrsCertMapper;
    
	/**
	 * 기관 도메인 상세 정보를 조회한다.
	 * @param OrgCrsCertVO
	 * @return OrgCrsCertVO
	 * @throws Exception
	 */
	@Override
	public OrgCrsCertVO view(OrgCrsCertVO vo) throws Exception {
		vo = orgCrsCertMapper.select(vo);
		vo = sysFileService.getFile(vo, new NestedFileHandler());
		return vo;
	}
	
	/**
	 * 기관 도메인 정보를 등록한다.
	 * @param OrgCrsCertVO
	 * @return String
	 * @throws Exception
	 */
	@Override
	public int add(OrgCrsCertVO vo) throws Exception {
		sysFileService.bindFile(vo, new NestedFileHandler());
		return orgCrsCertMapper.insert(vo);
	}	
	
	/**
	 * 기관 도메인 정보를 수정한다.
	 * @param OrgCrsCertVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int edit(OrgCrsCertVO vo) throws Exception {
		sysFileService.bindFile(vo, new NestedFileHandler());
		return orgCrsCertMapper.update(vo);
	}
	
	/**
	 * 기관 도메인 정보를 삭제 한다.
	 * @param OrgCrsCertVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int remove(OrgCrsCertVO vo) throws Exception {
		sysFileService.removeFile(vo, new NestedFileHandler());
		return orgCrsCertMapper.delete(vo);
	}
}
