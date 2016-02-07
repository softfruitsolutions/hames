package com.hames.util.peer;

import java.math.BigDecimal;

public class BigDecimalUtil {

	/**
	 * BigDecimal Calculation
	 */
	
	public static boolean isEqual(BigDecimal bigDecimal1,BigDecimal bigDecimal2){
		if(bigDecimal1 != null && bigDecimal2 != null){
			if(bigDecimal1.compareTo(bigDecimal2) == 0){
				return true;
			}
		}
		return false;
	}
	
	public static boolean isLessThan(BigDecimal bigDecimal1,BigDecimal bigDecimal2){
		if(bigDecimal1 != null && bigDecimal2 != null){
			if(bigDecimal1.compareTo(bigDecimal2) == -1){
				return true;
			}
		}
		return false;
	}
	
	public static boolean isGreaterThan(BigDecimal bigDecimal1,BigDecimal bigDecimal2){
		if(bigDecimal1 != null && bigDecimal2 != null){
			if(bigDecimal1.compareTo(bigDecimal2) == 1){
				return true;
			}	
		}
		return false;
	}
	
	
}
