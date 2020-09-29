package com.ocheejeh.basicemployeesystem.service.serviceimpl;


import com.ocheejeh.basicemployeesystem.model.Admin;
import com.ocheejeh.basicemployeesystem.repository.AdminRepo;
import com.ocheejeh.basicemployeesystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService
{
    AdminRepo adminRepo;

    @Autowired
    public AdminServiceImpl(AdminRepo adminRepo)
    {
        this.adminRepo = adminRepo;
    }

    @Override
    public boolean valid(Admin admin)
    {
        return admin.getPassword().equalsIgnoreCase("admin")
                && admin.getUserName().equalsIgnoreCase("admin");
    }
}
