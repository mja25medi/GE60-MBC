package egovframework.edutrack.modules.lecture.bookmark.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.annotation.HrdApiScore;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.modules.course.contents.service.ContentsVO;
import egovframework.edutrack.modules.course.contents.service.impl.ContentsMapper;
import egovframework.edutrack.modules.course.subject.service.OnlineSubjectVO;
import egovframework.edutrack.modules.course.subject.service.impl.SubjectOnlineMapper;
import egovframework.edutrack.modules.lecture.bookmark.service.BookmarkService;
import egovframework.edutrack.modules.lecture.bookmark.service.BookmarkVO;
import egovframework.edutrack.modules.opencourse.subject.service.OpenCrsSbjVO;
import egovframework.edutrack.modules.opencourse.subject.service.impl.OpenCrsSbjMapper;
import egovframework.edutrack.modules.student.result.service.EduResultService;
import egovframework.edutrack.modules.student.result.service.EduResultVO;
import egovframework.edutrack.modules.student.result.service.impl.EduResultMapper;
import egovframework.edutrack.modules.student.student.service.StudentVO;
import egovframework.edutrack.modules.student.student.service.impl.StudentMapper;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("bookmarkService")
public class BookmarkServiceImpl extends EgovAbstractServiceImpl implements BookmarkService {

	@Resource(name="bookmarkMapper")
	private BookmarkMapper bookmarkMapper;

	@Resource(name="subjectOnlineMapper")
	private SubjectOnlineMapper subjectOnlineMapper;

	@Resource(name="eduResultMapper")
	private EduResultMapper eduResultMapper;

	@Resource(name="studentMapper")
	private StudentMapper studentMapper;

	@Resource(name="contentsMapper")
	private ContentsMapper contentsMapper;

	@Resource(name="openCrsSbjMapper")
	private OpenCrsSbjMapper			openCrsSbjMapper;
	
	@Resource
	private EduResultService eduResultService;
	
	@Resource
	private BookmarkService bookmarkService;

	/**
	 * 교재 목록 조회 (단순 리스트 반환)
	 * @param sbjCd
	 * @param parUnitCd
	 * @return
	 */
	public ProcessResultListVO<BookmarkVO> listBookmark(BookmarkVO iBookmarkVO) throws Exception{
		//---- 과목 관련 정보 검색
		OnlineSubjectVO onsbjVO = new OnlineSubjectVO();
		onsbjVO.setSbjCd(iBookmarkVO.getSbjCd());
		onsbjVO = subjectOnlineMapper.select(onsbjVO);

		BookmarkVO bookmarkVO = new BookmarkVO();
		bookmarkVO.setSbjCd(iBookmarkVO.getSbjCd());
		bookmarkVO.setUnitType("R");
		bookmarkVO.setUnitNm(onsbjVO.getSbjNm());
		bookmarkVO.setStdNo(iBookmarkVO.getStdNo());
		bookmarkVO.setParUnitCd("");
		bookmarkVO.setUnitLvl(0);

		List<BookmarkVO> resultListVO = bookmarkMapper.listBookmark(iBookmarkVO);

		for (BookmarkVO parent : resultListVO) {
			if(StringUtil.isNull(parent.getParUnitCd())) {
				bookmarkVO.addSubVO(parent);
			}
			for (BookmarkVO child : resultListVO) {
				if(parent.getUnitCd().equals(child.getParUnitCd())) {
					parent.addSubVO(child);
				}
			}
		}

		List<BookmarkVO> bookmarkList = new ArrayList<BookmarkVO>();
		if("Y".equals(onsbjVO.getWinMenuUseYn())) {
			//-- 학습창 메뉴 사용인 경우 (최상위 목록만을 가져온다. 하위 목록의 진도율, 시간등 계산)
			listContentsArray(bookmarkVO, bookmarkList);
		} else {
			listContentsTree(bookmarkVO, bookmarkList);
		}
		ProcessResultListVO<BookmarkVO> resultList = new ProcessResultListVO<BookmarkVO>();
		resultList.setReturnList(bookmarkList);
		resultList.setResult(1);
		return resultList;
	}

