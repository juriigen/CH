package ch.com.constructionhunt.utils;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;


/**
 * Created by jurgen on 17.10.2018.
 */

public class FragmentUtil {

    public static void animateFragmentIn(FragmentManager fragmentManager, int containerId, Fragment newFragment, String tag) {
        animateFragmentIn(fragmentManager, containerId, newFragment, tag, true);
    }

    public static void animateFragmentIn(FragmentManager fragmentManager, int containerId, Fragment newFragment, String tag, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerId, newFragment, tag);
        if (addToBackStack && !newFragment.isAdded()) {
            fragmentTransaction.addToBackStack(tag);
        }
        fragmentTransaction.commit();
    }
}
