package hames.core.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;

public class ModelUtil {
	
	private static List<String> successMessages;

	public static void addSuccess(String msg,Model model){
		successMessages = new ArrayList<String>();
		successMessages.add(msg);
	}
	
	public static void addMessages(Model model) {
		model.addAttribute("successMessages", successMessages);
	}
	
	public static void removeMessages(){
		successMessages = new ArrayList<String>();
	}
	
}
