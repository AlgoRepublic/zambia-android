package com.algorelpublic.zambia.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.algorelpublic.zambia.R;
import com.algorelpublic.zambia.Zambia;
import com.algorelpublic.zambia.adapter.AdapterResult;
import com.algorelpublic.zambia.model.SearchResultModel;
import com.algorelpublic.zambia.utils.Constants;
import com.google.gson.Gson;

import java.util.ArrayList;

import static android.widget.LinearLayout.VERTICAL;
import static com.algorelpublic.zambia.fragments.AdvanceSearchStepsFragment.noOfPersons;
import static com.algorelpublic.zambia.fragments.AdvanceSearchStepsFragment.queryList;
import static com.algorelpublic.zambia.fragments.AdvanceSearchStepsFragment.selectionList;

/**
 * Created by Adil Nazir on 16/07/2017.
 */

public class SearchResultFragment extends BaseFragment implements View.OnClickListener {

    public static SearchResultFragment instance;
    private static ArrayList<ArrayList<String>> resultIdList;
    public ArrayList<SearchResultModel.Results> searchList = new ArrayList<>();
    private View view;
    private SearchResultModel searchResultModel;
    private RecyclerView rvResults;
    private boolean isItemAdded = false;


    public static SearchResultFragment newInstance(ArrayList<ArrayList<String>> mList) {
        resultIdList = mList;
        instance = new SearchResultFragment();
        return instance;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        try {
            setToolBar();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        super.onCreate(savedInstanceState);
    }

    private void setToolBar() throws NullPointerException {
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Results</font>"));
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_result, container, false);
        init();
        addListener();
        Gson gson = new Gson();
        searchResultModel = gson.fromJson(Zambia.db.getString(Constants.RESPONSE_GSON_SEARCH_RESULT), SearchResultModel.class);
        getResult();
        return view;
    }


    private void init() {
        rvResults = (RecyclerView) view.findViewById(R.id.rvResults);
    }

    private void addListener() {
//        btnForgotPassword.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }

    }

    public void getResult() {
        if (searchList.size() > 0)
            searchList.clear();
        for (int i = 0; i < resultIdList.size(); i++) {
            searchResult(resultIdList.get(i), i);
        }

        setRecyclerAdapter();

    }

