package egovframework.edutrack.modules.org.creaplc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.log.message.service.LogMsgLogService;
import egovframework.edutrack.modules.log.message.service.LogMsgLogVO;
import egovframework.edutrack.modules.log.message.service.LogMsgTransLogVO;
import egovframework.edutrack.modules.org.creaplc.service.OrgCreAplcInfoService;
import egovframework.edutrack.modules.org.creaplc.service.OrgCreAplcInfoVO;
import egovframework.edutrack.modules.org.emailtpl.service.OrgEmailTplService;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoService;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * @author hy
 *
 */
@Service("orgCreAplcInfoService")
public class OrgCreAplcInfoServiceImpl 
	extends EgovAbstractServiceImpl implements OrgCreAplcInfoService {
	
	/** dao */
/*	@Resource(name="orgCreAplcInfoDAO")
	private OrgCreAplcInfoMapper orgCreAplcInfoMapper;*/
	/** Mapper */
	@Resource(name="orgCreAplcInfoMapper")
	private OrgCreAplcInfoMapper 		orgCreAplcInfoMapper;
	
	
	@Resource(name="logMsgLogService")
	private LogMsgLogService logMsgLogService;
	
	@Resource(name="orgEmailTplService")
	private OrgEmailTplService orgEmailTplService ;
	
	@Resource(name="orgOrgInfoService")
	private OrgOrgInfoService orgOrgInfoService ;

	@Override
	public ProcessResultListVO<OrgCreAplcInfoVO> list(OrgCreAplcInfoVO vo) throws Exception {
		ProcessResultListVO<OrgCreAplcInfoVO> resultList = new ProcessResultListVO<OrgCreAplcInfoVO>(); 
		try {
			List<OrgCreAplcInfoVO> list =  orgCreAplcInfoMapper.list(vo);
			resultList.setResult(1);
			resultList.setReturnList(list);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
	@Override
	public ProcessResultListVO<OrgCreAplcInfoVO> listPageing(OrgCreAplcInfoVO vo, 
			int pageIndex, int listScale, int pageScale) throws Exception {
		ProcessResultListVO<OrgCreAplcInfoVO> resultList = new ProcessResultListVO<OrgCreAplcInfoVO>(); 
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(pageIndex);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = orgCreAplcInfoMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<OrgCreAplcInfoVO> CreAplcInfoList =  orgCreAplcInfoMapper.listPageing(vo);
			resultList.setResult(1);
			resultList.setReturnList(CreAplcInfoList);
			resultList.setPageInfo(paginationInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
	@Override
	public ProcessResultListVO<OrgCreAplcInfoVO> listPageing(OrgCreAplcInfoVO vo, 
			int pageIndex, int listScale) throws Exception {
		return this.listPageing(vo, pageIndex, listScale, Constants.LIST_PAGE_SCALE);
	}
	
	@Override
	public ProcessResultListVO<OrgCreAplcInfoVO> listPageing(OrgCreAplcInfoVO vo, 
			int pageIndex) throws Exception {
		return this.listPageing(vo, pageIndex, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}	
	
	@Override
	public OrgCreAplcInfoVO view(OrgCreAplcInfoVO vo) throws Exception {
		return orgCreAplcInfoMapper.select(vo);
	}
	
	@Override
	public int add(OrgCreAplcInfoVO vo) throws Exception {
		return orgCreAplcInfoMapper.insert(vo);
	}
	
	/*
	 * 공공활용 개설신청 + 인증메일 전송
	 * */
	@Override
	public int add(OrgCreAplcInfoVO vo, LogMsgLogVO msgVo) throws Exception {
		if(ValidationUtils.isEmpty(vo.getCreAplcSn())) {
			vo.setCreAplcSn(orgCreAplcInfoMapper.selectKey());
		}
		
		int result = orgCreAplcInfoMapper.insert(vo);
		
		String creAplcSn = vo.getCreAplcSn().toString();
		String certKey = vo.getEmailCertKey();
		
		String menuCd = msgVo.getOrgCd();
		String orgCd = msgVo.getOrgCd();
		
		//발신자 정보를 조회(발송기관)
		OrgOrgInfoVO orgInfoVO = new OrgOrgInfoVO();
		orgInfoVO.setOrgCd(orgCd);
		orgInfoVO = orgOrgInfoService.view(orgInfoVO);
		
		//transVo를 생성
		LogMsgTransLogVO trans = new LogMsgTransLogVO();

		trans.setRecvAddr(vo.getEmailAddr());
		trans.setRecvNm(vo.getRegNm());
		trans.setRecvYn("Y"); //안받으면 못찾으니까 강제적으로 Y

		//메세지를 생성한다.
		LogMsgLogVO message = trans.getMessage();
		message.setSendMenuCd(menuCd);
		message.setMsgDivCd("EMAIL");
		message.setSendAddr(orgInfoVO.getEmailAddr());
		message.setSendNm(orgInfoVO.getEmailNm());
		message.setRsrvSendDttm("00000000000000");//예약발신일시

		message.addSubTrans(trans);
		
		// 메일 데코레이션
		Map<String, Object> argu = new HashMap<String, Object>();
		argu.put("Hostdomain",Constants.PRODUCT_HOST_URL);
		argu.put("CreAplcSn", creAplcSn);
		argu.put("CertKey", certKey);
		argu.put("SendDate", DateTimeUtil.getCurrentString("yy.MM.dd"));
		orgEmailTplService.decorator(orgCd, "TPL000007", argu, message);
		
		
//		logMsgLogService.addMessageWithSend(msgVo);		//실서버 메일 등록 및 전송
		logMsgLogService.addMessage(message);				//TODO local 메일 등록 테스트 @arothy
		
		return result;
	}
	
	@Override
	public int edit(OrgCreAplcInfoVO vo) throws Exception {
		return orgCreAplcInfoMapper.update(vo);
	}
	
	@Override
	public int remove(OrgCreAplcInfoVO vo) throws Exception {
		return orgCreAplcInfoMapper.delete(vo);
	}

	@Override
	public int editEmailCertYn(OrgCreAplcInfoVO vo) throws Exception {
		return orgCreAplcInfoMapper.updateEmailCertYn(vo);
	}
}
