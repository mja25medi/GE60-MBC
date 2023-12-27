package egovframework.edutrack.modules.lecture.project.board.info.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.lecture.project.board.atcl.service.PrjBbsAtclVO;
import egovframework.edutrack.modules.lecture.project.board.atcl.service.impl.PrjBbsAtclMapper;
import egovframework.edutrack.modules.lecture.project.board.cmnt.service.PrjBbsCmntVO;
import egovframework.edutrack.modules.lecture.project.board.cmnt.service.impl.PrjBbsCmntMapper;
import egovframework.edutrack.modules.lecture.project.board.info.service.PrjBbsService;
import egovframework.edutrack.modules.lecture.project.board.info.service.PrjBbsVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("prjBbsService")
public class PrjBbsServiceImpl extends EgovAbstractServiceImpl implements PrjBbsService {

	@Resource(name="prjBbsMapper")
	private PrjBbsMapper prjBbsMapper;

	@Resource(name="prjBbsAtclMapper")
	private PrjBbsAtclMapper prjBbsAtclMapper;

	@Resource(name="prjBbsCmntMapper")
	private PrjBbsCmntMapper prjBbsCmntMapper;

	/**
	 * 팀프로젝트 게시판 목록 조회
	 * @author mhShin 
	 * @date 2013. 10. 30.
	 * @param vo
	 * @return ProcessResultListVO<PrjBbsVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultListVO<PrjBbsVO> list(PrjBbsVO vo) throws Exception {
		ProcessResultListVO<PrjBbsVO> resultList = new ProcessResultListVO<PrjBbsVO>();
		try {
			List<PrjBbsVO> returnList =  prjBbsMapper.list(vo);
			resultList.setResult(1);
			resultList.setReturnList(returnList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	/**
	 * 팀프로젝트 게시판 목록 조회
	 * @author mhShin 
	 * @date 2013. 10. 30.
	 * @param vo
	 * @return ProcessResultListVO<PrjBbsVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultListVO<PrjBbsVO> listBbs(PrjBbsVO vo) throws Exception {
		ProcessResultListVO<PrjBbsVO> resultList = new ProcessResultListVO<PrjBbsVO>();
		try {
			List<PrjBbsVO> returnList =  prjBbsMapper.listBbs(vo);
			resultList.setResult(1);
			resultList.setReturnList(returnList);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setResult(-1);
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	/**
	 * 팀프로젝트 게시판 등록
	 * @author mhShin 
	 * @date 2013. 10. 30.
	 * @param vo
	 * @return ProcessResultVO<PrjBbsVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<PrjBbsVO> add(PrjBbsVO vo) throws Exception {
		ProcessResultVO<PrjBbsVO> resultVO = new ProcessResultVO<PrjBbsVO>();
		try {
			//bbs_cd setting
			vo.setBbsCd(prjBbsMapper.selectProjectBoardBbsCd());
			prjBbsMapper.insert(vo);
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
	 * 팀프로젝트 게시판 정보 조회
	 * @author mhShin 
	 * @date 2013. 10. 30.
	 * @param vo
	 * @return ProcessResultVO<PrjBbsVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<PrjBbsVO> view(PrjBbsVO vo) throws Exception {
		ProcessResultVO<PrjBbsVO> resultVO = new ProcessResultVO<PrjBbsVO>();
		try {
			PrjBbsVO returnVO = prjBbsMapper.select(vo);
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
	 * 팀프로젝트 게시판 정보 수정
	 * @author mhShin 
	 * @date 2013. 10. 30.
	 * @param vo
	 * @return ProcessResultVO<PrjBbsVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<PrjBbsVO> edit(PrjBbsVO vo) throws Exception {
		ProcessResultVO<PrjBbsVO> resultVO = new ProcessResultVO<PrjBbsVO>();
		try {
			prjBbsMapper.update(vo);
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
	 * 팀프로젝트 게시판 정보 삭제
	 * @author mhShin 
	 * @date 2013. 10. 30.
	 * @param vo
	 * @return ProcessResultVO<PrjBbsVO>
	 * @throws Exception 
	 */
	@Override
	public ProcessResultVO<?> remove(PrjBbsVO vo) throws Exception {
		
		
		ProcessResultVO<?> resultVO = new ProcessResultVO();
		try {
			PrjBbsAtclVO prjBbsAtclVO = new PrjBbsAtclVO();
			prjBbsAtclVO.setBbsCd(vo.getBbsCd());
			prjBbsAtclMapper.deleteBbsAll(prjBbsAtclVO);
			
			PrjBbsCmntVO prjBbsCmntVO = new PrjBbsCmntVO();
			prjBbsCmntVO.setBbsCd(vo.getBbsCd());
			prjBbsCmntMapper.deleteBbsAll(prjBbsCmntVO);

			prjBbsMapper.delete(vo);
			resultVO.setResultSuccess();
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResultFailed();
			resultVO.setMessage(e.getMessage());
		}

		
		return resultVO;
	}
}

