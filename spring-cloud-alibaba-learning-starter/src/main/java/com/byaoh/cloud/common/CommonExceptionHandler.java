package com.byaoh.cloud.common;

import cn.hutool.core.date.DateUtil;
import com.byaoh.cloud.framework.exception.ApiException;
import com.byaoh.cloud.framework.web.Result;
import com.byaoh.cloud.framework.web.ResultCode;
import com.byaoh.cloud.framework.web.ResultFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 全局异常处理器
 *
 * @author ruoyi
 */
@RestControllerAdvice
public class CommonExceptionHandler {
	private static final Logger log = LoggerFactory.getLogger(CommonExceptionHandler.class);

	/**
	 * 请求方式不支持
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public Result<Void> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
															HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		log.error("请求地址'{}',不支持'{}'请求", requestURI, e.getMethod());
		return ResultFactory.failed(e.getMessage());
	}

	/**
	 * 业务异常
	 */
	@ExceptionHandler(ApiException.class)
	public Result<Void> handleServiceException(ApiException e) {
		return ResultFactory.build(e.getCode(), e.getMessage());
	}

	/**
	 * 拦截未知的运行时异常
	 */
	@ExceptionHandler(RuntimeException.class)
	public Result<Void> handleRuntimeException(RuntimeException e, HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		log.error("请求地址'{}',发生未知异常.", requestURI, e);
		return ResultFactory.failed(e.getMessage());
	}

	/**
	 * 系统异常
	 */
	@ExceptionHandler(Exception.class)
	public Result<Void> handleException(Exception e, HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		log.error("请求地址'{}',发生系统异常.", requestURI, e);
		return ResultFactory.failed(e.getMessage());
	}

	/**
	 * get请求 参数校验绑定错误
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(BindException.class)
	public Result<Void> validationGetMessage(BindException e) {
		return ResultFactory.failed(errorMessage(e.getBindingResult()));
	}

	/**
	 * get请求实体Date类型转换
	 *
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				this.setValue(DateUtil.parse(text));
			}
		});
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public Result<Void> validationArrayMessage(ConstraintViolationException e) {
		Set<String> messages = e.getConstraintViolations().stream().map(ConstraintViolation::getMessageTemplate).collect(Collectors.toSet());
		return ResultFactory.failed(String.join(",", messages));
	}

	private String errorMessage(BindingResult bindingResult) {
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		return fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public Result<Void> handleValidationException(DataIntegrityViolationException e) {
		log.error("违反唯一性约束", e);
		return ResultFactory.build(ResultCode.DATA_INTEGRITY_VIOLATION);
	}
}
