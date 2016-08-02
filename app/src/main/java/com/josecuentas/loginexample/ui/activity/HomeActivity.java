package com.josecuentas.loginexample.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.josecuentas.loginexample.R;
import com.josecuentas.loginexample.model.entity.UserEntity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by José Norberto Cuentas Turpo.
 * Email: <jcuentast@gmail.com> on 1/08/16.
 * GitHub: PibeDx
 */
public class HomeActivity extends AppCompatActivity {

	@Bind(R.id.txv_username) TextView mTxvUsername;
	private UserEntity mUserEntity;

	@Override protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		injectView();
		bundles();
		populate();

	}

	private void injectView(){
		ButterKnife.bind(this);
	}

	private void bundles() {
		if(getIntent() != null) {
			Bundle bundle = getIntent().getExtras();
			if (bundle != null) {
				mUserEntity = (UserEntity) bundle.getParcelable(UserEntity.BUNDLE);
			}
		}
	}

	private void populate() {
		if (mUserEntity != null){
			String message = "Buen día " + mUserEntity.getName();
			mTxvUsername.setText(message);
		}
	}
}
