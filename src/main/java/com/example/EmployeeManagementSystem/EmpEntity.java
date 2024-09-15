package com.example.EmployeeManagementSystem;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="emp")
public class EmpEntity {
    @Id
    @Column
    private Long id;
    @Column
    private String name;
    @Column
    private String email;
    
}
