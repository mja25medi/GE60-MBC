package egovframework.edutrack.modules.etc.hrdapi.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.modules.course.subject.service.impl.SubjectOfflineMapper;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiRestTemplate;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiScoreService;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiScoreVO;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiVO;
import egovframework.edutrack.modules.log.apisync.service.LogApiSyncVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *  <b>기타 - 기타 관련사이트 관리</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("hrdApiScoreService")
public class HrdApiScoreServiceImpl 
	extends EgovAbstractServiceImpl implements HrdApiScoreService {
	
	@Autowired
	private HrdApiRestTemplate hrdApiRestTemplate;
	
	@Autowired
	@Qualifier("hrdApiTemplate")
	private RestTemplate restTemplate;

	@Resource(name="hrdApiScoreMapper")
	private HrdApiScoreMapper hrdApiScoreMapper;
	
	@Override
	public int insert(HrdApiScoreVO vo) {
		return hrdApiScoreMapper.insert(vo);
	}

	@Override
	public int update(HrdApiScoreVO vo) {
		return hrdApiScoreMapper.update(vo);
	}

	@Override
	public int merge(HrdApiScoreVO vo) {
		return hrdApiScoreMapper.merge(vo);
	}

	@Override
	public int delete(HrdApiScoreVO vo) {
		return hrdApiScoreMapper.delete(vo);
	}
	
	@Override
	public EgovMap selectScore(HrdApiScoreVO vo) {
		return hrdApiScoreMapper.selectScore(vo);
	}

	@Override
	public List<EgovMap> listScore(HrdApiScoreVO vo) {
		return hrdApiScoreMapper.listScore(vo);
	}
	
	@Override
	public List<EgovMap> listForApi(HrdApiScoreVO vo) {
		return hrdApiScoreMapper.selectForApi(vo);
	}

	@Override
	public ProcessResultListVO<EgovMap> listPagingScore(HrdApiScoreVO vo) {
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
			//int totalCount = hrdApiScoreMapper.countListPageingScore(vo);
			int totalCount = 0;
			
			List<EgovMap> scoreList = hrdApiScoreMapper.listPagingScore(vo);
			if(scoreList != null) {
				totalCount = scoreList.size();
			}
			paginationInfo.setTotalRecordCount(totalCount);
			
			resultList.setResult(1);
			resultList.setReturnList(scoreList);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
		}		
		return resultList;
	}
	
	@Override
	public ProcessResultListVO<HrdApiVO> callApi(List<EgovMap> egovList, HrdApiScoreVO vo) {
		ProcessResultListVO<HrdApiVO> resultList = new ProcessResultListVO<HrdApiVO>();
		
		//List<EgovMap> egovList = hrdApiScoreMapper.selectForApi(vo);
		
		if(egovList == null || egovList.size() == 0) {
			throw new ServiceProcessException("API 발송할 이력이 없습니다.");
		}
		
		String jsonString = JsonUtil.getJsonStringHrdApi(egovList);	
		//요청 URL
		String apiUrl = Constants.HRD_API_DOMAIN + Constants.HRD_API_SCORE_URL; 
		URI url;
		try {
			url = new URI(apiUrl);
		} catch (URISyntaxException e1) {
			throw new ServiceProcessException("URL 조합 오류");
		}
		HrdApiVO hrdApiVO = new HrdApiVO();
		hrdApiVO.setApiKey(Constants.HRD_API_KEY);
		hrdApiVO.setUserId(Constants.HRD_API_USER_ID);
		
		LogApiSyncVO lasvo = new LogApiSyncVO();
		lasvo.setCrsCd(vo.getCrsCd());
		lasvo.setSyncGubunCd("SCORE");
		lasvo.setSiteUrl(apiUrl);
		lasvo.setSyncSuccessCnt(egovList.size());
		
		ResponseEntity<HrdApiVO> responseEntity;
		try {
			responseEntity = hrdApiRestTemplate.exchange(hrdApiVO, url, jsonString, HrdApiVO.class,lasvo);
		} catch (Exception e) {
			e.getStackTrace();
			throw new ServiceProcessException("발송 오류");
		}
		
		HrdApiVO responVo = responseEntity.getBody();
		if(responseEntity.getStatusCodeValue() == 200) {
			String strResult = (String) responVo.getCode();
			if("200".equals(strResult)) {
				vo.setSyncStatus("S");
			}
			resultList.setResult(1);
			resultList.setMessage(responVo.getMsg());
			vo.setSyncResultMsg(responVo.getMsg());
			for (int i = 0; i < egovList.size(); i++) {
				EgovMap egovMap = egovList.get(i);
				vo.setNum(Integer.parseInt(StringUtil.nvl(egovMap.get("num"))));
				/*vo.setUserAgentPk((String)egovMap.get("userAgentPk"));
				vo.setCourseAgentPk((String)egovMap.get("courseAgentPk"));
				vo.setClassAgentPk((String)egovMap.get("classAgentPk"));*/
				hrdApiScoreMapper.update(vo);
			}
		}else {
			//실패지점 색출하여 그이전까지 데이터 재전송
			String failSeq = StringUtil.nvl(responVo.getDetail().get("seq"));
			String failNum = "";
			/*String failUserAgentPk = "";
			String failCourseAgentPk = "";
			String failClassAgentPk = "";*/
			List<EgovMap> reSendEgovList = new ArrayList<>();
			if(!"".equals(failSeq)) {
				for (int i = 0; i < egovList.size(); i++) {
					EgovMap egovMap = egovList.get(i);
					if(!failSeq.equals(StringUtil.nvl(egovMap.get("seq")))) {
						reSendEgovList.add(egovMap);
					}
					if(failSeq.equals(StringUtil.nvl(egovMap.get("seq")))) {
						failNum = StringUtil.nvl(egovMap.get("num"));
						/*failUserAgentPk = (String)egovMap.get("userAgentPk");
						failCourseAgentPk = (String)egovMap.get("courseAgentPk");
						failClassAgentPk = (String)egovMap.get("classAgentPk");*/
						break;
					}
				}
			}
			if(!"".equals(failNum)) {
				vo.setSyncStatus("F");
				vo.setSyncResultMsg(responVo.getMsg());
				vo.setNum(Integer.parseInt(failNum));
				/*vo.setUserAgentPk((String)failUserAgentPk);
				vo.setCourseAgentPk(failCourseAgentPk);
				vo.setClassAgentPk(failClassAgentPk);*/
				hrdApiScoreMapper.update(vo);
			}
			if(reSendEgovList.size() > 0) {
				//실패지점까지 성공데이터가 있을때
				lasvo.setSyncSuccessCnt(reSendEgovList.size());
				lasvo.setSyncFailCnt(egovList.size()-reSendEgovList.size());
				lasvo.setSyncFailPk(failNum);
				String reSendjsonString = JsonUtil.getJsonStringHrdApi(reSendEgovList);
				ResponseEntity<HrdApiVO> reSendResponseEntity;
				try {
					reSendResponseEntity = hrdApiRestTemplate.exchange(hrdApiVO, url, reSendjsonString,HrdApiVO.class,lasvo);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServiceProcessException("재발송 오류");
				}
				HrdApiVO reSendresponVo = reSendResponseEntity.getBody();
				String msg = reSendresponVo.getMsg()+" 성공수 : "+reSendEgovList.size()+", 실패건 pk : "+failNum;
				if(reSendResponseEntity.getStatusCodeValue() == 200) {
					if("200".equals(reSendresponVo.getCode())) {
						vo.setSyncStatus("S");
					}
					resultList.setResult(1);
					resultList.setMessage(msg);
					vo.setSyncResultMsg(msg);
					
					for (int i = 0; i < reSendEgovList.size(); i++) {
						EgovMap egovMap = reSendEgovList.get(i);
						vo.setNum(Integer.parseInt(StringUtil.nvl(egovMap.get("num"))));
						/*vo.setUserAgentPk((String)egovMap.get("userAgentPk"));
						vo.setCourseAgentPk((String)egovMap.get("courseAgentPk"));
						vo.setClassAgentPk((String)egovMap.get("classAgentPk"));*/
						hrdApiScoreMapper.update(vo);
					}
				}else {
					resultList.setResult(-1);
					resultList.setMessage(msg);
				}
			}else {
				//실패지점까지 성공데이터가없을때
				resultList.setResult(-1);
				resultList.setMessage(responVo.getMsg()+" 성공수 : "+reSendEgovList.size()+", 실패건 pk : "+failNum);
			}
		}
		
		
		return resultList;
	}
	
}
