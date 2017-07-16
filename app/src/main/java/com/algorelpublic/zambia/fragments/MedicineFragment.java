package com.algorelpublic.zambia.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.algorelpublic.zambia.Interface.ICheckListener;
import com.algorelpublic.zambia.Method.Favourite;
import com.algorelpublic.zambia.R;
import com.algorelpublic.zambia.Zambia;
import com.algorelpublic.zambia.adapter.AdapterMedicines;
import com.algorelpublic.zambia.model.MedicineModel;
import com.algorelpublic.zambia.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by creater on 6/19/2017.
 */

public class MedicineFragment extends BaseFragment implements ICheckListener {

    public static MedicineFragment instance;
    private View view;
    private ArrayList<MedicineModel.Medicines> listParent;
    private HashMap<MedicineModel.Medicines, ArrayList<MedicineModel.Medicines>> mapChild;
    private ArrayList<MedicineModel.Medicines> listChild;
    private ExpandableListView elvGuideline;
    AdapterMedicines adapterMedicines;
    private MedicineModel medicineModel;
    ArrayList<Favourite> favourites;
    Gson gson = new Gson();

    public static MedicineFragment newInstance() {
        instance = new MedicineFragment();
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
        appCompatActivity.getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Medicines</font>"));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_guideline, container, false);
        init();
        gson = new Gson();
        medicineModel = gson.fromJson(Zambia.db.getString(Constants.RESPONSE_GSON_MEDICINES_LINE), MedicineModel.class);
        favourites = gson.fromJson(Zambia.db.getString(Constants.FAVOURITE_GSON),
                new TypeToken<ArrayList<Favourite>>() {
                }.getType());
        if (medicineModel != null)
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
        listParent.addAll(medicineModel.medicines);
        for (int i = 0; i < listParent.size(); i++) {
            if (!listParent.get(i).subCategoryId.equalsIgnoreCase("") && !listParent.get(i).subCategoryName.equalsIgnoreCase("")) {
                listChild.add(listParent.get(i));
                mapChild.put(listParent.get(i), listChild);
            }
        }
        adapterMedicines = new AdapterMedicines(listParent, mapChild, getActivity());
        adapterMedicines.setmListener(this);
        elvGuideline.setAdapter(adapterMedicines);
    }

    private void init() {
        elvGuideline = (ExpandableListView) view.findViewById(R.id.elvGuideline);
    }

    @Override
    public void onChangeListener(MedicineModel.Medicines model, boolean isAdd) {
        if (isAdd)
            addInFavourite(model);
        else
            removeFromFavourite(model);

    }

    private void removeFromFavourite(MedicineModel.Medicines model) {
        if (favourites != null && favourites.size() > 0) {
            for (int i = 0; i < favourites.size(); i++) {
                if (favourites.get(i).id.equalsIgnoreCase(model.id))
                    favourites.remove(i);
            }
        }
        String jsonString = gson.toJson(favourites);
        Zambia.db.putString(Constants.FAVOURITE_GSON, jsonString);
    }

    private void addInFavourite(MedicineModel.Medicines model) {
        if (favourites != null) {
            Favourite favourite = new Favourite();
            favourite.categoryId = model.categoryId;
            favourite.categoryName = model.categoryName;
            favourite.contentHeading = model.contentHeading;
            favourite.htmlContent = model.htmlContent;
            favourite.id = model.id;
            favourite.subCategoryId = model.subCategoryId;
            favourite.subCategoryName = model.subCategoryName;
            favourite.isFavourite = true;
            favourite.type = Constants.MEDICINE;

            favourites.add(favourite);
            String jsonString = gson.toJson(favourites);
            Zambia.db.putString(Constants.FAVOURITE_GSON, jsonString);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        String response = gson.toJson(medicineModel);
        Zambia.db.putString(Constants.RESPONSE_GSON_MEDICINES_LINE, response);
    }

    @Override
    public void expandGroupEvent(int groupPosition, boolean isExpanded) {
        if (isExpanded)
            elvGuideline.collapseGroup(groupPosition);
        else
            elvGuideline.expandGroup(groupPosition);
    }


    public void setSOSRecyclerAdapter() {
//        rvSOS.setLayoutManager(new GridLayoutManager(getActivity(), 1, VERTICAL, false));
//        rvSOS.setHasFixedSize(true);
//        sosList.addAll(ProfileModel.getInstance().user.address);
//        rvSOS.setAdapter(new AdapterSOS(getActivity(), sosList));
    }

}
