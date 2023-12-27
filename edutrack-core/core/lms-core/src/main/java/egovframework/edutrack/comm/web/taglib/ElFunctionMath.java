package egovframework.edutrack.comm.web.taglib;

public class ElFunctionMath {

	public static double round(double value, int digit) {
		try {
			return Double.parseDouble(String.format("%." + Integer.toString(digit) + "f",value));
		} catch (NumberFormatException ex) {
			return -1;
		}
	}
	
}
