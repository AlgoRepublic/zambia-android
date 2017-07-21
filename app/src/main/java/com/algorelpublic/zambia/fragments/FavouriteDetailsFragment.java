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
import android.widget.TextView;
import android.widget.Toast;

import com.algorelpublic.zambia.Method.Favourite;
import com.algorelpublic.zambia.R;
import com.algorelpublic.zambia.activities.ZambiaMain;
import com.algorelpublic.zambia.utils.NetworkUtil;

/**
 * Created by creater on 6/20/2017.
 */

public class FavouriteDetailsFragment extends BaseFragment implements View.OnClickListener {

    public static FavouriteDetailsFragment instance;
    private static Favourite favouriteModel;
    private View view;
    private WebView webview;
    private ProgressDialog progressDialog;
    String mime = "text/html";
    String encoding = "utf-8";
    private TextView tvCategoryName;

    public static FavouriteDetailsFragment newInstance(Favourite model) {
        favouriteModel = model;
        instance = new FavouriteDetailsFragment();
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
        appCompatActivity.getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Favourites</font>"));
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_guidline_detail, container, false);
        init();
        addListener();
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

        webview.loadDataWithBaseURL(null, favouriteModel.htmlContent, mime, encoding, null);
        tvCategoryName.setText(favouriteModel.categoryName);
    }

    private void init() {
        tvCategoryName = (TextView) view.findViewById(R.id.tvCategoryName);
        webview = (WebView) view.findViewById(R.id.wbGuidelineDetail);
//        ZambiaMain.favouriteCheckBox.setVisibility(View.VISIBLE);
//        ZambiaMain.favouriteCheckBox.setOnClickListener(this);
//        ZambiaMain.favouriteCheckBox.setChecked(favouriteModel.isFavourite);
    }

    private void addListener() {
//        btnForgotPassword.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ZambiaMain.favouriteCheckBox.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.group_chk_box:

                break;
        }

    }
}