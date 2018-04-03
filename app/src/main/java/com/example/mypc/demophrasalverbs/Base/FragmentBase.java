package com.example.mypc.demophrasalverbs.Base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.mypc.demophrasalverbs.R;


public class FragmentBase extends Fragment {

    private static FragmentBase intance;

    public static FragmentBase getIntance() {
        if (null == intance) {
            intance = new FragmentBase();
        }
        return intance;
    }

    public void addFragment(Fragment fragment, FragmentManager manager) {
        if (null != manager) {
            String nameFragment = fragment.getClass().getName();
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(R.id.flContent, fragment);
            ft.addToBackStack(nameFragment);
            ft.commit();
            manager.executePendingTransactions();
        }
    }
}