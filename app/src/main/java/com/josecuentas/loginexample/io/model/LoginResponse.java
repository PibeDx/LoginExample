package com.josecuentas.loginexample.io.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by José Norberto Cuentas Turpo.
 * Email: <jcuentast@gmail.com> on 1/08/16.
 * GitHub: PibeDx
 */

	//Rest Documentation
	//https://backendless.com/documentation/users/rest/users_login.htm
public class LoginResponse extends BaseResponse{

	private String name;
	@SerializedName("___class") private String type;
	@SerializedName("user-token") private String token;
	private String email;
	private String objectId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
}
