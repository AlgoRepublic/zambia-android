package com.algorelpublic.zambia.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by creater on 7/4/2017.
 */

public class SearchResultModel {
    public static SearchResultModel _obj = null;

    public SearchResultModel() {

    }

    public static SearchResultModel getInstance() {
        if (_obj == null) {
            _obj = new SearchResultModel();
        }
        return _obj;
    }

    public void setObj(SearchResultModel obj) {
        _obj = obj;
    }

    @SerializedName("status")
    public String status;

    @SerializedName("results")
    public ArrayList<Results> resultsList = new ArrayList<>();

    public class Results {

        @SerializedName("id")
        public String id;

        @SerializedName("step_1_id")
        public String step_1_id;

        @SerializedName("step_1_name")
        public String step_1_name;

        @SerializedName("step_2_id")
        public String step_2_id;

        @SerializedName("step_2_name")
        public String step_2_name;

        @SerializedName("step_3_id")
        public String step_3_id;

        @SerializedName("step_3_name")
        public String step_3_name;

        @SerializedName("step_4_name")
        public String step_4_name;

        @SerializedName("step_4_id")
        public String step_4_id;
        @SerializedName("step_5_id")
        public String step_5_id;

        @SerializedName("step_6_id")
        public String step_6_id;
        @SerializedName("step_6_name")
        public String step_6_name;

        @SerializedName("step_7_id")
        public String step_7_id;

        @SerializedName("step_8_id")
        public String step_8_id;
        @SerializedName("step_8_name")
        public String step_8_name;

        @SerializedName("step_9_id")
        public String step_9_id;
        @SerializedName("step_9_name")
        public String step_9_name;

        @SerializedName("step_10_id")
        public String step_10_id;
        @SerializedName("step_10_name")
        public String step_10_name;

        @SerializedName("patient_name")
        public String patient_name;

        @SerializedName("search_result")
        public String search_result;

        @SerializedName("created_at")
        public String created_at;

        @SerializedName("updated_at")
        public String updated_at;


    }

}
