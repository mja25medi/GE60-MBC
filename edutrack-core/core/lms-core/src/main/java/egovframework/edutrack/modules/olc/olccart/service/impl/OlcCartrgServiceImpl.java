package egovframework.edutrack.modules.olc.olccart.service.impl;

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
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.olc.olccart.service.OlcCartrgService;
import egovframework.edutrack.modules.olc.olccart.service.OlcCartrgVO;
import egovframework.edutrack.modules.olc.sharerel.service.OlcShareRelVO;
import egovframework.edutrack.modules.olc.sharerel.service.impl.OlcShareRelMapper;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Service("olcCartrgService")
public class OlcCartrgServiceImpl
	extends EgovAbstractServiceImpl implements OlcCartrgService {

	private final class NestedFileHandler implements FileHandler<OlcCartrgVO> {

		@Override
		public String getPK(OlcCartrgVO vo) {
			return vo.getCartrgCd().toString();
		}

		@Override
		public String getRepoCd() {
			return "OLC_LESN_CARTRG";
		}

		@Override
		public List<SysFileVO> getFiles(OlcCartrgVO vo) {
			List<SysFileVO> fileList = new ArrayList<SysFileVO>();
			// 단일 항목도 저장.
			if(ValidationUtils.isNotZeroNull(vo.getThumbFile().getFileSn()))
				fileList.add(vo.getThumbFile());
			if(ValidationUtils.isNotZeroNull(vo.getTopLogoImg().getFileSn()))
				fileList.add(vo.getTopLogoImg());
			if(ValidationUtils.isNotZeroNull(vo.getSubLogoImg().getFileSn()))
				fileList.add(vo.getSubLogoImg());
			if("U".equals(vo.getBkgImgSelType()) ){
				if(ValidationUtils.isNotZeroNull(vo.getBkgImg().getFileSn()))
					fileList.add(vo.getBkgImg());
			}

			return fileList;
		}

		@Override
		public OlcCartrgVO setFiles(OlcCartrgVO vo, FileListVO fileListVO) {
			vo.setThumbFile(fileListVO.getFile("thumb"));
			vo.setTopLogoImg(fileListVO.getFile("toplogo"));
			vo.setSubLogoImg(fileListVO.getFile("sublogo"));
			vo.setBkgImg(fileListVO.getFile("bkgimg"));
			return vo;
		}
	}

	@Resource(name="olcCartrgMapper")
	private OlcCartrgMapper 		olcCartrgMapper;

	@Resource(name="olcShareRelMapper")
	private OlcShareRelMapper 	olcShareRelMapper;
	
	@Resource(name="sysFileService")
	private SysFileService sysFileService;
	/**
	 * OLC의 모든 목록을 조회하여 반환한다.
	 * @param OlcCartrgVO.orgCd
	 * @param OlcCartrgVO.userNo
	 * @param OlcCartrgVO.ctgrCd
	 * @return ProcessResultListVO<OlcCartrgVO>
	 */
	@Override
	public ProcessResultListVO<OlcCartrgVO> list(OlcCartrgVO vo)  throws Exception{
		ProcessResultListVO<OlcCartrgVO> resultList = new ProcessResultListVO<OlcCartrgVO>();
		try {
			List<OlcCartrgVO> returnList = olcCartrgMapper.list(vo);
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
	 * OLC의 페이징된 목록을 조회하여 반환한다.
	 * 페이징 정보 포함.
	 * @param OlcCartrgVO.orgCd
	 * @param OlcCartrgVO.userNo
	 * @param OlcCartrgVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return ProcessResultListVO<OlcCartrgVO>
	 */
	@Override
	public ProcessResultListVO<OlcCartrgVO> listPageing(OlcCartrgVO vo, int curPage, int listScale, int pageScale)  throws Exception{
		ProcessResultListVO<OlcCartrgVO> resultList = new ProcessResultListVO<OlcCartrgVO>();
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(curPage);
			paginationInfo.setRecordCountPerPage(listScale);
			paginationInfo.setPageSize(pageScale);
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());
			
			// 전체 목록 수
			int totalCount = olcCartrgMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<OlcCartrgVO> returnList = olcCartrgMapper.listPageing(vo, curPage, listScale, pageScale);
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
	 * OLC의 페이징된 목록을 조회하여 반환한다.
	 * 페이징 정보 포함.
	 * @param OlcCartrgVO.orgCd
	 * @param OlcCartrgVO.userNo
	 * @param OlcCartrgVO.ctgrCd
	 * @param curPage
	 * @param listScale
	 * @return ProcessResultListVO<OlcCartrgVO>
	 */
	@Override
	public ProcessResultListVO<OlcCartrgVO> listPageing(OlcCartrgVO vo, int curPage, int listScale) throws Exception {
		return this.listPageing(vo, curPage, listScale, Constants.LIST_PAGE_SCALE);
	}

	/**
	 * OLC의 페이징된 목록을 조회하여 반환한다.
	 * 페이징 정보 포함.
	 * @param OlcCartrgVO.orgCd
	 * @param OlcCartrgVO.userNo
	 * @param OlcCartrgVO.ctgrCd
	 * @param curPage
	 * @return ProcessResultListVO<OlcCartrgVO>
	 */
	@Override
	public ProcessResultListVO<OlcCartrgVO> listPageing(OlcCartrgVO vo, int curPage) throws Exception {
		return this.listPageing(vo, curPage, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
	}

	/**
	 * OLC 정보 단일 레코드를 조회하여 반환한다.
	 * @param OlcCartrgVO.orgCd
	 * @param OlcCartrgVO.userNo
	 * @param OlcCartrgVO.cartrgCd
	 * @return
	 */
	@Override
	public ProcessResultVO<OlcCartrgVO> view(OlcCartrgVO vo) throws Exception {
		vo = olcCartrgMapper.select(vo);
		vo = sysFileService.getFile(vo, new NestedFileHandler());
		return new ProcessResultVO<OlcCartrgVO>().setResultSuccess().setReturnVO(vo);
	}

	/**
	 * OLC 정보 단일 레코드를 조회하여 반환한다.
	 * @param OlcCartrgVO.orgCd
	 * @param OlcCartrgVO.userNo
	 * @param OlcCartrgVO.cartrgCd
	 * @param 조회수 증가 여부(boolean) : default -> false
	 * @return
	 */
	@Override
	public ProcessResultVO<OlcCartrgVO> view(OlcCartrgVO vo, boolean hitsup) throws Exception {
		if(hitsup) olcCartrgMapper.hitsup(vo);
		vo = olcCartrgMapper.select(vo);
		vo = sysFileService.getFile(vo, new NestedFileHandler());
		return new ProcessResultVO<OlcCartrgVO>().setResultSuccess().setReturnVO(vo);
	}

	/**
	 * OLC 정보 단일 레코드를 DB에 Insert하고 결과를 반환한다.
	 * @param OlcCartrgVO
	 * @return ProcessResultVO<OlcCartrgVO>
	 */
	@Override
	public ProcessResultVO<OlcCartrgVO> add(OlcCartrgVO vo) throws Exception {
		ProcessResultVO<OlcCartrgVO> resultVO = new ProcessResultVO<OlcCartrgVO>();
		try {
			vo.setCartrgCd(olcCartrgMapper.selectKey());
			olcCartrgMapper.insert(vo);
			resultVO.setReturnVO(vo);
			resultVO.setResultSuccess();
			if(vo.getThumbFileSn() > 0){
				sysFileService.bindFile(vo, new NestedFileHandler());
			}
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}		

		return resultVO;
	}

	/**
	 * OLC 정보 단일 레코드를 Update하고 결과를 반환한다.
	 * @param OlcCartrgVO
	 * @return ProcessResultVO<OlcCartrgVO>
	 */
	@Override
	public ProcessResultVO<OlcCartrgVO> edit(OlcCartrgVO vo) throws Exception {
		
		ProcessResultVO<OlcCartrgVO> resultVO = new ProcessResultVO<OlcCartrgVO>();
		try {
			olcCartrgMapper.update(vo);
			resultVO.setReturnVO(vo);
			OlcShareRelVO olcShareRelVO = new OlcShareRelVO();
			olcShareRelVO.setOrgCd(vo.getOrgCd());
			olcShareRelVO.setCtgrCd(vo.getCtgrCd());
			olcShareRelVO.setCartrgCd(vo.getCartrgCd());

			/*OLC공유 체크시 공유코드 승인완료 변경*/
			if("Y".equals(vo.getOpenYn()) ){
				vo.setCntsShareCd("03");
				olcCartrgMapper.updateShareContents(vo);
			}

			if("Y".equals(vo.getKnowOpenYn()) ){
				vo.setKnowShareCd("03");
				if(ValidationUtils.isNotEmpty(vo.getKnowShareCd()) && vo.getKnowShareCd().equals("03")){
					vo.setKnowOpenYn("Y");
				}else{
					vo.setKnowOpenYn("N");
				}
				olcCartrgMapper.updateShareKnow(vo);
			}
			/*OLC공유 체크시 공유코드 승인완료 변경*/

			/*OLC공유 체크해제시 공유된 분류값 삭제*/
			if(ValidationUtils.isNull(vo.getOpenYn()) ){
				olcShareRelVO.setCtgrDivCd("C");
				olcShareRelMapper.deleteCartrgCheck(olcShareRelVO);
			}

			if(ValidationUtils.isNull(vo.getKnowOpenYn()) ){
				olcShareRelVO.setCtgrDivCd("K");
				olcShareRelMapper.deleteCartrgCheck(olcShareRelVO);
			}
			/*OLC공유 체크해제시 공유된 분류값 삭제*/

			if(ValidationUtils.isNotEmpty(vo.getThumbFileSn()) && vo.getThumbFileSn() > 0){
				sysFileService.bindFile(resultVO.getReturnVO(), new NestedFileHandler());
			}			
			
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}		


		return resultVO;
	}


	/**
	 * OLC 정보 단일 레코드를 Delete하고 결과를 반환한다.
	 * @param OlcCartrgVO
	 * @return ProcessResultVO<OlcCartrgVO>
	 */
	@Override
	public ProcessResultVO<?> remove(OlcCartrgVO vo) {
		ProcessResultVO<?> resultVO = new ProcessResultVO();
		try {
			olcCartrgMapper.delete(vo);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}		
		return resultVO;
	}

	/**
	 * OLC 정보의 순서를 변경하고 결과를 반환한다.
	 * @param OlcCartrgVO
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<?> move(OlcCartrgVO vo, String moveType) throws Exception {

		//-- 해당 카테고리 내의 모든 OLC 목록을 구한다.
		List<OlcCartrgVO> olcCartrgList = olcCartrgMapper.list(vo);
		List<OlcCartrgVO> newOLCList = new ArrayList<OlcCartrgVO>();
		int lineCnt = 0;
		if("up".equals(moveType)) {
			//-- 위로
			for(OlcCartrgVO ocvo : olcCartrgList) {
				//-- 코드가 같으면 하나 위의 omvo를 가져오고. 작업해 보자
				if(ocvo.getCartrgCd().equals(vo.getCartrgCd())) {
					OlcCartrgVO socvo = newOLCList.get(lineCnt - 1); // 하나 위의 메뉴 받아오기
					newOLCList.remove(lineCnt - 1); //-- 하나위의 목록을 삭제
					newOLCList.add(ocvo);
					newOLCList.add(socvo);
				} else {
					newOLCList.add(ocvo);
				}
				lineCnt++;
			}
		} else if("down".equals(moveType)) {
			//-- 메뉴 아래로
			OlcCartrgVO nocvo = null;

			for(OlcCartrgVO ocvo : olcCartrgList) {
				if(ocvo.getCartrgCd().equals(vo.getCartrgCd())) {
					nocvo = ocvo;
				} else {
					newOLCList.add(ocvo);
					if(ValidationUtils.isNotEmpty(nocvo)) {
						newOLCList.add(nocvo);
						nocvo = null;
					}
				}
			}
		}
		int order = 0;
		for(OlcCartrgVO dvo : newOLCList) {
			order++;
			dvo.setCartrgOdr(order);
		}
		olcCartrgMapper.updateBatch(newOLCList);
		return ProcessResultVO.success();
	}

	/**
	 * OLC 정보의 순서를 변경하고 결과를 반환한다.
	 * @param OlcCartrgVO
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<?> ShareCdChange(OlcCartrgVO vo, String cdType) throws Exception {
		OlcCartrgVO newDto = olcCartrgMapper.select(vo);
		if("cnts".equals(cdType)) {
			newDto.setCntsShareCd(vo.getCntsShareCd());
		} else if("know".equals(cdType)) {
			newDto.setKnowShareCd(vo.getKnowShareCd());
		}
		olcCartrgMapper.updateShareCd(newDto);
		return ProcessResultVO.success();
	}

	/**
	 * OLC 공유정보 단일 레코드를 Update하고 결과를 반환한다.
	 * @param OlcCartrgVO
	 * @return ProcessResultVO<OlcCartrgVO>
	 */
	@Override
	public ProcessResultVO<OlcCartrgVO> editShare(OlcCartrgVO vo) throws Exception {
		ProcessResultVO<OlcCartrgVO> resultVO = new ProcessResultVO<OlcCartrgVO>();
		try {
			if(ValidationUtils.isNotEmpty(vo.getCntsShareCd()) && vo.getCntsShareCd().equals("03")){
				vo.setOpenYn("Y");
			}else{
				vo.setOpenYn("N");
			}
			if(ValidationUtils.isNotEmpty(vo.getKnowShareCd()) && vo.getKnowShareCd().equals("03")){
				vo.setKnowOpenYn("Y");
			}else{
				vo.setKnowOpenYn("N");
			}
			olcCartrgMapper.updateShare(vo);
			resultVO.setReturnVO(vo);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}		
		return resultVO;
	}

	/**
	 * OLC 단일 레코드를 Update하고 결과를 반환한다. - 디자인 수정
	 * @param OlcCartrgVO
	 * @return ProcessResultVO<OlcCartrgVO>
	 */
	@Override
	public ProcessResultVO<OlcCartrgVO> editDesign(OlcCartrgVO vo) throws Exception {
		ProcessResultVO<OlcCartrgVO> resultVO = new ProcessResultVO<OlcCartrgVO>();
		try {
			olcCartrgMapper.updateDesing(vo);
			sysFileService.bindFile(vo, new NestedFileHandler());
			resultVO.setReturnVO(vo);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}		
		
		return resultVO;
	}
}
