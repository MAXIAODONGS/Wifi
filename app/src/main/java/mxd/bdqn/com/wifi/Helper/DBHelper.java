package mxd.bdqn.com.wifi.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/5/1 0001.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = "sqlite";
    private final static String DB_NAME = "wifi.db";
    private final static int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user (id integer primary key autoincrement,name text,pwd text);");
        db.execSQL("insert into user values(null,'15029938467','123456')");
        db.execSQL("create table userInfo (id integer primary key autoincrement,name text,image text,schoolName text,schoolCode text,phone text,stuCode text,age integer,gender integer,state integer);");
        db.execSQL("insert into userInfo values(null,'15029938467','logo','体育学院','舞蹈专业','15029938467','1402322222',20,0,'男')");
        db.execSQL("create table money (id integer primary key autoincrement,name text,money text);");
        //db.execSQL("insert into money values(null,'15029938467','100')");
        db.execSQL("create table serviceInfo (id integer primary key autoincrement,name text,image text,url text);");
        db.execSQL("insert into serviceInfo values(null,'淘宝','taobao','https://ai.m.taobao.com/index.html?pid=mm_26632368_9250376_31190121&clk1=d6cef2d7bb489b8528ac7f228e64c34e&upsid=d6cef2d7bb489b8528ac7f228e64c34e')");
        db.execSQL("insert into serviceInfo values(null,'爱奇艺','aiqiyi','http://m.iqiyi.com/')");
        db.execSQL("insert into serviceInfo values(null,'小说','shuo','http://m.hao123.com/novel/')");
        db.execSQL("insert into serviceInfo values(null,'美团','tuan','http://m.hao123.com/novel/')");
        db.execSQL("insert into serviceInfo values(null,'百度音乐','music','http://music.baidu.com/')");
        db.execSQL("insert into serviceInfo values(null,'菜鸟裹裹','kuaidi','http://wap.guoguo-app.com/')");
        db.execSQL("insert into serviceInfo values(null,'百度地图','map','http://map.baidu.com/mobile/webapp/index/index/')");
        db.execSQL("insert into serviceInfo values(null,'火车票','huoche','http://m.ctrip.com/webapp/train/v2/index?sourceid=2362&allianceid=18887&sid=453561&utm_source=sm&utm_medium=cpc&utm_campaign=sm2362&sepopup=19&hiderecommapp=1#!/index\n')");
        db.execSQL("insert into serviceInfo values(null,'机票','plan','http://m.ctrip.com/html5/flight/?sepopup=3&sourceid=1768&allianceid=18887&sid=453558&utm_source=sm&utm_medium=cpc&utm_campaign=sm1768')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
