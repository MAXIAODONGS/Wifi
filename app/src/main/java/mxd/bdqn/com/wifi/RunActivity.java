package mxd.bdqn.com.wifi;

import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mxd.bdqn.com.wifi.adapter.StudentAdapter;
import mxd.bdqn.com.wifi.model.News;
import mxd.bdqn.com.wifi.view.MyListView;

public class RunActivity extends AppCompatActivity {
    @Bind(R.id.tools)
    Toolbar toolbar;
    @Bind(R.id.freelook_listview)
    MyListView myListView;
    private int mScreenWidth;
    StudentAdapter studentAdapter;
    List<News> news = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);
        ButterKnife.bind(this);
        inniDate();
        studentAdapter = new StudentAdapter(this, news);
        mScreenWidth = getResources().getDisplayMetrics().widthPixels;
        setSupportActionBar(toolbar);
        myListView.setAdapter(studentAdapter);
        myListView.setonRefreshListener(new MyListView.OnRefreshListener() {

            @Override
            public void onRefresh() {
                new AsyncTask<Void, Void, Void>() {
                    protected Void doInBackground(Void... params) {
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        add();
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {

                        studentAdapter.notifyDataSetChanged();
                        myListView.onRefreshComplete();
                    }

                }.execute();
            }
        });
    }

    private void inniDate() {
        for (int i = 0; i < 10; i++) {
            News o = new News();
            o.setDescr("女生宿舍4号楼204花花"
            );
            o.setPrice("5元");
            o.setTime(i + "分钟前");
            o.setName("学校门口取快递：156325255663225,花花,限女生");
            news.add(o);
        }

    }

    private void mDate() {
        for (int i = 0; i < 5; i++) {
            News o = new News();
            o.setDescr("女生宿舍4号楼204花花"
            );
            o.setPrice("5元");
            o.setTime(i + "分钟前");
            o.setName("学校门口取快递：156325255663225,花花,限女生");
            news.add(o);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 加载menu文件
        getMenuInflater().inflate(R.menu.add, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_option:
                popup2();
                break;

        }


        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.back})
    public void Onclick(View view) {
        switch (view.getId()) {
            case R.id.back:
                this.finish();
                break;
        }

    }

    /**
     * 加号选择
     */

    private void popup2() {
        // 将布局文件转换view对象
        View view = LayoutInflater.from(this).inflate(R.layout.layout_option_dialog, null);
        // 创建 PopupWindow 对象
        PopupWindow popupWindow = new PopupWindow(view, mScreenWidth / 2, LinearLayout.LayoutParams.WRAP_CONTENT);
        // 必须设置背景
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x92000000));
        view.findViewById(R.id.address).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RunActivity.this, "没有开启", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
                studentAdapter.notifyDataSetChanged();

            }
        });
        view.findViewById(R.id.my).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                news.clear();
                mDate();
                studentAdapter.notifyDataSetChanged();
            }
        });


        // 是否可以获取焦点
        popupWindow.setFocusable(true);

        // 点击其他区域是否消失
        popupWindow.setOutsideTouchable(true);


        int i = mScreenWidth - (int) (mScreenWidth * 0.3);


        // 显示PopupWindow 并且设置 X 轴的偏移量
        popupWindow.showAsDropDown(toolbar, i, 0);
    }

    private void add() {
        News o = new News();
        o.setDescr("男生宿舍4号楼204,马晓东"
        );
        o.setPrice("10元");
        o.setTime(1 + "分钟前");
        o.setName("学校门口取快递：5555555555555,马晓东");
        news.add(0, o);
    }

}
