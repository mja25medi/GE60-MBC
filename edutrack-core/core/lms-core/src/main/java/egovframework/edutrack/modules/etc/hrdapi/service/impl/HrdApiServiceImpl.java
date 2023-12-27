package egovframework.edutrack.modules.etc.hrdapi.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiRestTemplate;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiService;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiSyncVO;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiVO;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdOtpVO;
import egovframework.edutrack.modules.log.apisync.service.LogApiSyncVO;
import egovframework.edutrack.modules.log.apisync.service.impl.LogApiSyncMapper;
import egovframework.edutrack.modules.user.info.service.impl.UsrUserInfoMapper;
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
@Service("hrdApiService")
public class HrdApiServiceImpl 
	extends EgovAbstractServiceImpl implements HrdApiService {
	
	@Autowired
	private HrdApiRestTemplate hrdApiRestTemplate;
	
	/** Mapper */
    @Resource(name="logApiSyncMapper")
    private LogApiSyncMapper 		logApiSyncMapper;
    
    @Resource(name="usrUserInfoMapper")
    private UsrUserInfoMapper 			usrUserInfoMapper;
    
    @Resource(name="hrdApiSyncMapper")
    private HrdApiSyncMapper 		hrdApiSyncMapper;
	
	/**
	 * call hrd api
	 * @param HrdApiVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<HrdApiVO> callHrdApi(HrdApiVO vo) throws Exception {
		ProcessResultListVO<HrdApiVO> resultList = new ProcessResultListVO<HrdApiVO>(); 
		try {
			//JSON 데이터 생성
			JSONObject jsonObj = new JSONObject();
			JSONArray jsonArr = new JSONArray();
			JSONObject data = new JSONObject();
			data.put("agentPk", "adbank01");
			data.put("seq", "1");
			data.put("userAgentPk", "user001");
			data.put("loginDate", "2021-09-07 11:26:25");
			data.put("loginIp", "100.100.100.100");
			jsonArr.add(data);
			jsonObj.put("dataList", jsonArr);
			//요청 URL
			String apiUrl = Constants.HRD_API_DOMAIN + Constants.HRD_API_LOGIN_URL; 
			HttpURLConnection conn = null;
			URL url = new URL(apiUrl);
			conn = (HttpURLConnection)url.openConnection();
			//method 및 header 설정
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("X-TQIAPI-HEADER", "지급받은 API KEY");
			conn.setRequestProperty("X-TQIAPI-USER", "emon 웹 사이트 아이디");
			//POST 방식으로 스트링을 통한 JSON 전송
			conn.setDoOutput(true);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream
			()));
			bw.write(jsonObj.toString());
			bw.flush();
			bw.close();
			//response 받기
			int responseCode = conn.getResponseCode();
			BufferedReader in;
			if(responseCode == 200) {
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
			in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			//결과 출력
			String inputLine;
			StringBuffer response = new StringBuffer();
			while((inputLine = in.readLine()) != null) {
			response.append(inputLine);
			}
			System.out.println("code : " + responseCode);
			System.out.println("error msg : " + response);
			in.close();
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
	/**
	 * hrd restTemplate api call
	 * @param HrdApiVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<HrdApiVO> callRestTemplateHrdApi(HrdApiVO vo) throws Exception {
		ProcessResultListVO<HrdApiVO> resultList = new ProcessResultListVO<HrdApiVO>(); 
		try {
			//JSON 데이터 생성
			JSONObject jsonObj = new JSONObject();
			JSONArray jsonArr = new JSONArray();
			JSONObject data = new JSONObject();
			data.put("agentPk", "adbank01");
			data.put("seq", "1");
			data.put("userAgentPk", "user001");
			data.put("userName", "김병탁");
			data.put("resNo", "90090921325");
			data.put("encResNo", "(주)애드뱅크");
			data.put("email", "btkim@adbank.co.kr");
			data.put("mobile", "010-9876-5432");
			data.put("changeState", "C");
			data.put("regDate", "2021-09-08 13:49:23");
			data.put("nwIno", "12345678");
			data.put("trneeSe", "007");
			data.put("irglbrSe", "000");
			
			data.put("loginDate", "2022-07-29 15:45:25");
			data.put("loginIp", "210.101.173.142");
			jsonArr.add(data);
			jsonObj.put("dataList", jsonArr);
			//요청 URL
			String apiUrl = Constants.HRD_API_DOMAIN + Constants.HRD_API_USER_URL; 
			URI url = new URI(apiUrl);
			vo.setApiKey(Constants.HRD_API_KEY);
			vo.setUserId(Constants.HRD_API_USER_ID);
			
			LogApiSyncVO lasvo = new LogApiSyncVO();
			lasvo.setSyncGubunCd("USER_INFO");
			lasvo.setSiteUrl(apiUrl);
			lasvo.setSyncSuccessCnt(1);
			
			//POST 방식으로 스트링을 통한 JSON 전송
			ResponseEntity<HrdApiVO> responseEntity = hrdApiRestTemplate.exchange(vo,url, jsonObj,HrdApiVO.class,lasvo);
			if(responseEntity.getStatusCodeValue() == 200) {
				HrdApiVO responVo = responseEntity.getBody();
				String strResult = (String) responVo.getCode();
				List<HrdApiVO> successList = new ArrayList<HrdApiVO>();
				if("200".equals(strResult)) {
					successList.add(responVo);
				}else {
					
				}
				resultList.setReturnList(successList);
			}
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
	
	/**
	 * 회원 정보 api call
	 * @param EgovMap
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<HrdApiVO> callUserInfoHrdApi(List<EgovMap> egovList,HrdApiSyncVO hasvo) throws Exception {
		ProcessResultListVO<HrdApiVO> resultList = new ProcessResultListVO<HrdApiVO>();
		//필수값 
		//hasvo.setCrsYear(hasvo.getCrsYear());
		//hasvo.setCrsTerm(hasvo.getCrsTerm());
		//hasvo.setSyncGubunCd(hasvo.getSyncGubunCd());
		//hasvo.setModNo(UserBroker.getUserNo(request));
		
		//JSON 데이터 생성
		JSONObject jsonObj = getJsonDataHrdApi(egovList);
		//요청 URL
		String apiUrl = Constants.HRD_API_DOMAIN + Constants.HRD_API_USER_URL; 
		URI url = new URI(apiUrl);
		HrdApiVO vo = new HrdApiVO();
		vo.setApiKey(Constants.HRD_API_KEY);
		vo.setUserId(Constants.HRD_API_USER_ID);
		
		
		LogApiSyncVO lasvo = new LogApiSyncVO();
		lasvo.setSyncGubunCd(hasvo.getSyncGubunCd());
		lasvo.setSiteUrl(apiUrl);
		lasvo.setSyncSuccessCnt(egovList.size());
		
		//POST 방식으로 스트링을 통한 JSON 전송
		ResponseEntity<HrdApiVO> responseEntity = hrdApiRestTemplate.exchange(vo,url, jsonObj,HrdApiVO.class,lasvo);
		HrdApiVO responVo = responseEntity.getBody();
		if(responseEntity.getStatusCodeValue() == 200) {
			String strResult = (String) responVo.getCode();
			if("200".equals(strResult)) {
				hasvo.setSyncStatus("S");
			}
			resultList.setResult(1);
			resultList.setMessage(responVo.getMsg());
			hasvo.setSyncResultMsg(responVo.getMsg());
			hrdApiSyncMapper.updateHrdApiSync(hasvo);
		}else {
			hasvo.setSyncStatus("W");
			hasvo.setSyncResultMsg("");
			hrdApiSyncMapper.updateHrdApiSync(hasvo);
			//실패지점 색출하여 그이전까지 데이터 재전송
			String failSeq = StringUtil.nvl(responVo.getDetail().get("seq"));
			String failPk = "";
			List<EgovMap> reSendEgovList = new ArrayList<>();
			if(!"".equals(failSeq)) {
				for (int i = 0; i < egovList.size(); i++) {
					EgovMap egovMap = egovList.get(i);
					if(!failSeq.equals(StringUtil.nvl(egovMap.get("seq")))) {
						reSendEgovList.add(egovMap);
					}
					if(failSeq.equals(StringUtil.nvl(egovMap.get("seq")))) {
						failPk = (String)egovMap.get("userAgentPk");
						break;
					}
				}
			}
			if(!"".equals(failPk)) {
				hasvo.setSyncStatus("F");
				hasvo.setSyncResultMsg(responVo.getMsg());
				hasvo.setApiPk(failPk);
				hrdApiSyncMapper.updateHrdApiSync(hasvo);
			}
			if(reSendEgovList.size() > 0) {
				//실패지점까지 성공데이터가 있을때
				lasvo.setSyncSuccessCnt(reSendEgovList.size());
				lasvo.setSyncFailCnt(egovList.size()-reSendEgovList.size());
				lasvo.setSyncFailPk(failPk);
				JSONObject reSendjsonObj = getJsonDataHrdApi(reSendEgovList);
				ResponseEntity<HrdApiVO> reSendResponseEntity = hrdApiRestTemplate.exchange(vo,url, reSendjsonObj,HrdApiVO.class,lasvo);
				HrdApiVO reSendresponVo = reSendResponseEntity.getBody();
				String msg = reSendresponVo.getMsg()+" 성공수 : "+reSendEgovList.size()+", 실패건 pk : "+failPk;
				if(reSendResponseEntity.getStatusCodeValue() == 200) {
					if("200".equals(reSendresponVo.getCode())) {
						hasvo.setSyncStatus("S");
					}
					resultList.setResult(1);
					resultList.setMessage(msg);
					hasvo.setSyncResultMsg(msg);
					
					for (int i = 0; i < reSendEgovList.size(); i++) {
						EgovMap egovMap = reSendEgovList.get(i);
						hasvo.setApiPk((String)egovMap.get("userAgentPk"));
						hrdApiSyncMapper.updateHrdApiSync(hasvo);
					}
				}else {
					resultList.setResult(-1);
					resultList.setMessage(msg);
				}
			}else {
				//실패지점까지 성공데이터가없을때
				resultList.setResult(-1);
				resultList.setMessage(responVo.getMsg()+" 성공수 : "+reSendEgovList.size()+", 실패건 pk : "+failPk);
			}
		}
		
		return resultList;
	}
	
	/**
	 * api call
	 * @param EgovMap
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<HrdApiVO> callHrdApiSend(List<EgovMap> egovList,HrdApiSyncVO hasvo) throws Exception {
		ProcessResultListVO<HrdApiVO> resultList = new ProcessResultListVO<HrdApiVO>();
		//필수값 
		//hasvo.setCrsYear(hasvo.getCrsYear());
		//hasvo.setCrsTerm(hasvo.getCrsTerm());
		//hasvo.setSyncGubunCd(hasvo.getSyncGubunCd());
		//hasvo.setModNo(UserBroker.getUserNo(request));
		
		//JSON 데이터 생성
		JSONObject jsonObj = getJsonDataHrdApi(egovList);
		//요청 URL
		String apiUrl = hasvo.getApiUrl();
		URI url = new URI(apiUrl);
		HrdApiVO vo = new HrdApiVO();
		vo.setApiKey(Constants.HRD_API_KEY);
		vo.setUserId(Constants.HRD_API_USER_ID);
		
		LogApiSyncVO lasvo = new LogApiSyncVO();
		lasvo.setSyncGubunCd(hasvo.getSyncGubunCd());
		lasvo.setSiteUrl(apiUrl);
		lasvo.setSyncSuccessCnt(egovList.size());
		
		//POST 방식으로 스트링을 통한 JSON 전송
		ResponseEntity<HrdApiVO> responseEntity = hrdApiRestTemplate.exchange(vo,url, jsonObj,HrdApiVO.class,lasvo);
		HrdApiVO responVo = responseEntity.getBody();
		if(responseEntity.getStatusCodeValue() == 200) {
			String strResult = (String) responVo.getCode();
			if("200".equals(strResult)) {
				hasvo.setSyncStatus("S");
			}
			resultList.setResult(1);
			resultList.setMessage(responVo.getMsg());
			hasvo.setSyncResultMsg(responVo.getMsg());
			hrdApiSyncMapper.updateHrdApiSync(hasvo);
		}else {
			hasvo.setSyncStatus("W");
			hasvo.setSyncResultMsg("");
			hrdApiSyncMapper.updateHrdApiSync(hasvo);
			//실패지점 색출하여 그이전까지 데이터 재전송
			String failSeq = StringUtil.nvl(responVo.getDetail().get("seq"));
			String failPk = "";
			List<EgovMap> reSendEgovList = new ArrayList<>();
			if(!"".equals(failSeq)) {
				for (int i = 0; i < egovList.size(); i++) {
					EgovMap egovMap = egovList.get(i);
					if(!failSeq.equals(StringUtil.nvl(egovMap.get("seq")))) {
						reSendEgovList.add(egovMap);
					}
					if(failSeq.equals(StringUtil.nvl(egovMap.get("seq")))) {
						failPk = (String)egovMap.get("userAgentPk");
						break;
					}
				}
			}
			if(!"".equals(failPk)) {
				hasvo.setSyncStatus("F");
				hasvo.setSyncResultMsg(responVo.getMsg());
				hasvo.setApiPk(failPk);
				hrdApiSyncMapper.updateHrdApiSync(hasvo);
			}
			if(reSendEgovList.size() > 0) {
				//실패지점까지 성공데이터가 있을때
				lasvo.setSyncSuccessCnt(reSendEgovList.size());
				lasvo.setSyncFailCnt(egovList.size()-reSendEgovList.size());
				lasvo.setSyncFailPk(failPk);
				JSONObject reSendjsonObj = getJsonDataHrdApi(reSendEgovList);
				ResponseEntity<HrdApiVO> reSendResponseEntity = hrdApiRestTemplate.exchange(vo,url, reSendjsonObj,HrdApiVO.class,lasvo);
				HrdApiVO reSendresponVo = reSendResponseEntity.getBody();
				String msg = reSendresponVo.getMsg()+" 성공수 : "+reSendEgovList.size()+", 실패건 pk : "+failPk;
				if(reSendResponseEntity.getStatusCodeValue() == 200) {
					if("200".equals(reSendresponVo.getCode())) {
						hasvo.setSyncStatus("S");
					}
					resultList.setResult(1);
					resultList.setMessage(msg);
					hasvo.setSyncResultMsg(msg);
					
					for (int i = 0; i < reSendEgovList.size(); i++) {
						EgovMap egovMap = reSendEgovList.get(i);
						hasvo.setApiPk((String)egovMap.get("userAgentPk"));
						hrdApiSyncMapper.updateHrdApiSync(hasvo);
					}
				}else {
					resultList.setResult(-1);
					resultList.setMessage(msg);
				}
			}else {
				//실패지점까지 성공데이터가없을때
				resultList.setResult(-1);
				resultList.setMessage(responVo.getMsg()+" 성공수 : "+reSendEgovList.size()+", 실패건 pk : "+failPk);
			}
		}
		
		return resultList;
	}
	
	public static JSONObject getJsonDataHrdApi(List<EgovMap> egovList) {
		//JSON 데이터 생성
		JSONObject jsonObj = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		for (int i = 0; i < egovList.size(); i++) {
			EgovMap egovMap = egovList.get(i);
			JSONObject data = JSONObject.fromObject(egovMap);
			jsonArr.add(data);
		}
		jsonObj.put("dataList", jsonArr);
		return jsonObj;
	}
	
	/**
	 * api 전송 데이터 싱크
	 * @param  HrdApiSyncVO 
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int mergeUserInfoHrdApiSync(HrdApiSyncVO vo) throws Exception{
		return hrdApiSyncMapper.mergeUserInfoHrdApiSync(vo);
	}
	
	/**
	 * api용 회원정보 조회
	 * @param HrdApiSyncVO vo
	 * @return  List<EgovMap>
	 */
	@Override
	public List<EgovMap> listUserInfoHrdApiSync(HrdApiSyncVO vo) throws Exception {
		return hrdApiSyncMapper.listUserInfoHrdApiSync(vo);
	}
	
	/**
	 * api 회원 로그인 정보 전송 데이터 싱크
	 * @param  HrdApiSyncVO 
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int mergeUserLoginHrdApiSync(HrdApiSyncVO vo) throws Exception{
		return hrdApiSyncMapper.mergeUserLoginHrdApiSync(vo);
	}
	
	/**
	 * api용 회원 로그인 정보 조회
	 * @param HrdApiSyncVO vo
	 * @return  List<EgovMap>
	 */
	@Override
	public List<EgovMap> listUserLoginHrdApiSync(HrdApiSyncVO vo) throws Exception {
		return hrdApiSyncMapper.listUserLoginHrdApiSync(vo);
	}
	
	/**
	 * api 전송 데이터 리스트 조회
	 * @param  HrdApiSyncVO 
	 * @return ProcessResultListVO<EgovMap>
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<EgovMap> listHrdApiSync(HrdApiSyncVO vo) throws Exception{
		ProcessResultListVO<EgovMap> resultList = new ProcessResultListVO<EgovMap>(); 
 		try {
 			List<EgovMap> logList =  hrdApiSyncMapper.listHrdApiSync(vo);
 			resultList.setResult(1);
 			resultList.setReturnList(logList);
 		} catch (Exception e){
 			e.printStackTrace();
 			resultList.setResult(-1);
 			resultList.setMessage(e.getMessage());
 		}
 		return resultList;
	}
	
	/**
	 * api 회원 정보 페이징 목록 조회
	 * @param HrdApiSyncVO vo
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<EgovMap> listPageingHrdApiSync(HrdApiSyncVO vo) throws Exception {
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
		int totalCount = hrdApiSyncMapper.countHrdApiSync(vo);
		paginationInfo.setTotalRecordCount(totalCount);
		
		List<EgovMap> etcList = hrdApiSyncMapper.listPageingHrdApiSync(vo);
		resultList.setResult(1);
		resultList.setReturnList(etcList);
		resultList.setPageInfo(paginationInfo);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
		}		
		return resultList;
	}
	
	/**
	 * api 회원 정보 페이징 목록 조회
	 * @param HrdApiSyncVO vo
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<EgovMap> listPageingUserInfo(HrdApiSyncVO vo) throws Exception {
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
		int totalCount = hrdApiSyncMapper.countUserInfo(vo);
		paginationInfo.setTotalRecordCount(totalCount);
		
		List<EgovMap> userList = hrdApiSyncMapper.listPageingUserInfo(vo);
		resultList.setResult(1);
		resultList.setReturnList(userList);
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
	public List<EgovMap> listUserInfo(HrdApiSyncVO vo) throws Exception {
		return hrdApiSyncMapper.listUserInfo(vo);
	}
	
	/**
	 * api 회원 로그인 정보 페이징 목록 조회
	 * @param HrdApiSyncVO vo
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<EgovMap> listPageingUserLogin(HrdApiSyncVO vo) throws Exception {
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
		int totalCount = hrdApiSyncMapper.countUserLogin(vo);
		paginationInfo.setTotalRecordCount(totalCount);
		
		List<EgovMap> userList = hrdApiSyncMapper.listPageingUserLogin(vo);
		resultList.setResult(1);
		resultList.setReturnList(userList);
		resultList.setPageInfo(paginationInfo);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
		}		
		return resultList;
	}
	
	/**
	 * api용 회원로그인정보 조회
	 * @param HrdApiSyncVO vo
	 * @return  List<EgovMap>
	 */
	@Override
	public List<EgovMap> listUserLogin(HrdApiSyncVO vo) throws Exception {
		return hrdApiSyncMapper.listUserLogin(vo);
	}

	/**
	 * api 년도 기수별  전송 등록 - 컨텐츠
	 * @param HrdApiSyncVO vo
	 * @return  int
	 */
	@Transactional
	@Override
	public int mergeCntsHrdApiSync(HrdApiSyncVO vo) {
		return hrdApiSyncMapper.mergeCntsHrdApiSync(vo);
	}
	
	/**
	 * api용 회원정보 조회
	 * @param HrdApiSyncVO vo
	 * @return  List<EgovMap>
	 */
	@Override
	public List<EgovMap> listCntsHrdApiSync(HrdApiSyncVO vo) {
		return hrdApiSyncMapper.listCntsHrdApiSync(vo);
	}
	
	/**
	 * otp용 진도차시 또는 평가횟수 평가타입 평가구분코드 구하기
	 * @param HrdApiSyncVO vo
	 * @return  List<EgovMap>
	 */
	@Override
	public HrdOtpVO getClassTmeEval(HrdOtpVO vo) {
		HrdOtpVO resultVo = null;
			if(vo.getEvalCd().equals("99")) {
				vo.setEvalType("기타");
				vo.setClassTme("00");
			}else if(vo.getEvalCd().equals("00")){
				vo.setEvalType("입과");
				vo.setClassTme("00");
			}else if(vo.getEvalCd().equals("01")){
				vo.setEvalType("진도"); 
				resultVo = hrdApiSyncMapper.getClassTmeUnit(vo);
			}else if(vo.getEvalCd().equals("02")){
				vo.setEvalType("시험");
				resultVo = hrdApiSyncMapper.getClassTmeExam(vo);
			}else if(vo.getEvalCd().equals("03")){
				vo.setEvalType("과제");
				resultVo = hrdApiSyncMapper.getClassTmeAsmt(vo);
			}else if(vo.getEvalCd().equals("04")){
				vo.setEvalType("진행평가");
				resultVo = hrdApiSyncMapper.getClassTmeSemiExam(vo);
			}else {
				vo.setEvalType("기타");
				vo.setClassTme("00");
			}
			if(resultVo != null) {
				if("".equals(StringUtil.nvl(resultVo.getClassTme()))) {
					vo.setClassTme("01");
				}else {
					if("0".equals(StringUtil.nvl(resultVo.getClassTme()))) {
						vo.setClassTme("01");
					}else if(StringUtil.nvl(resultVo.getClassTme()).length() == 1) {
						vo.setClassTme("0"+resultVo.getClassTme());
					}else {
						vo.setClassTme(resultVo.getClassTme());
					}
				}
			}else if(!"00".equals(vo.getClassTme())){
				vo.setClassTme("01");
			}
		
		return vo;
	}
	
	
}
