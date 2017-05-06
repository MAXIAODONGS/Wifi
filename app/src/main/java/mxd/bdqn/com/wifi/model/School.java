package mxd.bdqn.com.wifi.model;

/**
 * Create by tao.zeng on 2016-8-29.
 */
public class School implements java.io.Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -1753567670369182131L;
    private Integer id;
    private String name;

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getDatail() {
        return datail;
    }

    public void setDatail(String datail) {
        this.datail = datail;
    }

    public School(Integer id, String name, String datail) {

        this.id = id;
        this.name = name;
        this.datail = datail;
    }

    private String datail;

    /**
     *
     */
    public School() {
        // TODO Auto-generated constructor stub
    }


}