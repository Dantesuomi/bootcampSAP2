package com.SAP.bootcampSAP2.controller;

import com.SAP.bootcampSAP2.entities.Employee;
import com.SAP.bootcampSAP2.service.interfaces.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody @Valid Employee employee){
        try {
            Employee savedEmployee = employeeService.addNewEmployee(employee);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/allEmployees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        try {
            List<Employee> allEmployees = employeeService.getAllEmployees();
            return ResponseEntity.status(HttpStatus.OK).body(allEmployees);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody @Valid Employee employee,
                                                   @PathVariable long employeeId){
        try {
            if (!employeeService.doesEmployeeExistById(employeeId)) {
                throw new ResourceNotFoundException("Employee not found with id: " + employeeId);
            }
            Employee updatedEmployee = employeeService.updateEmployeeById(employeeId, employee);
            return ResponseEntity.status(HttpStatus.OK).body(updatedEmployee);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable long employeeId){
        if (!employeeService.doesEmployeeExistById(employeeId)) {
            throw new ResourceNotFoundException("Employee not found with id: " + employeeId);
        }
        employeeService.deleteEmployeeById(employeeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
