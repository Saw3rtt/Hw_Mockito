package com.skypro.hw_springandmockito.Service;

import com.skypro.hw_springandmockito.Employee;
import com.skypro.hw_springandmockito.exceptions.EmployeeNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {
    @Mock
    EmployeeService employeeService;
    @InjectMocks
    DepartmentServiceImpl underTest;
    Employee ivan = new Employee("Ivan", "Kask", 1, 1030);
    Employee valery = new Employee("Valery", "Vagonov", 1, 100);
    Employee oleg = new Employee("Oleg", "Zaycev", 2, 1020);
    Collection<Employee> employees;

    @BeforeEach
    void beforeEach() {
        employees = List.of(oleg, valery, ivan);
    }

    @Test
    void searchMaxSalaryEmployee_employeeFind_returnEmployeeWithMaxSalary() {
        when(employeeService.searchAll()).thenReturn(employees);
        Employee maxSalaryEmployee = underTest.searchMaxSalaryEmployee(1);

        assertEquals(ivan, maxSalaryEmployee);
    }

    @Test
    void searchMaxSalaryEmployee_employeeNotFind_thrownEmployeeNotFindException() {
        when(employeeService.searchAll()).thenReturn(Collections.emptyList());
        EmployeeNotFoundException ex = assertThrows(EmployeeNotFoundException.class,
                () -> underTest.searchMaxSalaryEmployee(1));
        assertEquals("Нет сотрудников", ex.getMessage());
    }

    @Test
    void searchMinSalaryEmployee_employeeFind_returnEmployeeWithMinSalary() {
        when(employeeService.searchAll()).thenReturn(employees);
        Employee minSalaryEmployee = underTest.searchMinSalaryEmployee(1);

        assertEquals(valery, minSalaryEmployee);

    }

    @Test
    void searchMinSalaryEmployee_employeeNotFind_thrownEmployeeNotFindException() {
        when(employeeService.searchAll()).thenReturn(Collections.emptyList());
        EmployeeNotFoundException ex = assertThrows(EmployeeNotFoundException.class,
                () -> underTest.searchMinSalaryEmployee(1));
        assertEquals("Нет сотрудников", ex.getMessage());
    }

    @Test
    void searchAllEmployees_departmentsSizeContains_returnDepartmentsSize() {
        List<Employee> allEmployees = Arrays.asList(
                new Employee("Oleg", "Kask", 1, 331),
                new Employee("Vasya", "Smith", 1, 222),
                new Employee("Egor", "Bobov", 2, 13123)
        );

        when(employeeService.searchAll()).thenReturn(allEmployees);


        Collection<Employee> departmentEmployees = underTest.searchAllEmployees(1);


        assertEquals(2, departmentEmployees.size());
    }

    @Test
    void searchGetAllGroupingByDepartment_departmenWithAllEmployees_returnDepartmentsEmployees() {
        List<Employee> allEmployees = Arrays.asList(
                new Employee("Oleg", "Kask", 1, 331),
                new Employee("Vasya", "Smith", 1, 222),
                new Employee("Egor", "Bobov", 2, 13123)
        );

        when(employeeService.searchAll()).thenReturn(allEmployees);

        Map<Integer, List<Employee>> groupedEmployees = underTest.getAllGroupingByDepartment();

        assertEquals(2, groupedEmployees.size());
    }
}