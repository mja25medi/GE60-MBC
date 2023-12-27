package egovframework.edutrack.comm.aspect;

import static egovframework.edutrack.modules.log.userupdate.service.UserInfoLog.LogType.UPDATE;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.edutrack.comm.exception.ServiceIllegalArgumentException;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.modules.log.userupdate.service.LogUserUpdateLogVO;
import egovframework.edutrack.modules.log.userupdate.service.UserInfoLog;
import egovframework.edutrack.modules.log.userupdate.service.UserInfoLog.LogType;
import egovframework.edutrack.modules.log.userupdate.service.impl.LogUserUpdateLogMapper;
import egovframework.edutrack.modules.user.info.service.UsrLoginVO;
import egovframework.edutrack.modules.user.info.service.UsrUserAuthGrpVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;
import egovframework.edutrack.modules.user.info.service.impl.UsrUserAuthGrpMapper;
import egovframework.edutrack.modules.user.info.service.impl.UsrUserInfoMapper;

@Aspect
@Component
public class LogAspect {

	protected final Log log = LogFactory.getLog(this.getClass());
	
    @Resource(name="usrUserInfoMapper")
	private UsrUserInfoMapper 			usrUserInfoMapper;
    
    @Resource(name="usrUserAuthGrpMapper")
    private UsrUserAuthGrpMapper 		usrUserAuthGrpMapper;
    
    @Resource(name="logUserUpdateLogMapper")
    private LogUserUpdateLogMapper		logUserUpdateLogMapper;
    
    private ObjectMapper objectMapper = new ObjectMapper();
    
    @Around("@annotation(annotation)")
    public Object insertUserUpdateLog(ProceedingJoinPoint pjp, UserInfoLog annotation) throws Throwable {
    	UsrLoginVO userParam = Arrays.stream(pjp.getArgs())
    			.filter(UsrLoginVO.class::isInstance)
    			.map(UsrLoginVO.class::cast)
    			.findAny()
    			.orElseThrow(
    					() -> new ServiceIllegalArgumentException(
    							String.format("올바른 타입의 인자가 없습니다. [%s]", UsrLoginVO.class.getName())));
    	
    	String targetUserNo = Objects.requireNonNull(userParam.getUserNo());
    	UsrUserInfoVO snapshot = getUserInfoWithAuth(targetUserNo);
    	
    	Object returnValue = pjp.proceed();
    	try {
    		LogType logType = annotation.value();
        	LogUserUpdateLogVO log = generateAccessLog(targetUserNo);
        	log.setLogType(logType);
        	log.setMethodInfo(pjp.getSignature().toShortString());
        	if(UPDATE.equals(logType)) {
        		UsrUserInfoVO after = getUserInfoWithAuth(targetUserNo);
        		log.setHistory(userInfoDirtyCheck(snapshot, after));
        	}
        	logUserUpdateLogMapper.insert(log);    		
    	} catch(Exception e) {
    		log.error("USER_UPDATE_LOG ERROR");
    		e.printStackTrace();
    	}
    	return returnValue;   		
    }
    
    private UsrUserInfoVO getUserInfoWithAuth(String userNo) throws Exception {
    	UsrUserInfoVO infoVO = new UsrUserInfoVO();
    	infoVO.setUserNo(userNo);
    	UsrUserInfoVO userInfo = usrUserInfoMapper.select(infoVO);
    	
    	UsrUserAuthGrpVO authVO = new UsrUserAuthGrpVO();
    	authVO.setUserNo(userNo);
    	String authGrpCd = usrUserAuthGrpMapper.list(authVO).stream()
    			.map(UsrUserAuthGrpVO::getAuthGrpCd)
    			.collect(Collectors.toList()).toString();
		userInfo.setAuthGrpCd(authGrpCd);
		
		return userInfo;
    }
    
    private LogUserUpdateLogVO generateAccessLog(String targetUserNo) {
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
  
    	LogUserUpdateLogVO log = new LogUserUpdateLogVO();
    	log.setAccessIp(request.getRemoteAddr());
    	log.setAccessUserId(UserBroker.getUserId(request));
    	log.setAccessUserNo(UserBroker.getUserNo(request));
    	log.setTargetUserNo(targetUserNo);
    	log.setUriInfo(request.getRequestURI());
    	
    	return log;
    }
    
    @SuppressWarnings("unchecked")
    private String userInfoDirtyCheck(UsrUserInfoVO snapshot, UsrUserInfoVO after) throws JsonProcessingException {
    	Map<String, Object> snapshotMap = objectMapper.convertValue(snapshot, Map.class);
    	Map<String, Object> afterMap = objectMapper.convertValue(after, Map.class);
    	Map<String, String> historyMap = new HashMap<>();
    	
    	afterMap.keySet().stream()
    		.filter(key -> !isNotNeedInfo(key))
    		.filter(key -> !String.valueOf(afterMap.get(key)).equals(String.valueOf(snapshotMap.get(key))))
    		.forEach(key -> historyMap.put(key, updateInfo(snapshotMap.get(key), afterMap.get(key))));
    	return objectMapper.writeValueAsString(historyMap);
    }
    private String updateInfo(Object before, Object after) {
    	return String.format("[%s -> %s]", String.valueOf(before), String.valueOf(after));
    }
    private boolean isNotNeedInfo(String key) {
    	return "string".equals(key) || "modNo".equals(key) || "modDttm".equals(key);
    }
}
