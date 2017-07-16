package com.algorelpublic.zambia.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by creater on 6/16/2017.
 */

public class FAQSModel {
    public static FAQSModel _obj = null;

    public FAQSModel() {

    }

    public static FAQSModel getInstance() {
        if (_obj == null) {
            _obj = new FAQSModel();
        }
        return _obj;
    }

    public void setObj(FAQSModel obj) {
        _obj = obj;
    }

    @SerializedName("success")
    public boolean success;

    @SerializedName("status")
    public String status;

    @SerializedName("faqs")
    public ArrayList<FAQS> faqsArrayList = new ArrayList<>();

    public class FAQS {

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

