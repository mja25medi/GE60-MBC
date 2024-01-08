package egovframework.edutrack.modules.user.info.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Repository;

import egovframework.edutrack.comm.util.web.ValidationUtils;
import egovframework.edutrack.modules.student.student.service.StudentVO;
import egovframework.edutrack.modules.user.info.service.UsrUserInfoVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 *  <b>사용자 - 사용자 회원 정보 관리</b> 영역  DAO 클래스
 * @author Jamfam
 *
 */
@Mapper("usrUserInfoMapper")
public interface UsrUserInfoMapper{

    /**
	 * 사용자 회원 정보의 전체 목록을 조회한다. 
	 * @param  UsrUserInfoVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<UsrUserInfoVO> list(UsrUserInfoVO vo) throws Exception;
	
    /**
	 * 사용자 회원 정보의 검색된 수를 카운트 한다. 
	 * @param  UsrUserInfoVO 
	 * @return int
	 * @throws Exception
	 */
	public int count(UsrUserInfoVO vo) throws Exception;
	
	
	/**
	 * 사용자 상담 정보의 검색된 수를 카운트 한다. 
	 * @param  UsrUserInfoVO 
	 * @return int
	 * @throws Exception
	 */
	public int countConsult(UsrUserInfoVO vo) throws Exception;
	
	
    /**
	 * 사용자 회원 정보의 페이징 목록을 조회한다. 
	 * @param  UsrUserInfoVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<UsrUserInfoVO> listPageing(UsrUserInfoVO vo) throws Exception ;
	
	
	/**
	 * 사용자 상담 목록의 페이징 목록을 조회한다. 
	 * @param  UsrUserInfoVO 
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<UsrUserInfoVO> listConsultPaging(UsrUserInfoVO vo) throws Exception ;
	
	
    /**
	 * 사용자 회원 정보의 상세 정보를 조회한다.
	 * @param  UsrUserInfoVO 
	 * @return UsrUserInfoVO
	 * @throws Exception
	 */
	public UsrUserInfoVO select(UsrUserInfoVO vo) throws Exception;
	
    /**
	 * 사용자 회원 정보의 로그인용 상세 정보를 조회한다.
	 * @param  UsrUserInfoVO 
	 * @return UsrUserInfoVO
	 * @throws Exception
	 */
	public UsrUserInfoVO selectForLogin(UsrUserInfoVO vo) throws Exception;
	
	/**
	 * 사용자 회원 정보의 로그인용 상세 정보를 조회한다.
	 * @param  UsrUserInfoVO 
	 * @return UsrUserInfoVO
	 * @throws Exception
	 */
	public UsrUserInfoVO selectForSsoLogin(UsrUserInfoVO vo) throws Exception;

    /**
	 * 사용자의 키 정보를 조회한다.
	 * @param   
	 * @return String
	 * @throws Exception
	 */
	public String selectKey() throws Exception;
	
    /**
	 * 상담 정보를 조회한다.
	 * @param   
	 * @return String
	 * @throws Exception
	 */
	public String selectConsultKey() throws Exception;
	
    /**
     * 사용자 회원 정보의 상세 정보를 등록한다.  
     * @param  UsrUserInfoVO
     * @return String
     * @throws Exception
     */
    public int insert(UsrUserInfoVO vo) throws Exception;
    
    /**
     * 사용자 회원 정보의 상세 정보를 등록한다.  
     * @param  UsrUserInfoVO
     * @return String
     * @throws Exception
     */
    public String ssoinsert(UsrUserInfoVO vo) throws Exception;
    
    /**
     * 상담 정보를 등록한다.  
     * @param  UsrUserInfoVO
     * @return String
     * @throws Exception
     */
    public String insertConsult(UsrUserInfoVO vo) throws Exception;
    
    
    /**
     * 사용자 회원 정보의 상세 정보를 수정한다. 
     * @param  UsrUserInfoVO
     * @return int
     * @throws Exception
     */
    public int update(UsrUserInfoVO vo) throws Exception ;
    
