package egovframework.edutrack.modules.etc.bkgimg.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.etc.bkgimg.service.BkgImgService;
import egovframework.edutrack.modules.etc.bkgimg.service.BkgImgVO;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Service("bkgImgService")
public class BkgImgServiceImpl extends EgovAbstractServiceImpl implements BkgImgService{

	private final class NestedFileHandler
		implements FileHandler<BkgImgVO> {

		@Override
		public String getPK(BkgImgVO VO){
			return VO.getBkgrImgSn().toString();
		}

		@Override
		public String getRepoCd() {
			return "BKGIMG";
		}

		@Override
		public List<SysFileVO> getFiles(BkgImgVO VO) {
			List<SysFileVO> fileList = new ArrayList<SysFileVO>();
			if(ValidationUtils.isNotZeroNull(VO.getMainImgFileSn()))
				fileList.add(VO.getMainImgFile());
			return fileList;
		}

		@Override
		public BkgImgVO setFiles(BkgImgVO VO, FileListVO fileListVO){
			VO.setMainImgFile(fileListVO.getFile("main"));
			return VO;
		}


	}

	/** Mapper */
	@Resource(name="bkgImgMapper")
	private BkgImgMapper 		bkgImgMapper;

	@Resource(name="sysFileService")
	private SysFileService 		sysFileService;

	/**
	 * 배경이미지 목록을 반환한다.
	 * @param BkgImgVO.useYn
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<BkgImgVO> list (BkgImgVO VO) throws Exception {
		
		ProcessResultListVO<BkgImgVO> resultList = new ProcessResultListVO<BkgImgVO>();
		try {
			List<BkgImgVO> returnList = bkgImgMapper.list(VO);
			resultList.setReturnList(returnList);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}

		return resultList;
	}

	/**
	 * 배경이미지 페이징 목록을 반환한다.
	 * @param BkgImgVO.useYn
	 * @param curPage
	 * @param listScale
	 * @param pageScale
	 * @return
	 */
	@Override
	public ProcessResultListVO<BkgImgVO> listPageing(BkgImgVO vo) {
		ProcessResultListVO<BkgImgVO> resultList = new ProcessResultListVO<BkgImgVO>();
		try {
			/** start of paging */
			PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(vo.getCurPage());
			paginationInfo.setRecordCountPerPage(vo.getListScale());
			paginationInfo.setPageSize(vo.getPageScale());
			
			vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
			vo.setLastIndex(paginationInfo.getLastRecordIndex());	
			
			// 전체 목록 수
			int totalCount = bkgImgMapper.count(vo);
			paginationInfo.setTotalRecordCount(totalCount);
			
			List<BkgImgVO> returnList = bkgImgMapper.listPageing(vo);
			resultList.setReturnList(returnList);
			resultList.setPageInfo(paginationInfo);
			resultList.setResult(1);
		} catch (Exception e) {
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}


	/**
	 * 배경이미지 단일 항목 정보를 반환한다.
	 * @param BkgImgVO.bkgrImgSn
	 * @return
	 */
	@Override
	public ProcessResultVO<BkgImgVO> view(BkgImgVO VO)  throws Exception {
		VO = bkgImgMapper.select(VO);
		VO = sysFileService.getFile(VO, new NestedFileHandler());
		return new ProcessResultVO<BkgImgVO>().setResultSuccess().setReturnVO(VO);
	}

	/**
	 * 배경이미지 정보를 저장하고 결과를 반환한다.
	 * @param BkgImgVO
	 * @return
	 */
	@Override
	public ProcessResultVO<BkgImgVO> add(BkgImgVO VO)  throws Exception {
		
		VO.setBkgrImgSn(bkgImgMapper.selectKey());
		bkgImgMapper.insert(VO);
		// 주키가 생성된 이후 첨부파일번호들을 바인딩해서 저장
		sysFileService.bindFile(VO, new NestedFileHandler());
		return ProcessResultVO.success();
	}

	/**
	 * 배경이미지 정보를 수정하고 결과를 반환한다.
	 * @param BkgImgVO
	 * @return
	 */
	@Override
	public ProcessResultVO<BkgImgVO> edit(BkgImgVO VO)  throws Exception {
		bkgImgMapper.update(VO);
		// 주키가 생성된 이후 첨부파일번호들을 바인딩해서 저장
		sysFileService.bindFile(VO, new NestedFileHandler());
		return ProcessResultVO.success();
	}

	/**
	 * 배경이미지 순서를 변경하고 결과를 반환한다.
	 * @param VO
	 * @return
	 */
	@Override
	public ProcessResultVO<?> sort(BkgImgVO VO)  throws Exception {
		String[] itemList = StringUtil.split(VO.getBkgrImgSns(),"|");
		// 하위 코드 목록을 한꺼번에 조회
		List<BkgImgVO> itemArray = bkgImgMapper.list(VO);
		// 이중 포문으로 codeArray에 변경된 order를 다시 저장
		// 만약 파라매터로 코드키가 누락되는 경우가 있다면... 로직 수정 필요.
		for (BkgImgVO sVO : itemArray) {
			for (int order = 0; order < itemList.length; order++) {
				if(sVO.getBkgrImgSn().toString().equals(itemList[order])) {
					sVO.setBkgrImgOdr(order+1);	// 1부터 차례로 순서값을 지정
				}
			}
		}
		// 변경된 시스템코드 어래이를 일괄 저장.
		bkgImgMapper.updateBatch(itemArray);
		return ProcessResultVO.success();
	}

	/**
	 * 시스템 템플릿 정보를 삭제하고 결과를 반환한다.
	 * @param VO
	 * @return
	 */
	@Override
	public ProcessResultVO<?> remove(BkgImgVO VO)  throws Exception {
		sysFileService.removeFile(VO, new NestedFileHandler()); // 파일정보 삭제..
		bkgImgMapper.delete(VO);
		return ProcessResultVO.success();
	}

}
