package egovframework.edutrack.modules.system.file.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.modules.system.file.service.SysFileRepoLangVO;
import egovframework.edutrack.modules.system.file.service.SysFileRepoService;
import egovframework.edutrack.modules.system.file.service.SysFileRepoVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 *  <b>시스템 - 시스템 파일 리파지토리 정보 관리</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("sysFileRepoService")
public class SysFileRepoServiceImpl 
	extends EgovAbstractServiceImpl implements SysFileRepoService {

    @Resource(name="sysFileRepoMapper")
    private SysFileRepoMapper 		sysFileRepoMapper;
    
    @Resource(name="sysFileRepoLangMapper")
    private SysFileRepoLangMapper sysFileRepoLangMapper;
    
    /**
 	 *  파일 리파지토리 전체 목록을 조회한다.
 	 * @param SysFileRepoVO
 	 * @return ProcessResultListVO
 	 * @throws Exception
 	 */
 	@Override
	public ProcessResultListVO<SysFileRepoVO> list(SysFileRepoVO vo) throws Exception {
 		ProcessResultListVO<SysFileRepoVO> resultList = new ProcessResultListVO<SysFileRepoVO>();
 		List<SysFileRepoVO> fileRepoList =  sysFileRepoMapper.list(vo);
		List<SysFileRepoVO> returnList = new ArrayList<SysFileRepoVO>();
		for(SysFileRepoVO sfrvo : fileRepoList) {
			SysFileRepoLangVO sfrlvo = new SysFileRepoLangVO();
			sfrlvo.setRepoCd(sfrvo.getRepoCd());
			List<SysFileRepoLangVO> sysFileRepoLangList = sysFileRepoLangMapper.list(sfrlvo);
			sfrvo.setFileRepoLangList(sysFileRepoLangList);
			returnList.add(sfrvo);
		}
 		resultList.setResult(1);
 		resultList.setReturnList(returnList);
 		return resultList;
 	}
 	
     /**
 	 * 파일 리파지토리 페이징 목록을 조회한다.
 	 * @param SysFileRepoVO
 	 * @param pageIndex
 	 * @param listScale
 	 * @param pageScale
 	 * @return ProcessResultListVO
 	 * @throws Exception
 	 */
 	@Override
	public ProcessResultListVO<SysFileRepoVO> listPageing(SysFileRepoVO vo, 
 			int pageIndex, int listScale, int pageScale) throws Exception {
 		ProcessResultListVO<SysFileRepoVO> resultList = new ProcessResultListVO<SysFileRepoVO>(); 
 		
		/** start of paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pageIndex);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		
		// 전체 목록 수
		int totalCount = sysFileRepoMapper.count(vo);
		paginationInfo.setTotalRecordCount(totalCount);
		
		List<SysFileRepoVO> fileRepoList =  sysFileRepoMapper.listPageing(vo);
		
		List<SysFileRepoVO> returnList = new ArrayList<SysFileRepoVO>();
		for(SysFileRepoVO sfrvo : fileRepoList) {
			SysFileRepoLangVO sfrlvo = new SysFileRepoLangVO();
			sfrlvo.setRepoCd(sfrvo.getRepoCd());
			List<SysFileRepoLangVO> sysFileRepoLangList = sysFileRepoLangMapper.list(sfrlvo);
			sfrvo.setFileRepoLangList(sysFileRepoLangList);
			returnList.add(sfrvo);
		}		
		
		resultList.setResult(1);
		resultList.setReturnList(returnList);
		resultList.setPageInfo(paginationInfo);
 			
 		return resultList;
 	}
 	
     /**
 	 * 파일 리파지토리 페이징 목록을 조회한다.
 	 * @param SysFileRepoVO
 	 * @param pageIndex
 	 * @param listScale
 	 * @return ProcessResultListVO
 	 * @throws Exception
 	 */
 	@Override
	public ProcessResultListVO<SysFileRepoVO> listPageing(SysFileRepoVO vo, 
 			int pageIndex, int listScale) throws Exception {
 		return this.listPageing(vo, pageIndex, listScale, Constants.LIST_PAGE_SCALE);
 	}
 	
     /**
 	 * 파일 리파지토리 페이징 목록을 조회한다.
 	 * @param SysFileRepoVO
 	 * @param pageIndex
 	 * @return ProcessResultListVO
 	 * @throws Exception
 	 */
 	@Override
	public ProcessResultListVO<SysFileRepoVO> listPageing(SysFileRepoVO vo, 
 			int pageIndex) throws Exception {
 		return this.listPageing(vo, pageIndex, Constants.LIST_SCALE, Constants.LIST_PAGE_SCALE);
 	}	
 	
 	/**
 	 * 파일 리파지토리 상세 정보를 조회한다.
 	 * @param SysFileRepoVO
 	 * @return SysFileRepoVO
 	 * @throws Exception
 	 */
 	@Override
	public SysFileRepoVO view(SysFileRepoVO vo) throws Exception {
 		vo = sysFileRepoMapper.select(vo);
		SysFileRepoLangVO sfrlvo = new SysFileRepoLangVO();
		sfrlvo.setRepoCd(vo.getRepoCd());
		List<SysFileRepoLangVO> sysFileRepoLangList = sysFileRepoLangMapper.list(sfrlvo);
		vo.setFileRepoLangList(sysFileRepoLangList); 		
 		return vo;
 	}
 	
 	/**
 	 * 파일 리파지토리 정보를 등록한다.
 	 * @param SysFileRepoVO
 	 * @return String
 	 * @throws Exception
 	 */
 	@Override
	public int add(SysFileRepoVO vo) throws Exception {
 		int result = sysFileRepoMapper.insert(vo);
		List<SysFileRepoLangVO> sysFileRepoLangList = vo.getFileRepoLangList();
		for(SysFileRepoLangVO sfrlvo : sysFileRepoLangList) {
			try {
				sfrlvo.setRepoCd(vo.getRepoCd());
				sysFileRepoLangMapper.insert(sfrlvo);
			} catch(Exception e) {
				sfrlvo.setRepoCd(vo.getRepoCd());
				sysFileRepoLangMapper.update(sfrlvo);
			}
		} 		
 		return result;
 	}	
 	
 	/**
 	 * 파일 리파지토리 정보를 수정한다.
 	 * @param SysFileRepoVO
 	 * @return int
 	 * @throws Exception
 	 */
 	@Override
	public int edit(SysFileRepoVO vo) throws Exception {
 		int result = sysFileRepoMapper.update(vo);
		List<SysFileRepoLangVO> sysFileRepoLangList = vo.getFileRepoLangList();
		for(SysFileRepoLangVO sfrlvo : sysFileRepoLangList) {
			try {
				sfrlvo.setRepoCd(vo.getRepoCd());
				sysFileRepoLangMapper.insert(sfrlvo);
			} catch(Exception e) {
				sfrlvo.setRepoCd(vo.getRepoCd());
				sysFileRepoLangMapper.update(sfrlvo);
			}
		}  				
 		return result;
 	}
 	
 	/**
 	 * 파일 리파지토리 정보를 삭제 한다.
 	 * @param SysFileRepoVO
 	 * @return int
 	 * @throws Exception
 	 */
 	@Override
	public int remove(SysFileRepoVO vo) throws Exception {
 		SysFileRepoLangVO sysFileRepoLangVO = new SysFileRepoLangVO();
 		sysFileRepoLangVO.setRepoCd(vo.getRepoCd());
 		sysFileRepoLangMapper.deleteAll(sysFileRepoLangVO);
 		
  		return sysFileRepoMapper.delete(vo);
 	}
}
