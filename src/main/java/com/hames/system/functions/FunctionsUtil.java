package com.hames.system.functions;

import java.util.List;

public class FunctionsUtil {

	/**
	 * Testing if list contains the specific object
	 * @param list
	 * @param obj
	 * @return
	 */
	public static boolean contains(List list, Object obj){
		if(list.contains(obj)){
			return true;
		}
		
		return false;
	}
}
