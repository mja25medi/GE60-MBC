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
import egovframework.edutrack.modules.library.cnts.olc.service.ClibOlcCntsService;
import egovframework.edutrack.modules.library.cnts.olc.service.ClibOlcCntsVO;
import egovframework.edutrack.modules.library.cnts.olc.service.ClibOlcPageVO;
import egovframework.edutrack.modules.library.cnts.share.req.service.ClibCntsShareReqVO;
import egovframework.edutrack.modules.library.cnts.share.req.service.impl.ClibCntsShareReqMapper;
import egovframework.edutrack.modules.library.share.olc.service.ClibShareOlcCntsVO;
import egovframework.edutrack.modules.library.share.olc.service.impl.ClibShareOlcCntsMapper;
import egovframework.edutrack.modules.library.share.olc.service.impl.ClibShareOlcPageMapper;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Service("clibOlcCntsService")
public class ClibOlcCntsServiceImpl extends EgovAbstractServiceImpl implements ClibOlcCntsService{

	private final class NestedFileHandler implements FileHandler<ClibOlcCntsVO> {

		@Override
		public String getPK(ClibOlcCntsVO vo) {
			return vo.getCntsCd().toString();
		}

		@Override
		public String getRepoCd() {
			return "OLC_CNTS";
		}

		@Override
		public List<SysFileVO> getFiles(ClibOlcCntsVO vo) {
			List<SysFileVO> fileList = new ArrayList<SysFileVO>();
			// 단일 항목도 저장.
			if(ValidationUtils.isNotZeroNull(vo.getThumbFile().getFileSn()))
				fileList.add(vo.getThumbFile());
			if(ValidationUtils.isNotZeroNull(vo.getLogoImg().getFileSn()))
				fileList.add(vo.getLogoImg());
			if("U".equals(vo.getBkgrImgUldDiv()) ) {
				if(ValidationUtils.isNotZeroNull(vo.getBkgrImg().getFileSn()))
					fileList.add(vo.getBkgrImg());
			}
			return fileList;
		}

		@Override
		public ClibOlcCntsVO setFiles(ClibOlcCntsVO vo, FileListVO fileListVO) {
			vo.setThumbFile(fileListVO.getFile("thumb"));
			vo.setLogoImg(fileListVO.getFile("logo"));
			if("U".equals(vo.getBkgrImgUldDiv()) ) {;
				vo.setBkgrImg(fileListVO.getFile("bkgr"));
			}
			return vo;
		}
	}

	@Resource(name="clibOlcCntsMapper")
	private ClibOlcCntsMapper 		clibOlcCntsMapper;

	@Resource(name="clibOlcPageMapper")
	private ClibOlcPageMapper 		clibOlcPageMapper;

	@Resource(name="clibShareOlcCntsMapper")
	private ClibShareOlcCntsMapper clibShareOlcCntsMapper;

	@Resource(name="clibShareOlcPageMapper")
	private ClibShareOlcPageMapper clibShareOlcPageMapper;

	@Resource(name="clibCntsShareReqMapper")
	private ClibCntsShareReqMapper clibCntsShareReqMapper;

	@Resource(name="sysFileService")
	private SysFileService sysFileService;

