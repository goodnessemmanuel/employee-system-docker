package com.ocheejeh.basicemployeesystem.service;

import com.ocheejeh.basicemployeesystem.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    List<Employee> listAllEmployee();

    void saveEmployee(Employee employee);

    Employee getEmployeeById(Long id);

    void deleteEmployeeRecord(Long employeeId);

    Page<Employee> findEmployeePage(int pageNo, int sizeOfPage);

    Page<Employee> findAndSortEmployeePage(int pageNo, int sizeOfPage, String fieldToSort, String sortOrder);

    Boolean checkIfEmployeeEmailExists(String emailAddress);
}
