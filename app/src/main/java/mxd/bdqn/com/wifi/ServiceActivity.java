package mxd.bdqn.com.wifi;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mxd.bdqn.com.wifi.Helper.DBHelper;
import mxd.bdqn.com.wifi.Helper.Load;
import mxd.bdqn.com.wifi.Util.Util;
import mxd.bdqn.com.wifi.model.ServiceInfo;
import mxd.bdqn.com.wifi.model.User;


public class ServiceActivity extends AppCompatActivity {


    @Bind(R.id.tools)
    Toolbar toolbar;
    @Bind(R.id.recycle_view)
    RecyclerView recyclerView;
    private DBHelper helper;
    private SQLiteDatabase db;
    List<ServiceInfo> serviceInfos = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setAdapter(new MusicAdapter());
        setSupportActionBar(toolbar);

        helper = new DBHelper(this);
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from serviceInfo", null);
        while (cursor.moveToNext()) {
            ServiceInfo serviceInfo = Load.cursorToService(cursor);
            serviceInfos.add(serviceInfo);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.share:
                Util.share(this, "一个人不是说让自己大气自己就真的大气了，这种大气绝对与自己的成就有关。当一个人真正变得大气的时候，也就不在乎外界对你的任何指责和批评，因为你的内心始终自信满满，你把时间放在做更有意义的事情当中，你没有时间去理会这些琐碎的谣言。你将一切针对你的针尖锋芒置之于外。");
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    class MusicAdapter extends RecyclerView.Adapter<ViewHolder> {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text, null);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.setItem(serviceInfos.get(position));
            holder.refresh();
        }

        @Override
        public int getItemCount() {
            return serviceInfos.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ServiceInfo serviceInfo;
        @Bind(R.id.text_name)
        TextView title;

        @Bind(R.id.image_view)
        ImageView imageView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ServiceActivity.this, WebActivity.class);
                    intent.putExtra("api", serviceInfos.get(getAdapterPosition()).getUrl());
                    startActivity(intent);

                    // Toast.makeText(context,  "我的位置"+getAdapterPosition() , Toast.LENGTH_SHORT).show();
                }
            });

        }

        void setItem(ServiceInfo music) {
            this.serviceInfo = music;
        }

        void refresh() {
            title.setText(serviceInfo.getName());
            Picasso.with(ServiceActivity.this).load(Util.URL + "image/" + serviceInfo.getImage() + ".jpg").error(R.drawable.home_pic1).into(imageView);

        }

    }
    @OnClick({R.id.back})
    public void Onclick(View view) {
        switch (view.getId()) {
            case R.id.back:
                this.finish();
                break;
        }

    }
}
