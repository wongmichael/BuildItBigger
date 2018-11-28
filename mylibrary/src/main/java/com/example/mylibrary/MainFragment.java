package com.example.mylibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainFragment extends Fragment {
    public MainFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return
        Toast.makeText(getActivity(),"mylibrary MainFrag onCreateView",Toast.LENGTH_LONG).show();
        Log.d("mylibrary mainFragment","onCreateView");
        View rootView = inflater.inflate(R.layout.fragment_main,container,false);

        Intent intent = getActivity().getIntent();
        String joke = intent.getStringExtra(MainActivity.JOKE_KEY);
        TextView textView = rootView.findViewById(R.id.fragment_main_tv);
        textView.setText(joke);

        return rootView;
    }
}