	/**
	 * 교재 목록 조회 (트리형태의 VO 반환)
	 * @param sbjCd
	 * @param parUnitCd
	 * @return
	 */
	public ProcessResultVO<BookmarkVO> listBookmarkTreeVO(BookmarkVO iBookmarkVO) throws Exception {
		//---- 과목 관련 정보 검색
		OnlineSubjectVO onsbjVO = new OnlineSubjectVO();
		onsbjVO.setSbjCd(iBookmarkVO.getSbjCd());
		onsbjVO = subjectOnlineMapper.select(onsbjVO);

		BookmarkVO bookmarkVO = new BookmarkVO();
		bookmarkVO.setSbjCd(iBookmarkVO.getSbjCd());
		bookmarkVO.setUnitType("R");
		bookmarkVO.setUnitNm(onsbjVO.getSbjNm());
		bookmarkVO.setStdNo(iBookmarkVO.getStdNo());
		bookmarkVO.setParUnitCd("");
		bookmarkVO.setUnitLvl(0);

		List<BookmarkVO> resultListVO = bookmarkMapper.listBookmark(iBookmarkVO);

		for (BookmarkVO parent : resultListVO) {
			if(StringUtil.isNull(parent.getParUnitCd())) {
				bookmarkVO.addSubVO(parent);
			}
			for (BookmarkVO child : resultListVO) {
				if(parent.getUnitCd().equals(child.getParUnitCd())) {
					parent.addSubVO(child);
				}
			}
		}
		ProcessResultVO<BookmarkVO> result = new ProcessResultVO<BookmarkVO>();
		result.setReturnVO(bookmarkVO);
		result.setResult(1);
		return result;
	}

	/**
	 * 교내 학습 관리용 교재 목록 조회 (단순 목록 형태의 LIST 반환)
	 * @param sbjCd
	 * @param parUnitCd
	 * @return
	 */
	public ProcessResultListVO<BookmarkVO> listCntsForClass(BookmarkVO iBookmarkVO) throws Exception{
		//---- 과목 관련 정보 검색
		OnlineSubjectVO onsbjVO = new OnlineSubjectVO();
		onsbjVO.setSbjCd(iBookmarkVO.getSbjCd());
		onsbjVO = subjectOnlineMapper.select(onsbjVO);

		BookmarkVO bookmarkVO = new BookmarkVO();
		bookmarkVO.setSbjCd(iBookmarkVO.getSbjCd());
		bookmarkVO.setUnitType("R");
		bookmarkVO.setUnitNm(onsbjVO.getSbjNm());
		bookmarkVO.setStdNo(iBookmarkVO.getStdNo());
		bookmarkVO.setParUnitCd("");
		bookmarkVO.setUnitLvl(0);

		List<BookmarkVO> resultListVO = bookmarkMapper.listCntsForClass(iBookmarkVO);

		for (BookmarkVO parent : resultListVO) {
			if(StringUtil.isNull(parent.getParUnitCd())) {
				bookmarkVO.addSubVO(parent);
			}
			for (BookmarkVO child : resultListVO) {
				if(parent.getUnitCd().equals(child.getParUnitCd())) {
					parent.addSubVO(child);
				}
			}
		}

		List<BookmarkVO> bookmarkList = new ArrayList<BookmarkVO>();
		if("Y".equals(onsbjVO.getWinMenuUseYn())) {
			//-- 학습창 메뉴 사용인 경우 (최상위 목록만을 가져온다. 하위 목록의 진도율, 시간등 계산)
			listContentsArray(bookmarkVO, bookmarkList);
		} else {
			listContentsTree(bookmarkVO, bookmarkList);
		}
		ProcessResultListVO<BookmarkVO> resultList = new ProcessResultListVO<BookmarkVO>();
		resultList.setReturnList(bookmarkList);
		resultList.setResult(1);
		return resultList;
	}

