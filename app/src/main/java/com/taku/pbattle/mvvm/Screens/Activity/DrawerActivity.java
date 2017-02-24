package com.taku.pbattle.mvvm.Screens.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.taku.pbattle.mvvm.Model.DrawerItem;
import com.taku.pbattle.mvvm.R;
import com.taku.pbattle.mvvm.Screens.Adapter.DrawerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by TAKU on 2017/02/24.
 */

public class DrawerActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private DrawerAdapter mDrawerAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    public static ActivityType whichItem;

    protected DrawerLayout mDrawerLayout;
    protected ListView mDrawerListView;
    protected Toolbar mToolbar;
    private View mDrawerHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerListView = (ListView) findViewById(R.id.drawer_list);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                if (whichItem == null) {
                    return;
                }
                Intent intent;
                switch (whichItem) {
                    case MainActivity:
                        intent = new Intent(DrawerActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case ListActivity:
                        intent = new Intent(DrawerActivity.this, ListActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case SettingActivity:
                        intent = new Intent(DrawerActivity.this, SettingActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    default:
                        break;
                }
                whichItem = null;
            }
        };
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerAdapter = new DrawerAdapter(this, 0, setDrawerItems(this));

        if (mDrawerListView.getHeaderViewsCount() == 0) {
            mDrawerHeader = getLayoutInflater().inflate(R.layout.header_drawer, null);
            mDrawerListView.addHeaderView(mDrawerHeader, null, false);
        }
        mDrawerListView.setOnItemClickListener(this);
        mDrawerListView.setAdapter(mDrawerAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerToggle.onOptionsItemSelected(item);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getId() == R.id.drawer_list) {
            whichItem = null;
            mDrawerLayout.closeDrawers();
            switch (i) {
                case 1:
                    if (this instanceof MainActivity) {
                        return;
                    }
                    whichItem = ActivityType.MainActivity;
                    break;
                case 2:
                    if (this instanceof ListActivity) {
                        return;
                    }
                    whichItem = ActivityType.ListActivity;
                    break;
                case 3:
                    if (this instanceof SettingActivity) {
                        return;
                    }
                    whichItem = ActivityType.SettingActivity;
                    break;
            }
        }
    }

    private enum ActivityType {
        MainActivity,
        ListActivity,
        SettingActivity
    }

    private List<DrawerItem> setDrawerItems(Context context) {
        List<String> actions = Arrays.asList(context.getResources().getStringArray(R.array.drawer_actions));
        List<DrawerItem> navigationItems = new ArrayList<>();
        for (int i = 0; i < actions.size(); i++) {
            DrawerItem drawerItem = new DrawerItem(actions.get(i));
            navigationItems.add(drawerItem);
        }
        return navigationItems;
    }
}
