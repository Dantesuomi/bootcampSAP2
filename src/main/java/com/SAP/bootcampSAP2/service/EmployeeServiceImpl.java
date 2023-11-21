package com.SAP.bootcampSAP2.service;

import com.SAP.bootcampSAP2.entities.Employee;
import com.SAP.bootcampSAP2.service.interfaces.EmployeeRepository;
import com.SAP.bootcampSAP2.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee addNewEmployee(Employee employee) {
        employee.setDateOfJoining(LocalDateTime.now());
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployeeById(Long employeeId, Employee updatedEmployee) {
        Optional<Employee> existingEmployeeOptional = employeeRepository.findById(employeeId);

        if (existingEmployeeOptional.isPresent()) {
            Employee existingEmployee = existingEmployeeOptional.get();
            existingEmployee.setName(updatedEmployee.getName());
            existingEmployee.setRole(updatedEmployee.getRole());
            return employeeRepository.save(existingEmployee);
        } else {
            throw new ResourceNotFoundException("Employee not found with id: " + employeeId);
        }
    }
        @Override
    public void deleteEmployeeById(long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public Boolean doesEmployeeExistById(Long employeeId) {
        return employeeRepository.existsById(employeeId);
    }


}
