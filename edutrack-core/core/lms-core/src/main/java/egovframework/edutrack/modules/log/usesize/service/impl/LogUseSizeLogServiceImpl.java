package egovframework.edutrack.modules.log.usesize.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.util.web.FileUtil;
import egovframework.edutrack.modules.log.usesize.service.LogUseSizeLogVO;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoVO;
import egovframework.edutrack.modules.org.info.service.impl.OrgOrgInfoMapper;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 *  <b>로그 - 사용량 로그</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("logUseSizeLogService")
public class LogUseSizeLogServiceImpl 
	extends EgovAbstractServiceImpl {
	
	/** Mapper */
    @Resource(name="logUseSizeLogMapper")
    private LogUseSizeLogMapper 		logUseSizeLogMapper;

    @Resource(name="orgOrgInfoMapper")
    private OrgOrgInfoMapper 			orgOrgInfoMapper;
    
	/**
	 * 사용량 로그 정보를 조회한다.
	 * @param LogUseSizeLogVO
	 * @return LogUseSizeLogVO
	 * @throws Exception
	 */
	public LogUseSizeLogVO view(LogUseSizeLogVO vo) throws Exception {
		return logUseSizeLogMapper.select(vo);
	}
	
	/**
	 * 사용량 로그 정보를 등록한다.
	 * @param LogUseSizeLogVO
	 * @return String
	 * @throws Exception
	 */
	public int add(LogUseSizeLogVO vo) throws Exception {
		
		//-- 모든 사이트의 사용량을 한꺼번에 구해서 저장.
		// 사용중인 사이트 정보를 가져온다.
		OrgOrgInfoVO ooivo = new OrgOrgInfoVO();
		List<OrgOrgInfoVO> orgInfoList = orgOrgInfoMapper.list(ooivo);
		
		/*
		for(OrgOrgInfoVO sooivo : orgInfoList) {
			long contentsSize = 0;
			long attachSize = 0;
			//-- Contents 사용량 구하기
			// 사용중인 온라인 과목의 목록 검색.
			OnlineSubjectDTO osdto = new OnlineSubjectDTO();
			osdto.setOrgCd(sooivo.getOrgCd());
			List<OnlineSubjectDTO> subjectList = subjectOnlineMapper.list(osdto).getReturnList();
			for(OnlineSubjectDTO sosdto : subjectList) {
				String dirPath = Constants.CONTENTS_STORAGE_PATH + "/" + sosdto.getSbjCd();
				contentsSize += FileUtil.getTotalSize(dirPath);
			}

			//--OLC 콘텐츠용 개인 콘텐츠 항목 검색 (관리자)
			UserInfoDTO uidto = new UserInfoDTO();
			uidto.setOrgCd(sooivo.getOrgCd());
			uidto.setSearchAuthGrp("MANAGER");
			List<UserInfoDTO> userList1 = userInfoMapper.list(uidto).getReturnList();
			for(UserInfoDTO suidto : userList1) {
				String dirPath = Constants.CONTENTS_STORAGE_PATH + "/usercnts/" + suidto.getUserNo();
				contentsSize += FileUtil.getTotalSize(dirPath);
			}

			//--OLC 콘텐츠용 개인 콘텐츠 항목 검색 (콘텐츠 관리자)
			uidto.setSearchAuthGrp("CNTSMGR");
			List<UserInfoDTO> userList2 = userInfoMapper.list(uidto).getReturnList();
			for(UserInfoDTO suidto : userList2) {
				String dirPath = Constants.CONTENTS_STORAGE_PATH + "/usercnts/" + suidto.getUserNo();
				contentsSize += FileUtil.getTotalSize(dirPath);
			}

			//-- 첨부파일 사용량 구하기
			UseSizeLogDTO usldto = new UseSizeLogDTO();
			usldto.setOrgCd(sooivo.getOrgCd());
			usldto = useSizeLogMapper.selectAtchSize(usldto).getReturnDto();

			attachSize = usldto.getUseSize();

			UseSizeLogDTO iusldto = new UseSizeLogDTO();
			iusldto.setOrgCd(sooivo.getOrgCd());
			iusldto.setDivCd("HDD");
			iusldto.setUseSize(contentsSize + attachSize);
			useSizeLogMapper.marge(iusldto);
		}	*/	
		
		return logUseSizeLogMapper.insert(vo);
	}		
}
