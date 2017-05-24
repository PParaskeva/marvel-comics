package com.example.panagiotis.marvelcomics.Contract;


public class Presenter implements IContract.IPresenter {
    IContract.IView iView;

    public Presenter(IContract.IView iView) {
        this.iView = iView;
    }

    @Override
    public void getComics() {

    }

    @Override
    public void filterComics() {

    }

    @Override
    public void start() {
        iView.setPresenter(this);
    }
}
