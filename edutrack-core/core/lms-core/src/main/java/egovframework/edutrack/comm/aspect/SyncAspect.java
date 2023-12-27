package egovframework.edutrack.comm.aspect;

import static egovframework.edutrack.comm.annotation.HrdApiCrsOnlnSbj.SyncType.DELETE;
import static egovframework.edutrack.comm.annotation.HrdApiStdStd.SyncType.UPDATE;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import egovframework.edutrack.comm.annotation.HrdApiCrsCreCrs;
import egovframework.edutrack.comm.annotation.HrdApiCrsCreCrs.CreSyncType;
import egovframework.edutrack.comm.annotation.HrdApiCrsOnlnSbj;
import egovframework.edutrack.comm.annotation.HrdApiScore;
import egovframework.edutrack.comm.annotation.HrdApiStdStd;
import egovframework.edutrack.comm.annotation.HrdApiUsrUserInfo;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.security.KISASeed;
import egovframework.edutrack.comm.util.web.DateTimeUtil;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.course.course.service.CourseVO;
import egovframework.edutrack.modules.course.createcourse.service.CreateCourseVO;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiCreVO;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiOnlnSbjVO;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiScoreVO;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiStdVO;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiUserInfoVO;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiUserLoginVO;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiVO;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdCourseSendable;
import egovframework.edutrack.modules.etc.hrdapi.service.HrdStdSearchable;
import egovframework.edutrack.modules.etc.hrdapi.service.impl.HrdApiCreMapper;
import egovframework.edutrack.modules.etc.hrdapi.service.impl.HrdApiOnlnSbjMapper;
import egovframework.edutrack.modules.etc.hrdapi.service.impl.HrdApiScoreMapper;
import egovframework.edutrack.modules.etc.hrdapi.service.impl.HrdApiStdMapper;
import egovframework.edutrack.modules.etc.hrdapi.service.impl.HrdApiUserInfoMapper;
import egovframework.edutrack.modules.etc.hrdapi.service.impl.HrdApiUserLoginMapper;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentSendVO;
import egovframework.edutrack.modules.lecture.bookmark.service.BookmarkVO;
import egovframework.edutrack.modules.lecture.exam.service.ExamStareVO;
import egovframework.edutrack.modules.lecture.exam.service.impl.ExamMapper;
import egovframework.edutrack.modules.log.apisync.service.LogApiSyncVO;
import egovframework.edutrack.modules.log.apisync.service.impl.LogApiSyncMapper;
import egovframework.edutrack.modules.log.logintry.service.LogUserLoginTryLogVO;
import egovframework.edutrack.modules.log.logintry.service.impl.LogUserLoginTryLogMapper;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoVO;
import egovframework.edutrack.modules.org.info.service.impl.OrgOrgInfoMapper;
import egovframework.edutrack.modules.student.result.service.EduResultVO;
import egovframework.edutrack.modules.student.result.service.impl.EduResultMapper;
import egovframework.edutrack.modules.student.student.service.StudentVO;
import egovframework.edutrack.modules.student.student.service.impl.StudentMapper;
import egovframework.edutrack.modules.user.info.service.UsrLoginVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;
import egovframework.edutrack.modules.user.info.service.impl.UsrLoginMapper;
import egovframework.edutrack.modules.user.info.service.impl.UsrUserInfoMapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Aspect
@Component
public class SyncAspect {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	/** Mapper */
    @Resource(name="logApiSyncMapper")
    private LogApiSyncMapper 		logApiSyncMapper;
    
    @Resource(name="hrdApiScoreMapper")
    private HrdApiScoreMapper hrdApiScoreMapper;
    
    @Resource(name="examMapper")
    private ExamMapper examMapper;
    
    @Resource(name="hrdApiCreMapper")
    private HrdApiCreMapper 		hrdApiCreMapper;
    
    @Resource(name="hrdApiStdMapper")
    private HrdApiStdMapper 		hrdApiStdMapper;
    
    @Resource(name="hrdApiOnlnSbjMapper")
    private HrdApiOnlnSbjMapper 		hrdApiOnlnSbjMapper;
    
	@Resource(name="studentMapper")
	private StudentMapper studentMapper;
	
	@Resource(name="eduResultMapper")
	private EduResultMapper eduResultMapper;
	
    @Resource(name="usrUserInfoMapper")
	private UsrUserInfoMapper 			usrUserInfoMapper;
    
    @Resource(name="usrLoginMapper")
    private UsrLoginMapper 			usrLoginMapper;
	
	@Resource(name="hrdApiUserInfoMapper")
	private HrdApiUserInfoMapper 		hrdApiUserInfoMapper;
	
	@Resource(name="logUserLoginTryLogMapper")
    private LogUserLoginTryLogMapper 		logUserLoginTryLogMapper;
	
	@Resource(name="hrdApiUserLoginMapper")
	private HrdApiUserLoginMapper 		hrdApiUserLoginMapper;
	