    /**
     * 사용자 회원 정보의 아바타 정보를 수정한다. 
     * @param  UsrUserInfoVO
     * @return int
     * @throws Exception
     */
    public int updateAvatar(UsrUserInfoVO vo) throws Exception ;
    
    /**
     * 사용자 회원 정보의 상세 정보를 삭제한다.  
     * @param  UsrUserInfoVO
     * @return int
     * @throws Exception
     */
    public int delete(UsrUserInfoVO vo) throws Exception;
    
    /**
	 * 이메일과 이름을 이용하여 사용자 정보를 검색한다.
	 * @param  UsrUserInfoVO 
	 * @return UsrUserInfoVO
	 * @throws Exception
	 */
	public UsrUserInfoVO selectSearchId(UsrUserInfoVO vo) throws Exception;
	
	
	/**
	 *  사용자 패스워드를 검색한다.
	 * @param  UsrUserInfoVO 
	 * @return UsrUserInfoVO
	 * @throws Exception
	 */
	public UsrUserInfoVO selectSearchPass(UsrUserInfoVO vo) throws Exception;
    /**
	 * 사용자의 중복키를 이용하여 중복 여부를 조회한다.
	 * @param  UsrUserInfoVO 
	 * @return int
	 * @throws Exception
	 */
	public int selectDuplicate(UsrUserInfoVO vo) throws Exception;
	
    /**
     * 사용자의 회원 탈퇴 처리를 한다. 
     * @param  UsrUserInfoVO
     * @return int
     * @throws Exception
     */
    public int updateWithdrawal(UsrUserInfoVO vo) throws Exception;
    
    /**
	 * SNS 회원 정보의 상세 정보를 조회한다.
	 * @param  UsrUserInfoVO 
	 * @return UsrUserInfoVO
	 * @throws Exception
	 */
	public UsrUserInfoVO selectUserSns(UsrUserInfoVO vo) throws Exception;
	
	/**
	 * 사용자 회원 정보의 로그인용 상세 정보를 조회한다.
	 * @param  UsrUserInfoVO 
	 * @return UsrUserInfoVO
	 * @throws Exception
	 */
	public UsrUserInfoVO selectForLoginCheck(UsrUserInfoVO vo) throws Exception;

	public Object selectForKribbEncPass(UsrUserInfoVO infoVO) throws Exception;
	
    /**
     * 사용자 아이디로 사용자 정보를 가져온다.
     *
     * @return ProcessResultDTO
     */
	public UsrUserInfoVO selectByUserId(UsrUserInfoVO infoVO) throws Exception;

	/**
	 * [HRD] 사용중인 상태의 회원인지 조회
	 * @param vo
	 * @return
	 */
	public UsrUserInfoVO selectUserCheckByUserIdOrgCd(UsrUserInfoVO vo);
	
	/**
	 * 중복체크를 위해 이메일로 사용자 정보 확인
	 * @param  UsrUserInfoVO 
	 * @return String
	 * @throws Exception
	 */
	public String selectEmailCheck(UsrUserInfoVO infoVO); 
	
	/**
	 * api용 회원정보 조회
	 * @param  UsrUserInfoVO 
	 * @return List<EgovMap>
	 * @throws Exception
	 */
	public List<EgovMap> selectUserInfoApi(UsrUserInfoVO infoVO) throws Exception; 
	
	public int updateNiceCheckInfo(UsrUserInfoVO vo);

	/**
	 * 소셜 회원가입 체크
	 */
	public int oauthCheckId(Map<String, Object> paramMap) throws Exception;

	/**
	 * 소셜 로그인
	 */
	public UsrUserInfoVO oauthLogin(Map<String, Object> paramMap) throws Exception;
	
	/**
	 * 강사 IDE 수정
	 *
	 * @reurn ProcessResultVO
	 */
	public abstract int updateTeacherIde(UsrUserInfoVO infoVO) throws Exception;
	
	/**
	 * 아이디로 강사 번호 조회
	 * @param request
	 *
	 * @return  ProcessResultVO
	 */
	public abstract UsrUserInfoVO selectUserNo(UsrUserInfoVO iStudentVO);
	
	public int addTeacherIdeUrl(UsrUserInfoVO vo);
	
}
