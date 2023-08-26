package com.skypro.hw_springandmockito.Service;

import com.skypro.hw_springandmockito.Employee;
import com.skypro.hw_springandmockito.exceptions.EmployeeAlreadyAddedException;
import com.skypro.hw_springandmockito.exceptions.EmployeeNotFoundException;
import com.skypro.hw_springandmockito.exceptions.EmployeeStorageFullException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    public static final int EMPLOYEE_MAX = 4;
    private final Map<String, Employee> employeeMap;

    public EmployeeServiceImpl() {
        this.employeeMap = new HashMap<>();
    }

    @Override
    public Employee addEmployee(String firstName, String surName, int department, double salary) {
        if (employeeMap.size() == EMPLOYEE_MAX) {
            throw new EmployeeStorageFullException("Превышен лимит количества сотрудников в фирме");
        }
        Employee employee = new Employee(firstName, surName, department, salary);
        if (employeeMap.containsKey(firstName + surName)) {
            throw new EmployeeAlreadyAddedException("Уже есть такой сотрудник");
        }
        employeeMap.put(firstName + surName, employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String surName) {
        Employee employee = employeeMap.remove(firstName + surName);
        if (Objects.isNull(employee)) {
            throw new EmployeeNotFoundException("Сотрудника не существует");
        }
        return employee;
    }

    @Override
    public Employee searchEmployee(String firstName, String surName) {
        Employee employee = employeeMap.get(firstName + surName);
        if (Objects.isNull(employee)) {
            throw new EmployeeNotFoundException("Сотрудник не найден!");
        }
        return employee;
    }

    @Override
    public Collection<Employee> searchAll() {
        return employeeMap.values();
    }
}