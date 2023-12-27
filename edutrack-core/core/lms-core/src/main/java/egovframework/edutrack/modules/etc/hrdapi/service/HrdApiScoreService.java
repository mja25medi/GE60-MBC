package egovframework.edutrack.modules.etc.hrdapi.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface HrdApiScoreService {

	abstract int insert(HrdApiScoreVO vo);
	
	abstract int update(HrdApiScoreVO vo);
	
	abstract int merge(HrdApiScoreVO vo);
	
	abstract int delete(HrdApiScoreVO vo);
	 
	abstract ProcessResultListVO<HrdApiVO> callApi(List<EgovMap> egovList, HrdApiScoreVO vo);
	
	abstract EgovMap selectScore(HrdApiScoreVO vo);
	
	abstract List<EgovMap> listScore(HrdApiScoreVO vo);
	
	abstract List<EgovMap> listForApi(HrdApiScoreVO vo);
	
	abstract ProcessResultListVO<EgovMap> listPagingScore(HrdApiScoreVO vo);
	
}