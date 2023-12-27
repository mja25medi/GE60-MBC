package egovframework.edutrack.modules.teacher.tmtab.service;


import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.service.ProcessResultListVO;



/**
 * <strong>강사 - 강사정보</strong> 영역 서비스 인터페이스
 * @author DongKwang
 */
public interface TchTmtabService {

	/**
	 * 강사 시간표 조회
	 * 
	 * @return ProcessResultListVO
	 */
	public abstract ProcessResultListVO<TchTmtabVO> listTmtab(TchTmtabVO teacherTmtabVO, int curPage) throws Exception;
	
	
}