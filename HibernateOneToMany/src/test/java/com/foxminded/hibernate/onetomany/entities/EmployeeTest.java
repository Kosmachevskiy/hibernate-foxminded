package com.foxminded.hibernate.onetomany.entities;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Created by toss on 18.12.16.
 */
public class EmployeeTest {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void lastNameMayBeNoNull(){
        Employee employee = new Employee();
        employee.setFirstname("SomeName");
//        employee.setLastname("SomeLastName"); //Mistake
        employee.setSalary(600);

        Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee);

        Assert.assertFalse(constraintViolations.isEmpty());
        Assert.assertEquals("may not be null", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void nameMayBeNoNull(){
        Employee employee = new Employee();
//        employee.setFirstname("SomeName"); //Mistake
        employee.setLastname("SomeLastName");
        employee.setSalary(600);

        Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee);

        Assert.assertFalse(constraintViolations.isEmpty());
        Assert.assertEquals("may not be null", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void salaryMustBeGreaterThan(){
        Employee employee = new Employee();
        employee.setFirstname("SomeName");
        employee.setLastname("SomeLastName");
        employee.setSalary(400); //Mistake

        Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee);

        Assert.assertFalse(constraintViolations.isEmpty());
        Assert.assertEquals("must be greater than or equal to 500",
                constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void salaryMustBeLessThan(){
        Employee employee = new Employee();
        employee.setFirstname("SomeName");
        employee.setLastname("SomeLastName");
        employee.setSalary(11000); //Mistake here

        Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee);

        Assert.assertFalse(constraintViolations.isEmpty());
        Assert.assertEquals("must be less than or equal to 10000",
                constraintViolations.iterator().next().getMessage());
    }

}