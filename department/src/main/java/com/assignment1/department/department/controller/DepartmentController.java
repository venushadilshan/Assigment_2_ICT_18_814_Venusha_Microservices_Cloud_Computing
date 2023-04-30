package com.assignment1.department.department.controller;

import com.assignment1.department.department.entity.Department;
import com.assignment1.department.department.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    //adding new department
    //http://localhost:8080/department/new
    @PostMapping("/department/new")
    public ResponseEntity<Department> createNewDepartment(@RequestBody Department departmentData){
        return ResponseEntity.ok()
                .header("custom-header","Venusha")
                .body(departmentService.save(departmentData));

    }

    //get list of departments
    //http://localhost:8080/departments
    @GetMapping("departments")
    public List<Department> getDepList(){
        return departmentService.listAll();
    }


    //get a department by ID
    //http://localhost:8080/department/1
    @GetMapping("/department/{id}")
    public Department getDepbyId (@PathVariable("id") Long id){
        return departmentService.getDepartmentbyId(id);

    }

    //deleted department by ID
    //http://localhost:8080/department/1/delete
    @DeleteMapping("/department/{id}/delete")
    public String deleteDep(@PathVariable ("id") Long id){
        return departmentService.deleteDepartment(id);
    }

    //update department by ID
    //http://localhost:8080/department/1/update
    @PutMapping("/department/{id}/update")
    public Department updateDep(@PathVariable ("id") Long id, @RequestBody Department departmentData){
        return departmentService.updateDepartment(id,departmentData);

    }


}
