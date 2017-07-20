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

    private ImageView ivLoad;
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
            animateProgress();
           // ivLoad.setVisibility(View.GONE);
        } else {
            Zambia.db.putInt(Constants.PROGRESS_LOAD_APP, 0);
            animateProgress();
            apiHandler.postDelayed(loadAboutUs, API_TIME);
        }
    }

    public void animateProgress() {
        anim = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(Animation.INFINITE);
        anim.setDuration(3000);
        ivLoad.setAnimation(anim);
        ivLoad.startAnimation(anim);
    }

    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }

}

