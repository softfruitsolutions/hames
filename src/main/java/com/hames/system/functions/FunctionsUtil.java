package com.hames.system.functions;

import java.util.Set;

public class FunctionsUtil {

	/**
	 * Testing if list contains the specific object
	 * @param list
	 * @param obj
	 * @return
	 */
	public static boolean contains(Set set, Object obj){
		if(set.contains(obj)){
			return true;
		}
		
		return false;
	}
}
