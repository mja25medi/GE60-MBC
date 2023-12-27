package egovframework.edutrack.modules.opencourse.ctgr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.opencourse.ctgr.service.OpenCrsCtgrService;
import egovframework.edutrack.modules.opencourse.ctgr.service.OpenCrsCtgrVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("openCrsCtgrService")
public class OpenCrsCtgrServiceImpl
	extends EgovAbstractServiceImpl implements OpenCrsCtgrService {

	/** Mapper */
	@Resource(name="openCrsCtgrMapper")
	private OpenCrsCtgrMapper			openCrsCtgrMapper;

	/**
	 * 공개강좌 분류 목록 조회
	 *
	 * @return ProcessResultListVO
	 */
	@Override
	public ProcessResultListVO<OpenCrsCtgrVO> list(OpenCrsCtgrVO vo) throws Exception {
		ProcessResultListVO<OpenCrsCtgrVO> resultList = new ProcessResultListVO<OpenCrsCtgrVO>();
		try {
			List<OpenCrsCtgrVO> returnList = openCrsCtgrMapper.list(vo);
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
	 * 공개강좌 분류 정보 조회
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<OpenCrsCtgrVO> view(OpenCrsCtgrVO vo) throws Exception {
		ProcessResultVO<OpenCrsCtgrVO> resultVO = new ProcessResultVO<OpenCrsCtgrVO>();
		try {
			OpenCrsCtgrVO returnVO = openCrsCtgrMapper.select(vo);
			resultVO.setReturnVO(returnVO);
			resultVO.setResult(1);
		} catch (Exception e){
			e.printStackTrace();
			resultVO.setResult(-1);
			resultVO.setMessage(e.getMessage());
		}		
		return resultVO;
	}

	/**
	 * 공개강좌 분류 정보 등록
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<OpenCrsCtgrVO> add(OpenCrsCtgrVO vo) throws Exception {
		ProcessResultVO<OpenCrsCtgrVO> resultVO = new ProcessResultVO<OpenCrsCtgrVO>();
		try {
			if(ValidationUtils.isEmpty(vo.getCtgrCd())) {
				vo.setCtgrCd(openCrsCtgrMapper.selectKey());
			}
			openCrsCtgrMapper.insert(vo);
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
	 * 공개강좌 분류 정보 수정
	 *
	 * @return  ProcessResultVO
	 */
	@Override
	public ProcessResultVO<OpenCrsCtgrVO> edit(OpenCrsCtgrVO vo) throws Exception {
		ProcessResultVO<OpenCrsCtgrVO> resultVO = new ProcessResultVO<OpenCrsCtgrVO>();
		try {
			openCrsCtgrMapper.update(vo);
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
	 * 공개강좌 분류 정보 삭제
	 *
	 * @return
	 */
	@Override
	public ProcessResultVO<?> remove(OpenCrsCtgrVO vo) throws Exception {
		ProcessResultVO<OpenCrsCtgrVO> resultVO = new ProcessResultVO<OpenCrsCtgrVO>();
		try {
			openCrsCtgrMapper.delete(vo);
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
	 * 공개강좌 분류 정렬 순서 변경
	 * @param iCodeVO
	 * @return
	 */
	@Override
	public ProcessResultVO<?> sort(OpenCrsCtgrVO vo) throws Exception {
		String[] itemList = StringUtil.split(vo.getCtgrCd(),"|");
		// 과목의 목록을 조회한다.
		List<OpenCrsCtgrVO> itemArray = openCrsCtgrMapper.list(vo);
		// 이중 포문으로 itemArray에 변경된 order를 다시 저장
		for (OpenCrsCtgrVO occvo : itemArray) {
			for (int order = 0; order < itemList.length; order++) {
				if(occvo.getCtgrCd().equals(itemList[order])) {
					occvo.setCtgrOdr(order+1);	// 1부터 차례로 순서값을 지정
				}
			}
		}
		openCrsCtgrMapper.updateBatch(itemArray);
		return ProcessResultVO.success();
	}
}
