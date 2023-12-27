package egovframework.edutrack.modules.etc.hrdapi.service.impl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiCreService;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiCreVO;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiRestTemplate;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiVO;
import egovframework.edutrack.modules.log.apisync.service.LogApiSyncVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("hrdApiCreService")
public class HrdApiCreServiceImpl implements HrdApiCreService {

    @Resource(name="hrdApiCreMapper")
    private HrdApiCreMapper 		hrdApiCreMapper;
    
	@Autowired
	private HrdApiRestTemplate hrdApiRestTemplate;
	
	/**
	 * api용 수업정보 조회
	 * @param HrdApiCreVO vo
	 * @return  List<EgovMap>
	 */
	@Override
	public List<EgovMap> listCreInfo(HrdApiCreVO vo) throws Exception {
		return hrdApiCreMapper.listCreInfo(vo);
	}
	
	
	
	/**
	 * api용 수업정보 조회
	 * @param HrdApiCreVO vo
	 * @return  List<EgovMap>
	 */
	@Override
	public List<EgovMap> listCreInfoApi(HrdApiCreVO vo) throws Exception {
		return hrdApiCreMapper.listCreInfoApi(vo);
	}
	


	/**
	 * api용 수업정보 조회
	 * @param HrdApiCreVO vo
	 * @return  List<EgovMap>
	 */
	@Override
	public ProcessResultListVO<EgovMap> listCreInfoPageing(HrdApiCreVO vo) throws Exception {
		ProcessResultListVO<EgovMap> resultList = new ProcessResultListVO<EgovMap>();
		try {
		/* start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(vo.getCurPage());
		paginationInfo.setRecordCountPerPage(vo.getListScale());
		paginationInfo.setPageSize(vo.getPageScale());
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		
		// 전체 목록 수
		int totalCount = hrdApiCreMapper.countCreInfo(vo);
		paginationInfo.setTotalRecordCount(totalCount);
		
		List<EgovMap> creList = hrdApiCreMapper.listCreInfoPageing(vo);
		resultList.setResult(1);
		resultList.setReturnList(creList);
		resultList.setPageInfo(paginationInfo);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
		}		
		return resultList;
	}
	
	/**
	 * 수업 정보 api call
	 * @param EgovMap
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<HrdApiVO> callCreInfoHrdApi(String jsonString, List<EgovMap> egovList, HrdApiCreVO hasvo) throws Exception {
		ProcessResultListVO<HrdApiVO> resultList = new ProcessResultListVO<HrdApiVO>();
		//필수값 
		//hasvo.setCrsYear(hasvo.getCrsYear());
		//hasvo.setCrsTerm(hasvo.getCrsTerm());
		//hasvo.setSyncGubunCd(hasvo.getSyncGubunCd());
		//hasvo.setModNo(UserBroker.getUserNo(request));
		
		//JSON 데이터 생성
		//JSONObject jsonObj = getJsonDataHrdApi(egovList);
		//요청 URL
		String apiUrl = Constants.HRD_API_DOMAIN + Constants.HRD_API_CLASS_URL;
		URI url = new URI(apiUrl);
		HrdApiVO vo = new HrdApiVO();
		vo.setApiKey(Constants.HRD_API_KEY);
		vo.setUserId(Constants.HRD_API_USER_ID);
		
		LogApiSyncVO lasvo = new LogApiSyncVO();
		lasvo.setCrsCd(hasvo.getCrsCd());
		lasvo.setSyncGubunCd(hasvo.getSyncGubunCd());
		lasvo.setSiteUrl(apiUrl);
		lasvo.setSyncSuccessCnt(egovList.size());
		
		//POST 방식으로 스트링을 통한 JSON 전송
		ResponseEntity<HrdApiVO> responseEntity = hrdApiRestTemplate.exchange(vo,url, jsonString, HrdApiVO.class,lasvo);
		HrdApiVO responVo = responseEntity.getBody();
		if(responseEntity.getStatusCodeValue() == 200) {
			String strResult = (String) responVo.getCode();
			
			if("200".equals(strResult)) {
				hasvo.setSyncStatus("S");
			}
			
			resultList.setResult(1);
			resultList.setMessage(responVo.getMsg());
			hasvo.setSyncResultMsg(responVo.getMsg());
			
			for (int i = 0; i < egovList.size(); i++) {
				EgovMap egovMap = egovList.get(i);
				hasvo.setNum(Integer.parseInt(StringUtil.nvl(egovMap.get("num"))));
				hasvo.setCrsCd((String)egovMap.get("crsCd"));
				hasvo.setClassAgentPk((String)egovMap.get("classAgentPk"));
				hrdApiCreMapper.updateHrdApiCre(hasvo);
			}
			
		}else {
			//실패지점 색출하여 그이전까지 데이터 재전송
			String failSeq = StringUtil.nvl(responVo.getDetail().get("seq"));
			String failNum = "";
			String failPk = "";
			String failCrsCd = "";
			List<EgovMap> reSendEgovList = new ArrayList<>();
			if(!"".equals(failSeq)) {
				for (int i = 0; i < egovList.size(); i++) {
					EgovMap egovMap = egovList.get(i);
					if(!failSeq.equals(StringUtil.nvl(egovMap.get("seq")))) {
						reSendEgovList.add(egovMap);
					}
					if(failSeq.equals(StringUtil.nvl(egovMap.get("seq")))) {
						failNum = StringUtil.nvl(egovMap.get("num"));
						failPk = (String)egovMap.get("classAgentPk");
						failCrsCd = (String)egovMap.get("crsCd");
						break;
					}
				}
			}
			if(!"".equals(failPk)) {
				hasvo.setSyncStatus("F");
				hasvo.setSyncResultMsg(responVo.getMsg());
				hasvo.setNum(Integer.parseInt(failNum));
				hasvo.setClassAgentPk(failPk);
				hasvo.setCrsCd(failCrsCd);
				hrdApiCreMapper.updateHrdApiCre(hasvo);
			}
			if(reSendEgovList.size() > 0) {
				//실패지점까지 성공데이터가 있을때
				lasvo.setSyncSuccessCnt(reSendEgovList.size());
				lasvo.setSyncFailCnt(egovList.size()-reSendEgovList.size());
				lasvo.setSyncFailPk(failPk);
				
				String resendJsonString = JsonUtil.getJsonStringHrdApi(reSendEgovList);
				ResponseEntity<HrdApiVO> reSendResponseEntity = hrdApiRestTemplate.exchange(vo,url, resendJsonString,HrdApiVO.class,lasvo);
				HrdApiVO reSendresponVo = reSendResponseEntity.getBody();
				String msg = reSendresponVo.getMsg()+" 성공수 : "+reSendEgovList.size()+", 실패건 num : "+failPk;
				if(reSendResponseEntity.getStatusCodeValue() == 200) {
					if("200".equals(reSendresponVo.getCode())) {
						hasvo.setSyncStatus("S");
					}
					resultList.setResult(1);
					resultList.setMessage(msg);
					hasvo.setSyncResultMsg(msg);
					
					for (int i = 0; i < reSendEgovList.size(); i++) {
						EgovMap egovMap = reSendEgovList.get(i);
						long reNum = (long)egovMap.get("num");
						hasvo.setCrsCd((String)egovMap.get("crsCd"));
						hasvo.setClassAgentPk((String)egovMap.get("classAgentPk"));
						hasvo.setNum(Long.valueOf(reNum).intValue());
						hrdApiCreMapper.updateHrdApiCre(hasvo);
					}
				}else {
					resultList.setResult(-1);
					resultList.setMessage(msg);
				}
			}else {
				//실패지점까지 성공데이터가없을때
				resultList.setResult(-1);
				resultList.setMessage(responVo.getMsg()+" 성공수 : "+reSendEgovList.size()+", 실패건 num : "+failPk);
			}
		}
		
		return resultList;
	}
	


}
