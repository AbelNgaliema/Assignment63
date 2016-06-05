package com.example.abeln.myapplication.TestFactories;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import Domain.Branch;
import Factories.BranchFactory;

/**
 * Created by AbelN on 08/05/2016.
 */
public class TestBranchFactory extends TestCase {

    @Test

    public void testCreate() {
        Map<String, String> values = new HashMap<String, String>();


        values.put("manager", "Abel");
        values.put("address", "Bellville");



        //Object

        Branch branch = BranchFactory.createBranch(values, 964582);
        assertEquals("Abel", branch.getManager());
        assertEquals("Bellville", branch.getAddress());
        assertEquals(964582, branch.getTelephoneNumber());

    }

    @Test

    public void testUpdate()
    {
        Map<String, String> values = new HashMap<String, String>();


        values.put("manager", "Abel");
        values.put("address", "Bellville");



        //Object

        Branch branch = BranchFactory.createBranch(values, 964582);


        Branch branch1 = new Branch.Builder().copy(branch).address("Cape Town").build();

        assertEquals("Abel", branch1.getManager());
        assertEquals("Cape Town", branch1.getAddress());
        assertEquals(964582, branch1.getTelephoneNumber());
    }
}
