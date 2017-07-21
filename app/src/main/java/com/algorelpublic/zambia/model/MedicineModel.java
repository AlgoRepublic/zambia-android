package com.algorelpublic.zambia.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by creater on 6/19/2017.
 */

public class MedicineModel {
    public static MedicineModel _obj = null;

    public MedicineModel() {

    }

    public static MedicineModel getInstance() {
        if (_obj == null) {
            _obj = new MedicineModel();
        }
        return _obj;
    }

    public void setObj(MedicineModel obj) {
        _obj = obj;
    }


    @SerializedName("status")
    public String status;

    @SerializedName("medicines")
    public ArrayList<Medicines> medicines = new ArrayList<>();

    public class Medicines {

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

