package com.hames.util.peer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

public class ModelUtil {
	
	private static List<String> successMessages = new ArrayList<String>();
	private static List<String> errorMessages = new ArrayList<String>();

	public static void addSuccess(String msg){
		successMessages.add(msg);
	}
	
	public static void addError(String msg){
		errorMessages.add(msg);
	}
	
	public static void addMessages(ModelAndView modelView) {
		if(errorMessages != null && !errorMessages.isEmpty()){
			modelView.getModel().put("errorMessages", errorMessages);
			return;
		}
		
		if(successMessages != null && !successMessages.isEmpty()){
			modelView.getModel().put("successMessages", successMessages);	
		}
		
	}
	
	public static void removeMessages(){
		successMessages.clear();
		errorMessages.clear();
	}
	
}
