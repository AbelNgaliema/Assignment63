package com.example.abeln.myapplication.TestFactories;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import Domain.CustomerAddress;
import Factories.CustomerAddressFactory;

/**
 * Created by AbelN on 15/04/2016.
 */
public class TestCustomerAddressFactory extends TestCase {

    @Test
    public void testCreate()
    {

        Map<String, String> values = new HashMap<String,String>();
        values.put("address", "58 Victoria Rd. Southfield");
        values.put("city", "Cape Town");

        CustomerAddress customerAddress = CustomerAddressFactory.createCustomerAddress(values,7800);

        assertEquals("58 Victoria Rd. Southfield", customerAddress.getAddress());
        assertEquals("Cape Town", customerAddress.getCity());
        assertEquals(7800, customerAddress.getPostalCode());

    }

    @Test
    public void testUpdate()
    {
        Map<String, String> values = new HashMap<String,String>();
        values.put("address", "58 Victoria Rd. Southfield");
        values.put("city", "Cape Town");

        CustomerAddress customerAddress = CustomerAddressFactory.createCustomerAddress(values,7800);

        CustomerAddress copyCustomerAddress = new CustomerAddress.Builder().copy(customerAddress).city("JHB").build();

        assertEquals("58 Victoria Rd. Southfield", copyCustomerAddress.getAddress());
        assertEquals("JHB", copyCustomerAddress.getCity());
        assertEquals(7800, copyCustomerAddress.getPostalCode());


    }

}

