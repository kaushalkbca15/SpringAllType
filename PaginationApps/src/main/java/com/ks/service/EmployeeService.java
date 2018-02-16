package com.ks.service;

import java.util.List;

import com.ks.dto.EmployeeDTO;
import com.ks.dto.EmployeeDTOPhoto;

public interface EmployeeService {

	public String saveEmployee(EmployeeDTO dto);

	public Integer getPageNo();

	public List<EmployeeDTO> getEmployeeDetails(Integer currentPage);

	public String update(EmployeeDTO dto);

	public String delete(String eid);

	public String registerWithPhoto(EmployeeDTOPhoto empPhotoDTO);
}
