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
public class TalkFragment extends Fragment {
    Context context;
    @Bind(R.id.history)
    RecyclerView recyclerView;
    @Bind(R.id.fabu)
    TextView fabu;
    NewsAdapter newsAdapter;
    List<News> news = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public TalkFragment() {
        inniDate();
    }

    private void inniDate() {
        for (int i = 0; i < 10; i++) {
            News o = new News();
            o.setDescr("说出你遇到的问题并且谈谈您的想法"
            );
            o.setImage("home_pic1");
            o.setTime(i + "分钟前");
            o.setName("您觉得智慧校园网络怎么样？");
            news.add(o);

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_school, container, false);
        // Inflate the layout for this fragment
        ButterKnife.bind(this, view);
        newsAdapter = new NewsAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(newsAdapter);
        fabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
                newsAdapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    private void add() {
        News o = new News();
        o.setDescr("说出你遇到的问题？"
        );
        o.setImage("home_pic1");
        o.setTime("1分钟前");
        o.setName("您觉得智慧校园怎么样？");
        news.add(0, o);
    }

    class NewsAdapter extends RecyclerView.Adapter<ViewHolder> {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.talk, null);
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


        }

    }

}