	@Resource(name="orgOrgInfoMapper")
	private OrgOrgInfoMapper 		orgOrgInfoMapper;
	
	 
	/** 산업인력공단 API 사용여부 확인 **/
	 public String getHrdApiUseYn() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String orgCd = UserBroker.getUserOrgCd(request);	// 기관 코드 조회
		String useYn = "N";
		try {
			useYn = orgOrgInfoMapper.selectHrdApiUseYn(orgCd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return useYn;
	}
	
    @Around("execution(* egovframework.edutrack.modules.etc.hrdapi.service.HrdApiRestTemplate.*(..))")
	public Object proceedingApi(ProceedingJoinPoint pjp) throws Throwable {
    	Object result = null;
    	if("Y".equals(getHrdApiUseYn())) {
    	
    		System.out.println("LOG::::::::::::::::Start");
    		long start = System.currentTimeMillis();
		
    		LogApiSyncVO lasvo = new LogApiSyncVO();
		
    		Object[] params = pjp.getArgs();
    		for(Object param : params) {
    			if(param instanceof LogApiSyncVO) {
    				lasvo = (LogApiSyncVO) param;
    				break;
    			}
    		}
		
    		int syncFailCnt = Integer.parseInt(StringUtil.nvl(lasvo.getSyncFailCnt(),"0"));
    		int syncSuccessCnt = Integer.parseInt(StringUtil.nvl(lasvo.getSyncSuccessCnt(),"0"));
    		String failPk = StringUtil.nvl(lasvo.getSyncFailPk());
    		String siteUrl = StringUtil.nvl(lasvo.getSiteUrl());
    		String crsCd = StringUtil.nvl(lasvo.getCrsCd());
    		String syncGubunCd = StringUtil.nvl(lasvo.getSyncGubunCd());
    		String syncResultMsg = "";
    		System.out.println("LOG::::::::::::::::inja");
		
		
    		HrdApiVO responVo = new HrdApiVO();
    		try {			
    			result = pjp.proceed(pjp.getArgs());
    			ResponseEntity<HrdApiVO> responseEntity = (ResponseEntity<HrdApiVO>) result;
    			responVo = responseEntity.getBody();
    			System.out.println("LOG::::::::::::::::responVo");
    		} catch (Exception e) {
    			log.error(e.getMessage());
    			e.printStackTrace();
    			syncFailCnt = syncSuccessCnt;
    			syncSuccessCnt = 0;
    			syncResultMsg = e.getMessage();
    			throw e;
    		} finally {
    			long finish = System.currentTimeMillis();
    			System.out.println("LOG::::::::::::::::finish time");
    			String syncSuccessYn = "";
    			if("SUCCESS".equals(responVo.getStatus())) {
    				syncSuccessYn = "Y";
    			}else {
    				syncSuccessYn = "N";
    				syncFailCnt = syncSuccessCnt;
    				syncSuccessCnt = 0;
    			}
			
    			syncResultMsg = StringUtil.nvl(responVo.getMsg());
			
    			LogApiSyncVO lasVo = new LogApiSyncVO();
    			lasVo.setCrsCd(crsCd);
    			lasVo.setSyncGubunCd(syncGubunCd);
				lasVo.setSiteUrl(siteUrl);
				lasVo.setSyncTime((int)(finish - start));
				lasVo.setSyncResultMsg(syncResultMsg);
				lasVo.setSyncSuccessYn(syncSuccessYn);
				lasVo.setSyncSuccessCnt(syncSuccessCnt);
				lasVo.setSyncFailCnt(syncFailCnt);
				lasVo.setSyncFailPk(failPk);
				System.out.println("LOG::::::::::::::::insert start");
				logApiSyncMapper.insertApiSync(lasVo);
				System.out.println("LOG::::::::::::::::insert end");
				System.out.println("LOG::::::::::::::::End");
			}
		}
    	return result;
    }
    
    /** 회원 정보 api 시작 **/
    @AfterReturning("@annotation(egovframework.edutrack.comm.annotation.HrdApiUsrUserInfo)")
   	public void insertUsrUserInfoApi(JoinPoint jp) throws Throwable {
    	if("Y".equals(getHrdApiUseYn())) {
    		try {	
    	   		UsrUserInfoVO uuivo = new UsrUserInfoVO();
    			Object[] params = jp.getArgs();
    			for(Object param : params) {
    				if(param instanceof UsrUserInfoVO) {
    					uuivo = (UsrUserInfoVO) param;
    					break;
    				}
    			}
    			
    			MethodSignature signature = (MethodSignature) jp.getSignature();
    		    Method method = signature.getMethod();
    		    HrdApiUsrUserInfo annotation = method.getAnnotation(HrdApiUsrUserInfo.class);
    		    String type = annotation.value().getStringValue();
    	   		String regNo = getUserNoFromSession();
       			
    	   		if(!"".equals(StringUtil.nvl(uuivo.getUserNo()))) {
    	   			uuivo = usrUserInfoMapper.select(uuivo);
    	   			if(uuivo != null) {
    	   				HrdApiUserInfoVO hauiVo = new HrdApiUserInfoVO();
    	   				hauiVo.setAgentPk("edulife");
    	   				hauiVo.setSeq(1);
    	   				hauiVo.setUserAgentPk(uuivo.getUserNo());
    	   				hauiVo.setUserName(uuivo.getUserNm());
    	   				if(!"".equals(StringUtil.nvl(KISASeed.seedDecryption(uuivo.getJuminNo()))) && StringUtil.nvl(KISASeed.seedDecryption(uuivo.getJuminNo())).length() > 10) {
    	   					hauiVo.setResNo(KISASeed.seedDecryption(uuivo.getJuminNo()));
    	   				}else {
    	   					if(StringUtil.nvl(uuivo.getBirth()).length() > 7) {
    	   						hauiVo.setResNo(uuivo.getBirth().substring(2, 8));
    	   					}else if(StringUtil.nvl(uuivo.getBirth()).length() < 7 && StringUtil.nvl(uuivo.getBirth()).length() < 5) {
    	   						hauiVo.setResNo(uuivo.getBirth());
    	   					}else {
    	   						hauiVo.setResNo("20000101");
    	   					}
    	   					String resNo = hauiVo.getResNo();
    	   					if("F".equals(StringUtil.nvl(uuivo.getSexCd()))) {
    	   						if("2".equals(hauiVo.getResNo().substring(0, 1))) {
    	   							hauiVo.setResNo(resNo+"4");
    	   						}else {
    	   							hauiVo.setResNo(resNo+"2");
    	   						}
    	   					}else if("M".equals(StringUtil.nvl(uuivo.getSexCd()))) {
    	   						if("2".equals(hauiVo.getResNo().substring(0, 1))) {
    	   							hauiVo.setResNo(resNo+"3");
    	   						}else {
    	   							hauiVo.setResNo(resNo+"1");
    	   						}
    	   					}else {
    	   						hauiVo.setResNo(resNo+"3");
    	   					}
    	   				}
    	   				hauiVo.setEncResNo("메디오피아테크 HRD-NET");
    	   				hauiVo.setEmail(uuivo.getEmail());
    	   				String mobileNo = "";
    	   				if(!"".equals(StringUtil.nvl(uuivo.getMobileNo()))) {
    	   					mobileNo = uuivo.getMobileNo().replace("-", "");
    	   					if(mobileNo.length() == 11) {
    	   						hauiVo.setMobile(mobileNo.substring(0, 3)+"-"+mobileNo.substring(3, 7)+"-"+mobileNo.substring(7, 11));
    	   					}else if(mobileNo.length() == 10) {
    	   						hauiVo.setMobile(mobileNo.substring(0, 2)+"-"+mobileNo.substring(2, 6)+"-"+mobileNo.substring(6, 10));
    	   					}else {
    	   						hauiVo.setMobile("010-1234-5678");
    	   					}
    	   				}else {
    	   					hauiVo.setMobile("-");
    	   				}
    	   				hauiVo.setChangeState(type);
    	   				if(!"".equals(StringUtil.nvl(uuivo.getCostCompNo()))) {
    	   					if(StringUtil.nvl(uuivo.getCostCompNo()).length() == 11) {
    	   						hauiVo.setNwIno(uuivo.getCostCompNo());
    	   					}
    	   				}
    	   				hauiVo.setTrneeSe("007");
    	   				hauiVo.setIrglbrSe("000");
    	   				hauiVo.setSyncStatus("W");
    	   				hauiVo.setSyncResultMsg("");
    	   				hauiVo.setRegNo(regNo);
    	   				hauiVo.setModNo(regNo);
    	   				hrdApiUserInfoMapper.insertUserInfo(hauiVo);
    	   			}
    	   		}
       		} catch (Exception e) {
       			log.error(e.getMessage());
       			e.printStackTrace();
       			throw e;
       		}
    	}
   	}
    
    @AfterReturning(pointcut = "execution(* egovframework.edutrack.modules.user.info.service.impl.UsrUserInfoServiceImpl.addBatch(..)) ", returning = "resultVO")
   	public void insertUsrUserInfoApi(JoinPoint jp, ProcessResultListVO<?> resultVO) throws Throwable {
    	if("Y".equals(getHrdApiUseYn())) {
	    	try {	
				List<UsrUserInfoVO> userList = new ArrayList<UsrUserInfoVO>();
				if(resultVO!=null) {
					Object returnListVO = resultVO.getReturnList();
					if(returnListVO instanceof List) {
						if(((List)returnListVO).size()>0 && (((List)returnListVO).get(0) instanceof UsrUserInfoVO)){
							userList = (List<UsrUserInfoVO>)returnListVO;
					    }
					}
				}
				
			    String type = "C";
		   		String regNo = getUserNoFromSession();
		   		
		   		if(userList.size() > 0 ) {
		   			for(int i=0; i<userList.size(); i++){
		   				UsrUserInfoVO suuivo = userList.get(i);
		   				suuivo = usrUserInfoMapper.select(suuivo);
			   			if(suuivo != null) {
			   				HrdApiUserInfoVO shauiVo = new HrdApiUserInfoVO();
			   				shauiVo.setAgentPk("edulife");
			   				shauiVo.setSeq(i+1);
			   				shauiVo.setUserAgentPk(suuivo.getUserNo());
			   				shauiVo.setUserName(suuivo.getUserNm());
			   				
			   				if(!"".equals(StringUtil.nvl(KISASeed.seedDecryption(suuivo.getJuminNo()))) && StringUtil.nvl(KISASeed.seedDecryption(suuivo.getJuminNo())).length() > 10) {
			   					shauiVo.setResNo(KISASeed.seedDecryption(suuivo.getJuminNo()));
			   				}else {
			   					if(StringUtil.nvl(suuivo.getBirth()).length() > 7) {
			   						shauiVo.setResNo(suuivo.getBirth());
			   					}else if(StringUtil.nvl(suuivo.getBirth()).length() < 7 && StringUtil.nvl(suuivo.getBirth()).length() < 5) {
			   						shauiVo.setResNo(suuivo.getBirth());
			   					}else {
			   						shauiVo.setResNo("20000101");
			   					}
			   					String resNo = shauiVo.getResNo();
			   					if("F".equals(StringUtil.nvl(suuivo.getSexCd()))) {
			   						if("2".equals(shauiVo.getResNo().substring(0, 1))) {
			   							shauiVo.setResNo(resNo+"4");
			   						}else {
			   							shauiVo.setResNo(resNo+"2");
			   						}
			   					}else if("M".equals(StringUtil.nvl(suuivo.getSexCd()))) {
			   						if("2".equals(shauiVo.getResNo().substring(0, 1))) {
			   							shauiVo.setResNo(resNo+"3");
			   						}else {
			   							shauiVo.setResNo(resNo+"1");
			   						}
			   					}else {
			   						shauiVo.setResNo(resNo+"3");
			   					}
			   				}
			   				shauiVo.setEncResNo("메디오피아테크 HRD-NET");
			   				shauiVo.setEmail(suuivo.getEmail());
			   				String mobileNo = "";
			   				if(!"".equals(StringUtil.nvl(suuivo.getMobileNo()))) {
			   					mobileNo = suuivo.getMobileNo().replace("-", "");
			   					if(mobileNo.length() == 11) {
			   						shauiVo.setMobile(mobileNo.substring(0, 3)+"-"+mobileNo.substring(3, 7)+"-"+mobileNo.substring(7, 11));
			   					}else if(mobileNo.length() == 10) {
			   						shauiVo.setMobile(mobileNo.substring(0, 2)+"-"+mobileNo.substring(2, 6)+"-"+mobileNo.substring(6, 10));
			   					}else {
			   						shauiVo.setMobile("010-1234-5678");
			   					}
			   				}else {
			   					shauiVo.setMobile("010-1234-5678");
			   				}
			   				shauiVo.setChangeState(type);
			   				if(!"".equals(StringUtil.nvl(suuivo.getCostCompNo()))) {
			   					if(StringUtil.nvl(suuivo.getCostCompNo()).length() == 11) {
			   						shauiVo.setNwIno(suuivo.getCostCompNo());
			   					}
			   				}
			   				shauiVo.setTrneeSe("007");
			   				shauiVo.setIrglbrSe("000");
			   				shauiVo.setSyncStatus("W");
			   				shauiVo.setSyncResultMsg("");
			   				shauiVo.setRegNo(regNo);
			   				shauiVo.setModNo(regNo);
			   				hrdApiUserInfoMapper.insertUserInfo(shauiVo);
			   			}
		   			}
		   		}
	   		} catch (Exception e) {
	   			log.error(e.getMessage());
	   			e.printStackTrace();
	   			throw e;
	   		}
    	}
   	}
    
    
    /** 회원 정보 api 끝 **/
    
    /** 회원 로그인 정보 api 시작 **/
    @AfterReturning("@annotation(egovframework.edutrack.comm.annotation.HrdApiUsrLogin)")
   	public void insertUsrLoginApi(JoinPoint jp) throws Throwable {
    	if("Y".equals(getHrdApiUseYn())) {
	    	try {	
		   		UsrLoginVO ulvo = new UsrLoginVO();
		   		LogUserLoginTryLogVO lultlvo = new LogUserLoginTryLogVO();
				Object[] params = jp.getArgs();
				for(Object param : params) {
					if(param instanceof UsrLoginVO) {
						ulvo = (UsrLoginVO) param;
						break;
					}
					if(param instanceof LogUserLoginTryLogVO) {
						lultlvo = (LogUserLoginTryLogVO) param;
						break;
					}
				}
		   		String regNo = getUserNoFromSession();
		   		
		   		if(!"".equals(StringUtil.nvl(ulvo.getUserNo()))||!"".equals(StringUtil.nvl(lultlvo.getUserNo()))) {
		   			String userNo = StringUtil.nvl(ulvo.getUserNo(),lultlvo.getUserNo());
		   			ulvo.setUserNo(userNo);
		   			ulvo = usrLoginMapper.select(ulvo);
		   			lultlvo.setUserId(ulvo.getUserId());
		   			List<EgovMap> resultlultlList = logUserLoginTryLogMapper.selectUserLoginApi(lultlvo);
		   			if(ulvo != null && resultlultlList != null && resultlultlList.size() > 0) {
		   				EgovMap logMap = resultlultlList.get(0);
		   				String loginDate = StringUtil.nvl(logMap.get("loginDate"));
		   				String loginIp = StringUtil.nvl(logMap.get("loginIp"));
		   				if(!"".equals(loginDate)) {
		   					HrdApiUserLoginVO haulVo = new HrdApiUserLoginVO();
		   					haulVo.setAgentPk("edulife");
		   					haulVo.setSeq(1);
		   					haulVo.setUserAgentPk(ulvo.getUserNo());
		   					haulVo.setLoginDate(loginDate);
		   					haulVo.setLoginIp(loginIp);
		   					haulVo.setSyncStatus("W");
		   					haulVo.setSyncResultMsg("");
		   					haulVo.setRegNo(regNo);
		   					haulVo.setModNo(regNo);
		   					hrdApiUserLoginMapper.insertUserLogin(haulVo);
		   				}
		   			}
		   		}
	   		} catch (Exception e) {
	   			log.error(e.getMessage());
	   			e.printStackTrace();
	   			throw e;
	   		}
    	}
   	}
    
    
    /** 회원 로그인 정보 api 끝 **/
    
    /** 과정정보  api 시작 **/
	@AfterReturning("@annotation(annotation)")
	public void insertCourserInfoApi(JoinPoint jp, HrdApiCrsOnlnSbj annotation) {
		if("Y".equals(getHrdApiUseYn())) {
			try {
				HrdCourseSendable target = Arrays.stream(jp.getArgs())
				    						.filter(HrdCourseSendable.class::isInstance)
				    						.map(HrdCourseSendable.class::cast)
											.findAny()
											.orElseThrow(() -> new IllegalArgumentException("타입이 일치하는 인자가 없습니다. : HrdCourseSendable"));
				
				HrdApiCrsOnlnSbj.SyncType syncType = annotation.value();
	
			    HrdApiOnlnSbjVO hrdApiOnlnSbj = new HrdApiOnlnSbjVO(target, syncType);
			    validateHrdOnlnSbjVO(hrdApiOnlnSbj);
			    
			    if(DELETE.equals(syncType)) {
			    	hrdApiOnlnSbj = Optional.ofNullable(hrdApiOnlnSbjMapper.selectRecentOne(hrdApiOnlnSbj))
			    			.orElseThrow(() -> new IllegalArgumentException("해당하는 API 연계 내역이 존재하지 않습니다. pk: " + target.callHrdCouresAgentPk()));
			    }
			    
			    String userNo = getUserNoFromSession();
			    hrdApiOnlnSbj.setRegNo(userNo);
			    hrdApiOnlnSbj.setModNo(userNo);
			    
			    hrdApiOnlnSbjMapper.insert(hrdApiOnlnSbj);
			    
		    } catch (Exception e) {
		    	log.error("과정 정보 내역 API 연동 오류 -> " +  e.getMessage());
		    }
		}
	}
	private void validateHrdOnlnSbjVO(HrdApiOnlnSbjVO vo) {
		String courseAgentPk = vo.getCourseAgentPk();
		String simsaCode = vo.getSimsaCode();
		String tracseId = vo.getTracseId();
		
		if(ValidationUtils.isEmpty(courseAgentPk)) throw new IllegalArgumentException("courseAgentPk empty");
		if(ValidationUtils.isEmpty(simsaCode) || simsaCode.length() != 20) vo.setSimsaCode("I202201010000L000000"); // 예외 발생? 기본값 세팅?
		if(ValidationUtils.isEmpty(tracseId) || tracseId.length() != 17) vo.setTracseId("ABA20220000111111"); // 예외 발생? 기본값 세팅?
	}
    /** 과정정보  api 끝 **/
    
    /** 수업정보  api 시작 **/
	@AfterReturning("@annotation(egovframework.edutrack.comm.annotation.HrdApiCrsCreCrs)")
	public void insertCreInfoApi(JoinPoint jp) {
		if("Y".equals(getHrdApiUseYn())) {
			try { 
				MethodSignature signature = (MethodSignature) jp.getSignature();
			    Method method = signature.getMethod();
			    HrdApiCrsCreCrs annotation = method.getAnnotation(HrdApiCrsCreCrs.class);
			    CreSyncType creSyncType = annotation.value();
			    Object[] params = jp.getArgs();
			    HrdApiCreVO hacvo = new HrdApiCreVO();
			    
			    if(creSyncType.name().equals("CRSUPDATE")) {	//기수 수정할 때
			    	CourseVO courseVO = new CourseVO();
					for(Object param : params) {
						if(param instanceof CourseVO) {
							courseVO = (CourseVO) param;
							break;
						}
					}
					hacvo.setCrsCd(courseVO.getCrsCd());
			    }else {	
			    	CreateCourseVO createCourseVO = new CreateCourseVO();
					for(Object param : params) {
						if(param instanceof CreateCourseVO) {
							createCourseVO = (CreateCourseVO) param;
							break;
						}
					}
					hacvo.setCrsCd(createCourseVO.getCrsCd());
			   		hacvo.setCrsCreCd(createCourseVO.getCrsCreCd());
			    }
		   		
			    String userNo = getUserNoFromSession();
			   
			    if(creSyncType.name().equals("DELETE")) {
			    	HrdApiCreVO cvo = new HrdApiCreVO();
			    	hacvo.setClassAgentPk(hacvo.getCrsCreCd());
			    	cvo = hrdApiCreMapper.selectRecentOne(hacvo);
			    	if(cvo == null) throw new Exception("해당하는 API 연계 내역이 존재하지 않습니다. pk: " + hacvo.getClassAgentPk());
			    	cvo.setChangeState(creSyncType.getStringValue());
			    	cvo.setRegNo(userNo);
			    	cvo.setModNo(userNo);
			    	hrdApiCreMapper.insertHrdApiCre(cvo); 
			    }else {
			    	 List<HrdApiCreVO> returnList = hrdApiCreMapper.listCreInfoData(hacvo);
			    	 for(int i=0;i<returnList.size();i++) {
					    	HrdApiCreVO vo = new HrdApiCreVO();
					    	vo = returnList.get(i);
					    	vo.setChangeState(creSyncType.getStringValue());
					    	vo.setRegNo(userNo);
					    	vo.setModNo(userNo);
					   		hrdApiCreMapper.insertHrdApiCre(vo); 
					    }
			    }
			    
			} catch (Exception e) {
			   	log.error("수업 정보 내역 API 연동 오류 " +  e.getMessage());
			}
		}
	}
    /** 수업정보  api 끝 **/
    
    /** 수강정보  api 시작 **/
	@AfterReturning("@annotation(annotation)")
	public void insertStdInfoApi(JoinPoint jp, HrdApiStdStd annotation) {
		if("Y".equals(getHrdApiUseYn())) {
			try {
			HrdStdSearchable targetParam = Arrays.stream(jp.getArgs())
											.filter(HrdStdSearchable.class::isInstance)
											.map(HrdStdSearchable.class::cast)
											.findAny()
											.orElseThrow(() -> new IllegalArgumentException("타입이 일치하는 인자가 없습니다. : HrdStdSearchable"));
			
			HrdApiStdStd.SyncType syncType = annotation.value();
			
			StudentVO target = Optional.ofNullable(studentMapper.selectStudentInfoCre(new StudentVO(targetParam)))
									.orElseThrow(() -> new IllegalArgumentException("해당하는 수강생이 존재하지 않습니다. pk: " + targetParam.callHrdStdNo()));
			
			HrdApiStdVO hrdApiStd = new HrdApiStdVO(target, syncType);
			hrdApiStd.setSearchInfoFromStudent(target);
			
			if(UPDATE.equals(syncType)) {
				EduResultVO eduResult = Optional.ofNullable(eduResultMapper.selectResult(new EduResultVO(targetParam)))
											.orElse(new EduResultVO());
				hrdApiStd.updateScore(eduResult);
			}
			
		    String userNo = getUserNoFromSession();
		    hrdApiStd.setRegNo(userNo);
		    hrdApiStd.setModNo(userNo);
			
			hrdApiStdMapper.insertHrdApiStd(hrdApiStd);
			
			} catch (Exception e) {
				log.error("수강 정보 내역 API 연동 오류 -> " +  e.getMessage());
			}
		}
	}
    /** 수강정보  api 끝 **/
    
    /** 성적이력  api 시작 **/
    /**
     * 시험, 과제, 단계 평가 : 응시 시작, 응시 종료, 채점
     * 진도 : 수강시작, 수강종료
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("@annotation(egovframework.edutrack.comm.annotation.HrdApiScore)")
    public Object proceedingApiScoreTest(ProceedingJoinPoint pjp) throws Throwable {
    	Object result = null;
    	if("Y".equals(getHrdApiUseYn())) {
	    	MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
	    	HrdApiScore custom = methodSignature.getMethod().getAnnotation(HrdApiScore.class);
	    	methodSignature.getMethod().getReturnType();
	    	result = pjp.proceed(pjp.getArgs());
	    	
	    	ExamStareVO examStareVO = new ExamStareVO();
	    	AssignmentSendVO assignmentSendVO = new AssignmentSendVO();
	    	BookmarkVO bookmarkVO = new BookmarkVO();
	    	
	    	Object[] params = pjp.getArgs();
	    	String stdNo = "";
	    	String[] stdNoList = null;
	    	String category = "";
	    	String saveType = "";
	    	
			for(Object param : params) {
				if(param instanceof ExamStareVO) {
					examStareVO = (ExamStareVO) param;
					stdNo = examStareVO.getStdNo();
					category = StringUtil.nvl(examStareVO.getScoreCategory());
					saveType = StringUtil.nvl(examStareVO.getScoreSaveType());
					break;
				}
				if(param instanceof AssignmentSendVO) {
					assignmentSendVO = (AssignmentSendVO) param;
					stdNo = assignmentSendVO.getStdNo();
					category = StringUtil.nvl(assignmentSendVO.getScoreCategory());
					saveType = StringUtil.nvl(assignmentSendVO.getScoreSaveType());
					break;
				}
				if(param instanceof BookmarkVO) {
					bookmarkVO = (BookmarkVO) param;
					stdNo = bookmarkVO.getStdNo();
					category = StringUtil.nvl(bookmarkVO.getScoreCategory());
					saveType = StringUtil.nvl(bookmarkVO.getScoreSaveType());
					break;
				}
			}
	    	
	    	//STD_RESULT 테이블에 데이터가 없으면 INSERT
	    	EduResultVO eduResultVO = new EduResultVO(); 
	    	eduResultVO.setStdNo(stdNo);
	    	EduResultVO resultEduResultVO = eduResultMapper.selectResult(eduResultVO);
	    	if(resultEduResultVO == null) {
	    		eduResultVO.setRegNo("AUTO");
	    		eduResultMapper.insertResult(eduResultVO);
	    	}
	    	
	    	if("EXAM".equals(category)) {
	    		if(!"T".equals(StringUtil.nvl(examStareVO.getStartFlagYn()))) {//임지저장일 경우에는 성적 api 쌓지 않음 / 시험시작, 제출, 평가만  성적 api 쌓기
	    			hrdApiScoreExam(examStareVO, saveType);
	    		}
	    	}else if("ASMT".equals(category)) {
	    		hrdApiScoreAsmt(assignmentSendVO, saveType);
	    	}else if("BOOKMARK".equals(category)) {
	    		hrdApiScoreBookmark(bookmarkVO, saveType);
	    	}
    	}
		return result;
    	
    }
    
    /**
     * 시험,진행단계평가 성적 이력 : 응시 시작, 종료, 채점 구분
     * @param vo
     * @param saveType
     */
    private void hrdApiScoreExam(ExamStareVO vo, String saveType) {
    	//파라미터 필수 값 : STD_NO, EXAM_SN, CRS_CRE_CD, START_FLAG_YN
    	String paramStartFlagYn = StringUtil.nvl(vo.getStartFlagYn(),"N");
    	int paramExamSn = vo.getExamSn();
    	String crsCreCd = StringUtil.nvl(vo.getCrsCreCd());

    	HrdApiScoreVO hrdApiScoreVO = new HrdApiScoreVO();
    	
    	if(saveType.equals("RESET")) {//Reset은 이미 삭제한 데이터이므로, api 데이터 중 최신 데이터를 조회해온다.
    		//오프라인 시험 체크 필요
    		String evalCd = "02";//시험
    		if("Y".equals(vo.getSemiExamYn())){
    			evalCd = "04";//진행단계평가
    		}
    		
    		hrdApiScoreVO.setStartEndFlag("");//혹시 값이 세팅될 수 있기 때문에 초기화
    		hrdApiScoreVO.setStdNo(vo.getStdNo());
    		hrdApiScoreVO.setEvalCd(evalCd);
    		hrdApiScoreVO.setEvalTypeCd(String.valueOf(vo.getExamSn()));
    		
    		HrdApiScoreVO resultHrdApiScoreVO = hrdApiScoreMapper.selectScoreOneRecent(hrdApiScoreVO);
    		
    		if(resultHrdApiScoreVO == null) {
        		log.error("[성적api저장 미 조회 오류(시험)] " + saveType + " param = " + vo.getStdNo() + ", " + vo.getExamSn() + ", " + vo.getCrsCreCd() );
        	}
    		
    		resultHrdApiScoreVO.setChangeState("D");
    		resultHrdApiScoreVO.setLmsFlag("D");
    		resultHrdApiScoreVO.setRegNo(vo.getRegNo());
    		resultHrdApiScoreVO.setSyncStatus("W");
    		resultHrdApiScoreVO.setScore(0);//점수 0
    		
    		hrdApiScoreMapper.insert(resultHrdApiScoreVO);
    		
    	}else {
    		//수강생의 시험 성적, crs, cre 등 정보 조회
        	hrdApiScoreVO = hrdApiScoreMapper.selectScoreExam(vo);
        	
        	if(hrdApiScoreVO == null) {
        		log.error("[성적api저장 미 조회 오류(시험)] " + saveType + " param = " + vo.getStdNo() + ", " + vo.getExamSn() + ", " + vo.getCrsCreCd() );
        	}
        	
        	try {
        		if(hrdApiScoreVO != null) {
        			hrdApiScoreVO.convertScore();
        		}
			} catch (Exception e) {
				log.error(e.getMessage());
			}
        	
        	//기존 로그 테이블에 EXAM_SN에 해당하는 평가방법 값이 있으면 해당 값으로 사용, 아니면 시험 갯수 조회 후 세팅
        	String evalType = StringUtil.nvl(hrdApiScoreVO.getEvalType());//시험_00
        	String evalCd = StringUtil.nvl(hrdApiScoreVO.getEvalCd());
        	String evalTypeFirstNm = "";
        	
        	int chasi = hrdApiScoreVO.getChasi();
        	int oldChasi = hrdApiScoreVO.getOldMaxEvalType();
        	
        	if("02".equals(evalCd)) {//시험
        		evalTypeFirstNm = "시험_";
        	}else if("04".equals(evalCd)) {//진행단계평가
        		evalTypeFirstNm = "진행평가_";
        	}
        	
        	//오프라인 시험 체크 필요
        	
        	if("".equals(evalType)) {
        		chasi = hrdApiScoreVO.getOldMaxEvalType() + 1;
        		evalType = evalTypeFirstNm + String.valueOf(chasi);//기존 개설과정의 시험 EVAL_TYPE의 마지막 문자열 MAX 값 + 1
        	}
        	
        	
        	//공통 파라미터
        	hrdApiScoreVO.setAgentPk("edulife");
        	hrdApiScoreVO.setEvalType(evalType);
        	//hrdApiScoreVO.setChangeState("C");//등록
        	hrdApiScoreVO.setIsCopiedAnswer("N");
        	hrdApiScoreVO.setChasi(chasi);
        	hrdApiScoreVO.setSyncStatus("W");
        	hrdApiScoreVO.setRegNo(vo.getRegNo());
        	
        	if(saveType.equals("START")) {
        		
        		hrdApiScoreVO.setAccessIp(vo.getRegIp());//시험 시작한 IP
        		hrdApiScoreVO.setStartEndFlag("S");//시작
        		hrdApiScoreVO.setLmsFlag("S");//시작
        		
        		//기존 데이터 있으면, U 없으면 C
        		HrdApiScoreVO startHrdApiScoreVO = hrdApiScoreMapper.selectScoreOneRecent(hrdApiScoreVO);
        		if(startHrdApiScoreVO == null) {
        			hrdApiScoreVO.setChangeState("C");
        		}else {
        			hrdApiScoreVO.setChangeState("U");
        		}
        		
        		hrdApiScoreMapper.insert(hrdApiScoreVO);
        	}else if(saveType.equals("END")) {//시험 응시 종료
        		//hrdApiScoreVO.setAccessIp(hrdApiScoreVO.getAccessIp());//시험 시작한 IP : 매뉴얼문서 기준 시험 시작 IP
        		hrdApiScoreVO.setChangeState("U");
        		hrdApiScoreVO.setAccessIp(vo.getRegIp());
        		hrdApiScoreVO.setStartEndFlag("E");//종료
        		hrdApiScoreVO.setLmsFlag("E");//시작
        		
        		hrdApiScoreMapper.insert(hrdApiScoreVO);
        	}else if(saveType.equals("RATE")) {//평가
        		//평가(채점)의 경우, 최종 제출한 데이터가 있어야만 평가가 가능하다. (HRD 측 답변)
        		//insert 전 최종 제출 데이터 조회 후 값 세팅 : 수강생 IP, 제출일자
        		
        		hrdApiScoreVO.setStartEndFlag("E");//종료
        		HrdApiScoreVO endHrdApiScoreVO = hrdApiScoreMapper.selectScoreOneRecent(hrdApiScoreVO);
        		
        		if(endHrdApiScoreVO == null) {//저장 오류가 안 나기 위해 
        			hrdApiScoreVO.setAccessIp(hrdApiScoreVO.getAccessIp());//시험 시작한 IP : 매뉴얼문서 기준 시험 시작 IP
        			log.error("[성적 채점 오류] 최종 제출 데이터 미조회 param =" + hrdApiScoreVO.toString());
        		}else {
        			hrdApiScoreVO.setAccessIp(endHrdApiScoreVO.getAccessIp());
        			hrdApiScoreVO.setSubmitDate(endHrdApiScoreVO.getSubmitDate());
        			hrdApiScoreVO.setNum(endHrdApiScoreVO.getNum());//submitDate형식 변환X
        		}
        		hrdApiScoreVO.setChangeState("U");
        		hrdApiScoreVO.setLmsFlag("R");//평가
        		
        		hrdApiScoreMapper.insert(hrdApiScoreVO);
        	}
    	}
    	
    }    
    
    private void hrdApiScoreAsmt(AssignmentSendVO vo, String saveType) {
    	//수강생의 시험 성적, crs, cre 등 정보 조회
    	HrdApiScoreVO hrdApiScoreVO = new HrdApiScoreVO();
    	
    	if(saveType.equals("RESET")) {//Reset은 이미 삭제한 데이터이므로, api 데이터 중 최신 데이터를 조회해온다.
    		//오프라인 시험 체크 필요
    		hrdApiScoreVO.setStartEndFlag("");//혹시 값이 세팅될 수 있기 때문에 초기화
    		hrdApiScoreVO.setStdNo(vo.getStdNo());
    		hrdApiScoreVO.setEvalCd("03");
    		hrdApiScoreVO.setEvalTypeCd(String.valueOf(vo.getAsmtSn()));
    		
    		HrdApiScoreVO resultHrdApiScoreVO = hrdApiScoreMapper.selectScoreOneRecent(hrdApiScoreVO);//가장 최근 과제 api 조회
    		
    		if(resultHrdApiScoreVO == null) {
        		log.error("[성적api저장 미 조회 오류(과제)] " + saveType + " param = " + vo.getStdNo() + ", " + vo.getAsmtSn() + ", " + vo.getCrsCreCd() );
        	}
    		
    		resultHrdApiScoreVO.setChangeState("D");
    		resultHrdApiScoreVO.setLmsFlag("D");
    		resultHrdApiScoreVO.setRegNo(vo.getRegNo());
    		resultHrdApiScoreVO.setSyncStatus("W");
    		resultHrdApiScoreVO.setScore(0);//점수 0
    		
    		hrdApiScoreMapper.insert(resultHrdApiScoreVO);
    		
    	}else {
    	
	    	if(saveType.equals("START")) {
	    		vo.setStartFlagYn("Y");
	    	}else {
	    		vo.setStartFlagYn("N");
	    	}
	    	
	    	hrdApiScoreVO = hrdApiScoreMapper.selectScoreAsmt(vo);
	    	
        	try {
        		if(hrdApiScoreVO != null) {
        			hrdApiScoreVO.convertScore();
        		}
			} catch (Exception e) {
				log.error(e.getMessage());
			}
	    	
	    	String evalType = StringUtil.nvl(hrdApiScoreVO.getEvalType());
	    	int chasi = hrdApiScoreVO.getChasi();
	    	int oldChasi = hrdApiScoreVO.getOldMaxEvalType();
	    	
	    	if("".equals(evalType)) {
	    		chasi = oldChasi + 1;
	    		evalType = "과제_" + String.valueOf(chasi);//기존 개설과정의 과제 EVAL_TYPE의 마지막 문자열 MAX 값 + 1
	    	}
	    	
	    	hrdApiScoreVO.setAgentPk("edulife");
	    	hrdApiScoreVO.setEvalType(evalType);
	    	//hrdApiScoreVO.setChangeState("C");//등록
	    	hrdApiScoreVO.setIsCopiedAnswer("N");
	    	hrdApiScoreVO.setChasi(chasi);
	    	hrdApiScoreVO.setSyncStatus("W");
	    	hrdApiScoreVO.setAccessIp(vo.getRegIp());
	    	hrdApiScoreVO.setRegNo(vo.getRegNo());
	    	
	    	if(saveType.equals("START")) {
	    		hrdApiScoreVO.setStartEndFlag("S");
	    		hrdApiScoreVO.setLmsFlag("S");
	    		
	    		//기존 데이터 있으면, U 없으면 C
	    		HrdApiScoreVO startHrdApiScoreVO = hrdApiScoreMapper.selectScoreOneRecent(hrdApiScoreVO);
	    		if(startHrdApiScoreVO == null) {
	    			hrdApiScoreVO.setChangeState("C");
	    		}else {
	    			hrdApiScoreVO.setChangeState("U");
	    		}
	    		
	    		hrdApiScoreMapper.insert(hrdApiScoreVO);
	    	}else if(saveType.equals("END")) {
	    		hrdApiScoreVO.setChangeState("U");
	    		hrdApiScoreVO.setStartEndFlag("E");
	    		hrdApiScoreVO.setLmsFlag("E");
	    		hrdApiScoreMapper.insert(hrdApiScoreVO);
	    	}else if(saveType.equals("RATE")) {//평가
	    		//평가(채점)의 경우, 최종 제출한 데이터가 있어야만 평가가 가능하다. (HRD 측 답변)
	    		//insert 전 최종 제출 데이터 조회 후 값 세팅 : 수강생 IP, 제출일자
	    		hrdApiScoreVO.setChangeState("U");
	    		hrdApiScoreVO.setStartEndFlag("E");//종료
	    		hrdApiScoreVO.setLmsFlag("R");//평가
	    		HrdApiScoreVO endHrdApiScoreVO = hrdApiScoreMapper.selectScoreOneRecent(hrdApiScoreVO);
	    		
	    		if(endHrdApiScoreVO == null) {//저장 오류가 안 나기 위해 
	    			log.error("[과제 채점 오류] 최종 제출 데이터 미조회 param =" + hrdApiScoreVO.toString());
	    		}else {
	    			hrdApiScoreVO.setAccessIp(endHrdApiScoreVO.getAccessIp());
	        		hrdApiScoreVO.setSubmitDate(endHrdApiScoreVO.getSubmitDate());
	        		hrdApiScoreVO.setNum(endHrdApiScoreVO.getNum());
	    		}
	    		hrdApiScoreMapper.insert(hrdApiScoreVO);
	    	}
    	}
    }
    
    private void hrdApiScoreBookmark(BookmarkVO vo, String saveType) {
    	
    	HrdApiScoreVO hrdApiScoreVO = new HrdApiScoreVO();
    	hrdApiScoreVO = hrdApiScoreMapper.selectScoreBookmark(vo);
    	if(DateTimeUtil.chkDateTimeNowAfter(hrdApiScoreVO.getTermEndDttm())) return;
    	
    	String evalType = StringUtil.nvl(hrdApiScoreVO.getEvalType());
    	int chasi = hrdApiScoreVO.getChasi();
    	int oldChasi = hrdApiScoreVO.getOldMaxEvalType();
    	if("".equals(evalType)) {
    		chasi = oldChasi + 1;
    		evalType = "진도_" + String.valueOf(chasi);//기존 개설과정의 진도 EVAL_TYPE의 마지막 문자열 MAX 값 + 1
    	}
    	
    	hrdApiScoreVO.setAgentPk("edulife");
    	hrdApiScoreVO.setEvalType(evalType);
    	hrdApiScoreVO.setIsCopiedAnswer("X");
    	hrdApiScoreVO.setSyncStatus("W");
    	hrdApiScoreVO.setAccessIp(vo.getRegIp());
    	hrdApiScoreVO.setRegNo(vo.getRegNo());
    	hrdApiScoreVO.setChasi(chasi);//진도의 경우 차시
    	
    	if(saveType.equals("START")) {
    		hrdApiScoreVO.setStartEndFlag("S");
    		hrdApiScoreVO.setLmsFlag("S");
    		
    		//기존 데이터 있으면, U 없으면 C
    		HrdApiScoreVO startHrdApiScoreVO = hrdApiScoreMapper.selectScoreOneRecent(hrdApiScoreVO);
    		if(startHrdApiScoreVO == null) {
    			hrdApiScoreVO.setChangeState("C");
    		}else {
    			hrdApiScoreVO.setChangeState("U");
    		}
    		
    		hrdApiScoreMapper.insert(hrdApiScoreVO);
    	}else if(saveType.equals("END")) {
    		hrdApiScoreVO.setChangeState("U");//수정
    		hrdApiScoreVO.setStartEndFlag("E");
    		hrdApiScoreVO.setLmsFlag("E");
    		hrdApiScoreMapper.insert(hrdApiScoreVO);
    	}
    }
    
    /** 성적이력  api 끝 **/
    
	private String getUserNoFromSession() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		return UserBroker.getUserNo(request);
	}
	
	
}
