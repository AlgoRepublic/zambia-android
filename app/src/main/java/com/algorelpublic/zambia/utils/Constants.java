package com.algorelpublic.zambia.utils;

/**
 * Created by Adil on 11/21/2016.
 */
public class Constants {

    //Strings
    public static int STATUS_OK = 200, INTERNET_FAILURE = 101;
    public static final String USER_ID = "USER_id";
    public static final String IS_LOGIN = "IS_LOGIN";
    public static String GENERAL_ERROR = "There was some error in server please try again later.";
    final public static String NAME = "Name";
    final public static String FNAME = "FName";
    final public static String LNAME = "LName";
    final public static String EMAIL = "Email";
    final public static String GCM_REGISTRATION_TOKEN = "GCM_REGISTRATION_TOKEN";
    final public static String USER_PASSWORD = "user_password";
    final public static String USER_TOKEN = "user_token";
    //stagging url
//    final public static String BASE_URL = "https://zambia-internal.herokuapp.com/api";
    //production url
    final public static String BASE_URL = "http://91.194.91.72:3000/api";
    final public static String DEVICE_TOKEN = "DeviceToken";
    public static final String IS_NEW_USER = "IS_NEW_USER";
    public static final String RESPONSE_GSON_ABOUT_US = "response_gson_about_us";
    public static final String RESPONSE_GSON_FAQS = "response_gson_faqs";
    public static final String RESPONSE_GSON_HELP_LINE = "response_gson_help_line";
    public static final String RESPONSE_GSON_GUIDELINE_LINE = "response_gson_guideline_line";
    public static final String RESPONSE_GSON_SEARCH_CRITERIAS = "response_gson_search_criterias";
    public static final String RESPONSE_GSON_SEARCH_RESULT = "response_gson_search_result";
    public static final String RESPONSE_GSON_MEDICINES_LINE = "response_gson_medicines_line";
    public static final String LOAD_FROM_MEMORY = "load_from_memory";
    public static final String PROGRESS_LOAD_APP = "progress_load_app";
    public static final String FAVOURITE_GSON = "favourite_gson";


    //URLS
    public static final String REGISTER_FCM_URL = BASE_URL + "/users/register_fcm_id";
    public static final String CATEGORY_URL = BASE_URL + "/categories";
    public static final String POST_URL = BASE_URL + "/posts";
    public static final String FEEDBACK_URL = BASE_URL + "/feedback/create";
    public static final String HELPLINE_URL = BASE_URL + "/helplines";
    public static final String FETCH_GUIDELINE_URL = BASE_URL + "/posts/fetch_guidelines";
    public static final String FETCH_MEDICINES_URL = BASE_URL + "/posts/fetch_medicines";
    public static final String FETCH_ABOUT_US_URL = BASE_URL + "/posts/fetch_about_us";
    public static final String FETCH_FAQS_URL = BASE_URL + "/posts/fetch_faq";
    public static final String FETCH_SEARCH_RESULT_URL = BASE_URL + "/searches/get_search_results";
    public static final String FETCH_SEARCH_CRITERIAS_URL = BASE_URL + "/searches/get_search_criterias";

    //
    public static final int MEDICINE = 1;
    public static final int GUIDELINE = 2;
    public static final int STAGE_SERVICE = 3;
    public static final int STAGE_SUB_CAT1 = 4;
    public static final int STAGE_SUB_CAT2 = 5;
    public static final int STAGE_SUB_CAT3 = 6;
    public static final int STAGE_SUB_CAT4 = 7;
    public static final int STAGE_SUB_CAT5 = 8;
    public static final int STAGE_SUB_CAT6 = 9;
    public static final int STAGE_SUB_CAT7 = 10;
    public static final int STAGE_SUB_CAT8 = 11;
    public static final int STAGE_SUB_CAT9 = 12;

    public static final int STAGE_SERVICE_PERSON_2 = 13;
    public static final int STAGE_SUB_CAT1_PERSON_2 = 14;
    public static final int STAGE_SUB_CAT2_PERSON_2 = 15;
    public static final int STAGE_SUB_CAT3_PERSON_2 = 16;
    public static final int STAGE_SERVICE_PERSON_3 = 17;
    public static final int STAGE_SUB_CAT1_PERSON_3 = 18;
    public static final int STAGE_SUB_CAT2_PERSON_3 = 19;
    public static final int STAGE_SUB_CAT3_PERSON_3 = 20;


    public static final int STAGE_PERSON_1 = 213;
    public static final int STAGE_PERSON_2 = 214;
    public static final int STAGE_PERSON_3 = 215;
    public static final int STAGE_PERSON_4 = 214;
    public static final int STAGE_PERSON_5 = 215;


}
