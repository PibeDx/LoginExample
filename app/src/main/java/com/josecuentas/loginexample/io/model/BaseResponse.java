package com.josecuentas.loginexample.io.model;

/**
 * Created by José Norberto Cuentas Turpo.
 * Email: <jcuentast@gmail.com> on 1/08/16.
 * GitHub: PibeDx
 */

	//Rest Documentation
	//https://backendless.com/documentation/users/rest/users_login.htm
public class BaseResponse {

	private static final int SUCCESS = 0;
	private int code;
	private String message;

	public boolean isSuccess() {
		if(code == SUCCESS) return true;
		return false;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
