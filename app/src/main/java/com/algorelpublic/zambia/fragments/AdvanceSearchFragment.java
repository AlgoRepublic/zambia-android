package com.algorelpublic.zambia.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.algorelpublic.zambia.R;
import com.algorelpublic.zambia.Zambia;
import com.algorelpublic.zambia.activities.ZambiaMain;
import com.algorelpublic.zambia.model.SearchCriteriaModel;
import com.algorelpublic.zambia.utils.Constants;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by creater on 7/5/2017.
 */

public class AdvanceSearchFragment extends BaseFragment implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    public static AdvanceSearchFragment instance;
    private View view;
    private TextView tvContactNumber, tvTitle, tvAddress;
    private SearchCriteriaModel searchCriteriaModel;
    ArrayList<SearchCriteriaModel.Criteria> servicesList;
    private SeekBar spNoOfPersons;
    private int stepSize = 1;
    private String noOfPersons;
    private ImageView ivPerson1, ivPerson2, ivPerson3, ivPerson4, ivPerson5;
    private Button btnSkip, btnNext;

    public static AdvanceSearchFragment newInstance() {
        instance = new AdvanceSearchFragment();
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
        appCompatActivity.getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Advance Search</font>"));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_advance_search, container, false);
        init();
        addListener();
        setSeekBar();
        Gson gson = new Gson();
        searchCriteriaModel = gson.fromJson(Zambia.db.getString(Constants.RESPONSE_GSON_SEARCH_CRITERIAS), SearchCriteriaModel.class);
        if (searchCriteriaModel != null)
            setValues();

        return view;
    }

    private void setSeekBar() {
        try {
            spNoOfPersons.setMax(5);
            spNoOfPersons.setProgress(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setValues() {
        if (searchCriteriaModel != null && searchCriteriaModel.criteriaes.size() > 0) {
            servicesList = new ArrayList<>();
            for (int i = 0; i < searchCriteriaModel.criteriaes.size(); i++) {
                if (searchCriteriaModel.criteriaes.get(i).parentId != null && !searchCriteriaModel.criteriaes.get(i).parentId.equalsIgnoreCase("")) {
                    servicesList.add(searchCriteriaModel.criteriaes.get(i));
                }
            }

        }
    }


    private void setSpinner() {
//        Sadapter = new SpinnerAdapter(getActivity(), R.layout.incident_type_item, incidentTypesList);
//        incidenttype.setAdapter(Sadapter);
//        incidenttype.setOnItemSelectedListener(this);
    }


    private void init() {
        tvContactNumber = (TextView) view.findViewById(R.id.tvContactNumber);
        tvAddress = (TextView) view.findViewById(R.id.tvAddress);
        tvAddress = (TextView) view.findViewById(R.id.tvAddress);
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        spNoOfPersons = (SeekBar) view.findViewById(R.id.spNoOfPersons);
        ivPerson1 = (ImageView) view.findViewById(R.id.ivPerson1);
        ivPerson2 = (ImageView) view.findViewById(R.id.ivPerson2);
        ivPerson3 = (ImageView) view.findViewById(R.id.ivPerson3);
        ivPerson4 = (ImageView) view.findViewById(R.id.ivPerson4);
        ivPerson5 = (ImageView) view.findViewById(R.id.ivPerson5);
        btnSkip = (Button) view.findViewById(R.id.btnSkip);
        btnNext = (Button) view.findViewById(R.id.btnNext);


    }

    private void addListener() {
        btnSkip.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        spNoOfPersons.setOnSeekBarChangeListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNext:
                if (noOfPersons.equalsIgnoreCase("0")) {
                    Toast.makeText(getActivity(), "Please Select No. of persons", Toast.LENGTH_SHORT).show();
                    return;
                }
                ((ZambiaMain) getActivity()).callFragmentWithReplace(R.id.container, AdvanceSearchDetailFragment.newInstance(noOfPersons), "");
                break;
            case R.id.btnSkip:
                ((ZambiaMain) getActivity()).callFragmentWithReplace(R.id.container, AdvanceSearchDetailFragment.newInstance("1"), "");

                break;
        }

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        progress = ((int) Math.round(progress / stepSize)) * stepSize;
        seekBar.setProgress(progress);
        noOfPersons = String.valueOf(progress);
        setNoOfPersons();
        Log.d("radio", noOfPersons);
    }

    private void setNoOfPersons() {
        switch (noOfPersons) {
            case "0":
                ivPerson1.setImageResource(R.drawable.un_selected);
                ivPerson2.setImageResource(R.drawable.un_selected);
                ivPerson3.setImageResource(R.drawable.un_selected);
                ivPerson4.setImageResource(R.drawable.un_selected);
                ivPerson5.setImageResource(R.drawable.un_selected);
                break;
            case "1":
                ivPerson1.setImageResource(R.drawable.selected_people);
                ivPerson2.setImageResource(R.drawable.un_selected);
                ivPerson3.setImageResource(R.drawable.un_selected);
                ivPerson4.setImageResource(R.drawable.un_selected);
                ivPerson5.setImageResource(R.drawable.un_selected);
                break;
            case "2":
                ivPerson1.setImageResource(R.drawable.selected_people);
                ivPerson2.setImageResource(R.drawable.selected_people);
                ivPerson3.setImageResource(R.drawable.un_selected);
                ivPerson4.setImageResource(R.drawable.un_selected);
                ivPerson5.setImageResource(R.drawable.un_selected);
                break;
            case "3":
                ivPerson1.setImageResource(R.drawable.selected_people);
                ivPerson2.setImageResource(R.drawable.selected_people);
                ivPerson3.setImageResource(R.drawable.selected_people);
                ivPerson4.setImageResource(R.drawable.un_selected);
                ivPerson5.setImageResource(R.drawable.un_selected);
                break;
            case "4":
                ivPerson1.setImageResource(R.drawable.selected_people);
                ivPerson2.setImageResource(R.drawable.selected_people);
                ivPerson3.setImageResource(R.drawable.selected_people);
                ivPerson4.setImageResource(R.drawable.selected_people);
                ivPerson5.setImageResource(R.drawable.un_selected);
                break;
            case "5":
                ivPerson1.setImageResource(R.drawable.selected_people);
                ivPerson2.setImageResource(R.drawable.selected_people);
                ivPerson3.setImageResource(R.drawable.selected_people);
                ivPerson4.setImageResource(R.drawable.selected_people);
                ivPerson5.setImageResource(R.drawable.selected_people);
                break;

        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}