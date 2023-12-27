package egovframework.edutrack.modules.teacher.item.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.edutrack.Constants;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;
import egovframework.edutrack.modules.system.file.service.SysFileService;
import egovframework.edutrack.modules.system.file.service.SysFileVO;
import egovframework.edutrack.modules.system.file.service.support.FileHandler;
import egovframework.edutrack.modules.system.file.service.support.FileListVO;
import egovframework.edutrack.modules.teacher.item.service.TchItemService;
import egovframework.edutrack.modules.teacher.item.service.TchItemVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


@Service("tchItemService")
public class TchItemServiceImpl extends EgovAbstractServiceImpl implements TchItemService {

	private final class ItemFileHandler
			implements FileHandler<TchItemVO> {

		public String getRepoCd() {
			return "TCH_LEC_ITEM";
		}

		public String getPK(TchItemVO dto) {
			return dto.getUserNo() + Constants.SEPERATER_DB + dto.getLecItemSn();
		}

		public List<SysFileVO> getFiles(TchItemVO dto) {
			return dto.getLecItemFiles();
		}

		@Override
		public TchItemVO setFiles(TchItemVO dto, FileListVO fileListVO) {
			dto.setLecItemFiles(fileListVO.getFiles("file"));
			return dto;
		}
	}

	
	@Resource(name="tchItemMapper")
	private TchItemMapper 		tchItemMapper;
	
	@Resource(name="sysFileService")
	private SysFileService 		sysFileService;

	/**
	 * 강사 강의안 목록 조회
	 *
	 * @return ProcessResultListVO
	 * @throws Exception 
	 */
	public ProcessResultListVO<TchItemVO> listItem(TchItemVO teacherItemVO, int curPage) throws Exception {
		teacherItemVO.setCurPage(curPage);
		ProcessResultListVO<TchItemVO> resultList = new ProcessResultListVO<TchItemVO>();
		try {
			List<TchItemVO> returnList =  tchItemMapper.listItem(teacherItemVO);
			List<TchItemVO> list = sysFileService.getFile(returnList, new ItemFileHandler());
			resultList.setReturnList(list);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

	/**
	 * 강사 강의안 상세 조회
	 *
	 * @return  ProcessResultVO
	 * @throws Exception 
	 */
	public ProcessResultVO<TchItemVO> viewItem(TchItemVO teacherItemVO) throws Exception {
		teacherItemVO = tchItemMapper.selectItem(teacherItemVO);
		teacherItemVO = sysFileService.getFile(teacherItemVO, new ItemFileHandler());
		return new ProcessResultVO<TchItemVO>().setResultSuccess().setReturnVO(teacherItemVO);
	}



	/**
	 * 강사 강의안 입력
	 *
	 * @return  ProcessResultVO
	 * @throws Exception 
	 */
	public ProcessResultVO<TchItemVO> addItem(TchItemVO teacherItemVO) throws Exception{
		teacherItemVO.setLecItemSn(tchItemMapper.selectKey());
		tchItemMapper.insertItem(teacherItemVO);
		sysFileService.bindFile(teacherItemVO, new ItemFileHandler());
		return ProcessResultVO.success();
	}

	/**
	 * 강사강의안 수정
	 *
	 * @return  ProcessResultVO
	 * @throws Exception 
	 */
	public ProcessResultVO<TchItemVO> editItem(TchItemVO teacherItemVO) throws Exception{
		tchItemMapper.updateItem(teacherItemVO);
		sysFileService.bindFileUpdate(teacherItemVO, new ItemFileHandler());
		return ProcessResultVO.success();
	}

	/**
	 * 강사 강의안 삭제
	 * @param  삭제 대상 코드값
	 * @return
	 * @throws Exception 
	 */
	public ProcessResultVO<TchItemVO> deleteItem(TchItemVO teacherItemVO) throws Exception{
		sysFileService.removeFile(teacherItemVO, new ItemFileHandler());
		tchItemMapper.deleteItem(teacherItemVO);
		return ProcessResultVO.success();
	}

}


