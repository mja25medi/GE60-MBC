package egovframework.edutrack.comm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.service.OrgCodeMemService;
import egovframework.edutrack.modules.org.code.service.OrgCodeService;
import egovframework.edutrack.modules.org.code.service.OrgCodeVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 *  <b>공통 - 공통 기관 코드 메모리 서비스</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("orgCodeMemService")
public class OrgCodeMemServiceImpl extends EgovAbstractServiceImpl implements OrgCodeMemService {

	@Resource(name="orgCodeService")
	private OrgCodeService 		orgCodeService;
	
	/**
	 * 기관코드 리스트를 반환한다.
	 *
	 * @param codeCtgrCd
	 * @return
	 */
	@Override
	public synchronized List<OrgCodeVO> getOrgCodeList(String orgCd, String codeCtgrCd) throws Exception {
		return orgCodeService.listCode(orgCd,codeCtgrCd).getReturnList();
	}
}
