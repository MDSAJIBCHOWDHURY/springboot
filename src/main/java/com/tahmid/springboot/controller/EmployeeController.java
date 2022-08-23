package com.tahmid.springboot.controller;


import com.tahmid.springboot.exception.ResourceNotFoundException;
import com.tahmid.springboot.model.Employee;
import com.tahmid.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    //get all employees
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<Employee> getAllEmployees() {

        //return employeeRepository.findAll();
        return employeeRepository.findAllEmployee();
    }

    //create employee
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Employee createEmployee(@RequestBody Employee employee) {

        return employeeRepository.save(employee);

    }

    //get employee by id
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Employee getEmployeeById(@PathVariable long id) {
//        Employee employee = employeeRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id));
//        return ResponseEntity.ok(employee);
        Employee employee = employeeRepository.findEmployee(id);
        return employee;
    }

    //update employee by id
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public Employee updateEmployee(@PathVariable long id, @RequestBody Employee employeeDetails) {
        Employee updateEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id = " + id));
//        updateEmployee.setFirstName(employeeDetails.getFirstName());
//        updateEmployee.setLastName(employeeDetails.getLastName());
//        updateEmployee.setEmailId(employeeDetails.getEmailId());
//      employeeRepository.save(updateEmployee);
        System.out.println("FName ========= " + employeeDetails.getLastName());

        employeeRepository.updateEmployee(employeeDetails.getFirstName(), employeeDetails.getLastName(),
                employeeDetails.getEmailId(), id);

        return employeeDetails;
    }

    //delete employee
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteEmployee(@PathVariable long id) {
//        Employee employee = employeeRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id));
//        employeeRepository.delete(employee);
        employeeRepository.deleteEmployee(id);



    }

    //get json objects
    @RequestMapping(value = "/objects",method = RequestMethod.GET)
    public List<Employee> getJsonObjects(@RequestBody List<Employee> employees){
        List<Employee> updatedEmployees = new ArrayList<>();
        updatedEmployees.addAll(employees);
        for(int i=0;i<updatedEmployees.size();i++){
            Employee employee = updatedEmployees.get(i);
            employee.setId(100+i);
        }
        return updatedEmployees;

    }
}

