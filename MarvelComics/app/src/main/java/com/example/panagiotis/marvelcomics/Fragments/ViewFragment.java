package com.example.panagiotis.marvelcomics.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.panagiotis.marvelcomics.Adapter.Adapter;
import com.example.panagiotis.marvelcomics.Adapter.Adapter2;
import com.example.panagiotis.marvelcomics.Contract.IContract;
import com.example.panagiotis.marvelcomics.Contract.Presenter;
import com.example.panagiotis.marvelcomics.MainActivity;
import com.example.panagiotis.marvelcomics.R;
import com.example.panagiotis.marvelcomics.Realm.ComicsRealm;
import com.example.panagiotis.marvelcomics.pojos.Example;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

import static com.google.common.base.Preconditions.checkNotNull;


public class ViewFragment extends Fragment implements IContract.IView {

    @BindView(R.id.recycleView)
    RecyclerView recyclerView;

    @BindView(R.id.recycleView2)
    RecyclerView recyclerView2;
    Adapter adapter;
    Adapter2 adapter2;
    IContract.IPresenter iPresenter;
    Double max,min;
    private ProgressDialog pDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iPresenter=new Presenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_view, container, false);
        ButterKnife.bind(this,v);
        Bundle b = getArguments();
        if(b!=null){
            max= Double.valueOf(b.getString("max"));
            min= Double.valueOf(b.getString("min"));
            iPresenter.filterComics(min,max);
        }
        else{
            iPresenter.getComics();
        }

        return v;
    }

    @Override
    public void showComics(Example example) {
        adapter=new Adapter(R.layout.row,getActivity(),example,(MainActivity)getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showFilterComics(RealmResults<ComicsRealm> results) {
        recyclerView.setVisibility(View.GONE);
        recyclerView2.setVisibility(View.VISIBLE);
        adapter2=new Adapter2(getActivity(),R.layout.row,results,(MainActivity)getActivity());
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        recyclerView2.setAdapter(adapter2);
    }

    @Override
    public void showProgressDialog() {
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        if(pDialog.isShowing() || pDialog!=null){
            pDialog.dismiss();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        iPresenter.start();
    }

    @Override
    public void setPresenter(IContract.IPresenter presenter) {
        iPresenter=checkNotNull(presenter);
    }
}
