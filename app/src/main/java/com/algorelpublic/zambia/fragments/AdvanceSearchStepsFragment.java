package com.algorelpublic.zambia.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.algorelpublic.zambia.R;
import com.algorelpublic.zambia.activities.ZambiaMain;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by android on 7/21/17.
 */

public class AdvanceSearchStepsFragment extends BaseFragment {

    public static AdvanceSearchStepsFragment instance;
    public static int noOfPersons;
    private View view;
    public static int totalPersons=1;
    private HashMap<String,ArrayList<String>> personHash = new HashMap<>();
    public static ArrayList<String> selectionList = new ArrayList<>();
    public static ArrayList<ArrayList<String>> queryList = new ArrayList<>();

    public static AdvanceSearchStepsFragment newInstance(String persons) {
        noOfPersons = Integer.parseInt(persons);
        instance = new AdvanceSearchStepsFragment();
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_advance_search_steps, container, false);
        ((ZambiaMain) getActivity()).callFragmentWithReplace(R.id.steps_container,
                AdvanceSearchAllStepsFragment.newInstance(null),"AdvanceSearchStepsFragment");

        return view;
    }
}
