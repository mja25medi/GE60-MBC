package egovframework.edutrack.modules.library.cnts.media.service.impl;

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
import egovframework.edutrack.modules.library.cnts.media.service.ClibMediaCntsService;
import egovframework.edutrack.modules.library.cnts.media.service.ClibMediaCntsVO;
import egovframework.edutrack.modules.library.cnts.share.req.service.ClibCntsShareReqVO;
import egovframework.edutrack.modules.library.cnts.share.req.service.impl.ClibCntsShareReqMapper;
import egovframework.edutrack.modules.library.share.media.service.ClibShareMediaCntsVO;
import egovframework.edutrack.modules.library.share.media.service.impl.ClibShareMediaCntsMapper;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Service("clibMediaCntsService")
public class ClibMediaServiceImpl extends EgovAbstractServiceImpl implements ClibMediaCntsService {

	private final class NestedFileHandler
		implements FileHandler<ClibMediaCntsVO> {

		@Override
		public String getPK(ClibMediaCntsVO vo) {
			return vo.getCntsCd().toString();
		}

		@Override
		public String getRepoCd() {
			return "MEDIA_CNTS";
		}

		@Override
		public List<SysFileVO> getFiles(ClibMediaCntsVO vo) {
			List<SysFileVO> fileList = new ArrayList<SysFileVO>();
			if(ValidationUtils.isNotZeroNull(vo.getThumbFileSn()))
				fileList.add(vo.getThumbFile());
			return fileList;
		}

		@Override
		public ClibMediaCntsVO setFiles(ClibMediaCntsVO vo, FileListVO fileListVO) {
			vo.setThumbFile(fileListVO.getFile("thumb"));
			return vo;
		}
	}

	@Resource(name="sysFileService")
	private SysFileService sysFileService;

	@Resource(name="clibMediaCntsMapper")
	private ClibMediaCntsMapper 		clibMediaCntsMapper;

	@Resource(name="clibShareMediaCntsMapper")
	private ClibShareMediaCntsMapper 	clibShareMediaCntsMapper;

	@Resource(name="clibCntsShareReqMapper")
	private ClibCntsShareReqMapper clibCntsShareReqMapper;

