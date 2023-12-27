package egovframework.edutrack.modules.course.creCrsResh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.modules.course.creCrsResh.service.CrsReshResultService;
import egovframework.edutrack.modules.course.creCrsResh.service.CrsReshResultVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


@Service("crsReshResultService")
public class CrsReshResultServiceImpl 
	extends EgovAbstractServiceImpl implements  CrsReshResultService {

	/** Mapper */
	@Resource(name="crsReshResultMapper")
	private CrsReshResultMapper 	crsReshResultMapper;
	
	/**
	 * 개설 과정 설문 결과 목록 조회
	 */
	@Override
	public	ProcessResultListVO<CrsReshResultVO> list(CrsReshResultVO VO) throws Exception {
		ProcessResultListVO<CrsReshResultVO> resultList = new ProcessResultListVO<CrsReshResultVO>();
		try {
			List<CrsReshResultVO> returnList = crsReshResultMapper.list(VO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}
}
