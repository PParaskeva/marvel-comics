package com.example.panagiotis.marvelcomics.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.panagiotis.marvelcomics.Contract.IContract;
import com.example.panagiotis.marvelcomics.Contract.Presenter_Details;
import com.example.panagiotis.marvelcomics.R;
import com.example.panagiotis.marvelcomics.pojos.Example;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

public class DetailFragment extends Fragment implements IContract.IView_details {

    IContract.IPresenter_Details iPresenter;
    String id="";

    @BindView(R.id.title_textView)
    TextView title_textView;

    @BindView(R.id.description_textView)
    TextView description_textView;

    @BindView(R.id.page_count_textView)
    TextView page_count_textView;

    @BindView(R.id.author_textView)
    TextView author_textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iPresenter=new Presenter_Details(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this,v);
        Bundle b = getArguments();
        id=b.getString("id");
        iPresenter.getById(id);
        return v;
    }

    @Override
    public void showDetails(Example example) {
        title_textView.setText(example.getData().getResults().get(0).getTitle());
        if(example.getData().getResults().get(0).getDescription()!=null)
            description_textView.setText(example.getData().getResults().get(0).getDescription());
        else{
            description_textView.setText("No available description");
        }
        page_count_textView.setText("Page count :"+example.getData().getResults().get(0).getPageCount().toString());

        if(example.getData().getResults().get(0).getCreators().getAvailable()!=0){
            String temp="";
            for (int i=0;i<example.getData().getResults().get(0).getCreators().getAvailable();i++){
                temp=example.getData().getResults().get(0).getCreators().getItems().get(i).getName()+'\n';
            }
            author_textView.setText(temp);
        }
        else {
            author_textView.setText("No Author's available");
        }
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void dismissProgressDialog() {

    }

    @Override
    public void setPresenter(IContract.IPresenter_Details presenter) {
        iPresenter=checkNotNull(presenter);
    }

    @Override
    public void onResume() {
        super.onResume();
        iPresenter.start();
    }
}
