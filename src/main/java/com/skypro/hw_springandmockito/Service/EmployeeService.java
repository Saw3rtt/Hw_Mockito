package com.skypro.hw_springandmockito.Service;

import com.skypro.hw_springandmockito.Employee;

import java.util.Collection;

public interface EmployeeService {
    Employee addEmployee(String firstname, String surname, int department, double salary);

    Employee removeEmployee(String firstName, String surName);

    Employee searchEmployee(String firstName, String surName);

    Collection<Employee> searchAll();
}