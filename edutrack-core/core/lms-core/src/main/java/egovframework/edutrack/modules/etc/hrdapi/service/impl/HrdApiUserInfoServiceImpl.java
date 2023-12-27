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
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiUserInfoService;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiUserInfoVO;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiVO;
import egovframework.edutrack.modules.log.apisync.service.LogApiSyncVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *  <b>기타 - 기타 관련사이트 관리</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("hrdApiUserInfoService")
public class HrdApiUserInfoServiceImpl  extends EgovAbstractServiceImpl implements HrdApiUserInfoService {
	
	@Autowired
	private HrdApiRestTemplate hrdApiRestTemplate;
	
	/** Mapper */
    @Resource(name="hrdApiUserInfoMapper")
    private HrdApiUserInfoMapper 		hrdApiUserInfoMapper;
	
	
    
    /**
	 * api용 회원정보 조회
	 * @param HrdApiUserInfoVO vo
	 * @return  List<EgovMap>
	 */
	@Override
	public EgovMap selectUserInfo(HrdApiUserInfoVO vo) throws Exception {
		return hrdApiUserInfoMapper.selectUserInfo(vo);
	}
	
	/**
	 * api용 회원정보 조회
	 * @param HrdApiUserInfoVO vo
	 * @return  List<EgovMap>
	 */
	@Override
	public List<EgovMap> listUserInfo(HrdApiUserInfoVO vo) throws Exception {
		return hrdApiUserInfoMapper.listUserInfo(vo);
	}
	
	/**
	 * api 전송 데이터 리스트 조회
	 * @param  HrdApiUserInfoVO 
	 * @return ProcessResultListVO<EgovMap>
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<EgovMap> listUserInfoResult(HrdApiUserInfoVO vo) throws Exception{
		ProcessResultListVO<EgovMap> resultList = new ProcessResultListVO<EgovMap>(); 
 		try {
 			List<EgovMap> logList =  hrdApiUserInfoMapper.listUserInfo(vo);
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
	 * @param HrdApiUserInfoVO vo
	 * @param pageIndex
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<EgovMap> listPageingUserInfo(HrdApiUserInfoVO vo) throws Exception {
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
		int totalCount = hrdApiUserInfoMapper.countUserInfo(vo);
		paginationInfo.setTotalRecordCount(totalCount);
		
		List<EgovMap> etcList = hrdApiUserInfoMapper.listPageingUserInfo(vo);
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
	 * api용 회원정보 조회
	 * @param HrdApiUserInfoVO vo
	 * @return  List<EgovMap>
	 */
	@Override
	public List<EgovMap> listUserInfoHrdApiSync(HrdApiUserInfoVO vo) throws Exception {
		return hrdApiUserInfoMapper.listUserInfoHrdApiSync(vo);
	}

	/**
	 * api용 회원정보 개수 조회
	 * @param HrdApiUserInfoVO vo
	 * @return  List<EgovMap>
	 */
	@Override
	public int countUserInfo(HrdApiUserInfoVO vo) throws Exception {
		return hrdApiUserInfoMapper.countUserInfo(vo);
	}

	/**
	 * api용 회원정보 등록
	 * @param HrdApiUserInfoVO vo
	 * @return  List<EgovMap>
	 */
	@Override
	public int insertUserInfo(HrdApiUserInfoVO vo) throws Exception {
		return hrdApiUserInfoMapper.insertUserInfo(vo);
	}

	/**
	 * api용 회원정보 수정
	 * @param HrdApiUserInfoVO vo
	 * @return  List<EgovMap>
	 */
	@Override
	public int updateUserInfo(HrdApiUserInfoVO vo) throws Exception {
		return hrdApiUserInfoMapper.updateUserInfo(vo);
	}

	/**
	 * api용 회원정보 삭제
	 * @param HrdApiUserInfoVO vo
	 * @return  List<EgovMap>
	 */
	@Override
	public int deleteUserInfo(HrdApiUserInfoVO vo) throws Exception {
		return hrdApiUserInfoMapper.deleteUserInfo(vo);
	}
	
