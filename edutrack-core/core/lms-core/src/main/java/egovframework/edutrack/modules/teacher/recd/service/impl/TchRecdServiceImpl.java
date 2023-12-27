package egovframework.edutrack.modules.teacher.recd.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.edutrack.modules.teacher.recd.service.TchRecdService;
import egovframework.edutrack.modules.teacher.recd.service.TchRecdVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


@Service("tchRecdService")
public class TchRecdServiceImpl extends EgovAbstractServiceImpl implements TchRecdService {

	private final class RecdFileHandler
			implements FileHandler<TchRecdVO> {

		public String getRepoCd() {
			return "TCH_LEC_RECD";
		}

		public String getPK(TchRecdVO dto) {
			return dto.getUserNo() + Constants.SEPERATER_DB + dto.getLecRecdSn();
		}

		public List<SysFileVO> getFiles(TchRecdVO dto) {
			return dto.getLecRecdFiles();
		}

		public TchRecdVO setFiles(TchRecdVO dto, FileListVO fileListVO) {
			dto.setLecRecdFiles(fileListVO.getFiles("file"));
			return dto;
		}
	}

	
	@Resource(name="tchRecdMapper")
	private TchRecdMapper 		tchRecdMapper;
	
	@Resource(name="sysFileService")
	private SysFileService 		sysFileService;

	/**
	 * 강사 강의이력 목록 조회
	 *
	 * @return ProcessResultListVO
	 * @throws Exception 
	 */
	public ProcessResultListVO<TchRecdVO> listRecd(TchRecdVO teacherRecdVO, int curPage) throws Exception {
		teacherRecdVO.setCurPage(curPage);
		ProcessResultListVO<TchRecdVO> resultList = new ProcessResultListVO<TchRecdVO>();
		try {
			List<TchRecdVO> returnList =  tchRecdMapper.listRecd(teacherRecdVO);
			List<TchRecdVO> list = sysFileService.getFile(returnList, new RecdFileHandler());
			resultList.setReturnList(list);
		} catch (Exception e){
			e.printStackTrace();
			resultList.setMessage(e.getMessage());
		}
		return resultList;
	}

	/**
	 * 강사 강의이력 상세 조회
	 *
	 * @return  ProcessResultVO
	 * @throws Exception 
	 */
	public ProcessResultVO<TchRecdVO> viewRecd(TchRecdVO teacherRecdVO) throws Exception {
		teacherRecdVO = tchRecdMapper.selectRecd(teacherRecdVO);
		teacherRecdVO = sysFileService.getFile(teacherRecdVO, new RecdFileHandler());
		return new ProcessResultVO<TchRecdVO>().setResultSuccess().setReturnVO(teacherRecdVO);
	}



	/**
	 * 강사 강의이력 입력
	 *
	 * @return  ProcessResultVO
	 * @throws Exception 
	 */
	public ProcessResultVO<TchRecdVO> addRecd(TchRecdVO teacherRecdVO) throws Exception{
		teacherRecdVO.setLecRecdSn(tchRecdMapper.selectKey());
		tchRecdMapper.insertRecd(teacherRecdVO);
		sysFileService.bindFile(teacherRecdVO, new RecdFileHandler());
		return ProcessResultVO.success();
	}

	/**
	 * 강사강의안 수정
	 *
	 * @return  ProcessResultVO
	 * @throws Exception 
	 */
	public ProcessResultVO<TchRecdVO> editRecd(TchRecdVO teacherRecdVO) throws Exception{
		tchRecdMapper.updateRecd(teacherRecdVO);
		sysFileService.bindFileUpdate(teacherRecdVO, new RecdFileHandler());
		return ProcessResultVO.success();
	}

	/**
	 * 강사 강의이력 삭제
	 * @param  삭제 대상 코드값
	 * @return
	 * @throws Exception 
	 */
	public ProcessResultVO<TchRecdVO> deleteRecd(TchRecdVO teacherRecdVO) throws Exception{
		sysFileService.removeFile(teacherRecdVO, new RecdFileHandler());
		tchRecdMapper.deleteRecd(teacherRecdVO);
		return ProcessResultVO.success();
	}
}


