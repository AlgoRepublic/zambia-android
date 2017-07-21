package com.algorelpublic.zambia.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
    ArrayList<SearchCriteriaModel.Criteria> servicesList, subCat1List, subCat2List, subCat3List,
            servicesListPerson2, subCat1ListPerson2, subCat2ListPerson2, subCat3ListPerson2,
            servicesListPerson3, subCat1ListPerson3, subCat2ListPerson3, subCat3ListPerson3,
            servicesListPerson4, subCat1ListPerson4, subCat2ListPerson4, subCat3ListPerson4,
            servicesListPerson5, subCat1ListPerson5, subCat2ListPerson5, subCat3ListPerson5;
    private ImageView ivPerson1, ivPerson2, ivPerson3, ivPerson4, ivPerson5;
    private Button btnNext, btnPrev, btnSearch;
    private Button btnPerson1, btnPerson2, btnPerson3, btnPerson4, btnPerson5;
    private Spinner spServices, spSubCat1, spSubCat2, spSubCat3,
            spServicesPerson2, spSubCat1Person2, spSubCat2Person2, spSubCat3Person2,
            spServicesPerson3, spSubCat1Person3, spSubCat2Person3, spSubCat3Person3,
            spServicesPerson4, spSubCat1Person4, spSubCat2Person4, spSubCat3Person4,
            spServicesPerson5, spSubCat1Person5, spSubCat2Person5, spSubCat3Person5;
    private ServiceSpinnerAdapter serviceAdapter;
    private String serviceParentId, subCatParentId, subCatParentId2, subCatParentId3;
    private RelativeLayout rlServices, rlSubCat1, rlSubCat2, rlSubCat3,
            rlServicesPerson2, rlSubCat1Person2, rlSubCat2Person2, rlSubCat3Person2,
            rlServicesPerson3, rlSubCat1Person3, rlSubCat2Person3, rlSubCat3Person3,
            rlServicesPerson4, rlSubCat1Person4, rlSubCat2Person4, rlSubCat3Person4,
            rlServicesPerson5, rlSubCat1Person5, rlSubCat2Person5, rlSubCat3Person5;
    private int count = 3;
    private TextView tvServices, tvSubCat1, tvSubCat2, tvSubCat3,
            tvServicesPerson2, tvSubCat1Person2, tvSubCat2Person2, tvSubCat3Person2,
            tvServicesPerson3, tvSubCat1Person3, tvSubCat2Person3, tvSubCat3Person3,
            tvServicesPerson4, tvSubCat1Person4, tvSubCat2Person4, tvSubCat3Person4,
            tvServicesPerson5, tvSubCat1Person5, tvSubCat2Person5, tvSubCat3Person5;
    private LinearLayout llPerson1, llPerson2, llPerson3, llPerson4, llPerson5;
    private int perviousTabCount, perviousTabCountPerson2, perviousTabCountPerson3, perviousTabCountPerson4, perviousTabCountPerson5;
    private ArrayList<ArrayList<String>> queryList = new ArrayList<>();
    private ArrayList<String> person1List = new ArrayList<>();
    private ArrayList<String> person2List = new ArrayList<>();
    private ArrayList<String> person3List = new ArrayList<>();
    private ArrayList<String> person4List = new ArrayList<>();
    private ArrayList<String> person5List = new ArrayList<>();

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


    //FETCH LIST FOR FIRST SPINNER
    private void setValues() {
        if (searchCriteriaModel != null && searchCriteriaModel.criteriaes.size() > 0) {
            servicesList = new ArrayList<>();
            for (int i = 0; i < searchCriteriaModel.criteriaes.size(); i++) {
                if (searchCriteriaModel.criteriaes.get(i).parentId == null || searchCriteriaModel.criteriaes.get(i).parentId.equalsIgnoreCase("")) {
                    servicesList.add(searchCriteriaModel.criteriaes.get(i));
                }
            }
            if (servicesList != null && servicesList.size() > 0)
                setServiceAdapter();
        }
    }

    //INIT
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
        spServicesPerson4 = (Spinner) view.findViewById(R.id.spServicesPerson4);
        spSubCat1Person4 = (Spinner) view.findViewById(R.id.spSubCat1Person4);
        spSubCat2Person4 = (Spinner) view.findViewById(R.id.spSubCat2Person4);
        spSubCat3Person4 = (Spinner) view.findViewById(R.id.spSubCat3Person4);
        spServicesPerson5 = (Spinner) view.findViewById(R.id.spServicesPerson5);
        spSubCat1Person5 = (Spinner) view.findViewById(R.id.spSubCat1Person5);
        spSubCat2Person5 = (Spinner) view.findViewById(R.id.spSubCat2Person5);
        spSubCat3Person5 = (Spinner) view.findViewById(R.id.spSubCat3Person5);

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
        rlServicesPerson4 = (RelativeLayout) view.findViewById(R.id.rlServicesPerson4);
        rlSubCat1Person4 = (RelativeLayout) view.findViewById(R.id.rlSubCat1Person4);
        rlSubCat2Person4 = (RelativeLayout) view.findViewById(R.id.rlSubCat2Person4);
        rlSubCat3Person4 = (RelativeLayout) view.findViewById(R.id.rlSubCat3Person4);
        rlServicesPerson5 = (RelativeLayout) view.findViewById(R.id.rlServicesPerson5);
        rlSubCat1Person5 = (RelativeLayout) view.findViewById(R.id.rlSubCat1Person5);
        rlSubCat2Person5 = (RelativeLayout) view.findViewById(R.id.rlSubCat2Person5);
        rlSubCat3Person5 = (RelativeLayout) view.findViewById(R.id.rlSubCat3Person5);

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
        tvServicesPerson4 = (TextView) view.findViewById(R.id.tvServicesPerson2);
        tvSubCat1Person4 = (TextView) view.findViewById(R.id.tvSubCat1Person2);
        tvSubCat2Person4 = (TextView) view.findViewById(R.id.tvSubCat2Person2);
        tvSubCat3Person4 = (TextView) view.findViewById(R.id.tvSubCat3Person2);
        tvServicesPerson5 = (TextView) view.findViewById(R.id.tvServicesPerson5);
        tvSubCat1Person5 = (TextView) view.findViewById(R.id.tvSubCat1Person5);
        tvSubCat2Person5 = (TextView) view.findViewById(R.id.tvSubCat2Person5);
        tvSubCat3Person5 = (TextView) view.findViewById(R.id.tvSubCat3Person5);

        llPerson1 = (LinearLayout) view.findViewById(R.id.llPerson1);
        llPerson2 = (LinearLayout) view.findViewById(R.id.llPerson2);
        llPerson3 = (LinearLayout) view.findViewById(R.id.llPerson3);
        llPerson4 = (LinearLayout) view.findViewById(R.id.llPerson4);
        llPerson5 = (LinearLayout) view.findViewById(R.id.llPerson5);

        btnPrev = (Button) view.findViewById(R.id.btnPrev);
        btnNext = (Button) view.findViewById(R.id.btnNext);
        btnSearch = (Button) view.findViewById(R.id.btnSearch);
    }

    //ADD LISTENER
    private void addListener() {
        btnPrev.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
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
                setServicePerson2Adapter();

        }
    }

    private void setServicesPerson3() {
        if (searchCriteriaModel != null && searchCriteriaModel.criteriaes.size() > 0) {
            servicesListPerson3 = new ArrayList<>();
            for (int i = 0; i < searchCriteriaModel.criteriaes.size(); i++) {
                if (searchCriteriaModel.criteriaes.get(i).parentId == null || searchCriteriaModel.criteriaes.get(i).parentId.equalsIgnoreCase("")) {
                    servicesListPerson3.add(searchCriteriaModel.criteriaes.get(i));
                }
            }
            if (servicesListPerson3 != null && servicesListPerson3.size() > 0)
                setServicePerson3Adapter();

        }
    }

    private void setServicesPerson4() {
        if (searchCriteriaModel != null && searchCriteriaModel.criteriaes.size() > 0) {
            servicesListPerson4 = new ArrayList<>();
            for (int i = 0; i < searchCriteriaModel.criteriaes.size(); i++) {
                if (searchCriteriaModel.criteriaes.get(i).parentId == null || searchCriteriaModel.criteriaes.get(i).parentId.equalsIgnoreCase("")) {
                    servicesListPerson4.add(searchCriteriaModel.criteriaes.get(i));
                }
            }
            if (servicesListPerson4 != null && servicesListPerson4.size() > 0)
                setServicePerson4Adapter();

        }
    }

    private void setServicesPerson5() {
        if (searchCriteriaModel != null && searchCriteriaModel.criteriaes.size() > 0) {
            servicesListPerson5 = new ArrayList<>();
            for (int i = 0; i < searchCriteriaModel.criteriaes.size(); i++) {
                if (searchCriteriaModel.criteriaes.get(i).parentId == null || searchCriteriaModel.criteriaes.get(i).parentId.equalsIgnoreCase("")) {
                    servicesListPerson5.add(searchCriteriaModel.criteriaes.get(i));
                }
            }
            if (servicesListPerson5 != null && servicesListPerson5.size() > 0)
                setServicePerson5Adapter();

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
                subCatParentId = subCat1List.get(i).id;
                if (person1List.size() > 1)
                    person1List.set(1, subCatParentId);
                else
                    person1List.add(1, subCatParentId);
                Log.d("Id", "id ====>>" + subCat1List.get(i).id + "name ====>> " + subCat1List.get(i).title + "position ===>>" + i);
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
//                setSubCat3Adapter(serviceParentId);
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
                Log.d("Id", "id ====>>" + servicesListPerson2.get(i).id + "name ====>> " + servicesListPerson2.get(i).title + "position ===>>" + i);
                setSubCat1SpinnerPerson2(servicesListPerson2.get(i).id);
                if (person2List.size() > 0)
                    person2List.set(0, servicesListPerson2.get(i).id);
                else
                    person2List.add(0, servicesListPerson2.get(i).id);

                break;

            case R.id.spSubCat1Person2:
                Log.d("Id", "id ====>>" + subCat1ListPerson2.get(i).id + "name ====>> " + subCat1ListPerson2.get(i).title + "position ===>>" + i);
                setSubCat2SpinnerPerson2(subCat1ListPerson2.get(i).id);
                if (person2List.size() > 1)
                    person2List.set(1, subCat1ListPerson2.get(i).id);
                else
                    person2List.add(1, subCat1ListPerson2.get(i).id);

                break;

            case R.id.spSubCat2Person2:
                Log.d("Id", "id ====>>" + subCat2ListPerson2.get(i).id + "name ====>> " + subCat2ListPerson2.get(i).title + "position ===>>" + i);
                setSubCat3SpinnerPerson2(subCat2ListPerson2.get(i).id);
                if (person2List.size() > 2)
                    person2List.set(2, subCat2ListPerson2.get(i).id);
                else
                    person2List.add(2, subCat2ListPerson2.get(i).id);
                break;

            case R.id.spSubCat3Person2:
                Log.d("Id", "id ====>>" + subCat3ListPerson2.get(i).id + "name ====>> " + subCat3ListPerson2.get(i).title + "position ===>>" + i);
                if (person2List.size() > 3)
                    person2List.set(3, subCat3ListPerson2.get(i).id);
                else
                    person2List.add(3, subCat3ListPerson2.get(i).id);
//                setSubCat3Adapter(serviceParentId);
                if (Integer.parseInt(noOfPersons) > 2) {
                    btnNext.setVisibility(View.VISIBLE);
                    btnSearch.setVisibility(View.GONE);
                } else {
                    btnNext.setVisibility(View.GONE);
                    btnSearch.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.spServicesPerson3:
                Log.d("Id", "id ====>>" + servicesListPerson3.get(i).id + "name ====>> " + servicesListPerson3.get(i).title + "position ===>>" + i);
                setSubCat1SpinnerPerson3(servicesListPerson3.get(i).id);
                if (person3List.size() > 0)
                    person3List.set(0, servicesListPerson3.get(i).id);
                else
                    person3List.add(0, servicesListPerson3.get(i).id);

                break;

            case R.id.spSubCat1Person3:
                Log.d("Id", "id ====>>" + subCat1ListPerson3.get(i).id + "name ====>> " + subCat1ListPerson3.get(i).title + "position ===>>" + i);
                setSubCat2SpinnerPerson3(subCat1ListPerson3.get(i).id);
                if (person3List.size() > 1)
                    person3List.set(1, subCat1ListPerson3.get(i).id);
                else
                    person3List.add(1, subCat1ListPerson3.get(i).id);
                break;
            case R.id.spSubCat2Person3:
                Log.d("Id", "id ====>>" + subCat2ListPerson3.get(i).id + "name ====>> " + subCat2ListPerson3.get(i).title + "position ===>>" + i);
                setSubCat3SpinnerPerson3(subCat2ListPerson3.get(i).id);
                if (person3List.size() > 2)
                    person3List.set(2, subCat2ListPerson3.get(i).id);
                else
                    person3List.add(2, subCat2ListPerson3.get(i).id);
                break;

            case R.id.spSubCat3Person3:
                Log.d("Id", "id ====>>" + subCat3ListPerson3.get(i).id + "name ====>> " + subCat3ListPerson3.get(i).title + "position ===>>" + i);
                if (person3List.size() > 3)
                    person3List.set(3, subCat3ListPerson3.get(i).id);
                else
                    person3List.add(3, subCat3ListPerson3.get(i).id);

//                setSubCat3Adapter(serviceParentId);
                if (Integer.parseInt(noOfPersons) > 3) {
                    btnNext.setVisibility(View.VISIBLE);
                    btnSearch.setVisibility(View.GONE);
                } else {
                    btnNext.setVisibility(View.GONE);
                    btnSearch.setVisibility(View.VISIBLE);
                }
                break;


            case R.id.spServicesPerson4:
                Log.d("Id", "id ====>>" + servicesListPerson4.get(i).id + "name ====>> " + servicesListPerson4.get(i).title + "position ===>>" + i);
                setSubCat1SpinnerPerson4(servicesListPerson4.get(i).id);
                if (person4List.size() > 0)
                    person4List.set(0, servicesListPerson4.get(i).id);
                else
                    person4List.add(0, servicesListPerson4.get(i).id);

                break;

            case R.id.spSubCat1Person4:
                Log.d("Id", "id ====>>" + subCat1ListPerson4.get(i).id + "name ====>> " + subCat1ListPerson4.get(i).title + "position ===>>" + i);
                setSubCat2SpinnerPerson4(subCat1ListPerson4.get(i).id);
                if (person4List.size() > 1)
                    person4List.set(1, subCat1ListPerson4.get(i).id);
                else
                    person4List.add(1, subCat1ListPerson4.get(i).id);

                break;

            case R.id.spSubCat2Person4:
                Log.d("Id", "id ====>>" + subCat2ListPerson4.get(i).id + "name ====>> " + subCat2ListPerson4.get(i).title + "position ===>>" + i);
                setSubCat3SpinnerPerson4(subCat2ListPerson4.get(i).id);
                if (person4List.size() > 2)
                    person4List.set(2, subCat2ListPerson4.get(i).id);
                else
                    person4List.add(2, subCat2ListPerson4.get(i).id);
                break;

            case R.id.spSubCat3Person4:
                Log.d("Id", "id ====>>" + subCat3ListPerson4.get(i).id + "name ====>> " + subCat3ListPerson4.get(i).title + "position ===>>" + i);
                if (person4List.size() > 3)
                    person4List.set(3, subCat3ListPerson4.get(i).id);
                else
                    person4List.add(3, subCat3ListPerson4.get(i).id);
//                setSubCat3Adapter(serviceParentId);
                if (Integer.parseInt(noOfPersons) > 3) {
                    btnNext.setVisibility(View.VISIBLE);
                    btnSearch.setVisibility(View.GONE);
                } else {
                    btnNext.setVisibility(View.GONE);
                    btnSearch.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.spServicesPerson5:
                Log.d("Id", "id ====>>" + servicesListPerson5.get(i).id + "name ====>> " + servicesListPerson5.get(i).title + "position ===>>" + i);
                setSubCat1SpinnerPerson5(servicesListPerson5.get(i).id);
                if (person5List.size() > 0)
                    person5List.set(0, servicesListPerson5.get(i).id);
                else
                    person5List.add(0, servicesListPerson5.get(i).id);

                break;

            case R.id.spSubCat1Person5:
                Log.d("Id", "id ====>>" + subCat1ListPerson5.get(i).id + "name ====>> " + subCat1ListPerson5.get(i).title + "position ===>>" + i);
                setSubCat2SpinnerPerson5(subCat1ListPerson5.get(i).id);
                if (person4List.size() > 1)
                    person4List.set(1, subCat1ListPerson5.get(i).id);
                else
                    person4List.add(1, subCat1ListPerson5.get(i).id);

                break;

            case R.id.spSubCat2Person5:
                Log.d("Id", "id ====>>" + subCat2ListPerson5.get(i).id + "name ====>> " + subCat2ListPerson5.get(i).title + "position ===>>" + i);
                setSubCat3SpinnerPerson5(subCat2ListPerson5.get(i).id);
                if (person4List.size() > 2)
                    person4List.set(2, subCat2ListPerson5.get(i).id);
                else
                    person4List.add(2, subCat2ListPerson5.get(i).id);
                break;

            case R.id.spSubCat3Person5:
                Log.d("Id", "id ====>>" + subCat3ListPerson5.get(i).id + "name ====>> " + subCat3ListPerson5.get(i).title + "position ===>>" + i);
                if (person4List.size() > 3)
                    person4List.set(3, subCat3ListPerson5.get(i).id);
                else
                    person4List.add(3, subCat3ListPerson5.get(i).id);
//                setSubCat3Adapter(serviceParentId);
                if (Integer.parseInt(noOfPersons) > 4) {
                    btnNext.setVisibility(View.VISIBLE);
                    btnSearch.setVisibility(View.GONE);
                } else {
                    btnNext.setVisibility(View.GONE);
                    btnSearch.setVisibility(View.VISIBLE);
                }
                break;

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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
                if (queryList.size() > 0)
                    queryList.clear();
                queryList.add(person1List);
                queryList.add(person2List);
                queryList.add(person3List);
                queryList.add(person4List);
                callFragment(R.id.container, SearchResultFragment.newInstance(queryList), "");
                break;
            case "5":
                if (queryList.size() > 0)
                    queryList.clear();
                queryList.add(person1List);
                queryList.add(person2List);
                queryList.add(person3List);
                queryList.add(person4List);
                queryList.add(person5List);
                callFragment(R.id.container, SearchResultFragment.newInstance(queryList), "");
                break;
        }
    }

    private void btnNextPress(View v) {
        switch (count) {
            case Constants.STAGE_SERVICE:
                if (subCat1List != null && subCat1List.size() > 0) {
                    hideServiceLayout();
                    showSubCat();
                    count = Constants.STAGE_SUB_CAT1;
                } else {
                    goto2Tab();
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
                    goto2Tab();
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
                    goto2Tab();
                    count = Constants.STAGE_SERVICE_PERSON_2;
                    perviousTabCount = Constants.STAGE_SUB_CAT2;
                }
                break;

            case Constants.STAGE_PERSON_2:
                goto2Tab();
                count = Constants.STAGE_SERVICE_PERSON_2;
                perviousTabCount = Constants.STAGE_PERSON_2;
                break;

            case Constants.STAGE_SERVICE_PERSON_2:
                if (subCat1ListPerson2 != null && subCat1ListPerson2.size() > 0) {
                    hideServiceLayoutPerson2();
                    showSubCatPerson2();
                    count = Constants.STAGE_SUB_CAT1_PERSON_2;
                } else {
                    goto3Tab();
                    count = Constants.STAGE_SERVICE_PERSON_3;
                    perviousTabCountPerson2 = Constants.STAGE_SERVICE_PERSON_2;
                }
                break;

            case Constants.STAGE_SUB_CAT1_PERSON_2:
                if (subCat2ListPerson2 != null && subCat2ListPerson2.size() > 0) {
                    hideSubCatPerson2();
                    showSubCat2Person2();
                    count = Constants.STAGE_SUB_CAT2_PERSON_2;
                } else {
                    goto3Tab();
                    count = Constants.STAGE_SERVICE_PERSON_3;
                    perviousTabCountPerson2 = Constants.STAGE_SUB_CAT1_PERSON_2;
                }
                break;

            case Constants.STAGE_SUB_CAT2_PERSON_2:
                if (subCat3ListPerson2 != null && subCat3ListPerson2.size() > 0) {
                    hideSubCat2Person2();
                    showSubCat3Person2();
                    count = Constants.STAGE_PERSON_3;
                } else {
                    goto3Tab();
                    count = Constants.STAGE_SERVICE_PERSON_3;
                    perviousTabCountPerson2 = Constants.STAGE_SUB_CAT2_PERSON_2;
                }
                break;

            case Constants.STAGE_PERSON_3:
                goto3Tab();
                count = Constants.STAGE_SERVICE_PERSON_3;
                perviousTabCountPerson2 = Constants.STAGE_PERSON_3;
                break;

            case Constants.STAGE_SERVICE_PERSON_3:
                if (subCat1ListPerson3 != null && subCat1ListPerson3.size() > 0) {
                    hideServiceLayoutPerson3();
                    showSubCatPerson3();
                    count = Constants.STAGE_SUB_CAT1_PERSON_3;
                } else {
                    goto4Tab();
                    count = Constants.STAGE_SERVICE_PERSON_4;
                    perviousTabCountPerson3 = Constants.STAGE_SERVICE_PERSON_3;
                }
                break;

            case Constants.STAGE_SUB_CAT1_PERSON_3:
                if (subCat2ListPerson3 != null && subCat2List.size() > 0) {
                    hideSubCatPerson3();
                    showSubCat2Person3();
                    count = Constants.STAGE_SUB_CAT2_PERSON_3;
                } else {
                    goto4Tab();
                    count = Constants.STAGE_SERVICE_PERSON_4;
                    perviousTabCountPerson3 = Constants.STAGE_SUB_CAT1_PERSON_3;
                }
                break;

            case Constants.STAGE_SUB_CAT2_PERSON_3:
                if (subCat3ListPerson3 != null && subCat3ListPerson3.size() > 0) {
                    hideSubCat2Person3();
                    showSubCat3Person3();
                    count = Constants.STAGE_PERSON_4;
                } else {
                    goto4Tab();
                    count = Constants.STAGE_SERVICE_PERSON_4;
                    perviousTabCountPerson3 = Constants.STAGE_SUB_CAT2_PERSON_3;
                }
                break;

            case Constants.STAGE_PERSON_4:
                goto4Tab();
                count = Constants.STAGE_SERVICE_PERSON_5;
                perviousTabCountPerson4 = Constants.STAGE_PERSON_4;
                break;

            case Constants.STAGE_SERVICE_PERSON_4:
                if (subCat1ListPerson4 != null && subCat1ListPerson4.size() > 0) {
                    hideServiceLayoutPerson4();
                    showSubCatPerson4();
                    count = Constants.STAGE_SUB_CAT1_PERSON_4;
                } else {
                    goto5Tab();
                    count = Constants.STAGE_SERVICE_PERSON_4;
                    perviousTabCountPerson4 = Constants.STAGE_SERVICE_PERSON_4;
                }
                break;

            case Constants.STAGE_SUB_CAT1_PERSON_4:
                if (subCat2ListPerson4 != null && subCat2ListPerson4.size() > 0) {
                    hideSubCatPerson4();
                    showSubCat2Person4();
                    count = Constants.STAGE_SUB_CAT2_PERSON_4;
                } else {
                    goto5Tab();
                    count = Constants.STAGE_SERVICE_PERSON_5;
                    perviousTabCountPerson4 = Constants.STAGE_SUB_CAT1_PERSON_4;
                }
                break;

            case Constants.STAGE_SUB_CAT2_PERSON_4:
                if (subCat3ListPerson4 != null && subCat3ListPerson4.size() > 0) {
                    hideSubCat2Person4();
                    showSubCat3Person4();
                    count = Constants.STAGE_PERSON_5;
                } else {
                    goto5Tab();
                    count = Constants.STAGE_SERVICE_PERSON_5;
                    perviousTabCountPerson4 = Constants.STAGE_SUB_CAT2_PERSON_4;
                }
                break;

            case Constants.STAGE_PERSON_5:
                goto5Tab();
                count = Constants.STAGE_SERVICE_PERSON_5;
                perviousTabCountPerson4 = Constants.STAGE_PERSON_5;
                break;

            case Constants.STAGE_SERVICE_PERSON_5:
                if (subCat1ListPerson5 != null && subCat1ListPerson5.size() > 0) {
                    hideServiceLayoutPerson5();
                    showSubCatPerson5();
                    count = Constants.STAGE_SUB_CAT1_PERSON_5;
                } else {
//                    gotoSecondTab();
//                    count = Constants.STAGE_SERVICE_PERSON_5;
//                    perviousTabCountPerson3 = Constants.STAGE_SERVICE;
                }
                break;

            case Constants.STAGE_SUB_CAT1_PERSON_5:
                if (subCat2ListPerson5 != null && subCat2ListPerson5.size() > 0) {
                    hideSubCatPerson5();
                    showSubCat2Person5();
                    count = Constants.STAGE_SUB_CAT2_PERSON_5;
                } else {
//                    gotoSecondTab();
//                    count = Constants.STAGE_SERVICE_PERSON_5;
//                    perviousTabCountPerson3 = Constants.STAGE_SERVICE;
                }
                break;

            case Constants.STAGE_SUB_CAT2_PERSON_5:
                if (subCat3ListPerson5 != null && subCat3ListPerson5.size() > 0) {
                    hideSubCat2Person5();
                    showSubCat3Person5();
                    count = Constants.STAGE_SUB_CAT3_PERSON_5;
                } else {
//                    gotoSecondTab();
//                    count = Constants.STAGE_SERVICE_PERSON_5;
//                    perviousTabCountPerson3 = Constants.STAGE_SERVICE;
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
                gotoPrev1Tab();
                count = perviousTabCount;
                break;
            case Constants.STAGE_SERVICE_PERSON_2:
                gotoPrev1Tab();
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
                gotoPerv3Tab();
                count = perviousTabCountPerson2;
                break;
            case Constants.STAGE_SERVICE_PERSON_3:
                gotoPerv3Tab();
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
            case Constants.STAGE_PERSON_4:
                gotoPrev1Tab();
                count = perviousTabCount;
                break;
            case Constants.STAGE_SERVICE_PERSON_4:
                gotoPrev1Tab();
                count = perviousTabCountPerson3;
                break;
            case Constants.STAGE_SUB_CAT1_PERSON_4:
                hideSubCat2Person2();
                showSubCatPerson2();
                count = Constants.STAGE_SERVICE_PERSON_2;
                break;
            case Constants.STAGE_SUB_CAT2_PERSON_4:
                hideSubCat3Person2();
                showSubCat2Person2();
                count = Constants.STAGE_SUB_CAT1_PERSON_2;
                break;
            case Constants.STAGE_PERSON_5:
                gotoPerv3Tab();
                count = perviousTabCountPerson4;
                break;
            case Constants.STAGE_SERVICE_PERSON_5:
                gotoPerv3Tab();
                count = perviousTabCountPerson4;
                break;
            case Constants.STAGE_SUB_CAT1_PERSON_5:
                hideSubCat2Person3();
                showSubCatPerson3();
                count = Constants.STAGE_SERVICE_PERSON_5;
                break;
            case Constants.STAGE_SUB_CAT2_PERSON_5:
                hideSubCat3Person3();
                showSubCat2Person3();
                count = Constants.STAGE_SUB_CAT1_PERSON_5;
                break;
        }
    }


    //SET SPINNER ADAPTER
    private void setServiceAdapter() {
        String upperString = servicesList.get(0).questionText.substring(0, 1).toUpperCase() + servicesList.get(0).questionText.substring(1);
        tvServices.setText(Html.fromHtml(upperString));
        tvServices.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        serviceAdapter = new ServiceSpinnerAdapter(getActivity(), R.layout.service_item, servicesList);
        spServices.setAdapter(serviceAdapter);
        spServices.setOnItemSelectedListener(this);
    }

    private void setSub1CatAdapter() {
        String upperString = subCat1List.get(0).questionText.substring(0, 1).toUpperCase() + subCat1List.get(0).questionText.substring(1);
        tvSubCat1.setText(Html.fromHtml(upperString));
        tvSubCat1.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        serviceAdapter = new ServiceSpinnerAdapter(getActivity(), R.layout.service_item, subCat1List);
        spSubCat1.setAdapter(serviceAdapter);
        spSubCat1.setOnItemSelectedListener(this);
    }

    private void setSubCat2Adapter() {
        String upperString = subCat2List.get(0).questionText.substring(0, 1).toUpperCase() + subCat2List.get(0).questionText.substring(1);
        tvSubCat2.setText(Html.fromHtml(upperString));
        tvSubCat2.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        serviceAdapter = new ServiceSpinnerAdapter(getActivity(), R.layout.service_item, subCat2List);
        spSubCat2.setAdapter(serviceAdapter);
        spSubCat2.setOnItemSelectedListener(this);
    }

    private void setSubCat3Adapter() {
        String upperString = subCat3List.get(0).questionText.substring(0, 1).toUpperCase() + subCat3List.get(0).questionText.substring(1);
        tvSubCat3.setText(Html.fromHtml(upperString));
        tvSubCat3.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        serviceAdapter = new ServiceSpinnerAdapter(getActivity(), R.layout.service_item, subCat3List);
        spSubCat3.setAdapter(serviceAdapter);
        spSubCat3.setOnItemSelectedListener(this);
    }

    private void setServicePerson2Adapter() {
        String upperString = servicesListPerson2.get(0).questionText.substring(0, 1).toUpperCase() + servicesListPerson2.get(0).questionText.substring(1);
        tvServicesPerson2.setText(Html.fromHtml(upperString));
        tvServicesPerson2.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        serviceAdapter = new ServiceSpinnerAdapter(getActivity(), R.layout.service_item, servicesListPerson2);
        spServicesPerson2.setAdapter(serviceAdapter);
        spServicesPerson2.setOnItemSelectedListener(this);
    }

    private void setSub1CatPerson2Adapter() {
        String upperString = subCat1ListPerson2.get(0).questionText.substring(0, 1).toUpperCase() + subCat1ListPerson2.get(0).questionText.substring(1);
        tvSubCat1Person2.setText(Html.fromHtml(upperString));
        tvSubCat1Person2.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        serviceAdapter = new ServiceSpinnerAdapter(getActivity(), R.layout.service_item, subCat1ListPerson2);
        spSubCat1Person2.setAdapter(serviceAdapter);
        spSubCat1Person2.setOnItemSelectedListener(this);
    }

    private void setSubCat2Person2Adapter() {
        String upperString = subCat2ListPerson2.get(0).questionText.substring(0, 1).toUpperCase() + subCat2ListPerson2.get(0).questionText.substring(1);
        tvSubCat2Person2.setText(Html.fromHtml(upperString));
        tvSubCat2Person2.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        serviceAdapter = new ServiceSpinnerAdapter(getActivity(), R.layout.service_item, subCat2ListPerson2);
        spSubCat2Person2.setAdapter(serviceAdapter);
        spSubCat2Person2.setOnItemSelectedListener(this);
    }

    private void setSubCat3Person2Adapter() {
        String upperString = subCat3ListPerson2.get(0).questionText.substring(0, 1).toUpperCase() + subCat3ListPerson2.get(0).questionText.substring(1);
        tvSubCat3Person2.setText(Html.fromHtml(upperString));
        tvSubCat3Person2.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        serviceAdapter = new ServiceSpinnerAdapter(getActivity(), R.layout.service_item, subCat3ListPerson2);
        spSubCat3Person2.setAdapter(serviceAdapter);
        spSubCat3Person2.setOnItemSelectedListener(this);
    }

    private void setServicePerson3Adapter() {
        String upperString = servicesListPerson3.get(0).questionText.substring(0, 1).toUpperCase() + servicesListPerson3.get(0).questionText.substring(1);
        tvServicesPerson3.setText(Html.fromHtml(upperString));
        tvServicesPerson3.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        serviceAdapter = new ServiceSpinnerAdapter(getActivity(), R.layout.service_item, servicesListPerson3);
        spServicesPerson3.setAdapter(serviceAdapter);
        spServicesPerson3.setOnItemSelectedListener(this);
    }

    private void setSub1CatPerson3Adapter() {
        String upperString = subCat1ListPerson3.get(0).questionText.substring(0, 1).toUpperCase() + subCat1ListPerson3.get(0).questionText.substring(1);
        tvSubCat1Person3.setText(Html.fromHtml(upperString));
        tvSubCat1Person3.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        serviceAdapter = new ServiceSpinnerAdapter(getActivity(), R.layout.service_item, subCat1ListPerson3);
        spSubCat1Person3.setAdapter(serviceAdapter);
        spSubCat1Person3.setOnItemSelectedListener(this);
    }

    private void setSubCat2Person3Adapter() {
        String upperString = subCat2ListPerson3.get(0).questionText.substring(0, 1).toUpperCase() + subCat2ListPerson3.get(0).questionText.substring(1);
        tvSubCat2Person3.setText(Html.fromHtml(upperString));
        tvSubCat2Person3.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        serviceAdapter = new ServiceSpinnerAdapter(getActivity(), R.layout.service_item, subCat2ListPerson3);
        spSubCat2Person3.setAdapter(serviceAdapter);
        spSubCat2Person3.setOnItemSelectedListener(this);
    }

    private void setSubCat3Person3Adapter() {
        String upperString = subCat3ListPerson3.get(0).questionText.substring(0, 1).toUpperCase() + subCat3ListPerson3.get(0).questionText.substring(1);
        tvSubCat3Person3.setText(Html.fromHtml(upperString));
        tvSubCat3Person3.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        serviceAdapter = new ServiceSpinnerAdapter(getActivity(), R.layout.service_item, subCat3ListPerson3);
        spSubCat3Person3.setAdapter(serviceAdapter);
        spSubCat3Person3.setOnItemSelectedListener(this);
    }

    private void setServicePerson4Adapter() {
        String upperString = servicesListPerson4.get(0).questionText.substring(0, 1).toUpperCase() + servicesListPerson4.get(0).questionText.substring(1);
        tvServicesPerson4.setText(Html.fromHtml(upperString));
        tvServicesPerson4.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        serviceAdapter = new ServiceSpinnerAdapter(getActivity(), R.layout.service_item, servicesListPerson4);
        spServicesPerson4.setAdapter(serviceAdapter);
        spServicesPerson4.setOnItemSelectedListener(this);
    }

    private void setSub1CatPerson4Adapter() {
        String upperString = subCat1ListPerson4.get(0).questionText.substring(0, 1).toUpperCase() + subCat1ListPerson4.get(0).questionText.substring(1);
        tvSubCat1Person4.setText(Html.fromHtml(upperString));
        tvSubCat1Person4.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        serviceAdapter = new ServiceSpinnerAdapter(getActivity(), R.layout.service_item, subCat1ListPerson4);
        spSubCat1Person4.setAdapter(serviceAdapter);
        spSubCat1Person4.setOnItemSelectedListener(this);
    }

    private void setSubCat2Person4Adapter() {
        String upperString = subCat2ListPerson4.get(0).questionText.substring(0, 1).toUpperCase() + subCat2ListPerson4.get(0).questionText.substring(1);
        tvSubCat2Person4.setText(Html.fromHtml(upperString));
        tvSubCat2Person4.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        serviceAdapter = new ServiceSpinnerAdapter(getActivity(), R.layout.service_item, subCat2ListPerson4);
        spSubCat2Person4.setAdapter(serviceAdapter);
        spSubCat2Person4.setOnItemSelectedListener(this);
    }

    private void setSubCat3Person4Adapter() {
        String upperString = subCat3ListPerson4.get(0).questionText.substring(0, 1).toUpperCase() + subCat3ListPerson4.get(0).questionText.substring(1);
        tvSubCat3Person4.setText(Html.fromHtml(upperString));
        tvSubCat3Person4.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        serviceAdapter = new ServiceSpinnerAdapter(getActivity(), R.layout.service_item, subCat3ListPerson4);
        spSubCat3Person4.setAdapter(serviceAdapter);
        spSubCat3Person4.setOnItemSelectedListener(this);
    }

    private void setServicePerson5Adapter() {
        String upperString = servicesListPerson5.get(0).questionText.substring(0, 1).toUpperCase() + servicesListPerson5.get(0).questionText.substring(1);
        tvServicesPerson5.setText(Html.fromHtml(upperString));
        tvServicesPerson5.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        serviceAdapter = new ServiceSpinnerAdapter(getActivity(), R.layout.service_item, servicesListPerson5);
        spServicesPerson5.setAdapter(serviceAdapter);
        spServicesPerson5.setOnItemSelectedListener(this);
    }

    private void setSub1CatPerson5Adapter() {
        String upperString = subCat1ListPerson5.get(0).questionText.substring(0, 1).toUpperCase() + subCat1ListPerson5.get(0).questionText.substring(1);
        tvSubCat1Person5.setText(Html.fromHtml(upperString));
        tvSubCat1Person5.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        serviceAdapter = new ServiceSpinnerAdapter(getActivity(), R.layout.service_item, subCat1ListPerson5);
        spSubCat1Person5.setAdapter(serviceAdapter);
        spSubCat1Person5.setOnItemSelectedListener(this);
    }

    private void setSubCat2Person5Adapter() {
        String upperString = subCat2ListPerson5.get(0).questionText.substring(0, 1).toUpperCase() + subCat2ListPerson5.get(0).questionText.substring(1);
        tvSubCat2Person5.setText(Html.fromHtml(upperString));
        tvSubCat2Person5.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        serviceAdapter = new ServiceSpinnerAdapter(getActivity(), R.layout.service_item, subCat2ListPerson5);
        spSubCat2Person5.setAdapter(serviceAdapter);
        spSubCat2Person5.setOnItemSelectedListener(this);
    }

    private void setSubCat3Person5Adapter() {
        String upperString = subCat3ListPerson5.get(0).questionText.substring(0, 1).toUpperCase() + subCat3ListPerson5.get(0).questionText.substring(1);
        tvSubCat3Person5.setText(Html.fromHtml(upperString));
        tvSubCat3Person5.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        serviceAdapter = new ServiceSpinnerAdapter(getActivity(), R.layout.service_item, subCat3ListPerson5);
        spSubCat3Person5.setAdapter(serviceAdapter);
        spSubCat3Person5.setOnItemSelectedListener(this);
    }


    //Setting Spinners
    private void setSubCat1Spinner(String parentId) {

        if (searchCriteriaModel != null && searchCriteriaModel.criteriaes.size() > 0) {
            subCat1List = new ArrayList<>();
            for (int i = 0; i < searchCriteriaModel.criteriaes.size(); i++) {
                if (searchCriteriaModel.criteriaes.get(i).parentId != null && searchCriteriaModel.criteriaes.get(i).parentId.equalsIgnoreCase(parentId)) {
                    subCat1List.add(searchCriteriaModel.criteriaes.get(i));
                }
            }
            if (subCat1List != null && subCat1List.size() > 0) {
                setSub1CatAdapter();
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
                setSubCat2Adapter();
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
                setSubCat3Adapter();
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
            subCat1ListPerson2 = new ArrayList<>();
            for (int i = 0; i < searchCriteriaModel.criteriaes.size(); i++) {
                if (searchCriteriaModel.criteriaes.get(i).parentId != null && searchCriteriaModel.criteriaes.get(i).parentId.equalsIgnoreCase(parentId)) {
                    subCat1ListPerson2.add(searchCriteriaModel.criteriaes.get(i));
                }
            }
            if (subCat1ListPerson2 != null && subCat1ListPerson2.size() > 0) {
                setSub1CatPerson2Adapter();
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
            subCat2ListPerson2 = new ArrayList<>();
            for (int i = 0; i < searchCriteriaModel.criteriaes.size(); i++) {
                if (searchCriteriaModel.criteriaes.get(i).parentId != null && searchCriteriaModel.criteriaes.get(i).parentId.equalsIgnoreCase(parentId)) {
                    subCat2ListPerson2.add(searchCriteriaModel.criteriaes.get(i));
                }
            }
            if (subCat2ListPerson2 != null && subCat2ListPerson2.size() > 0) {
                setSubCat2Person2Adapter();
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
            subCat3ListPerson2 = new ArrayList<>();
            for (int i = 0; i < searchCriteriaModel.criteriaes.size(); i++) {
                if (searchCriteriaModel.criteriaes.get(i).parentId != null && searchCriteriaModel.criteriaes.get(i).parentId.equalsIgnoreCase(parentId)) {
                    subCat3ListPerson2.add(searchCriteriaModel.criteriaes.get(i));
                }
            }
            if (subCat3ListPerson2 != null && subCat3ListPerson2.size() > 0) {
                setSubCat3Person2Adapter();
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
            subCat1ListPerson3 = new ArrayList<>();
            for (int i = 0; i < searchCriteriaModel.criteriaes.size(); i++) {
                if (searchCriteriaModel.criteriaes.get(i).parentId != null && searchCriteriaModel.criteriaes.get(i).parentId.equalsIgnoreCase(parentId)) {
                    subCat1ListPerson3.add(searchCriteriaModel.criteriaes.get(i));
                }
            }
            if (subCat1ListPerson2 != null && subCat1ListPerson3.size() > 0) {
                setSub1CatPerson3Adapter();
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
            subCat2ListPerson3 = new ArrayList<>();
            for (int i = 0; i < searchCriteriaModel.criteriaes.size(); i++) {
                if (searchCriteriaModel.criteriaes.get(i).parentId != null && searchCriteriaModel.criteriaes.get(i).parentId.equalsIgnoreCase(parentId)) {
                    subCat2ListPerson3.add(searchCriteriaModel.criteriaes.get(i));
                }
            }
            if (subCat2ListPerson3 != null && subCat2ListPerson3.size() > 0) {
                setSubCat2Person3Adapter();
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
            subCat3ListPerson3 = new ArrayList<>();
            for (int i = 0; i < searchCriteriaModel.criteriaes.size(); i++) {
                if (searchCriteriaModel.criteriaes.get(i).parentId != null && searchCriteriaModel.criteriaes.get(i).parentId.equalsIgnoreCase(parentId)) {
                    subCat3ListPerson3.add(searchCriteriaModel.criteriaes.get(i));
                }
            }
            if (subCat3ListPerson3 != null && subCat3ListPerson3.size() > 0) {
                setSubCat3Person3Adapter();
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

    private void setSubCat1SpinnerPerson4(String parentId) {

        if (searchCriteriaModel != null && searchCriteriaModel.criteriaes.size() > 0) {
            subCat1ListPerson4 = new ArrayList<>();
            for (int i = 0; i < searchCriteriaModel.criteriaes.size(); i++) {
                if (searchCriteriaModel.criteriaes.get(i).parentId != null && searchCriteriaModel.criteriaes.get(i).parentId.equalsIgnoreCase(parentId)) {
                    subCat1ListPerson4.add(searchCriteriaModel.criteriaes.get(i));
                }
            }
            if (subCat1ListPerson4 != null && subCat1ListPerson4.size() > 0) {
                setSub1CatPerson4Adapter();
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

    private void setSubCat2SpinnerPerson4(String parentId) {

        if (searchCriteriaModel != null && searchCriteriaModel.criteriaes.size() > 0) {
            subCat2ListPerson4 = new ArrayList<>();
            for (int i = 0; i < searchCriteriaModel.criteriaes.size(); i++) {
                if (searchCriteriaModel.criteriaes.get(i).parentId != null && searchCriteriaModel.criteriaes.get(i).parentId.equalsIgnoreCase(parentId)) {
                    subCat2ListPerson4.add(searchCriteriaModel.criteriaes.get(i));
                }
            }
            if (subCat2ListPerson4 != null && subCat2ListPerson4.size() > 0) {
                setSubCat2Person4Adapter();
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

    private void setSubCat3SpinnerPerson4(String parentId) {

        if (searchCriteriaModel != null && searchCriteriaModel.criteriaes.size() > 0) {
            subCat3ListPerson4 = new ArrayList<>();
            for (int i = 0; i < searchCriteriaModel.criteriaes.size(); i++) {
                if (searchCriteriaModel.criteriaes.get(i).parentId != null && searchCriteriaModel.criteriaes.get(i).parentId.equalsIgnoreCase(parentId)) {
                    subCat3ListPerson4.add(searchCriteriaModel.criteriaes.get(i));
                }
            }
            if (subCat3ListPerson4 != null && subCat3ListPerson4.size() > 0) {
                setSubCat3Person4Adapter();
                btnNext.setVisibility(View.VISIBLE);
                btnSearch.setVisibility(View.GONE);
            } else {
                if (Integer.parseInt(noOfPersons) > 4) {
                    btnNext.setVisibility(View.VISIBLE);
                    btnSearch.setVisibility(View.GONE);
                } else {
                    btnNext.setVisibility(View.GONE);
                    btnSearch.setVisibility(View.VISIBLE);
                }

            }
        }
    }

    private void setSubCat1SpinnerPerson5(String parentId) {
        if (searchCriteriaModel != null && searchCriteriaModel.criteriaes.size() > 0) {
            subCat1ListPerson5 = new ArrayList<>();
            for (int i = 0; i < searchCriteriaModel.criteriaes.size(); i++) {
                if (searchCriteriaModel.criteriaes.get(i).parentId != null && searchCriteriaModel.criteriaes.get(i).parentId.equalsIgnoreCase(parentId)) {
                    subCat1ListPerson5.add(searchCriteriaModel.criteriaes.get(i));
                }
            }
            if (subCat1ListPerson5 != null && subCat1ListPerson5.size() > 0) {
                setSub1CatPerson5Adapter();
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

    private void setSubCat2SpinnerPerson5(String parentId) {

        if (searchCriteriaModel != null && searchCriteriaModel.criteriaes.size() > 0) {
            subCat2ListPerson5 = new ArrayList<>();
            for (int i = 0; i < searchCriteriaModel.criteriaes.size(); i++) {
                if (searchCriteriaModel.criteriaes.get(i).parentId != null && searchCriteriaModel.criteriaes.get(i).parentId.equalsIgnoreCase(parentId)) {
                    subCat2ListPerson5.add(searchCriteriaModel.criteriaes.get(i));
                }
            }
            if (subCat2ListPerson5 != null && subCat2ListPerson5.size() > 0) {
                setSubCat2Person5Adapter();
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

    private void setSubCat3SpinnerPerson5(String parentId) {

        if (searchCriteriaModel != null && searchCriteriaModel.criteriaes.size() > 0) {
            subCat3ListPerson5 = new ArrayList<>();
            for (int i = 0; i < searchCriteriaModel.criteriaes.size(); i++) {
                if (searchCriteriaModel.criteriaes.get(i).parentId != null && searchCriteriaModel.criteriaes.get(i).parentId.equalsIgnoreCase(parentId)) {
                    subCat3ListPerson5.add(searchCriteriaModel.criteriaes.get(i));
                }
            }
            if (subCat3ListPerson5 != null && subCat3ListPerson5.size() > 0) {
                setSubCat3Person5Adapter();
                btnNext.setVisibility(View.VISIBLE);
                btnSearch.setVisibility(View.GONE);
            } else {
                btnNext.setVisibility(View.GONE);
                btnSearch.setVisibility(View.VISIBLE);
            }
        }

    }


    // Tabs Button Animate


    private void gotoPrev1Tab() {
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


    private void goto2Tab() {
//        hidePerson1();
        llPerson1.setVisibility(View.GONE);
        showPerson2();
        setServicesPerson2();
        btnPerson2.setBackgroundResource(R.drawable.cicle_bg_selected);
        btnPerson1.setBackgroundResource(R.drawable.circle_bg);

        //        btnPerson1.set

    }

    private void gotoPerv2Tab() {
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

    private void goto3Tab() {
//        hidePerson1();
        llPerson2.setVisibility(View.GONE);
        showPerson3();
        setServicesPerson3();
        btnPerson3.setBackgroundResource(R.drawable.cicle_bg_selected);
        btnPerson2.setBackgroundResource(R.drawable.circle_bg);

        //        btnPerson1.set

    }

    private void gotoPerv3Tab() {
        showPrePerson3();
        llPerson4.setVisibility(View.GONE);
//        hidePerson2();
//        setServicesPerson2();
        btnNext.setVisibility(View.VISIBLE);
        btnSearch.setVisibility(View.GONE);
        btnPerson3.setBackgroundResource(R.drawable.cicle_bg_selected);
        btnPerson4.setBackgroundResource(R.drawable.circle_bg);

        //        btnPerson1.set

    }


    private void goto4Tab() {
//        hidePerson1();
        llPerson3.setVisibility(View.GONE);
        showPerson4();
        setServicesPerson4();
        btnPerson4.setBackgroundResource(R.drawable.cicle_bg_selected);
        btnPerson3.setBackgroundResource(R.drawable.circle_bg);

        //        btnPerson1.set

    }

    private void gotoPerv4Tab() {
        showPrePerson4();
        llPerson5.setVisibility(View.GONE);
//        hidePerson2();
//        setServicesPerson2();
        btnNext.setVisibility(View.VISIBLE);
        btnSearch.setVisibility(View.GONE);
        btnPerson4.setBackgroundResource(R.drawable.cicle_bg_selected);
        btnPerson5.setBackgroundResource(R.drawable.circle_bg);

        //        btnPerson1.set

    }

    private void goto5Tab() {
//        hidePerson1();
        llPerson4.setVisibility(View.GONE);
        showPerson5();
        setServicesPerson5();
        btnPerson5.setBackgroundResource(R.drawable.cicle_bg_selected);
        btnPerson4.setBackgroundResource(R.drawable.circle_bg);

        //        btnPerson1.set

    }


    // Animation Tabs layout
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

    private void showPrePerson3() {
        TranslateAnimation anim = new TranslateAnimation(-1000f, 0f, 0f, 0f);  // might need to review the docs
        anim.setDuration(300); // set how long you want the animation
        llPerson3.setAnimation(anim);
        llPerson3.setVisibility(View.VISIBLE);
    }

    private void showPerson4() {
        TranslateAnimation anim = new TranslateAnimation(1000f, 0f, 0f, 0f);  // might need to review the docs
        anim.setDuration(300); // set how long you want the animation
        llPerson4.setAnimation(anim);
        llPerson4.setVisibility(View.VISIBLE);
    }

    private void showPrePerson4() {
        TranslateAnimation anim = new TranslateAnimation(-1000f, 0f, 0f, 0f);  // might need to review the docs
        anim.setDuration(300); // set how long you want the animation
        llPerson4.setAnimation(anim);
        llPerson4.setVisibility(View.VISIBLE);
    }

    private void showPerson5() {
        TranslateAnimation anim = new TranslateAnimation(1000f, 0f, 0f, 0f);  // might need to review the docs
        anim.setDuration(300); // set how long you want the animation
        llPerson5.setAnimation(anim);
        llPerson5.setVisibility(View.VISIBLE);
    }

    private void showPrePerson5() {
        TranslateAnimation anim = new TranslateAnimation(-1000f, 0f, 0f, 0f);  // might need to review the docs
        anim.setDuration(300); // set how long you want the animation
        llPerson5.setAnimation(anim);
        llPerson5.setVisibility(View.VISIBLE);
    }


    // Animations tabs content
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


    private void hideServiceLayoutPerson4() {
        rlServicesPerson4.animate()
                .alpha(0.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rlServicesPerson4.setVisibility(View.GONE);
                    }
                });
    }

    private void showServiceLayoutPerson4() {
        rlServicesPerson4.animate()
                .alpha(1.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rlServicesPerson4.setVisibility(View.VISIBLE);

                    }
                });
    }

    private void hideSubCatPerson4() {
        rlSubCat1Person4.animate()
                .alpha(0.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rlSubCat1Person4.setVisibility(View.GONE);
                    }
                });
    }

    private void showSubCatPerson4() {
        rlSubCat1Person4.animate()
                .alpha(1.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rlSubCat1Person4.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void showSubCat2Person4() {
        rlSubCat2Person2.animate()
                .alpha(1.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rlSubCat2Person4.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void hideSubCat2Person4() {
        rlSubCat2Person2.animate()
                .alpha(0.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rlSubCat2Person4.setVisibility(View.GONE);
                    }
                });
    }

    private void showSubCat3Person4() {
        rlSubCat3Person4.animate()
                .alpha(1.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rlSubCat3Person4.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void hideSubCat3Person4() {
        rlSubCat3Person4.animate()
                .alpha(0.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rlSubCat3Person4.setVisibility(View.GONE);
                    }
                });
    }


    private void hideServiceLayoutPerson5() {
        rlServicesPerson5.animate()
                .alpha(0.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rlServicesPerson5.setVisibility(View.GONE);
                    }
                });
    }

    private void showServiceLayoutPerson5() {
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

    private void hideSubCatPerson5() {
        rlSubCat1Person5.animate()
                .alpha(0.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rlSubCat1Person5.setVisibility(View.GONE);
                    }
                });
    }

    private void showSubCatPerson5() {
        rlSubCat1Person5.animate()
                .alpha(1.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rlSubCat1Person5.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void showSubCat2Person5() {
        rlSubCat2Person5.animate()
                .alpha(1.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rlSubCat2Person5.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void hideSubCat2Person5() {
        rlSubCat2Person5.animate()
                .alpha(0.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rlSubCat2Person5.setVisibility(View.GONE);
                    }
                });
    }

    private void showSubCat3Person5() {
        rlSubCat3Person5.animate()
                .alpha(1.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rlSubCat3Person5.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void hideSubCat3Person5() {
        rlSubCat3Person5.animate()
                .alpha(0.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rlSubCat3Person5.setVisibility(View.GONE);
                    }
                });
    }


}