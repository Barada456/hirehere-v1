package com.centroxy.controllers;

import java.io.IOException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.centroxy.entities.Employee;
import com.centroxy.entities.Notification;
import com.centroxy.repositories.EmployeeRepository;
import com.centroxy.repositories.NotificationRepository;
import com.centroxy.utils.FileUploadUtil;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	EmployeeRepository employeeRepo;
	@Autowired
	Notification notification;
	@Autowired
	NotificationRepository notificationRepository;

	@GetMapping("/allEmployees/{role}/{page}")
	public String showEmployeeDashboard(Model model, @PathVariable(value = "page", required = false) Integer page, @PathVariable(value = "role", required = false) String role) {
		if (page == null) {
			page = 0;
		}

		Pageable pageble = PageRequest.of(page, 8);
		Page<Employee> findAll = employeeRepo.findAll(pageble);


		model.addAttribute("listOfEmployee", findAll);
		model.addAttribute("currentpage", page);
		model.addAttribute("totalpage", findAll.getTotalPages());


		/*
		 * Find all the notifications meant for the Employee and populate the same upon login.
		 */
		List<Notification> employeeNotifications = notificationRepository.findByOrderByDateTimeDesc();
		model.addAttribute("NotificationsForEmployee", employeeNotifications);
		
		

		return "allEmployees";
	}

	@GetMapping("/addNewEmployee")
	public String showAddNewEmployeeForm(Model model) {
		model.addAttribute("EmployeeDetails", new Employee());
		return "addEmployee";
	}

	@PostMapping("/addEmployee")
	public String addNewEmployee(@ModelAttribute Employee employee, @RequestParam("image") MultipartFile multipartFile)
			throws IOException {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		employee.setEmpImage(fileName);
		Employee savedEmployee = employeeRepo.save(employee);
		String uploadDir = "employee-pics/" + savedEmployee.getEmpId();
		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		return "redirect:/hr/dashboard";
	}

	@GetMapping("/dashboard")
	public String showEmployeeDashboard(Model model) {
		List<Notification> employeeNotifications = notificationRepository.findByOrderByDateTimeDesc();
		model.addAttribute("NotificationsForEmployee", employeeNotifications);
		
		int noOfNotifications = notificationRepository.countNoOfNotifications();
		model.addAttribute("notificationcount",noOfNotifications);
		
		
		
		return "employee-dashboard";
	}

}
