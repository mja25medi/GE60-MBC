package egovframework.edutrack.modules.org.menu.service.impl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.util.web.StringUtil;
import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.board.bbs.service.BrdBbsInfoVO;
import egovframework.edutrack.modules.board.bbs.service.impl.BrdBbsInfoMapper;
import egovframework.edutrack.modules.org.info.service.OrgOrgInfoVO;
import egovframework.edutrack.modules.org.info.service.impl.OrgOrgInfoMapper;
import egovframework.edutrack.modules.org.menu.service.OrgAuthGrpMenuVO;
import egovframework.edutrack.modules.org.menu.service.OrgMenuLangVO;
import egovframework.edutrack.modules.org.menu.service.OrgMenuService;
import egovframework.edutrack.modules.org.menu.service.OrgMenuVO;
import egovframework.edutrack.modules.org.page.service.OrgPageVO;
import egovframework.edutrack.modules.org.page.service.impl.OrgPageMapper;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 *  <b>기관 - 기관 메뉴 관리</b> 영역  Service 클래스
 * @author Jamfam
 *
 */
@Service("orgMenuService")
public class OrgMenuServiceImpl 
	extends EgovAbstractServiceImpl implements OrgMenuService {
	
	/**
	 * 메뉴값을 저장하는 내부 변수 [캐쉬 저장소]
	 * key : menuType + "_" + authGrp
	 * value : 해당되는 rootMenuDTO
	 */
	private final Hashtable<String, OrgMenuVO> menuCache = new Hashtable<String, OrgMenuVO>();

	/**
	 * 캐쉬저장소의 유효성을 판단하는 버젼값
	 */
	private int menuVersion = -1;	

/*    @Resource(name="orgAuthGrpMenuDAO")
    private OrgAuthGrpMenuMapper 	orgAuthGrpMenuMapper;
    
    @Resource(name="orgMenuLangDAO")
    private OrgMenuLangMapper 		orgMenuLangMapper;    

    @Resource(name="orgMenuDAO")
    private OrgMenuMapper 			orgMenuMapper;

    @Resource(name="orgOrgInfoDAO")
    private OrgOrgInfoMapper 		orgOrgInfoMapper;

    @Resource(name="brdBbsInfoDAO")
    private BrdBbsInfoDAO 		brdBbsInfoMapper;

    @Resource(name="orgPageDAO")
    private OrgPageDAO 			orgPageMapper;*/
	
	/** Mapper */
	@Resource(name="orgAuthGrpMenuMapper")
	private OrgAuthGrpMenuMapper 		orgAuthGrpMenuMapper;
	
	@Resource(name="orgMenuLangMapper")
	private OrgMenuLangMapper 		orgMenuLangMapper;

	@Resource(name="orgMenuMapper")
	private OrgMenuMapper 		orgMenuMapper;

	@Resource(name="orgOrgInfoMapper")
	private OrgOrgInfoMapper 		orgOrgInfoMapper;
	
	@Resource(name="brdBbsInfoMapper")
	private BrdBbsInfoMapper 		brdBbsInfoMapper;

	@Resource(name="orgPageMapper")
	private OrgPageMapper 		orgPageMapper;
    
    /**
 	 *  메뉴의 전체 목록을 트리 형태로 조회한다.
 	 * @param OrgMenuVO
 	 * @return ProcessResultListVO
 	 * @throws Exception
 	 */
	@Override
	public ProcessResultListVO<OrgMenuVO> listTreeMenu(OrgMenuVO vo) throws Exception {
 		ProcessResultListVO<OrgMenuVO> resultList = new ProcessResultListVO<OrgMenuVO>(); 
 		List<OrgMenuVO> result =  this.listMenuByDB(vo);

		//최상단용 VO 만들기
 		OrgMenuVO smvo = new OrgMenuVO();

		//-- 트리형태로 목록을 구성하여 내보낸다.
		for (OrgMenuVO parent : result) {
			if(StringUtil.isNull(parent.getParMenuCd())) {
				smvo.addSubMenu(parent);
			}
			for (OrgMenuVO child : result) {
				if(parent.getMenuCd().equals(child.getParMenuCd())) {
					parent.addSubMenu(child);
				}
			}
		}
		//반환할 리스트 만들기
		resultList.setReturnList(smvo.getSubList());
 		return resultList;
 	}  	
 	
    /**
 	 *  메뉴의 전체 목록을 디비에서 조회한다.
 	 * @param OrgMenuVO
 	 * @return ProcessResultListVO
 	 * @throws Exception
 	 */
	private List<OrgMenuVO> listMenuByDB(OrgMenuVO vo) throws Exception {
 		List<OrgMenuVO> result =  orgMenuMapper.list(vo);
 		List<OrgMenuVO> menuList = new ArrayList<OrgMenuVO>();
		for(OrgMenuVO smvo : result) {
			OrgMenuLangVO smlvo = new OrgMenuLangVO();
			smlvo.setOrgCd(smvo.getOrgCd());
			smlvo.setMenuCd(smvo.getMenuCd());
			List<OrgMenuLangVO> menuLangList = orgMenuLangMapper.list(smlvo);
			smvo.setMenuLangList(menuLangList);
			menuList.add(smvo);
		}
 		return menuList;
 	}
	
	/**
	 * 기관의 권한 메뉴 조회
	 * 하위 메뉴를 포함한 최상위 메뉴 DTO를 반환함.
	 * @param dto.orgCd : 기관 코드 (필수)
	 * @param dto.menuType : 메뉴 유형 (필수)
	 * @return ProcessResultListDTO
	 */
	@Override
	public OrgMenuVO listAuthGrpTreeMenu(OrgMenuVO vo) throws Exception {
		OrgOrgInfoVO ooivo = new OrgOrgInfoVO();
		ooivo.setOrgCd(vo.getOrgCd());
		
		String menuKey = vo.getOrgCd()+"."+vo.getMenuType()+"."+vo.getAuthGrpCd();
		OrgMenuVO rootMenu = null;

		if(this.isMenuChanged(ooivo)) {
			this.menuCache.clear();
			this.menuVersion = orgOrgInfoMapper.selectMenuVersion(ooivo);
		}

		if(!this.menuCache.containsKey(menuKey)) {
			this.menuCache.put(menuKey, this.rootMenuVOFromDB(vo));
		} else {
			//-- 캐시가 있는 경우 아무것도 안해도됨.
		}
		rootMenu = this.menuCache.get(menuKey);
		OrgMenuVO test = new OrgMenuVO();
		test = findMenuByMenuCd(rootMenu, vo.getMenuCd());
		
		return test;

		//return findMenuByMenuCd(rootMenu, vo.getMenuCd());
	}

	/**
	 * 실제 DB에서 메뉴를 검색하여, 상하위 구조화 하여 menuDTO 반환
	 * 하위 메뉴를 포함한 최상위 메뉴 DTO를 반환함.
	 * @param dto.orgCd : 기관 코드 (필수)
	 * @param dto.menuType : 메뉴 유형 (필수)
	 * @return ProcessResultListDTO
	 */
	private OrgMenuVO rootMenuVOFromDB(OrgMenuVO vo) throws Exception {
		//-- 최상위 메뉴 DTO 초기화 셋팅
		OrgMenuVO omvo = new OrgMenuVO();
		omvo.setOrgCd(vo.getOrgCd());
		omvo.setMenuType(vo.getMenuType());
		omvo.setMenuCd("ROOTMENU");
		omvo.setMenuNm("ROOTMENU");
		omvo.setAuthGrpCd(vo.getAuthGrpCd());
		omvo.setParMenuCd("");
		omvo.setMenuLvl(0);

		//--- 기관의 메뉴 목록 전체를 가져와 Tree 형태로 구조화 한다.
		List<OrgMenuVO> resultList = orgMenuMapper.listAuthGrpMenu(omvo);
		for (OrgMenuVO parent : resultList) {
			if(StringUtil.isNull(parent.getParMenuCd())) {
				//-- 메뉴 언어 리스트 셋팅
				OrgMenuLangVO omlvo = new OrgMenuLangVO();
				omlvo.setOrgCd(parent.getOrgCd());
				omlvo.setMenuCd(parent.getMenuCd());
				List<OrgMenuLangVO> orgMenuLangList = orgMenuLangMapper.list(omlvo);
				parent.setMenuLangList(orgMenuLangList);
				omvo.addSubMenu(parent);
			}
			for (OrgMenuVO child : resultList) {
				if(parent.getMenuCd().equals(child.getParMenuCd())) {
					//-- 메뉴 언어 리스트 셋팅
					OrgMenuLangVO omlvo = new OrgMenuLangVO();
					omlvo.setOrgCd(child.getOrgCd());
					omlvo.setMenuCd(child.getMenuCd());
					List<OrgMenuLangVO> orgMenuLangList = orgMenuLangMapper.list(omlvo);
					child.setMenuLangList(orgMenuLangList);
					parent.addSubMenu(child);
				}
			}
		}
		return omvo;
	}	

	/**
	 * 주어진 MenuDTO에서 menuCd에 해당되는 메뉴를 찾아서 반환한다.
	 * @param rootMenu 찾고자 하는 대상 루트메뉴
	 * @param menuCd 찾을 MenuCd
	 * @return 찾아진 MenuDTO, 없을 경우 null 반환.
	 */
	@Override
	public OrgMenuVO findMenuByMenuCd(OrgMenuVO rootMenu, String menuCd) {
		// rootMenu가 menuCd와 일치하거나,
		// 주어진 검색키(menuCd)가 없으면 바로 rootMenu를 반환. (기본값으로 rootMenu가 반환)
		if(StringUtil.isNull(menuCd) || rootMenu.getMenuCd().equals(menuCd))
			return rootMenu;

		OrgMenuVO result = null;

		// childMenu가 있을 경우
		if(rootMenu.getSubList().size() > 0) {
			for (OrgMenuVO current : rootMenu.getSubList()) {
				// 탐색 및 자식메뉴들에 대한 재귀호출
				if(current.getMenuCd().equals(menuCd)) {
					return current;
				} else {
					result = findMenuByMenuCd(current, menuCd);
					if(result != null)
						return result;
				}
			}
		}
		return result;
	}	
	
	/**
	 * 기관 메뉴의 단일 레코드를 반환한다.
	 * @param dto.orgCd : 기관 코드 (필수)
	 * @param dto.menuType : 메뉴 유형 (필수)
	 * @param dto.menuCd : 메뉴 코드 (필수)
	 * @return ProcessResultListDTO
	 */
	@Override
	public OrgMenuVO viewMenu(OrgMenuVO vo) throws Exception {
		vo = orgMenuMapper.select(vo);
		//-- 메뉴 언어 리스트 셋팅
		OrgMenuLangVO omlvo = new OrgMenuLangVO();
		omlvo.setOrgCd(vo.getOrgCd());
		omlvo.setMenuCd(vo.getMenuCd());
		List<OrgMenuLangVO> orgMenuLangList = orgMenuLangMapper.list(omlvo);
		vo.setMenuLangList(orgMenuLangList);
		return vo;
	}	
	
 	/**
 	 * 기관의 메뉴를 등록한다.
 	 * @param OrgMenuVO
 	 * @return String
 	 * @throws Exception
 	 */
	@Override
	public int addMenu(OrgMenuVO vo) throws Exception {

		String orgCd = vo.getOrgCd();
		String option1 = StringUtil.nvl(vo.getOptnCtgrCd1(),""); //-- bbs, page
		String option2 = StringUtil.nvl(vo.getOptnCtgrCd2(),""); //-- bbsCd, pageCd

		// 상위 메뉴 정보 조회
		OrgMenuVO parentMenu = new OrgMenuVO();
		parentMenu.setOrgCd(vo.getOrgCd());
		parentMenu.setMenuType(vo.getMenuType());
		parentMenu.setMenuCd(vo.getParMenuCd());
		
		if(vo.getParMenuCd() == null || "".equals(vo.getParMenuCd())){
			parentMenu.setMenuLvl(0);
		}else{
			parentMenu = orgMenuMapper.select(parentMenu);
		}

		String menuCd = vo.getMenuCd();
		if("Y".equals(vo.getAutoMakeYn())) {
			//---- 메뉴 코드 신규 생성
			menuCd = orgMenuMapper.selectKey();
		}

		//---- 신규 메뉴코드 세팅
		vo.setMenuCd(menuCd);

		//---- 메뉴 레벨 : 상위 메뉴 레벨 + 1
		vo.setMenuLvl(parentMenu.getMenuLvl()+1);

		//---- 메뉴 경로 : 상위 메뉴 경로 + 현제 메뉴 코드
		vo.setMenuPath(StringUtil.nvl(parentMenu.getMenuPath(),"")+"|"+menuCd);

		//-- 메뉴 등록
		int result = orgMenuMapper.insert(vo);

		OrgOrgInfoVO ooivo = new OrgOrgInfoVO();
		ooivo.setOrgCd(vo.getOrgCd());
		
		this.setMenuChanged(ooivo);	// DB의 변경 여부를 기록

		//-- Lang별 메뉴명 등록
		List<OrgMenuLangVO> menuLangList = vo.getMenuLangList();
		for(OrgMenuLangVO omldto : menuLangList) {
			try {
				omldto.setOrgCd(vo.getOrgCd());
				omldto.setMenuCd(vo.getMenuCd());
				orgMenuLangMapper.insert(omldto);
			} catch(Exception e) {
				omldto.setOrgCd(vo.getOrgCd());
				omldto.setMenuCd(vo.getMenuCd());
				orgMenuLangMapper.update(omldto);
			}
		}

		//-- 게시판, 페이지 정보에 메뉴코드 등록
		if("BBS".equals(option1)) {
			BrdBbsInfoVO bbivo = new BrdBbsInfoVO();
			bbivo.setOrgCd(orgCd);
			bbivo.setBbsCd(option2);
			bbivo.setMenuCd(menuCd);
			bbivo.setModNo(vo.getModNo());
			brdBbsInfoMapper.updateMenuCd(bbivo);
		}
		else if("PAGE".equals(option1)) {
			OrgPageVO opvo = new OrgPageVO();
			opvo.setOrgCd(orgCd);
			opvo.setPageCd(option2);
			opvo.setMenuCd(menuCd);
			opvo.setModNo(vo.getModNo());
			orgPageMapper.updateMenuCd(opvo);
		}
		return result;
	}	
	
 	/**
 	 * 기관의 메뉴를 수정한다.
 	 * @param OrgMenuVO
 	 * @return int
 	 * @throws Exception
 	 */
	@Override
	public int editMenu(OrgMenuVO vo) throws Exception {
		//-- 해당 메뉴에 속한 모든 게시판 및 페이지를 초기화 한다.
		BrdBbsInfoVO bbivo = new BrdBbsInfoVO();
		bbivo.setOrgCd(vo.getOrgCd());
		bbivo.setMenuCd(vo.getMenuCd());
		brdBbsInfoMapper.updateMenuCdToNull(bbivo);

		OrgPageVO opvo = new OrgPageVO();
		opvo.setOrgCd(vo.getOrgCd());
		opvo.setMenuCd(vo.getMenuCd());
		orgPageMapper.updateMenuCdToNull(opvo);

		//-- 기존의 메뉴 정보를 가져온다.
		// 변경전의 메뉴 Path를 가져와 셋팅한다.
		OrgMenuVO oldMenuVO = orgMenuMapper.select(vo);
		vo.setMenuPath(oldMenuVO.getMenuPath());

		String option1 = vo.getOptnCtgrCd1();
		String option2 = vo.getOptnCtgrCd2();

		//-- 메뉴 정보 Update
		int result = orgMenuMapper.update(vo);

		//-- 메뉴 Lang 정보 Update
		List<OrgMenuLangVO> menuLangList = vo.getMenuLangList();
		for(OrgMenuLangVO omldto : menuLangList) {
			try {
				orgMenuLangMapper.insert(omldto);
			} catch(Exception e) {
				orgMenuLangMapper.update(omldto);
			}
		}

		OrgOrgInfoVO ooivo = new OrgOrgInfoVO();
		ooivo.setOrgCd(vo.getOrgCd());
		this.setMenuChanged(ooivo);	// DB의 변경 여부를 기록

		//-- 신규 메뉴 정보를 이용해 연결된 메뉴 코드 변경
		if("BBS".equals(option1)) {
			BrdBbsInfoVO sbbivo = new BrdBbsInfoVO();
			sbbivo.setOrgCd(vo.getOrgCd());
			sbbivo.setBbsCd(option2);
			sbbivo.setMenuCd(vo.getMenuCd());
			sbbivo.setModNo(vo.getModNo());
			brdBbsInfoMapper.updateMenuCd(sbbivo);
		}
		else if("PAGE".equals(option1)) {
			OrgPageVO sopvo = new OrgPageVO();
			sopvo.setOrgCd(vo.getOrgCd());
			sopvo.setPageCd(option2);
			sopvo.setMenuCd(vo.getMenuCd());
			sopvo.setModNo(vo.getModNo());
			orgPageMapper.updateMenuCd(sopvo);
		}
		return result;
	}
	
 	/**
 	 * 기관의 메뉴를 삭제한다.
 	 * @param OrgMenuVO
 	 * @return int
 	 * @throws Exception
 	 */
	@Override
	public int removeMenu(OrgMenuVO vo) throws Exception {

		//-- 메뉴 정보 검색
		vo = orgMenuMapper.select(vo);
		
		OrgOrgInfoVO ooivo = new OrgOrgInfoVO();
		ooivo.setOrgCd(vo.getOrgCd());

		String option1 = vo.getOptnCtgrCd1(); // bbs, page
		String option2 = vo.getOptnCtgrCd2(); // bbs_cd, page_cd

		//-- 권한 그룹 값 셋팅
		OrgAuthGrpMenuVO oagmvo = new OrgAuthGrpMenuVO();
		oagmvo.setOrgCd(vo.getOrgCd());
		oagmvo.setMenuType(vo.getMenuType());
		oagmvo.setMenuCd(vo.getMenuCd());

		//-- Lang별 메뉴명 값 셋팅
		OrgMenuLangVO omlvo = new OrgMenuLangVO();
		omlvo.setOrgCd(vo.getOrgCd());
		omlvo.setMenuCd(vo.getMenuCd());

		// 권한 그룹 메뉴 삭제
		orgAuthGrpMenuMapper.delete(oagmvo);

		// Lang별 메뉴명 삭제
		orgMenuLangMapper.deleteAll(omlvo);

		if("BBS".equals(option1)) {
			BrdBbsInfoVO bbivo = new BrdBbsInfoVO();
			bbivo.setOrgCd(vo.getOrgCd());
			bbivo.setBbsCd(option2);
			bbivo.setModNo(vo.getModNo());
			brdBbsInfoMapper.updateMenuCdToNull(bbivo); //-- 기존
		}
		else if("PAGE".equals(option1)) {
			OrgPageVO opvo = new OrgPageVO();
			opvo.setOrgCd(vo.getOrgCd());
			opvo.setPageCd(option2);
			opvo.setModNo(vo.getModNo());
			orgPageMapper.updateMenuCdToNull(opvo);
		}
		//-- DB 정보 삭제
		int result = orgMenuMapper.delete(vo);

		// DB의 변경 여부를 기록
		this.setMenuChanged(ooivo);

		return result;
	}
	
 	/**
 	 * 기관의 메뉴의 순서를 변경한다.
 	 * @param OrgMenuVO
 	 * @return int
 	 * @throws Exception
 	 */
	@Override
	public int moveMenu(OrgMenuVO vo, String moveType) throws Exception {

		//-- 상위 메뉴의 코드를 이용해 같은 Level의 메뉴 목록을 조회한다.
		OrgMenuVO parMenuVO = new OrgMenuVO();
		parMenuVO.setOrgCd(vo.getOrgCd());
		parMenuVO.setMenuType(vo.getMenuType());
		parMenuVO.setMenuCd(vo.getMenuCd());
		parMenuVO = orgMenuMapper.select(parMenuVO);
		
		OrgOrgInfoVO ooivo = new OrgOrgInfoVO();
		ooivo.setOrgCd(vo.getOrgCd());

		List<OrgMenuVO> orgMenuList = orgMenuMapper.list(parMenuVO);
		List<OrgMenuVO> newMenuList = new ArrayList<OrgMenuVO>();
		int lineCnt = 0;
		if("up".equals(moveType)) {
			//-- 메뉴 위로.
			for(OrgMenuVO somvo : orgMenuList) {
				//-- 메뉴 코드가 같으면 하나 위의 omdto를 가져오고. 작업해 보자
				if(somvo.getMenuCd().equals(vo.getMenuCd())) {
					OrgMenuVO ssomvo = newMenuList.get(lineCnt - 1); // 하나 위의 메뉴 받아오기
					newMenuList.remove(lineCnt - 1); //-- 하나위의 목록을 삭제
					newMenuList.add(somvo);
					newMenuList.add(ssomvo);
				} else {
					newMenuList.add(somvo);
				}
				lineCnt++;
			}
		} else if("down".equals(moveType)) {
			//-- 메뉴 아래로
			OrgMenuVO nomdto = null;

			for(OrgMenuVO somvo : orgMenuList) {
				if(somvo.getMenuCd().equals(vo.getMenuCd())) {
					nomdto = somvo;
				} else {
					newMenuList.add(somvo);
					if(ValidationUtils.isNotEmpty(nomdto)) {
						newMenuList.add(nomdto);
						nomdto = null;
					}
				}
			}
		}
		int order = 0;
		for(OrgMenuVO ddto : newMenuList) {
			order++;
			ddto.setMenuOdr(order);
			orgMenuMapper.update(ddto);
		}
		this.setMenuChanged(ooivo);	// DB의 변경 여부를 기록

		return 1;
	}
	
	/**
	 * 기관의 메뉴의 순서를 변경한다.
	 * @param OrgMenuVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int sortMenu(OrgMenuVO vo) throws Exception {
		
		String[] menuList = StringUtil.split(vo.getMenuCd(),"|");

		// 하위 코드 목록을 한꺼번에 조회
		OrgMenuVO omvo = new OrgMenuVO();
		omvo.setMenuType(vo.getMenuType());
		omvo.setOrgCd(vo.getOrgCd());
		
		OrgOrgInfoVO ooivo = new OrgOrgInfoVO();
		ooivo.setOrgCd(vo.getOrgCd());
		
		List<OrgMenuVO> menuArray = orgMenuMapper.list(omvo); 

		// 이중 포문으로 menuArray에 변경된 order를 다시 저장
		// 만약 파라매터로 코드키가 누락되는 경우가 있다면... 로직 수정 필요.
		for (OrgMenuVO iomvo : menuArray) {
			for (int order = 0; order < menuList.length; order++) {
				if(iomvo.getMenuCd().equals(menuList[order])) {
					iomvo.setMenuOdr(order+1);	// 1부터 차례로 순서값을 지정
					orgMenuMapper.update(iomvo);
				}
			}
		}
		// DB의 변경 여부를 기록
		this.setMenuChanged(ooivo);
		return 1;
		
	}
	
 	/**
 	 * 기관의 메뉴를 초기화 한다.
 	 * @param OrgMenuVO
 	 * @return int
 	 * @throws Exception
 	 */
	@Override
	public int initMenu(OrgMenuVO vo) throws Exception {
		OrgOrgInfoVO ooivo = new OrgOrgInfoVO();
		ooivo.setOrgCd(vo.getOrgCd());
		
		//-- 설정값 셋팅.
		OrgAuthGrpMenuVO oagmvo = new OrgAuthGrpMenuVO();
		oagmvo.setOrgCd(vo.getOrgCd());
		oagmvo.setMenuType(vo.getMenuType());
		oagmvo.setRegNo(vo.getRegNo());
		oagmvo.setModNo(vo.getModNo());

		OrgMenuLangVO omlvo = new OrgMenuLangVO();
		omlvo.setOrgCd(vo.getOrgCd());
		omlvo.setMenuType(vo.getMenuType());
		omlvo.setRegNo(vo.getRegNo());
		omlvo.setModNo(vo.getModNo());

		//--- 기존 정보 삭제.
		orgAuthGrpMenuMapper.deleteInit(oagmvo);
		orgMenuLangMapper.deleteInit(omlvo);

		//-- 게시판 연결정보 끊기
		BrdBbsInfoVO bbivo = new BrdBbsInfoVO();
		bbivo.setOrgCd(vo.getOrgCd());
		bbivo.setModNo(vo.getModNo());
		brdBbsInfoMapper.updateAllMenuCdToNull(bbivo);

		//-- 페이지 연결정보 끊기
		OrgPageVO opvo = new OrgPageVO();
		opvo.setOrgCd(vo.getOrgCd());
		opvo.setModNo(vo.getModNo());
		orgPageMapper.updateAllMenuCdToNull(opvo);

		//-- 단계별 메늏 삭제.. 3, 2, 1
		vo.setMenuLvl(3);
		orgMenuMapper.deleteByMenuLvl(vo);

		vo.setMenuLvl(2);
		orgMenuMapper.deleteByMenuLvl(vo);

		vo.setMenuLvl(1);
		orgMenuMapper.deleteByMenuLvl(vo);

		//-- 메뉴 초기화 등록
		int result = orgMenuMapper.insertInit(vo);
		orgMenuLangMapper.insertInit(omlvo);
		orgAuthGrpMenuMapper.insertInit(oagmvo);

		this.setMenuChanged(ooivo);	// DB의 변경 여부를 기록

		return result;
	}	
	
 	/**
 	 * 기관의 권한 메뉴를 등록 한다.
 	 * @param OrgAuthGrpMenuVO
 	 * @return int
 	 * @throws Exception
 	 */
	@Override
	public int addAuthGrpMenu(OrgAuthGrpMenuVO vo) throws Exception {
		OrgOrgInfoVO ooivo = new OrgOrgInfoVO();
		ooivo.setOrgCd(vo.getOrgCd());
		
		//---- authGroupMenu 삭제
		OrgAuthGrpMenuVO oagmvo = new OrgAuthGrpMenuVO();
		oagmvo.setOrgCd(vo.getOrgCd());
		oagmvo.setMenuType(vo.getMenuType());
		oagmvo.setAuthGrpCd(vo.getAuthGrpCd());
		orgAuthGrpMenuMapper.delete(oagmvo);

		String[] viewList = StringUtil.split(vo.getViewAuthArray(),"|");
		String[] creList = StringUtil.split(vo.getCreAuthArray(),"|");

		List<OrgAuthGrpMenuVO> authGroupMenuArray = new ArrayList<OrgAuthGrpMenuVO>();

		for(int i=0;i<viewList.length;i++) {
			makeAuthGroupMenu(vo.getOrgCd(), vo.getMenuType(), vo.getAuthGrpCd(), viewList[i], authGroupMenuArray, creList);
		}
		for(int i=0; i < authGroupMenuArray.size();i++) {
			OrgAuthGrpMenuVO ioagmvo = authGroupMenuArray.get(i);
			orgAuthGrpMenuMapper.merge(ioagmvo);
		}

		// DB의 변경 여부를 기록
		this.setMenuChanged(ooivo);

		// 성공처리를 표현하는 ProcessResultDTO<Object>를 반환.
		return 1;
	}
	
	private void makeAuthGroupMenu(String orgCd, String menuType, String authGrpCd, 
			String menuCd, List<OrgAuthGrpMenuVO> menuList, String[] creList) throws Exception {
		OrgMenuVO omvo = new OrgMenuVO();
		omvo.setOrgCd(orgCd);
		omvo.setMenuType(menuType);
		omvo.setMenuCd(menuCd);
		omvo = orgMenuMapper.select(omvo);

		if(!"".equals(StringUtil.nvl(omvo.getParMenuCd()))) {
			makeAuthGroupMenu(orgCd, menuType, authGrpCd, omvo.getParMenuCd(), menuList, creList);
		}

		OrgAuthGrpMenuVO ioagmvo = new OrgAuthGrpMenuVO();
		String creAuth = "N";
		for(int j=0;j<creList.length;j++){
			if(menuCd.equals(creList[j])) creAuth = "Y";
		}

		ioagmvo.setOrgCd(orgCd);
		ioagmvo.setMenuType(menuType);
		ioagmvo.setAuthGrpCd(authGrpCd);
		ioagmvo.setMenuCd(menuCd);
		ioagmvo.setViewAuth("Y");
		ioagmvo.setCreAuth(creAuth);
		ioagmvo.setModAuth(creAuth);
		ioagmvo.setDelAuth(creAuth);

		menuList.add(ioagmvo);
	}
	
 	/**
 	 * 기관 메뉴의 권한 정보 조회
 	 * @param OrgAuthGrpMenuVO
 	 * @return int
 	 * @throws Exception
 	 */	
	@Override
	public OrgMenuVO viewAuthorizeByMenu(OrgMenuVO vo) throws Exception {
		// 캐쉬 태우기 검토
		return orgMenuMapper.selectAuthorizeByMenu(vo);
	}	
	
	@Override
	public OrgMenuVO viewAuthorizeByMenu2(OrgMenuVO vo) throws Exception {
		// 캐쉬 태우기 검토
		return orgMenuMapper.selectAuthorizeByMenu2(vo);
	}	
	
	/**
	 * 메뉴의 버젼값을 DB와 비교한다.
	 * @return true:변경됨, false:변경되지 않음
	 */
	private boolean isMenuChanged(OrgOrgInfoVO vo) throws Exception {
		return (this.menuVersion != orgOrgInfoMapper.selectMenuVersion(vo)) ? true : false;
	}

	/**
	 * 메뉴가 변경되었음을 DB에 저장한다.
	 */
	private void setMenuChanged(OrgOrgInfoVO vo) throws Exception {
		orgOrgInfoMapper.updateMenuVersion(vo);
	}
}
