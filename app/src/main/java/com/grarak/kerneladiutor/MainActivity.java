/*
 * Copyright (C) 2015 Willi Ye
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.grarak.kerneladiutor;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.LightingColorFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.grarak.kerneladiutor.elements.ListAdapter;
import com.grarak.kerneladiutor.elements.ScrimInsetsFrameLayout;
import com.grarak.kerneladiutor.elements.SplashView;
import com.grarak.kerneladiutor.fragments.information.FrequencyTableFragment;
import com.grarak.kerneladiutor.fragments.information.KernelInformationFragment;
import com.grarak.kerneladiutor.fragments.kernel.BatteryFragment;
import com.grarak.kerneladiutor.fragments.kernel.CPUFragment;
import com.grarak.kerneladiutor.fragments.kernel.CPUHotplugFragment;
import com.grarak.kerneladiutor.fragments.kernel.CPUVoltageFragment;
import com.grarak.kerneladiutor.fragments.kernel.GPUFragment;
import com.grarak.kerneladiutor.fragments.kernel.IOFragment;
import com.grarak.kerneladiutor.fragments.kernel.KSMFragment;
import com.grarak.kerneladiutor.fragments.kernel.LMKFragment;
import com.grarak.kerneladiutor.fragments.kernel.MiscFragment;
import com.grarak.kerneladiutor.fragments.kernel.ScreenFragment;
import com.grarak.kerneladiutor.fragments.kernel.SoundFragment;
import com.grarak.kerneladiutor.fragments.kernel.VMFragment;
import com.grarak.kerneladiutor.fragments.kernel.WakeFragment;
import com.grarak.kerneladiutor.fragments.other.AboutusFragment;
import com.grarak.kerneladiutor.fragments.other.SettingsFragment;
import com.grarak.kerneladiutor.fragments.tools.BuildpropFragment;
import com.grarak.kerneladiutor.fragments.tools.ProfileFragment;
import com.grarak.kerneladiutor.utils.Constants;
import com.grarak.kerneladiutor.utils.Utils;
import com.grarak.kerneladiutor.utils.kernel.CPUHotplug;
import com.grarak.kerneladiutor.utils.kernel.CPUVoltage;
import com.grarak.kerneladiutor.utils.kernel.GPU;
import com.grarak.kerneladiutor.utils.kernel.KSM;
import com.grarak.kerneladiutor.utils.kernel.LMK;
import com.grarak.kerneladiutor.utils.kernel.Screen;
import com.grarak.kerneladiutor.utils.kernel.Sound;
import com.grarak.kerneladiutor.utils.kernel.Wake;
import com.grarak.kerneladiutor.utils.root.RootUtils;

/**
 * Created by willi on 01.12.14.
 */
public class MainActivity extends ActionBarActivity implements Constants {

    private static Context context;
    private boolean hasRoot;
    private boolean hasBusybox;

    private ProgressBar progressBar;
    private Toolbar toolbar;

    private ActionBarDrawerToggle mDrawerToggle;

    private DrawerLayout mDrawerLayout;
    private ScrimInsetsFrameLayout mScrimInsetsFrameLayout;
    private ListView mDrawerList;
    private SplashView mSplashView;

    public static String LAUNCH_INTENT = "launch_section";
    private String LAUNCH_NAME;
    private int cur_position;

    private AlertDialog betaDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (context != null) {
            RootUtils.closeSU();
            ((Activity) context).finish();
        }
        context = this;
        Utils.DARKTHEME = Utils.getBoolean("darktheme", false, this);

