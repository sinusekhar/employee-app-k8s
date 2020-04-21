package com.sinu.play.apps.controller;

import com.sinu.play.apps.cbo.Employee;
import com.sinu.play.apps.cbo.EmployeeDTO;
import com.sinu.play.apps.cbo.Response;
import com.sinu.play.apps.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeDao employeeDao;

    @PostMapping("/employee")
    @Transactional
    public Response createEmployee(@RequestBody EmployeeDTO employeeDTO){
        try {
            Employee emp = new Employee();
            emp.setFirstName(employeeDTO.getFirstName());
            emp.setLastName(employeeDTO.getLastName());
            emp.setId(employeeDTO.getId());

            employeeDao.createEmployee(emp);

            Response res = new Response();
            res.setCode(0);
            res.setStatus("success");

            return res;
        }catch(Exception ex){
            ex.printStackTrace();

            Response res = new Response();
            res.setCode(1);
            res.setStatus("failed: "+ex.getMessage());

            return res;
        }
    }

    @GetMapping("/employee/{id}")
    @Transactional(readOnly = true)
    @ResponseBody
    public EmployeeDTO getEmployee(@PathVariable("id") int id) throws Exception{
        try {
            Employee emp = employeeDao.getEmployee(id);

            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setFirstName(emp.getFirstName());
            employeeDTO.setLastName(emp.getLastName());
            employeeDTO.setId(emp.getId());
            return employeeDTO;
        }catch(Exception ex){
            ex.printStackTrace();

            throw ex;
        }
    }
}
