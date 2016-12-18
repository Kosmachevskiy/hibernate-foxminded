package com.foxminded.hibernate.onetomany;

import org.hibernate.*;

import com.foxminded.hibernate.onetomany.entities.Department;
import com.foxminded.hibernate.onetomany.entities.Employee;
import com.foxminded.hibernate.onetomany.util.HibernateUtil;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class App {
    static SessionFactory sf = HibernateUtil.getSessionFactory();

    public static void main(String[] args) {
        clearTables();
        fill();

        org.hibernate.classic.Session session = sf.openSession();

        /*Using HQL*/
        Query query = session.createQuery("FROM Department");
        List<Department> list = query.list();

        Query query1 = session.createQuery("FROM Employee E WHERE E.department = :dep");
        query1.setParameter("dep", list.get(0));
        List<Employee> list1 = query1.list();

        System.out.println(list1.get(0));
        System.out.println(list1.get(1));

        /*Using criteria*/
//        Criteria criteria = session.createCriteria(Employee.class);
//        criteria.add(Restrictions.eq(""))

    }

    private static void fill() {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();

        Department department = new Department();
        department.setDepartmentName("Sales");


        Employee emp1 = new Employee("Nina", "Mayers", "111");
        Employee emp2 = new Employee("Tony", "Almeida", "222");
        department.addEmployee(emp1);
        department.addEmployee(emp2);
        session.save(department);

        emp1.setDepartment(department);
        emp2.setDepartment(department);

        session.save(emp1);
        session.save(emp2);

        Department department2 = new Department();
        department2.setDepartmentName("Marketing");

        Employee emp3 = new Employee("NewNina", "Mayers", "111");
        Employee emp4 = new Employee("NewTony", "Almeida", "222");

        department2.addEmployee(emp3);
        department2.addEmployee(emp4);
        session.save(department2);

        emp3.setDepartment(department2);
        emp4.setDepartment(department2);

        session.save(emp3);
        session.save(emp4);

        transaction.commit();
        session.close();
    }

    public static void clearTables() {
        org.hibernate.classic.Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE  FROM Employee").executeUpdate();
        session.createQuery("DELETE FROM Department").executeUpdate();
        transaction.commit();
        session.close();

    }
}