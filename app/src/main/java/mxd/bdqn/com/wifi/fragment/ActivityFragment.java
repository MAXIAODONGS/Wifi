package mxd.bdqn.com.wifi.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import mxd.bdqn.com.wifi.Helper.Load;
import mxd.bdqn.com.wifi.R;
import mxd.bdqn.com.wifi.model.News;
import mxd.bdqn.com.wifi.model.School;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityFragment extends Fragment {
    Context context;
    @Bind(R.id.history)
    RecyclerView recyclerView;
    List<News> news = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public ActivityFragment() {
        inniDate();
    }

    private void inniDate() {
        for (int i = 0; i < 10; i++) {
            News o = new News();
            o.setDescr("关于2017年五一放假的通知二\n" +
                    "各位老师同学：\n" +
                    "\n" +
                    "　　值五一劳动节来临之际，根据国务院办公厅公布的《2017年节假日安排的通知》的有关规定，结合我公司实际情况，经领导班子研究决定，现将2017年五一劳动节放假事项通知如下：\n" +
                    "\n" +
                    "　　一、五一劳动节放假时间定为5月1日，与周末连休。\n" +
                    "\n" +
                    "　　二、各部门接通知后，妥善安排好值班工作，并将各部门值班表于2017年4月28日下午17：00以前报公司办公室。\n" +
                    "\n" +
                    "　　三、各部门要加强对值班人员的管理，认真落实公司突发事件预案制度，切实做好公司防火、安全、保卫等工作，发现苗头要及时向公司办公室值班人员报告。"
            );
            o.setImage("home_pic1");
            o.setTime(i + "小时前");
            o.setName("五一放假通知" + i);
            news.add(o);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_school_history, container, false);
        // Inflate the layout for this fragment
        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new NewsAdapter());
        return view;
    }

    class NewsAdapter extends RecyclerView.Adapter<ViewHolder> {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_show1, null);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.setItem(news.get(position));
            holder.refresh();
        }

        @Override
        public int getItemCount() {
            return news.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        News music;
        @Bind(R.id.descr)
        TextView title;

        @Bind(R.id.image_view)
        ImageView imageView;
        @Bind(R.id.date)
        TextView date;
        @Bind(R.id.titles)
        TextView titles;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);


        }

        void setItem(News music) {
            this.music = music;
        }

        void refresh() {
            title.setText(music.getDescr());
            titles.setText(music.getName());
            date.setText(music.getTime());
            Picasso.with(context).load(music.getImage() + ".jpg").error(R.drawable.home_pic5).into(imageView);

        }

    }

}
