package com.sinu.play.apps.dao;

import com.sinu.play.apps.cbo.Employee;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Repository
@EnableTransactionManagement
public class EmployeeDaoImpl extends HibernateDaoSupport implements EmployeeDao {
    private SessionFactory sessionFactory;

    @Qualifier("sessionFactory")
    @Autowired
    public void init(SessionFactory factory) {
        setSessionFactory(factory);
        this.sessionFactory = factory;

    }

    @Override
    public void createEmployee(Employee emp) throws Exception {
        this.sessionFactory.getCurrentSession().save(emp);
    }

    @Override
    public Employee getEmployee(int id) throws Exception {
        return this.sessionFactory.getCurrentSession().load(Employee.class,id);
    }
}
