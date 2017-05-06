package mxd.bdqn.com.wifi;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mxd.bdqn.com.wifi.Helper.Load;

public class MoneyActivity extends AppCompatActivity {

    @Bind(R.id.text_money)
    TextView text;
    @Bind(R.id.tools)
    Toolbar toolbar;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            text.setText("0");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        Integer money = Load.getMoney(this);
        text.setText(money.toString());
    }

    @OnClick({R.id.back, R.id.chong, R.id.ti, R.id.my_acount, R.id.my_acount_detail, R.id.my_wifi_detail})
    public void Onclick(View view) {
        switch (view.getId()) {
            case R.id.back:
                startActivity(new Intent(this, HomeActivity.class));
                break;
            case R.id.chong:
                startActivity(new Intent(this, ChongzhiActivity.class));
                break;
            case R.id.my_acount:
                Toast.makeText(this, "没有开启", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ti:
                if (!text.getText().toString().equals(0)) {

                    Load.clear(this, "money");
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            mHandler.sendEmptyMessage(0);
                        }
                    }.start();
                    Toast.makeText(this, "提现成功...", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "提现失败...", Toast.LENGTH_SHORT).show();
                }


                break;
            case R.id.my_acount_detail:
                Toast.makeText(this, "没有开启", Toast.LENGTH_SHORT).show();
                break;
            case R.id.my_wifi_detail:
                Toast.makeText(this, "没有开启", Toast.LENGTH_SHORT).show();
                break;
        }

    }

}
