package com.sinu.play.apps.service;

import com.sinu.play.apps.cbo.Employee;
import com.sinu.play.apps.cbo.EmployeeDTO;
import com.sinu.play.apps.dao.EmployeeDao;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmployeeService {
    private static final Logger logger = LogManager.getLogger(EmployeeService.class);
    @Autowired
    private EmployeeDao employeeDao;

    @Value("${counter.url}")
    private String counterAppUrl;

    private CloseableHttpClient httpclient = HttpClients.createDefault();

    public EmployeeDTO getEmployee(int id) throws Exception{
        Employee emp = employeeDao.getEmployee(id);

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName(emp.getFirstName());
        employeeDTO.setLastName(emp.getLastName());
        employeeDTO.setId(emp.getId());

        return employeeDTO;
    }

    public void createEmployee(EmployeeDTO employeeDTO) throws Exception{
        int id = getLatestCounterFromService();

        Employee emp = new Employee();
        emp.setFirstName(employeeDTO.getFirstName());
        emp.setLastName(employeeDTO.getLastName());
        emp.setId(id);

        employeeDTO.setId(id);

        employeeDao.createEmployee(emp);
    }

    private int getLatestCounterFromService() throws Exception{
        HttpGet httpGet = new HttpGet(counterAppUrl);
        CloseableHttpResponse response = httpclient.execute(httpGet);

        logger.info("Status code: "+response.getStatusLine());

        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            HttpEntity entity = response.getEntity();
            return Integer.parseInt(EntityUtils.toString(entity));
        }else{
            logger.info("Counter all has failed: Defaulting to system time");
            return (int)(new Date()).getTime();
        }

    }
}
