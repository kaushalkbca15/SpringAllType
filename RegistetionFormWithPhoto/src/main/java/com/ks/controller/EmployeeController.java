package com.ks.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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

	@RequestMapping(value = "registerWPhoto", method = RequestMethod.GET)
	public String registerWPhotoPage() {
		return "photoUpload";
	}

	@RequestMapping(value = "registerWPhoto", method = RequestMethod.POST)
	public String registerPhotoEmp(@RequestParam CommonsMultipartFile[] fileUpload, Map<String, String> map,
			@RequestParam("ename") String ename, @RequestParam("sal") String sal, @RequestParam("adds") String adds,
			@RequestParam("mobile") String mobile) {
		String responseMessage = null;
		EmployeeDTOPhoto empPhotoDTO = null;
		if (fileUpload != null && fileUpload.length > 0) {
			for (CommonsMultipartFile originalFile : fileUpload) {
				empPhotoDTO = new EmployeeDTOPhoto();

				empPhotoDTO.setAdds(adds);
				empPhotoDTO.setMobile(mobile);
				empPhotoDTO.setName(ename);
				empPhotoDTO.setSal(sal);
				empPhotoDTO.setPhotoData(originalFile.getBytes());
				empPhotoDTO.setFileName(empPhotoDTO.getName() + "_" + empPhotoDTO.getEid());
				responseMessage = employeeService.registerWithPhoto(empPhotoDTO);

				map.put("responseMessage", responseMessage);
			}
		}
		return "photoUpload";
	}

	@RequestMapping(value = "viewall", method = RequestMethod.GET)
	public String viewAllDetails(Map<String, Object> map, @RequestParam("id") String id) {
		List<EmployeeDTOPhoto> dtoPhoto = null;
		Integer pageNo = 0;
		Integer currentPage = Integer.parseInt(id);
		Integer NoOfRecords = null;
		NoOfRecords = employeeService.getTotalRow();
		pageNo = NoOfRecords / 4;
		if (NoOfRecords % 4 != 0) {
			pageNo++;
		}
		dtoPhoto = employeeService.getAllDetailEmp(currentPage);
		map.put("responseData", dtoPhoto);
		map.put("pageNo", pageNo);
		map.put("currentPageNo", currentPage);
		return "viewAll";

	}

	@RequestMapping(value = "fileDownload", method = RequestMethod.GET)
	public void fileDownload(HttpServletResponse res, @RequestParam("id") String id) throws IOException {
		byte[] image = null;
		image=employeeService.photoDownload(Integer.parseInt(id));
		System.out.println("aaaaa  "+image.length);
		List<EmployeeDTOPhoto> dtoPhoto = null;
		dtoPhoto = employeeService.getAllDetailEmp(1);
		EmployeeDTOPhoto dto=dtoPhoto.get(0);
		image=dto.getImage().getBytes();
		
		
		System.out.println(image.length);
		
	/*	res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition","attachment;filename=remp.jpg");*/
        
        res.setContentType("application/force-download");
        res.setHeader("Content-Disposition", "attachment; filename=asdf.jpg");
        
		ServletOutputStream out = res.getOutputStream();

		byte[] outputByte = new byte[4096];
		//copy binary contect to output stream
		for (byte b : image) {
			out.write(b);
		}

		out.flush();
		out.close();
		//return "viewAll";
	}

}
