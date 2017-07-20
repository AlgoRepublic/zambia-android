package com.algorelpublic.zambia.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.algorelpublic.zambia.R;
import com.algorelpublic.zambia.Zambia;
import com.algorelpublic.zambia.adapter.ServiceSpinnerAdapter;
import com.algorelpublic.zambia.model.SearchCriteriaModel;
import com.algorelpublic.zambia.utils.Constants;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by adil nazir on 12/07/2017.
 */

public class AdvanceSearchDetailFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    public static AdvanceSearchDetailFragment instance;
    private static String noOfPersons;
    private View view;
    private SearchCriteriaModel searchCriteriaModel;
    ArrayList<SearchCriteriaModel.Criteria> servicesList, subCatList, subCat2List, subCat3List,
            servicesListPerson2, subCatListPerson2, subCat2ListPerson2, subCat3ListPerson2,
            servicesListPerson3, subCatListPerson3, subCat2ListPerson3, subCat3ListPerson3;
    private ImageView ivPerson1, ivPerson2, ivPerson3, ivPerson4, ivPerson5;
    private Button btnNext, btnPrev, btnSearch;
    private Button btnPerson1, btnPerson2, btnPerson3, btnPerson4, btnPerson5;
    private Spinner spServices, spSubCat1, spSubCat2, spSubCat3,
            spServicesPerson2, spSubCat1Person2, spSubCat2Person2, spSubCat3Person2,
            spServicesPerson3, spSubCat1Person3, spSubCat2Person3, spSubCat3Person3;
    private ServiceSpinnerAdapter serviceAdapter;
    private String serviceParentId, subCatParentId, subCatParentId2, subCatParentId3;
    private RelativeLayout rlServices, rlSubCat1, rlSubCat2, rlSubCat3,
            rlServicesPerson2, rlSubCat1Person2, rlSubCat2Person2, rlSubCat3Person2,
            rlServicesPerson3, rlSubCat1Person3, rlSubCat2Person3, rlSubCat3Person3;
    private int count = 3;
    private TextView tvServices, tvSubCat1, tvSubCat2, tvSubCat3,
            tvServicesPerson2, tvSubCat1Person2, tvSubCat2Person2, tvSubCat3Person2,
            tvServicesPerson3, tvSubCat1Person3, tvSubCat2Person3, tvSubCat3Person3;
    private LinearLayout llPerson1, llPerson2, llPerson3, llPerson4, llPerson5;
    private int perviousTabCount, perviousTabCountPerson2, perviousTabCountPerson3, perviousTabCountPerson4, perviousTabCountPerson5;
    private ArrayList<ArrayList<String>> queryList = new ArrayList<>();
    private ArrayList<String> person1List = new ArrayList<>();
    private ArrayList<String> person2List = new ArrayList<>();
    private ArrayList<String> person3List = new ArrayList<>();

    public static AdvanceSearchDetailFragment newInstance(String persons) {
        noOfPersons = persons;
        instance = new AdvanceSearchDetailFragment();
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmet_advance_search_detail, container, false);
        init();
        addListener();
        setPersonTabs();
        Gson gson = new Gson();
        searchCriteriaModel = gson.fromJson(Zambia.db.getString(Constants.RESPONSE_GSON_SEARCH_CRITERIAS), SearchCriteriaModel.class);
        if (searchCriteriaModel != null)
            setValues();

        return view;
    }

    /**
     * set tabs
     */
    private void setPersonTabs() {
        switch (noOfPersons) {
            case "1":
                btnPerson1.setVisibility(View.VISIBLE);
                btnPerson2.setVisibility(View.GONE);
                btnPerson3.setVisibility(View.GONE);
                btnPerson4.setVisibility(View.GONE);
                btnPerson5.setVisibility(View.GONE);
                break;
            case "2":
                btnPerson1.setVisibility(View.VISIBLE);
                btnPerson2.setVisibility(View.VISIBLE);
                btnPerson3.setVisibility(View.GONE);
                btnPerson4.setVisibility(View.GONE);
                btnPerson5.setVisibility(View.GONE);
                break;
            case "3":
                btnPerson1.setVisibility(View.VISIBLE);
                btnPerson2.setVisibility(View.VISIBLE);
                btnPerson3.setVisibility(View.VISIBLE);
                btnPerson4.setVisibility(View.GONE);
                btnPerson5.setVisibility(View.GONE);
                break;
            case "4":
                btnPerson1.setVisibility(View.VISIBLE);
                btnPerson2.setVisibility(View.VISIBLE);
                btnPerson3.setVisibility(View.VISIBLE);
                btnPerson4.setVisibility(View.VISIBLE);
                btnPerson5.setVisibility(View.GONE);
                break;
            case "5":
                btnPerson1.setVisibility(View.VISIBLE);
                btnPerson2.setVisibility(View.VISIBLE);
                btnPerson3.setVisibility(View.VISIBLE);
                btnPerson4.setVisibility(View.VISIBLE);
                btnPerson5.setVisibility(View.VISIBLE);
                break;
        }

    }


    private void setValues() {
        if (searchCriteriaModel != null && searchCriteriaModel.criteriaes.size() > 0) {
            servicesList = new ArrayList<>();
            for (int i = 0; i < searchCriteriaModel.criteriaes.size(); i++) {
                if (searchCriteriaModel.criteriaes.get(i).parentId == null || searchCriteriaModel.criteriaes.get(i).parentId.equalsIgnoreCase("")) {
                    servicesList.add(searchCriteriaModel.criteriaes.get(i));
                }
            }
            if (servicesList != null && servicesList.size() > 0)
                setServiceSpinner();
        }
    }

    private void setServicesPerson2() {
        if (searchCriteriaModel != null && searchCriteriaModel.criteriaes.size() > 0) {
            servicesListPerson2 = new ArrayList<>();
            for (int i = 0; i < searchCriteriaModel.criteriaes.size(); i++) {
                if (searchCriteriaModel.criteriaes.get(i).parentId == null || searchCriteriaModel.criteriaes.get(i).parentId.equalsIgnoreCase("")) {
                    servicesListPerson2.add(searchCriteriaModel.criteriaes.get(i));
                }
            }
            if (servicesListPerson2 != null && servicesListPerson2.size() > 0)
                setServiceSpinnerPerson2();

        }
    }

    private void setServicesPerson3() {
        if (searchCriteriaModel != null && searchCriteriaModel.criteriaes.size() > 0) {
            servicesList = new ArrayList<>();
            for (int i = 0; i < searchCriteriaModel.criteriaes.size(); i++) {
                if (searchCriteriaModel.criteriaes.get(i).parentId == null || searchCriteriaModel.criteriaes.get(i).parentId.equalsIgnoreCase("")) {
                    servicesList.add(searchCriteriaModel.criteriaes.get(i));
                }
            }
            if (servicesList != null && servicesList.size() > 0)
                setServiceSpinnerPerson3();

        }
    }


    private void setServiceSpinner() {
        String upperString = servicesList.get(0).questionText.substring(0, 1).toUpperCase() + servicesList.get(0).questionText.substring(1);
        tvServices.setText(Html.fromHtml(upperString));
        tvServices.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        serviceAdapter = new ServiceSpinnerAdapter(getActivity(), R.layout.service_item, servicesList);
        spServices.setAdapter(serviceAdapter);
        spServices.setOnItemSelectedListener(this);
    }

    private void setSubCatSpinner() {
        String upperString = subCatList.get(0).questionText.substring(0, 1).toUpperCase() + subCatList.get(0).questionText.substring(1);
        tvSubCat1.setText(Html.fromHtml(upperString));
        tvSubCat1.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        serviceAdapter = new ServiceSpinnerAdapter(getActivity(), R.layout.service_item, subCatList);
        spSubCat1.setAdapter(serviceAdapter);
        spSubCat1.setOnItemSelectedListener(this);
    }

    private void setSubCat2Spinner() {
        String upperString = subCat2List.get(0).questionText.substring(0, 1).toUpperCase() + subCat2List.get(0).questionText.substring(1);
        tvSubCat2.setText(Html.fromHtml(upperString));
        tvSubCat2.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        serviceAdapter = new ServiceSpinnerAdapter(getActivity(), R.layout.service_item, subCat2List);
        spSubCat2.setAdapter(serviceAdapter);
        spSubCat2.setOnItemSelectedListener(this);
    }

    private void setSubCat3Spinner() {
        String upperString = subCat3List.get(0).questionText.substring(0, 1).toUpperCase() + subCat3List.get(0).questionText.substring(1);
        tvSubCat3.setText(Html.fromHtml(upperString));
        tvSubCat3.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        serviceAdapter = new ServiceSpinnerAdapter(getActivity(), R.layout.service_item, subCat3List);
        spSubCat3.setAdapter(serviceAdapter);
        spSubCat3.setOnItemSelectedListener(this);
    }

    private void setServiceSpinnerPerson2() {
        String upperString = servicesList.get(0).questionText.substring(0, 1).toUpperCase() + servicesList.get(0).questionText.substring(1);
        tvServicesPerson2.setText(Html.fromHtml(upperString));
        tvServicesPerson2.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        serviceAdapter = new ServiceSpinnerAdapter(getActivity(), R.layout.service_item, servicesList);
        spServicesPerson2.setAdapter(serviceAdapter);
        spServicesPerson2.setOnItemSelectedListener(this);
    }

    private void setSubCatSpinnerPerson2() {
        String upperString = subCatList.get(0).questionText.substring(0, 1).toUpperCase() + subCatList.get(0).questionText.substring(1);
        tvSubCat1Person2.setText(Html.fromHtml(upperString));
        tvSubCat1Person2.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        serviceAdapter = new ServiceSpinnerAdapter(getActivity(), R.layout.service_item, subCatList);
        spSubCat1Person2.setAdapter(serviceAdapter);
        spSubCat1Person2.setOnItemSelectedListener(this);
    }

    private void setSubCat2SpinnerPerson2() {
        String upperString = subCat2List.get(0).questionText.substring(0, 1).toUpperCase() + subCat2List.get(0).questionText.substring(1);
        tvSubCat2Person2.setText(Html.fromHtml(upperString));
        tvSubCat2Person2.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        serviceAdapter = new ServiceSpinnerAdapter(getActivity(), R.layout.service_item, subCat2List);
        spSubCat2Person2.setAdapter(serviceAdapter);
        spSubCat2Person2.setOnItemSelectedListener(this);
    }

    private void setSubCat3SpinnerPerson2() {
        String upperString = subCat3List.get(0).questionText.substring(0, 1).toUpperCase() + subCat3List.get(0).questionText.substring(1);
        tvSubCat3Person2.setText(Html.fromHtml(upperString));
        tvSubCat3Person2.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        serviceAdapter = new ServiceSpinnerAdapter(getActivity(), R.layout.service_item, subCat3List);
        spSubCat3Person2.setAdapter(serviceAdapter);
        spSubCat3Person2.setOnItemSelectedListener(this);
    }

    private void setServiceSpinnerPerson3() {
        String upperString = subCat3List.get(0).questionText.substring(0, 1).toUpperCase() + subCat3List.get(0).questionText.substring(1);
        tvServicesPerson3.setText(Html.fromHtml(upperString));
        tvServicesPerson3.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        serviceAdapter = new ServiceSpinnerAdapter(getActivity(), R.layout.service_item, servicesList);
        spServicesPerson3.setAdapter(serviceAdapter);
        spServicesPerson3.setOnItemSelectedListener(this);
    }

    private void setSubCatSpinnerPerson3() {
        String upperString = subCatList.get(0).questionText.substring(0, 1).toUpperCase() + subCatList.get(0).questionText.substring(1);
        tvSubCat1Person3.setText(Html.fromHtml(upperString));
        tvSubCat1Person3.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        serviceAdapter = new ServiceSpinnerAdapter(getActivity(), R.layout.service_item, subCatList);
        spSubCat1Person3.setAdapter(serviceAdapter);
        spSubCat1Person3.setOnItemSelectedListener(this);
    }

    private void setSubCat2SpinnerPerson3() {
        String upperString = subCat2List.get(0).questionText.substring(0, 1).toUpperCase() + subCat2List.get(0).questionText.substring(1);
        tvSubCat2Person3.setText(Html.fromHtml(upperString));
        tvSubCat2Person3.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        serviceAdapter = new ServiceSpinnerAdapter(getActivity(), R.layout.service_item, subCat2List);
        spSubCat2Person3.setAdapter(serviceAdapter);
        spSubCat2Person3.setOnItemSelectedListener(this);
    }

    private void setSubCat3SpinnerPerson3() {
        String upperString = subCat3List.get(0).questionText.substring(0, 1).toUpperCase() + subCat3List.get(0).questionText.substring(1);
        tvSubCat3Person3.setText(Html.fromHtml(upperString));
        tvSubCat3Person3.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        serviceAdapter = new ServiceSpinnerAdapter(getActivity(), R.layout.service_item, subCat3List);
        spSubCat3Person3.setAdapter(serviceAdapter);
        spSubCat3Person3.setOnItemSelectedListener(this);
    }


    private void init() {
        //set tabs buttons
        btnPerson1 = (Button) view.findViewById(R.id.btnPerson1);
        btnPerson2 = (Button) view.findViewById(R.id.btnPerson2);
        btnPerson3 = (Button) view.findViewById(R.id.btnPerson3);
        btnPerson4 = (Button) view.findViewById(R.id.btnPerson4);
        btnPerson5 = (Button) view.findViewById(R.id.btnPerson5);

        //set spinners
        spServices = (Spinner) view.findViewById(R.id.spServices);
        spSubCat1 = (Spinner) view.findViewById(R.id.spSubCat1);
        spSubCat2 = (Spinner) view.findViewById(R.id.spSubCat2);
        spSubCat3 = (Spinner) view.findViewById(R.id.spSubCat3);
        spServicesPerson2 = (Spinner) view.findViewById(R.id.spServicesPerson2);
        spSubCat1Person2 = (Spinner) view.findViewById(R.id.spSubCat1Person2);
        spSubCat2Person2 = (Spinner) view.findViewById(R.id.spSubCat2Person2);
        spSubCat3Person2 = (Spinner) view.findViewById(R.id.spSubCat3Person2);
        spServicesPerson3 = (Spinner) view.findViewById(R.id.spServicesPerson3);
        spSubCat1Person3 = (Spinner) view.findViewById(R.id.spSubCat1Person3);
        spSubCat2Person3 = (Spinner) view.findViewById(R.id.spSubCat2Person3);
        spSubCat3Person3 = (Spinner) view.findViewById(R.id.spSubCat3Person3);

        //set Tabs layouts
        rlServices = (RelativeLayout) view.findViewById(R.id.rlServices);
        rlSubCat1 = (RelativeLayout) view.findViewById(R.id.rlSubCat1);
        rlSubCat2 = (RelativeLayout) view.findViewById(R.id.rlSubCat2);
        rlSubCat3 = (RelativeLayout) view.findViewById(R.id.rlSubCat3);
        rlServicesPerson2 = (RelativeLayout) view.findViewById(R.id.rlServicesPerson2);
        rlSubCat1Person2 = (RelativeLayout) view.findViewById(R.id.rlSubCat1Person2);
        rlSubCat2Person2 = (RelativeLayout) view.findViewById(R.id.rlSubCat2Person2);
        rlSubCat3Person2 = (RelativeLayout) view.findViewById(R.id.rlSubCat3Person2);
        rlServicesPerson3 = (RelativeLayout) view.findViewById(R.id.rlServicesPerson3);
        rlSubCat1Person3 = (RelativeLayout) view.findViewById(R.id.rlSubCat1Person3);
        rlSubCat2Person3 = (RelativeLayout) view.findViewById(R.id.rlSubCat2Person3);
        rlSubCat3Person3 = (RelativeLayout) view.findViewById(R.id.rlSubCat3Person3);

        tvServices = (TextView) view.findViewById(R.id.tvServices);
        tvSubCat1 = (TextView) view.findViewById(R.id.tvSubCat1);
        tvSubCat2 = (TextView) view.findViewById(R.id.tvSubCat2);
        tvSubCat3 = (TextView) view.findViewById(R.id.tvSubCat3);
        tvServicesPerson2 = (TextView) view.findViewById(R.id.tvServicesPerson2);
        tvSubCat1Person2 = (TextView) view.findViewById(R.id.tvSubCat1Person2);
        tvSubCat2Person2 = (TextView) view.findViewById(R.id.tvSubCat2Person2);
        tvSubCat3Person2 = (TextView) view.findViewById(R.id.tvSubCat3Person2);
        tvServicesPerson3 = (TextView) view.findViewById(R.id.tvServicesPerson3);
        tvSubCat1Person3 = (TextView) view.findViewById(R.id.tvSubCat1Person3);
        tvSubCat2Person3 = (TextView) view.findViewById(R.id.tvSubCat2Person3);
        tvSubCat3Person3 = (TextView) view.findViewById(R.id.tvSubCat3Person3);

        llPerson1 = (LinearLayout) view.findViewById(R.id.llPerson1);
        llPerson2 = (LinearLayout) view.findViewById(R.id.llPerson2);
        llPerson3 = (LinearLayout) view.findViewById(R.id.llPerson3);

        btnPrev = (Button) view.findViewById(R.id.btnPrev);
        btnNext = (Button) view.findViewById(R.id.btnNext);
        btnSearch = (Button) view.findViewById(R.id.btnSearch);
    }

    private void addListener() {
        btnPrev.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNext:
                btnNextPress(v);
                break;
            case R.id.btnPrev:
                btnPrevPress(v);
                break;
            case R.id.btnSearch:
                searchClick();
//                getActivity().getSupportFragmentManager().popBackStackImmediate();
                break;
        }

    }

    private void searchClick() {
        switch (noOfPersons) {
            case "1":
                if (queryList.size() > 0)
                    queryList.clear();
                queryList.add(person1List);
                callFragment(R.id.container, SearchResultFragment.newInstance(queryList), "");
                break;
            case "2":
                if (queryList.size() > 0)
                    queryList.clear();
                queryList.add(person1List);
                queryList.add(person2List);
                callFragment(R.id.container, SearchResultFragment.newInstance(queryList), "");
                break;
            case "3":
                if (queryList.size() > 0)
                    queryList.clear();
                queryList.add(person1List);
                queryList.add(person2List);
                queryList.add(person3List);
                callFragment(R.id.container, SearchResultFragment.newInstance(queryList), "");
                break;
            case "4":

                break;
            case "5":

                break;
        }
    }

    private void btnNextPress(View v) {
        switch (count) {
            case Constants.STAGE_SERVICE:
                if (subCatList != null && subCatList.size() > 0) {
                    hideServiceLayout();
                    showSubCat();
                    count = Constants.STAGE_SUB_CAT1;
                } else {
                    gotoSecondTab();
                    count = Constants.STAGE_SERVICE_PERSON_2;
                    perviousTabCount = Constants.STAGE_SERVICE;
                }
                btnPrev.setVisibility(View.VISIBLE);
                break;
            case Constants.STAGE_SUB_CAT1:
                if (subCat2List != null && subCat2List.size() > 0) {
                    count = Constants.STAGE_SUB_CAT2;
                    hideSubCat();
                    showSubCat2();
                } else {
                    gotoSecondTab();
                    count = Constants.STAGE_SERVICE_PERSON_2;
                    perviousTabCount = Constants.STAGE_SUB_CAT1;
                }
                break;
            case Constants.STAGE_SUB_CAT2:
                if (subCat3List != null && subCat3List.size() > 0) {
                    hideSubCat2();
                    showSubCat3();
                    count = Constants.STAGE_PERSON_2;
                } else {
                    gotoSecondTab();
                    count = Constants.STAGE_SERVICE_PERSON_2;
                    perviousTabCount = Constants.STAGE_SUB_CAT2;
                }
                break;
            case Constants.STAGE_PERSON_2:
                gotoSecondTab();
                count = Constants.STAGE_SERVICE_PERSON_2;
                perviousTabCount = Constants.STAGE_PERSON_2;
                break;


            case Constants.STAGE_SERVICE_PERSON_2:
                if (subCatList != null && subCatList.size() > 0) {
                    hideServiceLayoutPerson2();
                    showSubCatPerson2();
                    count = Constants.STAGE_SUB_CAT1_PERSON_2;
                } else {
                    gotoThirdTab();
                    count = Constants.STAGE_SERVICE_PERSON_3;
                    perviousTabCountPerson2 = Constants.STAGE_SERVICE_PERSON_2;
                }
                break;
            case Constants.STAGE_SUB_CAT1_PERSON_2:
                if (subCat2List != null && subCat2List.size() > 0) {
                    hideSubCatPerson2();
                    showSubCat2Person2();
                    count = Constants.STAGE_SUB_CAT2_PERSON_2;
                } else {
                    gotoSecondTab();
                    count = Constants.STAGE_SERVICE_PERSON_3;
                    perviousTabCountPerson2 = Constants.STAGE_SUB_CAT1_PERSON_2;
                }
                break;
            case Constants.STAGE_SUB_CAT2_PERSON_2:
                if (subCat3List != null && subCat3List.size() > 0) {
                    hideSubCat2Person2();
                    showSubCat3Person2();
                    count = Constants.STAGE_PERSON_3;
                } else {
                    gotoThirdTab();
                    count = Constants.STAGE_SERVICE_PERSON_3;
                    perviousTabCountPerson2 = Constants.STAGE_SUB_CAT2_PERSON_2;
                }
                break;
            case Constants.STAGE_PERSON_3:
                gotoThirdTab();
                count = Constants.STAGE_SERVICE_PERSON_3;
                perviousTabCountPerson2 = Constants.STAGE_PERSON_3;
                break;


            case Constants.STAGE_SERVICE_PERSON_3:
                if (subCatList != null && subCatList.size() > 0) {
                    hideServiceLayoutPerson3();
                    showSubCatPerson3();
                    count = Constants.STAGE_SUB_CAT1_PERSON_3;
                } else {
//                    gotoSecondTab();
                    count = Constants.STAGE_SERVICE_PERSON_3;
                    perviousTabCountPerson3 = Constants.STAGE_SERVICE;
                }
                break;
            case Constants.STAGE_SUB_CAT1_PERSON_3:
                if (subCat2List != null && subCat2List.size() > 0) {
                    hideSubCatPerson3();
                    showSubCat2Person3();
                    count = Constants.STAGE_SUB_CAT2_PERSON_3;
                } else {
//                    gotoSecondTab();
                    count = Constants.STAGE_SERVICE_PERSON_3;
                    perviousTabCountPerson3 = Constants.STAGE_SERVICE;
                }
                break;
            case Constants.STAGE_SUB_CAT2_PERSON_3:
                if (subCat3List != null && subCat3List.size() > 0) {
                    hideSubCat2Person3();
                    showSubCat3Person3();
                    count = Constants.STAGE_SUB_CAT3_PERSON_3;
                } else {
//                    gotoSecondTab();
                    count = Constants.STAGE_SERVICE_PERSON_3;
                    perviousTabCountPerson3 = Constants.STAGE_SERVICE;
                }
                break;


        }
    }

    private void btnPrevPress(View v) {
        switch (count) {
            case Constants.STAGE_SERVICE:
                hideSubCat();
                showServiceLayout();
                btnPrev.setVisibility(View.GONE);
                break;
            case Constants.STAGE_SUB_CAT1:
                hideSubCat2();
                showSubCat();
                count = Constants.STAGE_SERVICE;
                break;
            case Constants.STAGE_SUB_CAT2:
                hideSubCat3();
                showSubCat2();
                count = Constants.STAGE_SUB_CAT1;
                break;
            case Constants.STAGE_PERSON_2:
                gotoFirstTab();
                count = perviousTabCount;
                break;
            case Constants.STAGE_SERVICE_PERSON_2:
                gotoFirstTab();
                count = perviousTabCount;
                break;
            case Constants.STAGE_SUB_CAT1_PERSON_2:
                hideSubCat2Person2();
                showSubCatPerson2();
                count = Constants.STAGE_SERVICE_PERSON_2;
                break;
            case Constants.STAGE_SUB_CAT2_PERSON_2:
                hideSubCat3Person2();
                showSubCat2Person2();
                count = Constants.STAGE_SUB_CAT1_PERSON_2;
                break;
            case Constants.STAGE_PERSON_3:
                gotoPervSecondTab();
                count = perviousTabCountPerson2;
                break;
            case Constants.STAGE_SERVICE_PERSON_3:
                gotoPervSecondTab();
                count = perviousTabCountPerson2;
                break;
            case Constants.STAGE_SUB_CAT1_PERSON_3:
                hideSubCat2Person3();
                showSubCatPerson3();
                count = Constants.STAGE_SERVICE_PERSON_3;
                break;
            case Constants.STAGE_SUB_CAT2_PERSON_3:
                hideSubCat3Person3();
                showSubCat2Person3();
                count = Constants.STAGE_SUB_CAT1_PERSON_3;
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Spinner spinner = (Spinner) adapterView;

        switch (spinner.getId()) {
            case R.id.spServices:
                serviceParentId = servicesList.get(i).id;
                if (person1List.size() > 0)
                    person1List.set(0, serviceParentId);
                else
                    person1List.add(0, serviceParentId);
                Log.d("Id", "id ====>>" + servicesList.get(i).id + "name ====>> " + servicesList.get(i).title + "position ===>>" + i);
                setSubCat1Spinner(serviceParentId);
                break;

            case R.id.spSubCat1:
                subCatParentId = subCatList.get(i).id;
                if (person1List.size() > 1)
                    person1List.set(1, subCatParentId);
                else
                    person1List.add(1, subCatParentId);
                Log.d("Id", "id ====>>" + subCatList.get(i).id + "name ====>> " + subCatList.get(i).title + "position ===>>" + i);
                setSubCat2Spinner(subCatParentId);
                break;

            case R.id.spSubCat2:
                subCatParentId2 = subCat2List.get(i).id;
                if (person1List.size() > 2)
                    person1List.set(2, subCatParentId2);
                else
                    person1List.add(2, subCatParentId2);
                Log.d("Id", "id ====>>" + subCat2List.get(i).id + "name ====>> " + subCat2List.get(i).title + "position ===>>" + i);
                setSubCat3Spinner(subCatParentId2);
                break;

            case R.id.spSubCat3:
                subCatParentId3 = subCat3List.get(i).id;
                Log.d("Id", "id ====>>" + subCat3List.get(i).id + "name ====>> " + subCat3List.get(i).title + "position ===>>" + i);
//                setSubCat3Spinner(serviceParentId);
                if (person1List.size() > 3)
                    person1List.set(3, subCatParentId3);
                else
                    person1List.add(3, subCatParentId3);
                if (Integer.parseInt(noOfPersons) > 1) {
                    btnNext.setVisibility(View.VISIBLE);
                    btnSearch.setVisibility(View.GONE);
                }
//                    gotoSecondTab();
                else {
                    btnNext.setVisibility(View.GONE);
                    btnSearch.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.spServicesPerson2:
                Log.d("Id", "id ====>>" + servicesList.get(i).id + "name ====>> " + servicesList.get(i).title + "position ===>>" + i);
                setSubCat1SpinnerPerson2(servicesList.get(i).id);
                if (person2List.size() > 0)
                    person2List.set(0, servicesList.get(i).id);
                else
                    person2List.add(0, servicesList.get(i).id);

                break;

            case R.id.spSubCat1Person2:
                Log.d("Id", "id ====>>" + subCatList.get(i).id + "name ====>> " + subCatList.get(i).title + "position ===>>" + i);
                setSubCat2SpinnerPerson2(subCatList.get(i).id);
                if (person2List.size() > 1)
                    person2List.set(1, subCatList.get(i).id);
                else
                    person2List.add(1, subCatList.get(i).id);

                break;

            case R.id.spSubCat2Person2:
                Log.d("Id", "id ====>>" + subCat2List.get(i).id + "name ====>> " + subCat2List.get(i).title + "position ===>>" + i);
                setSubCat3SpinnerPerson2(subCat2List.get(i).id);
                if (person2List.size() > 2)
                    person2List.set(2, subCat2List.get(i).id);
                else
                    person2List.add(2, subCat2List.get(i).id);
                break;

            case R.id.spSubCat3Person2:
                Log.d("Id", "id ====>>" + subCat3List.get(i).id + "name ====>> " + subCat3List.get(i).title + "position ===>>" + i);
                if (person2List.size() > 3)
                    person2List.set(3, subCat3List.get(i).id);
                else
                    person2List.add(3, subCat3List.get(i).id);
//                setSubCat3Spinner(serviceParentId);
                if (Integer.parseInt(noOfPersons) > 2) {
                    btnNext.setVisibility(View.VISIBLE);
                    btnSearch.setVisibility(View.GONE);
                } else {
                    btnNext.setVisibility(View.GONE);
                    btnSearch.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.spServicesPerson3:
                Log.d("Id", "id ====>>" + servicesList.get(i).id + "name ====>> " + servicesList.get(i).title + "position ===>>" + i);
                setSubCat1SpinnerPerson3(servicesList.get(i).id);
                if (person3List.size() > 0)
                    person3List.set(0, servicesList.get(i).id);
                else
                    person3List.add(0, servicesList.get(i).id);

                break;

            case R.id.spSubCat1Person3:
                Log.d("Id", "id ====>>" + subCatList.get(i).id + "name ====>> " + subCatList.get(i).title + "position ===>>" + i);
                setSubCat2SpinnerPerson3(subCatList.get(i).id);
                if (person3List.size() > 1)
                    person3List.set(1, subCatList.get(i).id);
                else
                    person3List.add(1, subCatList.get(i).id);
                break;
            case R.id.spSubCat2Person3:
                Log.d("Id", "id ====>>" + subCat2List.get(i).id + "name ====>> " + subCat2List.get(i).title + "position ===>>" + i);
                setSubCat3SpinnerPerson3(subCat2List.get(i).id);
                if (person3List.size() > 2)
                    person3List.set(2, subCat2List.get(i).id);
                else
                    person3List.add(2, subCat2List.get(i).id);
                break;

            case R.id.spSubCat3Person3:
                Log.d("Id", "id ====>>" + subCat3List.get(i).id + "name ====>> " + subCat3List.get(i).title + "position ===>>" + i);
                if (person3List.size() > 3)
                    person3List.set(3, subCat3List.get(i).id);
                else
                    person3List.add(3, subCat3List.get(i).id);

//                setSubCat3Spinner(serviceParentId);
                if (Integer.parseInt(noOfPersons) > 3) {
                    btnNext.setVisibility(View.VISIBLE);
                    btnSearch.setVisibility(View.GONE);
                } else {
                    btnNext.setVisibility(View.GONE);
                    btnSearch.setVisibility(View.VISIBLE);
                }
                break;
        }

    }


    private void gotoSecondTab() {
//        hidePerson1();
        llPerson1.setVisibility(View.GONE);
        showPerson2();
        setServicesPerson2();
        btnPerson2.setBackgroundResource(R.drawable.cicle_bg_selected);
        btnPerson1.setBackgroundResource(R.drawable.circle_bg);

        //        btnPerson1.set

    }

    private void gotoThirdTab() {
//        hidePerson1();
        llPerson2.setVisibility(View.GONE);
        showPerson3();
        setServicesPerson3();
        btnPerson3.setBackgroundResource(R.drawable.cicle_bg_selected);
        btnPerson2.setBackgroundResource(R.drawable.circle_bg);

        //        btnPerson1.set

    }

    private void gotoFourthTab() {
//        hidePerson1();
        llPerson2.setVisibility(View.GONE);
        showPerson3();
        setServicesPerson3();
        btnPerson3.setBackgroundResource(R.drawable.cicle_bg_selected);
        btnPerson2.setBackgroundResource(R.drawable.circle_bg);

        //        btnPerson1.set

    }

    private void gotoFirstTab() {
        showPerson1();
        llPerson2.setVisibility(View.GONE);
//        hidePerson2();
//        setServicesPerson2();
        btnNext.setVisibility(View.VISIBLE);
        btnSearch.setVisibility(View.GONE);
        btnPerson1.setBackgroundResource(R.drawable.cicle_bg_selected);
        btnPerson2.setBackgroundResource(R.drawable.circle_bg);

        //        btnPerson1.set

    }

    private void gotoPervSecondTab() {
        showPrePerson2();
        llPerson3.setVisibility(View.GONE);
//        hidePerson2();
//        setServicesPerson2();
        btnNext.setVisibility(View.VISIBLE);
        btnSearch.setVisibility(View.GONE);
        btnPerson2.setBackgroundResource(R.drawable.cicle_bg_selected);
        btnPerson3.setBackgroundResource(R.drawable.circle_bg);

        //        btnPerson1.set

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void setSubCat1Spinner(String parentId) {

        if (searchCriteriaModel != null && searchCriteriaModel.criteriaes.size() > 0) {
            subCatList = new ArrayList<>();
            for (int i = 0; i < searchCriteriaModel.criteriaes.size(); i++) {
                if (searchCriteriaModel.criteriaes.get(i).parentId != null && searchCriteriaModel.criteriaes.get(i).parentId.equalsIgnoreCase(parentId)) {
                    subCatList.add(searchCriteriaModel.criteriaes.get(i));
                }
            }
            if (subCatList != null && subCatList.size() > 0) {
                setSubCatSpinner();
                btnNext.setVisibility(View.VISIBLE);
                btnSearch.setVisibility(View.GONE);
            } else {
                if (Integer.parseInt(noOfPersons) > 1) {
                    btnNext.setVisibility(View.VISIBLE);
                    btnSearch.setVisibility(View.GONE);
                } else {
                    btnNext.setVisibility(View.GONE);
                    btnSearch.setVisibility(View.VISIBLE);
                }
            }

        }

    }

    private void setSubCat2Spinner(String parentId) {

        if (searchCriteriaModel != null && searchCriteriaModel.criteriaes.size() > 0) {
            subCat2List = new ArrayList<>();
            for (int i = 0; i < searchCriteriaModel.criteriaes.size(); i++) {
                if (searchCriteriaModel.criteriaes.get(i).parentId != null && searchCriteriaModel.criteriaes.get(i).parentId.equalsIgnoreCase(parentId)) {
                    subCat2List.add(searchCriteriaModel.criteriaes.get(i));
                }
            }
            if (subCat2List != null && subCat2List.size() > 0) {
                setSubCat2Spinner();
                btnNext.setVisibility(View.VISIBLE);
                btnSearch.setVisibility(View.GONE);
            } else {
                if (Integer.parseInt(noOfPersons) > 1) {
                    btnNext.setVisibility(View.VISIBLE);
                    btnSearch.setVisibility(View.GONE);
                } else {
                    btnNext.setVisibility(View.GONE);
                    btnSearch.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    private void setSubCat3Spinner(String parentId) {

        if (searchCriteriaModel != null && searchCriteriaModel.criteriaes.size() > 0) {
            subCat3List = new ArrayList<>();
            for (int i = 0; i < searchCriteriaModel.criteriaes.size(); i++) {
                if (searchCriteriaModel.criteriaes.get(i).parentId != null && searchCriteriaModel.criteriaes.get(i).parentId.equalsIgnoreCase(parentId)) {
                    subCat3List.add(searchCriteriaModel.criteriaes.get(i));
                }
            }
            if (subCat3List != null && subCat3List.size() > 0) {
                setSubCat3Spinner();
                btnNext.setVisibility(View.VISIBLE);
                btnSearch.setVisibility(View.GONE);
            } else {
                if (Integer.parseInt(noOfPersons) > 1) {
                    btnNext.setVisibility(View.VISIBLE);
                    btnSearch.setVisibility(View.GONE);
                } else {
                    btnNext.setVisibility(View.GONE);
                    btnSearch.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    private void setSubCat1SpinnerPerson2(String parentId) {

        if (searchCriteriaModel != null && searchCriteriaModel.criteriaes.size() > 0) {
            subCatList = new ArrayList<>();
            for (int i = 0; i < searchCriteriaModel.criteriaes.size(); i++) {
                if (searchCriteriaModel.criteriaes.get(i).parentId != null && searchCriteriaModel.criteriaes.get(i).parentId.equalsIgnoreCase(parentId)) {
                    subCatList.add(searchCriteriaModel.criteriaes.get(i));
                }
            }
            if (subCatList != null && subCatList.size() > 0) {
                setSubCatSpinnerPerson2();
                btnNext.setVisibility(View.VISIBLE);
                btnSearch.setVisibility(View.GONE);
            } else {
                if (Integer.parseInt(noOfPersons) > 2) {
                    btnNext.setVisibility(View.VISIBLE);
                    btnSearch.setVisibility(View.GONE);
                } else {
                    btnNext.setVisibility(View.GONE);
                    btnSearch.setVisibility(View.VISIBLE);
                }
            }

        }

    }

    private void setSubCat2SpinnerPerson2(String parentId) {

        if (searchCriteriaModel != null && searchCriteriaModel.criteriaes.size() > 0) {
            subCat2List = new ArrayList<>();
            for (int i = 0; i < searchCriteriaModel.criteriaes.size(); i++) {
                if (searchCriteriaModel.criteriaes.get(i).parentId != null && searchCriteriaModel.criteriaes.get(i).parentId.equalsIgnoreCase(parentId)) {
                    subCat2List.add(searchCriteriaModel.criteriaes.get(i));
                }
            }
            if (subCat2List != null && subCat2List.size() > 0) {
                setSubCat2SpinnerPerson2();
                btnNext.setVisibility(View.VISIBLE);
                btnSearch.setVisibility(View.GONE);
            } else {
                if (Integer.parseInt(noOfPersons) > 2) {
                    btnNext.setVisibility(View.VISIBLE);
                    btnSearch.setVisibility(View.GONE);
                } else {
                    btnNext.setVisibility(View.GONE);
                    btnSearch.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    private void setSubCat3SpinnerPerson2(String parentId) {

        if (searchCriteriaModel != null && searchCriteriaModel.criteriaes.size() > 0) {
            subCat3List = new ArrayList<>();
            for (int i = 0; i < searchCriteriaModel.criteriaes.size(); i++) {
                if (searchCriteriaModel.criteriaes.get(i).parentId != null && searchCriteriaModel.criteriaes.get(i).parentId.equalsIgnoreCase(parentId)) {
                    subCat3List.add(searchCriteriaModel.criteriaes.get(i));
                }
            }
            if (subCat3List != null && subCat3List.size() > 0) {
                setSubCat3SpinnerPerson2();
                btnNext.setVisibility(View.VISIBLE);
                btnSearch.setVisibility(View.GONE);
            } else {
                if (Integer.parseInt(noOfPersons) > 2) {
                    btnNext.setVisibility(View.VISIBLE);
                    btnSearch.setVisibility(View.GONE);
                } else {
                    btnNext.setVisibility(View.GONE);
                    btnSearch.setVisibility(View.VISIBLE);
                }

            }
        }
    }

    private void setSubCat1SpinnerPerson3(String parentId) {
        if (searchCriteriaModel != null && searchCriteriaModel.criteriaes.size() > 0) {
            subCatList = new ArrayList<>();
            for (int i = 0; i < searchCriteriaModel.criteriaes.size(); i++) {
                if (searchCriteriaModel.criteriaes.get(i).parentId != null && searchCriteriaModel.criteriaes.get(i).parentId.equalsIgnoreCase(parentId)) {
                    subCatList.add(searchCriteriaModel.criteriaes.get(i));
                }
            }
            if (subCatList != null && subCatList.size() > 0) {
                setSubCatSpinnerPerson3();
                btnNext.setVisibility(View.VISIBLE);
                btnSearch.setVisibility(View.GONE);
            } else {
                if (Integer.parseInt(noOfPersons) > 3) {
                    btnNext.setVisibility(View.VISIBLE);
                    btnSearch.setVisibility(View.GONE);
                } else {
                    btnNext.setVisibility(View.GONE);
                    btnSearch.setVisibility(View.VISIBLE);
                }
            }

        }

    }

    private void setSubCat2SpinnerPerson3(String parentId) {

        if (searchCriteriaModel != null && searchCriteriaModel.criteriaes.size() > 0) {
            subCat2List = new ArrayList<>();
            for (int i = 0; i < searchCriteriaModel.criteriaes.size(); i++) {
                if (searchCriteriaModel.criteriaes.get(i).parentId != null && searchCriteriaModel.criteriaes.get(i).parentId.equalsIgnoreCase(parentId)) {
                    subCat2List.add(searchCriteriaModel.criteriaes.get(i));
                }
            }
            if (subCat2List != null && subCat2List.size() > 0) {
                setSubCat2SpinnerPerson3();
                btnNext.setVisibility(View.VISIBLE);
                btnSearch.setVisibility(View.GONE);
            } else {
                if (Integer.parseInt(noOfPersons) > 3) {
                    btnNext.setVisibility(View.VISIBLE);
                    btnSearch.setVisibility(View.GONE);
                } else {
                    btnNext.setVisibility(View.GONE);
                    btnSearch.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    private void setSubCat3SpinnerPerson3(String parentId) {

        if (searchCriteriaModel != null && searchCriteriaModel.criteriaes.size() > 0) {
            subCat3List = new ArrayList<>();
            for (int i = 0; i < searchCriteriaModel.criteriaes.size(); i++) {
                if (searchCriteriaModel.criteriaes.get(i).parentId != null && searchCriteriaModel.criteriaes.get(i).parentId.equalsIgnoreCase(parentId)) {
                    subCat3List.add(searchCriteriaModel.criteriaes.get(i));
                }
            }
            if (subCat3List != null && subCat3List.size() > 0) {
                setSubCat3SpinnerPerson3();
                btnNext.setVisibility(View.VISIBLE);
                btnSearch.setVisibility(View.GONE);
            } else {
                if (Integer.parseInt(noOfPersons) > 3) {
                    btnNext.setVisibility(View.VISIBLE);
                    btnSearch.setVisibility(View.GONE);
                } else {
                    btnNext.setVisibility(View.GONE);
                    btnSearch.setVisibility(View.VISIBLE);
                }
            }
        }

    }


    private void hideServiceLayout() {
        rlServices.animate()
                .alpha(0.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rlServices.setVisibility(View.GONE);
                    }
                });
    }

    private void showServiceLayout() {
        rlServices.animate()
                .alpha(1.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rlServices.setVisibility(View.VISIBLE);

                    }
                });
    }

    private void hideSubCat() {
        rlSubCat1.animate()
                .alpha(0.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rlSubCat1.setVisibility(View.GONE);
                    }
                });
    }

    private void showSubCat() {
        rlSubCat1.animate()
                .alpha(1.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rlSubCat1.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void showSubCat2() {
        rlSubCat2.animate()
                .alpha(1.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rlSubCat2.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void hideSubCat2() {
        rlSubCat2.animate()
                .alpha(0.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rlSubCat2.setVisibility(View.GONE);
                    }
                });
    }

    private void showSubCat3() {
        rlSubCat3.animate()
                .alpha(1.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rlSubCat3.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void hideSubCat3() {
        rlSubCat3.animate()
                .alpha(0.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rlSubCat3.setVisibility(View.GONE);
                    }
                });
    }

    private void hideServiceLayoutPerson2() {
        rlServicesPerson2.animate()
                .alpha(0.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rlServicesPerson2.setVisibility(View.GONE);
                    }
                });
    }

    private void showServiceLayoutPerson2() {
        rlServicesPerson2.animate()
                .alpha(1.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rlServicesPerson2.setVisibility(View.VISIBLE);

                    }
                });
    }

    private void hideSubCatPerson2() {
        rlSubCat1Person2.animate()
                .alpha(0.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rlSubCat1Person2.setVisibility(View.GONE);
                    }
                });
    }

    private void showSubCatPerson2() {
        rlSubCat1Person2.animate()
                .alpha(1.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rlSubCat1Person2.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void showSubCat2Person2() {
        rlSubCat2Person2.animate()
                .alpha(1.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rlSubCat2Person2.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void hideSubCat2Person2() {
        rlSubCat2Person2.animate()
                .alpha(0.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rlSubCat2Person2.setVisibility(View.GONE);
                    }
                });
    }

    private void showSubCat3Person2() {
        rlSubCat3Person2.animate()
                .alpha(1.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rlSubCat3Person2.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void hideSubCat3Person2() {
        rlSubCat3Person2.animate()
                .alpha(0.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rlSubCat3Person2.setVisibility(View.GONE);
                    }
                });
    }


    private void hideServiceLayoutPerson3() {
        rlServicesPerson3.animate()
                .alpha(0.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rlServicesPerson3.setVisibility(View.GONE);
                    }
                });
    }

    private void showServiceLayoutPerson3() {
        rlServicesPerson3.animate()
                .alpha(1.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rlServicesPerson3.setVisibility(View.VISIBLE);

                    }
                });
    }

    private void hideSubCatPerson3() {
        rlSubCat1Person3.animate()
                .alpha(0.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rlSubCat1Person3.setVisibility(View.GONE);
                    }
                });
    }

    private void showSubCatPerson3() {
        rlSubCat1Person3.animate()
                .alpha(1.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rlSubCat1Person3.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void showSubCat2Person3() {
        rlSubCat2Person3.animate()
                .alpha(1.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rlSubCat2Person3.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void hideSubCat2Person3() {
        rlSubCat2Person3.animate()
                .alpha(0.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rlSubCat2Person3.setVisibility(View.GONE);
                    }
                });
    }

    private void showSubCat3Person3() {
        rlSubCat3Person3.animate()
                .alpha(1.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rlSubCat3Person3.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void hideSubCat3Person3() {
        rlSubCat3Person3.animate()
                .alpha(0.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rlSubCat3Person3.setVisibility(View.GONE);
                    }
                });
    }

    private void showPerson1() {
        TranslateAnimation anim = new TranslateAnimation(-1000f, 0f, 0f, 0f);  // might need to review the docs
        anim.setDuration(300); // set how long you want the animation
        llPerson1.setAnimation(anim);
        llPerson1.setVisibility(View.VISIBLE);
    }

    private void hidePerson1() {
        TranslateAnimation anim = new TranslateAnimation(-1000f, 0f, 0f, 0f);  // might need to review the docs
        anim.setDuration(300); // set how long you want the animation
        llPerson1.setAnimation(anim);
        llPerson1.setVisibility(View.GONE);
    }

    private void hidePerson2() {
        TranslateAnimation anim = new TranslateAnimation(-1000f, 0f, 0f, 0f);  // might need to review the docs
        anim.setDuration(300); // set how long you want the animation
        llPerson2.setAnimation(anim);
        llPerson2.setVisibility(View.GONE);
    }

    private void showPerson2() {
        TranslateAnimation anim = new TranslateAnimation(1000f, 0f, 0f, 0f);  // might need to review the docs
        anim.setDuration(300); // set how long you want the animation
        llPerson2.setAnimation(anim);
        llPerson2.setVisibility(View.VISIBLE);
    }

    private void showPrePerson2() {
        TranslateAnimation anim = new TranslateAnimation(-1000f, 0f, 0f, 0f);  // might need to review the docs
        anim.setDuration(300); // set how long you want the animation
        llPerson2.setAnimation(anim);
        llPerson2.setVisibility(View.VISIBLE);
    }

    private void showPerson3() {
        TranslateAnimation anim = new TranslateAnimation(1000f, 0f, 0f, 0f);  // might need to review the docs
        anim.setDuration(300); // set how long you want the animation
        llPerson3.setAnimation(anim);
        llPerson3.setVisibility(View.VISIBLE);
    }


}