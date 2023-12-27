
package egovframework.edutrack.modules.org.equ.service;

import java.io.OutputStream;
import java.util.List;

import egovframework.edutrack.comm.service.ProcessResultListVO;

public interface OrgEquService {
	
	public int addEquipment(OrgEquVO vo) throws Exception;
	public OrgEquVO viewEquipment(OrgEquVO vo) throws Exception;
	public ProcessResultListVO<OrgEquVO> listPagingEquipment(OrgEquVO vo);
	public ProcessResultListVO<OrgEquVO> listPagingEquipment(OrgEquVO vo, boolean fileIn);
	public int updateEquipment(OrgEquVO vo) throws Exception;
	public int deleteEquipment(OrgEquVO vo) throws Exception;
	
	public int addItem(OrgEquItemVO vo);
	public OrgEquItemVO viewItem(OrgEquItemVO vo) throws Exception;
	public List<OrgEquItemVO> listItem(OrgEquItemVO vo);
	public List<OrgEquItemVO> validListItem(OrgEquItemVO vo);
	public ProcessResultListVO<OrgEquItemVO> listPagingItem(OrgEquItemVO vo);
	public int updateItem(OrgEquItemVO vo);
	public int updateItemUse(OrgEquItemVO vo);
	public int deleteItem(OrgEquItemVO vo);
	
	public int addRent(OrgEquRentVO vo);
	public OrgEquRentVO viewRent(OrgEquRentVO vo) throws Exception;
	public List<OrgEquRentVO> listRent(OrgEquRentVO vo);
	public ProcessResultListVO<OrgEquRentVO> listPagingRent(OrgEquRentVO vo);
	public int updateRent(OrgEquRentVO vo);
	public int deleteRent(OrgEquRentVO vo);
	
	public int cancelRent(OrgEquRentVO vo);
	public int chkEnableItemCnt(OrgEquRentVO vo);
	public int updateEquipmentUse(OrgEquVO vo);
	
	public int updateEquOdr(OrgEquVO vo);
	public void sampleExcelDownload(OutputStream os) throws Exception;
	public ProcessResultListVO<OrgEquItemVO> excelUploadItemAdd(OrgEquItemVO vo, String fileName, String filePath, String orgCd) throws Exception;
	
	public void rentListExcelDownload(OrgEquRentVO vo, OutputStream os) throws Exception;
}