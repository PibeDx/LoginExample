package com.josecuentas.loginexample.presenter;

import com.josecuentas.loginexample.io.model.LoginRaw;
import com.josecuentas.loginexample.model.entity.UserEntity;
import com.josecuentas.loginexample.model.interactor.LoginCallback;
import com.josecuentas.loginexample.model.interactor.LoginInteractor;
import com.josecuentas.loginexample.view.LoginView;

/**
 * Created by José Norberto Cuentas Turpo.
 * Email: <jcuentast@gmail.com> on 1/08/16.
 * GitHub: PibeDx
 */
public class LoginPresenter implements Presenter<LoginView>, LoginCallback {

	private LoginView mLoginView;
	private LoginInteractor mLoginInteractor;
	private String mUsername, mPassword;

	@Override public void attachedView(LoginView view) {
		if (view == null) throw new IllegalArgumentException("You can't set a null view");

		mLoginView = view;
		mLoginInteractor = new LoginInteractor(view.getContext());
	}

	@Override public void detachView() {
		mLoginView = null;
	}

	public void login(String username, String password){
		mUsername = username;
		mPassword = password;
		LoginRaw loginRaw = new LoginRaw();
		loginRaw.setLogin(mUsername);
		loginRaw.setPassword(mPassword);

		mLoginView.showLoading();
		mLoginInteractor.login(loginRaw, this);
	}

	@Override public void onResponse(UserEntity userEntity) {
		mLoginView.hideLoaging();
		mLoginView.loginSuccess(userEntity);

	}

	@Override public void onError(String message) {
		mLoginView.hideLoaging();
		mLoginView.showError(message);
	}

	@Override public void onNetworkConnectionError() {
		mLoginView.hideLoaging();
		mLoginView.showConnectionErrorMessage();
	}

	@Override public void onServerError(String message) {
		mLoginView.hideLoaging();
		mLoginView.showError(message);
	}
}
