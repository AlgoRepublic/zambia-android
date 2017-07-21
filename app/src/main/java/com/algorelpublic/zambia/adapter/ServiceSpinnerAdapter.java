package com.algorelpublic.zambia.adapter;

import com.algorelpublic.zambia.R;
import com.algorelpublic.zambia.model.SearchCriteriaModel;


import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * Created by adilnazir on 12/07/2017.
 */

public class ServiceSpinnerAdapter extends ArrayAdapter<SearchCriteriaModel.Criteria> {

    private Context context;
    private ArrayList<SearchCriteriaModel.Criteria> criteriasList;
    LayoutInflater inflater;

    public ServiceSpinnerAdapter(Context context, int textViewResourceId, ArrayList<SearchCriteriaModel.Criteria> list) {
        super(context, textViewResourceId, list);
        this.context = context;
        this.criteriasList = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
//        return getCustomView(position, convertView, parent);
        View row = inflater.inflate(R.layout.service_item_drop_down, parent, false);
        TextView label = (TextView) row.findViewById(R.id.spinnerItem);
        label.setText(criteriasList.get(position).title);
        return row;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = inflater.inflate(R.layout.service_item, parent, false);
        TextView label = (TextView) row.findViewById(R.id.spinnerItem);
        label.setText(criteriasList.get(position).title);
        return row;

//        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        View row = inflater.inflate(R.layout.service_item, parent, false);
        TextView label = (TextView) row.findViewById(R.id.spinnerItem);
        label.setText(criteriasList.get(position).title);
        return row;
    }

}