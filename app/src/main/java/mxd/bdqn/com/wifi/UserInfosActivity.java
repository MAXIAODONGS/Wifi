package mxd.bdqn.com.wifi;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mxd.bdqn.com.wifi.Helper.DBHelper;
import mxd.bdqn.com.wifi.Helper.Load;
import mxd.bdqn.com.wifi.view.CircleImageView;

public class UserInfosActivity extends AppCompatActivity {

    private Bitmap bitmap;
    @Bind(R.id.image_photo)
    CircleImageView circleImageView;
    @Bind(R.id.text_name)
    TextView textView;
    private String loginName;
    @Bind(R.id.spinner_schoolCourse)
    Spinner schoolCource;
    @Bind(R.id.spinner_schoolName)
    Spinner schoolName;
    private final String[] Names = {
            "外事学院", "体育学院",

    };
    private final String[] Code = {
            "计算机科学与技术", "网络工程",
            "软件工程",
    };
    private String name;
    private String s;
    @Bind(R.id.radios)
    RadioGroup radioGroup;
    private CharSequence text;
    @Bind(R.id.edit_stuCode)
    EditText stuCode;
    private DBHelper helper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_infos);
        ButterKnife.bind(this);
        helper = new DBHelper(this);
        db = helper.getWritableDatabase();
        loginName = Load.getLoginName(this);
        textView.setText(loginName);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Names);
        ArrayAdapter dd = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Code);
        schoolCource.setAdapter(dd);
        schoolName.setAdapter(arrayAdapter);
        schoolName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                name = Names[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) UserInfosActivity.this.findViewById(checkedRadioButtonId);
                text = radioButton.getText();
            }
        });
    }

    @OnClick({R.id.btn_login, R.id.image_photo})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                invidate();
                startActivity(new Intent(this, HomeActivity.class));
                break;
            case R.id.image_photo:
                choosePictures();
                break;

        }
        startActivity(new Intent(this, HomeActivity.class));
    }

    private void invidate() {
        if (stuCode.getText().toString().length() < 0) {
            Toast.makeText(this, "信息不完善..请检查信息..", Toast.LENGTH_SHORT).show();

        } else {

            Load.clear(this, "school");

            Load.school(this, name, loginName);

        }


    }

    /**
     * 选择图片
     */
    private void choosePictures() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        //相片类型
        intent.setType("image/*");
        startActivityForResult(intent, 0x001);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;
        try {
            // 获取图片选择结果
            Uri uri = data.getData();
            if (uri == null) return;
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            if (bitmap != null) {
                circleImageView.setImageBitmap(bitmap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
