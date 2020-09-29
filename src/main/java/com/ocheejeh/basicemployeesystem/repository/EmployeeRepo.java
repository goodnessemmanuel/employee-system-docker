package com.ocheejeh.basicemployeesystem.repository;



import com.ocheejeh.basicemployeesystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    Employee findByEmail(String emailAddress);
}
