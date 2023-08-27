package com.skypro.hw_springandmockito.Service;

import com.skypro.hw_springandmockito.Employee;
import com.skypro.hw_springandmockito.exceptions.EmployeeAlreadyAddedException;
import com.skypro.hw_springandmockito.exceptions.EmployeeNotFoundException;
import com.skypro.hw_springandmockito.exceptions.EmployeeStorageFullException;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {
    EmployeeServiceImpl underTest = new EmployeeServiceImpl();
    Employee employee = new Employee("Valery", "Vagonov", 1, 100);


    @Test
    void removeEmployee_employeeIsNotMap_thrownException() {
        EmployeeNotFoundException ex =
                assertThrows(EmployeeNotFoundException.class, () ->
                        underTest.removeEmployee("Valery", "Vagonov"));
        assertEquals("Сотрудника не существует", ex.getMessage());
    }

    @Test
    void removeEmployee_employeeIsInMap_employeeRemovedAndReturned() {
        underTest.addEmployee(employee.getFirstName(), employee.getSurName(),
                employee.getDepartment(), employee.getSalary());

        Employee result = underTest.removeEmployee(employee.getFirstName(), employee.getSurName());

        assertEquals(employee, result);
        assertFalse(underTest.searchAll().contains(employee));

    }

    @Test
    void addEmployee_employeeIsInMap_employeeCompare() {
        underTest.addEmployee(employee.getFirstName(), employee.getSurName(),
                employee.getDepartment(), employee.getSalary());
        EmployeeAlreadyAddedException ex =
                assertThrows(EmployeeAlreadyAddedException.class, () ->
                        underTest.addEmployee("Valery", "Vagonov", 1, 100));
        assertEquals("Уже есть такой сотрудник", ex.getMessage());
    }

    @Test
    void addEmployee_employeeAddInMap_addEmployee() {
        Employee addedEmployee = underTest.addEmployee("Valery", "Vagonov", 1, 10000);
        assertNotNull(addedEmployee);
        assertEquals("Valery", addedEmployee.getFirstName());
        assertEquals("Vagonov", addedEmployee.getSurName());
        assertEquals(1, addedEmployee.getDepartment());
        assertEquals(10000, addedEmployee.getSalary());
    }

    @Test
    public void addEmployee_employeeCompareMaxSize_throwsIfEmployeeExceedEmployeeMax() {
        for (int i = 0; i < EmployeeServiceImpl.EMPLOYEE_MAX; i++) {
            underTest.addEmployee("Ivan" + i, "Ivanov" + i, i, 10000);
        }

        EmployeeStorageFullException ex = assertThrows(EmployeeStorageFullException.class, () ->
                underTest.addEmployee("Valery", "Vagonov", 10, 60000));
        assertEquals("Превышен лимит количества сотрудников в фирме", ex.getMessage());
    }


    @Test
    void searchEmployee_SearchNotFoundInMap_SearchNonExistentEmployee() {
        EmployeeNotFoundException ex = assertThrows(EmployeeNotFoundException.class, () ->
                underTest.searchEmployee("Valery", "Ivanov"));
        assertEquals("Сотрудник не найден!", ex.getMessage());
    }

    @Test
    void searchEmployee_searchEmployeeInMap_returnedEmployee() {
        underTest.addEmployee("Valery", "Vagonov", 1, 50000);
        Employee searchedEmployee = underTest.searchEmployee("Valery", "Vagonov");

        assertNotNull(searchedEmployee);
        assertEquals("Valery", searchedEmployee.getFirstName());
        assertEquals("Vagonov", searchedEmployee.getSurName());
        assertEquals(1, searchedEmployee.getDepartment());
        assertEquals(50000, searchedEmployee.getSalary());


    }

    @Test
    void searchAll_searchAllEmployeeInMap_returnedAllEmployee() {
        underTest.addEmployee("Valery", "Ivanov", 1, 40);
        underTest.addEmployee("Valery", "Zaycev", 2, 630);

        Collection<Employee> allEmployees = underTest.searchAll();
        assertEquals(2, allEmployees.size());
    }
}