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
import com.algorelpublic.zambia.model.FAQSModel;
import com.algorelpublic.zambia.utils.Constants;
import com.google.gson.Gson;

/**
 * Created by creater on 6/16/2017.
 */

public class ToolsFragment extends BaseFragment implements View.OnClickListener {

    public static ToolsFragment instance;
    private View view;
    private WebView webview;
    private ProgressDialog progressDialog;
    String mime = "text/html";
    String encoding = "utf-8";
    private FAQSModel faqsModel;

    public static ToolsFragment newInstance() {
        instance = new ToolsFragment();
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
        appCompatActivity.getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>FAQS</font>"));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_about_us, container, false);
        init();
        addListener();
        Gson gson = new Gson();
        faqsModel = gson.fromJson(Zambia.db.getString(Constants.RESPONSE_GSON_FAQS), FAQSModel.class);
        loadWebView();
        if (faqsModel != null) {
            try {
                webview.loadDataWithBaseURL(null, faqsModel.faqsArrayList.get(0).htmlContent, mime, encoding, null);
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            } catch (IndexOutOfBoundsException ex) {
                ex.printStackTrace();
            }
        }
        return view;
    }

    private void loadWebView() {
        WebSettings settings = webview.getSettings();
        webview.setBackgroundColor(Color.TRANSPARENT);
        settings.setJavaScriptEnabled(true);
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