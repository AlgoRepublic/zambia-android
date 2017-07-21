package com.algorelpublic.zambia.services;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.algorelpublic.zambia.model.AboutUsModel;
import com.algorelpublic.zambia.model.FAQSModel;
import com.algorelpublic.zambia.model.GeneralModel;
import com.algorelpublic.zambia.model.GuidelineModel;
import com.algorelpublic.zambia.model.HelpLineModel;
import com.algorelpublic.zambia.model.MedicineModel;
import com.algorelpublic.zambia.model.SearchCriteriaModel;
import com.algorelpublic.zambia.model.SearchResultModel;
import com.algorelpublic.zambia.utils.CallBack;
import com.algorelpublic.zambia.utils.Constants;

import java.util.HashMap;

import com.algorelpublic.zambia.model.FCMModel;

/**
 * Created by creater on 6/15/2017.
 */

public class APIService extends BaseService {
    public APIService(Context ctx) {
        super((Activity) ctx);
    }


    public void registerFCM(String fcmID, boolean showProgress, CallBack obj) {
        String Url = Constants.REGISTER_FCM_URL;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("fcm_id", fcmID);
        this.post(Url, params, obj, FCMModel.getInstance(), showProgress);
        Log.e(
                "Url", Url);
    }

    public void getAboutUs(boolean showProgress, CallBack obj) {
        String Url = Constants.FETCH_ABOUT_US_URL;
        this.get(Url, obj, AboutUsModel.getInstance(), showProgress);
        Log.e("Url", Url);
    }

    public void getFAQS(boolean showProgress, CallBack obj) {
        String Url = Constants.FETCH_FAQS_URL;
        this.get(Url, obj, FAQSModel.getInstance(), showProgress);
        Log.e("Url", Url);
    }

    public void getHelpLine(boolean showProgress, CallBack obj) {
        String Url = Constants.HELPLINE_URL;
        this.get(Url, obj, HelpLineModel.getInstance(), showProgress);
        Log.e("Url", Url);
    }

    public void getGuideline(boolean showProgress, CallBack obj) {
        String Url = Constants.FETCH_GUIDELINE_URL;
        this.get(Url, obj, GuidelineModel.getInstance(), showProgress);
        Log.e("Url", Url);
    }

    public void getSearchCriterias(boolean showProgress, CallBack obj) {
        String Url = Constants.FETCH_SEARCH_CRITERIAS_URL;
        this.get(Url, obj, SearchCriteriaModel.getInstance(), showProgress);
        Log.e("Url", Url);
    }

    public void getSearchResults(boolean showProgress, CallBack obj) {
        String Url = Constants.FETCH_SEARCH_RESULT_URL;
        this.get(Url, obj, SearchResultModel.getInstance(), showProgress);
        Log.e("Url", Url);
    }

    public void getMedicine(boolean showProgress, CallBack obj) {
        String Url = Constants.FETCH_MEDICINES_URL;
        this.get(Url, obj, MedicineModel.getInstance(), showProgress);
        Log.e("Url", Url);
    }

    public void submitFeedback(String name, String email, String contactNumber, String feeback, boolean showProgress, CallBack obj) {
        String Url = Constants.FEEDBACK_URL;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("name", name);
        params.put("email", email);
        params.put("contact_number", contactNumber);
        params.put("feedback", feeback);
        this.post(Url, params, obj, GeneralModel.getInstance(), showProgress);
        Log.e("Url", Url);
    }


}
