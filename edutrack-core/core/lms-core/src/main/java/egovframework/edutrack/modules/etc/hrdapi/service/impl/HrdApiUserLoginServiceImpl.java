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
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiRestTemplate;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiUserInfoService;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiUserInfoVO;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiUserLoginVO;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiVO;
import egovframework.edutrack.modules.log.apisync.service.LogApiSyncVO;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiUserLoginService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import net.sf.json.JSONObject;

/**
 *  <b>기타 - 기타 관련사이트 관리</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("hrdApiUserLoginService")
public class HrdApiUserLoginServiceImpl  extends EgovAbstractServiceImpl implements HrdApiUserLoginService {
	
	@Autowired
	private HrdApiRestTemplate hrdApiRestTemplate;
	
	/** Mapper */
    @Resource(name="hrdApiUserLoginMapper")
    private HrdApiUserLoginMapper 		hrdApiUserLoginMapper;
	
	
    
    /**
	 * api용 회원 로그인 정보 조회
	 * @param HrdApiUserLoginVO vo
	 * @return  List<EgovMap>
	 */
	@Override
	public EgovMap selectUserLogin(HrdApiUserLoginVO vo) throws Exception {
		return hrdApiUserLoginMapper.selectUserLogin(vo);
	}
	
	/**
	 * api용 회원 로그인 정보 조회
	 * @param HrdApiUserLoginVO vo
	 * @return  List<EgovMap>
	 */
	@Override
	public List<EgovMap> listUserLogin(HrdApiUserLoginVO vo) throws Exception {
		return hrdApiUserLoginMapper.listUserLogin(vo);
	}
	
	/**
	 * api 회원  로그인 정보 전송 데이터 리스트 조회
	 * @param  HrdApiUserLoginVO 
	 * @return ProcessResultListVO<EgovMap>
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<EgovMap> listUserLoginResult(HrdApiUserLoginVO vo) throws Exception{
		ProcessResultListVO<EgovMap> resultList = new ProcessResultListVO<EgovMap>(); 
 		try {
 			List<EgovMap> logList =  hrdApiUserLoginMapper.listUserLogin(vo);
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
	 * api 회원 로그인  정보 페이징 목록 조회
	 * @param HrdApiUserLoginVO vo
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<EgovMap> listPageingUserLogin(HrdApiUserLoginVO vo) throws Exception {
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
		int totalCount = hrdApiUserLoginMapper.countUserLogin(vo);
		paginationInfo.setTotalRecordCount(totalCount);
		
		List<EgovMap> etcList = hrdApiUserLoginMapper.listPageingUserLogin(vo);
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
	 * api용 회원 로그인 정보 조회
	 * @param HrdApiUserLoginVO vo
	 * @return  List<EgovMap>
	 */
	@Override
	public List<EgovMap> listUserLoginHrdApiSync(HrdApiUserLoginVO vo) throws Exception {
		return hrdApiUserLoginMapper.listUserLoginHrdApiSync(vo);
	}

	/**
	 * api용 회원 로그인 정보 개수 조회
	 * @param HrdApiUserLoginVO vo
	 * @return  List<EgovMap>
	 */
	@Override
	public int countUserLogin(HrdApiUserLoginVO vo) throws Exception {
		return hrdApiUserLoginMapper.countUserLogin(vo);
	}

	/**
	 * api용 회원 로그인 정보 등록
	 * @param HrdApiUserLoginVO vo
	 * @return  List<EgovMap>
	 */
	@Override
	public int insertUserLogin(HrdApiUserLoginVO vo) throws Exception {
		return hrdApiUserLoginMapper.insertUserLogin(vo);
	}

	/**
	 * api용 회원 로그인 정보 수정
	 * @param HrdApiUserLoginVO vo
	 * @return  List<EgovMap>
	 */
	@Override
	public int updateUserLogin(HrdApiUserLoginVO vo) throws Exception {
		return hrdApiUserLoginMapper.updateUserLogin(vo);
	}

	/**
	 * api용 회원 로그인 정보 삭제
	 * @param HrdApiUserLoginVO vo
	 * @return  List<EgovMap>
	 */
	@Override
	public int deleteUserLogin(HrdApiUserLoginVO vo) throws Exception {
		return hrdApiUserLoginMapper.deleteUserLogin(vo);
	}
	
	/**
	 * 회원 정보 api call
	 * @param EgovMap
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<HrdApiVO> callUserLoginHrdApi(List<EgovMap> egovList,HrdApiUserLoginVO haulvo) throws Exception {
		ProcessResultListVO<HrdApiVO> resultList = new ProcessResultListVO<HrdApiVO>();
		//필수값 
		//hasvo.setCrsYear(hasvo.getCrsYear());
		//hasvo.setCrsTerm(hasvo.getCrsTerm());
		//hasvo.setSyncGubunCd(hasvo.getSyncGubunCd());
		//hasvo.setModNo(UserBroker.getUserNo(request));
		
		//JSON데이터 변환
		String jsonString = JsonUtil.getJsonStringHrdApi(egovList);
		
		//요청 URL
		String apiUrl = Constants.HRD_API_DOMAIN + Constants.HRD_API_LOGIN_URL; 
		URI url = new URI(apiUrl);
		HrdApiVO vo = new HrdApiVO();
		vo.setApiKey(Constants.HRD_API_KEY);
		vo.setUserId(Constants.HRD_API_USER_ID);
		
		LogApiSyncVO lasvo = new LogApiSyncVO();
		lasvo.setSyncGubunCd("USER_LOGIN");
		lasvo.setSiteUrl(apiUrl);
		lasvo.setSyncSuccessCnt(egovList.size());
		
		//POST 방식으로 스트링을 통한 JSON 전송
		ResponseEntity<HrdApiVO> responseEntity = hrdApiRestTemplate.exchange(vo,url, jsonString,HrdApiVO.class,lasvo);
		HrdApiVO responVo = responseEntity.getBody();
		if(responseEntity.getStatusCodeValue() == 200) {
			String strResult = (String) responVo.getCode();
			if("200".equals(strResult)) {
				haulvo.setSyncStatus("S");
			}
			resultList.setResult(1);
			resultList.setMessage(responVo.getMsg());
			haulvo.setSyncResultMsg(responVo.getMsg());
			
			for (int i = 0; i < egovList.size(); i++) {
				EgovMap egovMap = egovList.get(i);
				haulvo.setNum(Integer.parseInt(StringUtil.nvl(egovMap.get("num"))));
				haulvo.setUserAgentPk((String)egovMap.get("userAgentPk"));
				haulvo.setSeq(Integer.parseInt(StringUtil.nvl(egovMap.get("seq"))));
				hrdApiUserLoginMapper.updateUserLogin(haulvo);
			}
		}else {
			//실패지점 색출하여 그이전까지 데이터 재전송
			String failSeq = responVo.getDetail() == null?"":StringUtil.nvl(responVo.getDetail().get("seq"));
			String failNum = "";
			String failPk = "";
			List<EgovMap> reSendEgovList = new ArrayList<>();
			if(!"".equals(failSeq)) {
				for (int i = 0; i < egovList.size(); i++) {
					EgovMap egovMap = egovList.get(i);
					if(!failSeq.equals(StringUtil.nvl(egovMap.get("seq")))) {
						reSendEgovList.add(egovMap);
					}
					if(failSeq.equals(StringUtil.nvl(egovMap.get("seq")))) {
						failNum = StringUtil.nvl(egovMap.get("num"));
						failPk = (String)egovMap.get("userAgentPk");
						break;
					}
				}
			}
			if(!"".equals(failPk)) {
				haulvo.setSyncStatus("F");
				haulvo.setSyncResultMsg(responVo.getMsg());
				haulvo.setNum(Integer.parseInt(failNum));
				haulvo.setUserAgentPk((String)failPk);
				haulvo.setSeq(Integer.parseInt(failSeq));
				hrdApiUserLoginMapper.updateUserLogin(haulvo);
			}
			if(reSendEgovList.size() > 0) {
				//실패지점까지 성공데이터가 있을때
				lasvo.setSyncSuccessCnt(reSendEgovList.size());
				lasvo.setSyncFailCnt(egovList.size()-reSendEgovList.size());
				lasvo.setSyncFailPk(failPk);
				String reSendjsonString = JsonUtil.getJsonStringHrdApi(reSendEgovList);
				ResponseEntity<HrdApiVO> reSendResponseEntity = hrdApiRestTemplate.exchange(vo,url, reSendjsonString,HrdApiVO.class,lasvo);
				HrdApiVO reSendresponVo = reSendResponseEntity.getBody();
				String msg = reSendresponVo.getMsg()+" 성공수 : "+reSendEgovList.size()+", 실패건 pk : "+failPk;
				if(reSendResponseEntity.getStatusCodeValue() == 200) {
					if("200".equals(reSendresponVo.getCode())) {
						haulvo.setSyncStatus("S");
					}
					resultList.setResult(1);
					resultList.setMessage(msg);
					haulvo.setSyncResultMsg(msg);
					
					for (int i = 0; i < reSendEgovList.size(); i++) {
						EgovMap egovMap = reSendEgovList.get(i);
						haulvo.setNum(Integer.parseInt(StringUtil.nvl(egovMap.get("num"))));
						haulvo.setUserAgentPk((String)egovMap.get("userAgentPk"));
						haulvo.setSeq(Integer.parseInt(StringUtil.nvl(egovMap.get("seq"))));
						hrdApiUserLoginMapper.updateUserLogin(haulvo);
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
}
