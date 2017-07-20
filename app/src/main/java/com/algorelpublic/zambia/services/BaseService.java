package com.algorelpublic.zambia.services;


import android.app.Activity;
import android.app.ProgressDialog;
import android.view.View;
import android.widget.ProgressBar;

import com.algorelpublic.zambia.R;
import com.algorelpublic.zambia.utils.CallBack;
import com.algorelpublic.zambia.utils.Constants;
import com.algorelpublic.zambia.utils.NetworkUtil;
import com.algorelpublic.zambia.utils._;
import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.google.gson.Gson;

import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


/**
 * Created by Aljuma on 1/10/14.
 */

public class BaseService {

    AQuery aq;
    Activity context;
    ProgressBar progressBar;
    ProgressDialog dialog;

    public BaseService(Activity act) {
        aq = new AQuery(act);
        context = act;
    }

    public BaseService(Activity act, View view) {
        aq = new AQuery(view);
        context = act;
    }

    /**
     * @param url
     * @param callBack
     * @param model
     * @param showProgress
     */
    public void get(String url, final CallBack callBack, final Object model, final boolean showProgress) {

        if (!NetworkUtil.isInternetConnected(context)) {
            NetworkUtil.internetFailedDialog(context);
            return;
        }

        if (showProgress) {
            dialog = new ProgressDialog(context);
            dialog.setMessage("Loading. Please wait...");
            dialog.show();
        }
        if (aq.id(R.id.progress_bar).isExist() && showProgress) {
            progressBar = (ProgressBar) aq.id(R.id.progress_bar).getView();
            progressBar.setVisibility(View.VISIBLE);
        }

        aq.ajax(url, JSONObject.class,
                new AjaxCallback<JSONObject>() {
                    @Override
                    public void callback(String url, JSONObject json,
                                         AjaxStatus status) {
                        if (status.getCode() != Constants.STATUS_OK && status.getCode() != Constants.INTERNET_FAILURE) {
                            _.log("AjaxStatus: " + status.getCode());
                            _.log("There is some problem in server");
                            dismissProgressBar(showProgress);
                            NetworkUtil.showStatusDialog(context, context.getResources().getString(R.string.app_name), Constants.GENERAL_ERROR, false);
                            return;
                        }
                        Object obj = model;
                        if (json != null) {
                            _.log("JSON::" + json.toString());
                            if (validateToken(json)) {
                                return;
                            }
                            Gson gson = new Gson();
                            obj = gson.fromJson(json.toString(), obj.getClass());
                            dismissProgressBar(showProgress);
                            if (dialog != null)
                                dialog.dismiss();
                            callBack.invoke(obj);
                        } else {
                            dismissProgressBar(showProgress);
                            if (dialog != null)
                                dialog.dismiss();
                            showServerError(status);
                            return;
                        }

                    }
                });
    }

    private void dismissProgressBar(boolean showProgress) {
        if (progressBar != null && showProgress) {
            progressBar.setVisibility(View.GONE);
        }
    }

    private boolean validateToken(JSONObject json) {
        try {
            if (json.getJSONObject("response").getInt("code") == -2) {
                NetworkUtil.showStatusDialog(context, context.getResources().getString(R.string.app_name), "" + json.getJSONObject("response").getString("msg").toString(), true);
                if (progressBar != null) {
                    progressBar.setVisibility(View.GONE);
                }
                return true;
            }
        } catch (JSONException je) {

        }
        return false;
    }

