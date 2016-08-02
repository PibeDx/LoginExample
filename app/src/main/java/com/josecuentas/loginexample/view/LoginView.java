package com.josecuentas.loginexample.view;

import com.josecuentas.loginexample.model.entity.UserEntity;

/**
 * Created by José Norberto Cuentas Turpo.
 * Email: <jcuentast@gmail.com> on 1/08/16.
 * GitHub: PibeDx
 */
public interface LoginView extends MvpView{
	void showLoading();
	void hideLoaging();
	void showConnectionErrorMessage();
	void showServerError(String message);
	void showError(String message);
	void loginSuccess(UserEntity userEntity);
}
