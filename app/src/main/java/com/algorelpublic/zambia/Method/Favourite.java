package com.algorelpublic.zambia.Method;

import com.google.gson.annotations.SerializedName;

/**
 * Created by creater on 6/20/2017.
 */

public class Favourite {

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

    public int type = 0;
}
