package egovframework.edutrack.modules.teacher.tmtab.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.modules.teacher.tmtab.service.TchTmtabService;
import egovframework.edutrack.modules.teacher.tmtab.service.TchTmtabVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


@Service("tchTmtabService")
public class TchTmtabServiceImpl extends EgovAbstractServiceImpl implements TchTmtabService {

	/** Mapper */
	@Resource(name="tchTmtabMapper")
	private TchTmtabMapper 		tchTmtabMapper;

	/**
	 * 강사 활동이력 목록 조회
	 *
	 * @return ProcessResultListVO
	 * @throws Exception 
	 */
	public ProcessResultListVO<TchTmtabVO> listTmtab(TchTmtabVO teacherTmtabVO, int curPage) throws Exception {
		teacherTmtabVO.setCurPage(curPage);
		
		ProcessResultListVO<TchTmtabVO> resultList = new ProcessResultListVO<TchTmtabVO>(); 
		try {
			List<TchTmtabVO> returnList = tchTmtabMapper.listTmtab(teacherTmtabVO);
			resultList.setResult(1);
			resultList.setReturnList(returnList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

}
