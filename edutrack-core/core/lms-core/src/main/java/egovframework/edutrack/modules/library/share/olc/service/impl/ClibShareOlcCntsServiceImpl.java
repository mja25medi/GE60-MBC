package egovframework.edutrack.modules.library.share.olc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.library.cnts.share.req.service.ClibCntsShareReqVO;
import egovframework.edutrack.modules.library.cnts.share.req.service.impl.ClibCntsShareReqMapper;
import egovframework.edutrack.modules.library.share.olc.service.ClibShareOlcCntsService;
import egovframework.edutrack.modules.library.share.olc.service.ClibShareOlcCntsVO;
import egovframework.edutrack.modules.library.share.olc.service.ClibShareOlcPageVO;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Service("clibShareOlcCntsService")
public class ClibShareOlcCntsServiceImpl extends EgovAbstractServiceImpl implements ClibShareOlcCntsService {

	private final class NestedFileHandler
		implements FileHandler<ClibShareOlcCntsVO> {

		@Override
		public String getPK(ClibShareOlcCntsVO vo) {
			return vo.getCntsCd().toString();
		}

		@Override
		public String getRepoCd() {
			return "SHARE_OLC_CNTS";
		}

		@Override
		public List<SysFileVO> getFiles(ClibShareOlcCntsVO vo) {
			List<SysFileVO> fileList = new ArrayList<SysFileVO>();
			if(ValidationUtils.isNotZeroNull(vo.getThumbFileSn()))
				fileList.add(vo.getThumbFile());
			return fileList;
		}

		@Override
		public ClibShareOlcCntsVO setFiles(ClibShareOlcCntsVO vo, FileListVO fileListVO) {
			vo.setThumbFile(fileListVO.getFile("thumb"));
			return vo;
		}
	}

	@Resource(name="sysFileService")
	private SysFileService sysFileService;

	/** Mapper */
	@Resource(name="clibShareOlcCntsMapper")
	private ClibShareOlcCntsMapper clibShareOlcCntsMapper;

	@Resource(name="clibShareOlcPageMapper")
	private ClibShareOlcPageMapper clibShareOlcPageMapper;

	@Resource(name="clibCntsShareReqMapper")
	private ClibCntsShareReqMapper clibCntsShareReqMapper;

