package com.ocheejeh.basicemployeesystem.controller;

import com.ocheejeh.basicemployeesystem.model.Admin;
import com.ocheejeh.basicemployeesystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController
{
    AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/login")
    public String showLoginForm(Model model){
        Admin admin = new Admin();
        model.addAttribute("admin", admin);
        return "login";
    }

    @PostMapping("/login")
    public String authLogin(@ModelAttribute("admin") Admin admin, BindingResult result, Model model, HttpSession session)
    {
        if(!adminService.valid(admin))
        {
            model.addAttribute("invalidAdmin", true);
            return "login";
        }
        session.setAttribute("admin", admin);
        return "redirect:/";
    }
}
