package org.beesden.ratings.controller.rest;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.beesden.ratings.model.MessageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestErrorHandler {

	@Autowired
	private MessageSource messageSource;

	/**
	 * Convert a list of Spring field errors into an application error list
	 *
	 * @param bindingResult
	 * @return
	 */
	private MessageList getBindingMessages(List<FieldError> fieldErrors) {
		MessageList messageList = new MessageList();
		for (FieldError fieldError : fieldErrors) {
			String defaultMessage = resolveMessage(fieldError);
			String key = "bees.error." + fieldError.getField() + "." + fieldError.getCode();
			Object[] arguments = Arrays.copyOfRange(fieldError.getArguments(), 1, fieldError.getArguments().length);
			messageList.addMessage(defaultMessage, key.toLowerCase(), arguments);
		}
		return messageList;
	}

	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public MessageList processValidationError(BindException ex) {
		return getBindingMessages(ex.getFieldErrors());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public MessageList processValidationError(MethodArgumentNotValidException ex) {
		return getBindingMessages(ex.getBindingResult().getFieldErrors());
	}

	private String resolveMessage(FieldError fieldError) {
		Locale currentLocale = LocaleContextHolder.getLocale();
		String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);
		return localizedErrorMessage;
	}
}