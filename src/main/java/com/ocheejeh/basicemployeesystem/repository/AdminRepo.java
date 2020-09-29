package com.ocheejeh.basicemployeesystem.repository;

import com.ocheejeh.basicemployeesystem.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {
}
