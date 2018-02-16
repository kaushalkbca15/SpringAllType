package com.ks.dao;

import java.util.List;

import com.ks.entity.EmployeeEntity;
import com.ks.entity.EmployeeEntityPhoto;

public interface EmployeeDAO {

	public Integer saveEmployee(EmployeeEntity entity);

	public Integer getPageNo();

	public List<EmployeeEntity> getEmployeeDetails(Integer currentPage);

	public Integer update(EmployeeEntity entity);

	public Integer delete(String eid);

	public Integer registerWithPhoto(EmployeeEntityPhoto entity);
}
