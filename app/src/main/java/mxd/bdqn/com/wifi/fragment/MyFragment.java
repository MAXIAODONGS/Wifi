package mxd.bdqn.com.wifi.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.security.PublicKey;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mxd.bdqn.com.wifi.Helper.Load;
import mxd.bdqn.com.wifi.LoginActivity;
import mxd.bdqn.com.wifi.MoneyActivity;
import mxd.bdqn.com.wifi.R;
import mxd.bdqn.com.wifi.RunActivity;
import mxd.bdqn.com.wifi.ShouActivity;
import mxd.bdqn.com.wifi.UserDetailsActivity;
import mxd.bdqn.com.wifi.UserInfoActivity;
import mxd.bdqn.com.wifi.UserInfosActivity;
import mxd.bdqn.com.wifi.model.UserInfo;
import mxd.bdqn.com.wifi.view.CircleImageView;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment {

    Context context;
    @Bind(R.id.text_login)
    TextView names;
    @Bind(R.id.image_view)
    CircleImageView circleImageView;

    private WindowManager.LayoutParams params;
    private Bitmap bitmap;

    public MyFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        ButterKnife.bind(this, view);
        SharedPreferences user = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        String name = user.getString("name", null);
        int id = user.getInt("id", 0);
        if (id == 1) {
            names.setText(name);
            circleImageView.setClickable(true);
        }

        // Inflate the layout for this fragment
        return view;
    }

    @OnClick(R.id.image_view)
    public void click(View v) {
        SharedPreferences user = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        int id = user.getInt("id", 0);
        if (id == 1) {
            choosePictures();
        } else {
            startActivity(new Intent(context, LoginActivity.class));
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
            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
            if (bitmap != null) {
                circleImageView.setImageBitmap(bitmap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.call_phone, R.id.back_s, R.id.location_s, R.id.money, R.id.my_shou, R.id.version, R.id.my_info, R.id.my_talk, R.id.my_run})
    public void onClick(View v) {
        String loginName = Load.getLoginName(context);
        switch (v.getId()) {
            case R.id.money:
                if (loginName == null) {
                    Toast.makeText(context, "尊敬的用户请先登录..", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(context, MoneyActivity.class));
                }
                break;
            case R.id.my_run:
                if (loginName == null) {
                    Toast.makeText(context, "尊敬的用户请先登录..", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(context, RunActivity.class));
                }
                break;
            case R.id.call_phone:
                call("13895585204");
                break;
            case R.id.my_talk:
                if (loginName == null) {
                    Toast.makeText(context, "尊敬的用户请先登录..", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(context, UserInfoActivity.class));
                }

                break;
            case R.id.my_shou:

                if (loginName == null) {
                    Toast.makeText(context, "尊敬的用户请先登录..", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(context, ShouActivity.class));
                }


                break;
            case R.id.my_info:

                if (loginName == null) {
                    Toast.makeText(context, "尊敬的用户请先登录..", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(context, UserDetailsActivity.class));

                }
                break;
            case R.id.version:
                new AlertDialog.Builder(context)
                        .setIcon(getResources().getDrawable(R.drawable.ic_loading_fail2))
                        .setTitle("版本更新")
                        .setMessage("当前已经是最新版本...")
                        .create().show();


                break;
            case R.id.back_s:

                if (loginName == null) {
                    Toast.makeText(context, "尊敬的用户请先登录..", Toast.LENGTH_SHORT).show();
                } else {
                    pupu1();

                }

                break;
            case R.id.location_s:

                if (loginName == null) {
                    Toast.makeText(context, "尊敬的用户请先登录..", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(context, "签到成功..", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    DialogInterface.OnClickListener listeners = new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {

            switch (which) {

                case AlertDialog.BUTTON_POSITIVE:
                    call("13895585204");
                    break;

                case AlertDialog.BUTTON_NEGATIVE:

                    break;
                default:
                    break;
            }
        }
    };

    private void call(String telNumber) {


        // 跳转至系统拨打电话界面
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
                + telNumber));

        startActivity(intent);
    }

    private void pupu1() {
        // 将布局文件转换view对象
        View view = LayoutInflater.from(context).inflate(R.layout.layout_bottom_dialog, null);
        // 创建 PopupWindow 对象
        final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        // 必须设置背景
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        // 获取当前activity窗体对象
        params = getActivity().getWindow().getAttributes();
        // 获取到当前窗体对象的透明度
        params.alpha = 0.8F;

        // 设置透明度
        getActivity().getWindow().setAttributes(params);
        //返回绑定点击事件消失
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1.0F;
                getActivity().getWindow().setAttributes(params);
            }
        });

        view.findViewById(R.id.turn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(context, LoginActivity.class));
            }
        });
        view.findViewById(R.id.back_s).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Load.clear(context, "login");
                names.setText("登录/注册");
                Toast.makeText(context, "退出成功", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();


            }
        });
        view.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        // 是否可以获取焦点
        popupWindow.setFocusable(true);

        // 点击其他区域是否消失
        popupWindow.setOutsideTouchable(true);

        popupWindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);


    }

}
