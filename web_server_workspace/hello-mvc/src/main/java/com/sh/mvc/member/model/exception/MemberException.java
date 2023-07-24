package com.sh.mvc.member.model.exception;

public class MemberException extends RuntimeException {

	public MemberException() {
		super();
	}

	public MemberException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MemberException(String message, Throwable cause) {
		super(message, cause);
	}

	public MemberException(String message) {
		super(message);
	}

	public MemberException(Throwable cause) {
						// 모든 예외의 조상이 Throwable
		super(cause);
	}
	
}
