package egovframework.edutrack.modules.library.cnts.share.req.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.library.cnts.share.req.service.ClibCntsShareReqService;
import egovframework.edutrack.modules.library.cnts.share.req.service.ClibCntsShareReqVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("clibCntsShareReqService")
public class ClibCntsShareReqServiceImpl extends EgovAbstractServiceImpl implements ClibCntsShareReqService{

	/** Mapper */
	@Resource(name="clibCntsShareReqMapper")
	private ClibCntsShareReqMapper clibCntsShareReqMapper;

	/**
	 * 콘텐츠 공유요청 목록으로 가져온다.
	 * @param ClibCntsShareReqVO.cntsCd
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultListVO<ClibCntsShareReqVO> list(ClibCntsShareReqVO vo) throws Exception {
		ProcessResultListVO<ClibCntsShareReqVO> resultList = new ProcessResultListVO<ClibCntsShareReqVO>();
		try {
			List<ClibCntsShareReqVO> returnList = clibCntsShareReqMapper.list(vo);
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
	 *  콘텐츠 공유요청 정보를 조회한다.
	 * @param ClibCntsShareReqVO.cntsCd
	 * @return ProcessReslutListVO
	 */
	@Override
	public ProcessResultVO<ClibCntsShareReqVO> view(ClibCntsShareReqVO vo) throws Exception {
		ProcessResultVO<ClibCntsShareReqVO> resultVO = new ProcessResultVO<ClibCntsShareReqVO>();
		try {
			ClibCntsShareReqVO returnVO = clibCntsShareReqMapper.select(vo);
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
	 * 콘텐츠 공유요청 정보를 등록하고 결과를 반환한다.
	 * @param ClibCntsShareReqVO
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ClibCntsShareReqVO> add(ClibCntsShareReqVO vo) throws Exception {
		ProcessResultVO<ClibCntsShareReqVO> resultVO = new ProcessResultVO<ClibCntsShareReqVO>();
		try {
			//---- 신규 코드 세팅
			vo.setReqSn(clibCntsShareReqMapper.selectKey());
			clibCntsShareReqMapper.insert(vo);
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
	 * 콘텐츠 공유요청 정보를 등록하고 결과를 반환한다.
	 * @param ClibCntsShareReqVO
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ClibCntsShareReqVO> edit(ClibCntsShareReqVO vo) throws Exception {
		ProcessResultVO<ClibCntsShareReqVO> resultVO = new ProcessResultVO<ClibCntsShareReqVO>();
		try {
			clibCntsShareReqMapper.update(vo);
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
	 * 콘텐츠 공유요청 정보를 등록하고 결과를 반환한다.
	 * @param ClibCntsShareReqVO
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<ClibCntsShareReqVO> delete(ClibCntsShareReqVO vo) throws Exception {
		ProcessResultVO<ClibCntsShareReqVO> resultVO = new ProcessResultVO<ClibCntsShareReqVO>();
		try {
			clibCntsShareReqMapper.delete(vo);
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
