package com.demo.EmployeeManageApp.service.impl;

import com.demo.EmployeeManageApp.dto.EmployeeDto;
import com.demo.EmployeeManageApp.entity.Employee;
import com.demo.EmployeeManageApp.exception.ResourceNotFoundException;
import com.demo.EmployeeManageApp.mapper.EmployeeMapper;
import com.demo.EmployeeManageApp.repository.EmployeeRepository;
import com.demo.EmployeeManageApp.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor // constructor based dependency injection -2
public class EmployeeServiceImpl implements EmployeeService
{

    //dependency injection - 1
    private EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto)
    {

        //1 - First convert EmployeeDto to Employee JPA Entity, use Mapper class
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

        //2 - save Employee entity to database
        Employee saveEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(saveEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId)
    {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist by given Id!" + employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees()
    {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee)
    {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist by given Id!" + employeeId));

        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());

        Employee updatedEmployeeObj = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId)
    {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist by given Id!" + employeeId));

        employeeRepository.deleteById(employeeId);
    }
}

