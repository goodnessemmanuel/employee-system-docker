package com.ocheejeh.basicemployeesystem.service.serviceimpl;


import com.ocheejeh.basicemployeesystem.model.Employee;
import com.ocheejeh.basicemployeesystem.repository.EmployeeRepo;
import com.ocheejeh.basicemployeesystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * employee service implementation class
 */

@Component
public class EmployeeServiceImpl implements EmployeeService
{

    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepo employeeRepo)
    {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public List<Employee> listAllEmployee()
    {
        return employeeRepo.findAll();
    }

    @Override
    public void saveEmployee(Employee employee)
    {
        employeeRepo.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id)
    {
        return employeeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee invalid and/or not found for " + id));
    }


    @Override
    public void deleteEmployeeRecord(Long employeeId)
    {
        if(employeeRepo.existsById(employeeId))
        {
            employeeRepo.deleteById(employeeId);
        }
    }

    @Override
    public Page<Employee> findEmployeePage(int pageNo, int sizeOfPage) {
        Pageable pageable = PageRequest.of(pageNo - 1, sizeOfPage);
        return employeeRepo.findAll(pageable);
    }

    @Override
    public Page<Employee> findAndSortEmployeePage(int pageNo, int sizeOfPage, String fieldToSort, String sortOrder)
    {
        Sort sort = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(fieldToSort).ascending() : Sort.by(fieldToSort).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, sizeOfPage, sort); //sort and paginate

        return employeeRepo.findAll(pageable);
    }

    @Override
    public Boolean checkIfEmployeeEmailExists(String emailAddress) {
        return employeeRepo.findByEmail(emailAddress) != null;
    }

}
