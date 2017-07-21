package com.algorelpublic.zambia.fragments;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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
import com.algorelpublic.zambia.adapter.AdapterHelpline;
import com.algorelpublic.zambia.model.FAQSModel;
import com.algorelpublic.zambia.model.HelpLineModel;
import com.algorelpublic.zambia.utils.Constants;
import com.google.gson.Gson;

/**
 * Created by creater on 6/16/2017.
 */

public class HelpLineFragment extends BaseFragment {

    public static HelpLineFragment instance;
    private View view;
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
        Gson gson = new Gson();
        helplineModel = gson.fromJson(Zambia.db.getString(Constants.RESPONSE_GSON_HELP_LINE), HelpLineModel.class);
        if (helplineModel.helplines.size()>0)
            init();

        return view;
    }

    private void init() {
        rvHeadline = (RecyclerView) view.findViewById(R.id.rvHeadline);
        rvHeadline.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        rvHeadline.setHasFixedSize(true);
        AdapterHelpline adapterHelpline = new AdapterHelpline(getActivity(),helplineModel.helplines);
        rvHeadline.setAdapter(adapterHelpline);
    }
}