package hames.core.system;

import hames.core.bean.ModelUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Controller
public class HamesInterceptorAdapter extends HandlerInterceptorAdapter{

	private static final Logger logger = LoggerFactory.getLogger(HamesInterceptorAdapter.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		/**
		 * Clearing messages in ModelView
		 */
		ModelUtil.removeMessages();
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,
							ModelAndView modelAndView) throws Exception {
	
		/**
		 * Adding messages to ModelView
		 */
		ModelUtil.addMessages(modelAndView);
		
		super.postHandle(request, response, handler, modelAndView);
	}
}
