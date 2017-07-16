package com.algorelpublic.zambia.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by creater on 6/15/2017.
 */

public class AboutUsModel {
    public static AboutUsModel _obj = null;

    public AboutUsModel() {

    }

    public static AboutUsModel getInstance() {
        if (_obj == null) {
            _obj = new AboutUsModel();
        }
        return _obj;
    }

    public void setObj(AboutUsModel obj) {
        _obj = obj;
    }

    @SerializedName("success")
    public boolean success;

    @SerializedName("status")
    public String status;

    @SerializedName("about_us")
    public ArrayList<AboutUs> aboutUsList = new ArrayList<>();

    public class AboutUs {

        @SerializedName("id")
        public String id;

        @SerializedName("content_heading")
        public String contentHeading;

        @SerializedName("html_content")
        public String htmlContent;

        @SerializedName("created_at")
        public String createdAt;

        @SerializedName("updated_at")
        public String updatedAt;


    }

}
