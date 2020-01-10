package com.mingdi101.annotations.advisor;

import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mingdi101.annotations.exception.NoBookFoundException;

@ControllerAdvice(annotations = RestController.class)
public class ApiResultHandler implements ResponseBodyAdvice<Object> {

	private ThreadLocal<ObjectMapper> mapperThreadLocal = ThreadLocal.withInitial(ObjectMapper::new);

	private static final Class[] annos = { RequestMapping.class, GetMapping.class, PostMapping.class,
			DeleteMapping.class, PutMapping.class };

	/**
	 * 对所有RestController的接口方法进行拦截
	 */
	@Override
	public boolean supports(MethodParameter returnType, Class converterType) {
		AnnotatedElement element = returnType.getAnnotatedElement();
		return Arrays.stream(annos).anyMatch(anno -> anno.isAnnotation() && element.isAnnotationPresent(anno));
	}

	@Override
	public Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType,
			Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		Object out;
		ObjectMapper mapper = mapperThreadLocal.get();
		response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
		if (body instanceof ApiResult) {
			out = body;
		} else if (body instanceof ErrorCode) {
			ErrorCode errorCode = (ErrorCode) body;
			out = new ApiResult(errorCode.getCode(), errorCode.getMsg(), null);
		} else if (body instanceof String) {
			ApiResult result = ApiResult.valueOf((String) body);
			try {
				// 因为是String类型，我们要返回Json字符串，否则SpringBoot框架会转换出错
				out = mapper.writeValueAsString(result);
			} catch (JsonProcessingException e) {
				out = ApiResult.errorOf(ErrorCode.JSON_PARSE_ERROR, e.getMessage());
			}
		} else {
			try {
				out = ApiResult.valueOf(mapper.writeValueAsString(body));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				out = ApiResult.errorOf(ErrorCode.JSON_PARSE_ERROR,"");
				e.printStackTrace();
			}
		}
		return out;
	}

	/**
	 * 全局异常捕捉处理
	 * 
	 * @param ex
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(value = NoBookFoundException.class)
	public ApiResult errorHandler(Exception ex) {
		return ApiResult.errorOf(ErrorCode.PARAMS_ERROR, ex.getMessage());
	}
	@InitBinder("book")
	public void setBookBinder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("book.");
	}
	@InitBinder("author")
	public void setAuthorBinder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("author.");
	}
}