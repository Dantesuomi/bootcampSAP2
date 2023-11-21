package com.SAP.bootcampSAP2.service.interfaces;

import com.SAP.bootcampSAP2.entities.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    Employee addNewEmployee (Employee employee);

    List<Employee> getAllEmployees();

    void deleteEmployeeById(long employeeId);

    Boolean doesEmployeeExistById(Long employeeId);

    Employee updateEmployeeById(Long employeeId, Employee employee);
}
