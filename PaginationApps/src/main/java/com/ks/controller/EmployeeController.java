package com.ks.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ks.dto.EmployeeDTO;
import com.ks.dto.EmployeeDTOPhoto;
import com.ks.service.EmployeeService;

@Controller

public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String homePage() {
		return "home";
	}

	@RequestMapping(value = "home", method = RequestMethod.POST)
	public String registerPage(Map<String, String> map, @RequestParam("ename") String ename,
			@RequestParam("sal") String sal, @RequestParam("adds") String adds, @RequestParam("mobile") String mobile) {
		String response = null;
		EmployeeDTO dto = new EmployeeDTO();
		dto.setAdds(adds);
		dto.setMobile(mobile);
		dto.setName(ename);
		dto.setSal(sal);
		response = employeeService.saveEmployee(dto);
		map.put("response", response);
		return "home";
	}

	@RequestMapping(value = "view_all", method = RequestMethod.GET)
	public String viewDetails(Map<String, Object> map, @RequestParam("id") String id) {
		Integer pageNo = 0;
		Integer currentPage = Integer.parseInt(id);
		Integer NoOfRecords = null;
		List<EmployeeDTO> list = null;
		NoOfRecords = employeeService.getPageNo();
		pageNo = NoOfRecords / 5;
		if (NoOfRecords % 5 != 0) {
			pageNo++;
		}
		list = employeeService.getEmployeeDetails(currentPage);
		map.put("TotalPage", pageNo);
		map.put("response", list);
		map.put("CPN", currentPage);
		return "empdetails";
	}

	private static EmployeeDTO dto ;
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(Map<String, Object> map, @RequestParam("data") String data) {
		String[] strArr = data.split(",");
		dto= new EmployeeDTO();
		dto.setEid(Integer.parseInt(strArr[0]));
		dto.setName(strArr[1]);
		dto.setSal(strArr[2]);
		dto.setAdds(strArr[3]);
		dto.setMobile(strArr[4]);
		map.put("responseData", dto);
		return "edit";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(Map<String, Object> map, @RequestParam("eid") String eid, @RequestParam("ename") String ename,
			@RequestParam("sal") String sal, @RequestParam("adds") String adds, @RequestParam("mobile") String mobile) {
		String responseData=null;
		EmployeeDTO dto = new EmployeeDTO();
		dto.setEid(Integer.parseInt(eid));
		dto.setAdds(adds);
		dto.setMobile(mobile);
		dto.setName(ename);
		dto.setSal(sal);
		responseData=employeeService.update(dto);
		map.put("updateReponse", responseData);
		return "edit";
	}
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(Map<String, Object> map, @RequestParam("eid")String eid){
		String responseMessage=null;
		responseMessage=employeeService.delete(eid);
		map.put("responseData", dto);
		map.put("responseMessage", responseMessage);
		return "empdetails";
	}
	@RequestMapping(value = "registerWPhoto", method = RequestMethod.GET)
	public String registerWPhotoPage(){
		return "photoUpload";
	}
	@RequestMapping(value = "registerWPhoto", method = RequestMethod.POST)
	public String registerPhotoEmp(@RequestParam CommonsMultipartFile[] fileUpload,Map<String, String> map, @RequestParam("ename") String ename,
			@RequestParam("sal") String sal, @RequestParam("adds") String adds, @RequestParam("mobile") String mobile){
		String responseMessage=null;
		EmployeeDTOPhoto empPhotoDTO = null;
		   if (fileUpload != null && fileUpload.length > 0) {
	            for (CommonsMultipartFile originalFile : fileUpload){
	            	empPhotoDTO = new EmployeeDTOPhoto();
	              
	            	empPhotoDTO.setAdds(adds);
	            	empPhotoDTO.setMobile(mobile);
	            	empPhotoDTO.setName(ename);
	            	empPhotoDTO.setSal(sal);
	            	empPhotoDTO.setPhotoData(originalFile.getBytes());
	                empPhotoDTO.setFileName(empPhotoDTO.getName()+"_"+empPhotoDTO.getEid());
	                responseMessage=employeeService.registerWithPhoto(empPhotoDTO);
	                
	                map.put("responseMessage", responseMessage);
	                
	                
	             
//		            System.out.println("Saving file: " + originalFile.getOriginalFilename());
	            }
//	            System.out.println("photo......: "+empPhotoDTO.getPhotoData());

	        }
	  
		
		
		
		
		return "photoUpload";
	}
	
	
}

























