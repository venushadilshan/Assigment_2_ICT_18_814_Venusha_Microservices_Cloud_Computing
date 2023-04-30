package com.assignment1.employee.employee.controller;

import com.assignment1.employee.employee.entity.Employee;
import com.assignment1.employee.employee.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    //adding new employee
    //http://localhost:8080/employee/new
    @PostMapping("/employee/new")
    public ResponseEntity<Employee> createNewEmp(@RequestBody Employee employeeData){
        return ResponseEntity.ok()
                .header("custom-header","Venusha")
                .body(employeeService.save(employeeData));

    }

    //get all employees
    //http://localhost:8080/employees
    @GetMapping("employees")
    public List<Employee> getDepList(){
        return employeeService.listAll();
    }

    //get emp by id
    //http://localhost:8080/employee/1
    @GetMapping("/employee/{id}")
    public Employee detDepbyId (@PathVariable("id") Long id){
        return employeeService.getEmployeebyId(id);

    }

    //delete emp by id
    //http://localhost:8080/employee/1/delete
    @DeleteMapping("/employee/{id}/delete")
    public String deleteEmp(@PathVariable ("id") Long id){
        return employeeService.deleteEmployee(id);
    }

    //update emp
    //http://localhost:8080/employee/1/update
    @PutMapping("/employee/{id}/update")
    public Employee updateEmp(@PathVariable ("id") Long id, @RequestBody Employee employeeData){
        return employeeService.updateEmployee(id,employeeData);

    }
}
