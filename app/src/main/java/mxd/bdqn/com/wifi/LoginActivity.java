package mxd.bdqn.com.wifi;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mxd.bdqn.com.wifi.Helper.DBHelper;
import mxd.bdqn.com.wifi.Helper.Load;
import mxd.bdqn.com.wifi.model.User;

public class LoginActivity extends AppCompatActivity {
    @Bind(R.id.edit_pwd)
    EditText mPwd;
    @Bind(R.id.edit_name)
    EditText mName;
    private ProgressDialog mDialog;
    private DBHelper helper;
    private SQLiteDatabase db;
    private Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mDialog = new ProgressDialog(this);

        mDialog.setMessage("登录中...");
        String loginName = Load.getLoginName(this);
        mName.setText(loginName);


    }

    private User cursorToUser(Cursor cursor) {
        User user = new User();

        user.setName(cursor.getString(cursor.getColumnIndex("name")));
        user.setPwd(cursor.getString(cursor.getColumnIndex("pwd")));

        return user;
    }

    @OnClick(R.id.btn_login)
    public void Onclick(View v) {
        login(mName.getText().toString(), mPwd.getText().toString());
    }

    private void login(String name, String pwd) {

        helper = new DBHelper(this);
        db = helper.getWritableDatabase();
        if (name != null && !name.equals("") && !pwd.equals("") && pwd != null) {
            mDialog.show();
            cursor = db.rawQuery("select * from user", null);
            while (cursor.moveToNext()) {
                name = cursor.getString(1);
                pwd = cursor.getString(2);

            }
            //User user = cursorToUser(cursor);

            if (name.equals(name) && pwd.equals(pwd)) {
                Load.login(this, name);
                mDialog.dismiss();
                startActivity(new Intent(this, HomeActivity.class));
            } else {
                mDialog.dismiss();
                Toast.makeText(this, "登录失败...", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "请检查用户名密码..", Toast.LENGTH_SHORT).show();
        }


    }

    @OnClick({R.id.register, R.id.forgetPwd})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.forgetPwd:
                startActivity(new Intent(this, RegisterActivity.class));
                break;

        }
    }
}
