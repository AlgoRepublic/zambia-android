package com.algorelpublic.zambia.Interface;

import com.algorelpublic.zambia.model.MedicineModel;

/**
 * Created by creater on 6/19/2017.
 */

public interface ICheckListener {


    void onChangeListener(MedicineModel.Medicines model, boolean isAdd);


    void expandGroupEvent(int groupPosition, boolean isExpanded);
}