        if (Utils.DARKTHEME) super.setTheme(R.style.AppThemeDark);
        if (Utils.getBoolean("forceenglish", false, this)) Utils.setLocale("en", this);
        try {
            LAUNCH_NAME = getIntent().getStringExtra(LAUNCH_INTENT);
            if (LAUNCH_NAME == null && VERSION_NAME.contains("beta") && Utils.getBoolean("betainfo", true, this))
                betaDialog = new AlertDialog.Builder(MainActivity.this)
                        .setMessage(getString(R.string.beta_message, VERSION_NAME))
                        .setNeutralButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (Utils.DARKTHEME) toolbar.setPopupTheme(R.style.ThemeOverlay_AppCompat_Dark);
        setSupportActionBar(toolbar);

        progressBar = new ProgressBar(this);
        progressBar.getIndeterminateDrawable().setColorFilter(new LightingColorFilter(0xFF000000,
                getResources().getColor(android.R.color.white)));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM, ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(progressBar, new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT, Gravity.CENTER_VERTICAL | Gravity.END));

        if (mDrawerLayout != null && mScrimInsetsFrameLayout != null)
            mDrawerLayout.closeDrawer(mScrimInsetsFrameLayout);

        new Task().execute();
    }

    private void selectItem(int position) {
        Fragment fragment = mList.get(position).getFragment();

        if (fragment == null || cur_position == position) {
            mDrawerList.setItemChecked(cur_position, true);
            return;
        }

        mDrawerLayout.closeDrawer(mScrimInsetsFrameLayout);

        cur_position = position;

        try {
            getFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle(mList.get(position).getTitle());
        mDrawerList.setItemChecked(position, true);
    }

    private void setList() {
        mList.clear();
        mList.add(new ListAdapter.MainHeader());
        mList.add(new ListAdapter.Header(getString(R.string.information)));
        mList.add(new ListAdapter.Item(getString(R.string.kernel_information), new KernelInformationFragment()));
        mList.add(new ListAdapter.Item(getString(R.string.frequency_table), new FrequencyTableFragment()));
        mList.add(new ListAdapter.Header(getString(R.string.kernel)));
        mList.add(new ListAdapter.Item(getString(R.string.cpu), new CPUFragment()));
        if (CPUVoltage.hasCpuVoltage())
            mList.add(new ListAdapter.Item(getString(R.string.cpu_voltage), new CPUVoltageFragment()));
        if (CPUHotplug.hasCpuHotplug())
            mList.add(new ListAdapter.Item(getString(R.string.cpu_hotplug), new CPUHotplugFragment()));
        if (GPU.hasGpuControl())
            mList.add(new ListAdapter.Item(getString(R.string.gpu), new GPUFragment()));
        if (Screen.hasScreen())
            mList.add(new ListAdapter.Item(getString(R.string.screen), new ScreenFragment()));
        if (Wake.hasWake())
            mList.add(new ListAdapter.Item(getString(R.string.wake_controls), new WakeFragment()));
        if (Sound.hasSound())
            mList.add(new ListAdapter.Item(getString(R.string.sound), new SoundFragment()));
        mList.add(new ListAdapter.Item(getString(R.string.battery), new BatteryFragment()));
        mList.add(new ListAdapter.Item(getString(R.string.io_scheduler), new IOFragment()));
        if (KSM.hasKsm())
            mList.add(new ListAdapter.Item(getString(R.string.ksm), new KSMFragment()));
        if (LMK.getMinFrees() != null)
            mList.add(new ListAdapter.Item(getString(R.string.low_memory_killer), new LMKFragment()));
        mList.add(new ListAdapter.Item(getString(R.string.virtual_memory), new VMFragment()));
        mList.add(new ListAdapter.Item(getString(R.string.misc_controls), new MiscFragment()));
        mList.add(new ListAdapter.Header(getString(R.string.tools)));
        mList.add(new ListAdapter.Item(getString(R.string.build_prop_editor), new BuildpropFragment()));
        mList.add(new ListAdapter.Item(getString(R.string.profile), new ProfileFragment()));
        mList.add(new ListAdapter.Header(getString(R.string.other)));
        mList.add(new ListAdapter.Item(getString(R.string.settings), new SettingsFragment()));
        mList.add(new ListAdapter.Item(getString(R.string.about_us), new AboutusFragment()));
    }

    private void setView() {
        mScrimInsetsFrameLayout = (ScrimInsetsFrameLayout) findViewById(R.id.scrimInsetsFrameLayout);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setStatusBarBackgroundColor(getResources().getColor(R.color.color_primary_dark));
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerList = (ListView) findViewById(R.id.drawer_list);
        mSplashView = (SplashView) findViewById(R.id.splash_view);
    }

    private void setInterface() {
        mScrimInsetsFrameLayout.setLayoutParams(getDrawerParams());
        if (Utils.DARKTHEME)
            mScrimInsetsFrameLayout.setBackgroundColor(getResources().getColor(R.color.navigationdrawer_background_dark));
        mDrawerList.setAdapter(new ListAdapter.Adapter(this, mList));
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, mDrawerLayout, toolbar, 0, 0) {
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        if (Utils.DARKTHEME)
            mDrawerLayout.setBackgroundColor(getResources().getColor(R.color.black));

        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                if (mDrawerToggle != null) mDrawerToggle.syncState();
            }
        });
    }

    private class Task extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            setView();
        }

        @Override
        protected String doInBackground(Void... params) {
            // Check root access and busybox installation
            if (RootUtils.rooted()) hasRoot = RootUtils.rootAccess();
            if (hasRoot) hasBusybox = RootUtils.busyboxInstalled();

            if (hasRoot && hasBusybox) {
                RootUtils.su = new RootUtils.SU();

                String[] permission = new String[]{
                        CPU_MAX_FREQ, CPU_MIN_FREQ, CPU_SCALING_GOVERNOR, LMK_MINFREE
                };
                for (String file : permission)
                    RootUtils.runCommand("chmod 444 " + file);

                setList();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (!hasRoot || !hasBusybox) {
                Intent i = new Intent(MainActivity.this, TextActivity.class);
                Bundle args = new Bundle();
                args.putString(TextActivity.ARG_TEXT, !hasRoot ? getString(R.string.no_root)
                        : getString(R.string.no_busybox));
                Log.d(TAG, !hasRoot ? getString(R.string.no_root) : getString(R.string.no_busybox));
                i.putExtras(args);
                startActivity(i);

                if (betaDialog != null) betaDialog.dismiss();
                cancel(true);
                finish();
                return;
            }

            mSplashView.finish();
            setInterface();
            try {
                ((ViewGroup) progressBar.getParent()).removeView(progressBar);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            if (LAUNCH_NAME == null) LAUNCH_NAME = KernelInformationFragment.class.getSimpleName();
            for (int i = 0; i < mList.size(); i++) {
                if (mList.get(i).getFragment() != null)
                    if (LAUNCH_NAME.equals(mList.get(i).getFragment().getClass().getSimpleName()))
                        selectItem(i);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mScrimInsetsFrameLayout != null)
            mScrimInsetsFrameLayout.setLayoutParams(getDrawerParams());
        if (mDrawerToggle != null) mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        if (!mDrawerLayout.isDrawerOpen(mScrimInsetsFrameLayout)) super.onBackPressed();
        else mDrawerLayout.closeDrawer(mScrimInsetsFrameLayout);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RootUtils.closeSU();
    }

    public static void destroy() {
        if (context != null) ((Activity) context).finish();
    }

    private DrawerLayout.LayoutParams getDrawerParams() {
        DrawerLayout.LayoutParams params = (DrawerLayout.LayoutParams) mScrimInsetsFrameLayout.getLayoutParams();
        int width = getResources().getDisplayMetrics().widthPixels;

        boolean tablet = Utils.isTablet(this);
        int actionBarSize = Utils.getActionBarHeight(this);
        if (Utils.getScreenOrientation(this) == Configuration.ORIENTATION_LANDSCAPE) {
            params.width = width / 2;
            if (tablet) params.width -= actionBarSize + 30;
        } else params.width = tablet ? width / 2 : width - actionBarSize;

        return params;
    }

}
