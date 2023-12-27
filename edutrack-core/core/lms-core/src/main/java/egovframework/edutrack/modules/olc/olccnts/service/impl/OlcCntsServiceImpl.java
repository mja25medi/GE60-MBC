package egovframework.edutrack.modules.olc.olccnts.service.impl;

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
import egovframework.edutrack.modules.olc.olccnts.service.OlcCntsService;
import egovframework.edutrack.modules.olc.olccnts.service.OlcCntsVO;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Service("olcCntsService")
public class OlcCntsServiceImpl extends EgovAbstractServiceImpl implements OlcCntsService {

	private final class NestedFileHandler
		implements FileHandler<OlcCntsVO> {

		@Override
		public String getPK(OlcCntsVO vo) {
			return vo.getCntsCd();
		}

		@Override
		public String getRepoCd() {
			return "OLCCNTS";
		}

		@Override
		public List<SysFileVO> getFiles(OlcCntsVO vo) {
			List<SysFileVO> fileList = vo.getAttachImages();
			fileList.addAll(vo.getAttachFiles());
			return fileList;
		}

		@Override
		public OlcCntsVO setFiles(OlcCntsVO vo, FileListVO fileListVO) {
			vo.setAttachImages(fileListVO.getFiles("image"));
			vo.setAttachFiles(fileListVO.getFiles("file"));
			return vo;
		}
	}

	@Resource(name="sysFileService")
	private SysFileService sysFileService;

	/** Mapper */
	@Resource(name="olcCntsMapper")
	private OlcCntsMapper olcCntsMapper;
	

