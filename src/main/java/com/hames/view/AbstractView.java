package com.hames.view;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.PropertyAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DefaultBindingErrorProcessor;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.hames.bean.UserContext;
import com.hames.system.functions.DatePropertyEditor;

@Controller
public abstract class AbstractView {

	public void activeMenu(Model model,String menuName){
		model.addAttribute("menu", menuName);
		System.out.println(UserContext.staff);
		model.addAttribute("staffUtil", UserContext.staff);
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd/MM/yyyy");
	    binder.registerCustomEditor(DateTime.class, new DatePropertyEditor(dateTimeFormatter));
	    
	    binder.setBindingErrorProcessor(new DefaultBindingErrorProcessor() {
	        @Override
	        public void processPropertyAccessException(PropertyAccessException ex, BindingResult bindingResult) {
	            String propertyName = ex.getPropertyName();
	            Object value = ex.getValue();
	            bindingResult.addError(new FieldError(bindingResult.getObjectName(), propertyName, value, true,
	            new String[] { "moderation.field.error" }, new Object[] { propertyName, value },
	            "Invalid value for " + propertyName + "(" + value + ")"));
	        }
	    });
	}
		 
}
