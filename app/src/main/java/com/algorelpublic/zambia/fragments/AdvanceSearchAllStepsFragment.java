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
import java.util.HashMap;

/**
 * Created by android on 7/21/17.
 */

public class AdvanceSearchAllStepsFragment extends BaseFragment
        implements View.OnClickListener,
        AdapterView.OnItemSelectedListener {

    public static AdvanceSearchAllStepsFragment instance;
    private View view;
    private Button btnPerson1, btnPerson2, btnPerson3, btnPerson4, btnPerson5;
    private Button[] personsArray = {btnPerson1, btnPerson2, btnPerson3, btnPerson4, btnPerson5};
    private Button btnNext, btnPrev, btnAdvancesearch;
    private Spinner spServices;
    private ServiceSpinnerAdapter serviceAdapter;
    private TextView tvServices;
    public ArrayList<SearchCriteriaModel.Criteria>  childList = new ArrayList<>();;
    private String serviceParentId;
    private SearchCriteriaModel searchCriteriaModel;
    ArrayList<SearchCriteriaModel.Criteria> servicesList;

    public static AdvanceSearchAllStepsFragment newInstance(String parentId) {
        instance = new AdvanceSearchAllStepsFragment();
        Bundle args = new Bundle();
        args.putString("ParentID", parentId);
        instance.setArguments(args);
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    /**
     * set tabs
     */
    public void setPersonTabs(int noOfPersons) {
        for (int loop=0;loop<personsArray.length;loop++){
            if (loop < noOfPersons){
                personsArray[loop].setVisibility(View.VISIBLE);
            }else{
                personsArray[loop].setVisibility(View.GONE);
            }
        }
    }
    public void setPersonsColor(int number){
        for (int loop=0;loop<personsArray.length;loop++){
            if (loop == number){
                personsArray[loop].setBackgroundResource(R.drawable.cicle_bg_selected);
            }else{
                personsArray[loop].setBackgroundResource(R.drawable.circle_bg);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_advance_search_child, container, false);
        init();
        setPersonTabs(AdvanceSearchStepsFragment.noOfPersons);
        setPersonsColor(AdvanceSearchStepsFragment.totalPersons-1);
        addListener();
        serviceParentId = getArguments().getString("ParentID");
        Gson gson = new Gson();
        searchCriteriaModel = gson.fromJson(Zambia.db.getString(Constants.RESPONSE_GSON_SEARCH_CRITERIAS), SearchCriteriaModel.class);
        if (searchCriteriaModel != null)
            populateView(serviceParentId);

        setRetainInstance(true);
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
        btnAdvancesearch = (Button) view.findViewById(R.id.btnAdvancesearch);

        //set tabs buttons
        personsArray[0] = (Button) view.findViewById(R.id.btnPerson1);
        personsArray[1] = (Button) view.findViewById(R.id.btnPerson2);
        personsArray[2] = (Button) view.findViewById(R.id.btnPerson3);
        personsArray[3] = (Button) view.findViewById(R.id.btnPerson4);
        personsArray[4] = (Button) view.findViewById(R.id.btnPerson5);
    }

    //ADD LISTENER
    private void addListener() {
        btnPrev.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnAdvancesearch.setOnClickListener(this);
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        serviceParentId = servicesList.get(i).id;
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
                Log.e("DD","/"+getActivity().getSupportFragmentManager().getBackStackEntryCount());
                if (getActivity().getSupportFragmentManager().getBackStackEntryCount()>2) {
                    getActivity().onBackPressed();
                }else{
                    AdvanceSearchStepsFragment.totalPersons=1;
                    AdvanceSearchStepsFragment.selectionList.clear();
                    AdvanceSearchStepsFragment.queryList.clear();
                }
                break;
            case R.id.btnAdvancesearch:
                searchClick();
                break;
        }
    }
    private void searchClick() {
        AdvanceSearchStepsFragment.selectionList.add(serviceParentId);
        if (AdvanceSearchStepsFragment.selectionList.size()>0) {
            AdvanceSearchStepsFragment.queryList.add(AdvanceSearchStepsFragment.selectionList);
        }
        callFragment(R.id.container, SearchResultFragment.newInstance(AdvanceSearchStepsFragment.queryList), "");
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
//                setPersonsColor
//                        (AdvanceSearchStepsFragment.totalPersons-1);
            }else{
                AdvanceSearchStepsFragment.selectionList.add(serviceParentId);
                ((ZambiaMain) getActivity()).addFragmentWithReplace(R.id.steps_container,
                        AdvanceSearchAllStepsFragment.newInstance(serviceParentId), "AdvanceSearchDetailFragment");
            }
        }else{
            AdvanceSearchStepsFragment.selectionList.add(serviceParentId);
            ((ZambiaMain) getActivity()).addFragmentWithReplace(R.id.steps_container,
                    AdvanceSearchAllStepsFragment.newInstance(serviceParentId), "AdvanceSearchDetailFragment");
        }
    }
    private void checkChild(String parentId) {

        if (searchCriteriaModel != null && searchCriteriaModel.criteriaes.size() > 0) {
            childList.clear();
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
            btnAdvancesearch.setVisibility(View.INVISIBLE);
            btnPrev.setVisibility(View.VISIBLE);
        } else {
            if (AdvanceSearchStepsFragment.noOfPersons == AdvanceSearchStepsFragment.totalPersons) {
                btnNext.setVisibility(View.INVISIBLE);
                btnPrev.setVisibility(View.VISIBLE);
                btnAdvancesearch.setVisibility(View.VISIBLE);
            }else if (AdvanceSearchStepsFragment.noOfPersons > 1) {
                btnNext.setVisibility(View.VISIBLE);
                btnAdvancesearch.setVisibility(View.INVISIBLE);
                btnPrev.setVisibility(View.VISIBLE);
            }
        }
    }
}