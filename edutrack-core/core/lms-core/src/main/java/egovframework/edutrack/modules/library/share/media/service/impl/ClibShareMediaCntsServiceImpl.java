package egovframework.edutrack.modules.library.share.media.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.library.cnts.share.req.service.ClibCntsShareReqVO;
import egovframework.edutrack.modules.library.cnts.share.req.service.impl.ClibCntsShareReqMapper;
import egovframework.edutrack.modules.library.share.media.service.ClibShareMediaCntsService;
import egovframework.edutrack.modules.library.share.media.service.ClibShareMediaCntsVO;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Service("clibShareMediaCntsService")
public class ClibShareMediaCntsServiceImpl
	extends EgovAbstractServiceImpl implements ClibShareMediaCntsService {

	private final class NestedFileHandler
		implements FileHandler<ClibShareMediaCntsVO> {

		@Override
		public String getPK(ClibShareMediaCntsVO vo) {
			return vo.getCntsCd().toString();
		}

		@Override
		public String getRepoCd() {
			return "SHARE_MEDIA_CNTS";
		}

		@Override
		public List<SysFileVO> getFiles(ClibShareMediaCntsVO vo) {
			List<SysFileVO> fileList = new ArrayList<SysFileVO>();
			if(ValidationUtils.isNotZeroNull(vo.getThumbFileSn()))
				fileList.add(vo.getThumbFile());
			return fileList;
		}

		@Override
		public ClibShareMediaCntsVO setFiles(ClibShareMediaCntsVO vo, FileListVO fileListVO) {
			vo.setThumbFile(fileListVO.getFile("thumb"));
			return vo;
		}
	}

	@Resource(name="sysFileService")
	private SysFileService sysFileService;

	/** Mapper */
	@Resource(name="clibShareMediaCntsMapper")
	private ClibShareMediaCntsMapper 	clibShareMediaCntsMapper;

	@Resource(name="clibCntsShareReqMapper")
	private ClibCntsShareReqMapper clibCntsShareReqMapper;

	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 공유 승인완료 목록을 가져온다.
	 * @param ClibShareMediaCntsVO.orgCd
	 * @param ClibShareMediaCntsVO.ctgrCd
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<ClibShareMediaCntsVO> list(ClibShareMediaCntsVO vo) throws Exception  {
		ProcessResultListVO<ClibShareMediaCntsVO> resultList = new ProcessResultListVO<ClibShareMediaCntsVO>();
		try {
			List<ClibShareMediaCntsVO> returnList = clibShareMediaCntsMapper.list(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}


	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 공유 승인완료 페이징 목록을 가져온다.
	 * @param ClibShareMediaCntsVO.orgCd
	 * @param ClibShareMediaCntsVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<ClibShareMediaCntsVO> listPageing(ClibShareMediaCntsVO vo) throws Exception  {

		ProcessResultListVO<ClibShareMediaCntsVO> resultList = new ProcessResultListVO<ClibShareMediaCntsVO>();
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(vo.getCurPage());
			paginationInfo.setRecordCountPerPage(vo.getListScale());
			paginationInfo.setPageSize(vo.getPageScale());
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = clibShareMediaCntsMapper.listPageingCount(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<ClibShareMediaCntsVO> returnList = clibShareMediaCntsMapper.listPageing(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		
		
		return resultList;
	}


	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 전체 목록을 가져온다.
	 * @param ClibShareMediaCntsVO.orgCd
	 * @param ClibShareMediaCntsVO.ctgrCd
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<ClibShareMediaCntsVO> listManage(ClibShareMediaCntsVO vo) throws Exception  {
		ProcessResultListVO<ClibShareMediaCntsVO> resultList = new ProcessResultListVO<ClibShareMediaCntsVO>();
		try {
			List<ClibShareMediaCntsVO> returnList = clibShareMediaCntsMapper.listManage(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 페이징 목록을 가져온다.
	 * @param ClibShareMediaCntsVO.orgCd
	 * @param ClibShareMediaCntsVO.ctgrCd
	 * @param curPage
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<ClibShareMediaCntsVO> listManagePageing(ClibShareMediaCntsVO vo, int curPage) throws Exception  {
		
		return this.listManagePageing(vo, curPage, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}

	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 페이징 목록을 가져온다.
	 * @param ClibShareMediaCntsVO.orgCd
	 * @param ClibShareMediaCntsVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<ClibShareMediaCntsVO> listManagePageing(ClibShareMediaCntsVO vo, int curPage, int listScale) throws Exception  {

		return this.listManagePageing(vo, curPage, listScale, Constants.LIST_PAGE_SCALE);
	}

	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 페이징 목록을 가져온다.
	 * @param ClibShareMediaCntsVO.orgCd
	 * @param ClibShareMediaCntsVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<ClibShareMediaCntsVO> listManagePageing(ClibShareMediaCntsVO vo, int curPage, int listScale, int pageScale) throws Exception  {

		ProcessResultListVO<ClibShareMediaCntsVO> resultList = new ProcessResultListVO<ClibShareMediaCntsVO>();
		
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(curPage);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = clibShareMediaCntsMapper.listManagePageingCount(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<ClibShareMediaCntsVO> returnList = clibShareMediaCntsMapper.listManagePageing(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}

		
		return resultList;
	}

	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 단일항목 정보를 가져온다.
	 * @param ClibShareMediaCntsVO.orgCd
	 * @param ClibShareMediaCntsVO.userNo
	 * @param ClibShareMediaCntsVO.ctgrCd
	 * @return ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ClibShareMediaCntsVO> view(ClibShareMediaCntsVO vo) throws Exception  {
		vo = clibShareMediaCntsMapper.select(vo);
		vo = sysFileService.getFile(vo, new NestedFileHandler());
		return new ProcessResultVO<ClibShareMediaCntsVO>().setResultSuccess()
				.setReturnVO(vo);
	}

	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 단일항목 정보를 등록하고 결과를 가져온다.
	 * @param ClibShareMediaCntsVO
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ClibShareMediaCntsVO> add(ClibShareMediaCntsVO vo) throws Exception {
		//---- 콘텐츠 코드 생성
		//ClibShareMediaCntsVO cccvo = clibShareMediaCntsMapper.selectCntsCd().getReturnVO();
		String cntsCd = StringUtil.generateNewId("SMC");
		//---- 신규 콘텐츠 코드 세팅
		vo.setCntsCd(cntsCd);

		//-- 콘텐츠 등록
		clibShareMediaCntsMapper.insert(vo);

		// 주키가 생성된 이후 첨부파일번호들을 바인딩해서 저장
		sysFileService.bindFile(vo, new NestedFileHandler());
		return new ProcessResultVO<ClibShareMediaCntsVO>().setReturnVO(vo).setResultSuccess();
	}

	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 단일항목 정보를 수정하고 결과를 가져온다.
	 * @param ClibShareMediaCntsVO
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ClibShareMediaCntsVO> edit(ClibShareMediaCntsVO vo) throws Exception {
		clibShareMediaCntsMapper.update(vo);
		sysFileService.bindFileUpdate(vo, new NestedFileHandler());
		return new ProcessResultVO<ClibShareMediaCntsVO>().setReturnVO(vo).setResultSuccess();
	}



	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 단일항목 정보를 삭제하고 결과를 가져온다.
	 * @param ClibShareMediaCntsVO.orgCd
	 * @param ClibShareMediaCntsVO.userNo
	 * @param ClibShareMediaCntsVO.ctgrCd
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ClibShareMediaCntsVO> delete(ClibShareMediaCntsVO vo) throws Exception {
		//-- 사용중인 콘텐츠 인지 검색 하여 삭제 할 것인지, 공유 해제 할 것 인지 선택


		sysFileService.removeFile(vo, new NestedFileHandler()); // 파일정보 삭제..
		clibShareMediaCntsMapper.delete(vo);
		return ProcessResultVO.success();
	}

	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 순서를 변경하고 결과를 반환한다.
	 * @param OlcCntsVO
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<?> move(ClibShareMediaCntsVO vo, String moveType)  throws Exception {

		//-- 해당 카테고리 내의 모든 콘텐츠  목록을 구한다.
		List<ClibShareMediaCntsVO> olcCtgrList = clibShareMediaCntsMapper.list(vo);
		List<ClibShareMediaCntsVO> newCtgrList = new ArrayList<ClibShareMediaCntsVO>();
		int lineCnt = 0;
		if("up".equals(moveType)) {
			//-- 위로
			for(ClibShareMediaCntsVO cccvo : olcCtgrList) {
				//-- 코드가 같으면 하나 위의 omvo를 가져오고. 작업해 보자
				if(cccvo.getCtgrCd().equals(vo.getCtgrCd())) {
					ClibShareMediaCntsVO socvo = newCtgrList.get(lineCnt - 1); // 하나 위의 메뉴 받아오기
					newCtgrList.remove(lineCnt - 1); //-- 하나위의 목록을 삭제
					newCtgrList.add(cccvo);
					newCtgrList.add(socvo);
				} else {
					newCtgrList.add(cccvo);
				}
				lineCnt++;
			}
		} else if("down".equals(moveType)) {
			//-- 메뉴 아래로
			ClibShareMediaCntsVO ncccvo = null;

			for(ClibShareMediaCntsVO cccvo : olcCtgrList) {
				if(cccvo.getCtgrCd().equals(vo.getCtgrCd())) {
					ncccvo = cccvo;
				} else {
					newCtgrList.add(cccvo);
					if(ValidationUtils.isNotEmpty(ncccvo)) {
						newCtgrList.add(ncccvo);
						ncccvo = null;
					}
				}
			}
		}
		int order = 0;
		for(ClibShareMediaCntsVO dvo : newCtgrList) {
			order++;
			dvo.setCntsOdr(order);
		}
		clibShareMediaCntsMapper.updateBatch(newCtgrList);
		return ProcessResultVO.success();
	}

	/**
	 * 콘텐츠 라리브러리 : 원본 콘텐츠의 공유 미디어 콘텐츠 전체 목록을 가져온다.
	 * @param ClibShareMediaCntsVO.orgCd
	 * @param ClibShareMediaCntsVO.originCntsCd
	 * @param ClibShareMediaCntsVO.ctgrCd
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<ClibShareMediaCntsVO> listByOrigin(ClibShareMediaCntsVO vo)  throws Exception {
		ProcessResultListVO<ClibShareMediaCntsVO> resultList = new ProcessResultListVO<ClibShareMediaCntsVO>();
		try {
			List<ClibShareMediaCntsVO> returnList = clibShareMediaCntsMapper.listByOrigin(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	/**
	 * 콘텐츠 라리브러리 : 원본 콘텐츠의 공유 미디어 콘텐츠 페이징 목록을 가져온다.
	 * @param ClibShareMediaCntsVO.orgCd
	 * @param ClibShareMediaCntsVO.originCntsCd
	 * @param ClibShareMediaCntsVO.ctgrCd
	 * @param curPage
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<ClibShareMediaCntsVO> listByOriginPageing(ClibShareMediaCntsVO vo, int curPage)  throws Exception {

		return this.listByOriginPageing(vo, curPage, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}

	/**
	 * 콘텐츠 라리브러리 : 원본 콘텐츠의 공유 미디어 콘텐츠 페이징 목록을 가져온다.
	 * @param ClibShareMediaCntsVO.orgCd
	 * @param ClibShareMediaCntsVO.originCntsCd
	 * @param ClibShareMediaCntsVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<ClibShareMediaCntsVO> listByOriginPageing(ClibShareMediaCntsVO vo, int curPage, int listScale)  throws Exception {
		return this.listByOriginPageing(vo, curPage, listScale, Constants.LIST_PAGE_SCALE);
	}

	/**
	 * 콘텐츠 라리브러리 : 원본 콘텐츠의 공유 미디어 콘텐츠 페이징 목록을 가져온다.
	 * @param ClibShareMediaCntsVO.orgCd
	 * @param ClibShareMediaCntsVO.originCntsCd
	 * @param ClibShareMediaCntsVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<ClibShareMediaCntsVO> listByOriginPageing(ClibShareMediaCntsVO vo, int curPage, int listScale, int pageScale)  throws Exception {
		ProcessResultListVO<ClibShareMediaCntsVO> resultList = new ProcessResultListVO<ClibShareMediaCntsVO>();
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(curPage);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());		

			// 전체 목록 수
			int totalCount = clibShareMediaCntsMapper.listByOriginPageingCount(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<ClibShareMediaCntsVO> returnList = clibShareMediaCntsMapper.listByOriginPageing(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
			resultList.setPageInfo(paginationInfo);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 OLC제작용 목록을 가져온다.
	 * @param ClibShareMediaCntsVO.orgCd
	 * @param ClibShareMediaCntsVO.ctgrCd
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<ClibShareMediaCntsVO> listOlc(ClibShareMediaCntsVO vo)  throws Exception {
		ProcessResultListVO<ClibShareMediaCntsVO> resultList = new ProcessResultListVO<ClibShareMediaCntsVO>();
		try {
			List<ClibShareMediaCntsVO> returnList = clibShareMediaCntsMapper.listOlc(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}

		return resultList;
	}

	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 공유해제
	 * @param ClibShareMediaCntsVO
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ClibShareMediaCntsVO> updateNoShare(ClibShareMediaCntsVO vo)  throws Exception {

		clibShareMediaCntsMapper.updateNoShare(vo);
		return new ProcessResultVO<ClibShareMediaCntsVO>().setReturnVO(vo).setResultSuccess();
	}

	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 공유거절
	 * @param ClibShareMediaCntsVO
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ClibShareMediaCntsVO> rejectShare(ClibShareMediaCntsVO vo)  throws Exception {

		//--콘텐츠 공유신청 데이터 삭제
		ClibCntsShareReqVO ccsrvo = new ClibCntsShareReqVO();
		ccsrvo.setCntsCd(vo.getCntsCd());
		ccsrvo.setModNo(vo.getModNo());
		clibCntsShareReqMapper.rejectCnts(ccsrvo);

		return new ProcessResultVO<ClibShareMediaCntsVO>().setReturnVO(vo).setResultSuccess();
	}

	/**
	 * 콘텐츠 라리브러리 : 공유 미디어 콘텐츠 공유신청관리
	 * @param ClibShareMediaCntsVO
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ClibShareMediaCntsVO> updateShareCd(ClibShareMediaCntsVO vo)  throws Exception {
		clibShareMediaCntsMapper.updateShareMedia(vo);

		//--콘텐츠 공유신청 승인상태변경
		ClibCntsShareReqVO ccsrvo = new ClibCntsShareReqVO();
		ccsrvo.setModNo(vo.getModNo());
		ccsrvo.setReqSn(vo.getReqSn());
		ccsrvo.setShareStsCd(vo.getShareReqStsCd());
		clibCntsShareReqMapper.updateShareCd(ccsrvo);
		return new ProcessResultVO<ClibShareMediaCntsVO>().setReturnVO(vo).setResultSuccess();
	}


}
