package com.josecuentas.loginexample.model.interactor;

import com.josecuentas.loginexample.model.entity.UserEntity;

/**
 * Created by José Norberto Cuentas Turpo.
 * Email: <jcuentast@gmail.com> on 1/08/16.
 * GitHub: PibeDx
 */
public interface LoginCallback {

	void onResponse(UserEntity userEntity);
	void onError(String message);
	void onNetworkConnectionError();
	void onServerError(String message);

}
