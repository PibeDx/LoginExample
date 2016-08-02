package com.josecuentas.loginexample.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by José Norberto Cuentas Turpo.
 * Email: <jcuentast@gmail.com> on 1/08/16.
 * GitHub: PibeDx
 */
public class UserEntity implements Parcelable {

	public static final String BUNDLE = "UserEntity";

	private String name;
	private String email;
	private String objectId;
	private String token;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}


	@Override public int describeContents() {
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.name);
		dest.writeString(this.email);
		dest.writeString(this.objectId);
		dest.writeString(this.token);
	}

	public UserEntity() {
	}

	protected UserEntity(Parcel in) {
		this.name = in.readString();
		this.email = in.readString();
		this.objectId = in.readString();
		this.token = in.readString();
	}

	public static final Parcelable.Creator<UserEntity> CREATOR = new Parcelable.Creator<UserEntity>() {
		@Override public UserEntity createFromParcel(Parcel source) {
			return new UserEntity(source);
		}

		@Override public UserEntity[] newArray(int size) {
			return new UserEntity[size];
		}
	};
}
