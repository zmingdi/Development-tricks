package com.mingdi101.annotations.advisor;

public class ApiResult {
    public ApiResult(int code2, String msg, Object object) {
    	this.setCode(code2);
    	this.setMessage(msg);
    	this.value = object;
	}
	/**
     * 错误码，对应{@link ErrorCode}，表示一种错误类型
     * 如果是成功，则code为200
     */
    private int code;
    /**
     * 对错误的具体解释
     */
    private String message;
    /**
     * 返回的结果包装在value中，value可以是单个对象
     */
    private final Object value;
	public static ApiResult valueOf(Object body) {
		ApiResult result = new ApiResult(ErrorCode.SUCCESS.getCode(),ErrorCode.SUCCESS.getMsg(),body);
		return result;
	}
	public static ApiResult errorOf(ErrorCode jsonParseError, String message2) {
		ApiResult result = new ApiResult(jsonParseError.getCode(), jsonParseError.getMsg(),message2);
		return result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getValue() {
		return value;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
}