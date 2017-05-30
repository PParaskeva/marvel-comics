package com.example.panagiotis.marvelcomics;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.panagiotis.marvelcomics.Fragments.ViewFragment;
import com.example.panagiotis.marvelcomics.Fragments.price_fragment;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentTransfer(new ViewFragment());

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab.setVisibility(View.GONE);
                fragmentTransfer(new price_fragment());
            }
        });
    }

    public void fragmentTransfer(Fragment f) {
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment;
        fragment = f;
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }

    public void fragmentTransfer(Fragment f,String id) {
        Bundle args = new Bundle();
        args.putString("id", id);
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment;
        fragment = f;
        fragment.setArguments(args);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }

    public void fragmentTransfer(Fragment f,String max,String min) {
        fab.setVisibility(View.VISIBLE);
        Bundle args = new Bundle();
        args.putString("min", min);
        args.putString("max", max);
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment;
        fragment = f;
        fragment.setArguments(args);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }
}
