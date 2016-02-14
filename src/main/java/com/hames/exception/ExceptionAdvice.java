package com.hames.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hames.util.enums.ErrorCode;
import com.hames.util.model.ErrorNode;
import com.hames.util.model.JsonResponse;
import com.hames.view.RolePermissionController;

@ControllerAdvice
public class ExceptionAdvice {
	
	private static final Logger logger = LoggerFactory.getLogger(RolePermissionController.class);

	/**
	 * Handling Validation Exception
	 * @param e
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(value=HttpStatus.UNPROCESSABLE_ENTITY)
	private JsonResponse handleValidationException(Exception e){
		logger.debug("Validation exception are presents while processing entity");
		JsonResponse response = new JsonResponse();
		response.setStatus(Boolean.FALSE);
		response.setMessage(new ErrorNode(ErrorCode.VALIDATION_ERROR,HttpStatus.UNPROCESSABLE_ENTITY.toString(),e.getMessage()));
		return response;
	}
	
	@ResponseBody
	@ExceptionHandler(AuthorizationException.class)
	@ResponseStatus(value=HttpStatus.UNAUTHORIZED)
	private JsonResponse handleAuthenticationException(Exception e){
		logger.debug("User isn't permitted to execute this operation.");
		JsonResponse response = new JsonResponse();
		response.setStatus(Boolean.FALSE);
		response.setMessage(new ErrorNode(ErrorCode.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED.toString(),ErrorNode.UNAUTHORIZED_ACCESS_MESSAGE));
		return response;
	}
	
}
