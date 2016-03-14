package com.hames.util.peer;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class ClassUtil {
	
	/**
	 * Get all fields of class<?> type. Includes superclass
	 * @param fields
	 * @param type
	 * @return
	 */
	public static List<Field> getAllFields(List<Field> fields, Class<?> type){
		fields.addAll(Arrays.asList(type.getDeclaredFields()));
		
		if(type.getSuperclass() != null){
			fields.addAll(getAllFields(fields, type.getSuperclass()));
		}
		
		return fields;
	}

}
