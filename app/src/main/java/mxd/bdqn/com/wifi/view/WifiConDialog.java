package mxd.bdqn.com.wifi.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import mxd.bdqn.com.wifi.ChongzhiActivity;
import mxd.bdqn.com.wifi.Helper.Load;
import mxd.bdqn.com.wifi.MoneyActivity;
import mxd.bdqn.com.wifi.R;
import mxd.bdqn.com.wifi.Util.WifiAdmin;
import mxd.bdqn.com.wifi.Util.WifiConnect;


/**
 * Class Name: WifiConnDialog.java<br>
 * Function:Wifi连接对话框<br>
 * <p>
 * Modifications:<br>
 *
 * @author ZYT DateTime 2014-5-14 下午2:23:37<br>
 * @version 1.0<br>
 *          <br>
 */
public class WifiConDialog extends Dialog {

    private Context context;

    private ScanResult scanResult;
    private String wifiName;
    private int level;
    private String securigyLevel;

    private TextView txtWifiName;
    private TextView txtSinglStrength;
    private TextView txtSecurityLevel;
    private TextView edtPassword;


    private TextView txtBtnConn;
    private TextView txtBtnCancel;

    public WifiConDialog(Context context, int theme) {
        super(context, theme);
    }

    private WifiConDialog(Context context, int theme, String wifiName,
                          int singlStren, String securityLevl) {
        super(context, theme);
        this.context = context;
        this.wifiName = wifiName;
        this.level = singlStren;
        this.securigyLevel = securityLevl;
    }

    public WifiConDialog(Context context, int theme, ScanResult scanResult,
                         OnNetworkChangeListener onNetworkChangeListener) {
        this(context, theme, scanResult.SSID, scanResult.level,
                scanResult.capabilities);
        this.scanResult = scanResult;
        this.onNetworkChangeListener = onNetworkChangeListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_wifi_con);
        setCanceledOnTouchOutside(false);

        initView();
        setListener();
    }

    private void setListener() {

        edtPassword.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (TextUtils.isEmpty(s)) {
                    txtBtnConn.setEnabled(false);


                } else {
                    txtBtnConn.setEnabled(true);

                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        txtBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("txtBtnCancel");
                WifiConDialog.this.dismiss();
            }
        });

        txtBtnConn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                WifiConnect.WifiCipherType type = null;
                if (scanResult.capabilities.toUpperCase().contains("WPA")) {
                    type = WifiConnect.WifiCipherType.WIFICIPHER_WPA;
                } else if (scanResult.capabilities.toUpperCase()
                        .contains("WEP")) {
                    type = WifiConnect.WifiCipherType.WIFICIPHER_WEP;
                } else {
                    type = WifiConnect.WifiCipherType.WIFICIPHER_NOPASS;
                }

                // 连接网络
                WifiAdmin mWifiAdmin = new WifiAdmin(context);
                Integer money = Load.getMoney(context);
                if (money > 0) {
                    boolean bRet = mWifiAdmin.connect(scanResult.SSID, "nn18791000399", type);
                    if (bRet) {
                        showShortToast("认证成功");
                        onNetworkChangeListener.onNetWorkConnect();
                    } else {
                        showShortToast("认证失败");
                        onNetworkChangeListener.onNetWorkConnect();
                    }
                    WifiConDialog.this.dismiss();
                } else {

                    Toast.makeText(context, "账户余额不足请充值", Toast.LENGTH_SHORT).show();
                    WifiConDialog.this.dismiss();
                    context.startActivity(new Intent(context, MoneyActivity.class));
                }

            }
        });
    }

    private void initView() {
        txtWifiName = (TextView) findViewById(R.id.txt_wifi_name);
        txtSinglStrength = (TextView) findViewById(R.id.txt_signal_strength);
        txtSecurityLevel = (TextView) findViewById(R.id.txt_security_level);
        edtPassword = (TextView) findViewById(R.id.edt_password);

        txtBtnCancel = (TextView) findViewById(R.id.txt_btn_cancel);
        txtBtnConn = (TextView) findViewById(R.id.txt_btn_connect);

        txtWifiName.setText(wifiName);
        txtSinglStrength.setText(WifiAdmin.singlLevToStr(level));
        txtSecurityLevel.setText(securigyLevel);
        String loginName = Load.getLoginName(context);
        edtPassword.setText(loginName);

    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
        WindowManager wm = (WindowManager) getContext().getSystemService(
                Context.WINDOW_SERVICE);
        Point size = new Point();
        wm.getDefaultDisplay().getSize(size);

        super.show();
        getWindow().setLayout((int) (size.x * 9 / 10),
                LayoutParams.WRAP_CONTENT);
    }

    private void showShortToast(String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    private OnNetworkChangeListener onNetworkChangeListener;

}
