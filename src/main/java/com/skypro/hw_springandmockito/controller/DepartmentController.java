package com.skypro.hw_springandmockito.controller;

import com.skypro.hw_springandmockito.Employee;
import com.skypro.hw_springandmockito.Service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee searchMaxSalaryEmployee(@RequestParam int department) {
        return departmentService.searchMaxSalaryEmployee(department);
    }

    @GetMapping("/min-salary")
    public Employee searchMinSalaryEmployee(@RequestParam int department) {
        return departmentService.searchMinSalaryEmployee(department);
    }

    @GetMapping("/all/{department}")
    public Collection<Employee> searchAllEmployees(@PathVariable("department") int department) {
        return departmentService.searchAllEmployees(department);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> getAllGroupingByDepartment() {
        return departmentService.getAllGroupingByDepartment();
    }
}
