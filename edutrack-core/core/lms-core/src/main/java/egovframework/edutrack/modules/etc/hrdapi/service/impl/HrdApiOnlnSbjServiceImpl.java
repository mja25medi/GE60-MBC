package egovframework.edutrack.modules.etc.hrdapi.service.impl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiOnlnSbjService;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiOnlnSbjVO;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiRestTemplate;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiVO;
import egovframework.edutrack.modules.log.apisync.service.LogApiSyncVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import net.sf.json.JSONObject;

/**
 *  [HRD API] 과정 정보 연계
 */
@Service("hrdApiOnlnSbjService")
public class HrdApiOnlnSbjServiceImpl  extends EgovAbstractServiceImpl implements HrdApiOnlnSbjService {

	@Autowired
	private HrdApiRestTemplate hrdApiRestTemplate;
	
	/** Mapper */
    @Resource(name="hrdApiOnlnSbjMapper")
    private HrdApiOnlnSbjMapper 		hrdApiOnlnSbjMapper;

    /**
	 * api용 과정 정보 조회
	 * @param HrdApiOnlnSbjVO vo
	 * @return  List<EgovMap>
	 */
	@Override
	public List<EgovMap> getList(HrdApiOnlnSbjVO vo) throws Exception {
		return hrdApiOnlnSbjMapper.list(vo);
	}
	
    /**
	 * api 발송용 과정 정보 리스트 조회
	 * @param HrdApiOnlnSbjVO vo
	 * @return  List<EgovMap>
	 */
	@Override
	public List<EgovMap> getListForSync(HrdApiOnlnSbjVO vo) throws Exception {
		return hrdApiOnlnSbjMapper.listForSync(vo);
	}

    /**
	 * api용 과정 정보 페이징 조회
	 * @param HrdApiOnlnSbjVO vo
	 * @return ProcessResultListVO<EgovMap>
	 */
	@Override
	public ProcessResultListVO<EgovMap> getListPageing(HrdApiOnlnSbjVO vo) throws Exception {
		ProcessResultListVO<EgovMap> resultList = new ProcessResultListVO<>();
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(vo.getCurPage());
		paginationInfo.setRecordCountPerPage(vo.getListScale());
		paginationInfo.setPageSize(vo.getPageScale());
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		
		int totalCnt = hrdApiOnlnSbjMapper.count(vo);
		paginationInfo.setTotalRecordCount(totalCnt);
		
		List<EgovMap> egovList = hrdApiOnlnSbjMapper.listPageing(vo);
		resultList.setReturnList(egovList);
		resultList.setPageInfo(paginationInfo);
		
		return resultList;
	}

    /**
	 * 과정 정보 내역 발송
	 * @param HrdApiOnlnSbjVO vo
	 * @return ProcessResultListVO<HrdApiVO>
	 */
	@Override
	public ProcessResultListVO<HrdApiVO> callCourseInfoHrdApi(List<EgovMap> dataList, HrdApiOnlnSbjVO hauivo) throws Exception {
		ProcessResultListVO<HrdApiVO> resultList = new ProcessResultListVO<HrdApiVO>();

		String jsonString = JsonUtil.getJsonStringHrdApi(dataList);
		
		//요청 URL
		String apiUrlStr = Constants.HRD_API_DOMAIN + Constants.HRD_API_COURSE_URL; 
		URI apiUrl = new URI(apiUrlStr);
		HrdApiVO vo = new HrdApiVO();
		vo.setApiKey(Constants.HRD_API_KEY);
		vo.setUserId(Constants.HRD_API_USER_ID);

		LogApiSyncVO lasvo = new LogApiSyncVO();
		lasvo.setSyncGubunCd("COURSE_INFO");
		lasvo.setSiteUrl(apiUrlStr);
		lasvo.setSyncSuccessCnt(dataList.size());
		
		//POST 방식으로 스트링을 통한 JSON 전송
		ResponseEntity<HrdApiVO> responseEntity = hrdApiRestTemplate.exchange(vo, apiUrl, jsonString,HrdApiVO.class,lasvo);
		HrdApiVO responVo = responseEntity.getBody();
 		String resultMsg = responVo.getMsg();
		if(responseEntity.getStatusCodeValue() == 200) {
			String strResult = (String) responVo.getCode();
			if("200".equals(strResult)) {
				hauivo.setSyncStatus("S");
				hauivo.setSyncResultMsg(resultMsg);
				for(EgovMap data : dataList) {
					hauivo.setNum(Integer.parseInt(StringUtil.nvl(data.get("num"),"0")));
					hrdApiOnlnSbjMapper.updateSyncStatus(hauivo);
				}
			}
			resultList.setResult(1);
			resultList.setMessage(resultMsg);
		}else {
			//실패지점 색출하여 그이전까지 데이터 재전송
			JSONObject detail = responVo.getDetail();
			String failSeq = (detail == null) ? "" : StringUtil.nvl(detail.get("seq"));
			List<EgovMap> reSendList = new ArrayList<>();
			String failPk = "";
			String failNum = "";
			if(!"".equals(failSeq)) {
 				int failSeqIndex = Integer.parseInt(failSeq) - 1;
				reSendList = dataList.subList(0, failSeqIndex);
				
				EgovMap failData = dataList.get(failSeqIndex);
				failPk = StringUtil.nvl(failData.get("courseAgentPk"));
				failNum = StringUtil.nvl(failData.get("num"),"0");
				
				hauivo.setSyncStatus("F");
				hauivo.setSyncResultMsg(responVo.getMsg());
				hauivo.setNum(Integer.parseInt(failNum));
				
				hrdApiOnlnSbjMapper.updateSyncStatus(hauivo);
			}
				
			if(reSendList.size() > 0) {
				//실패지점까지 성공데이터가 있을때
				lasvo.setSyncSuccessCnt(reSendList.size());
				lasvo.setSyncFailCnt(dataList.size()-reSendList.size());
				lasvo.setSyncFailPk(failPk);
				
				String reSendJsonString = JsonUtil.getJsonStringHrdApi(reSendList);
				
				ResponseEntity<HrdApiVO> reSendResponseEntity = hrdApiRestTemplate.exchange(vo,apiUrl, reSendJsonString,HrdApiVO.class,lasvo);
				HrdApiVO reSendresponVo = reSendResponseEntity.getBody();
				String reSendResultMsg = reSendresponVo.getMsg();
				String msg = reSendResultMsg +" 성공수 : "+reSendList.size()+", 실패건 pk : "+failPk;
				if(reSendResponseEntity.getStatusCodeValue() == 200) {
					if("200".equals(reSendresponVo.getCode())) {
						hauivo.setSyncStatus("S");
						hauivo.setSyncResultMsg(reSendResultMsg);
					}
					resultList.setResult(1);
					resultList.setMessage(msg);
					for (int i = 0; i < reSendList.size(); i++) {
						EgovMap data = reSendList.get(i);
						hauivo.setNum(Integer.parseInt(StringUtil.nvl(data.get("num"),"0")));
						hrdApiOnlnSbjMapper.updateSyncStatus(hauivo);
					}
				}else {
					resultList.setResult(-1);
					resultList.setMessage(msg);
				}
			}else {
				//실패지점까지 성공데이터가없을때
				resultList.setResult(-1);
				resultList.setMessage(responVo.getMsg()+" 성공수 : "+reSendList.size()+", 실패건 pk : "+failPk);
			}
		}
		return resultList;	
	}
}
