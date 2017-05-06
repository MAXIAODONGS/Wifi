package mxd.bdqn.com.wifi.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import mxd.bdqn.com.wifi.Helper.Load;
import mxd.bdqn.com.wifi.R;
import mxd.bdqn.com.wifi.model.School;

/**
 * A simple {@link Fragment} subclass.
 */
public class SchoolZhuanFragment extends Fragment {
    Context context;
    @Bind(R.id.history)
    RecyclerView recyclerView;
    List<School> news = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public SchoolZhuanFragment() {
        inniDate();
    }

    private void inniDate() {
        School newss = new School();
        newss.setName("外事学院");
        newss.setDatail("西安外事学院\n"+

                "开设专业:\n" +
                "\n" +
                "统招本科专业10个:英语、国际经济与贸易、财务管理、计算机科学与技术、电子信息工程、市场营销、物流管理、新闻学、艺术设计、护理学\n" +
                "\n" +
                "统招专升本专业6个:英语、国际经济与贸易、财务管理、计算机科学与技术、电子信息工程、新闻学\n" +
                "\n" +
                "统招专科专业25个:应用英语、应用日语、应用法语、计算机信息管理、计算机通信、移动通信技术、计算机控制技术、汽车检测与维修技术、保险实务、资产评估与管理、会计电算化、国际商务、电子商务、工商企业管理、物流管理、旅游管理、园林工程技术、环境艺术设计、广告设计与制作、影视动画、文秘、新闻采编与制作、法律事务、护理、口腔医学技术\n" +
                "\n" +
                "统招专科(五年制)专业6个:应用英语、计算机信息管理、汽车检测与维修技术、国际商务、旅游管理、护理\n" +
                "\n" +
                "成人统招专科专业11个:应用英语、计算机应用技术、国际商务、旅游管理、资产评估与管理、新闻采编与制作、广告设计与制作 、电子商务、会计电算化、法律事务、护理\n" +
                "\n" +
                "国际合作教育招生专业:中澳合作TAFE国际商务\n" +
                "\n" +
                "自考本科(全日制)专业16个:英语、计算机及应用、计算机信息管理、通信工程、机电一体化工程、国际贸易、电子商务、工商企业管理、旅游管理、物流管理、餐饮管理、会计、金融、新闻学、汉语言文学、法律\n" +
                "\n" +
                "自考专科(全日制)专业7个:电子技术、饭店管理、企业财务管理、市场营销、广告、视觉传达设计、室内设计\n" +
                "\n" +
                "中专(三年制)专业9个:英语、计算应用与维护技术、电子技术应用、计算机网络、电子商务、国际商务、市场营销、旅游与酒店管理、幼儿教育\n" +
                "\n" +
                "毕业生就业\n" +
                "\n" +
                "学校高度重视毕业生推荐就业工作,坚持毕业生就业与用人单位招聘双向选择、毕业生自主择业的原则,坚持学校推荐与个人自荐相结合的原则,先后已有12届毕业生顺利走上工作岗位,深受用人单位的欢迎。\n" +
                "\n" +
                "校内设有省人才交流中心第三分部和毕业生就业指导中心,在北京、上海、广州等地建立了毕业生就业培训中心,为毕业生就业或出国留学广开渠道。学校网站与兄弟省、市人才交流中心联网,实行网上推荐。每年多次举办校园人才洽谈会,内引外联,校企联合,先后与全国数千余家大中型企业建立了长期人才输送关系。几年来,有大批毕业生赴北京、上海、广州毕业生就业培训中心,进行岗前培训和就业,每届毕业生在六月中旬前均能顺利就业或出国深造。\n" +
                "\n" +
                "民办教育是社会主义市场经济体制的产物,社会需求始终是民办学校立足的根本。多年来,学院始终坚持以质量求生存,以信誉求发展,以真诚求理解,以贡献求支持的社会主义市场原则。努力提高教育质量和办学水平,以良好的教学设施和学习环境吸引学生,以规范管理和优质服务留住学生,以高水准的教育质量和教学手段满足学生,以突出的专业特色和人文精神塑造学生,以高就业率和就业质量取信于学生,培养了一大批优秀人才,树立了良好的社会形象。");
        School news1 = new School();
        news1.setName("体育学院");
        news1.setDatail(
               "院系设置\n" +
                "截至2013年，学院有体育教育系、运动训练系、武术系、社会体育\n" +
                "与休闲体育系、健康科学系、体育传媒系、体育艺术系、体育经济与体育管理系、研究生部、思政部、教育学院、竞技体育运动学校12个院系部。[3]  开设18个本科专业（体育学类专业7个，非体育学类专业11个），涵盖了经、理、教、文、管、艺6个专业门类。[2] \n" +
                "学科建设\n" +
                "至2013年，学院拥有2个一级学科硕士学位授权点和9个二级学科硕士学位授权点，1个陕西省重点学科建设点，2个陕西省普通高校哲学社会科学特色学科建设项目，是体育专业硕士学位首批试点单位，2004年开始与上海体育学院等院校合作培养博士研究生。[2] \n" +
                "硕士授权\n" +
                "一级学科：体育学、心理学\n" +
                "二级学科：体育教育训练学、运动人体科学、体育人文社会学、民族传统体育学、体育管理学、体育艺术学、应用心理学、运动医学、课程与教学论[2] \n" +
                "陕西省重点学科建设点：体育学[2] \n" +
                "陕西省普通高校哲学社会科学特色学科建设项目：西部体育与区域社会发展、民族民间传统体育文化\n" +
                "师资力量\n" +
                "截至2013年，学院有教职工797人，专职教师497人：其中教授61人，副教授129人；具有硕士学位以上的教师277人，占教师总数的56%；具有博士学位51人，占教师总数的10.3%；具有陕西省突出贡献专家、国家体育总局优秀中青年学术技术带头人、陕西省教学名师等荣誉称号的专家学者5人；硕士生导师89人。6个省级教学团队。[4] \n" +
                "省级教学团队：田径、篮球、健美操、运动人体科学、社会体育、体操[2] \n" +
                "教学建设\n" +
                "截至2013年，学院拥有3个国家级特色专业建设点，3个省级特色专业建设点，2个省级专业综合改革试点项目；1 门国家级精品课程，12门省级精品课程，2个省级实验教学示范中心，4个省级人才培养模式创新试验区和1个省级校外实践教育基地。[5] \n" +
                "国家级特色专业建设点：体育教育、运动训练、运动人体科学\n" +
                "省级特色专业建设点：民族传统体育、表演、应用心理学\n" +
                "省级专业综合改革试点项目：体育教育、运动训练[2] \n" +
                "训练基地：国家级社会体育指导员培训基地、国家高水平体育后备人才基地、国家跆拳道训练基地、国家健美操训练基地、国家柔道中高级教练员培训中心、中国地掷球培训中心、西北地区田径高水平人才培训中心\n" +
                "国家级精品课程：田径\n" +
                "省级精品课程：篮球、足球、体操，健美操、艺术体操、散打、武术套路、运动生理学、运动生物力学、体育经济、体育概论、学校体育学[6] \n" +
                "省级实验教学示范中心：运动人体科学实验中心、计算机实验教学中心\n" +
                "省级人才培养模式创新试验区：运动康复促进、田径、体育艺术表演、武术表演（古典武艺）[5] \n" +
                "教学成果\n" +
                "王涛、冯敬、肖俊峰、顾原等作为西安体育学院培养的学生入选国家集训\n" +
                "队，并在世界锦标赛、奥运会上取得了优异成绩。1998年，西安体育学院学生代表中国参加在韩国举办的世界青年跆拳道邀请赛中获得女子51kg级、63kg级，男子84kg级冠军。1999年，学院学生张伟永取得了参加2000年悉尼奥运会跆拳道比赛的入场券。2001年，西安体育学院武术系学生在参加世界武术散打锦标赛中获65kg级冠军。2004年以西安体育学院学生为主的健美操队参加了在法国和保加利亚举行的世界杯和锦标赛分获第四和第三名。1995年至2013年，西安体育学院学生在国际、国内重大赛事中共获得奖牌230枚，其中金牌87枚。优异的竞赛成绩。[7] ");
        news.add(news1);
        news.add(newss);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_school_history, container, false);
        // Inflate the layout for this fragment
        ButterKnife.bind(this, view);
        String schoolName = Load.getSchoolName(context);
        for (School s : news
                ) {
        if (s.getName().equals(schoolName)){
                news.clear();
                news.add(s);
            }

        }
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new NewsAdapter());
        return view;
    }

    class NewsAdapter extends RecyclerView.Adapter<ViewHolder> {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, null);
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
        School music;
        @Bind(android.R.id.text1)
        TextView title;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);


        }

        void setItem(School music) {
            this.music = music;
        }

        void refresh() {
            title.setText(music.getDatail());

        }

    }

}
