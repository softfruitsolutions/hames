package hames.core.view;

import hames.core.bean.ModelUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public abstract class AbstractView extends HandlerInterceptorAdapter {

	public abstract String getTitleDefinition(Model model);
	
	public void activeMenu(Model model,String menuName){
		model.addAttribute("menu", menuName);
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		ModelUtil.removeMessages();
		return super.preHandle(request, response, handler);
	}
	
}
