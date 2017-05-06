package mxd.bdqn.com.wifi.Helper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;

import mxd.bdqn.com.wifi.model.ServiceInfo;
import mxd.bdqn.com.wifi.model.User;

/**
 * Created by Administrator on 2017/5/1 0001.
 */

public class Load {

    public static void login(Context context, String name) {
        SharedPreferences uers = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = uers.edit();
        editor.clear();
        editor.putString("name", name);
        editor.putInt("id", 1);
        editor.apply();
    }

    public static void school(Context context, String name, String code) {
        SharedPreferences uers = context.getSharedPreferences("school", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = uers.edit();
        editor.clear();
        editor.putString("name", name);
        editor.putString("code", code);
        editor.apply();
    }

    public static void money(Context context, Integer name, String code) {
        SharedPreferences uers = context.getSharedPreferences("money", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = uers.edit();
        editor.clear();
        editor.putInt("money", name);
        editor.putString("code", code);
        editor.apply();
    }

    public static Integer getMoney(Context context) {
        SharedPreferences uers = context.getSharedPreferences("money", Context.MODE_PRIVATE);
        return uers.getInt("money", 0);
    }

    public static String getSchoolName(Context context) {
        SharedPreferences uers = context.getSharedPreferences("school", Context.MODE_PRIVATE);
        return uers.getString("name", "请完善个人信息");
    }

    public static String getSchoolCode(Context context) {
        SharedPreferences uers = context.getSharedPreferences("school", Context.MODE_PRIVATE);
        return uers.getString("code", null);
    }

    public static Integer getLoginId(Context context) {
        SharedPreferences uers = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        return uers.getInt("id", 0);
    }

    public static String getLoginName(Context context) {
        SharedPreferences uers = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        return uers.getString("name", null);
    }

    public static void clear(Context context, String table) {
        SharedPreferences ddd = context.getSharedPreferences(table, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = ddd.edit();
        editor.clear();
        editor.apply();
    }

    public static User cursorToUser(Cursor cursor) {
        User user = new User();

        user.setName(cursor.getString(cursor.getColumnIndex("name")));
        user.setPwd(cursor.getString(cursor.getColumnIndex("pwd")));

        return user;
    }

    public static ServiceInfo cursorToService(Cursor cursor) {
        ServiceInfo user = new ServiceInfo();
        user.setId(cursor.getInt(cursor.getColumnIndex("id")));
        user.setName(cursor.getString(cursor.getColumnIndex("name")));
        user.setImage(cursor.getString(cursor.getColumnIndex("image")));
        user.setUrl(cursor.getString(cursor.getColumnIndex("url")));

        return user;
    }

}
