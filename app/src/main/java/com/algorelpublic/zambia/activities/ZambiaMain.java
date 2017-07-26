package com.algorelpublic.zambia.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;

import com.algorelpublic.zambia.R;
import com.algorelpublic.zambia.Shimmer.Shimmer;
import com.algorelpublic.zambia.Shimmer.ShimmerRadioButton;
import com.algorelpublic.zambia.Zambia;
import com.algorelpublic.zambia.fragments.AboutUsFragment;
import com.algorelpublic.zambia.fragments.AdvanceSearchFragment;
import com.algorelpublic.zambia.fragments.ContactUsFragment;
import com.algorelpublic.zambia.fragments.FavouriteFragment;
import com.algorelpublic.zambia.fragments.GuidelinesFragment;
import com.algorelpublic.zambia.fragments.HelpLineFragment;
import com.algorelpublic.zambia.fragments.MedicineFragment;
import com.algorelpublic.zambia.fragments.SearchResultFragment;
import com.algorelpublic.zambia.fragments.ToolsFragment;
import com.algorelpublic.zambia.utils.Constants;

public class ZambiaMain extends BaseActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    public static CheckBox favouriteCheckBox;
    private ShimmerRadioButton advance_search, guideline, medicines,
            about, tools, add_favorite, helpline, contact_us, share, sync;

    private ShimmerRadioButton[] radioButtons = {advance_search, guideline, medicines,
            about, tools, add_favorite, helpline, contact_us, share, sync};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zambia_main);
        setToolbar();
    }

    public void setToolbar() {
        favouriteCheckBox = (CheckBox) findViewById(R.id.group_chk_box);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.BLACK);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Consolidated Guidelines</font>"));
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        toolbar.setNavigationIcon(R.drawable.ic_view_headline_black_24dp);
        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View v) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        setItems();
    }

    private void setItems() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View view = navigationView.getHeaderView(0);

        radioButtons[0] = (ShimmerRadioButton) view.findViewById(R.id.advance_search);
        radioButtons[1] = (ShimmerRadioButton) view.findViewById(R.id.guideline);
        radioButtons[2] = (ShimmerRadioButton) view.findViewById(R.id.about);
        radioButtons[3] = (ShimmerRadioButton) view.findViewById(R.id.tools);
        radioButtons[4] = (ShimmerRadioButton) view.findViewById(R.id.add_favorite);
        radioButtons[5] = (ShimmerRadioButton) view.findViewById(R.id.medicines);
        radioButtons[6] = (ShimmerRadioButton) view.findViewById(R.id.helpline);
        radioButtons[7] = (ShimmerRadioButton) view.findViewById(R.id.contact_us);
        radioButtons[8] = (ShimmerRadioButton) view.findViewById(R.id.share);
        radioButtons[9] = (ShimmerRadioButton) view.findViewById(R.id.sync);

        for (int loop = 0; loop < radioButtons.length; loop++) {
            radioButtons[loop].setOnClickListener(this);
        }
        Shimmer shimmer = new Shimmer();
        shimmer.start(radioButtons[0]);
        radioButtons[0].setShimmering(true);
        callFragmentWithReplace(R.id.container, GuidelinesFragment.newInstance(), null);

    }

    private void clearBackStack() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    private void shareIntent() {
        String message = getString(R.string.app_name);
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(share, getString(R.string.app_name)));
    }

    /**
     * Take care of popping the fragment back stack or finishing the activity
     * as appropriate.
     */
    @Override
    public void onBackPressed() {
        final SearchResultFragment fragment = (SearchResultFragment) getSupportFragmentManager().findFragmentByTag("SearchResultFragment");
        if(fragment != null){
            fragment.allowBackPressed();
        }else{
            super.onBackPressed();
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //Replacing the main content with ContentFragment Which is our Inbox View;
            case R.id.medicines:
                resetMenu(v);
                callFragmentWithReplace(R.id.container, MedicineFragment.newInstance(), null);
                break;
            case R.id.guideline:
                resetMenu(v);
                callFragmentWithReplace(R.id.container, GuidelinesFragment.newInstance(), null);
                break;
            case R.id.advance_search:
                resetMenu(v);
                callFragmentWithReplace(R.id.container, AdvanceSearchFragment.newInstance(), "AdvanceSearchFragment");
                break;
            case R.id.tools:
                resetMenu(v);
                callFragmentWithReplace(R.id.container, ToolsFragment.newInstance(), null);
                break;
            case R.id.add_favorite:
                resetMenu(v);
                callFragmentWithReplace(R.id.container, FavouriteFragment.newInstance(), null);
                break;
            case R.id.helpline:
                resetMenu(v);
                callFragmentWithReplace(R.id.container, HelpLineFragment.newInstance(), null);
                break;
            case R.id.contact_us:
                resetMenu(v);
                callFragmentWithReplace(R.id.container, ContactUsFragment.newInstance(), null);
                break;
            case R.id.about:
                resetMenu(v);
                callFragmentWithReplace(R.id.container, AboutUsFragment.newInstance(), null);
                break;
            case R.id.share:
                resetMenu(v);
                shareIntent();
                break;
            case R.id.sync:
                resetMenu(v);
                Zambia.db.putInt(Constants.PROGRESS_LOAD_APP, 0);
                showDialog();
                apiHandler.postDelayed(loadAboutUs, API_TIME);
                break;
        }
        drawer.closeDrawers();
    }

    private void resetMenu(View v) {
        for (int loop = 0; loop < radioButtons.length; loop++) {
            radioButtons[loop].setChecked(false);
        }
        ((RadioButton) v).setChecked(true);
    }
}
