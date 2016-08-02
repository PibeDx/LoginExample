package com.josecuentas.loginexample.io.adapter;

import com.josecuentas.loginexample.BuildConfig;
import com.josecuentas.loginexample.io.model.LoginRaw;
import com.josecuentas.loginexample.io.model.LoginResponse;
import com.josecuentas.loginexample.utils.Contants;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;

/**
 * Created by José Norberto Cuentas Turpo.
 * Email: <jcuentast@gmail.com> on 1/08/16.
 * GitHub: PibeDx
 */
public class LoginAdapter {

	private static final String TAG = "LoginAdapter";

	private static LoginService apiService;

	public static LoginService getApiService() {
		if (apiService == null) {
			RestAdapter adapter = new RestAdapter.Builder()
					.setEndpoint(BuildConfig.HOST)
					.setClient(new OkClient(getClient()))
					.setLogLevel(RestAdapter.LogLevel.FULL)
					.build();

			apiService = adapter.create(LoginService.class);
		}
		return apiService;
	}


	private static OkHttpClient getClient() {
		OkHttpClient client = new OkHttpClient();
		client.setConnectTimeout(2, TimeUnit.MINUTES);
		client.setReadTimeout(2, TimeUnit.MINUTES);
		return client;
	}

	public interface LoginService {

		@Headers({
				"application-id: " + Contants.APPLICATION_KEY,
				"secret-key: " + Contants.REST_API_KEY,
				"Content-Type: application/json",
				"application-type: REST"
		})

		@POST("/v1/users/login") void login(@Body LoginRaw raw, Callback<LoginResponse> callback);
	}

}
