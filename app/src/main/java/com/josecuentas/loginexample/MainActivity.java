package com.josecuentas.loginexample;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import com.josecuentas.loginexample.model.entity.UserEntity;
import com.josecuentas.loginexample.presenter.LoginPresenter;
import com.josecuentas.loginexample.ui.activity.BaseActivity;
import com.josecuentas.loginexample.ui.activity.HomeActivity;
import com.josecuentas.loginexample.view.LoginView;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements LoginView {

	public static final String TAG = "MainActivity";

	@Bind(R.id.view_container) RelativeLayout mViewContainer;
	@Bind(R.id.edt_username) EditText mEdtUsername;
	@Bind(R.id.edt_password) EditText mEdtPassword;
	@Bind(R.id.btn_login) Button mBtnLogin;

	private LoginPresenter mLoginPresenter;
	private ProgressDialog mProgressDialog;
	private String mUsername, mPassword;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		injectView();
		setSetup();
		setBtnEvents();
		mLoginPresenter = new LoginPresenter();
		mLoginPresenter.attachedView(this);

	}

	private void injectView() {
		ButterKnife.bind(this);
	}

	private void setSetup() {
		//equals style user and password
		mEdtUsername.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
		mEdtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
		mEdtPassword.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
	}

	private void setBtnEvents() {
		mBtnLogin.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View view) {
        setDataForm();
        hideKeyboard();
        mLoginPresenter.login(mUsername, mPassword);
			}
		});
	}

  private void setDataForm(){
    mUsername = mEdtUsername.getText().toString();
    mPassword = mEdtPassword.getText().toString();
  }

	@Override public void showLoading() {
		mProgressDialog = new ProgressDialog(getContext());
		mProgressDialog.setMessage(getResources().getString(R.string.message_loading));
		mProgressDialog.setCancelable(false);
		mProgressDialog.show();
	}

	@Override public void hideLoaging() {
		mProgressDialog.dismiss();
	}

	@Override public void showConnectionErrorMessage() {
		showMessage(mViewContainer, getResources().getString(R.string.error_connection));
	}

  @Override public void showServerError(String message) {
    showMessage(mViewContainer, message);
  }

  @Override public void showError(String message) {
		Log.v(TAG, "message " + message);

		showMessage(mViewContainer, message);
	}

	@Override public void loginSuccess(UserEntity userEntity) {
		Log.v(TAG, "loginSuccess");
		Bundle bundle = new Bundle();
		bundle.putParcelable(UserEntity.BUNDLE, userEntity);

    next(bundle, HomeActivity.class, true);
	}

	@Override public Context getContext() {
		return this;
	}

	private void hideKeyboard() {
		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(mViewContainer.getWindowToken(), 0);
	}


}
