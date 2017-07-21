package com.algorelpublic.zambia.model;

/**
 * Created by creater on 6/16/2017.
 */

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HelpLineModel {
    public static HelpLineModel _obj = null;

    public HelpLineModel() {

    }

    public static HelpLineModel getInstance() {
        if (_obj == null) {
            _obj = new HelpLineModel();
        }
        return _obj;
    }

    public void setObj(HelpLineModel obj) {
        _obj = obj;
    }


    @SerializedName("status")
    public String status;

    @SerializedName("helplines")
    public ArrayList<Helpline> helplines = new ArrayList<>();

    public class Helpline {

        @SerializedName("id")
        public String id;

        @SerializedName("title")
        public String title;

        @SerializedName("address")
        public String address;

        @SerializedName("city")
        public String city;

        @SerializedName("country")
        public String country;

        @SerializedName("contact_no")
        public String contactNo;


    }

}


