package mxd.bdqn.com.wifi;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import mxd.bdqn.com.wifi.Helper.Load;
import mxd.bdqn.com.wifi.Util.SIMCardInfo;
import mxd.bdqn.com.wifi.Util.Util;
import mxd.bdqn.com.wifi.fragment.DiscoverFragment;
import mxd.bdqn.com.wifi.fragment.HomeFragment;
import mxd.bdqn.com.wifi.fragment.MyFragment;

public class HomeActivity extends AppCompatActivity {
    private final static String[] TITLE = {"首页", "发现", "我的"};
    private final static int[] NAV_ICON = {R.drawable.nav_home, R.drawable.nav_discover, R.drawable.nav_my};
    private static final int REQUEST_PERMISSION = 0x001;
    private static final String TAG = "HomeActivity";
    private double exitTime;
    @Bind(R.id.home_tool)
    Toolbar toolbar;
    @Bind(R.id.home_view_pager)
    ViewPager viewPager;
    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.schoolName)
    TextView schoolName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        // 检查权限 存储设备的读写权限
        int permissionCheck1 = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_WIFI_STATE);
        int permissionCheck2 = ContextCompat.checkSelfPermission(this, Manifest.permission.CHANGE_NETWORK_STATE);
        // 相机的使用权限
        int permissionCheck3 = ContextCompat.checkSelfPermission(this, Manifest.permission.CHANGE_WIFI_STATE);
        // 音频使用权限
        int permissionCheck4 = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE);
        int permissionCheck5 = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_WIFI_STATE);
        int permissionCheck6 = ContextCompat.checkSelfPermission(this, Manifest.permission.WAKE_LOCK);
        int permissionCheck7 = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        int permissionCheck8 = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int permissionCheck9 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        // 检查是否有权限
        if (permissionCheck1 != PackageManager.PERMISSION_GRANTED ||
                permissionCheck2 != PackageManager.PERMISSION_GRANTED ||
                permissionCheck3 != PackageManager.PERMISSION_GRANTED ||
                permissionCheck4 != PackageManager.PERMISSION_GRANTED ||
                permissionCheck5 != PackageManager.PERMISSION_GRANTED ||
                permissionCheck6 != PackageManager.PERMISSION_GRANTED ||
                permissionCheck8 != PackageManager.PERMISSION_GRANTED ||
                permissionCheck9 != PackageManager.PERMISSION_GRANTED ||
                permissionCheck7 != PackageManager.PERMISSION_GRANTED) {

            // 动态申请权限
            ActivityCompat.requestPermissions(this, new String[]{
                            Manifest.permission.ACCESS_WIFI_STATE,//相机
                            Manifest.permission.CHANGE_NETWORK_STATE,//写文件
                            Manifest.permission.CHANGE_WIFI_STATE,//读文件
                            Manifest.permission.ACCESS_NETWORK_STATE,//音频
                            Manifest.permission.WAKE_LOCK,//相机
                            Manifest.permission.ACCESS_COARSE_LOCATION,//写文件
                            Manifest.permission.READ_PHONE_STATE,//读文件
                            Manifest.permission.ACCESS_FINE_LOCATION,//读文件

                    },
                    REQUEST_PERMISSION);
        } else {
            //执行原本逻辑  初始化view设置事件
            initView();
        }
    }

    /**
     * 处理权限请求结果
     *
     * @param requestCode  权限请求码
     * @param permissions  请求的权限列表
     * @param grantResults 请求的结果集
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION) {
            if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                initView();
            } else {
                Toast.makeText(HomeActivity.this, "无权限使用", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void initView() {
        setSupportActionBar(toolbar);
        String nativePhoneNumber = SIMCardInfo.getPhoneInfo(this);
        Log.e(TAG, "initView: " + nativePhoneNumber);
        String providersName = SIMCardInfo.getProvidersName(this);

        Log.e(TAG, "initView: " + providersName);
        viewPager.setAdapter(new MyAdpter(getSupportFragmentManager()));

        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < TITLE.length; i++) {

            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setText(TITLE[i]);
            tab.setIcon(NAV_ICON[i]);
        }
        String schoolName = Load.getSchoolName(this);
        this.schoolName.setText(schoolName);
    }

    class MyAdpter extends FragmentPagerAdapter {

        public MyAdpter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            Fragment fragement = null;
            switch (position) {

                case 0:
                    fragement = new HomeFragment();
                    break;
                case 1:
                    fragement = new DiscoverFragment();
                    break;
                case 2:
                    fragement = new MyFragment();
                    break;

            }


            return fragement;
        }

        @Override
        public int getCount() {
            return TITLE.length;
        }
    }

    /**
     * 返回键
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

            if ((System.currentTimeMillis() - exitTime) > 2000)  //System.currentTimeMillis()无论何时调用，肯定大于2000
            {
                Toast.makeText(getApplicationContext(), "再按一次退出", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.about, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                Util.share(this, "一个人不是说让自己大气自己就真的大气了，这种大气绝对与自己的成就有关。当一个人真正变得大气的时候，也就不在乎外界对你的任何指责和批评，因为你的内心始终自信满满，你把时间放在做更有意义的事情当中，你没有时间去理会这些琐碎的谣言。你将一切针对你的针尖锋芒置之于外。");
                break;
            case R.id.net:
                String loginName = Load.getLoginName(this);
                if (loginName != null) {
                    if (schoolName.equals("请完善个人信息") || schoolName == null) {
                        Toast.makeText(this, "请完善个人信息....", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, UserInfosActivity.class));
                    } else {
                        startActivity(new Intent(this, WifiListActivity.class));
                    }
                } else {
                    Toast.makeText(this, "您还没有登录...", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(this, LoginActivity.class));
                }


        }

        return super.onOptionsItemSelected(item);
    }

}
