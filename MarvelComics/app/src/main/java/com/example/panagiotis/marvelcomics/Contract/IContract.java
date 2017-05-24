package com.example.panagiotis.marvelcomics.Contract;


import com.example.panagiotis.marvelcomics.MVP.BasePresenter;
import com.example.panagiotis.marvelcomics.MVP.BaseView;

public interface IContract {
    public interface IPresenter extends BasePresenter{
        void getComics();
        void filterComics();
    }
    public interface IView extends BaseView<IContract.IPresenter>{
        void showComics();
        void showFilterComics();
        void showProgressDialog();
        void dismissProgressDialog();
    }
}
