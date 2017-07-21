package com.algorelpublic.zambia.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.algorelpublic.zambia.R;
import com.algorelpublic.zambia.activities.ZambiaMain;

import java.util.ArrayList;

/**
 * Created by android on 7/21/17.
 */

public class AdvanceSearchStepsFragment extends BaseFragment {

    public static AdvanceSearchStepsFragment instance;
    public static int noOfPersons;
    private View view;
    public static int totalPersons=1;
    public static ArrayList<String> selectionList = new ArrayList<>();
    private Button btnPerson1, btnPerson2, btnPerson3, btnPerson4, btnPerson5;
    private Button[] personsArray = {btnPerson1, btnPerson2, btnPerson3, btnPerson4, btnPerson5};
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
        init();
        setPersonTabs(noOfPersons);

        ((ZambiaMain) getActivity()).callFragmentWithReplace(R.id.steps_container,
                AdvanceSearchAllStepsFragment.newInstance(null),"");

        return view;
    }
    //INIT
    private void init() {
        //set tabs buttons
        personsArray[0] = (Button) view.findViewById(R.id.btnPerson1);
        personsArray[1] = (Button) view.findViewById(R.id.btnPerson2);
        personsArray[2] = (Button) view.findViewById(R.id.btnPerson3);
        personsArray[3] = (Button) view.findViewById(R.id.btnPerson4);
        personsArray[4] = (Button) view.findViewById(R.id.btnPerson5);
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
}
