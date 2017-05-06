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
public class SchoolZunFragment extends Fragment {
    Context context;
    @Bind(R.id.history)
    RecyclerView recyclerView;
    List<School> news = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public SchoolZunFragment() {
        inniDate();
    }

    private void inniDate() {
        School newss = new School();
        newss.setName("外事学院");
        newss.setDatail("西安外事学院\n"+

                "办学思想和领导班子\n" +
                "\n" +
                "学院始终坚持全面贯彻党的教育方针和国家政策法规,自觉接受政府教育行政部门的领导和社会监督,始终把社会效益放在首位,坚持学校的公益性质和不取回报的非营利原则。\n" +
                "\n" +
                "学院确立了切实可行的发展目标,即立足陕西,服务西部,面向全国,培养德、智、体、美全面发展,具有创新精神和实践能力的高素质应用型人才。坚持教育创新和可持续的科学发展观,经过长期不懈地努力,使学院成为基础扎实,师资力量雄厚,设施先进完备,学科特色突出,国内一流,国际知名的民办普通高校。\n" +
                "\n" +
                "学院设有理事会、党委、校务会,实行理事会领导下的院长负责制。\n" +
                "\n" +
                "理事长、院长黄藤教授,现为十届全国人大代表,陕西中华职教社主任,教育学博士。具有10年公办高校工作经验,6年国外学习生活阅历,15年民办高校工作实践。主编教育理论、人文科学丛书3套,出版教育理论研究著作2部,发表学术论文20余篇。曾获中国民办高等教育创新奖、中国高校杰出校长、陕西省优秀教育工作者、十大杰出青年、新长征突击手、陕西省职业教育先进个人等荣誉。\n" +
                "\n" +
                "理事会和校务会特别重视发挥党组织的政治保证和监督作用,保证了党的教育方针和国家政策法规的全面贯彻。党委书记周延海教授,原任西北工业大学党委书记,省科委、省教委和省人大教科文卫委员会主任等职,长期从事教育教学工作,具有丰富的高校管理和思想政治工作经验。\n" +
                "\n" +
                "学院十分重视并不断加强领导班子建设,组成了老中青结合、团结务实的校务委员会。校务会由以下同志组成:理事长、院长黄藤,党委书记周延海,常务副院长李玉华,理事、副院长刘其民、高战华,理事、党委副书记、副院长梁佐仁,副院长陈时伟,教务长李汝峰,总务长杨富学。\n" +
                "\n" +
                "学院聘请了一批资深专家教授担任二级学院主要领导,医学院院长鞠名达教授,外国语学院院长周式中教授,经济管理学院院长张京鹏教授,信息工程学院院长尹季昆教授,人文学院副院长薛迪之教授。\n" +
                "\n" +
                "师资队伍\n" +
                "\n" +
                "学院始终把教师队伍建设摆在突出的重要地位,采取一切可能的措施,吸纳高层次人才,形成了年龄与专业结构合理,业务素质较高的专任教师队伍。这些同志中有很大一部分是在高等院校工作多年的专家教授,另一部分是年富力强的研究生和普通高校的本科毕业生。\n" +
                "\n" +
                "学院非常重视骨干教师的聘任和青年教师的培养。充分利用社会优良教育资源,发挥了老专家、老教授在学科建设、科研、人才培养和教学管理工作中的重要作用。各专业均配备有学科带头人和副高职以上教学骨干,各门必修公共课、专业基础课和专业课均配有讲师以上的专任教师。对青年教师采取不同方式提高其业务素质。一是建立导师制,由老教授、专家传、帮、带; 二是送往国内、外大学进修学习;三是鼓励报考在职研究生,提高其学历层次;四是举办各类研究生课程进修班,通过二至三年的培养,使其大部分取得硕士学位或达到同等水平。\n" +
                "\n" +
                "教学与学生管理\n" +
                "\n" +
                "学院按照市场需求,适时调整专业结构。大力扶持特色学科,发展优势专业,扩大专业适应面,形成了合理的学科专业结构与布局,为招生和毕业生就业奠定了基础。\n" +
                "\n" +
                "在教学管理方面,采取了一系列有效措施。例如成立教学委员会和教学督导组,进一步完善教学管理制度,规范了管理程序;深化教学改革,试行分级教学和弹性学制,为学生个性发展和创新能力培养提供优越的条件;积极推进多专业证书制,增强学生就业与创业的竞争力;加强英语、计算机、护理等应用性较强课程教学改革。精简课时,强化基础训练和实践性教学环节等,切实提高了教学质量,取得了显著成效。\n" +
                "\n" +
                "在教学方法改革方面,充分体现人才培养的时代特征,努力实现教学手段现代化。充实与拓展多媒体教室,积极尝试网络化教学。为学生自主学习创造了良好条件。\n" +
                "\n" +
                "严格考试管理,改革考试制度。例如取消补考,建立重修制度;打破考试与考查的界限,建立闭卷、开卷、面试、口试、实验报告、读书报告等多形式考试及管理办法;打破校内外考试的界限,鼓励学生参加国家各类考试,成绩合格者该课程可在校内免试,并计算学分。\n" +
                "\n" +
                "这些改革尝试,有效地调动了学生学习的积极性、主动性和创新精神,教育教学质量稳步提高。在陕西省统一组织的专升本考试中,我院学生被录取人数连年居全省民办高校之首,先后有千余名学生考入本科院校学习;在历次学历文凭统考中,通过率均名列西安地区第一位;在远程教育研究生软件工程单科考试中,取得清华大学远程教育全国第一名的优异成绩;在“陕西省大学生高等数学竞赛”中,有14名学生获奖。其中一等奖1名,二等奖3名,三等奖10名。\n" +
                "\n" +
                "学院高度重视学生思想政治工作,狠抓“两课”教育,开展心理健康教育和心理咨询活动。重视学生党、团组织建设和发挥党、团员的先锋模范作用。先后有近万名学生参加业余党校学习,1600余名积极分子光荣入党。\n" +
                "\n" +
                "在学生管理方面,学院坚持专职班主任与辅导员相结合的管理制度。由班主任、辅导员和公寓管理员共同形成了一个有力、有机的学生管理网络。使学生从进校到毕业,都处于学校的教育管理之中,有效保障了正常教学秩序。2005年,获得省教工委、教育厅授予的“文明校园”荣誉称号。\n" +
                "\n" +
                "在此基础上,学院不断强化育人意识,突出素质教育,重视学生个性发展,涌现出一大批品学兼优的青年学生。苏昊同学蝉联2003、2004年世界电子竞技大赛中国赛区冠军。王颖同学获“全国三好学生”称号。白丽霞同学获CCTV杯全国英语演讲比赛陕西赛区第一名。在第七、八、九届“挑战杯”全国大学生科技作品竞赛中,学院作为全国唯一一所民办院校发起单位,多人进入决赛,获得两个全国二等奖、六个三等奖和优秀组织奖。在各类体育、文艺比赛中,获得多个一等奖和单项冠军。\n" +
                "\n" );
        School news1 = new School();
        news1.setName("体育学院");
        news1.setDatail(
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
if (s.getName().equals(schoolName)) {
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
