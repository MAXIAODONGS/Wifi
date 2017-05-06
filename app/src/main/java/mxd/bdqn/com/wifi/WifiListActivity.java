package mxd.bdqn.com.wifi;

import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

import mxd.bdqn.com.wifi.Util.WifiAdmin;
import mxd.bdqn.com.wifi.adapter.MyListViewAdapter;
import mxd.bdqn.com.wifi.view.MyListView;
import mxd.bdqn.com.wifi.view.OnNetworkChangeListener;
import mxd.bdqn.com.wifi.view.WifiConDialog;
import mxd.bdqn.com.wifi.view.WifiConnDialog;
import mxd.bdqn.com.wifi.view.WifiStatusDialog;


public class WifiListActivity extends Activity {

    protected static final String TAG = WifiListActivity.class.getSimpleName();

    private static final int REFRESH_CONN = 100;

    private static final int REQ_SET_WIFI = 200;

    // Wifi管理类
    private WifiAdmin mWifiAdmin;
    // 扫描结果列表
    private List<ScanResult> list = new ArrayList<ScanResult>();
    // 显示列表
    private MyListView listView;
    private ToggleButton tgbWifiSwitch;

    private MyListViewAdapter mAdapter;
      private OnNetworkChangeListener mOnNetworkChangeListener = new OnNetworkChangeListener() {

        @Override
        public void onNetWorkDisConnect() {
            getWifiListInfo();
            mAdapter.setDatas(list);
            mAdapter.notifyDataSetChanged();
        }

        @Override
        public void onNetWorkConnect() {
            getWifiListInfo();
            mAdapter.setDatas(list);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            mAdapter.notifyDataSetChanged();
        }
    };
    private WifiManager wifiManager;
    private Context context;
    private TelephonyManager telMgr;

    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);
        //innie();

        initData();
        initView();
        setListener();
        refreshWifiStatusOnTime();
      /*  String nativePhoneNumber = SIMCardInfo.getNativePhoneNumber(this);
        Toast.makeText(context, "" + nativePhoneNumber, Toast.LENGTH_SHORT).show();*/
       /* String providersName = SIMCardInfo.getProvidersName(this);
        Toast.makeText(context, "" + providersName, Toast.LENGTH_SHORT).show();
        String phoneInfo = SIMCardInfo.getPhoneInfo(this);
        Toast.makeText(context, "" + phoneInfo, Toast.LENGTH_SHORT).show();*/


    }


    private void initData() {
        mWifiAdmin = new WifiAdmin(WifiListActivity.this);
        // 获得Wifi列表信息
        getWifiListInfo();
    }

    private void initView() {

        tgbWifiSwitch = (ToggleButton) findViewById(R.id.tgb_wifi_switch);
        listView = (MyListView) findViewById(R.id.freelook_listview);
        mAdapter = new MyListViewAdapter(this, list);
        listView.setAdapter(mAdapter);
        //
        int wifiState = mWifiAdmin.checkState();
        if (wifiState == WifiManager.WIFI_STATE_DISABLED
                || wifiState == WifiManager.WIFI_STATE_DISABLING
                || wifiState == WifiManager.WIFI_STATE_UNKNOWN) {
            tgbWifiSwitch.setChecked(false);
        } else {
            tgbWifiSwitch.setChecked(true);
        }
    }

    private void setListener() {

        tgbWifiSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    Log.w(TAG, "======== open wifi ========");
                    // 打开Wifi
                    mWifiAdmin.openWifi();
                } else {
                    Log.w(TAG, "======== close wifi ========");
//					// 关闭Wifi
//					boolean res = mWifiAdmin.closeWifi();
//					if (!res) {
//						gotoSysCloseWifi();
//					}
                    mWifiAdmin.closeWifi();
                }
            }
        });

        // 设置刷新监听
        listView.setonRefreshListener(new MyListView.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new AsyncTask<Void, Void, Void>() {
                    protected Void doInBackground(Void... params) {
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        getWifiListInfo();
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {
                        mAdapter.setDatas(list);
                        mAdapter.notifyDataSetChanged();
                        listView.onRefreshComplete();
                    }

                }.execute();
            }
        });

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos,
                                    long id) {
                int position = pos - 1;

                ScanResult scanResult = list.get(position);

                String desc = "";
                String descOri = scanResult.capabilities;
                if (descOri.toUpperCase().contains("WPA-PSK")) {
                    desc = "WPA";
                }
                if (descOri.toUpperCase().contains("WPA2-PSK")) {
                    desc = "WPA2";
                }
                if (descOri.toUpperCase().contains("WPA-PSK")
                        && descOri.toUpperCase().contains("WPA2-PSK")) {
                    desc = "WPA/WPA2";
                }

                if (desc.equals("")) {
                    isConnectSelf(scanResult);
                    return;
                }
                isConnect(scanResult);
            }

            private void isConnect(ScanResult scanResult) {
                if (mWifiAdmin.isConnect(scanResult)) {
                    // 已连接，显示连接状态对话框
                    WifiStatusDialog mStatusDialog = new WifiStatusDialog(
                            WifiListActivity.this, R.style.PopDialog,
                            scanResult, mOnNetworkChangeListener);
                    mStatusDialog.show();
                } else {
                    // 未连接显示连接输入对话框
                    WifiConDialog mDialog = new WifiConDialog(
                            WifiListActivity.this, R.style.PopDialog,
                            scanResult, mOnNetworkChangeListener);
                    // WifiConnDialog mDialog = new WifiConnDialog(
                    // WifiListActivity.this, R.style.PopDialog, wifiName,
                    // singlStrength, secuString);
                    mDialog.show();
                }
            }


            private void isConnectSelf(ScanResult scanResult) {
                if (mWifiAdmin.isConnect(scanResult)) {

                    // 已连接，显示连接状态对话框
                    WifiStatusDialog mStatusDialog = new WifiStatusDialog(
                            WifiListActivity.this, R.style.PopDialog,
                            scanResult, mOnNetworkChangeListener);
                    mStatusDialog.show();

                } else {
                    boolean iswifi = mWifiAdmin.connectSpecificAP(scanResult);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    if (iswifi) {
                        Toast.makeText(WifiListActivity.this, "连接成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(WifiListActivity.this, "连接失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    ArrayList<ScanResult> lists;

    public void innie() {

        wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);  //获得系统wifi服务
        lists = (ArrayList<ScanResult>) wifiManager.getScanResults();
        sortByLevel(lists);

    }

    //将搜索到的wifi根据信号强度从强到弱进行排序
    private void sortByLevel(ArrayList<ScanResult> list) {
        for (int i = 0; i < list.size(); i++)
            for (int j = 1; j < list.size(); j++) {
                if (list.get(i).level < list.get(j).level)  //level属性即为强度
                {
                    ScanResult temp = null;
                    temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        Log.e(TAG, "sortByLevel: " + list.toString());
    }

    private void getWifiListInfo() {
        System.out.println("WifiListActivity#getWifiListInfo");
        mWifiAdmin.startScan();
        List<ScanResult> tmpList = mWifiAdmin.getWifiList();
        Log.e(TAG, "getWifiListInfo: " + tmpList.toString());
        if (tmpList == null) {
            list.clear();
        } else {
            list = tmpList;
        }
    }

    private Handler mHandler = new MyHandler(this);

    protected boolean isUpdate = true;

    private static class MyHandler extends Handler {

        private WeakReference<WifiListActivity> reference;

        public MyHandler(WifiListActivity activity) {
            this.reference = new WeakReference<WifiListActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {

            WifiListActivity activity = reference.get();

            switch (msg.what) {
                case REFRESH_CONN:
                    activity.getWifiListInfo();
                    activity.mAdapter.setDatas(activity.list);
                    activity.mAdapter.notifyDataSetChanged();
                    break;

                default:
                    break;
            }

            super.handleMessage(msg);
        }
    }

    /**
     * Function:定时刷新Wifi列表信息<br>
     *
     * @author ZYT DateTime 2014-5-15 上午9:14:34<br>
     * <br>
     */
    private void refreshWifiStatusOnTime() {
        new Thread() {
            public void run() {
                while (isUpdate) {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mHandler.sendEmptyMessage(REFRESH_CONN);
                }
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isUpdate = false;
    }

    /**
     * Function:到系统中设置wifi，如果用户手动关闭失败，跳转到系统中进行关闭Wifi<br>
     *
     * @author ZYT DateTime 2014-5-15 上午10:03:15<br>
     * <br>
     */
    private void gotoSysCloseWifi() {

        Intent intent = new Intent("android.settings.WIFI_SETTINGS");
        intent.setComponent(new ComponentName("com.android.settings",
                "com.android.settings.Settings$WifiSettingsActivity"));
        startActivityForResult(intent, REQ_SET_WIFI);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_SET_WIFI:
                // 处理改变wifi状态结果
                //
                int wifiState = mWifiAdmin.checkState();
                if (wifiState == WifiManager.WIFI_STATE_DISABLED
                        || wifiState == WifiManager.WIFI_STATE_DISABLING
                        || wifiState == WifiManager.WIFI_STATE_UNKNOWN) {
                    tgbWifiSwitch.setChecked(false);
                } else {
                    tgbWifiSwitch.setChecked(true);
                }
                break;

            default:
                break;
        }
    }
}