	/**
	 * 교재 목록 조회 (트리형태의 VO 반환)
	 * @param sbjCd
	 * @param parUnitCd
	 * @return
	 */
	public ProcessResultListVO<BookmarkVO> listBookmarkSub(BookmarkVO iBookmarkVO, String rootUnitCd) throws Exception  {
		//---- 과목 관련 정보 검색
		OnlineSubjectVO onsbjVO = new OnlineSubjectVO();
		onsbjVO.setSbjCd(iBookmarkVO.getSbjCd());
		onsbjVO = subjectOnlineMapper.select(onsbjVO);

		BookmarkVO bookmarkVO = new BookmarkVO();
		bookmarkVO.setSbjCd(iBookmarkVO.getSbjCd());
		bookmarkVO.setUnitType("R");
		bookmarkVO.setUnitNm(onsbjVO.getSbjNm());
		bookmarkVO.setStdNo(iBookmarkVO.getStdNo());
		bookmarkVO.setParUnitCd("");
		bookmarkVO.setUnitLvl(0);

		List<BookmarkVO> resultListVO = bookmarkMapper.listBookmark(iBookmarkVO);

		for (BookmarkVO parent : resultListVO) {
			if(rootUnitCd.equals(parent.getUnitCd())) {
				bookmarkVO.addSubVO(parent);
			}
			for (BookmarkVO child : resultListVO) {
				if(parent.getUnitCd().equals(child.getParUnitCd())) {
					parent.addSubVO(child);
				}
			}
		}
		List<BookmarkVO> bookmarkList = new ArrayList<BookmarkVO>();

		listContentsTree(bookmarkVO, bookmarkList);

		ProcessResultListVO<BookmarkVO> resultList = new ProcessResultListVO<BookmarkVO>();
		resultList.setReturnList(bookmarkList);
		resultList.setResult(1);
		return resultList;
	}


	/**
	 * 교재 목차를 ArrayList 로 만든다. (트리형 목차를 단순 List로 만든다.)
	 * @param arrayList
	 * @param bookmarkVO
	 */
	private void listContentsTree(BookmarkVO iBookmarkVO, List<BookmarkVO> bookmarkList) throws Exception  {
		if(!"R".equals(iBookmarkVO.getUnitType())) bookmarkList.add(iBookmarkVO);
		if(iBookmarkVO.getSubList().size() > 0) {
			for(int i=0; i < iBookmarkVO.getSubList().size(); i++) {
				BookmarkVO bookmarkVO = iBookmarkVO.getSubList().get(i);
				listContentsTree(bookmarkVO, bookmarkList);
			}
		}
	}

	/**
	 * 교재 목차를 ArrayList 로 만든다. 1차시 레벨로 반환
	 * @param arrayList
	 * @param bookmarkVO
	 */
	private void listContentsArray(BookmarkVO iBookmarkVO, List<BookmarkVO> bookmarkList) throws Exception  {
		for(int i=0; i < iBookmarkVO.getSubList().size(); i++) {
			BookmarkVO bookmarkVO = iBookmarkVO.getSubList().get(i);
			bookmarkVO.setPrgrRatio(bookmarkVO.getSummaryRatio());
			bookmarkVO.setConnTotTm(bookmarkVO.getTotalConnectTime());
			bookmarkVO.setConnCnt(bookmarkVO.getTotalConnect());
			bookmarkVO.setUnitCd(bookmarkVO.getLastConnectUinitCd(""));
			bookmarkVO.setQuizUnitCd(bookmarkVO.getQuiz());
			bookmarkList.add(bookmarkVO);
		}
	}


