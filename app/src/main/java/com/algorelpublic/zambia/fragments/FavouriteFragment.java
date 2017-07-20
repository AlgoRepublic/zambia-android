package com.algorelpublic.zambia.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.algorelpublic.zambia.Interface.IClickFavourite;
import com.algorelpublic.zambia.Method.Favourite;
import com.algorelpublic.zambia.R;
import com.algorelpublic.zambia.Zambia;
import com.algorelpublic.zambia.adapter.AdapterFavourite;
import com.algorelpublic.zambia.model.GuidelineModel;
import com.algorelpublic.zambia.model.MedicineModel;
import com.algorelpublic.zambia.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by creater on 6/20/2017.
 */

public class FavouriteFragment extends BaseFragment implements IClickFavourite {

    public static FavouriteFragment instance;
    private View view;
    private ArrayList<Favourite> listParent;
    private HashMap<Favourite, ArrayList<Favourite>> mapChild;
    private ArrayList<Favourite> listChild;
    private ExpandableListView elvGuideline;
    private AdapterFavourite adapterfavourite;
    private ArrayList<Favourite> favourites;
    private MedicineModel medicineModel;
    private GuidelineModel guidelineModel;
    private Gson gson = new Gson();
    private String response;

    public static FavouriteFragment newInstance() {
        instance = new FavouriteFragment();
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
        appCompatActivity.getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Favourite</font>"));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_guideline, container, false);
        init();
        medicineModel = gson.fromJson(Zambia.db.getString(Constants.RESPONSE_GSON_MEDICINES_LINE), MedicineModel.class);
        guidelineModel = gson.fromJson(Zambia.db.getString(Constants.RESPONSE_GSON_GUIDELINE_LINE), GuidelineModel.class);

        favourites = gson.fromJson(Zambia.db.getString(Constants.FAVOURITE_GSON),
                new TypeToken<ArrayList<Favourite>>() {
                }.getType());
        if (favourites != null)
            populateItems();
        return view;
    }

    private void populateItems() {
        listParent = new ArrayList<>();
        mapChild = new HashMap<>();
        listChild = new ArrayList<>();
        if (listParent.size() > 0)
            listParent.clear();
        if (listChild.size() > 0)
            listChild.clear();
        listParent.addAll(favourites);
        for (int i = 0; i < listParent.size(); i++) {
            if (!listParent.get(i).subCategoryId.equalsIgnoreCase("") && !listParent.get(i).subCategoryName.equalsIgnoreCase("")) {
                listChild.add(listParent.get(i));
                mapChild.put(listParent.get(i), listChild);
            }
        }
        adapterfavourite = new AdapterFavourite(listParent, mapChild, getActivity());
        adapterfavourite.setmListener(this);
        elvGuideline.setAdapter(adapterfavourite);
    }

    private void init() {
        elvGuideline = (ExpandableListView) view.findViewById(R.id.elvGuideline);
    }


    @Override
    public void onClick(int pos) {

        switch (favourites.get(pos).type) {
            case Constants.GUIDELINE:
                removeFromGuideline(favourites.get(pos));
                break;
            case Constants.MEDICINE:
                removeFromMedicine(favourites.get(pos));
                break;
        }
        favourites.remove(pos);
        listParent.remove(pos);
        adapterfavourite.notifyDataSetChanged();
    }

    private void removeFromMedicine(Favourite model) {
        if (medicineModel != null && medicineModel.medicines.size() > 0) {
            for (int i = 0; i < medicineModel.medicines.size(); i++) {
                if (medicineModel.medicines.get(i).id.equalsIgnoreCase(model.id))
                    medicineModel.medicines.get(i).isFavourite = false;
            }
        }
    }

    private void removeFromGuideline(Favourite model) {
        if (guidelineModel != null && guidelineModel.guidelines.size() > 0) {
            for (int i = 0; i < guidelineModel.guidelines.size(); i++) {
                if (guidelineModel.guidelines.get(i).id.equalsIgnoreCase(model.id))
                    guidelineModel.guidelines.get(i).isFavourite = false;
            }
        }
    }

    @Override
    public void expandGroupEvent(int groupPosition, boolean isExpanded) {
        if (isExpanded)
            elvGuideline.collapseGroup(groupPosition);
        else
            elvGuideline.expandGroup(groupPosition);
    }

    @Override
    public void onResume() {
        super.onResume();
        setToolBar();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        response = gson.toJson(guidelineModel);
        Zambia.db.putString(Constants.RESPONSE_GSON_GUIDELINE_LINE, response);
        response = gson.toJson(medicineModel);
        Zambia.db.putString(Constants.RESPONSE_GSON_MEDICINES_LINE, response);
        String jsonString = gson.toJson(favourites);
        Zambia.db.putString(Constants.FAVOURITE_GSON, jsonString);
    }
}
