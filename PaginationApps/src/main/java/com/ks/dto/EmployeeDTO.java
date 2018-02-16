package com.ks.dto;

/**
 * @author ADMIN
 *
 */
public class EmployeeDTO {

	private Integer eid;
	private String name;
	private String sal;
	private String adds;
	private String mobile;

	
	
	@Override
	public String toString() {
		return  eid + "," + name + "," +sal+ "," + adds + "," + mobile;
	}

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSal() {
		return sal;
	}

	public void setSal(String sal) {
		this.sal = sal;
	}

	public String getAdds() {
		return adds;
	}

	public void setAdds(String adds) {
		this.adds = adds;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
