package com.example.panagiotis.marvelcomics.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.panagiotis.marvelcomics.Contract.IContract;
import com.example.panagiotis.marvelcomics.Contract.Presenter;
import com.example.panagiotis.marvelcomics.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ViewFragment extends Fragment implements IContract.IView {

    @BindView(R.id.recycleView)
    RecyclerView recyclerView;

    IContract.IPresenter iPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iPresenter=new Presenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_view, container, false);
        iPresenter.getComics();
        ButterKnife.bind(this,v);
        return v;
    }

    @Override
    public void showComics() {

    }

    @Override
    public void showFilterComics() {

    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void dismissProgressDialog() {

    }

    @Override
    public void onResume() {
        super.onResume();
        iPresenter.start();
    }

    @Override
    public void setPresenter(IContract.IPresenter presenter) {

    }
}
