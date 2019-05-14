package co.edu.konradlorenz.cardview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.io.Serializable;


public class SeasonAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    Bundle arg = new Bundle();

    public SeasonAdapter(FragmentManager fm, int NumOfTabs, Serializable serializable) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        System.out.println(serializable.toString());
        arg.putSerializable("capitulos", serializable);

    }



    @Override
    public Fragment getItem(int position) {
        Fragment fragment = DynamicFragment.newInstance(position);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
