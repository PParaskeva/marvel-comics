package com.example.panagiotis.marvelcomics.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.panagiotis.marvelcomics.MainActivity;
import com.example.panagiotis.marvelcomics.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class price_fragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.Max_editText)
    EditText max;
    @BindView(R.id.min_editText)
    EditText min;
    @BindView(R.id.search_button)
    Button search;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_price_fragment, container, false);
        ButterKnife.bind(this,v);
        search.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.search_button){
            String d1=min.getText().toString();
            String d2=max.getText().toString();

            if (d1.length()==0){
                d1="0";
            }
            if (d2.length()==0){
                d2="0";
            }
            ((MainActivity)getActivity()).fragmentTransfer(new ViewFragment(),d2,d1);
        }
    }
}