	/**
	 * 콘텐츠 라리브러리 : 미디어 콘텐츠 전체 목록을 가져온다.
	 * @param ClibMediaCntsVO.orgCd
	 * @param ClibMediaCntsVO.userNo
	 * @param ClibMediaCntsVO.ctgrCd
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<ClibMediaCntsVO> list(ClibMediaCntsVO vo) throws Exception  {
		ProcessResultListVO<ClibMediaCntsVO> resultList = new ProcessResultListVO<ClibMediaCntsVO>();
		try {
			List<ClibMediaCntsVO> returnList = clibMediaCntsMapper.list(vo);
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
	 * 콘텐츠 라리브러리 : 미디어 콘텐츠 페이징 목록을 가져온다.
	 * @param ClibMediaCntsVO.orgCd
	 * @param ClibMediaCntsVO.userNo
	 * @param ClibMediaCntsVO.ctgrCd
	 * @param curPage
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<ClibMediaCntsVO> listPageing(ClibMediaCntsVO vo, int curPage) throws Exception  {

		ProcessResultListVO<ClibMediaCntsVO> resultList = new ProcessResultListVO<ClibMediaCntsVO>();
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(curPage);
			paginationInfo.setRecordCountPerPage(Constants.LIST_SCALE);
			paginationInfo.setPageSize(Constants.LIST_PAGE_SCALE);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = clibMediaCntsMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<ClibMediaCntsVO> returnList = clibMediaCntsMapper.listPageing(vo);
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
	 * 콘텐츠 라리브러리 : 미디어 콘텐츠 페이징 목록을 가져온다.
	 * @param ClibMediaCntsVO.orgCd
	 * @param ClibMediaCntsVO.userNo
	 * @param ClibMediaCntsVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<ClibMediaCntsVO> listPageing(ClibMediaCntsVO vo) throws Exception  {

		ProcessResultListVO<ClibMediaCntsVO> resultList = new ProcessResultListVO<ClibMediaCntsVO>();
		
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(vo.getCurPage());
			paginationInfo.setRecordCountPerPage(vo.getListScale());
			paginationInfo.setPageSize(vo.getPageScale());

			// 전체 목록 수
			int totalCount = clibMediaCntsMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			List<ClibMediaCntsVO> returnList = clibMediaCntsMapper.listPageing(vo);
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
	 * 콘텐츠 라리브러리 : 미디어 콘텐츠 단일항목 정보를 가져온다.
	 * @param ClibMediaCntsVO.orgCd
	 * @param ClibMediaCntsVO.userNo
	 * @param ClibMediaCntsVO.ctgrCd
	 * @return ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ClibMediaCntsVO> view(ClibMediaCntsVO vo) throws Exception  {
		vo = clibMediaCntsMapper.select(vo);
		vo = sysFileService.getFile(vo, new NestedFileHandler());
		return new ProcessResultVO<ClibMediaCntsVO>().setResultSuccess()
				.setReturnVO(vo);
	}

	/**
	 * 콘텐츠 라리브러리 : 업로드 파일키를 이용하여 미디어 콘텐츠 단일항목 정보를 가져온다.
	 * @param ClibMediaCntsVO.orgCd
	 * @param ClibMediaCntsVO.userNo
	 * @param ClibMediaCntsVO.ctgrCd
	 * @return ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ClibMediaCntsVO> viewByUploadFileKey(ClibMediaCntsVO vo) throws Exception  {
		ProcessResultVO<ClibMediaCntsVO> resultVO = new ProcessResultVO<ClibMediaCntsVO>();
		try {
			ClibMediaCntsVO returnVO = clibMediaCntsMapper.selectByUploadFileKey(vo);
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
	 * 콘텐츠 라리브러리 : 미디어 콘텐츠 단일항목 정보를 등록하고 결과를 가져온다.
	 * @param ClibMediaCntsVO
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ClibMediaCntsVO> add(ClibMediaCntsVO vo) throws Exception {
		//---- 콘텐츠 코드 생성
		String cntsCd = StringUtil.generateNewId("UMC");
		//ClibMediaCntsVO cccvo = clibMediaCntsMapper.selectCntsCd().getReturnVO();
		//---- 신규 콘텐츠 코드 세팅
		vo.setCntsCd(cntsCd);
		//-- 콘텐츠 등록
		clibMediaCntsMapper.insert(vo);

		// 주키가 생성된 이후 첨부파일번호들을 바인딩해서 저장
		sysFileService.bindFile(vo, new NestedFileHandler());

		return new ProcessResultVO<ClibMediaCntsVO>().setReturnVO(vo).setResultSuccess();
	}

	/**
	 * 콘텐츠 라리브러리 : 미디어 콘텐츠 단일항목 정보를 수정하고 결과를 가져온다.
	 * @param ClibMediaCntsVO
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ClibMediaCntsVO> edit(ClibMediaCntsVO vo) throws Exception {
		clibMediaCntsMapper.update(vo);
		sysFileService.bindFileUpdate(vo, new NestedFileHandler());
		return new ProcessResultVO<ClibMediaCntsVO>().setReturnVO(vo).setResultSuccess();
	}



	/**
	 * 콘텐츠 라리브러리 : 미디어 콘텐츠 단일항목 정보를 삭제하고 결과를 가져온다.
	 * @param ClibMediaCntsVO.orgCd
	 * @param ClibMediaCntsVO.userNo
	 * @param ClibMediaCntsVO.ctgrCd
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ClibMediaCntsVO> delete(ClibMediaCntsVO vo) throws Exception {

		ClibShareMediaCntsVO shmcvo = new ClibShareMediaCntsVO();
		shmcvo.setOrgCd(vo.getOrgCd());
		shmcvo.setOriginCntsCd(vo.getCntsCd());
		shmcvo.setUseYn("Y");
		List<ClibShareMediaCntsVO> shareList =  clibShareMediaCntsMapper.listByOrigin(shmcvo);

		//공유 컨텐츠가 없을경우 파일 삭제
		if(shareList.size() == 0){
			sysFileService.removeFile(vo, new NestedFileHandler()); // 파일정보 삭제..
		}

		//--콘텐츠 공유신청 데이터 삭제
		ClibCntsShareReqVO ccsrvo = new ClibCntsShareReqVO();
		ccsrvo.setCntsCd(vo.getCntsCd());
		clibCntsShareReqMapper.deleteCnts(ccsrvo);

		clibMediaCntsMapper.delete(vo);
		return ProcessResultVO.success();
	}

	/**
	 * 콘텐츠 라리브러리 : 미디어 콘텐츠 순서를 변경하고 결과를 반환한다.
	 * @param OlcCntsVO
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<?> move(ClibMediaCntsVO vo, String moveType)  throws Exception {

		//-- 해당 카테고리 내의 모든 콘텐츠  목록을 구한다.
		List<ClibMediaCntsVO> olcCtgrList = clibMediaCntsMapper.list(vo);
		List<ClibMediaCntsVO> newCtgrList = new ArrayList<ClibMediaCntsVO>();
		int lineCnt = 0;
		if("up".equals(moveType)) {
			//-- 위로
			for(ClibMediaCntsVO cccvo : olcCtgrList) {
				//-- 코드가 같으면 하나 위의 omvo를 가져오고. 작업해 보자
				if(cccvo.getCtgrCd().equals(vo.getCtgrCd())) {
					ClibMediaCntsVO socvo = newCtgrList.get(lineCnt - 1); // 하나 위의 메뉴 받아오기
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
			ClibMediaCntsVO ncccvo = null;

			for(ClibMediaCntsVO cccvo : olcCtgrList) {
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
		for(ClibMediaCntsVO dvo : newCtgrList) {
			order++;
			dvo.setCntsOdr(order);
		}
		clibMediaCntsMapper.updateBatch(newCtgrList);
		return ProcessResultVO.success();
	}

	/**
	 * 콘텐츠 라리브러리 : 미디어 콘텐츠를 공유 콘텐츠로 복사한다.
	 * @param ClibMediaCntsVO
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ClibMediaCntsVO> addShare(ClibMediaCntsVO vo, String[] shareCd) throws Exception  {
		String cntsCd = "";
		for(int i=0; i<shareCd.length; i++){
			//---- 콘텐츠 코드 생성
			//ClibShareMediaCntsVO csmcvo = clibShareMediaCntsMapper.selectCntsCd().getReturnVO();
			cntsCd = StringUtil.generateNewId("SMC");

			//---- 신규 콘텐츠 코드 세팅
			vo.setShareCntsCd(cntsCd);

			//-- 콘텐츠 등록
			clibMediaCntsMapper.insertShare(vo);

			//--콘텐츠 공유신청 데이터 생성 - 기본값 01:요청 있음
			ClibCntsShareReqVO ccsrvo = new ClibCntsShareReqVO();
			ccsrvo.setCntsCd(vo.getShareCntsCd());
			//ccsrvo.setCntsTypeCd("MEDIA");
			ccsrvo.setCntsTypeCd(vo.getCntsType());
			ccsrvo.setRegNo(vo.getRegNo());
			ccsrvo.setModNo(vo.getModNo());
			ccsrvo.setShareDivCd(shareCd[i]);
			ccsrvo.setShareStsCd(vo.getShareStsCd());
			ccsrvo.setCclCd(vo.getCclCd());



			if( StringUtil.isNotNull(ccsrvo.getShareDivCd()) ){
				ccsrvo.setReqSn(clibCntsShareReqMapper.selectKey());
				clibCntsShareReqMapper.insert(ccsrvo);
			}
		}


		// 주키가 생성된 이후 첨부파일번호들을 바인딩해서 저장
		//sysFileService.bindFile(vo, new NestedFileHandler());
		return new ProcessResultVO<ClibMediaCntsVO>().setReturnVO(vo).setResultSuccess();
	}

	/**
	 * 콘텐츠 라리브러리 : 미디어 콘텐츠 OLC제작용 콘텐츠를 가져온다
	 * @param ClibMediaCntsVO.orgCd
	 * @param ClibMediaCntsVO.userNo
	 * @param ClibMediaCntsVO.ctgrCd
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<ClibMediaCntsVO> listOlc(ClibMediaCntsVO vo) throws Exception  {
		ProcessResultListVO<ClibMediaCntsVO> resultList = new ProcessResultListVO<ClibMediaCntsVO>();
		try {
			List<ClibMediaCntsVO> returnList = clibMediaCntsMapper.listOlc(vo);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}

		return resultList;
	}
}
