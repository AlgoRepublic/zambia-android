package com.algorelpublic.zambia.fragments;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.algorelpublic.zambia.R;
import com.algorelpublic.zambia.Zambia;
import com.algorelpublic.zambia.model.AboutUsModel;
import com.algorelpublic.zambia.utils.Constants;
import com.algorelpublic.zambia.utils.NetworkUtil;
import com.google.gson.Gson;

/**
 * Created by Adil on 6/15/2017.
 */

public class AboutUsFragment extends BaseFragment implements View.OnClickListener {

    public static AboutUsFragment instance;
    private View view;
    private WebView webview;
    private ProgressDialog progressDialog;
    String mime = "text/html";
    String encoding = "utf-8";
    private AboutUsModel aboutUsModel;

    public static AboutUsFragment newInstance() {
        instance = new AboutUsFragment();
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
        appCompatActivity.getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>About Us</font>"));
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_about_us, container, false);
        init();
        addListener();
        Gson gson = new Gson();
        aboutUsModel = gson.fromJson(Zambia.db.getString(Constants.RESPONSE_GSON_ABOUT_US), AboutUsModel.class);
        loadWebView();
        return view;
    }

    private void loadWebView() {
        WebSettings settings = webview.getSettings();
        webview.setBackgroundColor(Color.TRANSPARENT);
        settings.setJavaScriptEnabled(true);
        settings.setAppCacheMaxSize(5 * 1024 * 1024);
        settings.setAppCachePath(getActivity().getCacheDir().getAbsolutePath());
        settings.setAllowFileAccess(true);
        settings.setAppCacheEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT); // load online by default

        if (!NetworkUtil.isInternetConnected(getActivity())) {
            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
        webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getActivity(), "Error:" + description, Toast.LENGTH_SHORT).show();

            }
        });

        webview.loadDataWithBaseURL(null, aboutUsModel.aboutUsList.get(0).htmlContent, mime, encoding, null);
//        webview.loadUrl(AboutUsModel.AboutUs);
    }

    private void init() {
        webview = (WebView) view.findViewById(R.id.wbAbout);
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