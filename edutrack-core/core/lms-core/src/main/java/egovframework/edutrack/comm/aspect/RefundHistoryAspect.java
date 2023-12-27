package egovframework.edutrack.comm.aspect;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import egovframework.edutrack.comm.annotation.RefundHistory;
import egovframework.edutrack.comm.exception.MediopiaDefineException;
import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.util.web.UserBroker;
import egovframework.edutrack.modules.student.payment.service.impl.PaymentMapper;
import egovframework.edutrack.modules.student.student.service.StudentVO;
import egovframework.edutrack.modules.student.student.service.impl.StudentMapper;

@Aspect
@Component
public class RefundHistoryAspect {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
    @Resource(name="paymentMapper")
    private PaymentMapper 		paymentMapper;
    
    @Resource(name="studentMapper")
    private StudentMapper 		studentMapper;
    
	@AfterReturning("@annotation(egovframework.edutrack.comm.annotation.RefundHistory)")
	public void insertCourserInfoApi(JoinPoint jp) {
		try {
			StudentVO studentVO = (StudentVO) jp.getArgs()[0];
			StudentVO insertStudentVO = studentMapper.selectForRefundHsty(studentVO);
			
			if(insertStudentVO == null) {
				throw new ServiceProcessException("환불 이력 저장을 위한 수강생 정보가 조회되지 않습니다.");
			}
			MethodSignature methodSignature = (MethodSignature) jp.getSignature();
			RefundHistory refundHistory = methodSignature.getMethod().getAnnotation(RefundHistory.class);
			insertStudentVO.setRefundTypeCd(refundHistory.value().getCode());
			
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
			if(request != null) {
				insertStudentVO.setRegNo(UserBroker.getUserNo(request));
			}
			
			studentMapper.insertStdPayRefundHsty(insertStudentVO);
		    
	    } catch (MediopiaDefineException e) {
	    	log.error("[환불 이력 저장 오류] 사유 : " +  e.getMessage());
		} catch (Exception e) {
			log.error("[환불 이력 저장 오류]");
	    }
	}
}
