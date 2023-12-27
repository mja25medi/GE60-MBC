package egovframework.edutrack.modules.opencourse.subject.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.modules.opencourse.subject.service.OpenCrsSbjService;
import egovframework.edutrack.modules.opencourse.subject.service.OpenCrsSbjVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("openCrsSbjService")
public class OpenCrsSbjServiceImpl
	extends EgovAbstractServiceImpl implements OpenCrsSbjService {

	@Resource(name="openCrsSbjMapper")
	private OpenCrsSbjMapper 		openCrsSbjMapper;
	
	/**
	 * 공개강좌 연결과목 목록 조회
	 *
	 * @return ProcessResultListvo
	 */
	@Override
	public ProcessResultListVO<OpenCrsSbjVO> list(OpenCrsSbjVO vo) throws Exception {
		ProcessResultListVO<OpenCrsSbjVO> resultList = new ProcessResultListVO<OpenCrsSbjVO>();
		try {
			List<OpenCrsSbjVO> returnList = openCrsSbjMapper.list(vo);
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
	 * 공개강좌 연결과목 정보 조회
	 *
	 * @return  ProcessResultvo
	 */
	@Override
	public ProcessResultVO<OpenCrsSbjVO> view(OpenCrsSbjVO vo) throws Exception {
		ProcessResultVO<OpenCrsSbjVO> resultVO = new ProcessResultVO<OpenCrsSbjVO>();
		try {
			OpenCrsSbjVO returnVO = openCrsSbjMapper.select(vo);
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
	 * 공개강좌 연결과목 정보 등록
	 *
	 * @return  ProcessResultvo
	 */
	@Override
	public ProcessResultVO<OpenCrsSbjVO> add(OpenCrsSbjVO vo) throws Exception {
		ProcessResultVO<OpenCrsSbjVO> resultVO = new ProcessResultVO<OpenCrsSbjVO>();
		try {
			openCrsSbjMapper.insert(vo);
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
	 * 공개강좌 연결과목 정보 수정
	 *
	 * @return  ProcessResultvo
	 */
	@Override
	public ProcessResultVO<OpenCrsSbjVO> edit(OpenCrsSbjVO vo) throws Exception {
		ProcessResultVO<OpenCrsSbjVO> resultVO = new ProcessResultVO<OpenCrsSbjVO>();
		try {
			openCrsSbjMapper.update(vo);
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
	 * 공개강좌 연결과목 정보 삭제
	 *
	 * @return
	 */
	@Override
	public ProcessResultVO<?> remove(OpenCrsSbjVO vo) throws Exception {
		ProcessResultVO<OpenCrsSbjVO> resultVO = new ProcessResultVO<OpenCrsSbjVO>();
		try {
			openCrsSbjMapper.delete(vo);
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
	 * 공개강좌 연결과목 정렬 순서 변경
	 * @param iCodevo
	 * @return
	 */
	@Override
	public ProcessResultVO<?> sort(OpenCrsSbjVO vo) throws Exception {
		String[] itemList = StringUtil.split(vo.getSbjCd(),"|");
		// 과목의 목록을 조회한다.
		List<OpenCrsSbjVO> itemArray = openCrsSbjMapper.list(vo);
		// 이중 포문으로 itemArray에 변경된 order를 다시 저장
		for (OpenCrsSbjVO ocsvo : itemArray) {
			for (int order = 0; order < itemList.length; order++) {
				if(ocsvo.getSbjCd().equals(itemList[order])) {
					ocsvo.setSbjOdr(order+1);	// 1부터 차례로 순서값을 지정
				}
			}
		}
		openCrsSbjMapper.updateBatch(itemArray);
		return ProcessResultVO.success();
	}
}
