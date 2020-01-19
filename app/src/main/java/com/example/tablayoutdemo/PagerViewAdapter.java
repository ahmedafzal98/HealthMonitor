package com.example.tablayoutdemo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

class PagerViewAdapter extends FragmentPagerAdapter {
    public PagerViewAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        switch (position){

            case 0:
                fragment = new fragment_camera();
                break;

            case 1:
                fragment = new fragment_chats();
                break;

            case 2:
                fragment = new fragment_status();
                break;

            case 3:
                fragment= new fragment_personal_profile();
                break;

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
