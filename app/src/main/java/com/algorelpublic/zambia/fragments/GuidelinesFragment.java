package com.algorelpublic.zambia.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.algorelpublic.zambia.Interface.IGuidelineCheckListener;
import com.algorelpublic.zambia.Method.Favourite;
import com.algorelpublic.zambia.R;
import com.algorelpublic.zambia.Zambia;
import com.algorelpublic.zambia.adapter.AdapterGuideLine;
import com.algorelpublic.zambia.model.GuidelineModel;
import com.algorelpublic.zambia.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by creater on 6/19/2017.
 */

public class GuidelinesFragment extends BaseFragment implements IGuidelineCheckListener {

    public static GuidelinesFragment instance;
    private View view;
    private ArrayList<GuidelineModel.Guideline> listParent;
    private HashMap<GuidelineModel.Guideline, ArrayList<GuidelineModel.Guideline>> mapChild;
    private ArrayList<GuidelineModel.Guideline> listChild;
    private ExpandableListView elvGuideline;
    AdapterGuideLine adapterGuideLine;
    GuidelineModel guidelineModel;
    ArrayList<Favourite> favourites;
    Gson gson = new Gson();

    public static GuidelinesFragment newInstance() {
        instance = new GuidelinesFragment();
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
        appCompatActivity.getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Consolidated Guidelines</font>"));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_guideline, container, false);
        init();
        Gson gson = new Gson();
        guidelineModel = gson.fromJson(Zambia.db.getString(Constants.RESPONSE_GSON_GUIDELINE_LINE), GuidelineModel.class);
        if (guidelineModel != null)
            populateItems();
        favourites = gson.fromJson(Zambia.db.getString(Constants.FAVOURITE_GSON),
                new TypeToken<ArrayList<Favourite>>() {
                }.getType());
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
        listParent.addAll(guidelineModel.guidelines);
        for (int i = 0; i < listParent.size(); i++) {
            if (!listParent.get(i).subCategoryId.equalsIgnoreCase("") && !listParent.get(i).subCategoryName.equalsIgnoreCase("")) {
                listChild.add(listParent.get(i));
                mapChild.put(listParent.get(i), listChild);
            }
        }
        adapterGuideLine = new AdapterGuideLine(listParent, mapChild, getActivity());
        adapterGuideLine.setmListener(this);
        elvGuideline.setAdapter(adapterGuideLine);
    }

    private void init() {
        elvGuideline = (ExpandableListView) view.findViewById(R.id.elvGuideline);
    }


    @Override
    public void onChangeListener(GuidelineModel.Guideline model, boolean isAdd) {
        if (isAdd)
            addInFavourite(model);
        else
            removeFromFavourite(model);
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

    private void removeFromFavourite(GuidelineModel.Guideline model) {
        if (favourites != null && favourites.size() > 0) {
            for (int i = 0; i < favourites.size(); i++) {
                if (favourites.get(i).id.equalsIgnoreCase(model.id))
                    favourites.remove(i);
            }
        }
        String jsonString = gson.toJson(favourites);
        Zambia.db.putString(Constants.FAVOURITE_GSON, jsonString);
    }

    private void addInFavourite(GuidelineModel.Guideline model) {
        if (favourites != null) {
            Favourite favourite = new Favourite();
            favourite.categoryId = model.categoryId;
            favourite.categoryName = model.categoryName;
            favourite.contentHeading = model.contentHeading;
            favourite.htmlContent = model.htmlContent;
            favourite.id = model.id;
            favourite.subCategoryId = model.subCategoryId;
            favourite.subCategoryName = model.subCategoryName;
            favourite.type = Constants.GUIDELINE;
            favourite.isFavourite = true;
            favourites.add(favourite);
            String jsonString = gson.toJson(favourites);
            Zambia.db.putString(Constants.FAVOURITE_GSON, jsonString);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        String response = gson.toJson(guidelineModel);
        Zambia.db.putString(Constants.RESPONSE_GSON_GUIDELINE_LINE, response);
    }
}
