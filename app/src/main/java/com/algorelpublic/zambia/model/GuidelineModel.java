package com.algorelpublic.zambia.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by creater on 6/19/2017.
 */

public class GuidelineModel {
    public static GuidelineModel _obj = null;

    public GuidelineModel() {

    }

    public static GuidelineModel getInstance() {
        if (_obj == null) {
            _obj = new GuidelineModel();
        }
        return _obj;
    }

    public void setObj(GuidelineModel obj) {
        _obj = obj;
    }


    @SerializedName("status")
    public String status;

    @SerializedName("guidelines")
    public ArrayList<Guideline> guidelines = new ArrayList<>();

    public class Guideline {

        @SerializedName("id")
        public String id;

        @SerializedName("content_heading")
        public String contentHeading;

        @SerializedName("html_content")
        public String htmlContent;

        @SerializedName("category_id")
        public String categoryId;

        @SerializedName("category_name")
        public String categoryName;

        @SerializedName("sub_category_id")
        public String subCategoryId;

        @SerializedName("sub_category_name")
        public String subCategoryName;

        public boolean isFavourite;


    }

}


