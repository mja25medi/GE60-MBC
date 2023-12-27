package egovframework.edutrack.batch.api.hrd;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.request.ServletRequestAttributes;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.JsonUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiCreService;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiCreVO;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiOnlnSbjService;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiOnlnSbjVO;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiScoreService;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiScoreVO;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiStdService;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiStdVO;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiUserInfoService;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiUserInfoVO;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiUserLoginService;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiUserLoginVO;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Component
public class HrdApiBatch {
	protected final Log log = LogFactory.getLog(this.getClass());
	
	@Autowired @Qualifier("hrdApiUserInfoService")
	private HrdApiUserInfoService	hrdApiUserInfoService;
	
	@Autowired @Qualifier("hrdApiUserLoginService")
	private HrdApiUserLoginService	hrdApiUserLoginService;
	
	@Autowired @Qualifier("hrdApiStdService")
	private HrdApiStdService	hrdApiStdService;
	
	@Autowired @Qualifier("hrdApiOnlnSbjService")
	private HrdApiOnlnSbjService	hrdApiOnlnSbjService;
	
	@Autowired @Qualifier("hrdApiCreService")
	private HrdApiCreService	hrdApiCreService;
	
	@Autowired @Qualifier("hrdApiScoreService")
	private HrdApiScoreService	hrdApiScoreService;
	
