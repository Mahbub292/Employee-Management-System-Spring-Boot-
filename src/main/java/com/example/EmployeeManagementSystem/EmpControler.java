package com.example.EmployeeManagementSystem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;






@Controller
//@RestController
public class EmpControler {

    
    //EmpService empService = new EmpServiceImplement();
    @Autowired private EmpService empService;


    @GetMapping("/login")
    public String loginPage(){
        return "login.html";
    }

    @PostMapping("/login")
    public String loginInfo(@RequestParam String username, @RequestParam String password, Model model){

           
        if ("admin".equals(username) && "admin".equals(password)) {
           // model.addAttribute("message", "Welcome, " + username + "!");
            List<Employee> employees = empService.readEmployees();
            model.addAttribute("employees", employees);
            return "home-page.html"; 
        } else {
            model.addAttribute("error", "Invalid username or password.");
            return "login.html"; 
        }
    }


    @GetMapping("/home")
    public String getHomePage(Model model) {
        List<Employee> employees = empService.readEmployees();
        model.addAttribute("employees", employees);
        return "home-page.html";
    }

    @GetMapping("/edit-info")
    public String getEditPage(Model model) {
        List<Employee> employees = empService.readEmployees();
        model.addAttribute("employees", employees);
        return "edit-employee-info.html";
    }

    @PostMapping("/edit-employee-info")
    public String editEmployeeInfo(@RequestParam Long employeeId, @RequestParam String employeeName, @RequestParam String employeeEmail, Model model) {
        System.out.println("From controller"+employeeEmail);
        Employee employee = new Employee();
        employee.setName(employeeName);
        employee.setEmail(employeeEmail);

        String message = empService.update(employee, employeeId);
        List<Employee> employees = empService.readEmployees();
        model.addAttribute("employees", employees);

        model.addAttribute("message", message);

        return "edit-employee-info.html";
    }

    @GetMapping("/add-employee")
    public String getAddEmployeePage(Model model) {
        List<Employee> employees = empService.readEmployees();
        model.addAttribute("employees", employees);
        return "add-employee.html";
    }

    @PostMapping("/add-new-employee")
    public String AddEmployeeInfo(@RequestParam Long employeeId, @RequestParam String employeeName, @RequestParam String employeeEmail, Model model) {
        
        Employee employee = new Employee();
        employee.setId(employeeId);
        employee.setName(employeeName);
        employee.setEmail(employeeEmail);

        String message = empService.addEmployee(employee);

        List<Employee> employees = empService.readEmployees();
        model.addAttribute("employees", employees);

        model.addAttribute("message", message);

        return "add-employee.html";
    }

    @GetMapping("/delete-employee")
    public String getDeleteEmployeePage(Model model) {
        List<Employee> employees = empService.readEmployees();
        model.addAttribute("employees", employees);
        return "delete-employee.html";
    }


    @PostMapping("/delete-employee-info")
    public String deleteEmployeeInfo(@RequestParam Long employeeId, Model model){
        
        if (empService.deleteEmployee(employeeId)){
            model.addAttribute("message", "Delete Successfully");
        }else{
            model.addAttribute("message", "Not Found!!");
        }
        
        List<Employee> employees = empService.readEmployees();
        model.addAttribute("employees", employees);

        return "delete-employee.html";
        
    }
    

    

}