	/**
	 * 콘텐츠 라리브러리 : OLC의 모든 목록을 조회하여 반환한다.
	 * @param ClibOlcCntsVO.orgCd
	 * @param ClibOlcCntsVO.userNo
	 * @param ClibOlcCntsVO.ctgrCd
	 * @return ProcessResultListVO<ClibOlcCntsVO>
	 */
	@Override
	public ProcessResultListVO<ClibOlcCntsVO> list(ClibOlcCntsVO vo) throws Exception {
		ProcessResultListVO<ClibOlcCntsVO> resultList = new ProcessResultListVO<ClibOlcCntsVO>();
		try {
			List<ClibOlcCntsVO> returnList = clibOlcCntsMapper.list(vo);
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
	 * 콘텐츠 라리브러리 : OLC의 페이징된 목록을 조회하여 반환한다.
	 * 페이징 정보 포함.
	 * @param ClibOlcCntsVO.orgCd
	 * @param ClibOlcCntsVO.userNo
	 * @param ClibOlcCntsVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO<ClibOlcCntsVO>
	 */
	@Override
	public ProcessResultListVO<ClibOlcCntsVO> listPageing(ClibOlcCntsVO vo) throws Exception {
		ProcessResultListVO<ClibOlcCntsVO> resultList = new ProcessResultListVO<ClibOlcCntsVO>();
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(vo.getCurPage());
			paginationInfo.setRecordCountPerPage(vo.getListScale());
			paginationInfo.setPageSize(vo.getPageScale());
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());

			// 전체 목록 수
			int totalCount = clibOlcCntsMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<ClibOlcCntsVO> returnList = clibOlcCntsMapper.listPageing(vo);
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
	 * 콘텐츠 라리브러리 : OLC 정보 단일 레코드를 조회하여 반환한다.
	 * @param ClibOlcCntsVO.orgCd
	 * @param ClibOlcCntsVO.userNo
	 * @param ClibOlcCntsVO.cartrgCd
	 * @return
	 */
	@Override
	public ProcessResultVO<ClibOlcCntsVO> view(ClibOlcCntsVO vo) throws Exception {
		vo = clibOlcCntsMapper.select(vo);
		vo = sysFileService.getFile(vo, new NestedFileHandler());
		return new ProcessResultVO<ClibOlcCntsVO>().setResultSuccess().setReturnVO(vo);
	}

	/**
	 * 콘텐츠 라리브러리 : OLC 정보 단일 레코드를 조회하여 반환한다.
	 * @param ClibOlcCntsVO.orgCd
	 * @param ClibOlcCntsVO.userNo
	 * @param ClibOlcCntsVO.cartrgCd
	 * @param 조회수 증가 여부(boolean) : default -> false
	 * @return
	 */
	@Override
	public ProcessResultVO<ClibOlcCntsVO> view(ClibOlcCntsVO vo, boolean hitsup) throws Exception {
		if(hitsup) clibOlcCntsMapper.hitsup(vo);
		return this.view(vo);
	}

	/**
	 * 콘텐츠 라리브러리 : OLC 정보 단일 레코드를 DB에 Insert하고 결과를 반환한다.
	 * @param ClibOlcCntsVO
	 * @return ProcessResultVO<ClibOlcCntsVO>
	 */
	@Override
	public ProcessResultVO<ClibOlcCntsVO> add(ClibOlcCntsVO vo) throws Exception {
		//-- 콘텐츠 키 값 생성
		//ClibOlcCntsVO cocvo = clibOlcCntsMapper.selectCntsCd().getReturnVO();
		String cntsCd = StringUtil.generateNewId("UOC");
		//-- 콘텐츠 키 셋팅
		vo.setCntsCd(cntsCd);
		//-- 콘텐츠 등록

		clibOlcCntsMapper.insert(vo);
		// 주키가 생성된 이후 첨부파일번호들을 바인딩해서 저장
		sysFileService.bindFile(vo, new NestedFileHandler());
		/*
		//--콘텐츠 공유신청 데이터 생성 - 기본값 02:요청없음
		ClibCntsShareReqVO ccsrvo = new ClibCntsShareReqVO();
		ccsrvo.setCntsCd(vo.getCntsCd());

		ccsrvo.setCntsTypeCd("OLC");
		ccsrvo.setRegNo(vo.getRegNo());
		ccsrvo.setModNo(vo.getModNo());

		ccsrvo.setShareDivCd("CNTS");
		ccsrvo.setShareStsCd("02");
		clibCntsShareReqMapper.insert(ccsrvo);

		ccsrvo.setShareDivCd("KNOW");
		ccsrvo.setShareStsCd("02");
		clibCntsShareReqMapper.insert(ccsrvo);
		*/
		return new ProcessResultVO<ClibOlcCntsVO>().setReturnVO(vo).setResultSuccess();
	}

	/**
	 * 콘텐츠 라리브러리 : OLC 정보 단일 레코드를 Update하고 결과를 반환한다.
	 * @param ClibOlcCntsVO
	 * @return ProcessResultVO<ClibOlcCntsVO>
	 */
	@Override
	public ProcessResultVO<ClibOlcCntsVO> edit(ClibOlcCntsVO vo) throws Exception {
		//-- 기존 콘텐츠 정보 가져온다.
		ClibOlcCntsVO cocvo = clibOlcCntsMapper.select(vo);

		//-- 기존 정보를 신규 정보로 덮어 쓴다.
		vo.setCntsTitleColor(cocvo.getCntsTitleColor());
		vo.setCntsTableBkgrColor(cocvo.getCntsTableBkgrColor());
		vo.setCntsTableFontColor(cocvo.getCntsTableFontColor() );
		vo.setCntsTablePosCd(cocvo.getCntsTablePosCd());
		vo.setPageNoPosCd(cocvo.getPageNoPosCd());
		vo.setLogoImgSn(cocvo.getLogoImgSn());
		vo.setBkgrImgSn(cocvo.getBkgrImgSn());
		vo.setBkgrImgTypeCd(cocvo.getBkgrImgTypeCd());
		vo.setBkgrImgUldDiv(cocvo.getBkgrImgUldDiv());

		clibOlcCntsMapper.update(vo);
		sysFileService.bindFileUpdate(vo, new NestedFileHandler());
		return new ProcessResultVO<ClibOlcCntsVO>().setReturnVO(vo).setResultSuccess();
	}


	/**
	 * 콘텐츠 라리브러리 : OLC 디자인 정보 단일 레코드를 Update하고 결과를 반환한다.
	 * @param ClibOlcCntsVO
	 * @return ProcessResultVO<ClibOlcCntsVO>
	 */
	@Override
	public ProcessResultVO<ClibOlcCntsVO> editDesign(ClibOlcCntsVO vo) throws Exception {
		//-- 기존 콘텐츠 정보 가져온다.
		ClibOlcCntsVO cocvo = clibOlcCntsMapper.select(vo);

		//-- 기존 정보를 신규 정보로 덮어 쓴다.
		vo.setCntsOdr(cocvo.getCntsOdr());
		vo.setCtgrCd(cocvo.getCtgrCd());
		vo.setThumbFileSn(cocvo.getThumbFileSn());
		vo.setCntsNm(cocvo.getCntsNm());
		vo.setCntsTag(cocvo.getCntsTag());
		vo.setCntsCts(cocvo.getCntsCts());
		vo.setUseYn(cocvo.getUseYn());

		clibOlcCntsMapper.update(vo);
		sysFileService.bindFileUpdate(vo, new NestedFileHandler());
		return new ProcessResultVO<ClibOlcCntsVO>().setReturnVO(vo).setResultSuccess();
	}


	/**
	 * 콘텐츠 라리브러리 : OLC 정보 단일 레코드를 Delete하고 결과를 반환한다.
	 * @param ClibOlcCntsVO
	 * @return ProcessResultVO<ClibOlcCntsVO>
	 */
	@Override
	public ProcessResultVO<?> remove(ClibOlcCntsVO vo) throws Exception {

		ClibShareOlcCntsVO sholcvo = new ClibShareOlcCntsVO();
		sholcvo.setOrgCd(vo.getOrgCd());
		sholcvo.setOriginCntsCd(vo.getCntsCd());
		sholcvo.setUseYn("Y");
		List<ClibShareOlcCntsVO> shareList =  clibShareOlcCntsMapper.listByOrigin(sholcvo);

		//공유 컨텐츠가 없을경우 파일 삭제
		if(shareList.size() == 0){
			sysFileService.removeFile(vo, new NestedFileHandler()); // 파일정보 삭제..
		}

		//OLC 페이지 삭제
		ClibOlcPageVO copvo = new ClibOlcPageVO();
		copvo.setCntsCd(vo.getCntsCd());
		clibOlcPageMapper.deleteByCnts(copvo);

		//--콘텐츠 공유신청 데이터 삭제
		ClibCntsShareReqVO ccsrvo = new ClibCntsShareReqVO();
		ccsrvo.setCntsCd(vo.getCntsCd());
		clibCntsShareReqMapper.deleteCnts(ccsrvo);

		//OLC 콘텐츠 삭제
		clibOlcCntsMapper.delete(vo);


		return new ProcessResultVO<ClibOlcCntsVO>().setReturnVO(vo).setResultSuccess();
	}

	/**
	 * 콘텐츠 라리브러리 : OLC 정보의 순서를 변경하고 결과를 반환한다.
	 * @param ClibOlcCntsVO
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<?> move(ClibOlcCntsVO vo, String moveType) throws Exception {

		//-- 해당 카테고리 내의 모든 OLC 목록을 구한다.
		List<ClibOlcCntsVO> olcCntsList = clibOlcCntsMapper.list(vo);
		List<ClibOlcCntsVO> newOLCList = new ArrayList<ClibOlcCntsVO>();
		int lineCnt = 0;
		if("up".equals(moveType)) {
			//-- 위로
			for(ClibOlcCntsVO cocvo : olcCntsList) {
				//-- 코드가 같으면 하나 위의 omvo를 가져오고. 작업해 보자
				if(cocvo.getCntsCd().equals(vo.getCntsCd())) {
					ClibOlcCntsVO socvo = newOLCList.get(lineCnt - 1); // 하나 위의 메뉴 받아오기
					newOLCList.remove(lineCnt - 1); //-- 하나위의 목록을 삭제
					newOLCList.add(cocvo);
					newOLCList.add(socvo);
				} else {
					newOLCList.add(cocvo);
				}
				lineCnt++;
			}
		} else if("down".equals(moveType)) {
			//-- 메뉴 아래로
			ClibOlcCntsVO nocvo = null;

			for(ClibOlcCntsVO cocvo : olcCntsList) {
				if(cocvo.getCntsCd().equals(vo.getCntsCd())) {
					nocvo = cocvo;
				} else {
					newOLCList.add(cocvo);
					if(ValidationUtils.isNotEmpty(nocvo)) {
						newOLCList.add(nocvo);
						nocvo = null;
					}
				}
			}
		}
		int order = 0;
		for(ClibOlcCntsVO dvo : newOLCList) {
			order++;
			dvo.setCntsOdr(order);
		}
		clibOlcCntsMapper.updateBatch(newOLCList);
		return ProcessResultVO.success();
	}

	/**
	 * 콘텐츠 라리브러리 : OLC 콘텐츠를 공유 콘텐츠로 복사한다.
	 * @param ClibOlcCntsVO
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ClibOlcCntsVO> addShare(ClibOlcCntsVO vo, String[] shareCd) throws Exception {
		String cntsCd = "";
		for(int i=0; i<shareCd.length; i++){
			//---- 콘텐츠 코드 생성
			cntsCd = StringUtil.generateNewId("SOC");

			//---- 신규 콘텐츠 코드 세팅
			vo.setShareCntsCd(cntsCd);
			clibOlcCntsMapper.insertShare(vo);

			ClibOlcPageVO olcPageVO = new ClibOlcPageVO();
			olcPageVO.setOrgCd(vo.getOrgCd());
			olcPageVO.setCntsCd(vo.getCntsCd());

			List<ClibOlcPageVO> clibOlcPageList = clibOlcPageMapper.list(olcPageVO);
			for(ClibOlcPageVO copvo : clibOlcPageList) {
				//---- 페이지 코드 생성
				String pageCd = StringUtil.generateNewId("SOP");
				olcPageVO.setShareCntsCd(cntsCd);
				olcPageVO.setSharePageCd(pageCd);

				olcPageVO.setPageNm(copvo.getPageNm());
				olcPageVO.setPageOdr(copvo.getPageOdr());
				olcPageVO.setPageDiv(copvo.getPageDiv());
				olcPageVO.setPageCts(copvo.getPageCts());
				olcPageVO.setFilePath(copvo.getFilePath());
				olcPageVO.setPageDesc(copvo.getPageDesc());
				olcPageVO.setRegNo(copvo.getRegNo());
				olcPageVO.setModNo(copvo.getModNo());

				clibOlcPageMapper.insertShare(olcPageVO);
			}
			//--콘텐츠 공유신청 데이터 생성 - 기본값 01:요청 있음
			ClibCntsShareReqVO ccsrvo = new ClibCntsShareReqVO();
			int selectKey = clibCntsShareReqMapper.selectKey();
			ccsrvo.setReqSn(selectKey);
			ccsrvo.setCntsCd(vo.getShareCntsCd());
			ccsrvo.setCntsTypeCd("OLC");
			ccsrvo.setRegNo(vo.getRegNo());
			ccsrvo.setModNo(vo.getModNo());
			ccsrvo.setShareDivCd(shareCd[i]);
			ccsrvo.setShareStsCd(vo.getShareStsCd());
			ccsrvo.setCclCd(vo.getCclCd());
			
			if( StringUtil.isNotNull(ccsrvo.getShareDivCd()) ){
				
				clibCntsShareReqMapper.insert(ccsrvo);
			}
		}


		// 주키가 생성된 이후 첨부파일번호들을 바인딩해서 저장
		//sysFileService.bindFile(vo, new NestedFileHandler());
		return new ProcessResultVO<ClibOlcCntsVO>().setReturnVO(vo).setResultSuccess();
	}

}
