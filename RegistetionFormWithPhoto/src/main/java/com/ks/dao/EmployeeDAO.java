package com.ks.dao;

import java.util.List;

import com.ks.entity.EmployeeEntityPhoto;

public interface EmployeeDAO {

	
	public Integer registerWithPhoto(EmployeeEntityPhoto entity);

	public Integer getTotalEmpNo();

	public List<EmployeeEntityPhoto> getAllEmpdetails(Integer currentPage);
	public byte[] getPhoto(Integer id);
}
