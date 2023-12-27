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
import egovframework.edutrack.modules.library.share.olc.service.ClibShareOlcPageService;
import egovframework.edutrack.modules.library.share.olc.service.ClibShareOlcPageVO;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Service("clibShareOlcPageService")
public class ClibShareOlcPageServiceImpl extends EgovAbstractServiceImpl implements ClibShareOlcPageService{

	private final class NestedFileHandler
		implements FileHandler<ClibShareOlcPageVO> {

		@Override
		public String getPK(ClibShareOlcPageVO vo) {
			return vo.getCntsCd().toString();
		}

		@Override
		public String getRepoCd() {
			return "SHARE_OLC_PAGE";
		}

		@Override
		public List<SysFileVO> getFiles(ClibShareOlcPageVO vo) {
			List<SysFileVO> fileList = vo.getAttachImages();
			fileList.addAll(vo.getAttachFiles());
			return fileList;
		}

		@Override
		public ClibShareOlcPageVO setFiles(ClibShareOlcPageVO vo, FileListVO fileListVO) {
			vo.setAttachImages(fileListVO.getFiles("image"));
			vo.setAttachFiles(fileListVO.getFiles("file"));
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

	/**
	 * 콘텐츠 라리브러리 : 공유 OLC 페이지 전체 목록을 가져온다.
	 * @param ClibShareOlcPageVO.orgCd
	 * @param ClibShareOlcPageVO.userNo
	 * @param ClibShareOlcPageVO.ctgrCd
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<ClibShareOlcPageVO> list(ClibShareOlcPageVO vo) throws Exception  {
		ProcessResultListVO<ClibShareOlcPageVO> resultList = new ProcessResultListVO<ClibShareOlcPageVO>();
		try {
			List<ClibShareOlcPageVO> returnList = clibShareOlcPageMapper.list(vo);
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
	 * 콘텐츠 라리브러리 : 공유 OLC 페이지 페이징 목록을 가져온다.
	 * @param ClibShareOlcPageVO.orgCd
	 * @param ClibShareOlcPageVO.userNo
	 * @param ClibShareOlcPageVO.ctgrCd
	 * @param curPage
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<ClibShareOlcPageVO> listPageing(ClibShareOlcPageVO vo, int curPage) throws Exception  {

		return this.listPageing(vo, curPage, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}

	/**
	 * 콘텐츠 라리브러리 : 공유 OLC 페이지 페이징 목록을 가져온다.
	 * @param ClibShareOlcPageVO.orgCd
	 * @param ClibShareOlcPageVO.userNo
	 * @param ClibShareOlcPageVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<ClibShareOlcPageVO> listPageing(ClibShareOlcPageVO vo, int curPage, int listScale) throws Exception  {
	
		return this.listPageing(vo, curPage, listScale, Constants.LIST_PAGE_SCALE);
	}

	/**
	 * 콘텐츠 라리브러리 : 공유 OLC 페이지 페이징 목록을 가져온다.
	 * @param ClibShareOlcPageVO.orgCd
	 * @param ClibShareOlcPageVO.userNo
	 * @param ClibShareOlcPageVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<ClibShareOlcPageVO> listPageing(ClibShareOlcPageVO vo, int curPage, int listScale, int pageScale)  throws Exception {
		
		ProcessResultListVO<ClibShareOlcPageVO> resultList = new ProcessResultListVO<ClibShareOlcPageVO>();
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(curPage);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());

			// 전체 목록 수
			int totalCount = clibShareOlcPageMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<ClibShareOlcPageVO> returnList = clibShareOlcPageMapper.listPageing(vo);
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
	 * 콘텐츠 라리브러리 : 공유 OLC 페이지 단일항목 정보를 가져온다.
	 * @param ClibShareOlcPageVO.orgCd
	 * @param ClibShareOlcPageVO.userNo
	 * @param ClibShareOlcPageVO.ctgrCd
	 * @return ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ClibShareOlcPageVO> view(ClibShareOlcPageVO vo)  throws Exception {
		vo = clibShareOlcPageMapper.select(vo);
		vo = sysFileService.getFile(vo, new NestedFileHandler());
		return new ProcessResultVO<ClibShareOlcPageVO>().setResultSuccess().setReturnVO(vo);
	}

	/**
	 * 콘텐츠 라리브러리 : 공유 OLC 페이지 단일항목 정보를 등록하고 결과를 가져온다.
	 * @param ClibShareOlcPageVO
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ClibShareOlcPageVO> add(ClibShareOlcPageVO vo) throws Exception  {
		//---- 페이지 코드 생성
		ClibShareOlcPageVO copvo = clibShareOlcPageMapper.selectPageCd();

		//---- 신규 페이지 코드 세팅
		vo.setPageCd(copvo.getPageCd());

		//-- 페이지 등록
		clibShareOlcPageMapper.insert(vo);

		// 주키가 생성된 이후 첨부파일번호들을 바인딩해서 저장
		sysFileService.bindFile(vo, new NestedFileHandler());

		return new ProcessResultVO<ClibShareOlcPageVO>().setReturnVO(vo).setResultSuccess();
	}

	/**
	 * 콘텐츠 라리브러리 : 공유 OLC 페이지 단일항목 정보를 수정하고 결과를 가져온다.
	 * @param ClibShareOlcPageVO
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ClibShareOlcPageVO> edit(ClibShareOlcPageVO vo) throws Exception  {
		clibShareOlcPageMapper.update(vo);
		sysFileService.bindFileUpdate(vo, new  NestedFileHandler());
		return new ProcessResultVO<ClibShareOlcPageVO>().setReturnVO(vo).setResultSuccess();
	}

	/**
	 * 콘텐츠 라리브러리 : 공유 OLC 페이지 단일항목 정보를 삭제하고 결과를 가져온다.
	 * @param ClibShareOlcPageVO.orgCd
	 * @param ClibShareOlcPageVO.userNo
	 * @param ClibShareOlcPageVO.ctgrCd
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ClibShareOlcPageVO> delete(ClibShareOlcPageVO vo) throws Exception  {

		sysFileService.removeFile(vo, new NestedFileHandler());
		clibShareOlcPageMapper.delete(vo);
		return ProcessResultVO.success();
	}

	/**
	 * 콘텐츠 라리브러리 : 공유 OLC 페이지 순서를 변경하고 결과를 반환한다.
	 * @param ClibShareOlcPageVO
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<?> move(ClibShareOlcPageVO vo, String moveType) throws Exception  {

		//-- 해당 카테고리 내의 모든 콘텐츠  목록을 구한다.
		List<ClibShareOlcPageVO> olcCtgrList = clibShareOlcPageMapper.list(vo);
		List<ClibShareOlcPageVO> newCtgrList = new ArrayList<ClibShareOlcPageVO>();
		int lineCnt = 0;
		if("up".equals(moveType)) {
			//-- 위로
			for(ClibShareOlcPageVO cccvo : olcCtgrList) {
				//-- 코드가 같으면 하나 위의 omvo를 가져오고. 작업해 보자
				if(cccvo.getPageCd().equals(vo.getPageCd())) {
					ClibShareOlcPageVO socvo = newCtgrList.get(lineCnt - 1); // 하나 위의 메뉴 받아오기
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
			ClibShareOlcPageVO ncccvo = null;

			for(ClibShareOlcPageVO cccvo : olcCtgrList) {
				if(cccvo.getPageCd().equals(vo.getPageCd())) {
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
		for(ClibShareOlcPageVO dvo : newCtgrList) {
			order++;
			dvo.setPageOdr(order);
		}
		clibShareOlcPageMapper.updateBatch(newCtgrList);
		return ProcessResultVO.success();
	}

	/**
	 * 콘텐츠 라리브러리 : 원본 OLC 페이지의 공유 OLC 페이지 전체 목록을 가져온다.
	 * @param ClibShareOlcPageVO.orgCd
	 * @param ClibShareOlcPageVO.originCntsCd
	 * @param ClibShareOlcPageVO.ctgrCd
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<ClibShareOlcPageVO> listByOrigin(ClibShareOlcPageVO vo) throws Exception  {
		ProcessResultListVO<ClibShareOlcPageVO> resultList = new ProcessResultListVO<ClibShareOlcPageVO>();
		try {
			List<ClibShareOlcPageVO> returnList = clibShareOlcPageMapper.listByOrigin(vo);
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
	 * 콘텐츠 라리브러리 : 원본 OLC 페이지의 공유 OLC 페이지 페이징 목록을 가져온다.
	 * @param ClibShareOlcPageVO.orgCd
	 * @param ClibShareOlcPageVO.originCntsCd
	 * @param ClibShareOlcPageVO.ctgrCd
	 * @param curPage
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<ClibShareOlcPageVO> listByOriginPageing(ClibShareOlcPageVO vo, int curPage) throws Exception  {

		
		return this.listByOriginPageing(vo, curPage, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}

	/**
	 * 콘텐츠 라리브러리 : 원본 OLC 페이지의 공유 OLC 페이지 페이징 목록을 가져온다.
	 * @param ClibShareOlcPageVO.orgCd
	 * @param ClibShareOlcPageVO.originCntsCd
	 * @param ClibShareOlcPageVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<ClibShareOlcPageVO> listByOriginPageing(ClibShareOlcPageVO vo, int curPage, int listScale) throws Exception  {
		return this.listByOriginPageing(vo, curPage, listScale, Constants.LIST_PAGE_SCALE);
	}

	/**
	 * 콘텐츠 라리브러리 : 원본 OLC 페이지의 공유 OLC 페이지 페이징 목록을 가져온다.
	 * @param ClibShareOlcPageVO.orgCd
	 * @param ClibShareOlcPageVO.originCntsCd
	 * @param ClibShareOlcPageVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<ClibShareOlcPageVO> listByOriginPageing(ClibShareOlcPageVO vo, int curPage, int listScale, int pageScale) throws Exception  {
		
		ProcessResultListVO<ClibShareOlcPageVO> resultList = new ProcessResultListVO<ClibShareOlcPageVO>();
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(curPage);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			List<ClibShareOlcPageVO> returnList = clibShareOlcPageMapper.listByOriginPageing(vo);
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

}
