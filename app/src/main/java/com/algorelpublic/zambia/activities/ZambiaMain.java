package com.algorelpublic.zambia.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

import com.algorelpublic.zambia.R;
import com.algorelpublic.zambia.fragments.AboutUsFragment;
import com.algorelpublic.zambia.fragments.AdvanceSearchFragment;
import com.algorelpublic.zambia.fragments.ContactUsFragment;
import com.algorelpublic.zambia.fragments.FavouriteFragment;
import com.algorelpublic.zambia.fragments.GuidelinesFragment;
import com.algorelpublic.zambia.fragments.HelpLineFragment;
import com.algorelpublic.zambia.fragments.MedicineFragment;
import com.algorelpublic.zambia.fragments.ToolsFragment;
import com.androidquery.AQuery;

public class ZambiaMain extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    MenuItem medicines, guidelineReview;
    public static CheckBox favouriteCheckBox;

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
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerLayout = navigationView.getHeaderView(0);
        setItems(navigationView);
        navigationView.setNavigationItemSelectedListener(this);


    }

    private void setItems(NavigationView navigationView) {
        // get menu from navigationView
        Menu menu = navigationView.getMenu();
        // find MenuItem you want to change
        medicines = menu.findItem(R.id.medicines);
        guidelineReview = menu.findItem(R.id.guideline);
        guidelineReview.setVisible(false);
        callFragmentWithReplace(R.id.container, GuidelinesFragment.newInstance(), null);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.isChecked()) menuItem.setChecked(false);
        else menuItem.setChecked(true);

        //Closing drawer on item click
        drawer.closeDrawers();
        clearBackStack();
        //Check to see which item was being clicked and perform appropriate action
        switch (menuItem.getItemId()) {

            //Replacing the main content with ContentFragment Which is our Inbox View;
            case R.id.medicines:
                callFragmentWithReplace(R.id.container, MedicineFragment.newInstance(), null);
                guidelineReview.setVisible(true);
                medicines.setVisible(false);
                return true;
            case R.id.guideline:
                callFragmentWithReplace(R.id.container, GuidelinesFragment.newInstance(), null);
                guidelineReview.setVisible(false);
                medicines.setVisible(true);

                return true;
            case R.id.advance_search:
                callFragmentWithReplace(R.id.container, AdvanceSearchFragment.newInstance(), null);
                break;
            case R.id.tools:
                callFragmentWithReplace(R.id.container, ToolsFragment.newInstance(), null);
                break;
            case R.id.add_favorite:
                callFragmentWithReplace(R.id.container, FavouriteFragment.newInstance(), null);
                break;
            case R.id.helpline:
                callFragmentWithReplace(R.id.container, HelpLineFragment.newInstance(), null);
                break;
            case R.id.contact_us:
                callFragmentWithReplace(R.id.container, ContactUsFragment.newInstance(), null);
                break;
            case R.id.about:
                callFragmentWithReplace(R.id.container, AboutUsFragment.newInstance(), null);
                break;
            case R.id.share:
                shareIntent();
                break;
            case R.id.sync:
                break;
        }
        return false;
    }

    private void clearBackStack() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    private void shareIntent() {
        String message = "Zambia";
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(share, "Zambia"));
    }
}
