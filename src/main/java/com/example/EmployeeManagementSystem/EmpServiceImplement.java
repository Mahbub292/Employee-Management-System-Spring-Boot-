package com.example.EmployeeManagementSystem;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class EmpServiceImplement implements EmpService {

    List<Employee> employees = new ArrayList<>();

    @Autowired
    private EmpRepository empRepository;

    @Override
    public String addEmployee(Employee emp){
        String message;

        try{
            EmpEntity empEntity = new EmpEntity();

            BeanUtils.copyProperties(emp, empEntity);
            empRepository.save(empEntity);

            message = "Save Succesfully";
            
        }catch(Exception e){
            message = "Enter Unique Id";
        }
        return message;
        
    }

    @Override
    public List<Employee> readEmployees(){

        List<EmpEntity> empEntityList =empRepository.findAll();

        List<Employee> employees = new ArrayList<>();

        for(EmpEntity empEntity : empEntityList){

            Employee emp = new Employee();

            emp.setId(empEntity.getId());
            emp.setName(empEntity.getName());
            emp.setEmail(empEntity.getEmail());

            employees.add(emp);
        }
        return employees;
    }

    @Override
    public boolean deleteEmployee(Long id){
        boolean bool;
        try{
            EmpEntity empEntity = empRepository.findById(id).get();
            empRepository.delete(empEntity);
            bool = true;
        }catch(Exception e){
            bool = false;
        }
        return bool;
        
    }

    // @Override
    // public boolean deleteEmployee(Long id){
    //     Employee empToRemove = null;
    //     for(Employee emp : employees){
    //         if(emp.getId() == id){
    //             empToRemove = emp;
    //             break;
    //         }
    //     }
    //     if(empToRemove != null){
    //         employees.remove(empToRemove);
    //         return true;
    //     }
        
    //     return false;
    // }
    @Override
    public String update(Employee emp, Long id){
        String message;
        try{
            EmpEntity empEntity = empRepository.findById(id).get();
            if(emp.getName() != ""){
                empEntity.setName(emp.getName());
            }
            if(emp.getEmail() != ""){
                empEntity.setEmail(emp.getEmail());
            }
            empRepository.save(empEntity);
            message = "Update succesfully";
        }catch(Exception e){
            message = "Not Found";
        }
        return message;
        
        
    }

}
