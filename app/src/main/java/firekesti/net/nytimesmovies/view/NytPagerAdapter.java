package firekesti.net.nytimesmovies.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import firekesti.net.nytimesmovies.R;
import firekesti.net.nytimesmovies.movies.LatestPicksFragment;
import firekesti.net.nytimesmovies.mylist.MyListFragment;

/**
 * A PagerAdapter to switch between the main views of the app
 */
class NytPagerAdapter extends FragmentPagerAdapter {

    private String[] pageTitles;

    NytPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        pageTitles = new String[2];
        pageTitles[0] = context.getString(R.string.latest_picks);
        pageTitles[1] = context.getString(R.string.my_list);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return LatestPicksFragment.newInstance();
            case 1:
                return MyListFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return pageTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitles[position];
    }
}
