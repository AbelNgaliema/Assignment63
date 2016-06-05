package com.example.abeln.myapplication.TestFactories;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import Domain.Customer;
import Domain.CustomerAddress;
import Domain.PersonalInformation;
import Factories.CustomerAddressFactory;
import Factories.CustomerFactory;
import Factories.PersonalInformationFactory;

/**
 * Created by AbelN on 15/04/2016.
 */
public class TestCustomerFactory extends TestCase {

    @Test

    public void testCreate()
    {
        //Customer Address object created
        Map<String, String> values = new HashMap<String,String>();
        values.put("address", "58 Victoria Rd. Southfield");
        values.put("city", "Cape Town");

        CustomerAddress customerAddress = CustomerAddressFactory.createCustomerAddress(values, 7800);

        //PersonalInfromation Object Created
        Map<String, String> values1 = new HashMap<String,String>();
        values1.put("name","Abel");
        values1.put("surname","Ngaliema");
        values1.put("idNumber","12223944");
        values1.put("email", "abeln@dipar.co.za");

        PersonalInformation personalInformation = PersonalInformationFactory.createPersonalInformation(values1, 02100000, 0210000000);


        Customer customer = CustomerFactory.createCustomer(customerAddress,personalInformation);


        assertEquals("58 Victoria Rd. Southfield", customer.getCustomerAddress().getAddress());
        assertEquals("Cape Town", customer.getCustomerAddress().getCity());
        assertEquals(7800, customer.getCustomerAddress().getPostalCode());
        assertEquals("Abel", customer.getPersonalInformation().getName());
        assertEquals("Ngaliema", customer.getPersonalInformation().getSutname());
        assertEquals("12223944", customer.getPersonalInformation().getIdNumber());
        assertEquals("abeln@dipar.co.za", customer.getPersonalInformation().getEmailAddress());

    }

    @Test

    public void testUpdate()
    {
        //Customer Address object created
        Map<String, String> values = new HashMap<String,String>();
        values.put("address", "58 Victoria Rd. Southfield");
        values.put("city", "Cape Town");

        CustomerAddress customerAddress = CustomerAddressFactory.createCustomerAddress(values, 7800);

        //PersonalInfromation Object Created
        Map<String, String> values1 = new HashMap<String,String>();
        values1.put("name","Abel");
        values1.put("surname","Ngaliema");
        values1.put("idNumber","12223944");
        values1.put("email", "abeln@dipar.co.za");

        PersonalInformation personalInformation = PersonalInformationFactory.createPersonalInformation(values1, 02100000, 0210000000);


        Customer  customer = CustomerFactory.createCustomer( customerAddress, personalInformation);

        Map<String, String> values2 = new HashMap<String,String>();
        values2.put("address", "67 Victoria Rd. Southfield");
        values2.put("city", "Cape Town");

        CustomerAddress newCustomerAddress = CustomerAddressFactory.createCustomerAddress(values2, 7800);



        Customer newCustomer = new Customer.Builder().copy(customer).CustomerAddress(newCustomerAddress).build();


        assertEquals("67 Victoria Rd. Southfield", newCustomer.getCustomerAddress().getAddress());
        assertEquals("Cape Town", newCustomer.getCustomerAddress().getCity());
        assertEquals(7800, newCustomer.getCustomerAddress().getPostalCode());
        assertEquals("Abel", newCustomer.getPersonalInformation().getName());
        assertEquals("Ngaliema", newCustomer.getPersonalInformation().getSutname());
        assertEquals("12223944", newCustomer.getPersonalInformation().getIdNumber());
        assertEquals("abeln@dipar.co.za", newCustomer.getPersonalInformation().getEmailAddress());


    }


}