	/**
	 * 회원 정보 api call
	 * @param EgovMap
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<HrdApiVO> callUserInfoHrdApi(List<EgovMap> egovList,HrdApiUserInfoVO hauivo) throws Exception {
		ProcessResultListVO<HrdApiVO> resultList = new ProcessResultListVO<HrdApiVO>();

		//JSON데이터 변환
		String jsonString = JsonUtil.getJsonStringHrdApi(egovList);
		
		//요청 URL
		String apiUrl = Constants.HRD_API_DOMAIN + Constants.HRD_API_USER_URL;
		URI url = new URI(apiUrl);
		HrdApiVO vo = new HrdApiVO();
		vo.setApiKey(Constants.HRD_API_KEY);
		vo.setUserId(Constants.HRD_API_USER_ID);
		
		LogApiSyncVO lasvo = new LogApiSyncVO();
		lasvo.setSyncGubunCd("USER_INFO");
		lasvo.setSiteUrl(apiUrl);
		lasvo.setSyncSuccessCnt(egovList.size());
		
		//POST 방식으로 스트링을 통한 JSON 전송
		ResponseEntity<HrdApiVO> responseEntity = hrdApiRestTemplate.exchange(vo,url, jsonString,HrdApiVO.class,lasvo);
		HrdApiVO responVo = responseEntity.getBody();
		if(responseEntity.getStatusCodeValue() == 200) {
			String strResult = (String) responVo.getCode();
			if("200".equals(strResult)) {
				hauivo.setSyncStatus("S");
			}
			resultList.setResult(1);
			resultList.setMessage(responVo.getMsg());
			hauivo.setSyncResultMsg(responVo.getMsg());
			for (int i = 0; i < egovList.size(); i++) {
				EgovMap egovMap = egovList.get(i);
				hauivo.setNum(Integer.parseInt(StringUtil.nvl(egovMap.get("num"))));
				hauivo.setUserAgentPk((String)egovMap.get("userAgentPk"));
				hauivo.setSeq(Integer.parseInt(StringUtil.nvl(egovMap.get("seq"))));
				hauivo.setRegDate((String)egovMap.get("regDate"));
				hrdApiUserInfoMapper.updateUserInfo(hauivo);
			}
		}else {
			//실패지점 색출하여 그이전까지 데이터 재전송
			String failSeq = StringUtil.nvl(responVo.getDetail().get("seq"));
			String failNum = "";
			String failPk = "";
			String regDate = "";
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
						regDate = (String)egovMap.get("regDate");
						break;
					}
				}
			}
			if(!"".equals(failPk)) {
				hauivo.setSyncStatus("F");
				hauivo.setSyncResultMsg(responVo.getMsg());
				hauivo.setNum(Integer.parseInt(failNum));
				hauivo.setUserAgentPk((String)failPk);
				hauivo.setSeq(Integer.parseInt(failSeq));
				hauivo.setRegDate(regDate);
				hrdApiUserInfoMapper.updateUserInfo(hauivo);
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
						hauivo.setSyncStatus("S");
					}
					resultList.setResult(1);
					resultList.setMessage(msg);
					hauivo.setSyncResultMsg(msg);
					
					for (int i = 0; i < reSendEgovList.size(); i++) {
						EgovMap egovMap = reSendEgovList.get(i);
						hauivo.setNum(Integer.parseInt(StringUtil.nvl(egovMap.get("num"))));
						hauivo.setUserAgentPk((String)egovMap.get("userAgentPk"));
						hauivo.setRegDate((String)egovMap.get("regDate"));
						hauivo.setSeq(Integer.parseInt(StringUtil.nvl(egovMap.get("seq"))));
						hrdApiUserInfoMapper.updateUserInfo(hauivo);
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
