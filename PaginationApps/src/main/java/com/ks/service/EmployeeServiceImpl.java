package com.ks.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ks.dao.EmployeeDAO;
import com.ks.dto.EmployeeDTO;
import com.ks.dto.EmployeeDTOPhoto;
import com.ks.entity.EmployeeEntity;
import com.ks.entity.EmployeeEntityPhoto;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAO employeeDAO;

	@Override
	public String saveEmployee(EmployeeDTO dto) {
		EmployeeEntity entity = new EmployeeEntity();
		Integer empId;
		String response = null;
		entity.setAdds(dto.getAdds());
		entity.setCreatedDate(new Date());
		entity.setMobile(dto.getMobile());
		entity.setSal(dto.getSal());
		entity.setName(dto.getName());
		entity.setUpdateDate(new Date());
		empId = employeeDAO.saveEmployee(entity);
		if (empId != null) {
			response = "Employee with id  :  " + empId + " successfully inserted.";
		} else {
			response = "Try again!";
		}
		return response;
	}

	@Override
	public Integer getPageNo() {
		return employeeDAO.getPageNo();
		
		
	}

	@Override
	public List<EmployeeDTO> getEmployeeDetails(Integer currentPage) {
		List<EmployeeEntity> list=null;
		List<EmployeeDTO> listDTO=null;
		listDTO=new ArrayList();
		list=employeeDAO.getEmployeeDetails(currentPage);
		for (EmployeeEntity employeeEntity : list) {
			EmployeeDTO dto=new EmployeeDTO();
			dto.setAdds(employeeEntity.getAdds());
			dto.setMobile(employeeEntity.getMobile());
			dto.setName(employeeEntity.getName());
			dto.setSal(employeeEntity.getSal());
			dto.setEid(employeeEntity.getEid());
			listDTO.add(dto);
		}
		return listDTO;
	}

	@Override
	public String update(EmployeeDTO dto) {
		Integer upDateEmpId=null;
		String responseData=null;
		EmployeeEntity entity=new EmployeeEntity();
		entity.setEid(dto.getEid());
		entity.setName(dto.getName());
		entity.setSal(dto.getSal());
		entity.setAdds(dto.getAdds());
		entity.setMobile(dto.getMobile());
		entity.setUpdateDate(new Date());
		upDateEmpId=employeeDAO.update(entity);
		if(upDateEmpId>0){
			responseData="Successfully update Emp Id: "+upDateEmpId;
		}
		else{
			responseData="Try again !";
		}
		return responseData;
	}
	
	@Override
	public String delete(String eid) {
		Integer empid=null;
		String responseMessage=null;
		empid=employeeDAO.delete(eid);
		if(empid!=null){
			responseMessage="Record deleted successfully Empid: "+empid;
		}else{
			responseMessage="Try again !";
		}
		return responseMessage;
	}
	
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
	
	
	
}
