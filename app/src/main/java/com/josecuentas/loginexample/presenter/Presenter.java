package com.josecuentas.loginexample.presenter;

/**
 * Created by Erik Jhordan Rey
 */
public interface Presenter<V>{

	void attachedView(V view);

	void detachView();
}