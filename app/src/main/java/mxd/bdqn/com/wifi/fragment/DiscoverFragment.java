package mxd.bdqn.com.wifi.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import mxd.bdqn.com.wifi.HistoryActivity;
import mxd.bdqn.com.wifi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment {

    @Bind(R.id.vp_container)
    ViewPager viewPager;
    @Bind(R.id.tab_nav_container)
    TabLayout tabLayout;
    Context context;
    private final static String[] TITLE = {
            "话题", "论坛",

    };

    public DiscoverFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dicover, container, false);
        ButterKnife.bind(this, view);
        // //关联
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < TITLE.length; i++) {

            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setText(TITLE[i]);

        }
        return view;
    }

    class MyAdapter extends FragmentPagerAdapter {

        MyAdapter(FragmentManager fm) {
            super(fm);
        }


        /**
         * 返回每一个页卡显示的fragment对象
         *
         * @param position
         * @return
         */
        @Override
        public Fragment getItem(int position) {
            Fragment fragement = null;
            Bundle bundle = null;
            switch (position) {

                case 0:
                    fragement = new TalkFragment();

                    break;
                case 1:
                    fragement = new TalkFragment();
                    break;

            }
            return fragement;
        }

        /**
         * 返回 页卡的数量
         *
         * @return
         */
        @Override
        public int getCount() {
            return TITLE.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLE[position];
        }
    }

}
