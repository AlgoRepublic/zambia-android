package com.algorelpublic.zambia.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.algorelpublic.zambia.R;
import com.algorelpublic.zambia.Zambia;
import com.algorelpublic.zambia.activities.ZambiaMain;
import com.algorelpublic.zambia.adapter.ServiceSpinnerAdapter;
import com.algorelpublic.zambia.model.SearchCriteriaModel;
import com.algorelpublic.zambia.utils.Constants;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by android on 7/21/17.
 */

public class AdvanceSearchAllStepsFragment extends BaseFragment
        implements View.OnClickListener,
        AdapterView.OnItemSelectedListener {

    public static AdvanceSearchAllStepsFragment instance;
    private View view;
    private Button btnNext, btnPrev, btnSearch;
    private Spinner spServices;
    private ServiceSpinnerAdapter serviceAdapter;
    private TextView tvServices;
    private static String serviceParentId;
    ArrayList<SearchCriteriaModel.Criteria> childList;
    private SearchCriteriaModel searchCriteriaModel;
    ArrayList<SearchCriteriaModel.Criteria> servicesList;

    public static AdvanceSearchAllStepsFragment newInstance(String parentId) {
        instance = new AdvanceSearchAllStepsFragment();
        serviceParentId = parentId;
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_advance_search_child, container, false);
        init();
        addListener();
        Gson gson = new Gson();
        searchCriteriaModel = gson.fromJson(Zambia.db.getString(Constants.RESPONSE_GSON_SEARCH_CRITERIAS), SearchCriteriaModel.class);
        if (searchCriteriaModel != null)
            populateView(serviceParentId);
        return view;
    }
    //INIT
    private void init() {
        //set spinners
        spServices = (Spinner) view.findViewById(R.id.spServices);
        //set Tabs layouts
        tvServices = (TextView) view.findViewById(R.id.tvServices);
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
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        serviceParentId = servicesList.get(i).id;
        AdvanceSearchStepsFragment.selectionList.add(serviceParentId);
        checkChild(serviceParentId);
        Log.d("Id", "id ====>>" + servicesList.get(i).id + "name ====>> " + servicesList.get(i).title + "position ===>>" + i);
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
                if (getActivity().getSupportFragmentManager().getBackStackEntryCount()>2)
                    getActivity().onBackPressed();
                break;
            case R.id.btnSearch:
                searchClick();
                break;
        }
    }
    private void searchClick() {
        if (AdvanceSearchStepsFragment.selectionList.size()>0) {
            AdvanceSearchStepsFragment.queryList.add(AdvanceSearchStepsFragment.selectionList);
        }
        callFragment(R.id.container, SearchResultFragment.newInstance(AdvanceSearchStepsFragment.queryList), "");
        //AdvanceSearchStepsFragment.selectionList.clear();
    }
    //Setting Spinners
    private void populateView(String parentId) {

        if (searchCriteriaModel != null && searchCriteriaModel.criteriaes.size() > 0) {
            servicesList = new ArrayList<>();
            for (int i = 0; i < searchCriteriaModel.criteriaes.size(); i++) {
                if (searchCriteriaModel.criteriaes.get(i).parentId != null && searchCriteriaModel.criteriaes.get(i).parentId.equalsIgnoreCase(parentId)) {
                    servicesList.add(searchCriteriaModel.criteriaes.get(i));
                }else if (parentId == null) {
                    if (searchCriteriaModel.criteriaes.get(i).parentId == null || searchCriteriaModel.criteriaes.get(i).parentId.equalsIgnoreCase("")) {
                        servicesList.add(searchCriteriaModel.criteriaes.get(i));
                    }
                }
            }
            if (servicesList.size()>0)
            setSpinnerAdapter(servicesList);
        }
    }
    //SET SPINNER ADAPTER
    private void setSpinnerAdapter(ArrayList<SearchCriteriaModel.Criteria> servicesList) {
        String upperString = servicesList.get(0).questionText.substring(0, 1).toUpperCase() + servicesList.get(0).questionText.substring(1);
        tvServices.setText(Html.fromHtml(upperString));
        tvServices.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        serviceAdapter = new ServiceSpinnerAdapter(getActivity(), R.layout.service_item, servicesList);
        spServices.setAdapter(serviceAdapter);
        spServices.setOnItemSelectedListener(this);
        serviceParentId = servicesList.get(0).id;
    }

    private void btnNextPress(View v) {
       // Log.e("DDD",childList.size()+"/"+totalPersons+"/"+serviceParentId);
        if (AdvanceSearchStepsFragment.totalPersons < AdvanceSearchStepsFragment.noOfPersons) {
            if (childList.size() == 0) {
                ((ZambiaMain) getActivity()).addFragmentWithReplace(R.id.steps_container,
                        AdvanceSearchAllStepsFragment.newInstance(null), "AdvanceSearchDetailFragment");
                if (AdvanceSearchStepsFragment.selectionList.size()>0) {
                    AdvanceSearchStepsFragment.queryList.add(AdvanceSearchStepsFragment.selectionList);
                    AdvanceSearchStepsFragment.selectionList.clear();
                }
                AdvanceSearchStepsFragment.totalPersons++;
                AdvanceSearchStepsFragment.instance.setPersonsColor
                        (AdvanceSearchStepsFragment.totalPersons-1);
            }else{
                ((ZambiaMain) getActivity()).addFragmentWithReplace(R.id.steps_container,
                        AdvanceSearchAllStepsFragment.newInstance(serviceParentId), "AdvanceSearchDetailFragment");
            }
        }else{
            ((ZambiaMain) getActivity()).addFragmentWithReplace(R.id.steps_container,
                    AdvanceSearchAllStepsFragment.newInstance(serviceParentId), "AdvanceSearchDetailFragment");
        }
    }
    private void checkChild(String parentId) {

        if (searchCriteriaModel != null && searchCriteriaModel.criteriaes.size() > 0) {
            childList = new ArrayList<>();
            for (int i = 0; i < searchCriteriaModel.criteriaes.size(); i++) {
                if (searchCriteriaModel.criteriaes.get(i).parentId != null && searchCriteriaModel.criteriaes.get(i).parentId.equalsIgnoreCase(parentId)) {
                    childList.add(searchCriteriaModel.criteriaes.get(i));
                }
            }
        }
        setButtons();
    }
    private void setButtons(){
        if (childList != null && childList.size() > 0) {
            btnNext.setVisibility(View.VISIBLE);
            btnSearch.setVisibility(View.INVISIBLE);
            btnPrev.setVisibility(View.VISIBLE);
        } else {
            if (AdvanceSearchStepsFragment.noOfPersons == AdvanceSearchStepsFragment.totalPersons) {
                btnNext.setVisibility(View.INVISIBLE);
                btnPrev.setVisibility(View.VISIBLE);
                btnSearch.setVisibility(View.VISIBLE);
            }else if (AdvanceSearchStepsFragment.noOfPersons > 1) {
                btnNext.setVisibility(View.VISIBLE);
                btnSearch.setVisibility(View.INVISIBLE);
                btnPrev.setVisibility(View.VISIBLE);
            }
        }
    }
}