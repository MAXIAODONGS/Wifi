package mxd.bdqn.com.wifi;

import android.content.Intent;
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

public class RegisterActivity extends AppCompatActivity {

    @Bind(R.id.back)
    TextView back;
    @Bind(R.id.edit_phone)
    EditText editPhone;
    @Bind(R.id.edit_pwd)
    EditText editPwd;
    @Bind(R.id.edit_rpwd)
    EditText editRPwd;
    private DBHelper helper;
    private SQLiteDatabase db;
    private Cursor cursor;
    private String name;
    private String pwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        helper = new DBHelper(this);
        db = helper.getWritableDatabase();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterActivity.this.finish();
            }
        });
    }

    @OnClick(R.id.btn_login)
    public void onClick(View view) {
        cursor = db.rawQuery("select * from user", null);
        while (cursor.moveToNext()) {
            name = cursor.getString(1);
            pwd = cursor.getString(2);

        }
        if (editPhone.getText().toString().length() < 11) {

            Toast.makeText(this, "请输入十一位手机号", Toast.LENGTH_SHORT).show();
        } else if (!editPwd.getText().toString().equals(editRPwd.getText().toString())) {
            Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
        } else if (editPhone.getText().toString().equals(name)) {
            Toast.makeText(this, "手机号已经注册", Toast.LENGTH_SHORT).show();
        } else {
            register(editPhone.getText().toString(), editPwd.getText().toString());
        }

    }

    private void register(String name, String pwd) {

        db.execSQL("insert into user values(null,'" + name + "','" + pwd + "')");
        Toast.makeText(this, "注册成功..", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, LoginActivity.class));
    }

}
