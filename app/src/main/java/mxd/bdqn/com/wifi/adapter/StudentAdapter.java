package mxd.bdqn.com.wifi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;


import butterknife.Bind;
import butterknife.ButterKnife;
import mxd.bdqn.com.wifi.R;
import mxd.bdqn.com.wifi.model.News;

/**
 * Created by Administrator on 2017/3/10.
 */

public class StudentAdapter extends BaseAdapter {

    private List<News> students;
    private Context ctx;
    private LayoutInflater mInflater;
    private int p;

    public StudentAdapter(Context context, List<News> list) {
        this.students = list;
        this.ctx = context;
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public News getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        p = position;

        ViewHolder holder;


        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.run, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        News item = getItem(position);
        holder.setItem(item);
        holder.refresh();


        return convertView;

    }


    //保存控件和数据,优化list
    class ViewHolder {
        News music;
        @Bind(R.id.descr)
        TextView title;
        @Bind(R.id.date)
        TextView date;
        @Bind(R.id.titles)
        TextView titles;
        @Bind(R.id.run_qian)
        TextView run;
        @Bind(R.id.money)
        TextView money;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            if (run.getText().equals("抢单")) {
                run.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(ctx, "抢单成功", Toast.LENGTH_SHORT).show();

                        run.setText("已抢单");
                    }
                });

            } else {
                Toast.makeText(ctx, "已抢单,请选择其他。", Toast.LENGTH_SHORT).show();
            }
        }

        void setItem(News music) {
            this.music = music;
        }

        void refresh() {
            title.setText(music.getDescr());
            titles.setText(music.getName());
            date.setText(music.getTime());
            money.setText(music.getPrice());


        }


    }
}
