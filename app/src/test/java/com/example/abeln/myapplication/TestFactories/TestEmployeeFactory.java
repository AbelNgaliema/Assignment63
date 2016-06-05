package com.example.abeln.myapplication.TestFactories;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import Domain.Employee;
import Factories.EmployeeFactory;

/**
 * Created by AbelN on 08/05/2016.
 */
public class TestEmployeeFactory extends TestCase {


    @Test

    public void testCreate() {
        Map<String, String> values = new HashMap<String, String>();


        values.put("name", "Abel");
        values.put("surname", "Ngaliema");
        values.put("position", "Developer");
        values.put("password", "12345");
        values.put("systemName", "abeln");



        //Object

        Employee employee = EmployeeFactory.createEmployee(values, 12000);
        assertEquals("Abel", employee.getName());
        assertEquals("Ngaliema", employee.getSurname());
        assertEquals("Developer", employee.getPosition());
        assertEquals("12345", employee.getPassword());
        assertEquals("abeln", employee.getSystemName());
        assertEquals(12000.0, employee.getSalary());

    }

    @Test

    public void testUpdate()
    {
        Map<String, String> values = new HashMap<String, String>();


        values.put("name", "Abel");
        values.put("surname", "Ngaliema");
        values.put("position", "Developer");
        values.put("password", "12345");
        values.put("systemName", "abeln");


        //Object

        Employee employee = EmployeeFactory.createEmployee(values, 12000);

        Employee employee1 = new Employee.Builder().copy(employee).position("Manager").build();

        assertEquals("Abel", employee1.getName());
        assertEquals("Ngaliema", employee1.getSurname());
        assertEquals("Manager", employee1.getPosition());
        assertEquals("12345", employee.getPassword());
        assertEquals("abeln", employee.getSystemName());
        assertEquals(12000.0, employee.getSalary());
    }


}
