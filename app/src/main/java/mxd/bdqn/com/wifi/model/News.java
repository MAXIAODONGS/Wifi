package mxd.bdqn.com.wifi.model;

/**
 * Create by tao.zeng on 2016-8-29.
 * 
 */
public class News implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1753567670369182131L;
	private Integer musicid;
	private String name;
	private String time;
	private String address;
	private String image;
	private String descr;
	private String calls;
	private String mapx;
	private String mapy;
	private String price;

	/**
	 * 
	 */
	public News() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param musicid
	 * @param name
	 * @param time
	 * @param address
	 * @param image
	 * @param descr
	 * @param calls
	 * @param mapx
	 * @param mapy
	 * @param price
	 */
	public News(Integer musicid, String name, String time, String address,
				String image, String descr, String calls, String mapx, String mapy,
				String price) {
		super();
		this.musicid = musicid;
		this.name = name;
		this.time = time;
		this.address = address;
		this.image = image;
		this.descr = descr;
		this.calls = calls;
		this.mapx = mapx;
		this.mapy = mapy;
		this.price = price;
	}

	/**
	 * @return the musicid
	 */
	public Integer getMusicid() {
		return musicid;
	}

	/**
	 * @param musicid
	 *            the musicid to set
	 */
	public void setMusicid(Integer musicid) {
		this.musicid = musicid;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the descr
	 */
	public String getDescr() {
		return descr;
	}

	/**
	 * @param descr
	 *            the descr to set
	 */
	public void setDescr(String descr) {
		this.descr = descr;
	}

	/**
	 * @return the calls
	 */
	public String getCalls() {
		return calls;
	}

	/**
	 * @param calls
	 *            the calls to set
	 */
	public void setCalls(String calls) {
		this.calls = calls;
	}

	/**
	 * @return the mapx
	 */
	public String getMapx() {
		return mapx;
	}

	/**
	 * @param mapx
	 *            the mapx to set
	 */
	public void setMapx(String mapx) {
		this.mapx = mapx;
	}

	/**
	 * @return the mapy
	 */
	public String getMapy() {
		return mapy;
	}

	/**
	 * @param mapy
	 *            the mapy to set
	 */
	public void setMapy(String mapy) {
		this.mapy = mapy;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

}