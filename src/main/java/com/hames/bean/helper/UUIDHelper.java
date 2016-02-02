package com.hames.bean.helper;

import java.util.UUID;

public class UUIDHelper {

	/**
	 * Get UUID
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString();
	}
}
