package hames.core.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;

public class ModelUtil {
	
	private static List<String> successMessages = new ArrayList<String>();
	private static List<String> errorMessages = new ArrayList<String>();

	public static void addSuccess(String msg){
		successMessages.add(msg);
	}
	
	public static void addError(String msg){
		errorMessages.add(msg);
	}
	
	public static void addMessages(Model model) {
		model.addAttribute("successMessages", successMessages);
		model.addAttribute("errorMessages", errorMessages);
	}
	
	public static void removeMessages(){
		successMessages.clear();
		errorMessages.clear();
	}
	
}
