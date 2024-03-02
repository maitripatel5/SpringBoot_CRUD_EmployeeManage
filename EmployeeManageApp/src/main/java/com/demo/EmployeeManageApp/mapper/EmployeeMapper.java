package com.demo.EmployeeManageApp.mapper;

import com.demo.EmployeeManageApp.dto.EmployeeDto;
import com.demo.EmployeeManageApp.entity.Employee;

public class EmployeeMapper
{
    //method to map Employee entity (JPA) to EmployeeDto
    public static EmployeeDto mapToEmployeeDto(Employee employee)
    {
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
    }

    //method to map EmployeeDto to Employee entity (JPA)
    public static Employee mapToEmployee(EmployeeDto employeeDto)
    {
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail()
        );
    }
}
