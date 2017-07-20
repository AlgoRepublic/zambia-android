package com.algorelpublic.zambia.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.InputMethodManager;
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

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class BaseActivity extends AppCompatActivity {
    private State lifecycleState = null;
    public static InputMethodManager imeManager;
    private ProgressDialog dialog;

    protected static final int SPLASH_TIME = 2 * 1000;
    protected static final int API_TIME = 1000;
    protected Intent intent;
    protected APIService service;
    protected RotateAnimation anim;
    protected Handler apiHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLifecycleState(State.CREATED);
        imeManager = (InputMethodManager) getApplicationContext().getSystemService(INPUT_METHOD_SERVICE);
    }
    Runnable loadSplashThread = new Runnable() {
        @Override
        public void run() {
            startApp();
        }
    };



    private void startApp() {
        intent = new Intent(this, ZambiaMain.class);
        startActivity(intent);
        finish();
    }
    Runnable loadAboutUs = new Runnable() {
        @Override
        public void run() {
            service = new APIService(BaseActivity.this);
            service.getAboutUs(false, new CallBack(BaseActivity.this, "saveAboutUs"));
            apiHandler.postDelayed(loadFAQS, API_TIME);
        }
    };
    Runnable loadFAQS = new Runnable() {
        @Override
        public void run() {
            service = new APIService(BaseActivity.this);
            service.getFAQS(false, new CallBack(BaseActivity.this, "saveFAQS"));
            apiHandler.postDelayed(loadHelpLine, API_TIME);
        }
    };
    Runnable loadHelpLine = new Runnable() {
        @Override
        public void run() {
            service = new APIService(BaseActivity.this);
            service.getHelpLine(false, new CallBack(BaseActivity.this, "saveHelpLine"));
            apiHandler.postDelayed(loadGuideLine, API_TIME);
        }
    };
    Runnable loadGuideLine = new Runnable() {
        @Override
        public void run() {
            service = new APIService(BaseActivity.this);
            service.getGuideline(false, new CallBack(BaseActivity.this, "saveGuideLine"));
            apiHandler.postDelayed(loadMedicine, API_TIME);
        }
    };
    Runnable loadMedicine = new Runnable() {
        @Override
        public void run() {
            service = new APIService(BaseActivity.this);
            service.getMedicine(false, new CallBack(BaseActivity.this, "saveMedicine"));
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
            service = new APIService(BaseActivity.this);
            service.getSearchCriterias(false, new CallBack(BaseActivity.this, "searchCriterias"));
            apiHandler.postDelayed(loadSearchResults, API_TIME);

        }

    };
    Runnable loadSearchResults = new Runnable() {
        @Override
        public void run() {
            service = new APIService(BaseActivity.this);
            service.getSearchResults(false, new CallBack(BaseActivity.this, "searchResults"));
        }
    };


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
            removeDialog();
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
    public void removeDialog() {
        dialog.dismiss();
    }


    public void showDialog() {

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");
        dialog.setMessage("Please wait...");

        dialog.show();
    }


    public File getOutputMediaFile() {
        File mediaStorageDir = new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "FFM");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp
                + ".jpg");
        return mediaFile;
    }


    public void callFragmentWithReplace(int containerId, Fragment fragment, String tag) {
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(containerId, fragment, tag)
                .setCustomAnimations(R.anim.slide_in_enter, R.anim.slide_in_exit,
                        R.anim.slide_pop_enter, R.anim.slide_pop_exit);
        if (tag != null)
            transaction.addToBackStack(tag)
                    .commit();
        else
            transaction
                    .commit();
    }

    public void callFragment(int containerId, Fragment fragment, String tag) {
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(containerId, fragment, tag)
                .setCustomAnimations(R.anim.slide_in_enter, R.anim.slide_in_exit,
                        R.anim.slide_pop_enter, R.anim.slide_pop_exit);
        if (tag != null)
            transaction.addToBackStack(tag)
                    .commit();
        else
            transaction
                    .commit();
    }


    public String Get24FormatTime(String date) {
        String a = date.replaceAll("\\D+", "");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(a));

        String delegate = "hh:mm";
        String time = (String) DateFormat.format(delegate, Calendar.getInstance().getTime());

        return (time);
    }

    public String GetDate(String date) {
        java.text.DateFormat df = new SimpleDateFormat("yy-d-M'T'hh:mm:ss");
        Date startDate;
        String newDateString = null;
        try {
            startDate = df.parse(date);
            newDateString = df.format(startDate);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        String[] date1 = newDateString.split("-");
        String[] date2 = date1[2].toString().split("T");
        return (date1[1] + "/" + date2[0] + "/" + date1[0] + " " + date2[1]);
    }

    public String Get12FormatTime1(Long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);

        String delegate = "hh:mm a";
        String time = (String) DateFormat.format(delegate, calendar.getTime());

        return (time);
    }

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public String DateMilli(String date) {
        String a = date.replaceAll("\\D+", "");
        return a;
    }

    public String getRealPathFromURI(Uri contentUri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


    public void hideKeyPad(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(),
                InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setLifecycleState(State.STARTED);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setLifecycleState(State.RESUMED);
    }

    @Override
    protected void onPause() {
        super.onPause();

        setLifecycleState(State.PAUSED);
    }


    @Override
    protected void onStop() {
        super.onStop();
        setLifecycleState(State.STOPPED);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        setLifecycleState(State.DESTROYED);
    }

    /**
     * @return the lifecycleState
     */
    public State getLifecycleState() {
        return lifecycleState;
    }

    /**
     * @param lifecycleState the lifecycleState to set
     */
    public void setLifecycleState(State lifecycleState) {
        this.lifecycleState = lifecycleState;
    }

    /**
     * The possibles states of an activity lifecycle.
     */
    public static enum State {
        CREATED, STARTED, RESUMED, PAUSED, STOPPED, DESTROYED;
    }

    ;
}
