package com.foxminded.hibernate.onetomany;

import com.foxminded.hibernate.onetomany.entities.Department;
import com.foxminded.hibernate.onetomany.entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class App {

    private static EntityManagerFactory entityManagerFactory;

    private static void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
    }

    private static void tearDown() throws Exception {
        entityManagerFactory.close();
    }

    public static void main(String[] args) throws Exception {
        setUp();
        fill();
        tearDown();
    }

    private static void fill() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        doStaff(entityManager);

        entityManager.close();
        transaction.commit();
    }

    private static void doStaff(EntityManager entityManager) {
        Department department = new Department();
        department.setDepartmentName("Sales");


        Employee emp1 = new Employee("Nina", "Mayers", "111");
        Employee emp2 = new Employee("Tony", "Almeida", "222");
        department.addEmployee(emp1);
        department.addEmployee(emp2);
        entityManager.persist(department);

        emp1.setDepartment(department);
        emp2.setDepartment(department);

        entityManager.persist(emp1);
        entityManager.persist(emp2);

        Department department2 = new Department();
        department2.setDepartmentName("Marketing");

        Employee emp3 = new Employee("NewNina", "Mayers", "111");
        Employee emp4 = new Employee("NewTony", "Almeida", "222");

        department2.addEmployee(emp3);
        department2.addEmployee(emp4);
        entityManager.persist(department2);

        emp3.setDepartment(department2);
        emp4.setDepartment(department2);

        entityManager.persist(emp3);
        entityManager.persist(emp4);
    }

}