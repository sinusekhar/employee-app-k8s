package com.sinu.play.apps.controller;

import com.sinu.play.apps.cbo.EmployeeDTO;
import com.sinu.play.apps.cbo.Response;
import com.sinu.play.apps.dao.CacheDao;
import com.sinu.play.apps.service.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {
    private static final Logger logger = LogManager.getLogger(EmployeeController.class);
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CacheDao cacheDao;

    @PostMapping("/employee")
    @Transactional
    public Response createEmployee(@RequestBody EmployeeDTO employeeDTO){
        try {
            employeeService.createEmployee(employeeDTO);

            Response res = new Response();
            res.setCode(0);
            res.setStatus("success");
            res.setId(employeeDTO.getId());

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
            if(cacheDao.get(Integer.toString(id)) != null){
                logger.info("Returning from cache:"+id);
                return (EmployeeDTO) cacheDao.get(Integer.toString(id));
            }

            EmployeeDTO employeeDTO = employeeService.getEmployee(id);

            logger.info("Returning from database:"+id);
            cacheDao.set(employeeDTO, Integer.toString(id));
            return employeeDTO;
        }catch(Exception ex){
            ex.printStackTrace();

            throw ex;
        }
    }
}
