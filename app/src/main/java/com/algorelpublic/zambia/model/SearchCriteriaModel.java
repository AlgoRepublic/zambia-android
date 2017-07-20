package com.algorelpublic.zambia.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by creater on 7/3/2017.
 */

public class SearchCriteriaModel {

    public static SearchCriteriaModel _obj = null;

    public SearchCriteriaModel() {

    }

    public static SearchCriteriaModel getInstance() {
        if (_obj == null) {
            _obj = new SearchCriteriaModel();
        }
        return _obj;
    }

    public void setObj(SearchCriteriaModel obj) {
        _obj = obj;
    }

    @SerializedName("status")
    public String status;

    @SerializedName("criterias")
    public ArrayList<Criteria> criteriaes = new ArrayList<>();

    public class Criteria {

        @SerializedName("id")
        public String id;

        @SerializedName("title")
        public String title;

        @SerializedName("parent_id")
        public String parentId;

        @SerializedName("parent_name")
        public String parentName;

        @SerializedName("question_text")
        public String questionText;

        @SerializedName("type")
        public String type;


    }

}
