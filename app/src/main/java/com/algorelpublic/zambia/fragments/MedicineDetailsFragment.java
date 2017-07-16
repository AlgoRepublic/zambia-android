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
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.algorelpublic.zambia.Method.Favourite;
import com.algorelpublic.zambia.R;
import com.algorelpublic.zambia.Zambia;
import com.algorelpublic.zambia.activities.ZambiaMain;
import com.algorelpublic.zambia.model.MedicineModel;
import com.algorelpublic.zambia.utils.Constants;
import com.algorelpublic.zambia.utils.NetworkUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by creater on 6/19/2017.
 */

public class MedicineDetailsFragment extends BaseFragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    public static MedicineDetailsFragment instance;

    private static MedicineModel.Medicines medicineModel;
    public MedicineModel medicineDBModel;
    private View view;
    private WebView webview;
    private ProgressDialog progressDialog;
    String mime = "text/html";
    String encoding = "utf-8";
    private TextView tvCategoryName;
    ArrayList<Favourite> favourites;
    Gson gson = new Gson();

    public static MedicineDetailsFragment newInstance(MedicineModel.Medicines model) {
        medicineModel = model;
        instance = new MedicineDetailsFragment();
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
        appCompatActivity.getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Medicines</font>"));
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_guidline_detail, container, false);
        init();
        addListener();
        loadWebView();
        medicineDBModel = gson.fromJson(Zambia.db.getString(Constants.RESPONSE_GSON_MEDICINES_LINE), MedicineModel.class);
        favourites = gson.fromJson(Zambia.db.getString(Constants.FAVOURITE_GSON),
                new TypeToken<ArrayList<Favourite>>() {
                }.getType());

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

        webview.loadDataWithBaseURL(null, medicineModel.htmlContent, mime, encoding, null);
        tvCategoryName.setText(medicineModel.categoryName);
    }


    private void init() {
        tvCategoryName = (TextView) view.findViewById(R.id.tvCategoryName);
        webview = (WebView) view.findViewById(R.id.wbGuidelineDetail);
        ZambiaMain.favouriteCheckBox.setVisibility(View.VISIBLE);
        ZambiaMain.favouriteCheckBox.setOnCheckedChangeListener(this);
        ZambiaMain.favouriteCheckBox.setChecked(medicineModel.isFavourite);
    }

    private void addListener() {
//        btnForgotPassword.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ZambiaMain.favouriteCheckBox.setVisibility(View.GONE);
        updateModel();

    }

    private void updateModel() {
        if (medicineDBModel != null && medicineDBModel.medicines.size() > 0) {
            for (int loop = 0; loop < medicineDBModel.medicines.size(); loop++) {
                if (medicineDBModel.medicines.get(loop).id.equalsIgnoreCase(medicineModel.id)) {
                    medicineDBModel.medicines.get(loop).isFavourite = medicineModel.isFavourite;
                }
            }
            String response = gson.toJson(medicineDBModel);
            Zambia.db.putString(Constants.RESPONSE_GSON_MEDICINES_LINE, response);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }

    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            addInFavourite(medicineModel);
            medicineModel.isFavourite = isChecked;
        } else {
            removeFromFavourite(medicineModel);
            medicineModel.isFavourite = isChecked;
        }
    }

    private void removeFromFavourite(MedicineModel.Medicines model) {
        if (favourites != null && favourites.size() > 0) {
            for (int i = 0; i < favourites.size(); i++) {
                if (favourites.get(i).id.equalsIgnoreCase(model.id))
                    favourites.remove(i);
            }
        }
        String jsonString = gson.toJson(favourites);
        Zambia.db.putString(Constants.FAVOURITE_GSON, jsonString);
    }

    private void addInFavourite(MedicineModel.Medicines model) {
        if (favourites != null) {
            Favourite favourite = new Favourite();
            favourite.categoryId = model.categoryId;
            favourite.categoryName = model.categoryName;
            favourite.contentHeading = model.contentHeading;
            favourite.htmlContent = model.htmlContent;
            favourite.id = model.id;
            favourite.subCategoryId = model.subCategoryId;
            favourite.subCategoryName = model.subCategoryName;
            favourite.type = Constants.MEDICINE;
            favourite.isFavourite = true;
            favourites.add(favourite);
            String jsonString = gson.toJson(favourites);
            Zambia.db.putString(Constants.FAVOURITE_GSON, jsonString);
        }
    }
}