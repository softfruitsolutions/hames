package com.hames.util.peer;

import java.util.Set;

/**
 * Util to support JSP related functions. 
 * Any changes in this must reflect the tld file also
 * 
 * @author afilansari
 *
 */
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
