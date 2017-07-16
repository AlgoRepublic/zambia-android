package com.algorelpublic.zambia.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.algorelpublic.zambia.R;
import com.algorelpublic.zambia.model.GeneralModel;
import com.algorelpublic.zambia.services.APIService;
import com.algorelpublic.zambia.utils.CallBack;
import com.algorelpublic.zambia.utils.Util;

/**
 * Created by creater on 6/16/2017.
 */

public class ContactUsFragment extends BaseFragment implements View.OnClickListener {

    public static ContactUsFragment instance;
    private View view;
    private Button btnSubmit;
    private EditText edtFeedBack, edtName, edtEmail, edtContactNumber;
    private APIService service;

    public static ContactUsFragment newInstance() {
        instance = new ContactUsFragment();
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
        appCompatActivity.getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Contact Us</font>"));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_contact_us, container, false);
        init();
        addListener();
        return view;
    }


    private void init() {
        edtName = (EditText) view.findViewById(R.id.edtName);
        edtEmail = (EditText) view.findViewById(R.id.edtEmail);
        edtContactNumber = (EditText) view.findViewById(R.id.edtContactNumber);
        edtFeedBack = (EditText) view.findViewById(R.id.edtFeedBack);
        btnSubmit = (Button) view.findViewById(R.id.btnSubmit);
    }

    private void addListener() {
        btnSubmit.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSubmit:
                btnSubmitFeedBack(v);
                break;
        }

    }

    private boolean isContentNull(String content) {
        //TODO: Replace this with your own logic
        return content.length() == 0;
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private void btnSubmitFeedBack(View v) {
        if (isContentNull(edtName.getText().toString())) {
            edtName.requestFocus();
            edtName.setError(getString(R.string.required));
            return;
        }
        if (isContentNull(edtEmail.getText().toString())) {
            edtEmail.requestFocus();
            edtEmail.setError(getString(R.string.required));
            return;
        }
        if (!isEmailValid(edtEmail.getText().toString())) {
            //TODO: Replace this(edtEmail.getText().toString())) {
            edtEmail.requestFocus();
            edtEmail.setError(getString(R.string.invalid));
            return;
        }
        if (isContentNull(edtFeedBack.getText().toString())) {
            edtFeedBack.requestFocus();
            edtFeedBack.setError(getString(R.string.required));
            return;
        }

        Util.hidekeyPad(getContext(), view);
        service = new APIService(getActivity());
        service.submitFeedback(edtName.getText().toString(), edtEmail.getText().toString(),
                edtContactNumber.getText().toString(), edtFeedBack.getText().toString(),
                true, new CallBack(ContactUsFragment.this, "ContactUs"));
    }


    /**
     * ****** this Method must be public ********
     *
     * @param caller
     * @param model
     */

    public void ContactUs(Object caller, Object model) {
        GeneralModel.getInstance().setObj((GeneralModel) model);
        if (GeneralModel.getInstance().status.equalsIgnoreCase("ok")) {
            Toast.makeText(getActivity(), "" + GeneralModel.getInstance().message, Toast.LENGTH_SHORT).show();
        } else {
        }
    }
}