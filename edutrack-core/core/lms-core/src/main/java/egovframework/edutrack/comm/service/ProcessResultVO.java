package egovframework.edutrack.comm.service;

public class ProcessResultVO<T> extends AbstractResult {

	public final static int RESULT_SUCC = 1;
	public final static int RESULT_FAIL = -1;
	
	private int[] retrunCnt;
	private T returnVO;
	
	public ProcessResultVO() {
		super();
	}
	
	public ProcessResultVO(int returnResult) {
		super(returnResult);
	}

	public ProcessResultVO(int returnResult, String message) {
		super(returnResult, message);
	}

	public ProcessResultVO(T returnVO) {
		super();
		this.returnVO = returnVO;
	}
	
	public ProcessResultVO(T object, int returnResult) {
		super(returnResult);
		this.returnVO = object;
	}


	public T getReturnVO() {
		return returnVO;
	}
	
	/**
	 * 성공 코드를 설정하고 자신을 반환한다.
	 * @return
	 */
	public ProcessResultVO<T> setResultSuccess() {
		super.setResult(RESULT_SUCC);
		return this;
	}

	/**
	 * 실패 코드를 설정하고 자신을 반환한다.
	 * @return
	 */
	public ProcessResultVO<T> setResultFailed() {
		super.setResult(RESULT_FAIL);
		return this;
	}

	/**
	 * DTO를 설정하고 this를 반환한다.
	 * @param returnVO
	 * @return
	 */
	public ProcessResultVO<T> setReturnVO(T returnVO) {
		this.returnVO = returnVO;
		return this;
	}
	
	/**
	 * 처리결과가 <b>성공</b>인 PResultVO<T>를 생성해서 반환.
	 * resultVO는 null이다.
	 * @param <T>
	 * @return
	 */
	public static <T> ProcessResultVO<T> success() {
		return new ProcessResultVO<T>(RESULT_SUCC);
	}

	/**
	 * 처리결과가 <b>성공</b>인 PResultVO<T>를 메시지를 같이 포함 생성해서 반환.
	 * resultVO는 null이다.
	 * @param <T>
	 * @return
	 */
	public static <T> ProcessResultVO<T> success(String message) {
		return new ProcessResultVO<T>(RESULT_SUCC, message);
	}

	/**
	 * 처리결과가 <b>실패</b>인 PResultVO<T>를 생성해서 반환.
	 * resultVO는 null이다.
	 * @param <T>
	 * @return
	 */
	public static <T> ProcessResultVO<T> failed() {
		return new ProcessResultVO<T>(RESULT_FAIL);
	}

	/**
	 * 처리결과가 <b>실패</b>인 PResultVO<T>를 메시지를 같이 포함 생성해서 반환.
	 * resultVO는 null이다.
	 * @param <T>
	 * @return
	 */
	public static <T> ProcessResultVO<T> failed(final String message) {
		return new ProcessResultVO<T>() {{
			setResult(RESULT_FAIL);
			setMessage(message);
		}};
	}
	
	public int[] getRetrunCnt() {
		return retrunCnt;
	}

	public void setRetrunCnt(int[] retrunCnt) {
		this.retrunCnt = retrunCnt;
	}
	
}
