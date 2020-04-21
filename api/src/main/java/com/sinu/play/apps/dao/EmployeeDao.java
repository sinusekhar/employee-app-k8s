package com.sinu.play.apps.dao;

import com.sinu.play.apps.cbo.Employee;

public interface EmployeeDao {
    public void createEmployee(Employee emp) throws Exception;
    public Employee getEmployee(int id) throws Exception;
}
