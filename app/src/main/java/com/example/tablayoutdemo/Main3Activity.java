package com.example.tablayoutdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

public class Main3Activity extends AppCompatActivity implements TabLayout.OnTabSelectedListener , ViewPager.OnPageChangeListener {

    public ViewPager viewPager = null;
    PagerViewAdapter pagerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pagerViewAdapter = new PagerViewAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.fragment_container);
        viewPager.setAdapter(pagerViewAdapter);
        viewPager.addOnPageChangeListener((ViewPager.OnPageChangeListener) this);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.addOnTabSelectedListener(this);

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.getTabAt(position).select();

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
