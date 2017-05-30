package com.example.panagiotis.marvelcomics.Contract;


import com.example.panagiotis.marvelcomics.Constants;
import com.example.panagiotis.marvelcomics.Realm.ComicsRealm;
import com.example.panagiotis.marvelcomics.Services.Connection;
import com.example.panagiotis.marvelcomics.Services.IData;
import com.example.panagiotis.marvelcomics.pojos.Example;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmResults;

public class Presenter implements IContract.IPresenter {
    IContract.IView iView;
    IData connection;
    private Realm realm;

    public Presenter(IContract.IView iView) {
        this.iView = iView;
    }


    @Override
    public void getComics() {
        iView.showProgressDialog();
        connection=Connection.getConnection();
        connection.get_100_comics(100,getHash(),Constants.apikey,getUnixTimeStamp()
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Example>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final Example example) {
                        realm = Realm.getDefaultInstance();
                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                realm.delete(ComicsRealm.class);
                            }
                        });

                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                for(int i=0;i<example.getData().getResults().size();i++) {
                                    ComicsRealm comicsRealm = realm.createObject(ComicsRealm.class);
                                    comicsRealm.setName(example.getData().getResults().get(i).getTitle());
                                    comicsRealm.setPrice(example.getData().getResults().get(i).getPrices().get(0).getPrice());
                                    comicsRealm.setId(example.getData().getResults().get(i).getId());
                                }
                            }
                        });

                        iView.showComics(example);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.dismissProgressDialog();
                    }

                    @Override
                    public void onComplete() {
                        iView.dismissProgressDialog();
                    }
                });
    }

    @Override
    public void filterComics(final Double min, final Double max) {
        realm = Realm.getDefaultInstance();
        RealmResults<ComicsRealm> results = realm.where(ComicsRealm.class).
                greaterThanOrEqualTo("price",min).
                lessThanOrEqualTo("price",max).
                findAll();
        iView.showFilterComics(results);
    }

    @Override
    public void start() {
        iView.setPresenter(this);
    }

    private  String getHash() {
        String marvelHash = "";

        try {

            String timeStamp    = getUnixTimeStamp();
            String marvelData   = timeStamp + Constants.hash + Constants.apikey;

            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] hash = messageDigest.digest(marvelData.getBytes());

            StringBuilder stringBuilder = new StringBuilder(2 * hash.length);

            for (byte b : hash)
                stringBuilder.append(String.format("%02x", b&0xff));

            marvelHash = stringBuilder.toString();
            //System.out.println(marvelHash);

        } catch (NoSuchAlgorithmException e) {

            System.out.println("[DEBUG]" + " MarvelApiUtils generateMarvelHash - " +
                    "NoSuchAlgorithmException");
        }
        return marvelHash;
    }

    public  String getUnixTimeStamp () {
        System.out.println(String.valueOf(System.currentTimeMillis() / 1000L));
        return String.valueOf(System.currentTimeMillis() / 1000L);
    }
}
