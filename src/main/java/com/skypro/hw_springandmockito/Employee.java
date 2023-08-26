package com.skypro.hw_springandmockito;

import java.util.Objects;

public class Employee {

    private String firstName;
    private String surName;
    private int department;
    private double salary;

    public Employee(String firstName, String surName, int department, double salary) {
        this.firstName = firstName;
        this.surName = surName;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee " + "firstName='" + firstName + '\'' + ", lastName='" + surName + '\'';
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    public int getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName) && Objects.equals(surName, employee.surName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, surName);
    }
}
