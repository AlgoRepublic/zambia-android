package com.algorelpublic.zambia.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.algorelpublic.zambia.Method.Favourite;
import com.algorelpublic.zambia.R;
import com.algorelpublic.zambia.Zambia;
import com.algorelpublic.zambia.model.AboutUsModel;
import com.algorelpublic.zambia.model.FAQSModel;
import com.algorelpublic.zambia.model.GuidelineModel;
import com.algorelpublic.zambia.model.HelpLineModel;
import com.algorelpublic.zambia.model.MedicineModel;
import com.algorelpublic.zambia.model.SearchCriteriaModel;
import com.algorelpublic.zambia.model.SearchResultModel;
import com.algorelpublic.zambia.services.APIService;
import com.algorelpublic.zambia.utils.CallBack;
import com.algorelpublic.zambia.utils.Constants;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by Adil on 6/6/2017.
 */

public class Splash extends BaseActivity {
    private static final int SPLASH_TIME = 2 * 1000;
    private static final int API_TIME = 1000;
    private Intent intent;
    private APIService service;
    private RotateAnimation anim;
    private ImageView ivLoad;
    Handler apiHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        ivLoad = (ImageView) findViewById(R.id.ivLoad);

        if (Zambia.db.getBoolean(Constants.LOAD_FROM_MEMORY)) {
//            new Handler().postDelayed(new Runnable() {
//
//                @Override
//                public void run() {
//                    startApp();
//                }
//            }, SPLASH_TIME);
            apiHandler.postDelayed(loadSplashThread, SPLASH_TIME);
            ivLoad.setVisibility(View.GONE);
        } else {
            Zambia.db.putInt(Constants.PROGRESS_LOAD_APP, 0);
            animateProgress();
            apiHandler.postDelayed(loadAboutUs, API_TIME);

        }
    }

    Runnable loadSplashThread = new Runnable() {
        @Override
        public void run() {
            startApp();
        }
    };

    Runnable loadAboutUs = new Runnable() {
        @Override
        public void run() {
            service = new APIService(Splash.this);
            service.getAboutUs(false, new CallBack(Splash.this, "saveAboutUs"));
            apiHandler.postDelayed(loadFAQS, API_TIME);
        }
    };
    Runnable loadFAQS = new Runnable() {
        @Override
        public void run() {
            service = new APIService(Splash.this);
            service.getFAQS(false, new CallBack(Splash.this, "saveFAQS"));
            apiHandler.postDelayed(loadHelpLine, API_TIME);
        }
    };
    Runnable loadHelpLine = new Runnable() {
        @Override
        public void run() {
            service = new APIService(Splash.this);
            service.getHelpLine(false, new CallBack(Splash.this, "saveHelpLine"));
            apiHandler.postDelayed(loadGuideLine, API_TIME);
        }
    };
    Runnable loadGuideLine = new Runnable() {
        @Override
        public void run() {
            service = new APIService(Splash.this);
            service.getGuideline(false, new CallBack(Splash.this, "saveGuideLine"));
            apiHandler.postDelayed(loadMedicine, API_TIME);
        }
    };
    Runnable loadMedicine = new Runnable() {
        @Override
        public void run() {
            service = new APIService(Splash.this);
            service.getMedicine(false, new CallBack(Splash.this, "saveMedicine"));
            apiHandler.postDelayed(loadFavourite, API_TIME);
        }
    };
    Runnable loadFavourite = new Runnable() {
        @Override
        public void run() {
            ArrayList<Favourite> favouriteArrayList = new ArrayList<>();
            Gson gson = new Gson();
            String jsonString = gson.toJson(favouriteArrayList);
            Zambia.db.putString(Constants.FAVOURITE_GSON, jsonString);
            apiHandler.postDelayed(loadSearchCriterias, API_TIME);
        }
    };

    Runnable loadSearchCriterias = new Runnable() {
        @Override
        public void run() {
            service = new APIService(Splash.this);
            service.getSearchCriterias(false, new CallBack(Splash.this, "searchCriterias"));
            apiHandler.postDelayed(loadSearchResults, API_TIME);

        }

    };
    Runnable loadSearchResults = new Runnable() {
        @Override
        public void run() {
            service = new APIService(Splash.this);
            service.getSearchResults(false, new CallBack(Splash.this, "searchResults"));
        }
    };

    public void animateProgress() {
        anim = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(Animation.INFINITE);
        anim.setDuration(3000);
        ivLoad.setAnimation(anim);
        ivLoad.startAnimation(anim);
    }


    /**
     * ****** this Method must be public ********
     *
     * @param caller
     * @param model
     */

    public void saveFAQS(Object caller, Object model) {
        FAQSModel.getInstance().setObj((FAQSModel) model);
        if (FAQSModel.getInstance().status.equalsIgnoreCase("ok")) {
            Gson gson = new Gson();
            String response = gson.toJson(FAQSModel.getInstance());
            Zambia.db.putString(Constants.RESPONSE_GSON_FAQS, response);
            Zambia.db.putInt(Constants.PROGRESS_LOAD_APP, Zambia.db.getInt(Constants.PROGRESS_LOAD_APP) + 10);
            checkForProgress();
        } else {
        }
    }

    /**
     * ****** this Method must be public ********
     *
     * @param caller
     * @param model
     */

    public void searchCriterias(Object caller, Object model) {
        SearchCriteriaModel.getInstance().setObj((SearchCriteriaModel) model);
        if (SearchCriteriaModel.getInstance().status.equalsIgnoreCase("ok")) {
            Gson gson = new Gson();
            String response = gson.toJson(SearchCriteriaModel.getInstance());
            Zambia.db.putString(Constants.RESPONSE_GSON_SEARCH_CRITERIAS, response);
            Zambia.db.putInt(Constants.PROGRESS_LOAD_APP, Zambia.db.getInt(Constants.PROGRESS_LOAD_APP) + 15);
            checkForProgress();
        } else {
        }
    }

    /**
     * ****** this Method must be public ********
     *
     * @param caller
     * @param model
     */

    public void searchResults(Object caller, Object model) {
        SearchResultModel.getInstance().setObj((SearchResultModel) model);
        if (SearchResultModel.getInstance().status.equalsIgnoreCase("ok")) {
            Gson gson = new Gson();
            String response = gson.toJson(SearchResultModel.getInstance());
            Zambia.db.putString(Constants.RESPONSE_GSON_SEARCH_RESULT, response);
            Zambia.db.putInt(Constants.PROGRESS_LOAD_APP, Zambia.db.getInt(Constants.PROGRESS_LOAD_APP) + 15);
            checkForProgress();
        } else {
        }
    }

    /**
     * ****** this Method must be public ********
     *
     * @param caller
     * @param model
     */

    public void saveHelpLine(Object caller, Object model) {
        HelpLineModel.getInstance().setObj((HelpLineModel) model);
        if (HelpLineModel.getInstance().status.equalsIgnoreCase("ok")) {
            Gson gson = new Gson();
            String response = gson.toJson(HelpLineModel.getInstance());
            Zambia.db.putString(Constants.RESPONSE_GSON_HELP_LINE, response);
            Zambia.db.putInt(Constants.PROGRESS_LOAD_APP, Zambia.db.getInt(Constants.PROGRESS_LOAD_APP) + 15);
            checkForProgress();
        } else {
        }
    }

    /**
     * ****** this Method must be public ********
     *
     * @param caller
     * @param model
     */

    public void saveGuideLine(Object caller, Object model) {
        GuidelineModel.getInstance().setObj((GuidelineModel) model);
        if (GuidelineModel.getInstance().status.equalsIgnoreCase("ok")) {
            Gson gson = new Gson();
            String response = gson.toJson(GuidelineModel.getInstance());
            Zambia.db.putString(Constants.RESPONSE_GSON_GUIDELINE_LINE, response);
            Zambia.db.putInt(Constants.PROGRESS_LOAD_APP, Zambia.db.getInt(Constants.PROGRESS_LOAD_APP) + 15);
            checkForProgress();
        } else {
        }
    }

    /**
     * ****** this Method must be public ********
     *
     * @param caller
     * @param model
     */

    public void saveMedicine(Object caller, Object model) {
        MedicineModel.getInstance().setObj((MedicineModel) model);
        if (MedicineModel.getInstance().status.equalsIgnoreCase("ok")) {
            Gson gson = new Gson();
            String response = gson.toJson(MedicineModel.getInstance());
            Zambia.db.putString(Constants.RESPONSE_GSON_MEDICINES_LINE, response);
            Zambia.db.putInt(Constants.PROGRESS_LOAD_APP, Zambia.db.getInt(Constants.PROGRESS_LOAD_APP) + 15);
            checkForProgress();
        } else {
        }
    }

    /**
     * ****** this Method must be public ********
     *
     * @param caller
     * @param model
     */

    public void saveAboutUs(Object caller, Object model) {
        AboutUsModel.getInstance().setObj((AboutUsModel) model);
        if (AboutUsModel.getInstance().status.equalsIgnoreCase("ok")) {
            updateModel();
            Zambia.db.putBoolean(Constants.LOAD_FROM_MEMORY, true);
            Zambia.db.putInt(Constants.PROGRESS_LOAD_APP, Zambia.db.getInt(Constants.PROGRESS_LOAD_APP) + 15);
            checkForProgress();
        } else {
        }
    }

    private void checkForProgress() {
        if (Zambia.db.getInt(Constants.PROGRESS_LOAD_APP) < 100) {
            return;
        } else {
            startApp();
        }

    }

    public void updateModel() {
        Gson gson = new Gson();
        String response = gson.toJson(AboutUsModel.getInstance());
        Zambia.db.putString(Constants.RESPONSE_GSON_ABOUT_US, response);
    }

    private void startApp() {
        intent = new Intent(this, ZambiaMain.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }

}

