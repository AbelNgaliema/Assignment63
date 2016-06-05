package com.example.abeln.myapplication.TestFactories;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import Domain.Supplier;
import Factories.SupplierFactory;

/**
 * Created by AbelN on 08/05/2016.
 */
public class TestSupplierFactory extends TestCase {

    @Test

    public void testCreate() {
        Map<String, String> values = new HashMap<String, String>();


        values.put("name", "Abel");
        values.put("regNumber", "CPO0FFFR-NGD");
        values.put("address", "Buitekant");


        //Object

        Supplier supplier = SupplierFactory.createSupplier(values,022152145);
        assertEquals("Abel", supplier.getName());
        assertEquals("CPO0FFFR-NGD", supplier.getRegistrationNumber());
        assertEquals("Buitekant", supplier.getAddress());
        assertEquals(022152145, supplier.getTelephoneNumber());

    }

    @Test

    public void testUpdate()
    {
        Map<String, String> values = new HashMap<String, String>();


        values.put("name", "Abel");
        values.put("regNumber", "CPO0FFFR-NGD");
        values.put("address", "Buitekant");


        //Object

        Supplier supplier = SupplierFactory.createSupplier(values,022152145);

        Supplier supplier1 = new Supplier.Builder().copy(supplier).registrationNumber("JFK").build();

        assertEquals("Abel", supplier1.getName());
        assertEquals("JFK", supplier1.getRegistrationNumber());
        assertEquals("Buitekant", supplier1.getAddress());
        assertEquals(022152145, supplier1.getTelephoneNumber());

    }

}
