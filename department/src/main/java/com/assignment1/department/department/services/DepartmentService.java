package com.assignment1.department.department.services;

import com.assignment1.department.department.entity.Department;
import com.assignment1.department.department.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    private DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    //new department
    public Department save(Department departmentData){
        return departmentRepository.save(departmentData);
    }

    //list out all departments
    public List<Department> listAll(){
        return departmentRepository.findAll();
    }

    //get department by id
    public Department getDepartmentbyId(Long id){
        Optional<Department> selectedDaprtment = departmentRepository.findById(id);
        if(selectedDaprtment.isPresent()) {return departmentRepository.findById(id).get();}
        //if no such dep, return null
        else {return null;}
    }

    //delete department using id
    public String deleteDepartment (Long id){
        Optional<Department> selectedDepartment = departmentRepository.findById(id);
        if (selectedDepartment.isPresent()) {
            departmentRepository.deleteById(id);
            return "Department deleted";

        } else {
            //if no such dep, return message
            return ("No such department is found in the database. Please re-check the department ID");
        }
    }

    //update department
    public Department updateDepartment (Long id, Department departmentData){
        //check for ID
        Optional<Department> isExist = departmentRepository.findById(id);

        //if exists
        if (isExist.isPresent()) {
            Department selectedDepartment = departmentRepository.findById(id).get();
            //set new values
            selectedDepartment.setDepName(departmentData.getDepName());
            selectedDepartment.setEmployeeCount(departmentData.getEmployeeCount());
            selectedDepartment.setLocation(departmentData.getLocation());
            //save updated object
            departmentRepository.save(selectedDepartment);
            return selectedDepartment;
        } else {
            //if no such id
            System.out.println("No such department is found in the database. Please re-check the department ID");
        }
        return null;
    }
}
