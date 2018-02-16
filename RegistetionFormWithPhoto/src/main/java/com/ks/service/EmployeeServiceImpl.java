package com.ks.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ks.dao.EmployeeDAO;
import com.ks.dto.EmployeeDTOPhoto;
import com.ks.entity.EmployeeEntityPhoto;

import Decoder.BASE64Encoder;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAO employeeDAO;


	@Override
	public String registerWithPhoto(EmployeeDTOPhoto empPhotoDTO) {
		Integer empId=null;
		String responseMessage=null;
		EmployeeEntityPhoto entity=new EmployeeEntityPhoto();
		entity.setAdds(empPhotoDTO.getAdds());
		entity.setCreatedDate(new Date());
		entity.setMobile(empPhotoDTO.getMobile());
		entity.setSal(empPhotoDTO.getSal());
		entity.setName(empPhotoDTO.getName());
		entity.setUpdateDate(new Date());
		entity.setPhoto(empPhotoDTO.getPhotoData());
		entity.setFileName(empPhotoDTO.getFileName());
		empId=employeeDAO.registerWithPhoto(entity);
		if(empId==null){
			responseMessage="Try again!";
		}else{
			responseMessage="Register successfullay id"+empId;
		}
		return responseMessage;
	}


	@Override
	public Integer getTotalRow() {
		Integer totalRecordNo=0;
		totalRecordNo=employeeDAO.getTotalEmpNo();
		return totalRecordNo;
	}


	@Override
	public List<EmployeeDTOPhoto> getAllDetailEmp(Integer currentPage) {
		List<EmployeeEntityPhoto> employeeEntityPhotoList=null;
		List<EmployeeDTOPhoto> dtoPhotosList = new ArrayList<EmployeeDTOPhoto>();
		if (currentPage != null) {
			employeeEntityPhotoList = employeeDAO.getAllEmpdetails(currentPage);
			for (EmployeeEntityPhoto employeeEntity : employeeEntityPhotoList) {
				EmployeeDTOPhoto dto = new EmployeeDTOPhoto();
				dto.setAdds(employeeEntity.getAdds());
				dto.setEid(employeeEntity.getEid());
				dto.setFileName(employeeEntity.getFileName());
				dto.setMobile(employeeEntity.getMobile());
				dto.setSal(employeeEntity.getSal());
				dto.setName(employeeEntity.getName());
				
				//for image start
				BASE64Encoder base64Encoder = new BASE64Encoder();
				 
		         StringBuilder imageString = new StringBuilder();
		         
		         imageString.append("data:image/jpg;base64,");
		         
		         imageString.append(base64Encoder.encode(employeeEntity.getPhoto()));
		         
		          dto.setImage(imageString.toString());
		          //end

		          
				dtoPhotosList.add(dto);
			}
		}
		return dtoPhotosList;
	}	
	
	@Override
	public byte[] photoDownload(Integer id) {
		byte[] image=null;
		if(id!=null){
			image=employeeDAO.getPhoto(id);
		}
		return image;
	}
	
}
