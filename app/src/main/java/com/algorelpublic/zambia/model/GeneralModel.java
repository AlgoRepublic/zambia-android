package com.algorelpublic.zambia.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Adil on 6/16/2017.
 */

public class GeneralModel {

    public static GeneralModel _obj = null;

    public GeneralModel() {

    }

    public static GeneralModel getInstance() {
        if (_obj == null) {
            _obj = new GeneralModel();
        }
        return _obj;
    }

    public void setObj(GeneralModel obj) {
        _obj = obj;
    }

    @SerializedName("status")
    public String status;

    @SerializedName("message")
    public String message;

}