	public	ProcessResultVO<BookmarkVO> viewBookmark(BookmarkVO iBookmarkVO) throws Exception {
		ProcessResultVO<BookmarkVO> resultVO = new ProcessResultVO<BookmarkVO>();
		try {
			BookmarkVO returnVO = bookmarkMapper.selectBookmark(iBookmarkVO);
			resultVO.setReturnVO(returnVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}
	
	
	/**
	 * 컨텐츠 입장 시,
	 */
	//@HrdApiScore(category = Category.BOOKMARK, saveType = SaveType.START)
	@HrdApiScore
	public	ProcessResultVO<BookmarkVO> viewBookmarkHrdLog(BookmarkVO iBookmarkVO) throws Exception {
		ProcessResultVO<BookmarkVO> resultVO = new ProcessResultVO<BookmarkVO>();
		try {
			BookmarkVO returnVO = bookmarkMapper.selectBookmark(iBookmarkVO);
			iBookmarkVO.setScoreSaveType("START");
			iBookmarkVO.setScoreCategory("BOOKMARK");
			resultVO.setReturnVO(returnVO); 
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}

	public	ProcessResultVO<BookmarkVO> viewContents(BookmarkVO iBookmarkVO) throws Exception {
		ProcessResultVO<BookmarkVO> resultVO = new ProcessResultVO<BookmarkVO>();
		try {
			BookmarkVO returnVO = new BookmarkVO();
			if("mysql".equals(Constants.DATABASE_DB_TYPE) && "5".equals(Constants.DATABASE_DB_VERSION)) {
				returnVO = bookmarkMapper.selectContentsVer5(iBookmarkVO);
			}else {
				returnVO = bookmarkMapper.selectContents(iBookmarkVO);
			}
			resultVO.setReturnVO(returnVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		return resultVO;
	}

	//@HrdApiScore(category = Category.BOOKMARK, saveType = SaveType.START) 
	public	ProcessResultVO<BookmarkVO> addBookmark(BookmarkVO iBookmarkVO) throws Exception {

		ProcessResultVO<BookmarkVO> resultVO = new ProcessResultVO<BookmarkVO>();
		try {
			bookmarkMapper.insertBookmark(iBookmarkVO);


			StudentVO stdVO = new StudentVO();
			stdVO.setStdNo(iBookmarkVO.getStdNo());
			stdVO = studentMapper.selectStudent(stdVO);

			//-- 자동 성적 처리 :
			EduResultVO eduResultVO = new EduResultVO();
			eduResultVO.setCrsCreCd(stdVO.getCrsCreCd());
			eduResultVO.setStdNo(iBookmarkVO.getStdNo());
			eduResultVO.setSbjCd(iBookmarkVO.getSbjCd());

			iBookmarkVO.setScoreCategory("BOOKMARK");
			iBookmarkVO.setScoreSaveType("START");
			
			eduResultService.addEduResultSp(eduResultVO, iBookmarkVO);
			
			resultVO.setReturnVO(iBookmarkVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		
		
		return resultVO;
	}
	
	public	ProcessResultVO<BookmarkVO> addBookmarkDetail(BookmarkVO iBookmarkVO) throws Exception {
		
		ProcessResultVO<BookmarkVO> resultVO = new ProcessResultVO<BookmarkVO>();
		try {
			bookmarkMapper.insertBookmarkDetail(iBookmarkVO);
			
			/*StudentVO stdVO = new StudentVO();
			stdVO.setStdNo(iBookmarkVO.getStdNo());
			stdVO = studentMapper.selectStudent(stdVO);
			
			//-- 자동 성적 처리 :
			EduResultVO eduResultVO = new EduResultVO();
			eduResultVO.setCrsCreCd(stdVO.getCrsCreCd());
			eduResultVO.setStdNo(iBookmarkVO.getStdNo());
			eduResultVO.setSbjCd(iBookmarkVO.getSbjCd());
			
			eduResultMapper.insertEduResultSp(eduResultVO);*/
			
			resultVO.setReturnVO(iBookmarkVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		
		return resultVO;
	}

	//@HrdApiScore(category = Category.BOOKMARK, saveType = SaveType.END)
	public	ProcessResultVO<BookmarkVO> editBookmark(BookmarkVO iBookmarkVO) throws Exception {

		ProcessResultVO<BookmarkVO> resultVO = new ProcessResultVO<BookmarkVO>();
		try {
			if(iBookmarkVO.getConnTotTm() > 999999999) iBookmarkVO.setConnTotTm(999999999);
			if(iBookmarkVO.getConnTm() > 999999999) iBookmarkVO.setConnTm(999999999);
			if(iBookmarkVO.getConnCnt() > 999) iBookmarkVO.setConnCnt(999);
			bookmarkMapper.updateBookmark(iBookmarkVO);
			
			bookmarkMapper.updateDetailStudyTm(iBookmarkVO);

			StudentVO stdVO = new StudentVO();
			stdVO.setStdNo(iBookmarkVO.getStdNo());
			stdVO = studentMapper.selectStudent(stdVO);

			//-- 자동 성적 처리 :
			iBookmarkVO.setScoreSaveType("END");
			iBookmarkVO.setScoreCategory("BOOKMARK");
			
			EduResultVO eduResultVO = new EduResultVO();
			eduResultVO.setCrsCreCd(stdVO.getCrsCreCd());
			eduResultVO.setStdNo(iBookmarkVO.getStdNo());
			eduResultVO.setSbjCd(iBookmarkVO.getSbjCd());

			eduResultService.addEduResultSp(eduResultVO, iBookmarkVO);

			resultVO.setReturnVO(iBookmarkVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}
	
	public ProcessResultVO<BookmarkVO> editDetailStudyYn(BookmarkVO iBookmarkVO) throws Exception {
		
		ProcessResultVO<BookmarkVO> resultVO = new ProcessResultVO<BookmarkVO>();
		try {
			bookmarkMapper.updateDetailStudyYn(iBookmarkVO);
			
			/*StudentVO stdVO = new StudentVO();
			stdVO.setStdNo(iBookmarkVO.getStdNo());
			stdVO = studentMapper.selectStudent(stdVO);
			
			//-- 자동 성적 처리 :
			EduResultVO eduResultVO = new EduResultVO();
			eduResultVO.setCrsCreCd(stdVO.getCrsCreCd());
			eduResultVO.setStdNo(iBookmarkVO.getStdNo());
			eduResultVO.setSbjCd(iBookmarkVO.getSbjCd());
			
			eduResultMapper.insertEduResultSp(eduResultVO);*/
			
			resultVO.setReturnVO(iBookmarkVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		
		return resultVO;
	}

	public	ProcessResultVO<BookmarkVO> hitsupBookmark(BookmarkVO iBookmarkVO) throws Exception {
		ProcessResultVO<BookmarkVO> resultVO = new ProcessResultVO<BookmarkVO>();
		try {
			bookmarkMapper.updateHitsup(iBookmarkVO);
			resultVO.setReturnVO(iBookmarkVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}

	public	ProcessResultVO<BookmarkVO> deleteBookmark(BookmarkVO iBookmarkVO) throws Exception {
		ProcessResultVO<BookmarkVO> resultVO = new ProcessResultVO<BookmarkVO>();
		try {
			bookmarkMapper.deleteBookmark(iBookmarkVO);
			resultVO.setReturnVO(iBookmarkVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}

	public ProcessResultVO<BookmarkVO> viewBookmarkStd(BookmarkVO iBookmarkVO ) throws Exception {
		ProcessResultVO<BookmarkVO> resultVO = new ProcessResultVO<BookmarkVO>();
		try {
			BookmarkVO returnVO = bookmarkMapper.selectBookmarkStd(iBookmarkVO);
			resultVO.setReturnVO(returnVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}

	public	ProcessResultVO<BookmarkVO> viewTodayStudyYn(BookmarkVO iBookmarkVO) throws Exception {
		ProcessResultVO<BookmarkVO> resultVO = new ProcessResultVO<BookmarkVO>();
		try {
			BookmarkVO returnVO = bookmarkMapper.selectTodayStudyYn(iBookmarkVO);
			resultVO.setReturnVO(returnVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}

	/**
	 * 단원별 학습자의 진도율 목록을 반환한다.
	 * @param sbjCd
	 * @param parUnitCd
	 * @return
	 */
	public ProcessResultListVO<BookmarkVO> listStdBookmark(BookmarkVO iBookmarkVO) throws Exception  {
		ProcessResultListVO<BookmarkVO> resultList = new ProcessResultListVO<BookmarkVO>();
		try {
			List<BookmarkVO> returnList =  bookmarkMapper.listStdBookmark(iBookmarkVO);
			resultList.setResult(1);
			resultList.setReturnList(returnList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	/**
	 * 교내학습관리 수강승인용
	 * @param iBookmarkVO
	 * @return
	 */
	/*public	ProcessResultVO<BookmarkVO> addClassBookmark(BookmarkVO iBookmarkVO) throws Exception  {
		String[] stdNoArray = StringUtil.split(iBookmarkVO.getStdNo(),",");

		ContentsVO contentsVO = new ContentsVO();
		contentsVO.setSbjCd(iBookmarkVO.getSbjCd());
		contentsVO.setUnitCd(iBookmarkVO.getUnitCd());
		contentsVO = contentsMapper.selectContents(contentsVO);
		int critTm = contentsVO.getCritTm(); //-- 기준시간 분

		for(int i=0; i<stdNoArray.length; i++) {
			BookmarkVO svo = new BookmarkVO();
			svo.setStdNo(stdNoArray[i]);
			svo.setSbjCd(iBookmarkVO.getSbjCd());
			svo.setUnitCd(iBookmarkVO.getUnitCd());
			svo.setStudyDttm(StringUtil.ReplaceAll(iBookmarkVO.getStudyDttm(), ".", ""));
			svo.setStudyDiv("CLS");
			svo.setSchlStudyDt(StringUtil.ReplaceAll(iBookmarkVO.getStudyDttm(), ".", ""));
			svo.setSchlStudyStartTm(iBookmarkVO.getSchlStudyStartTm());
			svo.setSchlStudyEndTm(iBookmarkVO.getSchlStudyEndTm());
			svo.setSchlStudyDivCd(iBookmarkVO.getSchlStudyDivCd());
			svo.setAprvUserNo(iBookmarkVO.getAprvUserNo());

			try {
				//-- 기존 정보가 있는 경우
				BookmarkVO ssvo = new BookmarkVO();
				ssvo.setStdNo(stdNoArray[i]);
				ssvo.setSbjCd(iBookmarkVO.getSbjCd());
				ssvo.setUnitCd(iBookmarkVO.getUnitCd());
				ssvo = bookmarkMapper.selectBookmark(ssvo);
				int connTotTm = ssvo.getConnTotTm() + (iBookmarkVO.getConnTm()*60); //-- 학습시간
				if(connTotTm > 999999999) connTotTm = 999999999;
				int connCnt = ssvo.getConnCnt() + 1;
				if(connCnt > 999) connCnt = 999;
				//float prgrRatio = (connTotTm / (critTm * 60))*100;
				//if(prgrRatio > 100) prgrRatio = 100;
				svo.setConnTotTm(connTotTm);
				svo.setConnCnt(connCnt); //-- 학습횟수 증가
				svo.setPrgrRatio(100); //-- 무조건 100%
				bookmarkMapper.updateBookmark(svo);
			} catch (Exception e) {
				//-- 기존 정보가 없는 경우
				//float prgrRatio = ((iBookmarkVO.getConnTm()*60) / (critTm * 60))*100;
				//if(prgrRatio > 100) prgrRatio = 100;
				int connTotTm = iBookmarkVO.getConnTm()*60;
				if(connTotTm > 999999999) connTotTm = 999999999;
				svo.setConnTotTm(connTotTm);
				svo.setConnCnt(1); //-- 학습횟수 증가.
				svo.setPrgrRatio(100); //-- 무조건 100%
				bookmarkMapper.insertBookmark(svo);
			}
			StudentVO stdVO = new StudentVO();
			stdVO.setStdNo(stdNoArray[i]);
			stdVO = studentMapper.selectStudent(stdVO);

			//-- 자동 성적 처리 :
			EduResultVO eduResultVO = new EduResultVO();
			eduResultVO.setCrsCreCd(stdVO.getCrsCreCd());
			eduResultVO.setStdNo(stdNoArray[i]);
			eduResultVO.setSbjCd(iBookmarkVO.getSbjCd());

			eduResultMapper.insertEduResultSp(eduResultVO);
		}
		return new ProcessResultVO<BookmarkVO>().setResultSuccess();
	}*/

	/**
	 * 교내학습관리 승인취소용
	 * @param iBookmarkVO
	 * @return
	 */
	/*public	ProcessResultVO<BookmarkVO> delClassBookmark(BookmarkVO iBookmarkVO) throws Exception  {
		//-- 교제의 정보를 가져온다.
		ContentsVO cvo = new ContentsVO();
		cvo.setSbjCd(iBookmarkVO.getSbjCd());
		cvo.setUnitCd(iBookmarkVO.getUnitCd());
		cvo = contentsMapper.selectContents(cvo);
		int critTm = cvo.getCritTm() * 60; //초로 환산함.

		String[] stdNoArray = StringUtil.split(iBookmarkVO.getStdNo(),",");

		for(int i=0; i<stdNoArray.length; i++) {
			BookmarkVO svo = new BookmarkVO();
			svo.setStdNo(stdNoArray[i]);
			svo.setSbjCd(iBookmarkVO.getSbjCd());
			svo.setUnitCd(iBookmarkVO.getUnitCd());
			//svo.setStudyDttm("null");
			svo.setStudyDiv("PER");
			//svo.setPrgrRatio(0);
			//svo.setConnTotTm(0);
			svo.setSchlStudyDt("");
			svo.setSchlStudyStartTm("");
			svo.setSchlStudyEndTm("");
			svo.setSchlStudyDivCd("");
			svo.setAprvUserNo("");
			try {
				//-- 기존 정보가 있는 경우
				BookmarkVO ssvo = new BookmarkVO();
				ssvo.setStdNo(stdNoArray[i]);
				ssvo.setSbjCd(iBookmarkVO.getSbjCd());
				ssvo.setUnitCd(iBookmarkVO.getUnitCd());
				ssvo = bookmarkMapper.selectBookmark(ssvo);

				//-- 기존 정보가 교내 학습인 경우에만 수정함.
				if("CLS".equals(ssvo.getStudyDiv())) {
					int connCnt = ssvo.getConnCnt() - 1;
					int prgrRatio = 0;
					int connTotTm = ssvo.getConnTotTm() - critTm;
					if(connTotTm < 0) connTotTm = 0;
					prgrRatio = Math.round(connTotTm/critTm*100);
					if(connCnt < 0) connCnt = 0;

					svo.setPrgrRatio(prgrRatio);
					svo.setConnTotTm(connTotTm);
					svo.setConnCnt(connCnt);

					bookmarkMapper.updateBookmark(svo);

					StudentVO stdVO = new StudentVO();
					stdVO.setStdNo(stdNoArray[i]);
					stdVO = studentMapper.selectStudent(stdVO);

					//-- 자동 성적 처리 :
					EduResultVO eduResultVO = new EduResultVO();
					eduResultVO.setCrsCreCd(stdVO.getCrsCreCd());
					eduResultVO.setStdNo(stdNoArray[i]);
					eduResultVO.setSbjCd(iBookmarkVO.getSbjCd());

					eduResultMapper.insertEduResultSp(eduResultVO);
				}
			} catch (Exception e) {

			}
		}
		return new ProcessResultVO<BookmarkVO>().setResultSuccess();
	}*/

	/**
	 * 공개강좌 교재 목록 조회 (단순 리스트 반환)
	 * @param sbjCd
	 * @param parUnitCd
	 * @return
	 */
	public ProcessResultListVO<BookmarkVO> openCourseListBookmark(BookmarkVO iBookmarkVO) throws Exception  {
		//---- 과목 관련 정보 검색
		OpenCrsSbjVO openCrsSbjVO = new OpenCrsSbjVO();
		openCrsSbjVO.setSbjCd(iBookmarkVO.getSbjCd());
		openCrsSbjVO.setCrsCd(iBookmarkVO.getCrsCd());
		openCrsSbjVO = openCrsSbjMapper.select(openCrsSbjVO);

		BookmarkVO bookmarkVO = new BookmarkVO();
		bookmarkVO.setSbjCd(iBookmarkVO.getSbjCd());
		bookmarkVO.setUnitType("R");
		bookmarkVO.setUnitNm(openCrsSbjVO.getSbjNm());
		bookmarkVO.setStdNo(iBookmarkVO.getStdNo());
		bookmarkVO.setParUnitCd("");
		bookmarkVO.setUnitLvl(0);

		List<BookmarkVO> resultListVO = bookmarkMapper.listBookmark(iBookmarkVO);

		for (BookmarkVO parent : resultListVO) {
			if(StringUtil.isNull(parent.getParUnitCd())) {
				bookmarkVO.addSubVO(parent);
			}
			for (BookmarkVO child : resultListVO) {
				if(parent.getUnitCd().equals(child.getParUnitCd())) {
					parent.addSubVO(child);
				}
			}
		}

		List<BookmarkVO> bookmarkList = new ArrayList<BookmarkVO>();
		if("Y".equals(openCrsSbjVO.getWinMenuUseYn())) {
			//-- 학습창 메뉴 사용인 경우 (최상위 목록만을 가져온다. 하위 목록의 진도율, 시간등 계산)
			listContentsArray(bookmarkVO, bookmarkList);
		} else {
			listContentsTree(bookmarkVO, bookmarkList);
		}
		ProcessResultListVO<BookmarkVO> resultList = new ProcessResultListVO<BookmarkVO>();
		resultList.setReturnList(bookmarkList);
		resultList.setResult(1);
		return resultList;
	}

	/**
	 * 학습자의 과정 최종 학습일자를 조회한다.
	 * @param iBookmarkVO
	 * @return
	 */
	@Override
	public ProcessResultVO<BookmarkVO> selectLastStudyDttm(BookmarkVO iBookmarkVO) throws Exception  {
		ProcessResultVO<BookmarkVO> resultVO = new ProcessResultVO<BookmarkVO>();
		try {
			BookmarkVO returnVO = bookmarkMapper.selectLastStudyDttm(iBookmarkVO);
			resultVO.setReturnVO(returnVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		return resultVO;
	}
	
	/**
	 * 학습자의 과정 최종 학습일자를 조회한다.
	 * @param iBookmarkVO
	 * @return
	 */
	@Override
	public ProcessResultVO<BookmarkVO> checkDayLimit(BookmarkVO iBookmarkVO) throws Exception  {
		ProcessResultVO<BookmarkVO> resultVO = new ProcessResultVO<BookmarkVO>();
		try {
			BookmarkVO returnVO = bookmarkMapper.checkDayLimit(iBookmarkVO);
			resultVO.setReturnVO(returnVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		
		return resultVO;
	}
	
	/**
	 * 학습자의 과정 최종 학습일자를 조회한다.
	 * @param iBookmarkVO
	 * @return
	 */
	@Override
	public ProcessResultVO<BookmarkVO> selectSbjBookmarkCnt(BookmarkVO iBookmarkVO) throws Exception  {
		ProcessResultVO<BookmarkVO> resultVO = new ProcessResultVO<BookmarkVO>();
		try {
			BookmarkVO returnVO = bookmarkMapper.selectSbjBookmarkCnt(iBookmarkVO);
			resultVO.setReturnVO(returnVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		
		return resultVO;
	}
	
	/**
	 * 진행단계평가 및 북마크 정보 조회
	 * @param iBookmarkVO
	 * @return
	 */
	@Override
	public ProcessResultVO<BookmarkVO> selectBookmarkInfo(BookmarkVO iBookmarkVO) throws Exception  {
		ProcessResultVO<BookmarkVO> resultVO = new ProcessResultVO<BookmarkVO>();
		try {
			BookmarkVO returnVO = bookmarkMapper.selectBookmarkInfo(iBookmarkVO);
			resultVO.setReturnVO(returnVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		
		return resultVO;
	}
	
	public	ProcessResultVO<BookmarkVO> addBookmarkLog(BookmarkVO iBookmarkVO) throws Exception {
		ProcessResultVO<BookmarkVO> resultVO = new ProcessResultVO<BookmarkVO>();
		try {
			bookmarkMapper.insertBookmarkLog(iBookmarkVO);
			resultVO.setReturnVO(iBookmarkVO);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}
		return resultVO;
	}
	
	/**
	 * 단원별 학습자의 진도율 목록을 반환한다.
	 * @param sbjCd
	 * @param parUnitCd
	 * @return
	 */
	public ProcessResultListVO<BookmarkVO> listBookmarkLog(BookmarkVO iBookmarkVO) throws Exception  {
		ProcessResultListVO<BookmarkVO> resultList = new ProcessResultListVO<BookmarkVO>();
		try {
			List<BookmarkVO> returnList =  bookmarkMapper.listBookmarkLog(iBookmarkVO);
			resultList.setResult(1);
			resultList.setReturnList(returnList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	@Override
	public int updateAttendSts(BookmarkVO vo) throws Exception {
		BookmarkVO chkBookmark = bookmarkMapper.selectBookmark(vo);
		
		if(chkBookmark == null) bookmarkMapper.insertBookmark(vo);
		else bookmarkMapper.updateAttendSts(vo);
		
		EduResultVO eduResult = new EduResultVO();
		eduResult.setCrsCreCd(vo.getCrsCreCd());
		eduResult.setSbjCd(vo.getSbjCd());
		eduResult.setStdNo(vo.getStdNo());
		
		vo.setScoreCategory("");
		vo.setScoreSaveType("");
		
		eduResultService.addEduResultSp(eduResult, vo);
		
		return 0;
	}

	@Override
	public int updateAttendStsList(List<BookmarkVO> list) throws Exception {
		for(BookmarkVO vo : list) {
			bookmarkService.updateAttendSts(vo);
		}
		return 0;
	}
}