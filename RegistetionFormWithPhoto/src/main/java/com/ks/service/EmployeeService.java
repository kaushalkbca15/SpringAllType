package com.ks.service;

import java.util.List;

import com.ks.dto.EmployeeDTOPhoto;

public interface EmployeeService {
	public String registerWithPhoto(EmployeeDTOPhoto empPhotoDTO);

	public Integer getTotalRow();

	public List<EmployeeDTOPhoto> getAllDetailEmp(Integer currentPage);

	public byte[] photoDownload(Integer empid);
}
