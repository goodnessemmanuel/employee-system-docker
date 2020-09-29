package com.ocheejeh.basicemployeesystem.controller;


import com.ocheejeh.basicemployeesystem.model.Employee;
import com.ocheejeh.basicemployeesystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class EmployeeController
{
    EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService)
    {
        this.employeeService = employeeService;
    }

    //method handler to display list of employees to the view
    @GetMapping(value = {"/", "/home"})
    public String homePage(HttpSession session, Model model)
    {
        if(session.getAttribute("admin") == null)
        {
            return "redirect:/login";
        }
        //default sort is by first name and its ascending
        return paginateAndSortEmployeeView(1, "firstName", "asc", model);
    }

    @GetMapping("/show-add-form")
    public String showEmployeeForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "add-employee";
    }

    @PostMapping("/add-employee")
    public String addNewEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult result, Model model)
    {
        if(result.hasErrors()) //blank fields
        {
            return "add-employee";
        }
        String email  = employee.getEmail();
       if(employeeService.checkIfEmployeeEmailExists(email)){
           model.addAttribute("emailAlreadyExists", true);
           return "add-employee";
       }
       employeeService.saveEmployee(employee);
       return "redirect:/";
    }

    @GetMapping("/show-update-form/{id}")
    public String showEmployeeUpdateForm(@PathVariable("id") Long id, Model model)
    {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);

        return "update-employee";
    }

    @PostMapping("/update-employee")
    public String updateEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult result, Model model)
    {
        if(result.hasErrors()) //blank fields
        {
            return "update-employee";
        }
        employeeService.saveEmployee(employee);
        return "redirect:/home";
    }

    @GetMapping("/delete-employee/{id}")
    public String deleteEmployee(@PathVariable("id") Long id, Model model)
    {
        employeeService.deleteEmployeeRecord(id);
        return "redirect:/home";
    }

    /**
     * requests to this mapping will be of the form
     * '/page/2?fieldToSort=name&sortOrder=asc'
     */
    @GetMapping("/page/{pageNo}")
    public String paginateAndSortEmployeeView(@PathVariable(value = "pageNo") int pageNo,
                                       @RequestParam("fieldToSort") String fieldToSort,
                                       @RequestParam("sortOrder") String sortOrder,
                                       Model model)
    {
        int pageSize = 5; //use this page size as default
        Page<Employee> page = employeeService.findAndSortEmployeePage(pageNo, pageSize, fieldToSort, sortOrder);
        List<Employee> employeeList = page.getContent();

        if(employeeList.isEmpty()){
            return "empty-employee";
        }

        //pagination attributes
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("totalElement", page.getTotalElements());

        //sorting attribute
        model.addAttribute("fieldToSort", fieldToSort);
        model.addAttribute("sortOrder", sortOrder);
        String toggleSortOrder = sortOrder.equals("asc") ? "desc" : "asc";
        model.addAttribute("toggleSortOrder", toggleSortOrder);


        model.addAttribute("employeeList", employeeList);
        return "index";
    }
}
