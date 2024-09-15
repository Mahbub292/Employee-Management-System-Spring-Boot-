package com.example.EmployeeManagementSystem;

import java.util.List;

public interface EmpService {
    String addEmployee(Employee emp);
    List<Employee> readEmployees();
    boolean deleteEmployee(Long id);
    String update(Employee emp, Long id);

}
