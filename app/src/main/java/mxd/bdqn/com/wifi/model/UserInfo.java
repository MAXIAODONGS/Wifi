package mxd.bdqn.com.wifi.model;

/**
 * Created by Administrator on 2017/4/8.
 */

public class UserInfo {
    private Integer id;
    private String name;
    private String image;
    private Integer state;
    private String schoolName;
    private String schoolCode;
    private String phone;
    private Integer age;
    private String stuCode;
    private Integer money;
    private String gender;

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", state=" + state +
                ", schoolName='" + schoolName + '\'' +
                ", schoolCode='" + schoolCode + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                ", stuCode='" + stuCode + '\'' +
                ", money=" + money +
                ", gender=" + gender +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getStuCode() {
        return stuCode;
    }

    public void setStuCode(String stuCode) {
        this.stuCode = stuCode;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public UserInfo(Integer id, String name, String image, Integer state, String schoolName, String schoolCode, String phone, Integer age, String stuCode, Integer money, String gender) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.state = state;
        this.schoolName = schoolName;
        this.schoolCode = schoolCode;
        this.phone = phone;
        this.age = age;
        this.stuCode = stuCode;
        this.money = money;
        this.gender = gender;
    }

    public UserInfo() {
    }


}
