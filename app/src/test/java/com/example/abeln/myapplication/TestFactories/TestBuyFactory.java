package com.example.abeln.myapplication.TestFactories;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import Domain.Buy;
import Factories.BuyFactory;

/**
 * Created by AbelN on 15/04/2016.
 */
public class TestBuyFactory extends TestCase {


    @Test

    public void testCreate()
    {

        Map<String, String> values5 = new HashMap<String,String>();
        values5.put("mode","Cash");
        values5.put("cashier","Bingo");

        //Buy object
        Buy buy = BuyFactory.createBuy(1,values5,"BOOK1","CUSTOMERTWO");
        //Test
        assertEquals("Cash", buy.getMode());
        assertEquals("Bingo", buy.getCashier());
        assertEquals("BOOK1",buy.getBook());
        assertEquals("CUSTOMERTWO",buy.getCustomer());

    }

    @Test
    public void testUpdate()
    {

        Map<String, String> values5 = new HashMap<String,String>();
        values5.put("mode","Cash");
        values5.put("cashier","Bingo");
        Buy buy = BuyFactory.createBuy(1,values5,"BOOK1","CUSTOMERTWO");

        Buy newBuy =  new Buy.Builder().copy(buy).mode("Swipe").build();

        //Test
        assertEquals("Swipe", newBuy.getMode());
        assertEquals("Bingo", newBuy.getCashier());
<<<<<<< HEAD

=======
       assertEquals(1, newBuy.getId());
>>>>>>> 5a7ee5f277a329268c0bcc311d27c119127f9d6f




    }

}

