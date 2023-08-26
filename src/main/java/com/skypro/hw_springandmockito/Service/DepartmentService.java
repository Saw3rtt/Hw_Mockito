package com.skypro.hw_springandmockito.Service;

import com.skypro.hw_springandmockito.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee searchMaxSalaryEmployee(int department);

    Employee searchMinSalaryEmployee(int department);

    Collection<Employee> searchAllEmployees(int department);

    Map<Integer, List<Employee>> getAllGroupingByDepartment();
    
}
