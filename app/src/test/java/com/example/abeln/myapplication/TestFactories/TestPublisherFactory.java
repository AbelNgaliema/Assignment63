package com.example.abeln.myapplication.TestFactories;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import Domain.Publisher;
import Factories.PublisherFactory;

/**
 * Created by AbelN on 15/04/2016.
 */
public class TestPublisherFactory extends TestCase {

    @Test

    public void testCreate()
    {


        Map<String,String> values =  new HashMap<String,String>();
        values.put("name", "SkyRock");
        values.put("city","Cape Town");
        values.put("registration","c123idfr");

        Publisher publisher = PublisherFactory.createPublisher(values);

        assertEquals("SkyRock", publisher.getName());
        assertEquals("Cape Town", publisher.getCity());
        assertEquals("c123idfr", publisher.getRegistration());


    }

    @Test
    public void testUpdate()
    {
        Map<String,String> values =  new HashMap<String,String>();
        values.put("name", "SkyRock");
        values.put("city", "Cape Town");
        values.put("registration","c123idfr");

        Publisher publisher = PublisherFactory.createPublisher(values);

        Publisher newPublisher = new Publisher.Builder().copy(publisher).city("JHB").build();
        assertEquals("SkyRock", newPublisher.getName());
        assertEquals("JHB", newPublisher.getCity());
        assertEquals("c123idfr", newPublisher.getRegistration());


    }

}

