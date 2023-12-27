package egovframework.edutrack.modules.board.qna.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.board.qna.service.BrdQnaAnsrVO;
import egovframework.edutrack.modules.board.qna.service.BrdQnaQstnVO;
import egovframework.edutrack.modules.board.qna.service.BrdQnaService;
import egovframework.edutrack.modules.course.createcourse.service.impl.CreateCourseMapper;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Service("brdQnaService")
public class BrdQnaServiceImpl 
	extends EgovAbstractServiceImpl implements BrdQnaService {
	
	private final class QstnNestedFileHandler implements FileHandler<BrdQnaQstnVO> {
		@Override
		public String getPK(BrdQnaQstnVO vo) {
			return vo.getQnaSn().toString();
		}
	
		@Override
		public String getRepoCd() {
			return "QNA_QSTN";
		}
	
		@Override
		public List<SysFileVO> getFiles(BrdQnaQstnVO vo) {
			List<SysFileVO> fileList = vo.getAttachImages();
			fileList.addAll(vo.getAttachFiles());

			return fileList;
		}
	
		@Override
		public BrdQnaQstnVO setFiles(BrdQnaQstnVO vo, FileListVO fileListVO) {
			vo.setAttachImages(fileListVO.getFiles("image"));
			vo.setAttachFiles(fileListVO.getFiles("file"));
			return vo;
		}
	}
	
	private final class AnsrNestedFileHandler implements FileHandler<BrdQnaAnsrVO> {
		@Override
		public String getPK(BrdQnaAnsrVO vo) {
			return vo.getQnaSn().toString();
		}
	
		@Override
		public String getRepoCd() {
			return "QNA_ANSR";
		}
	
		@Override
		public List<SysFileVO> getFiles(BrdQnaAnsrVO vo) {
			List<SysFileVO> fileList = vo.getAttachImages();
			fileList.addAll(vo.getAttachFiles());

			return fileList;
		}
	
		@Override
		public BrdQnaAnsrVO setFiles(BrdQnaAnsrVO vo, FileListVO fileListVO) {
			vo.setAttachImages(fileListVO.getFiles("image"));
			vo.setAttachFiles(fileListVO.getFiles("file"));
			return vo;
		}
	}

	/** Mapper */
	@Resource(name="createCourseMapper")
	private CreateCourseMapper createCourseMapper;
	
    @Resource(name="brdQnaQstnMapper")
    private BrdQnaQstnMapper brdQnaQstnMapper;
    
    @Resource(name="brdQnaAnsrMapper")
    private BrdQnaAnsrMapper brdQnaAnsrMapper;
    
	@Resource(name="sysFileService")
	private SysFileService 		sysFileService;
	

	/**
	 * 1대1 문의 목록 페이징 조회
	 * @param BrdQnaQstnVO
	 * @param boolean
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<BrdQnaQstnVO> listPageing(BrdQnaQstnVO vo, boolean fileIn) throws Exception {
		ProcessResultListVO<BrdQnaQstnVO> resultList = new ProcessResultListVO<BrdQnaQstnVO>(); 
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(vo.getCurPage());
		paginationInfo.setRecordCountPerPage(vo.getListScale());
		paginationInfo.setPageSize(vo.getPageScale());
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		
		// 전체 목록 수
		int totalCount = brdQnaQstnMapper.count(vo);
		paginationInfo.setTotalRecordCount(totalCount);
		
		List<BrdQnaQstnVO> qstnList = new ArrayList<>();
		if("Y".equals(vo.getLecYn())) {
			qstnList = brdQnaQstnMapper.lecListPageing(vo);
		} else {
			qstnList =  brdQnaQstnMapper.listPageing(vo);
		}
		
		if(fileIn) {
			List<BrdQnaQstnVO> tempQstnList = new ArrayList<BrdQnaQstnVO>();
			for(BrdQnaQstnVO qstn : qstnList) {
				BrdQnaQstnVO tempQstn = sysFileService.getFile(qstn, new QstnNestedFileHandler());
				tempQstnList.add(tempQstn);
			}
			qstnList = tempQstnList;
		}
		resultList.setResult(1);
		resultList.setReturnList(qstnList);
		resultList.setPageInfo(paginationInfo);
		
		return resultList;
	}
	
	/**
	 * 1대1 문의 목록 페이징 조회(첨부파일 제외)
	 * @param BrdQnaQstnVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<BrdQnaQstnVO> listPageing(BrdQnaQstnVO vo) throws Exception {
		return this.listPageing(vo, false);
	}
	
	/**
	 * 1대1 문의 목록 조회(페이징,첨부파일 제외)
	 * @param BrdQnaQstnVO
	 * @return ProcessResultListVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultListVO<BrdQnaQstnVO> qnaList(BrdQnaQstnVO vo) throws Exception {
		ProcessResultListVO<BrdQnaQstnVO> resultList = new ProcessResultListVO<BrdQnaQstnVO>(); 

		List<BrdQnaQstnVO> qstnList = new ArrayList<>();
		if("Y".equals(vo.getLecYn())) {
			qstnList = brdQnaQstnMapper.lecList(vo);
		} else {
			//qstnList =  brdQnaQstnMapper.listPageing(vo);
		}

		resultList.setResult(1);
		resultList.setReturnList(qstnList);
		
		return resultList;
	}

	/**
	 * 1대1 문의 작성
	 * @param BrdQnaQstnVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int writeQstn(BrdQnaQstnVO vo) throws Exception {
		vo.setQnaSn(brdQnaQstnMapper.selectKey());
		sysFileService.bindFile(vo, new QstnNestedFileHandler());
		return brdQnaQstnMapper.insert(vo);
	}

	/**
	 * 1대1 문의 수정
	 * @param BrdQnaQstnVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int editQstn(BrdQnaQstnVO vo) throws Exception {
		sysFileService.bindFileUpdate(vo, new QstnNestedFileHandler());
		return brdQnaQstnMapper.update(vo);
	}

	/**
	 * 1대1 문의 삭제
	 * @param BrdQnaQstnVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int deleteQstn(BrdQnaQstnVO vo) throws Exception {
		sysFileService.removeFile(vo, new QstnNestedFileHandler());
		return brdQnaQstnMapper.delete(vo);
	}

	/**
	 * 1대1 문의 상세조회
	 * @param BrdQnaQstnVO
	 * @return BrdQnaQstnVO
	 * @throws Exception
	 */
	@Override
	public BrdQnaQstnVO viewQstn(BrdQnaQstnVO vo) throws Exception {
		BrdQnaQstnVO qstn = brdQnaQstnMapper.select(vo);
		if(qstn == null) {
			throw new Exception("해당하는 문의가 존재하지 않습니다.");
		}
		qstn = sysFileService.getFile(qstn, new QstnNestedFileHandler());
		return qstn;
	}
	
	@Override
	public BrdQnaQstnVO viewLecQstn(BrdQnaQstnVO vo) throws Exception {
		BrdQnaQstnVO qstn = brdQnaQstnMapper.lecSelect(vo);
		if(qstn == null) {
			throw new Exception("해당하는 문의가 존재하지 않습니다.");
		}
		qstn = sysFileService.getFile(qstn, new QstnNestedFileHandler());
		return qstn;
	}	

	/**
	 * 1대1 문의 답변 작성
	 * @param BrdQnaAnsrVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int writeAnsr(BrdQnaAnsrVO vo) throws Exception {
		BrdQnaQstnVO qstnVO = new BrdQnaQstnVO();
		qstnVO.setQnaSn(vo.getQnaSn());
		qstnVO.setStsCd("COMP");
		brdQnaQstnMapper.updateQnaSts(qstnVO);
		
		sysFileService.bindFile(vo, new AnsrNestedFileHandler());
		return brdQnaAnsrMapper.insert(vo);
	}

	/**
	 * 1대1 문의 답변 상세 조회
	 * @param BrdQnaAnsrVO
	 * @return ProcessResultVO
	 * @throws Exception
	 */
	@Override
	public ProcessResultVO<BrdQnaAnsrVO> viewAnsr(BrdQnaAnsrVO vo) throws Exception {
		ProcessResultVO<BrdQnaAnsrVO> result = new ProcessResultVO<>();
		BrdQnaAnsrVO ansr = brdQnaAnsrMapper.select(vo);
		if(ansr != null) {
			ansr = sysFileService.getFile(ansr, new AnsrNestedFileHandler());
		}
		return result.setReturnVO(ansr);
	}

	/**
	 * 1대1 문의 답변 수정
	 * @param BrdQnaAnsrVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int editAnsr(BrdQnaAnsrVO vo) throws Exception {
		sysFileService.bindFileUpdate(vo, new AnsrNestedFileHandler());
		return brdQnaAnsrMapper.update(vo);
	}
	
	@Override
	public int deleteAnsr(BrdQnaAnsrVO vo) throws Exception {
		BrdQnaQstnVO qstnVO = new BrdQnaQstnVO();
		qstnVO.setQnaSn(vo.getQnaSn());
		qstnVO.setStsCd("WAIT");
		brdQnaQstnMapper.updateQnaSts(qstnVO);
		
		sysFileService.removeFile(vo, new AnsrNestedFileHandler());
		return brdQnaAnsrMapper.delete(vo);
	}

	/**
	 * 1대1 문의 및 답변 삭제
	 * @param BrdQnaAnsrVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int deleteQstnAndAnsr(BrdQnaQstnVO vo) throws Exception {
		BrdQnaAnsrVO ansrVO = new BrdQnaAnsrVO();
		ansrVO.setQnaSn(vo.getQnaSn());
		sysFileService.removeFile(ansrVO, new AnsrNestedFileHandler());
		brdQnaAnsrMapper.delete(ansrVO);
		
		sysFileService.removeFile(vo, new QstnNestedFileHandler());
		return brdQnaQstnMapper.delete(vo);
	}
	
	@Override
	public List<BrdQnaQstnVO> getParCntsList(BrdQnaQstnVO vo) throws Exception {
		return brdQnaQstnMapper.parCntsListByCreCd(vo);
	}
}
