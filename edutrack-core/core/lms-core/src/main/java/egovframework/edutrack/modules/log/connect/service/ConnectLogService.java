package egovframework.edutrack.modules.log.connect.service;

import java.io.OutputStream;

import egovframework.edutrack.comm.exception.ServiceProcessException;
import egovframework.edutrack.comm.service.ProcessResultListVO;
import egovframework.edutrack.comm.service.ProcessResultVO;

public interface ConnectLogService {

		/**
		 * 시스템 접속 목록 조회
		 * @param iQuizStareDTO
		 * @return
		 */
		public abstract ProcessResultListVO<ConnectLogVO> listLog(ConnectLogVO iConnectLogVO);

		/**
		 * 시스템 접속 목록 조회 (일자별)
		 * @param iQuizStareDTO
		 * @return
		 */
		public abstract ProcessResultListVO<ConnectLogVO> listLogByDay(ConnectLogVO iConnectLogVO);

		/**
		 * 시스템 접속 목록 조회 (주별)
		 * @param iQuizStareDTO
		 * @return
		 */
		public abstract ProcessResultListVO<ConnectLogVO> listLogByWeek(ConnectLogVO iConnectLogVO);


		/**
		 * 시스템 접속 목록 조회 (월별)
		 * @param iQuizStareDTO
		 * @return
		 */
		public abstract ProcessResultListVO<ConnectLogVO> listLogByMonth(ConnectLogVO iConnectLogVO);


		/**
		 * 접속 로그 저장
		 * @param iPrintLogDTO
		 * @return
		 */
		public abstract ProcessResultVO<ConnectLogVO> addConnectLog(ConnectLogVO dto);

		/**
		 * 날짝 가져오기
		 * @param iPrintLogDTO
		 * @return
		 */
		public abstract ProcessResultVO<ConnectLogVO> viewAutoDate(ConnectLogVO iConnectLogVO);

		/**
		 * 홈페이지 접속 통계 엑셀다운로드
		 *
		 * @return  ProcessResultVO
		 */
		public void listExcelDownload(ConnectLogVO ConnectLogVO, OutputStream os) throws ServiceProcessException;

	}

