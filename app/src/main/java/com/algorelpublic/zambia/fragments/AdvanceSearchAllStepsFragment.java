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
import android.widget.Switch;
import android.widget.TextView;

import com.algorelpublic.zambia.R;
import com.algorelpublic.zambia.Zambia;
import com.algorelpublic.zambia.activities.BaseActivity;
import com.algorelpublic.zambia.activities.ZambiaMain;
import com.algorelpublic.zambia.adapter.ServiceSpinnerAdapter;
import com.algorelpublic.zambia.model.SearchCriteriaModel;
import com.algorelpublic.zambia.utils.Constants;
import com.google.gson.Gson;

import java.util.ArrayList;

import static com.algorelpublic.zambia.fragments.AdvanceSearchStepsFragment.noOfPersons;
import static com.algorelpublic.zambia.fragments.AdvanceSearchStepsFragment.queryList;
import static com.algorelpublic.zambia.fragments.AdvanceSearchStepsFragment.selectionList;
import static com.algorelpublic.zambia.fragments.AdvanceSearchStepsFragment.totalPersons;

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
    public ArrayList<SearchCriteriaModel.Criteria> childList = new ArrayList<>();
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
        for (int loop = 0; loop < personsArray.length; loop++) {
            if (loop < noOfPersons) {
                personsArray[loop].setVisibility(View.VISIBLE);
            } else {
                personsArray[loop].setVisibility(View.GONE);
            }
        }
    }

    public void setPersonsColor(int number) {
        for (int loop = 0; loop < personsArray.length; loop++) {
            if (loop == number) {
                personsArray[loop].setBackgroundResource(R.drawable.icon_red);
                personsArray[loop].setTextColor(getActivity().getResources().getColor(R.color.colorRed));

            } else {
                personsArray[loop].setBackgroundResource(R.drawable.icon_white);
                personsArray[loop].setTextColor(getActivity().getResources().getColor(android.R.color.white));

            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_advance_search_child, container, false);
        init();
        setPersonTabs(noOfPersons);
        setPersonsColor(totalPersons - 1);
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
                Log.e("DD", "/" + getActivity().getSupportFragmentManager().getBackStackEntryCount());
                if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 2) {
                    try {
                        if (selectionList.size() == 0) {
                            totalPersons--;
                            serviceParentId = null;
                            selectionList.addAll(queryList.get(queryList.size() - 1));
                            selectionList.remove(selectionList.size() - 1);
                            queryList.remove(queryList.size() - 1);
                        } else if (selectionList.size() > 0) {
                            serviceParentId = selectionList.get(selectionList.size() - 1);
                            selectionList.remove(selectionList.size() - 1);
                        }
                    } catch (IndexOutOfBoundsException ex) {
                        ex.printStackTrace();
                    }
                    getActivity().onBackPressed();
                } else {
                    totalPersons = 1;
                    selectionList.clear();
                    queryList.clear();
                    getActivity().onBackPressed();
                }
                break;
            case R.id.btnAdvancesearch:
                searchClick();
                break;
        }
    }

    private void searchClick() {
        selectionList.add(serviceParentId);
        if (selectionList.size() > 0) {
            queryList.add(selectionList);
        }
        ((ZambiaMain) ZambiaMain.activity).callFragmentWithReplace(R.id.container, SearchResultFragment.newInstance(queryList), null);
    }

    //Setting Spinners
    private void populateView(String parentId) {

        if (searchCriteriaModel != null && searchCriteriaModel.criteriaes.size() > 0) {
            servicesList = new ArrayList<>();
            for (int i = 0; i < searchCriteriaModel.criteriaes.size(); i++) {
                if (searchCriteriaModel.criteriaes.get(i).parentId != null && searchCriteriaModel.criteriaes.get(i).parentId.equalsIgnoreCase(parentId)) {
                    servicesList.add(searchCriteriaModel.criteriaes.get(i));
                } else if (parentId == null) {
                    if (searchCriteriaModel.criteriaes.get(i).parentId == null || searchCriteriaModel.criteriaes.get(i).parentId.equalsIgnoreCase("")) {
                        servicesList.add(searchCriteriaModel.criteriaes.get(i));
                    }
                }
            }
            if (servicesList.size() > 0)
                setSpinnerAdapter(servicesList);
        }
    }

    //SET SPINNER ADAPTER
    private void setSpinnerAdapter(ArrayList<SearchCriteriaModel.Criteria> servicesList) {
        String upperString = servicesList.get(0).questionText.substring(0, 1).toUpperCase() + servicesList.get(0).questionText.substring(1);
        if (serviceParentId == null)
            setUpperString(tvServices, upperString);
        else
            tvServices.setText(Html.fromHtml(upperString));
//        tvServices.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        serviceAdapter = new ServiceSpinnerAdapter(getActivity(), R.layout.service_item, servicesList);
        spServices.setAdapter(serviceAdapter);
        spServices.setOnItemSelectedListener(this);
        serviceParentId = servicesList.get(0).id;
    }

    private void setUpperString(TextView tvServices, String upperString) {
        String strString = "";
        switch (totalPersons) {
            case 1:
                strString = "first";
                break;
            case 2:
                strString = "second";
                break;
            case 3:
                strString = "third";
                break;
            case 4:
                strString = "fourth";
                break;
            case 5:
                strString = "fifth";
                break;
            default:
                strString = "first";
                break;
        }
        tvServices.setText(Html.fromHtml("For the " + strString + " individual," + System.getProperty("line.separator") + upperString));
    }

    private void btnNextPress(View v) {
        // Log.e("DDD",childList.size()+"/"+totalPersons+"/"+serviceParentId);
        selectionList.add(serviceParentId);
        if (totalPersons < noOfPersons) {
            if (childList.size() == 0) {
                ((ZambiaMain) getActivity()).addFragmentWithReplace(R.id.steps_container,
                        AdvanceSearchAllStepsFragment.newInstance(null), "AdvanceSearchDetailFragment");
                if (selectionList.size() > 0) {
                    ArrayList<String> tempArray = new ArrayList<>();
                    tempArray.addAll(selectionList);
                    queryList.add(tempArray);
                    selectionList.clear();
                }
                totalPersons++;
//                setPersonsColor
//                        (AdvanceSearchStepsFragment.totalPersons-1);
            } else {
                ((ZambiaMain) getActivity()).addFragmentWithReplace(R.id.steps_container,
                        AdvanceSearchAllStepsFragment.newInstance(serviceParentId), "AdvanceSearchDetailFragment");
            }
        } else {
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

    private void setButtons() {
        if (childList != null && childList.size() > 0) {
            btnNext.setVisibility(View.VISIBLE);
            btnAdvancesearch.setVisibility(View.INVISIBLE);
            btnPrev.setVisibility(View.VISIBLE);
        } else {
            if (noOfPersons == totalPersons) {
                btnNext.setVisibility(View.INVISIBLE);
                btnPrev.setVisibility(View.VISIBLE);
                btnAdvancesearch.setVisibility(View.VISIBLE);
            } else if (noOfPersons > 1) {
                btnNext.setVisibility(View.VISIBLE);
                btnAdvancesearch.setVisibility(View.INVISIBLE);
                btnPrev.setVisibility(View.VISIBLE);
//                tvServices.setText("For "+AdvanceSearchStepsFragment.noOfPersons+" individual, I am searching for information on:");
            }
        }
    }
}