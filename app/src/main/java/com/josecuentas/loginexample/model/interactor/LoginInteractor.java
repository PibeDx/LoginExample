package com.josecuentas.loginexample.model.interactor;

import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.josecuentas.loginexample.R;
import com.josecuentas.loginexample.io.adapter.LoginAdapter;
import com.josecuentas.loginexample.io.model.BaseResponse;
import com.josecuentas.loginexample.io.model.LoginRaw;
import com.josecuentas.loginexample.io.model.LoginResponse;
import com.josecuentas.loginexample.model.entity.UserEntity;
import com.josecuentas.loginexample.utils.Contants;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

/**
 * Created by José Norberto Cuentas Turpo.
 * Email: <jcuentast@gmail.com> on 1/08/16.
 * GitHub: PibeDx
 */
public class LoginInteractor {

	public static final String TAG = "LoginInteractor";
	private Context mContext;

	public LoginInteractor(Context context) {
		mContext = context;
	}

	public void login(LoginRaw loginRaw, final LoginCallback callback){
		if (validateForm(loginRaw, callback)){
			if (isInternetConnection()){
				LoginAdapter.getApiService().login(loginRaw, new Callback<LoginResponse>() {
					@Override public void success(LoginResponse loginResponse, Response response) {
						onSuccess(loginResponse, response, callback);
					}

					@Override public void failure(RetrofitError error) {
						String json = null;
						try {
							json = new String(((TypedByteArray)error.getResponse().getBody()).getBytes());
						}catch (NullPointerException ex) {}
						Log.v(TAG, "json " + json);
						if (json != null){
							onFailure(error, json, callback);
						} else {
							onFailure(error, callback);
						}
					}
				});
			} else {
				callback.onNetworkConnectionError();
			}

		}
	}

	private boolean validateForm(LoginRaw loginRaw, LoginCallback callback) {
		String username, password;
		username = loginRaw.getLogin();
		password = loginRaw.getPassword();

		if (TextUtils.isEmpty(username)) {
			callback.onError(getResource().getString(R.string.error_username_isempty));
			return false;
		}
		if (TextUtils.isEmpty(password)) {
			callback.onError(getResource().getString(R.string.error_password_isempty));
			return false;
		}

		return true;
	}

	private void onSuccess(LoginResponse loginResponse, Response response, final LoginCallback  callback){
		if (loginResponse.isSuccess()) {
			UserEntity userEntity = new UserEntity();
			userEntity.setEmail(loginResponse.getEmail());
			userEntity.setName(loginResponse.getName());
			userEntity.setObjectId(loginResponse.getObjectId());
			userEntity.setToken(loginResponse.getToken());
			callback.onResponse(userEntity);
		} else {
			callback.onError(loginResponse.getMessage());
		}

	}

	private void onFailure(RetrofitError retrofitError, LoginCallback callback){
		callback.onError(retrofitError.getMessage());
	}

	private void onFailure(RetrofitError retrofitError, String json, final LoginCallback callback){
		Gson gson = new GsonBuilder().create();
		BaseResponse baseResponse = gson.fromJson(json, BaseResponse.class);
		if (baseResponse != null) {
			String message = baseResponse.getMessage();
			callback.onError(message);
		}else {
			callback.onServerError(getResource().getString(R.string.error_server));
		}
	}


	//http://stackoverflow.com/a/4656606
	private boolean isInternetConnection() {
		ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm.getActiveNetworkInfo() != null
				&& cm.getActiveNetworkInfo().isAvailable()
				&& cm.getActiveNetworkInfo().isConnected()) {
			return true;
		} else {
			Log.v(TAG, "Internet Connection Not Present");
			return false;
		}
	}

	private Resources getResource(){
		return mContext.getResources();
	}

}
