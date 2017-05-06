package mxd.bdqn.com.wifi.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mxd.bdqn.com.wifi.ActivityActivity;
import mxd.bdqn.com.wifi.Helper.Load;
import mxd.bdqn.com.wifi.HistoryActivity;
import mxd.bdqn.com.wifi.InfoActivity;
import mxd.bdqn.com.wifi.LoginActivity;
import mxd.bdqn.com.wifi.R;
import mxd.bdqn.com.wifi.RunActivity;
import mxd.bdqn.com.wifi.ServiceActivity;
import mxd.bdqn.com.wifi.UserInfosActivity;
import mxd.bdqn.com.wifi.model.News;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements ViewPager.OnPageChangeListener {

    private final static int[] PICTURE = {
            R.drawable.home_pic1, R.drawable.home_pic2,
            R.drawable.home_pic3, R.drawable.home_pic4, R.drawable.home_pic5,
    };
    private static final String TAG = "HomeFragment";
    private int previousPosition;
    @Bind(R.id.top_s)
    TextView toTopBtn;
    @Bind(R.id.scrollView)
    ScrollView scrollView;

    @Bind(R.id.music)
    RecyclerView recyclerView;
    @Bind(R.id.image_slide_page)
    ViewPager viewPager;
    @Bind(R.id.layout_circle_images)
    LinearLayout linearLayout;

    private boolean isLooper = true;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        }
    };
    private TextView textView;
    private int scrollY;
    private View contentView;

    ProgressDialog progressDialog;
    private Context context;
    List<News> news = new ArrayList<>();
    private String schoolName;

    public HomeFragment() {
        for (int i = 0; i < 10; i++) {
            News o = new News();
            o.setDescr(" \"西安外事学院创建于1992年,是国家教育部批准的本科层次普通高等学校。目前,以实施本科教学为主,同时举办专科层次的高等职业教育。下设外国语、经济管理、信息工程、人文、医学、国际合作、成人与网络教育等7个二级院,附设职业中专、博迪学校。17个系,开设本科专业10个、高职专业25个、助学类专业23个。涵盖商科、人文科学、社会科学、理科、工科、农科、医科等7个大学科门类。多学科协调发展,具有良好的办学基础和鲜明特色。截至2006年7月,各类在校学生3万余名,其中高职生1.5万余名。\\n\" +\n" +
                    "                \"\\n\" +\n" +

                    "                \"\\n\" +\n" +
                    "                \"学院位于西安高新技术产业开发区,校园面积2020亩,校舍建筑68.5万平方米。\\n\" +\n" +
                    "                \"\\n\" +\n" +
                    "                \"根据各专业培养目标,主要建有千兆校园计算机宽带网、卫星数字传输接收系统、多功能多媒体转播网络、校园电视台和有线电视网等教学服务设施;建有多功能对外贸易模拟公司、会计模拟与电算化实验室、宾馆管理模拟实习中心、人机语音交互视听说系统、计算机网络中心、电视节目非线性编辑与采编中心、多媒体语音教室;建有电力拖动、电工电子线路、计算机程序控制、PLC与EDA、汽车检测与维修、高等护理等各类专业实验室和实训中心。在北京、上海、广州、杭州、大连等地建立了毕业生就业实习基地。\\n\" +\n" +
                    "                \"\\n\" +\n" +
                    "                \"建有南北两座图书馆,馆藏图书146万册。建有多个塑胶标准田径运动场、篮球场、排球场、网球场。建有可供5000人观看比赛的中心运动场及各类体育教学设施。\\n\" +\n" +
                    "                \"\\n\" +\n"
                   );
            o.setImage("home_pic1");
            o.setTime(i+"小时前");
            o.setName("智慧校园上线外事学院"+i);
            news.add(o);
        }
    }

    @Override
    public void onAttach(Context contexts) {
        super.onAttach(contexts);
        this.context = contexts;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("正在加载数据....");
        progressDialog.setCanceledOnTouchOutside(false);
        //progressDialog.show();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        viewPager.setAdapter(new MyAdapter());
        viewPager.addOnPageChangeListener(this);
        indicator();
        looper();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new NewsAdapter());


        toTopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollView.post(new Runnable() {
                    @Override
                    public void run() {

                        scrollView.fullScroll(ScrollView.FOCUS_UP);
                    }
                });
                toTopBtn.setVisibility(View.GONE);
            }
        });

        return view;

    }

    @OnClick({R.id.show, R.id.news, R.id.info, R.id.service,R.id.run})
    public void OnClick(View view) {
        schoolName = Load.getSchoolName(context);
        String loginName = Load.getLoginName(context);
        switch (view.getId()) {
            case R.id.show:
                if (loginName!=null){
                    if (schoolName.equals("请完善个人信息") || schoolName == null) {
                        Toast.makeText(context, "请完善个人信息", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(context, UserInfosActivity.class));
                    } else {
                        startActivity(new Intent(context, HistoryActivity.class));
                    }
                }else {
                    startActivity(new Intent(context, LoginActivity.class));
                }


                break;
            case R.id.news:
                if (loginName!=null){
                if (schoolName.equals("请完善个人信息") || schoolName == null) {

                    startActivity(new Intent(context, UserInfosActivity.class));
                } else {
                    startActivity(new Intent(context, ActivityActivity.class));
                }
                }else {
                    startActivity(new Intent(context, LoginActivity.class));
                }
                break;
            case R.id.info:
                if (loginName!=null){
                if (schoolName.equals("请完善个人信息") || schoolName == null) {

                    startActivity(new Intent(context, UserInfosActivity.class));
                } else {
                    startActivity(new Intent(context, InfoActivity.class));
                }
                }else {
                    startActivity(new Intent(context, LoginActivity.class));
                }

                break;

            case R.id.service:
                if (loginName!=null){
                if (schoolName.equals("请完善个人信息") || schoolName == null) {

                    startActivity(new Intent(context, UserInfosActivity.class));
                } else {
                    startActivity(new Intent(context, ServiceActivity.class));
                }
                }else {
                    startActivity(new Intent(context, LoginActivity.class));
                }

                break;
            case R.id.run:
                if (loginName!=null){
                    if (schoolName.equals("请完善个人信息") || schoolName == null) {

                        startActivity(new Intent(context, UserInfosActivity.class));
                    } else {
                        startActivity(new Intent(context, RunActivity.class));
                    }
                }else {
                    startActivity(new Intent(context, LoginActivity.class));
                }

                break;
        }

    }


    void looper() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (isLooper) {
                    SystemClock.sleep(4000);
                    mHandler.sendEmptyMessage(0);
                }
            }
        }.start();
    }

    //设置圈圈
    void indicator() {
        View view;
        for (int i = 0; i < PICTURE.length; i++) {
            // 动态创建圆点
            view = new ImageView(context);
            // 设置背景显示的圆点资源
            view.setBackgroundResource(R.drawable.shape_indicator_selector);
            // 设置大小属性
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(25, 25);
            if (i != 0) {
                // 除了第一个 其他的都添加外边距
                params.leftMargin = 15;
            }
            view.setLayoutParams(params);

            // 设置第一个显示实心圆(必须)
            view.setEnabled(i == 0);

            // 添加到指示器容器中
            linearLayout.addView(view);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        int newPosition = position % PICTURE.length;

        linearLayout.getChildAt(newPosition).setEnabled(true);
        linearLayout.getChildAt(previousPosition).setEnabled(false);
        previousPosition = newPosition;

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    class MyAdapter extends PagerAdapter {

        /**
         * 返回数据源的数量
         *
         * @return
         */
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        /**
         * @param view   指的是当前这个页卡视图
         * @param object 指的是当前的页卡对象
         * @return
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /**
         * @param container 指的是当前ViewPager对象
         * @param position  指的是当前页卡的下标
         * @return 当前显示的页卡对象
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            int newPosition = position % PICTURE.length;
            ImageView iv = new ImageView(container.getContext());
            // 设置ImageView显示的图片
            iv.setBackgroundResource(PICTURE[newPosition]);
            // 一定要将视图对象添加到容器中
            container.addView(iv);

            return iv;
        }

        /**
         * @param container 指的是ViewPager对象
         * @param position  指的是页卡的下标
         * @param object    指的是页卡对象
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((View) object);
        }


    }

    class NewsAdapter extends RecyclerView.Adapter<ViewHolder> {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_show, null);
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
          /*  view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, InfoActivity.class);
                    intent.putExtra("info", getAdapterPosition());
                    startActivity(intent);

                    // Toast.makeText(context,  "我的位置"+getAdapterPosition() , Toast.LENGTH_SHORT).show();
                }
            });*/

        }

        void setItem(News music) {
            this.music = music;
        }

        void refresh() {
            title.setText(music.getDescr());
            titles.setText(music.getName());
            date.setText(music.getTime());
            Picasso.with(context).load(music.getImage() + ".jpg").error(R.drawable.home_pic2).into(imageView);

        }

    }


}
