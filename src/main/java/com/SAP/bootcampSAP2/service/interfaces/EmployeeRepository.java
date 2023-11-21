package com.SAP.bootcampSAP2.service.interfaces;

import com.SAP.bootcampSAP2.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAll();

}