    /**
     * @param url
     * @param params
     * @param callBack
     * @param model
     * @param showProgress
     */
    public void post_with_file(String url, HashMap<String, Object> params, final CallBack callBack, final Object model, final boolean showProgress) {
        if (!NetworkUtil.isInternetConnected(context)) {
            NetworkUtil.internetFailedDialog(context);
            return;
        }
        if (aq.id(R.id.progress_bar).isExist() && showProgress) {
            progressBar = (ProgressBar) aq.id(R.id.progress_bar).getView();
            progressBar.setVisibility(View.VISIBLE);
        }
        aq.ajax(url, params, JSONObject.class,
                new AjaxCallback<JSONObject>() {

                    @Override
                    public void callback(String url, JSONObject json,
                                         AjaxStatus status) {

                        Object obj = model;
                        if (json != null) {
                            try {
                                Gson gson = new Gson();
                                obj = gson.fromJson(json.toString(),
                                        obj.getClass());
                                _.log("JSON::" + json.toString());
                                if (progressBar != null && showProgress)
                                    progressBar.setVisibility(View.GONE);
                                callBack.invoke(obj);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } else {
                            if (progressBar != null && showProgress) {
                                progressBar.setVisibility(View.GONE);
                            }
                            showServerError(status);
                            return;
                        }
                    }
                });
    }

    /**
     * @param url
     * @param params
     * @param callBack
     * @param model
     * @param showProgress
     */
    public void post(String url, HashMap<String, String> params, final CallBack callBack, final Object model, final boolean showProgress) {
        if (!NetworkUtil.isInternetConnected(context)) {
            NetworkUtil.internetFailedDialog(context);
            return;
        }

        if (showProgress) {
            dialog = new ProgressDialog(context);
            dialog.setMessage("Loading. Please wait...");
            dialog.show();
        }
        if (aq.id(R.id.progress_bar).isExist() && showProgress) {
            progressBar = (ProgressBar) aq.id(R.id.progress_bar).getView();
            progressBar.setVisibility(View.VISIBLE);
        }

        aq.ajax(url, params, JSONObject.class,
                new AjaxCallback<JSONObject>() {

                    @Override
                    public void callback(String url, JSONObject json,
                                         AjaxStatus status) {

                        Object obj = model;
                        Gson gson = new Gson();
                        if (json != null) {
                            try {
                                _.log("JSON:: " + json.toString());
                                if (validateToken(json)) {
                                    return;
                                }
                                obj = gson.fromJson(json.toString(),
                                        obj.getClass());
                                //Log.e("JSON::", json.toString());
                                if (progressBar != null && showProgress) {
                                    progressBar.setVisibility(View.GONE);
                                }
                                if (dialog != null)
                                    dialog.dismiss();
                                callBack.invoke(obj);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } else {
                            if (progressBar != null && showProgress) {
                                progressBar.setVisibility(View.GONE);
                            }
                            if (dialog != null)
                                dialog.dismiss();

                            if (obj == null)
                                showServerError(status);
                            if (status.getCode() == 400) {
                                String newJson = "{\"success\":" + false + ",\"Message\":" + status.getMessage() + "}";
                                obj = gson.fromJson(newJson.toString(),
                                        obj.getClass());
                            }
                            callBack.invoke(obj);
                            return;
                        }

                    }
                });
    }

    private void showServerError(AjaxStatus status) {
        _.log("status.getError(): " + status.getError());
        _.log("status.getCode(): " + status.getCode());
    }

    /**
     * @param url
     * @param callBack
     * @param model
     * @param showProgress
     */
    public void delete(String url, final CallBack callBack, final Object model, final boolean showProgress) {

        if (!NetworkUtil.isInternetConnected(context)) {
            NetworkUtil.internetFailedDialog(context);
            return;
        }

        if (showProgress) {
            dialog = new ProgressDialog(context);
            dialog.setMessage("Loading. Please wait...");
            dialog.show();
        }
        if (aq.id(R.id.progress_bar).isExist() && showProgress) {
            progressBar = (ProgressBar) aq.id(R.id.progress_bar).getView();
            progressBar.setVisibility(View.VISIBLE);
        }

        aq.delete(url, JSONObject.class,
                new AjaxCallback<JSONObject>() {
                    @Override
                    public void callback(String url, JSONObject json,
                                         AjaxStatus status) {
                        if (status.getCode() != Constants.STATUS_OK && status.getCode() != Constants.INTERNET_FAILURE) {
                            _.log("AjaxStatus: " + status.getCode());
                            _.log("There is some problem in server");
                            dismissProgressBar(showProgress);
                            NetworkUtil.showStatusDialog(context, context.getResources().getString(R.string.app_name), Constants.GENERAL_ERROR, false);
                            return;
                        }
                        Object obj = model;
                        if (json != null) {
                            _.log("JSON::" + json.toString());
                            if (validateToken(json)) {
                                return;
                            }
                            Gson gson = new Gson();
                            obj = gson.fromJson(json.toString(), obj.getClass());
                            dismissProgressBar(showProgress);
                            if (dialog != null)
                                dialog.dismiss();
                            callBack.invoke(obj);
                        } else {
                            dismissProgressBar(showProgress);
                            if (dialog != null)
                                dialog.dismiss();
                            showServerError(status);
                            return;
                        }

                    }
                });
    }

    /**
     * @param url
     * @param callBack
     * @param model
     * @param showProgress
     */
    public void put(String url, StringEntity entity, final CallBack callBack, final Object model, final boolean showProgress) {

        if (!NetworkUtil.isInternetConnected(context)) {
            NetworkUtil.internetFailedDialog(context);
            return;
        }

        if (showProgress) {
            dialog = new ProgressDialog(context);
            dialog.setMessage("Loading. Please wait...");
            dialog.show();
        }
        if (aq.id(R.id.progress_bar).isExist() && showProgress) {
            progressBar = (ProgressBar) aq.id(R.id.progress_bar).getView();
            progressBar.setVisibility(View.VISIBLE);
        }
//
        aq.put(url, "application/json", entity, JSONObject.class,
                new AjaxCallback<JSONObject>() {
                    @Override
                    public void callback(String url, JSONObject json,
                                         AjaxStatus status) {
                        if (status.getCode() != Constants.STATUS_OK && status.getCode() != Constants.INTERNET_FAILURE) {
                            _.log("AjaxStatus: " + status.getCode());
                            _.log("There is some problem in server");
                            dismissProgressBar(showProgress);
                            NetworkUtil.showStatusDialog(context, context.getResources().getString(R.string.app_name), Constants.GENERAL_ERROR, false);
                            return;
                        }
                        Object obj = model;
                        if (json != null) {
                            _.log("JSON::" + json.toString());
                            if (validateToken(json)) {
                                return;
                            }
                            Gson gson = new Gson();
                            obj = gson.fromJson(json.toString(), obj.getClass());
                            dismissProgressBar(showProgress);
                            if (dialog != null)
                                dialog.dismiss();
                            callBack.invoke(obj);
                        } else {
                            dismissProgressBar(showProgress);
                            if (dialog != null)
                                dialog.dismiss();
                            showServerError(status);
                            return;
                        }

                    }
                });
    }

}