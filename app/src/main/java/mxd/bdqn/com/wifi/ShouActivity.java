package mxd.bdqn.com.wifi;

import android.content.Intent;
import android.nfc.Tag;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mxd.bdqn.com.wifi.Util.Util;
import mxd.bdqn.com.wifi.fragment.DiscoverFragment;
import mxd.bdqn.com.wifi.fragment.MyFragment;
import mxd.bdqn.com.wifi.fragment.SchoolHistoryFragment;

public class ShouActivity extends AppCompatActivity {

    private final static String[] TITLE = {
            "全部", "我的图书", "我的视频", "我的图片", "我的新闻"

    };
    @Bind(R.id.tools)
    Toolbar toolbar;
    @Bind(R.id.vp_container)
    ViewPager viewPager;
    @Bind(R.id.tab_nav_container)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        // //关联
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));

        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < TITLE.length; i++) {

            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setText(TITLE[i]);

        }
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
                    fragement = new SchoolHistoryFragment();

                    break;
                case 1:
                    fragement = new SchoolHistoryFragment();

                    break;
                case 2:
                    fragement = new SchoolHistoryFragment();

                    break;
                case 3:
                    fragement = new SchoolHistoryFragment();

                    break;
                case 4:
                    fragement = new SchoolHistoryFragment();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.share:
                Util.share(this, "一个人不是说让自己大气自己就真的大气了，这种大气绝对与自己的成就有关。当一个人真正变得大气的时候，也就不在乎外界对你的任何指责和批评，因为你的内心始终自信满满，你把时间放在做更有意义的事情当中，你没有时间去理会这些琐碎的谣言。你将一切针对你的针尖锋芒置之于外。");
                break;

        }

        return super.onOptionsItemSelected(item);
    }
    @OnClick({R.id.back})
    public void Onclick(View view) {
        switch (view.getId()) {
            case R.id.back:
                this.finish();
                break;
        }

    }
}
