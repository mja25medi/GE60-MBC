package egovframework.edutrack.modules.library.cnts.olc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.library.cnts.olc.service.ClibOlcPageService;
import egovframework.edutrack.modules.library.cnts.olc.service.ClibOlcPageVO;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Service("clibOlcPageService")
public class ClibOlcPageServiceImpl
	extends EgovAbstractServiceImpl implements ClibOlcPageService {

	private final class NestedFileHandler
		implements FileHandler<ClibOlcPageVO> {

		@Override
		public String getPK(ClibOlcPageVO vo) {
			return vo.getPageCd();
		}

		@Override
		public String getRepoCd() {
			return "OLC_PAGE";
		}

		@Override
		public List<SysFileVO> getFiles(ClibOlcPageVO vo) {
			List<SysFileVO> fileList = vo.getAttachImages();
			fileList.addAll(vo.getAttachFiles());
			return fileList;
		}

		@Override
		public ClibOlcPageVO setFiles(ClibOlcPageVO vo, FileListVO fileListVO) {
			vo.setAttachImages(fileListVO.getFiles("image"));
			vo.setAttachFiles(fileListVO.getFiles("file"));
			return vo;
		}
	}

	@Resource(name="sysFileService")
	private SysFileService sysFileService;

	/** Mapper */
	@Resource(name="clibOlcPageMapper")
	private ClibOlcPageMapper 		clibOlcPageMapper;

	/**
	 * 콘텐츠 라리브러리 :  OLC 콘텐츠 페이지 전체 목록을 조회하여 반환한다.
	 * @param ClibOlcPageVO.orgCd
	 * @param ClibOlcPageVO.userNo
	 * @param ClibOlcPageVO.cartrgCd
	 * @return ProcessResultListVO<ClibOlcPageVO>
	 */
	@Override
	public ProcessResultListVO<ClibOlcPageVO> list(ClibOlcPageVO vo) throws Exception {
		ProcessResultListVO<ClibOlcPageVO> resultList = new ProcessResultListVO<ClibOlcPageVO>();
		try {
			List<ClibOlcPageVO> returnList = clibOlcPageMapper.list(vo);
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
	 * 콘텐츠 라리브러리 :  OLC 콘텐츠 페이지의 페이징된 목록을 조회하여 반환한다.
	 * 페이징 정보 포함.
	 * @param ClibOlcPageVO.orgCd
	 * @param ClibOlcPageVO.userNo
	 * @param ClibOlcPageVO.cartrgCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO<ClibOlcPageVO>
	 */
	@Override
	public ProcessResultListVO<ClibOlcPageVO> listPageing(ClibOlcPageVO vo, int curPage, int listScale, int pageScale) throws Exception {

		ProcessResultListVO<ClibOlcPageVO> resultList = new ProcessResultListVO<ClibOlcPageVO>();
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(curPage);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());

			// 전체 목록 수
			int totalCount = clibOlcPageMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<ClibOlcPageVO> returnList = clibOlcPageMapper.listPageing(vo);
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
	 * 콘텐츠 라리브러리 :  OLC 콘텐츠 페이지의 페이징된 목록을 조회하여 반환한다.
	 * 페이징 정보 포함.
	 * @param ClibOlcPageVO.orgCd
	 * @param ClibOlcPageVO.userNo
	 * @param ClibOlcPageVO.cartrgCd
	 * @param curPage
	 * @param listScale
	 * @return ProcessResultListVO<ClibOlcPageVO>
	 */
	@Override
	public ProcessResultListVO<ClibOlcPageVO> listPageing(ClibOlcPageVO vo, int curPage, int listScale)  throws Exception {
		return this.listPageing(vo, curPage, listScale, Constants.LIST_PAGE_SCALE);
	}

	/**
	 * 콘텐츠 라리브러리 :  OLC 콘텐츠 페이지의 페이징된 목록을 조회하여 반환한다.
	 * 페이징 정보 포함.
	 * @param ClibOlcPageVO.orgCd
	 * @param ClibOlcPageVO.userNo
	 * @param ClibOlcPageVO.cartrgCd
	 * @param curPage
	 * @return ProcessResultListVO<ClibOlcPageVO>
	 */
	@Override
	public ProcessResultListVO<ClibOlcPageVO> listPageing(ClibOlcPageVO vo, int curPage)  throws Exception {
		return this.listPageing(vo, curPage, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}

	/**
	 * 콘텐츠 라리브러리 :  OLC 콘텐츠 페이지 정보 단일 레코드를 조회하여 반환한다.
	 * @param ClibOlcPageVO.orgCd
	 * @param ClibOlcPageVO.userNo
	 * @param ClibOlcPageVO.cntsCd
	 * @return
	 */
	@Override
	public ProcessResultVO<ClibOlcPageVO> view(ClibOlcPageVO vo)  throws Exception {
		vo = clibOlcPageMapper.select(vo);
		vo = sysFileService.getFile(vo, new NestedFileHandler());
		this.atclUrlToView(vo);
		return new ProcessResultVO<ClibOlcPageVO>().setResultSuccess()
				.setReturnVO(vo);
	}

	/**
	 * 콘텐츠 라리브러리 :  OLC 콘텐츠 페이지 정보 단일 레코드를 DB에 Insert하고 결과를 반환한다.
	 * @param ClibOlcPageVO
	 * @return ProcessResultVO<ClibOlcPageVO>
	 */
	@Override
	public ProcessResultVO<ClibOlcPageVO> add(ClibOlcPageVO vo)  throws Exception {
		//-- 페이지 키 값 생성
		//ClibOlcPageVO pgvo = clibOlcPageMapper.selectPageCd().getReturnVO();
		String pageCd = StringUtil.generateNewId("UOC");
		//-- 페이지 키 셋팅
		vo.setPageCd(pageCd);
		// 첨부 이미지 URL 변경
		this.atclUrlToPersist(vo);
		// 글저장 후 주키 값 받아오기.
		clibOlcPageMapper.insert(vo);
		// 주키가 생성된 이후 첨부파일번호들을 바인딩해서 저장
		sysFileService.bindFile(vo, new NestedFileHandler());

		ProcessResultVO<ClibOlcPageVO> resultVO = new ProcessResultVO<ClibOlcPageVO>();
		resultVO.setReturnVO(vo);
		resultVO.setReturnVO(vo).setResultSuccess();

		return resultVO;

	}

	/**
	 * 콘텐츠 라리브러리 :  OLC 콘텐츠 페이지 정보 단일 레코드를 Update하고 결과를 반환한다.
	 * @param ClibOlcPageVO
	 * @return ProcessResultVO<ClibOlcPageVO>
	 */
	@Override
	public ProcessResultVO<ClibOlcPageVO> edit(ClibOlcPageVO vo)  throws Exception {
		this.atclUrlToPersist(vo);	// URL 변환
		clibOlcPageMapper.update(vo);
		sysFileService.bindFileUpdate(vo, new NestedFileHandler());

		ProcessResultVO<ClibOlcPageVO> resultVO = new ProcessResultVO<ClibOlcPageVO>();
		resultVO.setReturnVO(vo);
		resultVO.setReturnVO(vo).setResultSuccess();

		return resultVO;
	}


	/**
	 * 콘텐츠 라리브러리 :  OLC 콘텐츠 페이지 정보 단일 레코드를 Delete하고 결과를 반환한다.
	 * @param ClibOlcPageVO
	 * @return ProcessResultVO<ClibOlcPageVO>
	 */
	@Override
	public ProcessResultVO<ClibOlcPageVO> remove(ClibOlcPageVO vo)  throws Exception {
		sysFileService.removeFile(vo, new NestedFileHandler()); // 파일정보 삭제..
		clibOlcPageMapper.delete(vo);

		ProcessResultVO<ClibOlcPageVO> resultVO = new ProcessResultVO<ClibOlcPageVO>();
		resultVO.setReturnVO(vo);
		resultVO.setReturnVO(vo).setResultSuccess();

		return resultVO;
	}

	/**
	 * 콘텐츠 라리브러리 :  OLC 콘텐츠 페이지 정보의 순서를 변경하고 결과를 반환한다.
	 * @param ClibOlcPageVO
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<?> move(ClibOlcPageVO vo, String moveType)  throws Exception {

		//-- 해당 카테고리 내의 모든 OLC 목록을 구한다.
		List<ClibOlcPageVO> olcCntsList = clibOlcPageMapper.list(vo);
		List<ClibOlcPageVO> newCntsList = new ArrayList<ClibOlcPageVO>();
		int lineCnt = 0;
		if("up".equals(moveType)) {
			//-- 위로
			for(ClibOlcPageVO ocvo : olcCntsList) {
				//-- 코드가 같으면 하나 위의 omvo를 가져오고. 작업해 보자
				if(ocvo.getPageCd().equals(vo.getPageCd())) {
					ClibOlcPageVO socvo = newCntsList.get(lineCnt - 1); // 하나 위의 메뉴 받아오기
					newCntsList.remove(lineCnt - 1); //-- 하나위의 목록을 삭제
					newCntsList.add(ocvo);
					newCntsList.add(socvo);
				} else {
					newCntsList.add(ocvo);
				}
				lineCnt++;
			}
		} else if("down".equals(moveType)) {
			//-- 메뉴 아래로
			ClibOlcPageVO nocvo = null;

			for(ClibOlcPageVO ocvo : olcCntsList) {
				if(ocvo.getPageCd().equals(vo.getPageCd())) {
					nocvo = ocvo;
				} else {
					newCntsList.add(ocvo);
					if(ValidationUtils.isNotEmpty(nocvo)) {
						newCntsList.add(nocvo);
						nocvo = null;
					}
				}
			}
		}
		int order = 0;
		for(ClibOlcPageVO dvo : newCntsList) {
			order++;
			dvo.setPageOdr(order);
		}
		clibOlcPageMapper.updateBatch(newCntsList);
		return ProcessResultVO.success();
	}

	public ProcessResultVO<?> sort(ClibOlcPageVO vo) throws Exception {
		String[] pageList = StringUtil.split(vo.getPageCds(), "|");
		// 목록을 한꺼번에 조회
		List<ClibOlcPageVO> olcPageArray = clibOlcPageMapper.list(vo);
		// 이중 포문으로 bannerArray에 변경된 order를 다시 저장
		// 만약 파라매터로 코드키가 누락되는 경우가 있다면... 로직 수정 필요.
		for(ClibOlcPageVO clibOlcPageVO : olcPageArray){
			for (int order = 0; order < pageList.length; order++) {
				if(clibOlcPageVO.getPageCd().equals(pageList[order]) ){
					clibOlcPageVO.setPageOdr(order+1);
				}
			}
		}
		// 변경된 어래이를 일괄 저장.
		clibOlcPageMapper.updateBatch(olcPageArray);
		return ProcessResultVO.success();
	}


	private void atclUrlToPersist(ClibOlcPageVO vo) {
		vo.setPageCts(StringUtil.replaceUrlToPersist(vo.getPageCts()));
	}

	private void atclUrlToView(ClibOlcPageVO vo) {
		vo.setPageCts(StringUtil.replaceUrlToBrowser(vo.getPageCts()));
	}
}
