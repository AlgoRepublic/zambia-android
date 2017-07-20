package com.algorelpublic.zambia.Interface;

import com.algorelpublic.zambia.model.GuidelineModel;

/**
 * Created by creater on 6/20/2017.
 */

public interface IGuidelineCheckListener {

    void onChangeListener(GuidelineModel.Guideline model, boolean isAdd);


    void expandGroupEvent(int groupPosition, boolean isExpanded);
}
