package ch.com.constructionhunt.ui.sales;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import ch.com.constructionhunt.ui.sales.listview.SalesListFragment;
import ch.com.constructionhunt.ui.sales.map.MapFragmentCh;

/**
 * Created by jurgen on 17.10.2018.
 */

public class SalesPagerAdapter extends FragmentStatePagerAdapter {

    private int mTabCount;

    public SalesPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.mTabCount = 0;
    }

    private String[] tabTitles = new String[]{"List", "Map"};

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return SalesListFragment.newInstance();
            case 1:
                return MapFragmentCh.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mTabCount;
    }

    public void setCount(int count) {
        mTabCount = count;
    }
}
