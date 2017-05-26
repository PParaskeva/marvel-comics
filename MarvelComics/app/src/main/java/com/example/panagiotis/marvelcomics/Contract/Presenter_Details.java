package com.example.panagiotis.marvelcomics.Contract;

import com.example.panagiotis.marvelcomics.Constants;
import com.example.panagiotis.marvelcomics.Services.Connection;
import com.example.panagiotis.marvelcomics.Services.IData;
import com.example.panagiotis.marvelcomics.pojos.Example;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Presenter_Details implements IContract.IPresenter_Details  {
    IContract.IView_details iView_details;
    IData connection;

    public Presenter_Details(IContract.IView_details iView_details) {
        this.iView_details = iView_details;
    }

    @Override
    public void getById(String id) {
        connection=Connection.getConnection();
        connection.get_Details(id,getHash(),Constants.apikey,getUnixTimeStamp())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Example>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Example example) {
                        iView_details.showDetails(example);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void start() {
        iView_details.setPresenter(this);
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