	/**
	 * OLC CNTS 의 모든 목록을 조회하여 반환한다.
	 * @param OlcCntsVO.orgCd
	 * @param OlcCntsVO.userNo
	 * @param OlcCntsVO.cartrgCd
	 * @return ProcessResultListVO<OlcCntsVO>
	 */
	@Override
	public ProcessResultListVO<OlcCntsVO> list(OlcCntsVO vo)  throws Exception {
		ProcessResultListVO<OlcCntsVO> resultList = new ProcessResultListVO<OlcCntsVO>();
		try {
			List<OlcCntsVO> returnList = olcCntsMapper.list(vo);
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
	 * OLC CNTS 의 페이징된 목록을 조회하여 반환한다.
	 * 페이징 정보 포함.
	 * @param OlcCntsVO.orgCd
	 * @param OlcCntsVO.userNo
	 * @param OlcCntsVO.cartrgCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO<OlcCntsVO>
	 */
	@Override
	public ProcessResultListVO<OlcCntsVO> listPageing(OlcCntsVO vo, int curPage, int listScale, int pageScale)  throws Exception {
		
		ProcessResultListVO<OlcCntsVO> resultList = new ProcessResultListVO<OlcCntsVO>();

		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(curPage);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());

			// 전체 목록 수
			int totalCount = olcCntsMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<OlcCntsVO> returnList = olcCntsMapper.listPageing(vo);
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
	 * OLC의 CNTS 페이징된 목록을 조회하여 반환한다.
	 * 페이징 정보 포함.
	 * @param OlcCntsVO.orgCd
	 * @param OlcCntsVO.userNo
	 * @param OlcCntsVO.cartrgCd
	 * @param curPage
	 * @param listScale
	 * @return ProcessResultListVO<OlcCntsVO>
	 */
	@Override
	public ProcessResultListVO<OlcCntsVO> listPageing(OlcCntsVO vo, int curPage, int listScale) throws Exception  {
		return this.listPageing(vo, curPage, listScale, Constants.LIST_PAGE_SCALE);
	}

	/**
	 * OLC의 CNTS 페이징된 목록을 조회하여 반환한다.
	 * 페이징 정보 포함.
	 * @param OlcCntsVO.orgCd
	 * @param OlcCntsVO.userNo
	 * @param OlcCntsVO.cartrgCd
	 * @param curPage
	 * @return ProcessResultListVO<OlcCntsVO>
	 */
	@Override
	public ProcessResultListVO<OlcCntsVO> listPageing(OlcCntsVO vo, int curPage) throws Exception  {
		return this.listPageing(vo, curPage, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}

	/**
	 * OLC 정보 단일 레코드를 조회하여 반환한다.
	 * @param OlcCntsVO.orgCd
	 * @param OlcCntsVO.userNo
	 * @param OlcCntsVO.cntsCd
	 * @return
	 */
	@Override
	public ProcessResultVO<OlcCntsVO> view(OlcCntsVO vo) throws Exception  {
		vo = olcCntsMapper.select(vo);
		vo = sysFileService.getFile(vo, new NestedFileHandler());
		this.atclUrlToView(vo);
		return new ProcessResultVO<OlcCntsVO>().setResultSuccess()
				.setReturnVO(vo);
	}

	/**
	 * OLC CNTS 정보 단일 레코드를 DB에 Insert하고 결과를 반환한다.
	 * @param OlcCntsVO
	 * @return ProcessResultVO<OlcCntsVO>
	 */
	@Override
	public ProcessResultVO<OlcCntsVO> add(OlcCntsVO vo)  throws Exception {
		// 첨부 이미지 URL 변경
		this.atclUrlToPersist(vo);
		if(ValidationUtils.isEmpty(vo.getCntsCd())) {
			vo.setCntsCd(olcCntsMapper.selectKey());
		}
		// 글저장 후 주키 값 받아오기.
		olcCntsMapper.insert(vo);
		// 주키가 생성된 이후 첨부파일번호들을 바인딩해서 저장
		sysFileService.bindFile(vo, new NestedFileHandler());
		return ProcessResultVO.success();

	}

	/**
	 * OLC CNTS 정보 단일 레코드를 Update하고 결과를 반환한다.
	 * @param OlcCntsVO
	 * @return ProcessResultVO<OlcCntsVO>
	 */
	@Override
	public ProcessResultVO<OlcCntsVO> edit(OlcCntsVO vo) throws Exception  {
		this.atclUrlToPersist(vo);	// URL 변환
		sysFileService.bindFileUpdate(vo, new NestedFileHandler());
		olcCntsMapper.update(vo);
		return ProcessResultVO.success();
	}


	/**
	 * OLC CNTS 정보 단일 레코드를 Delete하고 결과를 반환한다.
	 * @param OlcCntsVO
	 * @return ProcessResultVO<OlcCntsVO>
	 */
	@Override
	public ProcessResultVO<?> remove(OlcCntsVO vo) throws Exception  {
		sysFileService.removeFile(vo, new NestedFileHandler()); // 파일정보 삭제..
		olcCntsMapper.delete(vo);
		return ProcessResultVO.success();
	}

	/**
	 * OLC CNTS 정보의 순서를 변경하고 결과를 반환한다.
	 * @param OlcCntsVO
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<?> move(OlcCntsVO vo, String moveType) throws Exception  {

		//-- 해당 카테고리 내의 모든 OLC 목록을 구한다.
		List<OlcCntsVO> olcCntsList = olcCntsMapper.list(vo);
		List<OlcCntsVO> newCntsList = new ArrayList<OlcCntsVO>();
		int lineCnt = 0;
		if("up".equals(moveType)) {
			//-- 위로
			for(OlcCntsVO ocvo : olcCntsList) {
				//-- 코드가 같으면 하나 위의 omvo를 가져오고. 작업해 보자
				if(ocvo.getCntsCd().equals(vo.getCntsCd())) {
					OlcCntsVO socvo = newCntsList.get(lineCnt - 1); // 하나 위의 메뉴 받아오기
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
			OlcCntsVO nocvo = null;

			for(OlcCntsVO ocvo : olcCntsList) {
				if(ocvo.getCntsCd().equals(vo.getCntsCd())) {
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
		for(OlcCntsVO dvo : newCntsList) {
			order++;
			dvo.setCntsOdr(order);
		}
		olcCntsMapper.updateBatch(newCntsList);
		return ProcessResultVO.success();
	}

	private void atclUrlToPersist(OlcCntsVO vo) {
		vo.setCntsCts(StringUtil.replaceUrlToPersist(vo.getCntsCts()));
	}

	private void atclUrlToView(OlcCntsVO vo) {
		vo.setCntsCts(StringUtil.replaceUrlToBrowser(vo.getCntsCts()));
	}
}
