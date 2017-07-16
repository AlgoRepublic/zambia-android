package com.algorelpublic.zambia.fragments;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.algorelpublic.zambia.R;
import com.algorelpublic.zambia.Zambia;
import com.algorelpublic.zambia.model.FAQSModel;
import com.algorelpublic.zambia.model.HelpLineModel;
import com.algorelpublic.zambia.utils.Constants;
import com.google.gson.Gson;

/**
 * Created by creater on 6/16/2017.
 */

public class HelpLineFragment extends BaseFragment implements View.OnClickListener {

    public static HelpLineFragment instance;
    private View view;
    private TextView tvContactNumber, tvTitle, tvAddress;
    private HelpLineModel helplineModel;
    private RecyclerView rvHeadline;

    public static HelpLineFragment newInstance() {
        instance = new HelpLineFragment();
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        try {
            setToolBar();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        super.onCreate(savedInstanceState);
    }

    private void setToolBar() throws NullPointerException {
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Helpline</font>"));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_helpline, container, false);
        init();
        addListener();
        Gson gson = new Gson();
        helplineModel = gson.fromJson(Zambia.db.getString(Constants.RESPONSE_GSON_HELP_LINE), HelpLineModel.class);
//        if (helplineModel != null)
//            setValues();
        return view;
    }

    private void setValues() {
        tvContactNumber.setText(helplineModel.helplines.get(0).contactNo);
        tvTitle.setText(helplineModel.helplines.get(0).title);
        tvAddress.setText(helplineModel.helplines.get(0).address + ", " + helplineModel.helplines.get(0).city
                + ", " + helplineModel.helplines.get(0).country);

    }


    private void init() {
        rvHeadline = (RecyclerView) view.findViewById(R.id.rvHeadline);

        tvContactNumber = (TextView) view.findViewById(R.id.tvContactNumber);
        tvAddress = (TextView) view.findViewById(R.id.tvAddress);
        tvAddress = (TextView) view.findViewById(R.id.tvAddress);
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
    }

    private void addListener() {
//        btnForgotPassword.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }

    }
}