    private void searchResult(ArrayList<String> personDataList, int p) {
        switch (personDataList.size()) {
            case 0:
                break;
            case 1:
                for (int i = 0; i < searchResultModel.resultsList.size(); i++) {
                    if (searchResultModel.resultsList.get(i).step_1_id.equalsIgnoreCase(personDataList.get(0))) {
                        searchList.add(searchResultModel.resultsList.get(i));
                        isItemAdded = true;
                        break;
                    }
                }
                if (isItemAdded) {
                    isItemAdded = false;
                } else {
                    searchList.add(null);
                    isItemAdded = false;
                }
                break;
            case 2:
                for (int i = 0; i < searchResultModel.resultsList.size(); i++) {
                    if (searchResultModel.resultsList.get(i).step_1_id.equalsIgnoreCase(personDataList.get(0))
                            && searchResultModel.resultsList.get(i).step_2_id.equalsIgnoreCase(personDataList.get(1))) {
                        searchList.add(searchResultModel.resultsList.get(i));
                        isItemAdded = true;
                        break;
                    }
                }
                if (isItemAdded) {
                    isItemAdded = false;
                } else {
                    searchList.add(null);
                    isItemAdded = false;
                }
                break;
            case 3:
                    for (int i = 0; i < searchResultModel.resultsList.size(); i++) {
                        if (searchResultModel.resultsList.get(i).step_1_id.equalsIgnoreCase(personDataList.get(0))
                                && searchResultModel.resultsList.get(i).step_2_id.equalsIgnoreCase(personDataList.get(1))
                                && searchResultModel.resultsList.get(i).step_3_id.equalsIgnoreCase(personDataList.get(2))) {
                            searchList.add(searchResultModel.resultsList.get(i));
                            isItemAdded = true;
                            break;
                        }
                    }
                if (isItemAdded) {
                    isItemAdded = false;
                } else {
                    searchList.add(null);
                    isItemAdded = false;
                }
                break;
            case 4:
                try{
                for (int i = 0; i < searchResultModel.resultsList.size(); i++) {
                    if (searchResultModel.resultsList.get(i).step_1_id.equalsIgnoreCase(personDataList.get(0))
                            && searchResultModel.resultsList.get(i).step_2_id.equalsIgnoreCase(personDataList.get(1))
                            && searchResultModel.resultsList.get(i).step_3_id.equalsIgnoreCase(personDataList.get(2))
                            && searchResultModel.resultsList.get(i).step_4_id.equalsIgnoreCase(personDataList.get(3))) {
                        searchList.add(searchResultModel.resultsList.get(i));
                        isItemAdded = true;
                        break;
                    }
                }
                }catch (NullPointerException e){
                    searchList.add(null);
                }
                if (isItemAdded) {
                    isItemAdded = false;
                } else {
                    searchList.add(null);
                    isItemAdded = false;
                }
                break;
            case 5:
                try{
                    for (int i = 0; i < searchResultModel.resultsList.size(); i++) {
                        if (searchResultModel.resultsList.get(i).step_1_id.equalsIgnoreCase(personDataList.get(0))
                                && searchResultModel.resultsList.get(i).step_2_id.equalsIgnoreCase(personDataList.get(1))
                                && searchResultModel.resultsList.get(i).step_3_id.equalsIgnoreCase(personDataList.get(2))
                                && searchResultModel.resultsList.get(i).step_4_id.equalsIgnoreCase(personDataList.get(3))
                                && searchResultModel.resultsList.get(i).step_5_id.equalsIgnoreCase(personDataList.get(4))) {
                            searchList.add(searchResultModel.resultsList.get(i));
                            isItemAdded = true;
                            break;
                        }
                    }
                }catch (NullPointerException e){
                    searchList.add(null);
                }
                if (isItemAdded) {
                    isItemAdded = false;
                } else {
                    searchList.add(null);
                    isItemAdded = false;
                }
                break;
            case 6:
                try{
                    for (int i = 0; i < searchResultModel.resultsList.size(); i++) {
                        if (searchResultModel.resultsList.get(i).step_1_id.equalsIgnoreCase(personDataList.get(0))
                                && searchResultModel.resultsList.get(i).step_2_id.equalsIgnoreCase(personDataList.get(1))
                                && searchResultModel.resultsList.get(i).step_3_id.equalsIgnoreCase(personDataList.get(2))
                                && searchResultModel.resultsList.get(i).step_4_id.equalsIgnoreCase(personDataList.get(3))
                                && searchResultModel.resultsList.get(i).step_5_id.equalsIgnoreCase(personDataList.get(4))
                                && searchResultModel.resultsList.get(i).step_6_id.equalsIgnoreCase(personDataList.get(5))) {
                            searchList.add(searchResultModel.resultsList.get(i));
                            isItemAdded = true;
                            break;
                        }
                    }
                }catch (NullPointerException e){
                    searchList.add(null);
                }
                if (isItemAdded) {
                    isItemAdded = false;
                } else {
                    searchList.add(null);
                    isItemAdded = false;
                }
                break;
            case 7:
                try{
                    for (int i = 0; i < searchResultModel.resultsList.size(); i++) {
                        if (searchResultModel.resultsList.get(i).step_1_id.equalsIgnoreCase(personDataList.get(0))
                                && searchResultModel.resultsList.get(i).step_2_id.equalsIgnoreCase(personDataList.get(1))
                                && searchResultModel.resultsList.get(i).step_3_id.equalsIgnoreCase(personDataList.get(2))
                                && searchResultModel.resultsList.get(i).step_4_id.equalsIgnoreCase(personDataList.get(3))
                                && searchResultModel.resultsList.get(i).step_5_id.equalsIgnoreCase(personDataList.get(4))
                                && searchResultModel.resultsList.get(i).step_6_id.equalsIgnoreCase(personDataList.get(5))
                                && searchResultModel.resultsList.get(i).step_7_id.equalsIgnoreCase(personDataList.get(6))) {
                            searchList.add(searchResultModel.resultsList.get(i));
                            isItemAdded = true;
                            break;
                        }
                    }
                }catch (NullPointerException e){
                    searchList.add(null);
                }
                if (isItemAdded) {
                    isItemAdded = false;
                } else {
                    searchList.add(null);
                    isItemAdded = false;
                }
                break;
            case 8:
                try{
                    for (int i = 0; i < searchResultModel.resultsList.size(); i++) {
                        if (searchResultModel.resultsList.get(i).step_1_id.equalsIgnoreCase(personDataList.get(0))
                                && searchResultModel.resultsList.get(i).step_2_id.equalsIgnoreCase(personDataList.get(1))
                                && searchResultModel.resultsList.get(i).step_3_id.equalsIgnoreCase(personDataList.get(2))
                                && searchResultModel.resultsList.get(i).step_4_id.equalsIgnoreCase(personDataList.get(3))
                                && searchResultModel.resultsList.get(i).step_5_id.equalsIgnoreCase(personDataList.get(4))
                                && searchResultModel.resultsList.get(i).step_6_id.equalsIgnoreCase(personDataList.get(5))
                                && searchResultModel.resultsList.get(i).step_7_id.equalsIgnoreCase(personDataList.get(6))
                                && searchResultModel.resultsList.get(i).step_8_id.equalsIgnoreCase(personDataList.get(7))) {
                            searchList.add(searchResultModel.resultsList.get(i));
                            isItemAdded = true;
                            break;
                        }
                    }
                }catch (NullPointerException e){
                    searchList.add(null);
                }
                if (isItemAdded) {
                    isItemAdded = false;
                } else {
                    searchList.add(null);
                    isItemAdded = false;
                }
                break;
            case 9:
                try{
                    for (int i = 0; i < searchResultModel.resultsList.size(); i++) {
                        if (searchResultModel.resultsList.get(i).step_1_id.equalsIgnoreCase(personDataList.get(0))
                                && searchResultModel.resultsList.get(i).step_2_id.equalsIgnoreCase(personDataList.get(1))
                                && searchResultModel.resultsList.get(i).step_3_id.equalsIgnoreCase(personDataList.get(2))
                                && searchResultModel.resultsList.get(i).step_4_id.equalsIgnoreCase(personDataList.get(3))
                                && searchResultModel.resultsList.get(i).step_5_id.equalsIgnoreCase(personDataList.get(4))
                                && searchResultModel.resultsList.get(i).step_6_id.equalsIgnoreCase(personDataList.get(5))
                                && searchResultModel.resultsList.get(i).step_7_id.equalsIgnoreCase(personDataList.get(6))
                                && searchResultModel.resultsList.get(i).step_8_id.equalsIgnoreCase(personDataList.get(7))
                                && searchResultModel.resultsList.get(i).step_9_id.equalsIgnoreCase(personDataList.get(8))) {
                            searchList.add(searchResultModel.resultsList.get(i));
                            isItemAdded = true;
                            break;
                        }
                    }
                }catch (NullPointerException e){
                    searchList.add(null);
                }
                if (isItemAdded) {
                    isItemAdded = false;
                } else {
                    searchList.add(null);
                    isItemAdded = false;
                }
                break;
        }

    }

    public void setRecyclerAdapter() {
        rvResults.setLayoutManager(new GridLayoutManager(getActivity(), 1, VERTICAL, false));
        rvResults.setHasFixedSize(true);
        rvResults.setAdapter(new AdapterResult(getActivity(), searchList));
    }

    public void allowBackPressed(){
        noOfPersons = 0;
        selectionList.clear();
        queryList.clear();
        FragmentManager fm = getActivity()
                .getSupportFragmentManager();
        fm.popBackStack ("AdvanceSearchFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        callFragmentWithReplace(R.id.container, AdvanceSearchFragment.newInstance(), "AdvanceSearchFragment");
    }

}