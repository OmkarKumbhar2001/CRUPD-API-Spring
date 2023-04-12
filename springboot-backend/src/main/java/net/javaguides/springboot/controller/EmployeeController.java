package net.javaguides.springboot.controller;


import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;//its get from auto wired
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")//its like router in nodejs set one path to all this is default Path
public class EmployeeController {
    @Autowired//is an annotation in Spring that is used to automatically wire dependencies
    private EmployeeRepository employeeRepository;
    //EmployeeRepository is a dependency of the EmployeeController
    @GetMapping
    public List<Employee> getAllEmployees(){
        //return all employee list
        return employeeRepository.findAll();

    }
    //build create REST API
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){
        //@RequestBody this is for Convert Json in Java Object
        return  employeeRepository.save(employee);
    }
    //build get Employee by id REST API
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id){
        //ResponseEntity<Employee> this is genric class i.e we passing EMp type
        //@PathVariable for getting info from url
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee Not Exist with this Id " + id));
        return ResponseEntity.ok(employee);

    }
    //Update employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee employeeDetails){
        Employee updateEmployee = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not exist with this id"+id));
        updateEmployee.setFirstName(employeeDetails.getFirstName());
        updateEmployee.setLastName(employeeDetails.getLastName());
        updateEmployee.setEmailId(employeeDetails.getEmailId());

        employeeRepository.save(updateEmployee);
        return ResponseEntity.ok(updateEmployee);
    }
    //build delete employee Rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id){
        Employee  deleteEmployee=employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not Wxist with Id"+id));
        employeeRepository.delete(deleteEmployee);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
