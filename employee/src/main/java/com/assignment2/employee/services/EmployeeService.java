package com.assignment2.employee.services;

import com.assignment2.employee.entity.Employee;
import com.assignment2.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;


    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    //save employee
    public Employee save( Employee employeeData){
       return employeeRepository.save(employeeData);
    }

//list out all employees
    public List<Employee> listAll(){
        return employeeRepository.findAll();
    }

    //get employee via id
    public Employee getEmployeebyId(Long id){
        Optional<Employee> selectedDaprtment = employeeRepository.findById(id);
        if(selectedDaprtment.isPresent()) {return employeeRepository.findById(id).get();}
        else {return null;}
    }

    //delete employee
    public String deleteEmployee (Long id){
        //check for id
        Optional<Employee> selectedDepartment = employeeRepository.findById(id);
        //if exits
        if (selectedDepartment.isPresent()) {
            employeeRepository.deleteById(id);
            return "Employee deleted";

        } else {
            //if not exist
            return ("No such Employee is found in the database. Please re-check the department ID");
        }
    }

    //update emp
    public Employee updateEmployee (Long id, Employee employeeData){
        //check for emp id
        Optional<Employee> isExist = employeeRepository.findById(id);
        //if exists
        if (isExist.isPresent()) {
            Employee selectedEmp = employeeRepository.findById(id).get();
            //set update values
            selectedEmp.setName(employeeData.getName());
            selectedEmp.setDepName(employeeData.getDepName());
            selectedEmp.setAge(employeeData.getAge());
            //save
            employeeRepository.save(selectedEmp);
            return selectedEmp;
        } else {
            System.out.println("No such Employee is found in the database. Please re-check the department ID");
        }
        return null;
    }

}
