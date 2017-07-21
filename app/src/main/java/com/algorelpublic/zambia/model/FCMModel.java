package com.algorelpublic.zambia.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Adil on 6/15/2017.
 */

public class FCMModel {
    public static FCMModel _obj = null;

    public FCMModel() {

    }

    public static FCMModel getInstance() {
        if (_obj == null) {
            _obj = new FCMModel();
        }
        return _obj;
    }

    public void setObj(FCMModel obj) {
        _obj = obj;
    }

    @SerializedName("success")
    public boolean success;

    @SerializedName("message")
    public String message;

    @SerializedName("fcm_id")
    public String fcmId;

}
