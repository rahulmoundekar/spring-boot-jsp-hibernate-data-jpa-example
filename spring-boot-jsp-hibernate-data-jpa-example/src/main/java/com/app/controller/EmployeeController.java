package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.entity.Employee;
import com.app.repository.EmployeeRepo;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeRepo employeeRepo;

	@GetMapping(value = "/")
	public String welcome() {
		return "index";
	}

	@GetMapping(value = "registerForm")
	public String registrationForm() {
		return "register";
	}

	@PostMapping(value = "register")
	public String saveEmployee(Model model, @ModelAttribute Employee employee) {
		
		Employee emp = employeeRepo.save(employee);
		if (emp != null) {
			model.addAttribute("success", "Employee Registered succcessfully");
		} else
			model.addAttribute("error", "Employee is not Registered!!");
		return "register";
	}

	
	@GetMapping(value = { "employeeList" })
	public String displayEmployees(Model model) {
		List<Employee> list = employeeRepo.findAll();
		model.addAttribute("employees", list);
		return "welcome";
	}
}
