package com.assignment2.admin.controller;

import com.assignment2.admin.entity.Department;
import com.assignment2.admin.entity.Employee;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class AdminController {

    //this controller is used to access the endpoints of department and employee services via eureka discovery server
    @Autowired
    EurekaClient client;

    //http://localhost:8021/employees
    @GetMapping("/employees")
    @HystrixCommand(fallbackMethod = "displayDefaultHome")
    public List<Employee> getEmployees(){
        List response;
        RestTemplate restTemplate = new RestTemplate();
        InstanceInfo instanceInfo = client.getNextServerFromEureka("EMPLOYEE_SERVICE",false);
        String empUrl = instanceInfo.getHomePageUrl();
        empUrl = empUrl+ "employees";
        response = restTemplate.getForObject(empUrl, List.class);
        return (response);
    }
    //http://localhost:8021/employee/11
    //get employee by id
    @GetMapping("/employee/{id}")
    //circuit breaker fallback method
    @HystrixCommand(fallbackMethod = "displayDefaultHome")
    public Employee getEmployee(@PathVariable("id") Long id){
        Employee response;
        RestTemplate restTemplate = new RestTemplate();
        InstanceInfo instanceInfo = client.getNextServerFromEureka("EMPLOYEE_SERVICE",false);
        String empUrl = instanceInfo.getHomePageUrl();
        empUrl = empUrl+ "/employee/"+id;
        response = restTemplate.getForObject(empUrl, Employee.class);
        return (response);
    }

    //adding new employee
    @PostMapping("/employee/new")
    public Employee createEmployee(@RequestBody Employee employeeData){
        Employee response;
        RestTemplate restTemplate = new RestTemplate();
        InstanceInfo instanceInfo = client.getNextServerFromEureka("EMPLOYEE_SERVICE",false);
        String empUrl = instanceInfo.getHomePageUrl();
        empUrl = empUrl+ "/employee/new";
        response = restTemplate.postForObject(empUrl,employeeData, Employee.class);
        return (response);

    }

    //http://localhost:8021/employee/15/delete
    @DeleteMapping("/employee/{id}/delete")
    @HystrixCommand(fallbackMethod = "displayDefaultHome")
    public String deleteEmployee(@PathVariable("id") Long id){
        String response="";
        RestTemplate restTemplate = new RestTemplate();
        InstanceInfo instanceInfo = client.getNextServerFromEureka("EMPLOYEE_SERVICE",false);
        String empUrl = instanceInfo.getHomePageUrl();
        empUrl = empUrl+ "/employee/"+id+"/delete";
        restTemplate.delete(empUrl, String.class);
        return ("deleted" + response);

    }

    //http://localhost:8021/employee/11/update
    @PutMapping("/employee/{id}/update")
    @HystrixCommand(fallbackMethod = "displayDefaultHome")
    public String updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employeeData){
        String response="";
        RestTemplate restTemplate = new RestTemplate();
        InstanceInfo instanceInfo = client.getNextServerFromEureka("EMPLOYEE_SERVICE",false);
        String empUrl = instanceInfo.getHomePageUrl();
        empUrl = empUrl+ "/employee/"+id+"/update";
        restTemplate.put(empUrl,employeeData);
        return ("updated" + response);

    }



    //get all departments
    //http://localhost:8021/departments
    @GetMapping("/departments")
    @HystrixCommand(fallbackMethod = "displayDefaultHome")
    public List <Department> getDepartments(){
        List response;
        RestTemplate restTemplate = new RestTemplate();
        InstanceInfo instanceInfo = client.getNextServerFromEureka("DEPARTMENT_SERVICE",false);
        String depurl = instanceInfo.getHomePageUrl();
        depurl = depurl+ "departments";
        response = restTemplate.getForObject(depurl, List.class);
        return (response);
    }


    //http://localhost:8021/department/12
    @GetMapping("/department/{id}")
    public Department getDepartment(@PathVariable("id") Long id){
        Department response;
        RestTemplate restTemplate = new RestTemplate();
        InstanceInfo instanceInfo = client.getNextServerFromEureka("DEPARTMENT_SERVICE",false);
        String depUrl = instanceInfo.getHomePageUrl();
        depUrl = depUrl+ "/department/"+id;
        //System.out.println(empUrl);

        response = restTemplate.getForObject(depUrl, Department.class);
        return (response);
    }

    //create new department
    @PostMapping("/department/new")
    public Department createDepartment(@RequestBody Department departmentData){
        Department response;
        RestTemplate restTemplate = new RestTemplate();
        InstanceInfo instanceInfo = client.getNextServerFromEureka("DEPARTMENT_SERVICE",false);
        String depUrl = instanceInfo.getHomePageUrl();
        depUrl = depUrl+ "/department/new";
        response = restTemplate.postForObject(depUrl,departmentData, Department.class);
        return (response);

    }

    //delete department by id
    //http://localhost:8021/department/2/delete
    @DeleteMapping("/department/{id}/delete")
    @HystrixCommand(fallbackMethod = "displayDefaultHome")
    public String deleteDepartment(@PathVariable("id") Long id){
        String response="";
        RestTemplate restTemplate = new RestTemplate();
        InstanceInfo instanceInfo = client.getNextServerFromEureka("DEPARTMENT_SERVICE",false);
        String depUrl = instanceInfo.getHomePageUrl();
        depUrl = depUrl+ "/department/"+id+"/delete";
        restTemplate.delete(depUrl, String.class);
        return ("deleted" + response);

    }

    //update department
    //http://localhost:8021/department/563/update
    @PutMapping("/department/{id}/update")
    @HystrixCommand(fallbackMethod = "displayDefaultHome")
    public String updateDepartment(@PathVariable("id") Long id, @RequestBody Department departmentData){
        String response="";
        RestTemplate restTemplate = new RestTemplate();
        InstanceInfo instanceInfo = client.getNextServerFromEureka("DEPARTMENT_SERVICE",false);
        String depUrl = instanceInfo.getHomePageUrl();
        depUrl = depUrl+ "/department/"+id+"/update";
        restTemplate.put(depUrl,departmentData);
        return ("updated" + response);

    }



}
