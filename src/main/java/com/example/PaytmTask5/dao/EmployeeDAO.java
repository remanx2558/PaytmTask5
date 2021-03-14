package com.example.PaytmTask5.dao;


import com.example.PaytmTask5.model.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> get();

    Employee get(int id);

    void save(Employee employee);

    void delete(int id);
}