	//초     분  시   일  월   년
	//20 * * * * *  20초
	//0 0 0/1 * * *  한시간
	/**
	 * HRDK API : 회원정보
	 * @throws Exception
	 */
	@Scheduled(cron="0 0 0/1 * * *")
	public void hrdApiUserInfo() throws Exception {
		ProcessResultListVO<HrdApiVO> resultList = new ProcessResultListVO<HrdApiVO>();
		String apiCd = Constants.HRD_API_CD_USER_HIST;
		String uri = Constants.HRD_API_CD_USER_HIST;
		
		String ymdH = this.getYmdH(-1);
		String startDate = ymdH+":00:00";
		String endDate = ymdH+":59:59";
		
		try {
			// hrd api 연동 사용 유무가 'Y' 인 경우만 수행
			if(Constants.HRD_API_USE_YN.equals("Y")) {
				List<EgovMap> egovList = new ArrayList<EgovMap>();
				HrdApiUserInfoVO hauivo = new HrdApiUserInfoVO();
				hauivo.setRegNo("USR000000002");
				hauivo.setModNo("USR000000002");
				hauivo.setSearchKey("syncStatus");
				hauivo.setSearchValue("W");
				hauivo.setSearchFrom(startDate);
				hauivo.setSearchTo(endDate);
				egovList = hrdApiUserInfoService.listUserInfoHrdApiSync(hauivo);
				
				System.out.println("hrdkapi-----apiCd:"+apiCd+", uri:"+uri);
				System.out.println("hrdkapi-----hrdApiUserInfo("+ymdH+")");
				System.out.println("hrdkapi-----startDate:"+startDate+", endDate:"+endDate);
				System.out.println("hrdkapi-----resultList size:"+egovList.size());
				
				if(egovList.size() > 0) {
					resultList = hrdApiUserInfoService.callUserInfoHrdApi(egovList,hauivo);
					System.out.println("hrdkapi----- api result:" + resultList.getResult());
					System.out.println("hrdkapi----- api msg:" + resultList.getMessage());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * HRDK API : 회원로그인 정보
	 * @throws Exception
	 */
	@Scheduled(cron="0 0 0/1 * * *")
	public void hrdApiUserLogin() throws Exception {
		ProcessResultListVO<HrdApiVO> resultList = new ProcessResultListVO<HrdApiVO>();
		String apiCd = Constants.HRD_API_CD_USER_HIST;
		String uri = Constants.HRD_API_CD_USER_HIST;
		
		String ymdH = this.getYmdH(-1);
		String startDate = ymdH+":00:00";
		String endDate = ymdH+":59:59";
		
		try {
			// hrd api 연동 사용 유무가 'Y' 인 경우만 수행
			if(Constants.HRD_API_USE_YN.equals("Y")) {
				List<EgovMap> egovList = new ArrayList<EgovMap>();
				HrdApiUserLoginVO haulvo = new HrdApiUserLoginVO();
				haulvo.setRegNo("USR000000002");
				haulvo.setModNo("USR000000002");
				haulvo.setSearchKey("syncStatus");
				haulvo.setSearchValue("W");
				haulvo.setSearchFrom(startDate);
				haulvo.setSearchTo(endDate);
				egovList = hrdApiUserLoginService.listUserLoginHrdApiSync(haulvo);
				
				System.out.println("hrdkapi-----apiCd:"+apiCd+", uri:"+uri);
				System.out.println("hrdkapi-----hrdApiUserLogin("+ymdH+")");
				System.out.println("hrdkapi-----startDate:"+startDate+", endDate:"+endDate);
				System.out.println("hrdkapi-----resultList size:"+egovList.size());
				
				if(egovList.size() > 0) {
					resultList = hrdApiUserLoginService.callUserLoginHrdApi(egovList,haulvo);
					System.out.println("hrdkapi----- api result:" + resultList.getResult());
					System.out.println("hrdkapi----- api msg:" + resultList.getMessage());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * HRDK API : 수강정보
	 * @throws Exception
	 */
	@Scheduled(cron="0 0 0/1 * * *")
	public void hrdApiStdInfo() throws Exception {
		ProcessResultListVO<HrdApiVO> resultList = new ProcessResultListVO<HrdApiVO>();
		String apiCd = Constants.HRD_API_CD_USER_HIST;
		String uri = Constants.HRD_API_CD_USER_HIST;
		
		String ymdH = this.getYmdH(-1);
		String startDate = ymdH+":00:00";
		String endDate = ymdH+":59:59";
		
		try {
			// hrd api 연동 사용 유무가 'Y' 인 경우만 수행
			if(Constants.HRD_API_USE_YN.equals("Y")) {
				List<EgovMap> egovList = new ArrayList<EgovMap>();
				HrdApiStdVO hasvo = new HrdApiStdVO();
				hasvo.setRegNo("USR000000002");
				hasvo.setModNo("USR000000002");
				hasvo.setSearchKey("syncStatus");
				hasvo.setSearchValue("W");
				hasvo.setSearchFrom(startDate);
				hasvo.setSearchTo(endDate);
				egovList = hrdApiStdService.listStdInfo(hasvo);
				String jsonString = JsonUtil.getJsonStringHrdApi(egovList);
				
				System.out.println("hrdkapi-----apiCd:"+apiCd+", uri:"+uri);
				System.out.println("hrdkapi-----hrdApiStdInfo("+ymdH+")");
				System.out.println("hrdkapi-----startDate:"+startDate+", endDate:"+endDate);
				System.out.println("hrdkapi-----resultList size:"+egovList.size());
				
				if(egovList.size() > 0) {
					resultList = hrdApiStdService.callStdInfoHrdApi(jsonString, egovList, hasvo);
					System.out.println("hrdkapi----- api result:" + resultList.getResult());
					System.out.println("hrdkapi----- api msg:" + resultList.getMessage());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * HRDK API : 과정정보
	 * @throws Exception
	 */
	@Scheduled(cron="0 0 0/1 * * *")
	public void hrdApiCrsOnlnSbj() throws Exception {
		ProcessResultListVO<HrdApiVO> resultList = new ProcessResultListVO<HrdApiVO>();
		String apiCd = Constants.HRD_API_CD_USER_HIST;
		String uri = Constants.HRD_API_CD_USER_HIST;
		
		String ymdH = this.getYmdH(-1);
		String startDate = ymdH+":00:00";
		String endDate = ymdH+":59:59";
		
		try {
			// hrd api 연동 사용 유무가 'Y' 인 경우만 수행
			if(Constants.HRD_API_USE_YN.equals("Y")) {
				List<EgovMap> egovList = new ArrayList<EgovMap>();
				HrdApiOnlnSbjVO haosvo = new HrdApiOnlnSbjVO();
				haosvo.setRegNo("USR000000002");
				haosvo.setModNo("USR000000002");
				haosvo.setSearchKey("syncStatus");
				haosvo.setSearchValue("W");
				haosvo.setSearchFrom(startDate);
				haosvo.setSearchTo(endDate);
				egovList = hrdApiOnlnSbjService.getListForSync(haosvo);
				
				System.out.println("hrdkapi-----apiCd:"+apiCd+", uri:"+uri);
				System.out.println("hrdkapi-----hrdApiCrsOnlnSbj("+ymdH+")");
				System.out.println("hrdkapi-----startDate:"+startDate+", endDate:"+endDate);
				System.out.println("hrdkapi-----resultList size:"+egovList.size());
				
				if(egovList.size() > 0) {
					resultList = hrdApiOnlnSbjService.callCourseInfoHrdApi(egovList,haosvo);
					System.out.println("hrdkapi----- api result:" + resultList.getResult());
					System.out.println("hrdkapi----- api msg:" + resultList.getMessage());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * HRDK API : 수업 정보
	 * @throws Exception
	 */
	@Scheduled(cron="0 0 0/1 * * *")
	public void hrdApiCreInfo() throws Exception {
		ProcessResultListVO<HrdApiVO> resultList = new ProcessResultListVO<HrdApiVO>();
		String apiCd = Constants.HRD_API_CD_USER_HIST;
		String uri = Constants.HRD_API_CD_USER_HIST;
		
		String ymdH = this.getYmdH(-1);
		String startDate = ymdH+":00:00";
		String endDate = ymdH+":59:59";
		
		try {
			// hrd api 연동 사용 유무가 'Y' 인 경우만 수행
			if(Constants.HRD_API_USE_YN.equals("Y")) {
				List<EgovMap> egovList = new ArrayList<EgovMap>();
				HrdApiCreVO hasvo = new HrdApiCreVO();
				hasvo.setRegNo("USR000000002");
				hasvo.setModNo("USR000000002");
				hasvo.setSearchKey("syncStatus");
				hasvo.setSearchValue("W");
				hasvo.setSearchFrom(startDate);
				hasvo.setSearchTo(endDate);
				egovList =hrdApiCreService.listCreInfoApi(hasvo);
				String jsonString = JsonUtil.getJsonStringHrdApi(egovList);
				
				System.out.println("hrdkapi-----apiCd:"+apiCd+", uri:"+uri);
				System.out.println("hrdkapi-----hrdApiCreInfo("+ymdH+")");
				System.out.println("hrdkapi-----startDate:"+startDate+", endDate:"+endDate);
				System.out.println("hrdkapi-----resultList size:"+egovList.size());
				
				if(egovList.size() > 0) {
					hasvo.setCrsCd((String)egovList.get(0).get("crsCd"));
					resultList = hrdApiCreService.callCreInfoHrdApi(jsonString, egovList, hasvo);
					System.out.println("hrdkapi----- api result:" + resultList.getResult());
					System.out.println("hrdkapi----- api msg:" + resultList.getMessage());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * HRDK API : 성적이력 정보
	 * @throws Exception
	 */
	@Scheduled(cron="0 0 0/1 * * *")
	public void hrdApiScore() throws Exception {
		ProcessResultListVO<HrdApiVO> resultList = new ProcessResultListVO<HrdApiVO>();
		String apiCd = Constants.HRD_API_CD_USER_HIST;
		String uri = Constants.HRD_API_CD_USER_HIST;
		
		String ymdH = this.getYmdH(-1);
		String startDate = ymdH+":00:00";
		String endDate = ymdH+":59:59";
		
		try {
			// hrd api 연동 사용 유무가 'Y' 인 경우만 수행
			if(Constants.HRD_API_USE_YN.equals("Y")) {
				List<EgovMap> egovList = new ArrayList<EgovMap>();
				HrdApiScoreVO hasvo = new HrdApiScoreVO();
				hasvo.setRegNo("USR000000002");
				hasvo.setModNo("USR000000002");
				hasvo.setSearchKey("syncStatus");
				hasvo.setSearchValue("W");
				hasvo.setSearchFrom(startDate);
				hasvo.setSearchTo(endDate);
				egovList = hrdApiScoreService.listForApi(hasvo);
				
				System.out.println("hrdkapi-----apiCd:"+apiCd+", uri:"+uri);
				System.out.println("hrdkapi-----hrdApiScore("+ymdH+")");
				System.out.println("hrdkapi-----startDate:"+startDate+", endDate:"+endDate);
				System.out.println("hrdkapi-----resultList size:"+egovList.size());
				
				if(egovList.size() > 0) {
					resultList = hrdApiScoreService.callApi(egovList, hasvo);
					System.out.println("hrdkapi----- api result:" + resultList.getResult());
					System.out.println("hrdkapi----- api msg:" + resultList.getMessage());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * HRDK API : 년월일시 추출
	 * @param amount
	 * @return
	 * @throws Exception
	 */
	private String getYmdH(int amount) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.HOUR, amount);
		return sdf.format(cal.getTime());
	}
}
