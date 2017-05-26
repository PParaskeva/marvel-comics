package com.example.panagiotis.marvelcomics.Contract;


import com.example.panagiotis.marvelcomics.MVP.BasePresenter;
import com.example.panagiotis.marvelcomics.MVP.BaseView;
import com.example.panagiotis.marvelcomics.Realm.ComicsRealm;
import com.example.panagiotis.marvelcomics.pojos.Example;

import io.realm.RealmResults;

public interface IContract {
    public interface IPresenter extends BasePresenter{
        void getComics();
        void filterComics(Double min, Double max);

    }
    public interface IView extends BaseView<IContract.IPresenter>{
        void showComics(Example example);
        void showFilterComics(RealmResults<ComicsRealm> results);
        void showProgressDialog();
        void dismissProgressDialog();
    }

    public interface IPresenter_Details extends BasePresenter{
        void getById(String id);
    }

    public interface IView_details extends BaseView<IContract.IPresenter_Details>{
        void showDetails(Example example);
        void showProgressDialog();
        void dismissProgressDialog();
    }

}
