package com.example.project_sem4;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.project_sem4.fragment.StatusAduanFragment;
import com.example.project_sem4.fragment.Tab_fragment.CompleteFragment;
import com.example.project_sem4.fragment.Tab_fragment.InprogressFragment;
import com.example.project_sem4.fragment.Tab_fragment.WaitingFragment;

public class MyViewPagerAdapter extends FragmentStateAdapter {

    public MyViewPagerAdapter(@NonNull StatusAduanFragment fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new WaitingFragment();
            case 1:
                return  new InprogressFragment();
            case 2:
                return new CompleteFragment();
            default:
                return new WaitingFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
