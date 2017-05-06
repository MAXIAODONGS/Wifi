package mxd.bdqn.com.wifi;

import android.app.Activity;
import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mxd.bdqn.com.wifi.Helper.Load;

public class ChongzhiActivity extends AppCompatActivity {
    @Bind(R.id.edt_password)
    EditText editText;
    @Bind(R.id.tools)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chongzhi);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
    }

    @OnClick({R.id.txt_btn_cancel, R.id.txt_btn_connect})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.txt_btn_cancel:
                this.finish();
                break;
            case R.id.txt_btn_connect:
                String s = editText.getText().toString();
                String loginName = Load.getLoginName(this);
                if (s.length() > 0) {
                    Load.money(this, Integer.valueOf(s), loginName);
                    Toast.makeText(this, "充值成功", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, MoneyActivity.class));
                } else {
                    Toast.makeText(this, "请输入充值金额", Toast.LENGTH_SHORT).show();
                }
                break;


        }

    }

    @OnClick(R.id.back)
    public void Onclick(View view) {

        startActivity(new Intent(this, MoneyActivity.class));

    }
}