	/**
	 * 콘텐츠 라리브러리 : 공유 OLC 콘텐츠 공유 승인완료목록을 가져온다.
	 * @param ClibShareOlcCntsVO.orgCd
	 * @param ClibShareOlcCntsVO.userNo
	 * @param ClibShareOlcCntsVO.ctgrCd
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<ClibShareOlcCntsVO> list(ClibShareOlcCntsVO vo) throws Exception {
		ProcessResultListVO<ClibShareOlcCntsVO> resultList = new ProcessResultListVO<ClibShareOlcCntsVO>();
		try {
			List<ClibShareOlcCntsVO> returnList = clibShareOlcCntsMapper.list(vo);
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
	 * 콘텐츠 라리브러리 : 공유 OLC 콘텐츠 공유 승인완료 페이징 목록을 가져온다.
	 * @param ClibShareOlcCntsVO.orgCd
	 * @param ClibShareOlcCntsVO.userNo
	 * @param ClibShareOlcCntsVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<ClibShareOlcCntsVO> listPageing(ClibShareOlcCntsVO vo) throws Exception  {

		
		ProcessResultListVO<ClibShareOlcCntsVO> resultList = new ProcessResultListVO<ClibShareOlcCntsVO>();
		
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(vo.getCurPage());
			paginationInfo.setRecordCountPerPage(vo.getListScale());
			paginationInfo.setPageSize(vo.getPageScale());
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());

			// 전체 목록 수
			int totalCount = clibShareOlcCntsMapper.listManagePageingCount(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<ClibShareOlcCntsVO> returnList = clibShareOlcCntsMapper.listPageing(vo);
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
	 * 콘텐츠 라리브러리 : 공유 OLC 콘텐츠 전체 목록을 가져온다.
	 * @param ClibShareOlcCntsVO.orgCd
	 * @param ClibShareOlcCntsVO.userNo
	 * @param ClibShareOlcCntsVO.ctgrCd
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<ClibShareOlcCntsVO> listManage(ClibShareOlcCntsVO vo) throws Exception  {
		ProcessResultListVO<ClibShareOlcCntsVO> resultList = new ProcessResultListVO<ClibShareOlcCntsVO>();
		try {
			List<ClibShareOlcCntsVO> returnList = clibShareOlcCntsMapper.listManage(vo);
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
	 * 콘텐츠 라리브러리 : 공유 OLC 콘텐츠 페이징 목록을 가져온다.
	 * @param ClibShareOlcCntsVO.orgCd
	 * @param ClibShareOlcCntsVO.userNo
	 * @param ClibShareOlcCntsVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<ClibShareOlcCntsVO> listManagePageing(ClibShareOlcCntsVO vo) throws Exception  {
		ProcessResultListVO<ClibShareOlcCntsVO> resultList = new ProcessResultListVO<ClibShareOlcCntsVO>();
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(vo.getCurPage());
			paginationInfo.setRecordCountPerPage(vo.getListScale());
			paginationInfo.setPageSize(vo.getPageScale());
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = clibShareOlcCntsMapper.listManagePageingCount(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<ClibShareOlcCntsVO> returnList = clibShareOlcCntsMapper.listManagePageing(vo);
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
	 * 콘텐츠 라리브러리 : 공유 OLC 콘텐츠 단일항목 정보를 가져온다.
	 * @param ClibShareOlcCntsVO.orgCd
	 * @param ClibShareOlcCntsVO.userNo
	 * @param ClibShareOlcCntsVO.ctgrCd
	 * @return ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ClibShareOlcCntsVO> view(ClibShareOlcCntsVO vo) throws Exception  {
		vo = clibShareOlcCntsMapper.select(vo);
		vo = sysFileService.getFile(vo, new NestedFileHandler());
		return new ProcessResultVO<ClibShareOlcCntsVO>().setResultSuccess().setReturnVO(vo);
	}

	/**
	 * 콘텐츠 라리브러리 : 공유 OLC 콘텐츠 단일항목 정보를 등록하고 결과를 가져온다.
	 * @param ClibShareOlcCntsVO
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ClibShareOlcCntsVO> add(ClibShareOlcCntsVO vo) throws Exception  {
		//---- 콘텐츠 코드 생성
		ClibShareOlcCntsVO csocvo = clibShareOlcCntsMapper.selectCntsCd();

		//---- 신규 콘텐츠 코드 세팅
		vo.setCntsCd(csocvo.getCntsCd());

		//-- 콘텐츠 등록
		clibShareOlcCntsMapper.insert(vo);

		// 주키가 생성된 이후 첨부파일번호들을 바인딩해서 저장
		sysFileService.bindFile(vo, new NestedFileHandler());

		return new ProcessResultVO<ClibShareOlcCntsVO>().setReturnVO(vo).setResultSuccess();
	}

	/**
	 * 콘텐츠 라리브러리 : 공유 OLC 콘텐츠 단일항목 정보를 수정하고 결과를 가져온다.
	 * @param ClibShareOlcCntsVO
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ClibShareOlcCntsVO> edit(ClibShareOlcCntsVO vo) throws Exception  {
		clibShareOlcCntsMapper.update(vo);
		sysFileService.bindFileUpdate(vo, new NestedFileHandler());

		return new ProcessResultVO<ClibShareOlcCntsVO>().setReturnVO(vo).setResultSuccess();
	}

	/**
	 * 콘텐츠 라리브러리 : 공유 OLC 콘텐츠 단일항목 정보를 삭제하고 결과를 가져온다.
	 * @param ClibShareOlcCntsVO.orgCd
	 * @param ClibShareOlcCntsVO.userNo
	 * @param ClibShareOlcCntsVO.ctgrCd
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ClibShareOlcCntsVO> delete(ClibShareOlcCntsVO vo) throws Exception  {
		//공유 OLC콘텐츠 하위 페이지 삭제
		ClibShareOlcPageVO scopvo = new ClibShareOlcPageVO();
		scopvo.setCntsCd(vo.getCntsCd());
		clibShareOlcPageMapper.delete(scopvo);

		//공유 OLC콘텐츠 삭제
		clibShareOlcCntsMapper.delete(vo);

		return ProcessResultVO.success();
	}

	/**
	 * 콘텐츠 라리브러리 : 공유 OLC 콘텐츠 순서를 변경하고 결과를 반환한다.
	 * @param ClibShareOlcCntsVO
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<?> move(ClibShareOlcCntsVO vo, String moveType) throws Exception  {

		//-- 해당 카테고리 내의 모든 콘텐츠  목록을 구한다.
		List<ClibShareOlcCntsVO> olcCtgrList = clibShareOlcCntsMapper.list(vo);
		List<ClibShareOlcCntsVO> newCtgrList = new ArrayList<ClibShareOlcCntsVO>();
		int lineCnt = 0;
		if("up".equals(moveType)) {
			//-- 위로
			for(ClibShareOlcCntsVO cccvo : olcCtgrList) {
				//-- 코드가 같으면 하나 위의 omvo를 가져오고. 작업해 보자
				if(cccvo.getCtgrCd().equals(vo.getCtgrCd())) {
					ClibShareOlcCntsVO socvo = newCtgrList.get(lineCnt - 1); // 하나 위의 메뉴 받아오기
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
			ClibShareOlcCntsVO ncccvo = null;

			for(ClibShareOlcCntsVO cccvo : olcCtgrList) {
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
		for(ClibShareOlcCntsVO dvo : newCtgrList) {
			order++;
			dvo.setCntsOdr(order);
		}
		clibShareOlcCntsMapper.updateBatch(newCtgrList);
		return ProcessResultVO.success();
	}

	/**
	 * 콘텐츠 라리브러리 : 원본 콘텐츠의 공유 OLC 콘텐츠 전체 목록을 가져온다.
	 * @param ClibShareOlcCntsVO.orgCd
	 * @param ClibShareOlcCntsVO.originCntsCd
	 * @param ClibShareOlcCntsVO.ctgrCd
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<ClibShareOlcCntsVO> listByOrigin(ClibShareOlcCntsVO vo) throws Exception  {
		ProcessResultListVO<ClibShareOlcCntsVO> resultList = new ProcessResultListVO<ClibShareOlcCntsVO>();
		try {
			List<ClibShareOlcCntsVO> returnList = clibShareOlcCntsMapper.listByOrigin(vo);
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
	 * 콘텐츠 라리브러리 : 원본 콘텐츠의 공유 OLC 콘텐츠 페이징 목록을 가져온다.
	 * @param ClibShareOlcCntsVO.orgCd
	 * @param ClibShareOlcCntsVO.originCntsCd
	 * @param ClibShareOlcCntsVO.ctgrCd
	 * @param curPage
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<ClibShareOlcCntsVO> listByOriginPageing(ClibShareOlcCntsVO vo, int curPage) throws Exception  {
		return this.listByOriginPageing(vo, curPage, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}

	/**
	 * 콘텐츠 라리브러리 : 원본 콘텐츠의 공유 OLC 콘텐츠 페이징 목록을 가져온다.
	 * @param ClibShareOlcCntsVO.orgCd
	 * @param ClibShareOlcCntsVO.originCntsCd
	 * @param ClibShareOlcCntsVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<ClibShareOlcCntsVO> listByOriginPageing(
			ClibShareOlcCntsVO vo, int curPage, int listScale)  throws Exception {

		return this.listByOriginPageing(vo, curPage, listScale, Constants.LIST_PAGE_SCALE);
	}

	/**
	 * 콘텐츠 라리브러리 : 원본 콘텐츠의 공유 OLC 콘텐츠 페이징 목록을 가져온다.
	 * @param ClibShareOlcCntsVO.orgCd
	 * @param ClibShareOlcCntsVO.originCntsCd
	 * @param ClibShareOlcCntsVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<ClibShareOlcCntsVO> listByOriginPageing(
			ClibShareOlcCntsVO vo, int curPage, int listScale, int pageScale) throws Exception  {
		ProcessResultListVO<ClibShareOlcCntsVO> resultList = new ProcessResultListVO<ClibShareOlcCntsVO>();
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(curPage);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = clibShareOlcCntsMapper.listByOriginPageingCount(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<ClibShareOlcCntsVO> returnList = clibShareOlcCntsMapper.listByOriginPageing(vo);
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
	 * 콘텐츠 라리브러리 : 공유 OLC 콘텐츠 공유해제
	 * @param ClibShareOlcCntsVO
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ClibShareOlcCntsVO> updateNoShare(ClibShareOlcCntsVO vo)  throws Exception {

		clibShareOlcCntsMapper.updateNoShare(vo);
		return new ProcessResultVO<ClibShareOlcCntsVO>().setReturnVO(vo).setResultSuccess();
	}

	/**
	 * 콘텐츠 라리브러리 : 공유 OLC 콘텐츠 공유해제
	 * @param ClibShareOlcCntsVO
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ClibShareOlcCntsVO> rejectShare(ClibShareOlcCntsVO vo)  throws Exception {

		//--콘텐츠 공유신청 데이터 삭제
		ClibCntsShareReqVO ccsrvo = new ClibCntsShareReqVO();
		ccsrvo.setCntsCd(vo.getCntsCd());
		ccsrvo.setModNo(vo.getModNo());
		clibCntsShareReqMapper.rejectCnts(ccsrvo);

		return new ProcessResultVO<ClibShareOlcCntsVO>().setReturnVO(vo).setResultSuccess();
	}

	/**
	 * 콘텐츠 라리브러리 : 공유 OLC 콘텐츠 공유신청관리
	 * @param ClibShareOlcCntsVO
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ClibShareOlcCntsVO> updateShareCd(ClibShareOlcCntsVO vo)  throws Exception {
		clibShareOlcCntsMapper.updateShareOlc(vo);

		//--콘텐츠 공유신청 승인상태변경
		ClibCntsShareReqVO ccsrvo = new ClibCntsShareReqVO();
		ccsrvo.setModNo(vo.getModNo());
		ccsrvo.setReqSn(vo.getReqSn());
		ccsrvo.setShareStsCd(vo.getShareReqStsCd());
		clibCntsShareReqMapper.updateShareCd(ccsrvo);
		return new ProcessResultVO<ClibShareOlcCntsVO>().setReturnVO(vo).setResultSuccess();
	}

}
