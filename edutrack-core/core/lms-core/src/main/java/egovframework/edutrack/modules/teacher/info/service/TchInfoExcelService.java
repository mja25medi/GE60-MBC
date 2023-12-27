package egovframework.edutrack.modules.teacher.info.service;

import java.lang.reflect.InvocationTargetException;

import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface TchInfoExcelService {

	/**
	 * 강사정보 엑셀 업로드(Excel2007 이상 지원)
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public abstract ProcessResultVO<TchInfoVO> addTeacherInfoExcel(String fileName, String filePath) throws ServiceProcessException;

}