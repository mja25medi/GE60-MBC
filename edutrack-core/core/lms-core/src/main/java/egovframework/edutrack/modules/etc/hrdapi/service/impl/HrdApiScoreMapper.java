package egovframework.edutrack.modules.etc.hrdapi.service.impl;


import java.util.List;

import egovframework.edutrack.modules.etc.hrdapi.service.HrdApiScoreVO;
import egovframework.edutrack.modules.lecture.assignment.service.AssignmentSendVO;
import egovframework.edutrack.modules.lecture.bookmark.service.BookmarkVO;
import egovframework.edutrack.modules.lecture.exam.service.ExamStareVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Mapper
public interface HrdApiScoreMapper {

	int insert(HrdApiScoreVO vo);
	
	int update(HrdApiScoreVO vo);
	
	int merge(HrdApiScoreVO vo);
	
	int delete(HrdApiScoreVO vo);
	
	List<EgovMap> selectForApi(HrdApiScoreVO vo);
	
	EgovMap selectScore(HrdApiScoreVO vo);
	
	List<EgovMap> listScore(HrdApiScoreVO vo);
	
	List<EgovMap> listPagingScore(HrdApiScoreVO vo);

	int countListPageingScore(HrdApiScoreVO vo);
	
	HrdApiScoreVO selectScoreExam(ExamStareVO vo);
	
	HrdApiScoreVO selectScoreAsmt(AssignmentSendVO vo);

	HrdApiScoreVO selectScoreBookmark(BookmarkVO vo);

	HrdApiScoreVO selectScoreOneRecent(HrdApiScoreVO hrdApiScoreVO);
	
}

