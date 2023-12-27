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
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiRestTemplate;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiStdService;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiStdVO;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiVO;
import egovframework.edutrack.modules.log.apisync.service.LogApiSyncVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Service("hrdApiStdService")
public class HrdApiStdServiceImpl implements HrdApiStdService {

    @Resource(name="hrdApiStdMapper")
    private HrdApiStdMapper 		hrdApiStdMapper;
    
	@Autowired
	private HrdApiRestTemplate hrdApiRestTemplate;
	
	/**
	 * api용 회원정보 조회
	 * @param HrdApiSyncVO vo
	 * @return  List<EgovMap>
	 */
	@Override
	public List<EgovMap> listStdInfo(HrdApiStdVO vo) throws Exception {
		return hrdApiStdMapper.listStdInfo(vo);
	}
	
	/**
	 * api 회원 정보 페이징 목록 조회
	 * @param HrdApiUserInfoVO vo
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<EgovMap> listStdInfoPageing(HrdApiStdVO vo) throws Exception {
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
		int totalCount = hrdApiStdMapper.countStdInfo(vo);
		paginationInfo.setTotalRecordCount(totalCount);
		
		List<EgovMap> stdList = hrdApiStdMapper.listStdInfoPageing(vo);
		resultList.setResult(1);
		resultList.setReturnList(stdList);
		resultList.setPageInfo(paginationInfo);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
		}		
		return resultList;
	}
	
	/**
	 * api용 회원정보 조회
	 * @param HrdApiSyncVO vo
	 * @return  List<EgovMap>
	 */
	@Override
	public EgovMap countStdApiStatus(HrdApiStdVO vo) throws Exception {
		return hrdApiStdMapper.countStdApiStatus(vo);
	}
	
	/**
	 * 회원 정보 api call
	 * @param EgovMap
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<HrdApiVO> callStdInfoHrdApi(String jsonString, List<EgovMap> egovList,HrdApiStdVO hasvo) throws Exception {
		ProcessResultListVO<HrdApiVO> resultList = new ProcessResultListVO<HrdApiVO>();
		//필수값 
		//hasvo.setCrsYear(hasvo.getCrsYear());
		//hasvo.setCrsTerm(hasvo.getCrsTerm());
		//hasvo.setSyncGubunCd(hasvo.getSyncGubunCd());
		//hasvo.setModNo(UserBroker.getUserNo(request));
		
		//요청 URL
		String apiUrl = Constants.HRD_API_DOMAIN + Constants.HRD_API_ATTEND_URL;  
		URI url = new URI(apiUrl);
		HrdApiVO vo = new HrdApiVO();
		vo.setApiKey(Constants.HRD_API_KEY);
		vo.setUserId(Constants.HRD_API_USER_ID);
		
		LogApiSyncVO lasvo = new LogApiSyncVO();
		lasvo.setCrsCd(hasvo.getCrsCd());
		lasvo.setSyncGubunCd("ATTEND_INFO");
		lasvo.setSiteUrl(apiUrl);
		lasvo.setSyncSuccessCnt(egovList.size());
		
		//POST 방식으로 스트링을 통한 JSON 전송
		ResponseEntity<HrdApiVO> responseEntity = hrdApiRestTemplate.exchange(vo, url, jsonString, HrdApiVO.class,lasvo);
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
				hrdApiStdMapper.updateHrdApiStd(hasvo);
			}
		}else {
			//실패지점 색출하여 그이전까지 데이터 재전송
			String failSeq = StringUtil.nvl(responVo.getDetail().get("seq"));
			String failNum = "";
			List<EgovMap> reSendEgovList = new ArrayList<>();
			if(!"".equals(failSeq)) {
				for (int i = 0; i < egovList.size(); i++) {
					EgovMap egovMap = egovList.get(i);
					if(!failSeq.equals(StringUtil.nvl(egovMap.get("seq")))) {
						reSendEgovList.add(egovMap);
					}
					if(failSeq.equals(StringUtil.nvl(egovMap.get("seq")))) {
						failNum = StringUtil.nvl(egovMap.get("num"));
						break;
					}
				}
			}
			if(!"".equals(failNum)) {
				hasvo.setSyncStatus("F");
				hasvo.setSyncResultMsg(responVo.getMsg());
				hasvo.setNum(Integer.parseInt(failNum));
				hrdApiStdMapper.updateHrdApiStd(hasvo);
			}
			if(reSendEgovList.size() > 0) {
				//실패지점까지 성공데이터가 있을때
				lasvo.setSyncSuccessCnt(reSendEgovList.size());
				lasvo.setSyncFailCnt(egovList.size()-reSendEgovList.size());
				lasvo.setSyncFailPk(failNum);
				String reSendjsonObj = JsonUtil.getJsonStringHrdApi(reSendEgovList);
				ResponseEntity<HrdApiVO> reSendResponseEntity = hrdApiRestTemplate.exchange(vo,url, reSendjsonObj,HrdApiVO.class,lasvo);
				HrdApiVO reSendresponVo = reSendResponseEntity.getBody();
				String msg = reSendresponVo.getMsg()+" 성공수 : "+reSendEgovList.size()+", 실패건 pk : "+failNum;
				if(reSendResponseEntity.getStatusCodeValue() == 200) {
					if("200".equals(reSendresponVo.getCode())) {
						hasvo.setSyncStatus("S");
					}
					resultList.setResult(1);
					resultList.setMessage(msg);
					hasvo.setSyncResultMsg(msg);
					
					for (int i = 0; i < reSendEgovList.size(); i++) {
						EgovMap egovMap = reSendEgovList.get(i);
						hasvo.setNum(Integer.parseInt(StringUtil.nvl(egovMap.get("num"))));
						hrdApiStdMapper.updateHrdApiStd(hasvo);
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
